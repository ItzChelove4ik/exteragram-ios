package org.webrtc;

import c.f$$ExternalSyntheticBUOutline1;
import com.android.dex.Dex$$ExternalSyntheticBUOutline1;
import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public class YuvHelper {
    public YuvHelper() {
    }

    public static void ABGRToI420(ByteBuffer r10, int r11, ByteBuffer r12, int r13, ByteBuffer r14, int r15, ByteBuffer r16, int r17, int r18, int r19) {
        nativeABGRToI420((ByteBuffer) checkNotNull(r10, "src"), r11, (ByteBuffer) checkNotNull(r12, "dstY"), r13, (ByteBuffer) checkNotNull(r14, "dstU"), r15, (ByteBuffer) checkNotNull(r16, "dstV"), r17, r18, r19);
    }

    public static void I420Copy(ByteBuffer r14, int r15, ByteBuffer r16, int r17, ByteBuffer r18, int r19, ByteBuffer r20, int r21, int r22) {
        I420Copy(r14, r15, r16, r17, r18, r19, r20, r21, r22, r21, r22, (r21 + 1) / 2, (r22 + 1) / 2);
    }

    public static void I420Copy(ByteBuffer r14, int r15, ByteBuffer r16, int r17, ByteBuffer r18, int r19, ByteBuffer r20, int r21, int r22, int r23, int r24) {
        I420Copy(r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, (r23 + 1) / 2, (r24 + 1) / 2);
    }

    public static void I420Copy(ByteBuffer r21, int r22, ByteBuffer r23, int r24, ByteBuffer r25, int r26, ByteBuffer r27, int r28, int r29, int r30, int r31, int r32, int r33) {
        int r2 = (r29 + 1) / 2;
        int r3 = r30 * r29;
        int r4 = r30 * r31;
        int r5 = (r32 * r2) + r4;
        int r6 = (r32 * r33) + r4;
        int r210 = (((r2 - 1) * r32) + r6) + ((r28 + 1) / 2);
        if (r27.capacity() < r210) goto L6;
        r27.limit(r3);
        r27.position(0);
        ByteBuffer r13 = r27.slice();
        r27.limit(r5);
        r27.position(r4);
        ByteBuffer r15 = r27.slice();
        r27.limit(r210);
        r27.position(r6);
        I420Copy(r21, r22, r23, r24, r25, r26, r13, r30, r15, r32, r27.slice(), r32, r28, r29);
        return;
    L6:
        Dex$$ExternalSyntheticBUOutline1.m("Expected destination buffer capacity to be at least ", r210, " was ", r27.capacity());
    }

    public static void I420Copy(ByteBuffer r1, int r2, ByteBuffer r3, int r4, ByteBuffer r5, int r6, ByteBuffer r7, int r8, ByteBuffer r9, int r10, ByteBuffer r11, int r12, int r13, int r14) {
        checkNotNull(r1, "srcY");
        checkNotNull(r3, "srcU");
        checkNotNull(r5, "srcV");
        checkNotNull(r7, "dstY");
        checkNotNull(r9, "dstU");
        checkNotNull(r11, "dstV");
        if (r13 <= 0) goto L7;
        if (r14 <= 0) goto L7;
        nativeI420Copy(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14);
        return;
    L7:
        f$$ExternalSyntheticBUOutline1.m("I420Copy: width and height should not be negative");
    }

    public static void I420Rotate(ByteBuffer r17, int r18, ByteBuffer r19, int r20, ByteBuffer r21, int r22, ByteBuffer r23, int r24, int r25, int r26) {
        checkNotNull(r17, "srcY");
        checkNotNull(r19, "srcU");
        checkNotNull(r21, "srcV");
        checkNotNull(r23, "dst");
        int r3 = r26 % 180;
        if (r3 != 0) goto L5;
        int r9 = r24;
    L6:
        if (r3 != 0) goto L8;
        int r32 = r25;
    L9:
        int r5 = (r32 + 1) / 2;
        int r11 = (r9 + 1) / 2;
        int r33 = r32 * r9;
        int r52 = r5 * r11;
        int r7 = (r52 * 2) + r33;
        if (r23.capacity() < r7) goto L13;
        r23.position(0);
        ByteBuffer r8 = r23.slice();
        r23.position(r33);
        ByteBuffer r10 = r23.slice();
        r23.position(r52 + r33);
        nativeI420Rotate(r17, r18, r19, r20, r21, r22, r8, r9, r10, r11, r23.slice(), r11, r24, r25, r26);
        return;
    L13:
        Dex$$ExternalSyntheticBUOutline1.m("Expected destination buffer capacity to be at least ", r7, " was ", r23.capacity());
        return;
    L8:
        r32 = r24;
        goto L9
    L5:
        r9 = r25;
        goto L6
    }

    public static void I420Rotate(ByteBuffer r1, int r2, ByteBuffer r3, int r4, ByteBuffer r5, int r6, ByteBuffer r7, int r8, ByteBuffer r9, int r10, ByteBuffer r11, int r12, int r13, int r14, int r15) {
        checkNotNull(r1, "srcY");
        checkNotNull(r3, "srcU");
        checkNotNull(r5, "srcV");
        checkNotNull(r7, "dstY");
        checkNotNull(r9, "dstU");
        checkNotNull(r11, "dstV");
        nativeI420Rotate(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15);
    }

    public static void I420ToNV12(ByteBuffer r11, int r12, ByteBuffer r13, int r14, ByteBuffer r15, int r16, ByteBuffer r17, int r18, int r19) {
        I420ToNV12(r11, r12, r13, r14, r15, r16, r17, r18, r19, r18, r19);
    }

    public static void I420ToNV12(ByteBuffer r17, int r18, ByteBuffer r19, int r20, ByteBuffer r21, int r22, ByteBuffer r23, int r24, int r25, int r26, int r27) {
        int r2 = (r24 + 1) / 2;
        int r3 = r26 * r25;
        int r4 = r26 * r27;
        int r1 = ((((r25 + 1) / 2) * r2) * 2) + r4;
        if (r23.capacity() < r1) goto L6;
        r23.limit(r3);
        r23.position(0);
        ByteBuffer r11 = r23.slice();
        r23.limit(r1);
        r23.position(r4);
        I420ToNV12(r17, r18, r19, r20, r21, r22, r11, r26, r23.slice(), r2 * 2, r24, r25);
        return;
    L6:
        Dex$$ExternalSyntheticBUOutline1.m("Expected destination buffer capacity to be at least ", r1, " was ", r23.capacity());
    }

    public static void I420ToNV12(ByteBuffer r1, int r2, ByteBuffer r3, int r4, ByteBuffer r5, int r6, ByteBuffer r7, int r8, ByteBuffer r9, int r10, int r11, int r12) {
        checkNotNull(r1, "srcY");
        checkNotNull(r3, "srcU");
        checkNotNull(r5, "srcV");
        checkNotNull(r7, "dstY");
        checkNotNull(r9, "dstUV");
        if (r11 <= 0) goto L7;
        if (r12 <= 0) goto L7;
        nativeI420ToNV12(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12);
        return;
    L7:
        f$$ExternalSyntheticBUOutline1.m("I420ToNV12: width and height should not be negative");
    }

    private static <T> T checkNotNull(T r1, String r2) {
        if (r1 == null) goto L5;
        return r1;
    L5:
        throw new NullPointerException(r2 + " should not be null");
    }

    public static void copyPlane(ByteBuffer r6, int r7, ByteBuffer r8, int r9, int r10, int r11) {
        nativeCopyPlane((ByteBuffer) checkNotNull(r6, "src"), r7, (ByteBuffer) checkNotNull(r8, "dst"), r9, r10, r11);
    }

    private static native void nativeABGRToI420(ByteBuffer r0, int r1, ByteBuffer r2, int r3, ByteBuffer r4, int r5, ByteBuffer r6, int r7, int r8, int r9);

    private static native void nativeCopyPlane(ByteBuffer r0, int r1, ByteBuffer r2, int r3, int r4, int r5);

    private static native void nativeI420Copy(ByteBuffer r0, int r1, ByteBuffer r2, int r3, ByteBuffer r4, int r5, ByteBuffer r6, int r7, ByteBuffer r8, int r9, ByteBuffer r10, int r11, int r12, int r13);

    private static native void nativeI420Rotate(ByteBuffer r0, int r1, ByteBuffer r2, int r3, ByteBuffer r4, int r5, ByteBuffer r6, int r7, ByteBuffer r8, int r9, ByteBuffer r10, int r11, int r12, int r13, int r14);

    private static native void nativeI420ToNV12(ByteBuffer r0, int r1, ByteBuffer r2, int r3, ByteBuffer r4, int r5, ByteBuffer r6, int r7, ByteBuffer r8, int r9, int r10, int r11);
}
