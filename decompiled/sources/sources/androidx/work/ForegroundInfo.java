package androidx.work;

import android.app.Notification;

/* loaded from: classes4.dex */
public final class ForegroundInfo {
    private final int mForegroundServiceType;
    private final Notification mNotification;
    private final int mNotificationId;

    public ForegroundInfo(int r1, Notification r2, int r3) {
        this.mNotificationId = r1;
        this.mNotification = r2;
        this.mForegroundServiceType = r3;
    }

    public boolean equals(Object r4) {
        if (this != r4) goto L6;
        return true;
    L6:
        if (r4 != null) goto L8;
    L18:
        return false;
    L8:
        if (ForegroundInfo.class != r4.getClass()) goto L18;
        ForegroundInfo r42 = (ForegroundInfo) r4;
        if (this.mNotificationId == r42.mNotificationId) goto L14;
        return false;
    L14:
        if (this.mForegroundServiceType == r42.mForegroundServiceType) goto L17;
        return false;
    L17:
        return this.mNotification.equals(r42.mNotification);
    }

    public int getForegroundServiceType() {
        return this.mForegroundServiceType;
    }

    public Notification getNotification() {
        return this.mNotification;
    }

    public int getNotificationId() {
        return this.mNotificationId;
    }

    public int hashCode() {
        return (((this.mNotificationId * 31) + this.mForegroundServiceType) * 31) + this.mNotification.hashCode();
    }

    public String toString() {
        return "ForegroundInfo{mNotificationId=" + this.mNotificationId + ", mForegroundServiceType=" + this.mForegroundServiceType + ", mNotification=" + this.mNotification + '}';
    }
}
