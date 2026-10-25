package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class BigDelimiterAtom extends Atom {
    public SymbolAtom delim;
    private int size;

    public BigDelimiterAtom(SymbolAtom r1, int r2) {
        this.delim = r1;
        this.size = r2;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r5) {
        Box r4 = DelimiterFactory.create(this.delim, r5, this.size);
        HorizontalBox r0 = new HorizontalBox();
        float r1 = r4.getHeight();
        float r2 = r4.getDepth() + r1;
        float r22 = ((-r2) / 2.0f) + r1;
        r4.setShift(r22 - r5.getTeXFont().getAxisHeight(r5.getStyle()));
        r0.add(r4);
        return r0;
    }
}
