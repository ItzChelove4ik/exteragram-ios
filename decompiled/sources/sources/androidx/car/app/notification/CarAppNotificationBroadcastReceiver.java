package androidx.car.app.notification;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import androidx.car.app.IStartCarApp;
import androidx.car.app.utils.RemoteUtils;
import java.util.Objects;

/* loaded from: classes3.dex */
public class CarAppNotificationBroadcastReceiver extends BroadcastReceiver {
    /* renamed from: $r8$lambda$q-h3KUTu1iDCbG2k9wImwtKNT7Q, reason: not valid java name */
    public static /* synthetic */ Object m647$r8$lambda$qh3KUTu1iDCbG2k9wImwtKNT7Q(IStartCarApp r0, Intent r1) {
        r0.startCarApp(r1);
        return null;
    }

    public CarAppNotificationBroadcastReceiver() {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context r2, Intent r3) {
        ComponentName r22 = (ComponentName) r3.getParcelableExtra("androidx.car.app.notification.COMPONENT_EXTRA_KEY");
        r3.removeExtra("androidx.car.app.notification.COMPONENT_EXTRA_KEY");
        r3.setComponent(r22);
        Bundle r1 = r3.getExtras();
        if (r1 == null) goto L5;
        IBinder r0 = r1.getBinder("androidx.car.app.extra.START_CAR_APP_BINDER_KEY");
        r1.remove("androidx.car.app.extra.START_CAR_APP_BINDER_KEY");
    L6:
        if (r0 != null) goto L9;
        Log.e("CarApp.NBR", "Notification intent missing expected extra: " + r3);
        return;
    L9:
        IStartCarApp r12 = IStartCarApp.Stub.asInterface(r0);
        Objects.requireNonNull(r12);
        RemoteUtils.dispatchCallToHost("startCarApp from notification", new CarAppNotificationBroadcastReceiver$$ExternalSyntheticLambda0(r12, r3));
        return;
    L5:
        r0 = null;
        goto L6
    }
}
