package androidx.camera.camera2.config;

import androidx.camera.core.concurrent.CameraCoordinator;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class CameraAppConfig_ProvideCameraCoordinatorFactory implements Provider {
    public static CameraCoordinator provideCameraCoordinator(CameraAppConfig r0) {
        return (CameraCoordinator) Preconditions.checkNotNullFromProvides(r0.provideCameraCoordinator());
    }
}
