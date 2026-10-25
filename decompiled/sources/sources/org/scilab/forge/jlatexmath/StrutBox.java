package org.scilab.forge.jlatexmath;

import ru.noties.jlatexmath.awt.Graphics2D;

/* loaded from: classes5.dex */
public class StrutBox extends Box {
    public StrutBox(float r1, float r2, float r3, float r4) {
        this.width = r1;
        this.height = r2;
        this.depth = r3;
        this.shift = r4;
    }

    @Override // org.scilab.forge.jlatexmath.Box
    public void draw(Graphics2D r1, float r2, float r3) {
    }

    @Override // org.scilab.forge.jlatexmath.Box
    public int getLastFontId() {
        return -1;
    }
}
