package org.telegram.utils.camera.roundvideo;

import org.telegram.utils.camera.roundvideo.RoundVideoCameraController;
import org.telegram.utils.camera.roundvideo.RoundVideoCodecRecorder;

/* loaded from: classes6.dex */
public final /* synthetic */ class RoundVideoCameraController$$ExternalSyntheticLambda13 implements RoundVideoCodecRecorder.ErrorListener {
    public final /* synthetic */ RoundVideoCameraController.Callback f$0;

    public /* synthetic */ RoundVideoCameraController$$ExternalSyntheticLambda13(RoundVideoCameraController.Callback r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.utils.camera.roundvideo.RoundVideoCodecRecorder.ErrorListener
    public final void onError(Exception r1) {
        this.f$0.onError(r1);
    }
}
