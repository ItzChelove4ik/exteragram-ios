package androidx.camera.core.processing;

import androidx.camera.core.SurfaceRequest;

/* loaded from: classes3.dex */
public final /* synthetic */ class DefaultSurfaceProcessor$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ SurfaceRequest f$0;

    public /* synthetic */ DefaultSurfaceProcessor$$ExternalSyntheticLambda4(SurfaceRequest r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.willNotProvideSurface();
    }
}
