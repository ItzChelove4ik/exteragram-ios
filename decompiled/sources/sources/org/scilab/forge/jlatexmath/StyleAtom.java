package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class StyleAtom extends Atom {
    private Atom at;
    private int style;

    public StyleAtom(int r1, Atom r2) {
        this.style = r1;
        this.at = r2;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r3) {
        int r0 = r3.getStyle();
        r3.setStyle(this.style);
        Box r2 = this.at.createBox(r3);
        r3.setStyle(r0);
        return r2;
    }
}
