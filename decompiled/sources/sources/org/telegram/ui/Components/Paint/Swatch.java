package org.telegram.ui.Components.Paint;

/* loaded from: classes7.dex */
public class Swatch {
    public float brushWeight;
    public int color;
    public float colorLocation;

    public Swatch(int r1, float r2, float r3) {
        this.color = r1;
        this.colorLocation = r2;
        this.brushWeight = r3;
    }

    public /* bridge */ /* synthetic */ Object clone() {
        return m11415clone();
    }

    /* renamed from: clone, reason: collision with other method in class */
    public Swatch m11415clone() {
        return new Swatch(this.color, this.colorLocation, this.brushWeight);
    }
}
