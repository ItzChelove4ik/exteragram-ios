package androidx.mediarouter.media;

import android.os.Bundle;
import android.os.SystemClock;
import androidx.core.util.TimeUtils;
import c.f$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public final class MediaItemStatus {
    public static final String EXTRA_HTTP_RESPONSE_HEADERS = "android.media.status.extra.HTTP_RESPONSE_HEADERS";
    public static final String EXTRA_HTTP_STATUS_CODE = "android.media.status.extra.HTTP_STATUS_CODE";
    static final String KEY_CONTENT_DURATION = "contentDuration";
    static final String KEY_CONTENT_POSITION = "contentPosition";
    static final String KEY_EXTRAS = "extras";
    static final String KEY_PLAYBACK_STATE = "playbackState";
    static final String KEY_TIMESTAMP = "timestamp";
    public static final int PLAYBACK_STATE_BUFFERING = 3;
    public static final int PLAYBACK_STATE_CANCELED = 5;
    public static final int PLAYBACK_STATE_ERROR = 7;
    public static final int PLAYBACK_STATE_FINISHED = 4;
    public static final int PLAYBACK_STATE_INVALIDATED = 6;
    public static final int PLAYBACK_STATE_PAUSED = 2;
    public static final int PLAYBACK_STATE_PENDING = 0;
    public static final int PLAYBACK_STATE_PLAYING = 1;
    final Bundle mBundle;

    public static final class Builder {
        private final Bundle mBundle;

        public Builder(int r3) {
            this.mBundle = new Bundle();
            setTimestamp(SystemClock.elapsedRealtime());
            setPlaybackState(r3);
        }

        public Builder(MediaItemStatus r2) {
            if (r2 == null) goto L6;
            this.mBundle = new Bundle(r2.mBundle);
            return;
        L6:
            f$$ExternalSyntheticBUOutline1.m("status must not be null");
            throw null;
        }

        public MediaItemStatus build() {
            return new MediaItemStatus(this.mBundle);
        }

        public Builder setContentDuration(long r3) {
            this.mBundle.putLong(MediaItemStatus.KEY_CONTENT_DURATION, r3);
            return this;
        }

        public Builder setContentPosition(long r3) {
            this.mBundle.putLong(MediaItemStatus.KEY_CONTENT_POSITION, r3);
            return this;
        }

        public Builder setExtras(Bundle r4) {
            Bundle r0 = this.mBundle;
            if (r4 != null) goto L6;
            r0.putBundle(MediaItemStatus.KEY_EXTRAS, null);
            return this;
        L6:
            r0.putBundle(MediaItemStatus.KEY_EXTRAS, new Bundle(r4));
            return this;
        }

        public Builder setPlaybackState(int r3) {
            this.mBundle.putInt(MediaItemStatus.KEY_PLAYBACK_STATE, r3);
            return this;
        }

        public Builder setTimestamp(long r3) {
            this.mBundle.putLong(MediaItemStatus.KEY_TIMESTAMP, r3);
            return this;
        }
    }

    public MediaItemStatus(Bundle r1) {
        this.mBundle = r1;
    }

    public static MediaItemStatus fromBundle(Bundle r1) {
        if (r1 != null) goto L4;
        return null;
    L4:
        return new MediaItemStatus(r1);
    }

    private static String playbackStateToString(int r0) {
        switch(r0) {
            case 0: goto L19;
            case 1: goto L17;
            case 2: goto L15;
            case 3: goto L13;
            case 4: goto L11;
            case 5: goto L9;
            case 6: goto L7;
            case 7: goto L5;
            default: goto L4;
        };
    L5:
        return "error";
    L7:
        return "invalidated";
    L9:
        return "canceled";
    L11:
        return "finished";
    L13:
        return "buffering";
    L15:
        return "paused";
    L17:
        return "playing";
    L19:
        return "pending";
    L4:
        return Integer.toString(r0);
    }

    public Bundle asBundle() {
        return this.mBundle;
    }

    public long getContentDuration() {
        return this.mBundle.getLong(KEY_CONTENT_DURATION, -1);
    }

    public long getContentPosition() {
        return this.mBundle.getLong(KEY_CONTENT_POSITION, -1);
    }

    public Bundle getExtras() {
        return this.mBundle.getBundle(KEY_EXTRAS);
    }

    public int getPlaybackState() {
        return this.mBundle.getInt(KEY_PLAYBACK_STATE, 7);
    }

    public long getTimestamp() {
        return this.mBundle.getLong(KEY_TIMESTAMP);
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder();
        r0.append("MediaItemStatus{ timestamp=");
        TimeUtils.formatDuration(SystemClock.elapsedRealtime() - getTimestamp(), r0);
        r0.append(" ms ago");
        r0.append(", playbackState=");
        r0.append(playbackStateToString(getPlaybackState()));
        r0.append(", contentPosition=");
        r0.append(getContentPosition());
        r0.append(", contentDuration=");
        r0.append(getContentDuration());
        r0.append(", extras=");
        r0.append(getExtras());
        r0.append(" }");
        return r0.toString();
    }
}
