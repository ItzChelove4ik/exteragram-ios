package ru.noties.jlatexmath.awt;

/* loaded from: classes7.dex */
public class BasicStroke implements Stroke {
    public static final int CAP_BUTT = 0;
    public static final int JOIN_MITER = 0;
    private final float miterLimit;
    private final float width;

    public BasicStroke(float r2, int r3, int r4) {
        this(r2, r3, r4, 10.0f);
    }

    public BasicStroke(float r1, int r2, int r3, float r4) {
        this.width = r1;
        this.miterLimit = r4;
    }

    @Override // ru.noties.jlatexmath.awt.Stroke
    public float miterLimit() {
        return this.miterLimit;
    }

    public String toString() {
        return "BasicStroke{width=" + this.width + ", miterLimit=" + this.miterLimit + '}';
    }

    @Override // ru.noties.jlatexmath.awt.Stroke
    public float width() {
        return this.width;
    }
}
