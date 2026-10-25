package androidx.media.app;

import android.app.PendingIntent;
import android.os.Build;
import android.support.v4.media.session.MediaSessionCompat;
import android.widget.RemoteViews;
import androidx.core.app.NotificationBuilderWithBuilderAccessor;
import androidx.core.app.NotificationCompat;

/* loaded from: classes3.dex */
public class NotificationCompat$MediaStyle extends NotificationCompat.Style {
    int[] mActionsToShowInCompact;
    int mDeviceIcon;
    PendingIntent mDeviceIntent;
    CharSequence mDeviceName;
    boolean mShowRemotePlaybackInfo;
    MediaSessionCompat.Token mToken;

    public NotificationCompat$MediaStyle() {
        this.mActionsToShowInCompact = null;
        this.mShowRemotePlaybackInfo = false;
    }

    @Override // androidx.core.app.NotificationCompat.Style
    public void apply(NotificationBuilderWithBuilderAccessor r6) {
        if (Build.VERSION.SDK_INT < 34) goto L6;
        NotificationCompat$Api21Impl.setMediaStyle(r6.getBuilder(), NotificationCompat$Api21Impl.fillInMediaStyle(NotificationCompat$Api34Impl.setRemotePlaybackInfo(NotificationCompat$Api21Impl.createMediaStyle(), this.mDeviceName, this.mDeviceIcon, this.mDeviceIntent, Boolean.valueOf(this.mShowRemotePlaybackInfo)), this.mActionsToShowInCompact, this.mToken));
        return;
    L6:
        NotificationCompat$Api21Impl.setMediaStyle(r6.getBuilder(), NotificationCompat$Api21Impl.fillInMediaStyle(NotificationCompat$Api21Impl.createMediaStyle(), this.mActionsToShowInCompact, this.mToken));
    }

    @Override // androidx.core.app.NotificationCompat.Style
    public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor r1) {
        return null;
    }

    @Override // androidx.core.app.NotificationCompat.Style
    public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor r1) {
        return null;
    }

    public NotificationCompat$MediaStyle setMediaSession(MediaSessionCompat.Token r1) {
        this.mToken = r1;
        return this;
    }

    public NotificationCompat$MediaStyle setShowActionsInCompactView(int... r1) {
        this.mActionsToShowInCompact = r1;
        return this;
    }
}
