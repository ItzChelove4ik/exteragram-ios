package androidx.concurrent.futures;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ExecutionException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlinx.coroutines.CancellableContinuation;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B#\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\n\u0010\u000bR\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Landroidx/concurrent/futures/ToContinuation;", "T", "Ljava/lang/Runnable;", "Lcom/google/common/util/concurrent/ListenableFuture;", "futureToObserve", "Lkotlinx/coroutines/CancellableContinuation;", "continuation", "<init>", "(Lcom/google/common/util/concurrent/ListenableFuture;Lkotlinx/coroutines/CancellableContinuation;)V", _UrlKt.FRAGMENT_ENCODE_SET, "run", "()V", "Lcom/google/common/util/concurrent/ListenableFuture;", "getFutureToObserve", "()Lcom/google/common/util/concurrent/ListenableFuture;", "Lkotlinx/coroutines/CancellableContinuation;", "getContinuation", "()Lkotlinx/coroutines/CancellableContinuation;", "concurrent-futures-ktx"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes3.dex */
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
        CancellableContinuation.DefaultImpls.cancel$default(r1, null, 1, null);
        return;
    L11:
        Result.Companion r02 = Result.Companion;     // Catch: ExecutionException -> L8
        r1.resumeWith(Result.m2018constructorimpl(AbstractResolvableFuture.getUninterruptibly(this.futureToObserve)));     // Catch: ExecutionException -> L8
        return;
    L8:
        e = move-exception;
        CancellableContinuation<T> r2 = this.continuation;
        Throwable r03 = ListenableFutureKt.access$nonNullCause(e);
        Result.Companion r12 = Result.Companion;
        r2.resumeWith(Result.m2018constructorimpl(ResultKt.createFailure(r03)));
    }
}
