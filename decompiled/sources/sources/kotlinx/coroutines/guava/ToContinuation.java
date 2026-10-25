package kotlinx.coroutines.guava;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.Uninterruptibles;
import java.util.concurrent.ExecutionException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlinx.coroutines.CancellableContinuation;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00060\u0002j\u0002`\u0003B#\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fR\u001d\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lkotlinx/coroutines/guava/ToContinuation;", "T", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "Lcom/google/common/util/concurrent/ListenableFuture;", "futureToObserve", "Lkotlinx/coroutines/CancellableContinuation;", "continuation", "<init>", "(Lcom/google/common/util/concurrent/ListenableFuture;Lkotlinx/coroutines/CancellableContinuation;)V", _UrlKt.FRAGMENT_ENCODE_SET, "run", "()V", "Lcom/google/common/util/concurrent/ListenableFuture;", "getFutureToObserve", "()Lcom/google/common/util/concurrent/ListenableFuture;", "Lkotlinx/coroutines/CancellableContinuation;", "getContinuation", "()Lkotlinx/coroutines/CancellableContinuation;", "kotlinx-coroutines-guava"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
final class ToContinuation<T> implements Runnable {
    private final CancellableContinuation<T> continuation;
    private final ListenableFuture<T> futureToObserve;

    /* JADX WARN: Multi-variable type inference failed */
    public ToContinuation(ListenableFuture<T> r1, CancellableContinuation<? super T> r2) {
        this.futureToObserve = r1;
        this.continuation = r2;
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean r0 = this.futureToObserve.isCancelled();
        CancellableContinuation<T> r1 = this.continuation;
        if (r0 == false) goto L11;
        CancellableContinuation.cancel$default(r1, null, 1, null);
        return;
    L11:
        Result.Companion r02 = Result.Companion;     // Catch: ExecutionException -> L8
        r1.resumeWith(Result.m2018constructorimpl(Uninterruptibles.getUninterruptibly(this.futureToObserve)));     // Catch: ExecutionException -> L8
        return;
    L8:
        e = move-exception;
        CancellableContinuation<T> r2 = this.continuation;
        Result.Companion r12 = Result.Companion;
        r2.resumeWith(Result.m2018constructorimpl(ResultKt.createFailure(ListenableFutureKt.access$nonNullCause(e))));
    }
}
