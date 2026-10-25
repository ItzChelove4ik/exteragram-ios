package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class OgonekAtom extends Atom {
    private Atom base;

    public OgonekAtom(Atom r1) {
        this.base = r1;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r7) {
        Box r6 = this.base.createBox(r7);
        VerticalBox r0 = new VerticalBox();
        r0.add(r6);
        Char r72 = r7.getTeXFont().getChar("ogonek", r7.getStyle());
        float r1 = r72.getItalic();
        CharBox r2 = new CharBox(r72);
        if (Math.abs(r1) <= 1.0E-7f) goto L5;
        Box r73 = new HorizontalBox(new StrutBox(-r1, 0.0f, 0.0f, 0.0f));
        r73.add(r2);
    L6:
        HorizontalBox r12 = new HorizontalBox(r73, r6.getWidth(), 1);
        r0.add(new StrutBox(0.0f, -r2.getHeight(), 0.0f, 0.0f));
        r0.add(r12);
        float r74 = r0.getHeight() + r0.getDepth();
        r0.setHeight(r6.getHeight());
        r0.setDepth(r74 - r6.getHeight());
        return r0;
    L5:
        r73 = r2;
        goto L6
    }
}
