package org.scilab.forge.jlatexmath;

import ru.noties.jlatexmath.awt.Graphics2D;

/* loaded from: classes5.dex */
public class ReflectBox extends Box {
    private Box box;

    public ReflectBox(Box r2) {
        this.box = r2;
        this.width = r2.width;
        this.height = r2.height;
        this.depth = r2.depth;
        this.shift = r2.shift;
    }

    @Override // org.scilab.forge.jlatexmath.Box
    public void draw(Graphics2D r7, float r8, float r9) {
        drawDebug(r7, r8, r9);
        r7.translate(r8, r9);
        r7.scale(-1.0d, 1.0d);
        this.box.draw(r7, -this.width, 0.0f);
        r7.scale(-1.0d, 1.0d);
        r7.translate(-r8, -r9);
    }

    @Override // org.scilab.forge.jlatexmath.Box
    public int getLastFontId() {
        return this.box.getLastFontId();
    }
}
