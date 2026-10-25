package androidx.camera.core.processing;

import android.view.Surface;
import androidx.camera.core.SurfaceOutput;
import androidx.camera.core.impl.utils.futures.AsyncFunction;
import androidx.camera.core.processing.SurfaceEdge;
import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes3.dex */
public final /* synthetic */ class SurfaceEdge$$ExternalSyntheticLambda3 implements AsyncFunction {
    public final /* synthetic */ SurfaceEdge f$0;
    public final /* synthetic */ SurfaceEdge.SettableSurface f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ SurfaceOutput.CameraInputInfo f$3;
    public final /* synthetic */ SurfaceOutput.CameraInputInfo f$4;

    public /* synthetic */ SurfaceEdge$$ExternalSyntheticLambda3(SurfaceEdge r1, SurfaceEdge.SettableSurface r2, int r3, SurfaceOutput.CameraInputInfo r4, SurfaceOutput.CameraInputInfo r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // androidx.camera.core.impl.utils.futures.AsyncFunction
    public final ListenableFuture apply(Object r7) {
        return SurfaceEdge.$r8$lambda$fmIccKEiiPDQZ5pv_vs05SRNksM(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, (Surface) r7);
    }
}
