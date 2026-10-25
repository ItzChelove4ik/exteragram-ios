package androidx.media.app;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;

/* loaded from: classes3.dex */
abstract class NotificationCompat$Api34Impl {
    @SuppressLint({"MissingPermission"})
    public static Notification.MediaStyle setRemotePlaybackInfo(Notification.MediaStyle r0, CharSequence r1, int r2, PendingIntent r3, Boolean r4) {
        if (r4.booleanValue() == false) goto L5;
        r0.setRemotePlaybackInfo(r1, r2, r3);
    L5:
        return r0;
    }
}
