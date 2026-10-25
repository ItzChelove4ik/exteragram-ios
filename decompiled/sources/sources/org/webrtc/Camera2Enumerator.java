package org.webrtc;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.SystemClock;
import android.util.Range;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.webrtc.CameraEnumerationAndroid;
import org.webrtc.CameraVideoCapturer;

/* loaded from: classes6.dex */
public class Camera2Enumerator implements CameraEnumerator {
    private static final double NANO_SECONDS_PER_SECOND = 1.0E9d;
    private static final String TAG = "Camera2Enumerator";
    private static final Map<String, List<CameraEnumerationAndroid.CaptureFormat>> cachedSupportedFormats = null;
    final CameraManager cameraManager;
    final Context context;

    static {
        cachedSupportedFormats = new HashMap();
    }

    public Camera2Enumerator(Context r2) {
        this.context = r2;
        this.cameraManager = (CameraManager) r2.getSystemService("camera");
    }

    public static List<CameraEnumerationAndroid.CaptureFormat.FramerateRange> convertFramerates(Range<Integer>[] r6, int r7) {
        ArrayList r0 = new ArrayList();
        int r1 = r6.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        Range<Integer> r3 = r6[r2];
        r0.add(new CameraEnumerationAndroid.CaptureFormat.FramerateRange(((Integer) r3.getLower()).intValue() * r7, ((Integer) r3.getUpper()).intValue() * r7));
        r2 = r2 + 1;
        goto L3
    L5:
        return r0;
    }

    private static List<Size> convertSizes(android.util.Size[] r6) {
        if (r6 == null) goto L11;
        if (r6.length == 0) goto L11;
        ArrayList r0 = new ArrayList(r6.length);
        int r1 = r6.length;
        int r2 = 0;
    L7:
        if (r2 >= r1) goto L9;
        android.util.Size r3 = r6[r2];
        r0.add(new Size(r3.getWidth(), r3.getHeight()));
        r2 = r2 + 1;
        goto L7
    L9:
        return r0;
    L11:
        return Collections.EMPTY_LIST;
    }

    private CameraCharacteristics getCameraCharacteristics(String r2) {
        return this.cameraManager.getCameraCharacteristics(r2);
    L4:
        e = move-exception;
        Logging.e(TAG, "Camera access exception: " + e);
        return null;
    }

    public static int getFpsUnitFactor(Range<Integer>[] r2) {
        if (r2.length != 0) goto L6;
        return 1000;
    L6:
        if (((Integer) r2[0].getUpper()).intValue() >= 1000) goto L8;
        return 1000;
    L8:
        return 1;
    }

    public static List<CameraEnumerationAndroid.CaptureFormat> getSupportedFormats(Context r1, String r2) {
        return getSupportedFormats((CameraManager) r1.getSystemService("camera"), r2);
    }

    public static List<CameraEnumerationAndroid.CaptureFormat> getSupportedFormats(CameraManager r14, String r15) {
        Map<String, List<CameraEnumerationAndroid.CaptureFormat>> r1 = cachedSupportedFormats;
        monitor-enter(r1);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (r1.containsKey(r15) == false) goto L11;
        List<CameraEnumerationAndroid.CaptureFormat> r142 = r1.get(r15);     // Catch: Throwable -> L9
        monitor-exit(r1);     // Catch: Throwable -> L9
        return r142;
    L11:
        Logging.d(TAG, "Get supported formats for camera index " + r15 + ".");     // Catch: Throwable -> L9
        long r2 = SystemClock.elapsedRealtime();     // Catch: Throwable -> L9
        CameraCharacteristics r143 = r14.getCameraCharacteristics(r15);     // Catch: Throwable -> L9 Exception -> L34
        StreamConfigurationMap r0 = (StreamConfigurationMap) r143.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);     // Catch: Throwable -> L9
        Range[] r4 = (Range[]) r143.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES);     // Catch: Throwable -> L9
        List<CameraEnumerationAndroid.CaptureFormat.FramerateRange> r42 = convertFramerates(r4, getFpsUnitFactor(r4));     // Catch: Throwable -> L9
        List<Size> r144 = getSupportedSizes(r143);     // Catch: Throwable -> L9
        Iterator<CameraEnumerationAndroid.CaptureFormat.FramerateRange> r43 = r42.iterator();     // Catch: Throwable -> L9
        int r6 = 0;
    L15:
        if (r43.hasNext() == false) goto L17;
        r6 = Math.max(r6, r43.next().max);     // Catch: Throwable -> L9
        goto L15
    L17:
        ArrayList r44 = new ArrayList();     // Catch: Throwable -> L9
        Iterator<Size> r145 = r144.iterator();     // Catch: Throwable -> L9
    L19:
        if (r145.hasNext() == false) goto L31;
        Size r7 = r145.next();     // Catch: Throwable -> L9
        long r10 = r0.getOutputMinFrameDuration(SurfaceTexture.class, new android.util.Size(r7.width, r7.height));     // Catch: Throwable -> L9 Exception -> L24
    L26:
        if (r10 != 0) goto L29;
        int r8 = r6;
    L30:
        r44.add(new CameraEnumerationAndroid.CaptureFormat(r7.width, r7.height, 0, r8));     // Catch: Throwable -> L9
        Logging.d(TAG, "Format: " + r7.width + "x" + r7.height + "@" + r8);     // Catch: Throwable -> L9
        goto L19
    L29:
        r8 = ((int) Math.round(NANO_SECONDS_PER_SECOND / r10)) * 1000;     // Catch: Throwable -> L9
    L24:
        r10 = 0;
        goto L26
    L31:
        cachedSupportedFormats.put(r15, r44);     // Catch: Throwable -> L9
        Logging.d(TAG, "Get supported formats for camera index " + r15 + " done. Time spent: " + (SystemClock.elapsedRealtime() - r2) + " ms.");     // Catch: Throwable -> L9
        monitor-exit(r1);     // Catch: Throwable -> L9
        return r44;
    L34:
        e = move-exception;
        Logging.e(TAG, "getCameraCharacteristics()", e);     // Catch: Throwable -> L9
        return new ArrayList();
    }

    public static List<Size> getSupportedSizes(CameraCharacteristics r2) {
        StreamConfigurationMap r0 = (StreamConfigurationMap) r2.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        ((Integer) r2.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)).getClass();
        return convertSizes(r0.getOutputSizes(SurfaceTexture.class));
    }

    public static boolean isSupported(Context r6) {
        CameraManager r62 = (CameraManager) r6.getSystemService("camera");
        String[] r1 = r62.getCameraIdList();     // Catch: Throwable -> L10
        int r2 = r1.length;     // Catch: Throwable -> L10
        int r3 = 0;
    L4:
        if (r3 >= r2) goto L12;
        if (((Integer) r62.getCameraCharacteristics(r1[r3]).get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)).intValue() == 2) goto L8;
        r3 = r3 + 1;
        goto L4
    L8:
        return false;
    L12:
        return true;
    L10:
        th = move-exception;
        Logging.e(TAG, "Camera access exception: " + th);
        return false;
    }

    @Override // org.webrtc.CameraEnumerator
    public CameraVideoCapturer createCapturer(String r2, CameraVideoCapturer.CameraEventsHandler r3) {
        return new Camera2Capturer(this.context, r2, r3);
    }

    @Override // org.webrtc.CameraEnumerator
    public String[] getDeviceNames() {
        return this.cameraManager.getCameraIdList();
    L4:
        e = move-exception;
        Logging.e(TAG, "Camera access exception: " + e);
        return new String[0];
    }

    @Override // org.webrtc.CameraEnumerator
    public List<CameraEnumerationAndroid.CaptureFormat> getSupportedFormats(String r1) {
        return getSupportedFormats(this.context, r1);
    }

    @Override // org.webrtc.CameraEnumerator
    public boolean isBackFacing(String r1) {
        CameraCharacteristics r0 = getCameraCharacteristics(r1);
        if (r0 != null) goto L5;
        return false;
    L5:
        if (((Integer) r0.get(CameraCharacteristics.LENS_FACING)).intValue() != 1) goto L9;
        return true;
    L9:
        return false;
    }

    @Override // org.webrtc.CameraEnumerator
    public boolean isFrontFacing(String r1) {
        CameraCharacteristics r0 = getCameraCharacteristics(r1);
        if (r0 != null) goto L5;
        return false;
    L5:
        if (((Integer) r0.get(CameraCharacteristics.LENS_FACING)).intValue() != 0) goto L10;
        return true;
    L10:
        return false;
    }
}
