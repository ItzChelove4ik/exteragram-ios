package com.google.android.material.color.utilities;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/* loaded from: classes5.dex */
public final class QuantizerWsmeans {
    private static final int MAX_ITERATIONS = 10;
    private static final double MIN_MOVEMENT_DISTANCE = 3.0d;

    public static final class Distance implements Comparable<Distance> {
        double distance;
        int index;

        public Distance() {
            this.index = -1;
            this.distance = -1.0d;
        }

        /* renamed from: compareTo, reason: avoid collision after fix types in other method */
        public int compareTo2(Distance r3) {
            return Double.valueOf(this.distance).compareTo(Double.valueOf(r3.distance));
        }

        @Override // java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(Distance r1) {
            return compareTo2(r1);
        }
    }

    private QuantizerWsmeans() {
    }

    public static Map<Integer, Integer> quantize(int[] r25, int[] r26, int r27) {
        Random r2 = new Random(272008);
        LinkedHashMap r3 = new LinkedHashMap();
        double[][] r4 = new double[r25.length][];
        int[] r5 = new int[r25.length];
        PointProviderLab r6 = new PointProviderLab();
        int r8 = 0;
        int r9 = 0;
    L3:
        boolean r11 = true;
        if (r8 >= r25.length) goto L10;
        int r10 = r25[r8];
        Integer r12 = (Integer) r3.get(Integer.valueOf(r10));
        if (r12 != null) goto L8;
        r4[r9] = r6.fromInt(r10);
        r5[r9] = r10;
        r9 = r9 + 1;
        r3.put(Integer.valueOf(r10), 1);
    L9:
        r8 = r8 + 1;
        goto L3
    L8:
        r3.put(Integer.valueOf(r10), Integer.valueOf(r12.intValue() + 1));
        goto L9
    L10:
        int[] r0 = new int[r9];
        int r82 = 0;
    L11:
        if (r82 >= r9) goto L13;
        r0[r82] = ((Integer) r3.get(Integer.valueOf(r5[r82]))).intValue();
        r82 = r82 + 1;
        goto L11
    L13:
        int r32 = Math.min(r27, r9);
        if (r26.length == 0) goto L16;
        r32 = Math.min(r32, r26.length);
    L16:
        double[][] r52 = new double[r32][];
        int r83 = 0;
        int r102 = 0;
    L18:
        if (r83 >= r26.length) goto L20;
        r52[r83] = r6.fromInt(r26[r83]);
        r102 = r102 + 1;
        r83 = r83 + 1;
        goto L18
    L20:
        int r1 = r32 - r102;
        if (r1 <= 0) goto L25;
        int r84 = 0;
    L23:
        if (r84 >= r1) goto L25;
        r84 = r84 + 1;
    L25:
        int[] r13 = new int[r9];
        int r85 = 0;
    L26:
        if (r85 >= r9) goto L28;
        r13[r85] = r2.nextInt(r32);
        r85 = r85 + 1;
        goto L26
    L28:
        int[][] r22 = new int[r32][];
        int r86 = 0;
    L29:
        if (r86 >= r32) goto L31;
        r22[r86] = new int[r32];
        r86 = r86 + 1;
        goto L29
    L31:
        Distance[][] r87 = new Distance[r32][];
        int r103 = 0;
    L32:
        if (r103 >= r32) goto L37;
        r87[r103] = new Distance[r32];
        int r122 = 0;
    L34:
        if (r122 >= r32) goto L36;
        r87[r103][r122] = new Distance();
        r122 = r122 + 1;
        goto L34
    L36:
        r103 = r103 + 1;
        goto L32
    L37:
        int[] r104 = new int[r32];
        int r123 = 0;
    L39:
        if (r123 >= 10) goto L82;
        int r132 = 0;
    L41:
        if (r132 >= r32) goto L49;
        int r14 = r132 + 1;
        int r15 = r14;
    L43:
        if (r15 >= r32) goto L45;
        int[] r252 = r0;
        double r02 = r6.distance(r52[r132], r52[r15]);
        Distance r7 = r87[r15][r132];
        r7.distance = r02;
        r7.index = r132;
        Distance r72 = r87[r132][r15];
        r72.distance = r02;
        r72.index = r15;
        r15 = r15 + 1;
        r0 = r252;
        r13 = r13;
        r11 = r11;
        goto L43
    L45:
        int[] r253 = r0;
        int[] r262 = r13;
        boolean r16 = r11;
        Arrays.sort(r87[r132]);
        int r03 = 0;
    L46:
        if (r03 >= r32) goto L48;
        r22[r132][r03] = r87[r132][r03].index;
        r03 = r03 + 1;
        goto L46
    L48:
        r0 = r253;
        r13 = r262;
        r132 = r14;
        r11 = r16;
        goto L41
    L49:
        int[] r254 = r0;
        int[] r263 = r13;
        boolean r162 = r11;
        int r04 = 0;
        int r17 = 0;
    L50:
        if (r04 >= r9) goto L67;
        double[] r73 = r4[r04];
        int r112 = r263[r04];
        double r133 = r6.distance(r73, r52[r112]);
        int r18 = r04;
        double r19 = r133;
        int r05 = -1;
        int r152 = 0;
    L52:
        if (r152 >= r32) goto L60;
        int r222 = r17;
        int[][] r21 = r22;
        if (r87[r112][r152].distance >= (4.0d * r133)) goto L59;
        double r110 = r6.distance(r73, r52[r152]);
        if (r110 >= r19) goto L59;
        r19 = r110;
        r05 = r152;
    L59:
        r152 = r152 + 1;
        r22 = r21;
        r17 = r222;
        goto L52
    L60:
        int r223 = r17;
        int[][] r212 = r22;
        if (r05 != (-1)) goto L63;
    L65:
        r17 = r223;
    L66:
        r04 = r18 + 1;
        r22 = r212;
        goto L50
    L63:
        if (Math.abs(Math.sqrt(r19) - Math.sqrt(r133)) <= 3.0d) goto L65;
        r17 = r223 + 1;
        r263[r18] = r05;
        goto L66
    L67:
        int[][] r213 = r22;
        if (r17 != 0) goto L71;
        if (r123 != 0) goto L82;
    L71:
        double[] r06 = new double[r32];
        double[] r111 = new double[r32];
        double[] r23 = new double[r32];
        boolean r74 = false;
        Arrays.fill(r104, 0);
        int r113 = 0;
    L72:
        if (r113 >= r9) goto L74;
        int r142 = r263[r113];
        double[] r153 = r4[r113];
        boolean r172 = r74;
        int r75 = r254[r113];
        r104[r142] = r104[r142] + r75;
        double r134 = r75;
        r06[r142] = r06[r142] + (r153[r172 ? 1 : 0] * r134);
        r111[r142] = r111[r142] + (r153[r162 ? 1 : 0] * r134);
        r23[r142] = r23[r142] + (r153[2] * r134);
        r113 = r113 + 1;
        r74 = false;
        goto L72
    L74:
        int r76 = 0;
    L75:
        if (r76 >= r32) goto L81;
        int r114 = r104[r76];
        if (r114 != 0) goto L79;
        r52[r76] = new double[]{0.0d, 0.0d, 0.0d};
        double[] r154 = r06;
        double[] r182 = r111;
    L80:
        r76 = r76 + 1;
        r06 = r154;
        r111 = r182;
        goto L75
    L79:
        double r135 = r06[r76];
        r154 = r06;
        r182 = r111;
        double r07 = r114;
        double r136 = r135 / r07;
        double r192 = r182[r76] / r07;
        double r224 = r23[r76] / r07;
        double[] r08 = r52[r76];
        r08[0] = r136;
        r08[r162 ? 1 : 0] = r192;
        r08[2] = r224;
        goto L80
    L81:
        r123 = r123 + 1;
        r0 = r254;
        r13 = r263;
        r11 = r162 ? 1 : 0;
        r22 = r213;
    L82:
        LinkedHashMap r09 = new LinkedHashMap();
        int r77 = 0;
    L83:
        if (r77 >= r32) goto L92;
        int r115 = r104[r77];
        if (r115 == 0) goto L91;
        int r24 = r6.toInt(r52[r77]);
        if (r09.containsKey(Integer.valueOf(r24)) == true) goto L91;
        r09.put(Integer.valueOf(r24), Integer.valueOf(r115));
    L91:
        r77 = r77 + 1;
        goto L83
    L92:
        return r09;
    }
}
