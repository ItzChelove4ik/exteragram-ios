package androidx.camera.core.processing;

import androidx.camera.core.processing.SurfaceEdge;

/* loaded from: classes3.dex */
public final /* synthetic */ class SurfaceEdge$$ExternalSyntheticLambda6 implements Runnable {
    public final /* synthetic */ SurfaceEdge.SettableSurface f$0;

    public /* synthetic */ SurfaceEdge$$ExternalSyntheticLambda6(SurfaceEdge.SettableSurface r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.decrementUseCount();
    }
}
