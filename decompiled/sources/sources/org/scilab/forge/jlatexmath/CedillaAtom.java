package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class CedillaAtom extends Atom {
    private Atom base;

    public CedillaAtom(Atom r1) {
        this.base = r1;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r7) {
        Box r6 = this.base.createBox(r7);
        VerticalBox r0 = new VerticalBox();
        r0.add(r6);
        Char r1 = r7.getTeXFont().getChar("jlatexmathcedilla", r7.getStyle());
        float r2 = r1.getItalic();
        Box r3 = new CharBox(r1);
        if (Math.abs(r2) <= 1.0E-7f) goto L5;
        Box r12 = new HorizontalBox(new StrutBox(-r2, 0.0f, 0.0f, 0.0f));
        r12.add(r3);
        r3 = r12;
    L5:
        HorizontalBox r13 = new HorizontalBox(r3, r6.getWidth(), 2);
        r0.add(new StrutBox(0.0f, -(SpaceAtom.getFactor(5, r7) * 0.4f), 0.0f, 0.0f));
        r0.add(r13);
        float r72 = r0.getHeight() + r0.getDepth();
        r0.setHeight(r6.getHeight());
        r0.setDepth(r72 - r6.getHeight());
        return r0;
    }
}
