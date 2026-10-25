package androidx.camera.core.impl;

import androidx.camera.core.impl.Observable;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes3.dex */
public abstract class StateObservable<T> implements Observable<T> {
    private final Object mLock;
    private final CopyOnWriteArraySet<ObserverWrapper<T>> mNotifySet;
    private final AtomicReference<Object> mState;
    private boolean mUpdating;
    private int mVersion;
    private final Map<Observable.Observer<? super T>, ObserverWrapper<T>> mWrapperMap;

    public static abstract class ErrorWrapper {
        public ErrorWrapper() {
        }

        public static ErrorWrapper wrap(Throwable r1) {
            return new AutoValue_StateObservable_ErrorWrapper(r1);
        }

        public abstract Throwable getError();
    }

    public static final class ObserverWrapper<T> implements Runnable {
        private static final Object NOT_SET = null;
        private final AtomicBoolean mActive;
        private final Executor mExecutor;
        private Object mLastState;
        private int mLatestSignalledVersion;
        private final Observable.Observer<? super T> mObserver;
        private final AtomicReference<Object> mStateRef;
        private boolean mWrapperUpdating;

        static {
            NOT_SET = new Object();
        }

        public ObserverWrapper(AtomicReference<Object> r3, Executor r4, Observable.Observer<? super T> r5) {
            this.mActive = new AtomicBoolean(true);
            this.mLastState = NOT_SET;
            this.mLatestSignalledVersion = -1;
            this.mWrapperUpdating = false;
            this.mStateRef = r3;
            this.mExecutor = r4;
            this.mObserver = r5;
        }

        public void close() {
            this.mActive.set(false);
        }

        @Override // java.lang.Runnable
        public void run() {
            monitor-enter(this);
        L8:
            th = move-exception;
            throw th;
        L4:
            if (this.mActive.get() == true) goto L10;
            this.mWrapperUpdating = false;     // Catch: Throwable -> L8
            monitor-exit(this);     // Catch: Throwable -> L8
            return;
        L10:
            Object r0 = this.mStateRef.get();     // Catch: Throwable -> L8
            int r2 = this.mLatestSignalledVersion;     // Catch: Throwable -> L8
            monitor-exit(this);     // Catch: Throwable -> L8
        L13:
            if (Objects.equals(this.mLastState, r0) == true) goto L18;
            this.mLastState = r0;
            boolean r3 = r0 instanceof ErrorWrapper;
            Observable.Observer<? super T> r4 = this.mObserver;
            if (r3 == false) goto L17;
            r4.onError(((ErrorWrapper) r0).getError());
            goto L18
        L17:
            r4.onNewData(r0);
        L18:
            monitor-enter(this);
        L27:
            th = move-exception;
            throw th;
        L20:
            if (r2 == this.mLatestSignalledVersion) goto L29;
            if (this.mActive.get() == false) goto L29;
            r0 = this.mStateRef.get();     // Catch: Throwable -> L27
            r2 = this.mLatestSignalledVersion;     // Catch: Throwable -> L27
            monitor-exit(this);     // Catch: Throwable -> L27
        L29:
            this.mWrapperUpdating = false;     // Catch: Throwable -> L27
            monitor-exit(this);     // Catch: Throwable -> L27
        }

        public void update(int r2) {
            monitor-enter(this);
        L7:
            th = move-exception;
            throw th;
        L4:
            if (this.mActive.get() == true) goto L10;
            monitor-exit(this);     // Catch: Throwable -> L7
            return;
        L10:
            if (r2 > this.mLatestSignalledVersion) goto L13;
            monitor-exit(this);     // Catch: Throwable -> L7
            return;
        L13:
            this.mLatestSignalledVersion = r2;     // Catch: Throwable -> L7
            if (this.mWrapperUpdating == false) goto L17;
            monitor-exit(this);     // Catch: Throwable -> L7
            return;
        L17:
            this.mWrapperUpdating = true;     // Catch: Throwable -> L7
            monitor-exit(this);     // Catch: Throwable -> L7
            this.mExecutor.execute(this);     // Catch: Throwable -> L21
            return;
        L21:
            monitor-enter(this);
            this.mWrapperUpdating = false;     // Catch: Throwable -> L26
            return;
        L26:
            th = move-exception;
            throw th;
        }
    }

    public StateObservable(Object r2, boolean r3) {
        this.mLock = new Object();
        this.mVersion = 0;
        this.mUpdating = false;
        this.mWrapperMap = new HashMap();
        this.mNotifySet = new CopyOnWriteArraySet();
        if (r3 == false) goto L6;
        Preconditions.checkArgument(r2 instanceof Throwable, "Initial errors must be Throwable");
        this.mState = new AtomicReference(ErrorWrapper.wrap((Throwable) r2));
        return;
    L6:
        this.mState = new AtomicReference(r2);
    }

    private void removeObserverLocked(Observable.Observer<? super T> r2) {
        ObserverWrapper<T> r22 = this.mWrapperMap.remove(r2);
        if (r22 == null) goto L6;
        r22.close();
        this.mNotifySet.remove(r22);
        return;
    }

    private void updateStateInternal(Object r4) {
        Object r0 = this.mLock;
        monitor-enter(r0);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (Objects.equals(this.mState.getAndSet(r4), r4) == false) goto L10;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L10:
        int r42 = this.mVersion + 1;     // Catch: Throwable -> L8
        this.mVersion = r42;     // Catch: Throwable -> L8
        if (this.mUpdating == false) goto L14;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L14:
        this.mUpdating = true;     // Catch: Throwable -> L8
        Iterator<ObserverWrapper<T>> r1 = this.mNotifySet.iterator();     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
    L17:
        if (r1.hasNext() == true) goto L18;
        Object r12 = this.mLock;
        monitor-enter(r12);
    L26:
        th = move-exception;
        throw th;
    L22:
        if (this.mVersion == r42) goto L23;
        Iterator<ObserverWrapper<T>> r43 = this.mNotifySet.iterator();     // Catch: Throwable -> L26
        int r02 = this.mVersion;     // Catch: Throwable -> L26
        monitor-exit(r12);     // Catch: Throwable -> L26
        r1 = r43;
        r42 = r02;
        goto L17
    L23:
        this.mUpdating = false;     // Catch: Throwable -> L26
        monitor-exit(r12);     // Catch: Throwable -> L26
        return;
    L18:
        r1.next().update(r42);
        goto L17
    }

    @Override // androidx.camera.core.impl.Observable
    public void addObserver(Executor r4, Observable.Observer<? super T> r5) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        removeObserverLocked(r5);     // Catch: Throwable -> L8
        ObserverWrapper<T> r1 = new ObserverWrapper(this.mState, r4, r5);     // Catch: Throwable -> L8
        this.mWrapperMap.put(r5, r1);     // Catch: Throwable -> L8
        this.mNotifySet.add(r1);     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        r1.update(0);
        return;
    L8:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.core.impl.Observable
    public ListenableFuture<T> fetchData() {
        Object r1 = this.mState.get();
        if ((r1 instanceof ErrorWrapper) == false) goto L7;
        return Futures.immediateFailedFuture(((ErrorWrapper) r1).getError());
    L7:
        return Futures.immediateFuture(r1);
    }

    @Override // androidx.camera.core.impl.Observable
    public void removeObserver(Observable.Observer<? super T> r2) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        removeObserverLocked(r2);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public void updateState(T r1) {
        updateStateInternal(r1);
    }
}
