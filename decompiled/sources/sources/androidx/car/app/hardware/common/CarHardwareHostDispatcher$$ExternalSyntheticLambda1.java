package androidx.car.app.hardware.common;

import androidx.car.app.hardware.ICarHardwareResult;
import androidx.car.app.serialization.Bundleable;
import androidx.car.app.utils.RemoteUtils;

/* loaded from: classes3.dex */
public final /* synthetic */ class CarHardwareHostDispatcher$$ExternalSyntheticLambda1 implements RemoteUtils.RemoteCall {
    public final /* synthetic */ CarHardwareHostDispatcher f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ Bundleable f$2;
    public final /* synthetic */ ICarHardwareResult f$3;

    public /* synthetic */ CarHardwareHostDispatcher$$ExternalSyntheticLambda1(CarHardwareHostDispatcher r1, int r2, Bundleable r3, ICarHardwareResult r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // androidx.car.app.utils.RemoteUtils.RemoteCall
    public final Object call() {
        return CarHardwareHostDispatcher.m638$r8$lambda$uJPuf2SZKQG1LuTBzNl32gl8Cs(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
