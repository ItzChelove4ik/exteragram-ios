package androidx.camera.core.impl;

import android.graphics.Rect;
import android.util.Range;
import android.util.Size;
import androidx.camera.core.CameraFilter;
import androidx.camera.core.CameraIdentifier;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.CameraUseCaseAdapterProvider;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.Logger;
import androidx.camera.core.featuregroup.GroupableFeature;
import androidx.camera.core.featuregroup.impl.ResolvedFeatureGroup;
import androidx.camera.core.impl.utils.RangeUtil;
import androidx.camera.core.internal.CameraUseCaseAdapter;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes3.dex */
public interface CameraInfoInternal extends CameraInfo {
    Set<Integer> getAvailableCapabilities();

    Object getCameraCharacteristics();

    String getCameraId();

    @Override // androidx.camera.core.CameraInfo
    default CameraIdentifier getCameraIdentifier() {
        return CameraIdentifier.Factory.create(getCameraId());
    }

    Quirks getCameraQuirks();

    default CameraInfoInternal getImplementation() {
        return this;
    }

    Rect getSensorRect();

    Set<DynamicRange> getSupportedDynamicRanges();

    @Override // androidx.camera.core.CameraInfo
    default Set<Range<Integer>> getSupportedFrameRateRanges(androidx.camera.core.SessionConfig r4) {
        int r1 = UseCaseAdditionSimulator.simulateAddUseCases(this, r4, true).getPrimaryStreamSpecResult().getMaxSupportedFrameRate();     // Catch: Throwable -> L19
        if (r4.getSessionType() != 1) goto L7;
        Set<Range<Integer>> r3 = RangeUtil.filterFixedRanges(getSupportedHighSpeedFrameRateRanges());
    L9:
        if (r3.isEmpty() == true) goto L11;
        LinkedHashSet r42 = new LinkedHashSet();
        Iterator<Range<Integer>> r32 = r3.iterator();
    L14:
        if (r32.hasNext() == false) goto L18;
        Range<Integer> r0 = r32.next();
        if (((Integer) r0.getUpper()).intValue() > r1) goto L14;
        r42.add(r0);
        goto L14
    L18:
        return r42;
    L11:
        return Collections.EMPTY_SET;
    L7:
        r3 = getSupportedFrameRateRanges();
    L19:
        th = move-exception;
        Logger.w("CameraInfoInternal", "Failed to get max supported frameRate by SessionConfig: " + r4, th);
        return Collections.EMPTY_SET;
    }

    List<Size> getSupportedHighResolutions(int r1);

    Set<Range<Integer>> getSupportedHighSpeedFrameRateRanges();

    Set<Integer> getSupportedOutputFormats();

    List<Size> getSupportedResolutions(int r1);

    boolean isPreviewStabilizationSupported();

    default boolean isResolvedFeatureGroupSupported(ResolvedFeatureGroup r6, androidx.camera.core.SessionConfig r7) {
        Iterator<GroupableFeature> r0 = r6.getFeatures().iterator();
    L4:
        if (r0.hasNext() == false) goto L17;
        GroupableFeature r1 = r0.next();
        if (r1.isSupportedIndividually(this, r7) == true) goto L4;
        Logger.d("CameraInfoInternal", r1 + " is not supported.");
        return false;
    L17:
        UseCaseAdditionSimulator.simulateAddUseCases(this, r7, false, r6);     // Catch: Throwable -> L12 IllegalArgumentException -> L14
        return true;
    L12:
        e = move-exception;
        Logger.d("CameraInfoInternal", "CameraInfoInternal.isResolvedFeatureGroupSupported failed", e);
        return false;
    }

    @Override // androidx.camera.core.CameraInfo
    default boolean isSessionConfigSupported(androidx.camera.core.SessionConfig r4) {
        CameraFilter r1 = r4.getCameraFilter();     // Catch: CameraUseCaseAdapter.CameraException -> L8 IllegalArgumentException -> L10
        if (r1 != null) goto L6;
    L12:
        UseCaseAdditionSimulator.simulateAddUseCases(this, r4, false);     // Catch: CameraUseCaseAdapter.CameraException -> L8 IllegalArgumentException -> L10
        return true;
    L6:
        if (r1.filter(Collections.singletonList(this)).isEmpty() == false) goto L12;
        return false;
    L8:
        e = e;
    L15:
        Logger.d("CameraInfoInternal", "CameraInfoInternal.isSessionConfigSupported failed", e);
        return false;
    L10:
        e = e;
        goto L15
    }

    boolean isVideoStabilizationSupported();

    default void setCameraUseCaseAdapterProvider(CameraUseCaseAdapterProvider r1) {
        UseCaseAdditionSimulator.setCameraUseCaseAdapterProvider(r1);
    }
}
