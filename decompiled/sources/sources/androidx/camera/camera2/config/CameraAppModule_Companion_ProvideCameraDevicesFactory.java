package androidx.camera.camera2.config;

import androidx.camera.camera2.pipe.CameraDevices;
import androidx.camera.camera2.pipe.CameraPipe;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class CameraAppModule_Companion_ProvideCameraDevicesFactory implements Provider {
    public static CameraDevices provideCameraDevices(CameraPipe r1) {
        return (CameraDevices) Preconditions.checkNotNullFromProvides(CameraAppModule.Companion.provideCameraDevices(r1));
    }
}
