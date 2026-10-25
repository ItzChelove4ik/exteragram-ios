package androidx.camera.camera2.config;

import androidx.camera.camera2.compat.workaround.CapturePipelineTorchCorrection;
import androidx.camera.camera2.impl.CapturePipeline;
import androidx.camera.camera2.impl.CapturePipelineImpl;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class UseCaseCameraModule_Companion_ProvideCapturePipelineFactory implements Provider {
    public static CapturePipeline provideCapturePipeline(javax.inject.Provider<CapturePipelineImpl> r1, javax.inject.Provider<CapturePipelineTorchCorrection> r2) {
        return (CapturePipeline) Preconditions.checkNotNullFromProvides(UseCaseCameraModule.Companion.provideCapturePipeline(r1, r2));
    }
}
