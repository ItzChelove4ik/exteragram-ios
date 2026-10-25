package androidx.concurrent.futures;

import com.google.common.util.concurrent.ListenableFuture;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public abstract class CallbackToFutureAdapter {

    public static final class Completer<T> {
        private boolean attemptedSetting;
        private ResolvableFuture<Void> cancellationFuture;
        SafeFuture<T> future;
        Object tag;

        public Completer() {
            this.cancellationFuture = ResolvableFuture.create();
        }

        private void setCompletedNormally() {
            this.tag = null;
            this.future = null;
            this.cancellationFuture = null;
        }

        public void addCancellationListener(Runnable r1, Executor r2) {
            ResolvableFuture<Void> r0 = this.cancellationFuture;
            if (r0 == null) goto L6;
            r0.addListener(r1, r2);
            return;
        }

        public void finalize() {
            SafeFuture<T> r0 = this.future;
            if (r0 == null) goto L8;
            if (r0.isDone() == true) goto L8;
            r0.setException(new FutureGarbageCollectedException("The completer object was garbage collected - this future would otherwise never complete. The tag was: " + this.tag));
        L8:
            if (this.attemptedSetting == true) goto L13;
            ResolvableFuture<Void> r4 = this.cancellationFuture;
            if (r4 == null) goto L14;
            r4.set(null);
            return;
        L14:
            return;
        }

        public void fireCancellationListeners() {
            this.tag = null;
            this.future = null;
            this.cancellationFuture.set(null);
        }

        public boolean set(T r3) {
            boolean r0 = true;
            this.attemptedSetting = true;
            SafeFuture<T> r1 = this.future;
            if (r1 != null) goto L5;
        L7:
            r0 = false;
        L8:
            if (r0 == false) goto L10;
            setCompletedNormally();
        L10:
            return r0;
        L5:
            if (r1.set(r3) == false) goto L7;
            goto L7
        }

        public boolean setCancelled() {
            boolean r0 = true;
            this.attemptedSetting = true;
            SafeFuture<T> r1 = this.future;
            if (r1 != null) goto L5;
        L7:
            r0 = false;
        L8:
            if (r0 == false) goto L10;
            setCompletedNormally();
        L10:
            return r0;
        L5:
            if (r1.cancelWithoutNotifyingCompleter(true) == false) goto L7;
            goto L7
        }

        public boolean setException(Throwable r3) {
            boolean r0 = true;
            this.attemptedSetting = true;
            SafeFuture<T> r1 = this.future;
            if (r1 != null) goto L5;
        L7:
            r0 = false;
        L8:
            if (r0 == false) goto L10;
            setCompletedNormally();
        L10:
            return r0;
        L5:
            if (r1.setException(r3) == false) goto L7;
            goto L7
        }
    }

    /* loaded from: classes3.dex */
    public static final class FutureGarbageCollectedException extends Throwable {
        public FutureGarbageCollectedException(String r1) {
            super(r1);
        }

        @Override // java.lang.Throwable
        public synchronized Throwable fillInStackTrace() {
            monitor-enter(this);
            monitor-exit(this);
            return this;
        }
    }

    public interface Resolver<T> {
        Object attachCompleter(Completer<T> r1);
    }

    public static final class SafeFuture<T> implements ListenableFuture<T> {
        final WeakReference<Completer<T>> completerWeakReference;
        private final AbstractResolvableFuture<T> delegate;

        /* renamed from: androidx.concurrent.futures.CallbackToFutureAdapter$SafeFuture$1, reason: invalid class name */
        public class AnonymousClass1 extends AbstractResolvableFuture<T> {
            final /* synthetic */ SafeFuture this$0;

            public AnonymousClass1(SafeFuture r1) {
                this.this$0 = r1;
            }

            @Override // androidx.concurrent.futures.AbstractResolvableFuture
            public String pendingToString() {
                Completer<T> r2 = this.this$0.completerWeakReference.get();
                if (r2 != null) goto L7;
                return "Completer object has been garbage collected, future will fail soon";
            L7:
                return "tag=[" + r2.tag + "]";
            }
        }

        public SafeFuture(Completer<T> r2) {
            this.delegate = new AnonymousClass1(this);
            this.completerWeakReference = new WeakReference(r2);
        }

        @Override // com.google.common.util.concurrent.ListenableFuture
        public void addListener(Runnable r1, Executor r2) {
            this.delegate.addListener(r1, r2);
        }

        @Override // java.util.concurrent.Future
        public boolean cancel(boolean r2) {
            Completer<T> r0 = this.completerWeakReference.get();
            boolean r1 = this.delegate.cancel(r2);
            if (r1 == false) goto L6;
            if (r0 == null) goto L6;
            r0.fireCancellationListeners();
        L6:
            return r1;
        }

        public boolean cancelWithoutNotifyingCompleter(boolean r1) {
            return this.delegate.cancel(r1);
        }

        @Override // java.util.concurrent.Future
        public T get() {
            return this.delegate.get();
        }

        @Override // java.util.concurrent.Future
        public T get(long r1, TimeUnit r3) {
            return this.delegate.get(r1, r3);
        }

        @Override // java.util.concurrent.Future
        public boolean isCancelled() {
            return this.delegate.isCancelled();
        }

        @Override // java.util.concurrent.Future
        public boolean isDone() {
            return this.delegate.isDone();
        }

        public boolean set(T r1) {
            return this.delegate.set(r1);
        }

        public boolean setException(Throwable r1) {
            return this.delegate.setException(r1);
        }

        public String toString() {
            return this.delegate.toString();
        }
    }

    public static <T> ListenableFuture<T> getFuture(Resolver<T> r3) {
        Completer<T> r0 = new Completer();
        SafeFuture<T> r1 = new SafeFuture(r0);
        r0.future = r1;
        r0.tag = r3.getClass();
        Object r32 = r3.attachCompleter(r0);     // Catch: Exception -> L7
        if (r32 == null) goto L9;
        r0.tag = r32;     // Catch: Exception -> L7
        return r1;
    L9:
        return r1;
    L7:
        e = move-exception;
        r1.setException(e);
        return r1;
    }
}
