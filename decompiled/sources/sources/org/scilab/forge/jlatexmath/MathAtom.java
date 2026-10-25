package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class MathAtom extends Atom {
    protected Atom base;
    private int style;

    public MathAtom(Atom r1, int r2) {
        this.base = r1;
        this.style = r2;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r3) {
        TeXEnvironment r32 = r3.copy(r3.getTeXFont().copy());
        r32.getTeXFont().setRoman(false);
        int r0 = r32.getStyle();
        r32.setStyle(this.style);
        Box r2 = this.base.createBox(r32);
        r32.setStyle(r0);
        return r2;
    }
}
