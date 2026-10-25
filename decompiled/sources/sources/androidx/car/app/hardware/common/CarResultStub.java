package androidx.car.app.hardware.common;

import android.os.IBinder;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.car.app.IOnDoneCallback;
import androidx.car.app.hardware.ICarHardwareResult;
import androidx.car.app.serialization.Bundleable;
import androidx.car.app.utils.RemoteUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;

/* loaded from: classes3.dex */
public class CarResultStub<T> extends ICarHardwareResult.Stub {
    private final Bundleable mBundle;
    private final CarHardwareHostDispatcher mHostDispatcher;
    private final boolean mIsSingleShot;
    private final Map<OnCarDataAvailableListener<T>, Executor> mListeners;
    private final int mResultType;
    private final T mUnsupportedValue;

    public static /* synthetic */ Object $r8$lambda$pqrm3pBqWmiTYzPQnBV2xWWZWww(CarResultStub r0, boolean r1, Bundleable r2) {
        r0.notifyResults(r1, r2);
        return null;
    }

    public static /* synthetic */ void $r8$lambda$vYNy658eO7HEYi1xIs0Tfw1Qm5E(Map.Entry r0, Object r1) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r0.getKey());
        throw null;
    }

    public CarResultStub(int r2, Bundleable r3, boolean r4, T r5, CarHardwareHostDispatcher r6) {
        this.mListeners = new HashMap();
        Objects.requireNonNull(r6);
        this.mHostDispatcher = r6;
        this.mResultType = r2;
        this.mBundle = r3;
        this.mIsSingleShot = r4;
        Objects.requireNonNull(r5);
        this.mUnsupportedValue = r5;
    }

    private T convertAndRecast(Bundleable r1) {
        return (T) r1.get();
    }

    private void notifyResults(boolean r4, Bundleable r5) {
        if (r4 == false) goto L4;
        T r42 = convertAndRecast(r5);
    L5:
        Iterator<Map.Entry<OnCarDataAvailableListener<T>, Executor>> r52 = this.mListeners.entrySet().iterator();
    L7:
        if (r52.hasNext() == false) goto L10;
        Map.Entry<OnCarDataAvailableListener<T>, Executor> r0 = r52.next();
        r0.getValue().execute(new CarResultStub$$ExternalSyntheticLambda0(r0, r42));
        goto L7
    L10:
        if (this.mIsSingleShot == false) goto L14;
        this.mListeners.clear();
        return;
    L14:
        return;
    L4:
        r42 = this.mUnsupportedValue;
        goto L5
    }

    public void addListener(Executor r3, OnCarDataAvailableListener<T> r4) {
        boolean r0 = this.mListeners.isEmpty();
        Map<OnCarDataAvailableListener<T>, Executor> r1 = this.mListeners;
        Objects.requireNonNull(r4);
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r4);
        r1.put(null, r3);
        if (r0 == true) goto L5;
        return;
    L5:
        boolean r32 = this.mIsSingleShot;
        CarHardwareHostDispatcher r42 = this.mHostDispatcher;
        if (r32 == false) goto L9;
        r42.dispatchGetCarHardwareResult(this.mResultType, this.mBundle, this);
        return;
    L9:
        r42.dispatchSubscribeCarHardwareResult(this.mResultType, this.mBundle, this);
    }

    @Override // androidx.car.app.hardware.ICarHardwareResult
    public void onCarHardwareResult(int r1, boolean r2, Bundleable r3, IBinder r4) {
        RemoteUtils.dispatchCallFromHost(IOnDoneCallback.Stub.asInterface(r4), "onCarHardwareResult", new CarResultStub$$ExternalSyntheticLambda1(this, r2, r3));
    }

    public boolean removeListener(OnCarDataAvailableListener<T> r2) {
        Map<OnCarDataAvailableListener<T>, Executor> r0 = this.mListeners;
        Objects.requireNonNull(r2);
        r0.remove(r2);
        if (this.mListeners.isEmpty() == true) goto L7;
        return false;
    L7:
        if (this.mIsSingleShot == true) goto L11;
        this.mHostDispatcher.dispatchUnsubscribeCarHardwareResult(this.mResultType, this.mBundle);
        return true;
    L11:
        return true;
    }
}
