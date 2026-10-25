package androidx.camera.camera2.pipe.config;

import android.content.Context;
import androidx.camera.featurecombinationquery.CameraDeviceSetupCompatFactory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class CameraPipeModule_Companion_ProvideCameraDeviceSetupCompatFactoryFactory implements Provider {
    public static CameraDeviceSetupCompatFactory provideCameraDeviceSetupCompatFactory(Context r1) {
        return (CameraDeviceSetupCompatFactory) Preconditions.checkNotNullFromProvides(CameraPipeModule.Companion.provideCameraDeviceSetupCompatFactory(r1));
    }
}
