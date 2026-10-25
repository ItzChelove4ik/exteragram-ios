package androidx.camera.core.impl.utils.futures;

import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes3.dex */
class ChainingListenableFuture<I, O> extends FutureChain<O> implements Runnable {
    private AsyncFunction<? super I, ? extends O> mFunction;
    private ListenableFuture<? extends I> mInputFuture;
    private final BlockingQueue<Boolean> mMayInterruptIfRunningChannel;
    private final CountDownLatch mOutputCreated;
    volatile ListenableFuture<? extends O> mOutputFuture;

    /* renamed from: androidx.camera.core.impl.utils.futures.ChainingListenableFuture$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ ChainingListenableFuture this$0;
        final /* synthetic */ ListenableFuture val$outputFuture;

        public AnonymousClass1(ChainingListenableFuture r1, ListenableFuture r2) {
            this.this$0 = r1;
            this.val$outputFuture = r2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r3v3, types: [androidx.camera.core.impl.utils.futures.ChainingListenableFuture] */
        @Override // java.lang.Runnable
        public void run() {
            this.this$0.set(Futures.getUninterruptibly(this.val$outputFuture));     // Catch: Throwable -> L6 ExecutionException -> L8 CancellationException -> L10
        L4:
            AnonymousClass1 this = this.this$0;
            this.mOutputFuture = null;
            return;
        L6:
            th = move-exception;
            this.this$0.mOutputFuture = null;
            throw th;
        L10:
            this.this$0.cancel(false);     // Catch: Throwable -> L6
        L8:
            e = move-exception;
            this.this$0.setException(e.getCause());     // Catch: Throwable -> L6
            goto L4
        }
    }

    public ChainingListenableFuture(AsyncFunction<? super I, ? extends O> r3, ListenableFuture<? extends I> r4) {
        this.mMayInterruptIfRunningChannel = new LinkedBlockingQueue(1);
        this.mOutputCreated = new CountDownLatch(1);
        this.mFunction = (AsyncFunction) Preconditions.checkNotNull(r3);
        this.mInputFuture = (ListenableFuture) Preconditions.checkNotNull(r4);
    }

    private void cancel(Future<?> r1, boolean r2) {
        if (r1 == null) goto L5;
        r1.cancel(r2);
        return;
    }

    private <E> void putUninterruptibly(BlockingQueue<E> r1, E r2) {
        boolean r0 = false;
    L12:
        r1.put(r2);     // Catch: Throwable -> L7 InterruptedException -> L11
    L4:
        if (r0 == false) goto L15;
        Thread.currentThread().interrupt();
        return;
    L15:
        return;
    L11:
        r0 = true;
    L7:
        th = move-exception;
        if (r0 == false) goto L10;
        Thread.currentThread().interrupt();
    L10:
        throw th;
    }

    private <E> E takeUninterruptibly(BlockingQueue<E> r1) {
        boolean r0 = false;
    L12:
        E r12 = r1.take();     // Catch: Throwable -> L7 InterruptedException -> L11
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

    @Override // androidx.camera.core.impl.utils.futures.FutureChain, java.util.concurrent.Future
    public boolean cancel(boolean r3) {
        if (super.cancel(r3) == false) goto L6;
        putUninterruptibly(this.mMayInterruptIfRunningChannel, Boolean.valueOf(r3));
        cancel(this.mInputFuture, r3);
        cancel(this.mOutputFuture, r3);
        return true;
    L6:
        return false;
    }

    @Override // androidx.camera.core.impl.utils.futures.FutureChain, java.util.concurrent.Future
    public O get() {
        if (isDone() == true) goto L11;
        ListenableFuture<? extends I> r0 = this.mInputFuture;
        if (r0 == null) goto L7;
        r0.get();
    L7:
        this.mOutputCreated.await();
        ListenableFuture<? extends O> r02 = this.mOutputFuture;
        if (r02 == null) goto L11;
        r02.get();
    L11:
        return (O) super.get();
    }

    @Override // androidx.camera.core.impl.utils.futures.FutureChain, java.util.concurrent.Future
    public O get(long r8, TimeUnit r10) {
        if (isDone() == true) goto L18;
        TimeUnit r0 = TimeUnit.NANOSECONDS;
        if (r10 == r0) goto L7;
        r8 = r0.convert(r8, r10);
        r10 = r0;
    L7:
        ListenableFuture<? extends I> r02 = this.mInputFuture;
        if (r02 == null) goto L10;
        long r3 = System.nanoTime();
        r02.get(r8, r10);
        r8 = r8 - Math.max(0, System.nanoTime() - r3);
    L10:
        long r32 = System.nanoTime();
        if (this.mOutputCreated.await(r8, r10) == false) goto L16;
        r8 = r8 - Math.max(0, System.nanoTime() - r32);
        ListenableFuture<? extends O> r03 = this.mOutputFuture;
        if (r03 == null) goto L18;
        r03.get(r8, r10);
        goto L18
    L16:
        throw new TimeoutException();
    L18:
        return (O) super.get(r8, r10);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [androidx.camera.core.impl.utils.futures.ChainingListenableFuture, androidx.camera.core.impl.utils.futures.ChainingListenableFuture<I, O>, androidx.camera.core.impl.utils.futures.FutureChain] */
    /* JADX WARN: Type inference failed for: r4v1, types: [androidx.camera.core.impl.utils.futures.ChainingListenableFuture] */
    /* JADX WARN: Type inference failed for: r4v3, types: [androidx.camera.core.impl.utils.futures.FutureChain] */
    /* JADX WARN: Type inference failed for: r4v6, types: [androidx.camera.core.impl.utils.futures.FutureChain] */
    /* JADX WARN: Type inference failed for: r4v7, types: [androidx.camera.core.impl.utils.futures.FutureChain] */
    /* JADX WARN: Type inference failed for: r4v8, types: [java.util.concurrent.CountDownLatch] */
    @Override // java.lang.Runnable
    public void run() {
        Object r1 = Futures.getUninterruptibly(this.mInputFuture);     // Catch: Throwable -> L9 Error -> L11 Exception -> L13 UndeclaredThrowableException -> L15 ExecutionException -> L18 CancellationException -> L20
        ListenableFuture<? extends O> r12 = this.mFunction.apply(r1);     // Catch: Throwable -> L9 Error -> L11 Exception -> L13 UndeclaredThrowableException -> L15
        this.mOutputFuture = r12;     // Catch: Throwable -> L9 Error -> L11 Exception -> L13 UndeclaredThrowableException -> L15
        if (isCancelled() == false) goto L17;
        r12.cancel(((Boolean) takeUninterruptibly(this.mMayInterruptIfRunningChannel)).booleanValue());     // Catch: Throwable -> L9 Error -> L11 Exception -> L13 UndeclaredThrowableException -> L15
        this.mOutputFuture = null;     // Catch: Throwable -> L9 Error -> L11 Exception -> L13 UndeclaredThrowableException -> L15
    L7:
        this.mFunction = null;
        this.mInputFuture = null;
        ChainingListenableFuture<I, O> this = (ChainingListenableFuture<I, O>) this.mOutputCreated;
        this.countDown();
        return;
    L17:
        r12.addListener(new AnonymousClass1(this, r12), CameraXExecutors.directExecutor());     // Catch: Throwable -> L9 Error -> L11 Exception -> L13 UndeclaredThrowableException -> L15
        goto L7
    L13:
        e = move-exception;
        this.setException(e);     // Catch: Throwable -> L9
        ChainingListenableFuture r4 = this;
    L23:
        r4.mFunction = null;
        r4.mInputFuture = null;
        r4.mOutputCreated.countDown();
        return;
    L11:
        e = move-exception;
        this.setException(e);     // Catch: Throwable -> L9
        r4 = this;
    L20:
        cancel(false);     // Catch: Throwable -> L9 Error -> L11 Exception -> L13 UndeclaredThrowableException -> L15
    L18:
        e = move-exception;
        setException(e.getCause());     // Catch: Throwable -> L9 Error -> L11 Exception -> L13 UndeclaredThrowableException -> L15
    L15:
        e = move-exception;
        this.setException(e.getCause());     // Catch: Throwable -> L9
        r4 = this;
    L9:
        th = move-exception;
        this.mFunction = null;
        this.mInputFuture = null;
        this.mOutputCreated.countDown();
        throw th;
    }
}
