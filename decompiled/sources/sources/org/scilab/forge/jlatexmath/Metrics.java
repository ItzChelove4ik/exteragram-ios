package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class Metrics {
    private final float d;
    private final float h;
    private final float i;
    private final float s;
    private final float w;

    public Metrics(float r1, float r2, float r3, float r4, float r5, float r6) {
        this.w = r1 * r5;
        this.h = r2 * r5;
        this.d = r3 * r5;
        this.i = r4 * r5;
        this.s = r6;
    }

    public float getDepth() {
        return this.d;
    }

    public float getHeight() {
        return this.h;
    }

    public float getItalic() {
        return this.i;
    }

    public float getSize() {
        return this.s;
    }

    public float getWidth() {
        return this.w;
    }
}
