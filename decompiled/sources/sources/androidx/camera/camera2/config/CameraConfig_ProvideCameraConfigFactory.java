package androidx.camera.camera2.config;

import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class CameraConfig_ProvideCameraConfigFactory implements Provider {
    public static CameraConfig provideCameraConfig(CameraConfig r0) {
        return (CameraConfig) Preconditions.checkNotNullFromProvides(r0.provideCameraConfig());
    }
}
