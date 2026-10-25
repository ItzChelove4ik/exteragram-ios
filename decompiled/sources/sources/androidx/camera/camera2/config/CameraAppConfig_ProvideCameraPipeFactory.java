package androidx.camera.camera2.config;

import androidx.camera.camera2.pipe.CameraPipe;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class CameraAppConfig_ProvideCameraPipeFactory implements Provider {
    public static CameraPipe provideCameraPipe(CameraAppConfig r0) {
        return (CameraPipe) Preconditions.checkNotNullFromProvides(r0.provideCameraPipe());
    }
}
