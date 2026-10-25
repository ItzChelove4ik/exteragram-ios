package androidx.camera.camera2.pipe.config;

import androidx.camera.camera2.pipe.core.Threads;
import androidx.camera.camera2.pipe.internal.CameraPipeLifetime;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import kotlinx.coroutines.Job;

/* loaded from: classes3.dex */
public abstract class ThreadConfigModule_ProvideThreadsFactory implements Provider {
    public static Threads provideThreads(ThreadConfigModule r0, CameraPipeLifetime r1, Job r2) {
        return (Threads) Preconditions.checkNotNullFromProvides(r0.provideThreads(r1, r2));
    }
}
