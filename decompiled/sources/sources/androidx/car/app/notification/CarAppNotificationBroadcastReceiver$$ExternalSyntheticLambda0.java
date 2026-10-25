package androidx.car.app.notification;

import android.content.Intent;
import androidx.car.app.IStartCarApp;
import androidx.car.app.utils.RemoteUtils;

/* loaded from: classes3.dex */
public final /* synthetic */ class CarAppNotificationBroadcastReceiver$$ExternalSyntheticLambda0 implements RemoteUtils.RemoteCall {
    public final /* synthetic */ IStartCarApp f$0;
    public final /* synthetic */ Intent f$1;

    public /* synthetic */ CarAppNotificationBroadcastReceiver$$ExternalSyntheticLambda0(IStartCarApp r1, Intent r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.car.app.utils.RemoteUtils.RemoteCall
    public final Object call() {
        return CarAppNotificationBroadcastReceiver.m647$r8$lambda$qh3KUTu1iDCbG2k9wImwtKNT7Q(this.f$0, this.f$1);
    }
}
