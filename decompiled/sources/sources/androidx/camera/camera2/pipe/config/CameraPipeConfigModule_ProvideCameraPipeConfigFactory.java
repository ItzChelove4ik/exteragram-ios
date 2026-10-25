package androidx.camera.camera2.pipe.config;

import androidx.camera.camera2.pipe.CameraPipe;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class CameraPipeConfigModule_ProvideCameraPipeConfigFactory implements Provider {
    public static CameraPipe.Config provideCameraPipeConfig(CameraPipeConfigModule r0) {
        return (CameraPipe.Config) Preconditions.checkNotNullFromProvides(r0.provideCameraPipeConfig());
    }
}
