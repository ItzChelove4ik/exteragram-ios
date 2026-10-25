package androidx.car.app.utils;

import androidx.car.app.IOnDoneCallback;
import androidx.car.app.utils.RemoteUtils;

/* loaded from: classes3.dex */
public final /* synthetic */ class RemoteUtils$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ IOnDoneCallback f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ RemoteUtils.HostCall f$2;

    public /* synthetic */ RemoteUtils$$ExternalSyntheticLambda3(IOnDoneCallback r1, String r2, RemoteUtils.HostCall r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        RemoteUtils.$r8$lambda$kZaNPbfuaKBY2Ftzn027n80YsOA(this.f$0, this.f$1, this.f$2);
    }
}
