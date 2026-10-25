package com.google.android.material.transition;

import android.graphics.Path;
import android.graphics.PointF;
import androidx.transition.PathMotion;

/* loaded from: classes5.dex */
public final class MaterialArcMotion extends PathMotion {
    public MaterialArcMotion() {
    }

    private static PointF getControlPoint(float r1, float r2, float r3, float r4) {
        if (r2 <= r4) goto L7;
        return new PointF(r3, r2);
    L7:
        return new PointF(r1, r4);
    }

    @Override // androidx.transition.PathMotion
    public Path getPath(float r1, float r2, float r3, float r4) {
        Path r0 = new Path();
        r0.moveTo(r1, r2);
        PointF r12 = getControlPoint(r1, r2, r3, r4);
        r0.quadTo(r12.x, r12.y, r3, r4);
        return r0;
    }
}
