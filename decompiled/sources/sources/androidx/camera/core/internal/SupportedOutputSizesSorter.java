package androidx.camera.core.internal;

import android.util.Pair;
import android.util.Rational;
import android.util.Size;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.ImageOutputConfig;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.utils.AspectRatioUtil;
import androidx.camera.core.impl.utils.CameraOrientationUtil;
import androidx.camera.core.impl.utils.CompareSizesByArea;
import androidx.camera.core.internal.utils.SizeUtil;
import androidx.camera.core.resolutionselector.AspectRatioStrategy;
import androidx.camera.core.resolutionselector.ResolutionFilter;
import androidx.camera.core.resolutionselector.ResolutionSelector;
import androidx.camera.core.resolutionselector.ResolutionStrategy;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class SupportedOutputSizesSorter {
    private final CameraInfoInternal mCameraInfoInternal;
    private final Rational mFullFovRatio;
    private final int mLensFacing;
    private final int mSensorOrientation;
    private final SupportedOutputSizesSorterLegacy mSupportedOutputSizesSorterLegacy;

    public SupportedOutputSizesSorter(CameraInfoInternal r2, Size r3) {
        this.mCameraInfoInternal = r2;
        this.mSensorOrientation = r2.getSensorRotationDegrees();
        this.mLensFacing = r2.getLensFacing();
        if (r3 == null) goto L5;
        Rational r32 = calculateFullFovRatioFromActiveArraySize(r3);
    L6:
        this.mFullFovRatio = r32;
        this.mSupportedOutputSizesSorterLegacy = new SupportedOutputSizesSorterLegacy(r2, r32);
        return;
    L5:
        r32 = calculateFullFovRatioFromSupportedOutputSizes(r2);
        goto L6
    }

    private static LinkedHashMap<Rational, List<Size>> applyAspectRatioStrategy(List<Size> r0, AspectRatioStrategy r1, Rational r2) {
        return applyAspectRatioStrategyFallbackRule(groupSizesByAspectRatio(r0), r1, r2);
    }

    private static LinkedHashMap<Rational, List<Size>> applyAspectRatioStrategyFallbackRule(Map<Rational, List<Size>> r7, AspectRatioStrategy r8, Rational r9) {
        int r0 = 0;
        boolean r1 = true;
        if (r9 != null) goto L5;
    L8:
        Rational r2 = getTargetAspectRatioRationalValue(r8.getPreferredAspectRatio(), r1);
        if (r8.getFallbackRule() != 0) goto L15;
        Rational r82 = getTargetAspectRatioRationalValue(r8.getPreferredAspectRatio(), r1);
        ArrayList r12 = new ArrayList(r7.keySet());
        int r3 = r12.size();
        int r4 = 0;
    L11:
        if (r4 >= r3) goto L15;
        Object r5 = r12.get(r4);
        r4 = r4 + 1;
        Rational r52 = (Rational) r5;
        if (r52.equals(r82) == true) goto L11;
        r7.remove(r52);
    L15:
        ArrayList r83 = new ArrayList(r7.keySet());
        Collections.sort(r83, new AspectRatioUtil.CompareAspectRatiosByMappingAreaInFullFovAspectRatioSpace(r2, r9));
        LinkedHashMap<Rational, List<Size>> r92 = new LinkedHashMap();
        int r13 = r83.size();
    L16:
        if (r0 >= r13) goto L18;
        Object r22 = r83.get(r0);
        r0 = r0 + 1;
        Rational r23 = (Rational) r22;
        r92.put(r23, r7.get(r23));
        goto L16
    L18:
        return r92;
    L5:
        if (r9.getNumerator() >= r9.getDenominator()) goto L8;
        r1 = false;
        goto L8
    }

    private List<Size> applyHighResolutionSettings(List<Size> r2, ResolutionSelector r3, int r4) {
        if (r3.getAllowedResolutionMode() != 1) goto L6;
        ArrayList r32 = new ArrayList();
        r32.addAll(r2);
        r32.addAll(this.mCameraInfoInternal.getSupportedHighResolutions(r4));
        Collections.sort(r32, new CompareSizesByArea(true));
        return r32;
    L6:
        return r2;
    }

    private static void applyMaxResolutionRestriction(LinkedHashMap<Rational, List<Size>> r6, Size r7) {
        int r72 = SizeUtil.getArea(r7);
        Iterator<Rational> r0 = r6.keySet().iterator();
    L4:
        if (r0.hasNext() == false) goto L12;
        List r1 = r6.get(r0.next());
        ArrayList r2 = new ArrayList();
        Iterator r3 = r1.iterator();
    L7:
        if (r3.hasNext() == false) goto L11;
        Size r4 = (Size) r3.next();
        if (SizeUtil.getArea(r4) > r72) goto L7;
        r2.add(r4);
        goto L7
    L11:
        r1.clear();
        r1.addAll(r2);
        goto L4
    }

    private static List<Size> applyResolutionFilter(List<Size> r1, ResolutionFilter r2, int r3, int r4, int r5) {
        if (r2 != null) goto L4;
        return r1;
    L4:
        int r32 = CameraOrientationUtil.surfaceRotationToDegrees(r3);
        boolean r0 = true;
        if (r5 == 1) goto L8;
        r0 = false;
    L8:
        int r33 = CameraOrientationUtil.getRelativeImageRotation(r32, r4, r0);
        List<Size> r22 = r2.filter(new ArrayList(r1), r33);
        if (r1.containsAll(r22) == false) goto L11;
        return r22;
    L11:
        f$$ExternalSyntheticBUOutline1.m("The returned sizes list of the resolution filter must be a subset of the provided sizes list.");
        return null;
    }

    private static void applyResolutionStrategy(LinkedHashMap<Rational, List<Size>> r2, ResolutionStrategy r3) {
        if (r3 == null) goto L8;
        Iterator<Rational> r0 = r2.keySet().iterator();
    L6:
        if (r0.hasNext() == false) goto L10;
        applyResolutionStrategyFallbackRule(r2.get(r0.next()), r3);
        goto L6
    L10:
        return;
    }

    private static void applyResolutionStrategyFallbackRule(List<Size> r4, ResolutionStrategy r5) {
        if (r4.isEmpty() == true) goto L28;
        int r0 = r5.getFallbackRule();
        if (r5 == ResolutionStrategy.HIGHEST_AVAILABLE_STRATEGY) goto L29;
        Size r52 = r5.getBoundSize();
        if (r0 != 0) goto L10;
        sortSupportedSizesByFallbackRuleNone(r4, r52);
        return;
    L10:
        if (r0 != 1) goto L12;
        sortSupportedSizesByFallbackRuleClosestHigherThenLower(r4, r52, true);
        return;
    L12:
        if (r0 != 2) goto L14;
        sortSupportedSizesByFallbackRuleClosestHigherThenLower(r4, r52, false);
        return;
    L14:
        if (r0 != 3) goto L16;
        sortSupportedSizesByFallbackRuleClosestLowerThenHigher(r4, r52, true);
        return;
    L16:
        if (r0 == 4) goto L18;
        return;
    L18:
        sortSupportedSizesByFallbackRuleClosestLowerThenHigher(r4, r52, false);
        return;
    L29:
        return;
    }

    private Rational calculateFullFovRatioFromActiveArraySize(Size r2) {
        return new Rational(r2.getWidth(), r2.getHeight());
    }

    private Rational calculateFullFovRatioFromSupportedOutputSizes(CameraInfoInternal r2) {
        List<Size> r1 = r2.getSupportedResolutions(256);
        if (r1.isEmpty() == false) goto L6;
        return null;
    L6:
        Size r12 = (Size) Collections.max(r1, new CompareSizesByArea());
        return new Rational(r12.getWidth(), r12.getHeight());
    }

    private List<Size> getResolutionCandidateList(List<Pair<Integer, Size[]>> r2, int r3) {
        List<Size> r22 = getSizeListByFormat(r2, r3);
        if (r22 != null) goto L5;
        r22 = this.mCameraInfoInternal.getSupportedResolutions(r3);
    L5:
        ArrayList r1 = new ArrayList(r22);
        Collections.sort(r1, new CompareSizesByArea(true));
        if (r1.isEmpty() == false) goto L8;
        Logger.w("SupportedOutputSizesCollector", "The retrieved supported resolutions from camera info internal is empty. Format is " + r3 + ".");
    L8:
        return r1;
    }

    public static List<Rational> getResolutionListGroupingAspectRatioKeys(List<Size> r6) {
        ArrayList r0 = new ArrayList();
        r0.add(AspectRatioUtil.ASPECT_RATIO_4_3);
        r0.add(AspectRatioUtil.ASPECT_RATIO_16_9);
        Iterator<Size> r62 = r6.iterator();
    L4:
        if (r62.hasNext() == false) goto L13;
        Size r1 = r62.next();
        Rational r2 = new Rational(r1.getWidth(), r1.getHeight());
        if (r0.contains(r2) == true) goto L4;
        int r3 = r0.size();
        int r4 = 0;
    L8:
        if (r4 >= r3) goto L12;
        Object r5 = r0.get(r4);
        r4 = r4 + 1;
        if (AspectRatioUtil.hasMatchingAspectRatio(r1, (Rational) r5) == false) goto L8;
    L12:
        r0.add(r2);
        goto L4
    L13:
        return r0;
    }

    private List<Size> getSizeListByFormat(List<Pair<Integer, Size[]>> r3, int r4) {
        if (r3 == null) goto L10;
        Iterator<Pair<Integer, Size[]>> r32 = r3.iterator();
    L6:
        if (r32.hasNext() == false) goto L10;
        Pair<Integer, Size[]> r0 = r32.next();
        if (((Integer) r0.first).intValue() != r4) goto L6;
        Size[] r33 = (Size[]) r0.second;
    L11:
        if (r33 != null) goto L14;
        return null;
    L14:
        return Arrays.asList(r33);
    L10:
        r33 = null;
        goto L11
    }

    public static Rational getTargetAspectRatioRationalValue(int r1, boolean r2) {
        if (r1 == (-1)) goto L14;
        if (r1 == 0) goto L14;
        if (r1 == 1) goto L9;
        Logger.e("SupportedOutputSizesCollector", "Undefined target aspect ratio: " + r1);
        return null;
    L9:
        if (r2 == false) goto L13;
        return AspectRatioUtil.ASPECT_RATIO_16_9;
    L13:
        return AspectRatioUtil.ASPECT_RATIO_9_16;
    L14:
        if (r2 == false) goto L18;
        return AspectRatioUtil.ASPECT_RATIO_4_3;
    L18:
        return AspectRatioUtil.ASPECT_RATIO_3_4;
    }

    public static Map<Rational, List<Size>> groupSizesByAspectRatio(List<Size> r5) {
        HashMap r0 = new HashMap();
        Iterator<Rational> r1 = getResolutionListGroupingAspectRatioKeys(r5).iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        r0.put(r1.next(), new ArrayList());
        goto L4
    L6:
        Iterator<Size> r52 = r5.iterator();
    L8:
        if (r52.hasNext() == false) goto L15;
        Size r12 = r52.next();
        Iterator r2 = r0.keySet().iterator();
    L11:
        if (r2.hasNext() == false) goto L8;
        Rational r3 = (Rational) r2.next();
        if (AspectRatioUtil.hasMatchingAspectRatio(r12, r3) == false) goto L11;
        ((List) r0.get(r3)).add(r12);
        goto L11
    L15:
        return r0;
    }

    public static List<Size> sortSupportedOutputSizesByResolutionSelector(ResolutionSelector r2, List<Size> r3, Size r4, int r5, Rational r6, int r7, int r8) {
        LinkedHashMap<Rational, List<Size>> r32 = applyAspectRatioStrategy(r3, r2.getAspectRatioStrategy(), r6);
        if (r4 == null) goto L5;
        applyMaxResolutionRestriction(r32, r4);
    L5:
        applyResolutionStrategy(r32, r2.getResolutionStrategy());
        ArrayList r42 = new ArrayList();
        Iterator<List<Size>> r33 = r32.values().iterator();
    L7:
        if (r33.hasNext() == false) goto L15;
        Iterator<Size> r62 = r33.next().iterator();
    L10:
        if (r62.hasNext() == false) goto L7;
        Size r0 = r62.next();
        if (r42.contains(r0) == true) goto L10;
        r42.add(r0);
        goto L10
    L15:
        return applyResolutionFilter(r42, r2.getResolutionFilter(), r5, r7, r8);
    }

    public static void sortSupportedSizesByFallbackRuleClosestHigherThenLower(List<Size> r5, Size r6, boolean r7) {
        ArrayList r0 = new ArrayList();
        int r1 = r5.size() - 1;
    L3:
        if (r1 < 0) goto L9;
        Size r2 = r5.get(r1);
        if (r2.getWidth() < r6.getWidth()) goto L8;
        if (r2.getHeight() >= r6.getHeight()) goto L9;
    L8:
        r0.add(0, r2);
        r1 = r1 - 1;
    L9:
        r5.removeAll(r0);
        Collections.reverse(r5);
        if (r7 == false) goto L17;
        r5.addAll(r0);
        return;
    }

    private static void sortSupportedSizesByFallbackRuleClosestLowerThenHigher(List<Size> r6, Size r7, boolean r8) {
        ArrayList r0 = new ArrayList();
        int r2 = 0;
    L4:
        if (r2 >= r6.size()) goto L10;
        Size r3 = r6.get(r2);
        if (r3.getWidth() > r7.getWidth()) goto L9;
        if (r3.getHeight() <= r7.getHeight()) goto L10;
    L9:
        r0.add(0, r3);
        r2 = r2 + 1;
    L10:
        r6.removeAll(r0);
        if (r8 == false) goto L18;
        r6.addAll(r0);
        return;
    }

    private static void sortSupportedSizesByFallbackRuleNone(List<Size> r1, Size r2) {
        boolean r0 = r1.contains(r2);
        r1.clear();
        if (r0 == false) goto L6;
        r1.add(r2);
        return;
    }

    public List<Size> getSortedSupportedOutputSizes(UseCaseConfig<?> r13) {
        ImageOutputConfig r0 = (ImageOutputConfig) r13;
        List<Size> r2 = r0.getCustomOrderedResolutions(null);
        if (r2 == null) goto L5;
        return r2;
    L5:
        ResolutionSelector r22 = r0.getResolutionSelector(null);
        List<Size> r3 = getResolutionCandidateList(r0.getSupportedResolutions(null), r13.getInputFormat());
        if (r22 == null) goto L8;
        Size r7 = ((ImageOutputConfig) r13).getMaxResolution(null);
        int r8 = r0.getTargetRotation(0);
        if (r13.isHighResolutionDisabled(false) == true) goto L12;
        r3 = applyHighResolutionSettings(r3, r22, r13.getInputFormat());
    L12:
        List<Size> r6 = r3;
        Logger.d("SupportedOutputSizesCollector", "useCaseConfig = " + r13 + ", candidateSizes = " + r6);
        return sortSupportedOutputSizesByResolutionSelector(r0.getResolutionSelector(), r6, r7, r8, this.mFullFovRatio, this.mSensorOrientation, this.mLensFacing);
    L8:
        return this.mSupportedOutputSizesSorterLegacy.sortSupportedOutputSizes(r3, r13);
    }
}
