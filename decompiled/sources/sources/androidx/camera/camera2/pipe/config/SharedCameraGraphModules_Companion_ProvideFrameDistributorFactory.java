package androidx.camera.camera2.pipe.config;

import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.camera2.pipe.core.SystemClockOffsets;
import androidx.camera.camera2.pipe.graph.StreamGraphImpl;
import androidx.camera.camera2.pipe.internal.FrameCaptureQueue;
import androidx.camera.camera2.pipe.internal.FrameDistributor;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class SharedCameraGraphModules_Companion_ProvideFrameDistributorFactory implements Provider {
    public static FrameDistributor provideFrameDistributor(StreamGraphImpl r1, FrameCaptureQueue r2, CameraMetadata r3, SystemClockOffsets r4) {
        return (FrameDistributor) Preconditions.checkNotNullFromProvides(SharedCameraGraphModules.Companion.provideFrameDistributor(r1, r2, r3, r4));
    }
}
