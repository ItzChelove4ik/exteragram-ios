package com.google.firebase.messaging;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.messaging.CommonNotificationBuilder;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes5.dex */
class DisplayNotification {
    private final Context context;
    private final ExecutorService networkIoExecutor;
    private final NotificationParams params;

    public DisplayNotification(Context r1, NotificationParams r2, ExecutorService r3) {
        this.networkIoExecutor = r3;
        this.context = r1;
        this.params = r2;
    }

    private boolean isAppForeground() {
        if (((KeyguardManager) this.context.getSystemService("keyguard")).inKeyguardRestrictedInputMode() == false) goto L6;
        return false;
    L6:
        if (PlatformVersion.isAtLeastLollipop() == true) goto L8;
        SystemClock.sleep(10);
    L8:
        int r0 = Process.myPid();
        List<ActivityManager.RunningAppProcessInfo> r4 = ((ActivityManager) this.context.getSystemService("activity")).getRunningAppProcesses();
        if (r4 == null) goto L19;
        Iterator<ActivityManager.RunningAppProcessInfo> r42 = r4.iterator();
    L12:
        if (r42.hasNext() == false) goto L19;
        ActivityManager.RunningAppProcessInfo r2 = r42.next();
        if (r2.pid != r0) goto L12;
        if (r2.importance != 100) goto L19;
        return true;
    L19:
        return false;
    }

    private void showNotification(CommonNotificationBuilder.DisplayNotificationInfo r3) {
        if (Log.isLoggable("FirebaseMessaging", 3) == false) goto L5;
        Log.d("FirebaseMessaging", "Showing notification");
    L5:
        ((NotificationManager) this.context.getSystemService("notification")).notify(r3.tag, r3.id, r3.notificationBuilder.build());
    }

    private ImageDownload startImageDownloadInBackground() {
        ImageDownload r0 = ImageDownload.create(this.params.getString("gcm.n.image"));
        if (r0 == null) goto L5;
        r0.start(this.networkIoExecutor);
    L5:
        return r0;
    }

    private void waitForAndApplyImageDownload(NotificationCompat.Builder r5, ImageDownload r6) {
        if (r6 == null) goto L17;
        Bitmap r0 = (Bitmap) Tasks.await(r6.getTask(), 5, TimeUnit.SECONDS);     // Catch: ExecutionException -> L7 TimeoutException -> L9 InterruptedException -> L10
        r5.setLargeIcon(r0);     // Catch: ExecutionException -> L7 TimeoutException -> L9 InterruptedException -> L10
        r5.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(r0).bigLargeIcon(null));     // Catch: ExecutionException -> L7 TimeoutException -> L9 InterruptedException -> L10
        return;
    L10:
        Log.w("FirebaseMessaging", "Interrupted while downloading image, showing notification without it");
        r6.close();
        Thread.currentThread().interrupt();
        return;
    L7:
        e = move-exception;
        Log.w("FirebaseMessaging", "Failed to download image: " + e.getCause());
        return;
    L9:
        Log.w("FirebaseMessaging", "Failed to download image in time, showing notification without it");
        r6.close();
        return;
    }

    public boolean handleNotification() {
        if (this.params.getBoolean("gcm.n.noui") == false) goto L6;
        return true;
    L6:
        if (isAppForeground() == false) goto L9;
        return false;
    L9:
        ImageDownload r0 = startImageDownloadInBackground();
        CommonNotificationBuilder.DisplayNotificationInfo r2 = CommonNotificationBuilder.createNotificationInfo(this.context, this.params);
        waitForAndApplyImageDownload(r2.notificationBuilder, r0);
        showNotification(r2);
        return true;
    }
}
