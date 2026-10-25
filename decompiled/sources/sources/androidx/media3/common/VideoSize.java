package androidx.media3.common;

import android.os.Bundle;
import androidx.media3.common.util.Util;
import com.android.dx.io.Opcodes;

/* loaded from: classes3.dex */
public final class VideoSize {
    private static final String FIELD_HEIGHT = null;
    private static final String FIELD_PIXEL_WIDTH_HEIGHT_RATIO = null;
    private static final String FIELD_WIDTH = null;
    public static final VideoSize UNKNOWN = null;
    public final int height;
    public final float pixelWidthHeightRatio;

    @Deprecated
    public final int unappliedRotationDegrees;
    public final int width;

    static {
        UNKNOWN = new VideoSize(0, 0);
        FIELD_WIDTH = Util.intToStringMaxRadix(0);
        FIELD_HEIGHT = Util.intToStringMaxRadix(1);
        FIELD_PIXEL_WIDTH_HEIGHT_RATIO = Util.intToStringMaxRadix(3);
    }

    public VideoSize(int r2, int r3) {
        this(r2, r3, 1.0f);
    }

    public VideoSize(int r1, int r2, float r3) {
        this.width = r1;
        this.height = r2;
        this.unappliedRotationDegrees = 0;
        this.pixelWidthHeightRatio = r3;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof VideoSize) == false) goto L14;
        VideoSize r52 = (VideoSize) r5;
        if (this.width != r52.width) goto L14;
        if (this.height != r52.height) goto L14;
        if (this.pixelWidthHeightRatio != r52.pixelWidthHeightRatio) goto L14;
        return true;
    L14:
        return false;
    }

    public int hashCode() {
        return ((((Opcodes.RSUB_INT_LIT8 + this.width) * 31) + this.height) * 31) + Float.floatToRawIntBits(this.pixelWidthHeightRatio);
    }

    public Bundle toBundle() {
        Bundle r0 = new Bundle();
        int r1 = this.width;
        if (r1 == 0) goto L5;
        r0.putInt(FIELD_WIDTH, r1);
    L5:
        int r12 = this.height;
        if (r12 == 0) goto L8;
        r0.putInt(FIELD_HEIGHT, r12);
    L8:
        float r3 = this.pixelWidthHeightRatio;
        if (r3 == 1.0f) goto L11;
        r0.putFloat(FIELD_PIXEL_WIDTH_HEIGHT_RATIO, r3);
    L11:
        return r0;
    }
}
