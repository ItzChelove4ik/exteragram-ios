package androidx.camera.camera2.pipe.config;

import android.content.Context;
import androidx.camera.camera2.pipe.compat.DevicePolicyManagerWrapper;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class CameraPipeModule_Companion_ProvideDevicePolicyManagerWrapperFactory implements Provider {
    public static DevicePolicyManagerWrapper provideDevicePolicyManagerWrapper(Context r1) {
        return (DevicePolicyManagerWrapper) Preconditions.checkNotNullFromProvides(CameraPipeModule.Companion.provideDevicePolicyManagerWrapper(r1));
    }
}
