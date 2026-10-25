package androidx.concurrent.futures;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ExecutionException;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlinx.coroutines.CancellableContinuationImpl;
import okhttp3.internal.url._UrlKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0000\u001a!\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0003\u001a\f\u0010\u0004\u001a\u00020\u0005*\u00020\u0006H\u0002\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, d2 = {"await", "T", "Lcom/google/common/util/concurrent/ListenableFuture;", "(Lcom/google/common/util/concurrent/ListenableFuture;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "nonNullCause", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/util/concurrent/ExecutionException;", "concurrent-futures-ktx"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes3.dex */
public abstract class ListenableFutureKt {
    public static final /* synthetic */ Throwable access$nonNullCause(ExecutionException r0) {
        return nonNullCause(r0);
    }

    public static final <T> Object await(ListenableFuture<T> r3, Continuation<? super T> r4) {
    L10:
        e = move-exception;
        throw nonNullCause(e);
    L3:
        if (r3.isDone() == false) goto L6;
        return AbstractResolvableFuture.getUninterruptibly(r3);
    L6:
        CancellableContinuationImpl r0 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(r4), 1);
        r3.addListener(new ToContinuation(r3, r0), DirectExecutor.INSTANCE);
        r0.invokeOnCancellation(new ListenableFutureKt$await$$inlined$suspendCancellableCoroutine$lambda$1(r3));
        Object r32 = r0.getResult();
        if (r32 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L9;
        DebugProbesKt.probeCoroutineSuspended(r4);
    L9:
        return r32;
    }

    private static final Throwable nonNullCause(ExecutionException r0) {
        return r0.getCause();
    }
}
