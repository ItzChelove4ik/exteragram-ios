package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function2;
import kotlin.time.DurationKt$$ExternalSyntheticBUOutline0;
import kotlinx.coroutines.internal.ContextScope;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a\r\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0001\u0010\u0002\u001aL\u0010\t\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00032'\u0010\b\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004¢\u0006\u0002\b\u0007H\u0086@\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\b\t\u0010\n\u001a\u0015\u0010\r\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000e\u001a#\u0010\u0013\u001a\u00020\u0012*\u00020\u00002\u0010\b\u0002\u0010\u0011\u001a\n\u0018\u00010\u000fj\u0004\u0018\u0001`\u0010¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u0011\u0010\u0015\u001a\u00020\u0012*\u00020\u0000¢\u0006\u0004\b\u0015\u0010\u0016\"\u001b\u0010\u0018\u001a\u00020\u0017*\u00020\u00008F¢\u0006\f\u0012\u0004\b\u001a\u0010\u0016\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001b"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "MainScope", "()Lkotlinx/coroutines/CoroutineScope;", "R", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ExtensionFunctionType;", "block", "coroutineScope", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlin/coroutines/CoroutineContext;", "context", "CoroutineScope", "(Lkotlin/coroutines/CoroutineContext;)Lkotlinx/coroutines/CoroutineScope;", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "cause", _UrlKt.FRAGMENT_ENCODE_SET, "cancel", "(Lkotlinx/coroutines/CoroutineScope;Ljava/util/concurrent/CancellationException;)V", "ensureActive", "(Lkotlinx/coroutines/CoroutineScope;)V", _UrlKt.FRAGMENT_ENCODE_SET, "isActive", "(Lkotlinx/coroutines/CoroutineScope;)Z", "isActive$annotations", "kotlinx-coroutines-core"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class CoroutineScopeKt {
    public static final CoroutineScope CoroutineScope(CoroutineContext r3) {
        if (r3.get(Job.Key) != null) goto L7;
        r3 = r3.plus(JobKt.Job$default(null, 1, null));
    L7:
        return new ContextScope(r3);
    }

    public static final CoroutineScope MainScope() {
        return new ContextScope(SupervisorKt.SupervisorJob$default(null, 1, null).plus(Dispatchers.getMain()));
    }

    public static final void cancel(CoroutineScope r2, CancellationException r3) {
        Job r0 = (Job) r2.getCoroutineContext().get(Job.Key);
        if (r0 == null) goto L6;
        r0.cancel(r3);
        return;
    L6:
        DurationKt$$ExternalSyntheticBUOutline0.m("Scope cannot be cancelled because it does not have a job: ", r2);
    }

    public static /* synthetic */ void cancel$default(CoroutineScope r0, CancellationException r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L5;
        r1 = null;
    L5:
        cancel(r0, r1);
    }

    public static final <R> Object coroutineScope(Function2<? super CoroutineScope, ? super Continuation<? super R>, ? extends Object> r2, Continuation<? super R> r3) {
        ScopeCoroutine r0 = new ScopeCoroutine(r3.getContext(), r3);
        Object r22 = UndispatchedKt.startUndispatchedOrReturn(r0, r0, r2);
        if (r22 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L5;
        DebugProbesKt.probeCoroutineSuspended(r3);
    L5:
        return r22;
    }

    public static final void ensureActive(CoroutineScope r0) {
        JobKt.ensureActive(r0.getCoroutineContext());
    }

    public static final boolean isActive(CoroutineScope r1) {
        Job r12 = (Job) r1.getCoroutineContext().get(Job.Key);
        if (r12 != null) goto L5;
        return true;
    L5:
        return r12.isActive();
    }
}
