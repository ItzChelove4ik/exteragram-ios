package androidx.camera.camera2.impl;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureRequest;
import android.view.Surface;
import androidx.camera.core.impl.CameraCaptureCallback;

/* loaded from: classes3.dex */
public final /* synthetic */ class CameraCallbackMap$$ExternalSyntheticLambda10 implements Runnable {
    public final /* synthetic */ CameraCaptureCallback f$0;
    public final /* synthetic */ CameraCaptureSession f$1;
    public final /* synthetic */ CaptureRequest f$2;
    public final /* synthetic */ Surface f$3;
    public final /* synthetic */ long f$4;

    public /* synthetic */ CameraCallbackMap$$ExternalSyntheticLambda10(CameraCaptureCallback r1, CameraCaptureSession r2, CaptureRequest r3, Surface r4, long r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CameraCallbackMap.m47$r8$lambda$sePtB5vsRnRT3T8jIK71hutC_E(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
