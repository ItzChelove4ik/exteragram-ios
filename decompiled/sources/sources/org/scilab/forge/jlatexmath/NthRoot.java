package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class NthRoot extends Atom {
    private static final float FACTOR = 0.55f;
    private static final String sqrtSymbol = "sqrt";
    private final Atom base;
    private final Atom root;

    public NthRoot(Atom r1, Atom r2) {
        if (r1 != null) goto L5;
        r1 = new EmptyAtom();
    L5:
        this.base = r1;
        if (r2 != null) goto L8;
        r2 = new EmptyAtom();
    L8:
        this.root = r2;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r9) {
        TeXFont r0 = r9.getTeXFont();
        int r1 = r9.getStyle();
        float r2 = r0.getDefaultRuleThickness(r1);
        if (r1 >= 2) goto L5;
        float r02 = r0.getXHeight(r1, r0.getChar(sqrtSymbol, r1).getFontCode());
    L6:
        float r03 = (Math.abs(r02) / 4.0f) + r2;
        HorizontalBox r3 = new HorizontalBox(this.base.createBox(r9.crampStyle()));
        r3.add(new SpaceAtom(5, 1.0f, 0.0f, 0.0f).createBox(r9.crampStyle()));
        float r12 = (r3.getHeight() + r3.getDepth()) + r03;
        Box r4 = DelimiterFactory.create(sqrtSymbol, r9, r12 + r2);
        float r04 = r03 + ((r4.getDepth() - r12) / 2.0f);
        r4.setShift(-(r3.getHeight() + r04));
        OverBar r13 = new OverBar(r3, r04, r4.getHeight());
        r13.setShift(-((r3.getHeight() + r04) + r2));
        HorizontalBox r05 = new HorizontalBox(r4);
        r05.add(r13);
        Atom r8 = this.root;
        if (r8 != null) goto L9;
        return r05;
    L9:
        Box r82 = r8.createBox(r9.rootStyle());
        float r14 = (r05.getHeight() + r05.getDepth()) * FACTOR;
        r82.setShift((r05.getDepth() - r82.getDepth()) - r14);
        Box r92 = new SpaceAtom(5, -10.0f, 0.0f, 0.0f).createBox(r9);
        HorizontalBox r15 = new HorizontalBox();
        float r22 = r82.getWidth() + r92.getWidth();
        if (r22 >= 0.0f) goto L12;
        r15.add(new StrutBox(-r22, 0.0f, 0.0f, 0.0f));
    L12:
        r15.add(r82);
        r15.add(r92);
        r15.add(r05);
        return r15;
    L5:
        r02 = r2;
        goto L6
    }
}
