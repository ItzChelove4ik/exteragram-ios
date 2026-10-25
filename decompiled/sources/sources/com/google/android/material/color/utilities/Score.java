package com.google.android.material.color.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public final class Score {
    private static final int BLUE_500 = -12417548;
    private static final double CUTOFF_CHROMA = 5.0d;
    private static final double CUTOFF_EXCITED_PROPORTION = 0.01d;
    private static final int MAX_COLOR_COUNT = 4;
    private static final double TARGET_CHROMA = 48.0d;
    private static final double WEIGHT_CHROMA_ABOVE = 0.3d;
    private static final double WEIGHT_CHROMA_BELOW = 0.1d;
    private static final double WEIGHT_PROPORTION = 0.7d;

    public static class ScoredComparator implements Comparator<ScoredHCT> {
        public ScoredComparator() {
        }

        /* renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(ScoredHCT r3, ScoredHCT r4) {
            return Double.compare(r4.score, r3.score);
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(ScoredHCT r1, ScoredHCT r2) {
            return compare2(r1, r2);
        }
    }

    public static class ScoredHCT {
        public final Hct hct;
        public final double score;

        public ScoredHCT(Hct r1, double r2) {
            this.hct = r1;
            this.score = r2;
        }
    }

    private Score() {
    }

    public static List<Integer> score(Map<Integer, Integer> r3) {
        return score(r3, 4, BLUE_500, true);
    }

    public static List<Integer> score(Map<Integer, Integer> r2, int r3) {
        return score(r2, r3, BLUE_500, true);
    }

    public static List<Integer> score(Map<Integer, Integer> r1, int r2, int r3) {
        return score(r1, r2, r3, true);
    }

    public static List<Integer> score(Map<Integer, Integer> r16, int r17, int r18, boolean r19) {
        ArrayList r1 = new ArrayList();
        int[] r3 = new int[360];
        Iterator<Map.Entry<Integer, Integer>> r4 = r16.entrySet().iterator();
        double r5 = 0.0d;
    L4:
        if (r4.hasNext() == false) goto L6;
        Map.Entry<Integer, Integer> r7 = r4.next();
        Hct r8 = Hct.fromInt(r7.getKey().intValue());
        r1.add(r8);
        int r82 = (int) Math.floor(r8.getHue());
        int r72 = r7.getValue().intValue();
        r3[r82] = r3[r82] + r72;
        r5 = r5 + r72;
        goto L4
    L6:
        double[] r42 = new double[360];
        int r73 = 0;
        int r83 = 0;
    L7:
        if (r83 >= 360) goto L13;
        double r9 = r3[r83] / r5;
        int r11 = r83 - 14;
    L10:
        if (r11 >= (r83 + 16)) goto L12;
        int r12 = MathUtils.sanitizeDegreesInt(r11);
        r42[r12] = r42[r12] + r9;
        r11 = r11 + 1;
        goto L10
    L12:
        r83 = r83 + 1;
        goto L7
    L13:
        ArrayList r2 = new ArrayList();
        int r32 = r1.size();
        int r52 = 0;
    L14:
        if (r52 >= r32) goto L27;
        Object r6 = r1.get(r52);
        r52 = r52 + 1;
        Hct r62 = (Hct) r6;
        double r84 = r42[MathUtils.sanitizeDegreesInt((int) Math.round(r62.getHue()))];
        if (r19 == false) goto L22;
        if (r62.getChroma() < CUTOFF_CHROMA) goto L14;
        if (r84 <= CUTOFF_EXCITED_PROPORTION) goto L14;
    L22:
        double r85 = (r84 * 100.0d) * WEIGHT_PROPORTION;
        if (r62.getChroma() >= TARGET_CHROMA) goto L25;
        double r10 = WEIGHT_CHROMA_BELOW;
    L26:
        r2.add(new ScoredHCT(r62, r85 + ((r62.getChroma() - TARGET_CHROMA) * r10)));
        goto L14
    L25:
        r10 = WEIGHT_CHROMA_ABOVE;
        goto L26
    L27:
        Collections.sort(r2, new ScoredComparator());
        ArrayList r13 = new ArrayList();
        int r33 = 90;
    L29:
        if (r33 < 15) goto L44;
        r13.clear();
        int r43 = r2.size();
        int r53 = 0;
    L31:
        if (r53 >= r43) goto L41;
        Object r63 = r2.get(r53);
        r53 = r53 + 1;
        Hct r64 = ((ScoredHCT) r63).hct;
        int r86 = r13.size();
        int r92 = 0;
    L33:
        if (r92 >= r86) goto L37;
        Object r102 = r13.get(r92);
        r92 = r92 + 1;
        if (MathUtils.differenceDegrees(r64.getHue(), ((Hct) r102).getHue()) >= r33) goto L33;
    L39:
        if (r13.size() < r17) goto L31;
    L37:
        r13.add(r64);
    L41:
        if (r13.size() >= r17) goto L44;
        r33 = r33 - 1;
    L44:
        ArrayList r0 = new ArrayList();
        if (r13.isEmpty() == false) goto L48;
        r0.add(Integer.valueOf(r18));
        return r0;
    L48:
        int r22 = r13.size();
    L49:
        if (r73 >= r22) goto L51;
        Object r34 = r13.get(r73);
        r73 = r73 + 1;
        r0.add(Integer.valueOf(((Hct) r34).toInt()));
        goto L49
    L51:
        return r0;
    }
}
