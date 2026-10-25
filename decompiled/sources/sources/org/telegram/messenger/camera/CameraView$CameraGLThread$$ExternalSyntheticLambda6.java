package org.telegram.messenger.camera;

import android.graphics.SurfaceTexture;
import org.telegram.messenger.camera.CameraView;

/* loaded from: classes5.dex */
public final /* synthetic */ class CameraView$CameraGLThread$$ExternalSyntheticLambda6 implements SurfaceTexture.OnFrameAvailableListener {
    public final /* synthetic */ CameraView.CameraGLThread f$0;

    public /* synthetic */ CameraView$CameraGLThread$$ExternalSyntheticLambda6(CameraView.CameraGLThread r1) {
        this.f$0 = r1;
    }

    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
    public final void onFrameAvailable(SurfaceTexture r1) {
        CameraView.CameraGLThread.$r8$lambda$SwjKBgMmKm1_673l0_PQf94d7U8(this.f$0, r1);
    }
}
