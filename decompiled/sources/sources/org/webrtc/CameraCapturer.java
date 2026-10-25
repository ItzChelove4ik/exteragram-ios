package org.webrtc;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.sun.jna.Native$$ExternalSyntheticBUOutline2;
import java.util.Arrays;
import java.util.List;
import org.webrtc.CameraSession;
import org.webrtc.CameraVideoCapturer;

/* loaded from: classes6.dex */
abstract class CameraCapturer implements CameraVideoCapturer {
    private static final int MAX_OPEN_CAMERA_ATTEMPTS = 3;
    private static final int OPEN_CAMERA_DELAY_MS = 500;
    private static final int OPEN_CAMERA_TIMEOUT = 10000;
    private static final String TAG = "CameraCapturer";
    private Context applicationContext;
    private final CameraEnumerator cameraEnumerator;
    private String cameraName;
    private final CameraSession.Events cameraSessionEventsHandler;
    private CameraVideoCapturer.CameraStatistics cameraStatistics;
    private Handler cameraThreadHandler;
    private CapturerObserver capturerObserver;
    private final CameraSession.CreateSessionCallback createSessionCallback;
    private CameraSession currentSession;
    private final CameraVideoCapturer.CameraEventsHandler eventsHandler;
    private boolean firstFrameObserved;
    private int framerate;
    private int height;
    private int openAttemptsRemaining;
    private final Runnable openCameraTimeoutRunnable;
    private String pendingCameraName;
    private boolean sessionOpening;
    private final Object stateLock;
    private SurfaceTextureHelper surfaceHelper;
    private CameraVideoCapturer.CameraSwitchHandler switchEventsHandler;
    private SwitchState switchState;
    private final Handler uiThreadHandler;
    private int width;

    /* renamed from: org.webrtc.CameraCapturer$1, reason: invalid class name */
    public class AnonymousClass1 implements CameraSession.CreateSessionCallback {
        final /* synthetic */ CameraCapturer this$0;

        public AnonymousClass1(CameraCapturer r1) {
            this.this$0 = r1;
        }

        @Override // org.webrtc.CameraSession.CreateSessionCallback
        public void onDone(CameraSession r6) {
            CameraCapturer.m22335$$Nest$mcheckIsOnCameraThread(this.this$0);
            Logging.d(CameraCapturer.TAG, "Create session done. Switch state: " + CameraCapturer.m22324$$Nest$fgetswitchState(this.this$0));
            CameraCapturer.m22325$$Nest$fgetuiThreadHandler(this.this$0).removeCallbacks(CameraCapturer.m22319$$Nest$fgetopenCameraTimeoutRunnable(this.this$0));
            Object r0 = CameraCapturer.m22321$$Nest$fgetstateLock(this.this$0);
            monitor-enter(r0);
            CameraCapturer.m22311$$Nest$fgetcapturerObserver(this.this$0).onCapturerStarted(true);     // Catch: Throwable -> L10
            CameraCapturer.m22332$$Nest$fputsessionOpening(this.this$0, false);     // Catch: Throwable -> L10
            CameraCapturer.m22328$$Nest$fputcurrentSession(this.this$0, r6);     // Catch: Throwable -> L10
            CameraCapturer r62 = this.this$0;     // Catch: Throwable -> L10
            CameraCapturer.m22327$$Nest$fputcameraStatistics(r62, new CameraVideoCapturer.CameraStatistics(CameraCapturer.m22322$$Nest$fgetsurfaceHelper(r62), CameraCapturer.m22314$$Nest$fgeteventsHandler(this.this$0)));     // Catch: Throwable -> L10
            CameraCapturer.m22329$$Nest$fputfirstFrameObserved(this.this$0, false);     // Catch: Throwable -> L10
            CameraCapturer.m22321$$Nest$fgetstateLock(this.this$0).notifyAll();     // Catch: Throwable -> L10
            SwitchState r63 = CameraCapturer.m22324$$Nest$fgetswitchState(this.this$0);     // Catch: Throwable -> L10
            SwitchState r1 = SwitchState.IN_PROGRESS;     // Catch: Throwable -> L10
            CameraCapturer r2 = this.this$0;
            if (r63 != r1) goto L13;
            CameraCapturer.m22334$$Nest$fputswitchState(r2, SwitchState.IDLE);     // Catch: Throwable -> L10
            if (CameraCapturer.m22323$$Nest$fgetswitchEventsHandler(this.this$0) == null) goto L15;
            CameraCapturer.m22323$$Nest$fgetswitchEventsHandler(this.this$0).onCameraSwitchDone(CameraCapturer.m22307$$Nest$fgetcameraEnumerator(this.this$0).isFrontFacing(CameraCapturer.m22308$$Nest$fgetcameraName(this.this$0)));     // Catch: Throwable -> L10
            CameraCapturer.m22333$$Nest$fputswitchEventsHandler(this.this$0, null);     // Catch: Throwable -> L10
        L15:
            monitor-exit(r0);     // Catch: Throwable -> L10
            return;
        L13:
            if (CameraCapturer.m22324$$Nest$fgetswitchState(r2) != SwitchState.PENDING) goto L15;
            String r64 = CameraCapturer.m22320$$Nest$fgetpendingCameraName(this.this$0);     // Catch: Throwable -> L10
            CameraCapturer.m22331$$Nest$fputpendingCameraName(this.this$0, null);     // Catch: Throwable -> L10
            CameraCapturer.m22334$$Nest$fputswitchState(this.this$0, SwitchState.IDLE);     // Catch: Throwable -> L10
            CameraCapturer r5 = this.this$0;     // Catch: Throwable -> L10
            CameraCapturer.m22338$$Nest$mswitchCameraInternal(r5, CameraCapturer.m22323$$Nest$fgetswitchEventsHandler(r5), r64);     // Catch: Throwable -> L10
        L10:
            th = move-exception;
            throw th;
        }

        @Override // org.webrtc.CameraSession.CreateSessionCallback
        public void onFailure(CameraSession.FailureType r7, String r8) {
            CameraCapturer.m22335$$Nest$mcheckIsOnCameraThread(this.this$0);
            CameraCapturer.m22325$$Nest$fgetuiThreadHandler(this.this$0).removeCallbacks(CameraCapturer.m22319$$Nest$fgetopenCameraTimeoutRunnable(this.this$0));
            Object r2 = CameraCapturer.m22321$$Nest$fgetstateLock(this.this$0);
            monitor-enter(r2);
            CameraCapturer.m22311$$Nest$fgetcapturerObserver(this.this$0).onCapturerStarted(false);     // Catch: Throwable -> L11
            CameraCapturer.m22330$$Nest$fputopenAttemptsRemaining(this.this$0, CameraCapturer.m22318$$Nest$fgetopenAttemptsRemaining(r3) - 1);     // Catch: Throwable -> L11
            if (CameraCapturer.m22318$$Nest$fgetopenAttemptsRemaining(this.this$0) > 0) goto L19;
            Logging.w(CameraCapturer.TAG, "Opening camera failed, passing: " + r8);     // Catch: Throwable -> L11
            CameraCapturer.m22332$$Nest$fputsessionOpening(this.this$0, false);     // Catch: Throwable -> L11
            CameraCapturer.m22321$$Nest$fgetstateLock(this.this$0).notifyAll();     // Catch: Throwable -> L11
            SwitchState r0 = CameraCapturer.m22324$$Nest$fgetswitchState(this.this$0);     // Catch: Throwable -> L11
            SwitchState r1 = SwitchState.IDLE;     // Catch: Throwable -> L11
            if (r0 != r1) goto L9;
        L14:
            CameraSession.FailureType r02 = CameraSession.FailureType.DISCONNECTED;     // Catch: Throwable -> L11
            CameraCapturer r6 = this.this$0;
            if (r7 != r02) goto L18;
            CameraCapturer.m22314$$Nest$fgeteventsHandler(r6).onCameraDisconnected();     // Catch: Throwable -> L11
        L20:
            monitor-exit(r2);     // Catch: Throwable -> L11
            return;
        L18:
            CameraCapturer.m22314$$Nest$fgeteventsHandler(r6).onCameraError(r8);     // Catch: Throwable -> L11
            goto L20
        L9:
            if (CameraCapturer.m22323$$Nest$fgetswitchEventsHandler(this.this$0) == null) goto L13;
            CameraCapturer.m22323$$Nest$fgetswitchEventsHandler(this.this$0).onCameraSwitchError(r8);     // Catch: Throwable -> L11
            CameraCapturer.m22333$$Nest$fputswitchEventsHandler(this.this$0, null);     // Catch: Throwable -> L11
        L13:
            CameraCapturer.m22334$$Nest$fputswitchState(this.this$0, r1);     // Catch: Throwable -> L11
            goto L14
        L19:
            Logging.w(CameraCapturer.TAG, "Opening camera failed, retry: " + r8);     // Catch: Throwable -> L11
            CameraCapturer.m22336$$Nest$mcreateSessionInternal(this.this$0, 500);     // Catch: Throwable -> L11
        L11:
            th = move-exception;
            throw th;
        }
    }

    /* renamed from: org.webrtc.CameraCapturer$2, reason: invalid class name */
    public class AnonymousClass2 implements CameraSession.Events {
        final /* synthetic */ CameraCapturer this$0;

        public AnonymousClass2(CameraCapturer r1) {
            this.this$0 = r1;
        }

        @Override // org.webrtc.CameraSession.Events
        public void onCameraClosed(CameraSession r3) {
            CameraCapturer.m22335$$Nest$mcheckIsOnCameraThread(this.this$0);
            Object r0 = CameraCapturer.m22321$$Nest$fgetstateLock(this.this$0);
            monitor-enter(r0);
        L11:
            th = move-exception;
            throw th;
        L5:
            if (r3 != CameraCapturer.m22313$$Nest$fgetcurrentSession(this.this$0)) goto L7;
        L13:
            CameraCapturer.m22314$$Nest$fgeteventsHandler(this.this$0).onCameraClosed();     // Catch: Throwable -> L11
            monitor-exit(r0);     // Catch: Throwable -> L11
            return;
        L7:
            if (CameraCapturer.m22313$$Nest$fgetcurrentSession(this.this$0) == null) goto L13;
            Logging.d(CameraCapturer.TAG, "onCameraClosed from another session.");     // Catch: Throwable -> L11
            monitor-exit(r0);     // Catch: Throwable -> L11
        }

        @Override // org.webrtc.CameraSession.Events
        public void onCameraDisconnected(CameraSession r3) {
            CameraCapturer.m22335$$Nest$mcheckIsOnCameraThread(this.this$0);
            Object r0 = CameraCapturer.m22321$$Nest$fgetstateLock(this.this$0);
            monitor-enter(r0);
        L9:
            th = move-exception;
            throw th;
        L5:
            if (r3 == CameraCapturer.m22313$$Nest$fgetcurrentSession(this.this$0)) goto L11;
            Logging.w(CameraCapturer.TAG, "onCameraDisconnected from another session.");     // Catch: Throwable -> L9
            monitor-exit(r0);     // Catch: Throwable -> L9
            return;
        L11:
            CameraCapturer.m22314$$Nest$fgeteventsHandler(this.this$0).onCameraDisconnected();     // Catch: Throwable -> L9
            this.this$0.stopCapture();     // Catch: Throwable -> L9
            monitor-exit(r0);     // Catch: Throwable -> L9
        }

        @Override // org.webrtc.CameraSession.Events
        public void onCameraError(CameraSession r4, String r5) {
            CameraCapturer.m22335$$Nest$mcheckIsOnCameraThread(this.this$0);
            Object r1 = CameraCapturer.m22321$$Nest$fgetstateLock(this.this$0);
            monitor-enter(r1);
        L9:
            th = move-exception;
            throw th;
        L5:
            if (r4 == CameraCapturer.m22313$$Nest$fgetcurrentSession(this.this$0)) goto L11;
            Logging.w(CameraCapturer.TAG, "onCameraError from another session: " + r5);     // Catch: Throwable -> L9
            monitor-exit(r1);     // Catch: Throwable -> L9
            return;
        L11:
            CameraCapturer.m22314$$Nest$fgeteventsHandler(this.this$0).onCameraError(r5);     // Catch: Throwable -> L9
            this.this$0.stopCapture();     // Catch: Throwable -> L9
            monitor-exit(r1);     // Catch: Throwable -> L9
        }

        @Override // org.webrtc.CameraSession.Events
        public void onCameraOpening() {
            CameraCapturer.m22335$$Nest$mcheckIsOnCameraThread(this.this$0);
            Object r0 = CameraCapturer.m22321$$Nest$fgetstateLock(this.this$0);
            monitor-enter(r0);
        L9:
            th = move-exception;
            throw th;
        L5:
            if (CameraCapturer.m22313$$Nest$fgetcurrentSession(this.this$0) == null) goto L11;
            Logging.w(CameraCapturer.TAG, "onCameraOpening while session was open.");     // Catch: Throwable -> L9
            monitor-exit(r0);     // Catch: Throwable -> L9
            return;
        L11:
            CameraCapturer.m22314$$Nest$fgeteventsHandler(this.this$0).onCameraOpening(CameraCapturer.m22308$$Nest$fgetcameraName(this.this$0));     // Catch: Throwable -> L9
            monitor-exit(r0);     // Catch: Throwable -> L9
        }

        @Override // org.webrtc.CameraSession.Events
        public void onFrameCaptured(CameraSession r3, VideoFrame r4) {
            CameraCapturer.m22335$$Nest$mcheckIsOnCameraThread(this.this$0);
            Object r0 = CameraCapturer.m22321$$Nest$fgetstateLock(this.this$0);
            monitor-enter(r0);
        L9:
            th = move-exception;
            throw th;
        L5:
            if (r3 == CameraCapturer.m22313$$Nest$fgetcurrentSession(this.this$0)) goto L12;
            Logging.w(CameraCapturer.TAG, "onFrameCaptured from another session.");     // Catch: Throwable -> L9
            monitor-exit(r0);     // Catch: Throwable -> L9
            return;
        L12:
            if (CameraCapturer.m22315$$Nest$fgetfirstFrameObserved(this.this$0) == true) goto L14;
            CameraCapturer.m22314$$Nest$fgeteventsHandler(this.this$0).onFirstFrameAvailable();     // Catch: Throwable -> L9
            CameraCapturer.m22329$$Nest$fputfirstFrameObserved(this.this$0, true);     // Catch: Throwable -> L9
        L14:
            CameraCapturer.m22310$$Nest$fgetcameraStatistics(this.this$0).addFrame();     // Catch: Throwable -> L9
            CameraCapturer.m22311$$Nest$fgetcapturerObserver(this.this$0).onFrameCaptured(r4);     // Catch: Throwable -> L9
            monitor-exit(r0);     // Catch: Throwable -> L9
        }
    }

    /* renamed from: org.webrtc.CameraCapturer$3, reason: invalid class name */
    public class AnonymousClass3 implements Runnable {
        final /* synthetic */ CameraCapturer this$0;

        public AnonymousClass3(CameraCapturer r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            CameraCapturer.m22314$$Nest$fgeteventsHandler(this.this$0).onCameraError("Camera failed to start within timeout.");
        }
    }

    /* renamed from: org.webrtc.CameraCapturer$4, reason: invalid class name */
    public class AnonymousClass4 implements CameraVideoCapturer.CameraEventsHandler {
        final /* synthetic */ CameraCapturer this$0;

        public AnonymousClass4(CameraCapturer r1) {
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
        }
    }

    /* renamed from: org.webrtc.CameraCapturer$5, reason: invalid class name */
    public class AnonymousClass5 implements Runnable {
        final /* synthetic */ CameraCapturer this$0;

        public AnonymousClass5(CameraCapturer r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            CameraCapturer r0 = this.this$0;
            r0.createCameraSession(CameraCapturer.m22312$$Nest$fgetcreateSessionCallback(r0), CameraCapturer.m22309$$Nest$fgetcameraSessionEventsHandler(this.this$0), CameraCapturer.m22306$$Nest$fgetapplicationContext(this.this$0), CameraCapturer.m22322$$Nest$fgetsurfaceHelper(this.this$0), CameraCapturer.m22308$$Nest$fgetcameraName(this.this$0), CameraCapturer.m22326$$Nest$fgetwidth(this.this$0), CameraCapturer.m22317$$Nest$fgetheight(this.this$0), CameraCapturer.m22316$$Nest$fgetframerate(this.this$0));
        }
    }

    /* renamed from: org.webrtc.CameraCapturer$6, reason: invalid class name */
    public class AnonymousClass6 implements Runnable {
        final /* synthetic */ CameraCapturer this$0;
        final /* synthetic */ CameraSession val$oldSession;

        public AnonymousClass6(CameraCapturer r1, CameraSession r2) {
            this.this$0 = r1;
            this.val$oldSession = r2;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.val$oldSession.stop();
        }
    }

    /* renamed from: org.webrtc.CameraCapturer$7, reason: invalid class name */
    public class AnonymousClass7 implements Runnable {
        final /* synthetic */ CameraCapturer this$0;
        final /* synthetic */ CameraVideoCapturer.CameraSwitchHandler val$switchEventsHandler;

        public AnonymousClass7(CameraCapturer r1, CameraVideoCapturer.CameraSwitchHandler r2) {
            this.this$0 = r1;
            this.val$switchEventsHandler = r2;
        }

        @Override // java.lang.Runnable
        public void run() {
            List r0 = Arrays.asList(CameraCapturer.m22307$$Nest$fgetcameraEnumerator(this.this$0).getDeviceNames());
            int r1 = r0.size();
            CameraCapturer r2 = this.this$0;
            if (r1 >= 2) goto L6;
            CameraCapturer.m22337$$Nest$mreportCameraSwitchError(r2, "No camera to switch to.", this.val$switchEventsHandler);
            return;
        L6:
            String r02 = (String) r0.get((r0.indexOf(CameraCapturer.m22308$$Nest$fgetcameraName(r2)) + 1) % r0.size());
            CameraCapturer.m22338$$Nest$mswitchCameraInternal(this.this$0, this.val$switchEventsHandler, r02);
        }
    }

    /* renamed from: org.webrtc.CameraCapturer$8, reason: invalid class name */
    public class AnonymousClass8 implements Runnable {
        final /* synthetic */ CameraCapturer this$0;
        final /* synthetic */ String val$cameraName;
        final /* synthetic */ CameraVideoCapturer.CameraSwitchHandler val$switchEventsHandler;

        public AnonymousClass8(CameraCapturer r1, CameraVideoCapturer.CameraSwitchHandler r2, String r3) {
            this.this$0 = r1;
            this.val$switchEventsHandler = r2;
            this.val$cameraName = r3;
        }

        @Override // java.lang.Runnable
        public void run() {
            CameraCapturer.m22338$$Nest$mswitchCameraInternal(this.this$0, this.val$switchEventsHandler, this.val$cameraName);
        }
    }

    /* renamed from: org.webrtc.CameraCapturer$9, reason: invalid class name */
    public class AnonymousClass9 implements Runnable {
        final /* synthetic */ CameraCapturer this$0;
        final /* synthetic */ CameraSession val$oldSession;

        public AnonymousClass9(CameraCapturer r1, CameraSession r2) {
            this.this$0 = r1;
            this.val$oldSession = r2;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.val$oldSession.stop();
        }
    }

    public enum SwitchState extends Enum<SwitchState> {
        private static final /* synthetic */ SwitchState[] $VALUES = null;
        public static final SwitchState IDLE = null;
        public static final SwitchState IN_PROGRESS = null;
        public static final SwitchState PENDING = null;

        private static /* synthetic */ SwitchState[] $values() {
            return new SwitchState[]{IDLE, PENDING, IN_PROGRESS};
        }

        static {
            IDLE = new SwitchState("IDLE", 0);
            PENDING = new SwitchState("PENDING", 1);
            IN_PROGRESS = new SwitchState("IN_PROGRESS", 2);
            $VALUES = $values();
        }

        SwitchState(String r1, int r2) {
        }

        public static SwitchState valueOf(String r1) {
            return (SwitchState) Enum.valueOf(SwitchState.class, r1);
        }

        public static SwitchState[] values() {
            return (SwitchState[]) $VALUES.clone();
        }
    }

    /* renamed from: -$$Nest$fgetapplicationContext, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Context m22306$$Nest$fgetapplicationContext(CameraCapturer r0) {
        return r0.applicationContext;
    }

    /* renamed from: -$$Nest$fgetcameraEnumerator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CameraEnumerator m22307$$Nest$fgetcameraEnumerator(CameraCapturer r0) {
        return r0.cameraEnumerator;
    }

    /* renamed from: -$$Nest$fgetcameraName, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m22308$$Nest$fgetcameraName(CameraCapturer r0) {
        return r0.cameraName;
    }

    /* renamed from: -$$Nest$fgetcameraSessionEventsHandler, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CameraSession.Events m22309$$Nest$fgetcameraSessionEventsHandler(CameraCapturer r0) {
        return r0.cameraSessionEventsHandler;
    }

    /* renamed from: -$$Nest$fgetcameraStatistics, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CameraVideoCapturer.CameraStatistics m22310$$Nest$fgetcameraStatistics(CameraCapturer r0) {
        return r0.cameraStatistics;
    }

    /* renamed from: -$$Nest$fgetcapturerObserver, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CapturerObserver m22311$$Nest$fgetcapturerObserver(CameraCapturer r0) {
        return r0.capturerObserver;
    }

    /* renamed from: -$$Nest$fgetcreateSessionCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CameraSession.CreateSessionCallback m22312$$Nest$fgetcreateSessionCallback(CameraCapturer r0) {
        return r0.createSessionCallback;
    }

    /* renamed from: -$$Nest$fgetcurrentSession, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CameraSession m22313$$Nest$fgetcurrentSession(CameraCapturer r0) {
        return r0.currentSession;
    }

    /* renamed from: -$$Nest$fgeteventsHandler, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CameraVideoCapturer.CameraEventsHandler m22314$$Nest$fgeteventsHandler(CameraCapturer r0) {
        return r0.eventsHandler;
    }

    /* renamed from: -$$Nest$fgetfirstFrameObserved, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m22315$$Nest$fgetfirstFrameObserved(CameraCapturer r0) {
        return r0.firstFrameObserved;
    }

    /* renamed from: -$$Nest$fgetframerate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m22316$$Nest$fgetframerate(CameraCapturer r0) {
        return r0.framerate;
    }

    /* renamed from: -$$Nest$fgetheight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m22317$$Nest$fgetheight(CameraCapturer r0) {
        return r0.height;
    }

    /* renamed from: -$$Nest$fgetopenAttemptsRemaining, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m22318$$Nest$fgetopenAttemptsRemaining(CameraCapturer r0) {
        return r0.openAttemptsRemaining;
    }

    /* renamed from: -$$Nest$fgetopenCameraTimeoutRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m22319$$Nest$fgetopenCameraTimeoutRunnable(CameraCapturer r0) {
        return r0.openCameraTimeoutRunnable;
    }

    /* renamed from: -$$Nest$fgetpendingCameraName, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m22320$$Nest$fgetpendingCameraName(CameraCapturer r0) {
        return r0.pendingCameraName;
    }

    /* renamed from: -$$Nest$fgetstateLock, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Object m22321$$Nest$fgetstateLock(CameraCapturer r0) {
        return r0.stateLock;
    }

    /* renamed from: -$$Nest$fgetsurfaceHelper, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SurfaceTextureHelper m22322$$Nest$fgetsurfaceHelper(CameraCapturer r0) {
        return r0.surfaceHelper;
    }

    /* renamed from: -$$Nest$fgetswitchEventsHandler, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CameraVideoCapturer.CameraSwitchHandler m22323$$Nest$fgetswitchEventsHandler(CameraCapturer r0) {
        return r0.switchEventsHandler;
    }

    /* renamed from: -$$Nest$fgetswitchState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SwitchState m22324$$Nest$fgetswitchState(CameraCapturer r0) {
        return r0.switchState;
    }

    /* renamed from: -$$Nest$fgetuiThreadHandler, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Handler m22325$$Nest$fgetuiThreadHandler(CameraCapturer r0) {
        return r0.uiThreadHandler;
    }

    /* renamed from: -$$Nest$fgetwidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m22326$$Nest$fgetwidth(CameraCapturer r0) {
        return r0.width;
    }

    /* renamed from: -$$Nest$fputcameraStatistics, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22327$$Nest$fputcameraStatistics(CameraCapturer r0, CameraVideoCapturer.CameraStatistics r1) {
        r0.cameraStatistics = r1;
    }

    /* renamed from: -$$Nest$fputcurrentSession, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22328$$Nest$fputcurrentSession(CameraCapturer r0, CameraSession r1) {
        r0.currentSession = r1;
    }

    /* renamed from: -$$Nest$fputfirstFrameObserved, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22329$$Nest$fputfirstFrameObserved(CameraCapturer r0, boolean r1) {
        r0.firstFrameObserved = r1;
    }

    /* renamed from: -$$Nest$fputopenAttemptsRemaining, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22330$$Nest$fputopenAttemptsRemaining(CameraCapturer r0, int r1) {
        r0.openAttemptsRemaining = r1;
    }

    /* renamed from: -$$Nest$fputpendingCameraName, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22331$$Nest$fputpendingCameraName(CameraCapturer r0, String r1) {
        r0.pendingCameraName = r1;
    }

    /* renamed from: -$$Nest$fputsessionOpening, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22332$$Nest$fputsessionOpening(CameraCapturer r0, boolean r1) {
        r0.sessionOpening = r1;
    }

    /* renamed from: -$$Nest$fputswitchEventsHandler, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22333$$Nest$fputswitchEventsHandler(CameraCapturer r0, CameraVideoCapturer.CameraSwitchHandler r1) {
        r0.switchEventsHandler = r1;
    }

    /* renamed from: -$$Nest$fputswitchState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22334$$Nest$fputswitchState(CameraCapturer r0, SwitchState r1) {
        r0.switchState = r1;
    }

    /* renamed from: -$$Nest$mcheckIsOnCameraThread, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22335$$Nest$mcheckIsOnCameraThread(CameraCapturer r0) {
        r0.checkIsOnCameraThread();
    }

    /* renamed from: -$$Nest$mcreateSessionInternal, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22336$$Nest$mcreateSessionInternal(CameraCapturer r0, int r1) {
        r0.createSessionInternal(r1);
    }

    /* renamed from: -$$Nest$mreportCameraSwitchError, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22337$$Nest$mreportCameraSwitchError(CameraCapturer r0, String r1, CameraVideoCapturer.CameraSwitchHandler r2) {
        r0.reportCameraSwitchError(r1, r2);
    }

    /* renamed from: -$$Nest$mswitchCameraInternal, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22338$$Nest$mswitchCameraInternal(CameraCapturer r0, CameraVideoCapturer.CameraSwitchHandler r1, String r2) {
        r0.switchCameraInternal(r1, r2);
    }

    public CameraCapturer(String r2, CameraVideoCapturer.CameraEventsHandler r3, CameraEnumerator r4) {
        this.createSessionCallback = new AnonymousClass1(this);
        this.cameraSessionEventsHandler = new AnonymousClass2(this);
        this.openCameraTimeoutRunnable = new AnonymousClass3(this);
        this.stateLock = new Object();
        this.switchState = SwitchState.IDLE;
        if (r3 != null) goto L5;
        r3 = new AnonymousClass4(this);
    L5:
        this.eventsHandler = r3;
        this.cameraEnumerator = r4;
        this.cameraName = r2;
        List r22 = Arrays.asList(r4.getDeviceNames());
        this.uiThreadHandler = new Handler(Looper.getMainLooper());
        if (r22.isEmpty() == false) goto L8;
        GlShader$$ExternalSyntheticBUOutline1.m("No cameras attached.");
        throw null;
    L8:
        if (r22.contains(this.cameraName) == false) goto L10;
        return;
    L10:
        Native$$ExternalSyntheticBUOutline2.m("Camera name ", this.cameraName, " does not match any known camera device.");
        throw null;
    }

    private void checkIsOnCameraThread() {
        if (Thread.currentThread() != this.cameraThreadHandler.getLooper().getThread()) goto L5;
        return;
    L5:
        Logging.e(TAG, "Check is on camera thread failed.");
        GlShader$$ExternalSyntheticBUOutline1.m("Not on camera thread.");
    }

    private void createSessionInternal(int r5) {
        this.uiThreadHandler.postDelayed(this.openCameraTimeoutRunnable, r5 + 10000);
        this.cameraThreadHandler.postDelayed(new AnonymousClass5(this), r5);
    }

    private void reportCameraSwitchError(String r1, CameraVideoCapturer.CameraSwitchHandler r2) {
        Logging.e(TAG, r1);
        if (r2 == null) goto L6;
        r2.onCameraSwitchError(r1);
        return;
    }

    private void switchCameraInternal(CameraVideoCapturer.CameraSwitchHandler r5, String r6) {
        Logging.d(TAG, "switchCamera internal");
        if (Arrays.asList(this.cameraEnumerator.getDeviceNames()).contains(r6) == true) goto L6;
        reportCameraSwitchError("Attempted to switch to unknown camera device " + r6, r5);
        return;
    L6:
        Object r0 = this.stateLock;
        monitor-enter(r0);
    L13:
        th = move-exception;
        throw th;
    L9:
        if (this.switchState == SwitchState.IDLE) goto L15;
        reportCameraSwitchError("Camera switch already in progress.", r5);     // Catch: Throwable -> L13
        monitor-exit(r0);     // Catch: Throwable -> L13
        return;
    L15:
        boolean r1 = this.sessionOpening;     // Catch: Throwable -> L13
        if (r1 == false) goto L18;
    L22:
        this.switchEventsHandler = r5;     // Catch: Throwable -> L13
        if (r1 == false) goto L27;
        this.switchState = SwitchState.PENDING;     // Catch: Throwable -> L13
        this.pendingCameraName = r6;     // Catch: Throwable -> L13
        monitor-exit(r0);     // Catch: Throwable -> L13
        return;
    L27:
        this.switchState = SwitchState.IN_PROGRESS;     // Catch: Throwable -> L13
        Logging.d(TAG, "switchCamera: Stopping session");     // Catch: Throwable -> L13
        this.cameraStatistics.release();     // Catch: Throwable -> L13
        this.cameraStatistics = null;     // Catch: Throwable -> L13
        this.cameraThreadHandler.post(new AnonymousClass9(this, this.currentSession));     // Catch: Throwable -> L13
        this.currentSession = null;     // Catch: Throwable -> L13
        this.cameraName = r6;     // Catch: Throwable -> L13
        this.sessionOpening = true;     // Catch: Throwable -> L13
        this.openAttemptsRemaining = 1;     // Catch: Throwable -> L13
        createSessionInternal(0);     // Catch: Throwable -> L13
        monitor-exit(r0);     // Catch: Throwable -> L13
        Logging.d(TAG, "switchCamera done");
        return;
    L18:
        if (this.currentSession != null) goto L22;
        reportCameraSwitchError("switchCamera: camera is not running.", r5);     // Catch: Throwable -> L13
        monitor-exit(r0);     // Catch: Throwable -> L13
    }

    @Override // org.webrtc.VideoCapturer
    public void changeCaptureFormat(int r4, int r5, int r6) {
        Logging.d(TAG, "changeCaptureFormat: " + r4 + "x" + r5 + "@" + r6);
        Object r0 = this.stateLock;
        monitor-enter(r0);
        stopCapture();     // Catch: Throwable -> L7
        startCapture(r4, r5, r6);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public abstract void createCameraSession(CameraSession.CreateSessionCallback r1, CameraSession.Events r2, Context r3, SurfaceTextureHelper r4, String r5, int r6, int r7, int r8);

    @Override // org.webrtc.VideoCapturer
    public void dispose() {
        Logging.d(TAG, "dispose");
        stopCapture();
    }

    public String getCameraName() {
        Object r0 = this.stateLock;
        monitor-enter(r0);
        String r1 = this.cameraName;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // org.webrtc.VideoCapturer
    public void initialize(SurfaceTextureHelper r1, Context r2, CapturerObserver r3) {
        this.applicationContext = r2;
        this.capturerObserver = r3;
        this.surfaceHelper = r1;
        this.cameraThreadHandler = r1.getHandler();
    }

    @Override // org.webrtc.VideoCapturer
    public boolean isScreencast() {
        return false;
    }

    public void printStackTrace() {
        Handler r4 = this.cameraThreadHandler;
        if (r4 == null) goto L5;
        Thread r42 = r4.getLooper().getThread();
    L6:
        if (r42 == null) goto L12;
        StackTraceElement[] r43 = r42.getStackTrace();
        if (r43.length <= 0) goto L14;
        Logging.d(TAG, "CameraCapturer stack trace:");
        int r0 = r43.length;
        int r2 = 0;
    L10:
        if (r2 >= r0) goto L15;
        Logging.d(TAG, r43[r2].toString());
        r2 = r2 + 1;
        goto L10
    L15:
        return;
    L14:
        return;
    L12:
        return;
    L5:
        r42 = null;
        goto L6
    }

    @Override // org.webrtc.VideoCapturer
    public void startCapture(int r4, int r5, int r6) {
        Logging.d(TAG, "startCapture: " + r4 + "x" + r5 + "@" + r6);
        if (this.applicationContext == null) goto L21;
        Object r0 = this.stateLock;
        monitor-enter(r0);
    L14:
        th = move-exception;
        throw th;
    L7:
        if (this.sessionOpening == false) goto L9;
    L16:
        Logging.w(TAG, "Session already open");     // Catch: Throwable -> L14
        monitor-exit(r0);     // Catch: Throwable -> L14
        return;
    L9:
        if (this.currentSession != null) goto L16;
        this.width = r4;     // Catch: Throwable -> L14
        this.height = r5;     // Catch: Throwable -> L14
        this.framerate = r6;     // Catch: Throwable -> L14
        this.sessionOpening = true;     // Catch: Throwable -> L14
        this.openAttemptsRemaining = 3;     // Catch: Throwable -> L14
        createSessionInternal(0);     // Catch: Throwable -> L14
        monitor-exit(r0);     // Catch: Throwable -> L14
        return;
    L21:
        GlShader$$ExternalSyntheticBUOutline1.m("CameraCapturer must be initialized before calling startCapture.");
    }

    @Override // org.webrtc.VideoCapturer
    public void stopCapture() {
        Logging.d(TAG, "Stop capture");
        Object r0 = this.stateLock;
        monitor-enter(r0);
    L25:
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.sessionOpening == false) goto L15;
        Logging.d(TAG, "Stop capture: Waiting for session to open");     // Catch: Throwable -> L9
        this.stateLock.wait();     // Catch: Throwable -> L9 InterruptedException -> L11
    L11:
        Logging.w(TAG, "Stop capture interrupted while waiting for the session to open.");     // Catch: Throwable -> L9
        Thread.currentThread().interrupt();     // Catch: Throwable -> L9
        return;
    L15:
        if (this.currentSession == null) goto L17;
        Logging.d(TAG, "Stop capture: Nulling session");     // Catch: Throwable -> L9
        this.cameraStatistics.release();     // Catch: Throwable -> L9
        this.cameraStatistics = null;     // Catch: Throwable -> L9
        CameraSession r2 = this.currentSession;     // Catch: Throwable -> L9
        this.cameraThreadHandler.post(new AnonymousClass6(this, r2));     // Catch: Throwable -> L9
        this.currentSession = null;     // Catch: Throwable -> L9
        this.capturerObserver.onCapturerStopped();     // Catch: Throwable -> L9
    L18:
        monitor-exit(r0);     // Catch: Throwable -> L9
        Logging.d(TAG, "Stop capture done");
        return;
    L17:
        Logging.d(TAG, "Stop capture: No session open");     // Catch: Throwable -> L9
        goto L18
    }

    @Override // org.webrtc.CameraVideoCapturer
    public void switchCamera(CameraVideoCapturer.CameraSwitchHandler r3) {
        Logging.d(TAG, "switchCamera");
        this.cameraThreadHandler.post(new AnonymousClass7(this, r3));
    }

    @Override // org.webrtc.CameraVideoCapturer
    public void switchCamera(CameraVideoCapturer.CameraSwitchHandler r3, String r4) {
        Logging.d(TAG, "switchCamera");
        this.cameraThreadHandler.post(new AnonymousClass8(this, r3, r4));
    }
}
