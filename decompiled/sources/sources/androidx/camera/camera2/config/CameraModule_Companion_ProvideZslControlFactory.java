package androidx.camera.camera2.config;

import androidx.camera.camera2.adapter.ZslControl;
import androidx.camera.camera2.impl.CameraProperties;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class CameraModule_Companion_ProvideZslControlFactory implements Provider {
    public static ZslControl provideZslControl(CameraProperties r1) {
        return (ZslControl) Preconditions.checkNotNullFromProvides(CameraModule.Companion.provideZslControl(r1));
    }
}
