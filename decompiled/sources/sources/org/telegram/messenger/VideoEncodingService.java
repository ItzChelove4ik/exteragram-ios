package org.telegram.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.NotificationCenter;

/* loaded from: classes5.dex */
public class VideoEncodingService extends Service implements NotificationCenter.NotificationCenterDelegate {
    private static VideoEncodingService instance;
    private NotificationCompat.Builder builder;
    int currentAccount;
    private MediaController.VideoConvertMessage currentMessage;
    String currentPath;

    /* renamed from: $r8$lambda$C9dTX8i97LBt6o5b-CjfoBo2nrw, reason: not valid java name */
    public static /* synthetic */ void m5050$r8$lambda$C9dTX8i97LBt6o5bCjfoBo2nrw(VideoEncodingService r0) {
        r0.updateNotification();
    }

    /* renamed from: $r8$lambda$lsmgM1I9BYcrIiNwgvAW-osME24, reason: not valid java name */
    public static /* synthetic */ void m5051$r8$lambda$lsmgM1I9BYcrIiNwgvAWosME24(VideoEncodingService r0) {
        r0.lambda$didReceivedNotification$0();
    }

    public VideoEncodingService() {
    }

    public static boolean isRunning() {
        if (instance == null) goto L6;
        return true;
    L6:
        return false;
    }

    private /* synthetic */ void lambda$didReceivedNotification$0() {
        MediaController.VideoConvertMessage r0 = MediaController.getInstance().getCurrentForegroundConverMessage();
        if (r0 == null) goto L6;
        setCurrentMessage(r0);
        return;
    L6:
        stopSelf();
    }

    private void setCurrentMessage(MediaController.VideoConvertMessage r3) {
        MediaController.VideoConvertMessage r0 = this.currentMessage;
        if (r0 == r3) goto L12;
        if (r0 == null) goto L7;
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.fileUploadProgressChanged);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.fileUploadFailed);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.fileUploaded);
    L7:
        updateBuilderForMessage(r3);
        this.currentMessage = r3;
        int r02 = r3.currentAccount;
        this.currentAccount = r02;
        this.currentPath = r3.messageObject.messageOwner.attachPath;
        NotificationCenter.getInstance(r02).addObserver(this, NotificationCenter.fileUploadProgressChanged);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.fileUploadFailed);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.fileUploaded);
        if (isRunning() == false) goto L11;
        updateNotification();
        return;
    L11:
        return;
    }

    public static void start(boolean r2) {
        if (instance == null) goto L17;
        if (r2 == false) goto L19;
        MediaController.VideoConvertMessage r22 = MediaController.getInstance().getCurrentForegroundConverMessage();
        VideoEncodingService r0 = instance;
        if (r0.currentMessage == r22) goto L20;
        if (r22 == null) goto L15;
        r0.setCurrentMessage(r22);
        return;
    L15:
        r0.stopSelf();
        return;
    L20:
        return;
    L19:
        return;
    L17:
        Intent r23 = new Intent(ApplicationLoader.applicationContext, VideoEncodingService.class);     // Catch: Exception -> L6
        ApplicationLoader.applicationContext.startService(r23);     // Catch: Exception -> L6
        return;
    L6:
        e = move-exception;
        FileLog.e(e);
    }

    public static void stop() {
        VideoEncodingService r0 = instance;
        if (r0 == null) goto L6;
        r0.stopSelf();
        return;
    }

    private void updateBuilderForMessage(MediaController.VideoConvertMessage r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        MessageObject r0 = r4.messageObject;
        if (r0 != null) goto L7;
    L9:
        boolean r02 = false;
    L11:
        if (r4.foregroundConversion == false) goto L13;
        this.builder.setTicker(LocaleController.getString(R.string.ConvertingVideo));
        this.builder.setContentText(LocaleController.getString(R.string.ConvertingVideo));
    L17:
        this.builder.setProgress(100, 0, true);
        return;
    L13:
        NotificationCompat.Builder r42 = this.builder;
        if (r02 == false) goto L16;
        r42.setTicker(LocaleController.getString(R.string.SendingGif));
        this.builder.setContentText(LocaleController.getString(R.string.SendingGif));
        goto L17
    L16:
        r42.setTicker(LocaleController.getString(R.string.SendingVideo));
        this.builder.setContentText(LocaleController.getString(R.string.SendingVideo));
        goto L17
    L7:
        if (MessageObject.isGifMessage(r0.messageOwner) == false) goto L9;
        r02 = true;
        goto L11
    }

    private void updateNotification() {
    L7:
        th = move-exception;
        FileLog.e(th);
        return;
    L3:
        if (MediaController.getInstance().getCurrentForegroundConverMessage() != null) goto L5;
        return;
    L5:
        NotificationManagerCompat.from(ApplicationLoader.applicationContext).notify(4, this.builder.build());     // Catch: Throwable -> L7
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r5, int r6, Object... r7) {
        boolean r1 = false;
        if (r5 != NotificationCenter.fileUploadProgressChanged) goto L16;
        String r52 = (String) r7[0];
        if (r6 != this.currentAccount) goto L27;
        String r62 = this.currentPath;
        if (r62 != null) goto L9;
        return;
    L9:
        if (r62.equals(r52) == false) goto L29;
        Long r63 = (Long) r7[1];
        Long r0 = (Long) r7[2];
        float r64 = Math.min(1.0f, r63.longValue() / r0.longValue());
        Boolean r72 = (Boolean) r7[3];
        int r65 = (int) (r64 * 100.0f);
        NotificationCompat.Builder r73 = this.builder;
        if (r65 != 0) goto L13;
        r1 = true;
    L13:
        r73.setProgress(100, r65, r1);
        updateNotification();
        return;
    L29:
        return;
    L27:
        return;
    L16:
        if (r5 != NotificationCenter.fileUploaded) goto L18;
    L19:
        String r53 = (String) r7[0];
        if (r6 != this.currentAccount) goto L31;
        String r66 = this.currentPath;
        if (r66 != null) goto L24;
        return;
    L24:
        if (r66.equals(r53) == false) goto L33;
        AndroidUtilities.runOnUIThread(new VideoEncodingService$$ExternalSyntheticLambda0(this));
        return;
    L33:
        return;
    L31:
        return;
    L18:
        if (r5 == NotificationCenter.fileUploadFailed) goto L19;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent r1) {
        return null;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        instance = null;
        stopForeground(true);     // Catch: Throwable -> L8
    L4:
        NotificationManagerCompat.from(ApplicationLoader.applicationContext).cancel(4);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.fileUploadProgressChanged);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.fileUploadFailed);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.fileUploaded);
        this.currentMessage = null;
        if (BuildVars.LOGS_ENABLED == false) goto L11;
        FileLog.d("VideoEncodingService: destroy video service");
        return;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent r3, int r4, int r5) {
        if (isRunning() == false) goto L5;
        return 2;
    L5:
        MediaController.VideoConvertMessage r32 = MediaController.getInstance().getCurrentForegroundConverMessage();
        if (r32 != null) goto L8;
        return 2;
    L8:
        instance = this;
        if (this.builder != null) goto L11;
        NotificationsController.checkOtherNotificationsChannel();
        NotificationCompat.Builder r52 = new NotificationCompat.Builder(ApplicationLoader.applicationContext, NotificationsController.OTHER_NOTIFICATIONS_CHANNEL);
        this.builder = r52;
        r52.setSmallIcon(android.R.drawable.stat_sys_upload);
        this.builder.setWhen(System.currentTimeMillis());
        this.builder.setChannelId(NotificationsController.OTHER_NOTIFICATIONS_CHANNEL);
        this.builder.setContentTitle(LocaleController.getString(R.string.AppName));
    L11:
        setCurrentMessage(r32);
        startForeground(4, this.builder.build());     // Catch: Throwable -> L14
    L16:
        AndroidUtilities.runOnUIThread(new VideoEncodingService$$ExternalSyntheticLambda1(this));
        return 2;
    L14:
        th = move-exception;
        FileLog.e(th);
        goto L16
    }
}
