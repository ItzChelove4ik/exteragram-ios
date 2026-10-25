package kotlinx.coroutines.intrinsics;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.DispatchException;
import kotlinx.coroutines.internal.DispatchedContinuationKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0004\u001aQ\u0010\b\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00022\u0006\u0010\u0005\u001a\u00028\u00002\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u0003H\u0000¢\u0006\u0004\b\b\u0010\t\u001a%\u0010\b\u001a\u00020\u0007*\b\u0012\u0004\u0012\u00020\u00070\u00032\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0000¢\u0006\u0004\b\b\u0010\u000b\u001a#\u0010\u000e\u001a\u00020\u00072\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00032\u0006\u0010\r\u001a\u00020\fH\u0002¢\u0006\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"R", "T", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", _UrlKt.FRAGMENT_ENCODE_SET, "receiver", "completion", _UrlKt.FRAGMENT_ENCODE_SET, "startCoroutineCancellable", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)V", "fatalCompletion", "(Lkotlin/coroutines/Continuation;Lkotlin/coroutines/Continuation;)V", _UrlKt.FRAGMENT_ENCODE_SET, "e", "dispatcherFailure", "(Lkotlin/coroutines/Continuation;Ljava/lang/Throwable;)V", "kotlinx-coroutines-core"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCancellable.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Cancellable.kt\nkotlinx/coroutines/intrinsics/CancellableKt\n*L\n1#1,65:1\n45#1,6:66\n45#1,6:72\n45#1,6:78\n*S KotlinDebug\n*F\n+ 1 Cancellable.kt\nkotlinx/coroutines/intrinsics/CancellableKt\n*L\n15#1:66,6\n25#1:72,6\n34#1:78,6\n*E\n"})
/* loaded from: classes.dex */
public abstract class CancellableKt {
    private static final void dispatcherFailure(Continuation<?> r1, Throwable r2) {
        if ((r2 instanceof DispatchException) == false) goto L5;
        r2 = ((DispatchException) r2).getCause();
    L5:
        Result.Companion r0 = Result.Companion;
        r1.resumeWith(Result.m2018constructorimpl(ResultKt.createFailure(r2)));
        throw r2;
    }

    public static final void startCoroutineCancellable(Continuation<? super Unit> r1, Continuation<?> r2) {
        Continuation r12 = IntrinsicsKt.intercepted(r1);     // Catch: Throwable -> L4
        Result.Companion r0 = Result.Companion;     // Catch: Throwable -> L4
        DispatchedContinuationKt.resumeCancellableWithInternal(r12, Result.m2018constructorimpl(Unit.INSTANCE));     // Catch: Throwable -> L4
        return;
    L4:
        th = move-exception;
        dispatcherFailure(r2, th);
    }

    public static final <R, T> void startCoroutineCancellable(Function2<? super R, ? super Continuation<? super T>, ? extends Object> r0, R r1, Continuation<? super T> r2) {
        Continuation r02 = IntrinsicsKt.intercepted(IntrinsicsKt.createCoroutineUnintercepted(r0, r1, r2));     // Catch: Throwable -> L4
        Result.Companion r12 = Result.Companion;     // Catch: Throwable -> L4
        DispatchedContinuationKt.resumeCancellableWithInternal(r02, Result.m2018constructorimpl(Unit.INSTANCE));     // Catch: Throwable -> L4
        return;
    L4:
        th = move-exception;
        dispatcherFailure(r2, th);
    }
}
