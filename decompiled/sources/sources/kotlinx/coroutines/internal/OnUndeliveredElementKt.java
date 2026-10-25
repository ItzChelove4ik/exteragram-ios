package kotlinx.coroutines.internal;

import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aC\u0010\u0004\u001a\u0004\u0018\u00010\u0005\"\u0004\b\u0000\u0010\u0001*\u0018\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00030\u0002j\b\u0012\u0004\u0012\u0002H\u0001`\u00062\u0006\u0010\u0007\u001a\u0002H\u00012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005H\u0000¢\u0006\u0002\u0010\t\u001a=\u0010\n\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0001*\u0018\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00030\u0002j\b\u0012\u0004\u0012\u0002H\u0001`\u00062\u0006\u0010\u0007\u001a\u0002H\u00012\u0006\u0010\u000b\u001a\u00020\fH\u0000¢\u0006\u0002\u0010\r**\b\u0000\u0010\u0000\u001a\u0004\b\u0000\u0010\u0001\"\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00030\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00030\u0002¨\u0006\u000e"}, d2 = {"OnUndeliveredElement", "E", "Lkotlin/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, "callUndeliveredElementCatchingException", "Lkotlinx/coroutines/internal/UndeliveredElementException;", "Lkotlinx/coroutines/internal/OnUndeliveredElement;", "element", "undeliveredElementException", "(Lkotlin/jvm/functions/Function1;Ljava/lang/Object;Lkotlinx/coroutines/internal/UndeliveredElementException;)Lkotlinx/coroutines/internal/UndeliveredElementException;", "callUndeliveredElement", "context", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/jvm/functions/Function1;Ljava/lang/Object;Lkotlin/coroutines/CoroutineContext;)V", "kotlinx-coroutines-core"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public abstract class OnUndeliveredElementKt {
    public static final <E> void callUndeliveredElement(Function1<? super E, Unit> r1, E r2, CoroutineContext r3) {
        UndeliveredElementException r12 = callUndeliveredElementCatchingException(r1, r2, null);
        if (r12 == null) goto L6;
        CoroutineExceptionHandlerKt.handleCoroutineException(r3, r12);
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <E> UndeliveredElementException callUndeliveredElementCatchingException(Function1<? super E, Unit> r2, E r3, UndeliveredElementException r4) {
        r2.invoke(r3);     // Catch: Throwable -> L4
        return r4;
    L4:
        th = move-exception;
        if (r4 == null) goto L11;
        if (r4.getCause() == th) goto L11;
        ExceptionsKt.addSuppressed(r4, th);
        return r4;
    L11:
        return new UndeliveredElementException("Exception in undelivered element handler for " + r3, th);
    }

    public static /* synthetic */ UndeliveredElementException callUndeliveredElementCatchingException$default(Function1 r0, Object r1, UndeliveredElementException r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = null;
    L6:
        return callUndeliveredElementCatchingException(r0, r1, r2);
    }
}
