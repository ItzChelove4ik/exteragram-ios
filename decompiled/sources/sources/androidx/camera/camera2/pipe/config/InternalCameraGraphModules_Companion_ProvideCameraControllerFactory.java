package androidx.camera.camera2.pipe.config;

import androidx.camera.camera2.pipe.CameraBackend;
import androidx.camera.camera2.pipe.CameraContext;
import androidx.camera.camera2.pipe.CameraController;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.CameraGraphId;
import androidx.camera.camera2.pipe.StreamGraph;
import androidx.camera.camera2.pipe.SurfaceTracker;
import androidx.camera.camera2.pipe.graph.GraphProcessorImpl;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class InternalCameraGraphModules_Companion_ProvideCameraControllerFactory implements Provider {
    public static CameraController provideCameraController(CameraGraphId r8, CameraGraph.Config r9, CameraBackend r10, CameraContext r11, GraphProcessorImpl r12, StreamGraph r13, SurfaceTracker r14) {
        return (CameraController) Preconditions.checkNotNullFromProvides(InternalCameraGraphModules.Companion.provideCameraController(r8, r9, r10, r11, r12, r13, r14));
    }
}
