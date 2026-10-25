package org.webrtc;

import org.webrtc.VideoFrame;

/* loaded from: classes6.dex */
public final /* synthetic */ class JavaI420Buffer$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ VideoFrame.I420Buffer f$0;

    public /* synthetic */ JavaI420Buffer$$ExternalSyntheticLambda0(VideoFrame.I420Buffer r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.release();
    }
}
