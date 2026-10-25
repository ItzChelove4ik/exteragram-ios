package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class FractionAtom extends Atom {
    private float defFactor;
    private boolean defFactorSet;
    private int denomAlign;
    private Atom denominator;
    private boolean noDefault;
    private int numAlign;
    private Atom numerator;
    private float thickness;
    private int unit;

    public FractionAtom(Atom r2, Atom r3) {
        this(r2, r3, true);
    }

    public FractionAtom(Atom r7, Atom r8, float r9, int r10, int r11) {
        this(r7, r8, true, r10, r11);
        this.defFactor = r9;
        this.defFactorSet = true;
    }

    public FractionAtom(Atom r7, Atom r8, int r9, float r10) {
        this(r7, r8, true, r9, r10);
    }

    public FractionAtom(Atom r1, Atom r2, int r3, float r4, int r5, int r6) {
        this(r1, r2, r3, r4);
        this.numAlign = checkAlignment(r5);
        this.denomAlign = checkAlignment(r6);
    }

    public FractionAtom(Atom r7, Atom r8, boolean r9) {
        this(r7, r8, !r9, 2, 0.0f);
    }

    public FractionAtom(Atom r3, Atom r4, boolean r5, int r6, float r7) {
        this.noDefault = false;
        this.numAlign = 2;
        this.denomAlign = 2;
        this.defFactorSet = false;
        SpaceAtom.checkUnit(r6);
        this.numerator = r3;
        this.denominator = r4;
        this.noDefault = r5;
        this.thickness = r7;
        this.unit = r6;
        this.type = 7;
    }

    public FractionAtom(Atom r1, Atom r2, boolean r3, int r4, int r5) {
        this(r1, r2, r3);
        this.numAlign = checkAlignment(r4);
        this.denomAlign = checkAlignment(r5);
    }

    private int checkAlignment(int r1) {
        if (r1 != 0) goto L4;
    L8:
        return r1;
    L4:
        if (r1 == 1) goto L8;
        return 2;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r15) {
        TeXFont r0 = r15.getTeXFont();
        int r1 = r15.getStyle();
        float r2 = r0.getDefaultRuleThickness(r1);
        if (this.noDefault == false) goto L6;
        this.thickness *= SpaceAtom.getFactor(this.unit, r15);
    L10:
        Atom r3 = this.numerator;
        if (r3 != null) goto L13;
        Box r32 = new StrutBox(0.0f, 0.0f, 0.0f, 0.0f);
    L14:
        Atom r5 = this.denominator;
        if (r5 != null) goto L17;
        Box r52 = new StrutBox(0.0f, 0.0f, 0.0f, 0.0f);
    L19:
        if (r32.getWidth() >= r52.getWidth()) goto L21;
        r32 = new HorizontalBox(r32, r52.getWidth(), this.numAlign);
    L23:
        if (r1 >= 2) goto L25;
        float r7 = r0.getNum1(r1);
        float r8 = r0.getDenom1(r1);
    L29:
        VerticalBox r9 = new VerticalBox();
        r9.add(r32);
        float r02 = r0.getAxisHeight(r1);
        float r10 = this.thickness;
        if (r10 <= 0.0f) goto L41;
        if (r1 >= 2) goto L33;
        float r12 = 3.0f * r10;
    L34:
        float r102 = r10 / 2.0f;
        float r13 = (r7 - r32.getDepth()) - (r02 + r102);
        float r03 = (r02 - r102) - (r52.getHeight() - r8);
        float r22 = r12 - r13;
        float r122 = r12 - r03;
        if (r22 <= 0.0f) goto L38;
        r7 = r7 + r22;
        r13 = r13 + r22;
    L38:
        if (r122 <= 0.0f) goto L40;
        r8 = r8 + r122;
        r03 = r03 + r122;
    L40:
        r9.add(new StrutBox(0.0f, r13, 0.0f, 0.0f));
        r9.add(new HorizontalRule(this.thickness, r32.getWidth(), 0.0f));
        r9.add(new StrutBox(0.0f, r03, 0.0f, 0.0f));
    L48:
        r9.add(r52);
        r9.setHeight(r7 + r32.getHeight());
        r9.setDepth(r8 + r52.getDepth());
        return new HorizontalBox(r9, r9.getWidth() + (new SpaceAtom(0, 0.12f, 0.0f, 0.0f).createBox(r15).getWidth() * 2.0f), 2);
    L33:
        r12 = r10;
        goto L34
    L41:
        if (r1 >= 2) goto L43;
        float r23 = r2 * 7.0f;
    L44:
        float r14 = (r7 - r32.getDepth()) - (r52.getHeight() - r8);
        float r24 = (r23 - r14) / 2.0f;
        if (r24 <= 0.0f) goto L47;
        r7 = r7 + r24;
        r8 = r8 + r24;
        r14 = r14 + (r24 * 2.0f);
    L47:
        r9.add(new StrutBox(0.0f, r14, 0.0f, 0.0f));
        goto L48
    L43:
        r23 = r2 * 3.0f;
        goto L44
    L25:
        r8 = r0.getDenom2(r1);
        if (this.thickness <= 0.0f) goto L28;
        r7 = r0.getNum2(r1);
        goto L29
    L28:
        r7 = r0.getNum3(r1);
        goto L29
    L21:
        r52 = new HorizontalBox(r52, r32.getWidth(), this.denomAlign);
        goto L23
    L17:
        r52 = r5.createBox(r15.denomStyle());
        goto L19
    L13:
        r32 = r3.createBox(r15.numStyle());
        goto L14
    L6:
        if (this.defFactorSet == false) goto L8;
        float r33 = this.defFactor * r2;
    L9:
        this.thickness = r33;
        goto L10
    L8:
        r33 = r2;
        goto L9
    }
}
