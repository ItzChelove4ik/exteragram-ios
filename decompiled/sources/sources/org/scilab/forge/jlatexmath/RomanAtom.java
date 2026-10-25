package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class RomanAtom extends Atom {
    protected Atom base;

    public RomanAtom(Atom r1) {
        this.base = r1;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r3) {
        if (this.base == null) goto L7;
        TeXEnvironment r32 = r3.copy(r3.getTeXFont().copy());
        r32.getTeXFont().setRoman(true);
        return this.base.createBox(r32);
    L7:
        return new StrutBox(0.0f, 0.0f, 0.0f, 0.0f);
    }
}
