package androidx.camera.core.internal;

import android.util.Rational;
import android.util.Size;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.ImageOutputConfig;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.utils.AspectRatioUtil;
import androidx.camera.core.impl.utils.CameraOrientationUtil;
import androidx.camera.core.impl.utils.CompareSizesByArea;
import androidx.camera.core.internal.utils.SizeUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
class SupportedOutputSizesSorterLegacy {
    private final Rational mFullFovRatio;
    private final boolean mIsSensorLandscapeResolution;
    private final int mLensFacing;
    private final int mSensorOrientation;

    public SupportedOutputSizesSorterLegacy(CameraInfoInternal r2, Rational r3) {
        this.mSensorOrientation = r2.getSensorRotationDegrees();
        this.mLensFacing = r2.getLensFacing();
        this.mFullFovRatio = r3;
        boolean r22 = true;
        if (r3 != null) goto L5;
    L8:
        this.mIsSensorLandscapeResolution = r22;
        return;
    L5:
        if (r3.getNumerator() >= r3.getDenominator()) goto L8;
        r22 = false;
        goto L8
    }

    private static Size flipSizeByRotation(Size r0, int r1, int r2, int r3) {
        if (r0 != null) goto L4;
    L7:
        return r0;
    L4:
        if (isRotationNeeded(r1, r2, r3) == false) goto L7;
        return new Size(r0.getHeight(), r0.getWidth());
    }

    private static Rational getAspectRatioGroupKeyOfTargetSize(Size r2, List<Size> r3) {
        if (r2 != null) goto L5;
        return null;
    L5:
        Iterator<Rational> r32 = SupportedOutputSizesSorter.getResolutionListGroupingAspectRatioKeys(r3).iterator();
    L7:
        if (r32.hasNext() == false) goto L12;
        Rational r0 = r32.next();
        if (AspectRatioUtil.hasMatchingAspectRatio(r2, r0) == false) goto L7;
        return r0;
    L12:
        return new Rational(r2.getWidth(), r2.getHeight());
    }

    private Rational getTargetAspectRatioByLegacyApi(ImageOutputConfig r2, List<Size> r3) {
        if (r2.hasTargetAspectRatio() == true) goto L5;
        Size r1 = getTargetSize(r2);
        if (r1 != null) goto L9;
        return null;
    L9:
        return getAspectRatioGroupKeyOfTargetSize(r1, r3);
    L5:
        return SupportedOutputSizesSorter.getTargetAspectRatioRationalValue(r2.getTargetAspectRatio(), this.mIsSensorLandscapeResolution);
    }

    private Size getTargetSize(ImageOutputConfig r3) {
        int r0 = r3.getTargetRotation(0);
        return flipSizeByRotation(r3.getTargetResolution(null), r0, this.mLensFacing, this.mSensorOrientation);
    }

    private static boolean isRotationNeeded(int r2, int r3, int r4) {
        int r22 = CameraOrientationUtil.surfaceRotationToDegrees(r2);
        if (1 != r3) goto L5;
        boolean r32 = true;
    L6:
        int r23 = CameraOrientationUtil.getRelativeImageRotation(r22, r4, r32);
        if (r23 != 90) goto L9;
    L12:
        return true;
    L9:
        if (r23 == 270) goto L12;
        return false;
    L5:
        r32 = false;
        goto L6
    }

    public List<Size> sortSupportedOutputSizes(List<Size> r13, UseCaseConfig<?> r14) {
        if (r13.isEmpty() == false) goto L5;
        return r13;
    L5:
        ArrayList r0 = new ArrayList(r13);
        Collections.sort(r0, new CompareSizesByArea(true));
        ArrayList r132 = new ArrayList();
        ImageOutputConfig r142 = (ImageOutputConfig) r14;
        Size r3 = r142.getMaxResolution(null);
        int r4 = 0;
        Size r5 = (Size) r0.get(0);
        if (r3 != null) goto L8;
    L9:
        r3 = r5;
    L10:
        Size r52 = getTargetSize(r142);
        Size r6 = SizeUtil.RESOLUTION_VGA;
        int r7 = SizeUtil.getArea(r6);
        if (SizeUtil.getArea(r3) >= r7) goto L13;
        r6 = SizeUtil.RESOLUTION_ZERO;
    L17:
        int r72 = r0.size();
        int r8 = 0;
    L18:
        if (r8 >= r72) goto L27;
        Object r9 = r0.get(r8);
        r8 = r8 + 1;
        Size r92 = (Size) r9;
        if (SizeUtil.getArea(r92) > SizeUtil.getArea(r3)) goto L18;
        if (SizeUtil.getArea(r92) < SizeUtil.getArea(r6)) goto L18;
        if (r132.contains(r92) == true) goto L18;
        r132.add(r92);
        goto L18
    L27:
        if (r132.isEmpty() == true) goto L53;
        Rational r02 = getTargetAspectRatioByLegacyApi(r142, r132);
        if (r52 != null) goto L31;
        r52 = r142.getDefaultResolution(null);
    L31:
        ArrayList r143 = new ArrayList();
        new HashMap();
        if (r02 != null) goto L37;
        r143.addAll(r132);
        if (r52 == null) goto L51;
        SupportedOutputSizesSorter.sortSupportedSizesByFallbackRuleClosestHigherThenLower(r143, r52, true);
        return r143;
    L51:
        return r143;
    L37:
        Map<Rational, List<Size>> r133 = SupportedOutputSizesSorter.groupSizesByAspectRatio(r132);
        if (r52 == null) goto L43;
        Iterator<Rational> r2 = r133.keySet().iterator();
    L41:
        if (r2.hasNext() == false) goto L43;
        SupportedOutputSizesSorter.sortSupportedSizesByFallbackRuleClosestHigherThenLower(r133.get(r2.next()), r52, true);
    L43:
        ArrayList r1 = new ArrayList(r133.keySet());
        Collections.sort(r1, new AspectRatioUtil.CompareAspectRatiosByMappingAreaInFullFovAspectRatioSpace(r02, this.mFullFovRatio));
        int r12 = r1.size();
    L44:
        if (r4 >= r12) goto L51;
        Object r03 = r1.get(r4);
        r4 = r4 + 1;
        Iterator<Size> r04 = r133.get((Rational) r03).iterator();
    L47:
        if (r04.hasNext() == false) goto L44;
        Size r22 = r04.next();
        if (r143.contains(r22) == true) goto L47;
        r143.add(r22);
        goto L47
    L53:
        throw new IllegalArgumentException("All supported output sizes are filtered out according to current resolution selection settings. \nminSize = " + r6 + "\nmaxSize = " + r3 + "\ninitial size list: " + r0);
    L13:
        if (r52 == null) goto L17;
        if (SizeUtil.getArea(r52) >= r7) goto L17;
        r6 = r52;
        goto L17
    L8:
        if (SizeUtil.getArea(r5) >= SizeUtil.getArea(r3)) goto L10;
        goto L9
    }
}
