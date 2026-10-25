package androidx.camera.core.processing.concurrent;

import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.processing.SurfaceEdge;
import java.util.Map;

/* loaded from: classes3.dex */
public final /* synthetic */ class DualSurfaceProcessorNode$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ DualSurfaceProcessorNode f$0;
    public final /* synthetic */ CameraInternal f$1;
    public final /* synthetic */ CameraInternal f$2;
    public final /* synthetic */ SurfaceEdge f$3;
    public final /* synthetic */ SurfaceEdge f$4;
    public final /* synthetic */ Map.Entry f$5;

    public /* synthetic */ DualSurfaceProcessorNode$$ExternalSyntheticLambda1(DualSurfaceProcessorNode r1, CameraInternal r2, CameraInternal r3, SurfaceEdge r4, SurfaceEdge r5, Map.Entry r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        DualSurfaceProcessorNode.$r8$lambda$bt74CxLa0a7Yc5G9XI0_olc5UM0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
