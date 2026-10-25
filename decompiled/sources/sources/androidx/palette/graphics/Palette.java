package androidx.palette.graphics;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.SparseBooleanArray;
import androidx.collection.ArrayMap;
import androidx.core.graphics.ColorUtils;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import okio.AsyncTimeout$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class Palette {
    static final Filter DEFAULT_FILTER = null;
    private final Swatch mDominantSwatch;
    private final Map<Target, Swatch> mSelectedSwatches;
    private final List<Swatch> mSwatches;
    private final List<Target> mTargets;
    private final SparseBooleanArray mUsedColors;

    /* renamed from: androidx.palette.graphics.Palette$1, reason: invalid class name */
    public static class AnonymousClass1 implements Filter {
        public AnonymousClass1() {
        }

        private boolean isBlack(float[] r1) {
            if (r1[2] > 0.05f) goto L6;
            return true;
        L6:
            return false;
        }

        private boolean isNearRedILine(float[] r3) {
            float r0 = r3[0];
            if (r0 >= 10.0f) goto L5;
        L9:
            return false;
        L5:
            if (r0 > 37.0f) goto L9;
            if (r3[1] > 0.82f) goto L9;
            return true;
        }

        private boolean isWhite(float[] r1) {
            if (r1[2] < 0.95f) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // androidx.palette.graphics.Palette.Filter
        public boolean isAllowed(int r1, float[] r2) {
            if (isWhite(r2) == false) goto L5;
            return false;
        L5:
            if (isBlack(r2) == false) goto L7;
            return false;
        L7:
            if (isNearRedILine(r2) == true) goto L13;
            return true;
        L13:
            return false;
        }
    }

    public static final class Builder {
        private final Bitmap mBitmap;
        private final List<Filter> mFilters;
        private int mMaxColors;
        private Rect mRegion;
        private int mResizeArea;
        private int mResizeMaxDimension;
        private final List<Swatch> mSwatches;
        private final List<Target> mTargets;

        public Builder(Bitmap r5) {
            ArrayList r0 = new ArrayList();
            this.mTargets = r0;
            this.mMaxColors = 16;
            this.mResizeArea = 12544;
            this.mResizeMaxDimension = -1;
            ArrayList r1 = new ArrayList();
            this.mFilters = r1;
            if (r5 != null) goto L5;
        L8:
            f$$ExternalSyntheticBUOutline1.m("Bitmap is not valid");
            throw null;
        L5:
            if (r5.isRecycled() == true) goto L8;
            r1.add(Palette.DEFAULT_FILTER);
            this.mBitmap = r5;
            this.mSwatches = null;
            r0.add(Target.LIGHT_VIBRANT);
            r0.add(Target.VIBRANT);
            r0.add(Target.DARK_VIBRANT);
            r0.add(Target.LIGHT_MUTED);
            r0.add(Target.MUTED);
            r0.add(Target.DARK_MUTED);
        }

        private int[] getPixelsFromBitmap(Bitmap r9) {
            int r3 = r9.getWidth();
            int r7 = r9.getHeight();
            int[] r1 = new int[r3 * r7];
            r9.getPixels(r1, 0, r3, 0, 0, r3, r7);
            Rect r92 = this.mRegion;
            if (r92 != null) goto L5;
            return r1;
        L5:
            int r93 = r92.width();
            int r0 = this.mRegion.height();
            int[] r2 = new int[r93 * r0];
            int r4 = 0;
        L6:
            if (r4 >= r0) goto L8;
            Rect r5 = this.mRegion;
            System.arraycopy(r1, ((r5.top + r4) * r3) + r5.left, r2, r4 * r93, r93);
            r4 = r4 + 1;
            goto L6
        L8:
            return r2;
        }

        private Bitmap scaleBitmapDown(Bitmap r6) {
            double r1 = -1.0d;
            if (this.mResizeArea <= 0) goto L8;
            int r0 = r6.getWidth() * r6.getHeight();
            int r5 = this.mResizeArea;
            if (r0 <= r5) goto L13;
            r1 = Math.sqrt(r5 / r0);
        L13:
            if (r1 > 0.0d) goto L16;
            return r6;
        L16:
            return Bitmap.createScaledBitmap(r6, (int) Math.ceil(r6.getWidth() * r1), (int) Math.ceil(r6.getHeight() * r1), false);
        L8:
            if (this.mResizeMaxDimension <= 0) goto L13;
            int r02 = Math.max(r6.getWidth(), r6.getHeight());
            int r52 = this.mResizeMaxDimension;
            if (r02 <= r52) goto L13;
            r1 = r52 / r02;
            goto L13
        }

        public Palette generate() {
            Bitmap r0 = this.mBitmap;
            Filter[] r1 = null;
            if (r0 == null) goto L16;
            Bitmap r02 = scaleBitmapDown(r0);
            Rect r2 = this.mRegion;
            if (r02 == this.mBitmap) goto L8;
            if (r2 == null) goto L8;
            double r3 = r02.getWidth() / this.mBitmap.getWidth();
            r2.left = (int) Math.floor(r2.left * r3);
            r2.top = (int) Math.floor(r2.top * r3);
            r2.right = Math.min((int) Math.ceil(r2.right * r3), r02.getWidth());
            r2.bottom = Math.min((int) Math.ceil(r2.bottom * r3), r02.getHeight());
        L8:
            int[] r32 = getPixelsFromBitmap(r02);
            int r4 = this.mMaxColors;
            if (this.mFilters.isEmpty() == true) goto L12;
            List<Filter> r12 = this.mFilters;
            r1 = (Filter[]) r12.toArray(new Filter[r12.size()]);
        L12:
            ColorCutQuantizer r22 = new ColorCutQuantizer(r32, r4, r1);
            if (r02 == this.mBitmap) goto L15;
            r02.recycle();
        L15:
            List<Swatch> r03 = r22.getQuantizedColors();
        L18:
            Palette r13 = new Palette(r03, this.mTargets);
            r13.generate();
            return r13;
        L16:
            r03 = this.mSwatches;
            if (r03 != null) goto L18;
            AsyncTimeout$$ExternalSyntheticBUOutline0.m();
            return null;
        }

        public Builder maximumColorCount(int r1) {
            this.mMaxColors = r1;
            return this;
        }
    }

    public interface Filter {
        boolean isAllowed(int r1, float[] r2);
    }

    public static final class Swatch {
        private final int mBlue;
        private int mBodyTextColor;
        private boolean mGeneratedTextColors;
        private final int mGreen;
        private float[] mHsl;
        private final int mPopulation;
        private final int mRed;
        private final int mRgb;
        private int mTitleTextColor;

        public Swatch(int r2, int r3) {
            this.mRed = Color.red(r2);
            this.mGreen = Color.green(r2);
            this.mBlue = Color.blue(r2);
            this.mRgb = r2;
            this.mPopulation = r3;
        }

        private void ensureTextColorsGenerated() {
            if (this.mGeneratedTextColors == true) goto L23;
            int r0 = ColorUtils.calculateMinimumAlpha(-1, this.mRgb, 4.5f);
            int r3 = ColorUtils.calculateMinimumAlpha(-1, this.mRgb, 3.0f);
            if (r0 == (-1)) goto L9;
            if (r3 == (-1)) goto L9;
            this.mBodyTextColor = ColorUtils.setAlphaComponent(-1, r0);
            this.mTitleTextColor = ColorUtils.setAlphaComponent(-1, r3);
            this.mGeneratedTextColors = true;
            return;
        L9:
            int r2 = ColorUtils.calculateMinimumAlpha(-16777216, this.mRgb, 4.5f);
            int r4 = ColorUtils.calculateMinimumAlpha(-16777216, this.mRgb, 3.0f);
            if (r2 == (-1)) goto L14;
            if (r4 == (-1)) goto L14;
            this.mBodyTextColor = ColorUtils.setAlphaComponent(-16777216, r2);
            this.mTitleTextColor = ColorUtils.setAlphaComponent(-16777216, r4);
            this.mGeneratedTextColors = true;
            return;
        L14:
            if (r0 == (-1)) goto L16;
            int r02 = ColorUtils.setAlphaComponent(-1, r0);
        L17:
            this.mBodyTextColor = r02;
            if (r3 == (-1)) goto L20;
            int r03 = ColorUtils.setAlphaComponent(-1, r3);
        L21:
            this.mTitleTextColor = r03;
            this.mGeneratedTextColors = true;
            return;
        L20:
            r03 = ColorUtils.setAlphaComponent(-16777216, r4);
            goto L21
        L16:
            r02 = ColorUtils.setAlphaComponent(-16777216, r2);
            goto L17
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if (r5 != null) goto L8;
        L15:
            return false;
        L8:
            if (Swatch.class != r5.getClass()) goto L15;
            Swatch r52 = (Swatch) r5;
            if (this.mPopulation != r52.mPopulation) goto L15;
            if (this.mRgb != r52.mRgb) goto L15;
            return true;
        }

        public int getBodyTextColor() {
            ensureTextColorsGenerated();
            return this.mBodyTextColor;
        }

        public float[] getHsl() {
            if (this.mHsl != null) goto L5;
            this.mHsl = new float[3];
        L5:
            ColorUtils.RGBToHSL(this.mRed, this.mGreen, this.mBlue, this.mHsl);
            return this.mHsl;
        }

        public int getPopulation() {
            return this.mPopulation;
        }

        public int getRgb() {
            return this.mRgb;
        }

        public int getTitleTextColor() {
            ensureTextColorsGenerated();
            return this.mTitleTextColor;
        }

        public int hashCode() {
            return (this.mRgb * 31) + this.mPopulation;
        }

        public String toString() {
            return Swatch.class.getSimpleName() + " [RGB: #" + Integer.toHexString(getRgb()) + "] [HSL: " + Arrays.toString(getHsl()) + "] [Population: " + this.mPopulation + "] [Title Text: #" + Integer.toHexString(getTitleTextColor()) + "] [Body Text: #" + Integer.toHexString(getBodyTextColor()) + ']';
        }
    }

    static {
        DEFAULT_FILTER = new AnonymousClass1();
    }

    public Palette(List<Swatch> r1, List<Target> r2) {
        this.mSwatches = r1;
        this.mTargets = r2;
        this.mUsedColors = new SparseBooleanArray();
        this.mSelectedSwatches = new ArrayMap();
        this.mDominantSwatch = findDominantSwatch();
    }

    private Swatch findDominantSwatch() {
        int r0 = this.mSwatches.size();
        int r1 = Integer.MIN_VALUE;
        Swatch r2 = null;
        int r3 = 0;
    L3:
        if (r3 >= r0) goto L8;
        Swatch r4 = this.mSwatches.get(r3);
        if (r4.getPopulation() <= r1) goto L7;
        r1 = r4.getPopulation();
        r2 = r4;
    L7:
        r3 = r3 + 1;
        goto L3
    L8:
        return r2;
    }

    public static Builder from(Bitmap r1) {
        return new Builder(r1);
    }

    private float generateScore(Swatch r7, Target r8) {
        float[] r0 = r7.getHsl();
        Swatch r6 = this.mDominantSwatch;
        if (r6 == null) goto L5;
        int r62 = r6.getPopulation();
    L6:
        float r3 = 0.0f;
        if (r8.getSaturationWeight() <= 0.0f) goto L9;
        float r2 = r8.getSaturationWeight() * (1.0f - Math.abs(r0[1] - r8.getTargetSaturation()));
    L11:
        if (r8.getLightnessWeight() <= 0.0f) goto L13;
        float r1 = r8.getLightnessWeight() * (1.0f - Math.abs(r0[2] - r8.getTargetLightness()));
    L15:
        if (r8.getPopulationWeight() <= 0.0f) goto L18;
        r3 = r8.getPopulationWeight() * (r7.getPopulation() / r62);
    L18:
        return (r2 + r1) + r3;
    L13:
        r1 = 0.0f;
        goto L15
    L9:
        r2 = 0.0f;
        goto L11
    L5:
        r62 = 1;
        goto L6
    }

    private Swatch generateScoredTarget(Target r3) {
        Swatch r0 = getMaxScoredSwatchForTarget(r3);
        if (r0 != null) goto L5;
    L7:
        return r0;
    L5:
        if (r3.isExclusive() == false) goto L7;
        this.mUsedColors.append(r0.getRgb(), true);
        goto L7
    }

    private Swatch getMaxScoredSwatchForTarget(Target r8) {
        int r0 = this.mSwatches.size();
        float r1 = 0.0f;
        Swatch r2 = null;
        int r3 = 0;
    L3:
        if (r3 >= r0) goto L12;
        Swatch r4 = this.mSwatches.get(r3);
        if (shouldBeScoredForTarget(r4, r8) == false) goto L11;
        float r5 = generateScore(r4, r8);
        if (r2 != null) goto L9;
    L10:
        r2 = r4;
        r1 = r5;
        goto L11
    L9:
        if (r5 > r1) goto L10;
    L11:
        r3 = r3 + 1;
        goto L3
    L12:
        return r2;
    }

    private boolean shouldBeScoredForTarget(Swatch r6, Target r7) {
        float[] r0 = r6.getHsl();
        if (r0[1] >= r7.getMinimumSaturation()) goto L5;
        return false;
    L5:
        if (r0[1] <= r7.getMaximumSaturation()) goto L7;
        return false;
    L7:
        if (r0[2] >= r7.getMinimumLightness()) goto L9;
        return false;
    L9:
        if (r0[2] <= r7.getMaximumLightness()) goto L11;
        return false;
    L11:
        if (this.mUsedColors.get(r6.getRgb()) == true) goto L18;
        return true;
    L18:
        return false;
    }

    public void generate() {
        int r0 = this.mTargets.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        Target r2 = this.mTargets.get(r1);
        r2.normalizeWeights();
        this.mSelectedSwatches.put(r2, generateScoredTarget(r2));
        r1 = r1 + 1;
        goto L3
    L5:
        this.mUsedColors.clear();
    }

    public Swatch getDarkMutedSwatch() {
        return getSwatchForTarget(Target.DARK_MUTED);
    }

    public Swatch getDarkVibrantSwatch() {
        return getSwatchForTarget(Target.DARK_VIBRANT);
    }

    public Swatch getDominantSwatch() {
        return this.mDominantSwatch;
    }

    public Swatch getMutedSwatch() {
        return getSwatchForTarget(Target.MUTED);
    }

    public Swatch getSwatchForTarget(Target r1) {
        return this.mSelectedSwatches.get(r1);
    }

    public List<Swatch> getSwatches() {
        return Collections.unmodifiableList(this.mSwatches);
    }
}
