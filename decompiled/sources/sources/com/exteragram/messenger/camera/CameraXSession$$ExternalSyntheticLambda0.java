package com.exteragram.messenger.camera;

import android.util.Size;
import androidx.camera.core.SurfaceRequest;
import com.exteragram.messenger.camera.CameraXSession;

/* loaded from: classes4.dex */
public final /* synthetic */ class CameraXSession$$ExternalSyntheticLambda0 implements SurfaceRequest.TransformationInfoListener {
    public final /* synthetic */ Size f$0;
    public final /* synthetic */ CameraXSession.PreviewSizeListener f$1;

    public /* synthetic */ CameraXSession$$ExternalSyntheticLambda0(Size r1, CameraXSession.PreviewSizeListener r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.camera.core.SurfaceRequest.TransformationInfoListener
    public final void onTransformationInfoUpdate(SurfaceRequest.TransformationInfo r2) {
        CameraXSession.$r8$lambda$TRled3lKaEUY2eaet8nSIa2avwg(this.f$0, this.f$1, r2);
    }
}
