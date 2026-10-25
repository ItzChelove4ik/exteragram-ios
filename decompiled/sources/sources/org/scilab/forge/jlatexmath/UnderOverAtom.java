package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class UnderOverAtom extends Atom {
    private final Atom base;
    private final Atom over;
    private final boolean overScriptSize;
    private final float overSpace;
    private final int overUnit;
    private final Atom under;
    private final boolean underScriptSize;
    private final float underSpace;
    private final int underUnit;

    public UnderOverAtom(Atom r1, Atom r2, int r3, float r4, boolean r5, Atom r6, int r7, float r8, boolean r9) {
        SpaceAtom.checkUnit(r3);
        SpaceAtom.checkUnit(r7);
        this.base = r1;
        this.under = r2;
        this.underUnit = r3;
        this.underSpace = r4;
        this.underScriptSize = r5;
        this.over = r6;
        this.overUnit = r7;
        this.overSpace = r8;
        this.overScriptSize = r9;
    }

    public UnderOverAtom(Atom r3, Atom r4, int r5, float r6, boolean r7, boolean r8) {
        SpaceAtom.checkUnit(r5);
        this.base = r3;
        if (r8 == false) goto L6;
        this.under = null;
        this.underSpace = 0.0f;
        this.underUnit = 0;
        this.underScriptSize = false;
        this.over = r4;
        this.overUnit = r5;
        this.overSpace = r6;
        this.overScriptSize = r7;
        return;
    L6:
        this.under = r4;
        this.underUnit = r5;
        this.underSpace = r6;
        this.underScriptSize = r7;
        this.overSpace = 0.0f;
        this.over = null;
        this.overUnit = 0;
        this.overScriptSize = false;
    }

    private static Box changeWidth(Box r2, float r3) {
        if (r2 != null) goto L4;
    L7:
        return r2;
    L4:
        if (Math.abs(r3 - r2.getWidth()) <= 1.0E-7f) goto L7;
        return new HorizontalBox(r2, r3, 2);
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r9) {
        Atom r0 = this.base;
        if (r0 != null) goto L5;
        Box r02 = new StrutBox(0.0f, 0.0f, 0.0f, 0.0f);
    L6:
        float r2 = r02.getWidth();
        Atom r3 = this.over;
        Box r4 = null;
        if (r3 != null) goto L9;
        Box r32 = null;
    L14:
        Atom r5 = this.under;
        if (r5 != null) goto L17;
    L21:
        VerticalBox r52 = new VerticalBox();
        r9.setLastFontId(r02.getLastFontId());
        if (this.over == null) goto L24;
        r52.add(changeWidth(r32, r2));
        r52.add(new SpaceAtom(this.overUnit, 0.0f, this.overSpace, 0.0f).createBox(r9));
    L24:
        Box r03 = changeWidth(r02, r2);
        r52.add(r03);
        float r33 = (r52.getHeight() + r52.getDepth()) - r03.getDepth();
        if (this.under == null) goto L27;
        r52.add(new SpaceAtom(this.overUnit, 0.0f, this.underSpace, 0.0f).createBox(r9));
        r52.add(changeWidth(r4, r2));
    L27:
        r52.setDepth((r52.getHeight() + r52.getDepth()) - r33);
        r52.setHeight(r33);
        return r52;
    L17:
        if (this.underScriptSize == false) goto L19;
        TeXEnvironment r42 = r9.subStyle();
    L20:
        r4 = r5.createBox(r42);
        r2 = Math.max(r2, r4.getWidth());
        goto L21
    L19:
        r42 = r9;
        goto L20
    L9:
        if (this.overScriptSize == false) goto L11;
        TeXEnvironment r53 = r9.subStyle();
    L12:
        r32 = r3.createBox(r53);
        r2 = Math.max(r2, r32.getWidth());
        goto L14
    L11:
        r53 = r9;
        goto L12
    L5:
        r02 = r0.createBox(r9);
        goto L6
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public int getLeftType() {
        return this.base.getLeftType();
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public int getRightType() {
        return this.base.getRightType();
    }
}
