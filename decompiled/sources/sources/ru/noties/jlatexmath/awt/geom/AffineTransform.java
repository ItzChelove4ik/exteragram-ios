package ru.noties.jlatexmath.awt.geom;

import android.graphics.Canvas;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes7.dex */
public class AffineTransform implements Cloneable {
    private final Canvas canvas;
    private final AffineTransform parent;
    private int save;
    private double scaleX;
    private double scaleY;
    private float translateX;
    private float translateY;

    private AffineTransform(AffineTransform r3, Canvas r4) {
        this.save = -1;
        this.scaleX = 1.0d;
        this.scaleY = 1.0d;
        this.parent = r3;
        this.canvas = r4;
    }

    public static AffineTransform create(Canvas r2) {
        return new AffineTransform(null, r2);
    }

    public /* bridge */ /* synthetic */ Object clone() {
        return m22462clone();
    }

    /* renamed from: clone, reason: collision with other method in class */
    public AffineTransform m22462clone() {
        AffineTransform r0 = new AffineTransform(this, this.canvas);
        r0.setScale(this.scaleX, this.scaleY);
        r0.setTranslate(this.translateX, this.translateY);
        r0.save = this.canvas.save();
        return r0;
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    public double getScaleX() {
        return this.scaleX;
    }

    public double getScaleY() {
        return this.scaleY;
    }

    public AffineTransform restore() {
        int r0 = this.save;
        if (r0 == (-1)) goto L5;
        this.canvas.restoreToCount(r0);
        this.save = -1;
    L5:
        AffineTransform r3 = this.parent;
        if (r3 == null) goto L8;
        return r3;
    L8:
        Segment$$ExternalSyntheticBUOutline1.m("Cannot restore root transform instance");
        return null;
    }

    public AffineTransform save() {
        AffineTransform r0 = new AffineTransform(this, this.canvas);
        r0.setScale(this.scaleX, this.scaleY);
        r0.setTranslate(this.translateX, this.translateY);
        r0.save = this.canvas.save();
        return r0;
    }

    public void scale(double r1, double r3) {
        setScale(r1, r3);
        this.canvas.scale((float) r1, (float) r3);
    }

    public void setScale(double r1, double r3) {
        this.scaleX = r1;
        this.scaleY = r3;
    }

    public void setTranslate(float r1, float r2) {
        this.translateX = r1;
        this.translateY = r2;
    }

    public void translate(float r2, float r3) {
        this.canvas.translate(r2, r3);
        setTranslate(r2, r3);
    }

    public float translateX() {
        return this.translateX;
    }

    public float translateY() {
        return this.translateY;
    }
}
