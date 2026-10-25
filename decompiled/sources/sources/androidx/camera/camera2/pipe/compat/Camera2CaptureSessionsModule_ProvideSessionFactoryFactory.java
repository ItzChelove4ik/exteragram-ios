package androidx.camera.camera2.pipe.compat;

import androidx.camera.camera2.pipe.CameraGraph;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class Camera2CaptureSessionsModule_ProvideSessionFactoryFactory implements Provider {
    public static CaptureSessionFactory provideSessionFactory(javax.inject.Provider<AndroidMSessionFactory> r7, javax.inject.Provider<AndroidMHighSpeedSessionFactory> r8, javax.inject.Provider<AndroidNSessionFactory> r9, javax.inject.Provider<AndroidPSessionFactory> r10, javax.inject.Provider<AndroidExtensionSessionFactory> r11, CameraGraph.Config r12) {
        return (CaptureSessionFactory) Preconditions.checkNotNullFromProvides(Camera2CaptureSessionsModule.INSTANCE.provideSessionFactory(r7, r8, r9, r10, r11, r12));
    }
}
