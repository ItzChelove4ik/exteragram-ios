package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.DispatchedContinuationKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a\u000e\u0010\u0000\u001a\u00020\u0001H\u0086@¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"yield", _UrlKt.FRAGMENT_ENCODE_SET, "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public abstract class YieldKt {
    public static final Object yield(Continuation<? super Unit> r4) {
        CoroutineContext r0 = r4.getContext();
        JobKt.ensureActive(r0);
        Continuation r1 = IntrinsicsKt.intercepted(r4);
        if ((r1 instanceof DispatchedContinuation) == false) goto L5;
        DispatchedContinuation r12 = (DispatchedContinuation) r1;
    L6:
        if (r12 != null) goto L9;
        Object r02 = Unit.INSTANCE;
    L19:
        if (r02 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L22;
        DebugProbesKt.probeCoroutineSuspended(r4);
    L22:
        if (r02 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L25;
        return r02;
    L25:
        return Unit.INSTANCE;
    L9:
        if (DispatchedContinuationKt.safeIsDispatchNeeded(r12.dispatcher, r0) == false) goto L11;
        r12.dispatchYield$kotlinx_coroutines_core(r0, Unit.INSTANCE);
    L17:
        r02 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        goto L19
    L11:
        YieldContext r2 = new YieldContext();
        CoroutineContext r03 = r0.plus(r2);
        Unit r3 = Unit.INSTANCE;
        r12.dispatchYield$kotlinx_coroutines_core(r03, r3);
        if (r2.dispatcherWasUnconfined == false) goto L17;
        if (DispatchedContinuationKt.yieldUndispatched(r12) == false) goto L16;
        r02 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        goto L19
    L16:
        r02 = r3;
        goto L19
    L5:
        r12 = null;
        goto L6
    }
}
