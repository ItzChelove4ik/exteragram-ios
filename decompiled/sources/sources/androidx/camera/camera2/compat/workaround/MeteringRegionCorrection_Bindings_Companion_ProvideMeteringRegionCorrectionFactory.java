package androidx.camera.camera2.compat.workaround;

import androidx.camera.camera2.compat.quirk.CameraQuirks;
import androidx.camera.camera2.compat.workaround.MeteringRegionCorrection;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class MeteringRegionCorrection_Bindings_Companion_ProvideMeteringRegionCorrectionFactory implements Provider {
    public static MeteringRegionCorrection provideMeteringRegionCorrection(CameraQuirks r1) {
        return (MeteringRegionCorrection) Preconditions.checkNotNullFromProvides(MeteringRegionCorrection.Bindings.Companion.provideMeteringRegionCorrection(r1));
    }
}
