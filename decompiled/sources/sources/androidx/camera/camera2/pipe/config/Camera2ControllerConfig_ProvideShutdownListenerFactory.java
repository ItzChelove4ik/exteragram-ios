package androidx.camera.camera2.pipe.config;

import androidx.camera.camera2.pipe.compat.Camera2CameraController;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class Camera2ControllerConfig_ProvideShutdownListenerFactory implements Provider {
    public static Camera2CameraController.ShutdownListener provideShutdownListener(Camera2ControllerConfig r0) {
        return (Camera2CameraController.ShutdownListener) Preconditions.checkNotNullFromProvides(r0.provideShutdownListener());
    }
}
