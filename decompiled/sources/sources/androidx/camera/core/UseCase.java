package androidx.camera.core;

import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Range;
import android.util.Size;
import androidx.camera.core.RotationProvider;
import androidx.camera.core.featuregroup.GroupableFeature;
import androidx.camera.core.featuregroup.impl.feature.DynamicRangeFeature;
import androidx.camera.core.featuregroup.impl.feature.FpsRangeFeature;
import androidx.camera.core.featuregroup.impl.feature.VideoStabilizationFeature;
import androidx.camera.core.impl.CameraControlInternal;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.ImageInputConfig;
import androidx.camera.core.impl.ImageOutputConfig;
import androidx.camera.core.impl.MutableOptionsBundle;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.StreamSpec;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.impl.stabilization.VideoStabilization;
import androidx.camera.core.impl.utils.TransformUtils;
import androidx.camera.core.impl.utils.UseCaseUtil;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.internal.TargetConfig;
import androidx.camera.core.internal.compat.quirk.AeFpsRangeQuirk;
import androidx.camera.core.internal.utils.UseCaseConfigUtil;
import androidx.camera.core.processing.TargetUtils;
import androidx.camera.core.resolutionselector.ResolutionSelector;
import androidx.core.util.Preconditions;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import okio.ByteString$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
public abstract class UseCase {
    private androidx.camera.core.impl.SessionConfig mAttachedSecondarySessionConfig;
    private androidx.camera.core.impl.SessionConfig mAttachedSessionConfig;
    private StreamSpec mAttachedStreamSpec;
    private CameraInternal mCamera;
    private UseCaseConfig<?> mCameraConfig;
    private final Object mCameraLock;
    private UseCaseConfig<?> mCurrentConfig;
    private UseCaseConfig<?> mExtendedConfig;
    private Set<GroupableFeature> mFeatureGroup;
    private boolean mInSession;
    private String mPhysicalCameraId;
    private final RotationProvider.Listener mRotationListener;
    private RotationProvider mRotationProvider;
    private final Object mRotationProviderLock;
    private CameraInternal mSecondaryCamera;
    private Matrix mSensorToBufferTransformMatrix;
    private State mState;
    private final Set<StateChangeCallback> mStateChangeCallbacks;
    private UseCaseConfig<?> mUseCaseConfig;
    private Rect mViewPortCropRect;

    /* renamed from: androidx.camera.core.UseCase$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$camera$core$impl$stabilization$VideoStabilization = null;

        static {
            int[] r0 = new int[VideoStabilization.values().length];
            $SwitchMap$androidx$camera$core$impl$stabilization$VideoStabilization = r0;
            r0[VideoStabilization.UNSPECIFIED.ordinal()] = 1;     // Catch: NoSuchFieldError -> L8
        L12:
            $SwitchMap$androidx$camera$core$impl$stabilization$VideoStabilization[VideoStabilization.OFF.ordinal()] = 2;     // Catch: NoSuchFieldError -> L9
        L14:
            $SwitchMap$androidx$camera$core$impl$stabilization$VideoStabilization[VideoStabilization.ON.ordinal()] = 3;     // Catch: NoSuchFieldError -> L10
        L18:
            $SwitchMap$androidx$camera$core$impl$stabilization$VideoStabilization[VideoStabilization.PREVIEW.ordinal()] = 4;     // Catch: NoSuchFieldError -> L11
            return;
        }
    }

    public enum State extends Enum<State> {
        private static final /* synthetic */ State[] $VALUES = null;
        public static final State ACTIVE = null;
        public static final State INACTIVE = null;

        private static /* synthetic */ State[] $values() {
            return new State[]{ACTIVE, INACTIVE};
        }

        static {
            ACTIVE = new State("ACTIVE", 0);
            INACTIVE = new State("INACTIVE", 1);
            $VALUES = $values();
        }

        State(String r1, int r2) {
        }

        public static State valueOf(String r1) {
            return (State) Enum.valueOf(State.class, r1);
        }

        public static State[] values() {
            return (State[]) $VALUES.clone();
        }
    }

    public interface StateChangeCallback {
        void onUseCaseActive(UseCase r1);

        void onUseCaseInactive(UseCase r1);

        void onUseCaseReset(UseCase r1);

        void onUseCaseUpdated(UseCase r1);
    }

    public UseCase(UseCaseConfig<?> r2) {
        this.mInSession = false;
        this.mStateChangeCallbacks = new HashSet();
        this.mCameraLock = new Object();
        this.mRotationProviderLock = new Object();
        this.mState = State.INACTIVE;
        this.mSensorToBufferTransformMatrix = new Matrix();
        this.mRotationProvider = null;
        this.mRotationListener = new UseCase$$ExternalSyntheticLambda1(this);
        this.mAttachedSessionConfig = androidx.camera.core.impl.SessionConfig.defaultEmptySessionConfig();
        this.mAttachedSecondarySessionConfig = androidx.camera.core.impl.SessionConfig.defaultEmptySessionConfig();
        this.mUseCaseConfig = r2;
        this.mCurrentConfig = r2;
    }

    private void addStateChangeCallback(StateChangeCallback r1) {
        this.mStateChangeCallbacks.add(r1);
    }

    private void applyFeatureGroupToConfig(MutableOptionsBundle r12) {
        Logger.d("UseCase", "applyFeaturesToConfig: mFeatureGroup = " + this.mFeatureGroup + ", this = " + this);
        Set<GroupableFeature> r5 = this.mFeatureGroup;
        if (r5 == null) goto L49;
        DynamicRange r6 = DynamicRangeFeature.DEFAULT_DYNAMIC_RANGE;
        Range<Integer> r7 = StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED;
        VideoStabilization r8 = VideoStabilizationFeature.DEFAULT_STABILIZATION;
        Iterator<GroupableFeature> r52 = r5.iterator();
    L7:
        if (r52.hasNext() == false) goto L18;
        GroupableFeature r9 = r52.next();
        if ((r9 instanceof DynamicRangeFeature) == true) goto L10;
        if ((r9 instanceof FpsRangeFeature) == true) goto L13;
        if ((r9 instanceof VideoStabilizationFeature) == false) goto L7;
        r8 = ((VideoStabilizationFeature) r9).getVideoStabilization();
        goto L7
    L13:
        FpsRangeFeature r92 = (FpsRangeFeature) r9;
        r7 = new Range(Integer.valueOf(r92.getMinFps()), Integer.valueOf(r92.getMaxFps()));
        goto L7
    L10:
        r6 = ((DynamicRangeFeature) r9).getDynamicRange();
        goto L7
    L18:
        if ((this instanceof Preview) == false) goto L20;
    L21:
        r12.insertOption(ImageInputConfig.OPTION_INPUT_DYNAMIC_RANGE, r6);
    L22:
        r12.insertOption(UseCaseConfig.OPTION_TARGET_FRAME_RATE, r7);
        int r11 = AnonymousClass1.$SwitchMap$androidx$camera$core$impl$stabilization$VideoStabilization[r8.ordinal()];
        if (r11 == 1) goto L36;
        if (r11 != 2) goto L26;
        r12.insertOption(UseCaseConfig.OPTION_PREVIEW_STABILIZATION_MODE, 1);
        r12.insertOption(UseCaseConfig.OPTION_VIDEO_STABILIZATION_MODE, 1);
        return;
    L26:
        if (r11 != 3) goto L28;
        r12.insertOption(UseCaseConfig.OPTION_PREVIEW_STABILIZATION_MODE, 0);
        r12.insertOption(UseCaseConfig.OPTION_VIDEO_STABILIZATION_MODE, 2);
        return;
    L28:
        if (r11 == 4) goto L30;
        return;
    L30:
        r12.insertOption(UseCaseConfig.OPTION_PREVIEW_STABILIZATION_MODE, 2);
        r12.insertOption(UseCaseConfig.OPTION_VIDEO_STABILIZATION_MODE, 0);
        return;
    L36:
        r12.insertOption(UseCaseConfig.OPTION_PREVIEW_STABILIZATION_MODE, 0);
        r12.insertOption(UseCaseConfig.OPTION_VIDEO_STABILIZATION_MODE, 0);
        return;
    L20:
        if (UseCaseUtil.isVideoCapture(this) == false) goto L22;
    }

    private void removeStateChangeCallback(StateChangeCallback r1) {
        this.mStateChangeCallbacks.remove(r1);
    }

    public void applyExpectedFrameRateRange(SessionConfig.Builder r4, StreamSpec r5) {
        if (StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED.equals(r5.getExpectedFrameRateRange()) == true) goto L6;
        r4.setExpectedFrameRateRange(r5.getExpectedFrameRateRange());
        return;
    L6:
        Object r52 = this.mCameraLock;
        monitor-enter(r52);
        List r3 = ((CameraInternal) Preconditions.checkNotNull(this.mCamera)).getCameraInfoInternal().getCameraQuirks().getAll(AeFpsRangeQuirk.class);     // Catch: Throwable -> L15
        boolean r2 = true;
        if (r3.size() <= 1) goto L12;
        r2 = false;
    L12:
        Preconditions.checkArgument(r2, "There should not have more than one AeFpsRangeQuirk.");     // Catch: Throwable -> L15
        if (r3.isEmpty() == true) goto L17;
        r4.setExpectedFrameRateRange(((AeFpsRangeQuirk) r3.get(0)).getTargetAeFpsRange());     // Catch: Throwable -> L15
    L17:
        monitor-exit(r52);     // Catch: Throwable -> L15
        return;
    L15:
        th = move-exception;
        throw th;
    }

    @SuppressLint({"WrongConstant"})
    public final void bindToCamera(CameraInternal r2, CameraInternal r3, UseCaseConfig<?> r4, UseCaseConfig<?> r5) {
        Object r0 = this.mCameraLock;
        monitor-enter(r0);
        this.mCamera = r2;     // Catch: Throwable -> L7
        this.mSecondaryCamera = r3;     // Catch: Throwable -> L7
        addStateChangeCallback(r2);     // Catch: Throwable -> L7
        if (r3 == null) goto L9;
        addStateChangeCallback(r3);     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
        this.mExtendedConfig = r4;
        this.mCameraConfig = r5;
        this.mCurrentConfig = mergeConfigs(r2.getCameraInfoInternal(), this.mExtendedConfig, this.mCameraConfig);
        Object r22 = this.mRotationProviderLock;
        monitor-enter(r22);
        RotationProvider r32 = this.mRotationProvider;     // Catch: Throwable -> L15
        if (r32 == null) goto L17;
        r32.addListener(CameraXExecutors.mainThreadExecutor(), this.mRotationListener);     // Catch: Throwable -> L15
    L17:
        monitor-exit(r22);     // Catch: Throwable -> L15
        onBind();
        return;
    L15:
        th = move-exception;
        throw th;
    L7:
        th = move-exception;
        throw th;
    }

    public UseCaseConfig<?> getAppConfig() {
        return this.mUseCaseConfig;
    }

    public int getAppTargetRotation() {
        return ((ImageOutputConfig) this.mCurrentConfig).getAppTargetRotation(-1);
    }

    public StreamSpec getAttachedStreamSpec() {
        return this.mAttachedStreamSpec;
    }

    public Size getAttachedSurfaceResolution() {
        StreamSpec r0 = this.mAttachedStreamSpec;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getResolution();
    }

    public CameraInternal getCamera() {
        Object r0 = this.mCameraLock;
        monitor-enter(r0);
        CameraInternal r1 = this.mCamera;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public CameraControlInternal getCameraControl() {
        Object r0 = this.mCameraLock;
        monitor-enter(r0);
        CameraInternal r1 = this.mCamera;     // Catch: Throwable -> L9
        if (r1 != null) goto L11;
        CameraControlInternal r12 = CameraControlInternal.DEFAULT_EMPTY_INSTANCE;     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return r12;
    L11:
        CameraControlInternal r13 = r1.getCameraControlInternal();     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return r13;
    L9:
        th = move-exception;
        throw th;
    }

    public String getCameraId() {
        return ((CameraInternal) Preconditions.checkNotNull(getCamera(), "No camera attached to use case: " + this)).getCameraInfoInternal().getCameraId();
    }

    public UseCaseConfig<?> getCurrentConfig() {
        return this.mCurrentConfig;
    }

    public abstract UseCaseConfig<?> getDefaultConfig(boolean r1, UseCaseConfigFactory r2);

    public CameraEffect getEffect() {
        return null;
    }

    public Set<GroupableFeature> getFeatureGroup() {
        return this.mFeatureGroup;
    }

    public int getImageFormat() {
        return this.mCurrentConfig.getInputFormat();
    }

    public int getMirrorModeInternal() {
        return ((ImageOutputConfig) this.mCurrentConfig).getMirrorMode(-1);
    }

    public String getName() {
        String r3 = this.mCurrentConfig.getTargetName("<UnknownUseCase-" + hashCode() + ">");
        Objects.requireNonNull(r3);
        return r3;
    }

    public String getPhysicalCameraId() {
        return this.mPhysicalCameraId;
    }

    public int getRelativeRotation(CameraInternal r2) {
        return getRelativeRotation(r2, false);
    }

    public int getRelativeRotation(CameraInternal r2, boolean r3) {
        int r1 = r2.getCameraInfoInternal().getSensorRotationDegrees(getTargetRotationInternal());
        if (r2.getHasTransform() == true) goto L7;
        if (r3 == true) goto L6;
        return r1;
    L6:
        return TransformUtils.within360(-r1);
    L7:
        return r1;
    }

    public CameraInternal getSecondaryCamera() {
        Object r0 = this.mCameraLock;
        monitor-enter(r0);
        CameraInternal r1 = this.mSecondaryCamera;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public String getSecondaryCameraId() {
        if (getSecondaryCamera() != null) goto L7;
        return null;
    L7:
        return getSecondaryCamera().getCameraInfoInternal().getCameraId();
    }

    public androidx.camera.core.impl.SessionConfig getSecondarySessionConfig() {
        return this.mAttachedSecondarySessionConfig;
    }

    public Matrix getSensorToBufferTransformMatrix() {
        return this.mSensorToBufferTransformMatrix;
    }

    public androidx.camera.core.impl.SessionConfig getSessionConfig() {
        return this.mAttachedSessionConfig;
    }

    public Set<DynamicRange> getSupportedDynamicRanges(CameraInfoInternal r1) {
        return null;
    }

    public Set<Integer> getSupportedEffectTargets() {
        return Collections.EMPTY_SET;
    }

    @SuppressLint({"WrongConstant"})
    public int getTargetRotationInternal() {
        return ((ImageOutputConfig) this.mCurrentConfig).getTargetRotation(0);
    }

    public abstract UseCaseConfig.Builder<?, ?, ?> getUseCaseConfigBuilder(Config r1);

    public Rect getViewPortCropRect() {
        return this.mViewPortCropRect;
    }

    public boolean isAutoRotationSupported() {
        return false;
    }

    public boolean isEffectTargetsSupported(int r2) {
        Iterator<Integer> r1 = getSupportedEffectTargets().iterator();
    L4:
        if (r1.hasNext() == false) goto L9;
        if (TargetUtils.isSuperset(r2, r1.next().intValue()) == false) goto L4;
        return true;
    L9:
        return false;
    }

    public boolean isMirroringRequired(CameraInternal r2) {
        int r1 = getMirrorModeInternal();
        if (r1 == (-1)) goto L14;
        if (r1 != 0) goto L6;
        return false;
    L6:
        if (r1 != 1) goto L8;
        return true;
    L8:
        if (r1 == 2) goto L10;
        UseCase$$ExternalSyntheticBUOutline0.m("Unknown mirrorMode: ", r1);
        return false;
    L10:
        return r2.isFrontFacing();
    L14:
        return false;
    }

    public UseCaseConfig<?> mergeConfigs(CameraInfoInternal r5, UseCaseConfig<?> r6, UseCaseConfig<?> r7) {
        if (r7 == null) goto L4;
        MutableOptionsBundle r72 = MutableOptionsBundle.from(r7);
        r72.removeOption(TargetConfig.OPTION_TARGET_NAME);
    L6:
        if (this.mUseCaseConfig.containsOption(ImageOutputConfig.OPTION_TARGET_ASPECT_RATIO) == false) goto L8;
    L9:
        Config.Option<ResolutionSelector> r0 = ImageOutputConfig.OPTION_RESOLUTION_SELECTOR;
        if (r72.containsOption(r0) == false) goto L12;
        r72.removeOption(r0);
    L12:
        UseCaseConfig<?> r02 = this.mUseCaseConfig;
        Config.Option<ResolutionSelector> r1 = ImageOutputConfig.OPTION_RESOLUTION_SELECTOR;
        if (r02.containsOption(r1) == false) goto L19;
        Config.Option<Size> r03 = ImageOutputConfig.OPTION_MAX_RESOLUTION;
        if (r72.containsOption(r03) == false) goto L19;
        if (((ResolutionSelector) this.mUseCaseConfig.retrieveOption(r1)).getResolutionStrategy() == null) goto L19;
        r72.removeOption(r03);
    L19:
        Iterator<Config.Option<?>> r04 = this.mUseCaseConfig.listOptions().iterator();
    L21:
        if (r04.hasNext() == false) goto L23;
        Config.Option<?> r12 = r04.next();
        Config.mergeOptionValue(r72, r72, this.mUseCaseConfig, r12);
        goto L21
    L23:
        if (r6 == null) goto L32;
        Iterator<Config.Option<?>> r05 = r6.listOptions().iterator();
    L26:
        if (r05.hasNext() == false) goto L32;
        Config.Option<?> r13 = r05.next();
        if (r13.getId().equals(TargetConfig.OPTION_TARGET_NAME.getId()) == true) goto L26;
        Config.mergeOptionValue(r72, r72, r6, r13);
    L32:
        if (r72.containsOption(ImageOutputConfig.OPTION_TARGET_RESOLUTION) == false) goto L36;
        Config.Option<Integer> r62 = ImageOutputConfig.OPTION_TARGET_ASPECT_RATIO;
        if (r72.containsOption(r62) == false) goto L36;
        r72.removeOption(r62);
    L36:
        Config.Option<ResolutionSelector> r63 = ImageOutputConfig.OPTION_RESOLUTION_SELECTOR;
        if (r72.containsOption(r63) == true) goto L39;
    L41:
        applyFeatureGroupToConfig(r72);
        return onMergeConfig(r5, getUseCaseConfigBuilder(r72));
    L39:
        if (((ResolutionSelector) r72.retrieveOption(r63)).getAllowedResolutionMode() == 0) goto L41;
        r72.insertOption(UseCaseConfig.OPTION_ZSL_DISABLED, Boolean.TRUE);
        goto L41
    L8:
        if (this.mUseCaseConfig.containsOption(ImageOutputConfig.OPTION_TARGET_RESOLUTION) == false) goto L12;
    L4:
        r72 = MutableOptionsBundle.create();
        goto L6
    }

    public final void notifyActive() {
        this.mState = State.ACTIVE;
        notifyState();
    }

    public final void notifyInactive() {
        this.mState = State.INACTIVE;
        notifyState();
    }

    public final void notifyReset() {
        Iterator<StateChangeCallback> r0 = this.mStateChangeCallbacks.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        r0.next().onUseCaseReset(this);
        goto L4
    }

    public final void notifyState() {
        int r0 = this.mState.ordinal();
        if (r0 != 0) goto L5;
        Iterator<StateChangeCallback> r02 = this.mStateChangeCallbacks.iterator();
    L13:
        if (r02.hasNext() == false) goto L19;
        r02.next().onUseCaseActive(this);
        goto L13
    L19:
        return;
    L5:
        if (r0 != 1) goto L15;
        Iterator<StateChangeCallback> r03 = this.mStateChangeCallbacks.iterator();
    L9:
        if (r03.hasNext() == false) goto L18;
        r03.next().onUseCaseInactive(this);
        goto L9
    L18:
        return;
    }

    public void onBind() {
    }

    public void onCameraControlReady() {
    }

    public UseCaseConfig<?> onMergeConfig(CameraInfoInternal r1, UseCaseConfig.Builder<?, ?, ?> r2) {
        return r2.getUseCaseConfig();
    }

    public void onProviderRotationChanged(int r1) {
        setTargetRotationInternal(r1);
    }

    public void onSessionStart() {
        this.mInSession = true;
    }

    public void onSessionStop() {
        this.mInSession = false;
    }

    public StreamSpec onSuggestedStreamSpecImplementationOptionsUpdated(Config r1) {
        StreamSpec r0 = this.mAttachedStreamSpec;
        if (r0 != null) goto L5;
        ByteString$$ExternalSyntheticBUOutline0.m("Attempt to update the implementation options for a use case without attached stream specifications.");
        return null;
    L5:
        return r0.toBuilder().setImplementationOptions(r1).build();
    }

    public abstract StreamSpec onSuggestedStreamSpecUpdated(StreamSpec r1, StreamSpec r2);

    public void onUnbind() {
    }

    public void setEffect(CameraEffect r1) {
        Preconditions.checkArgument(true);
    }

    public void setFeatureGroup(Set<GroupableFeature> r2) {
        if (r2 == null) goto L4;
        HashSet r0 = new HashSet(r2);
    L5:
        this.mFeatureGroup = r0;
        return;
    L4:
        r0 = null;
        goto L5
    }

    public void setPhysicalCameraId(String r1) {
        this.mPhysicalCameraId = r1;
    }

    public void setRotationProvider(RotationProvider r2) {
        Object r0 = this.mRotationProviderLock;
        monitor-enter(r0);
        this.mRotationProvider = r2;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public void setSensorToBufferTransformMatrix(Matrix r2) {
        this.mSensorToBufferTransformMatrix = new Matrix(r2);
    }

    public boolean setTargetRotationInternal(int r3) {
        int r0 = ((ImageOutputConfig) getCurrentConfig()).getTargetRotation(-1);
        if (r0 == (-1)) goto L8;
        if (r0 != r3) goto L8;
        return false;
    L8:
        UseCaseConfig.Builder<?, ?, ?> r02 = getUseCaseConfigBuilder(this.mUseCaseConfig);
        UseCaseConfigUtil.updateTargetRotationAndRelatedConfigs(r02, r3);
        this.mUseCaseConfig = r02.getUseCaseConfig();
        CameraInternal r32 = getCamera();
        if (r32 != null) goto L11;
        this.mCurrentConfig = this.mUseCaseConfig;
        return true;
    L11:
        this.mCurrentConfig = mergeConfigs(r32.getCameraInfoInternal(), this.mExtendedConfig, this.mCameraConfig);
        return true;
    }

    public void setViewPortCropRect(Rect r1) {
        this.mViewPortCropRect = r1;
    }

    public final void unbindFromCamera(CameraInternal r4) {
        onUnbind();
        Object r0 = this.mCameraLock;
        monitor-enter(r0);
        CameraInternal r1 = this.mCamera;     // Catch: Throwable -> L7
        if (r4 != r1) goto L9;
        removeStateChangeCallback(r1);     // Catch: Throwable -> L7
        this.mCamera = null;     // Catch: Throwable -> L7
    L9:
        CameraInternal r12 = this.mSecondaryCamera;     // Catch: Throwable -> L7
        if (r4 != r12) goto L12;
        removeStateChangeCallback(r12);     // Catch: Throwable -> L7
        this.mSecondaryCamera = null;     // Catch: Throwable -> L7
    L12:
        monitor-exit(r0);     // Catch: Throwable -> L7
        Object r42 = this.mRotationProviderLock;
        monitor-enter(r42);
        RotationProvider r02 = this.mRotationProvider;     // Catch: Throwable -> L18
        if (r02 == null) goto L20;
        r02.removeListener(this.mRotationListener);     // Catch: Throwable -> L18
    L20:
        monitor-exit(r42);     // Catch: Throwable -> L18
        this.mAttachedStreamSpec = null;
        this.mViewPortCropRect = null;
        this.mCurrentConfig = this.mUseCaseConfig;
        this.mExtendedConfig = null;
        this.mCameraConfig = null;
        return;
    L18:
        th = move-exception;
        throw th;
    L7:
        th = move-exception;
        throw th;
    }

    public void updateSessionConfig(List<androidx.camera.core.impl.SessionConfig> r4) {
        if (r4.isEmpty() == true) goto L17;
        this.mAttachedSessionConfig = r4.get(0);
        if (r4.size() <= 1) goto L8;
        this.mAttachedSecondarySessionConfig = r4.get(1);
    L8:
        Iterator<androidx.camera.core.impl.SessionConfig> r42 = r4.iterator();
    L10:
        if (r42.hasNext() == false) goto L24;
        Iterator<DeferrableSurface> r0 = r42.next().getSurfaces().iterator();
    L13:
        if (r0.hasNext() == false) goto L10;
        DeferrableSurface r1 = r0.next();
        if (r1.getContainerClass() != null) goto L13;
        r1.setContainerClass(getClass());
        goto L13
    L24:
        return;
    }

    public void updateSuggestedStreamSpec(StreamSpec r1, StreamSpec r2) {
        this.mAttachedStreamSpec = onSuggestedStreamSpecUpdated(r1, r2);
    }

    public void updateSuggestedStreamSpecImplementationOptions(Config r1) {
        this.mAttachedStreamSpec = onSuggestedStreamSpecImplementationOptionsUpdated(r1);
    }
}
