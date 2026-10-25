package androidx.media3.common.util;

import android.content.Context;
import android.opengl.EGL14;
import android.opengl.EGLDisplay;
import android.opengl.GLES20;
import android.opengl.GLU;
import android.os.Build;

/* loaded from: classes3.dex */
public abstract class GlUtil {
    public static final int[] EGL_CONFIG_ATTRIBUTES_RGBA_1010102 = null;
    public static final int[] EGL_CONFIG_ATTRIBUTES_RGBA_8888 = null;
    private static final int[] EGL_WINDOW_SURFACE_ATTRIBUTES_BT2020_HLG = null;
    private static final int[] EGL_WINDOW_SURFACE_ATTRIBUTES_BT2020_PQ = null;
    private static final int[] EGL_WINDOW_SURFACE_ATTRIBUTES_NONE = null;

    public static final class GlException extends Exception {
        public GlException(String r1) {
            super(r1);
        }
    }

    static {
        EGL_CONFIG_ATTRIBUTES_RGBA_8888 = new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 0, 12326, 0, 12344};
        EGL_CONFIG_ATTRIBUTES_RGBA_1010102 = new int[]{12352, 4, 12324, 10, 12323, 10, 12322, 10, 12321, 2, 12325, 0, 12326, 0, 12344};
        EGL_WINDOW_SURFACE_ATTRIBUTES_BT2020_PQ = new int[]{12445, 13120, 12344, 12344};
        EGL_WINDOW_SURFACE_ATTRIBUTES_BT2020_HLG = new int[]{12445, 13632, 12344, 12344};
        EGL_WINDOW_SURFACE_ATTRIBUTES_NONE = new int[]{12344};
    }

    public static void checkGlError() {
        StringBuilder r0 = new StringBuilder();
        boolean r1 = false;
    L3:
        int r2 = GLES20.glGetError();
        if (r2 == 0) goto L11;
        if (r1 == false) goto L7;
        r0.append('\n');
    L7:
        String r12 = GLU.gluErrorString(r2);
        if (r12 != null) goto L10;
        r12 = "error code: 0x" + Integer.toHexString(r2);
    L10:
        r0.append("glError: ");
        r0.append(r12);
        r1 = true;
        goto L3
    L11:
        if (r1 == true) goto L14;
        return;
    L14:
        throw new GlException(r0.toString());
    }

    public static void checkGlException(boolean r0, String r1) {
        if (r0 == false) goto L5;
        return;
    L5:
        throw new GlException(r1);
    }

    public static EGLDisplay getDefaultEglDisplay() {
        EGLDisplay r1 = EGL14.eglGetDisplay(0);
        checkGlException(!r1.equals(EGL14.EGL_NO_DISPLAY), "No EGL display.");
        checkGlException(EGL14.eglInitialize(r1, new int[1], 0, new int[1], 0), "Error in eglInitialize.");
        checkGlError();
        return r1;
    }

    public static boolean isBt2020HlgExtensionSupported() {
        return isExtensionSupported("EGL_EXT_gl_colorspace_bt2020_hlg");
    }

    public static boolean isBt2020PqExtensionSupported() {
        if (Build.VERSION.SDK_INT >= 33) goto L5;
        return false;
    L5:
        if (isExtensionSupported("EGL_EXT_gl_colorspace_bt2020_pq") == false) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean isColorTransferSupported(int r1) {
        if (r1 != 6) goto L7;
        return isBt2020PqExtensionSupported();
    L7:
        if (r1 == 7) goto L9;
        return true;
    L9:
        return isBt2020HlgExtensionSupported();
    }

    private static boolean isExtensionSupported(String r2) {
        String r0 = EGL14.eglQueryString(getDefaultEglDisplay(), 12373);
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.contains(r2) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean isProtectedContentExtensionSupported(Context r5) {
        int r0 = Build.VERSION.SDK_INT;
        if (r0 < 26) goto L5;
    L9:
        if (r0 >= 26) goto L14;
        if (r5.getPackageManager().hasSystemFeature("android.hardware.vr.high_performance") == true) goto L14;
        return false;
    L14:
        return isExtensionSupported("EGL_EXT_protected_content");
    L5:
        if ("samsung".equals(Build.MANUFACTURER) == false) goto L7;
    L8:
        return false;
    L7:
        if ("XT1650".equals(Build.MODEL) == false) goto L9;
        goto L8
    }

    public static boolean isSurfacelessContextExtensionSupported() {
        return isExtensionSupported("EGL_KHR_surfaceless_context");
    }
}
