package org.webrtc;

import org.webrtc.VideoEncoder;

/* loaded from: classes6.dex */
public final /* synthetic */ class VideoEncoderWrapper$$ExternalSyntheticLambda0 implements VideoEncoder.Callback {
    public final /* synthetic */ long f$0;

    public /* synthetic */ VideoEncoderWrapper$$ExternalSyntheticLambda0(long r1) {
        this.f$0 = r1;
    }

    @Override // org.webrtc.VideoEncoder.Callback
    public final void onEncodedFrame(EncodedImage r3, VideoEncoder.CodecSpecificInfo r4) {
        VideoEncoderWrapper.m22400$r8$lambda$jYrJJY9KI0LueThhuMHDCSjs4w(this.f$0, r3, r4);
    }
}
