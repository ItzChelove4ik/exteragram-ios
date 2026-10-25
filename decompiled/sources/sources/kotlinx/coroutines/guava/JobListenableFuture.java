package kotlinx.coroutines.guava;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.google.common.util.concurrent.Uninterruptibles;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.Job;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0013\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00028\u0000¢\u0006\u0002\u0010\u000fJ\u000e\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0012J\b\u0010\u0013\u001a\u00020\fH\u0016J\r\u0010\u0014\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0015J\u001e\u0010\u0014\u001a\u00028\u00002\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0096\u0002¢\u0006\u0002\u0010\u001aJ\u0017\u0010\u001b\u001a\u00028\u00002\b\u0010\u000e\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0002\u0010\u001cJ\u001c\u0010\u001d\u001a\u00020\u001e2\n\u0010\u001f\u001a\u00060 j\u0002`!2\u0006\u0010\"\u001a\u00020#H\u0016J\b\u0010$\u001a\u00020\fH\u0016J\u0010\u0010%\u001a\u00020\f2\u0006\u0010&\u001a\u00020\fH\u0016J\b\u0010'\u001a\u00020(H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u0007\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u00010\t \n*\f\u0012\u0006\u0012\u0004\u0018\u00010\t\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lkotlinx/coroutines/guava/JobListenableFuture;", "T", "Lcom/google/common/util/concurrent/ListenableFuture;", "jobToCancel", "Lkotlinx/coroutines/Job;", "<init>", "(Lkotlinx/coroutines/Job;)V", "auxFuture", "Lcom/google/common/util/concurrent/SettableFuture;", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin.jvm.PlatformType", "auxFutureIsFailed", _UrlKt.FRAGMENT_ENCODE_SET, "complete", "result", "(Ljava/lang/Object;)Z", "completeExceptionallyOrCancel", "t", _UrlKt.FRAGMENT_ENCODE_SET, "isCancelled", "get", "()Ljava/lang/Object;", "timeout", _UrlKt.FRAGMENT_ENCODE_SET, "unit", "Ljava/util/concurrent/TimeUnit;", "(JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;", "getInternal", "(Ljava/lang/Object;)Ljava/lang/Object;", "addListener", _UrlKt.FRAGMENT_ENCODE_SET, "listener", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "executor", "Ljava/util/concurrent/Executor;", "isDone", "cancel", "mayInterruptIfRunning", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "kotlinx-coroutines-guava"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nListenableFuture.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ListenableFuture.kt\nkotlinx/coroutines/guava/JobListenableFuture\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,525:1\n1#2:526\n*E\n"})
/* loaded from: classes5.dex */
final class JobListenableFuture<T> implements ListenableFuture<T> {
    private final SettableFuture<Object> auxFuture;
    private boolean auxFutureIsFailed;
    private final Job jobToCancel;

    public JobListenableFuture(Job r1) {
        this.jobToCancel = r1;
        this.auxFuture = SettableFuture.create();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final T getInternal(Object r1) {
        if ((r1 instanceof Cancelled) == true) goto L6;
        return r1;
    L6:
        throw new CancellationException().initCause(((Cancelled) r1).exception);
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public void addListener(Runnable r1, Executor r2) {
        this.auxFuture.addListener(r1, r2);
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean r2) {
        if (this.auxFuture.cancel(r2) == false) goto L6;
        Job.cancel$default(this.jobToCancel, null, 1, null);
        return true;
    L6:
        return false;
    }

    public final boolean complete(T r1) {
        return this.auxFuture.set(r1);
    }

    public final boolean completeExceptionallyOrCancel(Throwable r3) {
        boolean r0 = r3 instanceof CancellationException;
        SettableFuture<Object> r1 = this.auxFuture;
        if (r0 == true) goto L5;
        boolean r32 = r1.setException(r3);
        if (r32 == false) goto L9;
        this.auxFutureIsFailed = true;
    L9:
        return r32;
    L5:
        return r1.set(new Cancelled((CancellationException) r3));
    }

    @Override // java.util.concurrent.Future
    public T get() {
        return getInternal(this.auxFuture.get());
    }

    @Override // java.util.concurrent.Future
    public T get(long r2, TimeUnit r4) {
        return getInternal(this.auxFuture.get(r2, r4));
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        if (this.auxFuture.isCancelled() == false) goto L5;
        return true;
    L5:
        if (isDone() == false) goto L15;
        if (this.auxFutureIsFailed == true) goto L15;
        boolean r3 = Uninterruptibles.getUninterruptibly(this.auxFuture) instanceof Cancelled;     // Catch: ExecutionException -> L10 CancellationException -> L11
    L12:
        if (r3 == false) goto L15;
        return true;
    L11:
        r3 = true;
    L10:
        this.auxFutureIsFailed = true;
        r3 = false;
    L15:
        return false;
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return this.auxFuture.isDone();
    }

    public String toString() {
        StringBuilder r2 = new StringBuilder();
        r2.append(super.toString());
        r2.append("[status=");
        boolean r3 = isDone();
        SettableFuture<Object> r5 = this.auxFuture;
        if (r3 == true) goto L20;
        r2.append("PENDING, delegate=[" + r5 + ']');
    L17:
        r2.append(']');
        return r2.toString();
    L20:
        Object r52 = Uninterruptibles.getUninterruptibly(r5);     // Catch: Throwable -> L7 ExecutionException -> L9 CancellationException -> L15
        if ((r52 instanceof Cancelled) == false) goto L11;
        r2.append("CANCELLED, cause=[" + ((Cancelled) r52).exception + ']');     // Catch: Throwable -> L7 ExecutionException -> L9 CancellationException -> L15
    L19:
    L11:
        r2.append("SUCCESS, result=[" + r52 + ']');     // Catch: Throwable -> L7 ExecutionException -> L9 CancellationException -> L15
    L15:
        r2.append("CANCELLED");
    L9:
        e = move-exception;
        r2.append("FAILURE, cause=[" + e.getCause() + ']');
    L7:
        th = move-exception;
        r2.append("UNKNOWN, cause=[" + th.getClass() + " thrown from get()]");
        goto L17
    }
}
