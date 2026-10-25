package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class MiddleAtom extends Atom {
    public Atom base;
    public Box box;

    public MiddleAtom(Atom r3) {
        this.box = new StrutBox(0.0f, 0.0f, 0.0f, 0.0f);
        this.base = r3;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r1) {
        return this.box;
    }
}
