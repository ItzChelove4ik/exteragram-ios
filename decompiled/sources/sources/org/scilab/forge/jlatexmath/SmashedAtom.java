package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class SmashedAtom extends Atom {
    private Atom at;
    private boolean d;
    private boolean h;

    public SmashedAtom(Atom r2) {
        this.h = true;
        this.d = true;
        this.at = r2;
    }

    public SmashedAtom(Atom r2, String r3) {
        this.h = true;
        this.d = true;
        this.at = r2;
        if ("t".equals(r3) == false) goto L7;
        this.d = false;
        return;
    L7:
        if ("b".equals(r3) == false) goto L10;
        this.h = false;
        return;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r3) {
        Box r32 = this.at.createBox(r3);
        if (this.h == false) goto L6;
        r32.setHeight(0.0f);
    L6:
        if (this.d == false) goto L8;
        r32.setDepth(0.0f);
    L8:
        return r32;
    }
}
