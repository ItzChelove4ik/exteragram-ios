package com.exteragram.messenger.camera;

/* loaded from: classes4.dex */
public final /* synthetic */ class CameraXSession$$ExternalSyntheticLambda8 implements Runnable {
    public final /* synthetic */ CameraXSession f$0;

    public /* synthetic */ CameraXSession$$ExternalSyntheticLambda8(CameraXSession r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.closeCamera();
    }
}
