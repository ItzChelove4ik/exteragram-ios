package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class BoldAtom extends Atom {
    private Atom base;

    public BoldAtom(Atom r1) {
        this.base = r1;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r3) {
        if (this.base == null) goto L7;
        TeXEnvironment r32 = r3.copy(r3.getTeXFont().copy());
        r32.getTeXFont().setBold(true);
        return this.base.createBox(r32);
    L7:
        return new StrutBox(0.0f, 0.0f, 0.0f, 0.0f);
    }
}
