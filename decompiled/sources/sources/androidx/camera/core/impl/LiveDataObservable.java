package androidx.camera.core.impl;

import android.os.SystemClock;
import androidx.camera.core.impl.Observable;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executor;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public final class LiveDataObservable<T> implements Observable<T> {
    final MutableLiveData<Result<T>> mLiveData;
    private Observer<Result<T>> mLiveDataObserver;
    private final Map<Observable.Observer<? super T>, Executor> mObservers;

    public static final class Result<T> {
        private final Throwable mError;
        private final T mValue;

        private Result(T r1, Throwable r2) {
            this.mValue = r1;
            this.mError = r2;
        }

        public static <T> Result<T> fromValue(T r2) {
            return new Result(r2, null);
        }

        public boolean completedSuccessfully() {
            if (this.mError != null) goto L6;
            return true;
        L6:
            return false;
        }

        public Throwable getError() {
            return this.mError;
        }

        public T getValue() {
            if (completedSuccessfully() == true) goto L5;
            Segment$$ExternalSyntheticBUOutline1.m("Result contains an error. Does not contain a value.");
            return null;
        L5:
            return this.mValue;
        }

        public String toString() {
            StringBuilder r0 = new StringBuilder("[Result: <");
            if (completedSuccessfully() == false) goto L5;
            String r3 = "Value: " + this.mValue;
        L6:
            r0.append(r3);
            r0.append(">]");
            return r0.toString();
        L5:
            r3 = "Error: " + this.mError;
            goto L6
        }
    }

    public static /* synthetic */ void $r8$lambda$7Sc_53ASTYjdYS62VV4J0wSJfBk(LiveDataObservable r3, Result r4) {
        Map<Observable.Observer<? super T>, Executor> r0 = r3.mObservers;
        monitor-enter(r0);
        HashMap r1 = new HashMap(r3.mObservers);     // Catch: Throwable -> L11
        monitor-exit(r0);     // Catch: Throwable -> L11
        Iterator r32 = r1.entrySet().iterator();
    L8:
        if (r32.hasNext() == false) goto L10;
        Map.Entry r02 = (Map.Entry) r32.next();
        ((Executor) r02.getValue()).execute(new LiveDataObservable$$ExternalSyntheticLambda6(r02, r4));
        goto L8
    L10:
        return;
    L11:
        th = move-exception;
        throw th;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void $r8$lambda$AWTQLUu3rNfTZHr0s2jqBJBGAbk(Map.Entry r1, Result r2) {
        Observable.Observer r12 = (Observable.Observer) r1.getKey();
        if (r2.completedSuccessfully() == false) goto L6;
        r12.onNewData(r2.getValue());
        return;
    L6:
        Preconditions.checkNotNull(r2.getError());
        r12.onError(r2.getError());
    }

    /* renamed from: $r8$lambda$DTQl2TjsJw2q-1DsAXZ5y6fSWXY, reason: not valid java name */
    public static /* synthetic */ Object m600$r8$lambda$DTQl2TjsJw2q1DsAXZ5y6fSWXY(LiveDataObservable r2, CallbackToFutureAdapter.Completer r3) {
        r2.getClass();
        CameraXExecutors.mainThreadExecutor().execute(new LiveDataObservable$$ExternalSyntheticLambda4(r2, r3));
        return r2 + " [fetch@" + SystemClock.uptimeMillis() + "]";
    }

    public static /* synthetic */ void $r8$lambda$LKT2v2fAl083LP0BjTe4nlYR_DU(LiveDataObservable r1) {
        if (r1.mLiveDataObserver != null) goto L5;
        r1.mLiveDataObserver = new LiveDataObservable$$ExternalSyntheticLambda5(r1);
    L5:
        r1.mLiveData.observeForever(r1.mLiveDataObserver);
    }

    /* renamed from: $r8$lambda$RD-gtUSA8acdlq9PBbMSiiQGdR4, reason: not valid java name */
    public static /* synthetic */ void m601$r8$lambda$RDgtUSA8acdlq9PBbMSiiQGdR4(LiveDataObservable r1) {
        Observer<Result<T>> r0 = r1.mLiveDataObserver;
        if (r0 == null) goto L6;
        r1.mLiveData.removeObserver(r0);
        return;
    }

    public static /* synthetic */ void $r8$lambda$bIiLsq5VMRHUgaxTWfxQrAa_AEA(LiveDataObservable r1, Observable.Observer r2) {
        Result<T> r12 = r1.mLiveData.getValue();
        if (r12 != null) goto L6;
        return;
    L6:
        if (r12.completedSuccessfully() == false) goto L9;
        r2.onNewData(r12.getValue());
        return;
    L9:
        Preconditions.checkNotNull(r12.getError());
        r2.onError(r12.getError());
    }

    public static /* synthetic */ void $r8$lambda$sc5wmr8WXbqD092EUWP6jtN6OSA(LiveDataObservable r1, CallbackToFutureAdapter.Completer r2) {
        Result<T> r12 = r1.mLiveData.getValue();
        if (r12 != null) goto L7;
        r2.setException(new IllegalStateException("Observable has not yet been initialized with a value."));
        return;
    L7:
        if (r12.completedSuccessfully() == false) goto L10;
        r2.set(r12.getValue());
        return;
    L10:
        Preconditions.checkNotNull(r12.getError());
        r2.setException(r12.getError());
    }

    public LiveDataObservable() {
        this.mLiveData = new MutableLiveData();
        this.mObservers = new HashMap();
    }

    private void disableInternalObserver() {
        CameraXExecutors.mainThreadExecutor().execute(new LiveDataObservable$$ExternalSyntheticLambda2(this));
    }

    private void enableInternalObserver() {
        CameraXExecutors.mainThreadExecutor().execute(new LiveDataObservable$$ExternalSyntheticLambda3(this));
    }

    @Override // androidx.camera.core.impl.Observable
    public void addObserver(Executor r4, Observable.Observer<? super T> r5) {
        Map<Observable.Observer<? super T>, Executor> r0 = this.mObservers;
        monitor-enter(r0);
        boolean r1 = this.mObservers.isEmpty();     // Catch: Throwable -> L7
        this.mObservers.put(r5, r4);     // Catch: Throwable -> L7
        if (r1 == false) goto L9;
        enableInternalObserver();     // Catch: Throwable -> L7
    L10:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L9:
        r4.execute(new LiveDataObservable$$ExternalSyntheticLambda0(this, r5));     // Catch: Throwable -> L7
    L7:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.core.impl.Observable
    public ListenableFuture<T> fetchData() {
        return CallbackToFutureAdapter.getFuture(new LiveDataObservable$$ExternalSyntheticLambda1(this));
    }

    public void postValue(T r1) {
        this.mLiveData.postValue(Result.fromValue(r1));
    }

    @Override // androidx.camera.core.impl.Observable
    public void removeObserver(Observable.Observer<? super T> r3) {
        Map<Observable.Observer<? super T>, Executor> r0 = this.mObservers;
        monitor-enter(r0);
        this.mObservers.remove(r3);     // Catch: Throwable -> L7
        if (this.mObservers.isEmpty() == false) goto L9;
        disableInternalObserver();     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }
}
