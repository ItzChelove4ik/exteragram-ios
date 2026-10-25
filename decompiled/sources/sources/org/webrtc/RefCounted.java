package org.webrtc;

/* loaded from: classes6.dex */
public interface RefCounted {
    @CalledByNative
    void release();

    @CalledByNative
    void retain();
}
