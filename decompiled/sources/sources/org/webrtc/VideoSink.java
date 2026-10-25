package org.webrtc;

/* loaded from: classes6.dex */
public interface VideoSink {
    @CalledByNative
    void onFrame(VideoFrame r1);

    default void setParentSink(VideoSink r1) {
    }
}
