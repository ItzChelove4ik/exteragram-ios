package androidx.camera.core.streamsharing;

import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Pair;
import android.util.Rational;
import android.util.Size;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.ImageOutputConfig;
import androidx.camera.core.impl.MutableConfig;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.utils.AspectRatioUtil;
import androidx.camera.core.impl.utils.CompareSizesByArea;
import androidx.camera.core.impl.utils.TransformUtils;
import androidx.camera.core.internal.SupportedOutputSizesSorter;
import androidx.camera.core.resolutionselector.ResolutionSelector;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* loaded from: classes3.dex */
public class ResolutionsMerger {
    private static final double SAME_AREA_WIDTH_HEIGHT_RATIO = 0.0d;
    private final CameraInfoInternal mCameraInfo;
    private final Map<UseCaseConfig<?>, List<Size>> mChildSizesCache;
    private final Set<UseCaseConfig<?>> mChildrenConfigs;
    private final Rational mFallbackAspectRatio;
    private final Rational mSensorAspectRatio;
    private final Size mSensorSize;
    private final SupportedOutputSizesSorter mSizeSorter;

    public static class CompareAspectRatioByOverlappingAreaToReference implements Comparator<Rational> {
        private final Rational mReferenceAspectRatio;
        private final boolean mReverse;

        public CompareAspectRatioByOverlappingAreaToReference(Rational r1, boolean r2) {
            this.mReferenceAspectRatio = r1;
            this.mReverse = r2;
        }

        /* renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(Rational r2, Rational r3) {
            float r22 = ResolutionsMerger.access$000(r2, this.mReferenceAspectRatio);
            float r32 = ResolutionsMerger.access$000(r3, this.mReferenceAspectRatio);
            if (this.mReverse == false) goto L7;
            return Float.compare(r32, r22);
        L7:
            return Float.compare(r22, r32);
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(Rational r1, Rational r2) {
            return compare2(r1, r2);
        }
    }

    static {
        SAME_AREA_WIDTH_HEIGHT_RATIO = Math.sqrt(2.3703703703703702d);
    }

    private ResolutionsMerger(Size r2, CameraInfoInternal r3, Set<UseCaseConfig<?>> r4) {
        this(r2, r3, r4, new SupportedOutputSizesSorter(r3, r2));
    }

    public ResolutionsMerger(Size r2, CameraInfoInternal r3, Set<UseCaseConfig<?>> r4, SupportedOutputSizesSorter r5) {
        this.mChildSizesCache = new HashMap();
        this.mSensorSize = r2;
        Rational r22 = getSensorAspectRatio(r2);
        this.mSensorAspectRatio = r22;
        this.mFallbackAspectRatio = getFallbackAspectRatio(r22);
        this.mCameraInfo = r3;
        this.mChildrenConfigs = r4;
        this.mSizeSorter = r5;
    }

    public ResolutionsMerger(CameraInternal r2, Set<UseCaseConfig<?>> r3) {
        this(TransformUtils.rectToSize(r2.getCameraInfoInternal().getSensorRect()), r2.getCameraInfoInternal(), r3);
    }

    public static /* synthetic */ float access$000(Rational r0, Rational r1) {
        return computeAreaOverlapping(r0, r1);
    }

    private boolean areCroppingInDifferentDirection(float r3, float r4, float r5) {
        if (r3 != r4) goto L5;
    L15:
        return false;
    L5:
        if (r4 == r5) goto L15;
        if (r3 > r4) goto L10;
        if (r4 <= r5) goto L15;
        return true;
    L10:
        if (r4 >= r5) goto L12;
        return true;
    L12:
        return false;
    }

    private static float computeAreaOverlapping(Rational r1, Rational r2) {
        float r12 = r1.floatValue();
        float r22 = r2.floatValue();
        if (r12 <= r22) goto L7;
        return r22 / r12;
    L7:
        return r12 / r22;
    }

    private List<Size> filterOutChildSizesCausingDoubleCropping(Rational r4, List<Size> r5) {
        ArrayList r0 = new ArrayList();
        Iterator<Size> r52 = r5.iterator();
    L4:
        if (r52.hasNext() == false) goto L8;
        Size r1 = r52.next();
        if (isDoubleCropping(r4, r1) == true) goto L4;
        r0.add(r1);
        goto L4
    L8:
        return r0;
    }

    private static List<Size> filterOutChildSizesThatWillNeverBeSelected(List<Size> r7) {
        HashMap r0 = new HashMap();
        ArrayList r1 = new ArrayList();
        Iterator<Size> r72 = r7.iterator();
    L4:
        if (r72.hasNext() == false) goto L24;
        Size r2 = r72.next();
        Iterator r3 = r0.keySet().iterator();
    L7:
        if (r3.hasNext() == false) goto L11;
        Rational r4 = (Rational) r3.next();
        if (AspectRatioUtil.hasMatchingAspectRatio(r2, r4) == false) goto L7;
    L12:
        if (r4 == null) goto L22;
        Size r32 = (Size) r0.get(r4);
        Objects.requireNonNull(r32);
        if (r2.getHeight() > r32.getHeight()) goto L4;
        if (r2.getWidth() > r32.getWidth()) goto L4;
        if (r2.getWidth() != r32.getWidth()) goto L23;
        if (r2.getHeight() == r32.getHeight()) goto L4;
    L23:
        r1.add(r2);
        r0.put(r4, r2);
        goto L4
    L22:
        r4 = toRational(r2);
        goto L23
    L11:
        r4 = null;
        goto L12
    L24:
        return r1;
    }

    public static List<Size> filterOutParentSizeThatIsTooSmall(Collection<Size> r3, List<Size> r4) {
        if (r3.isEmpty() == true) goto L15;
        if (r4.isEmpty() == true) goto L15;
        ArrayList r0 = new ArrayList();
        Iterator<Size> r42 = r4.iterator();
    L9:
        if (r42.hasNext() == false) goto L13;
        Size r1 = r42.next();
        if (isAnyChildSizeCanBeCroppedOutWithoutUpscalingParent(r3, r1) == false) goto L9;
        r0.add(r1);
        goto L9
    L13:
        return r0;
    L15:
        return new ArrayList();
    }

    public static List<Size> filterResolutionsByAspectRatio(Rational r3, List<Size> r4) {
        ArrayList r0 = new ArrayList();
        Iterator<Size> r42 = r4.iterator();
    L4:
        if (r42.hasNext() == false) goto L8;
        Size r1 = r42.next();
        if (AspectRatioUtil.hasMatchingAspectRatio(r1, r3) == false) goto L4;
        r0.add(r1);
        goto L4
    L8:
        return r0;
    }

    private static Rational findCloserAspectRatio(Size r4) {
        if ((r4.getWidth() / r4.getHeight()) <= SAME_AREA_WIDTH_HEIGHT_RATIO) goto L7;
        return AspectRatioUtil.ASPECT_RATIO_16_9;
    L7:
        return AspectRatioUtil.ASPECT_RATIO_4_3;
    }

    private List<Size> getCameraSupportedHighResolutions() {
        return this.mCameraInfo.getSupportedHighResolutions(34);
    }

    private List<Size> getCameraSupportedResolutions() {
        return this.mCameraInfo.getSupportedResolutions(34);
    }

    private static Rect getCenterCroppedRectangle(Rational r4, Size r5) {
        int r0 = r5.getWidth();
        int r1 = r5.getHeight();
        Rational r52 = toRational(r5);
        if (r4.floatValue() != r52.floatValue()) goto L6;
        RectF r42 = new RectF(0.0f, 0.0f, r0, r1);
    L10:
        Rect r53 = new Rect();
        r42.round(r53);
        return r53;
    L6:
        if (r4.floatValue() <= r52.floatValue()) goto L9;
        float r54 = r0;
        float r43 = r54 / r4.floatValue();
        float r02 = (r1 - r43) / 2.0f;
        RectF r12 = new RectF(0.0f, r02, r54, r43 + r02);
    L8:
        r42 = r12;
        goto L10
    L9:
        float r55 = r1;
        float r44 = r4.floatValue() * r55;
        float r03 = (r0 - r44) / 2.0f;
        r12 = new RectF(r03, 0.0f, r44 + r03, r55);
        goto L8
    }

    private Set<Size> getChildrenRequiredResolutions() {
        HashSet r0 = new HashSet();
        Iterator<UseCaseConfig<?>> r1 = this.mChildrenConfigs.iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        r0.addAll(getSortedChildSizes(r1.next()));
        goto L4
    L6:
        return r0;
    }

    public static Rect getCropRectOfReferenceAspectRatio(Size r0, Size r1) {
        return getCenterCroppedRectangle(toRational(r1), r0);
    }

    private static Rational getFallbackAspectRatio(Rational r2) {
        Rational r0 = AspectRatioUtil.ASPECT_RATIO_4_3;
        if (r2.equals(r0) == false) goto L7;
        return AspectRatioUtil.ASPECT_RATIO_16_9;
    L7:
        if (r2.equals(AspectRatioUtil.ASPECT_RATIO_16_9) == false) goto L9;
        return r0;
    L9:
        Native$$ExternalSyntheticBUOutline5.m("Invalid sensor aspect-ratio: ", r2);
        return null;
    }

    public static List<Size> getParentSizesThatAreTooLarge(Collection<Size> r3, List<Size> r4) {
        if (r3.isEmpty() == true) goto L18;
        if (r4.isEmpty() == true) goto L18;
        List<Size> r42 = removeDuplicates(r4);
        ArrayList r0 = new ArrayList();
        Iterator<Size> r43 = r42.iterator();
    L9:
        if (r43.hasNext() == false) goto L14;
        Size r1 = r43.next();
        if (isAllChildSizesCanBeCroppedOutWithoutUpscalingParent(r3, r1) == false) goto L9;
        r0.add(r1);
        goto L9
    L14:
        if (r0.isEmpty() == true) goto L16;
        r0.remove(r0.size() - 1);
    L16:
        return r0;
    L18:
        return new ArrayList();
    }

    private PreferredChildSize getPreferredChildSizeInternal(Rect r1, UseCaseConfig<?> r2, boolean r3) {
        if (r3 == false) goto L4;
        Pair<Size, Size> r0 = getPreferredChildSizeForViewport(TransformUtils.rectToSize(r1), r2);
        Size r22 = (Size) r0.first;
        Size r02 = (Size) r0.second;
    L6:
        return new PreferredChildSize(r1, r02, r22);
    L4:
        Size r12 = TransformUtils.rectToSize(r1);
        r22 = getPreferredChildSize(r12, r2);
        r1 = getCropRectOfReferenceAspectRatio(r12, r22);
        r02 = r22;
        goto L6
    }

    private static Rational getSensorAspectRatio(Size r3) {
        Rational r0 = findCloserAspectRatio(r3);
        Logger.d("ResolutionsMerger", "The closer aspect ratio to the sensor size (" + r3 + ") is " + r0 + ".");
        return r0;
    }

    private List<Size> getSortedChildSizes(UseCaseConfig<?> r2) {
        if (this.mChildrenConfigs.contains(r2) == true) goto L5;
        Native$$ExternalSyntheticBUOutline5.m("Invalid child config: ", r2);
        return null;
    L5:
        if (this.mChildSizesCache.containsKey(r2) == false) goto L8;
        List<Size> r1 = this.mChildSizesCache.get(r2);
        Objects.requireNonNull(r1);
        return r1;
    L8:
        List<Size> r0 = filterOutChildSizesThatWillNeverBeSelected(this.mSizeSorter.getSortedSupportedOutputSizes(r2));
        this.mChildSizesCache.put(r2, r0);
        return r0;
    }

    private static List<Size> getSupportedPrivResolutions(List<Pair<Integer, Size[]>> r3) {
        Iterator<Pair<Integer, Size[]>> r32 = r3.iterator();
    L4:
        if (r32.hasNext() == false) goto L10;
        Pair<Integer, Size[]> r0 = r32.next();
        if (((Integer) r0.first).equals(34) == false) goto L4;
        return Arrays.asList((Size[]) r0.second);
    L10:
        return new ArrayList();
    }

    private Map<Rational, List<Size>> groupSizesByAspectRatio(List<Size> r7) {
        HashMap r6 = new HashMap();
        Rational r0 = AspectRatioUtil.ASPECT_RATIO_4_3;
        r6.put(r0, new ArrayList());
        Rational r1 = AspectRatioUtil.ASPECT_RATIO_16_9;
        r6.put(r1, new ArrayList());
        ArrayList r2 = new ArrayList();
        r2.add(r0);
        r2.add(r1);
        Iterator<Size> r72 = r7.iterator();
    L4:
        if (r72.hasNext() == false) goto L17;
        Size r02 = r72.next();
        if (r02.getHeight() <= 0) goto L4;
        int r12 = r2.size();
        int r3 = 0;
    L9:
        if (r3 >= r12) goto L13;
        Object r4 = r2.get(r3);
        r3 = r3 + 1;
        Rational r42 = (Rational) r4;
        if (AspectRatioUtil.hasMatchingAspectRatio(r02, r42) == false) goto L9;
        List r13 = (List) r6.get(r42);
    L14:
        if (r13 != null) goto L16;
        r13 = new ArrayList();
        Rational r32 = toRational(r02);
        r2.add(r32);
        r6.put(r32, r13);
    L16:
        r13.add(r02);
        goto L4
    L13:
        r13 = null;
        goto L14
    L17:
        return r6;
    }

    public static boolean hasUpscaling(Size r2, Size r3) {
        if (r2.getHeight() <= r3.getHeight()) goto L5;
        return true;
    L5:
        if (r2.getWidth() > r3.getWidth()) goto L11;
        return false;
    L11:
        return true;
    }

    private static boolean isAllChildSizesCanBeCroppedOutWithoutUpscalingParent(Collection<Size> r1, Size r2) {
        Iterator<Size> r12 = r1.iterator();
    L4:
        if (r12.hasNext() == false) goto L9;
        if (hasUpscaling(r12.next(), r2) == false) goto L4;
        return false;
    L9:
        return true;
    }

    private static boolean isAnyChildSizeCanBeCroppedOutWithoutUpscalingParent(Collection<Size> r1, Size r2) {
        Iterator<Size> r12 = r1.iterator();
    L4:
        if (r12.hasNext() == false) goto L9;
        if (hasUpscaling(r12.next(), r2) == true) goto L4;
        return true;
    L9:
        return false;
    }

    private boolean isDoubleCropping(Rational r2, Size r3) {
        if (this.mSensorAspectRatio.equals(r2) == false) goto L5;
        return false;
    L5:
        if (AspectRatioUtil.hasMatchingAspectRatio(r3, r2) == false) goto L8;
        return false;
    L8:
        return areCroppingInDifferentDirection(this.mSensorAspectRatio.floatValue(), r2.floatValue(), toRationalWithMod16Considered(r3).floatValue());
    }

    private boolean isDoubleCropping(Size r1, Size r2) {
        return isDoubleCropping(toRationalWithMod16Considered(r1), r2);
    }

    private boolean needToAddSensorResolutions() {
        Iterator<Size> r0 = getChildrenRequiredResolutions().iterator();
    L4:
        if (r0.hasNext() == false) goto L9;
        if (AspectRatioUtil.hasMatchingAspectRatio(r0.next(), this.mFallbackAspectRatio) == true) goto L4;
        return true;
    L9:
        return false;
    }

    private static List<Size> removeDuplicates(List<Size> r2) {
        if (r2.isEmpty() == false) goto L6;
        return r2;
    L6:
        return new ArrayList(new LinkedHashSet(r2));
    }

    public static Rect reverseRect(Rect r4) {
        return new Rect(r4.top, r4.left, r4.bottom, r4.right);
    }

    private List<Size> selectOtherAspectRatioParentResolutionsWithFovPriority(List<Size> r7, boolean r8) {
        Map<Rational, List<Size>> r72 = groupSizesByAspectRatio(r7);
        ArrayList r0 = new ArrayList(r72.keySet());
        sortByFov(r0);
        ArrayList r1 = new ArrayList();
        int r2 = r0.size();
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L10;
        Object r4 = r0.get(r3);
        r3 = r3 + 1;
        Rational r42 = (Rational) r4;
        if (r42.equals(AspectRatioUtil.ASPECT_RATIO_16_9) == true) goto L3;
        if (r42.equals(AspectRatioUtil.ASPECT_RATIO_4_3) == true) goto L3;
        List<Size> r5 = r72.get(r42);
        Objects.requireNonNull(r5);
        r1.addAll(selectParentResolutionsByAspectRatio(r42, r5, r8));
        goto L3
    L10:
        return r1;
    }

    private List<Size> selectParentResolutions(List<Size> r5) {
        ArrayList r0 = new ArrayList();
        if (needToAddSensorResolutions() == false) goto L5;
        r0.addAll(selectParentResolutionsByAspectRatio(this.mSensorAspectRatio, r5, false));
    L5:
        int r1 = r0.size();
        if (shouldPrioritizeFallbackAspectRatio() == false) goto L8;
        r1 = 0;
    L8:
        r0.addAll(r1, selectParentResolutionsByAspectRatio(this.mFallbackAspectRatio, r5, false));
        r0.addAll(selectOtherAspectRatioParentResolutionsWithFovPriority(r5, false));
        if (r0.isEmpty() == false) goto L11;
        Logger.w("ResolutionsMerger", "Failed to find a parent resolution that does not result in double-cropping, this might due to camera not supporting 4:3 and 16:9resolutions or a strict ResolutionSelector settings. Starting resolution selection process with resolutions that might have a smaller FOV.");
        r0.addAll(selectOtherAspectRatioParentResolutionsWithFovPriority(r5, true));
    L11:
        Logger.d("ResolutionsMerger", "Parent resolutions: " + r0);
        return r0;
    }

    private List<Size> selectParentResolutionsByAspectRatio(Rational r5, List<Size> r6, boolean r7) {
        List<Size> r62 = filterResolutionsByAspectRatio(r5, r6);
        sortInDescendingOrder(r62);
        HashSet r0 = new HashSet(r62);
        Iterator<UseCaseConfig<?>> r1 = this.mChildrenConfigs.iterator();
    L4:
        if (r1.hasNext() == false) goto L13;
        List<Size> r2 = getSortedChildSizes(r1.next());
        if (r7 == true) goto L9;
        r2 = filterOutChildSizesCausingDoubleCropping(r5, r2);
    L9:
        if (r2.isEmpty() == true) goto L11;
        r62 = filterOutParentSizeThatIsTooSmall(r2, r62);
        r0.retainAll(getParentSizesThatAreTooLarge(r2, r62));
        goto L4
    L11:
        return new ArrayList();
    L13:
        ArrayList r4 = new ArrayList();
        Iterator<Size> r52 = r62.iterator();
    L15:
        if (r52.hasNext() == false) goto L19;
        Size r63 = r52.next();
        if (r0.contains(r63) == true) goto L15;
        r4.add(r63);
        goto L15
    L19:
        return r4;
    }

    private boolean shouldIncludeHighResolutions() {
        Iterator<UseCaseConfig<?>> r2 = this.mChildrenConfigs.iterator();
    L3:
        boolean r1 = false;
        if (r2.hasNext() == false) goto L14;
        UseCaseConfig<?> r0 = r2.next();
        if (r0.isHighResolutionDisabled(false) == true) goto L3;
        if ((r0 instanceof ImageOutputConfig) == false) goto L3;
        ResolutionSelector r02 = ((ImageOutputConfig) r0).getResolutionSelector(null);
        if (r02 == null) goto L3;
        r1 = true;
        if (r02.getAllowedResolutionMode() != 1) goto L3;
    L14:
        return r1;
    }

    private boolean shouldPrioritizeFallbackAspectRatio() {
        if (this.mChildrenConfigs.isEmpty() == false) goto L5;
        return false;
    L5:
        Iterator<UseCaseConfig<?>> r0 = this.mChildrenConfigs.iterator();
    L7:
        if (r0.hasNext() == false) goto L21;
        Iterator<Size> r2 = getSortedChildSizes(r0.next()).iterator();
        boolean r4 = false;
        boolean r5 = false;
    L10:
        if (r2.hasNext() == false) goto L19;
        boolean r6 = AspectRatioUtil.hasMatchingAspectRatio(r2.next(), this.mFallbackAspectRatio);
        if (r6 == false) goto L14;
        r4 = true;
    L14:
        if (r5 == false) goto L17;
        if (r6 == false) goto L17;
        return false;
    L17:
        if (r6 == true) goto L10;
        r5 = true;
        goto L10
    L19:
        if (r4 == true) goto L7;
        return false;
    L21:
        return true;
    }

    private void sortByFov(List<Rational> r3) {
        Collections.sort(r3, new CompareAspectRatioByOverlappingAreaToReference(toRational(this.mSensorSize), true));
    }

    public static void sortInDescendingOrder(List<Size> r2) {
        Collections.sort(r2, new CompareSizesByArea(true));
    }

    private static Rational toRational(Size r2) {
        return new Rational(r2.getWidth(), r2.getHeight());
    }

    private static Rational toRationalWithMod16Considered(Size r2) {
        Rational r0 = AspectRatioUtil.ASPECT_RATIO_4_3;
        if (AspectRatioUtil.hasMatchingAspectRatio(r2, r0) == false) goto L5;
        return r0;
    L5:
        Rational r02 = AspectRatioUtil.ASPECT_RATIO_16_9;
        if (AspectRatioUtil.hasMatchingAspectRatio(r2, r02) == false) goto L9;
        return r02;
    L9:
        return toRational(r2);
    }

    public List<Size> getMergedResolutions(MutableConfig r4) {
        List<Size> r0 = getCameraSupportedResolutions();
        if (shouldIncludeHighResolutions() == false) goto L5;
        ArrayList r1 = new ArrayList(r0);
        r1.addAll(getCameraSupportedHighResolutions());
        r0 = r1;
    L5:
        List r42 = (List) r4.retrieveOption(ImageOutputConfig.OPTION_SUPPORTED_RESOLUTIONS, null);
        if (r42 == null) goto L9;
        r0 = getSupportedPrivResolutions(r42);
    L9:
        return selectParentResolutions(r0);
    }

    public Size getPreferredChildSize(Size r4, UseCaseConfig<?> r5) {
        List<Size> r52 = getSortedChildSizes(r5);
        Iterator<Size> r0 = r52.iterator();
    L4:
        if (r0.hasNext() == false) goto L11;
        Size r1 = r0.next();
        if (isDoubleCropping(r4, r1) == true) goto L4;
        if (hasUpscaling(r1, r4) == true) goto L4;
        return r1;
    L11:
        Iterator<Size> r3 = r52.iterator();
    L13:
        if (r3.hasNext() == false) goto L17;
        Size r53 = r3.next();
        if (hasUpscaling(r53, r4) == true) goto L13;
        return r53;
    L17:
        return r4;
    }

    public PreferredChildSize getPreferredChildSize(UseCaseConfig<?> r1, Rect r2, int r3, boolean r4) {
        if (TransformUtils.is90or270(r3) == false) goto L5;
        r2 = reverseRect(r2);
        boolean r32 = true;
    L6:
        PreferredChildSize r0 = getPreferredChildSizeInternal(r2, r1, r4);
        if (r32 == true) goto L9;
        return r0;
    L9:
        return new PreferredChildSize(reverseRect(r0.getCropRectBeforeScaling()), TransformUtils.reverseSize(r0.getChildSizeToScale()), r0.getOriginalSelectedChildSize());
    L5:
        r32 = false;
        goto L6
    }

    public Pair<Size, Size> getPreferredChildSizeForViewport(Size r3, UseCaseConfig<?> r4) {
        Iterator<Size> r2 = getSortedChildSizes(r4).iterator();
    L4:
        if (r2.hasNext() == false) goto L10;
        Size r42 = r2.next();
        Size r0 = TransformUtils.rectToSize(getCropRectOfReferenceAspectRatio(r42, r3));
        if (hasUpscaling(r0, r3) == true) goto L4;
        return Pair.create(r42, r0);
    L10:
        return Pair.create(r3, r3);
    }
}
