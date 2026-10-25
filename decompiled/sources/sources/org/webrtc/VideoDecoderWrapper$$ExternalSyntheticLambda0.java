package org.webrtc;

import org.webrtc.VideoDecoder;

/* loaded from: classes6.dex */
public final /* synthetic */ class VideoDecoderWrapper$$ExternalSyntheticLambda0 implements VideoDecoder.Callback {
    public final /* synthetic */ long f$0;

    public /* synthetic */ VideoDecoderWrapper$$ExternalSyntheticLambda0(long r1) {
        this.f$0 = r1;
    }

    @Override // org.webrtc.VideoDecoder.Callback
    public final void onDecodedFrame(VideoFrame r3, Integer r4, Integer r5) {
        VideoDecoderWrapper.m22399$r8$lambda$7bMzuIVw8g6pRhYmvpg0phzE20(this.f$0, r3, r4, r5);
    }
}
