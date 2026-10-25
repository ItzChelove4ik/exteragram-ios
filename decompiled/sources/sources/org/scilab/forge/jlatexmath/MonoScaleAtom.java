package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class MonoScaleAtom extends ScaleAtom {
    private float factor;

    public MonoScaleAtom(Atom r7, float r8) {
        double r2 = r8;
        super(r7, r2, r2);
        this.factor = r8;
    }

    @Override // org.scilab.forge.jlatexmath.ScaleAtom, org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r4) {
        TeXEnvironment r42 = r4.copy();
        float r0 = r42.getScaleFactor();
        r42.setScaleFactor(this.factor);
        return new ScaleBox(this.base.createBox(r42), this.factor / r0);
    }
}
