package androidx.camera.camera2.pipe.config;

import androidx.camera.camera2.pipe.graph.StreamGraphImpl;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class Camera2ControllerConfig_ProvideStreamGraphFactory implements Provider {
    public static StreamGraphImpl provideStreamGraph(Camera2ControllerConfig r0) {
        return (StreamGraphImpl) Preconditions.checkNotNullFromProvides(r0.provideStreamGraph());
    }
}
