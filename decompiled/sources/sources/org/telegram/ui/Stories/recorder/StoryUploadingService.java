package org.telegram.ui.Stories.recorder;

import android.R;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.NotificationsController;
import org.telegram.messenger.UserConfig;

/* loaded from: classes7.dex */
public class StoryUploadingService extends Service implements NotificationCenter.NotificationCenterDelegate {
    private NotificationCompat.Builder builder;
    private int currentAccount;
    private float currentProgress;
    private String path;

    public StoryUploadingService() {
        this.currentAccount = -1;
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.uploadStoryEnd);
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r4, int r5, Object... r6) {
        boolean r0 = false;
        if (r4 != NotificationCenter.uploadStoryProgress) goto L18;
        String r42 = this.path;
        if (r42 != null) goto L7;
        return;
    L7:
        if (r42.equals((String) r6[0]) == false) goto L28;
        float r52 = ((Float) r6[1]).floatValue();
        this.currentProgress = r52;
        NotificationCompat.Builder r62 = this.builder;
        int r53 = Math.round(r52 * 100.0f);
        if (this.currentProgress > 0.0f) goto L11;
        r0 = true;
    L11:
        r62.setProgress(100, r53, r0);
        NotificationManagerCompat.from(ApplicationLoader.applicationContext).notify(33, this.builder.build());     // Catch: Throwable -> L14
        return;
    L14:
        th = move-exception;
        FileLog.e(th);
        return;
    L28:
        return;
    L18:
        if (r4 != NotificationCenter.uploadStoryEnd) goto L29;
        String r43 = this.path;
        if (r43 != null) goto L22;
        return;
    L22:
        if (r43.equals((String) r6[0]) == false) goto L31;
        stopSelf();
        return;
    L31:
        return;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent r1) {
        return null;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);     // Catch: Exception -> L8
    L4:
        NotificationManagerCompat.from(ApplicationLoader.applicationContext).cancel(33);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.uploadStoryEnd);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.uploadStoryProgress);
        if (BuildVars.LOGS_ENABLED == false) goto L11;
        FileLog.d("upload story destroy");
        return;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent r3, int r4, int r5) {
        this.path = r3.getStringExtra("path");
        int r42 = this.currentAccount;
        int r32 = r3.getIntExtra("currentAccount", UserConfig.selectedAccount);
        this.currentAccount = r32;
        if (UserConfig.isValidAccount(r32) == true) goto L7;
        stopSelf();
        return 2;
    L7:
        if (r42 == this.currentAccount) goto L15;
        if (r42 == (-1)) goto L11;
        NotificationCenter.getInstance(r42).removeObserver(this, NotificationCenter.uploadStoryProgress);
    L11:
        int r43 = this.currentAccount;
        if (r43 == (-1)) goto L15;
        NotificationCenter.getInstance(r43).addObserver(this, NotificationCenter.uploadStoryProgress);
    L15:
        if (this.path != null) goto L19;
        stopSelf();
        return 2;
    L19:
        if (BuildVars.LOGS_ENABLED == false) goto L22;
        FileLog.d("start upload story");
    L22:
        if (this.builder != null) goto L24;
        NotificationsController.checkOtherNotificationsChannel();
        NotificationCompat.Builder r33 = new NotificationCompat.Builder(ApplicationLoader.applicationContext);
        this.builder = r33;
        r33.setSmallIcon(R.drawable.stat_sys_upload);
        this.builder.setWhen(System.currentTimeMillis());
        this.builder.setChannelId(NotificationsController.OTHER_NOTIFICATIONS_CHANNEL);
        this.builder.setContentTitle(LocaleController.getString(org.telegram.messenger.R.string.AppName));
        this.builder.setTicker(LocaleController.getString(org.telegram.messenger.R.string.StoryUploading));
        this.builder.setContentText(LocaleController.getString(org.telegram.messenger.R.string.StoryUploading));
    L24:
        this.currentProgress = 0.0f;
        this.builder.setProgress(100, Math.round(0.0f * 100.0f), false);
        startForeground(33, this.builder.build());
        NotificationManagerCompat.from(ApplicationLoader.applicationContext).notify(33, this.builder.build());     // Catch: Throwable -> L27
    L29:
        return 2;
    L27:
        th = move-exception;
        FileLog.e(th);
        goto L29
    }
}
