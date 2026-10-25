package org.scilab.forge.jlatexmath;

import ru.noties.jlatexmath.awt.Graphics2D;

/* loaded from: classes5.dex */
public class OverBar extends VerticalBox {
    public OverBar(Box r4, float r5, float r6) {
        add(new StrutBox(0.0f, r6, 0.0f, 0.0f));
        add(new HorizontalRule(r6, r4.getWidth(), 0.0f));
        add(new StrutBox(0.0f, r5, 0.0f, 0.0f));
        add(r4);
    }

    @Override // org.scilab.forge.jlatexmath.VerticalBox, org.scilab.forge.jlatexmath.Box
    public /* bridge */ /* synthetic */ void add(int r1, Box r2) {
        super.add(r1, r2);
    }

    @Override // org.scilab.forge.jlatexmath.VerticalBox, org.scilab.forge.jlatexmath.Box
    public /* bridge */ /* synthetic */ void draw(Graphics2D r1, float r2, float r3) {
        super.draw(r1, r2, r3);
    }

    @Override // org.scilab.forge.jlatexmath.VerticalBox, org.scilab.forge.jlatexmath.Box
    public /* bridge */ /* synthetic */ int getLastFontId() {
        return super.getLastFontId();
    }

    @Override // org.scilab.forge.jlatexmath.VerticalBox
    public /* bridge */ /* synthetic */ int getSize() {
        return super.getSize();
    }
}
