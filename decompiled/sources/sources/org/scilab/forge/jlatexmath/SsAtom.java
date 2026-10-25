package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class SsAtom extends Atom {
    private Atom base;

    public SsAtom(Atom r1) {
        this.base = r1;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r3) {
        TeXEnvironment r32 = r3.copy(r3.getTeXFont().copy());
        r32.getTeXFont().setSs(true);
        Box r2 = this.base.createBox(r32);
        r32.getTeXFont().setSs(false);
        return r2;
    }
}
