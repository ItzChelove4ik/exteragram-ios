package androidx.camera.camera2.pipe.config;

import android.content.Context;
import android.hardware.camera2.CameraManager;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class CameraPipeModule_Companion_ProvideCameraManagerFactory implements Provider {
    public static CameraManager provideCameraManager(Context r1) {
        return (CameraManager) Preconditions.checkNotNullFromProvides(CameraPipeModule.Companion.provideCameraManager(r1));
    }
}
