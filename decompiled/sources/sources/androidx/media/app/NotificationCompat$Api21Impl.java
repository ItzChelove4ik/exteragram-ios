package androidx.media.app;

import android.app.Notification;
import android.media.session.MediaSession;
import android.support.v4.media.session.MediaSessionCompat;

/* loaded from: classes3.dex */
abstract class NotificationCompat$Api21Impl {
    public static Notification.MediaStyle createMediaStyle() {
        return new Notification.MediaStyle();
    }

    public static Notification.MediaStyle fillInMediaStyle(Notification.MediaStyle r0, int[] r1, MediaSessionCompat.Token r2) {
        if (r1 == null) goto L4;
        setShowActionsInCompactView(r0, r1);
    L4:
        if (r2 == null) goto L6;
        setMediaSession(r0, (MediaSession.Token) r2.getToken());
    L6:
        return r0;
    }

    public static void setMediaSession(Notification.MediaStyle r0, MediaSession.Token r1) {
        r0.setMediaSession(r1);
    }

    public static void setMediaStyle(Notification.Builder r0, Notification.MediaStyle r1) {
        r0.setStyle(r1);
    }

    public static void setShowActionsInCompactView(Notification.MediaStyle r0, int... r1) {
        r0.setShowActionsInCompactView(r1);
    }
}
