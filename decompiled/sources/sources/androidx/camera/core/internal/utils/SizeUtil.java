package androidx.camera.core.internal.utils;

import android.util.Size;

/* loaded from: classes3.dex */
public abstract class SizeUtil {
    public static final Size RESOLUTION_1080P = null;
    public static final Size RESOLUTION_1440P = null;
    public static final Size RESOLUTION_1440P_16_9 = null;
    public static final Size RESOLUTION_480P = null;
    public static final Size RESOLUTION_720P = null;
    public static final Size RESOLUTION_QVGA = null;
    public static final Size RESOLUTION_UHD = null;
    public static final Size RESOLUTION_VGA = null;
    public static final Size RESOLUTION_ZERO = null;

    static {
        RESOLUTION_ZERO = new Size(0, 0);
        RESOLUTION_QVGA = new Size(320, 240);
        RESOLUTION_VGA = new Size(640, 480);
        RESOLUTION_480P = new Size(720, 480);
        RESOLUTION_720P = new Size(1280, 720);
        RESOLUTION_1080P = new Size(1920, 1080);
        RESOLUTION_1440P = new Size(1920, 1440);
        RESOLUTION_1440P_16_9 = new Size(2560, 1440);
        RESOLUTION_UHD = new Size(3840, 2160);
    }

    public static int getArea(int r0, int r1) {
        return r0 * r1;
    }

    public static int getArea(Size r1) {
        return getArea(r1.getWidth(), r1.getHeight());
    }

    public static boolean isSmallerByArea(Size r0, Size r1) {
        if (getArea(r0) >= getArea(r1)) goto L6;
        return true;
    L6:
        return false;
    }
}
