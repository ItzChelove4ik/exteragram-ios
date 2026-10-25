package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class OvalAtom extends FBoxAtom {
    public OvalAtom(Atom r1) {
        super(r1);
    }

    @Override // org.scilab.forge.jlatexmath.FBoxAtom, org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r2) {
        return new OvalBox((FramedBox) super.createBox(r2));
    }
}
