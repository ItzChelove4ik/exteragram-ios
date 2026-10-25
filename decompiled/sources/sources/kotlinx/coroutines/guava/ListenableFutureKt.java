package kotlinx.coroutines.guava;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.Uninterruptibles;
import java.util.concurrent.ExecutionException;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import okhttp3.Response$Builder$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0005\u001aZ\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042'\u0010\n\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0006¢\u0006\u0002\b\t¢\u0006\u0004\b\f\u0010\r\u001a\u0013\u0010\u0010\u001a\u00020\u000f*\u00020\u000eH\u0002¢\u0006\u0004\b\u0010\u0010\u0011\u001a \u0010\u0012\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u000bH\u0086@¢\u0006\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"T", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/CoroutineContext;", "context", "Lkotlinx/coroutines/CoroutineStart;", "start", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ExtensionFunctionType;", "block", "Lcom/google/common/util/concurrent/ListenableFuture;", "future", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;)Lcom/google/common/util/concurrent/ListenableFuture;", "Ljava/util/concurrent/ExecutionException;", _UrlKt.FRAGMENT_ENCODE_SET, "nonNullCause", "(Ljava/util/concurrent/ExecutionException;)Ljava/lang/Throwable;", "await", "(Lcom/google/common/util/concurrent/ListenableFuture;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-guava"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nListenableFuture.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ListenableFuture.kt\nkotlinx/coroutines/guava/ListenableFutureKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 CancellableContinuation.kt\nkotlinx/coroutines/CancellableContinuationKt\n*L\n1#1,525:1\n1#2:526\n433#3,10:527\n*S KotlinDebug\n*F\n+ 1 ListenableFuture.kt\nkotlinx/coroutines/guava/ListenableFutureKt\n*L\n258#1:527,10\n*E\n"})
/* loaded from: classes5.dex */
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
        return Uninterruptibles.getUninterruptibly(r3);
    L6:
        CancellableContinuationImpl r0 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(r4), 1);
        r0.initCancellability();
        r3.addListener(new ToContinuation(r3, r0), MoreExecutors.directExecutor());
        r0.invokeOnCancellation(new ListenableFutureKt$await$2$1(r3));
        Object r32 = r0.getResult();
        if (r32 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L9;
        DebugProbesKt.probeCoroutineSuspended(r4);
    L9:
        return r32;
    }

    public static final <T> ListenableFuture<T> future(CoroutineScope r1, CoroutineContext r2, CoroutineStart r3, Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> r4) {
        if (r3.isLazy() == true) goto L6;
        ListenableFutureCoroutine r22 = new ListenableFutureCoroutine(CoroutineContextKt.newCoroutineContext(r1, r2));
        r22.start(r3, r22, r4);
        return r22.future;
    L6:
        Response$Builder$$ExternalSyntheticBUOutline0.m(r3, " start is not supported");
        return null;
    }

    public static /* synthetic */ ListenableFuture future$default(CoroutineScope r0, CoroutineContext r1, CoroutineStart r2, Function2 r3, int r4, Object r5) {
        if ((r4 & 1) == 0) goto L6;
        r1 = EmptyCoroutineContext.INSTANCE;
    L6:
        if ((r4 & 2) == 0) goto L9;
        r2 = CoroutineStart.DEFAULT;
    L9:
        return future(r0, r1, r2, r3);
    }

    private static final Throwable nonNullCause(ExecutionException r0) {
        return r0.getCause();
    }
}
