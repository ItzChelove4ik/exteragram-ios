package org.webrtc;

import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public class JniCommon {
    public JniCommon() {
    }

    public static native void nativeAddRef(long r0);

    public static native ByteBuffer nativeAllocateByteBuffer(int r0);

    public static native void nativeFreeByteBuffer(ByteBuffer r0);

    public static native void nativeReleaseRef(long r0);
}
