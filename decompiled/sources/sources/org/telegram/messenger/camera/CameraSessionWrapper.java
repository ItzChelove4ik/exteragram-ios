package org.telegram.messenger.camera;

import android.graphics.Rect;
import java.util.concurrent.CountDownLatch;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes5.dex */
public class CameraSessionWrapper {
    public CameraSession camera1Session;
    public Camera2Session camera2Session;

    public CameraSessionWrapper() {
    }

    public static CameraSessionWrapper of(Camera2Session r1) {
        CameraSessionWrapper r0 = new CameraSessionWrapper();
        r0.camera2Session = r1;
        return r0;
    }

    public static CameraSessionWrapper of(CameraSession r1) {
        CameraSessionWrapper r0 = new CameraSessionWrapper();
        r0.camera1Session = r1;
        return r0;
    }

    public void destroy(boolean r3, Runnable r4, Runnable r5) {
        if (this.camera2Session == null) goto L9;
        if (r4 == null) goto L6;
        r4.run();
    L6:
        this.camera2Session.destroy(r3, r5);
        return;
    L9:
        if (this.camera1Session == null) goto L16;
        CameraController r0 = CameraController.getInstance();
        CameraSession r2 = this.camera1Session;
        if (r3 == true) goto L13;
        CountDownLatch r32 = new CountDownLatch(1);
    L14:
        r0.close(r2, r32, r4, r5);
        return;
    L13:
        r32 = null;
        goto L14
    }

    public boolean equals(Object r5) {
        if ((r5 instanceof CameraSession) == false) goto L9;
        if (r5 != this.camera1Session) goto L7;
        return true;
    L7:
        return false;
    L9:
        if ((r5 instanceof Camera2Session) == false) goto L15;
        if (r5 != this.camera2Session) goto L13;
        return true;
    L13:
        return false;
    L15:
        if ((r5 instanceof CameraSessionWrapper) == false) goto L25;
        CameraSessionWrapper r52 = (CameraSessionWrapper) r5;
        if (r52 != this) goto L19;
    L24:
        return true;
    L19:
        if (r52.camera1Session == this.camera1Session) goto L21;
    L23:
        return false;
    L21:
        if (r52.camera2Session != this.camera2Session) goto L23;
    L25:
        return false;
    }

    public void focusToRect(Rect r2, Rect r3) {
        if (this.camera2Session != null) goto L10;
        CameraSession r1 = this.camera1Session;
        if (r1 == null) goto L9;
        r1.focusToRect(r2, r3);
        return;
    L9:
        return;
    }

    @Deprecated
    public int getCameraId() {
        Camera2Session r0 = this.camera2Session;
        if (r0 != null) goto L5;
        CameraSession r1 = this.camera1Session;
        if (r1 != null) goto L9;
        return 0;
    L9:
        return r1.cameraInfo.cameraId;
    L5:
        return r0.cameraId.hashCode();
    }

    public String getCurrentFlashMode() {
        if (this.camera2Session == null) goto L6;
        return "off";
    L6:
        CameraSession r1 = this.camera1Session;
        if (r1 != null) goto L9;
        return null;
    L9:
        return r1.getCurrentFlashMode();
    }

    public int getCurrentOrientation() {
        Camera2Session r0 = this.camera2Session;
        if (r0 != null) goto L5;
        CameraSession r1 = this.camera1Session;
        if (r1 != null) goto L9;
        return 0;
    L9:
        return r1.getCurrentOrientation();
    L5:
        return r0.getCurrentOrientation();
    }

    public int getDisplayOrientation() {
        Camera2Session r0 = this.camera2Session;
        if (r0 != null) goto L5;
        CameraSession r1 = this.camera1Session;
        if (r1 != null) goto L9;
        return 0;
    L9:
        return r1.getDisplayOrientation();
    L5:
        return r0.getDisplayOrientation();
    }

    public String getNextFlashMode() {
        if (this.camera2Session == null) goto L6;
        return "off";
    L6:
        CameraSession r1 = this.camera1Session;
        if (r1 != null) goto L9;
        return null;
    L9:
        return r1.getNextFlashMode();
    }

    public Object getObject() {
        Camera2Session r0 = this.camera2Session;
        if (r0 == null) goto L5;
        return r0;
    L5:
        CameraSession r1 = this.camera1Session;
        if (r1 == null) goto L8;
        return r1;
    L8:
        return null;
    }

    public int getWorldAngle() {
        Camera2Session r0 = this.camera2Session;
        if (r0 != null) goto L5;
        CameraSession r1 = this.camera1Session;
        if (r1 != null) goto L9;
        return 0;
    L9:
        return r1.getWorldAngle();
    L5:
        return r0.getWorldAngle();
    }

    public boolean hasFlashModes() {
        if (this.camera2Session == null) goto L6;
        return false;
    L6:
        if (this.camera1Session != null) goto L8;
        return false;
    L8:
        return !r2.availableFlashModes.isEmpty();
    }

    public boolean isInitiated() {
        Camera2Session r0 = this.camera2Session;
        if (r0 != null) goto L5;
        CameraSession r1 = this.camera1Session;
        if (r1 != null) goto L9;
        return false;
    L9:
        return r1.isInitied();
    L5:
        return r0.isInitiated();
    }

    public boolean isSameTakePictureOrientation() {
        if (this.camera2Session != null) goto L11;
        CameraSession r1 = this.camera1Session;
        if (r1 != null) goto L8;
        return true;
    L8:
        return r1.isSameTakePictureOrientation();
    L11:
        return true;
    }

    public void setCurrentFlashMode(String r2) {
        if (this.camera2Session != null) goto L10;
        CameraSession r1 = this.camera1Session;
        if (r1 == null) goto L9;
        r1.setCurrentFlashMode(r2);
        return;
    L9:
        return;
    }

    public void setFlipFront(boolean r2) {
        if (this.camera2Session != null) goto L10;
        CameraSession r1 = this.camera1Session;
        if (r1 == null) goto L9;
        r1.setFlipFront(r2);
        return;
    L9:
        return;
    }

    public void setOptimizeForBarcode(boolean r2) {
        Camera2Session r0 = this.camera2Session;
        if (r0 == null) goto L6;
        r0.setScanningBarcode(r2);
        return;
    L6:
        CameraSession r1 = this.camera1Session;
        if (r1 == null) goto L10;
        r1.setOptimizeForBarcode(r2);
        return;
    }

    public void setZoom(float r3) {
        Camera2Session r0 = this.camera2Session;
        if (r0 == null) goto L6;
        r0.setZoom(AndroidUtilities.lerp(r0.getMinZoom(), this.camera2Session.getMaxZoom(), r3));
        return;
    L6:
        CameraSession r2 = this.camera1Session;
        if (r2 == null) goto L10;
        r2.setZoom(r3);
        return;
    }

    public void stopVideoRecording() {
        Camera2Session r0 = this.camera2Session;
        if (r0 == null) goto L6;
        r0.setRecordingVideo(false);
        return;
    L6:
        CameraSession r1 = this.camera1Session;
        if (r1 == null) goto L10;
        r1.stopVideoRecording();
        return;
    }

    public void updateRotation() {
        if (this.camera2Session != null) goto L10;
        CameraSession r1 = this.camera1Session;
        if (r1 == null) goto L9;
        r1.updateRotation();
        return;
    L9:
        return;
    }
}
