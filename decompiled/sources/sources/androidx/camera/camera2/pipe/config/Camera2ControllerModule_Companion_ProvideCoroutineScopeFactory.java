package androidx.camera.camera2.pipe.config;

import androidx.camera.camera2.pipe.core.Threads;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

/* loaded from: classes3.dex */
public abstract class Camera2ControllerModule_Companion_ProvideCoroutineScopeFactory implements Provider {
    public static CoroutineScope provideCoroutineScope(Threads r1, Job r2) {
        return (CoroutineScope) Preconditions.checkNotNullFromProvides(Camera2ControllerModule.Companion.provideCoroutineScope(r1, r2));
    }
}
