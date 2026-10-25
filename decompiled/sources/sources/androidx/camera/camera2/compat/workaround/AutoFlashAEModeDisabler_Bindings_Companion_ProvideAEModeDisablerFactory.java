package androidx.camera.camera2.compat.workaround;

import androidx.camera.camera2.compat.quirk.CameraQuirks;
import androidx.camera.camera2.compat.workaround.AutoFlashAEModeDisabler;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class AutoFlashAEModeDisabler_Bindings_Companion_ProvideAEModeDisablerFactory implements Provider {
    public static AutoFlashAEModeDisabler provideAEModeDisabler(CameraQuirks r1) {
        return (AutoFlashAEModeDisabler) Preconditions.checkNotNullFromProvides(AutoFlashAEModeDisabler.Bindings.Companion.provideAEModeDisabler(r1));
    }
}
