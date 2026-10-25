package org.telegram.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import org.telegram.messenger.NotificationCenter;

/* loaded from: classes5.dex */
public class ImportingService extends Service implements NotificationCenter.NotificationCenterDelegate {
    private NotificationCompat.Builder builder;

    public ImportingService() {
        int r0 = 0;
    L4:
        if (r0 >= 16) goto L6;
        NotificationCenter.getInstance(r0).addObserver(this, NotificationCenter.historyImportProgressChanged);
        NotificationCenter.getInstance(r0).addObserver(this, NotificationCenter.stickersImportProgressChanged);
        r0 = r0 + 1;
        goto L4
    }

    private boolean hasImportingHistory() {
        int r0 = 0;
    L4:
        if (r0 >= 16) goto L10;
        if (SendMessagesHelper.getInstance(r0).isImportingHistory() == true) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return true;
    L10:
        return false;
    }

    private boolean hasImportingStickers() {
        int r0 = 0;
    L4:
        if (r0 >= 16) goto L10;
        if (SendMessagesHelper.getInstance(r0).isImportingStickers() == true) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return true;
    L10:
        return false;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 == NotificationCenter.historyImportProgressChanged) goto L7;
        if (r1 == NotificationCenter.stickersImportProgressChanged) goto L7;
        return;
    L7:
        if (hasImportingStickers() == false) goto L9;
        return;
    L9:
        if (hasImportingStickers() == true) goto L14;
        stopSelf();
        return;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent r1) {
        return null;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);     // Catch: Throwable -> L12
    L4:
        NotificationManagerCompat.from(ApplicationLoader.applicationContext).cancel(5);
        int r0 = 0;
    L6:
        if (r0 >= 16) goto L9;
        NotificationCenter.getInstance(r0).removeObserver(this, NotificationCenter.historyImportProgressChanged);
        NotificationCenter.getInstance(r0).removeObserver(this, NotificationCenter.stickersImportProgressChanged);
        r0 = r0 + 1;
        goto L6
    L9:
        if (BuildVars.LOGS_ENABLED == false) goto L16;
        FileLog.d("destroy import service");
        return;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent r3, int r4, int r5) {
        if (hasImportingStickers() == true) goto L9;
        if (hasImportingHistory() == true) goto L9;
        stopSelf();
        return 2;
    L9:
        if (BuildVars.LOGS_ENABLED == false) goto L12;
        FileLog.d("start import service");
    L12:
        if (this.builder != null) goto L17;
        NotificationsController.checkOtherNotificationsChannel();
        NotificationCompat.Builder r32 = new NotificationCompat.Builder(ApplicationLoader.applicationContext);
        this.builder = r32;
        r32.setSmallIcon(android.R.drawable.stat_sys_upload);
        this.builder.setWhen(System.currentTimeMillis());
        this.builder.setChannelId(NotificationsController.OTHER_NOTIFICATIONS_CHANNEL);
        this.builder.setContentTitle(LocaleController.getString(R.string.AppName));
        boolean r33 = hasImportingHistory();
        NotificationCompat.Builder r52 = this.builder;
        if (r33 == false) goto L16;
        r52.setTicker(LocaleController.getString(R.string.ImporImportingService));
        this.builder.setContentText(LocaleController.getString(R.string.ImporImportingService));
        goto L17
    L16:
        r52.setTicker(LocaleController.getString(R.string.ImporImportingStickersService));
        this.builder.setContentText(LocaleController.getString(R.string.ImporImportingStickersService));
    L17:
        this.builder.setProgress(100, 0, true);
        startForeground(5, this.builder.build());
        NotificationManagerCompat.from(ApplicationLoader.applicationContext).notify(5, this.builder.build());
        return 2;
    }
}
