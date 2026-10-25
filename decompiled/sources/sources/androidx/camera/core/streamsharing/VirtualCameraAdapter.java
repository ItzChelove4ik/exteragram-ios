package androidx.camera.core.streamsharing;

import android.graphics.Rect;
import android.util.Range;
import android.util.Size;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.Logger;
import androidx.camera.core.Preview;
import androidx.camera.core.UseCase;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.ImageInputConfig;
import androidx.camera.core.impl.ImageOutputConfig;
import androidx.camera.core.impl.MutableConfig;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.StreamSpec;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.TransformUtils;
import androidx.camera.core.processing.SurfaceEdge;
import androidx.camera.core.processing.concurrent.DualOutConfig;
import androidx.camera.core.processing.util.OutConfig;
import androidx.camera.core.streamsharing.StreamSharing;
import androidx.core.util.Preconditions;
import c.f$$ExternalSyntheticBUOutline1;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* loaded from: classes3.dex */
class VirtualCameraAdapter implements UseCase.StateChangeCallback {
    final Set<UseCase> mChildren;
    final Map<UseCase, Boolean> mChildrenActiveState;
    private final Set<UseCaseConfig<?>> mChildrenConfigs;
    private final Map<UseCase, UseCaseConfig<?>> mChildrenConfigsMap;
    final Map<UseCase, SurfaceEdge> mChildrenEdges;
    private final Map<UseCase, VirtualCamera> mChildrenVirtualCameras;
    private final CameraInternal mParentCamera;
    private final CameraCaptureCallback mParentMetadataCallback;
    private final ResolutionsMerger mResolutionsMerger;
    private final CameraInternal mSecondaryParentCamera;
    private ResolutionsMerger mSecondaryResolutionsMerger;
    private final UseCaseConfigFactory mUseCaseConfigFactory;

    public static class VirtualCameraCaptureCallback extends CameraCaptureCallback {
        private final WeakReference<VirtualCameraAdapter> mVirtualCameraAdapterRef;

        public VirtualCameraCaptureCallback(VirtualCameraAdapter r2) {
            this.mVirtualCameraAdapterRef = new WeakReference(r2);
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureCompleted(int r2, CameraCaptureResult r3) {
            VirtualCameraAdapter r1 = this.mVirtualCameraAdapterRef.get();
            if (r1 == null) goto L8;
            Iterator<UseCase> r12 = r1.mChildren.iterator();
        L6:
            if (r12.hasNext() == false) goto L10;
            VirtualCameraAdapter.sendCameraCaptureResultToChild(r3, r12.next().getSessionConfig(), r2);
            goto L6
        L10:
            return;
        }
    }

    public VirtualCameraAdapter(CameraInternal r2, CameraInternal r3, Set<UseCase> r4, UseCaseConfigFactory r5, StreamSharing.Control r6) {
        this.mChildrenEdges = new HashMap();
        this.mChildrenVirtualCameras = new HashMap();
        this.mChildrenActiveState = new HashMap();
        this.mParentMetadataCallback = createCameraCaptureCallback();
        this.mParentCamera = r2;
        this.mSecondaryParentCamera = r3;
        this.mUseCaseConfigFactory = r5;
        this.mChildren = r4;
        Map<UseCase, UseCaseConfig<?>> r52 = toChildrenConfigsMap(r2, r4, r5);
        this.mChildrenConfigsMap = r52;
        HashSet r0 = new HashSet(r52.values());
        this.mChildrenConfigs = r0;
        this.mResolutionsMerger = new ResolutionsMerger(r2, r0);
        if (r3 == null) goto L5;
        this.mSecondaryResolutionsMerger = new ResolutionsMerger(r3, r0);
    L5:
        Iterator<UseCase> r32 = r4.iterator();
    L7:
        if (r32.hasNext() == false) goto L9;
        UseCase r42 = r32.next();
        this.mChildrenActiveState.put(r42, Boolean.FALSE);
        this.mChildrenVirtualCameras.put(r42, new VirtualCamera(r2, this, r6));
        goto L7
    }

    private OutConfig calculateOutConfig(UseCase r8, ResolutionsMerger r9, CameraInternal r10, SurfaceEdge r11, int r12, boolean r13, boolean r14) {
        int r122 = r10.getCameraInfo().getSensorRotationDegrees(r12);
        boolean r0 = TransformUtils.isMirrored(r11.getSensorToBufferTransform());
        UseCaseConfig<?> r1 = this.mChildrenConfigsMap.get(r8);
        Objects.requireNonNull(r1);
        PreferredChildSize r92 = r9.getPreferredChildSize(r1, r11.getCropRect(), TransformUtils.getRotationDegrees(r11.getSensorToBufferTransform()), r13);
        Rect r3 = r92.getCropRectBeforeScaling();
        Size r93 = r92.getChildSizeToScale();
        int r7 = getChildRotationDegrees(r8, r10);
        int r5 = TransformUtils.within360((r11.getRotationDegrees() + r7) - r122);
        if (r14 == false) goto L6;
        boolean r72 = false;
    L5:
        boolean r6 = r72;
        return OutConfig.of(getChildTargetType(r8), getChildFormat(r8), r3, TransformUtils.rotateSize(r93, r5), r5, r6);
    L6:
        r72 = r8.isMirroringRequired(r10) ^ r0;
        goto L5
    }

    private static void forceSetProvider(SurfaceEdge r0, DeferrableSurface r1, SessionConfig r2) {
        r0.invalidate();
        r0.setProvider(r1);     // Catch: DeferrableSurface.SurfaceClosedException -> L5
        return;
    L6:
        if (r2.getErrorListener() == null) goto L11;
        r2.getErrorListener().onError(r2, SessionConfig.SessionError.SESSION_ERROR_SURFACE_NEEDS_RESET);
        return;
    }

    private static int getChildFormat(UseCase r0) {
        if ((r0 instanceof ImageCapture) == false) goto L6;
        return 256;
    L6:
        return 34;
    }

    private int getChildRotationDegrees(UseCase r1, CameraInternal r2) {
        int r0 = ((ImageOutputConfig) r1.getCurrentConfig()).getTargetRotation(0);
        return r2.getCameraInfo().getSensorRotationDegrees(r0);
    }

    private static StreamSpec getChildStreamSpec(UseCase r0, StreamSpec r1, Map<UseCase, Size> r2) {
        StreamSpec.Builder r12 = r1.toBuilder();
        Size r02 = r2.get(r0);
        if (r02 == null) goto L6;
        r12.setOriginalConfiguredResolution(r02);
    L6:
        return r12.build();
    }

    public static DeferrableSurface getChildSurface(UseCase r3) {
        if ((r3 instanceof ImageCapture) == false) goto L5;
        List<DeferrableSurface> r32 = r3.getSessionConfig().getSurfaces();
    L7:
        if (r32.size() > 1) goto L9;
        boolean r0 = true;
    L10:
        Preconditions.checkState(r0);
        if (r32.size() == 1) goto L13;
        return null;
    L13:
        return r32.get(0);
    L9:
        r0 = false;
        goto L10
    L5:
        r32 = r3.getSessionConfig().getRepeatingCaptureConfig().getSurfaces();
        goto L7
    }

    private static int getChildTargetType(UseCase r1) {
        if ((r1 instanceof Preview) == false) goto L7;
        return 1;
    L7:
        if ((r1 instanceof ImageCapture) == false) goto L10;
        return 4;
    L10:
        return 2;
    }

    private static int getHighestSurfacePriority(Set<UseCaseConfig<?>> r3) {
        Iterator<UseCaseConfig<?>> r32 = r3.iterator();
        int r1 = 0;
    L4:
        if (r32.hasNext() == false) goto L6;
        r1 = Math.max(r1, r32.next().getSurfaceOccupancyPriority(0));
        goto L4
    L6:
        return r1;
    }

    private SurfaceEdge getUseCaseEdge(UseCase r1) {
        SurfaceEdge r0 = this.mChildrenEdges.get(r1);
        Objects.requireNonNull(r0);
        return r0;
    }

    private boolean isUseCaseActive(UseCase r1) {
        Boolean r0 = this.mChildrenActiveState.get(r1);
        Objects.requireNonNull(r0);
        return r0.booleanValue();
    }

    private static Range<Integer> resolveTargetFrameRate(Set<UseCaseConfig<?>> r3) {
        Range<Integer> r0 = StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED;
        Iterator<UseCaseConfig<?>> r32 = r3.iterator();
    L4:
        if (r32.hasNext() == false) goto L12;
        Range<Integer> r1 = r32.next().getTargetFrameRate(r0);
        Objects.requireNonNull(r1);
        if (StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED.equals(r0) == true) goto L7;
        r0 = r0.intersect(r1);     // Catch: IllegalArgumentException -> L10
    L10:
        Logger.d("VirtualCameraAdapter", "No intersected frame rate can be found from the target frame rate settings of the UseCases! Resolved: " + r0 + " <<>> " + r1);
        return r0.extend(r1);
    L7:
        r0 = r1;
        goto L4
    L12:
        return r0;
    }

    public static void sendCameraCaptureResultToChild(CameraCaptureResult r4, SessionConfig r5, int r6) {
        Iterator<CameraCaptureCallback> r0 = r5.getRepeatingCameraCaptureCallbacks().iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        r0.next().onCaptureCompleted(r6, new VirtualCameraCaptureResult(r5.getRepeatingCaptureConfig().getTagBundle(), r4));
        goto L4
    }

    private static Map<UseCase, UseCaseConfig<?>> toChildrenConfigsMap(CameraInternal r5, Set<UseCase> r6, UseCaseConfigFactory r7) {
        HashMap r0 = new HashMap();
        Iterator<UseCase> r62 = r6.iterator();
    L4:
        if (r62.hasNext() == false) goto L6;
        UseCase r1 = r62.next();
        r0.put(r1, r1.mergeConfigs(r5.getCameraInfoInternal(), null, r1.getDefaultConfig(true, r7)));
        goto L4
    L6:
        return r0;
    }

    private void updateVirtualCameraRotationDegrees(UseCase r2) {
        int r0 = getChildRotationDegrees(r2, this.mParentCamera);
        VirtualCamera r1 = this.mChildrenVirtualCameras.get(r2);
        Objects.requireNonNull(r1);
        r1.setRotationDegrees(r0);
    }

    public void bindChildren() {
        Iterator<UseCase> r0 = this.mChildren.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        UseCase r1 = r0.next();
        VirtualCamera r2 = this.mChildrenVirtualCameras.get(r1);
        Objects.requireNonNull(r2);
        r1.bindToCamera(r2, null, null, r1.getDefaultConfig(true, this.mUseCaseConfigFactory));
        goto L4
    }

    public CameraCaptureCallback createCameraCaptureCallback() {
        return new VirtualCameraCaptureCallback(this);
    }

    public Set<UseCase> getChildren() {
        return this.mChildren;
    }

    public Map<UseCase, OutConfig> getChildrenOutConfigs(SurfaceEdge r12, int r13, boolean r14, boolean r15) {
        HashMap r0 = new HashMap();
        Iterator<UseCase> r1 = this.mChildren.iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        UseCase r4 = r1.next();
        VirtualCameraAdapter r3 = this;
        OutConfig r11 = r3.calculateOutConfig(r4, this.mResolutionsMerger, this.mParentCamera, r12, r13, r14, r15);
        r3.updateVirtualCameraRotationDegrees(r4);
        r0.put(r4, r11);
        VirtualCameraAdapter this = r3;
        goto L4
    L6:
        return r0;
    }

    public Map<UseCase, DualOutConfig> getChildrenOutConfigs(SurfaceEdge r12, SurfaceEdge r13, int r14, boolean r15) {
        HashMap r0 = new HashMap();
        Iterator<UseCase> r1 = this.mChildren.iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        UseCase r4 = r1.next();
        VirtualCameraAdapter r3 = this;
        int r8 = r14;
        OutConfig r11 = r3.calculateOutConfig(r4, this.mResolutionsMerger, this.mParentCamera, r12, r8, r15, false);
        ResolutionsMerger r5 = r3.mSecondaryResolutionsMerger;
        Objects.requireNonNull(r5);
        CameraInternal r142 = r3.mSecondaryParentCamera;
        Objects.requireNonNull(r142);
        SurfaceEdge r7 = r13;
        OutConfig r132 = r3.calculateOutConfig(r4, r5, r142, r7, r8, r15, false);
        r3.updateVirtualCameraRotationDegrees(r4);
        r0.put(r4, DualOutConfig.of(r11, r132));
        VirtualCameraAdapter this = r3;
        r13 = r7;
        r14 = r8;
        goto L4
    L6:
        return r0;
    }

    public CameraCaptureCallback getParentMetadataCallback() {
        return this.mParentMetadataCallback;
    }

    public Map<UseCase, Size> getSelectedChildSizes(SurfaceEdge r8, boolean r9) {
        HashMap r0 = new HashMap();
        Iterator<UseCase> r1 = this.mChildren.iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        UseCase r2 = r1.next();
        ResolutionsMerger r3 = this.mResolutionsMerger;
        UseCaseConfig<?> r4 = this.mChildrenConfigsMap.get(r2);
        Objects.requireNonNull(r4);
        PreferredChildSize r32 = r3.getPreferredChildSize(r4, r8.getCropRect(), TransformUtils.getRotationDegrees(r8.getSensorToBufferTransform()), r9);
        r0.put(r2, r32.getOriginalSelectedChildSize());
        Logger.d("VirtualCameraAdapter", "Selected child size: " + r32.getOriginalSelectedChildSize() + ", useCase: " + r2);
        goto L4
    L6:
        return r0;
    }

    public void mergeChildrenConfigs(MutableConfig r5) {
        List<Size> r0 = this.mResolutionsMerger.getMergedResolutions(r5);
        r5.insertOption(ImageOutputConfig.OPTION_CUSTOM_ORDERED_RESOLUTIONS, r0);
        r5.insertOption(UseCaseConfig.OPTION_SURFACE_OCCUPANCY_PRIORITY, Integer.valueOf(getHighestSurfacePriority(this.mChildrenConfigs)));
        DynamicRange r02 = DynamicRangeUtils.resolveDynamicRange(this.mChildrenConfigs);
        if (r02 == null) goto L14;
        r5.insertOption(ImageInputConfig.OPTION_INPUT_DYNAMIC_RANGE, r02);
        r5.insertOption(UseCaseConfig.OPTION_TARGET_FRAME_RATE, resolveTargetFrameRate(this.mChildrenConfigs));
        Iterator<UseCase> r03 = this.mChildren.iterator();
    L6:
        if (r03.hasNext() == false) goto L13;
        UseCase r1 = r03.next();
        UseCaseConfig<?> r12 = this.mChildrenConfigsMap.get(r1);
        Objects.requireNonNull(r12);
        UseCaseConfig<?> r13 = r12;
        if (r13.getVideoStabilizationMode() == 0) goto L11;
        r5.insertOption(UseCaseConfig.OPTION_VIDEO_STABILIZATION_MODE, Integer.valueOf(r13.getVideoStabilizationMode()));
    L11:
        if (r13.getPreviewStabilizationMode() == 0) goto L6;
        r5.insertOption(UseCaseConfig.OPTION_PREVIEW_STABILIZATION_MODE, Integer.valueOf(r13.getPreviewStabilizationMode()));
        goto L6
    L13:
        return;
    L14:
        f$$ExternalSyntheticBUOutline1.m("Failed to merge child dynamic ranges, can not find a dynamic range that satisfies all children.");
    }

    public void notifyCameraControlReady() {
        Iterator<UseCase> r1 = this.mChildren.iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        r1.next().onCameraControlReady();
        goto L4
    }

    public void notifySessionStart() {
        Iterator<UseCase> r1 = this.mChildren.iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        r1.next().onSessionStart();
        goto L4
    }

    public void notifySessionStop() {
        Iterator<UseCase> r1 = this.mChildren.iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        r1.next().onSessionStop();
        goto L4
    }

    @Override // androidx.camera.core.UseCase.StateChangeCallback
    public void onUseCaseActive(UseCase r3) {
        Threads.checkMainThread();
        if (isUseCaseActive(r3) == true) goto L10;
        this.mChildrenActiveState.put(r3, Boolean.TRUE);
        DeferrableSurface r0 = getChildSurface(r3);
        if (r0 == null) goto L9;
        forceSetProvider(getUseCaseEdge(r3), r0, r3.getSessionConfig());
        return;
    L9:
        return;
    }

    @Override // androidx.camera.core.UseCase.StateChangeCallback
    public void onUseCaseInactive(UseCase r3) {
        Threads.checkMainThread();
        if (isUseCaseActive(r3) == true) goto L5;
        return;
    L5:
        this.mChildrenActiveState.put(r3, Boolean.FALSE);
        getUseCaseEdge(r3).disconnect();
    }

    @Override // androidx.camera.core.UseCase.StateChangeCallback
    public void onUseCaseReset(UseCase r2) {
        Threads.checkMainThread();
        SurfaceEdge r0 = getUseCaseEdge(r2);
        if (isUseCaseActive(r2) == false) goto L10;
        DeferrableSurface r1 = getChildSurface(r2);
        if (r1 == null) goto L9;
        forceSetProvider(r0, r1, r2.getSessionConfig());
        return;
    L9:
        return;
    }

    @Override // androidx.camera.core.UseCase.StateChangeCallback
    public void onUseCaseUpdated(UseCase r2) {
        Threads.checkMainThread();
        if (isUseCaseActive(r2) == true) goto L5;
        return;
    L5:
        SurfaceEdge r1 = getUseCaseEdge(r2);
        DeferrableSurface r0 = getChildSurface(r2);
        if (r0 == null) goto L9;
        forceSetProvider(r1, r0, r2.getSessionConfig());
        return;
    L9:
        r1.disconnect();
    }

    public void resetChildren() {
        Threads.checkMainThread();
        Iterator<UseCase> r0 = this.mChildren.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        onUseCaseReset(r0.next());
        goto L4
    }

    public void setChildrenEdges(Map<UseCase, SurfaceEdge> r3, Map<UseCase, Size> r4) {
        this.mChildrenEdges.clear();
        this.mChildrenEdges.putAll(r3);
        Iterator<Map.Entry<UseCase, SurfaceEdge>> r2 = this.mChildrenEdges.entrySet().iterator();
    L4:
        if (r2.hasNext() == false) goto L6;
        Map.Entry<UseCase, SurfaceEdge> r32 = r2.next();
        UseCase r0 = r32.getKey();
        SurfaceEdge r33 = r32.getValue();
        r0.setViewPortCropRect(r33.getCropRect());
        r0.setSensorToBufferTransformMatrix(r33.getSensorToBufferTransform());
        r0.updateSuggestedStreamSpec(getChildStreamSpec(r0, r33.getStreamSpec(), r4), null);
        r0.notifyState();
        goto L4
    }

    public void unbindChildren() {
        Iterator<UseCase> r0 = this.mChildren.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        UseCase r1 = r0.next();
        VirtualCamera r2 = this.mChildrenVirtualCameras.get(r1);
        Objects.requireNonNull(r2);
        r1.unbindFromCamera(r2);
        goto L4
    }
}
