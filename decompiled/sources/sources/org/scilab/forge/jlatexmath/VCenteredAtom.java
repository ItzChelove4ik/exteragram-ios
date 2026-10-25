package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class VCenteredAtom extends Atom {
    private final Atom atom;

    public VCenteredAtom(Atom r1) {
        this.atom = r1;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r3) {
        Box r2 = this.atom.createBox(r3);
        float r0 = r2.getHeight() + r2.getDepth();
        float r02 = -(r0 / 2.0f);
        r2.setShift(r02 - r3.getTeXFont().getAxisHeight(r3.getStyle()));
        return new HorizontalBox(r2);
    }
}
