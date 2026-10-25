package androidx.camera.core.processing;

import androidx.camera.core.SurfaceRequest;

/* loaded from: classes3.dex */
public final /* synthetic */ class DefaultSurfaceProcessor$$ExternalSyntheticLambda7 implements SurfaceRequest.TransformationInfoListener {
    public final /* synthetic */ DefaultSurfaceProcessor f$0;
    public final /* synthetic */ SurfaceRequest f$1;

    public /* synthetic */ DefaultSurfaceProcessor$$ExternalSyntheticLambda7(DefaultSurfaceProcessor r1, SurfaceRequest r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.camera.core.SurfaceRequest.TransformationInfoListener
    public final void onTransformationInfoUpdate(SurfaceRequest.TransformationInfo r2) {
        DefaultSurfaceProcessor.$r8$lambda$Q1rrgC8aoiGLiHQv9mIX_otuWf4(this.f$0, this.f$1, r2);
    }
}
