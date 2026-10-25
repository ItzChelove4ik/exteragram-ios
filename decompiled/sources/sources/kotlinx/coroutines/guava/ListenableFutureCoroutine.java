package kotlinx.coroutines.guava;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmField;
import kotlinx.coroutines.AbstractCoroutine;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010\fJ\u0018\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0014R\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lkotlinx/coroutines/guava/ListenableFutureCoroutine;", "T", "Lkotlinx/coroutines/AbstractCoroutine;", "context", "Lkotlin/coroutines/CoroutineContext;", "<init>", "(Lkotlin/coroutines/CoroutineContext;)V", "future", "Lkotlinx/coroutines/guava/JobListenableFuture;", "onCompleted", _UrlKt.FRAGMENT_ENCODE_SET, "value", "(Ljava/lang/Object;)V", "onCancelled", "cause", _UrlKt.FRAGMENT_ENCODE_SET, "handled", _UrlKt.FRAGMENT_ENCODE_SET, "kotlinx-coroutines-guava"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
final class ListenableFutureCoroutine<T> extends AbstractCoroutine<T> {

    @JvmField
    public final JobListenableFuture<T> future;

    public ListenableFutureCoroutine(CoroutineContext r2) {
        super(r2, true, true);
        this.future = new JobListenableFuture(this);
    }

    @Override // kotlinx.coroutines.AbstractCoroutine
    public void onCancelled(Throwable r1, boolean r2) {
        this.future.completeExceptionallyOrCancel(r1);
    }

    @Override // kotlinx.coroutines.AbstractCoroutine
    public void onCompleted(T r1) {
        this.future.complete(r1);
    }
}
