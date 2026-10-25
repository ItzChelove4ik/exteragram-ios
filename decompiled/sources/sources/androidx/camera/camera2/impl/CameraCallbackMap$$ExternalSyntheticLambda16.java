package androidx.camera.camera2.impl;

import android.hardware.camera2.CameraCaptureSession;
import androidx.camera.core.impl.CameraCaptureCallback;

/* loaded from: classes3.dex */
public final /* synthetic */ class CameraCallbackMap$$ExternalSyntheticLambda16 implements Runnable {
    public final /* synthetic */ CameraCaptureCallback f$0;
    public final /* synthetic */ CameraCaptureSession f$1;

    public /* synthetic */ CameraCallbackMap$$ExternalSyntheticLambda16(CameraCaptureCallback r1, CameraCaptureSession r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CameraCallbackMap.m46$r8$lambda$rG3mWWpycq50ysOQrBgrpSISs(this.f$0, this.f$1);
    }
}
