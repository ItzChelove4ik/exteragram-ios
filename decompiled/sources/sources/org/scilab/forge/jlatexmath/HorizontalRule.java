package org.scilab.forge.jlatexmath;

import ru.noties.jlatexmath.awt.Color;
import ru.noties.jlatexmath.awt.Graphics2D;
import ru.noties.jlatexmath.awt.geom.Rectangle2D;

/* loaded from: classes5.dex */
public class HorizontalRule extends Box {
    private Color color;
    private float speShift;

    public HorizontalRule(float r2, float r3, float r4) {
        this.color = null;
        this.speShift = 0.0f;
        this.height = r2;
        this.width = r3;
        this.shift = r4;
    }

    public HorizontalRule(float r2, float r3, float r4, Color r5) {
        this.speShift = 0.0f;
        this.height = r2;
        this.width = r3;
        this.color = r5;
        this.shift = r4;
    }

    public HorizontalRule(float r2, float r3, float r4, boolean r5) {
        this.color = null;
        this.speShift = 0.0f;
        this.height = r2;
        this.width = r3;
        if (r5 == false) goto L6;
        this.shift = r4;
        return;
    L6:
        this.shift = 0.0f;
        this.speShift = r4;
    }

    @Override // org.scilab.forge.jlatexmath.Box
    public void draw(Graphics2D r5, float r6, float r7) {
        Color r0 = r5.getColor();
        Color r1 = this.color;
        if (r1 == null) goto L5;
        r5.setColor(r1);
    L5:
        float r12 = this.speShift;
        float r3 = this.height;
        if (r12 != 0.0f) goto L8;
        r5.fill(new Rectangle2D.Float(r6, r7 - r3, this.width, r3));
    L9:
        r5.setColor(r0);
        return;
    L8:
        r5.fill(new Rectangle2D.Float(r6, (r7 - r3) + r12, this.width, r3));
        goto L9
    }

    @Override // org.scilab.forge.jlatexmath.Box
    public int getLastFontId() {
        return -1;
    }
}
