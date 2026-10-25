package androidx.car.app;

import android.content.Intent;
import android.content.res.Configuration;
import androidx.car.app.utils.RemoteUtils;

/* loaded from: classes3.dex */
public final /* synthetic */ class CarAppBinder$$ExternalSyntheticLambda4 implements RemoteUtils.HostCall {
    public final /* synthetic */ CarAppBinder f$0;
    public final /* synthetic */ ICarHost f$1;
    public final /* synthetic */ Configuration f$2;
    public final /* synthetic */ Intent f$3;

    public /* synthetic */ CarAppBinder$$ExternalSyntheticLambda4(CarAppBinder r1, ICarHost r2, Configuration r3, Intent r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // androidx.car.app.utils.RemoteUtils.HostCall
    public final Object dispatch() {
        return CarAppBinder.$r8$lambda$qV15wLbZchZqw7AYDWUTdB04thg(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
