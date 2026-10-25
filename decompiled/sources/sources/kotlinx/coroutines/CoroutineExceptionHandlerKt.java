package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.internal.CoroutineExceptionHandlerImpl_commonKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u001a\u001f\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u001f\u0010\t\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lkotlin/coroutines/CoroutineContext;", "context", _UrlKt.FRAGMENT_ENCODE_SET, "exception", _UrlKt.FRAGMENT_ENCODE_SET, "handleCoroutineException", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Throwable;)V", "originalException", "thrownException", "handlerException", "(Ljava/lang/Throwable;Ljava/lang/Throwable;)Ljava/lang/Throwable;", "kotlinx-coroutines-core"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public abstract class CoroutineExceptionHandlerKt {
    public static final void handleCoroutineException(CoroutineContext r1, Throwable r2) {
        if ((r2 instanceof DispatchException) == false) goto L15;
        r2 = ((DispatchException) r2).getCause();
    L15:
        CoroutineExceptionHandler r0 = (CoroutineExceptionHandler) r1.get(CoroutineExceptionHandler.Key);     // Catch: Throwable -> L9
        if (r0 == null) goto L11;
        r0.handleException(r1, r2);     // Catch: Throwable -> L9
        return;
    L11:
        CoroutineExceptionHandlerImpl_commonKt.handleUncaughtCoroutineException(r1, r2);
        return;
    L9:
        th = move-exception;
        CoroutineExceptionHandlerImpl_commonKt.handleUncaughtCoroutineException(r1, handlerException(r2, th));
    }

    public static final Throwable handlerException(Throwable r2, Throwable r3) {
        if (r2 != r3) goto L4;
        return r2;
    L4:
        RuntimeException r0 = new RuntimeException("Exception while trying to handle coroutine exception", r3);
        kotlin.ExceptionsKt.addSuppressed(r0, r2);
        return r0;
    }
}
