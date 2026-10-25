package androidx.lifecycle;

import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.lifecycle.Lifecycle;
import c.f$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public abstract class LiveData<T> {
    static final Object NOT_SET = null;
    int mActiveCount;
    private boolean mChangingActiveState;
    private volatile Object mData;
    final Object mDataLock;
    private boolean mDispatchInvalidated;
    private boolean mDispatchingValue;
    private SafeIterableMap<Observer<? super T>, LiveData<T>.ObserverWrapper> mObservers;
    volatile Object mPendingData;
    private final Runnable mPostValueRunnable;
    private int mVersion;

    /* renamed from: androidx.lifecycle.LiveData$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ LiveData this$0;

        public AnonymousClass1(LiveData r1) {
            this.this$0 = r1;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            Object r0 = this.this$0.mDataLock;
            monitor-enter(r0);
            Object r1 = this.this$0.mPendingData;     // Catch: Throwable -> L8
            LiveData r2 = this.this$0;     // Catch: Throwable -> L8
            r2.mPendingData = LiveData.NOT_SET;     // Catch: Throwable -> L8
            monitor-exit(r0);     // Catch: Throwable -> L8
            this.this$0.setValue(r1);
            return;
        L8:
            th = move-exception;
            throw th;
        }
    }

    /* loaded from: classes3.dex */
    public class AlwaysActiveObserver extends LiveData<T>.ObserverWrapper {
        final /* synthetic */ LiveData this$0;

        public AlwaysActiveObserver(LiveData r1, Observer<? super T> r2) {
            this.this$0 = r1;
            super(r1, r2);
        }

        @Override // androidx.lifecycle.LiveData.ObserverWrapper
        public boolean shouldBeActive() {
            return true;
        }
    }

    /* loaded from: classes3.dex */
    public class LifecycleBoundObserver extends LiveData<T>.ObserverWrapper implements LifecycleEventObserver {
        final LifecycleOwner mOwner;
        final /* synthetic */ LiveData this$0;

        public LifecycleBoundObserver(LiveData r1, LifecycleOwner r2, Observer<? super T> r3) {
            this.this$0 = r1;
            super(r1, r3);
            this.mOwner = r2;
        }

        @Override // androidx.lifecycle.LiveData.ObserverWrapper
        public void detachObserver() {
            this.mOwner.getLifecycle().removeObserver(this);
        }

        @Override // androidx.lifecycle.LiveData.ObserverWrapper
        public boolean isAttachedTo(LifecycleOwner r1) {
            if (this.mOwner != r1) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // androidx.lifecycle.LifecycleEventObserver
        public void onStateChanged(LifecycleOwner r2, Lifecycle.Event r3) {
            Lifecycle.State r22 = this.mOwner.getLifecycle().getCurrentState();
            if (r22 != Lifecycle.State.DESTROYED) goto L6;
            this.this$0.removeObserver(this.mObserver);
            return;
        L6:
            Lifecycle.State r32 = null;
        L7:
            if (r32 == r22) goto L9;
            activeStateChanged(shouldBeActive());
            Lifecycle.State r33 = this.mOwner.getLifecycle().getCurrentState();
            r32 = r22;
            r22 = r33;
            goto L7
        }

        @Override // androidx.lifecycle.LiveData.ObserverWrapper
        public boolean shouldBeActive() {
            return this.mOwner.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED);
        }
    }

    /* loaded from: classes3.dex */
    public abstract class ObserverWrapper {
        boolean mActive;
        int mLastVersion;
        final Observer<? super T> mObserver;
        final /* synthetic */ LiveData this$0;

        public ObserverWrapper(LiveData r1, Observer<? super T> r2) {
            this.this$0 = r1;
            this.mLastVersion = -1;
            this.mObserver = r2;
        }

        public void activeStateChanged(boolean r2) {
            if (r2 == this.mActive) goto L14;
            this.mActive = r2;
            LiveData r0 = this.this$0;
            if (r2 == false) goto L8;
            int r22 = 1;
        L9:
            r0.changeActiveCounter(r22);
            if (this.mActive == false) goto L13;
            this.this$0.dispatchingValue(this);
            return;
        L13:
            return;
        L8:
            r22 = -1;
            goto L9
        }

        public void detachObserver() {
        }

        public boolean isAttachedTo(LifecycleOwner r1) {
            return false;
        }

        public abstract boolean shouldBeActive();
    }

    static {
        NOT_SET = new Object();
    }

    public LiveData() {
        this.mDataLock = new Object();
        this.mObservers = new SafeIterableMap();
        this.mActiveCount = 0;
        Object r0 = NOT_SET;
        this.mPendingData = r0;
        this.mPostValueRunnable = new AnonymousClass1(this);
        this.mData = r0;
        this.mVersion = -1;
    }

    public LiveData(T r3) {
        this.mDataLock = new Object();
        this.mObservers = new SafeIterableMap();
        this.mActiveCount = 0;
        this.mPendingData = NOT_SET;
        this.mPostValueRunnable = new AnonymousClass1(this);
        this.mData = r3;
        this.mVersion = 0;
    }

    public static void assertMainThread(String r2) {
        if (ArchTaskExecutor.getInstance().isMainThread() == false) goto L5;
        return;
    L5:
        LiveData$$ExternalSyntheticBUOutline0.m("Cannot invoke ", r2, " on a background thread");
    }

    private void considerNotify(LiveData<T>.ObserverWrapper r3) {
        if (r3.mActive == true) goto L6;
        return;
    L6:
        if (r3.shouldBeActive() == true) goto L9;
        r3.activeStateChanged(false);
        return;
    L9:
        int r0 = r3.mLastVersion;
        int r1 = this.mVersion;
        if (r0 < r1) goto L12;
        return;
    L12:
        r3.mLastVersion = r1;
        r3.mObserver.onChanged((Object) this.mData);
    }

    public void changeActiveCounter(int r5) {
        int r0 = this.mActiveCount;
        this.mActiveCount = r5 + r0;
        if (this.mChangingActiveState == false) goto L5;
        return;
    L5:
        this.mChangingActiveState = true;
    L28:
        int r2 = this.mActiveCount;     // Catch: Throwable -> L19
        if (r0 == r2) goto L24;
        if (r0 != 0) goto L12;
        if (r2 <= 0) goto L12;
        boolean r3 = true;
    L13:
        if (r0 <= 0) goto L16;
        if (r2 != 0) goto L16;
        boolean r02 = true;
    L17:
        if (r3 == false) goto L21;
        onActive();     // Catch: Throwable -> L19
    L23:
        r0 = r2;
        goto L28
    L21:
        if (r02 == false) goto L23;
        onInactive();     // Catch: Throwable -> L19
    L16:
        r02 = false;
    L12:
        r3 = false;
        goto L13
    L24:
        this.mChangingActiveState = false;
        return;
    L19:
        th = move-exception;
        this.mChangingActiveState = false;
        throw th;
    }

    public void dispatchingValue(LiveData<T>.ObserverWrapper r4) {
        if (this.mDispatchingValue == false) goto L6;
        this.mDispatchInvalidated = true;
        return;
    L6:
        this.mDispatchingValue = true;
    L7:
        this.mDispatchInvalidated = false;
        if (r4 == null) goto L10;
        considerNotify(r4);
        r4 = null;
    L16:
        if (this.mDispatchInvalidated == true) goto L7;
        this.mDispatchingValue = false;
        return;
    L10:
        SafeIterableMap<Observer<? super T>, LiveData<T>.ObserverWrapper>.IteratorWithAdditions r1 = this.mObservers.iteratorWithAdditions();
    L12:
        if (r1.hasNext() == false) goto L16;
        considerNotify((ObserverWrapper) r1.next().getValue());
        if (this.mDispatchInvalidated == false) goto L12;
        goto L16
    }

    public T getValue() {
        T r1 = (T) this.mData;
        if (r1 == NOT_SET) goto L5;
        return r1;
    L5:
        return null;
    }

    public boolean hasActiveObservers() {
        if (this.mActiveCount <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    public void observe(LifecycleOwner r3, Observer<? super T> r4) {
        assertMainThread("observe");
        if (r3.getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED) goto L16;
        LifecycleBoundObserver r0 = new LifecycleBoundObserver(this, r3, r4);
        LiveData<T>.ObserverWrapper r2 = this.mObservers.putIfAbsent(r4, r0);
        if (r2 != null) goto L8;
    L12:
        if (r2 == null) goto L14;
        return;
    L14:
        r3.getLifecycle().addObserver(r0);
        return;
    L8:
        if (r2.isAttachedTo(r3) == true) goto L12;
        f$$ExternalSyntheticBUOutline1.m("Cannot add the same observer with different lifecycles");
        return;
    }

    public void observeForever(Observer<? super T> r2) {
        assertMainThread("observeForever");
        AlwaysActiveObserver r0 = new AlwaysActiveObserver(this, r2);
        LiveData<T>.ObserverWrapper r1 = this.mObservers.putIfAbsent(r2, r0);
        if ((r1 instanceof LifecycleBoundObserver) == true) goto L8;
        if (r1 == null) goto L6;
        return;
    L6:
        r0.activeStateChanged(true);
        return;
    L8:
        f$$ExternalSyntheticBUOutline1.m("Cannot add the same observer with different lifecycles");
    }

    public void onActive() {
    }

    public void onInactive() {
    }

    public void postValue(T r4) {
        Object r0 = this.mDataLock;
        monitor-enter(r0);
    L14:
        th = move-exception;
        throw th;
    L5:
        if (this.mPendingData != NOT_SET) goto L7;
        boolean r1 = true;
    L8:
        this.mPendingData = r4;     // Catch: Throwable -> L14
        monitor-exit(r0);     // Catch: Throwable -> L14
        if (r1 == true) goto L12;
        return;
    L12:
        ArchTaskExecutor.getInstance().postToMainThread(this.mPostValueRunnable);
        return;
    L7:
        r1 = false;
        goto L8
    }

    public void removeObserver(Observer<? super T> r2) {
        assertMainThread("removeObserver");
        LiveData<T>.ObserverWrapper r1 = this.mObservers.remove(r2);
        if (r1 != null) goto L5;
        return;
    L5:
        r1.detachObserver();
        r1.activeStateChanged(false);
    }

    public void setValue(T r2) {
        assertMainThread("setValue");
        this.mVersion++;
        this.mData = r2;
        dispatchingValue(null);
    }
}
