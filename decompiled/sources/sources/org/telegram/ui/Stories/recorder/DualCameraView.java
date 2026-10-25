package org.telegram.ui.Stories.recorder;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Build;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.exteragram.messenger.utils.system.VibratorUtils;
import com.google.common.primitives.Floats;
import com.google.zxing.common.detector.MathUtils;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.camera.CameraSessionWrapper;
import org.telegram.messenger.camera.CameraView;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public abstract class DualCameraView extends CameraView {
    private static final int[] dualWhitelistByDevice = null;
    private static final int[] dualWhitelistByModel = null;
    private boolean allowRotation;
    private float angle;
    private boolean atBottom;
    private boolean atTop;
    private float cx;
    private float cy;
    private boolean doNotSpanRotation;
    private boolean down;
    private boolean dualAvailable;
    private boolean enabledSavedDual;
    private final Matrix finalMatrix;
    private boolean firstMeasure;
    private float h;
    private Matrix invMatrix;
    private Runnable lastFocusToPoint;
    private final PointF lastTouch;
    private float lastTouchDistance;
    private double lastTouchRotation;
    private Runnable longpressRunnable;
    private boolean multitouch;
    private float rotationDiff;
    private boolean snappedRotation;
    private long tapTime;
    private float tapX;
    private float tapY;
    private Matrix tempMatrix;
    private float[] tempPoint;
    private final Matrix toGL;
    private final Matrix toScreen;
    private final PointF touch;
    private final Matrix touchMatrix;
    private float[] vertex;
    private final float[] vertices;
    private float[] verticesDst;
    private float[] verticesSrc;
    private float w;

    public static /* synthetic */ void $r8$lambda$AdqB_SUIHnaWWsztIloMPEF2k_Y(DualCameraView r0) {
        r0.lambda$checkTap$2();
    }

    public static /* synthetic */ void $r8$lambda$BDUEjGDaDbdUbrccOYYisJzQ2iY(TLObject r0, TLRPC.TL_error r1) {
    }

    public static /* synthetic */ void $r8$lambda$hHIKwhTEUMu1Rhr8btm1m5vD7lw(DualCameraView r0) {
        r0.lambda$checkTap$1();
    }

    static {
        dualWhitelistByDevice = new int[]{1893745684, -215458996, -862041025, -1258375037, -1320049076, -215749424, 1901578030, -215451421, 1908491424, -1321491332, -1155551678, 1908524435, 976847578, -1489198134, 1910814392, -713271737, -2010722764, 1407170066, -821405251, -1394190955, -1394190055, 1407170066, 1407159934, 1407172057, 1231389747, -2076538925, 41497626, 846150482, -1198092731, -251277614, -2073158771, 1273004781};
        dualWhitelistByModel = new int[0];
    }

    public DualCameraView(Context r1, boolean r2, boolean r3) {
        super(r1, r2, r3);
        this.lastTouch = new PointF();
        this.touch = new PointF();
        this.touchMatrix = new Matrix();
        this.finalMatrix = new Matrix();
        this.tempPoint = new float[4];
        this.toScreen = new Matrix();
        this.toGL = new Matrix();
        this.firstMeasure = true;
        this.invMatrix = new Matrix();
        this.vertices = new float[2];
        this.tempMatrix = new Matrix();
        this.vertex = new float[2];
        this.dualAvailable = dualAvailableStatic(r1);
    }

    private boolean checkTap(MotionEvent r8) {
        if (r8.getAction() != 0) goto L12;
        this.tapTime = System.currentTimeMillis();
        this.tapX = r8.getX();
        this.tapY = r8.getY();
        this.lastFocusToPoint = null;
        Runnable r82 = this.longpressRunnable;
        if (r82 == null) goto L8;
        AndroidUtilities.cancelRunOnUIThread(r82);
        this.longpressRunnable = null;
    L8:
        if (isAtDual(this.tapX, this.tapY) == false) goto L31;
        DualCameraView$$ExternalSyntheticLambda1 r83 = new DualCameraView$$ExternalSyntheticLambda1(this);
        this.longpressRunnable = r83;
        AndroidUtilities.runOnUIThread(r83, ViewConfiguration.getLongPressTimeout());
        return true;
    L31:
        return false;
    L12:
        if (r8.getAction() != 1) goto L25;
        if ((System.currentTimeMillis() - this.tapTime) <= ViewConfiguration.getTapTimeout()) goto L16;
    L21:
        this.tapTime = -1;
        Runnable r84 = this.longpressRunnable;
        if (r84 == null) goto L32;
        AndroidUtilities.cancelRunOnUIThread(r84);
        this.longpressRunnable = null;
        return false;
    L32:
        return false;
    L16:
        if (MathUtils.distance(this.tapX, this.tapY, r8.getX(), r8.getY()) >= AndroidUtilities.dp(10.0f)) goto L21;
        if (isAtDual(this.tapX, this.tapY) == false) goto L20;
        switchCamera();
        this.lastFocusToPoint = null;
        goto L21
    L20:
        this.lastFocusToPoint = new DualCameraView$$ExternalSyntheticLambda2(this);
        goto L21
    L25:
        if (r8.getAction() != 3) goto L34;
        this.tapTime = -1;
        this.lastFocusToPoint = null;
        Runnable r85 = this.longpressRunnable;
        if (r85 == null) goto L35;
        AndroidUtilities.cancelRunOnUIThread(r85);
        this.longpressRunnable = null;
        return false;
    L35:
        return false;
    L34:
        return false;
    }

    public static boolean dualAvailableDefault(Context r5, boolean r6) {
        int r1 = 0;
        if (SharedConfig.getDevicePerformanceClass() >= 1) goto L5;
    L9:
        boolean r0 = false;
    L10:
        if (r0 == false) goto L34;
        if (r5 != null) goto L13;
    L15:
        boolean r52 = false;
    L16:
        if (r52 == true) goto L33;
        if (r6 == false) goto L33;
        int r62 = (Build.MANUFACTURER + " " + Build.DEVICE).toUpperCase().hashCode();
        int r02 = 0;
    L19:
        int[] r3 = dualWhitelistByDevice;
        if (r02 >= r3.length) goto L25;
        if (r3[r02] == r62) goto L23;
        r02 = r02 + 1;
        goto L19
    L23:
        r52 = true;
    L25:
        if (r52 == true) goto L33;
        int r63 = (Build.MANUFACTURER + Build.MODEL).toUpperCase().hashCode();
    L27:
        int[] r03 = dualWhitelistByModel;
        if (r1 >= r03.length) goto L33;
        if (r03[r1] == r63) goto L31;
        r1 = r1 + 1;
        goto L27
    L31:
        return true;
    L33:
        return r52;
    L13:
        if (r5.getPackageManager().hasSystemFeature("android.hardware.camera.concurrent") == false) goto L15;
        r52 = true;
        goto L16
    L34:
        return r0;
    L5:
        if (Camera.getNumberOfCameras() <= 1) goto L9;
        if (SharedConfig.allowPreparingHevcPlayers() == false) goto L9;
        r0 = true;
        goto L10
    }

    public static boolean dualAvailableStatic(Context r2) {
        return MessagesController.getGlobalMainSettings().getBoolean("dual_available", dualAvailableDefault(r2, true));
    }

    private void extractPointsData(Matrix r10) {
        float[] r0 = this.vertices;
        r0[0] = 0.0f;
        r0[1] = 0.0f;
        r10.mapPoints(r0);
        float[] r02 = this.vertices;
        this.cx = r02[0];
        this.cy = r02[1];
        r02[0] = 1.0f;
        r02[1] = 0.0f;
        r10.mapPoints(r02);
        float[] r03 = this.vertices;
        this.angle = (float) Math.toDegrees(Math.atan2(r03[1] - this.cy, r03[0] - this.cx));
        float r04 = this.cx;
        float r5 = this.cy;
        float[] r6 = this.vertices;
        this.w = MathUtils.distance(r04, r5, r6[0], r6[1]) * 2.0f;
        float[] r05 = this.vertices;
        r05[0] = 0.0f;
        r05[1] = 1.0f;
        r10.mapPoints(r05);
        float r102 = this.cx;
        float r06 = this.cy;
        float[] r2 = this.vertices;
        this.h = MathUtils.distance(r102, r06, r2[0], r2[1]) * 2.0f;
    }

    private Matrix getSavedDualMatrix() {
        String r4 = MessagesController.getGlobalMainSettings().getString("dualmatrix", null);
        if (r4 != null) goto L5;
        return null;
    L5:
        String[] r42 = r4.split(";");
        if (r42.length == 9) goto L8;
        return null;
    L8:
        float[] r0 = new float[9];
        int r2 = 0;
    L10:
        if (r2 >= r42.length) goto L16;
        r0[r2] = Float.parseFloat(r42[r2]);     // Catch: Exception -> L13
        r2 = r2 + 1;
    L13:
        e = move-exception;
        FileLog.e(e);
        return null;
    L16:
        Matrix r43 = new Matrix();
        r43.setValues(r0);
        return r43;
    }

    private /* synthetic */ void lambda$checkTap$1() {
        if (this.tapTime <= 0) goto L10;
        dualToggleShape();
        performHapticFeedback(VibratorUtils.getType(0), 1);     // Catch: Exception -> L7
        return;
    L11:
        return;
    }

    private /* synthetic */ void lambda$checkTap$2() {
        focusToPoint((int) this.tapX, (int) this.tapY);
    }

    private void log(boolean r9) {
        int r1 = 0;
        boolean r0 = dualAvailableDefault(ApplicationLoader.applicationContext, false);
        if (MessagesController.getInstance(UserConfig.selectedAccount).collectDeviceStats == false) goto L12;
        TLRPC.TL_help_saveAppLog r2 = new TLRPC.TL_help_saveAppLog();     // Catch: Exception -> L9
        TLRPC.TL_inputAppEvent r3 = new TLRPC.TL_inputAppEvent();     // Catch: Exception -> L9
        r3.time = ConnectionsManager.getInstance(UserConfig.selectedAccount).getCurrentTime();     // Catch: Exception -> L9
        r3.type = "android_dual_camera";     // Catch: Exception -> L9
        TLRPC.TL_jsonObject r4 = new TLRPC.TL_jsonObject();     // Catch: Exception -> L9
        TLRPC.TL_jsonObjectValue r5 = new TLRPC.TL_jsonObjectValue();     // Catch: Exception -> L9
        r5.key = "device";     // Catch: Exception -> L9
        TLRPC.TL_jsonString r6 = new TLRPC.TL_jsonString();     // Catch: Exception -> L9
        r6.value = _UrlKt.FRAGMENT_ENCODE_SET + Build.MANUFACTURER + Build.MODEL;     // Catch: Exception -> L9
        r5.value = r6;     // Catch: Exception -> L9
        r4.value.add(r5);     // Catch: Exception -> L9
        r3.data = r4;     // Catch: Exception -> L9
        if (r0 == false) goto L7;
        r1 = 2;
    L7:
        r3.peer = (r9 ? 1 : 0) | r1;     // Catch: Exception -> L9
        r2.events.add(r3);     // Catch: Exception -> L9
        ConnectionsManager.getInstance(UserConfig.selectedAccount).sendRequest(r2, new DualCameraView$$ExternalSyntheticLambda0());     // Catch: Exception -> L9
        return;
    L13:
        return;
    }

    private void resetSavedDual() {
        MessagesController.getGlobalMainSettings().edit().putBoolean("dualcam", false).remove("dualmatrix").apply();
    }

    public static boolean roundDualAvailableDefault(Context r2) {
        if (SharedConfig.getDevicePerformanceClass() >= 1) goto L5;
        return false;
    L5:
        if (Camera.getNumberOfCameras() > 1) goto L7;
        return false;
    L7:
        if (SharedConfig.allowPreparingHevcPlayers() == false) goto L15;
        if (r2 != null) goto L10;
        return false;
    L10:
        if (r2.getPackageManager().hasSystemFeature("android.hardware.camera.concurrent") == false) goto L17;
        return true;
    L17:
        return false;
    L15:
        return false;
    }

    public static boolean roundDualAvailableStatic(Context r2) {
        return MessagesController.getGlobalMainSettings().getBoolean("rounddual_available", roundDualAvailableDefault(r2));
    }

    private void saveDual() {
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        r0.putBoolean("dualcam", isDual());
        if (isDual() == false) goto L5;
        float[] r1 = new float[9];
        getDualPosition().getValues(r1);
        r0.putString("dualmatrix", Floats.join(";", r1));
    L6:
        r0.apply();
        return;
    L5:
        r0.remove("dualmatrix");
        goto L6
    }

    private void setupDualMatrix() {
        Matrix r0 = getDualPosition();
        r0.reset();
        Matrix r1 = getSavedDualMatrix();
        if (r1 == null) goto L5;
        r0.set(r1);
    L6:
        updateDualPosition();
        return;
    L5:
        r0.postConcat(this.toScreen);
        float r12 = getMeasuredWidth() * 0.43f;
        float r2 = Math.min(getMeasuredWidth(), getMeasuredWidth()) * 0.025f;
        r0.postScale(r12 / getMeasuredWidth(), (getMeasuredHeight() * 0.43f) / getMeasuredHeight());
        r0.postTranslate((getMeasuredWidth() - r2) - r12, r2);
        r0.postConcat(this.toGL);
        goto L6
    }

    private void setupToScreenMatrix() {
        this.toScreen.reset();
        this.toScreen.postTranslate(1.0f, -1.0f);
        this.toScreen.postScale(getMeasuredWidth() / 2.0f, (-getMeasuredHeight()) / 2.0f);
        this.toScreen.invert(this.toGL);
    }

    private boolean touchEvent(MotionEvent r21) {
        boolean r2 = checkTap(r21);
        if (isDual() == false) goto L119;
        Matrix r3 = getDualPosition();
        if (r21.getPointerCount() <= 1) goto L7;
        boolean r4 = true;
    L8:
        PointF r7 = this.touch;
        if (r4 == false) goto L11;
        r7.x = (r21.getX(0) + r21.getX(1)) / 2.0f;
        this.touch.y = (r21.getY(0) + r21.getY(1)) / 2.0f;
        float r72 = MathUtils.distance(r21.getX(0), r21.getY(0), r21.getX(1), r21.getY(1));
        double r10 = Math.atan2(r21.getY(1) - r21.getY(0), r21.getX(1) - r21.getX(0));
    L13:
        if (this.multitouch == r4) goto L15;
        PointF r12 = this.lastTouch;
        PointF r13 = this.touch;
        r12.x = r13.x;
        r12.y = r13.y;
        this.lastTouchDistance = r72;
        this.lastTouchRotation = r10;
        this.multitouch = r4;
    L15:
        PointF r42 = this.touch;
        float r122 = r42.x;
        float r43 = r42.y;
        PointF r132 = this.lastTouch;
        float r14 = r132.x;
        float r133 = r132.y;
        if (r21.getAction() != 0) goto L18;
        this.touchMatrix.set(r3);
        float r16 = 2.0f;
        this.touchMatrix.postConcat(this.toScreen);
        this.rotationDiff = 0.0f;
        this.snappedRotation = false;
        this.doNotSpanRotation = false;
        Matrix r8 = this.touchMatrix;
        PointF r15 = this.touch;
        float r17 = 0.0f;
        this.down = isPointInsideDual(r8, r15.x, r15.y);
    L20:
        if (r21.getAction() != 2) goto L98;
        if (this.down == false) goto L98;
        if (MathUtils.distance(r122, r43, r14, r133) <= AndroidUtilities.dp(r16)) goto L29;
        Runnable r82 = this.longpressRunnable;
        if (r82 == null) goto L29;
        AndroidUtilities.cancelRunOnUIThread(r82);
        this.longpressRunnable = null;
    L29:
        if (r21.getPointerCount() > 1) goto L31;
        float r18 = 90.0f;
    L61:
        this.touchMatrix.postTranslate(r122 - r14, r43 - r133);
        this.finalMatrix.set(this.touchMatrix);
        extractPointsData(this.finalMatrix);
        float r44 = (Math.round(this.angle / r18) * r18) - this.angle;
        if (this.allowRotation == true) goto L64;
    L71:
        float r45 = this.cx;
        if (r45 >= r17) goto L74;
        float r9 = r17;
        this.finalMatrix.postTranslate(-r45, r9);
    L77:
        float r46 = this.cy;
        if (r46 >= r9) goto L81;
        this.finalMatrix.postTranslate(r9, -r46);
    L83:
        this.finalMatrix.postConcat(this.toGL);
        r3.set(this.finalMatrix);
        updateDualPosition();
        float r32 = this.cy;
        if (Math.min(r32, r32 - (this.h / r16)) >= AndroidUtilities.dp(66.0f)) goto L86;
        boolean r33 = true;
    L87:
        float r83 = this.cy;
        if (Math.max(r83, (this.h / r16) + r83) <= (getHeight() - AndroidUtilities.dp(66.0f))) goto L90;
        boolean r47 = true;
    L92:
        if (this.atTop == r33) goto L95;
        this.atTop = r33;
        onEntityDraggedTop(r33);
    L95:
        if (this.atBottom == r47) goto L98;
        this.atBottom = r47;
        onEntityDraggedBottom(r47);
        goto L98
    L90:
        r47 = false;
        goto L92
    L86:
        r33 = false;
        goto L87
    L81:
        if (r46 <= (getHeight() - AndroidUtilities.dp(150.0f))) goto L83;
        this.finalMatrix.postTranslate(0.0f, (getHeight() - AndroidUtilities.dp(150.0f)) - this.cy);
        goto L83
    L74:
        r9 = r17;
        if (r45 <= getWidth()) goto L77;
        this.finalMatrix.postTranslate(getWidth() - this.cx, r9);
        goto L77
    L64:
        if (this.doNotSpanRotation == true) goto L71;
        if (Math.abs(r44) >= 5.0f) goto L70;
        this.finalMatrix.postRotate(r44, this.cx, this.cy);
        if (this.snappedRotation == true) goto L71;
        AndroidUtilities.vibrateCursor(this);
        this.snappedRotation = true;
        goto L71
    L70:
        this.snappedRotation = false;
        goto L71
    L31:
        if (this.lastTouchDistance == r17) goto L40;
        extractPointsData(this.touchMatrix);
        float r84 = r72 / this.lastTouchDistance;
        r18 = 90.0f;
        if ((this.w * r84) <= (getWidth() * 0.7f)) goto L37;
        float r85 = getWidth() * 0.7f;
        float r92 = this.w;
    L35:
        r84 = r85 / r92;
    L39:
        this.touchMatrix.postScale(r84, r84, r122, r43);
    L41:
        float r86 = (float) Math.toDegrees(r10 - this.lastTouchRotation);
        float r93 = this.rotationDiff + r86;
        this.rotationDiff = r93;
        if (this.allowRotation == true) goto L58;
        if (Math.abs(r93) <= 20.0f) goto L46;
        boolean r94 = true;
    L47:
        this.allowRotation = r94;
        if (r94 == true) goto L55;
        extractPointsData(this.touchMatrix);
        if (((Math.round(this.angle / r18) * r18) - this.angle) <= 20.0f) goto L52;
        boolean r95 = true;
    L53:
        this.allowRotation = r95;
        goto L55
    L52:
        r95 = false;
    L55:
        if (this.snappedRotation == true) goto L58;
        AndroidUtilities.vibrateCursor(this);
        this.snappedRotation = true;
        goto L58
    L46:
        r94 = false;
    L58:
        if (this.allowRotation == false) goto L61;
        this.touchMatrix.postRotate(r86, r122, r43);
        goto L61
    L37:
        if ((this.w * r84) >= (getWidth() * 0.2f)) goto L39;
        r85 = getWidth() * 0.2f;
        r92 = this.w;
        goto L35
    L40:
        r18 = 90.0f;
    L98:
        if (r21.getAction() != 1) goto L106;
        this.allowRotation = false;
        this.rotationDiff = 0.0f;
        this.snappedRotation = false;
        invalidate();
        this.down = false;
        if (this.atTop == false) goto L103;
        this.atTop = false;
        onEntityDraggedTop(false);
    L103:
        if (this.atBottom == false) goto L113;
        this.atBottom = false;
        onEntityDraggedBottom(false);
    L113:
        PointF r1 = this.lastTouch;
        PointF r34 = this.touch;
        r1.x = r34.x;
        r1.y = r34.y;
        this.lastTouchDistance = r72;
        this.lastTouchRotation = r10;
        if (this.down == true) goto L118;
        if (r2 == true) goto L118;
        return false;
    L118:
        return true;
    L106:
        if (r21.getAction() != 3) goto L113;
        this.down = false;
        if (this.atTop == false) goto L111;
        this.atTop = false;
        onEntityDraggedTop(false);
    L111:
        if (this.atBottom == false) goto L113;
        this.atBottom = false;
        onEntityDraggedBottom(false);
        goto L113
    L18:
        r16 = 2.0f;
        r17 = 0.0f;
        goto L20
    L11:
        r7.x = r21.getX(0);
        this.touch.y = r21.getY(0);
        r10 = 0.0d;
        r72 = 0.0f;
        goto L13
    L7:
        r4 = false;
        goto L8
    L119:
        return r2;
    }

    public void allowToTapFocus() {
        Runnable r0 = this.lastFocusToPoint;
        if (r0 == null) goto L6;
        r0.run();
        this.lastFocusToPoint = null;
        return;
    }

    public void clearTapFocus() {
        this.lastFocusToPoint = null;
        this.tapTime = -1;
    }

    @Override // org.telegram.messenger.camera.CameraView
    public void destroy(boolean r1, Runnable r2) {
        saveDual();
        super.destroy(r1, r2);
    }

    public boolean dualAvailable() {
        return this.dualAvailable;
    }

    public boolean isAtDual(float r5, float r6) {
        if (isDual() == true) goto L5;
        return false;
    L5:
        float[] r0 = this.vertex;
        r0[0] = r5;
        r0[1] = r6;
        this.toGL.mapPoints(r0);
        getDualPosition().invert(this.invMatrix);
        this.invMatrix.mapPoints(this.vertex);
        int r62 = getDualShape() % 3;
        if (r62 == 0) goto L11;
        if (r62 == 1) goto L11;
        if (r62 == 3) goto L11;
        float r63 = 1.0f;
    L12:
        float[] r4 = this.vertex;
        float r02 = r4[0];
        if (r02 >= (-1.0f)) goto L15;
    L21:
        return false;
    L15:
        if (r02 > 1.0f) goto L21;
        float r42 = r4[1];
        if (r42 < (-r63)) goto L21;
        if (r42 > r63) goto L21;
        return true;
    L11:
        r63 = 0.5625f;
        goto L12
    }

    public boolean isDualTouch() {
        return this.down;
    }

    public boolean isPointInsideDual(Matrix r34, float r35, float r36) {
        if (this.verticesSrc != null) goto L6;
        this.verticesSrc = new float[8];
    L6:
        if (this.verticesDst != null) goto L8;
        this.verticesDst = new float[8];
    L8:
        int r1 = getDualShape() % 3;
        if (r1 == 0) goto L14;
        if (r1 == 1) goto L14;
        if (r1 == 3) goto L14;
        float r12 = 1.0f;
    L15:
        float[] r5 = this.verticesSrc;
        r5[0] = -1.0f;
        float r8 = -r12;
        r5[1] = r8;
        r5[2] = 1.0f;
        r5[3] = r8;
        r5[4] = 1.0f;
        r5[5] = r12;
        r5[6] = -1.0f;
        r5[7] = r12;
        r34.mapPoints(this.verticesDst, r5);
        float[] r13 = this.verticesDst;
        float r52 = r13[0];
        float r11 = r13[2];
        float r122 = (r52 - r11) * (r52 - r11);
        float r53 = r13[1];
        float r14 = r13[3];
        double r112 = Math.sqrt(r122 + ((r53 - r14) * (r53 - r14)));
        float[] r15 = this.verticesDst;
        float r54 = r15[2];
        float r132 = r15[4];
        float r142 = (r54 - r132) * (r54 - r132);
        float r55 = r15[3];
        float r16 = r15[5];
        double r133 = Math.sqrt(r142 + ((r55 - r16) * (r55 - r16)));
        float[] r17 = this.verticesDst;
        float r56 = r17[4];
        float r152 = r17[6];
        float r162 = (r56 - r152) * (r56 - r152);
        float r57 = r17[5];
        float r18 = r17[7];
        double r19 = Math.sqrt(r162 + ((r57 - r18) * (r57 - r18)));
        float[] r3 = this.verticesDst;
        float r163 = r3[6];
        float r172 = r3[0];
        float r182 = (r163 - r172) * (r163 - r172);
        float r164 = r3[7];
        float r32 = r3[1];
        double r33 = Math.sqrt(r182 + ((r164 - r32) * (r164 - r32)));
        float[] r58 = this.verticesDst;
        float r173 = r58[0];
        float r59 = r58[1];
        double r510 = Math.sqrt(((r173 - r35) * (r173 - r35)) + ((r59 - r36) * (r59 - r36)));
        float[] r7 = this.verticesDst;
        float r9 = r7[2];
        float r72 = r7[3];
        double r73 = Math.sqrt(((r9 - r35) * (r9 - r35)) + ((r72 - r36) * (r72 - r36)));
        float[] r92 = this.verticesDst;
        float r192 = r92[4];
        float r93 = r92[5];
        double r94 = Math.sqrt(((r192 - r35) * (r192 - r35)) + ((r93 - r36) * (r93 - r36)));
        float[] r0 = this.verticesDst;
        float r113 = r0[6];
        float r02 = r0[7];
        double r114 = Math.sqrt(((r113 - r35) * (r113 - r35)) + ((r02 - r36) * (r02 - r36)));
        double r21 = ((r112 + r510) + r73) / 2.0d;
        double r25 = ((r133 + r73) + r94) / 2.0d;
        double r27 = ((r19 + r94) + r114) / 2.0d;
        double r29 = ((r33 + r114) + r510) / 2.0d;
        if (((((Math.sqrt((((r21 - r112) * r21) * (r21 - r510)) * (r21 - r73)) + Math.sqrt((((r25 - r133) * r25) * (r25 - r73)) * (r25 - r94))) + Math.sqrt((((r27 - r19) * r27) * (r27 - r94)) * (r27 - r114))) + Math.sqrt((((r29 - r33) * r29) * (r29 - r114)) * (r29 - r510))) - (r112 * r133)) >= 1.0d) goto L18;
        return true;
    L18:
        return false;
    L14:
        r12 = 0.5625f;
        goto L15
    }

    public boolean isSavedDual() {
        if (dualAvailableStatic(getContext()) == true) goto L5;
    L8:
        return false;
    L5:
        if (MessagesController.getGlobalMainSettings().getBoolean("dualcam", dualAvailableDefault(ApplicationLoader.applicationContext, false)) == false) goto L8;
        return true;
    }

    public void onCameraError() {
        resetSaved();
    }

    @Override // org.telegram.messenger.camera.CameraView
    public void onDualCameraSuccess() {
        saveDual();
        if (this.enabledSavedDual == false) goto L5;
        onSavedDualCameraSuccess();
    L5:
        log(true);
    }

    public abstract void onEntityDraggedBottom(boolean r1);

    public abstract void onEntityDraggedTop(boolean r1);

    @Override // org.telegram.messenger.camera.CameraView, org.telegram.messenger.camera.CameraController.ErrorCallback
    public void onError(int r3, Camera r4, CameraSessionWrapper r5) {
        if (isDual() == false) goto L9;
        if (dualAvailableDefault(getContext(), false) == true) goto L7;
        SharedPreferences.Editor r32 = MessagesController.getGlobalMainSettings().edit();
        this.dualAvailable = false;
        r32.putBoolean("dual_available", false).apply();
        new AlertDialog.Builder(getContext()).setTitle(LocaleController.getString(R.string.DualErrorTitle)).setMessage(LocaleController.getString(R.string.DualErrorMessage)).setPositiveButton(LocaleController.getString(R.string.OK), null).show();
    L7:
        log(false);
        toggleDual();
    L9:
        if (getCameraSession(0) != null) goto L11;
    L13:
        onCameraError();
        return;
    L11:
        if (getCameraSession(0).equals(r5) == false) goto L13;
        resetCamera();
        goto L13
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r3) {
        if (r3.getAction() != 0) goto L9;
        if (isAtDual(r3.getX(), r3.getY()) == false) goto L9;
        return touchEvent(r3);
    L9:
        return super.onInterceptTouchEvent(r3);
    }

    @Override // org.telegram.messenger.camera.CameraView, android.widget.FrameLayout, android.view.View
    public void onMeasure(int r1, int r2) {
        super.onMeasure(r1, r2);
        setupToScreenMatrix();
    }

    public abstract void onSavedDualCameraSuccess();

    @Override // org.telegram.messenger.camera.CameraView, android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture r2, int r3, int r4) {
        if (this.firstMeasure == true) goto L5;
    L8:
        super.onSurfaceTextureAvailable(r2, r3, r4);
        return;
    L5:
        if (isSavedDual() == false) goto L7;
        this.enabledSavedDual = true;
        setupDualMatrix();
        this.dual = true;
    L7:
        this.firstMeasure = false;
        goto L8
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r2) {
        boolean r0 = touchEvent(r2);
        if (super.onTouchEvent(r2) == true) goto L8;
        if (r0 == true) goto L10;
        return false;
    L10:
        return true;
    L8:
        return true;
    }

    public void resetSaved() {
        resetSavedDual();
    }

    @Override // org.telegram.messenger.camera.CameraView
    public void toggleDual() {
        if (isDual() == true) goto L8;
        if (dualAvailable() == true) goto L8;
        return;
    L8:
        if (isDual() == true) goto L10;
        setupDualMatrix();
    L11:
        super.toggleDual();
        return;
    L10:
        resetSaved();
        goto L11
    }
}
