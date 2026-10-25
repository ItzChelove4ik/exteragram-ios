package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class DdotsAtom extends Atom {
    public DdotsAtom() {
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r7) {
        float r6 = TeXFormula.get("ldots").root.createBox(r7).getWidth();
        Box r0 = SymbolAtom.get("ldotp").createBox(r7);
        HorizontalBox r1 = new HorizontalBox(r0, r6, 0);
        HorizontalBox r2 = new HorizontalBox(r0, r6, 2);
        HorizontalBox r3 = new HorizontalBox(r0, r6, 1);
        Box r62 = new SpaceAtom(5, 0.0f, 4.0f, 0.0f).createBox(r7);
        VerticalBox r72 = new VerticalBox();
        r72.add(r1);
        r72.add(r62);
        r72.add(r2);
        r72.add(r62);
        r72.add(r3);
        r72.setHeight(r72.getHeight() + r72.getDepth());
        r72.setDepth(0.0f);
        return r72;
    }
}
