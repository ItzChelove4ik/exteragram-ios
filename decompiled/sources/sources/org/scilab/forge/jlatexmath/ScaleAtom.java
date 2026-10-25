package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class ScaleAtom extends Atom {
    protected Atom base;
    private double xscl;
    private double yscl;

    public ScaleAtom(Atom r2, double r3) {
        this.type = r2.type;
        this.base = r2;
        this.xscl = r3;
        this.yscl = r3;
    }

    public ScaleAtom(Atom r2, double r3, double r5) {
        this.type = r2.type;
        this.base = r2;
        this.xscl = r3;
        this.yscl = r5;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r7) {
        return new ScaleBox(this.base.createBox(r7), this.xscl, this.yscl);
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public int getLeftType() {
        return this.base.getLeftType();
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public int getRightType() {
        return this.base.getRightType();
    }
}
