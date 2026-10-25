package androidx.camera.camera2.config;

import androidx.camera.camera2.compat.Camera2CameraControlCompat;
import androidx.camera.camera2.impl.ComboRequestListener;
import androidx.camera.camera2.impl.UseCaseThreads;
import androidx.camera.camera2.interop.Camera2CameraControl;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class CameraModule_Companion_ProvideCamera2CameraControlFactory implements Provider {
    public static Camera2CameraControl provideCamera2CameraControl(Camera2CameraControlCompat r1, UseCaseThreads r2, ComboRequestListener r3) {
        return (Camera2CameraControl) Preconditions.checkNotNullFromProvides(CameraModule.Companion.provideCamera2CameraControl(r1, r2, r3));
    }
}
