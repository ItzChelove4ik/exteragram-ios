package org.telegram.ui.Components.Paint;

import android.graphics.PointF;

/* loaded from: classes7.dex */
public class Point {
    public boolean edge;
    public double x;
    public double y;
    public double z;

    public Point(double r1, double r3, double r5) {
        this.x = r1;
        this.y = r3;
        this.z = r5;
    }

    public Point(double r1, double r3, double r5, boolean r7) {
        this.x = r1;
        this.y = r3;
        this.z = r5;
        this.edge = r7;
    }

    public Point add(Point r8) {
        return new Point(this.x + r8.x, this.y + r8.y, this.z + r8.z);
    }

    public boolean equals(Object r7) {
        if (r7 != null) goto L6;
        return false;
    L6:
        if (r7 != this) goto L9;
        return true;
    L9:
        if ((r7 instanceof Point) == true) goto L11;
        return false;
    L11:
        Point r72 = (Point) r7;
        if (this.x == r72.x) goto L14;
    L18:
        return false;
    L14:
        if (this.y != r72.y) goto L18;
        if (this.z != r72.z) goto L18;
        return true;
    }

    public float getDistanceTo(Point r9) {
        return (float) Math.sqrt((Math.pow(this.x - r9.x, 2.0d) + Math.pow(this.y - r9.y, 2.0d)) + Math.pow(this.z - r9.z, 2.0d));
    }

    public Point multiplyByScalar(double r8) {
        return new Point(this.x * r8, this.y * r8, this.z * r8);
    }

    public Point multiplySum(Point r8, double r9) {
        return new Point((this.x + r8.x) * r9, (this.y + r8.y) * r9, (this.z + r8.z) * r9);
    }

    public Point substract(Point r8) {
        return new Point(this.x - r8.x, this.y - r8.y, this.z - r8.z);
    }

    public PointF toPointF() {
        return new PointF((float) this.x, (float) this.y);
    }
}
