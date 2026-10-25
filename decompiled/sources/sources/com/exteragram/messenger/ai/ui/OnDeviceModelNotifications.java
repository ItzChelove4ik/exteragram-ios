package com.exteragram.messenger.ai.ui;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.exteragram.messenger.icons.IconManager;
import com.exteragram.messenger.utils.AppUtils;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes4.dex */
public abstract class OnDeviceModelNotifications {
    public static void cancel(int r2) {
        NotificationManagerCompat.from(ApplicationLoader.applicationContext).cancel(r2 + 8732834);     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e("AI_ON_DEVICE_NOTIFICATION", e);
    }

    private static NotificationCompat.Builder createBuilder(int r5) {
        Context r0 = ApplicationLoader.applicationContext;
        PendingIntent r1 = PendingIntent.getActivity(r0, 0, new Intent(r0, LaunchActivity.class).setFlags(268435456), 201326592);
        return new NotificationCompat.Builder(r0, "ai_on_device_model").setSmallIcon(IconManager.getNotificationIcon()).setColor(AppUtils.getNotificationColor()).setShowWhen(false).setCategory("progress").setSubText(getModelLabel(r5)).setContentIntent(r1);
    }

    private static String getModelLabel(int r2) {
        int r0 = r2 & 2;
        if (r0 != 0) goto L5;
    L8:
        if (r0 == 0) goto L12;
        return LocaleController.getString(R.string.AIOnDevicePreviewModel);
    L12:
        if ((r2 & 1) != 0) goto L14;
        return null;
    L14:
        return LocaleController.getString(R.string.AIOnDeviceFastModel);
    L5:
        if ((r2 & 1) == 0) goto L8;
        return LocaleController.getString(R.string.AIOnDevicePreviewModel) + ", " + LocaleController.getString(R.string.AIOnDeviceFastModel);
    }

    private static void notify(int r4, NotificationCompat.Builder r5) {
        NotificationManagerCompat r0 = NotificationManagerCompat.from(ApplicationLoader.applicationContext);     // Catch: Exception -> L4
        r0.createNotificationChannel(new NotificationChannelCompat.Builder("ai_on_device_model", 3).setName(LocaleController.getString(R.string.AIOnDeviceNotificationChannel)).setLightsEnabled(false).setVibrationEnabled(false).build());     // Catch: Exception -> L4
        r0.notify(r4 + 8732834, r5.build());     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e("AI_ON_DEVICE_NOTIFICATION", e);
    }

    public static void showCompleted(int r2) {
        notify(r2, createBuilder(r2).setContentTitle(LocaleController.getString(R.string.AIOnDeviceNotificationReady)).setContentText(LocaleController.getString(R.string.AIOnDeviceNotificationReadyInfo)).setAutoCancel(true));
    }

    public static void showProgress(int r5, int r6) {
        NotificationCompat.Builder r0 = createBuilder(r5).setContentTitle(LocaleController.getString(R.string.DownloadingModel)).setSilent(true).setOngoing(false).setTimeoutAfter(300000);
        if (r6 < 0) goto L5;
        r0.setContentText(r6 + "%");
        r0.setProgress(100, r6, false);
    L6:
        notify(r5, r0);
        return;
    L5:
        r0.setProgress(0, 0, true);
        goto L6
    }
}
