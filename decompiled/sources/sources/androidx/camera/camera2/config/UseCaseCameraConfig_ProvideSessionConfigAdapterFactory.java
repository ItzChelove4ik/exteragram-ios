package androidx.camera.camera2.config;

import androidx.camera.camera2.adapter.SessionConfigAdapter;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class UseCaseCameraConfig_ProvideSessionConfigAdapterFactory implements Provider {
    public static SessionConfigAdapter provideSessionConfigAdapter(UseCaseCameraConfig r0) {
        return (SessionConfigAdapter) Preconditions.checkNotNullFromProvides(r0.provideSessionConfigAdapter());
    }
}
