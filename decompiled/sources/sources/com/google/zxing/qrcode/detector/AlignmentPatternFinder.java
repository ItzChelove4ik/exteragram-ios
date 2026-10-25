package com.google.zxing.qrcode.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
final class AlignmentPatternFinder {
    private final int[] crossCheckStateCount;
    private final int height;
    private final BitMatrix image;
    private final float moduleSize;
    private final List<AlignmentPattern> possibleCenters;
    private final int startX;
    private final int startY;
    private final int width;

    public AlignmentPatternFinder(BitMatrix r1, int r2, int r3, int r4, int r5, float r6, ResultPointCallback r7) {
        this.image = r1;
        this.possibleCenters = new ArrayList(5);
        this.startX = r2;
        this.startY = r3;
        this.width = r4;
        this.height = r5;
        this.moduleSize = r6;
        this.crossCheckStateCount = new int[3];
    }

    private static float centerFromEnd(int[] r1, int r2) {
        return (r2 - r1[2]) - (r1[1] / 2.0f);
    }

    private float crossCheckVertical(int r10, int r11, int r12, int r13) {
        BitMatrix r0 = this.image;
        int r1 = r0.getHeight();
        int[] r2 = this.crossCheckStateCount;
        r2[0] = 0;
        r2[1] = 0;
        r2[2] = 0;
        int r6 = r10;
    L3:
        if (r6 < 0) goto L10;
        if (r0.get(r11, r6) == false) goto L10;
        int r7 = r2[1];
        if (r7 > r12) goto L10;
        r2[1] = r7 + 1;
        r6 = r6 - 1;
    L10:
        if (r6 >= 0) goto L12;
    L50:
        return Float.NaN;
    L12:
        if (r2[1] > r12) goto L50;
    L14:
        if (r6 < 0) goto L21;
        if (r0.get(r11, r6) == true) goto L21;
        int r8 = r2[0];
        if (r8 > r12) goto L21;
        r2[0] = r8 + 1;
        r6 = r6 - 1;
    L21:
        if (r2[0] <= r12) goto L23;
        return Float.NaN;
    L23:
        int r102 = r10 + 1;
    L24:
        if (r102 >= r1) goto L30;
        if (r0.get(r11, r102) == false) goto L30;
        int r62 = r2[1];
        if (r62 > r12) goto L30;
        r2[1] = r62 + 1;
        r102 = r102 + 1;
    L30:
        if (r102 == r1) goto L50;
        if (r2[1] > r12) goto L50;
    L34:
        if (r102 >= r1) goto L40;
        if (r0.get(r11, r102) == true) goto L40;
        int r63 = r2[2];
        if (r63 > r12) goto L40;
        r2[2] = r63 + 1;
        r102 = r102 + 1;
    L40:
        int r112 = r2[2];
        if (r112 <= r12) goto L44;
        return Float.NaN;
    L44:
        if ((Math.abs(((r2[0] + r2[1]) + r112) - r13) * 5) < (r13 * 2)) goto L47;
        return Float.NaN;
    L47:
        if (foundPatternCross(r2) == false) goto L50;
        return centerFromEnd(r2, r102);
    }

    private boolean foundPatternCross(int[] r5) {
        float r4 = this.moduleSize;
        float r0 = r4 / 2.0f;
        int r2 = 0;
    L4:
        if (r2 >= 3) goto L9;
        if (Math.abs(r4 - r5[r2]) >= r0) goto L7;
        r2 = r2 + 1;
        goto L4
    L7:
        return false;
    L9:
        return true;
    }

    private AlignmentPattern handlePossibleCenter(int[] r7, int r8, int r9) {
        int r1 = (r7[0] + r7[1]) + r7[2];
        float r92 = centerFromEnd(r7, r9);
        float r82 = crossCheckVertical(r8, (int) r92, r7[1] * 2, r1);
        if (Float.isNaN(r82) == true) goto L17;
        float r72 = ((r7[0] + r7[1]) + r7[2]) / 3.0f;
        Iterator<AlignmentPattern> r0 = this.possibleCenters.iterator();
    L6:
        if (r0.hasNext() == false) goto L11;
        AlignmentPattern r12 = r0.next();
        if (r12.aboutEquals(r72, r82, r92) == false) goto L6;
        return r12.combineEstimate(r82, r92, r72);
    L11:
        this.possibleCenters.add(new AlignmentPattern(r92, r82, r72));
        return null;
    L17:
        return null;
    }

    public AlignmentPattern find() {
        int r0 = this.startX;
        int r1 = this.height;
        int r2 = this.width + r0;
        int r3 = this.startY + (r1 / 2);
        int[] r4 = new int[3];
        int r6 = 0;
    L3:
        if (r6 >= r1) goto L38;
        if ((r6 & 1) != 0) goto L7;
        int r7 = (r6 + 1) / 2;
    L8:
        int r72 = r7 + r3;
        r4[0] = 0;
        r4[1] = 0;
        r4[2] = 0;
        int r10 = r0;
    L9:
        if (r10 >= r2) goto L13;
        if (this.image.get(r10, r72) == true) goto L13;
        r10 = r10 + 1;
    L13:
        int r11 = 0;
    L14:
        if (r10 >= r2) goto L32;
        if (this.image.get(r10, r72) == false) goto L27;
        if (r11 != 1) goto L19;
        r4[1] = r4[1] + 1;
    L30:
        r10 = r10 + 1;
        goto L14
    L19:
        if (r11 == 2) goto L21;
        r11 = r11 + 1;
        r4[r11] = r4[r11] + 1;
        goto L30
    L21:
        if (foundPatternCross(r4) == false) goto L25;
        AlignmentPattern r112 = handlePossibleCenter(r4, r72, r10);
        if (r112 == null) goto L25;
        return r112;
    L25:
        r4[0] = r4[2];
        r4[1] = 1;
        r4[2] = 0;
        r11 = 1;
        goto L30
    L27:
        if (r11 != 1) goto L29;
        r11 = r11 + 1;
    L29:
        r4[r11] = r4[r11] + 1;
        goto L30
    L32:
        if (foundPatternCross(r4) == false) goto L36;
        AlignmentPattern r73 = handlePossibleCenter(r4, r72, r2);
        if (r73 == null) goto L36;
        return r73;
    L36:
        r6 = r6 + 1;
        goto L3
    L7:
        r7 = -((r6 + 1) / 2);
        goto L8
    L38:
        if (this.possibleCenters.isEmpty() == true) goto L42;
        return this.possibleCenters.get(0);
    L42:
        throw NotFoundException.getNotFoundInstance();
    }
}
