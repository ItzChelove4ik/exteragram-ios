package org.webrtc;

import org.webrtc.Camera2Session;

/* loaded from: classes6.dex */
public final /* synthetic */ class Camera2Session$CaptureSessionCallback$$ExternalSyntheticLambda0 implements VideoSink {
    public final /* synthetic */ Camera2Session.CaptureSessionCallback f$0;

    public /* synthetic */ Camera2Session$CaptureSessionCallback$$ExternalSyntheticLambda0(Camera2Session.CaptureSessionCallback r1) {
        this.f$0 = r1;
    }

    @Override // org.webrtc.VideoSink
    public final void onFrame(VideoFrame r1) {
        Camera2Session.CaptureSessionCallback.$r8$lambda$Du4gheEjtRdCZNeaqhQeHR1g9L4(this.f$0, r1);
    }
}
