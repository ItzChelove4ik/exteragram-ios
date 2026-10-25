package org.telegram.ui.Components.Paint;

import android.graphics.RectF;
import org.telegram.ui.Components.Paint.Brush;

/* loaded from: classes7.dex */
public class Shape {
    public float arrowTriangleLength;
    public final Brush.Shape brush;
    public float centerX;
    public float centerY;
    public boolean fill;
    public float middleX;
    public float middleY;
    public float radiusX;
    public float radiusY;
    public float rotation;
    public float rounding;
    public float thickness;

    public Shape(Brush.Shape r1) {
        this.brush = r1;
    }

    public void getBounds(RectF r6) {
        if (getType() != 4) goto L5;
        float r0 = this.centerX;
        float r1 = this.arrowTriangleLength;
        float r3 = this.centerY;
        r6.set(r0 - r1, r3 - r1, r0 + r1, r3 + r1);
        r6.union(this.radiusX, this.radiusY);
        r6.union(this.middleX, this.middleY);
    L8:
        float r5 = this.thickness;
        r6.inset((-r5) - 3.0f, (-r5) - 3.0f);
        return;
    L5:
        float r02 = Math.max(Math.abs(this.radiusX), Math.abs(this.radiusY));
        float r12 = this.centerX;
        float r03 = r02 * 1.42f;
        float r32 = this.centerY;
        r6.set(r12 - r03, r32 - r03, r12 + r03, r32 + r03);
        if (getType() != 3) goto L8;
        r6.union(this.middleX, this.middleY);
        goto L8
    }

    public int getType() {
        return this.brush.getShapeShaderType();
    }
}
