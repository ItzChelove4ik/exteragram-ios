package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class VdotsAtom extends Atom {
    public VdotsAtom() {
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r6) {
        Box r5 = SymbolAtom.get("ldotp").createBox(r6);
        VerticalBox r0 = new VerticalBox(r5, 0.0f, 4);
        Box r62 = new SpaceAtom(5, 0.0f, 4.0f, 0.0f).createBox(r6);
        r0.add(r62);
        r0.add(r5);
        r0.add(r62);
        r0.add(r5);
        float r52 = r0.getDepth();
        float r63 = r0.getHeight();
        r0.setDepth(0.0f);
        r0.setHeight(r52 + r63);
        return r0;
    }
}
