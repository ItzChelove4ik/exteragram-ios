package org.scilab.forge.jlatexmath;

import ru.noties.jlatexmath.awt.BasicStroke;
import ru.noties.jlatexmath.awt.Graphics2D;
import ru.noties.jlatexmath.awt.Stroke;
import ru.noties.jlatexmath.awt.geom.Rectangle2D;

/* loaded from: classes5.dex */
public class ShadowBox extends FramedBox {
    private float shadowRule;

    public ShadowBox(FramedBox r3, float r4) {
        super(r3.box, r3.thickness, r3.space);
        this.shadowRule = r4;
        this.depth += r4;
        this.width += r4;
    }

    @Override // org.scilab.forge.jlatexmath.FramedBox, org.scilab.forge.jlatexmath.Box
    public void draw(Graphics2D r13, float r14, float r15) {
        float r0 = this.thickness;
        float r2 = r0 / 2.0f;
        this.box.draw(r13, (this.space + r14) + r0, r15);
        Stroke r02 = r13.getStroke();
        r13.setStroke(new BasicStroke(this.thickness, 0, 0));
        float r6 = this.height;
        float r8 = this.width;
        float r9 = this.shadowRule;
        float r10 = this.thickness;
        r13.draw(new Rectangle2D.Float(r14 + r2, (r15 - r6) + r2, (r8 - r9) - r10, ((r6 + this.depth) - r9) - r10));
        float r3 = (float) Math.abs(1.0d / r13.getTransform().getScaleX());
        r13.setStroke(new BasicStroke(r3, 0, 0));
        float r5 = this.shadowRule;
        r13.fill(new Rectangle2D.Float((r14 + r5) - r3, ((this.depth + r15) - r5) - r3, this.width - r5, r5));
        float r142 = r14 + this.width;
        float r52 = this.shadowRule;
        float r143 = (r142 - r52) - r3;
        float r32 = this.height;
        r13.fill(new Rectangle2D.Float(r143, ((r15 - r32) + r2) + r52, r52, ((this.depth + r32) - (2.0f * r52)) - r2));
        r13.setStroke(r02);
    }

    @Override // org.scilab.forge.jlatexmath.FramedBox, org.scilab.forge.jlatexmath.Box
    public int getLastFontId() {
        return this.box.getLastFontId();
    }
}
