package org.telegram.messenger.camera;

import android.hardware.Camera;

/* loaded from: classes5.dex */
public final /* synthetic */ class CameraController$$ExternalSyntheticLambda15 implements Camera.ErrorCallback {
    public final /* synthetic */ CameraController f$0;
    public final /* synthetic */ CameraSession f$1;

    public /* synthetic */ CameraController$$ExternalSyntheticLambda15(CameraController r1, CameraSession r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.hardware.Camera.ErrorCallback
    public final void onError(int r2, Camera r3) {
        CameraController.$r8$lambda$LbbR0Pva4cugAOPQxOB2ZxZD7ds(this.f$0, this.f$1, r2, r3);
    }
}
