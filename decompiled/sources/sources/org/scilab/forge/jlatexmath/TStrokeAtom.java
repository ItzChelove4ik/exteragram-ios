package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class TStrokeAtom extends Atom {
    private boolean upper;

    public TStrokeAtom(boolean r1) {
        this.upper = r1;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r6) {
        Char r0 = r6.getTeXFont().getChar("bar", r6.getStyle());
        float r1 = r0.getItalic();
        TeXFont r3 = r6.getTeXFont();
        if (this.upper == false) goto L5;
        char r5 = 'T';
    L6:
        CharBox r2 = new CharBox(r3.getChar(r5, "mathnormal", r6.getStyle()));
        Box r52 = new CharBox(r0);
        if (Math.abs(r1) <= 1.0E-7f) goto L9;
        Box r62 = new HorizontalBox(new StrutBox(-r1, 0.0f, 0.0f, 0.0f));
        r62.add(r52);
        r52 = r62;
    L9:
        HorizontalBox r63 = new HorizontalBox(r52, r2.getWidth(), 2);
        VerticalBox r53 = new VerticalBox();
        r53.add(r2);
        r53.add(new StrutBox(0.0f, r2.getHeight() * (-0.5f), 0.0f, 0.0f));
        r53.add(r63);
        return r53;
    L5:
        r5 = 't';
        goto L6
    }
}
