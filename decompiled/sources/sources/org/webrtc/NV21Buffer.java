package org.webrtc;

import java.nio.ByteBuffer;
import org.webrtc.VideoFrame;

/* loaded from: classes6.dex */
public class NV21Buffer implements VideoFrame.Buffer {
    private final byte[] data;
    private final int height;
    private final RefCountDelegate refCountDelegate;
    private final int width;

    public NV21Buffer(byte[] r1, int r2, int r3, Runnable r4) {
        this.data = r1;
        this.width = r2;
        this.height = r3;
        this.refCountDelegate = new RefCountDelegate(r4);
    }

    private static native void nativeCropAndScale(int r0, int r1, int r2, int r3, int r4, int r5, byte[] r6, int r7, int r8, ByteBuffer r9, int r10, ByteBuffer r11, int r12, ByteBuffer r13, int r14);

    @Override // org.webrtc.VideoFrame.Buffer
    public VideoFrame.Buffer cropAndScale(int r18, int r19, int r20, int r21, int r22, int r23) {
        JavaI420Buffer r1 = JavaI420Buffer.allocate(r22, r23);
        nativeCropAndScale(r18, r19, r20, r21, r22, r23, this.data, this.width, this.height, r1.getDataY(), r1.getStrideY(), r1.getDataU(), r1.getStrideU(), r1.getDataV(), r1.getStrideV());
        return r1;
    }

    @Override // org.webrtc.VideoFrame.Buffer
    public int getHeight() {
        return this.height;
    }

    @Override // org.webrtc.VideoFrame.Buffer
    public int getWidth() {
        return this.width;
    }

    @Override // org.webrtc.VideoFrame.Buffer, org.webrtc.RefCounted
    public void release() {
        this.refCountDelegate.release();
    }

    @Override // org.webrtc.VideoFrame.Buffer, org.webrtc.RefCounted
    public void retain() {
        this.refCountDelegate.retain();
    }

    @Override // org.webrtc.VideoFrame.Buffer
    public VideoFrame.I420Buffer toI420() {
        int r3 = this.width;
        int r4 = this.height;
        return (VideoFrame.I420Buffer) cropAndScale(0, 0, r3, r4, r3, r4);
    }
}
