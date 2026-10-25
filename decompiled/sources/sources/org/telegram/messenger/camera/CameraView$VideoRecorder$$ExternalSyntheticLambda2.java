package org.telegram.messenger.camera;

import java.util.concurrent.CountDownLatch;
import org.telegram.messenger.camera.CameraView;

/* loaded from: classes5.dex */
public final /* synthetic */ class CameraView$VideoRecorder$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ CameraView.VideoRecorder f$0;
    public final /* synthetic */ CountDownLatch f$1;

    public /* synthetic */ CameraView$VideoRecorder$$ExternalSyntheticLambda2(CameraView.VideoRecorder r1, CountDownLatch r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CameraView.VideoRecorder.$r8$lambda$qW8FVxIanQD28yfq5GVor7XZNuw(this.f$0, this.f$1);
    }
}
