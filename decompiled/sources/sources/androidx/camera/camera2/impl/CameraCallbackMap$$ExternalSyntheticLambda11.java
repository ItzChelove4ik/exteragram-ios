package androidx.camera.camera2.impl;

import android.hardware.camera2.CameraCaptureSession;
import androidx.camera.core.impl.CameraCaptureCallback;

/* loaded from: classes3.dex */
public final /* synthetic */ class CameraCallbackMap$$ExternalSyntheticLambda11 implements Runnable {
    public final /* synthetic */ CameraCaptureCallback f$0;
    public final /* synthetic */ CameraCaptureSession f$1;
    public final /* synthetic */ long f$2;

    public /* synthetic */ CameraCallbackMap$$ExternalSyntheticLambda11(CameraCaptureCallback r1, CameraCaptureSession r2, long r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CameraCallbackMap.$r8$lambda$XTdOnnnHsk89bx5uDW3KaDeuITs(this.f$0, this.f$1, this.f$2);
    }
}
