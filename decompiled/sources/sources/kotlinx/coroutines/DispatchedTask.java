package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.ThreadContextKt;
import kotlinx.coroutines.scheduling.Task;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\f\b \u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00060\u0002j\u0002`\u0003B\u0011\b\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\f\u001a\u0004\u0018\u00010\rH ¢\u0006\u0002\b\u000eJ\u001f\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0012\u001a\u00020\u0013H\u0010¢\u0006\u0002\b\u0014J\u001f\u0010\u0015\u001a\u0002H\u0001\"\u0004\b\u0001\u0010\u00012\b\u0010\u0016\u001a\u0004\u0018\u00010\rH\u0010¢\u0006\u0004\b\u0017\u0010\u0018J\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u00132\b\u0010\u0016\u001a\u0004\u0018\u00010\rH\u0010¢\u0006\u0002\b\u001aJ\u0006\u0010\u001b\u001a\u00020\u0010J\u0015\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u0013H\u0000¢\u0006\u0002\b\u001eR\u0012\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\tX \u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u001f"}, d2 = {"Lkotlinx/coroutines/DispatchedTask;", "T", "Lkotlinx/coroutines/scheduling/Task;", "Lkotlinx/coroutines/SchedulerTask;", "resumeMode", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(I)V", "delegate", "Lkotlin/coroutines/Continuation;", "getDelegate$kotlinx_coroutines_core", "()Lkotlin/coroutines/Continuation;", "takeState", _UrlKt.FRAGMENT_ENCODE_SET, "takeState$kotlinx_coroutines_core", "cancelCompletedResult", _UrlKt.FRAGMENT_ENCODE_SET, "takenState", "cause", _UrlKt.FRAGMENT_ENCODE_SET, "cancelCompletedResult$kotlinx_coroutines_core", "getSuccessfulResult", "state", "getSuccessfulResult$kotlinx_coroutines_core", "(Ljava/lang/Object;)Ljava/lang/Object;", "getExceptionalResult", "getExceptionalResult$kotlinx_coroutines_core", "run", "handleFatalException", "exception", "handleFatalException$kotlinx_coroutines_core", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nDispatchedTask.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DispatchedTask.kt\nkotlinx/coroutines/DispatchedTask\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 CoroutineContext.kt\nkotlinx/coroutines/CoroutineContextKt\n+ 4 DispatchedTask.kt\nkotlinx/coroutines/DispatchedTaskKt\n+ 5 StackTraceRecovery.kt\nkotlinx/coroutines/internal/StackTraceRecoveryKt\n*L\n1#1,220:1\n1#2:221\n115#3,10:222\n126#3,2:236\n204#4:232\n205#4:235\n57#5,2:233\n*S KotlinDebug\n*F\n+ 1 DispatchedTask.kt\nkotlinx/coroutines/DispatchedTask\n*L\n82#1:222,10\n82#1:236,2\n95#1:232\n95#1:235\n95#1:233,2\n*E\n"})
/* loaded from: classes.dex */
public abstract class DispatchedTask<T> extends Task {

    @JvmField
    public int resumeMode;

    public DispatchedTask(int r1) {
        this.resumeMode = r1;
    }

    public void cancelCompletedResult$kotlinx_coroutines_core(Object r1, Throwable r2) {
    }

    public abstract Continuation<T> getDelegate$kotlinx_coroutines_core();

    public Throwable getExceptionalResult$kotlinx_coroutines_core(Object r2) {
        if ((r2 instanceof CompletedExceptionally) == false) goto L5;
        CompletedExceptionally r22 = (CompletedExceptionally) r2;
    L6:
        if (r22 != null) goto L8;
        return null;
    L8:
        return r22.cause;
    L5:
        r22 = null;
        goto L6
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> T getSuccessfulResult$kotlinx_coroutines_core(Object r1) {
        return r1;
    }

    public final void handleFatalException$kotlinx_coroutines_core(Throwable r4) {
        CoroutineExceptionHandlerKt.handleCoroutineException(getDelegate$kotlinx_coroutines_core().getContext(), new CoroutinesInternalError("Fatal exception in coroutines machinery for " + this + ". Please read KDoc to 'handleFatalException' method and report this incident to maintainers", r4));
    }

    @Override // java.lang.Runnable
    public final void run() {
        DispatchedContinuation r0 = (DispatchedContinuation) getDelegate$kotlinx_coroutines_core();     // Catch: Throwable -> L6 DispatchException -> L8
        Continuation<T> r1 = r0.continuation;     // Catch: Throwable -> L6 DispatchException -> L8
        Object r02 = r0.countOrElement;     // Catch: Throwable -> L6 DispatchException -> L8
        CoroutineContext r2 = r1.getContext();     // Catch: Throwable -> L6 DispatchException -> L8
        Object r03 = ThreadContextKt.updateThreadContext(r2, r02);     // Catch: Throwable -> L6 DispatchException -> L8
        Job r4 = null;
        if (r03 == ThreadContextKt.NO_THREAD_ELEMENTS) goto L10;
        UndispatchedCoroutine<?> r3 = CoroutineContextKt.updateUndispatchedCompletion(r1, r2, r03);     // Catch: Throwable -> L6 DispatchException -> L8
    L39:
        CoroutineContext r5 = r1.getContext();     // Catch: Throwable -> L16
        Object r6 = takeState$kotlinx_coroutines_core();     // Catch: Throwable -> L16
        Throwable r7 = getExceptionalResult$kotlinx_coroutines_core(r6);     // Catch: Throwable -> L16
        if (r7 == null) goto L14;
    L18:
        if (r4 != null) goto L20;
    L22:
        if (r7 == null) goto L24;
        Result.Companion r42 = Result.Companion;     // Catch: Throwable -> L16
        r1.resumeWith(Result.m2018constructorimpl(ResultKt.createFailure(r7)));     // Catch: Throwable -> L16
    L25:
        Unit r12 = Unit.INSTANCE;     // Catch: Throwable -> L16
        if (r3 != null) goto L28;
    L29:
        ThreadContextKt.restoreThreadContext(r2, r03);     // Catch: Throwable -> L6 DispatchException -> L8
        return;
    L28:
        if (r3.clearThreadContext() == true) goto L29;
        return;
    L24:
        Result.Companion r43 = Result.Companion;     // Catch: Throwable -> L16
        r1.resumeWith(Result.m2018constructorimpl(getSuccessfulResult$kotlinx_coroutines_core(r6)));     // Catch: Throwable -> L16
        goto L25
    L20:
        if (r4.isActive() == true) goto L22;
        CancellationException r44 = r4.getCancellationException();     // Catch: Throwable -> L16
        cancelCompletedResult$kotlinx_coroutines_core(r6, r44);     // Catch: Throwable -> L16
        Result.Companion r52 = Result.Companion;     // Catch: Throwable -> L16
        r1.resumeWith(Result.m2018constructorimpl(ResultKt.createFailure(r44)));     // Catch: Throwable -> L16
        goto L25
    L14:
        if (DispatchedTaskKt.isCancellableMode(this.resumeMode) == false) goto L18;
        r4 = (Job) r5.get(Job.Key);     // Catch: Throwable -> L16
    L16:
        th = move-exception;
        if (r3 != null) goto L33;
    L34:
        ThreadContextKt.restoreThreadContext(r2, r03);     // Catch: Throwable -> L6 DispatchException -> L8
    L35:
        throw th;     // Catch: Throwable -> L6 DispatchException -> L8
    L33:
        if (r3.clearThreadContext() == false) goto L35;
    L10:
        r3 = null;
    L8:
        e = move-exception;
        CoroutineExceptionHandlerKt.handleCoroutineException(getDelegate$kotlinx_coroutines_core().getContext(), e.getCause());
        return;
    L6:
        th = move-exception;
        handleFatalException$kotlinx_coroutines_core(th);
    }

    public abstract Object takeState$kotlinx_coroutines_core();
}
