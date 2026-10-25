package org.webrtc;

import java.nio.ByteBuffer;
import org.webrtc.VideoFrame;

/* loaded from: classes6.dex */
public class NV12Buffer implements VideoFrame.Buffer {
    private final ByteBuffer buffer;
    private final int height;
    private final RefCountDelegate refCountDelegate;
    private final int sliceHeight;
    private final int stride;
    private final int width;

    public NV12Buffer(int r1, int r2, int r3, int r4, ByteBuffer r5, Runnable r6) {
        this.width = r1;
        this.height = r2;
        this.stride = r3;
        this.sliceHeight = r4;
        this.buffer = r5;
        this.refCountDelegate = new RefCountDelegate(r6);
    }

    private static native void nativeCropAndScale(int r0, int r1, int r2, int r3, int r4, int r5, ByteBuffer r6, int r7, int r8, int r9, int r10, ByteBuffer r11, int r12, ByteBuffer r13, int r14, ByteBuffer r15, int r16);

    @Override // org.webrtc.VideoFrame.Buffer
    public VideoFrame.Buffer cropAndScale(int r20, int r21, int r22, int r23, int r24, int r25) {
        JavaI420Buffer r1 = JavaI420Buffer.allocate(r24, r25);
        nativeCropAndScale(r20, r21, r22, r23, r24, r25, this.buffer, this.width, this.height, this.stride, this.sliceHeight, r1.getDataY(), r1.getStrideY(), r1.getDataU(), r1.getStrideU(), r1.getDataV(), r1.getStrideV());
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
