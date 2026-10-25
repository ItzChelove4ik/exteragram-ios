package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class UnderOverArrowAtom extends Atom {
    private Atom base;
    private boolean dble;
    private boolean left;
    private boolean over;

    public UnderOverArrowAtom(Atom r2, boolean r3) {
        this.left = false;
        this.base = r2;
        this.over = r3;
        this.dble = true;
    }

    public UnderOverArrowAtom(Atom r2, boolean r3, boolean r4) {
        this.dble = false;
        this.base = r2;
        this.left = r3;
        this.over = r4;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r7) {
        Atom r0 = this.base;
        if (r0 == null) goto L5;
        Box r02 = r0.createBox(r7);
    L6:
        float r2 = new SpaceAtom(3, 1.0f, 0.0f, 0.0f).createBox(r7).getWidth();
        if (this.dble == false) goto L9;
        Box r72 = XLeftRightArrowFactory.create(r7, r02.getWidth());
        float r22 = r2 * 4.0f;
    L10:
        VerticalBox r3 = new VerticalBox();
        if (this.over == false) goto L14;
        r3.add(r72);
        r3.add(new HorizontalBox(r02, r72.getWidth(), 2));
        float r6 = r3.getDepth() + r3.getHeight();
        r3.setDepth(r02.getDepth());
        r3.setHeight(r6 - r02.getDepth());
        return r3;
    L14:
        r3.add(new HorizontalBox(r02, r72.getWidth(), 2));
        r3.add(new StrutBox(0.0f, r22, 0.0f, 0.0f));
        r3.add(r72);
        r3.setDepth((r3.getDepth() + r3.getHeight()) - r02.getHeight());
        r3.setHeight(r02.getHeight());
        return r3;
    L9:
        r72 = XLeftRightArrowFactory.create(this.left, r7, r02.getWidth());
        r22 = -r2;
        goto L10
    L5:
        r02 = new StrutBox(0.0f, 0.0f, 0.0f, 0.0f);
        goto L6
    }
}
