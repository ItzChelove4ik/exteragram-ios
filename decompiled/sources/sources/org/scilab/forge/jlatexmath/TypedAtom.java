package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class TypedAtom extends Atom {
    private final Atom atom;
    private final int leftType;
    private final int rightType;

    public TypedAtom(int r1, int r2, Atom r3) {
        this.leftType = r1;
        this.rightType = r2;
        this.atom = r3;
        this.type_limits = r3.type_limits;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r1) {
        return this.atom.createBox(r1);
    }

    public Atom getBase() {
        Atom r0 = this.atom;
        r0.type_limits = this.type_limits;
        return r0;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public int getLeftType() {
        return this.leftType;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public int getRightType() {
        return this.rightType;
    }
}
