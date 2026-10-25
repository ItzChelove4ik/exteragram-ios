package androidx.camera.camera2.pipe.config;

import android.content.Context;
import androidx.camera.camera2.pipe.CameraBackends;
import androidx.camera.camera2.pipe.CameraContext;
import androidx.camera.camera2.pipe.core.Threads;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class CameraPipeModule_Companion_ProvideCameraContextFactory implements Provider {
    public static CameraContext provideCameraContext(Context r1, Threads r2, CameraBackends r3) {
        return (CameraContext) Preconditions.checkNotNullFromProvides(CameraPipeModule.Companion.provideCameraContext(r1, r2, r3));
    }
}
