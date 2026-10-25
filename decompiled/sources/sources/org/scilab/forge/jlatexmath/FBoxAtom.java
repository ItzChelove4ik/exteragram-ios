package org.scilab.forge.jlatexmath;

import ru.noties.jlatexmath.awt.Color;

/* loaded from: classes5.dex */
public class FBoxAtom extends Atom {
    public float INTERSPACE;
    protected final Atom base;
    protected Color bg;
    protected Color line;

    public FBoxAtom(Atom r2) {
        this.INTERSPACE = 0.65f;
        this.bg = null;
        this.line = null;
        if (r2 != null) goto L6;
        this.base = new RowAtom();
        return;
    L6:
        this.base = r2;
        this.type = r2.type;
    }

    public FBoxAtom(Atom r1, Color r2, Color r3) {
        this(r1);
        this.bg = r2;
        this.line = r3;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r8) {
        Box r2 = this.base.createBox(r8);
        float r3 = r8.getTeXFont().getDefaultRuleThickness(r8.getStyle());
        float r4 = this.INTERSPACE * SpaceAtom.getFactor(0, r8);
        if (this.bg == null) goto L5;
        r8.isColored = true;
        return new FramedBox(r2, r3, r4, this.line, this.bg);
    L5:
        return new FramedBox(r2, r3, r4);
    }
}
