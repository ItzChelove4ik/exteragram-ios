package androidx.camera.core.processing;

import androidx.camera.core.SurfaceOutput;
import androidx.core.util.Consumer;

/* loaded from: classes3.dex */
public final /* synthetic */ class DefaultSurfaceProcessor$$ExternalSyntheticLambda6 implements Consumer {
    public final /* synthetic */ DefaultSurfaceProcessor f$0;
    public final /* synthetic */ SurfaceOutput f$1;

    public /* synthetic */ DefaultSurfaceProcessor$$ExternalSyntheticLambda6(DefaultSurfaceProcessor r1, SurfaceOutput r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.core.util.Consumer
    public final void accept(Object r2) {
        DefaultSurfaceProcessor.$r8$lambda$Yh367u_2KFarOwBqtvsSpeftiww(this.f$0, this.f$1, (SurfaceOutput.Event) r2);
    }
}
