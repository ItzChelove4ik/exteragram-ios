package androidx.camera.core.impl.utils;

import android.graphics.RectF;
import android.util.Rational;
import android.util.Size;
import androidx.camera.core.internal.utils.SizeUtil;
import androidx.core.util.Preconditions;
import java.util.Comparator;

/* loaded from: classes3.dex */
public abstract class AspectRatioUtil {
    public static final Rational ASPECT_RATIO_16_9 = null;
    public static final Rational ASPECT_RATIO_3_4 = null;
    public static final Rational ASPECT_RATIO_4_3 = null;
    public static final Rational ASPECT_RATIO_9_16 = null;

    public static final class CompareAspectRatiosByMappingAreaInFullFovAspectRatioSpace implements Comparator<Rational> {
        private final Rational mFullFovRatio;
        private final Rational mTargetRatio;
        private final RectF mTransformedMappingArea;

        public CompareAspectRatiosByMappingAreaInFullFovAspectRatioSpace(Rational r3, Rational r4) {
            this.mTargetRatio = r3;
            if (r4 != null) goto L6;
            r4 = new Rational(4, 3);
        L6:
            this.mFullFovRatio = r4;
            this.mTransformedMappingArea = getTransformedMappingArea(r3);
        }

        private float getMappingAreaSize(RectF r1) {
            return r1.width() * r1.height();
        }

        private float getOverlappingAreaSize(RectF r3, RectF r4) {
            if (r3.width() >= r4.width()) goto L5;
            float r2 = r3.width();
        L7:
            if (r3.height() >= r4.height()) goto L9;
            float r32 = r3.height();
        L11:
            return r2 * r32;
        L9:
            r32 = r4.height();
            goto L11
        L5:
            r2 = r4.width();
            goto L7
        }

        private RectF getTransformedMappingArea(Rational r5) {
            if (r5.floatValue() != this.mFullFovRatio.floatValue()) goto L7;
            return new RectF(0.0f, 0.0f, this.mFullFovRatio.getNumerator(), this.mFullFovRatio.getDenominator());
        L7:
            if (r5.floatValue() <= this.mFullFovRatio.floatValue()) goto L11;
            return new RectF(0.0f, 0.0f, this.mFullFovRatio.getNumerator(), (r5.getDenominator() * this.mFullFovRatio.getNumerator()) / r5.getNumerator());
        L11:
            return new RectF(0.0f, 0.0f, (r5.getNumerator() * this.mFullFovRatio.getDenominator()) / r5.getDenominator(), this.mFullFovRatio.getDenominator());
        }

        private boolean isMappingAreaCovered(RectF r2, RectF r3) {
            if (r2.width() >= r3.width()) goto L5;
            return false;
        L5:
            if (r2.height() < r3.height()) goto L10;
            return true;
        L10:
            return false;
        }

        /* renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(Rational r3, Rational r4) {
            if (r3.equals(r4) == false) goto L6;
            return 0;
        L6:
            RectF r32 = getTransformedMappingArea(r3);
            RectF r42 = getTransformedMappingArea(r4);
            boolean r0 = isMappingAreaCovered(r32, this.mTransformedMappingArea);
            boolean r1 = isMappingAreaCovered(r42, this.mTransformedMappingArea);
            if (r0 == false) goto L11;
            if (r1 == false) goto L11;
            return (int) Math.signum(getMappingAreaSize(r32) - getMappingAreaSize(r42));
        L11:
            if (r0 == false) goto L14;
            return -1;
        L14:
            if (r1 == false) goto L18;
            return 1;
        L18:
            return -((int) Math.signum(getOverlappingAreaSize(r32, this.mTransformedMappingArea) - getOverlappingAreaSize(r42, this.mTransformedMappingArea)));
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(Rational r1, Rational r2) {
            return compare2(r1, r2);
        }
    }

    static {
        ASPECT_RATIO_4_3 = new Rational(4, 3);
        ASPECT_RATIO_3_4 = new Rational(3, 4);
        ASPECT_RATIO_16_9 = new Rational(16, 9);
        ASPECT_RATIO_9_16 = new Rational(9, 16);
    }

    public static boolean hasMatchingAspectRatio(Size r1, Rational r2) {
        return hasMatchingAspectRatio(r1, r2, SizeUtil.RESOLUTION_VGA);
    }

    public static boolean hasMatchingAspectRatio(Size r4, Rational r5, Size r6) {
        if (r5 != null) goto L6;
        return false;
    L6:
        if (r5.equals(new Rational(r4.getWidth(), r4.getHeight())) == false) goto L10;
        return true;
    L10:
        if (SizeUtil.getArea(r4) >= SizeUtil.getArea(r6)) goto L12;
        return false;
    L12:
        return isPossibleMod16FromAspectRatio(r4, r5);
    }

    private static boolean isPossibleMod16FromAspectRatio(Size r5, Rational r6) {
        int r0 = r5.getWidth();
        int r52 = r5.getHeight();
        Rational r1 = new Rational(r6.getDenominator(), r6.getNumerator());
        int r2 = r0 % 16;
        if (r2 == 0) goto L5;
    L14:
        if (r2 != 0) goto L18;
        return ratioIntersectsMod16Segment(r52, r0, r6);
    L18:
        if ((r52 % 16) == 0) goto L20;
        return false;
    L20:
        return ratioIntersectsMod16Segment(r0, r52, r1);
    L5:
        if ((r52 % 16) != 0) goto L14;
        if (ratioIntersectsMod16Segment(Math.max(0, r52 - 16), r0, r6) == false) goto L9;
        return true;
    L9:
        if (ratioIntersectsMod16Segment(Math.max(0, r0 - 16), r52, r1) == true) goto L22;
        return false;
    L22:
        return true;
    }

    private static boolean ratioIntersectsMod16Segment(int r7, int r8, Rational r9) {
        if ((r8 % 16) != 0) goto L5;
        boolean r0 = true;
    L6:
        Preconditions.checkArgument(r0);
        double r3 = (r7 * r9.getNumerator()) / r9.getDenominator();
        if (r3 > Math.max(0, r8 - 16)) goto L9;
    L11:
        return false;
    L9:
        if (r3 >= (r8 + 16)) goto L11;
        return true;
    L5:
        r0 = false;
        goto L6
    }
}
