package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.DispatchedContinuationKt;
import kotlinx.coroutines.internal.ThreadContextKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\u001a'\u0010\u0005\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a5\u0010\u000b\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00072\u0006\u0010\n\u001a\u00020\tH\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a\u0017\u0010\r\u001a\u00020\u0004*\u0006\u0012\u0002\b\u00030\u0001H\u0002¢\u0006\u0004\b\r\u0010\u000e\"\u0018\u0010\u000f\u001a\u00020\t*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010\"\u0018\u0010\u0011\u001a\u00020\t*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0010¨\u0006\u0012"}, d2 = {"T", "Lkotlinx/coroutines/DispatchedTask;", _UrlKt.FRAGMENT_ENCODE_SET, "mode", _UrlKt.FRAGMENT_ENCODE_SET, "dispatch", "(Lkotlinx/coroutines/DispatchedTask;I)V", "Lkotlin/coroutines/Continuation;", "delegate", _UrlKt.FRAGMENT_ENCODE_SET, "undispatched", "resume", "(Lkotlinx/coroutines/DispatchedTask;Lkotlin/coroutines/Continuation;Z)V", "resumeUnconfined", "(Lkotlinx/coroutines/DispatchedTask;)V", "isCancellableMode", "(I)Z", "isReusableMode", "kotlinx-coroutines-core"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nDispatchedTask.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DispatchedTask.kt\nkotlinx/coroutines/DispatchedTaskKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 DispatchedContinuation.kt\nkotlinx/coroutines/internal/DispatchedContinuation\n+ 4 CoroutineContext.kt\nkotlinx/coroutines/CoroutineContextKt\n+ 5 StackTraceRecovery.kt\nkotlinx/coroutines/internal/StackTraceRecoveryKt\n*L\n1#1,220:1\n184#1,17:238\n1#2:221\n236#3:222\n237#3,2:233\n239#3:237\n115#4,10:223\n126#4,2:235\n57#5,2:255\n*S KotlinDebug\n*F\n+ 1 DispatchedTask.kt\nkotlinx/coroutines/DispatchedTaskKt\n*L\n174#1:238,17\n162#1:222\n162#1:233,2\n162#1:237\n162#1:223,10\n162#1:235,2\n204#1:255,2\n*E\n"})
/* loaded from: classes.dex */
public abstract class DispatchedTaskKt {
    public static final <T> void dispatch(DispatchedTask<? super T> r3, int r4) {
        Continuation<? super T> r0 = r3.getDelegate$kotlinx_coroutines_core();
        if (r4 != 4) goto L5;
        boolean r1 = true;
    L6:
        if (r1 == false) goto L8;
    L17:
        resume(r3, r0, r1);
        return;
    L8:
        if ((r0 instanceof DispatchedContinuation) == false) goto L17;
        if (isCancellableMode(r4) != isCancellableMode(r3.resumeMode)) goto L17;
        DispatchedContinuation r02 = (DispatchedContinuation) r0;
        CoroutineDispatcher r42 = r02.dispatcher;
        CoroutineContext r03 = r02.getContext();
        if (DispatchedContinuationKt.safeIsDispatchNeeded(r42, r03) == false) goto L15;
        DispatchedContinuationKt.safeDispatch(r42, r03, r3);
        return;
    L15:
        resumeUnconfined(r3);
        return;
    L5:
        r1 = false;
        goto L6
    }

    public static final boolean isCancellableMode(int r2) {
        if (r2 != 1) goto L5;
    L9:
        return true;
    L5:
        if (r2 == 2) goto L9;
        return false;
    }

    public static final boolean isReusableMode(int r1) {
        if (r1 != 2) goto L6;
        return true;
    L6:
        return false;
    }

    public static final <T> void resume(DispatchedTask<? super T> r3, Continuation<? super T> r4, boolean r5) {
        Object r0 = r3.takeState$kotlinx_coroutines_core();
        Throwable r1 = r3.getExceptionalResult$kotlinx_coroutines_core(r0);
        if (r1 == null) goto L6;
        Result.Companion r32 = Result.Companion;
        Object r33 = ResultKt.createFailure(r1);
    L5:
        Object r34 = Result.m2018constructorimpl(r33);
        if (r5 == false) goto L26;
        DispatchedContinuation r42 = (DispatchedContinuation) r4;
        Continuation<T> r52 = r42.continuation;
        Object r02 = r42.countOrElement;
        CoroutineContext r12 = r52.getContext();
        Object r03 = ThreadContextKt.updateThreadContext(r12, r02);
        if (r03 == ThreadContextKt.NO_THREAD_ELEMENTS) goto L11;
        UndispatchedCoroutine<?> r53 = CoroutineContextKt.updateUndispatchedCompletion(r52, r12, r03);
    L28:
        r42.continuation.resumeWith(r34);     // Catch: Throwable -> L20
        Unit r35 = Unit.INSTANCE;     // Catch: Throwable -> L20
        if (r53 != null) goto L15;
    L18:
        ThreadContextKt.restoreThreadContext(r12, r03);
        return;
    L15:
        if (r53.clearThreadContext() == true) goto L18;
        return;
    L20:
        th = move-exception;
        if (r53 != null) goto L23;
    L24:
        ThreadContextKt.restoreThreadContext(r12, r03);
    L25:
        throw th;
    L23:
        if (r53.clearThreadContext() == false) goto L25;
    L11:
        r53 = null;
        goto L28
    L26:
        r4.resumeWith(r34);
        return;
    L6:
        Result.Companion r13 = Result.Companion;
        r33 = r3.getSuccessfulResult$kotlinx_coroutines_core(r0);
        goto L5
    }

    private static final void resumeUnconfined(DispatchedTask<?> r3) {
        EventLoop r0 = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
        if (r0.isUnconfinedLoopActive() == false) goto L6;
        r0.dispatchUnconfined(r3);
        return;
    L6:
        r0.incrementUseCount(true);
        resume(r3, r3.getDelegate$kotlinx_coroutines_core(), true);     // Catch: Throwable -> L11
    L8:
        if (r0.processUnconfinedEvent() == true) goto L8;
    L10:
        r0.decrementUseCount(true);
        return;
    L11:
        th = move-exception;
        r3.handleFatalException$kotlinx_coroutines_core(th);     // Catch: Throwable -> L15
    L15:
        th = move-exception;
        r0.decrementUseCount(true);
        throw th;
    }
}
