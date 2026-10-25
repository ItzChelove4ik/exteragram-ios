package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class ReflectAtom extends Atom {
    private Atom base;

    public ReflectAtom(Atom r2) {
        this.type = r2.type;
        this.base = r2;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r2) {
        return new ReflectBox(this.base.createBox(r2));
    }
}
