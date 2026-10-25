package org.scilab.forge.jlatexmath;

import java.util.List;

/* loaded from: classes5.dex */
public class FencedAtom extends Atom {
    private static final int DELIMITER_FACTOR = 901;
    private static final float DELIMITER_SHORTFALL = 5.0f;
    private final Atom base;
    private SymbolAtom left;
    private final List<MiddleAtom> middle;
    private SymbolAtom right;

    public FencedAtom(Atom r2, SymbolAtom r3, List<MiddleAtom> r4, SymbolAtom r5) {
        this.left = null;
        this.right = null;
        if (r2 != null) goto L5;
        this.base = new RowAtom();
    L7:
        if (r3 != null) goto L9;
    L10:
        this.left = r3;
    L11:
        if (r5 != null) goto L13;
    L14:
        this.right = r5;
    L15:
        this.middle = r4;
        return;
    L13:
        if (r5.getName().equals("normaldot") == true) goto L15;
    L9:
        if (r3.getName().equals("normaldot") == true) goto L11;
    L5:
        this.base = r2;
        goto L7
    }

    public FencedAtom(Atom r2, SymbolAtom r3, SymbolAtom r4) {
        this(r2, r3, null, r4);
    }

    private static void center(Box r3, float r4) {
        float r0 = r3.getHeight();
        r3.setShift((-(((r3.getDepth() + r0) / 2.0f) - r0)) - r4);
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r9) {
        TeXFont r0 = r9.getTeXFont();
        Box r1 = this.base.createBox(r9);
        float r2 = SpaceAtom.getFactor(3, r9) * DELIMITER_SHORTFALL;
        float r02 = r0.getAxisHeight(r9.getStyle());
        float r3 = Math.max(r1.getHeight() - r02, r1.getDepth() + r02);
        float r22 = Math.max((r3 / 500.0f) * 901.0f, (r3 * 2.0f) - r2);
        HorizontalBox r32 = new HorizontalBox();
        if (this.middle == null) goto L14;
        int r4 = 0;
    L5:
        int r5 = this.middle.size();
        List<MiddleAtom> r6 = this.middle;
        if (r4 >= r5) goto L12;
        MiddleAtom r52 = r6.get(r4);
        Atom r62 = r52.base;
        if ((r62 instanceof SymbolAtom) == false) goto L10;
        Box r63 = DelimiterFactory.create(((SymbolAtom) r62).getName(), r9, r22);
        center(r63, r02);
        r52.box = r63;
    L10:
        r4 = r4 + 1;
        goto L5
    L12:
        if (r6.size() == 0) goto L14;
        r1 = this.base.createBox(r9);
    L14:
        SymbolAtom r42 = this.left;
        if (r42 == null) goto L17;
        Box r43 = DelimiterFactory.create(r42.getName(), r9, r22);
        center(r43, r02);
        r32.add(r43);
    L17:
        Atom r44 = this.base;
        if ((r44 instanceof SpaceAtom) == true) goto L20;
        r32.add(Glue.get(4, r44.getLeftType(), r9));
    L20:
        r32.add(r1);
        Atom r12 = this.base;
        if ((r12 instanceof SpaceAtom) == true) goto L23;
        r32.add(Glue.get(r12.getRightType(), 5, r9));
    L23:
        SymbolAtom r8 = this.right;
        if (r8 == null) goto L26;
        Box r82 = DelimiterFactory.create(r8.getName(), r9, r22);
        center(r82, r02);
        r32.add(r82);
    L26:
        return r32;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public int getLeftType() {
        return 7;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public int getRightType() {
        return 7;
    }
}
