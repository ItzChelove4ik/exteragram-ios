package org.telegram.ui.Components.Paint;

import android.graphics.Bitmap;
import android.graphics.PointF;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.Landmark;
import java.util.Iterator;
import org.telegram.ui.Components.Size;

/* loaded from: classes7.dex */
public class PhotoFace {
    private float angle;
    private PointF chinPoint;
    private PointF eyesCenterPoint;
    private float eyesDistance;
    private PointF foreheadPoint;
    private PointF mouthPoint;
    private float width;

    public PhotoFace(Face r10, Bitmap r11, Size r12, boolean r13) {
        Iterator<Landmark> r102 = r10.getLandmarks().iterator();
        PointF r0 = null;
        PointF r1 = null;
        PointF r2 = null;
        PointF r3 = null;
    L4:
        if (r102.hasNext() == false) goto L19;
        Landmark r4 = r102.next();
        PointF r5 = r4.getPosition();
        int r42 = r4.getType();
        if (r42 != 4) goto L8;
        r0 = transposePoint(r5, r11, r12, r13);
        goto L4
    L8:
        if (r42 != 5) goto L10;
        r2 = transposePoint(r5, r11, r12, r13);
        goto L4
    L10:
        if (r42 != 10) goto L12;
        r1 = transposePoint(r5, r11, r12, r13);
        goto L4
    L12:
        if (r42 != 11) goto L4;
        r3 = transposePoint(r5, r11, r12, r13);
        goto L4
    L19:
        if (r0 == null) goto L25;
        if (r1 == null) goto L25;
        if (r0.x >= r1.x) goto L24;
        PointF r8 = r1;
        r1 = r0;
        r0 = r8;
    L24:
        this.eyesCenterPoint = new PointF((r0.x * 0.5f) + (r1.x * 0.5f), (r0.y * 0.5f) + (r1.y * 0.5f));
        this.eyesDistance = (float) Math.hypot(r1.x - r0.x, r1.y - r0.y);
        this.angle = (float) Math.toDegrees(Math.atan2(r1.y - r0.y, r1.x - r0.x) + 3.141592653589793d);
        float r132 = this.eyesDistance;
        this.width = 2.35f * r132;
        float r133 = r132 * 0.8f;
        double r43 = (float) Math.toRadians(r12 - 90.0f);
        this.foreheadPoint = new PointF(this.eyesCenterPoint.x + (((float) Math.cos(r43)) * r133), this.eyesCenterPoint.y + (r133 * ((float) Math.sin(r43))));
    L25:
        if (r2 == null) goto L46;
        if (r3 != null) goto L28;
        return;
    L28:
        if (r2.x >= r3.x) goto L30;
        PointF r82 = r3;
        r3 = r2;
        r2 = r82;
    L30:
        this.mouthPoint = new PointF((r2.x * 0.5f) + (r3.x * 0.5f), (r2.y * 0.5f) + (r3.y * 0.5f));
        float r122 = this.eyesDistance * 0.7f;
        double r02 = (float) Math.toRadians(this.angle + 90.0f);
        this.chinPoint = new PointF(this.mouthPoint.x + (((float) Math.cos(r02)) * r122), this.mouthPoint.y + (r122 * ((float) Math.sin(r02))));
        return;
    }

    private PointF transposePoint(PointF r2, Bitmap r3, Size r4, boolean r5) {
        if (r5 == false) goto L5;
        int r1 = r3.getHeight();
    L4:
        float r12 = r1;
        if (r5 == false) goto L9;
        int r32 = r3.getWidth();
    L11:
        return new PointF((r4.width * r2.x) / r12, (r4.height * r2.y) / r32);
    L9:
        r32 = r3.getHeight();
        goto L11
    L5:
        r1 = r3.getWidth();
        goto L4
    }

    public float getAngle() {
        return this.angle;
    }

    public PointF getPointForAnchor(int r2) {
        if (r2 == 0) goto L18;
        if (r2 == 1) goto L16;
        if (r2 == 2) goto L14;
        if (r2 == 3) goto L12;
        return null;
    L12:
        return this.chinPoint;
    L14:
        return this.mouthPoint;
    L16:
        return this.eyesCenterPoint;
    L18:
        return this.foreheadPoint;
    }

    public float getWidthForAnchor(int r2) {
        if (r2 != 1) goto L7;
        return this.eyesDistance;
    L7:
        return this.width;
    }

    public boolean isSufficient() {
        if (this.eyesCenterPoint == null) goto L6;
        return true;
    L6:
        return false;
    }
}
