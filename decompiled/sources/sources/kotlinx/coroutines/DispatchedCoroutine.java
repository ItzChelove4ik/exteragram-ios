package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.internal.DispatchedContinuationKt;
import kotlinx.coroutines.internal.ScopeCoroutine;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0000\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\fH\u0002J\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014J\u0012\u0010\u0012\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014J\u000f\u0010\u0013\u001a\u0004\u0018\u00010\u0011H\u0000¢\u0006\u0002\b\u0014R\t\u0010\t\u001a\u00020\nX\u0082\u0004¨\u0006\u0015"}, d2 = {"Lkotlinx/coroutines/DispatchedCoroutine;", "T", "Lkotlinx/coroutines/internal/ScopeCoroutine;", "context", "Lkotlin/coroutines/CoroutineContext;", "uCont", "Lkotlin/coroutines/Continuation;", "<init>", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/Continuation;)V", "_decision", "Lkotlinx/atomicfu/AtomicInt;", "trySuspend", _UrlKt.FRAGMENT_ENCODE_SET, "tryResume", "afterCompletion", _UrlKt.FRAGMENT_ENCODE_SET, "state", _UrlKt.FRAGMENT_ENCODE_SET, "afterResume", "getResult", "getResult$kotlinx_coroutines_core", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class DispatchedCoroutine<T> extends ScopeCoroutine<T> {
    private static final /* synthetic */ AtomicIntegerFieldUpdater _decision$volatile$FU = null;
    private volatile /* synthetic */ int _decision$volatile;

    static {
        _decision$volatile$FU = AtomicIntegerFieldUpdater.newUpdater(DispatchedCoroutine.class, "_decision$volatile");
    }

    public DispatchedCoroutine(CoroutineContext r1, Continuation<? super T> r2) {
        super(r1, r2);
    }

    private static final /* synthetic */ AtomicIntegerFieldUpdater get_decision$volatile$FU() {
        return _decision$volatile$FU;
    }

    private final boolean tryResume() {
        AtomicIntegerFieldUpdater r0 = get_decision$volatile$FU();
    L3:
        int r1 = r0.get(this);
        if (r1 != 0) goto L5;
        if (get_decision$volatile$FU().compareAndSet(this, 0, 2) == false) goto L3;
        return true;
    L5:
        if (r1 != 1) goto L7;
        return false;
    L7:
        Segment$$ExternalSyntheticBUOutline1.m("Already resumed");
        return false;
    }

    private final boolean trySuspend() {
        AtomicIntegerFieldUpdater r0 = get_decision$volatile$FU();
    L3:
        int r1 = r0.get(this);
        if (r1 != 0) goto L6;
        if (get_decision$volatile$FU().compareAndSet(this, 0, 1) == false) goto L3;
        return true;
    L6:
        if (r1 != 2) goto L8;
        return false;
    L8:
        Segment$$ExternalSyntheticBUOutline1.m("Already suspended");
        return false;
    }

    @Override // kotlinx.coroutines.internal.ScopeCoroutine, kotlinx.coroutines.JobSupport
    public void afterCompletion(Object r1) {
        afterResume(r1);
    }

    @Override // kotlinx.coroutines.internal.ScopeCoroutine, kotlinx.coroutines.AbstractCoroutine
    public void afterResume(Object r2) {
        if (tryResume() == false) goto L5;
        return;
    L5:
        DispatchedContinuationKt.resumeCancellableWithInternal(IntrinsicsKt.intercepted(this.uCont), CompletionStateKt.recoverResult(r2, this.uCont));
    }

    public final Object getResult$kotlinx_coroutines_core() {
        if (trySuspend() == true) goto L5;
        Object r1 = JobSupportKt.unboxState(getState$kotlinx_coroutines_core());
        if ((r1 instanceof CompletedExceptionally) == true) goto L10;
        return r1;
    L10:
        throw ((CompletedExceptionally) r1).cause;
    L5:
        return IntrinsicsKt.getCOROUTINE_SUSPENDED();
    }
}
