package androidx.camera.camera2.pipe.config;

import androidx.camera.camera2.pipe.CameraPipe;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class CameraPipeModule_Companion_ProvideCameraMetadataConfigFactory implements Provider {
    public static CameraPipe.CameraMetadataConfig provideCameraMetadataConfig(CameraPipe.Config r1) {
        return (CameraPipe.CameraMetadataConfig) Preconditions.checkNotNullFromProvides(CameraPipeModule.Companion.provideCameraMetadataConfig(r1));
    }
}
