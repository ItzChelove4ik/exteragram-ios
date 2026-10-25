package org.webrtc;

import android.content.Context;
import android.hardware.Camera;
import android.os.Handler;
import android.os.SystemClock;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.telegram.ui.Stories.LivePlayer;
import org.webrtc.CameraEnumerationAndroid;
import org.webrtc.CameraSession;

/* loaded from: classes6.dex */
class Camera1Session implements CameraSession {
    private static final int NUMBER_OF_CAPTURE_BUFFERS = 3;
    private static final String TAG = "Camera1Session";
    private static final Histogram camera1ResolutionHistogram = null;
    private static final Histogram camera1StartTimeMsHistogram = null;
    private static final Histogram camera1StopTimeMsHistogram = null;
    private final Context applicationContext;
    private final Camera camera;
    private final int cameraId;
    private final Handler cameraThreadHandler;
    private final CameraEnumerationAndroid.CaptureFormat captureFormat;
    private final boolean captureToTexture;
    private final long constructionTimeNs;
    private final CameraSession.Events events;
    private boolean firstFrameReported;
    private final Camera.CameraInfo info;
    private OrientationHelper orientationHelper;
    private SessionState state;
    private final SurfaceTextureHelper surfaceTextureHelper;

    /* renamed from: org.webrtc.Camera1Session$1, reason: invalid class name */
    public class AnonymousClass1 implements Camera.ErrorCallback {
        final /* synthetic */ Camera1Session this$0;

        public AnonymousClass1(Camera1Session r1) {
            this.this$0 = r1;
        }

        @Override // android.hardware.Camera.ErrorCallback
        public void onError(int r3, Camera r4) {
            if (r3 != 100) goto L5;
            String r42 = "Camera server died!";
        L6:
            Logging.e(Camera1Session.TAG, r42);
            Camera1Session.m22279$$Nest$mstopInternal(this.this$0);
            Camera1Session r0 = this.this$0;
            if (r3 != 2) goto L10;
            Camera1Session.m22273$$Nest$fgetevents(r0).onCameraDisconnected(this.this$0);
            return;
        L10:
            Camera1Session.m22273$$Nest$fgetevents(r0).onCameraError(this.this$0, r42);
            return;
        L5:
            r42 = "Camera error: " + r3;
            goto L6
        }
    }

    /* renamed from: org.webrtc.Camera1Session$2, reason: invalid class name */
    public class AnonymousClass2 implements Camera.PreviewCallback {
        final /* synthetic */ Camera1Session this$0;

        public static /* synthetic */ void $r8$lambda$IaBojnBx0I01sdZaYVBja6mqHdg(AnonymousClass2 r0, byte[] r1) {
            r0.lambda$onPreviewFrame$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$WIxvmjSbIMTmT78fJu9aEGnuvsE(AnonymousClass2 r0, byte[] r1) {
            r0.lambda$onPreviewFrame$1(r1);
        }

        public AnonymousClass2(Camera1Session r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onPreviewFrame$0(byte[] r3) {
            if (Camera1Session.m22275$$Nest$fgetstate(this.this$0) != SessionState.RUNNING) goto L6;
            Camera1Session.m22269$$Nest$fgetcamera(this.this$0).addCallbackBuffer(r3);
            return;
        }

        private /* synthetic */ void lambda$onPreviewFrame$1(byte[] r3) {
            Camera1Session.m22270$$Nest$fgetcameraThreadHandler(this.this$0).post(new Camera1Session$2$$ExternalSyntheticLambda1(this, r3));
        }

        @Override // android.hardware.Camera.PreviewCallback
        public void onPreviewFrame(byte[] r7, Camera r8) {
            Camera1Session.m22277$$Nest$mcheckIsOnCameraThread(this.this$0);
            if (r8 == Camera1Session.m22269$$Nest$fgetcamera(this.this$0)) goto L7;
            Logging.e(Camera1Session.TAG, "Callback from a different camera. This should never happen.");
            return;
        L7:
            if (Camera1Session.m22275$$Nest$fgetstate(this.this$0) == SessionState.RUNNING) goto L10;
            Logging.d(Camera1Session.TAG, "Bytebuffer frame captured but camera is no longer running.");
            return;
        L10:
            long r0 = TimeUnit.MILLISECONDS.toNanos(SystemClock.elapsedRealtime());
            if (Camera1Session.m22274$$Nest$fgetfirstFrameReported(this.this$0) == true) goto L13;
            int r82 = (int) ((System.nanoTime() - Camera1Session.m22272$$Nest$fgetconstructionTimeNs(this.this$0)) / 1000000);
            Camera1Session.m22280$$Nest$sfgetcamera1StartTimeMsHistogram().addSample(r82);
            Camera1Session.m22276$$Nest$fputfirstFrameReported(this.this$0, true);
        L13:
            VideoFrame r72 = new VideoFrame(new NV21Buffer(r7, Camera1Session.m22271$$Nest$fgetcaptureFormat(this.this$0).width, Camera1Session.m22271$$Nest$fgetcaptureFormat(this.this$0).height, new Camera1Session$2$$ExternalSyntheticLambda0(this, r7)), Camera1Session.m22278$$Nest$mgetFrameOrientation(this.this$0), r0);
            Camera1Session.m22273$$Nest$fgetevents(this.this$0).onFrameCaptured(this.this$0, r72);
            r72.release();
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

    public static /* synthetic */ void $r8$lambda$e1uMaa9wRKdscVx9F1Eef9sw9Cc(Camera1Session r0, VideoFrame r1) {
        r0.lambda$listenForTextureFrames$0(r1);
    }

    /* renamed from: -$$Nest$fgetcamera, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Camera m22269$$Nest$fgetcamera(Camera1Session r0) {
        return r0.camera;
    }

    /* renamed from: -$$Nest$fgetcameraThreadHandler, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Handler m22270$$Nest$fgetcameraThreadHandler(Camera1Session r0) {
        return r0.cameraThreadHandler;
    }

    /* renamed from: -$$Nest$fgetcaptureFormat, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CameraEnumerationAndroid.CaptureFormat m22271$$Nest$fgetcaptureFormat(Camera1Session r0) {
        return r0.captureFormat;
    }

    /* renamed from: -$$Nest$fgetconstructionTimeNs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m22272$$Nest$fgetconstructionTimeNs(Camera1Session r2) {
        return r2.constructionTimeNs;
    }

    /* renamed from: -$$Nest$fgetevents, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CameraSession.Events m22273$$Nest$fgetevents(Camera1Session r0) {
        return r0.events;
    }

    /* renamed from: -$$Nest$fgetfirstFrameReported, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m22274$$Nest$fgetfirstFrameReported(Camera1Session r0) {
        return r0.firstFrameReported;
    }

    /* renamed from: -$$Nest$fgetstate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SessionState m22275$$Nest$fgetstate(Camera1Session r0) {
        return r0.state;
    }

    /* renamed from: -$$Nest$fputfirstFrameReported, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22276$$Nest$fputfirstFrameReported(Camera1Session r0, boolean r1) {
        r0.firstFrameReported = r1;
    }

    /* renamed from: -$$Nest$mcheckIsOnCameraThread, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22277$$Nest$mcheckIsOnCameraThread(Camera1Session r0) {
        r0.checkIsOnCameraThread();
    }

    /* renamed from: -$$Nest$mgetFrameOrientation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m22278$$Nest$mgetFrameOrientation(Camera1Session r0) {
        return r0.getFrameOrientation();
    }

    /* renamed from: -$$Nest$mstopInternal, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22279$$Nest$mstopInternal(Camera1Session r0) {
        r0.stopInternal();
    }

    /* renamed from: -$$Nest$sfgetcamera1StartTimeMsHistogram, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Histogram m22280$$Nest$sfgetcamera1StartTimeMsHistogram() {
        return camera1StartTimeMsHistogram;
    }

    static {
        camera1StartTimeMsHistogram = Histogram.createCounts("WebRTC.Android.Camera1.StartTimeMs", 1, 10000, 50);
        camera1StopTimeMsHistogram = Histogram.createCounts("WebRTC.Android.Camera1.StopTimeMs", 1, 10000, 50);
        camera1ResolutionHistogram = Histogram.createEnumeration("WebRTC.Android.Camera1.Resolution", CameraEnumerationAndroid.COMMON_RESOLUTIONS.size());
    }

    private Camera1Session(CameraSession.Events r3, boolean r4, Context r5, SurfaceTextureHelper r6, int r7, Camera r8, Camera.CameraInfo r9, CameraEnumerationAndroid.CaptureFormat r10, long r11) {
        Logging.d(TAG, "Create new camera1 session on camera " + r7);
        this.cameraThreadHandler = new Handler();
        this.events = r3;
        this.captureToTexture = r4;
        this.applicationContext = r5;
        this.surfaceTextureHelper = r6;
        this.cameraId = r7;
        this.camera = r8;
        this.info = r9;
        this.captureFormat = r10;
        this.constructionTimeNs = r11;
        this.orientationHelper = new OrientationHelper();
        r6.setTextureSize(r10.width, r10.height);
        startCapturing();
    }

    private void checkIsOnCameraThread() {
        if (Thread.currentThread() != this.cameraThreadHandler.getLooper().getThread()) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("Wrong thread");
    }

    public static void create(CameraSession.CreateSessionCallback r11, CameraSession.Events r12, boolean r13, Context r14, SurfaceTextureHelper r15, String r16, int r17, int r18, int r19) {
        long r9 = System.nanoTime();
        Logging.d(TAG, "Open camera " + r16);
        r12.onCameraOpening();
        int r5 = Camera1Enumerator.getCameraIndex(r16);     // Catch: IllegalArgumentException -> L35
        Camera r6 = Camera.open(r5);     // Catch: RuntimeException -> L32
        if (r6 != null) goto L46;
        r11.onFailure(CameraSession.FailureType.ERROR, "Camera.open returned null for camera id = " + r5);
        return;
    L46:
        r6.setPreviewTexture(r15.getSurfaceTexture());     // Catch: Throwable -> L26 IOException -> L28
        Camera.CameraInfo r7 = new Camera.CameraInfo();
        Camera.getCameraInfo(r5, r7);
        Camera.Parameters r2 = r6.getParameters();     // Catch: RuntimeException -> L23
        CameraEnumerationAndroid.CaptureFormat r8 = findClosestCaptureFormat(r2, r17, r18, r19);     // Catch: RuntimeException -> L23
        updateCameraParameters(r6, r2, r8, findClosestPictureSize(r2, r17, r18), r13);     // Catch: RuntimeException -> L23
        if (r13 == true) goto L42;
        int r1 = r8.frameSize();
        int r22 = 0;
    L15:
        if (r22 >= 3) goto L42;
        r6.addCallbackBuffer(ByteBuffer.allocateDirect(r1).array());
        r22 = r22 + 1;
    L42:
        r6.setDisplayOrientation(0);     // Catch: RuntimeException -> L20
        r11.onDone(new Camera1Session(r12, r13, r14, r15, r5, r6, r7, r8, r9));
        return;
    L20:
        e = move-exception;
        r6.release();
        r11.onFailure(CameraSession.FailureType.ERROR, e.getMessage());
        return;
    L23:
        e = move-exception;
        r6.release();
        r11.onFailure(CameraSession.FailureType.ERROR, e.getMessage());
        return;
    L26:
        e = move-exception;
        r6.release();
        r11.onFailure(CameraSession.FailureType.ERROR, e.getMessage());
        return;
    L32:
        e = move-exception;
        r11.onFailure(CameraSession.FailureType.ERROR, e.getMessage());
        return;
    L35:
        e = move-exception;
        r11.onFailure(CameraSession.FailureType.ERROR, e.getMessage());
    }

    private static CameraEnumerationAndroid.CaptureFormat findClosestCaptureFormat(Camera.Parameters r3, int r4, int r5, int r6) {
        List<CameraEnumerationAndroid.CaptureFormat.FramerateRange> r0 = Camera1Enumerator.convertFramerates(r3.getSupportedPreviewFpsRange());
        Logging.d(TAG, "Available fps ranges: " + r0);
        CameraEnumerationAndroid.CaptureFormat.FramerateRange r62 = CameraEnumerationAndroid.getClosestSupportedFramerateRange(r0, r6);
        Size r32 = CameraEnumerationAndroid.getClosestSupportedSize(Camera1Enumerator.convertSizes(r3.getSupportedPreviewSizes()), r4, r5);
        CameraEnumerationAndroid.reportCameraResolution(camera1ResolutionHistogram, r32);
        return new CameraEnumerationAndroid.CaptureFormat(r32.width, r32.height, r62);
    }

    private static Size findClosestPictureSize(Camera.Parameters r0, int r1, int r2) {
        return CameraEnumerationAndroid.getClosestSupportedSize(Camera1Enumerator.convertSizes(r0.getSupportedPictureSizes()), r1, r2);
    }

    private int getFrameOrientation() {
        if (LivePlayer.recording == null) goto L5;
        int r0 = 0;
    L6:
        OrientationHelper.cameraOrientation = r0;
        if (this.info.facing != 1) goto L9;
        r0 = 360 - r0;
    L9:
        OrientationHelper.cameraRotation = r0;
        return (this.info.orientation + r0) % 360;
    L5:
        r0 = this.orientationHelper.getOrientation();
        goto L6
    }

    private /* synthetic */ void lambda$listenForTextureFrames$0(VideoFrame r7) {
        checkIsOnCameraThread();
        if (this.state == SessionState.RUNNING) goto L6;
        Logging.d(TAG, "Texture frame captured but camera is no longer running.");
        return;
    L6:
        boolean r1 = true;
        if (this.firstFrameReported == true) goto L9;
        camera1StartTimeMsHistogram.addSample((int) ((System.nanoTime() - this.constructionTimeNs) / 1000000));
        this.firstFrameReported = true;
    L9:
        TextureBufferImpl r2 = (TextureBufferImpl) r7.getBuffer();
        if (this.info.facing == 1) goto L13;
        r1 = false;
    L13:
        VideoFrame r0 = new VideoFrame(CameraSession.createTextureBufferWithModifiedTransformMatrix(r2, r1, 0), getFrameOrientation(), r7.getTimestampNs());
        this.events.onFrameCaptured(this, r0);
        r0.release();
    }

    private void listenForBytebufferFrames() {
        this.camera.setPreviewCallbackWithBuffer(new AnonymousClass2(this));
    }

    private void listenForTextureFrames() {
        this.surfaceTextureHelper.startListening(new Camera1Session$$ExternalSyntheticLambda0(this));
    }

    private void startCapturing() {
        Logging.d(TAG, "Start capturing");
        checkIsOnCameraThread();
        this.state = SessionState.RUNNING;
        this.camera.setErrorCallback(new AnonymousClass1(this));
        if (this.captureToTexture == false) goto L5;
        listenForTextureFrames();
    L11:
        this.camera.startPreview();     // Catch: RuntimeException -> L8
        return;
    L8:
        e = move-exception;
        stopInternal();
        this.events.onCameraError(this, e.getMessage());
        return;
    L5:
        listenForBytebufferFrames();
        goto L11
    }

    private void stopInternal() {
        Logging.d(TAG, "Stop internal");
        checkIsOnCameraThread();
        SessionState r0 = this.state;
        SessionState r2 = SessionState.STOPPED;
        if (r0 != r2) goto L6;
        Logging.d(TAG, "Camera is already stopped");
        return;
    L6:
        this.state = r2;
        this.surfaceTextureHelper.stopListening();
        this.camera.stopPreview();
        this.camera.release();
        this.events.onCameraClosed(this);
        OrientationHelper r3 = this.orientationHelper;
        if (r3 == null) goto L9;
        r3.stop();
    L9:
        Logging.d(TAG, "Stop done");
    }

    private static void updateCameraParameters(Camera r3, Camera.Parameters r4, CameraEnumerationAndroid.CaptureFormat r5, Size r6, boolean r7) {
        List<String> r0 = r4.getSupportedFocusModes();
        CameraEnumerationAndroid.CaptureFormat.FramerateRange r1 = r5.framerate;
        r4.setPreviewFpsRange(r1.min, r1.max);
        r4.setPreviewSize(r5.width, r5.height);
        r4.setPictureSize(r6.width, r6.height);
        if (r7 == true) goto L6;
        r4.setPreviewFormat(17);
    L6:
        if (r4.isVideoStabilizationSupported() == false) goto L8;
        r4.setVideoStabilization(true);
    L8:
        if (r0 != null) goto L10;
    L12:
        r3.setParameters(r4);
        return;
    L10:
        if (r0.contains("continuous-video") == false) goto L12;
        r4.setFocusMode("continuous-video");
        goto L12
    }

    @Override // org.webrtc.CameraSession
    public void stop() {
        Logging.d(TAG, "Stop camera1 session on camera " + this.cameraId);
        checkIsOnCameraThread();
        if (this.state == SessionState.STOPPED) goto L6;
        long r0 = System.nanoTime();
        stopInternal();
        camera1StopTimeMsHistogram.addSample((int) ((System.nanoTime() - r0) / 1000000));
        return;
    }
}
