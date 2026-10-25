package org.webrtc;

import c.f$$ExternalSyntheticBUOutline1;
import com.android.dex.Dex$$ExternalSyntheticBUOutline1;
import java.nio.ByteBuffer;
import org.webrtc.VideoFrame;

/* loaded from: classes6.dex */
public class JavaI420Buffer implements VideoFrame.I420Buffer {
    private final ByteBuffer dataU;
    private final ByteBuffer dataV;
    private final ByteBuffer dataY;
    private final int height;
    private final RefCountDelegate refCountDelegate;
    private final int strideU;
    private final int strideV;
    private final int strideY;
    private final int width;

    /* renamed from: $r8$lambda$xTh-MXd1J8cqpWZMP08871GEkR4, reason: not valid java name */
    public static /* synthetic */ void m22356$r8$lambda$xThMXd1J8cqpWZMP08871GEkR4(ByteBuffer r0) {
        JniCommon.nativeFreeByteBuffer(r0);
    }

    private JavaI420Buffer(int r1, int r2, ByteBuffer r3, int r4, ByteBuffer r5, int r6, ByteBuffer r7, int r8, Runnable r9) {
        this.width = r1;
        this.height = r2;
        this.dataY = r3;
        this.dataU = r5;
        this.dataV = r7;
        this.strideY = r4;
        this.strideU = r6;
        this.strideV = r8;
        this.refCountDelegate = new RefCountDelegate(r9);
    }

    public static JavaI420Buffer allocate(int r12, int r13) {
        int r0 = (r13 + 1) / 2;
        int r8 = (r12 + 1) / 2;
        int r1 = r12 * r13;
        int r2 = r8 * r0;
        int r3 = r1 + r2;
        ByteBuffer r02 = JniCommon.nativeAllocateByteBuffer(((r8 * 2) * r0) + r1);
        r02.position(0);
        r02.limit(r1);
        ByteBuffer r5 = r02.slice();
        r02.position(r1);
        r02.limit(r3);
        ByteBuffer r7 = r02.slice();
        r02.position(r3);
        r02.limit(r3 + r2);
        return new JavaI420Buffer(r12, r13, r5, r12, r7, r8, r02.slice(), r8, new JavaI420Buffer$$ExternalSyntheticLambda1(r02));
    }

    private static void checkCapacity(ByteBuffer r0, int r1, int r2, int r3) {
        int r32 = (r3 * (r2 - 1)) + r1;
        if (r0.capacity() < r32) goto L5;
        return;
    L5:
        Dex$$ExternalSyntheticBUOutline1.m("Buffer must be at least ", r32, " bytes, but was ", r0.capacity());
    }

    public static VideoFrame.Buffer cropAndScaleI420(VideoFrame.I420Buffer r19, int r20, int r21, int r22, int r23, int r24, int r25) {
        if (r22 == r24) goto L5;
    L8:
        JavaI420Buffer r18 = allocate(r24, r25);
        nativeCropAndScaleI420(r19.getDataY(), r19.getStrideY(), r19.getDataU(), r19.getStrideU(), r19.getDataV(), r19.getStrideV(), r20, r21, r22, r23, r18.getDataY(), r18.getStrideY(), r18.getDataU(), r18.getStrideU(), r18.getDataV(), r18.getStrideV(), r24, r25);
        return r18;
    L5:
        if (r23 != r25) goto L8;
        ByteBuffer r2 = r19.getDataY();
        ByteBuffer r3 = r19.getDataU();
        ByteBuffer r4 = r19.getDataV();
        r2.position(r20 + (r19.getStrideY() * r21));
        int r5 = r20 / 2;
        int r6 = r21 / 2;
        r3.position((r19.getStrideU() * r6) + r5);
        r4.position(r5 + (r6 * r19.getStrideV()));
        r19.retain();
        return wrap(r24, r25, r2.slice(), r19.getStrideY(), r3.slice(), r19.getStrideU(), r4.slice(), r19.getStrideV(), new JavaI420Buffer$$ExternalSyntheticLambda0(r19));
    }

    private static native void nativeCropAndScaleI420(ByteBuffer r0, int r1, ByteBuffer r2, int r3, ByteBuffer r4, int r5, int r6, int r7, int r8, int r9, ByteBuffer r10, int r11, ByteBuffer r12, int r13, ByteBuffer r14, int r15, int r16, int r17);

    public static JavaI420Buffer wrap(int r10, int r11, ByteBuffer r12, int r13, ByteBuffer r14, int r15, ByteBuffer r16, int r17, Runnable r18) {
        if (r12 == null) goto L16;
        if (r14 == null) goto L16;
        if (r16 == null) goto L16;
        if (r12.isDirect() == true) goto L9;
    L14:
        f$$ExternalSyntheticBUOutline1.m("Data buffers must be direct byte buffers.");
        return null;
    L9:
        if (r14.isDirect() == false) goto L14;
        if (r16.isDirect() == false) goto L14;
        ByteBuffer r3 = r12.slice();
        ByteBuffer r5 = r14.slice();
        ByteBuffer r7 = r16.slice();
        int r0 = (r10 + 1) / 2;
        int r4 = (r11 + 1) / 2;
        checkCapacity(r3, r10, r11, r13);
        checkCapacity(r5, r0, r4, r15);
        checkCapacity(r7, r0, r4, r17);
        return new JavaI420Buffer(r10, r11, r3, r13, r5, r15, r7, r17, r18);
    L16:
        f$$ExternalSyntheticBUOutline1.m("Data buffers cannot be null.");
        return null;
    }

    @Override // org.webrtc.VideoFrame.Buffer
    public VideoFrame.Buffer cropAndScale(int r1, int r2, int r3, int r4, int r5, int r6) {
        return cropAndScaleI420(this, r1, r2, r3, r4, r5, r6);
    }

    @Override // org.webrtc.VideoFrame.I420Buffer
    public ByteBuffer getDataU() {
        return this.dataU.slice();
    }

    @Override // org.webrtc.VideoFrame.I420Buffer
    public ByteBuffer getDataV() {
        return this.dataV.slice();
    }

    @Override // org.webrtc.VideoFrame.I420Buffer
    public ByteBuffer getDataY() {
        return this.dataY.slice();
    }

    @Override // org.webrtc.VideoFrame.Buffer
    public int getHeight() {
        return this.height;
    }

    @Override // org.webrtc.VideoFrame.I420Buffer
    public int getStrideU() {
        return this.strideU;
    }

    @Override // org.webrtc.VideoFrame.I420Buffer
    public int getStrideV() {
        return this.strideV;
    }

    @Override // org.webrtc.VideoFrame.I420Buffer
    public int getStrideY() {
        return this.strideY;
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
        retain();
        return this;
    }
}
