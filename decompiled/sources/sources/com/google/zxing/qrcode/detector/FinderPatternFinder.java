package com.google.zxing.qrcode.detector;

import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class FinderPatternFinder {
    private static final EstimatedModuleComparator moduleComparator = null;
    private final int[] crossCheckStateCount;
    private boolean hasSkipped;
    private final BitMatrix image;
    private final List<FinderPattern> possibleCenters;

    /* renamed from: com.google.zxing.qrcode.detector.FinderPatternFinder$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class EstimatedModuleComparator implements Comparator<FinderPattern>, Serializable {
        private EstimatedModuleComparator() {
        }

        public /* synthetic */ EstimatedModuleComparator(AnonymousClass1 r1) {
            this();
        }

        /* renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(FinderPattern r1, FinderPattern r2) {
            return Float.compare(r1.getEstimatedModuleSize(), r2.getEstimatedModuleSize());
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(FinderPattern r1, FinderPattern r2) {
            return compare2(r1, r2);
        }
    }

    static {
        moduleComparator = new EstimatedModuleComparator(null);
    }

    public FinderPatternFinder(BitMatrix r1, ResultPointCallback r2) {
        this.image = r1;
        this.possibleCenters = new ArrayList();
        this.crossCheckStateCount = new int[5];
    }

    private static float centerFromEnd(int[] r1, int r2) {
        return ((r2 - r1[4]) - r1[3]) - (r1[2] / 2.0f);
    }

    private boolean crossCheckDiagonal(int r11, int r12) {
        int[] r0 = getCrossCheckStateCount();
        int r2 = 0;
    L4:
        if (r11 < r2) goto L10;
        if (r12 < r2) goto L10;
        if (this.image.get(r12 - r2, r11 - r2) == false) goto L10;
        r0[2] = r0[2] + 1;
        r2 = r2 + 1;
    L10:
        if (r0[2] != 0) goto L12;
        return false;
    L12:
        if (r11 < r2) goto L18;
        if (r12 < r2) goto L18;
        if (this.image.get(r12 - r2, r11 - r2) == true) goto L18;
        r0[1] = r0[1] + 1;
        r2 = r2 + 1;
    L18:
        if (r0[1] != 0) goto L20;
        return false;
    L20:
        if (r11 < r2) goto L26;
        if (r12 < r2) goto L26;
        if (this.image.get(r12 - r2, r11 - r2) == false) goto L26;
        r0[0] = r0[0] + 1;
        r2 = r2 + 1;
    L26:
        if (r0[0] != 0) goto L28;
        return false;
    L28:
        int r22 = this.image.getHeight();
        int r5 = this.image.getWidth();
        int r6 = 1;
    L29:
        int r7 = r11 + r6;
        if (r7 >= r22) goto L36;
        int r8 = r12 + r6;
        if (r8 >= r5) goto L36;
        if (this.image.get(r8, r7) == false) goto L36;
        r0[2] = r0[2] + 1;
        r6 = r6 + 1;
    L36:
        int r3 = r11 + r6;
        if (r3 >= r22) goto L44;
        int r82 = r12 + r6;
        if (r82 >= r5) goto L44;
        if (this.image.get(r82, r3) == true) goto L44;
        r0[3] = r0[3] + 1;
        r6 = r6 + 1;
    L44:
        if (r0[3] != 0) goto L46;
        return false;
    L46:
        int r32 = r11 + r6;
        if (r32 >= r22) goto L54;
        int r83 = r12 + r6;
        if (r83 >= r5) goto L54;
        if (this.image.get(r83, r32) == false) goto L54;
        r0[4] = r0[4] + 1;
        r6 = r6 + 1;
    L54:
        if (r0[4] != 0) goto L57;
        return false;
    L57:
        return foundPatternDiagonal(r0);
    }

    private float crossCheckHorizontal(int r10, int r11, int r12, int r13) {
        BitMatrix r0 = this.image;
        int r1 = r0.getWidth();
        int[] r9 = getCrossCheckStateCount();
        int r2 = r10;
    L4:
        if (r2 < 0) goto L9;
        if (r0.get(r2, r11) == false) goto L9;
        r9[2] = r9[2] + 1;
        r2 = r2 - 1;
    L9:
        if (r2 >= 0) goto L11;
        return Float.NaN;
    L11:
        if (r2 < 0) goto L17;
        if (r0.get(r2, r11) == true) goto L17;
        int r6 = r9[1];
        if (r6 > r12) goto L17;
        r9[1] = r6 + 1;
        r2 = r2 - 1;
    L17:
        if (r2 >= 0) goto L19;
    L66:
        return Float.NaN;
    L19:
        if (r9[1] > r12) goto L66;
    L22:
        if (r2 < 0) goto L29;
        if (r0.get(r2, r11) == false) goto L29;
        int r7 = r9[0];
        if (r7 > r12) goto L29;
        r9[0] = r7 + 1;
        r2 = r2 - 1;
    L29:
        if (r9[0] <= r12) goto L31;
        return Float.NaN;
    L31:
        int r102 = r10 + 1;
    L32:
        if (r102 >= r1) goto L36;
        if (r0.get(r102, r11) == false) goto L36;
        r9[2] = r9[2] + 1;
        r102 = r102 + 1;
    L36:
        if (r102 != r1) goto L39;
        return Float.NaN;
    L39:
        if (r102 >= r1) goto L45;
        if (r0.get(r102, r11) == true) goto L45;
        int r72 = r9[3];
        if (r72 >= r12) goto L45;
        r9[3] = r72 + 1;
        r102 = r102 + 1;
    L45:
        if (r102 == r1) goto L66;
        if (r9[3] >= r12) goto L66;
    L50:
        if (r102 >= r1) goto L56;
        if (r0.get(r102, r11) == false) goto L56;
        int r8 = r9[4];
        if (r8 >= r12) goto L56;
        r9[4] = r8 + 1;
        r102 = r102 + 1;
    L56:
        int r112 = r9[4];
        if (r112 < r12) goto L60;
        return Float.NaN;
    L60:
        if ((Math.abs(((((r9[0] + r9[1]) + r9[2]) + r9[3]) + r112) - r13) * 5) < r13) goto L63;
        return Float.NaN;
    L63:
        if (foundPatternCross(r9) == false) goto L66;
        return centerFromEnd(r9, r102);
    }

    private float crossCheckVertical(int r10, int r11, int r12, int r13) {
        BitMatrix r0 = this.image;
        int r1 = r0.getHeight();
        int[] r9 = getCrossCheckStateCount();
        int r2 = r10;
    L4:
        if (r2 < 0) goto L9;
        if (r0.get(r11, r2) == false) goto L9;
        r9[2] = r9[2] + 1;
        r2 = r2 - 1;
    L9:
        if (r2 >= 0) goto L11;
        return Float.NaN;
    L11:
        if (r2 < 0) goto L17;
        if (r0.get(r11, r2) == true) goto L17;
        int r6 = r9[1];
        if (r6 > r12) goto L17;
        r9[1] = r6 + 1;
        r2 = r2 - 1;
    L17:
        if (r2 >= 0) goto L19;
    L66:
        return Float.NaN;
    L19:
        if (r9[1] > r12) goto L66;
    L22:
        if (r2 < 0) goto L29;
        if (r0.get(r11, r2) == false) goto L29;
        int r7 = r9[0];
        if (r7 > r12) goto L29;
        r9[0] = r7 + 1;
        r2 = r2 - 1;
    L29:
        if (r9[0] <= r12) goto L31;
        return Float.NaN;
    L31:
        int r102 = r10 + 1;
    L32:
        if (r102 >= r1) goto L36;
        if (r0.get(r11, r102) == false) goto L36;
        r9[2] = r9[2] + 1;
        r102 = r102 + 1;
    L36:
        if (r102 != r1) goto L39;
        return Float.NaN;
    L39:
        if (r102 >= r1) goto L45;
        if (r0.get(r11, r102) == true) goto L45;
        int r72 = r9[3];
        if (r72 >= r12) goto L45;
        r9[3] = r72 + 1;
        r102 = r102 + 1;
    L45:
        if (r102 == r1) goto L66;
        if (r9[3] >= r12) goto L66;
    L50:
        if (r102 >= r1) goto L56;
        if (r0.get(r11, r102) == false) goto L56;
        int r8 = r9[4];
        if (r8 >= r12) goto L56;
        r9[4] = r8 + 1;
        r102 = r102 + 1;
    L56:
        int r112 = r9[4];
        if (r112 < r12) goto L60;
        return Float.NaN;
    L60:
        if ((Math.abs(((((r9[0] + r9[1]) + r9[2]) + r9[3]) + r112) - r13) * 5) < (r13 * 2)) goto L63;
        return Float.NaN;
    L63:
        if (foundPatternCross(r9) == false) goto L66;
        return centerFromEnd(r9, r102);
    }

    public static void doClearCounts(int[] r1) {
        Arrays.fill(r1, 0);
    }

    public static void doShiftCounts2(int[] r6) {
        r6[0] = r6[2];
        r6[1] = r6[3];
        r6[2] = r6[4];
        r6[3] = 1;
        r6[4] = 0;
    }

    private int findRowSkip() {
        if (this.possibleCenters.size() > 1) goto L5;
        return 0;
    L5:
        Iterator<FinderPattern> r0 = this.possibleCenters.iterator();
        FinderPattern r3 = null;
    L7:
        if (r0.hasNext() == false) goto L14;
        FinderPattern r4 = r0.next();
        if (r4.getCount() < 2) goto L7;
        if (r3 != null) goto L12;
        r3 = r4;
        goto L7
    L12:
        this.hasSkipped = true;
        return ((int) (Math.abs(r3.getX() - r4.getX()) - Math.abs(r3.getY() - r4.getY()))) / 2;
    L14:
        return 0;
    }

    public static boolean foundPatternCross(int[] r7) {
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r1 >= 5) goto L10;
        int r3 = r7[r1];
        if (r3 == 0) goto L7;
        r2 = r2 + r3;
        r1 = r1 + 1;
        goto L4
    L7:
        return false;
    L10:
        if (r2 >= 7) goto L12;
        return false;
    L12:
        float r12 = r2 / 7.0f;
        float r22 = r12 / 2.0f;
        if (Math.abs(r12 - r7[0]) < r22) goto L15;
    L23:
        return false;
    L15:
        if (Math.abs(r12 - r7[1]) >= r22) goto L23;
        if (Math.abs((r12 * 3.0f) - r7[2]) >= (3.0f * r22)) goto L23;
        if (Math.abs(r12 - r7[3]) >= r22) goto L23;
        if (Math.abs(r12 - r7[4]) >= r22) goto L23;
        return true;
    }

    public static boolean foundPatternDiagonal(int[] r7) {
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r1 >= 5) goto L10;
        int r3 = r7[r1];
        if (r3 == 0) goto L7;
        r2 = r2 + r3;
        r1 = r1 + 1;
        goto L4
    L7:
        return false;
    L10:
        if (r2 >= 7) goto L12;
        return false;
    L12:
        float r12 = r2 / 7.0f;
        float r22 = r12 / 1.333f;
        if (Math.abs(r12 - r7[0]) < r22) goto L15;
    L23:
        return false;
    L15:
        if (Math.abs(r12 - r7[1]) >= r22) goto L23;
        if (Math.abs((r12 * 3.0f) - r7[2]) >= (3.0f * r22)) goto L23;
        if (Math.abs(r12 - r7[3]) >= r22) goto L23;
        if (Math.abs(r12 - r7[4]) >= r22) goto L23;
        return true;
    }

    private int[] getCrossCheckStateCount() {
        doClearCounts(this.crossCheckStateCount);
        return this.crossCheckStateCount;
    }

    private boolean haveMultiplyConfirmedCenters() {
        int r0 = this.possibleCenters.size();
        Iterator<FinderPattern> r1 = this.possibleCenters.iterator();
        float r3 = 0.0f;
        int r4 = 0;
        float r5 = 0.0f;
    L4:
        if (r1.hasNext() == false) goto L9;
        FinderPattern r6 = r1.next();
        if (r6.getCount() < 2) goto L4;
        r4 = r4 + 1;
        r5 = r5 + r6.getEstimatedModuleSize();
        goto L4
    L9:
        if (r4 >= 3) goto L11;
        return false;
    L11:
        float r02 = r5 / r0;
        Iterator<FinderPattern> r9 = this.possibleCenters.iterator();
    L13:
        if (r9.hasNext() == false) goto L16;
        r3 = r3 + Math.abs(r9.next().getEstimatedModuleSize() - r02);
        goto L13
    L16:
        if (r3 > (r5 * 0.05f)) goto L19;
        return true;
    L19:
        return false;
    }

    private FinderPattern[] selectBestPatterns() {
        if (this.possibleCenters.size() < 3) goto L51;
        Iterator<FinderPattern> r1 = this.possibleCenters.iterator();
    L5:
        int r4 = 2;
        if (r1.hasNext() == false) goto L10;
        if (r1.next().getCount() >= 2) goto L5;
        r1.remove();
        goto L5
    L10:
        Collections.sort(this.possibleCenters, moduleComparator);
        FinderPattern[] r12 = new FinderPattern[3];
        double r7 = Double.MAX_VALUE;
        int r3 = 0;
    L12:
        if (r3 >= (this.possibleCenters.size() - r4)) goto L46;
        FinderPattern r9 = this.possibleCenters.get(r3);
        float r10 = r9.getEstimatedModuleSize();
        r3 = r3 + 1;
        int r11 = r3;
    L15:
        if (r11 >= (this.possibleCenters.size() - 1)) goto L12;
        FinderPattern r122 = this.possibleCenters.get(r11);
        double r14 = squaredDistance(r9, r122);
        r11 = r11 + 1;
        int r17 = r4;
        int r2 = r11;
    L18:
        if (r2 >= this.possibleCenters.size()) goto L44;
        FinderPattern r42 = this.possibleCenters.get(r2);
        if (r42.getEstimatedModuleSize() > (1.4f * r10)) goto L43;
        double r18 = squaredDistance(r122, r42);
        double r20 = squaredDistance(r9, r42);
        if (r14 >= r18) goto L33;
        if (r18 > r20) goto L27;
        r20 = r18;
        r18 = r20;
    L28:
        double r22 = r14;
    L40:
        double r202 = Math.abs(r18 - (r20 * 2.0d)) + Math.abs(r18 - (r22 * 2.0d));
        if (r202 >= r7) goto L43;
        r12[0] = r9;
        r12[1] = r122;
        r12[r17] = r42;
        r7 = r202;
        goto L43
    L27:
        if (r14 < r20) goto L28;
        r22 = r20;
    L30:
        r20 = r14;
        goto L40
    L33:
        if (r18 < r20) goto L35;
        r22 = r20;
        r20 = r18;
    L38:
        r18 = r14;
        goto L40
    L35:
        if (r14 >= r20) goto L37;
        r22 = r18;
        r18 = r20;
        goto L30
    L37:
        r22 = r18;
    L43:
        r2 = r2 + 1;
        goto L18
    L44:
        r4 = r17;
        goto L15
    L46:
        if (r7 == Double.MAX_VALUE) goto L49;
        return r12;
    L49:
        throw NotFoundException.getNotFoundInstance();
    L51:
        throw NotFoundException.getNotFoundInstance();
    }

    private static double squaredDistance(FinderPattern r2, FinderPattern r3) {
        double r0 = r2.getX() - r3.getX();
        double r22 = r2.getY() - r3.getY();
        return (r0 * r0) + (r22 * r22);
    }

    public final FinderPatternInfo find(Map<DecodeHintType, ?> r12) {
        if (r12 != null) goto L5;
    L7:
        boolean r122 = false;
    L8:
        int r2 = this.image.getHeight();
        int r3 = this.image.getWidth();
        int r4 = (r2 * 3) / 388;
        if (r4 < 3) goto L11;
        if (r122 == true) goto L11;
    L12:
        int[] r123 = new int[5];
        int r6 = r4 - 1;
        boolean r7 = false;
    L13:
        if (r6 >= r2) goto L52;
        if (r7 == true) goto L52;
        doClearCounts(r123);
        int r8 = 0;
        int r9 = 0;
    L16:
        if (r8 >= r3) goto L45;
        if (this.image.get(r8, r6) == false) goto L24;
        if ((r9 & 1) != 1) goto L22;
        r9 = r9 + 1;
    L22:
        r123[r9] = r123[r9] + 1;
    L43:
        r8 = r8 + 1;
        goto L16
    L24:
        if ((r9 & 1) == 0) goto L26;
        r123[r9] = r123[r9] + 1;
        goto L43
    L26:
        if (r9 == 4) goto L28;
        r9 = r9 + 1;
        r123[r9] = r123[r9] + 1;
        goto L43
    L28:
        if (foundPatternCross(r123) == true) goto L30;
        doShiftCounts2(r123);
    L39:
        r9 = 3;
        goto L43
    L30:
        if (handlePossibleCenter(r123, r6, r8) == true) goto L32;
        doShiftCounts2(r123);
        goto L39
    L32:
        if (this.hasSkipped == false) goto L34;
        r7 = haveMultiplyConfirmedCenters();
    L37:
        doClearCounts(r123);
        r4 = 2;
        r9 = 0;
        goto L43
    L34:
        int r42 = findRowSkip();
        int r10 = r123[2];
        if (r42 <= r10) goto L37;
        r6 = r6 + ((r42 - r10) - 2);
        r8 = r3 - 1;
        goto L37
    L45:
        if (foundPatternCross(r123) == false) goto L51;
        if (handlePossibleCenter(r123, r6, r3) == false) goto L51;
        r4 = r123[0];
        if (this.hasSkipped == false) goto L51;
        r7 = haveMultiplyConfirmedCenters();
    L51:
        r6 = r6 + r4;
    L52:
        FinderPattern[] r11 = selectBestPatterns();
        ResultPoint.orderBestPatterns(r11);
        return new FinderPatternInfo(r11);
    L11:
        r4 = 3;
        goto L12
    L5:
        if (r12.containsKey(DecodeHintType.TRY_HARDER) == false) goto L7;
        r122 = true;
        goto L8
    }

    public final boolean handlePossibleCenter(int[] r6, int r7, int r8) {
        int r0 = 0;
        int r1 = (((r6[0] + r6[1]) + r6[2]) + r6[3]) + r6[4];
        int r82 = (int) centerFromEnd(r6, r8);
        float r72 = crossCheckVertical(r7, r82, r6[2], r1);
        if (Float.isNaN(r72) == true) goto L17;
        int r4 = (int) r72;
        float r62 = crossCheckHorizontal(r82, r4, r6[2], r1);
        if (Float.isNaN(r62) == true) goto L17;
        if (crossCheckDiagonal(r4, (int) r62) == false) goto L17;
        float r83 = r1 / 7.0f;
    L10:
        if (r0 >= this.possibleCenters.size()) goto L15;
        FinderPattern r12 = this.possibleCenters.get(r0);
        if (r12.aboutEquals(r83, r72, r62) == true) goto L13;
        r0 = r0 + 1;
        goto L10
    L13:
        this.possibleCenters.set(r0, r12.combineEstimate(r72, r62, r83));
    L16:
        return true;
    L15:
        this.possibleCenters.add(new FinderPattern(r62, r72, r83));
    L17:
        return false;
    }
}
