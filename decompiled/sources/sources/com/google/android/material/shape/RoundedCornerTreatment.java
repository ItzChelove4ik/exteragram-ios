package com.google.android.material.shape;

/* loaded from: classes5.dex */
public class RoundedCornerTreatment extends CornerTreatment {
    float radius;

    public RoundedCornerTreatment() {
        this.radius = -1.0f;
    }

    @Deprecated
    public RoundedCornerTreatment(float r1) {
        this.radius = r1;
    }

    @Override // com.google.android.material.shape.CornerTreatment
    public void getCornerPath(ShapePath r8, float r9, float r10, float r11) {
        float r112 = r11 * r10;
        r8.reset(0.0f, r112, 180.0f, 180.0f - r9);
        float r3 = r112 * 2.0f;
        r8.addArc(0.0f, 0.0f, r3, r3, 180.0f, r9);
    }
}
