package kotlinx.coroutines;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.internal.ConcurrentKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u001c\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\n\u0010\r\u001a\u00060\u000ej\u0002`\u000fH\u0016J\u001e\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\n0\u0014H\u0016J$\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u00122\n\u0010\r\u001a\u00060\u000ej\u0002`\u000f2\u0006\u0010\u000b\u001a\u00020\fH\u0016J.\u0010\u0017\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0018*\u00020\u00192\n\u0010\r\u001a\u00060\u000ej\u0002`\u000f2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0018\u0010\u001a\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\nH\u0016J\b\u0010\u001e\u001a\u00020\u001fH\u0016J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0096\u0002J\b\u0010$\u001a\u00020%H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006&"}, d2 = {"Lkotlinx/coroutines/ExecutorCoroutineDispatcherImpl;", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "Lkotlinx/coroutines/Delay;", "executor", "Ljava/util/concurrent/Executor;", "<init>", "(Ljava/util/concurrent/Executor;)V", "getExecutor", "()Ljava/util/concurrent/Executor;", "dispatch", _UrlKt.FRAGMENT_ENCODE_SET, "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "scheduleResumeAfterDelay", "timeMillis", _UrlKt.FRAGMENT_ENCODE_SET, "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "invokeOnTimeout", "Lkotlinx/coroutines/DisposableHandle;", "scheduleBlock", "Ljava/util/concurrent/ScheduledFuture;", "Ljava/util/concurrent/ScheduledExecutorService;", "cancelJobOnRejection", "exception", "Ljava/util/concurrent/RejectedExecutionException;", "close", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "equals", _UrlKt.FRAGMENT_ENCODE_SET, "other", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ExecutorCoroutineDispatcherImpl extends ExecutorCoroutineDispatcher implements Delay {
    private final Executor executor;

    public ExecutorCoroutineDispatcherImpl(Executor r1) {
        this.executor = r1;
        ConcurrentKt.removeFutureOnCancel(getExecutor());
    }

    private final void cancelJobOnRejection(CoroutineContext r1, RejectedExecutionException r2) {
        JobKt.cancel(r1, ExceptionsKt.CancellationException("The task was rejected", r2));
    }

    private final ScheduledFuture<?> scheduleBlock(ScheduledExecutorService r2, Runnable r3, CoroutineContext r4, long r5) {
        return r2.schedule(r3, r5, TimeUnit.MILLISECONDS);
    L4:
        e = move-exception;
        this.cancelJobOnRejection(r4, e);
        return null;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Executor r1 = getExecutor();
        if ((r1 instanceof ExecutorService) == false) goto L5;
        ExecutorService r12 = (ExecutorService) r1;
    L6:
        if (r12 == null) goto L9;
        r12.shutdown();
        return;
    L9:
        return;
    L5:
        r12 = null;
        goto L6
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void dispatch(CoroutineContext r2, Runnable r3) {
        Executor r0 = getExecutor();     // Catch: RejectedExecutionException -> L4
        AbstractTimeSourceKt.access$getTimeSource$p();     // Catch: RejectedExecutionException -> L4
        r0.execute(r3);     // Catch: RejectedExecutionException -> L4
        return;
    L4:
        e = move-exception;
        AbstractTimeSourceKt.access$getTimeSource$p();
        cancelJobOnRejection(r2, e);
        Dispatchers.getIO().dispatch(r2, r3);
    }

    public boolean equals(Object r2) {
        if ((r2 instanceof ExecutorCoroutineDispatcherImpl) == true) goto L5;
        return false;
    L5:
        if (((ExecutorCoroutineDispatcherImpl) r2).getExecutor() != getExecutor()) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // kotlinx.coroutines.ExecutorCoroutineDispatcher
    public Executor getExecutor() {
        return this.executor;
    }

    public int hashCode() {
        return System.identityHashCode(getExecutor());
    }

    @Override // kotlinx.coroutines.Delay
    public DisposableHandle invokeOnTimeout(long r10, Runnable r12, CoroutineContext r13) {
        Executor r0 = getExecutor();
        ScheduledFuture<?> r2 = null;
        if ((r0 instanceof ScheduledExecutorService) == false) goto L5;
        ScheduledExecutorService r4 = (ScheduledExecutorService) r0;
    L6:
        if (r4 == null) goto L8;
        long r7 = r10;
        Runnable r5 = r12;
        CoroutineContext r6 = r13;
        r2 = scheduleBlock(r4, r5, r6, r7);
    L9:
        if (r2 == null) goto L13;
        return new DisposableFutureHandle(r2);
    L13:
        return DefaultExecutor.INSTANCE.invokeOnTimeout(r7, r5, r6);
    L8:
        r7 = r10;
        r5 = r12;
        r6 = r13;
        goto L9
    L5:
        r4 = null;
        goto L6
    }

    @Override // kotlinx.coroutines.Delay
    public void scheduleResumeAfterDelay(long r10, CancellableContinuation<? super Unit> r12) {
        Executor r0 = getExecutor();
        ScheduledFuture<?> r2 = null;
        if ((r0 instanceof ScheduledExecutorService) == false) goto L5;
        ScheduledExecutorService r4 = (ScheduledExecutorService) r0;
    L6:
        if (r4 == null) goto L8;
        long r7 = r10;
        r2 = scheduleBlock(r4, new ResumeUndispatchedRunnable(this, r12), r12.getContext(), r7);
    L9:
        if (r2 == null) goto L12;
        CancellableContinuationKt.invokeOnCancellation(r12, new CancelFutureOnCancel(r2));
        return;
    L12:
        DefaultExecutor.INSTANCE.scheduleResumeAfterDelay(r7, r12);
        return;
    L8:
        r7 = r10;
        goto L9
    L5:
        r4 = null;
        goto L6
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public String toString() {
        return getExecutor().toString();
    }
}
