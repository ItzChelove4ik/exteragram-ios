package androidx.camera.camera2.config;

import android.content.Context;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class CameraAppConfig_ProvideContextFactory implements Provider {
    public static Context provideContext(CameraAppConfig r0) {
        return (Context) Preconditions.checkNotNullFromProvides(r0.provideContext());
    }
}
