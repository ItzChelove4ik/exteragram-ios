package androidx.camera.camera2.pipe.config;

import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.Request;
import androidx.camera.camera2.pipe.graph.Listener3A;
import androidx.camera.camera2.pipe.internal.FrameDistributor;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.List;

/* loaded from: classes3.dex */
public abstract class SharedCameraGraphModules_Companion_ProvideRequestListenersFactory implements Provider {
    public static List<Request.Listener> provideRequestListeners(CameraGraph.Config r1, Listener3A r2, FrameDistributor r3) {
        return (List) Preconditions.checkNotNullFromProvides(SharedCameraGraphModules.Companion.provideRequestListeners(r1, r2, r3));
    }
}
