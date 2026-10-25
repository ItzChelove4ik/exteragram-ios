package com.google.zxing.common;

import com.google.zxing.Binarizer;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;
import kotlin.UByte;

/* loaded from: classes5.dex */
public class GlobalHistogramBinarizer extends Binarizer {
    private static final byte[] EMPTY = null;
    private final int[] buckets;
    private byte[] luminances;

    static {
        EMPTY = new byte[0];
    }

    public GlobalHistogramBinarizer(LuminanceSource r1) {
        super(r1);
        this.luminances = EMPTY;
        this.buckets = new int[32];
    }

    private static int estimateBlackPoint(int[] r9) {
        int r0 = r9.length;
        int r1 = 0;
        int r2 = 0;
        int r3 = 0;
        int r4 = 0;
        int r5 = 0;
    L3:
        if (r2 >= r0) goto L10;
        int r6 = r9[r2];
        if (r6 <= r3) goto L7;
        r5 = r2;
        r3 = r6;
    L7:
        if (r6 <= r4) goto L9;
        r4 = r6;
    L9:
        r2 = r2 + 1;
        goto L3
    L10:
        int r22 = 0;
        int r32 = 0;
    L11:
        if (r1 >= r0) goto L16;
        int r62 = r1 - r5;
        int r7 = (r9[r1] * r62) * r62;
        if (r7 <= r32) goto L15;
        r22 = r1;
        r32 = r7;
    L15:
        r1 = r1 + 1;
        goto L11
    L16:
        if (r5 > r22) goto L20;
        int r8 = r5;
        r5 = r22;
        r22 = r8;
    L20:
        if ((r5 - r22) <= (r0 / 16)) goto L30;
        int r02 = r5 - 1;
        int r33 = -1;
        int r12 = r02;
    L22:
        if (r02 <= r22) goto L28;
        int r63 = r02 - r22;
        int r64 = ((r63 * r63) * (r5 - r02)) * (r4 - r9[r02]);
        if (r64 <= r33) goto L26;
        r12 = r02;
        r33 = r64;
    L26:
        r02 = r02 - 1;
        goto L22
    L28:
        return r12 << 3;
    L30:
        throw NotFoundException.getNotFoundInstance();
    }

    private void initArrays(int r3) {
        if (this.luminances.length >= r3) goto L5;
        this.luminances = new byte[r3];
    L5:
        int r0 = 0;
    L7:
        if (r0 >= 32) goto L9;
        this.buckets[r0] = 0;
        r0 = r0 + 1;
        goto L7
    }

    @Override // com.google.zxing.Binarizer
    public BitMatrix getBlackMatrix() {
        LuminanceSource r0 = getLuminanceSource();
        int r1 = r0.getWidth();
        int r2 = r0.getHeight();
        BitMatrix r3 = new BitMatrix(r1, r2);
        initArrays(r1);
        int[] r4 = this.buckets;
        int r6 = 1;
    L4:
        if (r6 >= 5) goto L9;
        byte[] r8 = r0.getRow((r2 * r6) / 5, this.luminances);
        int r9 = (r1 * 4) / 5;
        int r7 = r1 / 5;
    L6:
        if (r7 >= r9) goto L8;
        int r10 = (r8[r7] & UByte.MAX_VALUE) >> 3;
        r4[r10] = r4[r10] + 1;
        r7 = r7 + 1;
        goto L6
    L8:
        r6 = r6 + 1;
        goto L4
    L9:
        int r12 = estimateBlackPoint(r4);
        byte[] r02 = r0.getMatrix();
        int r5 = 0;
    L10:
        if (r5 >= r2) goto L18;
        int r62 = r5 * r1;
        int r72 = 0;
    L12:
        if (r72 >= r1) goto L17;
        if ((r02[r62 + r72] & UByte.MAX_VALUE) >= r12) goto L16;
        r3.set(r72, r5);
    L16:
        r72 = r72 + 1;
        goto L12
    L17:
        r5 = r5 + 1;
        goto L10
    L18:
        return r3;
    }
}
