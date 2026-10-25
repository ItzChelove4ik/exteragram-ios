package androidx.camera.camera2.pipe.config;

import android.hardware.camera2.CameraManager;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.core.Threads;
import androidx.camera.camera2.pipe.internal.CameraStatusMonitor;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import kotlinx.coroutines.Job;

/* loaded from: classes3.dex */
public abstract class Camera2ControllerModule_Companion_ProvideCameraStatusMonitorFactory implements Provider {
    public static CameraStatusMonitor provideCameraStatusMonitor(javax.inject.Provider<CameraManager> r1, Threads r2, CameraGraph.Config r3, Job r4) {
        return (CameraStatusMonitor) Preconditions.checkNotNullFromProvides(Camera2ControllerModule.Companion.provideCameraStatusMonitor(r1, r2, r3, r4));
    }
}
