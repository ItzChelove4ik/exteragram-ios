package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.internal.DispatchedContinuation;
import okhttp3.internal.url._UrlKt;
import okio.ByteString$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a'\u0010\u0005\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a)\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\t\"\u0004\b\u0000\u0010\u00002\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0000¢\u0006\u0004\b\n\u0010\u000b\u001a\u001f\u0010\u000e\u001a\u00020\u0004*\u0006\u0012\u0002\b\u00030\u00012\u0006\u0010\r\u001a\u00020\fH\u0007¢\u0006\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"T", "Lkotlinx/coroutines/CancellableContinuation;", "Lkotlinx/coroutines/CancelHandler;", "handler", _UrlKt.FRAGMENT_ENCODE_SET, "invokeOnCancellation", "(Lkotlinx/coroutines/CancellableContinuation;Lkotlinx/coroutines/CancelHandler;)V", "Lkotlin/coroutines/Continuation;", "delegate", "Lkotlinx/coroutines/CancellableContinuationImpl;", "getOrCreateCancellableContinuation", "(Lkotlin/coroutines/Continuation;)Lkotlinx/coroutines/CancellableContinuationImpl;", "Lkotlinx/coroutines/DisposableHandle;", "handle", "disposeOnCancellation", "(Lkotlinx/coroutines/CancellableContinuation;Lkotlinx/coroutines/DisposableHandle;)V", "kotlinx-coroutines-core"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCancellableContinuation.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CancellableContinuation.kt\nkotlinx/coroutines/CancellableContinuationKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,506:1\n1#2:507\n*E\n"})
/* loaded from: classes.dex */
public abstract class CancellableContinuationKt {
    public static final void disposeOnCancellation(CancellableContinuation<?> r1, DisposableHandle r2) {
        invokeOnCancellation(r1, new DisposeOnCancel(r2));
    }

    public static final <T> CancellableContinuationImpl<T> getOrCreateCancellableContinuation(Continuation<? super T> r2) {
        if ((r2 instanceof DispatchedContinuation) == false) goto L5;
        CancellableContinuationImpl<T> r0 = ((DispatchedContinuation) r2).claimReusableCancellableContinuation$kotlinx_coroutines_core();
        if (r0 == null) goto L16;
        if (r0.resetStateReusable() == true) goto L12;
        r0 = null;
    L12:
        if (r0 == null) goto L16;
        return r0;
    L16:
        return new CancellableContinuationImpl(r2, 2);
    L5:
        return new CancellableContinuationImpl(r2, 1);
    }

    public static final <T> void invokeOnCancellation(CancellableContinuation<? super T> r1, CancelHandler r2) {
        if ((r1 instanceof CancellableContinuationImpl) == false) goto L6;
        ((CancellableContinuationImpl) r1).invokeOnCancellationInternal$kotlinx_coroutines_core(r2);
        return;
    L6:
        ByteString$$ExternalSyntheticBUOutline0.m("third-party implementation of CancellableContinuation is not supported");
    }
}
