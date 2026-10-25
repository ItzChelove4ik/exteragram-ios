package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class LapedAtom extends Atom {
    private Atom at;
    private char type;

    public LapedAtom(Atom r1, char r2) {
        this.at = r1;
        this.type = r2;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r4) {
        Box r42 = this.at.createBox(r4);
        VerticalBox r0 = new VerticalBox();
        r0.add(r42);
        r0.setWidth(0.0f);
        char r3 = this.type;
        if (r3 != 'l') goto L5;
        r42.setShift(-r42.getWidth());
        return r0;
    L5:
        if (r3 == 'r') goto L8;
        r42.setShift((-r42.getWidth()) / 2.0f);
        return r0;
    L8:
        r42.setShift(0.0f);
        return r0;
    }
}
