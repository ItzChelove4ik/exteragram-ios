package org.scilab.forge.jlatexmath;

import ru.noties.jlatexmath.awt.BasicStroke;
import ru.noties.jlatexmath.awt.Color;
import ru.noties.jlatexmath.awt.Graphics2D;
import ru.noties.jlatexmath.awt.Stroke;
import ru.noties.jlatexmath.awt.geom.Rectangle2D;

/* loaded from: classes5.dex */
public class FramedBox extends Box {
    private Color bg;
    protected Box box;
    private Color line;
    protected float space;
    protected float thickness;

    public FramedBox(Box r4, float r5, float r6) {
        this.box = r4;
        this.width = (r4.width + (r5 * 2.0f)) + (2.0f * r6);
        this.height = (r4.height + r5) + r6;
        this.depth = (r4.depth + r5) + r6;
        this.shift = r4.shift;
        this.thickness = r5;
        this.space = r6;
    }

    public FramedBox(Box r1, float r2, float r3, Color r4, Color r5) {
        this(r1, r2, r3);
        this.line = r4;
        this.bg = r5;
    }

    @Override // org.scilab.forge.jlatexmath.Box
    public void draw(Graphics2D r11, float r12, float r13) {
        Stroke r0 = r11.getStroke();
        r11.setStroke(new BasicStroke(this.thickness, 0, 0));
        float r1 = this.thickness / 2.0f;
        if (this.bg == null) goto L6;
        Color r2 = r11.getColor();
        r11.setColor(this.bg);
        float r5 = this.height;
        float r7 = this.width;
        float r8 = this.thickness;
        r11.fill(new Rectangle2D.Float(r12 + r1, (r13 - r5) + r1, r7 - r8, (r5 + this.depth) - r8));
        r11.setColor(r2);
    L6:
        if (this.line == null) goto L8;
        Color r22 = r11.getColor();
        r11.setColor(this.line);
        float r4 = r12 + r1;
        float r52 = this.height;
        float r6 = (r13 - r52) + r1;
        float r14 = this.width;
        float r72 = this.thickness;
        r11.draw(new Rectangle2D.Float(r4, r6, r14 - r72, (r52 + this.depth) - r72));
        r11.setColor(r22);
    L9:
        r11.setStroke(r0);
        this.box.draw(r11, (r12 + this.space) + this.thickness, r13);
        return;
    L8:
        float r3 = r12 + r1;
        float r42 = this.height;
        float r53 = (r13 - r42) + r1;
        float r15 = this.width;
        float r62 = this.thickness;
        r11.draw(new Rectangle2D.Float(r3, r53, r15 - r62, (r42 + this.depth) - r62));
        goto L9
    }

    @Override // org.scilab.forge.jlatexmath.Box
    public int getLastFontId() {
        return this.box.getLastFontId();
    }
}
