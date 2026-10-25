package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class RuleAtom extends Atom {
    private static final float MAX_LENGTH = 4096.0f;
    private float h;
    private int hunit;
    private float r;
    private int runit;
    private float w;
    private int wunit;

    public RuleAtom(int r1, float r2, int r3, float r4, int r5, float r6) {
        this.wunit = r1;
        this.hunit = r3;
        this.runit = r5;
        this.w = r2;
        this.h = r4;
        this.r = r6;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r4) {
        float r0 = this.w * SpaceAtom.getFactor(this.wunit, r4);
        float r1 = this.h * SpaceAtom.getFactor(this.hunit, r4);
        float r2 = this.r * SpaceAtom.getFactor(this.runit, r4);
        if (Float.isFinite(r0) == true) goto L5;
    L6:
        r0 = 4096.0f;
    L8:
        if (Float.isFinite(r1) == true) goto L10;
    L11:
        r1 = 4096.0f;
    L13:
        return new HorizontalRule(r1, r0, r2);
    L10:
        if (r1 <= MAX_LENGTH) goto L13;
    L5:
        if (r0 <= MAX_LENGTH) goto L8;
        goto L6
    }
}
