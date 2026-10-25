package androidx.camera.camera2.pipe.config;

import androidx.camera.camera2.pipe.CameraPipe;
import androidx.camera.camera2.pipe.StrictMode;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class CameraPipeModule_Companion_ProvideStrictModeFactory implements Provider {
    public static StrictMode provideStrictMode(CameraPipe.Flags r1) {
        return (StrictMode) Preconditions.checkNotNullFromProvides(CameraPipeModule.Companion.provideStrictMode(r1));
    }
}
