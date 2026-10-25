package androidx.camera.camera2.config;

import androidx.camera.core.CameraXConfig;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class CameraAppConfig_ProvideCameraXConfigFactory implements Provider {
    public static CameraXConfig provideCameraXConfig(CameraAppConfig r0) {
        return (CameraXConfig) Preconditions.checkNotNullFromProvides(r0.provideCameraXConfig());
    }
}
