package androidx.camera.camera2.config;

import androidx.camera.camera2.impl.UseCaseThreads;
import androidx.camera.core.impl.CameraThreadConfig;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class CameraModule_Companion_ProvideUseCaseThreadsFactory implements Provider {
    public static UseCaseThreads provideUseCaseThreads(CameraConfig r1, CameraThreadConfig r2) {
        return (UseCaseThreads) Preconditions.checkNotNullFromProvides(CameraModule.Companion.provideUseCaseThreads(r1, r2));
    }
}
