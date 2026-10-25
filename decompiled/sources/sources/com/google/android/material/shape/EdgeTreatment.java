package com.google.android.material.shape;

/* loaded from: classes5.dex */
public class EdgeTreatment {
    public EdgeTreatment() {
    }

    public boolean forceIntersection() {
        return false;
    }

    public void getEdgePath(float r1, float r2, float r3, ShapePath r4) {
        r4.lineTo(r1, 0.0f);
    }

    @Deprecated
    public void getEdgePath(float r2, float r3, ShapePath r4) {
        getEdgePath(r2, r2 / 2.0f, r3, r4);
    }
}
