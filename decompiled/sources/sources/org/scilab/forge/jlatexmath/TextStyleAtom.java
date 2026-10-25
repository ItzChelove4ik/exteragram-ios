package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class TextStyleAtom extends Atom {
    private Atom at;
    private String style;

    public TextStyleAtom(Atom r1, String r2) {
        this.style = r2;
        this.at = r1;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r3) {
        String r0 = r3.getTextStyle();
        r3.setTextStyle(this.style);
        Box r2 = this.at.createBox(r3);
        r3.setTextStyle(r0);
        return r2;
    }
}
