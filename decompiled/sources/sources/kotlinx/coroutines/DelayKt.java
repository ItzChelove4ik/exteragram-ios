package kotlinx.coroutines;

import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\"\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0018\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0086@¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0013\u0010\b\u001a\u00020\u0000*\u00020\u0005H\u0000¢\u0006\u0004\b\u0006\u0010\u0007\"\u0018\u0010\u0003\u001a\u00020\n*\u00020\t8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {_UrlKt.FRAGMENT_ENCODE_SET, "timeMillis", _UrlKt.FRAGMENT_ENCODE_SET, "delay", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlin/time/Duration;", "toDelayMillis-LRDsOJo", "(J)J", "toDelayMillis", "Lkotlin/coroutines/CoroutineContext;", "Lkotlinx/coroutines/Delay;", "getDelay", "(Lkotlin/coroutines/CoroutineContext;)Lkotlinx/coroutines/Delay;", "kotlinx-coroutines-core"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nDelay.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Delay.kt\nkotlinx/coroutines/DelayKt\n+ 2 CancellableContinuation.kt\nkotlinx/coroutines/CancellableContinuationKt\n*L\n1#1,159:1\n433#2,10:160\n433#2,10:170\n*S KotlinDebug\n*F\n+ 1 Delay.kt\nkotlinx/coroutines/DelayKt\n*L\n103#1:160,10\n123#1:170,10\n*E\n"})
/* loaded from: classes5.dex */
public abstract class DelayKt {
    public static final Object delay(long r3, Continuation<? super Unit> r5) {
        if (r3 <= 0) goto L5;
        CancellableContinuationImpl r0 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(r5), 1);
        r0.initCancellability();
        if (r3 >= LongCompanionObject.MAX_VALUE) goto L9;
        getDelay(r0.getContext()).scheduleResumeAfterDelay(r3, r0);
    L9:
        Object r32 = r0.getResult();
        if (r32 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L13;
        DebugProbesKt.probeCoroutineSuspended(r5);
    L13:
        if (r32 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L16;
        return r32;
    L16:
        return Unit.INSTANCE;
    L5:
        return Unit.INSTANCE;
    }

    public static final Delay getDelay(CoroutineContext r1) {
        CoroutineContext.Element r12 = r1.get(ContinuationInterceptor.Key);
        if ((r12 instanceof Delay) == false) goto L5;
        Delay r13 = (Delay) r12;
    L6:
        if (r13 == null) goto L8;
        return r13;
    L8:
        return DefaultExecutorKt.getDefaultDelay();
    L5:
        r13 = null;
        goto L6
    }

    /* renamed from: toDelayMillis-LRDsOJo, reason: not valid java name */
    public static final long m3522toDelayMillisLRDsOJo(long r3) {
        boolean r0 = Duration.m3402isPositiveimpl(r3);
        if (r0 == true) goto L5;
        if (r0 == true) goto L9;
        return 0;
    L9:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    L5:
        return Duration.m3386getInWholeMillisecondsimpl(Duration.m3404plusLRDsOJo(r3, DurationKt.toDuration(999999, DurationUnit.NANOSECONDS)));
    }
}
