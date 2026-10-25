package kotlinx.coroutines.internal;

import java.util.Iterator;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000¨\u0006\u0006"}, d2 = {"handleUncaughtCoroutineException", _UrlKt.FRAGMENT_ENCODE_SET, "context", "Lkotlin/coroutines/CoroutineContext;", "exception", _UrlKt.FRAGMENT_ENCODE_SET, "kotlinx-coroutines-core"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public abstract class CoroutineExceptionHandlerImpl_commonKt {
    public static final void handleUncaughtCoroutineException(CoroutineContext r2, Throwable r3) {
        Iterator<CoroutineExceptionHandler> r0 = CoroutineExceptionHandlerImplKt.getPlatformExceptionHandlers().iterator();
    L4:
        if (r0.hasNext() == true) goto L14;
        ExceptionsKt.addSuppressed(r3, new DiagnosticCoroutineContextException(r2));     // Catch: Throwable -> L13
    L11:
        CoroutineExceptionHandlerImplKt.propagateExceptionFinalResort(r3);
        return;
    L14:
        r0.next().handleException(r2, r3);     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        CoroutineExceptionHandlerImplKt.propagateExceptionFinalResort(CoroutineExceptionHandlerKt.handlerException(r3, th));
        goto L4
    }
}
