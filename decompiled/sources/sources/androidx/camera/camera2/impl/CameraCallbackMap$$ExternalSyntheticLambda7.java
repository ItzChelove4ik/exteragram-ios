package androidx.camera.camera2.impl;

import androidx.camera.camera2.pipe.RequestMetadata;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CameraCaptureFailure;

/* loaded from: classes3.dex */
public final /* synthetic */ class CameraCallbackMap$$ExternalSyntheticLambda7 implements Runnable {
    public final /* synthetic */ CameraCaptureCallback f$0;
    public final /* synthetic */ CameraCallbackMap f$1;
    public final /* synthetic */ RequestMetadata f$2;
    public final /* synthetic */ CameraCaptureFailure f$3;

    public /* synthetic */ CameraCallbackMap$$ExternalSyntheticLambda7(CameraCaptureCallback r1, CameraCallbackMap r2, RequestMetadata r3, CameraCaptureFailure r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CameraCallbackMap.$r8$lambda$KQI8f9OIaJOzCCL54Z1iNtGDsFg(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
