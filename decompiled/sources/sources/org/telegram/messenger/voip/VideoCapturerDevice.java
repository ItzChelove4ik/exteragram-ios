package org.telegram.messenger.voip;

import android.content.Intent;
import android.graphics.Point;
import android.media.projection.MediaProjection;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Display;
import android.view.WindowManager;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.webrtc.Camera1Enumerator;
import org.webrtc.Camera2Enumerator;
import org.webrtc.CameraEnumerator;
import org.webrtc.CameraVideoCapturer;
import org.webrtc.CapturerObserver;
import org.webrtc.EglBase;
import org.webrtc.Logging;
import org.webrtc.ScreenCapturerAndroid;
import org.webrtc.SurfaceTextureHelper;
import org.webrtc.VideoCapturer;
import org.webrtc.voiceengine.WebRtcAudioRecord;

/* loaded from: classes6.dex */
public class VideoCapturerDevice {
    private static final int CAPTURE_FPS = 30;
    private static final int CAPTURE_HEIGHT = 720;
    private static final int CAPTURE_WIDTH = 1280;
    public static EglBase eglBase;
    private static VideoCapturerDevice[] instance;
    public static Intent mediaProjectionPermissionResultData;
    private int currentHeight;
    private int currentWidth;
    private Handler handler;
    private CapturerObserver nativeCapturerObserver;
    private long nativePtr;
    private HandlerThread thread;
    private VideoCapturer videoCapturer;
    private SurfaceTextureHelper videoCapturerSurfaceTextureHelper;

    /* renamed from: org.telegram.messenger.voip.VideoCapturerDevice$1, reason: invalid class name */
    public class AnonymousClass1 extends MediaProjection.Callback {
        final /* synthetic */ VideoCapturerDevice this$0;

        /* renamed from: $r8$lambda$ZQSuWiFDNOs2qmwnLNxKb-4FvRU, reason: not valid java name */
        public static /* synthetic */ void m5283$r8$lambda$ZQSuWiFDNOs2qmwnLNxKb4FvRU() {
            if (VoIPService.getSharedInstance() == null) goto L6;
            VoIPService.getSharedInstance().stopScreenCapture();
            return;
        }

        public AnonymousClass1(VideoCapturerDevice r1) {
            this.this$0 = r1;
        }

        @Override // android.media.projection.MediaProjection.Callback
        public void onStop() {
            AndroidUtilities.runOnUIThread(new VideoCapturerDevice$1$$ExternalSyntheticLambda0());
        }
    }

    /* renamed from: org.telegram.messenger.voip.VideoCapturerDevice$2, reason: invalid class name */
    public class AnonymousClass2 implements CameraVideoCapturer.CameraEventsHandler {
        final /* synthetic */ VideoCapturerDevice this$0;

        public static /* synthetic */ void $r8$lambda$nw9ghc47P82EeNmCdLj2fBnzRy0() {
            if (VoIPService.getSharedInstance() == null) goto L6;
            VoIPService.getSharedInstance().onCameraFirstFrameAvailable();
            return;
        }

        public AnonymousClass2(VideoCapturerDevice r1) {
            this.this$0 = r1;
        }

        @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
        public void onCameraClosed() {
        }

        @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
        public void onCameraDisconnected() {
        }

        @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
        public void onCameraError(String r1) {
        }

        @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
        public void onCameraFreezed(String r1) {
        }

        @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
        public void onCameraOpening(String r1) {
        }

        @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
        public void onFirstFrameAvailable() {
            AndroidUtilities.runOnUIThread(new VideoCapturerDevice$2$$ExternalSyntheticLambda0());
        }
    }

    /* renamed from: org.telegram.messenger.voip.VideoCapturerDevice$3, reason: invalid class name */
    public class AnonymousClass3 implements CameraVideoCapturer.CameraSwitchHandler {
        final /* synthetic */ VideoCapturerDevice this$0;

        public static /* synthetic */ void $r8$lambda$67_itrkxqu4lzdGSiVNTf3YO7M8(boolean r2) {
            if (VoIPService.getSharedInstance() == null) goto L6;
            VoIPService.getSharedInstance().setSwitchingCamera(false, r2);
            return;
        }

        public AnonymousClass3(VideoCapturerDevice r1) {
            this.this$0 = r1;
        }

        @Override // org.webrtc.CameraVideoCapturer.CameraSwitchHandler
        public void onCameraSwitchDone(boolean r1) {
            AndroidUtilities.runOnUIThread(new VideoCapturerDevice$3$$ExternalSyntheticLambda0(r1));
        }

        @Override // org.webrtc.CameraVideoCapturer.CameraSwitchHandler
        public void onCameraSwitchError(String r1) {
        }
    }

    /* renamed from: $r8$lambda$0-nZ5DnAGgcg8xOXGf7d4Y4uvo0, reason: not valid java name */
    public static /* synthetic */ void m5280$r8$lambda$0nZ5DnAGgcg8xOXGf7d4Y4uvo0(VideoCapturerDevice r0) {
        r0.lambda$onDestroy$9();
    }

    public static /* synthetic */ void $r8$lambda$09gzeW8Z9cyIDKpFJlFAr89cgKs(VideoCapturerDevice r0, String r1) {
        r0.lambda$init$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$AbWAc2gyuD_R4EsJCEzP90lW2hU(VideoCapturerDevice r2, Point r3) {
        VideoCapturer r22 = r2.videoCapturer;
        if (r22 == null) goto L6;
        r22.changeCaptureFormat(r3.x, r3.y, 30);
        return;
    }

    /* renamed from: $r8$lambda$KvX0CzzPhrWs9moc8IvyL-72hZ0, reason: not valid java name */
    public static /* synthetic */ void m5281$r8$lambda$KvX0CzzPhrWs9moc8IvyL72hZ0(VideoCapturerDevice r0) {
        r0.lambda$onDestroy$8();
    }

    public static /* synthetic */ void $r8$lambda$LDj9i6m80B8XJKE0GRVAGysHTUI(VideoCapturerDevice r0, long r1, Point r3) {
        r0.lambda$init$2(r1, r3);
    }

    public static /* synthetic */ void $r8$lambda$P_vChgjiUGMwc_tPmrz2_2hsCco(VideoCapturerDevice r0, long r1, String r3) {
        r0.lambda$init$5(r1, r3);
    }

    /* renamed from: $r8$lambda$QSUl5blf7nFUsgsBO-Fly5CLeiM, reason: not valid java name */
    public static /* synthetic */ void m5282$r8$lambda$QSUl5blf7nFUsgsBOFly5CLeiM(VideoCapturerDevice r0, boolean r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$U5ltPbp3b9XKgV3WgCgtCtFL9TY(VideoCapturerDevice r0, long r1, int r3) {
        r0.lambda$onStateChanged$7(r1, r3);
    }

    public static /* synthetic */ void $r8$lambda$Z8F8pbcejHavpeVfqDHRJMjDUGw(VideoCapturerDevice r0, int r1, long r2) {
        r0.lambda$onStateChanged$6(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$_Pq54ZF8A3kTI4oAKi3w5cvcJUk(VideoCapturerDevice r0, long r1) {
        r0.lambda$init$3(r1);
    }

    static {
        instance = new VideoCapturerDevice[2];
    }

    public VideoCapturerDevice(boolean r3) {
        Logging.enableLogToDebugOutput(Logging.Severity.LS_VERBOSE);
        Logging.d("VideoCapturerDevice", "device model = " + Build.MANUFACTURER + Build.MODEL);
        AndroidUtilities.runOnUIThread(new VideoCapturerDevice$$ExternalSyntheticLambda9(this, r3));
    }

    public static void checkScreenCapturerSize() {
        if (instance[1] == null) goto L13;
        Point r0 = getScreenCaptureSize();
        VideoCapturerDevice r1 = instance[1];
        int r2 = r1.currentWidth;
        int r3 = r0.x;
        if (r2 == r3) goto L8;
    L11:
        r1.currentWidth = r3;
        r1.currentHeight = r0.y;
        r1.handler.post(new VideoCapturerDevice$$ExternalSyntheticLambda3(r1, r0));
        return;
    L8:
        if (r1.currentHeight != r0.y) goto L11;
        return;
    }

    public static EglBase getEglBase() {
        if (eglBase != null) goto L6;
        eglBase = EglBase.create(null, EglBase.CONFIG_PLAIN);
    L6:
        return eglBase;
    }

    public static MediaProjection getMediaProjection() {
        VideoCapturerDevice r0 = instance[1];
        if (r0 != null) goto L7;
        return null;
    L7:
        return ((ScreenCapturerAndroid) r0.videoCapturer).getMediaProjection();
    }

    private static Point getScreenCaptureSize() {
        return getScreenCaptureSize(16);
    }

    private static Point getScreenCaptureSize(int r10) {
        Display r0 = ((WindowManager) ApplicationLoader.applicationContext.getSystemService("window")).getDefaultDisplay();
        Point r1 = new Point();
        r0.getRealSize(r1);
        int r02 = r1.x;
        int r2 = r1.y;
        if (r02 <= r2) goto L5;
        float r22 = r2 / r02;
    L6:
        int r03 = 1;
    L8:
        if (r03 > 100) goto L16;
        float r3 = r03 * r22;
        int r5 = (int) r3;
        if (r3 == r5) goto L12;
        r03 = r03 + 1;
        goto L8
    L12:
        if (r1.x > r1.y) goto L18;
        r5 = r03;
        r03 = r5;
    L18:
        if (r03 != (-1)) goto L20;
    L33:
        if (r03 != (-1)) goto L35;
    L38:
        float r04 = Math.max(r1.x / 970.0f, r1.y / 970.0f);
        float r32 = r10;
        r1.x = ((int) Math.ceil((r1.x / r04) / r32)) * r10;
        r1.y = ((int) Math.ceil((r1.y / r04) / r32)) * r10;
        return r1;
    L35:
        if (r22 == 1.0f) goto L38;
        return r1;
    L20:
        if (r22 == 1.0f) goto L33;
    L21:
        int r6 = r1.x;
        if (r6 > 1000) goto L29;
        int r8 = r1.y;
        if (r8 > 1000) goto L29;
        if ((r6 % r10) != 0) goto L29;
        if ((r8 % r10) == 0) goto L33;
    L29:
        int r62 = r6 - r03;
        r1.x = r62;
        int r7 = r1.y - r5;
        r1.y = r7;
        if (r62 >= 800) goto L21;
        if (r7 >= 800) goto L21;
        r03 = -1;
        goto L33
    L16:
        r03 = -1;
        r5 = -1;
        goto L18
    L5:
        r22 = r02 / r2;
        goto L6
    }

    private static EglBase.Context getSharedEGLContext() {
        if (eglBase != null) goto L5;
        eglBase = EglBase.create(null, EglBase.CONFIG_PLAIN);
    L5:
        EglBase r0 = eglBase;
        if (r0 != null) goto L8;
        return null;
    L8:
        return r0.getEglBaseContext();
    }

    private void init(long r2, String r4) {
        AndroidUtilities.runOnUIThread(new VideoCapturerDevice$$ExternalSyntheticLambda0(this, r2, r4));
    }

    private /* synthetic */ void lambda$init$2(long r5, Point r7) {
        if (this.videoCapturerSurfaceTextureHelper == null) goto L11;
        long r0 = this.nativePtr;
        if (r0 == 0) goto L13;
        this.nativeCapturerObserver = nativeGetJavaVideoCapturerObserver(r0);
        this.videoCapturer.initialize(this.videoCapturerSurfaceTextureHelper, ApplicationLoader.applicationContext, this.nativeCapturerObserver);
        FileLog.d("VideoCapturerDevice init(" + r5 + "): videoCapturer.startCapture SCREEN");
        this.videoCapturer.startCapture(r7.x, r7.y, 30);
        WebRtcAudioRecord r52 = WebRtcAudioRecord.Instance;
        if (r52 == null) goto L12;
        r52.initDeviceAudioRecord(((ScreenCapturerAndroid) this.videoCapturer).getMediaProjection());
        return;
    L12:
        return;
    L13:
        return;
    }

    private /* synthetic */ void lambda$init$3(long r5) {
        if (this.videoCapturerSurfaceTextureHelper != null) goto L5;
        return;
    L5:
        this.nativeCapturerObserver = nativeGetJavaVideoCapturerObserver(this.nativePtr);
        this.videoCapturer.initialize(this.videoCapturerSurfaceTextureHelper, ApplicationLoader.applicationContext, this.nativeCapturerObserver);
        FileLog.d("VideoCapturerDevice init(" + r5 + "): videoCapturer.startCapture CAMERA");
        this.videoCapturer.startCapture(CAPTURE_WIDTH, CAPTURE_HEIGHT, 30);
    }

    private /* synthetic */ void lambda$init$4(String r3) {
        ((CameraVideoCapturer) this.videoCapturer).switchCamera(new AnonymousClass3(this), r3);
    }

    private /* synthetic */ void lambda$init$5(long r7, String r9) {
        if (eglBase == null) goto L34;
        this.nativePtr = r7;
        if ("screen".equals(r9) == false) goto L12;
        if (this.videoCapturer != null) goto L24;
        this.videoCapturer = new ScreenCapturerAndroid(mediaProjectionPermissionResultData, new AnonymousClass1(this));
        Point r92 = getScreenCaptureSize();
        this.currentWidth = r92.x;
        this.currentHeight = r92.y;
        this.videoCapturerSurfaceTextureHelper = SurfaceTextureHelper.create("ScreenCapturerThread", eglBase.getEglBaseContext());
        this.handler.post(new VideoCapturerDevice$$ExternalSyntheticLambda4(this, r7, r92));
        return;
    L24:
        return;
    L12:
        if (Camera2Enumerator.isSupported(ApplicationLoader.applicationContext) == false) goto L14;
        CameraEnumerator r0 = new Camera2Enumerator(ApplicationLoader.applicationContext);
    L15:
        String[] r1 = r0.getDeviceNames();
        int r2 = 0;
    L17:
        if (r2 >= r1.length) goto L22;
        if (r0.isFrontFacing(r1[r2]) == "front".equals(r9)) goto L23;
        r2 = r2 + 1;
    L23:
        if (r2 == (-1)) goto L33;
        String r93 = r1[r2];
        if (this.videoCapturer != null) goto L29;
        this.videoCapturer = r0.createCapturer(r93, new AnonymousClass2(this));
        this.videoCapturerSurfaceTextureHelper = SurfaceTextureHelper.create("VideoCapturerThread", eglBase.getEglBaseContext());
        this.handler.post(new VideoCapturerDevice$$ExternalSyntheticLambda5(this, r7));
        return;
    L29:
        FileLog.d("VideoCapturerDevice init(" + r7 + "): videoCapturer.switchCamera CAMERA");
        this.handler.post(new VideoCapturerDevice$$ExternalSyntheticLambda6(this, r93));
        return;
    L33:
        return;
    L22:
        r2 = -1;
        goto L23
    L14:
        r0 = new Camera1Enumerator();
        goto L15
    }

    private /* synthetic */ void lambda$new$0(boolean r3) {
        if (eglBase != null) goto L5;
        eglBase = EglBase.create(null, EglBase.CONFIG_PLAIN);
    L5:
        instance[r3 ? 1 : 0] = this;
        HandlerThread r32 = new HandlerThread("CallThread");
        this.thread = r32;
        r32.start();
        this.handler = new Handler(this.thread.getLooper());
    }

    private /* synthetic */ void lambda$onDestroy$8() {
        if ((this.videoCapturer instanceof ScreenCapturerAndroid) == false) goto L8;
        WebRtcAudioRecord r0 = WebRtcAudioRecord.Instance;
        if (r0 == null) goto L8;
        r0.stopDeviceAudioRecord();
    L8:
        if (this.videoCapturer == null) goto L15;
        FileLog.d("VideoCapturerDevice onDestroy: videoCapturer.stopCapture");
        this.videoCapturer.stopCapture();     // Catch: InterruptedException -> L12
        this.videoCapturer.dispose();
        this.videoCapturer = null;
    L12:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return;
    L15:
        SurfaceTextureHelper r02 = this.videoCapturerSurfaceTextureHelper;
        if (r02 == null) goto L21;
        r02.dispose();
        this.videoCapturerSurfaceTextureHelper = null;
        return;
    }

    private /* synthetic */ void lambda$onDestroy$9() {
        int r0 = 0;
    L3:
        VideoCapturerDevice[] r1 = instance;
        if (r0 >= r1.length) goto L9;
        if (r1[r0] == this) goto L7;
        r0 = r0 + 1;
        goto L3
    L7:
        r1[r0] = null;
    L9:
        this.handler.post(new VideoCapturerDevice$$ExternalSyntheticLambda7(this));
        this.thread.quitSafely();     // Catch: Exception -> L12
        return;
    L12:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$onStateChanged$6(int r4, long r5) {
        if (this.videoCapturer != null) goto L6;
        return;
    L6:
        if (r4 != 2) goto L14;
        FileLog.d("VideoCapturerDevice onStateChanged(" + r5 + ", " + r4 + "): videoCapturer.startCapture");
        this.videoCapturer.startCapture(CAPTURE_WIDTH, CAPTURE_HEIGHT, 30);
        return;
    L14:
        FileLog.d("VideoCapturerDevice onStateChanged(" + r5 + ", " + r4 + "): videoCapturer.stopCapture");     // Catch: InterruptedException -> L11
        this.videoCapturer.stopCapture();     // Catch: InterruptedException -> L11
        return;
    L11:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
    }

    private /* synthetic */ void lambda$onStateChanged$7(long r3, int r5) {
        if (this.nativePtr == r3) goto L5;
        return;
    L5:
        this.handler.post(new VideoCapturerDevice$$ExternalSyntheticLambda1(this, r5, r3));
    }

    private static native CapturerObserver nativeGetJavaVideoCapturerObserver(long r0);

    private void onAspectRatioRequested(float r1) {
    }

    private void onDestroy() {
        FileLog.d("VideoCapturerDevice onDestroy ptr=" + this.nativePtr);
        this.nativePtr = 0;
        AndroidUtilities.runOnUIThread(new VideoCapturerDevice$$ExternalSyntheticLambda8(this));
    }

    private void onStateChanged(long r3, int r5) {
        FileLog.d("VideoCapturerDevice onStateChanged(" + r3 + ", " + r5 + ")");
        AndroidUtilities.runOnUIThread(new VideoCapturerDevice$$ExternalSyntheticLambda2(this, r3, r5));
    }
}
