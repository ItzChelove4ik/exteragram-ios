package androidx.camera.camera2.pipe.config;

import dagger.internal.Preconditions;
import dagger.internal.Provider;
import kotlinx.coroutines.Job;

/* loaded from: classes3.dex */
public abstract class CameraPipeModule_Companion_ProvideCameraPipeJobFactory implements Provider {
    public static Job provideCameraPipeJob() {
        return (Job) Preconditions.checkNotNullFromProvides(CameraPipeModule.Companion.provideCameraPipeJob());
    }
}
