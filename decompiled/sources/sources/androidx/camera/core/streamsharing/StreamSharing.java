package androidx.camera.core.streamsharing;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Log;
import android.util.Size;
import androidx.camera.core.CompositionSettings;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.ImageCapture$$ExternalSyntheticBackport0;
import androidx.camera.core.Logger;
import androidx.camera.core.UseCase;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.ImageInputConfig;
import androidx.camera.core.impl.ImageOutputConfig;
import androidx.camera.core.impl.MutableConfig;
import androidx.camera.core.impl.MutableOptionsBundle;
import androidx.camera.core.impl.OptionsBundle;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.StreamSpec;
import androidx.camera.core.impl.StreamUseCase;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.processing.DefaultSurfaceProcessor;
import androidx.camera.core.processing.SurfaceEdge;
import androidx.camera.core.processing.SurfaceProcessorNode;
import androidx.camera.core.processing.concurrent.DualOutConfig;
import androidx.camera.core.processing.concurrent.DualSurfaceProcessor;
import androidx.camera.core.processing.concurrent.DualSurfaceProcessorNode;
import androidx.camera.core.processing.util.OutConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* loaded from: classes3.dex */
public class StreamSharing extends UseCase {
    private SurfaceEdge mCameraEdge;
    private SessionConfig.CloseableErrorListener mCloseableErrorListener;
    private final CompositionSettings mCompositionSettings;
    private final StreamSharingConfig mDefaultConfig;
    private SurfaceEdge mDualProcessedEdge;
    private SurfaceEdge mDualSharingInputEdgeForEffect;
    private DualSurfaceProcessorNode mDualSharingNode;
    private SurfaceProcessorNode mDualSharingNodeForEffect;
    private SurfaceProcessorNode mEffectNode;
    private SurfaceEdge mSecondaryCameraEdge;
    private final CompositionSettings mSecondaryCompositionSettings;
    SessionConfig.Builder mSecondarySessionConfigBuilder;
    private SurfaceEdge mSecondarySharingInputEdge;
    SessionConfig.Builder mSessionConfigBuilder;
    private SurfaceEdge mSharingInputEdge;
    private SurfaceProcessorNode mSharingNode;
    private final VirtualCameraAdapter mVirtualCameraAdapter;

    public interface Control {
    }

    public static /* synthetic */ void $r8$lambda$ii4n5MMe9ie3N3t9WQpFcwenlq4(StreamSharing r0, String r1, String r2, UseCaseConfig r3, StreamSpec r4, StreamSpec r5, SessionConfig r6, SessionConfig.SessionError r7) {
        if (r0.getCamera() != null) goto L5;
        return;
    L5:
        r0.clearPipeline();
        r0.updateSessionConfig(r0.createPipelineAndUpdateChildrenSpecs(r1, r2, r3, r4, r5));
        r0.notifyReset();
        r0.mVirtualCameraAdapter.resetChildren();
    }

    public StreamSharing(CameraInternal r2, CameraInternal r3, CompositionSettings r4, CompositionSettings r5, Set<UseCase> r6, UseCaseConfigFactory r7) {
        super(getDefaultConfig(r6));
        this.mDefaultConfig = getDefaultConfig(r6);
        this.mCompositionSettings = r4;
        this.mSecondaryCompositionSettings = r5;
        this.mVirtualCameraAdapter = new VirtualCameraAdapter(r2, r3, r6, r7, new StreamSharing$$ExternalSyntheticLambda1(this));
        updateFeatureGroup(r6);
    }

    private void addCameraErrorListener(SessionConfig.Builder r9, String r10, String r11, UseCaseConfig<?> r12, StreamSpec r13, StreamSpec r14) {
        SessionConfig.CloseableErrorListener r0 = this.mCloseableErrorListener;
        if (r0 == null) goto L5;
        r0.close();
    L5:
        SessionConfig.CloseableErrorListener r02 = new SessionConfig.CloseableErrorListener(new StreamSharing$$ExternalSyntheticLambda2(this, r10, r11, r12, r13, r14));
        this.mCloseableErrorListener = r02;
        r9.setErrorListener(r02);
    }

    private void clearPipeline() {
        SessionConfig.CloseableErrorListener r0 = this.mCloseableErrorListener;
        if (r0 == null) goto L5;
        r0.close();
        this.mCloseableErrorListener = null;
    L5:
        SurfaceEdge r02 = this.mCameraEdge;
        if (r02 == null) goto L8;
        r02.close();
        this.mCameraEdge = null;
    L8:
        SurfaceEdge r03 = this.mSecondaryCameraEdge;
        if (r03 == null) goto L11;
        r03.close();
        this.mSecondaryCameraEdge = null;
    L11:
        SurfaceEdge r04 = this.mSharingInputEdge;
        if (r04 == null) goto L14;
        r04.close();
        this.mSharingInputEdge = null;
    L14:
        SurfaceEdge r05 = this.mSecondarySharingInputEdge;
        if (r05 == null) goto L17;
        r05.close();
        this.mSecondarySharingInputEdge = null;
    L17:
        SurfaceEdge r06 = this.mDualProcessedEdge;
        if (r06 == null) goto L20;
        r06.close();
        this.mDualProcessedEdge = null;
    L20:
        SurfaceEdge r07 = this.mDualSharingInputEdgeForEffect;
        if (r07 == null) goto L23;
        r07.close();
        this.mDualSharingInputEdgeForEffect = null;
    L23:
        SurfaceProcessorNode r08 = this.mSharingNode;
        if (r08 == null) goto L26;
        r08.release();
        this.mSharingNode = null;
    L26:
        DualSurfaceProcessorNode r09 = this.mDualSharingNode;
        if (r09 == null) goto L29;
        r09.release();
        this.mDualSharingNode = null;
    L29:
        SurfaceProcessorNode r010 = this.mEffectNode;
        if (r010 == null) goto L32;
        r010.release();
        this.mEffectNode = null;
    L32:
        SurfaceProcessorNode r011 = this.mDualSharingNodeForEffect;
        if (r011 == null) goto L36;
        r011.release();
        this.mDualSharingNodeForEffect = null;
        return;
    }

    private DualSurfaceProcessorNode createDualSharingNode(CameraInternal r1, CameraInternal r2, StreamSpec r3, CompositionSettings r4, CompositionSettings r5) {
        return new DualSurfaceProcessorNode(r1, r2, DualSurfaceProcessor.Factory.newInstance(r3.getDynamicRange(), r4, r5), "StreamSharing");
    }

    private SurfaceProcessorNode createEffectOrCopyNode(CameraInternal r2, StreamSpec r3) {
        getEffect();
        return new SurfaceProcessorNode(r2, DefaultSurfaceProcessor.Factory.newInstance(r3.getDynamicRange()), "StreamSharing");
    }

    private List<SessionConfig> createPipelineAndUpdateChildrenSpecs(String r7, String r8, UseCaseConfig<?> r9, StreamSpec r10, StreamSpec r11) {
        Threads.checkMainThread();
        if (r11 != null) goto L6;
        SurfaceEdge r72 = createPrimaryCameraInputSurface(r7, r8, r9, r10, null);
        CameraInternal r6 = getCamera();
        Objects.requireNonNull(r6);
        SurfaceProcessorNode r62 = createEffectOrCopyNode(r6, r10);
        this.mSharingNode = r62;
        transformAndOutputToChildUseCases(r72, r62, false);
        return ImageCapture$$ExternalSyntheticBackport0.m(this.mSessionConfigBuilder.build());
    L6:
        SurfaceEdge r63 = createPrimaryCameraInputSurface(r7, r8, r9, r10, r11);
        SurfaceEdge r73 = createSecondaryCameraInputSurface(r7, r8, r9, r10, r11);
        DualSurfaceProcessorNode r82 = createDualSharingNode(getCamera(), getSecondaryCamera(), r10, this.mCompositionSettings, this.mSecondaryCompositionSettings);
        this.mDualSharingNode = r82;
        transformDualSurfacesAndOutputToChildUseCases(r63, r73, r82, r10);
        return StreamSharing$$ExternalSyntheticBackport0.m(this.mSessionConfigBuilder.build(), this.mSecondarySessionConfigBuilder.build());
    }

    private SurfaceEdge createPrimaryCameraInputSurface(String r12, String r13, UseCaseConfig<?> r14, StreamSpec r15, StreamSpec r16) {
        Matrix r5 = getSensorToBufferTransformMatrix();
        CameraInternal r2 = getCamera();
        Objects.requireNonNull(r2);
        boolean r6 = r2.getHasTransform();
        Rect r7 = getCropRect(r15.getResolution());
        Objects.requireNonNull(r7);
        CameraInternal r22 = getCamera();
        Objects.requireNonNull(r22);
        int r8 = getRelativeRotation(r22);
        CameraInternal r23 = getCamera();
        Objects.requireNonNull(r23);
        SurfaceEdge r1 = new SurfaceEdge(3, 34, r15, r5, r6, r7, r8, -1, isMirroringRequired(r23));
        this.mCameraEdge = r1;
        if (r13 == null) goto L5;
        boolean r24 = true;
    L6:
        CameraInternal r3 = getCamera();
        Objects.requireNonNull(r3);
        this.mSharingInputEdge = getSharingInputEdge(r1, r3, r24);
        SessionConfig.Builder r17 = createSessionConfigBuilder(this.mCameraEdge, r14, r15);
        this.mSessionConfigBuilder = r17;
        addCameraErrorListener(r17, r12, r13, r14, r15, r16);
        return this.mSharingInputEdge;
    L5:
        r24 = false;
        goto L6
    }

    private SurfaceEdge createSecondaryCameraInputSurface(String r12, String r13, UseCaseConfig<?> r14, StreamSpec r15, StreamSpec r16) {
        Matrix r5 = getSensorToBufferTransformMatrix();
        CameraInternal r2 = getSecondaryCamera();
        Objects.requireNonNull(r2);
        boolean r6 = r2.getHasTransform();
        Rect r7 = getCropRect(r16.getResolution());
        Objects.requireNonNull(r7);
        CameraInternal r22 = getSecondaryCamera();
        Objects.requireNonNull(r22);
        int r8 = getRelativeRotation(r22);
        CameraInternal r23 = getSecondaryCamera();
        Objects.requireNonNull(r23);
        SurfaceEdge r1 = new SurfaceEdge(3, 34, r16, r5, r6, r7, r8, -1, isMirroringRequired(r23));
        this.mSecondaryCameraEdge = r1;
        CameraInternal r24 = getSecondaryCamera();
        Objects.requireNonNull(r24);
        this.mSecondarySharingInputEdge = getSharingInputEdge(r1, r24, true);
        SessionConfig.Builder r17 = createSessionConfigBuilder(this.mSecondaryCameraEdge, r14, r16);
        this.mSecondarySessionConfigBuilder = r17;
        addCameraErrorListener(r17, r12, r13, r14, r15, r16);
        return this.mSecondarySharingInputEdge;
    }

    private SessionConfig.Builder createSessionConfigBuilder(SurfaceEdge r4, UseCaseConfig<?> r5, StreamSpec r6) {
        SessionConfig.Builder r52 = SessionConfig.Builder.createFrom(r5, r6.getResolution());
        propagateChildrenTemplate(r52);
        propagateChildrenCamera2Interop(r6.getResolution(), r52);
        r52.addSurface(r4.getDeferrableSurface(), r6.getDynamicRange(), null, -1);
        r52.addRepeatingCameraCaptureCallback(this.mVirtualCameraAdapter.getParentMetadataCallback());
        if (r6.getImplementationOptions() == null) goto L5;
        r52.addImplementationOptions(r6.getImplementationOptions());
    L5:
        r52.setSessionType(r6.getSessionType());
        applyExpectedFrameRateRange(r52, r6);
        return r52;
    }

    public static List<UseCaseConfigFactory.CaptureType> getCaptureTypes(UseCase r2) {
        ArrayList r0 = new ArrayList();
        if (isStreamSharing(r2) == false) goto L9;
        Iterator<UseCase> r22 = ((StreamSharing) r2).getChildren().iterator();
    L6:
        if (r22.hasNext() == false) goto L8;
        r0.add(r22.next().getCurrentConfig().getCaptureType());
        goto L6
    L8:
        return r0;
    L9:
        r0.add(r2.getCurrentConfig().getCaptureType());
        return r0;
    }

    private static int getChildTemplate(UseCase r0) {
        return r0.getCurrentConfig().getDefaultSessionConfig().getTemplateType();
    }

    private Rect getCropRect(Size r3) {
        if (getViewPortCropRect() == null) goto L7;
        return getViewPortCropRect();
    L7:
        return new Rect(0, 0, r3.getWidth(), r3.getHeight());
    }

    private static StreamSharingConfig getDefaultConfig(Set<UseCase> r5) {
        MutableConfig r0 = new StreamSharingBuilder().getMutableConfig();
        r0.insertOption(ImageInputConfig.OPTION_INPUT_FORMAT, 34);
        ArrayList r1 = new ArrayList();
        Iterator<UseCase> r52 = r5.iterator();
    L4:
        if (r52.hasNext() == false) goto L9;
        UseCase r2 = r52.next();
        if (r2.getCurrentConfig().containsOption(UseCaseConfig.OPTION_CAPTURE_TYPE) == true) goto L7;
        Log.e("StreamSharing", "A child does not have capture type.");
        goto L4
    L7:
        r1.add(r2.getCurrentConfig().getCaptureType());
        goto L4
    L9:
        r0.insertOption(StreamSharingConfig.OPTION_CAPTURE_TYPES, r1);
        r0.insertOption(ImageOutputConfig.OPTION_MIRROR_MODE, 2);
        r0.insertOption(UseCaseConfig.OPTION_STREAM_USE_CASE, StreamUseCase.PREVIEW_VIDEO_STILL);
        return new StreamSharingConfig(OptionsBundle.from(r0));
    }

    private SurfaceEdge getSharingInputEdge(SurfaceEdge r1, CameraInternal r2, boolean r3) {
        getEffect();
        return r1;
    }

    public static boolean isStreamSharing(UseCase r0) {
        return r0 instanceof StreamSharing;
    }

    private void propagateChildrenCamera2Interop(Size r3, SessionConfig.Builder r4) {
        Iterator<UseCase> r2 = getChildren().iterator();
    L4:
        if (r2.hasNext() == false) goto L6;
        SessionConfig r0 = SessionConfig.Builder.createFrom(r2.next().getCurrentConfig(), r3).build();
        r4.addAllRepeatingCameraCaptureCallbacks(r0.getRepeatingCameraCaptureCallbacks());
        r4.addAllCameraCaptureCallbacks(r0.getSingleCameraCaptureCallbacks());
        r4.addAllSessionStateCallbacks(r0.getSessionStateCallbacks());
        r4.addAllDeviceStateCallbacks(r0.getDeviceStateCallbacks());
        r4.addImplementationOptions(r0.getImplementationOptions());
        goto L4
    }

    private void propagateChildrenTemplate(SessionConfig.Builder r4) {
        Iterator<UseCase> r3 = getChildren().iterator();
        int r1 = -1;
    L4:
        if (r3.hasNext() == false) goto L6;
        r1 = SessionConfig.getHigherPriorityTemplateType(r1, getChildTemplate(r3.next()));
        goto L4
    L6:
        if (r1 == (-1)) goto L10;
        r4.setTemplateType(r1);
        return;
    }

    private void transformAndOutputToChildUseCases(SurfaceEdge r5, SurfaceProcessorNode r6, boolean r7) {
        if (getViewPortCropRect() == null) goto L5;
        boolean r0 = true;
    L6:
        Map<UseCase, OutConfig> r72 = this.mVirtualCameraAdapter.getChildrenOutConfigs(r5, getTargetRotationInternal(), r0, r7);
        SurfaceProcessorNode.Out r62 = r6.transform(SurfaceProcessorNode.In.of(r5, new ArrayList(r72.values())));
        HashMap r1 = new HashMap();
        Iterator<Map.Entry<UseCase, OutConfig>> r73 = r72.entrySet().iterator();
    L8:
        if (r73.hasNext() == false) goto L10;
        Map.Entry<UseCase, OutConfig> r2 = r73.next();
        r1.put(r2.getKey(), r62.get(r2.getValue()));
        goto L8
    L10:
        Map<UseCase, Size> r52 = this.mVirtualCameraAdapter.getSelectedChildSizes(r5, r0);
        this.mVirtualCameraAdapter.setChildrenEdges(r1, r52);
        return;
    L5:
        r0 = false;
        goto L6
    }

    private void transformDualSurfacesAndOutputToChildUseCases(SurfaceEdge r4, SurfaceEdge r5, DualSurfaceProcessorNode r6, StreamSpec r7) {
        getEffect();
        if (getViewPortCropRect() == null) goto L5;
        boolean r62 = true;
    L6:
        Map<UseCase, DualOutConfig> r72 = this.mVirtualCameraAdapter.getChildrenOutConfigs(r4, r5, getTargetRotationInternal(), r62);
        DualSurfaceProcessorNode.Out r52 = this.mDualSharingNode.transform(DualSurfaceProcessorNode.In.of(r4, r5, new ArrayList(r72.values())));
        HashMap r0 = new HashMap();
        Iterator<Map.Entry<UseCase, DualOutConfig>> r73 = r72.entrySet().iterator();
    L8:
        if (r73.hasNext() == false) goto L10;
        Map.Entry<UseCase, DualOutConfig> r1 = r73.next();
        r0.put(r1.getKey(), r52.get(r1.getValue()));
        goto L8
    L10:
        Map<UseCase, Size> r42 = this.mVirtualCameraAdapter.getSelectedChildSizes(r4, r62);
        this.mVirtualCameraAdapter.setChildrenEdges(r0, r42);
        return;
    L5:
        r62 = false;
        goto L6
    }

    public Set<UseCase> getChildren() {
        return this.mVirtualCameraAdapter.getChildren();
    }

    @Override // androidx.camera.core.UseCase
    public UseCaseConfig<?> getDefaultConfig(boolean r3, UseCaseConfigFactory r4) {
        Config r42 = r4.getConfig(this.mDefaultConfig.getCaptureType(), 1);
        if (r3 == false) goto L5;
        r42 = Config.mergeConfigs(r42, this.mDefaultConfig.getConfig());
    L5:
        if (r42 != null) goto L9;
        return null;
    L9:
        return getUseCaseConfigBuilder(r42).getUseCaseConfig();
    }

    @Override // androidx.camera.core.UseCase
    public Set<DynamicRange> getSupportedDynamicRanges(CameraInfoInternal r3) {
        Set<UseCase> r2 = getChildren();
        HashSet r1 = null;
        if (r2.isEmpty() == false) goto L5;
        return null;
    L5:
        Iterator<UseCase> r22 = r2.iterator();
    L7:
        if (r22.hasNext() == false) goto L14;
        Set<DynamicRange> r0 = r22.next().getSupportedDynamicRanges(r3);
        if (r0 == null) goto L7;
        if (r1 == null) goto L12;
        r1.retainAll(r0);
        goto L7
    L12:
        r1 = new HashSet(r0);
        goto L7
    L14:
        return r1;
    }

    @Override // androidx.camera.core.UseCase
    public Set<Integer> getSupportedEffectTargets() {
        HashSet r1 = new HashSet();
        r1.add(3);
        return r1;
    }

    @Override // androidx.camera.core.UseCase
    public UseCaseConfig.Builder<?, ?, ?> getUseCaseConfigBuilder(Config r1) {
        return new StreamSharingBuilder(MutableOptionsBundle.from(r1));
    }

    @Override // androidx.camera.core.UseCase
    public void onBind() {
        super.onBind();
        this.mVirtualCameraAdapter.bindChildren();
    }

    @Override // androidx.camera.core.UseCase
    public void onCameraControlReady() {
        super.onCameraControlReady();
        this.mVirtualCameraAdapter.notifyCameraControlReady();
    }

    @Override // androidx.camera.core.UseCase
    public UseCaseConfig<?> onMergeConfig(CameraInfoInternal r1, UseCaseConfig.Builder<?, ?, ?> r2) {
        this.mVirtualCameraAdapter.mergeChildrenConfigs(r2.getMutableConfig());
        return r2.getUseCaseConfig();
    }

    @Override // androidx.camera.core.UseCase
    public void onSessionStart() {
        super.onSessionStart();
        this.mVirtualCameraAdapter.notifySessionStart();
    }

    @Override // androidx.camera.core.UseCase
    public void onSessionStop() {
        super.onSessionStop();
        this.mVirtualCameraAdapter.notifySessionStop();
    }

    @Override // androidx.camera.core.UseCase
    public StreamSpec onSuggestedStreamSpecImplementationOptionsUpdated(Config r2) {
        this.mSessionConfigBuilder.addImplementationOptions(r2);
        updateSessionConfig(ImageCapture$$ExternalSyntheticBackport0.m(this.mSessionConfigBuilder.build()));
        return getAttachedStreamSpec().toBuilder().setImplementationOptions(r2).build();
    }

    @Override // androidx.camera.core.UseCase
    public StreamSpec onSuggestedStreamSpecUpdated(StreamSpec r9, StreamSpec r10) {
        Logger.d("StreamSharing", "onSuggestedStreamSpecUpdated: primaryStreamSpec = " + r9 + ", secondaryStreamSpec " + r10);
        updateSessionConfig(createPipelineAndUpdateChildrenSpecs(getCameraId(), getSecondaryCameraId(), getCurrentConfig(), r9, r10));
        notifyActive();
        return r9;
    }

    @Override // androidx.camera.core.UseCase
    public void onUnbind() {
        super.onUnbind();
        clearPipeline();
        this.mVirtualCameraAdapter.unbindChildren();
    }

    public void updateFeatureGroup(Set<UseCase> r1) {
        setFeatureGroup(r1.iterator().next().getFeatureGroup());
    }
}
