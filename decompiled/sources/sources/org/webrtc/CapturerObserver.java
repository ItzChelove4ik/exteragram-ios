package org.webrtc;

/* loaded from: classes6.dex */
public interface CapturerObserver {
    void onCapturerStarted(boolean r1);

    void onCapturerStopped();

    void onFrameCaptured(VideoFrame r1);
}
