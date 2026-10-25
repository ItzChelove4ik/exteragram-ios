package org.webrtc;

import android.content.Context;

/* loaded from: classes6.dex */
public interface VideoCapturer {
    void changeCaptureFormat(int r1, int r2, int r3);

    void dispose();

    void initialize(SurfaceTextureHelper r1, Context r2, CapturerObserver r3);

    boolean isScreencast();

    void startCapture(int r1, int r2, int r3);

    void stopCapture();
}
