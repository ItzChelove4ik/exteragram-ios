package org.scilab.forge.jlatexmath;

import ru.noties.jlatexmath.awt.BasicStroke;
import ru.noties.jlatexmath.awt.Graphics2D;
import ru.noties.jlatexmath.awt.Stroke;
import ru.noties.jlatexmath.awt.geom.AffineTransform;
import ru.noties.jlatexmath.awt.geom.Line2D;

/* loaded from: classes5.dex */
public class FcscoreBox extends Box {
    private int N;
    private float space;
    private boolean strike;
    private float thickness;

    public FcscoreBox(int r2, float r3, float r4, float r5, boolean r6) {
        if (r2 >= 0) goto L6;
        r2 = 0;
    L8:
        this.N = r2;
        this.width = (r2 * (r4 + r5)) + (2.0f * r5);
        this.height = r3;
        this.depth = 0.0f;
        this.strike = r6;
        this.space = r5;
        this.thickness = r4;
        return;
    L6:
        if (r2 <= 4096) goto L8;
        r2 = 4096;
        goto L8
    }

    @Override // org.scilab.forge.jlatexmath.Box
    public void draw(Graphics2D r24, float r25, float r26) {
        AffineTransform r3 = r24.getTransform();
        Stroke r4 = r24.getStroke();
        double r5 = r3.getScaleX();
        double r7 = r3.getScaleY();
        if (r5 != r7) goto L5;
        AffineTransform r9 = r3.m22462clone();
        r9.scale(1.0d / r5, 1.0d / r7);
        r24.setTransform(r9);
    L6:
        int r92 = 0;
        r24.setStroke(new BasicStroke((float) (this.thickness * r5), 0, 0));
        float r8 = 2.0f;
        float r72 = this.thickness / 2.0f;
        Line2D.Float r10 = new Line2D.Float();
        float r11 = this.space;
        float r12 = (float) (((r25 + r11) * r5) + ((r11 / 2.0f) * r5));
        int r112 = (int) Math.round((r11 + this.thickness) * r5);
    L8:
        if (r92 >= this.N) goto L10;
        float r19 = r8;
        double r13 = r12 + (r72 * r5);
        double r21 = r5;
        int r52 = r112;
        r10.setLine(r13, (r26 - this.height) * r5, r13, r26 * r21);
        r24.draw(r10);
        r12 = r12 + r52;
        r92 = r92 + 1;
        r112 = r52;
        r8 = r19;
        r5 = r21;
        goto L8
    L10:
        double r212 = r5;
        float r192 = r8;
        float r6 = r12;
        if (this.strike == false) goto L13;
        float r53 = this.space;
        float r0 = this.height;
        r10.setLine((r25 + r53) * r212, (r26 - (r0 / r192)) * r212, r6 - ((r53 * r212) / 2.0d), (r26 - (r0 / r192)) * r212);
        r24.draw(r10);
    L13:
        r24.setTransform(r3);
        r24.setStroke(r4);
        return;
    L5:
        r5 = 1.0d;
        goto L6
    }

    @Override // org.scilab.forge.jlatexmath.Box
    public int getLastFontId() {
        return -1;
    }
}
