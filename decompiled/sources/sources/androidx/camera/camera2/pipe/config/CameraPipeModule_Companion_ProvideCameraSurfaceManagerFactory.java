package androidx.camera.camera2.pipe.config;

import androidx.camera.camera2.pipe.CameraSurfaceManager;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class CameraPipeModule_Companion_ProvideCameraSurfaceManagerFactory implements Provider {
    public static CameraSurfaceManager provideCameraSurfaceManager() {
        return (CameraSurfaceManager) Preconditions.checkNotNullFromProvides(CameraPipeModule.Companion.provideCameraSurfaceManager());
    }
}
