package androidx.camera.core.impl;

import android.util.Log;
import androidx.camera.core.CameraIdentifier;
import androidx.camera.core.impl.Observable;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;

/* loaded from: classes3.dex */
public abstract class AbstractCameraPresenceSource implements Observable<List<CameraIdentifier>> {
    private List<CameraIdentifier> mCurrentData;
    private Throwable mCurrentError;
    private boolean mIsActive;
    private final Object mLock;
    private final List<ObserverWrapper> mObservers;

    public static class ObserverWrapper {
        final Executor mExecutor;
        final Observable.Observer<? super List<CameraIdentifier>> mObserver;

        public ObserverWrapper(Executor r1, Observable.Observer<? super List<CameraIdentifier>> r2) {
            this.mExecutor = r1;
            this.mObserver = r2;
        }
    }

    public static /* synthetic */ void $r8$lambda$DY6WJHcCvKO5lgzk4F3BATyppQM(Throwable r0, ObserverWrapper r1, List r2) {
        if (r0 == null) goto L5;
        r1.mObserver.onError(r0);
        return;
    L5:
        r1.mObserver.onNewData(r2);
    }

    public AbstractCameraPresenceSource(List<String> r3) {
        this.mLock = new Object();
        this.mObservers = new CopyOnWriteArrayList();
        this.mCurrentError = null;
        this.mIsActive = false;
        ArrayList r0 = new ArrayList();
        Iterator<String> r32 = r3.iterator();
    L4:
        if (r32.hasNext() == false) goto L6;
        r0.add(CameraIdentifier.Factory.create(r32.next()));
        goto L4
    L6:
        this.mCurrentData = r0;
    }

    private void notifyObserver(ObserverWrapper r2, List<CameraIdentifier> r3, Throwable r4) {
        r2.mExecutor.execute(new AbstractCameraPresenceSource$$ExternalSyntheticLambda0(r4, r2, r3));
    }

    private void updateState(List<CameraIdentifier> r6, Throwable r7) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        boolean r1 = true;
        if (r7 != null) goto L7;
        Preconditions.checkNotNull(r6);     // Catch: Throwable -> L12
        if (this.mCurrentError == null) goto L19;
    L22:
        boolean r72 = true;
    L23:
        this.mCurrentError = null;     // Catch: Throwable -> L12
        this.mCurrentData = r6;     // Catch: Throwable -> L12
        boolean r62 = r72;
    L24:
        List<CameraIdentifier> r73 = Collections.unmodifiableList(this.mCurrentData);     // Catch: Throwable -> L12
        Throwable r3 = this.mCurrentError;     // Catch: Throwable -> L12
        monitor-exit(r0);     // Catch: Throwable -> L12
        if (r62 == false) goto L35;
        StringBuilder r02 = new StringBuilder("Data changed. Notifying ");
        r02.append(this.mObservers.size());
        r02.append(" observers. Error: ");
        if (r3 != null) goto L31;
        r1 = false;
    L31:
        r02.append(r1);
        Log.d("CameraPresenceSrc", r02.toString());
        Iterator<ObserverWrapper> r63 = this.mObservers.iterator();
    L33:
        if (r63.hasNext() == false) goto L41;
        notifyObserver(r63.next(), r73, r3);
        goto L33
    L41:
        return;
    L35:
        return;
    L19:
        if (this.mCurrentData.equals(r6) == false) goto L22;
        r72 = false;
        goto L23
    L7:
        if (this.mCurrentError != null) goto L9;
    L14:
        r62 = true;
    L15:
        this.mCurrentError = r7;     // Catch: Throwable -> L12
        this.mCurrentData = Collections.EMPTY_LIST;     // Catch: Throwable -> L12
        goto L24
    L9:
        if (this.mCurrentData.isEmpty() == false) goto L14;
        r62 = false;
    L12:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.core.impl.Observable
    public void addObserver(Executor r4, Observable.Observer<? super List<CameraIdentifier>> r5) {
        Preconditions.checkNotNull(r4);
        Preconditions.checkNotNull(r5);
        this.mObservers.add(new ObserverWrapper(r4, r5));
        Object r0 = this.mLock;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.mIsActive == false) goto L7;
    L11:
        List<CameraIdentifier> r1 = Collections.unmodifiableList(this.mCurrentData);     // Catch: Throwable -> L9
        Throwable r2 = this.mCurrentError;     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        notifyObserver(new ObserverWrapper(r4, r5), r1, r2);
        return;
    L7:
        if (this.mObservers.isEmpty() == true) goto L11;
        Log.i("CameraPresenceSrc", "First observer added. Starting monitoring.");     // Catch: Throwable -> L9
        this.mIsActive = true;     // Catch: Throwable -> L9
        startMonitoring();     // Catch: Throwable -> L9
        goto L11
    }

    @Override // androidx.camera.core.impl.Observable
    public void removeObserver(Observable.Observer<? super List<CameraIdentifier>> r4) {
        Preconditions.checkNotNull(r4);
        Iterator<ObserverWrapper> r0 = this.mObservers.iterator();
    L4:
        if (r0.hasNext() == false) goto L8;
        ObserverWrapper r1 = r0.next();
        if (r1.mObserver.equals(r4) == false) goto L4;
    L9:
        if (r1 == null) goto L11;
        this.mObservers.remove(r1);
    L11:
        Object r42 = this.mLock;
        monitor-enter(r42);
    L18:
        th = move-exception;
        throw th;
    L14:
        if (this.mIsActive == true) goto L16;
    L20:
        monitor-exit(r42);     // Catch: Throwable -> L18
        return;
    L16:
        if (this.mObservers.isEmpty() == false) goto L20;
        Log.i("CameraPresenceSrc", "Last observer removed. Stopping monitoring.");     // Catch: Throwable -> L18
        this.mIsActive = false;     // Catch: Throwable -> L18
        stopMonitoring();     // Catch: Throwable -> L18
        goto L20
    L8:
        r1 = null;
        goto L9
    }

    public abstract void startMonitoring();

    public abstract void stopMonitoring();

    public void updateData(List<CameraIdentifier> r2) {
        updateState(r2, null);
    }

    public void updateError(Throwable r2) {
        updateState(null, r2);
    }
}
