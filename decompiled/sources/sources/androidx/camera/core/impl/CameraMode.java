package androidx.camera.core.impl;

import com.exteragram.messenger.plugins.PluginsConstants;

/* loaded from: classes3.dex */
public abstract class CameraMode {
    public static String toLabelString(int r1) {
        if (r1 != 1) goto L5;
        return "CONCURRENT_CAMERA";
    L5:
        if (r1 == 2) goto L8;
        return PluginsConstants.Strategy.DEFAULT;
    L8:
        return "ULTRA_HIGH_RESOLUTION_CAMERA";
    }
}
