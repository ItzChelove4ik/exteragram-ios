package androidx.camera.camera2.pipe.config;

import androidx.camera.camera2.pipe.SurfaceTracker;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class Camera2ControllerConfig_ProvideSurfaceGraphFactory implements Provider {
    public static SurfaceTracker provideSurfaceGraph(Camera2ControllerConfig r0) {
        return (SurfaceTracker) Preconditions.checkNotNullFromProvides(r0.provideSurfaceGraph());
    }
}
