package androidx.camera.camera2.pipe.config;

import android.content.Context;
import android.content.pm.PackageManager;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class CameraPipeModule_Companion_ProvidePackageManagerFactory implements Provider {
    public static PackageManager providePackageManager(Context r1) {
        return (PackageManager) Preconditions.checkNotNullFromProvides(CameraPipeModule.Companion.providePackageManager(r1));
    }
}
