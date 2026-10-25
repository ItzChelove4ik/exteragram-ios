package kotlinx.coroutines.internal;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CompletionStateKt;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.DispatchException;
import kotlinx.coroutines.EventLoop;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.ThreadLocalEventLoop;
import kotlinx.coroutines.UndispatchedCoroutine;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a'\u0010\u0007\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\n\u0010\u0005\u001a\u00060\u0003j\u0002`\u0004H\u0000¢\u0006\u0004\b\u0007\u0010\b\u001a\u001b\u0010\n\u001a\u00020\t*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\n\u0010\u000b\u001a-\u0010\u0010\u001a\u00020\u0006\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u00028\u00000\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000eH\u0000¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u0019\u0010\u0013\u001a\u00020\t*\b\u0012\u0004\u0012\u00020\u00060\u0012H\u0000¢\u0006\u0004\b\u0013\u0010\u0014\"\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017\"\u0014\u0010\u0018\u001a\u00020\u00158\u0000X\u0081\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0017¨\u0006\u0019"}, d2 = {"Lkotlinx/coroutines/CoroutineDispatcher;", "Lkotlin/coroutines/CoroutineContext;", "context", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "runnable", _UrlKt.FRAGMENT_ENCODE_SET, "safeDispatch", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlin/coroutines/CoroutineContext;Ljava/lang/Runnable;)V", _UrlKt.FRAGMENT_ENCODE_SET, "safeIsDispatchNeeded", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlin/coroutines/CoroutineContext;)Z", "T", "Lkotlin/coroutines/Continuation;", "Lkotlin/Result;", "result", "resumeCancellableWithInternal", "(Lkotlin/coroutines/Continuation;Ljava/lang/Object;)V", "Lkotlinx/coroutines/internal/DispatchedContinuation;", "yieldUndispatched", "(Lkotlinx/coroutines/internal/DispatchedContinuation;)Z", "Lkotlinx/coroutines/internal/Symbol;", "UNDEFINED", "Lkotlinx/coroutines/internal/Symbol;", "REUSABLE_CLAIMED", "kotlinx-coroutines-core"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nDispatchedContinuation.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DispatchedContinuation.kt\nkotlinx/coroutines/internal/DispatchedContinuationKt\n+ 2 DispatchedContinuation.kt\nkotlinx/coroutines/internal/DispatchedContinuation\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 DispatchedTask.kt\nkotlinx/coroutines/DispatchedTaskKt\n+ 5 CoroutineContext.kt\nkotlinx/coroutines/CoroutineContextKt\n*L\n1#1,322:1\n302#1,5:330\n307#1,12:336\n319#1:404\n306#1:406\n307#1,12:408\n319#1:437\n207#2,7:323\n214#2,23:351\n237#2,2:384\n239#2:388\n217#2:389\n219#2:405\n1#3:335\n1#3:407\n1#3:438\n184#4,3:348\n187#4,14:390\n184#4,17:420\n184#4,17:439\n115#5,10:374\n126#5,2:386\n*S KotlinDebug\n*F\n+ 1 DispatchedContinuation.kt\nkotlinx/coroutines/internal/DispatchedContinuationKt\n*L\n277#1:330,5\n277#1:336,12\n277#1:404\n292#1:406\n292#1:408,12\n292#1:437\n277#1:323,7\n277#1:351,23\n277#1:384,2\n277#1:388\n277#1:389\n277#1:405\n277#1:335\n292#1:407\n277#1:348,3\n277#1:390,14\n292#1:420,17\n318#1:439,17\n277#1:374,10\n277#1:386,2\n*E\n"})
/* loaded from: classes.dex */
public abstract class DispatchedContinuationKt {

    @JvmField
    public static final Symbol REUSABLE_CLAIMED = null;
    private static final Symbol UNDEFINED = null;

    static {
        UNDEFINED = new Symbol("UNDEFINED");
        REUSABLE_CLAIMED = new Symbol("REUSABLE_CLAIMED");
    }

    public static final /* synthetic */ Symbol access$getUNDEFINED$p() {
        return UNDEFINED;
    }

    public static final <T> void resumeCancellableWithInternal(Continuation<? super T> r6, Object r7) {
        if ((r6 instanceof DispatchedContinuation) == false) goto L43;
        DispatchedContinuation r62 = (DispatchedContinuation) r6;
        Object r0 = CompletionStateKt.toState(r7);
        if (safeIsDispatchNeeded(r62.dispatcher, r62.getContext()) == false) goto L7;
        r62._state = r0;
        r62.resumeMode = 1;
        safeDispatch(r62.dispatcher, r62.getContext(), r62);
        return;
    L7:
        EventLoop r1 = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
        if (r1.isUnconfinedLoopActive() == false) goto L10;
        r62._state = r0;
        r62.resumeMode = 1;
        r1.dispatchUnconfined(r62);
        return;
    L10:
        r1.incrementUseCount(true);
        Job r3 = (Job) r62.getContext().get(Job.Key);     // Catch: Throwable -> L16
        if (r3 != null) goto L14;
    L18:
        Continuation<T> r02 = r62.continuation;     // Catch: Throwable -> L16
        Object r32 = r62.countOrElement;     // Catch: Throwable -> L16
        CoroutineContext r4 = r02.getContext();     // Catch: Throwable -> L16
        Object r33 = ThreadContextKt.updateThreadContext(r4, r32);     // Catch: Throwable -> L16
        if (r33 == ThreadContextKt.NO_THREAD_ELEMENTS) goto L22;
        UndispatchedCoroutine<?> r03 = CoroutineContextKt.updateUndispatchedCompletion(r02, r4, r33);     // Catch: Throwable -> L16
    L46:
        r62.continuation.resumeWith(r7);     // Catch: Throwable -> L31
        Unit r72 = Unit.INSTANCE;     // Catch: Throwable -> L31
        if (r03 != null) goto L26;
    L27:
        ThreadContextKt.restoreThreadContext(r4, r33);     // Catch: Throwable -> L16
    L29:
        if (r1.processUnconfinedEvent() == true) goto L29;
    L30:
        r1.decrementUseCount(true);
        return;
    L26:
        if (r03.clearThreadContext() == false) goto L29;
    L31:
        th = move-exception;
        if (r03 != null) goto L34;
    L35:
        ThreadContextKt.restoreThreadContext(r4, r33);     // Catch: Throwable -> L16
    L36:
        throw th;     // Catch: Throwable -> L16
    L34:
        if (r03.clearThreadContext() == false) goto L36;
    L22:
        r03 = null;
        goto L46
    L14:
        if (r3.isActive() == true) goto L18;
        CancellationException r73 = r3.getCancellationException();     // Catch: Throwable -> L16
        r62.cancelCompletedResult$kotlinx_coroutines_core(r0, r73);     // Catch: Throwable -> L16
        Result.Companion r04 = Result.Companion;     // Catch: Throwable -> L16
        r62.resumeWith(Result.m2018constructorimpl(ResultKt.createFailure(r73)));     // Catch: Throwable -> L16
    L16:
        th = move-exception;
        r62.handleFatalException$kotlinx_coroutines_core(th);     // Catch: Throwable -> L40
    L40:
        th = move-exception;
        r1.decrementUseCount(true);
        throw th;
    L43:
        r6.resumeWith(r7);
    }

    public static final void safeDispatch(CoroutineDispatcher r1, CoroutineContext r2, Runnable r3) {
        r1.dispatch(r2, r3);     // Catch: Throwable -> L4
        return;
    L4:
        th = move-exception;
        throw new DispatchException(th, r1, r2);
    }

    public static final boolean safeIsDispatchNeeded(CoroutineDispatcher r2, CoroutineContext r3) {
        return r2.isDispatchNeeded(r3);
    L4:
        th = move-exception;
        throw new DispatchException(th, r2, r3);
    }

    public static final boolean yieldUndispatched(DispatchedContinuation<? super Unit> r5) {
        Unit r0 = Unit.INSTANCE;
        EventLoop r1 = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
        if (r1.isUnconfinedQueueEmpty() == false) goto L6;
        return false;
    L6:
        if (r1.isUnconfinedLoopActive() == false) goto L8;
        r5._state = r0;
        r5.resumeMode = 1;
        r1.dispatchUnconfined(r5);
        return true;
    L8:
        r1.incrementUseCount(true);
        r5.run();     // Catch: Throwable -> L13
    L10:
        if (r1.processUnconfinedEvent() == true) goto L10;
    L12:
        r1.decrementUseCount(true);
        return false;
    L13:
        th = move-exception;
        r5.handleFatalException$kotlinx_coroutines_core(th);     // Catch: Throwable -> L17
    L17:
        th = move-exception;
        r1.decrementUseCount(true);
        throw th;
    }
}
