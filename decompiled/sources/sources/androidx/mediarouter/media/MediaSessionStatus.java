package androidx.mediarouter.media;

import android.os.Bundle;
import android.os.SystemClock;
import androidx.core.util.TimeUtils;
import c.f$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public final class MediaSessionStatus {
    static final String KEY_EXTRAS = "extras";
    static final String KEY_QUEUE_PAUSED = "queuePaused";
    static final String KEY_SESSION_STATE = "sessionState";
    static final String KEY_TIMESTAMP = "timestamp";
    public static final int SESSION_STATE_ACTIVE = 0;
    public static final int SESSION_STATE_ENDED = 1;
    public static final int SESSION_STATE_INVALIDATED = 2;
    final Bundle mBundle;

    public static final class Builder {
        private final Bundle mBundle;

        public Builder(int r3) {
            this.mBundle = new Bundle();
            setTimestamp(SystemClock.elapsedRealtime());
            setSessionState(r3);
        }

        public Builder(MediaSessionStatus r2) {
            if (r2 == null) goto L6;
            this.mBundle = new Bundle(r2.mBundle);
            return;
        L6:
            f$$ExternalSyntheticBUOutline1.m("status must not be null");
            throw null;
        }

        public MediaSessionStatus build() {
            return new MediaSessionStatus(this.mBundle);
        }

        public Builder setExtras(Bundle r4) {
            Bundle r0 = this.mBundle;
            if (r4 != null) goto L6;
            r0.putBundle(MediaSessionStatus.KEY_EXTRAS, null);
            return this;
        L6:
            r0.putBundle(MediaSessionStatus.KEY_EXTRAS, new Bundle(r4));
            return this;
        }

        public Builder setQueuePaused(boolean r3) {
            this.mBundle.putBoolean(MediaSessionStatus.KEY_QUEUE_PAUSED, r3);
            return this;
        }

        public Builder setSessionState(int r3) {
            this.mBundle.putInt(MediaSessionStatus.KEY_SESSION_STATE, r3);
            return this;
        }

        public Builder setTimestamp(long r3) {
            this.mBundle.putLong(MediaSessionStatus.KEY_TIMESTAMP, r3);
            return this;
        }
    }

    public MediaSessionStatus(Bundle r1) {
        this.mBundle = r1;
    }

    public static MediaSessionStatus fromBundle(Bundle r1) {
        if (r1 != null) goto L4;
        return null;
    L4:
        return new MediaSessionStatus(r1);
    }

    private static String sessionStateToString(int r1) {
        if (r1 != 0) goto L4;
        return "active";
    L4:
        if (r1 != 1) goto L6;
        return "ended";
    L6:
        if (r1 != 2) goto L8;
        return "invalidated";
    L8:
        return Integer.toString(r1);
    }

    public Bundle asBundle() {
        return this.mBundle;
    }

    public Bundle getExtras() {
        return this.mBundle.getBundle(KEY_EXTRAS);
    }

    public int getSessionState() {
        return this.mBundle.getInt(KEY_SESSION_STATE, 2);
    }

    public long getTimestamp() {
        return this.mBundle.getLong(KEY_TIMESTAMP);
    }

    public boolean isQueuePaused() {
        return this.mBundle.getBoolean(KEY_QUEUE_PAUSED);
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder();
        r0.append("MediaSessionStatus{ timestamp=");
        TimeUtils.formatDuration(SystemClock.elapsedRealtime() - getTimestamp(), r0);
        r0.append(" ms ago");
        r0.append(", sessionState=");
        r0.append(sessionStateToString(getSessionState()));
        r0.append(", queuePaused=");
        r0.append(isQueuePaused());
        r0.append(", extras=");
        r0.append(getExtras());
        r0.append(" }");
        return r0.toString();
    }
}
