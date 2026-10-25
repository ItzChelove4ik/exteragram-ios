package androidx.camera.core;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build;
import android.util.Range;
import android.util.Size;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.ImageInputConfig;
import androidx.camera.core.impl.ImageOutputConfig;
import androidx.camera.core.impl.MutableConfig;
import androidx.camera.core.impl.MutableOptionsBundle;
import androidx.camera.core.impl.OptionsBundle;
import androidx.camera.core.impl.PreviewConfig;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.StreamSpec;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.impl.capability.PreviewCapabilitiesImpl;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.internal.TargetConfig;
import androidx.camera.core.processing.SurfaceEdge;
import androidx.camera.core.processing.SurfaceProcessorNode;
import androidx.camera.core.resolutionselector.AspectRatioStrategy;
import androidx.camera.core.resolutionselector.ResolutionSelector;
import androidx.camera.core.resolutionselector.ResolutionStrategy;
import androidx.core.util.Preconditions;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executor;
import retrofit2.Utils$$ExternalSyntheticBUOutline2;

/* loaded from: classes3.dex */
public final class Preview extends UseCase {
    public static final Defaults DEFAULT_CONFIG = null;
    private static final Executor DEFAULT_SURFACE_PROVIDER_EXECUTOR = null;
    private SurfaceEdge mCameraEdge;
    private SessionConfig.CloseableErrorListener mCloseableErrorListener;
    SurfaceRequest mCurrentSurfaceRequest;
    private SurfaceProcessorNode mNode;
    SessionConfig.Builder mSessionConfigBuilder;
    private DeferrableSurface mSessionDeferrableSurface;
    private SurfaceProvider mSurfaceProvider;
    private Executor mSurfaceProviderExecutor;

    public static final class Builder implements UseCaseConfig.Builder<Preview, PreviewConfig, Builder>, ImageOutputConfig.Builder<Builder> {
        private final MutableOptionsBundle mMutableConfig;

        public Builder() {
            this(MutableOptionsBundle.create());
        }

        private Builder(MutableOptionsBundle r5) {
            this.mMutableConfig = r5;
            Class r0 = (Class) r5.retrieveOption(TargetConfig.OPTION_TARGET_CLASS, null);
            if (r0 != null) goto L5;
        L9:
            setCaptureType(UseCaseConfigFactory.CaptureType.PREVIEW);
            setTargetClass(Preview.class);
            Config.Option<Integer> r4 = ImageOutputConfig.OPTION_MIRROR_MODE;
            if (((Integer) r5.retrieveOption(r4, -1)).intValue() != (-1)) goto L13;
            r5.insertOption(r4, 2);
            return;
        L13:
            return;
        L5:
            if (r0.equals(Preview.class) == true) goto L9;
            Utils$$ExternalSyntheticBUOutline2.m("Invalid target class configuration for ", this, ": ", r0);
            throw null;
        }

        public static Builder fromConfig(Config r1) {
            return new Builder(MutableOptionsBundle.from(r1));
        }

        public Preview build() {
            PreviewConfig r1 = getUseCaseConfig();
            ImageOutputConfig.validateConfig(r1);
            return new Preview(r1);
        }

        @Override // androidx.camera.core.ExtendableBuilder
        public MutableConfig getMutableConfig() {
            return this.mMutableConfig;
        }

        @Override // androidx.camera.core.impl.UseCaseConfig.Builder
        public PreviewConfig getUseCaseConfig() {
            return new PreviewConfig(OptionsBundle.from(this.mMutableConfig));
        }

        @Override // androidx.camera.core.impl.UseCaseConfig.Builder
        public /* bridge */ /* synthetic */ UseCaseConfig getUseCaseConfig() {
            return getUseCaseConfig();
        }

        public Builder setCaptureType(UseCaseConfigFactory.CaptureType r3) {
            getMutableConfig().insertOption(UseCaseConfig.OPTION_CAPTURE_TYPE, r3);
            return this;
        }

        public Builder setDynamicRange(DynamicRange r3) {
            getMutableConfig().insertOption(ImageInputConfig.OPTION_INPUT_DYNAMIC_RANGE, r3);
            return this;
        }

        public Builder setHighResolutionDisabled(boolean r3) {
            getMutableConfig().insertOption(UseCaseConfig.OPTION_HIGH_RESOLUTION_DISABLED, Boolean.valueOf(r3));
            return this;
        }

        public Builder setMirrorMode(int r3) {
            if (Build.VERSION.SDK_INT < 33) goto L5;
            getMutableConfig().insertOption(ImageOutputConfig.OPTION_MIRROR_MODE, Integer.valueOf(r3));
        L5:
            return this;
        }

        public Builder setPreviewStabilizationEnabled(boolean r3) {
            MutableConfig r0 = getMutableConfig();
            Config.Option<Integer> r1 = UseCaseConfig.OPTION_PREVIEW_STABILIZATION_MODE;
            if (r3 == false) goto L5;
            int r32 = 2;
        L6:
            r0.insertOption(r1, Integer.valueOf(r32));
            return this;
        L5:
            r32 = 1;
            goto L6
        }

        public Builder setResolutionSelector(ResolutionSelector r3) {
            getMutableConfig().insertOption(ImageOutputConfig.OPTION_RESOLUTION_SELECTOR, r3);
            return this;
        }

        public Builder setSurfaceOccupancyPriority(int r3) {
            getMutableConfig().insertOption(UseCaseConfig.OPTION_SURFACE_OCCUPANCY_PRIORITY, Integer.valueOf(r3));
            return this;
        }

        @Deprecated
        public Builder setTargetAspectRatio(int r3) {
            if (r3 != (-1)) goto L5;
            r3 = 0;
        L5:
            getMutableConfig().insertOption(ImageOutputConfig.OPTION_TARGET_ASPECT_RATIO, Integer.valueOf(r3));
            return this;
        }

        public Builder setTargetClass(Class<Preview> r4) {
            getMutableConfig().insertOption(TargetConfig.OPTION_TARGET_CLASS, r4);
            if (getMutableConfig().retrieveOption(TargetConfig.OPTION_TARGET_NAME, null) != null) goto L5;
            setTargetName(r4.getCanonicalName() + "-" + UUID.randomUUID());
        L5:
            return this;
        }

        public Builder setTargetFrameRate(Range<Integer> r3) {
            getMutableConfig().insertOption(UseCaseConfig.OPTION_TARGET_FRAME_RATE, r3);
            return this;
        }

        public Builder setTargetName(String r3) {
            getMutableConfig().insertOption(TargetConfig.OPTION_TARGET_NAME, r3);
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.ImageOutputConfig.Builder
        @Deprecated
        public Builder setTargetResolution(Size r3) {
            getMutableConfig().insertOption(ImageOutputConfig.OPTION_TARGET_RESOLUTION, r3);
            return this;
        }

        @Override // androidx.camera.core.impl.ImageOutputConfig.Builder
        @Deprecated
        public /* bridge */ /* synthetic */ Builder setTargetResolution(Size r1) {
            return setTargetResolution(r1);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.ImageOutputConfig.Builder
        public Builder setTargetRotation(int r4) {
            getMutableConfig().insertOption(ImageOutputConfig.OPTION_TARGET_ROTATION, Integer.valueOf(r4));
            getMutableConfig().insertOption(ImageOutputConfig.OPTION_APP_TARGET_ROTATION, Integer.valueOf(r4));
            return this;
        }

        @Override // androidx.camera.core.impl.ImageOutputConfig.Builder
        public /* bridge */ /* synthetic */ Builder setTargetRotation(int r1) {
            return setTargetRotation(r1);
        }
    }

    public static final class Defaults {
        private static final PreviewConfig DEFAULT_CONFIG = null;
        private static final DynamicRange DEFAULT_DYNAMIC_RANGE = null;
        private static final ResolutionSelector DEFAULT_RESOLUTION_SELECTOR = null;

        static {
            ResolutionSelector r0 = new ResolutionSelector.Builder().setAspectRatioStrategy(AspectRatioStrategy.RATIO_4_3_FALLBACK_AUTO_STRATEGY).setResolutionStrategy(ResolutionStrategy.HIGHEST_AVAILABLE_STRATEGY).build();
            DEFAULT_RESOLUTION_SELECTOR = r0;
            DynamicRange r1 = DynamicRange.UNSPECIFIED;
            DEFAULT_DYNAMIC_RANGE = r1;
            DEFAULT_CONFIG = new Builder().setSurfaceOccupancyPriority(2).setTargetAspectRatio(0).setResolutionSelector(r0).setHighResolutionDisabled(true).setDynamicRange(r1).getUseCaseConfig();
        }

        public Defaults() {
        }

        public PreviewConfig getConfig() {
            return DEFAULT_CONFIG;
        }
    }

    public interface SurfaceProvider {
        void onSurfaceRequested(SurfaceRequest r1);
    }

    public static /* synthetic */ void $r8$lambda$762dDs35ABxrpJOuvYTWYx6zqRc(Preview r0, androidx.camera.core.impl.SessionConfig r1, SessionConfig.SessionError r2) {
        if (r0.getCamera() != null) goto L5;
        return;
    L5:
        r0.updateConfigAndOutput((PreviewConfig) r0.getCurrentConfig(), r0.getAttachedStreamSpec());
        r0.notifyReset();
    }

    public static /* synthetic */ void $r8$lambda$9rKe2IbowTTH7IX1gFEhZY0jxZg(SurfaceProvider r0, SurfaceRequest r1) {
        r0.onSurfaceRequested(r1);
    }

    static {
        DEFAULT_CONFIG = new Defaults();
        DEFAULT_SURFACE_PROVIDER_EXECUTOR = CameraXExecutors.mainThreadExecutor();
    }

    public Preview(PreviewConfig r1) {
        super(r1);
        this.mSurfaceProviderExecutor = DEFAULT_SURFACE_PROVIDER_EXECUTOR;
    }

    private void addCameraSurfaceAndErrorListener(SessionConfig.Builder r4, StreamSpec r5) {
        if (this.mSurfaceProvider == null) goto L5;
        r4.addSurface(this.mSessionDeferrableSurface, r5.getDynamicRange(), getPhysicalCameraId(), getMirrorModeInternal());
    L5:
        SessionConfig.CloseableErrorListener r52 = this.mCloseableErrorListener;
        if (r52 == null) goto L8;
        r52.close();
    L8:
        SessionConfig.CloseableErrorListener r53 = new SessionConfig.CloseableErrorListener(new Preview$$ExternalSyntheticLambda2(this));
        this.mCloseableErrorListener = r53;
        r4.setErrorListener(r53);
    }

    private void clearPipeline() {
        SessionConfig.CloseableErrorListener r0 = this.mCloseableErrorListener;
        if (r0 == null) goto L5;
        r0.close();
        this.mCloseableErrorListener = null;
    L5:
        DeferrableSurface r02 = this.mSessionDeferrableSurface;
        if (r02 == null) goto L8;
        r02.close();
        this.mSessionDeferrableSurface = null;
    L8:
        SurfaceProcessorNode r03 = this.mNode;
        if (r03 == null) goto L11;
        r03.release();
        this.mNode = null;
    L11:
        SurfaceEdge r04 = this.mCameraEdge;
        if (r04 == null) goto L14;
        r04.close();
        this.mCameraEdge = null;
    L14:
        SurfaceRequest r05 = this.mCurrentSurfaceRequest;
        if (r05 == null) goto L17;
        r05.clearTransformationInfoListener();
    L17:
        this.mCurrentSurfaceRequest = null;
    }

    private SessionConfig.Builder createPipeline(PreviewConfig r13, StreamSpec r14) {
        Threads.checkMainThread();
        CameraInternal r0 = getCamera();
        Objects.requireNonNull(r0);
        CameraInternal r02 = r0;
        clearPipeline();
        if (this.mCameraEdge != null) goto L5;
        boolean r1 = true;
    L6:
        Preconditions.checkState(r1);
        Matrix r6 = getSensorToBufferTransformMatrix();
        boolean r7 = r02.getHasTransform();
        Rect r8 = getCropRect(r14.getResolution());
        Objects.requireNonNull(r8);
        this.mCameraEdge = new SurfaceEdge(1, 34, r14, r6, r7, r8, getRelativeRotation(r02, isMirroringRequired(r02)), getAppTargetRotation(), shouldMirror(r02));
        getEffect();
        this.mCameraEdge.addOnInvalidatedListener(new Preview$$ExternalSyntheticLambda0(this));
        SurfaceRequest r142 = this.mCameraEdge.createSurfaceRequest(r02);
        this.mCurrentSurfaceRequest = r142;
        this.mSessionDeferrableSurface = r142.getDeferrableSurface();
        if (this.mSurfaceProvider == null) goto L9;
        sendSurfaceRequest();
    L9:
        SessionConfig.Builder r143 = SessionConfig.Builder.createFrom(r13, r14.getResolution());
        r143.setSessionType(r14.getSessionType());
        applyExpectedFrameRateRange(r143, r14);
        r143.setPreviewStabilization(r13.getPreviewStabilizationMode());
        if (r14.getImplementationOptions() == null) goto L12;
        r143.addImplementationOptions(r14.getImplementationOptions());
    L12:
        addCameraSurfaceAndErrorListener(r143, r14);
        return r143;
    L5:
        r1 = false;
        goto L6
    }

    private Rect getCropRect(Size r3) {
        if (getViewPortCropRect() != null) goto L5;
        if (r3 != null) goto L8;
        return null;
    L8:
        return new Rect(0, 0, r3.getWidth(), r3.getHeight());
    L5:
        return getViewPortCropRect();
    }

    public static PreviewCapabilities getPreviewCapabilities(CameraInfo r0) {
        return PreviewCapabilitiesImpl.from(r0);
    }

    private void sendSurfaceRequest() {
        sendTransformationInfoIfReady();
        SurfaceProvider r0 = (SurfaceProvider) Preconditions.checkNotNull(this.mSurfaceProvider);
        SurfaceRequest r1 = (SurfaceRequest) Preconditions.checkNotNull(this.mCurrentSurfaceRequest);
        this.mSurfaceProviderExecutor.execute(new Preview$$ExternalSyntheticLambda1(r0, r1));
    }

    private void sendTransformationInfoIfReady() {
        CameraInternal r0 = getCamera();
        SurfaceEdge r1 = this.mCameraEdge;
        if (r0 == null) goto L7;
        if (r1 == null) goto L8;
        r1.updateTransformation(getRelativeRotation(r0, isMirroringRequired(r0)), getAppTargetRotation());
        return;
    L8:
        return;
    }

    private boolean shouldMirror(CameraInternal r2) {
        if (r2.getHasTransform() == true) goto L5;
        return false;
    L5:
        if (isMirroringRequired(r2) == false) goto L10;
        return true;
    L10:
        return false;
    }

    private void updateConfigAndOutput(PreviewConfig r1, StreamSpec r2) {
        SessionConfig.Builder r12 = createPipeline(r1, r2);
        this.mSessionConfigBuilder = r12;
        updateSessionConfig(ImageCapture$$ExternalSyntheticBackport0.m(r12.build()));
    }

    @Override // androidx.camera.core.UseCase
    public UseCaseConfig<?> getDefaultConfig(boolean r4, UseCaseConfigFactory r5) {
        Defaults r0 = DEFAULT_CONFIG;
        Config r52 = r5.getConfig(r0.getConfig().getCaptureType(), 1);
        if (r4 == false) goto L5;
        r52 = Config.mergeConfigs(r52, r0.getConfig());
    L5:
        if (r52 != null) goto L9;
        return null;
    L9:
        return getUseCaseConfigBuilder(r52).getUseCaseConfig();
    }

    @Override // androidx.camera.core.UseCase
    public Set<Integer> getSupportedEffectTargets() {
        HashSet r1 = new HashSet();
        r1.add(1);
        return r1;
    }

    @Override // androidx.camera.core.UseCase
    public UseCaseConfig.Builder<?, ?, ?> getUseCaseConfigBuilder(Config r1) {
        return Builder.fromConfig(r1);
    }

    @Override // androidx.camera.core.UseCase
    public UseCaseConfig<?> onMergeConfig(CameraInfoInternal r2, UseCaseConfig.Builder<?, ?, ?> r3) {
        r3.getMutableConfig().insertOption(ImageInputConfig.OPTION_INPUT_FORMAT, 34);
        return r3.getUseCaseConfig();
    }

    @Override // androidx.camera.core.UseCase
    public StreamSpec onSuggestedStreamSpecImplementationOptionsUpdated(Config r2) {
        this.mSessionConfigBuilder.addImplementationOptions(r2);
        updateSessionConfig(ImageCapture$$ExternalSyntheticBackport0.m(this.mSessionConfigBuilder.build()));
        return getAttachedStreamSpec().toBuilder().setImplementationOptions(r2).build();
    }

    @Override // androidx.camera.core.UseCase
    public StreamSpec onSuggestedStreamSpecUpdated(StreamSpec r3, StreamSpec r4) {
        Logger.d("Preview", "onSuggestedStreamSpecUpdated: primaryStreamSpec = " + r3 + ", secondaryStreamSpec " + r4);
        updateConfigAndOutput((PreviewConfig) getCurrentConfig(), r3);
        return r3;
    }

    @Override // androidx.camera.core.UseCase
    public void onUnbind() {
        clearPipeline();
    }

    public void setSurfaceProvider(SurfaceProvider r2) {
        setSurfaceProvider(DEFAULT_SURFACE_PROVIDER_EXECUTOR, r2);
    }

    public void setSurfaceProvider(Executor r1, SurfaceProvider r2) {
        Threads.checkMainThread();
        if (r2 != null) goto L6;
        this.mSurfaceProvider = null;
        notifyInactive();
        return;
    L6:
        this.mSurfaceProvider = r2;
        this.mSurfaceProviderExecutor = r1;
        if (getAttachedSurfaceResolution() == null) goto L9;
        updateConfigAndOutput((PreviewConfig) getCurrentConfig(), getAttachedStreamSpec());
        notifyReset();
    L9:
        notifyActive();
    }

    @Override // androidx.camera.core.UseCase
    public void setViewPortCropRect(Rect r1) {
        super.setViewPortCropRect(r1);
        sendTransformationInfoIfReady();
    }

    public String toString() {
        return "Preview:" + getName();
    }
}
