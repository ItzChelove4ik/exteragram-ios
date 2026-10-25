package androidx.camera.core;

import android.graphics.PointF;
import android.view.Display;

/* loaded from: classes3.dex */
public final class DisplayOrientedMeteringPointFactory extends MeteringPointFactory {
    private final CameraInfo mCameraInfo;
    private final Display mDisplay;
    private final float mHeight;
    private final float mWidth;

    public DisplayOrientedMeteringPointFactory(Display r1, CameraInfo r2, float r3, float r4) {
        this.mWidth = r3;
        this.mHeight = r4;
        this.mDisplay = r1;
        this.mCameraInfo = r2;
    }

    private int getRelativeCameraOrientation(boolean r2) {
        int r0 = this.mDisplay.getRotation();     // Catch: Exception -> L6
        int r1 = this.mCameraInfo.getSensorRotationDegrees(r0);     // Catch: Exception -> L6
        if (r2 == false) goto L10;
        return (360 - r1) % 360;
    L10:
        return r1;
    L6:
        return 0;
    }

    @Override // androidx.camera.core.MeteringPointFactory
    public PointF convertPoint(float r7, float r8) {
        float r0 = this.mWidth;
        float r1 = this.mHeight;
        if (this.mCameraInfo.getLensFacing() != 0) goto L5;
        boolean r2 = true;
    L6:
        int r6 = getRelativeCameraOrientation(r2);
        if (r6 == 90) goto L11;
        if (r6 == 270) goto L11;
        r8 = r7;
        r7 = r8;
        r1 = r0;
        r0 = r1;
    L11:
        if (r6 != 90) goto L13;
    L18:
        r7 = r0 - r7;
    L19:
        if (r2 == false) goto L22;
        r8 = r1 - r8;
    L22:
        return new PointF(r8 / r1, r7 / r0);
    L13:
        if (r6 == 180) goto L17;
        if (r6 != 270) goto L19;
        r8 = r1 - r8;
        goto L19
    L17:
        r8 = r1 - r8;
        goto L18
    L5:
        r2 = false;
        goto L6
    }
}
