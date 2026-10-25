package androidx.car.app;

import androidx.car.app.utils.RemoteUtils;

/* loaded from: classes3.dex */
public final /* synthetic */ class AppManager$1$$ExternalSyntheticLambda1 implements RemoteUtils.HostCall {
    public final /* synthetic */ ScreenManager f$0;

    public /* synthetic */ AppManager$1$$ExternalSyntheticLambda1(ScreenManager r1) {
        this.f$0 = r1;
    }

    @Override // androidx.car.app.utils.RemoteUtils.HostCall
    public final Object dispatch() {
        return this.f$0.getTopTemplate();
    }
}
