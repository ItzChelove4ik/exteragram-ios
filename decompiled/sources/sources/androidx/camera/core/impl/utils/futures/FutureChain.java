package androidx.camera.core.impl.utils.futures;

import androidx.arch.core.util.Function;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
public class FutureChain<V> implements ListenableFuture<V> {
    CallbackToFutureAdapter.Completer<V> mCompleter;
    private final ListenableFuture<V> mDelegate;

    /* renamed from: androidx.camera.core.impl.utils.futures.FutureChain$1, reason: invalid class name */
    public class AnonymousClass1 implements CallbackToFutureAdapter.Resolver<V> {
        final /* synthetic */ FutureChain this$0;

        public AnonymousClass1(FutureChain r1) {
            this.this$0 = r1;
        }

        @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
        public Object attachCompleter(CallbackToFutureAdapter.Completer<V> r3) {
            if (this.this$0.mCompleter != null) goto L5;
            boolean r0 = true;
        L6:
            Preconditions.checkState(r0, "The result can only set once!");
            this.this$0.mCompleter = r3;
            return "FutureChain[" + this.this$0 + "]";
        L5:
            r0 = false;
            goto L6
        }
    }

    public FutureChain() {
        this.mDelegate = CallbackToFutureAdapter.getFuture(new AnonymousClass1(this));
    }

    public FutureChain(ListenableFuture<V> r1) {
        this.mDelegate = (ListenableFuture) Preconditions.checkNotNull(r1);
    }

    public static <V> FutureChain<V> from(ListenableFuture<V> r1) {
        if ((r1 instanceof FutureChain) == false) goto L7;
        return (FutureChain) r1;
    L7:
        return new FutureChain(r1);
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public void addListener(Runnable r1, Executor r2) {
        this.mDelegate.addListener(r1, r2);
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean r1) {
        return this.mDelegate.cancel(r1);
    }

    @Override // java.util.concurrent.Future
    public V get() {
        return this.mDelegate.get();
    }

    @Override // java.util.concurrent.Future
    public V get(long r1, TimeUnit r3) {
        return this.mDelegate.get(r1, r3);
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return this.mDelegate.isCancelled();
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return this.mDelegate.isDone();
    }

    public boolean set(V r1) {
        CallbackToFutureAdapter.Completer<V> r0 = this.mCompleter;
        if (r0 != null) goto L5;
        return false;
    L5:
        return r0.set(r1);
    }

    public boolean setException(Throwable r1) {
        CallbackToFutureAdapter.Completer<V> r0 = this.mCompleter;
        if (r0 != null) goto L5;
        return false;
    L5:
        return r0.setException(r1);
    }

    public final <T> FutureChain<T> transform(Function<? super V, T> r1, Executor r2) {
        return (FutureChain) Futures.transform(this, r1, r2);
    }

    public final <T> FutureChain<T> transformAsync(AsyncFunction<? super V, T> r1, Executor r2) {
        return (FutureChain) Futures.transformAsync(this, r1, r2);
    }
}
