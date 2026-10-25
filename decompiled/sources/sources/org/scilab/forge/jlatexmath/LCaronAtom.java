package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class LCaronAtom extends Atom {
    private boolean upper;

    public LCaronAtom(boolean r1) {
        this.upper = r1;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r7) {
        CharBox r0 = new CharBox(r7.getTeXFont().getChar("textapos", r7.getStyle()));
        TeXFont r2 = r7.getTeXFont();
        if (this.upper == false) goto L5;
        char r3 = 'L';
    L6:
        HorizontalBox r22 = new HorizontalBox(new CharBox(r2.getChar(r3, "mathnormal", r7.getStyle())));
        if (this.upper == false) goto L9;
        r22.add(new SpaceAtom(0, -0.3f, 0.0f, 0.0f).createBox(r7));
    L10:
        r22.add(r0);
        return r22;
    L9:
        r22.add(new SpaceAtom(0, -0.13f, 0.0f, 0.0f).createBox(r7));
        goto L10
    L5:
        r3 = 'l';
        goto L6
    }
}
