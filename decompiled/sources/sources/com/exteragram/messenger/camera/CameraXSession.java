package com.exteragram.messenger.camera;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.os.Looper;
import android.util.Range;
import android.util.Size;
import android.view.Display;
import android.view.Surface;
import androidx.camera.camera2.interop.Camera2CameraInfo;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraControl;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.CameraState;
import androidx.camera.core.ConcurrentCamera;
import androidx.camera.core.DisplayOrientedMeteringPointFactory;
import androidx.camera.core.FocusMeteringAction;
import androidx.camera.core.Preview;
import androidx.camera.core.SessionConfig;
import androidx.camera.core.SurfaceRequest;
import androidx.camera.core.UseCase;
import androidx.camera.core.UseCaseGroup;
import androidx.camera.core.ZoomState;
import androidx.camera.core.resolutionselector.AspectRatioStrategy;
import androidx.camera.core.resolutionselector.ResolutionSelector;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.utils.system.SystemUtils;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.SharedConfig;
import org.telegram.ui.Stories.recorder.DualCameraView;

/* loaded from: classes4.dex */
public class CameraXSession {
    private static final Size FALLBACK_SENSOR_ASPECT = null;
    private static final Range<Integer> FPS_30_RANGE = null;
    private static final Range<Integer> FPS_60_RANGE = null;
    private static final Map<CameraSelector, Boolean> STABILIZATION_SUPPORT_CACHE = null;
    private static volatile Boolean seamlessSwitchingAvailableCache;
    Camera camera;
    Camera cameraBack;
    private CameraControl cameraControl;
    private CameraControl cameraControlBack;
    private CameraControl cameraControlFront;
    Camera cameraFront;
    private CameraSelector cameraSelector;
    private boolean isBinding;
    private boolean isDualMode;
    private boolean isFrontface;
    private boolean isInitiated;
    private boolean isTorchOn;
    private final CameraLifecycle lifecycle;
    private Preview previewUseCase;
    private Preview previewUseCaseBack;
    ProcessCameraProvider provider;
    private volatile int recordingFrameRate;
    private final Preview.SurfaceProvider surfaceProviderPrimary;
    private Preview.SurfaceProvider surfaceProviderSecondary;

    /* renamed from: com.exteragram.messenger.camera.CameraXSession$1, reason: invalid class name */
    public class AnonymousClass1 implements Observer<ZoomState> {
        final /* synthetic */ CameraXSession this$0;
        final /* synthetic */ Camera val$cam;
        final /* synthetic */ CameraControl val$control;
        final /* synthetic */ LiveData val$zoomStateData;

        public AnonymousClass1(CameraXSession r1, LiveData r2, Camera r3, CameraControl r4) {
            this.this$0 = r1;
            this.val$zoomStateData = r2;
            this.val$cam = r3;
            this.val$control = r4;
        }

        /* renamed from: onChanged, reason: avoid collision after fix types in other method */
        public void onChanged2(ZoomState r3) {
            if (r3 == null) goto L9;
            this.val$zoomStateData.removeObserver(this);
            if (CameraXSession.m1142$$Nest$mwantsWideAngleStart(this.this$0, this.val$cam) == false) goto L8;
            CameraXSession.m1141$$Nest$mapplyWideAngle(this.this$0, this.val$control, r3);
            return;
        L8:
            return;
        }

        @Override // androidx.lifecycle.Observer
        public /* bridge */ /* synthetic */ void onChanged(ZoomState r1) {
            onChanged2(r1);
        }
    }

    public static class CameraLifecycle implements LifecycleOwner {
        private final LifecycleRegistry lifecycleRegistry;

        public CameraLifecycle() {
            LifecycleRegistry r0 = new LifecycleRegistry(this);
            this.lifecycleRegistry = r0;
            r0.setCurrentState(Lifecycle.State.CREATED);
        }

        @Override // androidx.lifecycle.LifecycleOwner
        public Lifecycle getLifecycle() {
            return this.lifecycleRegistry;
        }

        public void start() {
        L6:
            e = move-exception;
            FileLog.e(e);
            return;
        L3:
            if (this.lifecycleRegistry.getCurrentState() == Lifecycle.State.DESTROYED) goto L11;
            this.lifecycleRegistry.setCurrentState(Lifecycle.State.RESUMED);     // Catch: Exception -> L6
            return;
        }

        public void stop() {
            this.lifecycleRegistry.setCurrentState(Lifecycle.State.DESTROYED);     // Catch: Exception -> L4
            return;
        L4:
            e = move-exception;
            FileLog.e(e);
        }
    }

    public interface PreviewSizeListener {
        void onPreviewSize(int r1, int r2);
    }

    public static /* synthetic */ void $r8$lambda$293GI9xWXKNG5kPKaSxWt6Uy9f4(PreviewSizeListener r3, Context r4, SurfaceTexture r5, SurfaceRequest r6) {
        Size r0 = r6.getResolution();     // Catch: Exception -> L4
        r3.onPreviewSize(r0.getWidth(), r0.getHeight());     // Catch: Exception -> L4
        r6.setTransformationInfoListener(ContextCompat.getMainExecutor(r4), new CameraXSession$$ExternalSyntheticLambda0(r0, r3));     // Catch: Exception -> L4
        r5.setDefaultBufferSize(r0.getWidth(), r0.getHeight());     // Catch: Exception -> L4
        Surface r32 = new Surface(r5);     // Catch: Exception -> L4
        r6.provideSurface(r32, ContextCompat.getMainExecutor(r4), new CameraXSession$$ExternalSyntheticLambda1(r6, r32));     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
        r6.willNotProvideSurface();
    }

    public static /* synthetic */ void $r8$lambda$3ap979ggt1UA6uWOvBkMX6ucwD0(CameraXSession r0, ListenableFuture r1, boolean r2, Runnable r3) {
        r0.lambda$initCamera$3(r1, r2, r3);
    }

    public static /* synthetic */ int $r8$lambda$5Bpb2uDaDq6YWT8gzHGYT5j5yQ0(int r0, boolean r1, Set r2, Size r3) {
        return previewTier(r3, r0, r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$TRled3lKaEUY2eaet8nSIa2avwg(Size r2, PreviewSizeListener r3, SurfaceRequest.TransformationInfo r4) {
        Rect r42 = r4.getCropRect();
        if (r42.width() <= 0) goto L5;
        int r0 = r42.width();
    L7:
        if (r42.height() <= 0) goto L9;
        int r22 = r42.height();
    L10:
        r3.onPreviewSize(r0, r22);
        return;
    L9:
        r22 = r2.getHeight();
        goto L10
    L5:
        r0 = r2.getWidth();
        goto L7
    }

    public static /* synthetic */ List $r8$lambda$UgBo7gMDjWn9HWQDXGU1spN6jFQ(Size r0, Set r1, List r2, int r3) {
        return sortRoundPreviewSizes(r2, r0, r1);
    }

    /* renamed from: $r8$lambda$jewnXiGL03oQsgonhL2Q-Z_SOiI, reason: not valid java name */
    public static /* synthetic */ void m1140$r8$lambda$jewnXiGL03oQsgonhL2QZ_SOiI(SurfaceRequest r0, Surface r1, SurfaceRequest.Result r2) {
        r0.clearTransformationInfoListener();
        r1.release();
    }

    public static /* synthetic */ void $r8$lambda$rb0Pnzaeyz85DEsbXVziDkXE8Vk(CameraState r3) {
        CameraState.StateError r0 = r3.getError();
        if (r0 == null) goto L8;
        FileLog.e("CameraX camera state error: code=" + r0.getCode() + " type=" + r3.getType());
        if (r0.getCause() == null) goto L9;
        FileLog.e(r0.getCause());
        return;
    L9:
        return;
    }

    public static /* synthetic */ long $r8$lambda$v4NSQnloejkP_MjECQZi27YHhEY(Size r4) {
        return r4.getWidth() * r4.getHeight();
    }

    public static /* synthetic */ double $r8$lambda$wZRkxbdDRwhv33ZNzf22KoV2pfg(double r0, int r2, Size r3) {
        return previewScore(r3, r0, r2);
    }

    /* renamed from: -$$Nest$mapplyWideAngle, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1141$$Nest$mapplyWideAngle(CameraXSession r0, CameraControl r1, ZoomState r2) {
        r0.applyWideAngle(r1, r2);
    }

    /* renamed from: -$$Nest$mwantsWideAngleStart, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m1142$$Nest$mwantsWideAngleStart(CameraXSession r0, Camera r1) {
        return r0.wantsWideAngleStart(r1);
    }

    static {
        STABILIZATION_SUPPORT_CACHE = new ConcurrentHashMap();
        FPS_60_RANGE = new Range(60, 60);
        FPS_30_RANGE = new Range(30, 30);
        FALLBACK_SENSOR_ASPECT = new Size(4, 3);
    }

    public CameraXSession(CameraLifecycle r3, Preview.SurfaceProvider r4) {
        this.isInitiated = false;
        this.isDualMode = false;
        this.isBinding = false;
        this.recordingFrameRate = 30;
        this.isTorchOn = false;
        this.lifecycle = r3;
        this.surfaceProviderPrimary = r4;
    }

    private void applyInitialZoom(Camera r4, CameraControl r5) {
        if (r5 == null) goto L14;
        if (r4 == null) goto L15;
        r5.setZoomRatio(1.0f);
        if (wantsWideAngleStart(r4) == false) goto L16;
        LiveData<ZoomState> r0 = r4.getCameraInfo().getZoomState();
        if (r0.getValue() == null) goto L12;
        applyWideAngle(r5, r0.getValue());
        return;
    L12:
        r0.observe(this.lifecycle, new AnonymousClass1(this, r0, r4, r5));
        return;
    L16:
        return;
    L15:
        return;
    }

    private void applyWideAngle(CameraControl r2, ZoomState r3) {
        if (r3 != null) goto L4;
        return;
    L4:
        if (r3.getMinZoomRatio() >= 1.0f) goto L8;
        r2.setZoomRatio(r3.getMinZoomRatio());
        return;
    }

    private static double aspectRatioOf(Size r5) {
        int r0 = Math.min(r5.getWidth(), r5.getHeight());
        int r52 = Math.max(r5.getWidth(), r5.getHeight());
        if (r0 > 0) goto L5;
        return 1.3333333333333333d;
    L5:
        return r52 / r0;
    }

    private void bindDualUseCases() {
        boolean r0 = ExteraConfig.getExtendedFramesPerSecond();
        if (r0 == false) goto L5;
        Range<Integer> r1 = FPS_60_RANGE;
    L6:
        ConcurrentCamera r2 = tryBindConcurrentCameras(r1);
        if (r2 != null) goto L10;
        if (r0 == false) goto L10;
        r1 = FPS_30_RANGE;
        r2 = tryBindConcurrentCameras(r1);
    L10:
        if (r2 != null) goto L13;
        if (r1 == null) goto L13;
        r1 = null;
        r2 = tryBindConcurrentCameras(null);
    L13:
        if (r2 != null) goto L16;
        this.isDualMode = false;
        bindSingleUseCases();
        return;
    L16:
        if (r1 == null) goto L18;
        this.recordingFrameRate = ((Integer) r1.getLower()).intValue();
    L18:
        Iterator<Camera> r02 = r2.getCameras().iterator();
    L20:
        if (r02.hasNext() == false) goto L26;
        Camera r12 = r02.next();
        if (r12.getCameraInfo().getLensFacing() != 0) goto L24;
        this.cameraFront = r12;
        CameraControl r22 = r12.getCameraControl();
        this.cameraControlFront = r22;
        applyInitialZoom(this.cameraFront, r22);
    L25:
        observeCameraState(r12);
        goto L20
    L24:
        this.cameraBack = r12;
        CameraControl r23 = r12.getCameraControl();
        this.cameraControlBack = r23;
        applyInitialZoom(this.cameraBack, r23);
        goto L25
    L26:
        updateActiveControl(this.isFrontface);
        updateTorchState();
        return;
    L5:
        r1 = FPS_30_RANGE;
        goto L6
    }

    private void bindSingleUseCases() {
    L6:
        e = move-exception;
        FileLog.e(e);
        this.isInitiated = false;
        return;
    L4:
        if (this.isFrontface == false) goto L8;
        CameraSelector r1 = CameraSelector.DEFAULT_FRONT_CAMERA;     // Catch: Exception -> L6
    L9:
        this.cameraSelector = r1;     // Catch: Exception -> L6
        if (this.provider.hasCamera(r1) == true) goto L13;
        this.isInitiated = false;     // Catch: Exception -> L6
        return;
    L13:
        boolean r12 = ExteraConfig.getExtendedFramesPerSecond();     // Catch: Exception -> L6
        if (ExteraConfig.getCameraStabilization() == true) goto L16;
    L18:
        boolean r2 = false;
    L19:
        Preview r4 = buildPreview(this.cameraSelector, r2, null, r12);     // Catch: Exception -> L6
        this.previewUseCase = r4;     // Catch: Exception -> L6
        r4.setSurfaceProvider(this.surfaceProviderPrimary);     // Catch: Exception -> L6
        if (r12 == false) goto L28;
        Camera r13 = tryBindSingleAtExtendedFrameRate();     // Catch: Exception -> L6
        this.camera = r13;     // Catch: Exception -> L6
        if (r13 != null) goto L26;
        if (r2 == false) goto L26;
        Preview r14 = buildPreview(this.cameraSelector, false, null, true);     // Catch: Exception -> L6
        this.previewUseCase = r14;     // Catch: Exception -> L6
        r14.setSurfaceProvider(this.surfaceProviderPrimary);     // Catch: Exception -> L6
        this.camera = tryBindSingleAtExtendedFrameRate();     // Catch: Exception -> L6
    L26:
        if (this.camera != null) goto L30;
        Preview r15 = buildPreview(this.cameraSelector, r2, null, false);     // Catch: Exception -> L6
        this.previewUseCase = r15;     // Catch: Exception -> L6
        r15.setSurfaceProvider(this.surfaceProviderPrimary);     // Catch: Exception -> L6
        this.camera = tryBindSingleAtFrameRate(FPS_30_RANGE);     // Catch: Exception -> L6
    L30:
        if (this.camera != null) goto L36;
        this.camera = this.provider.bindToLifecycle(this.lifecycle, this.cameraSelector, new UseCase[]{this.previewUseCase});     // Catch: Exception -> L33
    L33:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L6
    L36:
        if (this.camera != null) goto L39;
        if (r2 == false) goto L39;
        Preview r16 = buildPreview(this.cameraSelector, false, null, false);     // Catch: Exception -> L6
        this.previewUseCase = r16;     // Catch: Exception -> L6
        r16.setSurfaceProvider(this.surfaceProviderPrimary);     // Catch: Exception -> L6
        this.camera = this.provider.bindToLifecycle(this.lifecycle, this.cameraSelector, new UseCase[]{this.previewUseCase});     // Catch: Exception -> L6
    L39:
        Camera r17 = this.camera;     // Catch: Exception -> L6
        if (r17 != null) goto L42;
        this.isInitiated = false;     // Catch: Exception -> L6
        return;
    L42:
        this.cameraControl = r17.getCameraControl();     // Catch: Exception -> L6
        observeCameraState(this.camera);     // Catch: Exception -> L6
        applyInitialZoom(this.camera, this.cameraControl);     // Catch: Exception -> L6
        updateTorchState();     // Catch: Exception -> L6
        return;
    L28:
        this.camera = tryBindSingleAtFrameRate(FPS_30_RANGE);     // Catch: Exception -> L6
        goto L30
    L16:
        if (isPreviewStabilizationSupported(this.cameraSelector) == false) goto L18;
        r2 = true;
        goto L19
    L8:
        r1 = CameraSelector.DEFAULT_BACK_CAMERA;     // Catch: Exception -> L6
        goto L9
    }

    private Preview buildPreview(CameraSelector r3, boolean r4, Range<Integer> r5, boolean r6) {
        Size r0 = getSensorAspect(r3);
        if (r6 == false) goto L5;
        Set<Size> r32 = get60FpsCapableSizes(r3);
    L6:
        Preview.Builder r33 = new Preview.Builder().setResolutionSelector(new ResolutionSelector.Builder().setAspectRatioStrategy(AspectRatioStrategy.RATIO_4_3_FALLBACK_AUTO_STRATEGY).setResolutionFilter(new CameraXSession$$ExternalSyntheticLambda5(r0, r32)).setAllowedResolutionMode(0).build());
        if (r5 == null) goto L10;
        r33.setTargetFrameRate(r5);
    L10:
        if (this.isDualMode == true) goto L13;
        if (r4 == false) goto L13;
        boolean r2 = true;
    L14:
        r33.setPreviewStabilizationEnabled(r2);
        if (ExteraConfig.getCameraMirrorMode() == true) goto L18;
        r33.setMirrorMode(0);
    L18:
        return r33.build();
    L13:
        r2 = false;
        goto L14
    L5:
        r32 = Collections.EMPTY_SET;
        goto L6
    }

    private void clearCameraReferences() {
        this.cameraBack = null;
        this.cameraFront = null;
        this.camera = null;
        this.cameraControlBack = null;
        this.cameraControlFront = null;
        this.cameraControl = null;
        this.previewUseCaseBack = null;
        this.previewUseCase = null;
    }

    public static Preview.SurfaceProvider createSurfaceProvider(Context r1, SurfaceTexture r2, PreviewSizeListener r3) {
        return new CameraXSession$$ExternalSyntheticLambda9(r3, r1, r2);
    }

    private Set<Size> get60FpsCapableSizes(CameraSelector r10) {
        HashSet r0 = new HashSet();
        ProcessCameraProvider r1 = this.provider;     // Catch: Exception -> L13
        if (r1 == null) goto L26;
        if (r10 == null) goto L26;
        if (r1.hasCamera(r10) == false) goto L26;
        StreamConfigurationMap r9 = (StreamConfigurationMap) Camera2CameraInfo.from(this.provider.getCameraInfo(r10)).getCameraCharacteristic(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);     // Catch: Exception -> L13
        if (r9 == null) goto L15;
        Size[] r12 = r9.getOutputSizes(SurfaceTexture.class);     // Catch: Exception -> L13
    L16:
        if (r12 == null) goto L26;
        int r2 = r12.length;     // Catch: Exception -> L13
        int r3 = 0;
    L19:
        if (r3 >= r2) goto L26;
        Size r4 = r12[r3];     // Catch: Exception -> L13
        long r5 = r9.getOutputMinFrameDuration(SurfaceTexture.class, r4);     // Catch: Exception -> L13
        if (r5 <= 0) goto L25;
        if (r5 > 16666667) goto L25;
        r0.add(r4);     // Catch: Exception -> L13
    L25:
        r3 = r3 + 1;
        goto L19
    L15:
        r12 = null;
    L26:
        return r0;
    L13:
        e = move-exception;
        FileLog.e(e);
        return r0;
    }

    private static Display getDefaultDisplay() {
        DisplayManager r0 = (DisplayManager) ApplicationLoader.applicationContext.getSystemService("display");
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getDisplay(0);
    }

    private Size getSensorAspect(CameraSelector r2) {
        ProcessCameraProvider r0 = this.provider;     // Catch: Exception -> L15
        if (r0 == null) goto L18;
        if (r2 == null) goto L18;
        if (r0.hasCamera(r2) == false) goto L18;
        Rect r1 = (Rect) Camera2CameraInfo.from(this.provider.getCameraInfo(r2)).getCameraCharacteristic(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);     // Catch: Exception -> L15
        if (r1 == null) goto L18;
        if (r1.width() <= 0) goto L18;
        if (r1.height() <= 0) goto L18;
        return new Size(r1.width(), r1.height());
    L18:
        return FALLBACK_SENSOR_ASPECT;
    L15:
        e = move-exception;
        FileLog.e(e);
        goto L18
    }

    private static boolean hasConcurrentFrontBackPair(Context r8) {
        if (r8 != null) goto L5;
    L36:
        return false;
    L5:
        if (Build.VERSION.SDK_INT < 30) goto L36;
        PackageManager r1 = r8.getPackageManager();
        if (r1 == null) goto L36;
        if (r1.hasSystemFeature("android.hardware.camera.concurrent") == false) goto L36;
        CameraManager r82 = (CameraManager) r8.getSystemService(CameraManager.class);
        if (r82 != null) goto L37;
        return false;
    L37:
        Iterator<Set<String>> r12 = r82.getConcurrentCameraIds().iterator();     // Catch: Exception -> L30
    L16:
        if (r12.hasNext() == false) goto L36;
        Iterator<String> r2 = r12.next().iterator();     // Catch: Exception -> L30
        boolean r3 = false;
        boolean r4 = false;
    L20:
        if (r2.hasNext() == false) goto L32;
        Integer r5 = (Integer) r82.getCameraCharacteristics(r2.next()).get(CameraCharacteristics.LENS_FACING);     // Catch: Exception -> L30
        if (r5 == null) goto L20;
        if (r5.intValue() == 0) goto L26;
        if (r5.intValue() != 1) goto L20;
        r4 = true;
        goto L20
    L26:
        r3 = true;
        goto L20
    L32:
        if (r3 == false) goto L16;
        if (r4 == false) goto L16;
        return true;
    L30:
        e = move-exception;
        FileLog.e(e);
        goto L36
    }

    private boolean isPreviewStabilizationSupported(CameraSelector r4) {
        if (r4 != null) goto L19;
    L18:
        return false;
    L19:
        ProcessCameraProvider r1 = this.provider;     // Catch: Exception -> L13
        if (r1 == null) goto L18;
        if (r1.hasCamera(r4) == false) goto L18;
        Map<CameraSelector, Boolean> r12 = STABILIZATION_SUPPORT_CACHE;     // Catch: Exception -> L13
        Boolean r2 = r12.get(r4);     // Catch: Exception -> L13
        if (r2 != null) goto L12;
        boolean r3 = Preview.getPreviewCapabilities(this.provider.getCameraInfo(r4)).isStabilizationSupported();     // Catch: Exception -> L13
        r12.put(r4, Boolean.valueOf(r3));     // Catch: Exception -> L13
        return r3;
    L12:
        return r2.booleanValue();
    L13:
        e = move-exception;
        FileLog.e(e);
        goto L18
    }

    public static boolean isRoundDualAvailable(Context r1) {
        if (DualCameraView.roundDualAvailableStatic(r1) == true) goto L5;
        return false;
    L5:
        if (isSeamlessSwitchingAvailable(r1) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean isSeamlessSwitchingAvailable(Context r2) {
        if (seamlessSwitchingAvailableCache != null) goto L5;
        boolean r1 = true;
        if (SharedConfig.getDevicePerformanceClass() >= 1) goto L9;
    L13:
        r1 = false;
    L14:
        seamlessSwitchingAvailableCache = Boolean.valueOf(r1);
        return seamlessSwitchingAvailableCache.booleanValue();
    L9:
        if (SharedConfig.allowPreparingHevcPlayers() == false) goto L13;
        if (hasConcurrentFrontBackPair(r2) == false) goto L13;
    L5:
        return seamlessSwitchingAvailableCache.booleanValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private /* synthetic */ void lambda$initCamera$3(ListenableFuture r3, boolean r4, Runnable r5) {
        this.provider = (ProcessCameraProvider) r3.get();     // Catch: Exception -> L10
        if (this.lifecycle.getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED) goto L21;
        if (r4 == true) goto L8;
    L12:
        boolean r32 = false;
    L13:
        this.isDualMode = r32;     // Catch: Exception -> L10
        rebindCamera();     // Catch: Exception -> L10
        this.lifecycle.start();     // Catch: Exception -> L10
        if (r5 == null) goto L22;
        r5.run();     // Catch: Exception -> L10
        return;
    L22:
        return;
    L8:
        if (supportsConcurrentFrontBackPair() == false) goto L12;
        r32 = true;
        goto L13
    L21:
        return;
    L10:
        e = move-exception;
        FileLog.e(e);
        this.isInitiated = false;
    }

    private void observeCameraState(Camera r2) {
        r2.getCameraInfo().getCameraState().observe(this.lifecycle, new CameraXSession$$ExternalSyntheticLambda6());     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    private static double previewScore(Size r9, double r10, int r12) {
        int r0 = Math.min(r9.getWidth(), r9.getHeight());
        int r92 = Math.max(r9.getWidth(), r9.getHeight());
        if (r0 <= 0) goto L9;
        if (r92 <= 0) goto L11;
        if (r12 <= 0) goto L12;
        double r1 = r92;
        double r3 = r0;
        return (((Math.max(0.0d, (r1 / r3) - r10) / r10) * 2.0d) + ((Math.abs(r0 - r12) / r12) * 1.0d)) + ((1.0d - (r3 / r1)) * 0.5d);
    L12:
        return Double.MAX_VALUE;
    L11:
        return Double.MAX_VALUE;
    L9:
        return Double.MAX_VALUE;
    }

    private static int previewTier(Size r4, int r5, boolean r6, Set<Size> r7) {
        if (Math.min(r4.getWidth(), r4.getHeight()) >= r5) goto L7;
        return 3;
    L7:
        if (Math.max(r4.getWidth(), r4.getHeight()) <= 1920) goto L9;
        return 2;
    L9:
        if ((r4.getWidth() * r4.getHeight()) > 2100000) goto L21;
        if (r6 == true) goto L13;
        return 0;
    L13:
        if (r7.contains(r4) == true) goto L20;
        return 1;
    L20:
        return 0;
    L21:
        return 2;
    }

    private void rebindCamera() {
        if (this.provider != null) goto L5;
        return;
    L5:
        if (this.lifecycle.getLifecycle().getCurrentState() != Lifecycle.State.DESTROYED) goto L7;
        return;
    L7:
        if (this.isBinding == true) goto L42;
        boolean r0 = true;
        this.isBinding = true;
        this.recordingFrameRate = 30;
        clearCameraReferences();
        this.provider.unbindAll();     // Catch: Throwable -> L13 Exception -> L15
        if (this.isDualMode == false) goto L17;
        bindDualUseCases();     // Catch: Throwable -> L13 Exception -> L15
    L19:
        if (this.camera != null) goto L22;
        r0 = false;
    L22:
        this.isInitiated = r0;     // Catch: Throwable -> L13 Exception -> L15
        this.isBinding = false;
        return;
    L17:
        bindSingleUseCases();     // Catch: Throwable -> L13 Exception -> L15
    L15:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L13
        this.isInitiated = false;     // Catch: Throwable -> L13
        this.provider.unbindAll();     // Catch: Throwable -> L13 Exception -> L28
    L30:
        clearCameraReferences();     // Catch: Throwable -> L13
        this.isBinding = false;
        return;
    L28:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L13
    L13:
        th = move-exception;
        this.isBinding = false;
        throw th;
    }

    private static Range<Integer> selectExtendedFpsRange(Collection<Range<Integer>> r5) {
        Range<Integer> r0 = null;
        if (r5 != null) goto L5;
        return null;
    L5:
        Iterator<Range<Integer>> r52 = r5.iterator();
    L7:
        if (r52.hasNext() == false) goto L20;
        Range<Integer> r1 = r52.next();
        if (r1 == null) goto L7;
        Integer r2 = (Integer) r1.getUpper();
        Range<Integer> r3 = FPS_60_RANGE;
        if (r2.equals(r3.getUpper()) == false) goto L7;
        if (r3.equals(r1) == true) goto L15;
        if (r0 == null) goto L19;
        if (((Integer) r1.getLower()).intValue() <= ((Integer) r0.getLower()).intValue()) goto L7;
    L19:
        r0 = r1;
        goto L7
    L15:
        return r1;
    L20:
        return r0;
    }

    private static List<Size> sortRoundPreviewSizes(List<Size> r5, Size r6, Set<Size> r7) {
        ArrayList r0 = new ArrayList(r5);
        int r52 = SystemUtils.getRoundVideoResolution();
        int r1 = Math.min(1440, Math.max(1024, r52 * 2));
        r0.sort(Comparator.comparingInt(new CameraXSession$$ExternalSyntheticLambda2(r52, !r7.isEmpty(), r7)).thenComparingDouble(new CameraXSession$$ExternalSyntheticLambda3(aspectRatioOf(r6), r1)).thenComparingLong(new CameraXSession$$ExternalSyntheticLambda4()));
        return r0;
    }

    private boolean supportsConcurrentFrontBackPair() {
        ProcessCameraProvider r7 = this.provider;
        if (r7 != null) goto L5;
        return false;
    L5:
        Iterator<List<CameraInfo>> r72 = r7.getAvailableConcurrentCameraInfos().iterator();
    L7:
        if (r72.hasNext() == false) goto L20;
        Iterator<CameraInfo> r1 = r72.next().iterator();
        boolean r2 = false;
        boolean r3 = false;
    L10:
        if (r1.hasNext() == false) goto L17;
        CameraInfo r4 = r1.next();
        if (r4.getLensFacing() != 0) goto L15;
        r2 = true;
    L15:
        if (r4.getLensFacing() != 1) goto L10;
        r3 = true;
        goto L10
    L17:
        if (r2 == false) goto L7;
        if (r3 == false) goto L7;
        return true;
    L20:
        return false;
    }

    private ConcurrentCamera tryBindConcurrentCameras(Range<Integer> r6) {
        boolean r0 = FPS_60_RANGE.equals(r6);     // Catch: Exception -> L5
        CameraSelector r1 = CameraSelector.DEFAULT_FRONT_CAMERA;     // Catch: Exception -> L5
        Preview r3 = buildPreview(r1, false, r6, r0);     // Catch: Exception -> L5
        this.previewUseCase = r3;     // Catch: Exception -> L5
        r3.setSurfaceProvider(this.surfaceProviderPrimary);     // Catch: Exception -> L5
        CameraSelector r32 = CameraSelector.DEFAULT_BACK_CAMERA;     // Catch: Exception -> L5
        Preview r62 = buildPreview(r32, false, r6, r0);     // Catch: Exception -> L5
        this.previewUseCaseBack = r62;     // Catch: Exception -> L5
        Preview.SurfaceProvider r02 = this.surfaceProviderSecondary;     // Catch: Exception -> L5
        if (r02 == null) goto L7;
        r62.setSurfaceProvider(r02);     // Catch: Exception -> L5
    L7:
        ConcurrentCamera.SingleCameraConfig r03 = new ConcurrentCamera.SingleCameraConfig(r1, new UseCaseGroup.Builder().addUseCase(this.previewUseCase).build(), this.lifecycle);     // Catch: Exception -> L5
        ConcurrentCamera.SingleCameraConfig r12 = new ConcurrentCamera.SingleCameraConfig(r32, new UseCaseGroup.Builder().addUseCase(this.previewUseCaseBack).build(), this.lifecycle);     // Catch: Exception -> L5
        ArrayList r63 = new ArrayList(2);     // Catch: Exception -> L5
        r63.add(r03);     // Catch: Exception -> L5
        r63.add(r12);     // Catch: Exception -> L5
        return this.provider.bindToLifecycle(r63);
    L5:
        e = move-exception;
        FileLog.e(e);
        this.provider.unbindAll();     // Catch: Exception -> L12
        return null;
    L12:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    private Camera tryBindSingleAtExtendedFrameRate() {
        SessionConfig.Builder r1 = new SessionConfig.Builder(new UseCase[]{this.previewUseCase});     // Catch: Exception -> L8
        Range<Integer> r2 = selectExtendedFpsRange(this.provider.getCameraInfo(this.cameraSelector).getSupportedFrameRateRanges(r1.build()));     // Catch: Exception -> L8
        if (r2 != null) goto L6;
        return null;
    L6:
        r1.setFrameRateRange(r2);     // Catch: Exception -> L8
        return tryBindSingleSession(r1.build(), ((Integer) r2.getUpper()).intValue());
    L8:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    private Camera tryBindSingleAtFrameRate(Range<Integer> r7) {
        CameraInfo r1 = this.provider.getCameraInfo(this.cameraSelector);     // Catch: Exception -> L8
        SessionConfig.Builder r2 = new SessionConfig.Builder(new UseCase[]{this.previewUseCase});     // Catch: Exception -> L8
        if (r1.getSupportedFrameRateRanges(r2.build()).contains(r7) == true) goto L6;
        return null;
    L6:
        r2.setFrameRateRange(r7);     // Catch: Exception -> L8
        return tryBindSingleSession(r2.build(), ((Integer) r7.getUpper()).intValue());
    L8:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    private Camera tryBindSingleSession(SessionConfig r5, int r6) {
    L10:
        e = move-exception;
        FileLog.e(e);
        return null;
    L4:
        if (this.provider.getCameraInfo(this.cameraSelector).isSessionConfigSupported(r5) == true) goto L6;
        return null;
    L6:
        Camera r52 = this.provider.bindToLifecycle(this.lifecycle, this.cameraSelector, r5);     // Catch: Exception -> L10
        if (r52 == null) goto L12;
        this.recordingFrameRate = r6;     // Catch: Exception -> L10
        return r52;
    L12:
        return r52;
    }

    private void updateActiveControl(boolean r1) {
        if (r1 == false) goto L5;
        this.camera = this.cameraFront;
        this.cameraControl = this.cameraControlFront;
        return;
    L5:
        this.camera = this.cameraBack;
        this.cameraControl = this.cameraControlBack;
    }

    private void updateTorchState() {
        boolean r1 = true;
        if (this.isDualMode == false) goto L21;
        CameraControl r0 = this.cameraControlFront;     // Catch: Exception -> L38
        if (r0 == null) goto L8;
        r0.enableTorch(false);     // Catch: Exception -> L38
    L8:
        if (this.cameraControlBack == null) goto L42;
        Camera r02 = this.cameraBack;     // Catch: Exception -> L38
        if (r02 != null) goto L12;
        return;
    L12:
        if (r02.getCameraInfo().hasFlashUnit() == false) goto L44;
        CameraControl r03 = this.cameraControlBack;     // Catch: Exception -> L38
        if (this.isTorchOn == true) goto L16;
    L18:
        r1 = false;
    L19:
        r03.enableTorch(r1);     // Catch: Exception -> L38
        return;
    L16:
        if (this.isFrontface == true) goto L18;
    L44:
        return;
    L42:
        return;
    L21:
        Camera r04 = this.camera;     // Catch: Exception -> L38
        if (r04 != null) goto L24;
        return;
    L24:
        if (this.cameraControl != null) goto L26;
        return;
    L26:
        if (r04.getCameraInfo().hasFlashUnit() == true) goto L28;
        return;
    L28:
        if (this.camera.getCameraInfo().getLensFacing() != 1) goto L30;
        boolean r05 = true;
    L31:
        CameraControl r3 = this.cameraControl;     // Catch: Exception -> L38
        if (this.isTorchOn == false) goto L35;
        if (r05 == false) goto L35;
    L36:
        r3.enableTorch(r1);     // Catch: Exception -> L38
        return;
    L35:
        r1 = false;
        goto L36
    L30:
        r05 = false;
    L38:
        e = move-exception;
        FileLog.e(e);
    }

    private boolean wantsWideAngleStart(Camera r1) {
        if (ExteraConfig.getStartWithWideAngleCamera() == false) goto L8;
        if (r1 != null) goto L6;
        return false;
    L6:
        if (r1.getCameraInfo().getLensFacing() != 1) goto L11;
        return true;
    L11:
        return false;
    L8:
        return false;
    }

    public void closeCamera() {
        if (Looper.myLooper() == Looper.getMainLooper()) goto L51;
        AndroidUtilities.runOnUIThread(new CameraXSession$$ExternalSyntheticLambda8(this));
        return;
    L51:
        ProcessCameraProvider r2 = this.provider;     // Catch: Throwable -> L11 Exception -> L13
        if (r2 == null) goto L55;
        r2.unbindAll();     // Catch: Throwable -> L11 Exception -> L13
    L55:
        Preview r22 = this.previewUseCase;     // Catch: Exception -> L18
        if (r22 == null) goto L20;
        r22.setSurfaceProvider(null);     // Catch: Exception -> L18
    L20:
        Preview r23 = this.previewUseCaseBack;     // Catch: Exception -> L18
        if (r23 == null) goto L25;
        r23.setSurfaceProvider(null);     // Catch: Exception -> L18
    L25:
        this.lifecycle.stop();
        this.isInitiated = false;
        clearCameraReferences();
        return;
    L18:
        e = e;
    L24:
        FileLog.e(e);
    L13:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L11
        Preview r24 = this.previewUseCase;     // Catch: Exception -> L30
        if (r24 == null) goto L32;
        r24.setSurfaceProvider(null);     // Catch: Exception -> L30
    L32:
        Preview r25 = this.previewUseCaseBack;     // Catch: Exception -> L30
        if (r25 == null) goto L25;
        r25.setSurfaceProvider(null);     // Catch: Exception -> L30
    L30:
        e = e;
    L11:
        th = move-exception;
        Preview r3 = this.previewUseCase;     // Catch: Exception -> L40
        if (r3 == null) goto L42;
        r3.setSurfaceProvider(null);     // Catch: Exception -> L40
    L42:
        Preview r32 = this.previewUseCaseBack;     // Catch: Exception -> L40
        if (r32 == null) goto L47;
        r32.setSurfaceProvider(null);     // Catch: Exception -> L40
    L47:
        this.lifecycle.stop();
        this.isInitiated = false;
        clearCameraReferences();
        throw th;
    L40:
        e = move-exception;
        FileLog.e(e);
        goto L47
    }

    public void focusToPoint(float r4, float r5, float r6, float r7) {
        if (this.cameraControl != null) goto L5;
        return;
    L5:
        if (this.camera != null) goto L7;
        return;
    L7:
        if (r6 > 0.0f) goto L9;
        return;
    L9:
        if (r7 <= 0.0f) goto L24;
        Display r0 = getDefaultDisplay();
        if (r0 == null) goto L25;
        FocusMeteringAction r42 = new FocusMeteringAction.Builder(new DisplayOrientedMeteringPointFactory(r0, this.camera.getCameraInfo(), r6, r7).createPoint(r4, r5), 3).build();     // Catch: Exception -> L16
        this.cameraControl.startFocusAndMetering(r42);     // Catch: Exception -> L16
        return;
    L16:
        e = move-exception;
        FileLog.e(e);
        return;
    L25:
        return;
    }

    public String getActiveCameraId() {
        Camera r1 = this.camera;
        if (r1 != null) goto L10;
        return null;
    L10:
        return Camera2CameraInfo.from(r1.getCameraInfo()).getCameraId();
    L7:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    public int getDisplayOrientation() {
        Display r2 = getDefaultDisplay();
        if (r2 == null) goto L5;
        int r22 = r2.getRotation();
    L7:
        if (r22 != 1) goto L9;
        return 90;
    L9:
        if (r22 != 2) goto L11;
        return 180;
    L11:
        if (r22 == 3) goto L13;
        return 0;
    L13:
        return 270;
    L5:
        r22 = 0;
        goto L7
    }

    public float getLinearZoom() {
        Camera r1 = this.camera;
        if (r1 != null) goto L5;
        return 0.0f;
    L5:
        ZoomState r12 = r1.getCameraInfo().getZoomState().getValue();
        if (r12 != null) goto L8;
        return 0.0f;
    L8:
        return r12.getLinearZoom();
    }

    public float getMaxZoomRatio() {
        Camera r0 = this.camera;
        if (r0 == null) goto L8;
        ZoomState r02 = r0.getCameraInfo().getZoomState().getValue();
        if (r02 != null) goto L7;
        return 1.0f;
    L7:
        return r02.getMaxZoomRatio();
    L8:
        return 1.0f;
    }

    public float getMinZoomRatio() {
        Camera r0 = this.camera;
        if (r0 == null) goto L8;
        ZoomState r02 = r0.getCameraInfo().getZoomState().getValue();
        if (r02 != null) goto L7;
        return 1.0f;
    L7:
        return r02.getMinZoomRatio();
    L8:
        return 1.0f;
    }

    public int getRecordingFrameRate() {
        return this.recordingFrameRate;
    }

    public float getZoomRatio() {
        Camera r1 = this.camera;
        if (r1 != null) goto L5;
        return 1.0f;
    L5:
        ZoomState r12 = r1.getCameraInfo().getZoomState().getValue();
        if (r12 != null) goto L8;
        return 1.0f;
    L8:
        return r12.getZoomRatio();
    }

    public void initCamera(Context r2, boolean r3, boolean r4, Runnable r5) {
        this.isFrontface = r3;
        ListenableFuture<ProcessCameraProvider> r32 = ProcessCameraProvider.getInstance(r2);
        r32.addListener(new CameraXSession$$ExternalSyntheticLambda7(this, r32, r4, r5), ContextCompat.getMainExecutor(r2));
    }

    public boolean isActiveCameraFrontface() {
        Camera r0 = this.camera;
        if (r0 == null) goto L5;
        int r02 = r0.getCameraInfo().getLensFacing();
        if (r02 != 0) goto L9;
        return true;
    L9:
        if (r02 != 1) goto L13;
        return false;
    L13:
        return this.isFrontface;
    L5:
        return this.isFrontface;
    }

    public boolean isDualMode() {
        return this.isDualMode;
    }

    public boolean isFrontface() {
        return this.isFrontface;
    }

    public boolean isInitiated() {
        return this.isInitiated;
    }

    public boolean isReady() {
        if (this.isInitiated == true) goto L5;
        return false;
    L5:
        if (this.isBinding == false) goto L7;
        return false;
    L7:
        if (this.camera == null) goto L13;
        return true;
    L13:
        return false;
    }

    public void setSecondSurfaceProvider(Preview.SurfaceProvider r2) {
        this.surfaceProviderSecondary = r2;
        if (this.isInitiated == true) goto L5;
        return;
    L5:
        if (this.isDualMode == false) goto L11;
        Preview r1 = this.previewUseCaseBack;
        if (r1 == null) goto L12;
        r1.setSurfaceProvider(r2);
        return;
    L12:
        return;
    }

    public void setTorchEnabled(boolean r1) {
        this.isTorchOn = r1;
        updateTorchState();
    }

    public void setZoomRatio(float r1) {
        CameraControl r0 = this.cameraControl;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.setZoomRatio(r1);
    }

    public void switchCamera() {
        boolean r0 = !this.isFrontface;
        this.isFrontface = r0;
        if (this.isDualMode == true) goto L5;
    L10:
        rebindCamera();
        return;
    L5:
        if (this.cameraFront == null) goto L10;
        if (this.cameraBack == null) goto L10;
        updateActiveControl(r0);
        updateTorchState();
    }
}
