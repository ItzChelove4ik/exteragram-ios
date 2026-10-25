package androidx.camera.camera2.impl;

import androidx.camera.camera2.adapter.CaptureResultAdapter;
import androidx.camera.camera2.pipe.RequestMetadata;
import androidx.camera.core.impl.CameraCaptureCallback;

/* loaded from: classes3.dex */
public final /* synthetic */ class CameraCallbackMap$$ExternalSyntheticLambda15 implements Runnable {
    public final /* synthetic */ CameraCaptureCallback f$0;
    public final /* synthetic */ CameraCallbackMap f$1;
    public final /* synthetic */ RequestMetadata f$2;
    public final /* synthetic */ CaptureResultAdapter f$3;

    public /* synthetic */ CameraCallbackMap$$ExternalSyntheticLambda15(CameraCaptureCallback r1, CameraCallbackMap r2, RequestMetadata r3, CaptureResultAdapter r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CameraCallbackMap.$r8$lambda$Iw0pZtqtAFlD20zeLvBxONw4I5k(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
