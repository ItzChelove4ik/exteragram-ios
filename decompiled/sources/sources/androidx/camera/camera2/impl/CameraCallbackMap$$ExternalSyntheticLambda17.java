package androidx.camera.camera2.impl;

import androidx.camera.camera2.pipe.RequestMetadata;
import androidx.camera.core.impl.CameraCaptureCallback;

/* loaded from: classes3.dex */
public final /* synthetic */ class CameraCallbackMap$$ExternalSyntheticLambda17 implements Runnable {
    public final /* synthetic */ CameraCaptureCallback f$0;
    public final /* synthetic */ CameraCallbackMap f$1;
    public final /* synthetic */ RequestMetadata f$2;

    public /* synthetic */ CameraCallbackMap$$ExternalSyntheticLambda17(CameraCaptureCallback r1, CameraCallbackMap r2, RequestMetadata r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CameraCallbackMap.m43$r8$lambda$M670Wl8LnPSOrY9T6VPTVWEY(this.f$0, this.f$1, this.f$2);
    }
}
