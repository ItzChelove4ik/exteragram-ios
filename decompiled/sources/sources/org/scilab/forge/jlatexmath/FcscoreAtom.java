package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class FcscoreAtom extends Atom {
    private int N;

    public FcscoreAtom(int r1) {
        this.N = r1;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r8) {
        float r82 = SpaceAtom.getFactor(5, r8) * 12.0f;
        int r7 = this.N;
        if (r7 != 5) goto L5;
        int r2 = 4;
    L6:
        float r3 = 1.0f * r82;
        float r4 = 0.07f * r82;
        float r5 = 0.125f * r82;
        if (r7 != 5) goto L10;
        boolean r72 = true;
    L12:
        return new FcscoreBox(r2, r3, r4, r5, r72);
    L10:
        r72 = false;
        goto L12
    L5:
        r2 = r7;
        goto L6
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public int getLeftType() {
        return 0;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public int getRightType() {
        return 0;
    }
}
