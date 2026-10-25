package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class TextCircledAtom extends Atom {
    private Atom at;

    public TextCircledAtom(Atom r1) {
        this.at = r1;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r4) {
        Box r0 = SymbolAtom.get("bigcirc").createBox(r4);
        r0.setShift(SpaceAtom.getFactor(1, r4) * (-0.07f));
        HorizontalBox r42 = new HorizontalBox(this.at.createBox(r4), r0.getWidth(), 2);
        r42.add(new StrutBox(-r42.getWidth(), 0.0f, 0.0f, 0.0f));
        r42.add(r0);
        return r42;
    }
}
