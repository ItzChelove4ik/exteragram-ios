package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class XArrowAtom extends Atom {
    private boolean left;
    private Atom over;
    private Atom under;

    public XArrowAtom(Atom r1, Atom r2, boolean r3) {
        this.over = r1;
        this.under = r2;
        this.left = r3;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r9) {
        Atom r0 = this.over;
        if (r0 == null) goto L5;
        Box r02 = r0.createBox(r9.supStyle());
    L6:
        Atom r2 = this.under;
        if (r2 == null) goto L9;
        Box r22 = r2.createBox(r9.subStyle());
    L10:
        Box r3 = new SpaceAtom(0, 1.5f, 0.0f, 0.0f).createBox(r9.supStyle());
        Box r4 = new SpaceAtom(0, 1.5f, 0.0f, 0.0f).createBox(r9.subStyle());
        Box r1 = new SpaceAtom(5, 0.0f, 2.0f, 0.0f).createBox(r9);
        float r32 = Math.max(r02.getWidth() + (r3.getWidth() * 2.0f), r22.getWidth() + (r4.getWidth() * 2.0f));
        Box r8 = XLeftRightArrowFactory.create(this.left, r9, r32);
        HorizontalBox r92 = new HorizontalBox(r02, r32, 2);
        HorizontalBox r03 = new HorizontalBox(r22, r32, 2);
        VerticalBox r23 = new VerticalBox();
        r23.add(r92);
        r23.add(r1);
        r23.add(r8);
        r23.add(r1);
        r23.add(r03);
        float r82 = r23.getHeight() + r23.getDepth();
        float r93 = ((r1.getHeight() + r1.getDepth()) + r03.getHeight()) + r03.getDepth();
        r23.setDepth(r93);
        r23.setHeight(r82 - r93);
        return new HorizontalBox(r23, r23.getWidth() + (r1.getHeight() * 2.0f), 2);
    L9:
        r22 = new StrutBox(0.0f, 0.0f, 0.0f, 0.0f);
        goto L10
    L5:
        r02 = new StrutBox(0.0f, 0.0f, 0.0f, 0.0f);
        goto L6
    }
}
