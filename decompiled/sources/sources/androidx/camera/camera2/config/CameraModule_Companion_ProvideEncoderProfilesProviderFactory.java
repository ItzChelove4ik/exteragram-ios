package androidx.camera.camera2.config;

import androidx.camera.camera2.compat.quirk.CameraQuirks;
import androidx.camera.core.impl.EncoderProfilesProvider;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class CameraModule_Companion_ProvideEncoderProfilesProviderFactory implements Provider {
    public static EncoderProfilesProvider provideEncoderProfilesProvider(String r1, CameraQuirks r2) {
        return (EncoderProfilesProvider) Preconditions.checkNotNullFromProvides(CameraModule.Companion.provideEncoderProfilesProvider(r1, r2));
    }
}
