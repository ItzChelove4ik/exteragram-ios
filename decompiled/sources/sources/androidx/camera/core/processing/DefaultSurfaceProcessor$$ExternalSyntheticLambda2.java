package androidx.camera.core.processing;

import androidx.camera.core.SurfaceOutput;

/* loaded from: classes3.dex */
public final /* synthetic */ class DefaultSurfaceProcessor$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ SurfaceOutput f$0;

    public /* synthetic */ DefaultSurfaceProcessor$$ExternalSyntheticLambda2(SurfaceOutput r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.close();
    }
}
