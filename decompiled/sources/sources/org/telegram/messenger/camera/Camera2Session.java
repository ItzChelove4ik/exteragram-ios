package org.telegram.messenger.camera;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Range;
import android.view.Surface;
import android.view.WindowManager;
import com.exteragram.messenger.ExteraConfig;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.Utilities;

/* loaded from: classes5.dex */
public class Camera2Session {
    private CameraCharacteristics cameraCharacteristics;
    private CameraDevice cameraDevice;
    public final String cameraId;
    private final CameraManager cameraManager;
    private final CameraDevice.StateCallback cameraStateCallback;
    private CaptureRequest.Builder captureRequestBuilder;
    private CameraCaptureSession captureSession;
    private final CameraCaptureSession.StateCallback captureStateCallback;
    private final Rect cropRegion;
    private float currentZoom;
    private Runnable doneCallback;
    private boolean flashing;
    private Handler handler;
    private ImageReader imageReader;
    private boolean isClosed;
    private boolean isError;
    private final boolean isFront;
    private boolean isSuccess;
    private long lastTime;
    private float maxZoom;
    private boolean nightMode;
    private boolean opened;
    private final android.util.Size previewSize;
    private volatile int recordingFrameRate;
    private boolean recordingVideo;
    private boolean scanningBarcode;
    private Rect sensorSize;
    private Surface surface;
    private SurfaceTexture surfaceTexture;
    private HandlerThread thread;

    /* renamed from: org.telegram.messenger.camera.Camera2Session$1, reason: invalid class name */
    public class AnonymousClass1 extends CameraDevice.StateCallback {
        final /* synthetic */ Camera2Session this$0;
        final /* synthetic */ String val$cameraId;

        public static /* synthetic */ void $r8$lambda$AmmKtkDyjJevP8wkqHSKJ4ijtVA(AnonymousClass1 r0) {
            r0.lambda$onError$0();
        }

        public AnonymousClass1(Camera2Session r1, String r2) {
            this.this$0 = r1;
            this.val$cameraId = r2;
        }

        private /* synthetic */ void lambda$onError$0() {
            Camera2Session.m5060$$Nest$fputisError(this.this$0, true);
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onDisconnected(CameraDevice r2) {
            Camera2Session.m5057$$Nest$fputcameraDevice(this.this$0, r2);
            FileLog.d("Camera2Session camera #" + this.val$cameraId + " disconnected");
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onError(CameraDevice r2, int r3) {
            Camera2Session.m5057$$Nest$fputcameraDevice(this.this$0, r2);
            FileLog.e("Camera2Session camera #" + this.val$cameraId + " received " + r3 + " error");
            AndroidUtilities.runOnUIThread(new Camera2Session$1$$ExternalSyntheticLambda0(this));
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onOpened(CameraDevice r3) {
            Camera2Session.m5057$$Nest$fputcameraDevice(this.this$0, r3);
            Camera2Session.m5062$$Nest$fputlastTime(this.this$0, System.currentTimeMillis());
            FileLog.d("Camera2Session camera #" + this.val$cameraId + " opened");
            Camera2Session.m5063$$Nest$mcheckOpen(this.this$0);
        }
    }

    /* renamed from: org.telegram.messenger.camera.Camera2Session$2, reason: invalid class name */
    public class AnonymousClass2 extends CameraCaptureSession.StateCallback {
        final /* synthetic */ Camera2Session this$0;
        final /* synthetic */ String val$cameraId;

        public static /* synthetic */ void $r8$lambda$ZE8b1niyL6oWgAGKJVrduEklzkY(AnonymousClass2 r0) {
            r0.lambda$onConfigured$0();
        }

        public static /* synthetic */ void $r8$lambda$hkpYlTahRg8FHu3gOnTYicX32NU(AnonymousClass2 r0) {
            r0.lambda$onConfigureFailed$1();
        }

        public AnonymousClass2(Camera2Session r1, String r2) {
            this.this$0 = r1;
            this.val$cameraId = r2;
        }

        private /* synthetic */ void lambda$onConfigureFailed$1() {
            Camera2Session.m5060$$Nest$fputisError(this.this$0, true);
        }

        private /* synthetic */ void lambda$onConfigured$0() {
            Camera2Session.m5061$$Nest$fputisSuccess(this.this$0, true);
            if (Camera2Session.m5056$$Nest$fgetdoneCallback(this.this$0) == null) goto L6;
            Camera2Session.m5056$$Nest$fgetdoneCallback(this.this$0).run();
            Camera2Session.m5059$$Nest$fputdoneCallback(this.this$0, null);
            return;
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigureFailed(CameraCaptureSession r2) {
            Camera2Session.m5058$$Nest$fputcaptureSession(this.this$0, r2);
            FileLog.e("Camera2Session camera #" + this.val$cameraId + " capture session failed to configure");
            AndroidUtilities.runOnUIThread(new Camera2Session$2$$ExternalSyntheticLambda1(this));
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigured(CameraCaptureSession r3) {
            Camera2Session.m5058$$Nest$fputcaptureSession(this.this$0, r3);
            FileLog.e("Camera2Session camera #" + this.val$cameraId + " capture session configured");
            Camera2Session.m5062$$Nest$fputlastTime(this.this$0, System.currentTimeMillis());
            Camera2Session.m5064$$Nest$mupdateCaptureRequest(this.this$0);     // Catch: Exception -> L5
            AndroidUtilities.runOnUIThread(new Camera2Session$2$$ExternalSyntheticLambda0(this));     // Catch: Exception -> L5
            return;
        L5:
            e = move-exception;
            FileLog.e(e);
        }
    }

    /* renamed from: org.telegram.messenger.camera.Camera2Session$3, reason: invalid class name */
    public class AnonymousClass3 implements ImageReader.OnImageAvailableListener {
        final /* synthetic */ Camera2Session this$0;
        final /* synthetic */ File val$file;
        final /* synthetic */ int val$orientation;
        final /* synthetic */ Utilities.Callback val$whenDone;

        public static /* synthetic */ void $r8$lambda$C8GruD4YcNmICIAfGZxauIzKYfU(Utilities.Callback r0, int r1) {
            if (r0 == null) goto L5;
            r0.run(Integer.valueOf(r1));
            return;
        }

        public AnonymousClass3(Camera2Session r1, File r2, Utilities.Callback r3, int r4) {
            this.this$0 = r1;
            this.val$file = r2;
            this.val$whenDone = r3;
            this.val$orientation = r4;
        }

        @Override // android.media.ImageReader.OnImageAvailableListener
        public void onImageAvailable(ImageReader r5) {
            Image r52 = r5.acquireLatestImage();
            ByteBuffer r0 = r52.getPlanes()[0].getBuffer();
            byte[] r1 = new byte[r0.remaining()];
            r0.get(r1);
            FileOutputStream r02 = null;
            FileOutputStream r2 = new FileOutputStream(this.val$file);     // Catch: Throwable -> L14 IOException -> L16
            r2.write(r1);     // Catch: Throwable -> L10 IOException -> L12
            r52.close();
        L31:
            r2.close();     // Catch: IOException -> L8
        L22:
            AndroidUtilities.runOnUIThread(new Camera2Session$3$$ExternalSyntheticLambda0(this.val$whenDone, this.val$orientation));
            return;
        L8:
            e = move-exception;
            e.printStackTrace();
        L12:
            IOException e = e;
        L18:
            e.printStackTrace();     // Catch: Throwable -> L10
            r52.close();
            if (r2 == null) goto L22;
        L10:
            th = th;
            r02 = r2;
        L24:
            r52.close();
            if (r02 != null) goto L33;
        L30:
            throw th;
        L33:
            r02.close();     // Catch: IOException -> L28
        L28:
            e = move-exception;
            e.printStackTrace();
            goto L30
        L16:
            e = move-exception;
            r2 = null;
            e = e;
        L14:
            th = th;
            goto L24
        }
    }

    /* renamed from: org.telegram.messenger.camera.Camera2Session$4, reason: invalid class name */
    public class AnonymousClass4 extends CameraCaptureSession.CaptureCallback {
        final /* synthetic */ Camera2Session this$0;

        public AnonymousClass4(Camera2Session r1) {
            this.this$0 = r1;
        }
    }

    public static class CompareSizesByArea implements Comparator<android.util.Size> {
        public CompareSizesByArea() {
        }

        /* renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(android.util.Size r5, android.util.Size r6) {
            return Long.signum((r5.getWidth() * r5.getHeight()) - (r6.getWidth() * r6.getHeight()));
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(android.util.Size r1, android.util.Size r2) {
            return compare2(r1, r2);
        }
    }

    public static /* synthetic */ void $r8$lambda$9QhCLSJjUxX5rUv2LZx4iltK3Y8(Camera2Session r0, Runnable r1) {
        r0.lambda$destroy$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$UYAJ9AJ3MhNfEpHjgaQxbWcmYgI(Camera2Session r0, SurfaceTexture r1) {
        r0.lambda$open$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$Vyn_idMcnMjguTLxv7JRLfVoqQU(Camera2Session r0) {
        r0.lambda$new$0();
    }

    public static /* synthetic */ void $r8$lambda$dptiU6gsFjkihGGTb6HKV_9y5vg(Camera2Session r0) {
        r0.lambda$checkOpen$2();
    }

    public static /* synthetic */ void $r8$lambda$yhVVkij6EFDeqv07ZFnid5xG7R4(Camera2Session r0, Runnable r1) {
        r0.lambda$destroy$3(r1);
    }

    /* renamed from: -$$Nest$fgetdoneCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m5056$$Nest$fgetdoneCallback(Camera2Session r0) {
        return r0.doneCallback;
    }

    /* renamed from: -$$Nest$fputcameraDevice, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5057$$Nest$fputcameraDevice(Camera2Session r0, CameraDevice r1) {
        r0.cameraDevice = r1;
    }

    /* renamed from: -$$Nest$fputcaptureSession, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5058$$Nest$fputcaptureSession(Camera2Session r0, CameraCaptureSession r1) {
        r0.captureSession = r1;
    }

    /* renamed from: -$$Nest$fputdoneCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5059$$Nest$fputdoneCallback(Camera2Session r0, Runnable r1) {
        r0.doneCallback = r1;
    }

    /* renamed from: -$$Nest$fputisError, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5060$$Nest$fputisError(Camera2Session r0, boolean r1) {
        r0.isError = r1;
    }

    /* renamed from: -$$Nest$fputisSuccess, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5061$$Nest$fputisSuccess(Camera2Session r0, boolean r1) {
        r0.isSuccess = r1;
    }

    /* renamed from: -$$Nest$fputlastTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5062$$Nest$fputlastTime(Camera2Session r0, long r1) {
        r0.lastTime = r1;
    }

    /* renamed from: -$$Nest$mcheckOpen, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5063$$Nest$mcheckOpen(Camera2Session r0) {
        r0.checkOpen();
    }

    /* renamed from: -$$Nest$mupdateCaptureRequest, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5064$$Nest$mupdateCaptureRequest(Camera2Session r0) {
        r0.updateCaptureRequest();
    }

    private Camera2Session(Context r5, boolean r6, String r7, android.util.Size r8) {
        float r0 = 1.0f;
        this.maxZoom = 1.0f;
        this.currentZoom = 1.0f;
        this.recordingFrameRate = 30;
        this.opened = false;
        this.cropRegion = new Rect();
        HandlerThread r1 = new HandlerThread("tg_camera2");
        this.thread = r1;
        r1.start();
        this.handler = new Handler(this.thread.getLooper());
        AnonymousClass1 r12 = new AnonymousClass1(this, r7);
        this.cameraStateCallback = r12;
        this.captureStateCallback = new AnonymousClass2(this, r7);
        this.isFront = r6;
        this.cameraId = r7;
        this.previewSize = r8;
        this.lastTime = System.currentTimeMillis();
        this.imageReader = ImageReader.newInstance(r8.getWidth(), r8.getHeight(), 256, 1);
        CameraManager r52 = (CameraManager) r5.getSystemService("camera");
        this.cameraManager = r52;
        CameraCharacteristics r62 = r52.getCameraCharacteristics(r7);     // Catch: Exception -> L9
        this.cameraCharacteristics = r62;     // Catch: Exception -> L9
        this.sensorSize = (Rect) r62.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);     // Catch: Exception -> L9
        Float r63 = (Float) this.cameraCharacteristics.get(CameraCharacteristics.SCALER_AVAILABLE_MAX_DIGITAL_ZOOM);     // Catch: Exception -> L9
        if (r63 != null) goto L6;
    L11:
        this.maxZoom = r0;     // Catch: Exception -> L9
        r52.openCamera(r7, r12, this.handler);     // Catch: Exception -> L9
        return;
    L6:
        if (r63.floatValue() < 1.0f) goto L11;
        r0 = r63.floatValue();     // Catch: Exception -> L9
    L9:
        e = move-exception;
        FileLog.e(e);
        AndroidUtilities.runOnUIThread(new Camera2Session$$ExternalSyntheticLambda0(this));
    }

    private void checkOpen() {
        if (this.opened == false) goto L6;
        return;
    L6:
        if (this.surfaceTexture != null) goto L8;
        return;
    L8:
        if (this.cameraDevice == null) goto L20;
        this.opened = true;
        this.surface = new Surface(this.surfaceTexture);
        ArrayList r0 = new ArrayList();     // Catch: Exception -> L13
        r0.add(this.surface);     // Catch: Exception -> L13
        r0.add(this.imageReader.getSurface());     // Catch: Exception -> L13
        this.cameraDevice.createCaptureSession(r0, this.captureStateCallback, null);     // Catch: Exception -> L13
        return;
    L13:
        e = move-exception;
        FileLog.e(e);
        AndroidUtilities.runOnUIThread(new Camera2Session$$ExternalSyntheticLambda3(this));
        return;
    }

    private void chooseFocusMode(CaptureRequest.Builder r5) {
        int[] r4 = (int[]) this.cameraCharacteristics.get(CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES);
        int r0 = r4.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L9;
        if (r4[r1] == 3) goto L6;
        r1 = r1 + 1;
        goto L3
    L6:
        r5.set(CaptureRequest.CONTROL_AF_MODE, 3);
        FileLog.d("Using continuous video auto-focus.");
        return;
    L9:
        FileLog.d("Auto-focus is not available.");
    }

    public static android.util.Size chooseOptimalSize(android.util.Size[] r6, int r7, int r8, boolean r9) {
        ArrayList r0 = new ArrayList(r6.length);
        ArrayList r1 = new ArrayList(r6.length);
        int r2 = 0;
    L4:
        if (r2 >= r6.length) goto L28;
        android.util.Size r3 = r6[r2];
        if (r9 == false) goto L13;
        if (r3.getHeight() > r8) goto L26;
        if (r3.getWidth() <= r7) goto L13;
    L26:
        r2 = r2 + 1;
    L13:
        if (r3.getHeight() != ((r3.getWidth() * r8) / r7)) goto L20;
        if (r3.getWidth() < r7) goto L20;
        if (r3.getHeight() < r8) goto L20;
        r0.add(r3);
    L20:
        if ((r3.getHeight() * r3.getWidth()) > ((r7 * r8) * 4)) goto L26;
        if (r3.getWidth() < r7) goto L26;
        if (r3.getHeight() < r8) goto L26;
        r1.add(r3);
        goto L26
    L28:
        if (r0.size() <= 0) goto L32;
        return (android.util.Size) Collections.min(r0, new CompareSizesByArea());
    L32:
        if (r1.size() <= 0) goto L36;
        return (android.util.Size) Collections.min(r1, new CompareSizesByArea());
    L36:
        return (android.util.Size) Collections.max(Arrays.asList(r6), new CompareSizesByArea());
    }

    private void chooseStabilizationMode(CaptureRequest.Builder r9) {
        int r0 = 0;
        int[] r4 = (int[]) this.cameraCharacteristics.get(CameraCharacteristics.LENS_INFO_AVAILABLE_OPTICAL_STABILIZATION);
        if (r4 == null) goto L11;
        int r5 = r4.length;
        int r6 = 0;
    L5:
        if (r6 >= r5) goto L11;
        if (r4[r6] == 1) goto L8;
        r6 = r6 + 1;
        goto L5
    L8:
        r9.set(CaptureRequest.LENS_OPTICAL_STABILIZATION_MODE, 1);
        r9.set(CaptureRequest.CONTROL_VIDEO_STABILIZATION_MODE, 0);
        FileLog.d("Using optical stabilization.");
        return;
    L11:
        int[] r8 = (int[]) this.cameraCharacteristics.get(CameraCharacteristics.CONTROL_AVAILABLE_VIDEO_STABILIZATION_MODES);
        int r42 = r8.length;
    L12:
        if (r0 >= r42) goto L18;
        if (r8[r0] == 1) goto L15;
        r0 = r0 + 1;
        goto L12
    L15:
        r9.set(CaptureRequest.CONTROL_VIDEO_STABILIZATION_MODE, 1);
        r9.set(CaptureRequest.LENS_OPTICAL_STABILIZATION_MODE, 0);
        FileLog.d("Using video stabilization.");
        return;
    L18:
        FileLog.d("Stabilization not available.");
    }

    public static Camera2Session create(boolean r22, int r23, int r24) {
        Context r3 = ApplicationLoader.applicationContext;
        CameraManager r4 = (CameraManager) r3.getSystemService("camera");
        String[] r6 = r4.getCameraIdList();     // Catch: Exception -> L41
        int r7 = r6.length;     // Catch: Exception -> L41
        int r10 = 0;
        android.util.Size r11 = null;
        String r12 = null;
        float r13 = 0.0f;
    L5:
        if (r10 >= r7) goto L40;
        String r14 = r6[r10];     // Catch: Exception -> L38
        CameraCharacteristics r15 = r4.getCameraCharacteristics(r14);     // Catch: Exception -> L38
        Camera2Session r16 = null;
        if (((Integer) r15.get(CameraCharacteristics.LENS_FACING)).intValue() == (!r22)) goto L12;
        CameraManager r19 = r4;
    L37:
        r10 = r10 + 1;
        r4 = r19;
        goto L5
    L12:
        StreamConfigurationMap r5 = (StreamConfigurationMap) r15.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);     // Catch: Exception -> L31
        if (((android.util.Size) r15.get(CameraCharacteristics.SENSOR_INFO_PIXEL_ARRAY_SIZE)) != null) goto L15;
        float r152 = 0.0f;
    L16:
        float r9 = r23 / r24;     // Catch: Exception -> L31
        if (r9 < 1.0f) goto L19;
        float r18 = 1.0f;
        boolean r8 = true;
    L21:
        if (r152 < r18) goto L23;
        r19 = r4;
        boolean r42 = true;
    L24:
        if (r8 == r42) goto L27;
        r152 = r18 / r152;     // Catch: Exception -> L31
    L27:
        if (r13 > 0.0f) goto L29;
    L33:
        if (r5 == null) goto L37;
        android.util.Size r43 = chooseOptimalSize(r5.getOutputSizes(SurfaceTexture.class), r23, r24, false);     // Catch: Exception -> L31
        if (r43 == null) goto L37;
        r11 = r43;
        r12 = r14;
        r13 = r152;
        goto L37
    L29:
        if (Math.abs(r9 - r13) <= Math.abs(r9 - r152)) goto L37;
    L23:
        r19 = r4;
        r42 = false;
        goto L24
    L19:
        r18 = 1.0f;
        r8 = false;
        goto L21
    L15:
        r152 = r9.getWidth() / r9.getHeight();     // Catch: Exception -> L31
    L31:
        e = e;
    L43:
        FileLog.e(e);
    L44:
        if (r12 != null) goto L47;
        return r16;
    L47:
        return new Camera2Session(r3, r22, r12, r11);
    L38:
        e = e;
        r16 = null;
        goto L43
    L40:
        r16 = null;
    L41:
        e = e;
        r16 = null;
        r11 = null;
        r12 = null;
        goto L43
    }

    private String findCameraId(boolean r7) {
        String[] r0 = this.cameraManager.getCameraIdList();     // Catch: CameraAccessException -> L16
        int r1 = r0.length;     // Catch: CameraAccessException -> L16
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L28;
        String r3 = r0[r2];     // Catch: CameraAccessException -> L16
        Integer r4 = (Integer) this.cameraManager.getCameraCharacteristics(r3).get(CameraCharacteristics.LENS_FACING);     // Catch: CameraAccessException -> L16
        if (r4 == null) goto L15;
        if (r7 == true) goto L8;
    L10:
        if (r7 == true) goto L15;
        if (r4.intValue() != 1) goto L15;
    L14:
        return r3;
    L8:
        if (r4.intValue() != 0) goto L10;
    L15:
        r2 = r2 + 1;
        goto L3
    L28:
        return null;
    L16:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    private int getJpegOrientation() {
        Context r1 = ApplicationLoader.applicationContext;     // Catch: Exception -> L22
        if (r1 != null) goto L6;
        return 0;
    L6:
        int r12 = ((WindowManager) r1.getSystemService("window")).getDefaultDisplay().getRotation();     // Catch: Exception -> L22
        if (r12 != 0) goto L9;
    L14:
        int r13 = 0;
    L18:
        Integer r2 = (Integer) this.cameraCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION);     // Catch: Exception -> L22
        if (this.isFront == false) goto L25;
        return (360 - ((r2.intValue() + r13) % 360)) % 360;
    L25:
        return ((r2.intValue() - r13) + 360) % 360;
    L9:
        if (r12 != 1) goto L11;
        r13 = 90;
        goto L18
    L11:
        if (r12 != 2) goto L13;
        r13 = 180;
        goto L18
    L13:
        if (r12 != 3) goto L14;
        r13 = 270;
    L22:
        e = move-exception;
        FileLog.e(e);
        return 0;
    }

    private /* synthetic */ void lambda$checkOpen$2() {
        this.isError = true;
    }

    private /* synthetic */ void lambda$destroy$3(Runnable r1) {
        this.thread.join();     // Catch: Exception -> L4
    L6:
        if (r1 == null) goto L11;
        r1.run();
        return;
    L11:
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
        goto L6
    }

    private /* synthetic */ void lambda$destroy$4(Runnable r3) {
        CameraCaptureSession r0 = this.captureSession;
        if (r0 == null) goto L5;
        r0.close();
        this.captureSession = null;
    L5:
        CameraDevice r02 = this.cameraDevice;
        if (r02 == null) goto L8;
        r02.close();
        this.cameraDevice = null;
    L8:
        ImageReader r03 = this.imageReader;
        if (r03 == null) goto L11;
        r03.close();
        this.imageReader = null;
    L11:
        this.thread.quitSafely();
        AndroidUtilities.runOnUIThread(new Camera2Session$$ExternalSyntheticLambda4(this, r3));
    }

    private /* synthetic */ void lambda$new$0() {
        this.isError = true;
    }

    private /* synthetic */ void lambda$open$1(SurfaceTexture r3) {
        this.surfaceTexture = r3;
        if (r3 == null) goto L5;
        r3.setDefaultBufferSize(getPreviewWidth(), getPreviewHeight());
    L5:
        checkOpen();
    }

    private Range<Integer> selectExtendedFpsRange() {
        Range<Integer>[] r6 = getAvailableFpsRanges();
        Range<Integer> r0 = null;
        if (r6 == null) goto L27;
        int r1 = r6.length;
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L27;
        Range<Integer> r3 = r6[r2];
        if (r3 == null) goto L26;
        if (((Integer) r3.getLower()).intValue() > 60) goto L26;
        if (((Integer) r3.getUpper()).intValue() != 60) goto L26;
        if (((Integer) r3.getLower()).intValue() == 60) goto L16;
    L18:
        if (r0 != null) goto L20;
    L25:
        r0 = r3;
        goto L26
    L20:
        if (((Integer) r3.getLower()).intValue() > ((Integer) r0.getLower()).intValue()) goto L25;
        if (((Integer) r3.getLower()).equals(r0.getLower()) == false) goto L26;
        if (((Integer) r3.getUpper()).intValue() >= ((Integer) r0.getUpper()).intValue()) goto L26;
    L16:
        if (((Integer) r3.getUpper()).intValue() != 60) goto L18;
        return r3;
    L26:
        r2 = r2 + 1;
    L27:
        return r0;
    }

    private void updateCaptureRequest() {
        CameraDevice r0 = this.cameraDevice;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.surface != null) goto L7;
        return;
    L7:
        if (this.captureSession != null) goto L56;
        return;
    L56:
    L20:
        e = move-exception;
        this.recordingFrameRate = 30;
        FileLog.e("Camera2Sessions setRepeatingRequest error in updateCaptureRequest", e);
        return;
    L11:
        if (this.recordingVideo == false) goto L14;
        int r2 = 3;
    L17:
        CaptureRequest.Builder r02 = r0.createCaptureRequest(r2);     // Catch: Exception -> L20
        this.captureRequestBuilder = r02;     // Catch: Exception -> L20
        if (this.scanningBarcode == false) goto L23;
        r02.set(CaptureRequest.CONTROL_SCENE_MODE, 16);     // Catch: Exception -> L20
    L29:
        CaptureRequest.Builder r03 = this.captureRequestBuilder;     // Catch: Exception -> L20
        CaptureRequest.Key r22 = CaptureRequest.FLASH_MODE;     // Catch: Exception -> L20
        if (this.flashing == true) goto L32;
        int r6 = 0;
    L36:
        r03.set(r22, Integer.valueOf(r6));     // Catch: Exception -> L20
        if (this.recordingVideo == false) goto L48;
        this.captureRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, 1);     // Catch: Exception -> L20
        this.recordingFrameRate = 30;     // Catch: Exception -> L20
        if (ExteraConfig.getExtendedFramesPerSecond() == false) goto L44;
        Range<Integer> r04 = selectExtendedFpsRange();     // Catch: Exception -> L20
        if (r04 == null) goto L44;
        this.captureRequestBuilder.set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, r04);     // Catch: Exception -> L20
        this.captureRequestBuilder.set(CaptureRequest.CONTROL_CAPTURE_INTENT, 3);     // Catch: Exception -> L20
        this.recordingFrameRate = 60;     // Catch: Exception -> L20
    L44:
        if (ExteraConfig.getCameraStabilization() == false) goto L46;
        chooseStabilizationMode(this.captureRequestBuilder);     // Catch: Exception -> L20
    L46:
        chooseFocusMode(this.captureRequestBuilder);     // Catch: Exception -> L20
    L48:
        if (this.sensorSize != null) goto L50;
    L52:
        this.captureRequestBuilder.addTarget(this.surface);     // Catch: Exception -> L20
        this.captureSession.setRepeatingRequest(this.captureRequestBuilder.build(), null, this.handler);     // Catch: Exception -> L20
        return;
    L50:
        if (Math.abs(this.currentZoom - 1.0f) < 0.01f) goto L52;
        int r05 = this.sensorSize.width() / 2;     // Catch: Exception -> L20
        int r23 = this.sensorSize.height() / 2;     // Catch: Exception -> L20
        int r3 = (int) ((this.sensorSize.width() * 0.5f) / this.currentZoom);     // Catch: Exception -> L20
        int r4 = (int) ((this.sensorSize.height() * 0.5f) / this.currentZoom);     // Catch: Exception -> L20
        this.cropRegion.set(r05 - r3, r23 - r4, r05 + r3, r23 + r4);     // Catch: Exception -> L20
        this.captureRequestBuilder.set(CaptureRequest.SCALER_CROP_REGION, this.cropRegion);     // Catch: Exception -> L20
        goto L52
    L32:
        if (this.recordingVideo == false) goto L34;
        r6 = 2;
        goto L36
    L34:
        r6 = 1;
        goto L36
    L23:
        if (this.nightMode == false) goto L29;
        CaptureRequest.Key r24 = CaptureRequest.CONTROL_SCENE_MODE;     // Catch: Exception -> L20
        if (this.isFront == false) goto L27;
        int r62 = 6;
    L28:
        r02.set(r24, Integer.valueOf(r62));     // Catch: Exception -> L20
        goto L29
    L27:
        r62 = 5;
        goto L28
    L14:
        if (this.scanningBarcode == false) goto L16;
        r2 = 2;
        goto L17
    L16:
        r2 = 1;
        goto L17
    }

    public void destroy(boolean r2) {
        destroy(r2, null);
    }

    public void destroy(boolean r2, Runnable r3) {
        this.isClosed = true;
        if (r2 == false) goto L6;
        this.handler.post(new Camera2Session$$ExternalSyntheticLambda2(this, r3));
        return;
    L6:
        CameraCaptureSession r22 = this.captureSession;
        if (r22 == null) goto L9;
        r22.close();
        this.captureSession = null;
    L9:
        CameraDevice r23 = this.cameraDevice;
        if (r23 == null) goto L12;
        r23.close();
        this.cameraDevice = null;
    L12:
        ImageReader r24 = this.imageReader;
        if (r24 == null) goto L15;
        r24.close();
        this.imageReader = null;
    L15:
        this.thread.quitSafely();
        this.thread.join();     // Catch: Exception -> L18
    L20:
        if (r3 == null) goto L25;
        AndroidUtilities.runOnUIThread(r3);
        return;
    L25:
        return;
    L18:
        e = move-exception;
        FileLog.e(e);
        goto L20
    }

    public Range<Integer>[] getAvailableFpsRanges() {
        CameraCharacteristics r1 = this.cameraCharacteristics;
        if (r1 != null) goto L7;
        return null;
    L7:
        return (Range[]) r1.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES);
    }

    public int getCurrentOrientation() {
        return getJpegOrientation();
    }

    public int getDisplayOrientation() {
        Context r1 = ApplicationLoader.applicationContext;     // Catch: Exception -> L22
        if (r1 != null) goto L6;
        return 0;
    L6:
        int r12 = ((WindowManager) r1.getSystemService("window")).getDefaultDisplay().getRotation();     // Catch: Exception -> L22
        if (r12 != 0) goto L9;
    L14:
        int r13 = 0;
    L18:
        Integer r2 = (Integer) this.cameraCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION);     // Catch: Exception -> L22
        if (this.isFront == false) goto L25;
        return (360 - ((r2.intValue() + r13) % 360)) % 360;
    L25:
        return ((r2.intValue() - r13) + 360) % 360;
    L9:
        if (r12 != 1) goto L11;
        r13 = 90;
        goto L18
    L11:
        if (r12 != 2) goto L13;
        r13 = 180;
        goto L18
    L13:
        if (r12 != 3) goto L14;
        r13 = 270;
    L22:
        e = move-exception;
        FileLog.e(e);
        return 0;
    }

    public boolean getFlash() {
        return this.flashing;
    }

    public float getMaxZoom() {
        return this.maxZoom;
    }

    public float getMinZoom() {
        return 1.0f;
    }

    public int getPreviewHeight() {
        return this.previewSize.getHeight();
    }

    public int getPreviewWidth() {
        return this.previewSize.getWidth();
    }

    public int getRecordingFrameRate() {
        return this.recordingFrameRate;
    }

    public int getWorldAngle() {
        int r0 = getDisplayOrientation();
        int r1 = getJpegOrientation() - r0;
        if (r1 < 0) goto L5;
        return r1;
    L5:
        return r1 + 360;
    }

    public float getZoom() {
        return this.currentZoom;
    }

    public boolean isInitiated() {
        if (this.isError == false) goto L5;
        return false;
    L5:
        if (this.isSuccess == true) goto L7;
        return false;
    L7:
        if (this.isClosed == true) goto L13;
        return true;
    L13:
        return false;
    }

    public boolean isTorchAvailable(boolean r2) {
        String r22 = findCameraId(r2);
        if (r22 == null) goto L12;
        CameraCharacteristics r1 = this.cameraManager.getCameraCharacteristics(r22);     // Catch: CameraAccessException -> L6
        return Boolean.TRUE.equals(r1.get(CameraCharacteristics.FLASH_INFO_AVAILABLE));
    L6:
        e = move-exception;
        FileLog.e(e);
        return false;
    L12:
        return false;
    }

    public void open(SurfaceTexture r3) {
        this.handler.post(new Camera2Session$$ExternalSyntheticLambda1(this, r3));
    }

    public void setFlash(boolean r2) {
        if (this.flashing == r2) goto L6;
        this.flashing = r2;
        updateCaptureRequest();
        return;
    }

    public void setNightMode(boolean r2) {
        if (this.nightMode == r2) goto L6;
        this.nightMode = r2;
        updateCaptureRequest();
        return;
    }

    public void setRecordingVideo(boolean r2) {
        if (this.recordingVideo == r2) goto L6;
        this.recordingVideo = r2;
        updateCaptureRequest();
        return;
    }

    public void setScanningBarcode(boolean r2) {
        if (this.scanningBarcode == r2) goto L6;
        this.scanningBarcode = r2;
        updateCaptureRequest();
        return;
    }

    public void setZoom(float r3) {
        if (isInitiated() == true) goto L6;
        return;
    L6:
        if (this.captureRequestBuilder != null) goto L8;
        return;
    L8:
        if (this.cameraDevice != null) goto L10;
        return;
    L10:
        if (this.sensorSize == null) goto L23;
        this.currentZoom = Utilities.clamp(r3, this.maxZoom, 1.0f);
        updateCaptureRequest();
        this.captureSession.setRepeatingRequest(this.captureRequestBuilder.build(), null, this.handler);     // Catch: Exception -> L15
        return;
    L15:
        e = move-exception;
        FileLog.e(e);
        return;
    }

    public boolean takePicture(File r6, Utilities.Callback<Integer> r7) {
        CameraDevice r0 = this.cameraDevice;
        if (r0 != null) goto L5;
    L17:
        return false;
    L5:
        if (this.captureSession == null) goto L17;
        CaptureRequest.Builder r02 = r0.createCaptureRequest(2);     // Catch: Exception -> L11
        int r2 = getJpegOrientation();     // Catch: Exception -> L11
        r02.set(CaptureRequest.JPEG_ORIENTATION, Integer.valueOf(r2));     // Catch: Exception -> L11
        this.imageReader.setOnImageAvailableListener(new AnonymousClass3(this, r6, r7, r2), null);     // Catch: Exception -> L11
        if (this.scanningBarcode == false) goto L13;
        r02.set(CaptureRequest.CONTROL_SCENE_MODE, 16);     // Catch: Exception -> L11
    L13:
        r02.addTarget(this.imageReader.getSurface());     // Catch: Exception -> L11
        this.captureSession.capture(r02.build(), new AnonymousClass4(this), null);     // Catch: Exception -> L11
        return true;
    L11:
        e = move-exception;
        FileLog.e("Camera2Sessions takePicture error", e);
        goto L17
    }

    public void whenDone(Runnable r2) {
        if (isInitiated() == false) goto L6;
        r2.run();
        this.doneCallback = null;
        return;
    L6:
        this.doneCallback = r2;
    }
}
