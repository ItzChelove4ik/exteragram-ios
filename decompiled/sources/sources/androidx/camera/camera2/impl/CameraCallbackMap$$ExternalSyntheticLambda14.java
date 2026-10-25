package androidx.camera.camera2.impl;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import androidx.camera.core.impl.CameraCaptureCallback;

/* loaded from: classes3.dex */
public final /* synthetic */ class CameraCallbackMap$$ExternalSyntheticLambda14 implements Runnable {
    public final /* synthetic */ CameraCaptureCallback f$0;
    public final /* synthetic */ CameraCaptureSession f$1;
    public final /* synthetic */ CaptureRequest f$2;
    public final /* synthetic */ TotalCaptureResult f$3;

    public /* synthetic */ CameraCallbackMap$$ExternalSyntheticLambda14(CameraCaptureCallback r1, CameraCaptureSession r2, CaptureRequest r3, TotalCaptureResult r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CameraCallbackMap.m45$r8$lambda$fOIsCatyf0iIb5EP61FtWeus(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
