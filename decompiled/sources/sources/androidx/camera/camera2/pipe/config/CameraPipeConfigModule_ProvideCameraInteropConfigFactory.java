package androidx.camera.camera2.pipe.config;

import androidx.camera.camera2.pipe.CameraPipe;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class CameraPipeConfigModule_ProvideCameraInteropConfigFactory implements Provider {
    public static CameraPipe.CameraInteropConfig provideCameraInteropConfig(CameraPipeConfigModule r0, CameraPipe.Config r1) {
        return (CameraPipe.CameraInteropConfig) Preconditions.checkNotNullFromProvides(r0.provideCameraInteropConfig(r1));
    }
}
