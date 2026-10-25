package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class FixedCharAtom extends CharSymbol {
    private final CharFont cf;

    public FixedCharAtom(CharFont r1) {
        this.cf = r1;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r2) {
        return new CharBox(r2.getTeXFont().getChar(this.cf, r2.getStyle()));
    }

    @Override // org.scilab.forge.jlatexmath.CharSymbol
    public CharFont getCharFont(TeXFont r1) {
        return this.cf;
    }
}
