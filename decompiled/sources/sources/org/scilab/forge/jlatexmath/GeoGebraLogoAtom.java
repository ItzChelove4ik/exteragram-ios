package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class GeoGebraLogoAtom extends Atom {
    public GeoGebraLogoAtom() {
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r3) {
        CharBox r2 = new CharBox(r3.getTeXFont().getDefaultChar('o', r3.getStyle()));
        return new GeoGebraLogoBox(r2.width, r2.height);
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public int getLeftType() {
        return 0;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public int getRightType() {
        return 0;
    }
}
