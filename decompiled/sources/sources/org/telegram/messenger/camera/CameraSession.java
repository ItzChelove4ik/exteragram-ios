package org.telegram.messenger.camera;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Build;
import android.text.TextUtils;
import android.view.OrientationEventListener;
import android.view.WindowManager;
import java.util.ArrayList;
import java.util.List;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;

/* loaded from: classes5.dex */
public class CameraSession {
    public static final int ORIENTATION_HYSTERESIS = 5;
    private Camera.AutoFocusCallback autoFocusCallback;
    public ArrayList<String> availableFlashModes;
    public CameraInfo cameraInfo;
    private String currentFlashMode;
    private int currentOrientation;
    private float currentZoom;
    private boolean destroyed;
    private int diffOrientation;
    private int displayOrientation;
    private boolean flipFront;
    Camera.CameraInfo info;
    private int infoCameraId;
    private boolean initied;
    private boolean isRound;
    private boolean isVideo;
    private int jpegOrientation;
    private int lastDisplayOrientation;
    private int lastOrientation;
    private int maxZoom;
    private boolean meteringAreaSupported;
    private boolean optimizeForBarcode;
    private OrientationEventListener orientationEventListener;
    private final int pictureFormat;
    private final Size pictureSize;
    private final Size previewSize;
    private boolean sameTakePictureOrientation;
    private boolean useTorch;

    /* renamed from: org.telegram.messenger.camera.CameraSession$1, reason: invalid class name */
    public class AnonymousClass1 extends OrientationEventListener {
        final /* synthetic */ CameraSession this$0;

        public AnonymousClass1(CameraSession r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.OrientationEventListener
        public void onOrientationChanged(int r3) {
            if (CameraSession.m5073$$Nest$fgetorientationEventListener(this.this$0) != null) goto L5;
            return;
        L5:
            if (CameraSession.m5068$$Nest$fgetinitied(this.this$0) == true) goto L7;
            return;
        L7:
            if (r3 == (-1)) goto L21;
            CameraSession r0 = this.this$0;
            CameraSession.m5074$$Nest$fputjpegOrientation(r0, CameraSession.m5077$$Nest$mroundOrientation(r0, r3, CameraSession.m5070$$Nest$fgetjpegOrientation(r0)));
            int r32 = ((WindowManager) ApplicationLoader.applicationContext.getSystemService("window")).getDefaultDisplay().getRotation();
            if (CameraSession.m5072$$Nest$fgetlastOrientation(this.this$0) != CameraSession.m5070$$Nest$fgetjpegOrientation(this.this$0)) goto L14;
            if (r32 != CameraSession.m5071$$Nest$fgetlastDisplayOrientation(this.this$0)) goto L14;
            return;
        L14:
            if (CameraSession.m5069$$Nest$fgetisVideo(this.this$0) == true) goto L16;
            this.this$0.configurePhotoCamera();
        L16:
            CameraSession.m5075$$Nest$fputlastDisplayOrientation(this.this$0, r32);
            CameraSession r2 = this.this$0;
            CameraSession.m5076$$Nest$fputlastOrientation(r2, CameraSession.m5070$$Nest$fgetjpegOrientation(r2));
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$BSjrOWlJ0XlSz8pFjO9V6cBNhHE(boolean r0, Camera r1) {
    }

    /* renamed from: -$$Nest$fgetinitied, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5068$$Nest$fgetinitied(CameraSession r0) {
        return r0.initied;
    }

    /* renamed from: -$$Nest$fgetisVideo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5069$$Nest$fgetisVideo(CameraSession r0) {
        return r0.isVideo;
    }

    /* renamed from: -$$Nest$fgetjpegOrientation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5070$$Nest$fgetjpegOrientation(CameraSession r0) {
        return r0.jpegOrientation;
    }

    /* renamed from: -$$Nest$fgetlastDisplayOrientation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5071$$Nest$fgetlastDisplayOrientation(CameraSession r0) {
        return r0.lastDisplayOrientation;
    }

    /* renamed from: -$$Nest$fgetlastOrientation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5072$$Nest$fgetlastOrientation(CameraSession r0) {
        return r0.lastOrientation;
    }

    /* renamed from: -$$Nest$fgetorientationEventListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ OrientationEventListener m5073$$Nest$fgetorientationEventListener(CameraSession r0) {
        return r0.orientationEventListener;
    }

    /* renamed from: -$$Nest$fputjpegOrientation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5074$$Nest$fputjpegOrientation(CameraSession r0, int r1) {
        r0.jpegOrientation = r1;
    }

    /* renamed from: -$$Nest$fputlastDisplayOrientation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5075$$Nest$fputlastDisplayOrientation(CameraSession r0, int r1) {
        r0.lastDisplayOrientation = r1;
    }

    /* renamed from: -$$Nest$fputlastOrientation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5076$$Nest$fputlastOrientation(CameraSession r0, int r1) {
        r0.lastOrientation = r1;
    }

    /* renamed from: -$$Nest$mroundOrientation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5077$$Nest$mroundOrientation(CameraSession r0, int r1, int r2) {
        return r0.roundOrientation(r1, r2);
    }

    public CameraSession(CameraInfo r3, Size r4, Size r5, int r6, boolean r7) {
        this.lastOrientation = -1;
        this.lastDisplayOrientation = -1;
        this.flipFront = true;
        this.availableFlashModes = new ArrayList();
        this.infoCameraId = -1;
        this.info = new Camera.CameraInfo();
        this.autoFocusCallback = new CameraSession$$ExternalSyntheticLambda0();
        this.previewSize = r4;
        this.pictureSize = r5;
        this.pictureFormat = r6;
        this.cameraInfo = r3;
        this.isRound = r7;
        SharedPreferences r32 = ApplicationLoader.applicationContext.getSharedPreferences("camera", 0);
        if (this.cameraInfo.frontCamera == 0) goto L5;
        String r42 = "flashMode_front";
    L6:
        this.currentFlashMode = r32.getString(r42, "off");
        AnonymousClass1 r33 = new AnonymousClass1(this, ApplicationLoader.applicationContext);
        this.orientationEventListener = r33;
        boolean r34 = r33.canDetectOrientation();
        OrientationEventListener r43 = this.orientationEventListener;
        if (r34 == false) goto L10;
        r43.enable();
        return;
    L10:
        r43.disable();
        this.orientationEventListener = null;
        return;
    L5:
        r42 = "flashMode";
        goto L6
    }

    private int getDisplayOrientation(Camera.CameraInfo r6, boolean r7) {
        int r5 = ((WindowManager) ApplicationLoader.applicationContext.getSystemService("window")).getDefaultDisplay().getRotation();
        int r3 = 0;
        if (r5 == 0) goto L13;
        if (r5 != 1) goto L6;
        r3 = 90;
        goto L13
    L6:
        if (r5 != 2) goto L8;
        r3 = 180;
        goto L13
    L8:
        if (r5 != 3) goto L13;
        r3 = 270;
    L13:
        int r52 = r6.facing;
        int r62 = r6.orientation;
        if (r52 != 1) goto L28;
        int r53 = (360 - ((r62 + r3) % 360)) % 360;
        if (r7 == true) goto L19;
        if (r53 != 90) goto L19;
        r53 = 270;
    L19:
        if (r7 == false) goto L21;
    L26:
        return r53;
    L21:
        if ("Huawei".equals(Build.MANUFACTURER) == false) goto L26;
        if ("angler".equals(Build.PRODUCT) == false) goto L26;
        if (r53 != 270) goto L26;
        return 90;
    L28:
        return ((r62 - r3) + 360) % 360;
    }

    private int getHigh() {
        if ("LGE".equals(Build.MANUFACTURER) == true) goto L5;
        return 1;
    L5:
        if ("g3_tmo_us".equals(Build.PRODUCT) == false) goto L10;
        return 4;
    L10:
        return 1;
    }

    private int roundOrientation(int r2, int r3) {
        if (r3 == (-1)) goto L8;
        int r1 = Math.abs(r2 - r3);
        if (Math.min(r1, 360 - r1) >= 50) goto L8;
        return r3;
    L8:
        return (((r2 + 45) / 90) * 90) % 360;
    }

    private void updateCameraInfo() {
        if (this.infoCameraId == this.cameraInfo.getCameraId()) goto L6;
        int r0 = this.cameraInfo.getCameraId();
        this.infoCameraId = r0;
        Camera.getCameraInfo(r0, this.info);
        return;
    }

    public void checkFlashMode(String r4) {
        if (this.availableFlashModes.contains(this.currentFlashMode) == false) goto L5;
        return;
    L5:
        this.currentFlashMode = r4;
        if (this.isRound == false) goto L9;
        configureRoundCamera(false);
        return;
    L9:
        configurePhotoCamera();
        SharedPreferences.Editor r0 = ApplicationLoader.applicationContext.getSharedPreferences("camera", 0).edit();
        if (this.cameraInfo.frontCamera == 0) goto L12;
        String r3 = "flashMode_front";
    L13:
        r0.putString(r3, r4).commit();
        return;
    L12:
        r3 = "flashMode";
        goto L13
    }

    public void configurePhotoCamera() {
        Camera r1 = this.cameraInfo.camera;     // Catch: Throwable -> L52
        if (r1 == null) goto L63;
        Camera.Parameters r2 = r1.getParameters();     // Catch: Exception -> L7 Throwable -> L52
    L9:
        updateCameraInfo();     // Catch: Throwable -> L52
        updateRotation();     // Catch: Throwable -> L52
        int r3 = this.currentOrientation - this.displayOrientation;     // Catch: Throwable -> L52
        this.diffOrientation = r3;     // Catch: Throwable -> L52
        if (r3 >= 0) goto L12;
        this.diffOrientation = r3 + 360;     // Catch: Throwable -> L52
    L12:
        if (r2 == null) goto L64;
        r2.setPreviewSize(this.previewSize.getWidth(), this.previewSize.getHeight());     // Catch: Throwable -> L52
        r2.setPictureSize(this.pictureSize.getWidth(), this.pictureSize.getHeight());     // Catch: Throwable -> L52
        r2.setPictureFormat(this.pictureFormat);     // Catch: Throwable -> L52
        r2.setJpegQuality(100);     // Catch: Throwable -> L52
        r2.setJpegThumbnailQuality(100);     // Catch: Throwable -> L52
        int r32 = r2.getMaxZoom();     // Catch: Throwable -> L52
        this.maxZoom = r32;     // Catch: Throwable -> L52
        r2.setZoom((int) (this.currentZoom * r32));     // Catch: Throwable -> L52
        if (this.optimizeForBarcode == false) goto L24;
        List<String> r33 = r2.getSupportedSceneModes();     // Catch: Throwable -> L52
        if (r33 == null) goto L21;
        if (r33.contains("barcode") == false) goto L21;
        r2.setSceneMode("barcode");     // Catch: Throwable -> L52
    L21:
        if (r2.getSupportedFocusModes().contains("continuous-video") == false) goto L26;
        r2.setFocusMode("continuous-video");     // Catch: Throwable -> L52
    L26:
        int r0 = this.jpegOrientation;     // Catch: Throwable -> L52
        boolean r4 = false;
        if (r0 == (-1)) goto L33;
        Camera.CameraInfo r34 = this.info;     // Catch: Throwable -> L52
        if (r34.facing != 1) goto L31;
        int r35 = ((r34.orientation - r0) + 360) % 360;     // Catch: Throwable -> L52
    L61:
        r2.setRotation(r35);     // Catch: Exception -> L55 Throwable -> L52
        int r02 = this.info.facing;     // Catch: Exception -> L55 Throwable -> L52
        int r6 = this.displayOrientation;
        if (r02 == 1) goto L39;
        if (r6 != r35) goto L44;
        r4 = true;
    L44:
        this.sameTakePictureOrientation = r4;     // Catch: Exception -> L55 Throwable -> L52
        goto L46
    L39:
        if (((360 - r6) % 360) != r35) goto L41;
        r4 = true;
    L41:
        this.sameTakePictureOrientation = r4;     // Catch: Exception -> L55 Throwable -> L52
    L46:
        if (this.useTorch == false) goto L48;
        String r7 = "torch";
    L49:
        r2.setFlashMode(r7);     // Catch: Throwable -> L52
        r1.setParameters(r2);     // Catch: Throwable -> L52 Exception -> L56
        goto L66
    L65:
        return;
    L66:
        return;
    L48:
        r7 = this.currentFlashMode;     // Catch: Throwable -> L52
        goto L49
    L31:
        r35 = (r34.orientation + r0) % 360;     // Catch: Throwable -> L52
        goto L61
    L33:
        r35 = 0;
        goto L61
    L24:
        if (r2.getSupportedFocusModes().contains("continuous-picture") == false) goto L26;
        r2.setFocusMode("continuous-picture");     // Catch: Throwable -> L52
        goto L26
    L64:
        return;
    L7:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L52
        r2 = null;
        goto L9
    L63:
        return;
    L52:
        th = move-exception;
        FileLog.e(th);
    }

    public void configureRecorder(int r6, MediaRecorder r7) {
        updateCameraInfo();
        int r0 = this.jpegOrientation;
        if (r0 == (-1)) goto L8;
        Camera.CameraInfo r1 = this.info;
        if (r1.facing != 1) goto L7;
        int r12 = ((r1.orientation - r0) + 360) % 360;
    L9:
        r7.setOrientationHint(r12);
        int r02 = getHigh();
        boolean r13 = CamcorderProfile.hasProfile(this.cameraInfo.cameraId, r02);
        boolean r4 = CamcorderProfile.hasProfile(this.cameraInfo.cameraId, 0);
        if (r13 == false) goto L14;
        if (r6 == 1) goto L13;
        if (r4 == true) goto L14;
    L13:
        r7.setProfile(CamcorderProfile.get(this.cameraInfo.cameraId, r02));
    L16:
        this.isVideo = true;
        return;
    L14:
        if (r4 == false) goto L18;
        r7.setProfile(CamcorderProfile.get(this.cameraInfo.cameraId, 0));
        goto L16
    L18:
        Segment$$ExternalSyntheticBUOutline1.m("cannot find valid CamcorderProfile");
        return;
    L7:
        r12 = (r1.orientation + r0) % 360;
        goto L9
    L8:
        r12 = 0;
        goto L9
    }

    public boolean configureRoundCamera(boolean r9) {
        this.isVideo = true;     // Catch: Throwable -> L7
        Camera r4 = this.cameraInfo.camera;     // Catch: Throwable -> L7
        if (r4 != null) goto L64;
    L58:
        return true;
    L64:
        Camera.Parameters r5 = r4.getParameters();     // Catch: Throwable -> L7 Exception -> L9
    L11:
        updateCameraInfo();     // Catch: Throwable -> L7
        updateRotation();     // Catch: Throwable -> L7
        if (r5 == null) goto L58;
        if (r9 == true) goto L16;
    L18:
        r5.setPreviewSize(this.previewSize.getWidth(), this.previewSize.getHeight());     // Catch: Throwable -> L7
        if (r9 == true) goto L21;
    L23:
        r5.setPictureSize(this.pictureSize.getWidth(), this.pictureSize.getHeight());     // Catch: Throwable -> L7
        r5.setPictureFormat(this.pictureFormat);     // Catch: Throwable -> L7
        r5.setRecordingHint(true);     // Catch: Throwable -> L7
        this.maxZoom = r5.getMaxZoom();     // Catch: Throwable -> L7
        if (r5.getSupportedFocusModes().contains("continuous-video") == false) goto L27;
        r5.setFocusMode("continuous-video");     // Catch: Throwable -> L7
    L29:
        int r92 = this.jpegOrientation;     // Catch: Throwable -> L7
        if (r92 == (-1)) goto L36;
        Camera.CameraInfo r0 = this.info;     // Catch: Throwable -> L7
        if (r0.facing != 1) goto L34;
        int r02 = ((r0.orientation - r92) + 360) % 360;     // Catch: Throwable -> L7
    L63:
        r5.setRotation(r02);     // Catch: Throwable -> L7 Exception -> L61
        int r93 = this.info.facing;     // Catch: Throwable -> L7 Exception -> L61
        int r1 = this.displayOrientation;
        if (r93 == 1) goto L42;
        if (r1 != r02) goto L48;
        boolean r94 = true;
    L49:
        this.sameTakePictureOrientation = r94;     // Catch: Throwable -> L7 Exception -> L61
        goto L50
    L48:
        r94 = false;
        goto L49
    L42:
        if (((360 - r1) % 360) != r02) goto L44;
        boolean r95 = true;
    L45:
        this.sameTakePictureOrientation = r95;     // Catch: Throwable -> L7 Exception -> L61
        goto L50
    L44:
        r95 = false;
    L50:
        r5.setFlashMode(this.currentFlashMode);     // Catch: Throwable -> L7
        r5.setZoom((int) (this.currentZoom * this.maxZoom));     // Catch: Throwable -> L7
        r4.setParameters(r5);     // Catch: Throwable -> L7 Exception -> L55
    L53:
        if (r5.getMaxNumMeteringAreas() <= 0) goto L58;
        this.meteringAreaSupported = true;     // Catch: Throwable -> L7
    L55:
        e = move-exception;
        throw new RuntimeException(e);     // Catch: Throwable -> L7
    L34:
        r02 = (r0.orientation + r92) % 360;     // Catch: Throwable -> L7
        goto L63
    L36:
        r02 = 0;
        goto L63
    L27:
        if (r5.getSupportedFocusModes().contains("auto") == false) goto L29;
        r5.setFocusMode("auto");     // Catch: Throwable -> L7
        goto L29
    L21:
        if (BuildVars.LOGS_ENABLED == false) goto L23;
        FileLog.d("set picture size = " + this.pictureSize.getWidth() + " " + this.pictureSize.getHeight());     // Catch: Throwable -> L7
        goto L23
    L16:
        if (BuildVars.LOGS_ENABLED == false) goto L18;
        FileLog.d("set preview size = " + this.previewSize.getWidth() + " " + this.previewSize.getHeight());     // Catch: Throwable -> L7
        goto L18
    L9:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L7
        r5 = null;
    L7:
        th = move-exception;
        FileLog.e(th);
        return false;
    }

    public void destroy() {
        this.initied = false;
        this.destroyed = true;
        OrientationEventListener r0 = this.orientationEventListener;
        if (r0 == null) goto L6;
        r0.disable();
        this.orientationEventListener = null;
        return;
    }

    public boolean flashAvailable() {
        Camera r3 = this.cameraInfo.camera;
        if (r3 != null) goto L23;
        return false;
    L23:
        Camera.Parameters r32 = r3.getParameters();     // Catch: Exception -> L7
    L9:
        if (r32 != null) goto L11;
        return false;
    L11:
        if (r32.getFlashMode() == null) goto L29;
        List<String> r33 = r32.getSupportedFlashModes();
        if (r33 != null) goto L16;
        return false;
    L16:
        if (r33.isEmpty() == false) goto L18;
        return false;
    L18:
        if (r33.size() != 1) goto L22;
        if (r33.get(0).equals("off") == false) goto L22;
        return false;
    L22:
        return true;
    L29:
        return false;
    L7:
        e = move-exception;
        FileLog.e(e);
        r32 = null;
        goto L9
    }

    public void focusToRect(Rect r6, Rect r7) {
        Camera r0 = this.cameraInfo.camera;     // Catch: Exception -> L18
        if (r0 == null) goto L27;
        r0.cancelAutoFocus();     // Catch: Exception -> L18
        Camera.Parameters r1 = r0.getParameters();     // Catch: Exception -> L7
    L9:
        if (r1 == null) goto L28;
        r1.setFocusMode("auto");     // Catch: Exception -> L18
        ArrayList r2 = new ArrayList();     // Catch: Exception -> L18
        r2.add(new Camera.Area(r6, 1000));     // Catch: Exception -> L18
        r1.setFocusAreas(r2);     // Catch: Exception -> L18
        if (this.meteringAreaSupported == false) goto L25;
        ArrayList r62 = new ArrayList();     // Catch: Exception -> L18
        r62.add(new Camera.Area(r7, 1000));     // Catch: Exception -> L18
        r1.setMeteringAreas(r62);     // Catch: Exception -> L18
    L25:
        r0.setParameters(r1);     // Catch: Exception -> L15
        r0.autoFocus(this.autoFocusCallback);     // Catch: Exception -> L15
    L29:
        return;
    L15:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L18
        return;
    L28:
        return;
    L7:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L18
        r1 = null;
        goto L9
    L27:
        return;
    L18:
        e = move-exception;
        FileLog.e(e);
    }

    public String getCurrentFlashMode() {
        return this.currentFlashMode;
    }

    public int getCurrentOrientation() {
        return this.currentOrientation;
    }

    public Camera.Size getCurrentPictureSize() {
        return this.cameraInfo.camera.getParameters().getPictureSize();
    }

    public Camera.Size getCurrentPreviewSize() {
        return this.cameraInfo.camera.getParameters().getPreviewSize();
    }

    public int getDisplayOrientation() {
        updateCameraInfo();     // Catch: Exception -> L4
        return getDisplayOrientation(this.info, true);
    L4:
        e = move-exception;
        FileLog.e(e);
        return 0;
    }

    public int getMaxZoom() {
        return this.maxZoom;
    }

    public String getNextFlashMode() {
        ArrayList<String> r0 = this.availableFlashModes;
        int r2 = 0;
    L4:
        if (r2 >= r0.size()) goto L15;
        if (r0.get(r2).equals(this.currentFlashMode) == true) goto L8;
        r2 = r2 + 1;
        goto L4
    L8:
        if (r2 >= (r0.size() - 1)) goto L12;
        return r0.get(r2 + 1);
    L12:
        return r0.get(0);
    L15:
        return this.currentFlashMode;
    }

    public int getWorldAngle() {
        return this.diffOrientation;
    }

    public boolean isFlipFront() {
        return this.flipFront;
    }

    public boolean isInitied() {
        return this.initied;
    }

    public boolean isSameTakePictureOrientation() {
        return this.sameTakePictureOrientation;
    }

    public void onStartRecord() {
        this.isVideo = true;
    }

    public void setCurrentFlashMode(String r4) {
        this.currentFlashMode = r4;
        if (this.isRound == false) goto L6;
        configureRoundCamera(false);
        return;
    L6:
        configurePhotoCamera();
        SharedPreferences.Editor r0 = ApplicationLoader.applicationContext.getSharedPreferences("camera", 0).edit();
        if (this.cameraInfo.frontCamera == 0) goto L9;
        String r3 = "flashMode_front";
    L10:
        r0.putString(r3, r4).commit();
        return;
    L9:
        r3 = "flashMode";
        goto L10
    }

    public void setFlipFront(boolean r1) {
        this.flipFront = r1;
    }

    public void setInitied() {
        this.initied = true;
    }

    public void setOneShotPreviewCallback(Camera.PreviewCallback r1) {
        CameraInfo r0 = this.cameraInfo;
        if (r0 == null) goto L11;
        Camera r02 = r0.camera;
        if (r02 == null) goto L12;
        r02.setOneShotPreviewCallback(r1);     // Catch: Exception -> L8
        return;
    L13:
        return;
    L12:
        return;
    }

    public void setOptimizeForBarcode(boolean r1) {
        this.optimizeForBarcode = r1;
        configurePhotoCamera();
    }

    public void setPreviewCallback(Camera.PreviewCallback r1) {
        this.cameraInfo.camera.setPreviewCallback(r1);
    }

    public void setTorchEnabled(boolean r2) {
        String r0 = this.currentFlashMode;     // Catch: Exception -> L14
        if (r2 == false) goto L5;
        String r22 = "torch";
    L6:
        this.currentFlashMode = r22;     // Catch: Exception -> L14
        if (TextUtils.equals(r0, r22) == false) goto L9;
        return;
    L9:
        if (this.isRound == false) goto L12;
        configureRoundCamera(false);     // Catch: Exception -> L14
        return;
    L12:
        configurePhotoCamera();     // Catch: Exception -> L14
        return;
    L5:
        r22 = "off";
    L14:
        e = move-exception;
        FileLog.e(e);
    }

    public void setZoom(float r2) {
        this.currentZoom = r2;
        if (this.isVideo == false) goto L8;
        if ("on".equals(this.currentFlashMode) == false) goto L8;
        this.useTorch = true;
    L8:
        if (this.isRound == false) goto L11;
        configureRoundCamera(false);
        return;
    L11:
        configurePhotoCamera();
    }

    public void stopVideoRecording() {
        this.isVideo = false;
        this.useTorch = false;
        configurePhotoCamera();
    }

    public void updateRotation() {
        if (this.cameraInfo == null) goto L49;
        updateCameraInfo();     // Catch: Throwable -> L40
        if (this.destroyed == false) goto L9;
        Camera r0 = null;
    L10:
        this.displayOrientation = getDisplayOrientation(this.info, true);
        int r3 = 0;
        if ("samsung".equals(Build.MANUFACTURER) == true) goto L13;
    L15:
        int r1 = this.displayOrientation;
        if (r1 == 0) goto L22;
        if (r1 != 1) goto L19;
        int r12 = 90;
    L26:
        Camera.CameraInfo r5 = this.info;
        if ((r5.orientation % 90) == 0) goto L30;
        r5.orientation = 0;
    L30:
        if (r5.facing != 1) goto L32;
        r3 = (360 - ((r5.orientation + r12) % 360)) % 360;
    L33:
        this.currentOrientation = r3;
        if (r0 != null) goto L44;
    L36:
        int r02 = this.currentOrientation - this.displayOrientation;
        this.diffOrientation = r02;
        if (r02 >= 0) goto L48;
        this.diffOrientation = r02 + 360;
        return;
    L48:
        return;
    L44:
        r0.setDisplayOrientation(r3);     // Catch: Throwable -> L43
        goto L36
    L32:
        r3 = ((r5.orientation - r12) + 360) % 360;
        goto L33
    L19:
        if (r1 != 2) goto L21;
        r12 = 180;
        goto L26
    L21:
        if (r1 != 3) goto L22;
        r12 = 270;
    L22:
        r12 = 0;
        goto L26
    L13:
        if ("sf2wifixx".equals(Build.PRODUCT) == false) goto L15;
    L9:
        r0 = this.cameraInfo.camera;
    L40:
        th = move-exception;
        FileLog.e(th);
        return;
    }
}
