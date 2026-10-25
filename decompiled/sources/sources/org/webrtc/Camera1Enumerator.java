package org.webrtc;

import android.hardware.Camera;
import android.os.SystemClock;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.webrtc.CameraEnumerationAndroid;
import org.webrtc.CameraVideoCapturer;

/* loaded from: classes6.dex */
public class Camera1Enumerator implements CameraEnumerator {
    private static final String TAG = "Camera1Enumerator";
    private static List<List<CameraEnumerationAndroid.CaptureFormat>> cachedSupportedFormats;
    private final boolean captureToTexture;

    public Camera1Enumerator() {
        this(true);
    }

    public Camera1Enumerator(boolean r1) {
        this.captureToTexture = r1;
    }

    public static List<CameraEnumerationAndroid.CaptureFormat.FramerateRange> convertFramerates(List<int[]> r5) {
        ArrayList r0 = new ArrayList();
        Iterator<int[]> r52 = r5.iterator();
    L4:
        if (r52.hasNext() == false) goto L6;
        int[] r1 = r52.next();
        r0.add(new CameraEnumerationAndroid.CaptureFormat.FramerateRange(r1[0], r1[1]));
        goto L4
    L6:
        return r0;
    }

    public static List<Size> convertSizes(List<Camera.Size> r4) {
        ArrayList r0 = new ArrayList();
        Iterator<Camera.Size> r42 = r4.iterator();
    L4:
        if (r42.hasNext() == false) goto L6;
        Camera.Size r1 = r42.next();
        r0.add(new Size(r1.width, r1.height));
        goto L4
    L6:
        return r0;
    }

    private static List<CameraEnumerationAndroid.CaptureFormat> enumerateFormats(int r11) {
        Logging.d(TAG, "Get supported formats for camera index " + r11 + ".");
        long r5 = SystemClock.elapsedRealtime();
        Camera r2 = null;
        Logging.d(TAG, "Opening camera with index " + r11);     // Catch: Throwable -> L19 RuntimeException -> L21
        r2 = Camera.open(r11);     // Catch: Throwable -> L19 RuntimeException -> L21
        Camera.Parameters r0 = r2.getParameters();     // Catch: Throwable -> L19 RuntimeException -> L21
        r2.release();
        ArrayList r1 = new ArrayList();
        List<int[]> r22 = r0.getSupportedPreviewFpsRange();     // Catch: Exception -> L8
        int r7 = 0;
        if (r22 == null) goto L10;
        int[] r23 = r22.get(r22.size() - 1);     // Catch: Exception -> L8
        r7 = r23[0];     // Catch: Exception -> L8
        int r24 = r23[1];     // Catch: Exception -> L8
    L11:
        Iterator<Camera.Size> r02 = r0.getSupportedPreviewSizes().iterator();     // Catch: Exception -> L8
    L12:
        if (r02.hasNext() == false) goto L17;
        Camera.Size r8 = r02.next();     // Catch: Exception -> L8
        r1.add(new CameraEnumerationAndroid.CaptureFormat(r8.width, r8.height, r7, r24));     // Catch: Exception -> L8
    L17:
        Logging.d(TAG, "Get supported formats for camera index " + r11 + " done. Time spent: " + (SystemClock.elapsedRealtime() - r5) + " ms.");
        return r1;
    L10:
        r24 = 0;
    L8:
        e = move-exception;
        Logging.e(TAG, "getSupportedFormats() failed on camera index " + r11, e);
        goto L17
    L21:
        e = move-exception;
        Logging.e(TAG, "Open camera failed on camera index " + r11, e);     // Catch: Throwable -> L19
        ArrayList r112 = new ArrayList();     // Catch: Throwable -> L19
        if (r2 == null) goto L25;
        r2.release();
    L25:
        return r112;
    L19:
        th = move-exception;
        if (r2 == null) goto L28;
        r2.release();
    L28:
        throw th;
    }

    public static int getCameraIndex(String r3) {
        Logging.d(TAG, "getCameraIndex: " + r3);
        int r1 = 0;
    L4:
        if (r1 >= Camera.getNumberOfCameras()) goto L9;
        if (r3.equals(getDeviceName(r1)) == true) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        return r1;
    L9:
        Native$$ExternalSyntheticBUOutline5.m("No such camera: ", r3);
        return 0;
    }

    private static Camera.CameraInfo getCameraInfo(int r3) {
        Camera.CameraInfo r0 = new Camera.CameraInfo();
        Camera.getCameraInfo(r3, r0);     // Catch: Exception -> L5
        return r0;
    L5:
        e = move-exception;
        Logging.e(TAG, "getCameraInfo failed on index " + r3, e);
        return null;
    }

    public static String getDeviceName(int r4) {
        Camera.CameraInfo r0 = getCameraInfo(r4);
        if (r0 != null) goto L7;
        return null;
    L7:
        if (r0.facing != 1) goto L9;
        String r1 = "front";
    L11:
        return "Camera " + r4 + ", Facing " + r1 + ", Orientation " + r0.orientation;
    L9:
        r1 = "back";
        goto L11
    }

    public static synchronized List<CameraEnumerationAndroid.CaptureFormat> getSupportedFormats(int r4) {
        monitor-enter(Camera1Enumerator.class);
    L10:
        th = move-exception;
        throw th;
    L5:
        if (cachedSupportedFormats != null) goto L12;
        cachedSupportedFormats = new ArrayList();     // Catch: Throwable -> L10
        int r1 = 0;
    L8:
        if (r1 >= Camera.getNumberOfCameras()) goto L12;
        cachedSupportedFormats.add(enumerateFormats(r1));     // Catch: Throwable -> L10
        r1 = r1 + 1;     // Catch: Throwable -> L10
    L12:
        List<CameraEnumerationAndroid.CaptureFormat> r42 = cachedSupportedFormats.get(r4);     // Catch: Throwable -> L10
        monitor-exit(Camera1Enumerator.class);
        return r42;
    }

    @Override // org.webrtc.CameraEnumerator
    public CameraVideoCapturer createCapturer(String r2, CameraVideoCapturer.CameraEventsHandler r3) {
        return new Camera1Capturer(r2, r3, this.captureToTexture);
    }

    @Override // org.webrtc.CameraEnumerator
    public String[] getDeviceNames() {
        ArrayList r5 = new ArrayList();
        int r0 = 0;
    L4:
        if (r0 >= Camera.getNumberOfCameras()) goto L11;
        String r1 = getDeviceName(r0);
        if (r1 == null) goto L8;
        r5.add(r1);
        Logging.d(TAG, "Index: " + r0 + ". " + r1);
    L9:
        r0 = r0 + 1;
        goto L4
    L8:
        Logging.e(TAG, "Index: " + r0 + ". Failed to query camera name.");
        goto L9
    L11:
        return (String[]) r5.toArray(new String[r5.size()]);
    }

    @Override // org.webrtc.CameraEnumerator
    public List<CameraEnumerationAndroid.CaptureFormat> getSupportedFormats(String r1) {
        return getSupportedFormats(getCameraIndex(r1));
    }

    @Override // org.webrtc.CameraEnumerator
    public boolean isBackFacing(String r1) {
        Camera.CameraInfo r0 = getCameraInfo(getCameraIndex(r1));
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.facing != 0) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // org.webrtc.CameraEnumerator
    public boolean isFrontFacing(String r1) {
        Camera.CameraInfo r0 = getCameraInfo(getCameraIndex(r1));
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.facing != 1) goto L9;
        return true;
    L9:
        return false;
    }
}
