package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B%\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0006\u0010\f\u001a\u00020\u0007R\t\u0010\n\u001a\u00020\u000bX\u0082\u0004¨\u0006\r"}, d2 = {"Lkotlinx/coroutines/CancelledContinuation;", "Lkotlinx/coroutines/CompletedExceptionally;", "continuation", "Lkotlin/coroutines/Continuation;", "cause", _UrlKt.FRAGMENT_ENCODE_SET, "handled", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lkotlin/coroutines/Continuation;Ljava/lang/Throwable;Z)V", "_resumed", "Lkotlinx/atomicfu/AtomicBoolean;", "makeResumed", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class CancelledContinuation extends CompletedExceptionally {
    private static final /* synthetic */ AtomicIntegerFieldUpdater _resumed$volatile$FU = null;
    private volatile /* synthetic */ int _resumed$volatile;

    static {
        _resumed$volatile$FU = AtomicIntegerFieldUpdater.newUpdater(CancelledContinuation.class, "_resumed$volatile");
    }

    public CancelledContinuation(Continuation<?> r3, Throwable r4, boolean r5) {
        if (r4 != null) goto L4;
        r4 = new CancellationException("Continuation " + r3 + " was cancelled normally");
    L4:
        super(r4, r5);
    }

    private static final /* synthetic */ AtomicIntegerFieldUpdater get_resumed$volatile$FU() {
        return _resumed$volatile$FU;
    }

    public final boolean makeResumed() {
        return get_resumed$volatile$FU().compareAndSet(this, 0, 1);
    }
}
