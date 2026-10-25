package com.google.android.material.bottomappbar;

import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.material.shape.EdgeTreatment;
import com.google.android.material.shape.ShapePath;

/* loaded from: classes5.dex */
public class BottomAppBarTopEdgeTreatment extends EdgeTreatment implements Cloneable {
    private static final int ANGLE_LEFT = 180;
    private static final int ANGLE_UP = 270;
    private static final int ARC_HALF = 180;
    private static final int ARC_QUARTER = 90;
    private static final float ROUNDED_CORNER_FAB_OFFSET = 1.75f;
    private float cradleVerticalOffset;
    private float fabCornerSize;
    private float fabDiameter;
    private float fabMargin;
    private float horizontalOffset;
    private float roundedCornerRadius;

    public BottomAppBarTopEdgeTreatment(float r2, float r3, float r4) {
        this.fabCornerSize = -1.0f;
        this.fabMargin = r2;
        this.roundedCornerRadius = r3;
        setCradleVerticalOffset(r4);
        this.horizontalOffset = 0.0f;
    }

    public float getCradleVerticalOffset() {
        return this.cradleVerticalOffset;
    }

    @Override // com.google.android.material.shape.EdgeTreatment
    public void getEdgePath(float r22, float r23, float r24, ShapePath r25) {
        float r3 = this.fabDiameter;
        if (r3 != 0.0f) goto L6;
        r25.lineTo(r22, 0.0f);
        return;
    L6:
        float r11 = ((this.fabMargin * 2.0f) + r3) / 2.0f;
        float r12 = r24 * this.roundedCornerRadius;
        float r13 = r23 + this.horizontalOffset;
        float r4 = (this.cradleVerticalOffset * r24) + ((1.0f - r24) * r11);
        if ((r4 / r11) < 1.0f) goto L10;
        r25.lineTo(r22, 0.0f);
        return;
    L10:
        float r5 = this.fabCornerSize;
        float r14 = r5 * r24;
        if (r5 != (-1.0f)) goto L13;
    L17:
        boolean r32 = true;
    L16:
        boolean r15 = r32;
        if (r15 == true) goto L20;
        float r33 = ROUNDED_CORNER_FAB_OFFSET;
        float r16 = 0.0f;
    L21:
        float r42 = r11 + r12;
        float r52 = r16 + r12;
        float r43 = (float) Math.sqrt((r42 * r42) - (r52 * r52));
        float r6 = r13 - r43;
        float r17 = r13 + r43;
        float r8 = (float) Math.toDegrees(Math.atan(r43 / r52));
        float r18 = (90.0f - r8) + r33;
        r25.lineTo(r6, 0.0f);
        float r34 = r6 - r12;
        float r53 = r6 + r12;
        float r62 = r12 * 2.0f;
        r25.addArc(r34, 0.0f, r53, r62, 270.0f, r8);
        if (r15 == false) goto L24;
        r25.addArc(r13 - r11, (-r11) - r16, r13 + r11, r11 - r16, 180.0f - r18, (r18 * 2.0f) - 180.0f);
    L25:
        r25.addArc(r17 - r12, 0.0f, r17 + r12, r62, 270.0f - r8, r8);
        r25.lineTo(r22, 0.0f);
        return;
    L24:
        float r35 = this.fabMargin;
        float r152 = r14 * 2.0f;
        float r44 = r35 + r152;
        float r36 = r13 - r11;
        r25.addArc(r36, -(r14 + r35), r44 + r36, r35 + r14, 180.0f - r18, ((r18 * 2.0f) - 180.0f) / 2.0f);
        float r54 = r13 + r11;
        float r37 = this.fabMargin;
        r25.lineTo(r54 - ((r37 / 2.0f) + r14), r37 + r14);
        float r0 = this.fabMargin;
        r25.addArc(r54 - (r152 + r0), -(r14 + r0), r54, r0 + r14, 90.0f, r18 - 90.0f);
        goto L25
    L20:
        r16 = r4;
        r33 = 0.0f;
        goto L21
    L13:
        if (Math.abs((r5 * 2.0f) - r3) < 0.1f) goto L17;
        r32 = false;
        goto L16
    }

    public float getFabCornerRadius() {
        return this.fabCornerSize;
    }

    public float getFabCradleMargin() {
        return this.fabMargin;
    }

    public float getFabCradleRoundedCornerRadius() {
        return this.roundedCornerRadius;
    }

    public float getFabDiameter() {
        return this.fabDiameter;
    }

    public float getHorizontalOffset() {
        return this.horizontalOffset;
    }

    public void setCradleVerticalOffset(float r2) {
        if (r2 < 0.0f) goto L6;
        this.cradleVerticalOffset = r2;
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("cradleVerticalOffset must be positive.");
    }

    public void setFabCornerSize(float r1) {
        this.fabCornerSize = r1;
    }

    public void setFabCradleMargin(float r1) {
        this.fabMargin = r1;
    }

    public void setFabCradleRoundedCornerRadius(float r1) {
        this.roundedCornerRadius = r1;
    }

    public void setFabDiameter(float r1) {
        this.fabDiameter = r1;
    }

    public void setHorizontalOffset(float r1) {
        this.horizontalOffset = r1;
    }
}
