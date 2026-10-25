package org.telegram.ui.Components;

import android.graphics.SurfaceTexture;
import org.telegram.ui.Components.InstantCameraView;

/* loaded from: classes7.dex */
public final /* synthetic */ class InstantCameraView$CameraGLThread$$ExternalSyntheticLambda1 implements SurfaceTexture.OnFrameAvailableListener {
    public final /* synthetic */ InstantCameraView.CameraGLThread f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ InstantCameraView$CameraGLThread$$ExternalSyntheticLambda1(InstantCameraView.CameraGLThread r1, int r2, int r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
    public final void onFrameAvailable(SurfaceTexture r3) {
        InstantCameraView.CameraGLThread.$r8$lambda$bfRzXZb72dJTpFcy4EYMnqi0NkI(this.f$0, this.f$1, this.f$2, r3);
    }
}
