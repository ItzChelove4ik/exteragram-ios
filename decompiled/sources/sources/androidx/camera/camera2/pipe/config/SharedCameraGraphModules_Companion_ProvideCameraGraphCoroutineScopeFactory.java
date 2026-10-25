package androidx.camera.camera2.pipe.config;

import androidx.camera.camera2.pipe.core.Threads;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

/* loaded from: classes3.dex */
public abstract class SharedCameraGraphModules_Companion_ProvideCameraGraphCoroutineScopeFactory implements Provider {
    public static CoroutineScope provideCameraGraphCoroutineScope(Threads r1, Job r2) {
        return (CoroutineScope) Preconditions.checkNotNullFromProvides(SharedCameraGraphModules.Companion.provideCameraGraphCoroutineScope(r1, r2));
    }
}
