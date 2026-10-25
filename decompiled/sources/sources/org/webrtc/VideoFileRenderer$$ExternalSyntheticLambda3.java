package org.webrtc;

import org.webrtc.VideoFrame;

/* loaded from: classes6.dex */
public final /* synthetic */ class VideoFileRenderer$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ VideoFileRenderer f$0;
    public final /* synthetic */ VideoFrame.I420Buffer f$1;
    public final /* synthetic */ VideoFrame f$2;

    public /* synthetic */ VideoFileRenderer$$ExternalSyntheticLambda3(VideoFileRenderer r1, VideoFrame.I420Buffer r2, VideoFrame r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        VideoFileRenderer.m22402$r8$lambda$thBQkU5jAxiF_o70UUp1dGNcyw(this.f$0, this.f$1, this.f$2);
    }
}
