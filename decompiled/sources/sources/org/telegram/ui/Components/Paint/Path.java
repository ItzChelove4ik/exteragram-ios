package org.telegram.ui.Components.Paint;

import java.util.Arrays;
import java.util.Vector;

/* loaded from: classes7.dex */
public class Path {
    private float baseWeight;
    private Brush brush;
    private int color;
    private Vector<Point> points;
    public double remainder;

    public Path(Point r2) {
        Vector<Point> r0 = new Vector();
        this.points = r0;
        r0.add(r2);
    }

    public Path(Point[] r2) {
        Vector<Point> r0 = new Vector();
        this.points = r0;
        r0.addAll(Arrays.asList(r2));
    }

    public float getBaseWeight() {
        return this.baseWeight;
    }

    public Brush getBrush() {
        return this.brush;
    }

    public int getColor() {
        return this.color;
    }

    public int getLength() {
        Vector<Point> r0 = this.points;
        if (r0 != null) goto L7;
        return 0;
    L7:
        return r0.size();
    }

    public Point[] getPoints() {
        Point[] r0 = new Point[this.points.size()];
        this.points.toArray(r0);
        return r0;
    }

    public void setup(int r1, float r2, Brush r3) {
        this.color = r1;
        this.baseWeight = r2;
        this.brush = r3;
    }
}
