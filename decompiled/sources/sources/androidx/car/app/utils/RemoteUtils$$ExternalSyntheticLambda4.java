package androidx.car.app.utils;

import androidx.car.app.IOnDoneCallback;
import androidx.car.app.utils.RemoteUtils;
import androidx.lifecycle.Lifecycle;

/* loaded from: classes3.dex */
public final /* synthetic */ class RemoteUtils$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ Lifecycle f$0;
    public final /* synthetic */ IOnDoneCallback f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ RemoteUtils.HostCall f$3;

    public /* synthetic */ RemoteUtils$$ExternalSyntheticLambda4(Lifecycle r1, IOnDoneCallback r2, String r3, RemoteUtils.HostCall r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        RemoteUtils.m648$r8$lambda$1ADQpFpr9d0_tX7AcIVXhvtU7o(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
