package org.telegram.messenger.camera;

import android.media.MediaCodec;
import java.nio.ByteBuffer;
import org.telegram.messenger.camera.CameraView;

/* loaded from: classes5.dex */
public final /* synthetic */ class CameraView$VideoRecorder$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ CameraView.VideoRecorder f$0;
    public final /* synthetic */ ByteBuffer f$1;
    public final /* synthetic */ MediaCodec.BufferInfo f$2;

    public /* synthetic */ CameraView$VideoRecorder$$ExternalSyntheticLambda0(CameraView.VideoRecorder r1, ByteBuffer r2, MediaCodec.BufferInfo r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CameraView.VideoRecorder.$r8$lambda$QZNHJK5UPSRb3o1Nc6MnaBR4YME(this.f$0, this.f$1, this.f$2);
    }
}
