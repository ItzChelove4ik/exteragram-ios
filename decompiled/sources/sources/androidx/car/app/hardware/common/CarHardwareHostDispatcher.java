package androidx.car.app.hardware.common;

import androidx.car.app.HostDispatcher;
import androidx.car.app.ICarHost;
import androidx.car.app.hardware.ICarHardwareHost;
import androidx.car.app.hardware.ICarHardwareResult;
import androidx.car.app.serialization.Bundleable;
import androidx.car.app.utils.RemoteUtils;
import java.util.Objects;

/* loaded from: classes3.dex */
public class CarHardwareHostDispatcher {
    private final HostDispatcher mHostDispatcher;
    private ICarHardwareHost mICarHardwareHost;

    /* renamed from: $r8$lambda$-uJPuf2SZKQG1LuTBzNl32gl8Cs, reason: not valid java name */
    public static /* synthetic */ Object m638$r8$lambda$uJPuf2SZKQG1LuTBzNl32gl8Cs(CarHardwareHostDispatcher r0, int r1, Bundleable r2, ICarHardwareResult r3) {
        r0.getHost().subscribeCarHardwareResult(r1, r2, r3);
        return null;
    }

    /* renamed from: $r8$lambda$J--K4g1XwTrYMz4UEkchHiRRUL8, reason: not valid java name */
    public static /* synthetic */ ICarHardwareHost m639$r8$lambda$JK4g1XwTrYMz4UEkchHiRRUL8(ICarHost r1) {
        return ICarHardwareHost.Stub.asInterface(r1.getHost("hardware"));
    }

    public static /* synthetic */ Object $r8$lambda$QGWz3y_oA7uH7F2k5epRAGxfhNo(CarHardwareHostDispatcher r0, int r1, Bundleable r2) {
        r0.getHost().unsubscribeCarHardwareResult(r1, r2);
        return null;
    }

    /* renamed from: $r8$lambda$RWB_Hd3Wpqr7Y3FVmYXjx-Lusow, reason: not valid java name */
    public static /* synthetic */ Object m640$r8$lambda$RWB_Hd3Wpqr7Y3FVmYXjxLusow(CarHardwareHostDispatcher r0, int r1, Bundleable r2, ICarHardwareResult r3) {
        r0.getHost().getCarHardwareResult(r1, r2, r3);
        return null;
    }

    public CarHardwareHostDispatcher(HostDispatcher r1) {
        Objects.requireNonNull(r1);
        this.mHostDispatcher = r1;
    }

    private ICarHardwareHost getHost() {
        ICarHardwareHost r0 = this.mICarHardwareHost;
        if (r0 != null) goto L6;
        ICarHardwareHost r02 = (ICarHardwareHost) this.mHostDispatcher.dispatchForResult("car", "getHost(CarHardware)", new CarHardwareHostDispatcher$$ExternalSyntheticLambda3());
        Objects.requireNonNull(r02);
        ICarHardwareHost r03 = r02;
        this.mICarHardwareHost = r03;
        return r03;
    L6:
        return r0;
    }

    public void dispatchGetCarHardwareResult(int r2, Bundleable r3, ICarHardwareResult r4) {
        Objects.requireNonNull(r4);
        RemoteUtils.dispatchCallToHost("getCarHardwareResult", new CarHardwareHostDispatcher$$ExternalSyntheticLambda0(this, r2, r3, r4));
    }

    public void dispatchSubscribeCarHardwareResult(int r2, Bundleable r3, ICarHardwareResult r4) {
        Objects.requireNonNull(r4);
        RemoteUtils.dispatchCallToHost("subscribeCarHardwareResult", new CarHardwareHostDispatcher$$ExternalSyntheticLambda1(this, r2, r3, r4));
    }

    public void dispatchUnsubscribeCarHardwareResult(int r2, Bundleable r3) {
        RemoteUtils.dispatchCallToHost("unsubscribeCarHardwareResult", new CarHardwareHostDispatcher$$ExternalSyntheticLambda2(this, r2, r3));
    }
}
