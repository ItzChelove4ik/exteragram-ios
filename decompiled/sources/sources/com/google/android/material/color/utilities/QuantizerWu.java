package com.google.android.material.color.utilities;

import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public final class QuantizerWu implements Quantizer {
    private static final int INDEX_BITS = 5;
    private static final int INDEX_COUNT = 33;
    private static final int TOTAL_SIZE = 35937;
    Box[] cubes;
    double[] moments;
    int[] momentsB;
    int[] momentsG;
    int[] momentsR;
    int[] weights;

    /* renamed from: com.google.android.material.color.utilities.QuantizerWu$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Box {
        int b0;
        int b1;
        int g0;
        int g1;
        int r0;
        int r1;
        int vol;

        private Box() {
            this.r0 = 0;
            this.r1 = 0;
            this.g0 = 0;
            this.g1 = 0;
            this.b0 = 0;
            this.b1 = 0;
            this.vol = 0;
        }

        public /* synthetic */ Box(AnonymousClass1 r1) {
            this();
        }
    }

    public static final class CreateBoxesResult {
        int resultCount;

        public CreateBoxesResult(int r1, int r2) {
            this.resultCount = r2;
        }
    }

    public enum Direction extends Enum<Direction> {
        private static final /* synthetic */ Direction[] $VALUES = null;
        public static final Direction BLUE = null;
        public static final Direction GREEN = null;
        public static final Direction RED = null;

        private static /* synthetic */ Direction[] $values() {
            return new Direction[]{RED, GREEN, BLUE};
        }

        static {
            RED = new Direction("RED", 0);
            GREEN = new Direction("GREEN", 1);
            BLUE = new Direction("BLUE", 2);
            $VALUES = $values();
        }

        Direction(String r1, int r2) {
        }

        public static Direction valueOf(String r1) {
            return (Direction) Enum.valueOf(Direction.class, r1);
        }

        public static Direction[] values() {
            return (Direction[]) $VALUES.clone();
        }
    }

    public static final class MaximizeResult {
        int cutLocation;
        double maximum;

        public MaximizeResult(int r1, double r2) {
            this.cutLocation = r1;
            this.maximum = r2;
        }
    }

    public QuantizerWu() {
    }

    public static int bottom(Box r3, Direction r4, int[] r5) {
        int r0 = r4.ordinal();
        if (r0 != 0) goto L5;
        int r42 = ((-r5[getIndex(r3.r0, r3.g1, r3.b1)]) + r5[getIndex(r3.r0, r3.g1, r3.b0)]) + r5[getIndex(r3.r0, r3.g0, r3.b1)];
        int r32 = r5[getIndex(r3.r0, r3.g0, r3.b0)];
    L10:
        return r42 - r32;
    L5:
        if (r0 != 1) goto L7;
        r42 = ((-r5[getIndex(r3.r1, r3.g0, r3.b1)]) + r5[getIndex(r3.r1, r3.g0, r3.b0)]) + r5[getIndex(r3.r0, r3.g0, r3.b1)];
        r32 = r5[getIndex(r3.r0, r3.g0, r3.b0)];
        goto L10
    L7:
        if (r0 != 2) goto L11;
        r42 = ((-r5[getIndex(r3.r1, r3.g1, r3.b0)]) + r5[getIndex(r3.r1, r3.g0, r3.b0)]) + r5[getIndex(r3.r0, r3.g1, r3.b0)];
        r32 = r5[getIndex(r3.r0, r3.g0, r3.b0)];
        goto L10
    L11:
        Native$$ExternalSyntheticBUOutline5.m("unexpected direction ", r4);
        return 0;
    }

    public static int getIndex(int r2, int r3, int r4) {
        return (((((r2 << 10) + (r2 << 6)) + r2) + (r3 << 5)) + r3) + r4;
    }

    public static int top(Box r2, Direction r3, int r4, int[] r5) {
        int r0 = r3.ordinal();
        if (r0 != 0) goto L5;
        int r32 = (r5[getIndex(r4, r2.g1, r2.b1)] - r5[getIndex(r4, r2.g1, r2.b0)]) - r5[getIndex(r4, r2.g0, r2.b1)];
        int r22 = r5[getIndex(r4, r2.g0, r2.b0)];
    L10:
        return r32 + r22;
    L5:
        if (r0 != 1) goto L7;
        r32 = (r5[getIndex(r2.r1, r4, r2.b1)] - r5[getIndex(r2.r1, r4, r2.b0)]) - r5[getIndex(r2.r0, r4, r2.b1)];
        r22 = r5[getIndex(r2.r0, r4, r2.b0)];
        goto L10
    L7:
        if (r0 != 2) goto L11;
        r32 = (r5[getIndex(r2.r1, r2.g1, r4)] - r5[getIndex(r2.r1, r2.g0, r4)]) - r5[getIndex(r2.r0, r2.g1, r4)];
        r22 = r5[getIndex(r2.r0, r2.g0, r4)];
        goto L10
    L11:
        Native$$ExternalSyntheticBUOutline5.m("unexpected direction ", r3);
        return 0;
    }

    public static int volume(Box r4, int[] r5) {
        return ((((((r5[getIndex(r4.r1, r4.g1, r4.b1)] - r5[getIndex(r4.r1, r4.g1, r4.b0)]) - r5[getIndex(r4.r1, r4.g0, r4.b1)]) + r5[getIndex(r4.r1, r4.g0, r4.b0)]) - r5[getIndex(r4.r0, r4.g1, r4.b1)]) + r5[getIndex(r4.r0, r4.g1, r4.b0)]) + r5[getIndex(r4.r0, r4.g0, r4.b1)]) - r5[getIndex(r4.r0, r4.g0, r4.b0)];
    }

    public void constructHistogram(Map<Integer, Integer> r9) {
        this.weights = new int[TOTAL_SIZE];
        this.momentsR = new int[TOTAL_SIZE];
        this.momentsG = new int[TOTAL_SIZE];
        this.momentsB = new int[TOTAL_SIZE];
        this.moments = new double[TOTAL_SIZE];
        Iterator<Map.Entry<Integer, Integer>> r92 = r9.entrySet().iterator();
    L4:
        if (r92.hasNext() == false) goto L6;
        Map.Entry<Integer, Integer> r0 = r92.next();
        int r1 = r0.getKey().intValue();
        int r02 = r0.getValue().intValue();
        int r2 = ColorUtils.redFromArgb(r1);
        int r3 = ColorUtils.greenFromArgb(r1);
        int r12 = ColorUtils.blueFromArgb(r1);
        int r4 = getIndex((r2 >> 3) + 1, (r3 >> 3) + 1, (r12 >> 3) + 1);
        int[] r5 = this.weights;
        r5[r4] = r5[r4] + r02;
        int[] r52 = this.momentsR;
        r52[r4] = r52[r4] + (r2 * r02);
        int[] r53 = this.momentsG;
        r53[r4] = r53[r4] + (r3 * r02);
        int[] r54 = this.momentsB;
        r54[r4] = r54[r4] + (r12 * r02);
        double[] r55 = this.moments;
        r55[r4] = r55[r4] + (r02 * (((r2 * r2) + (r3 * r3)) + (r12 * r12)));
        goto L4
    }

    public CreateBoxesResult createBoxes(int r14) {
        this.cubes = new Box[r14];
        int r1 = 0;
    L3:
        if (r1 >= r14) goto L5;
        this.cubes[r1] = new Box(null);
        r1 = r1 + 1;
        goto L3
    L5:
        double[] r12 = new double[r14];
        Box r2 = this.cubes[0];
        r2.r1 = 32;
        r2.g1 = 32;
        r2.b1 = 32;
        int r4 = 0;
        int r3 = 1;
    L6:
        if (r3 >= r14) goto L29;
        Box[] r5 = this.cubes;
        if (cut(r5[r4], r5[r3]).booleanValue() == false) goto L18;
        Box r52 = this.cubes[r4];
        if (r52.vol <= 1) goto L12;
        double r8 = variance(r52);
    L13:
        r12[r4] = r8;
        Box r42 = this.cubes[r3];
        if (r42.vol <= 1) goto L16;
        double r43 = variance(r42);
    L17:
        r12[r3] = r43;
    L19:
        double r44 = r12[0];
        int r82 = 0;
        int r9 = 1;
    L20:
        if (r9 > r3) goto L26;
        double r10 = r12[r9];
        if (r10 <= r44) goto L24;
        r82 = r9;
        r44 = r10;
    L24:
        r9 = r9 + 1;
        goto L20
    L26:
        if (r44 <= 0.0d) goto L27;
        r3 = r3 + 1;
        r4 = r82;
        goto L6
    L27:
        int r32 = r3 + 1;
    L31:
        return new CreateBoxesResult(r14, r32);
    L16:
        r43 = 0.0d;
        goto L17
    L12:
        r8 = 0.0d;
        goto L13
    L18:
        r12[r4] = 0.0d;
        r3 = r3 - 1;
        goto L19
    L29:
        r32 = r14;
        goto L31
    }

    public void createMoments() {
        int r2 = 1;
    L3:
        int r3 = 33;
        if (r2 >= 33) goto L12;
        int[] r4 = new int[33];
        int[] r5 = new int[33];
        int[] r6 = new int[33];
        int[] r7 = new int[33];
        double[] r8 = new double[33];
        int r9 = 1;
    L6:
        if (r9 >= r3) goto L11;
        int r10 = 0;
        int r13 = 0;
        double r14 = 0.0d;
        int r1 = 1;
        int r11 = 0;
        int r12 = 0;
    L8:
        if (r1 >= r3) goto L10;
        int r16 = getIndex(r2, r9, r1);
        r10 = r10 + this.weights[r16];
        r11 = r11 + this.momentsR[r16];
        r12 = r12 + this.momentsG[r16];
        r13 = r13 + this.momentsB[r16];
        r14 = r14 + this.moments[r16];
        r4[r1] = r4[r1] + r10;
        r5[r1] = r5[r1] + r11;
        r6[r1] = r6[r1] + r12;
        r7[r1] = r7[r1] + r13;
        r8[r1] = r8[r1] + r14;
        int r32 = getIndex(r2 - 1, r9, r1);
        int r17 = r1;
        int[] r15 = this.weights;
        r15[r16] = r15[r32] + r4[r17];
        int[] r18 = this.momentsR;
        r18[r16] = r18[r32] + r5[r17];
        int[] r19 = this.momentsG;
        r19[r16] = r19[r32] + r6[r17];
        int[] r110 = this.momentsB;
        r110[r16] = r110[r32] + r7[r17];
        double[] r111 = this.moments;
        r111[r16] = r111[r32] + r8[r17];
        r1 = r17 + 1;
        r3 = 33;
        goto L8
    L10:
        r9 = r9 + 1;
        r3 = 33;
        goto L6
    L11:
        r2 = r2 + 1;
        goto L3
    }

    public List<Integer> createResult(int r8) {
        ArrayList r0 = new ArrayList();
        int r1 = 0;
    L3:
        if (r1 >= r8) goto L8;
        Box r2 = this.cubes[r1];
        int r3 = volume(r2, this.weights);
        if (r3 <= 0) goto L7;
        int r4 = volume(r2, this.momentsR) / r3;
        int r5 = volume(r2, this.momentsG) / r3;
        int r22 = volume(r2, this.momentsB) / r3;
        int r23 = r22 & 255;
        r0.add(Integer.valueOf(r23 | ((((r4 & 255) << 16) | (-16777216)) | ((r5 & 255) << 8))));
    L7:
        r1 = r1 + 1;
        goto L3
    L8:
        return r0;
    }

    public Boolean cut(Box r14, Box r15) {
        int r6 = volume(r14, this.momentsR);
        int r7 = volume(r14, this.momentsG);
        int r8 = volume(r14, this.momentsB);
        int r9 = volume(r14, this.weights);
        Direction r3 = Direction.RED;
        MaximizeResult r13 = maximize(r14, r3, r14.r0 + 1, r14.r1, r6, r7, r8, r9);
        Direction r32 = Direction.GREEN;
        MaximizeResult r0 = maximize(r14, r32, r14.g0 + 1, r14.g1, r6, r7, r8, r9);
        Direction r33 = Direction.BLUE;
        MaximizeResult r1 = maximize(r14, r33, r14.b0 + 1, r14.b1, r6, r7, r8, r9);
        double r4 = r13.maximum;
        double r62 = r0.maximum;
        double r82 = r1.maximum;
        if (r4 < r62) goto L12;
        if (r4 < r82) goto L12;
        if (r13.cutLocation < 0) goto L9;
        r33 = r3;
    L16:
        r15.r1 = r14.r1;
        r15.g1 = r14.g1;
        r15.b1 = r14.b1;
        int r142 = r33.ordinal();
        if (r142 == 0) goto L24;
        if (r142 != 1) goto L20;
        int r132 = r0.cutLocation;
        r14.g1 = r132;
        r15.r0 = r14.r0;
        r15.g0 = r132;
        r15.b0 = r14.b0;
    L25:
        r14.vol = ((r14.r1 - r14.r0) * (r14.g1 - r14.g0)) * (r14.b1 - r14.b0);
        r15.vol = ((r15.r1 - r15.r0) * (r15.g1 - r15.g0)) * (r15.b1 - r15.b0);
        return Boolean.TRUE;
    L20:
        if (r142 != 2) goto L25;
        int r133 = r1.cutLocation;
        r14.b1 = r133;
        r15.r0 = r14.r0;
        r15.g0 = r14.g0;
        r15.b0 = r133;
        goto L25
    L24:
        int r134 = r13.cutLocation;
        r14.r1 = r134;
        r15.r0 = r134;
        r15.g0 = r14.g0;
        r15.b0 = r14.b0;
        goto L25
    L9:
        return Boolean.FALSE;
    L12:
        if (r62 < r4) goto L16;
        if (r62 < r82) goto L16;
        r33 = r32;
        goto L16
    }

    public MaximizeResult maximize(Box r19, Direction r20, int r21, int r22, int r23, int r24, int r25, int r26) {
        QuantizerWu r0 = this;
        Box r1 = r19;
        int r3 = bottom(r1, r20, r0.momentsR);
        int r4 = bottom(r1, r20, r0.momentsG);
        int r5 = bottom(r1, r20, r0.momentsB);
        int r6 = bottom(r1, r20, r0.weights);
        int r10 = -1;
        double r8 = 0.0d;
        int r7 = r21;
    L3:
        if (r7 >= r22) goto L15;
        int r12 = top(r1, r20, r7, r0.momentsR) + r3;
        int r13 = top(r1, r20, r7, r0.momentsG) + r4;
        int r14 = top(r1, r20, r7, r0.momentsB) + r5;
        int r15 = top(r1, r20, r7, r0.weights) + r6;
        if (r15 == 0) goto L13;
        double r02 = (((r12 * r12) + (r13 * r13)) + (r14 * r14)) / r15;
        int r122 = r23 - r12;
        int r132 = r24 - r13;
        int r142 = r25 - r14;
        int r152 = r26 - r15;
        if (r152 == 0) goto L13;
        double r03 = r02 + ((((r122 * r122) + (r132 * r132)) + (r142 * r142)) / r152);
        if (r03 <= r8) goto L13;
        r8 = r03;
        r10 = r7;
    L13:
        r7 = r7 + 1;
        r0 = this;
        r1 = r19;
        goto L3
    L15:
        return new MaximizeResult(r10, r8);
    }

    @Override // com.google.android.material.color.utilities.Quantizer
    public QuantizerResult quantize(int[] r2, int r3) {
        constructHistogram(new QuantizerMap().quantize(r2, r3).colorToCount);
        createMoments();
        List<Integer> r1 = createResult(createBoxes(r3).resultCount);
        LinkedHashMap r22 = new LinkedHashMap();
        Iterator<Integer> r12 = r1.iterator();
    L4:
        if (r12.hasNext() == false) goto L7;
        Integer r32 = r12.next();
        r32.intValue();
        r22.put(r32, 0);
        goto L4
    L7:
        return new QuantizerResult(r22);
    }

    public double variance(Box r10) {
        int r0 = volume(r10, this.momentsR);
        int r1 = volume(r10, this.momentsG);
        int r2 = volume(r10, this.momentsB);
        return (((((((this.moments[getIndex(r10.r1, r10.g1, r10.b1)] - this.moments[getIndex(r10.r1, r10.g1, r10.b0)]) - this.moments[getIndex(r10.r1, r10.g0, r10.b1)]) + this.moments[getIndex(r10.r1, r10.g0, r10.b0)]) - this.moments[getIndex(r10.r0, r10.g1, r10.b1)]) + this.moments[getIndex(r10.r0, r10.g1, r10.b0)]) + this.moments[getIndex(r10.r0, r10.g0, r10.b1)]) - this.moments[getIndex(r10.r0, r10.g0, r10.b0)]) - ((((r0 * r0) + (r1 * r1)) + (r2 * r2)) / volume(r10, this.weights));
    }
}
