package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmName;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.ScopeCoroutine;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u001f\u0010\u0004\u001a\u00020\u0003*\u0006\u0012\u0002\b\u00030\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001a\"\u0010\b\u001a\u0004\u0018\u00010\u0006*\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0080\u0010¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lkotlinx/coroutines/flow/internal/SafeCollector;", "Lkotlin/coroutines/CoroutineContext;", "currentContext", _UrlKt.FRAGMENT_ENCODE_SET, "checkContext", "(Lkotlinx/coroutines/flow/internal/SafeCollector;Lkotlin/coroutines/CoroutineContext;)V", "Lkotlinx/coroutines/Job;", "collectJob", "transitiveCoroutineParent", "(Lkotlinx/coroutines/Job;Lkotlinx/coroutines/Job;)Lkotlinx/coroutines/Job;", "kotlinx-coroutines-core"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class SafeCollector_commonKt {
    /* renamed from: $r8$lambda$0mv-1vIMKs5GqhpegvxZ5k4J09Y, reason: not valid java name */
    public static int m3553$r8$lambda$0mv1vIMKs5GqhpegvxZ5k4J09Y(SafeCollector r2, int r3, CoroutineContext.Element r4) {
        CoroutineContext.Key<?> r0 = r4.getKey();
        CoroutineContext.Element r22 = r2.collectContext.get(r0);
        if (r0 == Job.Key) goto L9;
        if (r4 == r22) goto L8;
        return Integer.MIN_VALUE;
    L8:
        return r3 + 1;
    L9:
        Job r23 = (Job) r22;
        Job r42 = transitiveCoroutineParent((Job) r4, r23);
        if (r42 != r23) goto L16;
        if (r23 != null) goto L14;
        return r3;
    L14:
        return r3 + 1;
    L16:
        throw new IllegalStateException(("Flow invariant is violated:\n\t\tEmission from another coroutine is detected.\n\t\tChild of " + r42 + ", expected child of " + r23 + ".\n\t\tFlowCollector is not thread-safe and concurrent emissions are prohibited.\n\t\tTo mitigate this restriction please use 'channelFlow' builder instead of 'flow'").toString());
    }

    @JvmName(name = "checkContext")
    public static final void checkContext(SafeCollector<?> r3, CoroutineContext r4) {
        if (((Number) r4.fold(0, new SafeCollector_commonKt$$ExternalSyntheticLambda0(r3))).intValue() != r3.collectContextSize) goto L6;
        return;
    L6:
        throw new IllegalStateException(("Flow invariant is violated:\n\t\tFlow was collected in " + r3.collectContext + ",\n\t\tbut emission happened in " + r4 + ".\n\t\tPlease refer to 'flow' documentation or use 'flowOn' instead").toString());
    }

    public static final Job transitiveCoroutineParent(Job r1, Job r2) {
    L2:
        if (r1 == null) goto L3;
        if (r1 == r2) goto L9;
        if ((r1 instanceof ScopeCoroutine) == false) goto L9;
        r1 = ((ScopeCoroutine) r1).getParent();
    L9:
        return r1;
    L3:
        return null;
    }
}
