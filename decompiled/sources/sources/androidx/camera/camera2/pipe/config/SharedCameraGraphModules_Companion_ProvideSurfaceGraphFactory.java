package androidx.camera.camera2.pipe.config;

import androidx.camera.camera2.pipe.CameraController;
import androidx.camera.camera2.pipe.CameraSurfaceManager;
import androidx.camera.camera2.pipe.graph.StreamGraphImpl;
import androidx.camera.camera2.pipe.graph.SurfaceGraph;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class SharedCameraGraphModules_Companion_ProvideSurfaceGraphFactory implements Provider {
    public static SurfaceGraph provideSurfaceGraph(StreamGraphImpl r1, javax.inject.Provider<CameraController> r2, CameraSurfaceManager r3) {
        return (SurfaceGraph) Preconditions.checkNotNullFromProvides(SharedCameraGraphModules.Companion.provideSurfaceGraph(r1, r2, r3));
    }
}
