package org.telegram.utils.camera.roundvideo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.util.Range;
import android.util.Size;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import androidx.camera.camera2.compat.AndroidRZoomCompat$$ExternalSyntheticApiModelOutline0;
import androidx.camera.camera2.compat.workaround.CameraMetadataSafeGetterKt$$ExternalSyntheticApiModelOutline0;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import kotlin.jvm.internal.LongCompanionObject;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.telegram.utils.camera.roundvideo.RoundVideoSession;

/* loaded from: classes6.dex */
final class RoundVideoCameraController {
    private volatile boolean active;
    private RoundVideoSession.CameraResolution activeCameraResolution;
    private RoundVideoSession.CameraFacing activeFacing;
    private Range<Integer> activeFpsRange;
    private RoundVideoSession.FrameRate activeFrameRate;
    private final Runnable applyZoomRunnable;
    private volatile boolean awaitingSwitchPreviewFrame;
    private boolean backConfigurationLogged;
    private final Callback callback;
    private CameraDevice cameraDevice;
    private Handler cameraHandler;
    private String cameraId;
    private final CameraManager cameraManager;
    private long cameraOpenRequestedNs;
    private long cameraSwitchStartedNs;
    private HandlerThread cameraThread;
    private final CameraCaptureSession.CaptureCallback captureCallback;
    private long captureCallbackIntervalCount;
    private long captureCallbackIntervalMaximumNs;
    private long captureCallbackIntervalTotalNs;
    private long captureFpsFirstTimestampNs;
    private long captureFpsLastTimestampNs;
    private long captureFrames;
    private long captureLastCallbackNs;
    private long captureSensorGapsOver100Ms;
    private long captureSensorGapsOver50Ms;
    private long captureSensorIntervalCount;
    private long captureSensorIntervalMaximumNs;
    private long captureSensorIntervalMinimumNs;
    private double captureSensorIntervalSquaredTotalNs;
    private long captureSensorIntervalTotalNs;
    private CameraCaptureSession captureSession;
    private long captureSessionRequestedNs;
    private CameraCharacteristics characteristics;
    private RoundVideoCodecRecorder commonStartRecorder;
    private final Runnable commonStartTimeoutRunnable;
    private final boolean compositionEnabled;
    private final Context context;
    private final CameraDevice.StateCallback deviceCallback;
    private final RoundVideoDiagnostics diagnostics;
    private boolean force30Fps;
    private boolean frameRateFallbackAttempted;
    private boolean frameRatePolicyResolved;
    private boolean frontConfigurationLogged;
    private RoundVideoGlProcessor glProcessor;
    private final View.OnLayoutChangeListener layoutChangeListener;
    private float maximumZoomRatio;
    private boolean notifySwitchCompletion;
    private boolean opening;
    private final RoundVideoSession.OutputResolution outputResolution;
    private final int outputSize;
    private long previewFpsStartedNs;
    private long previewFrames;
    private long previewGapsOver100Ms;
    private long previewGapsOver50Ms;
    private long previewIntervalCount;
    private long previewIntervalMaximumNs;
    private long previewIntervalMinimumNs;
    private double previewIntervalSquaredTotalNs;
    private long previewIntervalTotalNs;
    private long previewLastCallbackNs;
    private long previewLastTimestampNs;
    private final TextureView.SurfaceTextureListener previewListener;
    private Size previewSize;
    private Surface previewSurface;
    private long previewTimestampFirstNs;
    private long previewTimestampFrames;
    private long previewTimestampLastNs;
    private long previewTimestampNonMonotonic;
    private volatile boolean previewTransformEnabled;
    private final TextureView previewView;
    private RoundVideoCodecRecorder recorder;
    private volatile Size recordingSize;
    private Surface recordingSurface;
    private OutputPair regularFallbackPair;
    private boolean reopenAfterCameraClose;
    private CaptureRequest.Builder repeatingBuilder;
    private int requestGeneration;
    private final RoundVideoSession.CameraResolution requestedCameraResolution;
    private volatile RoundVideoSession.CameraFacing requestedFacing;
    private final RoundVideoSession.FrameRate requestedFrameRate;
    private long segmentStartedNs;
    private RoundVideoSession.CameraFacing selectedFacing;
    private final CameraCaptureSession.StateCallback sessionCallback;
    private volatile int sourceCropSize;
    private volatile boolean stopping;
    private boolean switchingCamera;
    private long timelineOffsetUs;
    private boolean torchEnabled;
    private int torchVerificationFrames;
    private int torchVerificationGeneration;
    private boolean torchVerificationPending;
    private int torchVerificationRetries;
    private final int videoBitrate;
    private RoundVideoMp4Writer writer;
    private final Rect zoomCropRect;
    private volatile float zoomProgress;

    /* renamed from: org.telegram.utils.camera.roundvideo.RoundVideoCameraController$1, reason: invalid class name */
    public class AnonymousClass1 implements TextureView.SurfaceTextureListener {
        final /* synthetic */ RoundVideoCameraController this$0;

        public AnonymousClass1(RoundVideoCameraController r1) {
            this.this$0 = r1;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(SurfaceTexture r3, int r4, int r5) {
            RoundVideoCameraController.m22098$$Nest$fgetdiagnostics(this.this$0).log("preview surface available: view=" + r4 + "x" + r5);
            RoundVideoCameraController.m22150$$Nest$mmaybeOpenCamera(this.this$0);
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture r3) {
            RoundVideoCameraController.m22098$$Nest$fgetdiagnostics(this.this$0).log("preview surface destroyed: active=" + RoundVideoCameraController.m22072$$Nest$fgetactive(this.this$0));
            if (RoundVideoCameraController.m22072$$Nest$fgetactive(this.this$0) == false) goto L7;
            RoundVideoCameraController.m22154$$Nest$mreportError(this.this$0, new IllegalStateException("Preview SurfaceTexture was destroyed"));
            return true;
        L7:
            return true;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture r3, int r4, int r5) {
            RoundVideoCameraController.m22098$$Nest$fgetdiagnostics(this.this$0).log("preview surface size changed: view=" + r4 + "x" + r5);
            RoundVideoCameraController.m22158$$Nest$mupdatePreviewTransform(this.this$0);
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture r11) {
            if (RoundVideoCameraController.m22077$$Nest$fgetawaitingSwitchPreviewFrame(this.this$0) == false) goto L5;
            RoundVideoCameraController.m22120$$Nest$fputawaitingSwitchPreviewFrame(this.this$0, false);
            RoundVideoCameraController.m22098$$Nest$fgetdiagnostics(this.this$0).log("camera switch first preview frame");
            RoundVideoCameraController.m22078$$Nest$fgetcallback(this.this$0).onCameraSwitchFirstFrame();
        L5:
            long r0 = SystemClock.elapsedRealtimeNanos();
            long r2 = r11.getTimestamp();
            if (RoundVideoCameraController.m22102$$Nest$fgetpreviewFpsStartedNs(this.this$0) != 0) goto L13;
            RoundVideoCameraController.m22132$$Nest$fputpreviewFpsStartedNs(this.this$0, r0);
            RoundVideoDiagnostics r112 = RoundVideoCameraController.m22098$$Nest$fgetdiagnostics(this.this$0);
            StringBuilder r4 = new StringBuilder("preview frame delivery started: thread=");
            r4.append(Thread.currentThread().getName());
            r4.append(", view=");
            r4.append(RoundVideoCameraController.m22110$$Nest$fgetpreviewView(this.this$0).getWidth());
            r4.append("x");
            r4.append(RoundVideoCameraController.m22110$$Nest$fgetpreviewView(this.this$0).getHeight());
            r4.append(", buffer=");
            r4.append(RoundVideoCameraController.m22106$$Nest$fgetpreviewSize(this.this$0));
            r4.append(", attached=");
            r4.append(RoundVideoCameraController.m22110$$Nest$fgetpreviewView(this.this$0).isAttachedToWindow());
            r4.append(", shown=");
            r4.append(RoundVideoCameraController.m22110$$Nest$fgetpreviewView(this.this$0).isShown());
            r4.append(", alpha=");
            r4.append(RoundVideoCameraController.m22110$$Nest$fgetpreviewView(this.this$0).getAlpha());
            r4.append(", hardwareAccelerated=");
            r4.append(RoundVideoCameraController.m22110$$Nest$fgetpreviewView(this.this$0).isHardwareAccelerated());
            r4.append(", displayRefreshRate=");
            if (RoundVideoCameraController.m22110$$Nest$fgetpreviewView(this.this$0).getDisplay() != null) goto L10;
            Object r5 = "unknown";
        L11:
            r4.append(r5);
            r112.log(r4.toString());
            goto L13
        L10:
            r5 = Float.valueOf(RoundVideoCameraController.m22110$$Nest$fgetpreviewView(this.this$0).getDisplay().getRefreshRate());
        L13:
            if (RoundVideoCameraController.m22104$$Nest$fgetpreviewLastCallbackNs(this.this$0) == 0) goto L15;
            RoundVideoCameraController r113 = this.this$0;
            RoundVideoCameraController.m22153$$Nest$mrecordPreviewInterval(r113, r0 - RoundVideoCameraController.m22104$$Nest$fgetpreviewLastCallbackNs(r113));
        L15:
            RoundVideoCameraController.m22134$$Nest$fputpreviewLastCallbackNs(this.this$0, r0);
            long r42 = RoundVideoCameraController.m22105$$Nest$fgetpreviewLastTimestampNs(this.this$0);
            RoundVideoCameraController r43 = this.this$0;
            if (r2 <= r42) goto L22;
            if (RoundVideoCameraController.m22107$$Nest$fgetpreviewTimestampFirstNs(r43) != 0) goto L20;
            RoundVideoCameraController.m22136$$Nest$fputpreviewTimestampFirstNs(this.this$0, r2);
        L20:
            RoundVideoCameraController.m22138$$Nest$fputpreviewTimestampLastNs(this.this$0, r2);
            RoundVideoCameraController r114 = this.this$0;
            RoundVideoCameraController.m22137$$Nest$fputpreviewTimestampFrames(r114, RoundVideoCameraController.m22108$$Nest$fgetpreviewTimestampFrames(r114) + 1);
        L24:
            RoundVideoCameraController.m22135$$Nest$fputpreviewLastTimestampNs(this.this$0, r2);
            RoundVideoCameraController r115 = this.this$0;
            RoundVideoCameraController.m22133$$Nest$fputpreviewFrames(r115, RoundVideoCameraController.m22103$$Nest$fgetpreviewFrames(r115) + 1);
            long r22 = r0 - RoundVideoCameraController.m22102$$Nest$fgetpreviewFpsStartedNs(this.this$0);
            if (r22 < 5000000000L) goto L28;
            RoundVideoCameraController.m22149$$Nest$mlogPreviewFrameMetrics(this.this$0, r0, r22);
            return;
        L28:
            return;
        L22:
            if (RoundVideoCameraController.m22105$$Nest$fgetpreviewLastTimestampNs(r43) == 0) goto L24;
            RoundVideoCameraController r116 = this.this$0;
            RoundVideoCameraController.m22139$$Nest$fputpreviewTimestampNonMonotonic(r116, RoundVideoCameraController.m22109$$Nest$fgetpreviewTimestampNonMonotonic(r116) + 1);
            goto L24
        }
    }

    /* renamed from: org.telegram.utils.camera.roundvideo.RoundVideoCameraController$2, reason: invalid class name */
    public class AnonymousClass2 extends CameraDevice.StateCallback {
        final /* synthetic */ RoundVideoCameraController this$0;

        public AnonymousClass2(RoundVideoCameraController r1) {
            this.this$0 = r1;
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onClosed(CameraDevice r3) {
            boolean r32 = RoundVideoCameraController.m22118$$Nest$fgetswitchingCamera(this.this$0);
            RoundVideoCameraController r0 = this.this$0;
            if (r32 == false) goto L9;
            RoundVideoCameraController.m22142$$Nest$fputswitchingCamera(r0, false);
            if (RoundVideoCameraController.m22072$$Nest$fgetactive(this.this$0) == false) goto L14;
            RoundVideoCameraController.m22151$$Nest$mopenCameraIfReady(this.this$0);
            return;
        L14:
            return;
        L9:
            if (RoundVideoCameraController.m22112$$Nest$fgetreopenAfterCameraClose(r0) == false) goto L15;
            RoundVideoCameraController.m22140$$Nest$fputreopenAfterCameraClose(this.this$0, false);
            if (RoundVideoCameraController.m22072$$Nest$fgetactive(this.this$0) == false) goto L16;
            RoundVideoCameraController.m22151$$Nest$mopenCameraIfReady(this.this$0);
            return;
        L16:
            return;
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onDisconnected(CameraDevice r5) {
            boolean r1 = false;
            RoundVideoCameraController.m22131$$Nest$fputopening(this.this$0, false);
            RoundVideoCameraController.m22098$$Nest$fgetdiagnostics(this.this$0).log("camera disconnected: id=" + r5.getId());
            if (RoundVideoCameraController.m22072$$Nest$fgetactive(this.this$0) == false) goto L12;
            if (RoundVideoCameraController.m22117$$Nest$fgetstopping(this.this$0) == true) goto L12;
            if (RoundVideoCameraController.m22118$$Nest$fgetswitchingCamera(this.this$0) == true) goto L12;
            if (RoundVideoCameraController.m22112$$Nest$fgetreopenAfterCameraClose(this.this$0) == true) goto L12;
            r1 = true;
        L12:
            if (RoundVideoCameraController.m22079$$Nest$fgetcameraDevice(this.this$0) != r5) goto L14;
            RoundVideoCameraController.m22144$$Nest$mcloseCaptureSession(this.this$0);
            RoundVideoCameraController.m22121$$Nest$fputcameraDevice(this.this$0, null);
        L14:
            r5.close();
            if (r1 == false) goto L18;
            RoundVideoCameraController.m22154$$Nest$mreportError(this.this$0, new IllegalStateException("Camera device disconnected"));
            return;
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onError(CameraDevice r3, int r4) {
            RoundVideoCameraController.m22131$$Nest$fputopening(this.this$0, false);
            if (RoundVideoCameraController.m22079$$Nest$fgetcameraDevice(this.this$0) != r3) goto L5;
            RoundVideoCameraController.m22144$$Nest$mcloseCaptureSession(this.this$0);
            RoundVideoCameraController.m22121$$Nest$fputcameraDevice(this.this$0, null);
        L5:
            r3.close();
            RoundVideoCameraController.m22154$$Nest$mreportError(this.this$0, new IllegalStateException("Camera device error: " + RoundVideoCameraController.m22161$$Nest$smcameraErrorName(r4) + " (" + r4 + ")"));
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onOpened(CameraDevice r5) {
            RoundVideoCameraController.m22131$$Nest$fputopening(this.this$0, false);
            RoundVideoCameraController.m22098$$Nest$fgetdiagnostics(this.this$0).log("camera opened: id=" + r5.getId() + ", elapsedMs=" + RoundVideoCameraController.m22162$$Nest$smelapsedMs(RoundVideoCameraController.m22080$$Nest$fgetcameraOpenRequestedNs(this.this$0)));
            if (RoundVideoCameraController.m22072$$Nest$fgetactive(this.this$0) == true) goto L5;
        L9:
            r5.close();
            return;
        L5:
            if (RoundVideoCameraController.m22118$$Nest$fgetswitchingCamera(this.this$0) == true) goto L9;
            RoundVideoCameraController.m22121$$Nest$fputcameraDevice(this.this$0, r5);
            RoundVideoCameraController.m22145$$Nest$mcreateCaptureSession(this.this$0);
        }
    }

    /* renamed from: org.telegram.utils.camera.roundvideo.RoundVideoCameraController$3, reason: invalid class name */
    public class AnonymousClass3 extends CameraCaptureSession.StateCallback {
        final /* synthetic */ RoundVideoCameraController this$0;

        /* renamed from: $r8$lambda$kR5RFBkZkJg0TL-6sNcWwFHrUog, reason: not valid java name */
        public static /* synthetic */ void m22165$r8$lambda$kR5RFBkZkJg0TL6sNcWwFHrUog(RoundVideoCameraController r0) {
            RoundVideoCameraController.m22158$$Nest$mupdatePreviewTransform(r0);
        }

        public AnonymousClass3(RoundVideoCameraController r1) {
            this.this$0 = r1;
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onClosed(CameraCaptureSession r2) {
            if (RoundVideoCameraController.m22096$$Nest$fgetcaptureSession(this.this$0) == r2) goto L5;
            return;
        L5:
            RoundVideoCameraController.m22129$$Nest$fputcaptureSession(this.this$0, null);
            RoundVideoCameraController.m22141$$Nest$fputrepeatingBuilder(this.this$0, null);
            RoundVideoCameraController.m22143$$Nest$fputtorchVerificationPending(this.this$0, false);
            RoundVideoCameraController.m22098$$Nest$fgetdiagnostics(this.this$0).log("capture session closed");
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigureFailed(CameraCaptureSession r3) {
            r3.close();
            if (RoundVideoCameraController.m22096$$Nest$fgetcaptureSession(this.this$0) != r3) goto L6;
            RoundVideoCameraController.m22129$$Nest$fputcaptureSession(this.this$0, null);
            RoundVideoCameraController.m22141$$Nest$fputrepeatingBuilder(this.this$0, null);
        L6:
            if (RoundVideoCameraController.m22072$$Nest$fgetactive(this.this$0) == true) goto L8;
        L20:
            RoundVideoCameraController.m22098$$Nest$fgetdiagnostics(this.this$0).log("stale capture session configuration failure ignored");
            return;
        L8:
            if (RoundVideoCameraController.m22079$$Nest$fgetcameraDevice(this.this$0) == null) goto L20;
            if (RoundVideoCameraController.m22118$$Nest$fgetswitchingCamera(this.this$0) == true) goto L20;
            if (RoundVideoCameraController.m22117$$Nest$fgetstopping(this.this$0) == true) goto L20;
            RoundVideoSession.FrameRate r32 = RoundVideoCameraController.m22076$$Nest$fgetactiveFrameRate(this.this$0);
            RoundVideoSession.FrameRate r0 = RoundVideoSession.FrameRate.FPS_60;
            RoundVideoCameraController r2 = this.this$0;
            if (r32 != r0) goto L18;
            RoundVideoCameraController.m22147$$Nest$mfallbackTo30Fps(r2, "60 fps session configuration failed", null);
            return;
        L18:
            RoundVideoCameraController.m22154$$Nest$mreportError(r2, new IllegalStateException("Camera capture session configuration failed"));
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigured(CameraCaptureSession r10) {
            if (RoundVideoCameraController.m22072$$Nest$fgetactive(this.this$0) == true) goto L5;
        L26:
            r10.close();
            return;
        L5:
            if (RoundVideoCameraController.m22079$$Nest$fgetcameraDevice(this.this$0) == null) goto L26;
            RoundVideoCameraController.m22129$$Nest$fputcaptureSession(this.this$0, r10);
            RoundVideoCameraController r102 = this.this$0;     // Catch: Exception -> L11
            RoundVideoCameraController.m22119$$Nest$fputactiveFacing(r102, RoundVideoCameraController.m22116$$Nest$fgetselectedFacing(r102));     // Catch: Exception -> L11
            RoundVideoCameraController r103 = this.this$0;     // Catch: Exception -> L11
            RoundVideoCameraController.m22141$$Nest$fputrepeatingBuilder(r103, RoundVideoCameraController.m22146$$Nest$mcreateRepeatingRequestBuilder(r103, true));     // Catch: Exception -> L11
            if (RoundVideoCameraController.m22099$$Nest$fgetglProcessor(this.this$0) == null) goto L13;
            RoundVideoCameraController.m22099$$Nest$fgetglProcessor(this.this$0).onCameraSessionConfigured();     // Catch: Exception -> L11
        L13:
            boolean r104 = RoundVideoCameraController.m22101$$Nest$fgetnotifySwitchCompletion(this.this$0);     // Catch: Exception -> L11
            RoundVideoCameraController.m22130$$Nest$fputnotifySwitchCompletion(this.this$0, false);     // Catch: Exception -> L11
            RoundVideoCameraController.m22120$$Nest$fputawaitingSwitchPreviewFrame(this.this$0, r104);     // Catch: Exception -> L11
            RoundVideoCameraController.m22156$$Nest$msubmitRepeatingRequest(this.this$0);     // Catch: Exception -> L11
            RoundVideoDiagnostics r2 = RoundVideoCameraController.m22098$$Nest$fgetdiagnostics(this.this$0);     // Catch: Exception -> L11
            StringBuilder r3 = new StringBuilder("capture session configured: facing=");     // Catch: Exception -> L11
            r3.append(RoundVideoCameraController.m22074$$Nest$fgetactiveFacing(this.this$0));     // Catch: Exception -> L11
            r3.append(", fpsRange=");     // Catch: Exception -> L11
            r3.append(RoundVideoCameraController.m22075$$Nest$fgetactiveFpsRange(this.this$0));     // Catch: Exception -> L11
            r3.append(", elapsedMs=");     // Catch: Exception -> L11
            r3.append(RoundVideoCameraController.m22162$$Nest$smelapsedMs(RoundVideoCameraController.m22097$$Nest$fgetcaptureSessionRequestedNs(this.this$0)));     // Catch: Exception -> L11
            r3.append(", segmentElapsedMs=");     // Catch: Exception -> L11
            r3.append(RoundVideoCameraController.m22162$$Nest$smelapsedMs(RoundVideoCameraController.m22115$$Nest$fgetsegmentStartedNs(this.this$0)));     // Catch: Exception -> L11
            if (r104 == false) goto L16;
            String r0 = ", switchElapsedMs=" + RoundVideoCameraController.m22162$$Nest$smelapsedMs(RoundVideoCameraController.m22081$$Nest$fgetcameraSwitchStartedNs(this.this$0));     // Catch: Exception -> L11
        L17:
            r3.append(r0);     // Catch: Exception -> L11
            r2.log(r3.toString());     // Catch: Exception -> L11
            RoundVideoCameraController.m22110$$Nest$fgetpreviewView(this.this$0).post(new RoundVideoCameraController$3$$ExternalSyntheticLambda0(this.this$0));     // Catch: Exception -> L11
            RoundVideoCameraController.m22078$$Nest$fgetcallback(this.this$0).onConfigured(new CameraInfo(RoundVideoCameraController.m22074$$Nest$fgetactiveFacing(this.this$0), RoundVideoCameraController.m22073$$Nest$fgetactiveCameraResolution(this.this$0), RoundVideoCameraController.m22076$$Nest$fgetactiveFrameRate(this.this$0), RoundVideoCameraController.m22106$$Nest$fgetpreviewSize(this.this$0), RoundVideoCameraController.m22111$$Nest$fgetrecordingSize(this.this$0), RoundVideoCameraController.m22100$$Nest$fgetmaximumZoomRatio(this.this$0), RoundVideoCameraController.m22148$$Nest$misTorchAvailable(this.this$0)), r104);     // Catch: Exception -> L11
            if (RoundVideoCameraController.m22113$$Nest$fgetrequestedFacing(this.this$0) == RoundVideoCameraController.m22074$$Nest$fgetactiveFacing(this.this$0)) goto L30;
            RoundVideoCameraController r105 = this.this$0;     // Catch: Exception -> L11
            RoundVideoCameraController.m22157$$Nest$mswitchCameraIfNeeded(r105, RoundVideoCameraController.m22113$$Nest$fgetrequestedFacing(r105));     // Catch: Exception -> L11
            return;
        L30:
            return;
        L16:
            r0 = _UrlKt.FRAGMENT_ENCODE_SET;
        L11:
            e = move-exception;
            RoundVideoSession.FrameRate r02 = RoundVideoCameraController.m22076$$Nest$fgetactiveFrameRate(this.this$0);
            RoundVideoSession.FrameRate r1 = RoundVideoSession.FrameRate.FPS_60;
            RoundVideoCameraController r9 = this.this$0;
            if (r02 != r1) goto L24;
            RoundVideoCameraController.m22147$$Nest$mfallbackTo30Fps(r9, "60 fps request submission rejected", e);
            return;
        L24:
            RoundVideoCameraController.m22154$$Nest$mreportError(r9, e);
        }
    }

    /* renamed from: org.telegram.utils.camera.roundvideo.RoundVideoCameraController$4, reason: invalid class name */
    public class AnonymousClass4 extends CameraCaptureSession.CaptureCallback {
        final /* synthetic */ RoundVideoCameraController this$0;

        public AnonymousClass4(RoundVideoCameraController r1) {
            this.this$0 = r1;
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureCompleted(CameraCaptureSession r18, CaptureRequest r19, TotalCaptureResult r20) {
            RoundVideoCameraController.m22159$$Nest$mverifyTorchResult(this.this$0, r19, r20);
            Long r1 = (Long) r20.get(CaptureResult.SENSOR_TIMESTAMP);
            if (r1 != null) goto L5;
            return;
        L5:
            if (r1.longValue() <= RoundVideoCameraController.m22086$$Nest$fgetcaptureFpsLastTimestampNs(this.this$0)) goto L33;
            long r2 = SystemClock.elapsedRealtimeNanos();
            long r4 = RoundVideoCameraController.m22085$$Nest$fgetcaptureFpsFirstTimestampNs(this.this$0);
            RoundVideoCameraController r5 = this.this$0;
            if (r4 != 0) goto L10;
            RoundVideoCameraController.m22125$$Nest$fputcaptureFpsFirstTimestampNs(r5, r1.longValue());
            RoundVideoCameraController.m22127$$Nest$fputcaptureFrames(this.this$0, 1);
        L12:
            if (RoundVideoCameraController.m22088$$Nest$fgetcaptureLastCallbackNs(this.this$0) == 0) goto L14;
            long r42 = r2 - RoundVideoCameraController.m22088$$Nest$fgetcaptureLastCallbackNs(this.this$0);
            RoundVideoCameraController r6 = this.this$0;
            RoundVideoCameraController.m22122$$Nest$fputcaptureCallbackIntervalCount(r6, RoundVideoCameraController.m22082$$Nest$fgetcaptureCallbackIntervalCount(r6) + 1);
            RoundVideoCameraController r62 = this.this$0;
            RoundVideoCameraController.m22124$$Nest$fputcaptureCallbackIntervalTotalNs(r62, RoundVideoCameraController.m22084$$Nest$fgetcaptureCallbackIntervalTotalNs(r62) + r42);
            RoundVideoCameraController r63 = this.this$0;
            RoundVideoCameraController.m22123$$Nest$fputcaptureCallbackIntervalMaximumNs(r63, Math.max(RoundVideoCameraController.m22083$$Nest$fgetcaptureCallbackIntervalMaximumNs(r63), r42));
        L14:
            RoundVideoCameraController.m22128$$Nest$fputcaptureLastCallbackNs(this.this$0, r2);
            RoundVideoCameraController.m22126$$Nest$fputcaptureFpsLastTimestampNs(this.this$0, r1.longValue());
            long r43 = r1.longValue() - RoundVideoCameraController.m22085$$Nest$fgetcaptureFpsFirstTimestampNs(this.this$0);
            if (r43 < 3000000000L) goto L34;
            float r64 = ((RoundVideoCameraController.m22087$$Nest$fgetcaptureFrames(this.this$0) - 1) * 1.0E9f) / r43;
            boolean r52 = false;
            if (RoundVideoCameraController.m22076$$Nest$fgetactiveFrameRate(this.this$0) != null) goto L20;
            int r44 = 0;
        L21:
            RoundVideoDiagnostics r7 = RoundVideoCameraController.m22098$$Nest$fgetdiagnostics(this.this$0);
            StringBuilder r10 = new StringBuilder("camera capture rate: measuredFps=");
            r10.append(r64);
            r10.append(", requestedFps=");
            r10.append(RoundVideoCameraController.m22114$$Nest$fgetrequestedFrameRate(this.this$0).getValue());
            r10.append(", activeFps=");
            if (r44 != 0) goto L24;
            Object r11 = "unknown";
        L25:
            r10.append(r11);
            r10.append(", targetMet=");
            if (r44 != 0) goto L28;
        L29:
            r52 = true;
        L30:
            r10.append(r52);
            r10.append(", fpsRange=");
            r10.append(RoundVideoCameraController.m22075$$Nest$fgetactiveFpsRange(this.this$0));
            r10.append(", sensorIntervalMs={avg=");
            r10.append(RoundVideoCameraController.m22160$$Nest$smaverageMs(RoundVideoCameraController.m22095$$Nest$fgetcaptureSensorIntervalTotalNs(this.this$0), RoundVideoCameraController.m22091$$Nest$fgetcaptureSensorIntervalCount(this.this$0)));
            r10.append(", min=");
            r10.append(RoundVideoCameraController.m22163$$Nest$smnanosToMs(RoundVideoCameraController.m22093$$Nest$fgetcaptureSensorIntervalMinimumNs(this.this$0)));
            r10.append(", max=");
            r10.append(RoundVideoCameraController.m22163$$Nest$smnanosToMs(RoundVideoCameraController.m22092$$Nest$fgetcaptureSensorIntervalMaximumNs(this.this$0)));
            r10.append(", jitter=");
            r10.append(RoundVideoCameraController.m22164$$Nest$smstandardDeviationMs(RoundVideoCameraController.m22095$$Nest$fgetcaptureSensorIntervalTotalNs(this.this$0), RoundVideoCameraController.m22094$$Nest$fgetcaptureSensorIntervalSquaredTotalNs(this.this$0), RoundVideoCameraController.m22091$$Nest$fgetcaptureSensorIntervalCount(this.this$0)));
            r10.append("}, sensorGaps={over50ms=");
            r10.append(RoundVideoCameraController.m22090$$Nest$fgetcaptureSensorGapsOver50Ms(this.this$0));
            r10.append(", over100ms=");
            r10.append(RoundVideoCameraController.m22089$$Nest$fgetcaptureSensorGapsOver100Ms(this.this$0));
            r10.append("}, callbackIntervalMs={avg=");
            r10.append(RoundVideoCameraController.m22160$$Nest$smaverageMs(RoundVideoCameraController.m22084$$Nest$fgetcaptureCallbackIntervalTotalNs(this.this$0), RoundVideoCameraController.m22082$$Nest$fgetcaptureCallbackIntervalCount(this.this$0)));
            r10.append(", max=");
            r10.append(RoundVideoCameraController.m22163$$Nest$smnanosToMs(RoundVideoCameraController.m22083$$Nest$fgetcaptureCallbackIntervalMaximumNs(this.this$0)));
            r10.append("}");
            r7.log(r10.toString());
            RoundVideoCameraController.m22155$$Nest$mresetCaptureFpsMeasurement(this.this$0);
            RoundVideoCameraController.m22125$$Nest$fputcaptureFpsFirstTimestampNs(this.this$0, r1.longValue());
            RoundVideoCameraController.m22126$$Nest$fputcaptureFpsLastTimestampNs(this.this$0, r1.longValue());
            RoundVideoCameraController.m22127$$Nest$fputcaptureFrames(this.this$0, 1);
            RoundVideoCameraController.m22128$$Nest$fputcaptureLastCallbackNs(this.this$0, r2);
            return;
        L28:
            if (r64 < (r44 * 0.85f)) goto L30;
        L24:
            r11 = Integer.valueOf(r44);
            goto L25
        L20:
            r44 = RoundVideoCameraController.m22076$$Nest$fgetactiveFrameRate(this.this$0).getValue();
            goto L21
        L34:
            return;
        L10:
            RoundVideoCameraController.m22152$$Nest$mrecordCaptureSensorInterval(r5, r1.longValue() - RoundVideoCameraController.m22086$$Nest$fgetcaptureFpsLastTimestampNs(this.this$0));
            RoundVideoCameraController r45 = this.this$0;
            RoundVideoCameraController.m22127$$Nest$fputcaptureFrames(r45, RoundVideoCameraController.m22087$$Nest$fgetcaptureFrames(r45) + 1);
            goto L12
        }
    }

    public interface Callback {
        void onCameraSwitchFirstFrame();

        void onCameraSwitchStarted(RoundVideoSession.CameraFacing r1);

        void onConfigured(CameraInfo r1, boolean r2);

        void onError(Exception r1);

        void onRecordingStarted();

        void onRecordingStopped();
    }

    public static final class CameraCandidate {
        final CameraCharacteristics characteristics;
        final FrameRatePlan frameRatePlan;
        final String id;
        final OutputPair regularPair;
        final Size[] sizes;

        public CameraCandidate(String r1, CameraCharacteristics r2, Size[] r3, OutputPair r4, FrameRatePlan r5) {
            this.id = r1;
            this.characteristics = r2;
            this.sizes = r3;
            this.regularPair = r4;
            this.frameRatePlan = r5;
        }
    }

    public static final class CameraInfo {
        final RoundVideoSession.CameraResolution cameraResolution;
        final RoundVideoSession.CameraFacing facing;
        final RoundVideoSession.FrameRate frameRate;
        final float maximumZoomRatio;
        final Size previewSize;
        final Size recordingSize;
        final boolean torchAvailable;

        public CameraInfo(RoundVideoSession.CameraFacing r1, RoundVideoSession.CameraResolution r2, RoundVideoSession.FrameRate r3, Size r4, Size r5, float r6, boolean r7) {
            this.facing = r1;
            this.cameraResolution = r2;
            this.frameRate = r3;
            this.previewSize = r4;
            this.recordingSize = r5;
            this.maximumZoomRatio = r6;
            this.torchAvailable = r7;
        }
    }

    public static final class FrameRatePlan {
        final Range<Integer> fpsRange;
        final RoundVideoSession.FrameRate frameRate;
        final OutputPair outputPair;

        public FrameRatePlan(RoundVideoSession.FrameRate r1, Range<Integer> r2, OutputPair r3) {
            this.frameRate = r1;
            this.fpsRange = r2;
            this.outputPair = r3;
        }
    }

    public static final class OutputPair {
        final RoundVideoSession.CameraResolution cameraResolution;
        final int cropSize;
        final Size preview;
        final Size recording;

        public OutputPair(Size r1, Size r2, RoundVideoSession.CameraResolution r3, int r4) {
            this.preview = r1;
            this.recording = r2;
            this.cameraResolution = r3;
            this.cropSize = r4;
        }
    }

    public static /* synthetic */ void $r8$lambda$2Fc_dJ5DcjI31A5Ft7DmVhwYjbY(RoundVideoCameraController r0, View r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9) {
        r0.lambda$new$0(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    public static /* synthetic */ void $r8$lambda$491wkZAaqRfQ4e43SWw65HL10QY(RoundVideoCameraController r0) {
        r0.createCaptureSession();
    }

    /* renamed from: $r8$lambda$7TauQyEVoIpaEFpaE-gpAsD65AM, reason: not valid java name */
    public static /* synthetic */ void m22066$r8$lambda$7TauQyEVoIpaEFpaEgpAsD65AM(RoundVideoCameraController r0, HandlerThread r1) {
        r0.lambda$release$5(r1);
    }

    /* renamed from: $r8$lambda$7kOLPySoiDBJ197Uw451eev3I-w, reason: not valid java name */
    public static /* synthetic */ void m22067$r8$lambda$7kOLPySoiDBJ197Uw451eev3Iw(RoundVideoCameraController r0) {
        r0.lambda$new$1();
    }

    public static /* synthetic */ void $r8$lambda$DyDSGFKIkdC1_iX6FEu7G5wFEIw(RoundVideoCameraController r0, RoundVideoCodecRecorder r1) {
        r0.lambda$startRecordingAfterFirstFrame$8(r1);
    }

    /* renamed from: $r8$lambda$GA1T_6Loc9gjMbQEr2KC-FL9bwo, reason: not valid java name */
    public static /* synthetic */ void m22068$r8$lambda$GA1T_6Loc9gjMbQEr2KCFL9bwo(RoundVideoCameraController r0) {
        r0.openCameraIfReady();
    }

    public static /* synthetic */ void $r8$lambda$LCYS0D_udTsi3TJvozL9qNX2HkE(RoundVideoCameraController r0, boolean r1) {
        r0.lambda$setTorchEnabled$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$LJXXjE8NW82dJzXI6GPQJhqbUwY(RoundVideoCameraController r0, RoundVideoCodecRecorder r1) {
        r0.lambda$startRecordingAfterFirstFrame$7(r1);
    }

    /* renamed from: $r8$lambda$VGjG-koRa4MfvLUjp0_NlY4X5yw, reason: not valid java name */
    public static /* synthetic */ void m22069$r8$lambda$VGjGkoRa4MfvLUjp0_NlY4X5yw(RoundVideoCameraController r0) {
        r0.lambda$stopSegment$4();
    }

    /* renamed from: $r8$lambda$_drZ5gYJBLzP3hzpZWkD0-riH5E, reason: not valid java name */
    public static /* synthetic */ void m22070$r8$lambda$_drZ5gYJBLzP3hzpZWkD0riH5E(RoundVideoCameraController r0) {
        r0.lambda$createRecordingPipeline$6();
    }

    public static /* synthetic */ void $r8$lambda$aSm8yikJu4WMAwTEMwNUopkCafk(RoundVideoCameraController r0) {
        r0.startRecordingAfterFirstFrame();
    }

    public static /* synthetic */ void $r8$lambda$l2dTEe0M919_mmWFgBaYy3z37oI(RoundVideoCameraController r0, RoundVideoSession.CameraFacing r1) {
        r0.lambda$setCameraFacing$2(r1);
    }

    /* renamed from: $r8$lambda$lidkUBM-2dWqsRYCtzL0nTp-H9o, reason: not valid java name */
    public static /* synthetic */ void m22071$r8$lambda$lidkUBM2dWqsRYCtzL0nTpH9o(RoundVideoCameraController r0, RoundVideoCodecRecorder r1) {
        r0.lambda$startRecordingAfterFirstFrame$9(r1);
    }

    public static /* synthetic */ void $r8$lambda$nsMRqNCOPwBW1KVpysXaqJ4ub_8(RoundVideoCameraController r0) {
        r0.applyRepeatingRequest();
    }

    public static /* synthetic */ void $r8$lambda$xNvqkK3mGMoZUtsNbXP3nFtsmRg(RoundVideoCameraController r0, RoundVideoCodecRecorder r1) {
        r0.lambda$startRecordingAfterFirstFrame$10(r1);
    }

    /* renamed from: -$$Nest$fgetactive, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m22072$$Nest$fgetactive(RoundVideoCameraController r0) {
        return r0.active;
    }

    /* renamed from: -$$Nest$fgetactiveCameraResolution, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RoundVideoSession.CameraResolution m22073$$Nest$fgetactiveCameraResolution(RoundVideoCameraController r0) {
        return r0.activeCameraResolution;
    }

    /* renamed from: -$$Nest$fgetactiveFacing, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RoundVideoSession.CameraFacing m22074$$Nest$fgetactiveFacing(RoundVideoCameraController r0) {
        return r0.activeFacing;
    }

    /* renamed from: -$$Nest$fgetactiveFpsRange, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Range m22075$$Nest$fgetactiveFpsRange(RoundVideoCameraController r0) {
        return r0.activeFpsRange;
    }

    /* renamed from: -$$Nest$fgetactiveFrameRate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RoundVideoSession.FrameRate m22076$$Nest$fgetactiveFrameRate(RoundVideoCameraController r0) {
        return r0.activeFrameRate;
    }

    /* renamed from: -$$Nest$fgetawaitingSwitchPreviewFrame, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m22077$$Nest$fgetawaitingSwitchPreviewFrame(RoundVideoCameraController r0) {
        return r0.awaitingSwitchPreviewFrame;
    }

    /* renamed from: -$$Nest$fgetcallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Callback m22078$$Nest$fgetcallback(RoundVideoCameraController r0) {
        return r0.callback;
    }

    /* renamed from: -$$Nest$fgetcameraDevice, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CameraDevice m22079$$Nest$fgetcameraDevice(RoundVideoCameraController r0) {
        return r0.cameraDevice;
    }

    /* renamed from: -$$Nest$fgetcameraOpenRequestedNs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m22080$$Nest$fgetcameraOpenRequestedNs(RoundVideoCameraController r2) {
        return r2.cameraOpenRequestedNs;
    }

    /* renamed from: -$$Nest$fgetcameraSwitchStartedNs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m22081$$Nest$fgetcameraSwitchStartedNs(RoundVideoCameraController r2) {
        return r2.cameraSwitchStartedNs;
    }

    /* renamed from: -$$Nest$fgetcaptureCallbackIntervalCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m22082$$Nest$fgetcaptureCallbackIntervalCount(RoundVideoCameraController r2) {
        return r2.captureCallbackIntervalCount;
    }

    /* renamed from: -$$Nest$fgetcaptureCallbackIntervalMaximumNs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m22083$$Nest$fgetcaptureCallbackIntervalMaximumNs(RoundVideoCameraController r2) {
        return r2.captureCallbackIntervalMaximumNs;
    }

    /* renamed from: -$$Nest$fgetcaptureCallbackIntervalTotalNs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m22084$$Nest$fgetcaptureCallbackIntervalTotalNs(RoundVideoCameraController r2) {
        return r2.captureCallbackIntervalTotalNs;
    }

    /* renamed from: -$$Nest$fgetcaptureFpsFirstTimestampNs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m22085$$Nest$fgetcaptureFpsFirstTimestampNs(RoundVideoCameraController r2) {
        return r2.captureFpsFirstTimestampNs;
    }

    /* renamed from: -$$Nest$fgetcaptureFpsLastTimestampNs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m22086$$Nest$fgetcaptureFpsLastTimestampNs(RoundVideoCameraController r2) {
        return r2.captureFpsLastTimestampNs;
    }

    /* renamed from: -$$Nest$fgetcaptureFrames, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m22087$$Nest$fgetcaptureFrames(RoundVideoCameraController r2) {
        return r2.captureFrames;
    }

    /* renamed from: -$$Nest$fgetcaptureLastCallbackNs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m22088$$Nest$fgetcaptureLastCallbackNs(RoundVideoCameraController r2) {
        return r2.captureLastCallbackNs;
    }

    /* renamed from: -$$Nest$fgetcaptureSensorGapsOver100Ms, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m22089$$Nest$fgetcaptureSensorGapsOver100Ms(RoundVideoCameraController r2) {
        return r2.captureSensorGapsOver100Ms;
    }

    /* renamed from: -$$Nest$fgetcaptureSensorGapsOver50Ms, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m22090$$Nest$fgetcaptureSensorGapsOver50Ms(RoundVideoCameraController r2) {
        return r2.captureSensorGapsOver50Ms;
    }

    /* renamed from: -$$Nest$fgetcaptureSensorIntervalCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m22091$$Nest$fgetcaptureSensorIntervalCount(RoundVideoCameraController r2) {
        return r2.captureSensorIntervalCount;
    }

    /* renamed from: -$$Nest$fgetcaptureSensorIntervalMaximumNs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m22092$$Nest$fgetcaptureSensorIntervalMaximumNs(RoundVideoCameraController r2) {
        return r2.captureSensorIntervalMaximumNs;
    }

    /* renamed from: -$$Nest$fgetcaptureSensorIntervalMinimumNs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m22093$$Nest$fgetcaptureSensorIntervalMinimumNs(RoundVideoCameraController r2) {
        return r2.captureSensorIntervalMinimumNs;
    }

    /* renamed from: -$$Nest$fgetcaptureSensorIntervalSquaredTotalNs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ double m22094$$Nest$fgetcaptureSensorIntervalSquaredTotalNs(RoundVideoCameraController r2) {
        return r2.captureSensorIntervalSquaredTotalNs;
    }

    /* renamed from: -$$Nest$fgetcaptureSensorIntervalTotalNs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m22095$$Nest$fgetcaptureSensorIntervalTotalNs(RoundVideoCameraController r2) {
        return r2.captureSensorIntervalTotalNs;
    }

    /* renamed from: -$$Nest$fgetcaptureSession, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CameraCaptureSession m22096$$Nest$fgetcaptureSession(RoundVideoCameraController r0) {
        return r0.captureSession;
    }

    /* renamed from: -$$Nest$fgetcaptureSessionRequestedNs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m22097$$Nest$fgetcaptureSessionRequestedNs(RoundVideoCameraController r2) {
        return r2.captureSessionRequestedNs;
    }

    /* renamed from: -$$Nest$fgetdiagnostics, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RoundVideoDiagnostics m22098$$Nest$fgetdiagnostics(RoundVideoCameraController r0) {
        return r0.diagnostics;
    }

    /* renamed from: -$$Nest$fgetglProcessor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RoundVideoGlProcessor m22099$$Nest$fgetglProcessor(RoundVideoCameraController r0) {
        return r0.glProcessor;
    }

    /* renamed from: -$$Nest$fgetmaximumZoomRatio, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m22100$$Nest$fgetmaximumZoomRatio(RoundVideoCameraController r0) {
        return r0.maximumZoomRatio;
    }

    /* renamed from: -$$Nest$fgetnotifySwitchCompletion, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m22101$$Nest$fgetnotifySwitchCompletion(RoundVideoCameraController r0) {
        return r0.notifySwitchCompletion;
    }

    /* renamed from: -$$Nest$fgetpreviewFpsStartedNs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m22102$$Nest$fgetpreviewFpsStartedNs(RoundVideoCameraController r2) {
        return r2.previewFpsStartedNs;
    }

    /* renamed from: -$$Nest$fgetpreviewFrames, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m22103$$Nest$fgetpreviewFrames(RoundVideoCameraController r2) {
        return r2.previewFrames;
    }

    /* renamed from: -$$Nest$fgetpreviewLastCallbackNs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m22104$$Nest$fgetpreviewLastCallbackNs(RoundVideoCameraController r2) {
        return r2.previewLastCallbackNs;
    }

    /* renamed from: -$$Nest$fgetpreviewLastTimestampNs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m22105$$Nest$fgetpreviewLastTimestampNs(RoundVideoCameraController r2) {
        return r2.previewLastTimestampNs;
    }

    /* renamed from: -$$Nest$fgetpreviewSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Size m22106$$Nest$fgetpreviewSize(RoundVideoCameraController r0) {
        return r0.previewSize;
    }

    /* renamed from: -$$Nest$fgetpreviewTimestampFirstNs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m22107$$Nest$fgetpreviewTimestampFirstNs(RoundVideoCameraController r2) {
        return r2.previewTimestampFirstNs;
    }

    /* renamed from: -$$Nest$fgetpreviewTimestampFrames, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m22108$$Nest$fgetpreviewTimestampFrames(RoundVideoCameraController r2) {
        return r2.previewTimestampFrames;
    }

    /* renamed from: -$$Nest$fgetpreviewTimestampNonMonotonic, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m22109$$Nest$fgetpreviewTimestampNonMonotonic(RoundVideoCameraController r2) {
        return r2.previewTimestampNonMonotonic;
    }

    /* renamed from: -$$Nest$fgetpreviewView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextureView m22110$$Nest$fgetpreviewView(RoundVideoCameraController r0) {
        return r0.previewView;
    }

    /* renamed from: -$$Nest$fgetrecordingSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Size m22111$$Nest$fgetrecordingSize(RoundVideoCameraController r0) {
        return r0.recordingSize;
    }

    /* renamed from: -$$Nest$fgetreopenAfterCameraClose, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m22112$$Nest$fgetreopenAfterCameraClose(RoundVideoCameraController r0) {
        return r0.reopenAfterCameraClose;
    }

    /* renamed from: -$$Nest$fgetrequestedFacing, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RoundVideoSession.CameraFacing m22113$$Nest$fgetrequestedFacing(RoundVideoCameraController r0) {
        return r0.requestedFacing;
    }

    /* renamed from: -$$Nest$fgetrequestedFrameRate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RoundVideoSession.FrameRate m22114$$Nest$fgetrequestedFrameRate(RoundVideoCameraController r0) {
        return r0.requestedFrameRate;
    }

    /* renamed from: -$$Nest$fgetsegmentStartedNs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m22115$$Nest$fgetsegmentStartedNs(RoundVideoCameraController r2) {
        return r2.segmentStartedNs;
    }

    /* renamed from: -$$Nest$fgetselectedFacing, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RoundVideoSession.CameraFacing m22116$$Nest$fgetselectedFacing(RoundVideoCameraController r0) {
        return r0.selectedFacing;
    }

    /* renamed from: -$$Nest$fgetstopping, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m22117$$Nest$fgetstopping(RoundVideoCameraController r0) {
        return r0.stopping;
    }

    /* renamed from: -$$Nest$fgetswitchingCamera, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m22118$$Nest$fgetswitchingCamera(RoundVideoCameraController r0) {
        return r0.switchingCamera;
    }

    /* renamed from: -$$Nest$fputactiveFacing, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22119$$Nest$fputactiveFacing(RoundVideoCameraController r0, RoundVideoSession.CameraFacing r1) {
        r0.activeFacing = r1;
    }

    /* renamed from: -$$Nest$fputawaitingSwitchPreviewFrame, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22120$$Nest$fputawaitingSwitchPreviewFrame(RoundVideoCameraController r0, boolean r1) {
        r0.awaitingSwitchPreviewFrame = r1;
    }

    /* renamed from: -$$Nest$fputcameraDevice, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22121$$Nest$fputcameraDevice(RoundVideoCameraController r0, CameraDevice r1) {
        r0.cameraDevice = r1;
    }

    /* renamed from: -$$Nest$fputcaptureCallbackIntervalCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22122$$Nest$fputcaptureCallbackIntervalCount(RoundVideoCameraController r0, long r1) {
        r0.captureCallbackIntervalCount = r1;
    }

    /* renamed from: -$$Nest$fputcaptureCallbackIntervalMaximumNs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22123$$Nest$fputcaptureCallbackIntervalMaximumNs(RoundVideoCameraController r0, long r1) {
        r0.captureCallbackIntervalMaximumNs = r1;
    }

    /* renamed from: -$$Nest$fputcaptureCallbackIntervalTotalNs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22124$$Nest$fputcaptureCallbackIntervalTotalNs(RoundVideoCameraController r0, long r1) {
        r0.captureCallbackIntervalTotalNs = r1;
    }

    /* renamed from: -$$Nest$fputcaptureFpsFirstTimestampNs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22125$$Nest$fputcaptureFpsFirstTimestampNs(RoundVideoCameraController r0, long r1) {
        r0.captureFpsFirstTimestampNs = r1;
    }

    /* renamed from: -$$Nest$fputcaptureFpsLastTimestampNs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22126$$Nest$fputcaptureFpsLastTimestampNs(RoundVideoCameraController r0, long r1) {
        r0.captureFpsLastTimestampNs = r1;
    }

    /* renamed from: -$$Nest$fputcaptureFrames, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22127$$Nest$fputcaptureFrames(RoundVideoCameraController r0, long r1) {
        r0.captureFrames = r1;
    }

    /* renamed from: -$$Nest$fputcaptureLastCallbackNs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22128$$Nest$fputcaptureLastCallbackNs(RoundVideoCameraController r0, long r1) {
        r0.captureLastCallbackNs = r1;
    }

    /* renamed from: -$$Nest$fputcaptureSession, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22129$$Nest$fputcaptureSession(RoundVideoCameraController r0, CameraCaptureSession r1) {
        r0.captureSession = r1;
    }

    /* renamed from: -$$Nest$fputnotifySwitchCompletion, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22130$$Nest$fputnotifySwitchCompletion(RoundVideoCameraController r0, boolean r1) {
        r0.notifySwitchCompletion = r1;
    }

    /* renamed from: -$$Nest$fputopening, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22131$$Nest$fputopening(RoundVideoCameraController r0, boolean r1) {
        r0.opening = r1;
    }

    /* renamed from: -$$Nest$fputpreviewFpsStartedNs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22132$$Nest$fputpreviewFpsStartedNs(RoundVideoCameraController r0, long r1) {
        r0.previewFpsStartedNs = r1;
    }

    /* renamed from: -$$Nest$fputpreviewFrames, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22133$$Nest$fputpreviewFrames(RoundVideoCameraController r0, long r1) {
        r0.previewFrames = r1;
    }

    /* renamed from: -$$Nest$fputpreviewLastCallbackNs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22134$$Nest$fputpreviewLastCallbackNs(RoundVideoCameraController r0, long r1) {
        r0.previewLastCallbackNs = r1;
    }

    /* renamed from: -$$Nest$fputpreviewLastTimestampNs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22135$$Nest$fputpreviewLastTimestampNs(RoundVideoCameraController r0, long r1) {
        r0.previewLastTimestampNs = r1;
    }

    /* renamed from: -$$Nest$fputpreviewTimestampFirstNs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22136$$Nest$fputpreviewTimestampFirstNs(RoundVideoCameraController r0, long r1) {
        r0.previewTimestampFirstNs = r1;
    }

    /* renamed from: -$$Nest$fputpreviewTimestampFrames, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22137$$Nest$fputpreviewTimestampFrames(RoundVideoCameraController r0, long r1) {
        r0.previewTimestampFrames = r1;
    }

    /* renamed from: -$$Nest$fputpreviewTimestampLastNs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22138$$Nest$fputpreviewTimestampLastNs(RoundVideoCameraController r0, long r1) {
        r0.previewTimestampLastNs = r1;
    }

    /* renamed from: -$$Nest$fputpreviewTimestampNonMonotonic, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22139$$Nest$fputpreviewTimestampNonMonotonic(RoundVideoCameraController r0, long r1) {
        r0.previewTimestampNonMonotonic = r1;
    }

    /* renamed from: -$$Nest$fputreopenAfterCameraClose, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22140$$Nest$fputreopenAfterCameraClose(RoundVideoCameraController r0, boolean r1) {
        r0.reopenAfterCameraClose = r1;
    }

    /* renamed from: -$$Nest$fputrepeatingBuilder, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22141$$Nest$fputrepeatingBuilder(RoundVideoCameraController r0, CaptureRequest.Builder r1) {
        r0.repeatingBuilder = r1;
    }

    /* renamed from: -$$Nest$fputswitchingCamera, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22142$$Nest$fputswitchingCamera(RoundVideoCameraController r0, boolean r1) {
        r0.switchingCamera = r1;
    }

    /* renamed from: -$$Nest$fputtorchVerificationPending, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22143$$Nest$fputtorchVerificationPending(RoundVideoCameraController r0, boolean r1) {
        r0.torchVerificationPending = r1;
    }

    /* renamed from: -$$Nest$mcloseCaptureSession, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22144$$Nest$mcloseCaptureSession(RoundVideoCameraController r0) {
        r0.closeCaptureSession();
    }

    /* renamed from: -$$Nest$mcreateCaptureSession, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22145$$Nest$mcreateCaptureSession(RoundVideoCameraController r0) {
        r0.createCaptureSession();
    }

    /* renamed from: -$$Nest$mcreateRepeatingRequestBuilder, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CaptureRequest.Builder m22146$$Nest$mcreateRepeatingRequestBuilder(RoundVideoCameraController r0, boolean r1) {
        return r0.createRepeatingRequestBuilder(r1);
    }

    /* renamed from: -$$Nest$mfallbackTo30Fps, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22147$$Nest$mfallbackTo30Fps(RoundVideoCameraController r0, String r1, Exception r2) {
        r0.fallbackTo30Fps(r1, r2);
    }

    /* renamed from: -$$Nest$misTorchAvailable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m22148$$Nest$misTorchAvailable(RoundVideoCameraController r0) {
        return r0.isTorchAvailable();
    }

    /* renamed from: -$$Nest$mlogPreviewFrameMetrics, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22149$$Nest$mlogPreviewFrameMetrics(RoundVideoCameraController r0, long r1, long r3) {
        r0.logPreviewFrameMetrics(r1, r3);
    }

    /* renamed from: -$$Nest$mmaybeOpenCamera, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22150$$Nest$mmaybeOpenCamera(RoundVideoCameraController r0) {
        r0.maybeOpenCamera();
    }

    /* renamed from: -$$Nest$mopenCameraIfReady, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22151$$Nest$mopenCameraIfReady(RoundVideoCameraController r0) {
        r0.openCameraIfReady();
    }

    /* renamed from: -$$Nest$mrecordCaptureSensorInterval, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22152$$Nest$mrecordCaptureSensorInterval(RoundVideoCameraController r0, long r1) {
        r0.recordCaptureSensorInterval(r1);
    }

    /* renamed from: -$$Nest$mrecordPreviewInterval, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22153$$Nest$mrecordPreviewInterval(RoundVideoCameraController r0, long r1) {
        r0.recordPreviewInterval(r1);
    }

    /* renamed from: -$$Nest$mreportError, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22154$$Nest$mreportError(RoundVideoCameraController r0, Exception r1) {
        r0.reportError(r1);
    }

    /* renamed from: -$$Nest$mresetCaptureFpsMeasurement, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22155$$Nest$mresetCaptureFpsMeasurement(RoundVideoCameraController r0) {
        r0.resetCaptureFpsMeasurement();
    }

    /* renamed from: -$$Nest$msubmitRepeatingRequest, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22156$$Nest$msubmitRepeatingRequest(RoundVideoCameraController r0) {
        r0.submitRepeatingRequest();
    }

    /* renamed from: -$$Nest$mswitchCameraIfNeeded, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22157$$Nest$mswitchCameraIfNeeded(RoundVideoCameraController r0, RoundVideoSession.CameraFacing r1) {
        r0.switchCameraIfNeeded(r1);
    }

    /* renamed from: -$$Nest$mupdatePreviewTransform, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22158$$Nest$mupdatePreviewTransform(RoundVideoCameraController r0) {
        r0.updatePreviewTransform();
    }

    /* renamed from: -$$Nest$mverifyTorchResult, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22159$$Nest$mverifyTorchResult(RoundVideoCameraController r0, CaptureRequest r1, TotalCaptureResult r2) {
        r0.verifyTorchResult(r1, r2);
    }

    /* renamed from: -$$Nest$smaverageMs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m22160$$Nest$smaverageMs(long r0, long r2) {
        return averageMs(r0, r2);
    }

    /* renamed from: -$$Nest$smcameraErrorName, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m22161$$Nest$smcameraErrorName(int r0) {
        return cameraErrorName(r0);
    }

    /* renamed from: -$$Nest$smelapsedMs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m22162$$Nest$smelapsedMs(long r0) {
        return elapsedMs(r0);
    }

    /* renamed from: -$$Nest$smnanosToMs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m22163$$Nest$smnanosToMs(long r0) {
        return nanosToMs(r0);
    }

    /* renamed from: -$$Nest$smstandardDeviationMs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m22164$$Nest$smstandardDeviationMs(long r0, double r2, long r4) {
        return standardDeviationMs(r0, r2, r4);
    }

    public RoundVideoCameraController(Context r3, TextureView r4, RoundVideoSession.OutputResolution r5, int r6, RoundVideoSession.CameraResolution r7, RoundVideoSession.FrameRate r8, boolean r9, RoundVideoDiagnostics r10, Callback r11) {
        this.zoomCropRect = new Rect();
        this.maximumZoomRatio = 1.0f;
        RoundVideoCameraController$$ExternalSyntheticLambda4 r0 = new RoundVideoCameraController$$ExternalSyntheticLambda4(this);
        this.layoutChangeListener = r0;
        this.commonStartTimeoutRunnable = new RoundVideoCameraController$$ExternalSyntheticLambda5(this);
        this.applyZoomRunnable = new RoundVideoCameraController$$ExternalSyntheticLambda6(this);
        this.previewListener = new AnonymousClass1(this);
        this.deviceCallback = new AnonymousClass2(this);
        this.sessionCallback = new AnonymousClass3(this);
        this.captureCallback = new AnonymousClass4(this);
        this.context = r3.getApplicationContext();
        this.cameraManager = (CameraManager) r3.getSystemService("camera");
        this.previewView = r4;
        this.outputResolution = r5;
        this.outputSize = r5.getSize();
        this.videoBitrate = r6;
        this.requestedCameraResolution = r7;
        this.requestedFrameRate = r8;
        this.compositionEnabled = r9;
        this.diagnostics = r10;
        this.callback = r11;
        r4.addOnLayoutChangeListener(r0);
    }

    private void announceRecordingStarted(RoundVideoCodecRecorder r4) {
        if (this.active == true) goto L5;
        return;
    L5:
        if (this.stopping == false) goto L7;
        return;
    L7:
        if (this.recorder != r4) goto L13;
        cancelCommonStartWait();
        this.diagnostics.log("common A/V start completed: segmentElapsedMs=" + elapsedMs(this.segmentStartedNs));
        this.callback.onRecordingStarted();
        return;
    }

    private void applyRepeatingRequest() {
        CameraCaptureSession r0 = this.captureSession;
        if (this.active == false) goto L25;
        if (r0 != null) goto L6;
        return;
    L6:
        if (this.cameraDevice != null) goto L23;
        return;
    L23:
        this.repeatingBuilder = createRepeatingRequestBuilder(false);     // Catch: IllegalArgumentException -> L11 CameraAccessException -> L13 IllegalStateException -> L15
        submitRepeatingRequest();     // Catch: IllegalArgumentException -> L11 CameraAccessException -> L13 IllegalStateException -> L15
        return;
    L13:
        e = e;
    L18:
        if (this.activeFrameRate != RoundVideoSession.FrameRate.FPS_60) goto L20;
        fallbackTo30Fps("60 fps updated request rejected", e);
        return;
    L20:
        reportError(e);
        return;
    L11:
        e = e;
    L15:
        e = move-exception;
        recoverFromClosedCamera(r0, e);
        return;
    }

    private void applyTorch(CaptureRequest.Builder r6) {
        int r1 = 0;
        if (this.torchEnabled == true) goto L5;
    L7:
        boolean r0 = false;
    L8:
        CameraCharacteristics r3 = this.characteristics;
        if (r3 != null) goto L11;
        int[] r32 = null;
    L13:
        if (contains(r32, 1) == false) goto L15;
        r6.set(CaptureRequest.CONTROL_AE_MODE, 1);
    L15:
        CaptureRequest.Key r2 = CaptureRequest.FLASH_MODE;
        if (r0 == false) goto L18;
        r1 = 2;
    L18:
        r6.set(r2, Integer.valueOf(r1));
        if (this.torchVerificationPending == false) goto L22;
        this.diagnostics.log("torch request configured: requested=" + this.torchEnabled + ", applied=" + r0 + ", aeMode=" + r6.get(CaptureRequest.CONTROL_AE_MODE) + ", flashMode=" + r6.get(r2) + ", cameraId=" + this.cameraId);
        return;
    L22:
        return;
    L11:
        r32 = (int[]) r3.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_MODES);
        goto L13
    L5:
        if (isTorchAvailable() == false) goto L7;
        r0 = true;
        goto L8
    }

    private void applyZoom(CaptureRequest.Builder r6) {
        float r0 = (this.zoomProgress * (this.maximumZoomRatio - 1.0f)) + 1.0f;
        Rect r3 = null;
        if (Build.VERSION.SDK_INT < 30) goto L11;
        CameraCharacteristics r1 = this.characteristics;
        if (r1 != null) goto L7;
        Range r12 = null;
    L8:
        if (r12 == null) goto L11;
        r6.set(AndroidRZoomCompat$$ExternalSyntheticApiModelOutline0.m(), Float.valueOf(clamp(r0, ((Float) r12.getLower()).floatValue(), ((Float) r12.getUpper()).floatValue())));
        return;
    L7:
        r12 = (Range) r1.get(CameraMetadataSafeGetterKt$$ExternalSyntheticApiModelOutline0.m());
    L11:
        CameraCharacteristics r13 = this.characteristics;
        if (r13 == null) goto L15;
        r3 = (Rect) r13.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
    L15:
        if (r3 != null) goto L17;
        return;
    L17:
        int r14 = Math.max(1, Math.round(r3.width() / r0));
        int r02 = Math.max(1, Math.round(r3.height() / r0));
        int r2 = r3.centerX() - (r14 / 2);
        int r32 = r3.centerY() - (r02 / 2);
        this.zoomCropRect.set(r2, r32, r14 + r2, r02 + r32);
        r6.set(CaptureRequest.SCALER_CROP_REGION, this.zoomCropRect);
    }

    private static long area(Size r4) {
        return r4.getWidth() * r4.getHeight();
    }

    private static float averageMs(long r2, long r4) {
        if (r4 != 0) goto L7;
        return 0.0f;
    L7:
        return (r2 / r4) / 1000000.0f;
    }

    private static String cameraErrorName(int r1) {
        if (r1 != 1) goto L5;
        return "CAMERA_IN_USE";
    L5:
        if (r1 != 2) goto L7;
        return "MAX_CAMERAS_IN_USE";
    L7:
        if (r1 != 3) goto L9;
        return "CAMERA_DISABLED";
    L9:
        if (r1 != 4) goto L11;
        return "CAMERA_DEVICE";
    L11:
        if (r1 == 5) goto L14;
        return "UNKNOWN";
    L14:
        return "CAMERA_SERVICE";
    }

    private void cancelCommonStartWait() {
        Handler r0 = this.cameraHandler;
        if (r0 == null) goto L5;
        r0.removeCallbacks(this.commonStartTimeoutRunnable);
    L5:
        this.commonStartRecorder = null;
    }

    private static Size chooseFallbackRecordingSize(Size[] r10, int r11) {
        int r0 = r10.length;
        int r2 = 0;
        Size r4 = null;
        int r3 = 0;
    L3:
        if (r3 >= r0) goto L14;
        Size r5 = r10[r3];
        if (isWithinAbsoluteLimit(r5) == false) goto L13;
        if (shortSide(r5) < r11) goto L13;
        if (r4 != null) goto L11;
    L12:
        r4 = r5;
        goto L13
    L11:
        if (area(r5) < area(r4)) goto L12;
    L13:
        r3 = r3 + 1;
        goto L3
    L14:
        if (r4 == null) goto L16;
        return r4;
    L16:
        int r112 = r10.length;
    L17:
        if (r2 >= r112) goto L30;
        Size r02 = r10[r2];
        if (isWithinAbsoluteLimit(r02) == false) goto L29;
        if (r4 != null) goto L23;
    L28:
        r4 = r02;
        goto L29
    L23:
        if (shortSide(r02) > shortSide(r4)) goto L28;
        if (shortSide(r02) != shortSide(r4)) goto L29;
        if (area(r02) < area(r4)) goto L28;
    L29:
        r2 = r2 + 1;
        goto L17
    L30:
        if (r4 == null) goto L32;
        return r4;
    L32:
        Segment$$ExternalSyntheticBUOutline1.m("Camera has no output at or below the bandwidth cap");
        return null;
    }

    private static OutputPair chooseOutputPair(Size[] r2, RoundVideoSession.OutputResolution r3, RoundVideoSession.CameraResolution r4) {
        OutputPair r0 = chooseTierPair(r2, getSourceCropSize(r3, r4), r4);
        if (r0 == null) goto L6;
        return r0;
    L6:
        if (r3 != RoundVideoSession.OutputResolution.P360) goto L12;
        RoundVideoSession.CameraResolution r02 = RoundVideoSession.CameraResolution.MEDIUM;
        if (r4 != r02) goto L12;
        OutputPair r03 = chooseTierPair(r2, 480, r02);
        if (r03 == null) goto L12;
        return r03;
    L12:
        int r04 = r3.getSize();
        RoundVideoSession.CameraResolution r1 = RoundVideoSession.CameraResolution.LOW;
        if (r4 == r1) goto L17;
        OutputPair r42 = chooseTierPair(r2, r04, r1);
        if (r42 == null) goto L17;
        return r42;
    L17:
        Size r32 = chooseFallbackRecordingSize(r2, r3.getSize());
        int r43 = Math.min(shortSide(r32), 1088);
        return new OutputPair(choosePreviewSize(r2, r32), r32, r1, r43);
    }

    private static Size choosePreviewSize(Size[] r5, Size r6) {
        int r0 = r5.length;
        int r1 = 0;
        Size r2 = r6;
    L3:
        if (r1 >= r0) goto L10;
        Size r3 = r5[r1];
        if (hasSameAspectRatio(r3, r6) == false) goto L9;
        if (comparePreviewSizes(r3, r2) >= 0) goto L9;
        r2 = r3;
    L9:
        r1 = r1 + 1;
        goto L3
    L10:
        return r2;
    }

    private static OutputPair chooseTierPair(Size[] r6, int r7, RoundVideoSession.CameraResolution r8) {
        int r0 = r6.length;
        OutputPair r1 = null;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L13;
        Size r3 = r6[r2];
        if (isValidTierSize(r3, r7) == false) goto L12;
        OutputPair r5 = new OutputPair(choosePreviewSize(r6, r3), r3, r8, r7);
        if (r1 != null) goto L10;
    L11:
        r1 = r5;
        goto L12
    L10:
        if (compareOutputPairs(r5, r1) < 0) goto L11;
    L12:
        r2 = r2 + 1;
        goto L3
    L13:
        return r1;
    }

    private static float clamp(float r0, float r1, float r2) {
        return Math.max(r1, Math.min(r2, r0));
    }

    private void closeCameraAndSurfaces() {
        cancelCommonStartWait();
        this.opening = false;
        this.switchingCamera = false;
        this.reopenAfterCameraClose = false;
        this.previewTransformEnabled = false;
        this.notifySwitchCompletion = false;
        this.awaitingSwitchPreviewFrame = false;
        this.repeatingBuilder = null;
        this.torchVerificationPending = false;
        closeCaptureSession();
        CameraDevice r0 = this.cameraDevice;
        if (r0 == null) goto L5;
        r0.close();
        this.cameraDevice = null;
    L5:
        Surface r02 = this.previewSurface;
        if (r02 == null) goto L8;
        r02.release();
        this.previewSurface = null;
    L8:
        RoundVideoGlProcessor r03 = this.glProcessor;
        if (r03 == null) goto L11;
        r03.stop();
        this.glProcessor = null;
    L11:
        this.recordingSurface = null;
    }

    private void closeCaptureSession() {
        CameraCaptureSession r0 = this.captureSession;
        if (r0 == null) goto L5;
        r0.close();
        this.captureSession = null;
    L5:
        this.repeatingBuilder = null;
    }

    private static int compareOutputPairs(OutputPair r6, OutputPair r7) {
        int r0 = Math.abs(shortSide(r6.preview) - 720);
        int r1 = Math.abs(shortSide(r7.preview) - 720);
        if (r0 != r1) goto L5;
        long r02 = area(r6.preview) + area(r6.recording);
        long r2 = area(r7.preview) + area(r7.recording);
        if (r02 == r2) goto L11;
        return Long.compare(r02, r2);
    L11:
        return Long.compare(area(r6.recording), area(r7.recording));
    L5:
        return Integer.compare(r0, r1);
    }

    private static int comparePreviewSizes(Size r2, Size r3) {
        int r0 = Math.abs(shortSide(r2) - 720);
        int r1 = Math.abs(shortSide(r3) - 720);
        if (r0 == r1) goto L7;
        return Integer.compare(r0, r1);
    L7:
        return Long.compare(area(r2), area(r3));
    }

    private static boolean contains(int[] r4, int r5) {
        if (r4 != null) goto L5;
        return false;
    L5:
        int r1 = r4.length;
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L12;
        if (r4[r2] == r5) goto L9;
        r2 = r2 + 1;
        goto L6
    L9:
        return true;
    L12:
        return false;
    }

    private void createCaptureSession() {
        CameraDevice r1 = this.cameraDevice;
        if (r1 != null) goto L5;
        return;
    L5:
        if (this.previewSurface != null) goto L7;
        return;
    L7:
        if (this.recordingSurface == null) goto L24;
        this.captureSessionRequestedNs = SystemClock.elapsedRealtimeNanos();     // Catch: IllegalArgumentException -> L11 Throwable -> L13
        this.diagnostics.log("capture session requested: preview=" + this.previewSize + ", recording=" + this.recordingSize + ", fpsRange=" + this.activeFpsRange);     // Catch: IllegalArgumentException -> L11 Throwable -> L13
        r1.createCaptureSession(Arrays.asList(new Surface[]{this.previewSurface, this.recordingSurface}), this.sessionCallback, this.cameraHandler);     // Catch: IllegalArgumentException -> L11 Throwable -> L13
        return;
    L13:
        e = move-exception;
        if (this.activeFrameRate != RoundVideoSession.FrameRate.FPS_60) goto L17;
        fallbackTo30Fps("60 fps session creation rejected", e);
        return;
    L17:
        reportError(e);
        return;
    }

    private void createRecordingPipeline() {
        RoundVideoMp4Writer r1 = this.writer;
        long r2 = this.timelineOffsetUs;
        int r4 = this.outputSize;
        int r5 = this.videoBitrate;
        int r6 = this.activeFrameRate.getValue();
        RoundVideoDiagnostics r7 = this.diagnostics;
        Callback r8 = this.callback;
        Objects.requireNonNull(r8);
        RoundVideoCodecRecorder r0 = new RoundVideoCodecRecorder(r1, r2, r4, r5, r6, r7, new RoundVideoCameraController$$ExternalSyntheticLambda13(r8));
        this.recorder = r0;
        Surface r3 = r0.prepare();
        Size r22 = this.recordingSize;
        int r42 = this.outputSize;
        int r52 = this.sourceCropSize;
        boolean r62 = isCameraTimestampRealtime();
        boolean r72 = this.compositionEnabled;
        RoundVideoDiagnostics r82 = this.diagnostics;
        RoundVideoCodecRecorder r11 = this.recorder;
        Callback r02 = this.callback;
        Objects.requireNonNull(r02);
        RoundVideoGlProcessor r12 = new RoundVideoGlProcessor(r22, r3, r42, r52, r62, r72, r82, 0, r11, new RoundVideoCameraController$$ExternalSyntheticLambda14(r02));
        this.glProcessor = r12;
        r12.setFirstFrameListener(new RoundVideoCameraController$$ExternalSyntheticLambda15(this));
        this.recordingSurface = this.glProcessor.start();
    }

    private CaptureRequest.Builder createRepeatingRequestBuilder(boolean r6) {
        CameraDevice r0 = this.cameraDevice;
        if (r0 != null) goto L5;
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("Camera request surfaces are unavailable");
        return null;
    L5:
        if (this.previewSurface == null) goto L12;
        if (this.recordingSurface == null) goto L12;
        CaptureRequest.Builder r02 = r0.createCaptureRequest(3);
        r02.addTarget(this.previewSurface);
        r02.addTarget(this.recordingSurface);
        r02.set(CaptureRequest.CONTROL_MODE, 1);
        r02.set(CaptureRequest.CONTROL_CAPTURE_INTENT, 3);
        setContinuousVideoAf(r02);
        setPreferredFps(r02, r6);
        applyZoom(r02);
        applyTorch(r02);
        int r62 = this.requestGeneration + 1;
        this.requestGeneration = r62;
        r02.setTag(Integer.valueOf(r62));
        if (this.torchVerificationPending == false) goto L11;
        this.torchVerificationGeneration = this.requestGeneration;
    L11:
        return r02;
    }

    private static long elapsedMs(long r2) {
        if (r2 != 0) goto L7;
        return -1;
    L7:
        return (SystemClock.elapsedRealtimeNanos() - r2) / 1000000;
    }

    private void ensureThread() {
        if (this.cameraThread == null) goto L5;
        return;
    L5:
        HandlerThread r0 = new HandlerThread("RoundVideoCamera2");
        this.cameraThread = r0;
        r0.start();
        this.cameraHandler = new Handler(this.cameraThread.getLooper());
    }

    private void fallbackTo30Fps(String r5, Exception r6) {
        if (this.frameRateFallbackAttempted == false) goto L9;
        if (r6 != null) goto L7;
        r6 = new IllegalStateException("30 fps fallback session failed");
    L7:
        reportError(r6);
        return;
    L9:
        this.frameRateFallbackAttempted = true;
        closeCaptureSession();
        OutputPair r0 = this.regularFallbackPair;
        if (r0 != null) goto L13;
        reportError(new IllegalStateException("Regular camera fallback is unavailable", r6));
        return;
    L13:
        this.activeFrameRate = RoundVideoSession.FrameRate.FPS_30;
        CameraCharacteristics r1 = this.characteristics;
        if (r1 != null) goto L16;
        Range[] r12 = null;
    L17:
        this.activeFpsRange = findBestFpsRange(r12, 30);
        this.previewSize = r0.preview;
        this.recordingSize = r0.recording;
        this.sourceCropSize = r0.cropSize;
        this.activeCameraResolution = r0.cameraResolution;
        SurfaceTexture r02 = this.previewView.getSurfaceTexture();
        if (r02 == null) goto L20;
        r02.setDefaultBufferSize(this.previewSize.getWidth(), this.previewSize.getHeight());
    L20:
        RoundVideoCodecRecorder r03 = this.recorder;
        if (r03 != null) goto L23;
    L26:
        RoundVideoGlProcessor r04 = this.glProcessor;
        if (r04 == null) goto L29;
        r04.stop();
        this.glProcessor = null;
    L29:
        RoundVideoCodecRecorder r05 = this.recorder;
        if (r05 == null) goto L32;
        r05.stop();
        this.recorder = null;
    L32:
        this.recordingSurface = null;
        createRecordingPipeline();     // Catch: Exception -> L43
        resetCaptureFpsMeasurement();
        RoundVideoDiagnostics r06 = this.diagnostics;
        StringBuilder r13 = new StringBuilder("60 fps fallback: reason=");
        r13.append(r5);
        if (r6 != null) goto L37;
        String r52 = _UrlKt.FRAGMENT_ENCODE_SET;
    L38:
        r13.append(r52);
        r13.append(", preview=");
        r13.append(this.previewSize);
        r13.append(", recording=");
        r13.append(this.recordingSize);
        r13.append(", crop=");
        r13.append(this.sourceCropSize);
        r13.append(", fpsRange=");
        r13.append(this.activeFpsRange);
        r06.log(r13.toString());
        Handler r53 = this.cameraHandler;
        if (this.active == false) goto L48;
        if (r53 == null) goto L49;
        r53.post(new RoundVideoCameraController$$ExternalSyntheticLambda12(this));
        return;
    L49:
        return;
    L48:
        return;
    L37:
        r52 = ", error=" + r6;
    L43:
        e = move-exception;
        reportError(e);
        return;
    L23:
        if (r03.isStarted() == false) goto L26;
        reportError(new IllegalStateException("Unable to change encoder frame rate after recording started", r6));
        return;
    L16:
        r12 = (Range[]) r1.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES);
        goto L17
    }

    private static Size[] filterSizesForFrameRate(StreamConfigurationMap r6, Size[] r7, int r8) {
        ArrayList r0 = new ArrayList(r7.length);
        int r1 = r7.length;
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L9;
        Size r4 = r7[r3];
        if (supportsFrameDuration(r6, r4, r8) == false) goto L7;
        r0.add(r4);
    L7:
        r3 = r3 + 1;
        goto L3
    L9:
        return (Size[]) r0.toArray(new Size[0]);
    }

    private static Range<Integer> findBestFpsRange(Range<Integer>[] r6, int r7) {
        Range<Integer> r0 = null;
        if (r6 != null) goto L5;
        return null;
    L5:
        int r1 = r6.length;
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L19;
        Range<Integer> r3 = r6[r2];
        if (r3.contains(Integer.valueOf(r7)) == false) goto L18;
        if (r0 != null) goto L12;
    L17:
        r0 = r3;
        goto L18
    L12:
        if (((Integer) r3.getLower()).intValue() > ((Integer) r0.getLower()).intValue()) goto L17;
        if (((Integer) r3.getLower()).equals(r0.getLower()) == false) goto L18;
        if (((Integer) r3.getUpper()).intValue() < ((Integer) r0.getUpper()).intValue()) goto L17;
    L18:
        r2 = r2 + 1;
        goto L6
    L19:
        return r0;
    }

    private static long getMinimumFrameDurationNs(CameraCharacteristics r3, Size r4) {
        StreamConfigurationMap r32 = (StreamConfigurationMap) r3.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        if (r32 != null) goto L8;
        return -1;
    L8:
        return r32.getOutputMinFrameDuration(SurfaceTexture.class, r4);
    L7:
        return -1;
    }

    private static int getSourceCropSize(RoundVideoSession.OutputResolution r2, RoundVideoSession.CameraResolution r3) {
        if (r3 != RoundVideoSession.CameraResolution.LOW) goto L7;
        return r2.getSize();
    L7:
        if (r2 != RoundVideoSession.OutputResolution.P480) goto L14;
        if (r3 != RoundVideoSession.CameraResolution.HIGH) goto L12;
        return 960;
    L12:
        return 720;
    L14:
        if (r3 != RoundVideoSession.CameraResolution.HIGH) goto L16;
        return 720;
    L16:
        return 540;
    }

    private static String hardwareLevelName(Integer r2) {
        if (r2 != null) goto L6;
        return "unknown";
    L6:
        if (r2.intValue() != 2) goto L10;
        return "LEGACY";
    L10:
        if (r2.intValue() != 0) goto L14;
        return "LIMITED";
    L14:
        if (r2.intValue() != 1) goto L18;
        return "FULL";
    L18:
        if (r2.intValue() != 3) goto L22;
        return "LEVEL_3";
    L22:
        if (Build.VERSION.SDK_INT < 28) goto L28;
        if (r2.intValue() != 4) goto L28;
        return "EXTERNAL";
    L28:
        return String.valueOf(r2);
    }

    private static boolean hasFlashUnit(CameraCharacteristics r2) {
        return Boolean.TRUE.equals(r2.get(CameraCharacteristics.FLASH_INFO_AVAILABLE));
    }

    private static boolean hasSameAspectRatio(Size r4, Size r5) {
        if ((r4.getWidth() * r5.getHeight()) != (r4.getHeight() * r5.getWidth())) goto L6;
        return true;
    L6:
        return false;
    }

    private boolean isCameraTimestampRealtime() {
        CameraCharacteristics r2 = this.characteristics;
        if (r2 != null) goto L5;
        return false;
    L5:
        Integer r22 = (Integer) r2.get(CameraCharacteristics.SENSOR_INFO_TIMESTAMP_SOURCE);
        if (r22 != null) goto L8;
    L10:
        return false;
    L8:
        if (r22.intValue() != 1) goto L10;
        return true;
    }

    private boolean isOutputRotationSwapped() {
        if (this.characteristics != null) goto L5;
    L18:
        return false;
    L5:
        if (this.previewView.getDisplay() == null) goto L18;
        Integer r0 = (Integer) this.characteristics.get(CameraCharacteristics.SENSOR_ORIENTATION);
        if (r0 != null) goto L10;
        return false;
    L10:
        int r3 = this.previewView.getDisplay().getRotation() * 90;
        int r02 = ((r0.intValue() - r3) + 360) % 360;
        if (r02 != 90) goto L13;
        return true;
    L13:
        if (r02 == 270) goto L19;
        return false;
    L19:
        return true;
    }

    private static boolean isPreferredCameraCandidate(CameraCandidate r1, CameraCandidate r2, RoundVideoSession.CameraFacing r3) {
        if (r3 == RoundVideoSession.CameraFacing.BACK) goto L5;
        return false;
    L5:
        if (hasFlashUnit(r1.characteristics) == true) goto L7;
        return false;
    L7:
        if (hasFlashUnit(r2.characteristics) == true) goto L13;
        return true;
    L13:
        return false;
    }

    private boolean isTorchAvailable() {
        if (this.activeFacing != RoundVideoSession.CameraFacing.BACK) goto L10;
        CameraCharacteristics r2 = this.characteristics;
        if (r2 != null) goto L7;
        return false;
    L7:
        if (hasFlashUnit(r2) == false) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    private static boolean isValidTierSize(Size r4, int r5) {
        int r0 = ((r5 * 15) / 100) + r5;
        int r1 = Math.min(1920, r5 * 2);
        int r2 = shortSide(r4);
        int r42 = Math.max(r4.getWidth(), r4.getHeight());
        if (r2 < r5) goto L8;
        if (r2 > r0) goto L10;
        if (r42 > r1) goto L11;
        return true;
    L11:
        return false;
    L10:
        return false;
    L8:
        return false;
    }

    private static boolean isWithinAbsoluteLimit(Size r2) {
        if (shortSide(r2) <= 1088) goto L5;
        return false;
    L5:
        if (Math.max(r2.getWidth(), r2.getHeight()) > 1920) goto L10;
        return true;
    L10:
        return false;
    }

    private /* synthetic */ void lambda$createRecordingPipeline$6() {
        Handler r0 = this.cameraHandler;
        if (r0 == null) goto L6;
        r0.post(new RoundVideoCameraController$$ExternalSyntheticLambda16(this));
        return;
    }

    private /* synthetic */ void lambda$new$0(View r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9) {
        updatePreviewTransform();
    }

    private /* synthetic */ void lambda$new$1() {
        RoundVideoCodecRecorder r0 = this.commonStartRecorder;
        if (this.active == true) goto L5;
        return;
    L5:
        if (this.stopping == true) goto L13;
        if (r0 != null) goto L8;
        return;
    L8:
        if (this.recorder != r0) goto L15;
        this.commonStartRecorder = null;
        reportError(new IllegalStateException("Timed out waiting for synchronized audio and video start"));
        return;
    L15:
        return;
    }

    private /* synthetic */ void lambda$release$5(HandlerThread r6) {
        cancelCommonStartWait();
        RoundVideoCodecRecorder r0 = this.recorder;
        if (r0 == null) goto L9;
        long r02 = r0.requestStop();
        RoundVideoGlProcessor r2 = this.glProcessor;
        if (r2 == null) goto L9;
        if (r02 == LongCompanionObject.MAX_VALUE) goto L9;
        r2.stopRecordingAt(r02);
    L9:
        closeCameraAndSurfaces();
        RoundVideoCodecRecorder r03 = this.recorder;
        if (r03 == null) goto L12;
        r03.stop();
        this.recorder = null;
    L12:
        r6.quitSafely();
    }

    private /* synthetic */ void lambda$setCameraFacing$2(RoundVideoSession.CameraFacing r1) {
        switchCameraIfNeeded(r1);
    }

    private /* synthetic */ void lambda$setTorchEnabled$3(boolean r4) {
        this.torchEnabled = r4;
        this.torchVerificationPending = isTorchAvailable();
        this.torchVerificationFrames = 0;
        this.torchVerificationRetries = 0;
        this.diagnostics.log("torch requested: enabled=" + r4 + ", available=" + isTorchAvailable() + ", cameraId=" + this.cameraId + ", facing=" + this.activeFacing);
        applyRepeatingRequest();
    }

    private /* synthetic */ void lambda$startRecordingAfterFirstFrame$10(RoundVideoCodecRecorder r2) {
        postToCameraThread(new RoundVideoCameraController$$ExternalSyntheticLambda3(this, r2));
    }

    private /* synthetic */ void lambda$startRecordingAfterFirstFrame$7(RoundVideoCodecRecorder r1) {
        startVideoAfterAudioReady(r1);
    }

    private /* synthetic */ void lambda$startRecordingAfterFirstFrame$8(RoundVideoCodecRecorder r2) {
        postToCameraThread(new RoundVideoCameraController$$ExternalSyntheticLambda2(this, r2));
    }

    private /* synthetic */ void lambda$startRecordingAfterFirstFrame$9(RoundVideoCodecRecorder r1) {
        announceRecordingStarted(r1);
    }

    private /* synthetic */ void lambda$stopSegment$4() {
        this.active = false;
        cancelCommonStartWait();
        RoundVideoCodecRecorder r1 = this.recorder;
        if (r1 == null) goto L9;
        long r12 = r1.requestStop();
        RoundVideoGlProcessor r3 = this.glProcessor;
        if (r3 == null) goto L9;
        if (r12 == LongCompanionObject.MAX_VALUE) goto L9;
        r3.stopRecordingAt(r12);
    L9:
        closeCameraAndSurfaces();
        RoundVideoCodecRecorder r13 = this.recorder;
        if (r13 == null) goto L12;
        r13.stop();
        this.recorder = null;
    L12:
        this.stopping = false;
        this.callback.onRecordingStopped();
    }

    private void logCameraConfigurationOnce(RoundVideoSession.CameraFacing r8, String r9, CameraCharacteristics r10, Size[] r11) {
        if (r8 != RoundVideoSession.CameraFacing.FRONT) goto L9;
        if (this.frontConfigurationLogged == true) goto L14;
        this.frontConfigurationLogged = true;
    L12:
        Integer r0 = (Integer) r10.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
        Integer r1 = (Integer) r10.get(CameraCharacteristics.SENSOR_ORIENTATION);
        Range[] r2 = (Range[]) r10.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES);
        int[] r3 = (int[]) r10.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_MODES);
        Boolean r4 = (Boolean) r10.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
        Rect r102 = (Rect) r10.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
        this.diagnostics.log("camera capabilities: id=" + r9 + ", facing=" + r8 + ", hardwareLevel=" + hardwareLevelName(r0) + ", sensorOrientation=" + r1 + ", activeArray=" + r102 + ", flashAvailable=" + r4 + ", aeModes=" + Arrays.toString(r3) + ", fpsRanges=" + Arrays.toString(r2) + ", outputSizes=" + Arrays.toString(r11));
        return;
    L14:
        return;
    L9:
        if (this.backConfigurationLogged == false) goto L11;
        return;
    L11:
        this.backConfigurationLogged = true;
        goto L12
    }

    private void logPreviewFrameMetrics(long r9, long r11) {
        long r0 = this.previewTimestampLastNs - this.previewTimestampFirstNs;
        if (r0 > 0) goto L5;
    L7:
        float r2 = 0.0f;
    L8:
        this.diagnostics.log("preview frame delivery: callbackFps=" + ((this.previewFrames * 1.0E9f) / r11) + ", timestampFps=" + r2 + ", callbackIntervalMs={avg=" + averageMs(this.previewIntervalTotalNs, this.previewIntervalCount) + ", min=" + nanosToMs(this.previewIntervalMinimumNs) + ", max=" + nanosToMs(this.previewIntervalMaximumNs) + ", jitter=" + standardDeviationMs(this.previewIntervalTotalNs, this.previewIntervalSquaredTotalNs, this.previewIntervalCount) + "}, gaps={over50ms=" + this.previewGapsOver50Ms + ", over100ms=" + this.previewGapsOver100Ms + "}, nonMonotonicTimestamps=" + this.previewTimestampNonMonotonic + ", viewState={shown=" + this.previewView.isShown() + ", alpha=" + this.previewView.getAlpha() + ", windowVisibility=" + this.previewView.getWindowVisibility() + "}");
        resetPreviewFrameMetrics();
        this.previewFpsStartedNs = r9;
        return;
    L5:
        if (this.previewTimestampFrames <= 1) goto L7;
        r2 = ((r4 - 1) * 1.0E9f) / r0;
        goto L8
    }

    private void maybeOpenCamera() {
        Handler r0 = this.cameraHandler;
        if (this.active == false) goto L10;
        if (r0 != null) goto L6;
        return;
    L6:
        if (this.previewView.isAvailable() == false) goto L12;
        r0.post(new RoundVideoCameraController$$ExternalSyntheticLambda7(this));
        return;
    L12:
        return;
    }

    private static float nanosToMs(long r0) {
        return r0 / 1000000.0f;
    }

    @SuppressLint({"MissingPermission"})
    private void openCameraIfReady() {
        if (this.active == true) goto L5;
        return;
    L5:
        if (this.opening == false) goto L7;
        return;
    L7:
        if (this.cameraDevice == null) goto L10;
        return;
    L10:
        if (ContextCompat.checkSelfPermission(this.context, "android.permission.CAMERA") == 0) goto L14;
        reportError(new SecurityException("Camera permission is not granted"));
        return;
    L14:
        if (ContextCompat.checkSelfPermission(this.context, "android.permission.RECORD_AUDIO") == 0) goto L35;
        reportError(new SecurityException("Audio recording permission is not granted"));
        return;
    L35:
        selectCameraAndSizes(this.requestedFacing);     // Catch: Exception -> L23
        SurfaceTexture r1 = this.previewView.getSurfaceTexture();     // Catch: Exception -> L23
        if (r1 == null) goto L39;
        r1.setDefaultBufferSize(this.previewSize.getWidth(), this.previewSize.getHeight());     // Catch: Exception -> L23
        if (this.previewSurface != null) goto L26;
        this.previewSurface = new Surface(r1);     // Catch: Exception -> L23
    L26:
        if (this.recorder != null) goto L28;
        createRecordingPipeline();     // Catch: Exception -> L23
    L31:
        this.opening = true;     // Catch: Exception -> L23
        this.cameraOpenRequestedNs = SystemClock.elapsedRealtimeNanos();     // Catch: Exception -> L23
        this.diagnostics.log("camera open requested: id=" + this.cameraId + ", preview=" + this.previewSize + ", recording=" + this.recordingSize + ", crop=" + this.sourceCropSize);     // Catch: Exception -> L23
        this.cameraManager.openCamera(this.cameraId, this.deviceCallback, this.cameraHandler);     // Catch: Exception -> L23
        return;
    L28:
        RoundVideoGlProcessor r12 = this.glProcessor;     // Catch: Exception -> L23
        if (r12 == null) goto L31;
        r12.updateInputConfiguration(this.recordingSize, this.sourceCropSize, isCameraTimestampRealtime());     // Catch: Exception -> L23
        this.recordingSurface = this.glProcessor.getCameraSurface();     // Catch: Exception -> L23
        goto L31
    L39:
        return;
    L23:
        e = move-exception;
        this.opening = false;
        reportError(e);
    }

    private void postToCameraThread(Runnable r1) {
        Handler r0 = this.cameraHandler;
        if (r0 == null) goto L6;
        r0.post(r1);
        return;
    }

    private float readMaximumZoomRatio(CameraCharacteristics r3) {
        if (Build.VERSION.SDK_INT < 30) goto L7;
        Range r2 = (Range) r3.get(CameraMetadataSafeGetterKt$$ExternalSyntheticApiModelOutline0.m());
        if (r2 == null) goto L10;
        float r22 = ((Float) r2.getUpper()).floatValue();
    L12:
        return clamp(r22, 1.0f, 4.0f);
    L10:
        r22 = 1.0f;
        goto L12
    L7:
        Float r23 = (Float) r3.get(CameraCharacteristics.SCALER_AVAILABLE_MAX_DIGITAL_ZOOM);
        if (r23 == null) goto L10;
        r22 = r23.floatValue();
        goto L12
    }

    private void recordCaptureSensorInterval(long r7) {
        this.captureSensorIntervalCount++;
        this.captureSensorIntervalTotalNs += r7;
        double r4 = r7;
        this.captureSensorIntervalSquaredTotalNs += r4 * r4;
        long r0 = this.captureSensorIntervalMinimumNs;
        if (r0 != 0) goto L5;
    L6:
        this.captureSensorIntervalMinimumNs = r7;
    L7:
        this.captureSensorIntervalMaximumNs = Math.max(this.captureSensorIntervalMaximumNs, r7);
        if (r7 <= 50000000) goto L11;
        this.captureSensorGapsOver50Ms++;
    L11:
        if (r7 <= 100000000) goto L14;
        this.captureSensorGapsOver100Ms++;
        return;
    L14:
        return;
    L5:
        if (r7 >= r0) goto L7;
        goto L6
    }

    private void recordPreviewInterval(long r7) {
        this.previewIntervalCount++;
        this.previewIntervalTotalNs += r7;
        double r4 = r7;
        this.previewIntervalSquaredTotalNs += r4 * r4;
        long r0 = this.previewIntervalMinimumNs;
        if (r0 != 0) goto L5;
    L6:
        this.previewIntervalMinimumNs = r7;
    L7:
        this.previewIntervalMaximumNs = Math.max(this.previewIntervalMaximumNs, r7);
        if (r7 <= 50000000) goto L11;
        this.previewGapsOver50Ms++;
    L11:
        if (r7 <= 100000000) goto L14;
        this.previewGapsOver100Ms++;
        return;
    L14:
        return;
    L5:
        if (r7 >= r0) goto L7;
        goto L6
    }

    private void recoverFromClosedCamera(CameraCaptureSession r3, IllegalStateException r4) {
        if (this.active == true) goto L5;
    L15:
        this.diagnostics.log("stale repeating request rejection ignored: " + r4.getMessage());
        return;
    L5:
        if (this.stopping == true) goto L15;
        if (r3 != this.captureSession) goto L15;
        this.diagnostics.log("repeating request rejected by closed camera; reopening device: " + r4.getMessage());
        closeCaptureSession();
        CameraDevice r32 = this.cameraDevice;
        this.cameraDevice = null;
        this.opening = false;
        if (r32 == null) goto L13;
        this.reopenAfterCameraClose = true;
        r32.close();
        return;
    L13:
        openCameraIfReady();
    }

    private void reportError(Exception r3) {
        this.diagnostics.error("camera error", r3);
        this.callback.onError(r3);
    }

    private void resetCaptureFpsMeasurement() {
        this.captureFpsFirstTimestampNs = 0;
        this.captureFpsLastTimestampNs = 0;
        this.captureFrames = 0;
        this.captureLastCallbackNs = 0;
        this.captureSensorIntervalCount = 0;
        this.captureSensorIntervalTotalNs = 0;
        this.captureSensorIntervalSquaredTotalNs = 0.0d;
        this.captureSensorIntervalMinimumNs = 0;
        this.captureSensorIntervalMaximumNs = 0;
        this.captureSensorGapsOver50Ms = 0;
        this.captureSensorGapsOver100Ms = 0;
        this.captureCallbackIntervalCount = 0;
        this.captureCallbackIntervalTotalNs = 0;
        this.captureCallbackIntervalMaximumNs = 0;
    }

    private void resetPreviewFrameMetrics() {
        this.previewFpsStartedNs = 0;
        this.previewFrames = 0;
        this.previewLastCallbackNs = 0;
        this.previewLastTimestampNs = 0;
        this.previewIntervalCount = 0;
        this.previewIntervalTotalNs = 0;
        this.previewIntervalSquaredTotalNs = 0.0d;
        this.previewIntervalMinimumNs = 0;
        this.previewIntervalMaximumNs = 0;
        this.previewGapsOver50Ms = 0;
        this.previewGapsOver100Ms = 0;
        this.previewTimestampFrames = 0;
        this.previewTimestampFirstNs = 0;
        this.previewTimestampLastNs = 0;
        this.previewTimestampNonMonotonic = 0;
    }

    private FrameRatePlan resolveFrameRate(String r10, CameraCharacteristics r11, StreamConfigurationMap r12, Size[] r13, OutputPair r14) {
        Range[] r112 = (Range[]) r11.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES);
        RoundVideoSession.FrameRate r0 = this.requestedFrameRate;
        RoundVideoSession.FrameRate r1 = RoundVideoSession.FrameRate.FPS_30;
        if (r0 != r1) goto L5;
    L16:
        Range<Integer> r113 = findBestFpsRange(r112, 30);
        RoundVideoDiagnostics r122 = this.diagnostics;
        StringBuilder r132 = new StringBuilder("fps selection: id=");
        r132.append(r10);
        r132.append(", requested=");
        r132.append(this.requestedFrameRate.getValue());
        r132.append(", mode=REGULAR, range=");
        r132.append(r113);
        if (this.force30Fps == false) goto L19;
        String r9 = ", reason=session-wide fallback";
    L20:
        r132.append(r9);
        r122.log(r132.toString());
        return new FrameRatePlan(r1, r113, r14);
    L19:
        r9 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L20
    L5:
        if (this.force30Fps == true) goto L16;
        int r02 = r0.getValue();
        Range<Integer> r15 = findBestFpsRange(r112, r02);
        if (r15 == null) goto L13;
        Size[] r123 = filterSizesForFrameRate(r12, r13, r02);
        OutputPair r133 = chooseOutputPair(r123, this.outputResolution, this.requestedCameraResolution);     // Catch: RuntimeException -> L12
        this.diagnostics.log("fps selection: id=" + r10 + ", requested=" + r02 + ", mode=REGULAR, range=" + r15 + ", compatibleSizes=" + Arrays.toString(r123));     // Catch: RuntimeException -> L12
        return new FrameRatePlan(RoundVideoSession.FrameRate.FPS_60, r15, r133);
    L12:
        this.diagnostics.log("fps selection: id=" + r10 + ", regular " + r02 + " fps rejected: no compatible output pair, compatibleSizes=" + Arrays.toString(r123));
    L14:
        Range<Integer> r114 = findBestFpsRange(r112, 30);
        this.diagnostics.log("fps selection: id=" + r10 + ", requested=" + r02 + ", fallback=30, range=" + r114);
        return new FrameRatePlan(RoundVideoSession.FrameRate.FPS_30, r114, r14);
    L13:
        this.diagnostics.log("fps selection: id=" + r10 + ", regular " + r02 + " fps rejected: advertisedRanges=" + Arrays.toString(r112));
        goto L14
    }

    private void resolveSessionFrameRatePolicy() {
        if (this.frameRatePolicyResolved == true) goto L19;
        boolean r0 = true;
        this.frameRatePolicyResolved = true;
        if (this.requestedFrameRate == RoundVideoSession.FrameRate.FPS_60) goto L8;
        return;
    L8:
        boolean r1 = supports60FpsForFacing(RoundVideoSession.CameraFacing.FRONT);
        boolean r2 = supports60FpsForFacing(RoundVideoSession.CameraFacing.BACK);
        if (r1 == false) goto L13;
        if (r2 == false) goto L13;
        r0 = false;
    L13:
        this.force30Fps = r0;
        RoundVideoDiagnostics r02 = this.diagnostics;
        StringBuilder r3 = new StringBuilder("60 fps session capability: front=");
        r3.append(r1);
        r3.append(", back=");
        r3.append(r2);
        r3.append(", selected=");
        if (this.force30Fps == false) goto L16;
        int r5 = 30;
    L17:
        r3.append(r5);
        r02.log(r3.toString());
        return;
    L16:
        r5 = 60;
        goto L17
    }

    private void selectCameraAndSizes(RoundVideoSession.CameraFacing r24) {
        resolveSessionFrameRatePolicy();
        if (r24 != RoundVideoSession.CameraFacing.FRONT) goto L5;
        int r9 = 0;
    L6:
        String[] r10 = this.cameraManager.getCameraIdList();
        int r11 = r10.length;
        CameraCandidate r12 = null;
        CameraCandidate r13 = null;
        int r14 = 0;
    L8:
        if (r14 >= r11) goto L57;
        String r2 = r10[r14];
        CameraCharacteristics r4 = this.cameraManager.getCameraCharacteristics(r2);
        Integer r5 = (Integer) r4.get(CameraCharacteristics.LENS_FACING);
        if (r5 != null) goto L12;
    L13:
        int r22 = r9;
        String[] r16 = r10;
    L55:
        r14 = r14 + 1;
        r10 = r16;
        r9 = r22;
        goto L8
    L12:
        if (r5.intValue() != r9) goto L13;
        StreamConfigurationMap r52 = (StreamConfigurationMap) r4.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        if (r52 != null) goto L17;
        this.diagnostics.log("camera candidate rejected: id=" + r2 + ", reason=no stream map");
        goto L13
    L17:
        Size[] r8 = r52.getOutputSizes(SurfaceTexture.class);
        if (r8 != null) goto L20;
    L22:
        r22 = r9;
        r16 = r10;
        this.diagnostics.log("camera candidate rejected: id=" + r2 + ", reason=no SurfaceTexture outputs");
        goto L55
    L20:
        if (r8.length != 0) goto L79;
        r2 = r2;
        goto L22
    L79:
        OutputPair r20 = chooseOutputPair(r8, this.outputResolution, this.requestedCameraResolution);     // Catch: RuntimeException -> L51
        r22 = r9;
        String r23 = r2;
        String r92 = "camera candidate rejected: id=";
        FrameRatePlan r21 = resolveFrameRate(r23, r4, r52, r8, r20);     // Catch: RuntimeException -> L47
        CameraCandidate r162 = new CameraCandidate(r23, r4, r8, r20, r21);     // Catch: RuntimeException -> L49
        r23 = r23;
        RoundVideoDiagnostics r53 = this.diagnostics;     // Catch: RuntimeException -> L47
        StringBuilder r6 = new StringBuilder();     // Catch: RuntimeException -> L47
        r16 = r10;
        r6.append("camera candidate accepted: id=");     // Catch: RuntimeException -> L36
        r6.append(r23);     // Catch: RuntimeException -> L36
        r6.append(", facing=");     // Catch: RuntimeException -> L36
        r6.append(r24);     // Catch: RuntimeException -> L36
        r6.append(", flashAvailable=");     // Catch: RuntimeException -> L36
        r6.append(hasFlashUnit(r4));     // Catch: RuntimeException -> L36
        r6.append(", fps=");     // Catch: RuntimeException -> L36
        r6.append(r21.frameRate.getValue());     // Catch: RuntimeException -> L36
        r6.append(", preview=");     // Catch: RuntimeException -> L36
        r6.append(r21.outputPair.preview);     // Catch: RuntimeException -> L36
        r6.append(", recording=");     // Catch: RuntimeException -> L36
        r6.append(r21.outputPair.recording);     // Catch: RuntimeException -> L36
        r53.log(r6.toString());     // Catch: RuntimeException -> L36
        if (r13 != null) goto L34;
    L38:
        r13 = r162;
    L39:
        RoundVideoSession.FrameRate r0 = this.requestedFrameRate;     // Catch: RuntimeException -> L36
        if (r0 != RoundVideoSession.FrameRate.FPS_30) goto L42;
    L43:
        if (r12 != null) goto L45;
    L46:
        r12 = r162;
        goto L55
    L45:
        if (isPreferredCameraCandidate(r162, r12, r24) == false) goto L55;
    L42:
        if (r21.frameRate != r0) goto L55;
    L34:
        if (isPreferredCameraCandidate(r162, r13, r24) == false) goto L39;
    L36:
        e = e;
    L53:
        this.diagnostics.log(r92 + r23 + ", reason=" + e);
    L49:
        e = e;
        r16 = r10;
        r23 = r23;
    L47:
        e = e;
        r16 = r10;
    L51:
        e = e;
        r22 = r9;
        r16 = r10;
        r23 = r2;
        r92 = "camera candidate rejected: id=";
        goto L53
    L57:
        if (r12 != null) goto L59;
        r12 = r13;
    L59:
        if (r12 == null) goto L73;
        String r25 = r12.id;
        CameraCharacteristics r3 = r12.characteristics;
        Size[] r42 = r12.sizes;
        OutputPair r54 = r12.regularPair;
        FrameRatePlan r62 = r12.frameRatePlan;
        OutputPair r93 = r62.outputPair;
        this.cameraId = r25;
        this.selectedFacing = r24;
        this.characteristics = r3;
        this.activeFrameRate = r62.frameRate;
        this.activeFpsRange = r62.fpsRange;
        this.frameRateFallbackAttempted = false;
        this.regularFallbackPair = r54;
        resetCaptureFpsMeasurement();
        this.previewSize = r93.preview;
        this.recordingSize = r93.recording;
        this.sourceCropSize = r93.cropSize;
        this.activeCameraResolution = r93.cameraResolution;
        this.maximumZoomRatio = readMaximumZoomRatio(r3);
        long r55 = getMinimumFrameDurationNs(r3, this.previewSize);
        long r94 = getMinimumFrameDurationNs(r3, this.recordingSize);
        RoundVideoDiagnostics r112 = this.diagnostics;
        StringBuilder r122 = new StringBuilder("camera outputs selected: output=");
        r122.append(this.outputSize);
        r122.append("x");
        r122.append(this.outputSize);
        r122.append(", cameraMode=");
        r122.append(this.activeCameraResolution);
        RoundVideoSession.CameraResolution r132 = this.activeCameraResolution;
        RoundVideoSession.CameraResolution r142 = this.requestedCameraResolution;
        String r163 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r132 == r142) goto L63;
        String r133 = " (fallback from " + this.requestedCameraResolution + ")";
    L64:
        r122.append(r133);
        r122.append(", crop=");
        r122.append(this.sourceCropSize);
        r122.append(", preview=");
        r122.append(this.previewSize);
        r122.append(", recording=");
        r122.append(this.recordingSize);
        r122.append(", previewMinFrameDurationNs=");
        r122.append(r55);
        r122.append(", recordingMinFrameDurationNs=");
        r122.append(r94);
        r122.append(", fps=");
        r122.append(this.activeFrameRate.getValue());
        r122.append(", fpsRange=");
        r122.append(this.activeFpsRange);
        r122.append(", timestampSource=");
        if (isCameraTimestampRealtime() == false) goto L67;
        String r02 = "REALTIME";
    L68:
        r122.append(r02);
        if (this.activeFrameRate == this.requestedFrameRate) goto L71;
        r163 = " (fallback from " + this.requestedFrameRate.getValue() + ")";
    L71:
        r122.append(r163);
        r112.log(r122.toString());
        logCameraConfigurationOnce(r24, r25, r3, r42);
        return;
    L67:
        r02 = "UNKNOWN";
        goto L68
    L63:
        r133 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L64
    L73:
        Segment$$ExternalSyntheticBUOutline1.m("Requested camera is not available");
        return;
    L5:
        r9 = 1;
        goto L6
    }

    private void setContinuousVideoAf(CaptureRequest.Builder r2) {
        CameraCharacteristics r1 = this.characteristics;
        if (r1 != null) goto L5;
        int[] r12 = null;
    L7:
        if (contains(r12, 3) == false) goto L10;
        r2.set(CaptureRequest.CONTROL_AF_MODE, 3);
        return;
    L10:
        return;
    L5:
        r12 = (int[]) r1.get(CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES);
        goto L7
    }

    private void setPreferredFps(CaptureRequest.Builder r3, boolean r4) {
        Range<Integer> r0 = this.activeFpsRange;
        if (r0 == null) goto L8;
        r3.set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, r0);
        if (r4 == false) goto L11;
        this.diagnostics.log("capture FPS range selected: " + this.activeFpsRange);
        return;
    L11:
        return;
    L8:
        if (r4 == false) goto L12;
        this.diagnostics.log("capture FPS range unavailable; HAL default will be used");
        return;
    }

    private static int shortSide(Size r1) {
        return Math.min(r1.getWidth(), r1.getHeight());
    }

    private static float standardDeviationMs(long r2, double r4, long r6) {
        if (r6 != 0) goto L6;
        return 0.0f;
    L6:
        double r62 = r6;
        double r22 = r2 / r62;
        return (float) (Math.sqrt(Math.max(0.0d, (r4 / r62) - (r22 * r22))) / 1000000.0d);
    }

    private void startRecordingAfterFirstFrame() {
        if (this.active == true) goto L5;
        return;
    L5:
        if (this.captureSession == null) goto L23;
        RoundVideoCodecRecorder r1 = this.recorder;
        if (r1 != null) goto L9;
        return;
    L9:
        if (r1.isStarted() == true) goto L25;
        RoundVideoCodecRecorder r12 = this.recorder;     // Catch: RuntimeException -> L14
        this.commonStartRecorder = r12;     // Catch: RuntimeException -> L14
        Handler r2 = this.cameraHandler;     // Catch: RuntimeException -> L14
        if (r2 == null) goto L16;
        r2.removeCallbacks(this.commonStartTimeoutRunnable);     // Catch: RuntimeException -> L14
        r2.postDelayed(this.commonStartTimeoutRunnable, 3000);     // Catch: RuntimeException -> L14
    L16:
        r12.startRecording(new RoundVideoCameraController$$ExternalSyntheticLambda0(this, r12), new RoundVideoCameraController$$ExternalSyntheticLambda1(this, r12));     // Catch: RuntimeException -> L14
        this.diagnostics.log("first camera frame received; audio startup requested: segmentElapsedMs=" + elapsedMs(this.segmentStartedNs));     // Catch: RuntimeException -> L14
        return;
    L14:
        e = move-exception;
        cancelCommonStartWait();
        reportError(e);
        return;
    L25:
        return;
    }

    private void startVideoAfterAudioReady(RoundVideoCodecRecorder r5) {
        if (this.active == true) goto L5;
        return;
    L5:
        if (this.stopping == false) goto L7;
        return;
    L7:
        if (this.recorder == r5) goto L9;
        return;
    L9:
        if (this.captureSession == null) goto L23;
        RoundVideoGlProcessor r1 = this.glProcessor;
        if (r1 == null) goto L24;
        r1.startRecording(r5.getTimeOriginNs());     // Catch: RuntimeException -> L15
        this.diagnostics.log("common A/V start armed; waiting for next camera frame: segmentElapsedMs=" + elapsedMs(this.segmentStartedNs));     // Catch: RuntimeException -> L15
        return;
    L15:
        e = move-exception;
        cancelCommonStartWait();
        reportError(e);
        return;
    L24:
        return;
    }

    private void submitRepeatingRequest() {
        CameraCaptureSession r0 = this.captureSession;
        CaptureRequest.Builder r1 = this.repeatingBuilder;
        if (r0 == null) goto L8;
        if (r1 == null) goto L9;
        r0.setRepeatingRequest(r1.build(), this.captureCallback, this.cameraHandler);
        return;
    L9:
        return;
    }

    private boolean supports60FpsForFacing(RoundVideoSession.CameraFacing r12) {
        if (r12 != RoundVideoSession.CameraFacing.FRONT) goto L5;
        int r122 = 0;
    L6:
        String[] r0 = this.cameraManager.getCameraIdList();
        int r3 = r0.length;
        int r4 = 0;
    L7:
        if (r4 >= r3) goto L27;
        String r6 = r0[r4];
        CameraCharacteristics r7 = this.cameraManager.getCameraCharacteristics(r6);
        Integer r5 = (Integer) r7.get(CameraCharacteristics.LENS_FACING);
        if (r5 != null) goto L11;
    L12:
        RoundVideoCameraController r52 = this;
    L26:
        r4 = r4 + 1;
        RoundVideoCameraController this = r52;
        goto L7
    L11:
        if (r5.intValue() != r122) goto L12;
        StreamConfigurationMap r8 = (StreamConfigurationMap) r7.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        if (r8 == null) goto L12;
        Size[] r9 = r8.getOutputSizes(SurfaceTexture.class);
        if (r9 == null) goto L12;
        if (r9.length == 0) goto L12;
        r52 = this;
        if (r52.resolveFrameRate(r6, r7, r8, r9, chooseOutputPair(r9, this.outputResolution, this.requestedCameraResolution)).frameRate != RoundVideoSession.FrameRate.FPS_60) goto L26;
        return true;
    L27:
        return false;
    L5:
        r122 = 1;
        goto L6
    }

    private static boolean supportsFrameDuration(StreamConfigurationMap r4, Size r5, int r6) {
        long r42 = r4.getOutputMinFrameDuration(SurfaceTexture.class, r5);
        if (r42 > 0) goto L5;
        return true;
    L5:
        if (r42 <= (1000000000 / r6)) goto L11;
        return false;
    L11:
        return true;
    }

    private void switchCameraIfNeeded(RoundVideoSession.CameraFacing r5) {
        if (this.active == true) goto L5;
        return;
    L5:
        if (this.switchingCamera == false) goto L7;
        return;
    L7:
        if (r5 == this.activeFacing) goto L23;
        this.switchingCamera = true;
        this.notifySwitchCompletion = true;
        this.torchEnabled = false;
        this.torchVerificationPending = false;
        this.zoomProgress = 0.0f;
        this.callback.onCameraSwitchStarted(r5);
        this.cameraSwitchStartedNs = SystemClock.elapsedRealtimeNanos();
        this.diagnostics.log("camera device switch started: from=" + this.activeFacing + ", to=" + r5);
        RoundVideoGlProcessor r1 = this.glProcessor;
        if (r1 == null) goto L12;
        r1.beginCameraSwitch(this.activeFacing, r5);
    L12:
        closeCaptureSession();
        CameraDevice r52 = this.cameraDevice;
        if (r52 == null) goto L17;
        this.cameraDevice = null;
        r52.close();
        return;
    L17:
        if (this.opening == true) goto L22;
        this.switchingCamera = false;
        openCameraIfReady();
        return;
    L22:
        return;
    }

    private void updatePreviewTransform() {
        if (this.previewTransformEnabled == false) goto L24;
        Size r0 = this.recordingSize;
        if (r0 != null) goto L8;
        return;
    L8:
        if (this.previewView.getWidth() != 0) goto L10;
        return;
    L10:
        if (this.previewView.getHeight() == 0) goto L25;
        int r1 = Math.min(this.sourceCropSize, Math.min(r0.getWidth(), r0.getHeight()));
        boolean r2 = isOutputRotationSwapped();
        if (r2 == false) goto L15;
        int r3 = r0.getHeight();
    L16:
        float r4 = r1;
        float r32 = r3 / r4;
        if (r2 == false) goto L19;
        int r5 = r0.getWidth();
    L20:
        float r52 = r5 / r4;
        Matrix r6 = new Matrix();
        r6.setScale(r32, r52, this.previewView.getWidth() * 0.5f, this.previewView.getHeight() * 0.5f);
        this.previewView.setTransform(r6);
        this.diagnostics.log("preview transform: view=" + this.previewView.getWidth() + "x" + this.previewView.getHeight() + ", source=" + r0 + ", crop=" + r1 + ", axesSwapped=" + r2 + ", scale=" + r32 + "x" + r52);
        return;
    L19:
        r5 = r0.getHeight();
        goto L20
    L15:
        r3 = r0.getWidth();
        goto L16
    L25:
        return;
    }

    private void verifyTorchResult(CaptureRequest r9, TotalCaptureResult r10) {
        if (this.torchVerificationPending == false) goto L48;
        Object r92 = r9.getTag();
        if ((r92 instanceof Integer) == true) goto L8;
        return;
    L8:
        if (((Integer) r92).intValue() != this.torchVerificationGeneration) goto L49;
        this.torchVerificationFrames++;
        Integer r93 = (Integer) r10.get(CaptureResult.FLASH_MODE);
        Integer r102 = (Integer) r10.get(CaptureResult.FLASH_STATE);
        if (this.torchEnabled == false) goto L17;
        if (r93 != null) goto L14;
    L16:
        boolean r1 = false;
    L22:
        if (this.torchEnabled == false) goto L30;
        if (r102 == null) goto L30;
        if (r102.intValue() == 3) goto L30;
        if (r102.intValue() == 4) goto L30;
        boolean r3 = false;
    L32:
        if (r1 == false) goto L36;
        if (r3 == false) goto L36;
        this.diagnostics.log("torch result confirmed: enabled=" + this.torchEnabled + ", resultMode=" + r93 + ", flashState=" + r102 + ", frames=" + this.torchVerificationFrames + ", retries=" + this.torchVerificationRetries);
        this.torchVerificationPending = false;
        return;
    L36:
        int r12 = this.torchVerificationFrames;
        if (r12 != 3) goto L43;
        int r32 = this.torchVerificationRetries;
        if (r32 != 0) goto L43;
        this.torchVerificationRetries = r32 + 1;
        this.torchVerificationFrames = 0;
        this.diagnostics.log("torch result not applied; rebuilding request: enabled=" + this.torchEnabled + ", resultMode=" + r93 + ", flashState=" + r102);
        applyRepeatingRequest();
        return;
    L43:
        if (r12 < 6) goto L47;
        this.diagnostics.log("torch result failed: enabled=" + this.torchEnabled + ", resultMode=" + r93 + ", flashState=" + r102 + ", retries=" + this.torchVerificationRetries);
        this.torchVerificationPending = false;
        return;
    L47:
        return;
    L30:
        r3 = true;
        goto L32
    L14:
        if (r93.intValue() != 2) goto L16;
    L15:
        r1 = true;
        goto L22
    L17:
        if (r93 == null) goto L15;
        if (r93.intValue() != 0) goto L16;
    L49:
        return;
    }

    public void release() {
        this.active = false;
        this.previewView.setSurfaceTextureListener(null);
        this.previewView.removeOnLayoutChangeListener(this.layoutChangeListener);
        Handler r0 = this.cameraHandler;
        HandlerThread r2 = this.cameraThread;
        this.cameraHandler = null;
        this.cameraThread = null;
        if (r0 == null) goto L8;
        if (r2 == null) goto L9;
        r0.post(new RoundVideoCameraController$$ExternalSyntheticLambda10(this, r2));
        return;
    L9:
        return;
    }

    public boolean setCameraFacing(RoundVideoSession.CameraFacing r3) {
        this.requestedFacing = r3;
        Handler r0 = this.cameraHandler;
        if (this.active == false) goto L8;
        if (r0 == null) goto L10;
        r0.post(new RoundVideoCameraController$$ExternalSyntheticLambda8(this, r3));
        return true;
    L10:
        return false;
    L8:
        return false;
    }

    public boolean setTorchEnabled(boolean r3) {
        Handler r0 = this.cameraHandler;
        if (this.active == false) goto L8;
        if (r0 == null) goto L10;
        r0.post(new RoundVideoCameraController$$ExternalSyntheticLambda9(this, r3));
        return true;
    L10:
        return false;
    L8:
        return false;
    }

    public boolean setZoom(float r3) {
        this.zoomProgress = clamp(r3, 0.0f, 1.0f);
        Handler r32 = this.cameraHandler;
        if (this.active == false) goto L8;
        if (r32 == null) goto L10;
        r32.removeCallbacks(this.applyZoomRunnable);
        r32.post(this.applyZoomRunnable);
        return true;
    L10:
        return false;
    L8:
        return false;
    }

    public void startSegment(RoundVideoMp4Writer r3, long r4, RoundVideoSession.CameraFacing r6) {
        ensureThread();
        this.writer = r3;
        this.timelineOffsetUs = r4;
        this.requestedFacing = r6;
        this.active = true;
        this.stopping = false;
        this.commonStartRecorder = null;
        this.previewTransformEnabled = true;
        this.segmentStartedNs = SystemClock.elapsedRealtimeNanos();
        resetPreviewFrameMetrics();
        resetCaptureFpsMeasurement();
        this.diagnostics.log("camera segment start: facing=" + r6 + ", timelineOffsetUs=" + r4 + ", textureAvailable=" + this.previewView.isAvailable());
        this.previewView.setSurfaceTextureListener(this.previewListener);
        maybeOpenCamera();
    }

    public boolean stopSegment() {
        Handler r0 = this.cameraHandler;
        if (this.active == true) goto L5;
    L10:
        return false;
    L5:
        if (this.stopping == true) goto L10;
        if (r0 == null) goto L10;
        this.stopping = true;
        this.previewTransformEnabled = false;
        this.diagnostics.log("camera segment stop requested");
        r0.post(new RoundVideoCameraController$$ExternalSyntheticLambda11(this));
        return true;
    }
}
