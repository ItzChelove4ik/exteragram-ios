package androidx.camera.camera2.config;

import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class CameraModule_Companion_ProvideCameraIdStringFactory implements Provider {
    public static String provideCameraIdString(CameraConfig r1) {
        return (String) Preconditions.checkNotNullFromProvides(CameraModule.Companion.provideCameraIdString(r1));
    }
}
