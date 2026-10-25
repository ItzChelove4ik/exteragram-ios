package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class IJAtom extends Atom {
    private boolean upper;

    public IJAtom(boolean r1) {
        this.upper = r1;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r6) {
        TeXFont r1 = r6.getTeXFont();
        if (this.upper == false) goto L5;
        char r2 = 'I';
    L6:
        CharBox r0 = new CharBox(r1.getChar(r2, "mathnormal", r6.getStyle()));
        TeXFont r22 = r6.getTeXFont();
        if (this.upper == false) goto L9;
        char r5 = 'J';
    L10:
        CharBox r12 = new CharBox(r22.getChar(r5, "mathnormal", r6.getStyle()));
        HorizontalBox r52 = new HorizontalBox(r0);
        r52.add(new SpaceAtom(0, -0.065f, 0.0f, 0.0f).createBox(r6));
        r52.add(r12);
        return r52;
    L9:
        r5 = 'j';
        goto L10
    L5:
        r2 = 'i';
        goto L6
    }
}
