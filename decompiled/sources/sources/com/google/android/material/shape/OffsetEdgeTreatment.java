package com.google.android.material.shape;

/* loaded from: classes5.dex */
public final class OffsetEdgeTreatment extends EdgeTreatment {
    private final float offset;
    private final EdgeTreatment other;

    public OffsetEdgeTreatment(EdgeTreatment r1, float r2) {
        this.other = r1;
        this.offset = r2;
    }

    @Override // com.google.android.material.shape.EdgeTreatment
    public boolean forceIntersection() {
        return this.other.forceIntersection();
    }

    @Override // com.google.android.material.shape.EdgeTreatment
    public void getEdgePath(float r2, float r3, float r4, ShapePath r5) {
        this.other.getEdgePath(r2, r3 - this.offset, r4, r5);
    }
}
