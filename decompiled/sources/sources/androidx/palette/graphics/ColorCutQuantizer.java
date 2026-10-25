package androidx.palette.graphics;

import android.graphics.Color;
import android.util.TimingLogger;
import androidx.core.graphics.ColorUtils;
import androidx.palette.graphics.Palette;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
final class ColorCutQuantizer {
    private static final Comparator<Vbox> VBOX_COMPARATOR_VOLUME = null;
    final int[] mColors;
    final Palette.Filter[] mFilters;
    final int[] mHistogram;
    final List<Palette.Swatch> mQuantizedColors;
    private final float[] mTempHsl;
    final TimingLogger mTimingLogger;

    /* renamed from: androidx.palette.graphics.ColorCutQuantizer$1, reason: invalid class name */
    public static class AnonymousClass1 implements Comparator<Vbox> {
        public AnonymousClass1() {
        }

        /* renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(Vbox r1, Vbox r2) {
            return r2.getVolume() - r1.getVolume();
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(Vbox r1, Vbox r2) {
            return compare2(r1, r2);
        }
    }

    public class Vbox {
        private int mLowerIndex;
        private int mMaxBlue;
        private int mMaxGreen;
        private int mMaxRed;
        private int mMinBlue;
        private int mMinGreen;
        private int mMinRed;
        private int mPopulation;
        private int mUpperIndex;
        final /* synthetic */ ColorCutQuantizer this$0;

        public Vbox(ColorCutQuantizer r1, int r2, int r3) {
            this.this$0 = r1;
            this.mLowerIndex = r2;
            this.mUpperIndex = r3;
            fitBox();
        }

        public final boolean canSplit() {
            if (getColorCount() <= 1) goto L5;
            return true;
        L5:
            return false;
        }

        public final int findSplitPoint() {
            int r0 = getLongestColorDimension();
            ColorCutQuantizer r1 = this.this$0;
            int[] r2 = r1.mColors;
            int[] r12 = r1.mHistogram;
            ColorCutQuantizer.modifySignificantOctet(r2, r0, this.mLowerIndex, this.mUpperIndex);
            Arrays.sort(r2, this.mLowerIndex, this.mUpperIndex + 1);
            ColorCutQuantizer.modifySignificantOctet(r2, r0, this.mLowerIndex, this.mUpperIndex);
            int r02 = this.mPopulation / 2;
            int r3 = this.mLowerIndex;
            int r4 = 0;
        L3:
            int r5 = this.mUpperIndex;
            if (r3 > r5) goto L11;
            r4 = r4 + r12[r2[r3]];
            if (r4 >= r02) goto L8;
            r3 = r3 + 1;
            goto L3
        L8:
            return Math.min(r5 - 1, r3);
        L11:
            return this.mLowerIndex;
        }

        public final void fitBox() {
            ColorCutQuantizer r0 = this.this$0;
            int[] r1 = r0.mColors;
            int[] r02 = r0.mHistogram;
            int r2 = this.mLowerIndex;
            int r3 = Integer.MAX_VALUE;
            int r6 = Integer.MIN_VALUE;
            int r7 = Integer.MIN_VALUE;
            int r8 = Integer.MIN_VALUE;
            int r9 = 0;
            int r4 = Integer.MAX_VALUE;
            int r5 = Integer.MAX_VALUE;
        L4:
            if (r2 > this.mUpperIndex) goto L19;
            int r10 = r1[r2];
            r9 = r9 + r02[r10];
            int r11 = ColorCutQuantizer.quantizedRed(r10);
            int r12 = ColorCutQuantizer.quantizedGreen(r10);
            int r102 = ColorCutQuantizer.quantizedBlue(r10);
            if (r11 <= r6) goto L8;
            r6 = r11;
        L8:
            if (r11 >= r3) goto L10;
            r3 = r11;
        L10:
            if (r12 <= r7) goto L12;
            r7 = r12;
        L12:
            if (r12 >= r4) goto L14;
            r4 = r12;
        L14:
            if (r102 <= r8) goto L16;
            r8 = r102;
        L16:
            if (r102 >= r5) goto L18;
            r5 = r102;
        L18:
            r2 = r2 + 1;
            goto L4
        L19:
            this.mMinRed = r3;
            this.mMaxRed = r6;
            this.mMinGreen = r4;
            this.mMaxGreen = r7;
            this.mMinBlue = r5;
            this.mMaxBlue = r8;
            this.mPopulation = r9;
        }

        public final Palette.Swatch getAverageColor() {
            ColorCutQuantizer r0 = this.this$0;
            int[] r1 = r0.mColors;
            int[] r02 = r0.mHistogram;
            int r2 = this.mLowerIndex;
            int r3 = 0;
            int r4 = 0;
            int r5 = 0;
            int r6 = 0;
        L4:
            if (r2 > this.mUpperIndex) goto L6;
            int r7 = r1[r2];
            int r8 = r02[r7];
            r4 = r4 + r8;
            r3 = r3 + (ColorCutQuantizer.quantizedRed(r7) * r8);
            r5 = r5 + (ColorCutQuantizer.quantizedGreen(r7) * r8);
            r6 = r6 + (r8 * ColorCutQuantizer.quantizedBlue(r7));
            r2 = r2 + 1;
            goto L4
        L6:
            float r03 = r4;
            return new Palette.Swatch(ColorCutQuantizer.approximateToRgb888(Math.round(r3 / r03), Math.round(r5 / r03), Math.round(r6 / r03)), r4);
        }

        public final int getColorCount() {
            return (this.mUpperIndex + 1) - this.mLowerIndex;
        }

        public final int getLongestColorDimension() {
            int r0 = this.mMaxRed - this.mMinRed;
            int r1 = this.mMaxGreen - this.mMinGreen;
            int r2 = this.mMaxBlue - this.mMinBlue;
            if (r0 < r1) goto L7;
            if (r0 < r2) goto L7;
            return -3;
        L7:
            if (r1 < r0) goto L11;
            if (r1 < r2) goto L13;
            return -2;
        L13:
            return -1;
        L11:
            return -1;
        }

        public final int getVolume() {
            return (((this.mMaxRed - this.mMinRed) + 1) * ((this.mMaxGreen - this.mMinGreen) + 1)) * ((this.mMaxBlue - this.mMinBlue) + 1);
        }

        public final Vbox splitBox() {
            if (canSplit() == false) goto L6;
            int r0 = findSplitPoint();
            Vbox r1 = new Vbox(this.this$0, r0 + 1, this.mUpperIndex);
            this.mUpperIndex = r0;
            fitBox();
            return r1;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("Can not split a box with only 1 color");
            return null;
        }
    }

    static {
        VBOX_COMPARATOR_VOLUME = new AnonymousClass1();
    }

    public ColorCutQuantizer(int[] r7, int r8, Palette.Filter[] r9) {
        this.mTempHsl = new float[3];
        this.mTimingLogger = null;
        this.mFilters = r9;
        int[] r0 = new int[32768];
        this.mHistogram = r0;
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r2 >= r7.length) goto L6;
        int r3 = quantizeFromRgb888(r7[r2]);
        r7[r2] = r3;
        r0[r3] = r0[r3] + 1;
        r2 = r2 + 1;
        goto L4
    L6:
        int r72 = 0;
        int r22 = 0;
    L7:
        if (r72 >= 32768) goto L17;
        if (r0[r72] <= 0) goto L14;
        if (shouldIgnoreColor(r72) == false) goto L14;
        r0[r72] = 0;
    L14:
        if (r0[r72] <= 0) goto L16;
        r22 = r22 + 1;
    L16:
        r72 = r72 + 1;
        goto L7
    L17:
        int[] r73 = new int[r22];
        this.mColors = r73;
        int r32 = 0;
        int r4 = 0;
    L18:
        if (r32 >= 32768) goto L23;
        if (r0[r32] <= 0) goto L22;
        r73[r4] = r32;
        r4 = r4 + 1;
    L22:
        r32 = r32 + 1;
        goto L18
    L23:
        if (r22 > r8) goto L28;
        this.mQuantizedColors = new ArrayList();
    L25:
        if (r1 >= r22) goto L27;
        int r82 = r73[r1];
        this.mQuantizedColors.add(new Palette.Swatch(approximateToRgb888(r82), r0[r82]));
        r1 = r1 + 1;
        goto L25
    L27:
        return;
    L28:
        this.mQuantizedColors = quantizePixels(r8);
    }

    private static int approximateToRgb888(int r2) {
        return approximateToRgb888(quantizedRed(r2), quantizedGreen(r2), quantizedBlue(r2));
    }

    public static int approximateToRgb888(int r2, int r3, int r4) {
        return Color.rgb(modifyWordWidth(r2, 5, 8), modifyWordWidth(r3, 5, 8), modifyWordWidth(r4, 5, 8));
    }

    private List<Palette.Swatch> generateAverageColors(Collection<Vbox> r4) {
        ArrayList r0 = new ArrayList(r4.size());
        Iterator<Vbox> r42 = r4.iterator();
    L4:
        if (r42.hasNext() == false) goto L8;
        Palette.Swatch r1 = r42.next().getAverageColor();
        if (shouldIgnoreColor(r1) == true) goto L4;
        r0.add(r1);
        goto L4
    L8:
        return r0;
    }

    public static void modifySignificantOctet(int[] r2, int r3, int r4, int r5) {
        if (r3 != (-2)) goto L5;
    L9:
        if (r4 > r5) goto L15;
        int r32 = r2[r4];
        int r0 = (quantizedGreen(r32) << 10) | (quantizedRed(r32) << 5);
        r2[r4] = quantizedBlue(r32) | r0;
        r4 = r4 + 1;
        goto L9
    L15:
        return;
    L5:
        if (r3 != (-1)) goto L11;
    L7:
        if (r4 > r5) goto L14;
        int r33 = r2[r4];
        int r02 = (quantizedBlue(r33) << 10) | (quantizedGreen(r33) << 5);
        r2[r4] = quantizedRed(r33) | r02;
        r4 = r4 + 1;
        goto L7
    L14:
        return;
    }

    private static int modifyWordWidth(int r0, int r1, int r2) {
        if (r2 <= r1) goto L4;
        int r02 = r0 << (r2 - r1);
    L6:
        return r02 & ((1 << r2) - 1);
    L4:
        r02 = r0 >> (r1 - r2);
        goto L6
    }

    private static int quantizeFromRgb888(int r4) {
        int r0 = modifyWordWidth(Color.red(r4), 8, 5);
        int r3 = modifyWordWidth(Color.green(r4), 8, 5);
        return modifyWordWidth(Color.blue(r4), 8, 5) | ((r0 << 10) | (r3 << 5));
    }

    private List<Palette.Swatch> quantizePixels(int r5) {
        PriorityQueue<Vbox> r0 = new PriorityQueue(r5, VBOX_COMPARATOR_VOLUME);
        r0.offer(new Vbox(this, 0, this.mColors.length - 1));
        splitBoxes(r0, r5);
        return generateAverageColors(r0);
    }

    public static int quantizedBlue(int r0) {
        return r0 & 31;
    }

    public static int quantizedGreen(int r0) {
        return (r0 >> 5) & 31;
    }

    public static int quantizedRed(int r0) {
        return (r0 >> 10) & 31;
    }

    private boolean shouldIgnoreColor(int r2) {
        int r22 = approximateToRgb888(r2);
        ColorUtils.colorToHSL(r22, this.mTempHsl);
        return shouldIgnoreColor(r22, this.mTempHsl);
    }

    private boolean shouldIgnoreColor(int r5, float[] r6) {
        Palette.Filter[] r0 = this.mFilters;
        if (r0 != null) goto L5;
    L13:
        return false;
    L5:
        if (r0.length <= 0) goto L13;
        int r02 = r0.length;
        int r2 = 0;
    L7:
        if (r2 >= r02) goto L13;
        if (this.mFilters[r2].isAllowed(r5, r6) == false) goto L10;
        r2 = r2 + 1;
        goto L7
    L10:
        return true;
    }

    private boolean shouldIgnoreColor(Palette.Swatch r2) {
        return shouldIgnoreColor(r2.getRgb(), r2.getHsl());
    }

    private void splitBoxes(PriorityQueue<Vbox> r2, int r3) {
    L3:
        if (r2.size() >= r3) goto L9;
        Vbox r1 = r2.poll();
        if (r1 == null) goto L13;
        if (r1.canSplit() == false) goto L14;
        r2.offer(r1.splitBox());
        r2.offer(r1);
        goto L3
    L14:
        return;
    L13:
        return;
    }

    public List<Palette.Swatch> getQuantizedColors() {
        return this.mQuantizedColors;
    }
}
