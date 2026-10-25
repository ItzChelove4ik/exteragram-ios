package androidx.camera.camera2.config;

import android.content.Context;
import androidx.camera.camera2.impl.DisplayInfoManager;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class CameraAppConfig_ProvideDisplayInfoManagerFactory implements Provider {
    public static DisplayInfoManager provideDisplayInfoManager(CameraAppConfig r0, Context r1) {
        return (DisplayInfoManager) Preconditions.checkNotNullFromProvides(r0.provideDisplayInfoManager(r1));
    }
}
