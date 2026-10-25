package androidx.work.impl;

import androidx.work.DirectExecutor;
import androidx.work.ListenableWorker;
import androidx.work.Logger;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CancellableContinuationImpl;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a(\u0010\u0004\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0087@¢\u0006\u0004\b\u0004\u0010\u0005\u001a#\u0010\t\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0002¢\u0006\u0004\b\t\u0010\n\u001a\u0013\u0010\r\u001a\u00020\f*\u00020\u000bH\u0002¢\u0006\u0004\b\r\u0010\u000e\"\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"T", "Lcom/google/common/util/concurrent/ListenableFuture;", "Landroidx/work/ListenableWorker;", "worker", "awaitWithin", "(Lcom/google/common/util/concurrent/ListenableFuture;Landroidx/work/ListenableWorker;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "V", "Ljava/util/concurrent/Future;", "future", "getUninterruptibly", "(Ljava/util/concurrent/Future;)Ljava/lang/Object;", "Ljava/util/concurrent/ExecutionException;", _UrlKt.FRAGMENT_ENCODE_SET, "nonNullCause", "(Ljava/util/concurrent/ExecutionException;)Ljava/lang/Throwable;", _UrlKt.FRAGMENT_ENCODE_SET, "TAG", "Ljava/lang/String;", "work-runtime_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nWorkerWrapper.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WorkerWrapper.kt\nandroidx/work/impl/WorkerWrapperKt\n+ 2 CancellableContinuation.kt\nkotlinx/coroutines/CancellableContinuationKt\n*L\n1#1,623:1\n351#2,11:624\n*S KotlinDebug\n*F\n+ 1 WorkerWrapper.kt\nandroidx/work/impl/WorkerWrapperKt\n*L\n569#1:624,11\n*E\n"})
/* loaded from: classes4.dex */
public abstract class WorkerWrapperKt {
    private static final String TAG = null;

    static {
        TAG = Logger.tagWithPrefix("WorkerWrapper");
    }

    public static final /* synthetic */ String access$getTAG$p() {
        return TAG;
    }

    public static final /* synthetic */ Object access$getUninterruptibly(Future r0) {
        return getUninterruptibly(r0);
    }

    public static final /* synthetic */ Throwable access$nonNullCause(ExecutionException r0) {
        return nonNullCause(r0);
    }

    public static final <T> Object awaitWithin(ListenableFuture<T> r3, ListenableWorker r4, Continuation<? super T> r5) {
    L10:
        e = move-exception;
        throw nonNullCause(e);
    L3:
        if (r3.isDone() == false) goto L6;
        return getUninterruptibly(r3);
    L6:
        CancellableContinuationImpl r0 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(r5), 1);
        r0.initCancellability();
        r3.addListener(new ToContinuation(r3, r0), DirectExecutor.INSTANCE);
        r0.invokeOnCancellation(new WorkerWrapperKt$awaitWithin$2$1(r4, r3));
        Object r32 = r0.getResult();
        if (r32 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L9;
        DebugProbesKt.probeCoroutineSuspended(r5);
    L9:
        return r32;
    }

    private static final <V> V getUninterruptibly(Future<V> r1) {
        boolean r0 = false;
    L12:
        V r12 = r1.get();     // Catch: Throwable -> L7 InterruptedException -> L11
    L4:
        if (r0 == false) goto L6;
        Thread.currentThread().interrupt();
    L6:
        return r12;
    L11:
        r0 = true;
    L7:
        th = move-exception;
        if (r0 == false) goto L10;
        Thread.currentThread().interrupt();
    L10:
        throw th;
    }

    private static final Throwable nonNullCause(ExecutionException r0) {
        return r0.getCause();
    }
}
