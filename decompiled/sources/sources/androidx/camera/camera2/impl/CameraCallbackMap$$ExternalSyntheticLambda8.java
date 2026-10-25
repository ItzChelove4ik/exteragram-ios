package androidx.camera.camera2.impl;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import androidx.camera.core.impl.CameraCaptureCallback;

/* loaded from: classes3.dex */
public final /* synthetic */ class CameraCallbackMap$$ExternalSyntheticLambda8 implements Runnable {
    public final /* synthetic */ CameraCaptureCallback f$0;
    public final /* synthetic */ CameraCaptureSession f$1;
    public final /* synthetic */ CaptureRequest f$2;
    public final /* synthetic */ CaptureResult f$3;

    public /* synthetic */ CameraCallbackMap$$ExternalSyntheticLambda8(CameraCaptureCallback r1, CameraCaptureSession r2, CaptureRequest r3, CaptureResult r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CameraCallbackMap.$r8$lambda$hNQmOr210V_9UdLd15fDwjTXneg(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
