package androidx.camera.camera2.compat.workaround;

import androidx.camera.camera2.compat.quirk.CameraQuirks;
import androidx.camera.camera2.compat.workaround.UseFlashModeTorchFor3aUpdate;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class UseFlashModeTorchFor3aUpdate_Bindings_Companion_ProvideUseFlashModeTorchFor3aUpdateFactory implements Provider {
    public static UseFlashModeTorchFor3aUpdate provideUseFlashModeTorchFor3aUpdate(CameraQuirks r1) {
        return (UseFlashModeTorchFor3aUpdate) Preconditions.checkNotNullFromProvides(UseFlashModeTorchFor3aUpdate.Bindings.Companion.provideUseFlashModeTorchFor3aUpdate(r1));
    }
}
