package androidx.camera.camera2.pipe.config;

import android.content.Context;
import androidx.camera.camera2.pipe.CameraBackend;
import androidx.camera.camera2.pipe.CameraBackends;
import androidx.camera.camera2.pipe.CameraPipe;
import androidx.camera.camera2.pipe.core.Threads;
import androidx.camera.camera2.pipe.internal.CameraPipeLifetime;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class CameraPipeModule_Companion_ProvideCameraBackendsFactory implements Provider {
    public static CameraBackends provideCameraBackends(CameraPipe.Config r6, javax.inject.Provider<CameraBackend> r7, Context r8, Threads r9, CameraPipeLifetime r10) {
        return (CameraBackends) Preconditions.checkNotNullFromProvides(CameraPipeModule.Companion.provideCameraBackends(r6, r7, r8, r9, r10));
    }
}
