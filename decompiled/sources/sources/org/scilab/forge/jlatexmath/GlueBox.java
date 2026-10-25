package org.scilab.forge.jlatexmath;

import ru.noties.jlatexmath.awt.Graphics2D;

/* loaded from: classes5.dex */
public class GlueBox extends Box {
    protected float shrink;
    protected float stretch;

    public GlueBox(float r1, float r2, float r3) {
        this.width = r1;
        this.stretch = r2;
        this.shrink = r3;
    }

    @Override // org.scilab.forge.jlatexmath.Box
    public void draw(Graphics2D r1, float r2, float r3) {
    }

    @Override // org.scilab.forge.jlatexmath.Box
    public int getLastFontId() {
        return -1;
    }
}
