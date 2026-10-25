package androidx.media3.common;

import android.os.Bundle;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;

/* loaded from: classes3.dex */
public final class PlaybackParameters {
    public static final PlaybackParameters DEFAULT = null;
    private static final String FIELD_PITCH = null;
    private static final String FIELD_SPEED = null;
    public final float pitch;
    private final int scaledUsPerMs;
    public final float speed;

    static {
        DEFAULT = new PlaybackParameters(1.0f);
        FIELD_SPEED = Util.intToStringMaxRadix(0);
        FIELD_PITCH = Util.intToStringMaxRadix(1);
    }

    public PlaybackParameters(float r2) {
        this(r2, 1.0f);
    }

    public PlaybackParameters(float r5, float r6) {
        boolean r2 = false;
        if (r5 <= 0.0f) goto L5;
        boolean r1 = true;
    L6:
        Assertions.checkArgument(r1);
        if (r6 <= 0.0f) goto L9;
        r2 = true;
    L9:
        Assertions.checkArgument(r2);
        this.speed = r5;
        this.pitch = r6;
        this.scaledUsPerMs = Math.round(r5 * 1000.0f);
        return;
    L5:
        r1 = false;
        goto L6
    }

    public static PlaybackParameters fromBundle(Bundle r3) {
        return new PlaybackParameters(r3.getFloat(FIELD_SPEED, 1.0f), r3.getFloat(FIELD_PITCH, 1.0f));
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L15:
        return false;
    L8:
        if (PlaybackParameters.class != r5.getClass()) goto L15;
        PlaybackParameters r52 = (PlaybackParameters) r5;
        if (this.speed != r52.speed) goto L15;
        if (this.pitch != r52.pitch) goto L15;
        return true;
    }

    public long getMediaTimeUsForPlayoutTimeMs(long r3) {
        return r3 * this.scaledUsPerMs;
    }

    public int hashCode() {
        return ((527 + Float.floatToRawIntBits(this.speed)) * 31) + Float.floatToRawIntBits(this.pitch);
    }

    public Bundle toBundle() {
        Bundle r0 = new Bundle();
        r0.putFloat(FIELD_SPEED, this.speed);
        r0.putFloat(FIELD_PITCH, this.pitch);
        return r0;
    }

    public String toString() {
        return Util.formatInvariant("PlaybackParameters(speed=%.2f, pitch=%.2f)", new Object[]{Float.valueOf(this.speed), Float.valueOf(this.pitch)});
    }

    public PlaybackParameters withSpeed(float r2) {
        return new PlaybackParameters(r2, this.pitch);
    }
}
