package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class tcaronAtom extends Atom {
    public tcaronAtom() {
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r6) {
        CharBox r5 = new CharBox(r6.getTeXFont().getChar("textapos", r6.getStyle()));
        HorizontalBox r1 = new HorizontalBox(new CharBox(r6.getTeXFont().getChar('t', "mathnormal", r6.getStyle())));
        r1.add(new SpaceAtom(0, -0.3f, 0.0f, 0.0f).createBox(r6));
        r1.add(r5);
        return r1;
    }
}
