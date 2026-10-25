package androidx.camera.core.processing.concurrent;

import androidx.camera.core.SurfaceOutput;
import androidx.core.util.Consumer;

/* loaded from: classes3.dex */
public final /* synthetic */ class DualSurfaceProcessor$$ExternalSyntheticLambda5 implements Consumer {
    public final /* synthetic */ DualSurfaceProcessor f$0;
    public final /* synthetic */ SurfaceOutput f$1;

    public /* synthetic */ DualSurfaceProcessor$$ExternalSyntheticLambda5(DualSurfaceProcessor r1, SurfaceOutput r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.core.util.Consumer
    public final void accept(Object r2) {
        DualSurfaceProcessor.m621$r8$lambda$SSJVT35FEA6PJGDsDjTufRx9_M(this.f$0, this.f$1, (SurfaceOutput.Event) r2);
    }
}
