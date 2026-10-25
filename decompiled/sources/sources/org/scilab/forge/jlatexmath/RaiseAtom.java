package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class RaiseAtom extends Atom {
    private Atom base;
    private float d;
    private int dunit;
    private float h;
    private int hunit;
    private float r;
    private int runit;

    public RaiseAtom(Atom r1, int r2, float r3, int r4, float r5, int r6, float r7) {
        this.base = r1;
        this.runit = r2;
        this.r = r3;
        this.hunit = r4;
        this.h = r5;
        this.dunit = r6;
        this.d = r7;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r6) {
        Box r0 = this.base.createBox(r6);
        int r1 = this.runit;
        if (r1 != (-1)) goto L5;
        r0.setShift(0.0f);
    L7:
        if (this.hunit != (-1)) goto L9;
        return r0;
    L9:
        HorizontalBox r12 = new HorizontalBox(r0);
        r12.setHeight(this.h * SpaceAtom.getFactor(this.hunit, r6));
        int r02 = this.dunit;
        if (r02 != (-1)) goto L13;
        r12.setDepth(0.0f);
        return r12;
    L13:
        r12.setDepth(this.d * SpaceAtom.getFactor(r02, r6));
        return r12;
    L5:
        r0.setShift((-this.r) * SpaceAtom.getFactor(r1, r6));
        goto L7
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
