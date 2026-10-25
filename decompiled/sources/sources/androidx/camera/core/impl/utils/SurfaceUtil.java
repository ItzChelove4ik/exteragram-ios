package androidx.camera.core.impl.utils;

import android.view.Surface;

/* loaded from: classes3.dex */
public class SurfaceUtil {
    static {
        System.loadLibrary("surface_util_jni");
    }

    private static native int[] nativeGetSurfaceInfo(Surface r0);
}
