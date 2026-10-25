package androidx.camera.core.impl;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public abstract class ExtendedCameraConfigProviderStore {
    private static final Map<Object, CameraConfigProvider> CAMERA_CONFIG_PROVIDERS = null;
    private static final Object LOCK = null;

    static {
        LOCK = new Object();
        CAMERA_CONFIG_PROVIDERS = new HashMap();
    }

    public static CameraConfigProvider getConfigProvider(Object r2) {
        Object r0 = LOCK;
        monitor-enter(r0);
        CameraConfigProvider r22 = CAMERA_CONFIG_PROVIDERS.get(r2);     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        if (r22 == null) goto L8;
        return r22;
    L8:
        return CameraConfigProvider.EMPTY;
    L9:
        th = move-exception;
        throw th;
    }
}
