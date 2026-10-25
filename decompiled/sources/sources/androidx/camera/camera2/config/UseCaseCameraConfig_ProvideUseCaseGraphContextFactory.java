package androidx.camera.camera2.config;

import androidx.camera.camera2.adapter.CameraStateAdapter;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class UseCaseCameraConfig_ProvideUseCaseGraphContextFactory implements Provider {
    public static UseCaseGraphContext provideUseCaseGraphContext(UseCaseCameraConfig r0, CameraStateAdapter r1) {
        return (UseCaseGraphContext) Preconditions.checkNotNullFromProvides(r0.provideUseCaseGraphContext(r1));
    }
}
