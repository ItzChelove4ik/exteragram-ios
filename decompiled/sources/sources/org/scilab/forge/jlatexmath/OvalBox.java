package org.scilab.forge.jlatexmath;

import ru.noties.jlatexmath.awt.BasicStroke;
import ru.noties.jlatexmath.awt.Graphics2D;
import ru.noties.jlatexmath.awt.Stroke;
import ru.noties.jlatexmath.awt.geom.RoundRectangle2D;

/* loaded from: classes5.dex */
public class OvalBox extends FramedBox {
    public OvalBox(FramedBox r3) {
        super(r3.box, r3.thickness, r3.space);
    }

    @Override // org.scilab.forge.jlatexmath.FramedBox, org.scilab.forge.jlatexmath.Box
    public void draw(Graphics2D r12, float r13, float r14) {
        this.box.draw(r12, (this.space + r13) + this.thickness, r14);
        Stroke r0 = r12.getStroke();
        r12.setStroke(new BasicStroke(this.thickness, 0, 0));
        float r1 = this.thickness;
        float r2 = r1 / 2.0f;
        float r9 = Math.min(this.width - r1, (this.height + this.depth) - r1) * 0.5f;
        float r5 = r13 + r2;
        float r132 = this.height;
        float r6 = (r14 - r132) + r2;
        float r142 = this.width;
        float r15 = this.thickness;
        r12.draw(new RoundRectangle2D.Float(r5, r6, r142 - r15, (r132 + this.depth) - r15, r9, r9));
        r12.setStroke(r0);
    }

    @Override // org.scilab.forge.jlatexmath.FramedBox, org.scilab.forge.jlatexmath.Box
    public int getLastFontId() {
        return this.box.getLastFontId();
    }
}
