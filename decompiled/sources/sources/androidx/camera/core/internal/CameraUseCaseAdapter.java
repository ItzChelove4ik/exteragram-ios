package androidx.camera.core.internal;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import android.util.Range;
import android.util.Size;
import android.view.Surface;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraControl;
import androidx.camera.core.CameraEffect;
import androidx.camera.core.CameraIdentifier;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.CompositionSettings;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.Logger;
import androidx.camera.core.Preview;
import androidx.camera.core.SurfaceRequest;
import androidx.camera.core.UseCase;
import androidx.camera.core.ViewPort;
import androidx.camera.core.concurrent.CameraCoordinator;
import androidx.camera.core.featuregroup.GroupableFeature;
import androidx.camera.core.featuregroup.impl.ResolvedFeatureGroup;
import androidx.camera.core.impl.AdapterCameraInfo;
import androidx.camera.core.impl.AdapterCameraInternal;
import androidx.camera.core.impl.CameraConfig;
import androidx.camera.core.impl.CameraControlInternal;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.ImageCaptureConfig;
import androidx.camera.core.impl.MutableOptionsBundle;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.StreamSpec;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.impl.utils.UseCaseUtil;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.internal.compat.workaround.StreamSharingForceEnabler;
import androidx.camera.core.streamsharing.StreamSharing;
import androidx.core.util.Preconditions;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* loaded from: classes3.dex */
public final class CameraUseCaseAdapter implements Camera {
    private final List<UseCase> mAppUseCases;
    private boolean mAttached;
    private final CameraConfig mCameraConfig;
    private final CameraCoordinator mCameraCoordinator;
    private final CameraIdentifier mCameraIdentifier;
    private final AdapterCameraInternal mCameraInternal;
    private final List<UseCase> mCameraUseCases;
    private final CompositionSettings mCompositionSettings;
    private List<CameraEffect> mEffects;
    private Range<Integer> mFrameRate;
    private Config mInteropConfig;
    private final Object mLock;
    private UseCase mPlaceholderForExtensions;
    private final AdapterCameraInternal mSecondaryCameraInternal;
    private final CompositionSettings mSecondaryCompositionSettings;
    private int mSessionType;
    private StreamSharing mStreamSharing;
    private final StreamSharingForceEnabler mStreamSharingForceEnabler;
    private final StreamSpecsCalculator mStreamSpecsCalculator;
    private final UseCaseConfigFactory mUseCaseConfigFactory;

    public static final class CameraException extends Exception {
        public CameraException(Throwable r1) {
            super(r1);
        }
    }

    public static class ConfigPair {
        UseCaseConfig<?> mCameraConfig;
        UseCaseConfig<?> mExtendedConfig;

        public ConfigPair(UseCaseConfig<?> r1, UseCaseConfig<?> r2) {
            this.mExtendedConfig = r1;
            this.mCameraConfig = r2;
        }
    }

    public static /* synthetic */ void $r8$lambda$5hNd8fSJgscI21DT4954dmCDmuQ(SurfaceRequest r4) {
        SurfaceTexture r0 = new SurfaceTexture(0);
        r0.setDefaultBufferSize(r4.getResolution().getWidth(), r4.getResolution().getHeight());
        r0.detachFromGLContext();
        Surface r1 = new Surface(r0);
        r4.provideSurface(r1, CameraXExecutors.directExecutor(), new CameraUseCaseAdapter$$ExternalSyntheticLambda1(r1, r0));
    }

    /* renamed from: $r8$lambda$LnyT-rDpxDU4Lj0jFr7wqOCUqwI, reason: not valid java name */
    public static /* synthetic */ void m602$r8$lambda$LnyTrDpxDU4Lj0jFr7wqOCUqwI(Surface r0, SurfaceTexture r1, SurfaceRequest.Result r2) {
        r0.release();
        r1.release();
    }

    public CameraUseCaseAdapter(CameraInternal r3, CameraInternal r4, AdapterCameraInfo r5, AdapterCameraInfo r6, CompositionSettings r7, CompositionSettings r8, CameraCoordinator r9, StreamSpecsCalculator r10, UseCaseConfigFactory r11) {
        this.mAppUseCases = new ArrayList();
        this.mCameraUseCases = new ArrayList();
        this.mEffects = Collections.EMPTY_LIST;
        this.mSessionType = 0;
        this.mFrameRate = StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED;
        this.mLock = new Object();
        this.mAttached = true;
        this.mInteropConfig = null;
        this.mStreamSharingForceEnabler = new StreamSharingForceEnabler();
        this.mCameraConfig = r5.getCameraConfig();
        this.mCameraInternal = new AdapterCameraInternal(r3, r5);
        if (r4 == null) goto L6;
        if (r6 == null) goto L6;
        this.mSecondaryCameraInternal = new AdapterCameraInternal(r4, r6);
    L7:
        this.mCompositionSettings = r7;
        this.mSecondaryCompositionSettings = r8;
        this.mCameraCoordinator = r9;
        this.mUseCaseConfigFactory = r11;
        this.mCameraIdentifier = CameraIdentifier.Factory.fromAdapterInfos(r5, r6);
        this.mStreamSpecsCalculator = r10;
        return;
    L6:
        this.mSecondaryCameraInternal = null;
        goto L7
    }

    private void applyCalculatedUseCaseChanges(CalculatedUseCaseInfo r7) {
        updateViewPortAndSensorToBufferMatrix(r7.getPrimaryStreamSpecResult().getStreamSpecs(), r7.getCameraUseCases());
        updateEffects(this.mEffects, r7.getCameraUseCases(), r7.getAppUseCases());
        Iterator<UseCase> r0 = r7.getCameraUseCasesToDetach().iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        r0.next().unbindFromCamera(this.mCameraInternal);
        goto L4
    L6:
        this.mCameraInternal.detachUseCases(r7.getCameraUseCasesToDetach());
        if (this.mSecondaryCameraInternal == null) goto L14;
        Iterator<UseCase> r02 = r7.getCameraUseCasesToDetach().iterator();
    L10:
        if (r02.hasNext() == false) goto L12;
        UseCase r1 = r02.next();
        AdapterCameraInternal r2 = this.mSecondaryCameraInternal;
        Objects.requireNonNull(r2);
        r1.unbindFromCamera(r2);
        goto L10
    L12:
        AdapterCameraInternal r03 = this.mSecondaryCameraInternal;
        Objects.requireNonNull(r03);
        r03.detachUseCases(r7.getCameraUseCasesToDetach());
    L14:
        if (r7.getCameraUseCasesToDetach().isEmpty() == false) goto L29;
        Iterator<UseCase> r04 = r7.getCameraUseCasesToKeep().iterator();
    L17:
        if (r04.hasNext() == false) goto L29;
        UseCase r12 = r04.next();
        Map<UseCase, StreamSpec> r22 = r7.getPrimaryStreamSpecResult().getStreamSpecs();
        if (r22.containsKey(r12) == false) goto L17;
        StreamSpec r23 = r22.get(r12);
        Objects.requireNonNull(r23);
        Config r3 = r23.getImplementationOptions();
        if (r3 == null) goto L17;
        if (hasImplementationOptionChanged(r23, r12.getSessionConfig()) == false) goto L17;
        r12.updateSuggestedStreamSpecImplementationOptions(r3);
        if (this.mAttached == false) goto L17;
        this.mCameraInternal.onUseCaseUpdated(r12);
        AdapterCameraInternal r24 = this.mSecondaryCameraInternal;
        if (r24 == null) goto L17;
        Objects.requireNonNull(r24);
        r24.onUseCaseUpdated(r12);
    L29:
        Iterator<UseCase> r05 = r7.getCameraUseCasesToAttach().iterator();
    L31:
        if (r05.hasNext() == false) goto L37;
        UseCase r13 = r05.next();
        ConfigPair r25 = r7.getUseCaseConfigs().get(r13);
        Objects.requireNonNull(r25);
        AdapterCameraInternal r32 = this.mSecondaryCameraInternal;
        AdapterCameraInternal r4 = this.mCameraInternal;
        if (r32 != null) goto L34;
        r13.bindToCamera(r4, null, r25.mExtendedConfig, r25.mCameraConfig);
        r13.updateSuggestedStreamSpec((StreamSpec) Preconditions.checkNotNull(r7.getPrimaryStreamSpecResult().getStreamSpecs().get(r13)), null);
        goto L31
    L34:
        Objects.requireNonNull(r32);
        r13.bindToCamera(r4, r32, r25.mExtendedConfig, r25.mCameraConfig);
        r13.updateSuggestedStreamSpec((StreamSpec) Preconditions.checkNotNull(r7.getPrimaryStreamSpecResult().getStreamSpecs().get(r13)), ((StreamSpecQueryResult) Preconditions.checkNotNull(r7.getSecondaryStreamSpecResult())).getStreamSpecs().get(r13));
        goto L31
    L37:
        if (this.mAttached == false) goto L41;
        this.mCameraInternal.attachUseCases(r7.getCameraUseCasesToAttach());
        AdapterCameraInternal r06 = this.mSecondaryCameraInternal;
        if (r06 == null) goto L41;
        Objects.requireNonNull(r06);
        r06.attachUseCases(r7.getCameraUseCasesToAttach());
    L41:
        Iterator<UseCase> r07 = r7.getCameraUseCasesToAttach().iterator();
    L43:
        if (r07.hasNext() == false) goto L45;
        r07.next().notifyState();
        goto L43
    L45:
        this.mAppUseCases.clear();
        this.mAppUseCases.addAll(r7.getAppUseCases());
        this.mCameraUseCases.clear();
        this.mCameraUseCases.addAll(r7.getCameraUseCases());
        this.mPlaceholderForExtensions = r7.getPlaceholderForExtensions();
        this.mStreamSharing = r7.getStreamSharing();
    }

    private void applyCameraConfig() {
        this.mCameraInternal.setExtendedConfig(this.mCameraConfig);
        AdapterCameraInternal r0 = this.mSecondaryCameraInternal;
        if (r0 == null) goto L6;
        r0.setExtendedConfig(this.mCameraConfig);
        return;
    }

    private static Map<UseCase, Set<GroupableFeature>> applyFeatureGroup(Collection<UseCase> r3, ResolvedFeatureGroup r4) {
        HashMap r0 = new HashMap();
        Iterator<UseCase> r32 = r3.iterator();
    L4:
        if (r32.hasNext() == false) goto L10;
        UseCase r1 = r32.next();
        r0.put(r1, r1.getFeatureGroup());
        if (r4 == null) goto L8;
        Set<GroupableFeature> r2 = r4.getFeatures();
    L9:
        r1.setFeatureGroup(r2);
        goto L4
    L8:
        r2 = null;
        goto L9
    L10:
        return r0;
    }

    private static UseCaseConfig<?> attachUseCaseSharedConfigs(UseCase r1, UseCaseConfig<?> r2, int r3, Range<Integer> r4) {
        if (r2 == null) goto L4;
        MutableOptionsBundle r22 = MutableOptionsBundle.from(r2);
    L5:
        r22.insertOption(UseCaseConfig.OPTION_SESSION_TYPE, Integer.valueOf(r3));
        if (StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED.equals(r4) == true) goto L9;
        r22.insertOption(UseCaseConfig.OPTION_TARGET_FRAME_RATE, Config.OptionPriority.HIGH_PRIORITY_REQUIRED, r4);
        r22.insertOption(UseCaseConfig.OPTION_IS_STRICT_FRAME_RATE_REQUIRED, Boolean.TRUE);
    L9:
        return r1.getUseCaseConfigBuilder(r22).getUseCaseConfig();
    L4:
        r22 = MutableOptionsBundle.create();
        goto L5
    }

    private void cacheInteropConfig() {
        Object r0 = this.mLock;
        monitor-enter(r0);
        CameraControlInternal r1 = this.mCameraInternal.getCameraControlInternal();     // Catch: Throwable -> L7
        this.mInteropConfig = r1.getInteropConfig();     // Catch: Throwable -> L7
        r1.clearInteropConfig();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    private CalculatedUseCaseInfo calculateAndValidateUseCases(Collection<UseCase> r20, boolean r21, boolean r22) {
        boolean r12 = r22;
        checkUnsupportedFeatureCombinationAndThrow(r20);
        if (r21 == false) goto L5;
    L8:
        StreamSharing r0 = createOrReuseStreamSharing(r20, r21);
        UseCase r14 = calculatePlaceholderForExtensions(r20, r0);
        Collection<UseCase> r2 = calculateCameraUseCases(r20, r14, r0);
        ArrayList r6 = new ArrayList(r2);
        r6.removeAll(this.mCameraUseCases);
        ArrayList r7 = new ArrayList(r2);
        r7.retainAll(this.mCameraUseCases);
        ArrayList r3 = new ArrayList(this.mCameraUseCases);
        r3.removeAll(r2);
        Map<UseCase, ConfigPair> r16 = getConfigs(r6, this.mCameraConfig.getUseCaseConfigFactory(), this.mUseCaseConfigFactory, this.mSessionType, this.mFrameRate);
        boolean r11 = isFeatureComboInvocation(new List[]{r6, r7});
        StreamSpecQueryResult r18 = this.mStreamSpecsCalculator.calculateSuggestedStreamSpecs(getCameraMode(), this.mCameraInternal.getCameraInfoInternal(), r6, r7, this.mCameraConfig, this.mSessionType, this.mFrameRate, r11, r12);     // Catch: IllegalArgumentException -> L16
    L18:
        e = e;
        r12 = r22;
    L23:
        if (r21 == false) goto L25;
    L28:
        throw e;
    L25:
        if (isStreamSharingAllowed() == false) goto L28;
        return calculateAndValidateUseCases(r20, true, r12);
    L11:
        if (this.mSecondaryCameraInternal == null) goto L20;
        StreamSpecsCalculator r32 = this.mStreamSpecsCalculator;     // Catch: IllegalArgumentException -> L18
        int r4 = getCameraMode();     // Catch: IllegalArgumentException -> L18
        AdapterCameraInternal r5 = this.mSecondaryCameraInternal;     // Catch: IllegalArgumentException -> L18
        Objects.requireNonNull(r5);     // Catch: IllegalArgumentException -> L18
        r12 = r22;
        StreamSpecQueryResult r1 = r32.calculateSuggestedStreamSpecs(r4, r5.getCameraInfoInternal(), r6, r7, this.mCameraConfig, this.mSessionType, this.mFrameRate, r11, r12);     // Catch: IllegalArgumentException -> L16
    L22:
        return new CalculatedUseCaseInfo(r20, r2, r6, r7, r3, r0, r14, r16, r18, r1);
    L20:
        r1 = null;
    L16:
        e = e;
        goto L23
    L5:
        if (shouldForceEnableStreamSharing(r20) == false) goto L8;
        return calculateAndValidateUseCases(r20, true, r12);
    }

    public static Collection<UseCase> calculateCameraUseCases(Collection<UseCase> r1, UseCase r2, StreamSharing r3) {
        ArrayList r0 = new ArrayList(r1);
        if (r2 == null) goto L5;
        r0.add(r2);
    L5:
        if (r3 == null) goto L7;
        r0.add(r3);
        r0.removeAll(r3.getChildren());
    L7:
        return r0;
    }

    private UseCase calculatePlaceholderForExtensions(Collection<UseCase> r3, StreamSharing r4) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        ArrayList r1 = new ArrayList(r3);     // Catch: Throwable -> L7
        if (r4 == null) goto L10;
        r1.add(r4);     // Catch: Throwable -> L7
        r1.removeAll(r4.getChildren());     // Catch: Throwable -> L7
    L10:
        if (isCoexistingPreviewImageCaptureRequired() == true) goto L12;
    L23:
        UseCase r2 = null;
    L24:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r2;
    L12:
        if (isExtraPreviewRequired(r1) == false) goto L18;
        if (isPreview(this.mPlaceholderForExtensions) == false) goto L16;
        r2 = this.mPlaceholderForExtensions;     // Catch: Throwable -> L7
        goto L24
    L16:
        r2 = createExtraPreview();     // Catch: Throwable -> L7
        goto L24
    L18:
        if (isExtraImageCaptureRequired(r1) == false) goto L23;
        if (isImageCapture(this.mPlaceholderForExtensions) == false) goto L22;
        r2 = this.mPlaceholderForExtensions;     // Catch: Throwable -> L7
        goto L24
    L22:
        r2 = createExtraImageCapture();     // Catch: Throwable -> L7
    L7:
        th = move-exception;
        throw th;
    }

    private static Matrix calculateSensorToBufferTransformMatrix(Rect r4, Size r5) {
        if (r4.width() > 0) goto L5;
    L7:
        boolean r0 = false;
    L8:
        Preconditions.checkArgument(r0, "Cannot compute viewport crop rects zero sized sensor rect.");
        RectF r02 = new RectF(r4);
        Matrix r42 = new Matrix();
        r42.setRectToRect(new RectF(0.0f, 0.0f, r5.getWidth(), r5.getHeight()), r02, Matrix.ScaleToFit.CENTER);
        r42.invert(r42);
        return r42;
    L5:
        if (r4.height() <= 0) goto L7;
        r0 = true;
        goto L8
    }

    private void checkUnsupportedFeatureCombinationAndThrow(Collection<UseCase> r2) {
        if (hasExtension() == true) goto L5;
    L13:
        Object r0 = this.mLock;
        monitor-enter(r0);
    L22:
        th = move-exception;
        throw th;
    L16:
        if (this.mEffects.isEmpty() == false) goto L18;
    L26:
        monitor-exit(r0);     // Catch: Throwable -> L22
        return;
    L18:
        if (hasUltraHdrImageCapture(r2) == true) goto L25;
        if (hasRawImageCapture(r2) == false) goto L26;
    L25:
        throw new IllegalArgumentException("Ultra HDR image and Raw capture does not support for use with CameraEffect.");     // Catch: Throwable -> L22
    L5:
        if (hasNonSdrConfig(r2) == false) goto L7;
        f$$ExternalSyntheticBUOutline1.m("Extensions are only supported for use with standard dynamic range.");
        return;
    L7:
        if (hasRawImageCapture(r2) == false) goto L13;
        f$$ExternalSyntheticBUOutline1.m("Extensions are not supported for use with Raw image capture.");
    }

    private static void clearFeatureGroup(Collection<UseCase> r2) {
        Iterator<UseCase> r22 = r2.iterator();
    L4:
        if (r22.hasNext() == false) goto L6;
        r22.next().setFeatureGroup(null);
        goto L4
    }

    private ImageCapture createExtraImageCapture() {
        return new ImageCapture.Builder().setTargetName("ImageCapture-Extra").build();
    }

    private Preview createExtraPreview() {
        Preview r1 = new Preview.Builder().setTargetName("Preview-Extra").build();
        r1.setSurfaceProvider(new CameraUseCaseAdapter$$ExternalSyntheticLambda0());
        return r1;
    }

    private StreamSharing createOrReuseStreamSharing(Collection<UseCase> r10, boolean r11) {
        Object r1 = this.mLock;
        monitor-enter(r1);
        Set<UseCase> r7 = getStreamSharingChildren(r10, r11);     // Catch: Throwable -> L11
        if (r7.size() < 2) goto L7;
    L15:
        StreamSharing r102 = this.mStreamSharing;     // Catch: Throwable -> L11
        if (r102 == null) goto L23;
        if (r102.getChildren().equals(r7) == false) goto L23;
        this.mStreamSharing.updateFeatureGroup(r7);     // Catch: Throwable -> L11
        StreamSharing r9 = this.mStreamSharing;     // Catch: Throwable -> L11
        Objects.requireNonNull(r9);     // Catch: Throwable -> L11
        monitor-exit(r1);     // Catch: Throwable -> L11
        return r9;
    L23:
        if (isStreamSharingChildrenCombinationValid(r7) == true) goto L26;
        monitor-exit(r1);     // Catch: Throwable -> L11
        return null;
    L26:
        StreamSharing r2 = new StreamSharing(this.mCameraInternal, this.mSecondaryCameraInternal, this.mCompositionSettings, this.mSecondaryCompositionSettings, r7, this.mUseCaseConfigFactory);     // Catch: Throwable -> L11
        monitor-exit(r1);     // Catch: Throwable -> L11
        return r2;
    L7:
        if (hasExtension() == true) goto L9;
    L13:
        monitor-exit(r1);     // Catch: Throwable -> L11
        return null;
    L9:
        if (UseCaseUtil.containsVideoCapture(r7) == true) goto L15;
    L11:
        th = move-exception;
        throw th;
    }

    private static UseCaseConfig<?> generateExtendedStreamSharingConfigFromPreview(UseCaseConfigFactory r2, StreamSharing r3) {
        UseCaseConfig<?> r22 = new Preview.Builder().build().getDefaultConfig(false, r2);
        if (r22 != null) goto L6;
        return null;
    L6:
        MutableOptionsBundle r23 = MutableOptionsBundle.from(r22);
        r23.removeOption(TargetConfig.OPTION_TARGET_CLASS);
        return r3.getUseCaseConfigBuilder(r23).getUseCaseConfig();
    }

    private int getCameraMode() {
        Object r0 = this.mLock;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.mCameraCoordinator.getCameraOperatingMode() == 2) goto L7;
        monitor-exit(r0);     // Catch: Throwable -> L9
        return 0;
    L7:
        monitor-exit(r0);     // Catch: Throwable -> L9
        return 1;
    }

    public static Map<UseCase, ConfigPair> getConfigs(Collection<UseCase> r5, UseCaseConfigFactory r6, UseCaseConfigFactory r7, int r8, Range<Integer> r9) {
        HashMap r0 = new HashMap();
        Iterator<UseCase> r52 = r5.iterator();
    L4:
        if (r52.hasNext() == false) goto L10;
        UseCase r1 = r52.next();
        if (StreamSharing.isStreamSharing(r1) == false) goto L8;
        UseCaseConfig<?> r2 = generateExtendedStreamSharingConfigFromPreview(r6, (StreamSharing) r1);
    L9:
        r0.put(r1, new ConfigPair(r2, attachUseCaseSharedConfigs(r1, r1.getDefaultConfig(true, r7), r8, r9)));
        goto L4
    L8:
        r2 = r1.getDefaultConfig(false, r6);
        goto L9
    L10:
        return r0;
    }

    private int getSharingTargets(boolean r3) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        Iterator<CameraEffect> r2 = this.mEffects.iterator();     // Catch: Throwable -> L11
        if (r2.hasNext() == true) goto L13;
        if (r3 == false) goto L8;
        int r22 = 3;
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L11
        return r22;
    L8:
        r22 = 0;
        goto L9
    L13:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r2.next());     // Catch: Throwable -> L11
        throw null;     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        throw th;
    }

    private Set<UseCase> getStreamSharingChildren(Collection<UseCase> r4, boolean r5) {
        HashSet r0 = new HashSet();
        int r3 = getSharingTargets(r5);
        Iterator<UseCase> r42 = r4.iterator();
    L4:
        if (r42.hasNext() == false) goto L8;
        UseCase r52 = r42.next();
        Preconditions.checkArgument(!StreamSharing.isStreamSharing(r52), "Only support one level of sharing for now.");
        if (r52.isEffectTargetsSupported(r3) == false) goto L4;
        r0.add(r52);
        goto L4
    L8:
        return r0;
    }

    private boolean hasExtension() {
        Object r0 = this.mLock;
        monitor-enter(r0);
        this.mCameraConfig.getSessionProcessor(null);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return false;
    L7:
        th = move-exception;
        throw th;
    }

    private static boolean hasImplementationOptionChanged(StreamSpec r4, SessionConfig r5) {
        Config r42 = r4.getImplementationOptions();
        Config r0 = r5.getImplementationOptions();
        Objects.requireNonNull(r42);
        if (r42.listOptions().size() == r5.getImplementationOptions().listOptions().size()) goto L5;
        return true;
    L5:
        Iterator<Config.Option<?>> r52 = r42.listOptions().iterator();
    L7:
        if (r52.hasNext() == false) goto L13;
        Config.Option<?> r1 = r52.next();
        if (r0.containsOption(r1) == false) goto L12;
        if (Objects.equals(r0.retrieveOption(r1), r42.retrieveOption(r1)) == true) goto L7;
    L12:
        return true;
    L13:
        return false;
    }

    private static boolean hasNonSdrConfig(Collection<UseCase> r1) {
        Iterator<UseCase> r12 = r1.iterator();
    L4:
        if (r12.hasNext() == false) goto L9;
        if (isNotSdr(r12.next().getCurrentConfig().getDynamicRange()) == false) goto L4;
        return true;
    L9:
        return false;
    }

    private static boolean hasRawImageCapture(Collection<UseCase> r3) {
        Iterator<UseCase> r32 = r3.iterator();
    L4:
        if (r32.hasNext() == false) goto L14;
        UseCase r0 = r32.next();
        if (isImageCapture(r0) == false) goto L4;
        UseCaseConfig<?> r02 = r0.getCurrentConfig();
        Config.Option<?> r1 = ImageCaptureConfig.OPTION_OUTPUT_FORMAT;
        if (r02.containsOption(r1) == false) goto L4;
        if (((Integer) Preconditions.checkNotNull((Integer) r02.retrieveOption(r1))).intValue() != 2) goto L4;
        return true;
    L14:
        return false;
    }

    private static boolean hasUltraHdrImageCapture(Collection<UseCase> r3) {
        Iterator<UseCase> r32 = r3.iterator();
    L4:
        if (r32.hasNext() == false) goto L13;
        UseCase r0 = r32.next();
        if (isImageCapture(r0) == false) goto L4;
        UseCaseConfig<?> r02 = r0.getCurrentConfig();
        Config.Option<?> r1 = ImageCaptureConfig.OPTION_OUTPUT_FORMAT;
        if (r02.containsOption(r1) == false) goto L4;
        if (((Integer) Preconditions.checkNotNull((Integer) r02.retrieveOption(r1))).intValue() != 1) goto L4;
        return true;
    L13:
        return false;
    }

    private boolean isCoexistingPreviewImageCaptureRequired() {
        Object r0 = this.mLock;
        monitor-enter(r0);
        boolean r1 = true;
        if (this.mCameraConfig.getUseCaseCombinationRequiredRule() == 1) goto L8;
        r1 = false;
    L8:
        monitor-exit(r0);     // Catch: Throwable -> L10
        return r1;
    L10:
        th = move-exception;
        throw th;
    }

    private static boolean isExtraImageCaptureRequired(Collection<UseCase> r6) {
        Iterator<UseCase> r62 = r6.iterator();
        boolean r1 = false;
        boolean r2 = false;
    L4:
        if (r62.hasNext() == false) goto L14;
        UseCase r3 = r62.next();
        if (isPreview(r3) == true) goto L13;
        if (StreamSharing.isStreamSharing(r3) == true) goto L13;
        if (isImageCapture(r3) == false) goto L4;
        r2 = true;
    L13:
        r1 = true;
        goto L4
    L14:
        if (r1 == false) goto L17;
        if (r2 == true) goto L17;
        return true;
    L17:
        return false;
    }

    private static boolean isExtraPreviewRequired(Collection<UseCase> r6) {
        Iterator<UseCase> r62 = r6.iterator();
        boolean r1 = false;
        boolean r2 = false;
    L4:
        if (r62.hasNext() == false) goto L14;
        UseCase r3 = r62.next();
        if (isPreview(r3) == true) goto L13;
        if (StreamSharing.isStreamSharing(r3) == true) goto L13;
        if (isImageCapture(r3) == false) goto L4;
        r1 = true;
    L13:
        r2 = true;
        goto L4
    L14:
        if (r1 == false) goto L17;
        if (r2 == true) goto L17;
        return true;
    L17:
        return false;
    }

    @SafeVarargs
    private static boolean isFeatureComboInvocation(List<UseCase>... r5) {
        int r0 = r5.length;
        int r1 = 0;
        boolean r2 = false;
    L3:
        if (r1 >= r0) goto L13;
        Iterator<UseCase> r3 = r5[r1].iterator();
    L6:
        if (r3.hasNext() == false) goto L10;
        if (r3.next().getFeatureGroup() == null) goto L6;
        r2 = true;
    L10:
        if (r2 == true) goto L11;
        r1 = r1 + 1;
        goto L3
    L11:
        return r2;
    L13:
        return r2;
    }

    private static boolean isImageCapture(UseCase r0) {
        return r0 instanceof ImageCapture;
    }

    private static boolean isNotSdr(DynamicRange r4) {
        if (r4.getBitDepth() != 10) goto L5;
        boolean r0 = true;
    L7:
        if (r4.getEncoding() != 1) goto L9;
    L11:
        boolean r42 = false;
    L12:
        if (r0 == true) goto L16;
        if (r42 == true) goto L16;
        return false;
    L16:
        return true;
    L9:
        if (r4.getEncoding() == 0) goto L11;
        r42 = true;
        goto L12
    L5:
        r0 = false;
        goto L7
    }

    private static boolean isPreview(UseCase r0) {
        return r0 instanceof Preview;
    }

    private boolean isStreamSharingAllowed() {
        if (hasExtension() == false) goto L5;
        return false;
    L5:
        if (this.mSecondaryCameraInternal == null) goto L7;
        return false;
    L7:
        if (this.mSessionType == 1) goto L12;
        return true;
    L12:
        return false;
    }

    public static boolean isStreamSharingChildrenCombinationValid(Collection<UseCase> r8) {
        int[] r0 = {1, 2, 4};
        HashSet r1 = new HashSet();
        Iterator<UseCase> r82 = r8.iterator();
    L4:
        if (r82.hasNext() == false) goto L15;
        UseCase r3 = r82.next();
        int r5 = 0;
    L7:
        if (r5 >= 3) goto L4;
        int r6 = r0[r5];
        if (r3.isEffectTargetsSupported(r6) == false) goto L14;
        if (r1.contains(Integer.valueOf(r6)) == true) goto L12;
        r1.add(Integer.valueOf(r6));
        goto L14
    L12:
        return false;
    L14:
        r5 = r5 + 1;
        goto L7
    L15:
        return true;
    }

    private static void restoreFeatureGroup(Map<UseCase, Set<GroupableFeature>> r2) {
        Iterator<Map.Entry<UseCase, Set<GroupableFeature>>> r22 = r2.entrySet().iterator();
    L4:
        if (r22.hasNext() == false) goto L6;
        Map.Entry<UseCase, Set<GroupableFeature>> r0 = r22.next();
        r0.getKey().setFeatureGroup(r0.getValue());
        goto L4
    }

    private void restoreInteropConfig() {
        Object r0 = this.mLock;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (this.mInteropConfig == null) goto L9;
        this.mCameraInternal.getCameraControlInternal().addInteropConfig(this.mInteropConfig);     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
    }

    private static List<CameraEffect> setEffectsOnUseCases(List<CameraEffect> r4, Collection<UseCase> r5) {
        ArrayList r0 = new ArrayList(r4);
        Iterator<UseCase> r52 = r5.iterator();
    L4:
        if (r52.hasNext() == false) goto L10;
        r52.next().setEffect(null);
        Iterator<CameraEffect> r1 = r4.iterator();
        if (r1.hasNext() == false) goto L4;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1.next());
        throw null;
    L10:
        return r0;
    }

    private boolean shouldForceEnableStreamSharing(Collection<UseCase> r2) {
        if (hasExtension() == false) goto L9;
        if (UseCaseUtil.containsVideoCapture(r2) == false) goto L9;
        return true;
    L9:
        return this.mStreamSharingForceEnabler.shouldForceEnableStreamSharing(this.mCameraInternal.getCameraInfoInternal().getCameraId(), r2);
    }

    public static void updateEffects(List<CameraEffect> r1, Collection<UseCase> r2, Collection<UseCase> r3) {
        List<CameraEffect> r12 = setEffectsOnUseCases(r1, r2);
        ArrayList r0 = new ArrayList(r3);
        r0.removeAll(r2);
        List<CameraEffect> r13 = setEffectsOnUseCases(r12, r0);
        if (r13.isEmpty() == true) goto L6;
        Logger.w("CameraUseCaseAdapter", "Unused effects: " + r13);
        return;
    }

    private void updateViewPortAndSensorToBufferMatrix(Map<UseCase, StreamSpec> r5, Collection<UseCase> r6) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        Iterator<UseCase> r62 = r6.iterator();     // Catch: Throwable -> L8
    L6:
        if (r62.hasNext() == false) goto L10;
        UseCase r1 = r62.next();     // Catch: Throwable -> L8
        r1.setSensorToBufferTransformMatrix(calculateSensorToBufferTransformMatrix(this.mCameraInternal.getCameraInfoInternal().getSensorRect(), ((StreamSpec) Preconditions.checkNotNull(r5.get(r1))).getResolution()));     // Catch: Throwable -> L8
        goto L6
    L10:
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        throw th;
    }

    public void addUseCases(Collection<UseCase> r4, ResolvedFeatureGroup r5) {
        Logger.d("CameraUseCaseAdapter", "addUseCases: appUseCasesToAdd = " + r4 + ", featureGroup = " + r5);
        Object r0 = this.mLock;
        monitor-enter(r0);
        applyCameraConfig();     // Catch: Throwable -> L12
        LinkedHashSet r1 = new LinkedHashSet(this.mAppUseCases);     // Catch: Throwable -> L12
        r1.addAll(r4);     // Catch: Throwable -> L12
        Map<UseCase, Set<GroupableFeature>> r42 = applyFeatureGroup(r1, r5);     // Catch: Throwable -> L12
    L14:
        e = move-exception;
        restoreFeatureGroup(r42);     // Catch: Throwable -> L12
        throw new CameraException(e);     // Catch: Throwable -> L12
    L6:
        if (this.mSecondaryCameraInternal == null) goto L8;
        boolean r52 = true;
    L9:
        applyCalculatedUseCaseChanges(calculateAndValidateUseCases(r1, r52, false));     // Catch: Throwable -> L12 IllegalArgumentException -> L14
        monitor-exit(r0);     // Catch: Throwable -> L12
        return;
    L8:
        r52 = false;
    L12:
        th = move-exception;
        throw th;
    }

    public void attachUseCases() {
        Object r0 = this.mLock;
        monitor-enter(r0);
    L11:
        th = move-exception;
        throw th;
    L5:
        if (this.mAttached == false) goto L7;
    L21:
        monitor-exit(r0);     // Catch: Throwable -> L11
        return;
    L7:
        if (this.mCameraUseCases.isEmpty() == true) goto L13;
        this.mCameraInternal.setExtendedConfig(this.mCameraConfig);     // Catch: Throwable -> L11
        AdapterCameraInternal r1 = this.mSecondaryCameraInternal;     // Catch: Throwable -> L11
        if (r1 == null) goto L13;
        r1.setExtendedConfig(this.mCameraConfig);     // Catch: Throwable -> L11
    L13:
        this.mCameraInternal.attachUseCases(this.mCameraUseCases);     // Catch: Throwable -> L11
        AdapterCameraInternal r12 = this.mSecondaryCameraInternal;     // Catch: Throwable -> L11
        if (r12 == null) goto L16;
        r12.attachUseCases(this.mCameraUseCases);     // Catch: Throwable -> L11
    L16:
        restoreInteropConfig();     // Catch: Throwable -> L11
        Iterator<UseCase> r13 = this.mCameraUseCases.iterator();     // Catch: Throwable -> L11
    L18:
        if (r13.hasNext() == false) goto L20;
        r13.next().notifyState();     // Catch: Throwable -> L11
        goto L18
    L20:
        this.mAttached = true;     // Catch: Throwable -> L11
        goto L21
    }

    public void detachUseCases() {
        Object r0 = this.mLock;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.mAttached == false) goto L12;
        this.mCameraInternal.detachUseCases(new ArrayList(this.mCameraUseCases));     // Catch: Throwable -> L9
        AdapterCameraInternal r1 = this.mSecondaryCameraInternal;     // Catch: Throwable -> L9
        if (r1 == null) goto L11;
        r1.detachUseCases(new ArrayList(this.mCameraUseCases));     // Catch: Throwable -> L9
    L11:
        cacheInteropConfig();     // Catch: Throwable -> L9
        this.mAttached = false;     // Catch: Throwable -> L9
    L12:
        monitor-exit(r0);     // Catch: Throwable -> L9
    }

    public CameraIdentifier getAdapterIdentifier() {
        return this.mCameraIdentifier;
    }

    @Override // androidx.camera.core.Camera
    public CameraControl getCameraControl() {
        return this.mCameraInternal.getCameraControl();
    }

    @Override // androidx.camera.core.Camera
    public CameraInfo getCameraInfo() {
        return this.mCameraInternal.getCameraInfo();
    }

    public List<UseCase> getUseCases() {
        Object r0 = this.mLock;
        monitor-enter(r0);
        ArrayList r1 = new ArrayList(this.mAppUseCases);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public boolean isRemoved() {
        if (this.mCameraInternal.isRemoved() == true) goto L11;
        AdapterCameraInternal r1 = this.mSecondaryCameraInternal;
        if (r1 != null) goto L7;
        return false;
    L7:
        if (r1.isRemoved() == true) goto L14;
        return false;
    L14:
        return true;
    L11:
        return true;
    }

    public void removeUseCases(Collection<UseCase> r4) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        clearFeatureGroup(r4);     // Catch: Throwable -> L11
        LinkedHashSet r1 = new LinkedHashSet(this.mAppUseCases);     // Catch: Throwable -> L11
        r1.removeAll(r4);     // Catch: Throwable -> L11
        if (this.mSecondaryCameraInternal == null) goto L7;
        boolean r42 = true;
    L8:
        applyCalculatedUseCaseChanges(calculateAndValidateUseCases(r1, r42, false));     // Catch: Throwable -> L11
        monitor-exit(r0);     // Catch: Throwable -> L11
        return;
    L7:
        r42 = false;
    L11:
        th = move-exception;
        throw th;
    }

    public void setActiveResumingMode(boolean r1) {
        this.mCameraInternal.setActiveResumingMode(r1);
    }

    public void setEffects(List<CameraEffect> r2) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        this.mEffects = r2;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public void setFrameRate(Range<Integer> r2) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        this.mFrameRate = r2;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public void setSessionType(int r2) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        this.mSessionType = r2;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public void setViewPort(ViewPort r1) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        monitor-exit(r0);     // Catch: Throwable -> L6
        return;
    L6:
        th = move-exception;
        throw th;
    }

    public CalculatedUseCaseInfo simulateAddUseCases(Collection<UseCase> r4, ResolvedFeatureGroup r5, boolean r6) {
        Logger.d("CameraUseCaseAdapter", "simulateAddUseCases: appUseCasesToAdd = " + r4 + ", featureGroup = " + r5);
        Object r0 = this.mLock;
        monitor-enter(r0);
        applyCameraConfig();     // Catch: Throwable -> L13
        LinkedHashSet r1 = new LinkedHashSet(this.mAppUseCases);     // Catch: Throwable -> L13
        r1.addAll(r4);     // Catch: Throwable -> L13
        Map<UseCase, Set<GroupableFeature>> r42 = applyFeatureGroup(r1, r5);     // Catch: Throwable -> L13
    L17:
        e = move-exception;
        throw new CameraException(e);     // Catch: Throwable -> L15
    L15:
        th = move-exception;
        restoreFeatureGroup(r42);     // Catch: Throwable -> L13
        throw th;     // Catch: Throwable -> L13
    L6:
        if (this.mSecondaryCameraInternal == null) goto L8;
        boolean r52 = true;
    L9:
        CalculatedUseCaseInfo r3 = calculateAndValidateUseCases(r1, r52, r6);     // Catch: Throwable -> L15 IllegalArgumentException -> L17
        restoreFeatureGroup(r42);     // Catch: Throwable -> L13
        monitor-exit(r0);     // Catch: Throwable -> L13
        return r3;
    L8:
        r52 = false;
    L13:
        th = move-exception;
        throw th;
    }
}
