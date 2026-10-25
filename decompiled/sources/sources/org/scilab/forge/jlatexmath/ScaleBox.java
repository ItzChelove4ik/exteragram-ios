package org.scilab.forge.jlatexmath;

import ru.noties.jlatexmath.awt.Graphics2D;

/* loaded from: classes5.dex */
public class ScaleBox extends Box {
    private Box box;
    private double xscl;
    private double yscl;

    public ScaleBox(Box r4, double r5, double r7) {
        this.box = r4;
        if (Double.isNaN(r5) == false) goto L5;
    L6:
        r5 = 0.0d;
    L7:
        this.xscl = r5;
        if (Double.isNaN(r7) == false) goto L10;
    L11:
        r7 = 0.0d;
    L12:
        this.yscl = r7;
        this.width = r4.width * ((float) Math.abs(this.xscl));
        double r52 = this.yscl;
        if (r52 <= 0.0d) goto L16;
        float r72 = r4.height;
    L17:
        this.height = r72 * ((float) r52);
        if (r52 <= 0.0d) goto L21;
        float r73 = r4.depth;
    L22:
        this.depth = r73 * ((float) r52);
        this.shift = r4.shift * ((float) r52);
        return;
    L21:
        r73 = -r4.height;
        goto L22
    L16:
        r72 = -r4.depth;
        goto L17
    L10:
        if (Double.isInfinite(r7) == false) goto L12;
    L5:
        if (Double.isInfinite(r5) == false) goto L7;
        goto L6
    }

    public ScaleBox(Box r7, float r8) {
        double r2 = r8;
        this(r7, r2, r2);
    }

    @Override // org.scilab.forge.jlatexmath.Box
    public void draw(Graphics2D r8, float r9, float r10) {
        drawDebug(r8, r9, r10);
        double r0 = this.xscl;
        if (r0 != 0.0d) goto L5;
        return;
    L5:
        if (this.yscl != 0.0d) goto L7;
        return;
    L7:
        if (r0 >= 0.0d) goto L9;
        float r02 = this.width;
    L10:
        r8.translate(r9 + r02, r10);
        r8.scale(this.xscl, this.yscl);
        this.box.draw(r8, 0.0f, 0.0f);
        r8.scale(1.0d / this.xscl, 1.0d / this.yscl);
        r8.translate((-r9) - r02, -r10);
        return;
    L9:
        r02 = 0.0f;
        goto L10
    }

    @Override // org.scilab.forge.jlatexmath.Box
    public int getLastFontId() {
        return this.box.getLastFontId();
    }
}
