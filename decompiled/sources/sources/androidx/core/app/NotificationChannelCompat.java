package androidx.core.app;

import android.app.Notification;
import android.app.NotificationChannel;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import androidx.core.util.Preconditions;

/* loaded from: classes3.dex */
public class NotificationChannelCompat {
    AudioAttributes mAudioAttributes;
    String mConversationId;
    String mDescription;
    String mGroupId;
    final String mId;
    int mImportance;
    int mLightColor;
    boolean mLights;
    CharSequence mName;
    String mParentId;
    boolean mShowBadge;
    Uri mSound;
    boolean mVibrationEnabled;
    long[] mVibrationPattern;

    public static class Api26Impl {
        public static NotificationChannel createNotificationChannel(String r1, CharSequence r2, int r3) {
            return new NotificationChannel(r1, r2, r3);
        }

        public static void enableLights(NotificationChannel r0, boolean r1) {
            r0.enableLights(r1);
        }

        public static void enableVibration(NotificationChannel r0, boolean r1) {
            r0.enableVibration(r1);
        }

        public static void setDescription(NotificationChannel r0, String r1) {
            r0.setDescription(r1);
        }

        public static void setGroup(NotificationChannel r0, String r1) {
            r0.setGroup(r1);
        }

        public static void setLightColor(NotificationChannel r0, int r1) {
            r0.setLightColor(r1);
        }

        public static void setShowBadge(NotificationChannel r0, boolean r1) {
            r0.setShowBadge(r1);
        }

        public static void setSound(NotificationChannel r0, Uri r1, AudioAttributes r2) {
            r0.setSound(r1, r2);
        }

        public static void setVibrationPattern(NotificationChannel r0, long[] r1) {
            r0.setVibrationPattern(r1);
        }
    }

    public static class Api30Impl {
        public static void setConversationId(NotificationChannel r0, String r1, String r2) {
            r0.setConversationId(r1, r2);
        }
    }

    public static class Builder {
        private final NotificationChannelCompat mChannel;

        public Builder(String r2, int r3) {
            this.mChannel = new NotificationChannelCompat(r2, r3);
        }

        public NotificationChannelCompat build() {
            return this.mChannel;
        }

        public Builder setLightsEnabled(boolean r2) {
            this.mChannel.mLights = r2;
            return this;
        }

        public Builder setName(CharSequence r2) {
            this.mChannel.mName = r2;
            return this;
        }

        public Builder setSound(Uri r2, AudioAttributes r3) {
            NotificationChannelCompat r0 = this.mChannel;
            r0.mSound = r2;
            r0.mAudioAttributes = r3;
            return this;
        }

        public Builder setVibrationEnabled(boolean r2) {
            this.mChannel.mVibrationEnabled = r2;
            return this;
        }
    }

    public NotificationChannelCompat(String r2, int r3) {
        this.mShowBadge = true;
        this.mSound = Settings.System.DEFAULT_NOTIFICATION_URI;
        this.mLightColor = 0;
        this.mId = (String) Preconditions.checkNotNull(r2);
        this.mImportance = r3;
        this.mAudioAttributes = Notification.AUDIO_ATTRIBUTES_DEFAULT;
    }

    public NotificationChannel getNotificationChannel() {
        int r0 = Build.VERSION.SDK_INT;
        if (r0 >= 26) goto L6;
        return null;
    L6:
        NotificationChannel r1 = Api26Impl.createNotificationChannel(this.mId, this.mName, this.mImportance);
        Api26Impl.setDescription(r1, this.mDescription);
        Api26Impl.setGroup(r1, this.mGroupId);
        Api26Impl.setShowBadge(r1, this.mShowBadge);
        Api26Impl.setSound(r1, this.mSound, this.mAudioAttributes);
        Api26Impl.enableLights(r1, this.mLights);
        Api26Impl.setLightColor(r1, this.mLightColor);
        Api26Impl.setVibrationPattern(r1, this.mVibrationPattern);
        Api26Impl.enableVibration(r1, this.mVibrationEnabled);
        if (r0 < 30) goto L13;
        String r02 = this.mParentId;
        if (r02 == null) goto L13;
        String r4 = this.mConversationId;
        if (r4 == null) goto L13;
        Api30Impl.setConversationId(r1, r02, r4);
    L13:
        return r1;
    }
}
