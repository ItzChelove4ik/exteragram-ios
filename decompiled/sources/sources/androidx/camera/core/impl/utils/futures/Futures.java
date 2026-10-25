package androidx.camera.core.impl.utils.futures;

import androidx.arch.core.util.Function;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.ImmediateFuture;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;

/* loaded from: classes3.dex */
public abstract class Futures {
    private static final Function<?, ?> IDENTITY_FUNCTION = null;

    /* JADX INFO: Add missing generic type declarations: [I, O] */
    /* renamed from: androidx.camera.core.impl.utils.futures.Futures$1, reason: invalid class name */
    public class AnonymousClass1<I, O> implements AsyncFunction<I, O> {
        final /* synthetic */ Function val$function;

        public AnonymousClass1(Function r1) {
            this.val$function = r1;
        }

        @Override // androidx.camera.core.impl.utils.futures.AsyncFunction
        public ListenableFuture<O> apply(I r1) {
            return Futures.immediateFuture(this.val$function.apply(r1));
        }
    }

    /* renamed from: androidx.camera.core.impl.utils.futures.Futures$2, reason: invalid class name */
    public class AnonymousClass2 implements Function<Object, Object> {
        public AnonymousClass2() {
        }

        @Override // androidx.arch.core.util.Function
        public Object apply(Object r1) {
            return r1;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [I] */
    /* renamed from: androidx.camera.core.impl.utils.futures.Futures$3, reason: invalid class name */
    public class AnonymousClass3<I> implements FutureCallback<I> {
        final /* synthetic */ CallbackToFutureAdapter.Completer val$completer;
        final /* synthetic */ Function val$function;

        public AnonymousClass3(CallbackToFutureAdapter.Completer r1, Function r2) {
            this.val$completer = r1;
            this.val$function = r2;
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        public void onFailure(Throwable r1) {
            this.val$completer.setException(r1);
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        public void onSuccess(I r3) {
            this.val$completer.set(this.val$function.apply(r3));     // Catch: Throwable -> L4
            return;
        L4:
            th = move-exception;
            this.val$completer.setException(th);
        }
    }

    /* renamed from: androidx.camera.core.impl.utils.futures.Futures$4, reason: invalid class name */
    public class AnonymousClass4 implements Runnable {
        final /* synthetic */ ListenableFuture val$input;

        public AnonymousClass4(ListenableFuture r1) {
            this.val$input = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.val$input.cancel(true);
        }
    }

    public static final class CallbackListener<V> implements Runnable {
        final FutureCallback<? super V> mCallback;
        final Future<V> mFuture;

        public CallbackListener(Future<V> r1, FutureCallback<? super V> r2) {
            this.mFuture = r1;
            this.mCallback = r2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Object r0 = Futures.getDone(this.mFuture);     // Catch: Error -> L5 RuntimeException -> L7 ExecutionException -> L9
            this.mCallback.onSuccess(r0);
            return;
        L5:
            e = e;
        L11:
            this.mCallback.onFailure(e);
            return;
        L7:
            e = e;
        L9:
            e = move-exception;
            Throwable r1 = e.getCause();
            FutureCallback<? super V> r2 = this.mCallback;
            if (r1 != null) goto L16;
            r2.onFailure(e);
            return;
        L16:
            r2.onFailure(r1);
        }

        public String toString() {
            return CallbackListener.class.getSimpleName() + "," + this.mCallback;
        }
    }

    public static /* synthetic */ Object $r8$lambda$XTux3ACV68DNtllDeHhwL_zb7Yw(ListenableFuture r3, CallbackToFutureAdapter.Completer r4) {
        propagateTransform(false, r3, IDENTITY_FUNCTION, r4, CameraXExecutors.directExecutor());
        return "nonCancellationPropagating[" + r3 + "]";
    }

    static {
        IDENTITY_FUNCTION = new AnonymousClass2();
    }

    public static <V> void addCallback(ListenableFuture<V> r1, FutureCallback<? super V> r2, Executor r3) {
        Preconditions.checkNotNull(r2);
        r1.addListener(new CallbackListener(r1, r2), r3);
    }

    public static <V> V getDone(Future<V> r3) {
        Preconditions.checkState(r3.isDone(), "Future was expected to be done, " + r3);
        return (V) getUninterruptibly(r3);
    }

    public static <V> V getUninterruptibly(Future<V> r1) {
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

    public static <V> ListenableFuture<V> immediateFailedFuture(Throwable r1) {
        return new ImmediateFuture.ImmediateFailedFuture(r1);
    }

    public static <V> ScheduledFuture<V> immediateFailedScheduledFuture(Throwable r1) {
        return new ImmediateFuture.ImmediateFailedScheduledFuture(r1);
    }

    public static <V> ListenableFuture<V> immediateFuture(V r1) {
        if (r1 != null) goto L6;
        return ImmediateFuture.nullFuture();
    L6:
        return new ImmediateFuture.ImmediateSuccessfulFuture(r1);
    }

    public static <V> ListenableFuture<V> nonCancellationPropagating(ListenableFuture<V> r1) {
        Preconditions.checkNotNull(r1);
        if (r1.isDone() == false) goto L6;
        return r1;
    L6:
        return CallbackToFutureAdapter.getFuture(new Futures$$ExternalSyntheticLambda0(r1));
    }

    public static <V> void propagate(ListenableFuture<V> r2, CallbackToFutureAdapter.Completer<V> r3) {
        propagateTransform(r2, IDENTITY_FUNCTION, r3, CameraXExecutors.directExecutor());
    }

    public static <I, O> void propagateTransform(ListenableFuture<I> r1, Function<? super I, ? extends O> r2, CallbackToFutureAdapter.Completer<O> r3, Executor r4) {
        propagateTransform(true, r1, r2, r3, r4);
    }

    private static <I, O> void propagateTransform(boolean r1, ListenableFuture<I> r2, Function<? super I, ? extends O> r3, CallbackToFutureAdapter.Completer<O> r4, Executor r5) {
        Preconditions.checkNotNull(r2);
        Preconditions.checkNotNull(r3);
        Preconditions.checkNotNull(r4);
        Preconditions.checkNotNull(r5);
        addCallback(r2, new AnonymousClass3(r4, r3), r5);
        if (r1 == false) goto L6;
        r4.addCancellationListener(new AnonymousClass4(r2), CameraXExecutors.directExecutor());
        return;
    }

    public static <V> ListenableFuture<List<V>> successfulAsList(Collection<? extends ListenableFuture<? extends V>> r3) {
        return new ListFuture(new ArrayList(r3), false, CameraXExecutors.directExecutor());
    }

    public static <I, O> ListenableFuture<O> transform(ListenableFuture<I> r1, Function<? super I, ? extends O> r2, Executor r3) {
        Preconditions.checkNotNull(r2);
        return transformAsync(r1, new AnonymousClass1(r2), r3);
    }

    public static <I, O> ListenableFuture<O> transformAsync(ListenableFuture<I> r1, AsyncFunction<? super I, ? extends O> r2, Executor r3) {
        ChainingListenableFuture r0 = new ChainingListenableFuture(r2, r1);
        r1.addListener(r0, r3);
        return r0;
    }
}
