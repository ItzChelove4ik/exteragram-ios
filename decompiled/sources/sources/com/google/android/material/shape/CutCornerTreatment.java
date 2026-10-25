package com.google.android.material.shape;

/* loaded from: classes5.dex */
public class CutCornerTreatment extends CornerTreatment {
    float size;

    public CutCornerTreatment() {
        this.size = -1.0f;
    }

    @Deprecated
    public CutCornerTreatment(float r1) {
        this.size = r1;
    }

    @Override // com.google.android.material.shape.CornerTreatment
    public void getCornerPath(ShapePath r3, float r4, float r5, float r6) {
        float r62 = r6 * r5;
        r3.reset(0.0f, r62, 180.0f, 180.0f - r4);
        double r52 = r62;
        r3.lineTo((float) (Math.sin(Math.toRadians(r4)) * r52), (float) (Math.sin(Math.toRadians(90.0f - r4)) * r52));
    }
}
