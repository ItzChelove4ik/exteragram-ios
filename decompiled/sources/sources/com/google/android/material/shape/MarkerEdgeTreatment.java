package com.google.android.material.shape;

/* loaded from: classes5.dex */
public final class MarkerEdgeTreatment extends EdgeTreatment {
    private final float radius;

    public MarkerEdgeTreatment(float r2) {
        this.radius = r2 - 0.001f;
    }

    @Override // com.google.android.material.shape.EdgeTreatment
    public boolean forceIntersection() {
        return true;
    }

    @Override // com.google.android.material.shape.EdgeTreatment
    public void getEdgePath(float r9, float r10, float r11, ShapePath r12) {
        float r92 = (float) ((this.radius * Math.sqrt(2.0d)) / 2.0d);
        float r112 = (float) Math.sqrt(Math.pow(this.radius, 2.0d) - Math.pow(r92, 2.0d));
        r12.reset(r10 - r92, ((float) (-((this.radius * Math.sqrt(2.0d)) - this.radius))) + r112);
        r12.lineTo(r10, (float) (-((this.radius * Math.sqrt(2.0d)) - this.radius)));
        r12.lineTo(r10 + r92, ((float) (-((this.radius * Math.sqrt(2.0d)) - this.radius))) + r112);
    }
}
