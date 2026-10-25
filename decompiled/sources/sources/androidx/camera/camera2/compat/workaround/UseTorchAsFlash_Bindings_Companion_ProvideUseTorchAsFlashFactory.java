package androidx.camera.camera2.compat.workaround;

import androidx.camera.camera2.compat.quirk.CameraQuirks;
import androidx.camera.camera2.compat.workaround.UseTorchAsFlash;
import androidx.camera.camera2.internal.IntrinsicZoomCalculator;
import androidx.camera.camera2.pipe.CameraDevices;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class UseTorchAsFlash_Bindings_Companion_ProvideUseTorchAsFlashFactory implements Provider {
    public static UseTorchAsFlash provideUseTorchAsFlash(CameraQuirks r1, CameraDevices r2, IntrinsicZoomCalculator r3) {
        return (UseTorchAsFlash) Preconditions.checkNotNullFromProvides(UseTorchAsFlash.Bindings.Companion.provideUseTorchAsFlash(r1, r2, r3));
    }
}
