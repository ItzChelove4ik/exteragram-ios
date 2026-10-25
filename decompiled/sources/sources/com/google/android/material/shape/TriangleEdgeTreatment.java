package com.google.android.material.shape;

/* loaded from: classes5.dex */
public class TriangleEdgeTreatment extends EdgeTreatment {
    private final boolean inside;
    private final float size;

    public TriangleEdgeTreatment(float r1, boolean r2) {
        this.size = r1;
        this.inside = r2;
    }

    @Override // com.google.android.material.shape.EdgeTreatment
    public void getEdgePath(float r4, float r5, float r6, ShapePath r7) {
        boolean r0 = this.inside;
        float r1 = this.size;
        if (r0 == false) goto L6;
        r7.lineTo(r5 - (r1 * r6), 0.0f);
        float r3 = this.size;
        r7.lineTo(r5, r3 * r6, (r3 * r6) + r5, 0.0f);
        r7.lineTo(r4, 0.0f);
        return;
    L6:
        r7.lineTo(r5 - (r1 * r6), 0.0f, r5, (-r1) * r6);
        r7.lineTo(r5 + (this.size * r6), 0.0f, r4, 0.0f);
    }
}
