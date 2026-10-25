package androidx.camera.camera2.pipe.config;

import androidx.camera.camera2.pipe.CameraPipe;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class CameraPipeConfigModule_ProvideCameraPipeFlagsFactory implements Provider {
    public static CameraPipe.Flags provideCameraPipeFlags(CameraPipeConfigModule r0) {
        return (CameraPipe.Flags) Preconditions.checkNotNullFromProvides(r0.provideCameraPipeFlags());
    }
}
