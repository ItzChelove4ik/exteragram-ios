package org.mvel2.asm;

import c.f$$ExternalSyntheticBUOutline1;
import okio.Buffer$$ExternalSyntheticBUOutline2;

/* loaded from: classes5.dex */
public class ByteVector {
    byte[] data;
    int length;

    public ByteVector() {
        this.data = new byte[64];
    }

    public ByteVector(int r1) {
        this.data = new byte[r1];
    }

    public ByteVector(byte[] r1) {
        this.data = r1;
        this.length = r1.length;
    }

    private void enlarge(int r4) {
        int r0 = this.length;
        byte[] r1 = this.data;
        if (r0 > r1.length) goto L10;
        int r2 = r1.length * 2;
        int r42 = r4 + r0;
        if (r2 > r42) goto L8;
        r2 = r42;
    L8:
        byte[] r43 = new byte[r2];
        System.arraycopy(r1, 0, r43, 0, r0);
        this.data = r43;
        return;
    L10:
        Buffer$$ExternalSyntheticBUOutline2.m("Internal error");
    }

    public final ByteVector encodeUtf8(String r10, int r11, int r12) {
        int r0 = r10.length();
        int r1 = r11;
        int r2 = r1;
    L4:
        if (r1 >= r0) goto L13;
        char r6 = r10.charAt(r1);
        if (r6 < 1) goto L9;
        if (r6 > 127) goto L9;
        r2 = r2 + 1;
    L12:
        r1 = r1 + 1;
    L9:
        if (r6 > 2047) goto L11;
        r2 = r2 + 2;
        goto L12
    L11:
        r2 = r2 + 3;
        goto L12
    L13:
        if (r2 > r12) goto L33;
        int r122 = this.length;
        int r13 = r122 - r11;
        int r62 = r13 - 2;
        if (r62 < 0) goto L18;
        byte[] r7 = this.data;
        r7[r62] = (byte) (r2 >>> 8);
        r7[r13 - 1] = (byte) r2;
    L18:
        if (((r122 + r2) - r11) <= this.data.length) goto L20;
        enlarge(r2 - r11);
    L20:
        int r123 = this.length;
    L21:
        if (r11 >= r0) goto L31;
        char r14 = r10.charAt(r11);
        if (r14 < 1) goto L26;
        if (r14 > 127) goto L26;
        this.data[r123] = (byte) r14;
        r123 = r123 + 1;
    L30:
        r11 = r11 + 1;
    L26:
        byte[] r22 = this.data;
        if (r14 > 2047) goto L29;
        int r63 = r123 + 1;
        r22[r123] = (byte) (((r14 >> 6) & 31) | 192);
        r123 = r123 + 2;
        r22[r63] = (byte) ((r14 & '?') | 128);
        goto L30
    L29:
        r22[r123] = (byte) (((r14 >> '\f') & 15) | com.android.dx.io.Opcodes.SHL_INT_LIT8);
        int r72 = r123 + 2;
        r22[r123 + 1] = (byte) (((r14 >> 6) & 63) | 128);
        r123 = r123 + 3;
        r22[r72] = (byte) ((r14 & '?') | 128);
        goto L30
    L31:
        this.length = r123;
        return this;
    L33:
        f$$ExternalSyntheticBUOutline1.m("UTF8 string too large");
        return null;
    }

    public final ByteVector put11(int r5, int r6) {
        int r0 = this.length;
        if ((r0 + 2) <= this.data.length) goto L5;
        enlarge(2);
    L5:
        byte[] r1 = this.data;
        int r2 = r0 + 1;
        r1[r0] = (byte) r5;
        r1[r2] = (byte) r6;
        this.length = r0 + 2;
        return this;
    }

    public final ByteVector put112(int r5, int r6, int r7) {
        int r0 = this.length;
        if ((r0 + 4) <= this.data.length) goto L5;
        enlarge(4);
    L5:
        byte[] r1 = this.data;
        r1[r0] = (byte) r5;
        r1[r0 + 1] = (byte) r6;
        int r62 = r0 + 3;
        r1[r0 + 2] = (byte) (r7 >>> 8);
        r1[r62] = (byte) r7;
        this.length = r0 + 4;
        return this;
    }

    public final ByteVector put12(int r6, int r7) {
        int r0 = this.length;
        if ((r0 + 3) <= this.data.length) goto L5;
        enlarge(3);
    L5:
        byte[] r1 = this.data;
        r1[r0] = (byte) r6;
        int r62 = r0 + 2;
        r1[r0 + 1] = (byte) (r7 >>> 8);
        r1[r62] = (byte) r7;
        this.length = r0 + 3;
        return this;
    }

    public final ByteVector put122(int r6, int r7, int r8) {
        int r0 = this.length;
        if ((r0 + 5) <= this.data.length) goto L5;
        enlarge(5);
    L5:
        byte[] r1 = this.data;
        r1[r0] = (byte) r6;
        r1[r0 + 1] = (byte) (r7 >>> 8);
        r1[r0 + 2] = (byte) r7;
        int r62 = r0 + 4;
        r1[r0 + 3] = (byte) (r8 >>> 8);
        r1[r62] = (byte) r8;
        this.length = r0 + 5;
        return this;
    }

    public ByteVector putByte(int r4) {
        int r0 = this.length;
        int r1 = r0 + 1;
        if (r1 <= this.data.length) goto L5;
        enlarge(1);
    L5:
        this.data[r0] = (byte) r4;
        this.length = r1;
        return this;
    }

    public ByteVector putByteArray(byte[] r3, int r4, int r5) {
        if ((this.length + r5) <= this.data.length) goto L5;
        enlarge(r5);
    L5:
        if (r3 == null) goto L7;
        System.arraycopy(r3, r4, this.data, this.length, r5);
    L7:
        this.length += r5;
        return this;
    }

    public ByteVector putInt(int r7) {
        int r0 = this.length;
        if ((r0 + 4) <= this.data.length) goto L5;
        enlarge(4);
    L5:
        byte[] r1 = this.data;
        r1[r0] = (byte) (r7 >>> 24);
        r1[r0 + 1] = (byte) (r7 >>> 16);
        int r2 = r0 + 3;
        r1[r0 + 2] = (byte) (r7 >>> 8);
        r1[r2] = (byte) r7;
        this.length = r0 + 4;
        return this;
    }

    public ByteVector putLong(long r8) {
        int r0 = this.length;
        if ((r0 + 8) <= this.data.length) goto L5;
        enlarge(8);
    L5:
        byte[] r1 = this.data;
        int r2 = (int) (r8 >>> 32);
        r1[r0] = (byte) (r2 >>> 24);
        r1[r0 + 1] = (byte) (r2 >>> 16);
        r1[r0 + 2] = (byte) (r2 >>> 8);
        r1[r0 + 3] = (byte) r2;
        int r82 = (int) r8;
        r1[r0 + 4] = (byte) (r82 >>> 24);
        r1[r0 + 5] = (byte) (r82 >>> 16);
        int r9 = r0 + 7;
        r1[r0 + 6] = (byte) (r82 >>> 8);
        r1[r9] = (byte) r82;
        this.length = r0 + 8;
        return this;
    }

    public ByteVector putShort(int r6) {
        int r0 = this.length;
        if ((r0 + 2) <= this.data.length) goto L5;
        enlarge(2);
    L5:
        byte[] r1 = this.data;
        int r2 = r0 + 1;
        r1[r0] = (byte) (r6 >>> 8);
        r1[r2] = (byte) r6;
        this.length = r0 + 2;
        return this;
    }

    public ByteVector putUTF8(String r8) {
        int r0 = r8.length();
        if (r0 > 65535) goto L18;
        int r2 = this.length;
        if (((r2 + 2) + r0) <= this.data.length) goto L7;
        enlarge(r0 + 2);
    L7:
        byte[] r3 = this.data;
        int r4 = r2 + 1;
        r3[r2] = (byte) (r0 >>> 8);
        int r22 = r2 + 2;
        r3[r4] = (byte) r0;
        int r42 = 0;
    L8:
        if (r42 >= r0) goto L16;
        char r5 = r8.charAt(r42);
        if (r5 < 1) goto L14;
        if (r5 > 127) goto L14;
        r3[r22] = (byte) r5;
        r42 = r42 + 1;
        r22 = r22 + 1;
    L14:
        this.length = r22;
        return encodeUtf8(r8, r42, 65535);
    L16:
        this.length = r22;
        return this;
    L18:
        f$$ExternalSyntheticBUOutline1.m("UTF8 string too large");
        return null;
    }

    public int size() {
        return this.length;
    }
}
