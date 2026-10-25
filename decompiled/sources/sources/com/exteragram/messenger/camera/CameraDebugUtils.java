package com.exteragram.messenger.camera;

import android.content.Context;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.util.Range;
import androidx.camera.camera2.interop.Camera2CameraInfo;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.CameraSelector;
import androidx.camera.lifecycle.ProcessCameraProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.camera.Camera2Session;
import org.telegram.messenger.camera.CameraController;
import org.telegram.messenger.camera.CameraSession;
import org.telegram.messenger.camera.Size;

/* loaded from: classes4.dex */
public abstract class CameraDebugUtils {
    private static String formatCamera2FpsRanges(Range<Integer>[] r7) {
        if (r7 != null) goto L5;
    L22:
        return "none";
    L5:
        if (r7.length == 0) goto L22;
        StringBuilder r1 = new StringBuilder();
        int r2 = r7.length;
        int r4 = 0;
    L8:
        if (r4 >= r2) goto L18;
        Range<Integer> r5 = r7[r4];
        if (r5 == null) goto L16;
        if (r1.length() <= 0) goto L15;
        r1.append(", ");
    L15:
        r1.append(formatFpsRange(((Integer) r5.getLower()).intValue(), ((Integer) r5.getUpper()).intValue(), false));
    L16:
        r4 = r4 + 1;
        goto L8
    L18:
        if (r1.length() != 0) goto L21;
        return "none";
    L21:
        return r1.toString();
    }

    public static String formatCameraSize(Size r2) {
        if (r2 != null) goto L6;
        return "n/a";
    L6:
        return r2.getWidth() + "x" + r2.getHeight();
    }

    public static String formatCameraXInfo(CameraInfo r3) {
        String r0 = Camera2CameraInfo.from(r3).getCameraId();     // Catch: Exception -> L4
    L14:
        float r1 = r3.getIntrinsicZoomRatio();     // Catch: Exception -> L8
    L11:
        return lensFacingToShortString(Integer.valueOf(r3.getLensFacing())) + r0 + "@" + r1;
    L8:
        e = move-exception;
        FileLog.e(e);
        r1 = 1.0f;
    L4:
        e = move-exception;
        FileLog.e(e);
        r0 = "?";
        goto L14
    }

    private static String formatFpsRange(int r1, int r2, boolean r3) {
        return formatFpsValue(r1, r3) + "-" + formatFpsValue(r2, r3);
    }

    private static String formatFpsValue(int r1, boolean r2) {
        float r12 = r1;
        if (r2 == false) goto L6;
        r12 = r12 / 1000.0f;
    L6:
        if (Math.abs(r12 - Math.round(r12)) >= 0.05f) goto L10;
        return Integer.toString(Math.round(r12));
    L10:
        return String.format(Locale.US, "%.1f", new Object[]{Float.valueOf(r12)});
    }

    private static String formatLegacyFpsRanges(List<int[]> r5) {
        if (r5 != null) goto L5;
    L24:
        return "none";
    L5:
        if (r5.isEmpty() == true) goto L24;
        StringBuilder r1 = new StringBuilder();
        Iterator<int[]> r52 = r5.iterator();
    L9:
        if (r52.hasNext() == false) goto L20;
        int[] r2 = r52.next();
        if (r2 == null) goto L9;
        if (r2.length < 2) goto L9;
        if (r1.length() <= 0) goto L18;
        r1.append(", ");
    L18:
        r1.append(formatFpsRange(r2[0], r2[1], true));
        goto L9
    L20:
        if (r1.length() != 0) goto L23;
        return "none";
    L23:
        return r1.toString();
    }

    private static String formatZoomStop(float r3) {
        String r32 = String.format(Locale.US, "%.3f", new Object[]{Float.valueOf(r3)});
        int r0 = r32.length();
    L3:
        if (r0 <= 0) goto L7;
        if (r32.charAt(r0 - 1) != '0') goto L7;
        r0 = r0 - 1;
    L7:
        if (r0 <= 0) goto L12;
        if (r32.charAt(r0 - 1) != '.') goto L12;
        r0 = r0 - 1;
    L12:
        return r32.substring(0, r0);
    }

    public static String formatZoomStops(float[] r5) {
        if (r5 != null) goto L4;
        return "none";
    L4:
        if (r5.length == 0) goto L19;
        StringBuilder r0 = new StringBuilder();
        int r1 = r5.length;
        int r2 = 0;
    L7:
        if (r2 >= r1) goto L13;
        float r3 = r5[r2];
        if (r0.length() <= 0) goto L11;
        r0.append(' ');
    L11:
        r0.append(formatZoomStop(r3));
        r2 = r2 + 1;
        goto L7
    L13:
        return r0.toString();
    L19:
        return "none";
    }

    public static String getCamera2CameraList(Context r7) {
        CameraManager r72 = (CameraManager) r7.getSystemService(CameraManager.class);     // Catch: Exception -> L18
        if (r72 != null) goto L6;
        return "manager=null";
    L6:
        StringBuilder r0 = new StringBuilder();     // Catch: Exception -> L18
        String[] r1 = r72.getCameraIdList();     // Catch: Exception -> L18
        int r2 = r1.length;     // Catch: Exception -> L18
        int r3 = 0;
    L7:
        if (r3 >= r2) goto L13;
        String r4 = r1[r3];     // Catch: Exception -> L18
        if (r0.length() <= 0) goto L11;
        r0.append(", ");     // Catch: Exception -> L18
    L11:
        r0.append(lensFacingToShortString((Integer) r72.getCameraCharacteristics(r4).get(CameraCharacteristics.LENS_FACING)));     // Catch: Exception -> L18
        r0.append(r4);     // Catch: Exception -> L18
        r3 = r3 + 1;     // Catch: Exception -> L18
        goto L7
    L13:
        if (r0.length() != 0) goto L16;
        return "none";
    L16:
        return r0.toString();
    L18:
        e = move-exception;
        FileLog.e(e);
        return "error=".concat(e.getClass().getSimpleName());
    }

    public static String getCamera2SupportedFpsRanges(Camera2Session r1) {
        if (r1 != null) goto L10;
        return "none";
    L10:
        return formatCamera2FpsRanges(r1.getAvailableFpsRanges());
    L7:
        e = move-exception;
        FileLog.e(e);
        return "error=".concat(e.getClass().getSimpleName());
    }

    public static String getCameraXAvailableCameraList(CameraXSession r10) {
        ProcessCameraProvider r0 = r10.provider;
        if (r0 != null) goto L6;
        return "provider=null";
    L6:
        StringBuilder r1 = new StringBuilder();
    L45:
        e = move-exception;
        FileLog.e(e);
        return "error=".concat(e.getClass().getSimpleName());
    L8:
        if (r10.isDualMode() == false) goto L31;
        Iterator<List<CameraInfo>> r102 = r0.getAvailableConcurrentCameraInfos().iterator();     // Catch: Exception -> L45
        int r2 = 0;
    L11:
        if (r102.hasNext() == false) goto L40;
        List<CameraInfo> r3 = r102.next();     // Catch: Exception -> L45
        StringBuilder r4 = new StringBuilder();     // Catch: Exception -> L45
        Iterator<CameraInfo> r32 = r3.iterator();     // Catch: Exception -> L45
        boolean r5 = false;
        boolean r6 = false;
    L14:
        if (r32.hasNext() == false) goto L24;
        CameraInfo r7 = r32.next();     // Catch: Exception -> L45
        int r8 = r7.getLensFacing();     // Catch: Exception -> L45
        if (r8 != 0) goto L18;
        r5 = true;
    L21:
        if (r4.length() <= 0) goto L23;
        r4.append(" + ");     // Catch: Exception -> L45
    L23:
        r4.append(formatCameraXInfo(r7));     // Catch: Exception -> L45
        goto L14
    L18:
        if (r8 != 1) goto L21;
        r6 = true;
        goto L21
    L24:
        if (r5 == false) goto L11;
        if (r6 == false) goto L11;
        if (r1.length() <= 0) goto L30;
        r1.append('\n');     // Catch: Exception -> L45
    L30:
        r1.append("pair");     // Catch: Exception -> L45
        r1.append(r2);     // Catch: Exception -> L45
        r1.append(": ");     // Catch: Exception -> L45
        r1.append(r4);     // Catch: Exception -> L45
        r2 = r2 + 1;     // Catch: Exception -> L45
    L40:
        if (r1.length() != 0) goto L44;
        return "none";
    L44:
        return r1.toString();
    L31:
        Iterator<CameraInfo> r103 = r0.getAvailableCameraInfos().iterator();     // Catch: Exception -> L45
    L33:
        if (r103.hasNext() == false) goto L40;
        CameraInfo r02 = r103.next();     // Catch: Exception -> L45
        if (r1.length() <= 0) goto L37;
        r1.append(", ");     // Catch: Exception -> L45
    L37:
        r1.append(formatCameraXInfo(r02));     // Catch: Exception -> L45
        goto L33
    }

    public static String getCameraXBoundCameraList(CameraXSession r4) {
        StringBuilder r0 = new StringBuilder();
        Camera r1 = r4.cameraFront;
        Camera r2 = r4.cameraBack;
        Camera r42 = r4.camera;
        if (r1 == null) goto L5;
        r0.append("front=");
        r0.append(formatCameraXInfo(r1.getCameraInfo()));
    L5:
        if (r2 == null) goto L11;
        if (r0.length() <= 0) goto L9;
        r0.append(", ");
    L9:
        r0.append("back=");
        r0.append(formatCameraXInfo(r2.getCameraInfo()));
    L11:
        if (r0.length() != 0) goto L15;
        if (r42 == null) goto L15;
        r0.append("active=");
        r0.append(formatCameraXInfo(r42.getCameraInfo()));
    L15:
        if (r0.length() != 0) goto L19;
        return "none";
    L19:
        return r0.toString();
    }

    public static String getCameraXPhysicalCameraList(CameraXSession r4) {
        Camera r42 = r4.camera;
        if (r42 != null) goto L20;
        return "none";
    L20:
        StringBuilder r1 = new StringBuilder();     // Catch: Exception -> L17
        Iterator<CameraInfo> r43 = r42.getCameraInfo().getPhysicalCameraInfos().iterator();     // Catch: Exception -> L17
    L7:
        if (r43.hasNext() == false) goto L13;
        CameraInfo r2 = r43.next();     // Catch: Exception -> L17
        if (r1.length() <= 0) goto L11;
        r1.append(", ");     // Catch: Exception -> L17
    L11:
        r1.append(formatCameraXInfo(r2));     // Catch: Exception -> L17
        goto L7
    L13:
        if (r1.length() != 0) goto L15;
        return "none";
    L15:
        return r1.toString();
    L17:
        e = move-exception;
        FileLog.e(e);
        return "error=".concat(e.getClass().getSimpleName());
    }

    public static String getCameraXSupportedFpsRanges(CameraXSession r4) {
        if (r4 != null) goto L33;
        return "none";
    L33:
        Camera r1 = r4.camera;     // Catch: Exception -> L30
        if (r1 == null) goto L9;
        CameraInfo r42 = r1.getCameraInfo();     // Catch: Exception -> L30
    L26:
        if (r42 != null) goto L28;
        return "none";
    L28:
        return formatCamera2FpsRanges((Range[]) Camera2CameraInfo.from(r42).getCameraCharacteristic(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES));
    L9:
        if (r4.isDualMode() == false) goto L17;
        if (r4.isFrontface() == false) goto L13;
        Camera r43 = r4.cameraFront;     // Catch: Exception -> L30
    L14:
        if (r43 == null) goto L25;
        r42 = r43.getCameraInfo();     // Catch: Exception -> L30
    L25:
        r42 = null;
        goto L26
    L13:
        r43 = r4.cameraBack;     // Catch: Exception -> L30
        goto L14
    L17:
        if (r4.provider == null) goto L25;
        if (r4.isFrontface() == false) goto L21;
        CameraSelector r12 = CameraSelector.DEFAULT_FRONT_CAMERA;     // Catch: Exception -> L30
    L23:
        if (r4.provider.hasCamera(r12) == false) goto L25;
        r42 = r4.provider.getCameraInfo(r12);     // Catch: Exception -> L30
        goto L26
    L21:
        r12 = CameraSelector.DEFAULT_BACK_CAMERA;     // Catch: Exception -> L30
    L30:
        e = move-exception;
        FileLog.e(e);
        return "error=".concat(e.getClass().getSimpleName());
    }

    public static String getLegacyCameraList() {
        ArrayList<org.telegram.messenger.camera.CameraInfo> r0 = CameraController.getInstance().getCameras();
        if (r0 != null) goto L5;
        return "none";
    L5:
        if (r0.isEmpty() == true) goto L24;
        StringBuilder r1 = new StringBuilder();
        int r2 = r0.size();
        int r3 = 0;
    L8:
        if (r3 >= r2) goto L18;
        org.telegram.messenger.camera.CameraInfo r4 = r0.get(r3);
        r3 = r3 + 1;
        org.telegram.messenger.camera.CameraInfo r42 = r4;
        if (r1.length() <= 0) goto L13;
        r1.append(", ");
    L13:
        if (r42.isFrontface() == false) goto L15;
        char r5 = 'f';
    L16:
        r1.append(r5);
        r1.append(r42.getCameraId());
        goto L8
    L15:
        r5 = 'b';
        goto L16
    L18:
        return r1.toString();
    L24:
        return "none";
    }

    public static String getLegacySupportedFpsRanges(CameraSession r1) {
        if (r1 == null) goto L13;
        org.telegram.messenger.camera.CameraInfo r0 = r1.cameraInfo;
        if (r0 != null) goto L6;
        return "none";
    L6:
        if (r0.getCamera() == null) goto L18;
        return formatLegacyFpsRanges(r1.cameraInfo.getCamera().getParameters().getSupportedPreviewFpsRange());
    L10:
        e = move-exception;
        FileLog.e(e);
        return "error=".concat(e.getClass().getSimpleName());
    L18:
        return "none";
    L13:
        return "none";
    }

    public static char lensFacingToShortString(Integer r2) {
        if (r2 != null) goto L6;
        return '?';
    L6:
        if (r2.intValue() != 0) goto L10;
        return 'f';
    L10:
        if (r2.intValue() != 1) goto L13;
        return 'b';
    L13:
        return '?';
    }

    public static float safeCameraXMaxZoomRatio(CameraXSession r0) {
        if (r0 != null) goto L10;
        return 1.0f;
    L10:
        return r0.getMaxZoomRatio();
    L5:
        e = move-exception;
        FileLog.e(e);
        return -1.0f;
    }

    public static float safeCameraXMinZoomRatio(CameraXSession r0) {
        if (r0 != null) goto L10;
        return 1.0f;
    L10:
        return r0.getMinZoomRatio();
    L5:
        e = move-exception;
        FileLog.e(e);
        return -1.0f;
    }

    public static float safeCameraXZoomRatio(CameraXSession r0) {
        if (r0 != null) goto L10;
        return 1.0f;
    L10:
        return r0.getZoomRatio();
    L5:
        e = move-exception;
        FileLog.e(e);
        return -1.0f;
    }
}
