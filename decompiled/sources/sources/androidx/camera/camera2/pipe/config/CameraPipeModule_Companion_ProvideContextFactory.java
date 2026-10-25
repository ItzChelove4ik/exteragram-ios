package androidx.camera.camera2.pipe.config;

import android.content.Context;
import androidx.camera.camera2.pipe.CameraPipe;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class CameraPipeModule_Companion_ProvideContextFactory implements Provider {
    public static Context provideContext(CameraPipe.Config r1) {
        return (Context) Preconditions.checkNotNullFromProvides(CameraPipeModule.Companion.provideContext(r1));
    }
}
