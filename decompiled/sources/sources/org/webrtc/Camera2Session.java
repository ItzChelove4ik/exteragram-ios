package org.webrtc;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.os.Handler;
import android.util.Range;
import android.view.Surface;
import java.util.Arrays;
import java.util.List;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.telegram.ui.Stories.LivePlayer;
import org.webrtc.CameraEnumerationAndroid;
import org.webrtc.CameraSession;

/* loaded from: classes6.dex */
class Camera2Session implements CameraSession {
    private static final String TAG = "Camera2Session";
    private static final Histogram camera2ResolutionHistogram = null;
    private static final Histogram camera2StartTimeMsHistogram = null;
    private static final Histogram camera2StopTimeMsHistogram = null;
    private final Context applicationContext;
    private final CameraSession.CreateSessionCallback callback;
    private CameraCharacteristics cameraCharacteristics;
    private CameraDevice cameraDevice;
    private final String cameraId;
    private final CameraManager cameraManager;
    private int cameraOrientation;
    private final Handler cameraThreadHandler;
    private CameraEnumerationAndroid.CaptureFormat captureFormat;
    private CameraCaptureSession captureSession;
    private final long constructionTimeNs;
    private final CameraSession.Events events;
    private boolean firstFrameReported;
    private int fpsUnitFactor;
    private final int framerate;
    private final int height;
    private boolean isCameraFrontFacing;
    private OrientationHelper orientationHelper;
    private SessionState state;
    private Surface surface;
    private final SurfaceTextureHelper surfaceTextureHelper;
    private final int width;

    public static class CameraCaptureCallback extends CameraCaptureSession.CaptureCallback {
        private CameraCaptureCallback() {
        }

        public /* synthetic */ CameraCaptureCallback(Camera2SessionIA r1) {
            this();
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureFailed(CameraCaptureSession r1, CaptureRequest r2, CaptureFailure r3) {
            Logging.d(Camera2Session.TAG, "Capture failed: " + r3);
        }
    }

    public class CameraStateCallback extends CameraDevice.StateCallback {
        final /* synthetic */ Camera2Session this$0;

        private CameraStateCallback(Camera2Session r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ CameraStateCallback(Camera2Session r1, Camera2SessionIA r2) {
            this(r1);
        }

        private String getErrorDescription(int r2) {
            if (r2 != 1) goto L5;
            return "Camera device is in use already.";
        L5:
            if (r2 != 2) goto L7;
            return "Camera device could not be opened because there are too many other open camera devices.";
        L7:
            if (r2 != 3) goto L9;
            return "Camera device could not be opened due to a device policy.";
        L9:
            if (r2 != 4) goto L11;
            return "Camera device has encountered a fatal error.";
        L11:
            if (r2 != 5) goto L13;
            return "Camera service has encountered a fatal error.";
        L13:
            return "Unknown camera error: " + r2;
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onClosed(CameraDevice r2) {
            Camera2Session.m22301$$Nest$mcheckIsOnCameraThread(this.this$0);
            Logging.d(Camera2Session.TAG, "Camera device closed.");
            Camera2Session.m22289$$Nest$fgetevents(this.this$0).onCameraClosed(this.this$0);
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onDisconnected(CameraDevice r3) {
            Camera2Session.m22301$$Nest$mcheckIsOnCameraThread(this.this$0);
            if (Camera2Session.m22287$$Nest$fgetcaptureSession(this.this$0) == null) goto L5;
        L7:
            boolean r32 = false;
        L8:
            Camera2Session.m22299$$Nest$fputstate(this.this$0, SessionState.STOPPED);
            Camera2Session.m22304$$Nest$mstopInternal(this.this$0);
            Camera2Session r0 = this.this$0;
            if (r32 == false) goto L12;
            Camera2Session.m22281$$Nest$fgetcallback(r0).onFailure(CameraSession.FailureType.DISCONNECTED, "Camera disconnected / evicted.");
            return;
        L12:
            Camera2Session.m22289$$Nest$fgetevents(r0).onCameraDisconnected(this.this$0);
            return;
        L5:
            if (Camera2Session.m22293$$Nest$fgetstate(this.this$0) == SessionState.STOPPED) goto L7;
            r32 = true;
            goto L8
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onError(CameraDevice r1, int r2) {
            Camera2Session.m22301$$Nest$mcheckIsOnCameraThread(this.this$0);
            Camera2Session.m22303$$Nest$mreportError(this.this$0, getErrorDescription(r2));
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onOpened(CameraDevice r5) {
            Camera2Session.m22301$$Nest$mcheckIsOnCameraThread(this.this$0);
            Logging.d(Camera2Session.TAG, "Camera opened.");
            Camera2Session.m22296$$Nest$fputcameraDevice(this.this$0, r5);
            Camera2Session.m22295$$Nest$fgetsurfaceTextureHelper(this.this$0).setTextureSize(Camera2Session.m22286$$Nest$fgetcaptureFormat(this.this$0).width, Camera2Session.m22286$$Nest$fgetcaptureFormat(this.this$0).height);
            Camera2Session.m22300$$Nest$fputsurface(this.this$0, new Surface(Camera2Session.m22295$$Nest$fgetsurfaceTextureHelper(this.this$0).getSurfaceTexture()));
            r5.createCaptureSession(Arrays.asList(new Surface[]{Camera2Session.m22294$$Nest$fgetsurface(this.this$0)}), new CaptureSessionCallback(this.this$0, null), Camera2Session.m22285$$Nest$fgetcameraThreadHandler(this.this$0));     // Catch: CameraAccessException -> L5
            return;
        L5:
            e = move-exception;
            Camera2Session.m22303$$Nest$mreportError(this.this$0, "Failed to create capture session. " + e);
        }
    }

    public class CaptureSessionCallback extends CameraCaptureSession.StateCallback {
        final /* synthetic */ Camera2Session this$0;

        public static /* synthetic */ void $r8$lambda$Du4gheEjtRdCZNeaqhQeHR1g9L4(CaptureSessionCallback r0, VideoFrame r1) {
            r0.lambda$onConfigured$0(r1);
        }

        private CaptureSessionCallback(Camera2Session r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ CaptureSessionCallback(Camera2Session r1, Camera2SessionIA r2) {
            this(r1);
        }

        private void chooseFocusMode(CaptureRequest.Builder r6) {
            int[] r5 = (int[]) Camera2Session.m22282$$Nest$fgetcameraCharacteristics(this.this$0).get(CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES);
            int r0 = r5.length;
            int r1 = 0;
        L4:
            if (r1 >= r0) goto L10;
            if (r5[r1] == 3) goto L7;
            r1 = r1 + 1;
            goto L4
        L7:
            r6.set(CaptureRequest.CONTROL_AF_MODE, 3);
            Logging.d(Camera2Session.TAG, "Using continuous video auto-focus.");
            return;
        L10:
            Logging.d(Camera2Session.TAG, "Auto-focus is not available.");
        }

        private void chooseStabilizationMode(CaptureRequest.Builder r10) {
            int r0 = 0;
            int[] r4 = (int[]) Camera2Session.m22282$$Nest$fgetcameraCharacteristics(this.this$0).get(CameraCharacteristics.LENS_INFO_AVAILABLE_OPTICAL_STABILIZATION);
            if (r4 == null) goto L11;
            int r6 = r4.length;
            int r7 = 0;
        L5:
            if (r7 >= r6) goto L11;
            if (r4[r7] == 1) goto L8;
            r7 = r7 + 1;
            goto L5
        L8:
            r10.set(CaptureRequest.LENS_OPTICAL_STABILIZATION_MODE, 1);
            r10.set(CaptureRequest.CONTROL_VIDEO_STABILIZATION_MODE, 0);
            Logging.d(Camera2Session.TAG, "Using optical stabilization.");
            return;
        L11:
            int[] r9 = (int[]) Camera2Session.m22282$$Nest$fgetcameraCharacteristics(this.this$0).get(CameraCharacteristics.CONTROL_AVAILABLE_VIDEO_STABILIZATION_MODES);
            int r42 = r9.length;
        L12:
            if (r0 >= r42) goto L18;
            if (r9[r0] == 1) goto L15;
            r0 = r0 + 1;
            goto L12
        L15:
            r10.set(CaptureRequest.CONTROL_VIDEO_STABILIZATION_MODE, 1);
            r10.set(CaptureRequest.LENS_OPTICAL_STABILIZATION_MODE, 0);
            Logging.d(Camera2Session.TAG, "Using video stabilization.");
            return;
        L18:
            Logging.d(Camera2Session.TAG, "Stabilization not available.");
        }

        private /* synthetic */ void lambda$onConfigured$0(VideoFrame r6) {
            Camera2Session.m22301$$Nest$mcheckIsOnCameraThread(this.this$0);
            if (Camera2Session.m22293$$Nest$fgetstate(this.this$0) == SessionState.RUNNING) goto L7;
            Logging.d(Camera2Session.TAG, "Texture frame captured but camera is no longer running.");
            return;
        L7:
            if (Camera2Session.m22290$$Nest$fgetfirstFrameReported(this.this$0) == true) goto L9;
            Camera2Session.m22298$$Nest$fputfirstFrameReported(this.this$0, true);
            int r0 = (int) ((System.nanoTime() - Camera2Session.m22288$$Nest$fgetconstructionTimeNs(this.this$0)) / 1000000);
            Camera2Session.m22305$$Nest$sfgetcamera2StartTimeMsHistogram().addSample(r0);
        L9:
            VideoFrame r02 = new VideoFrame(CameraSession.createTextureBufferWithModifiedTransformMatrix((TextureBufferImpl) r6.getBuffer(), Camera2Session.m22292$$Nest$fgetisCameraFrontFacing(this.this$0), -Camera2Session.m22284$$Nest$fgetcameraOrientation(this.this$0)), Camera2Session.m22302$$Nest$mgetFrameOrientation(this.this$0), r6.getTimestampNs());
            Camera2Session.m22289$$Nest$fgetevents(this.this$0).onFrameCaptured(this.this$0, r02);
            r02.release();
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigureFailed(CameraCaptureSession r2) {
            Camera2Session.m22301$$Nest$mcheckIsOnCameraThread(this.this$0);
            r2.close();
            Camera2Session.m22303$$Nest$mreportError(this.this$0, "Failed to configure capture session.");
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigured(CameraCaptureSession r8) {
            Camera2Session.m22301$$Nest$mcheckIsOnCameraThread(this.this$0);
            Logging.d(Camera2Session.TAG, "Camera capture session configured.");
            Camera2Session.m22297$$Nest$fputcaptureSession(this.this$0, r8);
            CaptureRequest.Builder r0 = Camera2Session.m22283$$Nest$fgetcameraDevice(this.this$0).createCaptureRequest(3);     // Catch: CameraAccessException -> L6
            r0.set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, new Range(Integer.valueOf(Camera2Session.m22286$$Nest$fgetcaptureFormat(this.this$0).framerate.min / Camera2Session.m22291$$Nest$fgetfpsUnitFactor(this.this$0)), Integer.valueOf(Camera2Session.m22286$$Nest$fgetcaptureFormat(this.this$0).framerate.max / Camera2Session.m22291$$Nest$fgetfpsUnitFactor(this.this$0))));     // Catch: CameraAccessException -> L6
            r0.set(CaptureRequest.CONTROL_AE_MODE, 1);     // Catch: CameraAccessException -> L6
            r0.set(CaptureRequest.CONTROL_AE_LOCK, Boolean.FALSE);     // Catch: CameraAccessException -> L6
            chooseStabilizationMode(r0);     // Catch: CameraAccessException -> L6
            chooseFocusMode(r0);     // Catch: CameraAccessException -> L6
            r0.addTarget(Camera2Session.m22294$$Nest$fgetsurface(this.this$0));     // Catch: CameraAccessException -> L6
            r8.setRepeatingRequest(r0.build(), new CameraCaptureCallback(null), Camera2Session.m22285$$Nest$fgetcameraThreadHandler(this.this$0));     // Catch: CameraAccessException -> L6
            Camera2Session.m22295$$Nest$fgetsurfaceTextureHelper(this.this$0).startListening(new Camera2Session$CaptureSessionCallback$$ExternalSyntheticLambda0(this));
            Logging.d(Camera2Session.TAG, "Camera device successfully started.");
            Camera2Session.m22281$$Nest$fgetcallback(this.this$0).onDone(this.this$0);
            return;
        L6:
            e = move-exception;
            Camera2Session.m22303$$Nest$mreportError(this.this$0, "Failed to start capture request. " + e);
        }
    }

    public enum SessionState extends Enum<SessionState> {
        private static final /* synthetic */ SessionState[] $VALUES = null;
        public static final SessionState RUNNING = null;
        public static final SessionState STOPPED = null;

        private static /* synthetic */ SessionState[] $values() {
            return new SessionState[]{RUNNING, STOPPED};
        }

        static {
            RUNNING = new SessionState("RUNNING", 0);
            STOPPED = new SessionState("STOPPED", 1);
            $VALUES = $values();
        }

        SessionState(String r1, int r2) {
        }

        public static SessionState valueOf(String r1) {
            return (SessionState) Enum.valueOf(SessionState.class, r1);
        }

        public static SessionState[] values() {
            return (SessionState[]) $VALUES.clone();
        }
    }

    /* renamed from: -$$Nest$fgetcallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CameraSession.CreateSessionCallback m22281$$Nest$fgetcallback(Camera2Session r0) {
        return r0.callback;
    }

    /* renamed from: -$$Nest$fgetcameraCharacteristics, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CameraCharacteristics m22282$$Nest$fgetcameraCharacteristics(Camera2Session r0) {
        return r0.cameraCharacteristics;
    }

    /* renamed from: -$$Nest$fgetcameraDevice, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CameraDevice m22283$$Nest$fgetcameraDevice(Camera2Session r0) {
        return r0.cameraDevice;
    }

    /* renamed from: -$$Nest$fgetcameraOrientation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m22284$$Nest$fgetcameraOrientation(Camera2Session r0) {
        return r0.cameraOrientation;
    }

    /* renamed from: -$$Nest$fgetcameraThreadHandler, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Handler m22285$$Nest$fgetcameraThreadHandler(Camera2Session r0) {
        return r0.cameraThreadHandler;
    }

    /* renamed from: -$$Nest$fgetcaptureFormat, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CameraEnumerationAndroid.CaptureFormat m22286$$Nest$fgetcaptureFormat(Camera2Session r0) {
        return r0.captureFormat;
    }

    /* renamed from: -$$Nest$fgetcaptureSession, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CameraCaptureSession m22287$$Nest$fgetcaptureSession(Camera2Session r0) {
        return r0.captureSession;
    }

    /* renamed from: -$$Nest$fgetconstructionTimeNs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m22288$$Nest$fgetconstructionTimeNs(Camera2Session r2) {
        return r2.constructionTimeNs;
    }

    /* renamed from: -$$Nest$fgetevents, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CameraSession.Events m22289$$Nest$fgetevents(Camera2Session r0) {
        return r0.events;
    }

    /* renamed from: -$$Nest$fgetfirstFrameReported, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m22290$$Nest$fgetfirstFrameReported(Camera2Session r0) {
        return r0.firstFrameReported;
    }

    /* renamed from: -$$Nest$fgetfpsUnitFactor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m22291$$Nest$fgetfpsUnitFactor(Camera2Session r0) {
        return r0.fpsUnitFactor;
    }

    /* renamed from: -$$Nest$fgetisCameraFrontFacing, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m22292$$Nest$fgetisCameraFrontFacing(Camera2Session r0) {
        return r0.isCameraFrontFacing;
    }

    /* renamed from: -$$Nest$fgetstate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SessionState m22293$$Nest$fgetstate(Camera2Session r0) {
        return r0.state;
    }

    /* renamed from: -$$Nest$fgetsurface, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Surface m22294$$Nest$fgetsurface(Camera2Session r0) {
        return r0.surface;
    }

    /* renamed from: -$$Nest$fgetsurfaceTextureHelper, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SurfaceTextureHelper m22295$$Nest$fgetsurfaceTextureHelper(Camera2Session r0) {
        return r0.surfaceTextureHelper;
    }

    /* renamed from: -$$Nest$fputcameraDevice, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22296$$Nest$fputcameraDevice(Camera2Session r0, CameraDevice r1) {
        r0.cameraDevice = r1;
    }

    /* renamed from: -$$Nest$fputcaptureSession, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22297$$Nest$fputcaptureSession(Camera2Session r0, CameraCaptureSession r1) {
        r0.captureSession = r1;
    }

    /* renamed from: -$$Nest$fputfirstFrameReported, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22298$$Nest$fputfirstFrameReported(Camera2Session r0, boolean r1) {
        r0.firstFrameReported = r1;
    }

    /* renamed from: -$$Nest$fputstate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22299$$Nest$fputstate(Camera2Session r0, SessionState r1) {
        r0.state = r1;
    }

    /* renamed from: -$$Nest$fputsurface, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22300$$Nest$fputsurface(Camera2Session r0, Surface r1) {
        r0.surface = r1;
    }

    /* renamed from: -$$Nest$mcheckIsOnCameraThread, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22301$$Nest$mcheckIsOnCameraThread(Camera2Session r0) {
        r0.checkIsOnCameraThread();
    }

    /* renamed from: -$$Nest$mgetFrameOrientation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m22302$$Nest$mgetFrameOrientation(Camera2Session r0) {
        return r0.getFrameOrientation();
    }

    /* renamed from: -$$Nest$mreportError, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22303$$Nest$mreportError(Camera2Session r0, String r1) {
        r0.reportError(r1);
    }

    /* renamed from: -$$Nest$mstopInternal, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22304$$Nest$mstopInternal(Camera2Session r0) {
        r0.stopInternal();
    }

    /* renamed from: -$$Nest$sfgetcamera2StartTimeMsHistogram, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Histogram m22305$$Nest$sfgetcamera2StartTimeMsHistogram() {
        return camera2StartTimeMsHistogram;
    }

    static {
        camera2StartTimeMsHistogram = Histogram.createCounts("WebRTC.Android.Camera2.StartTimeMs", 1, 10000, 50);
        camera2StopTimeMsHistogram = Histogram.createCounts("WebRTC.Android.Camera2.StopTimeMs", 1, 10000, 50);
        camera2ResolutionHistogram = Histogram.createEnumeration("WebRTC.Android.Camera2.Resolution", CameraEnumerationAndroid.COMMON_RESOLUTIONS.size());
    }

    private Camera2Session(CameraSession.CreateSessionCallback r3, CameraSession.Events r4, Context r5, CameraManager r6, SurfaceTextureHelper r7, String r8, int r9, int r10, int r11) {
        this.state = SessionState.RUNNING;
        Logging.d(TAG, "Create new camera2 session on camera " + r8);
        this.constructionTimeNs = System.nanoTime();
        this.cameraThreadHandler = new Handler();
        this.callback = r3;
        this.events = r4;
        this.applicationContext = r5;
        this.cameraManager = r6;
        this.surfaceTextureHelper = r7;
        this.cameraId = r8;
        this.width = r9;
        this.height = r10;
        this.framerate = r11;
        this.orientationHelper = new OrientationHelper();
        start();
    }

    private void checkIsOnCameraThread() {
        if (Thread.currentThread() != this.cameraThreadHandler.getLooper().getThread()) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("Wrong thread");
    }

    public static void create(CameraSession.CreateSessionCallback r10, CameraSession.Events r11, Context r12, CameraManager r13, SurfaceTextureHelper r14, String r15, int r16, int r17, int r18) {
        new Camera2Session(r10, r11, r12, r13, r14, r15, r16, r17, r18);
    }

    private void findCaptureFormat() {
        checkIsOnCameraThread();
        Range[] r0 = (Range[]) this.cameraCharacteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES);
        int r1 = Camera2Enumerator.getFpsUnitFactor(r0);
        this.fpsUnitFactor = r1;
        List<CameraEnumerationAndroid.CaptureFormat.FramerateRange> r02 = Camera2Enumerator.convertFramerates(r0, r1);
        List<Size> r12 = Camera2Enumerator.getSupportedSizes(this.cameraCharacteristics);
        Logging.d(TAG, "Available preview sizes: " + r12);
        Logging.d(TAG, "Available fps ranges: " + r02);
        if (r02.isEmpty() == false) goto L5;
    L9:
        reportError("No supported capture formats.");
        return;
    L5:
        if (r12.isEmpty() == true) goto L9;
        CameraEnumerationAndroid.CaptureFormat.FramerateRange r03 = CameraEnumerationAndroid.getClosestSupportedFramerateRange(r02, this.framerate);
        Size r13 = CameraEnumerationAndroid.getClosestSupportedSize(r12, this.width, this.height);
        CameraEnumerationAndroid.reportCameraResolution(camera2ResolutionHistogram, r13);
        this.captureFormat = new CameraEnumerationAndroid.CaptureFormat(r13.width, r13.height, r03);
        Logging.d(TAG, "Using capture format: " + this.captureFormat);
    }

    private int getFrameOrientation() {
        if (LivePlayer.recording == null) goto L5;
        int r0 = 0;
    L6:
        OrientationHelper.cameraOrientation = r0;
        if (this.isCameraFrontFacing == false) goto L9;
        r0 = 360 - r0;
    L9:
        OrientationHelper.cameraRotation = r0;
        return (this.cameraOrientation + r0) % 360;
    L5:
        r0 = this.orientationHelper.getOrientation();
        goto L6
    }

    private void openCamera() {
        checkIsOnCameraThread();
        Logging.d(TAG, "Opening camera " + this.cameraId);
        this.events.onCameraOpening();
        this.cameraManager.openCamera(this.cameraId, new CameraStateCallback(this, null), this.cameraThreadHandler);     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        reportError("Failed to open camera: " + e);
    }

    private void reportError(String r3) {
        checkIsOnCameraThread();
        Logging.e(TAG, "Error: " + r3);
        if (this.captureSession == null) goto L5;
    L7:
        boolean r0 = false;
    L8:
        this.state = SessionState.STOPPED;
        stopInternal();
        if (r0 == false) goto L12;
        this.callback.onFailure(CameraSession.FailureType.ERROR, r3);
        return;
    L12:
        this.events.onCameraError(this, r3);
        return;
    L5:
        if (this.state == SessionState.STOPPED) goto L7;
        r0 = true;
        goto L8
    }

    private void start() {
        checkIsOnCameraThread();
        Logging.d(TAG, "start");
        this.cameraCharacteristics = this.cameraManager.getCameraCharacteristics(this.cameraId);     // Catch: Throwable -> L10
        this.orientationHelper.start();
        this.cameraOrientation = ((Integer) this.cameraCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION)).intValue();
        if (((Integer) this.cameraCharacteristics.get(CameraCharacteristics.LENS_FACING)).intValue() != 0) goto L7;
        boolean r0 = true;
    L8:
        this.isCameraFrontFacing = r0;
        findCaptureFormat();
        openCamera();
        return;
    L7:
        r0 = false;
    L10:
        th = move-exception;
        reportError("getCameraCharacteristics(): " + th.getMessage());
    }

    private void stopInternal() {
        Logging.d(TAG, "Stop internal");
        checkIsOnCameraThread();
        this.surfaceTextureHelper.stopListening();
        CameraCaptureSession r0 = this.captureSession;
        if (r0 == null) goto L5;
        r0.close();
        this.captureSession = null;
    L5:
        Surface r02 = this.surface;
        if (r02 == null) goto L8;
        r02.release();
        this.surface = null;
    L8:
        CameraDevice r03 = this.cameraDevice;
        if (r03 == null) goto L11;
        r03.close();
        this.cameraDevice = null;
    L11:
        OrientationHelper r3 = this.orientationHelper;
        if (r3 == null) goto L14;
        r3.stop();
    L14:
        Logging.d(TAG, "Stop done");
    }

    @Override // org.webrtc.CameraSession
    public void stop() {
        Logging.d(TAG, "Stop camera2 session on camera " + this.cameraId);
        checkIsOnCameraThread();
        SessionState r0 = this.state;
        SessionState r1 = SessionState.STOPPED;
        if (r0 == r1) goto L6;
        long r2 = System.nanoTime();
        this.state = r1;
        stopInternal();
        camera2StopTimeMsHistogram.addSample((int) ((System.nanoTime() - r2) / 1000000));
        return;
    }
}
