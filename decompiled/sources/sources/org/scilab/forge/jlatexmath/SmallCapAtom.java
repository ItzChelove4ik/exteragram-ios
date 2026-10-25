package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class SmallCapAtom extends Atom {
    protected Atom base;

    public SmallCapAtom(Atom r1) {
        this.base = r1;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r3) {
        boolean r0 = r3.getSmallCap();
        r3.setSmallCap(true);
        Box r2 = this.base.createBox(r3);
        r3.setSmallCap(r0);
        return r2;
    }
}
