package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class PhantomAtom extends Atom implements Row {
    private boolean d;
    private RowAtom elements;
    private boolean h;
    private boolean w;

    public PhantomAtom(Atom r2) {
        this.w = true;
        this.h = true;
        this.d = true;
        if (r2 != null) goto L6;
        this.elements = new RowAtom();
        return;
    L6:
        this.elements = new RowAtom(r2);
    }

    public PhantomAtom(Atom r1, boolean r2, boolean r3, boolean r4) {
        this(r1);
        this.w = r2;
        this.h = r3;
        this.d = r4;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r5) {
        Box r52 = this.elements.createBox(r5);
        float r2 = 0.0f;
        if (this.w == false) goto L5;
        float r1 = r52.getWidth();
    L7:
        if (this.h == false) goto L9;
        float r3 = r52.getHeight();
    L11:
        if (this.d == false) goto L14;
        r2 = r52.getDepth();
    L14:
        return new StrutBox(r1, r3, r2, r52.getShift());
    L9:
        r3 = 0.0f;
        goto L11
    L5:
        r1 = 0.0f;
        goto L7
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public int getLeftType() {
        return this.elements.getLeftType();
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public int getRightType() {
        return this.elements.getRightType();
    }

    @Override // org.scilab.forge.jlatexmath.Row
    public void setPreviousAtom(Dummy r1) {
        this.elements.setPreviousAtom(r1);
    }
}
