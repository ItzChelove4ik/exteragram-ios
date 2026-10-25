package androidx.camera.camera2.impl;

import androidx.camera.camera2.pipe.RequestMetadata;
import androidx.camera.core.impl.CameraCaptureCallback;

/* loaded from: classes3.dex */
public final /* synthetic */ class CameraCallbackMap$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ CameraCaptureCallback f$0;
    public final /* synthetic */ CameraCallbackMap f$1;
    public final /* synthetic */ RequestMetadata f$2;
    public final /* synthetic */ int f$3;

    public /* synthetic */ CameraCallbackMap$$ExternalSyntheticLambda5(CameraCaptureCallback r1, CameraCallbackMap r2, RequestMetadata r3, int r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CameraCallbackMap.m44$r8$lambda$QRXFgL4pZ1oKUmL181seJInXCA(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
