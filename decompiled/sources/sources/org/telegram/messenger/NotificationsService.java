package org.telegram.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/* loaded from: classes.dex */
public class NotificationsService extends Service {
    public NotificationsService() {
    }

    @Override // android.app.Service
    public IBinder onBind(Intent r1) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        ApplicationLoader.postInitApplication();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        if (MessagesController.getGlobalNotificationsSettings().getBoolean("pushService", true) == false) goto L6;
        Intent r0 = new Intent("org.telegram.start");
        r0.setPackage(getPackageName());
        sendBroadcast(r0);
        return;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent r1, int r2, int r3) {
        return 1;
    }
}
