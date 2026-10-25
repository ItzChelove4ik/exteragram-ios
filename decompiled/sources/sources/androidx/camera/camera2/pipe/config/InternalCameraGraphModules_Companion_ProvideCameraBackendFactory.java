package androidx.camera.camera2.pipe.config;

import androidx.camera.camera2.pipe.CameraBackend;
import androidx.camera.camera2.pipe.CameraBackends;
import androidx.camera.camera2.pipe.CameraContext;
import androidx.camera.camera2.pipe.CameraGraph;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class InternalCameraGraphModules_Companion_ProvideCameraBackendFactory implements Provider {
    public static CameraBackend provideCameraBackend(CameraBackends r1, CameraGraph.Config r2, CameraContext r3) {
        return (CameraBackend) Preconditions.checkNotNullFromProvides(InternalCameraGraphModules.Companion.provideCameraBackend(r1, r2, r3));
    }
}
