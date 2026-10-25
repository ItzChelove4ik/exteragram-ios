package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class BigOperatorAtom extends Atom {
    protected Atom base;
    private boolean limits;
    private boolean limitsSet;
    private Atom over;
    private Atom under;

    public BigOperatorAtom(Atom r2, Atom r3, Atom r4) {
        this.limitsSet = false;
        this.limits = false;
        this.base = r2;
        this.under = r3;
        this.over = r4;
        this.type = 1;
    }

    public BigOperatorAtom(Atom r1, Atom r2, Atom r3, boolean r4) {
        this(r1, r2, r3);
        this.limits = r4;
        this.limitsSet = true;
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
    public Box createBox(TeXEnvironment r18) {
        TeXFont r2 = r18.getTeXFont();
        int r3 = r18.getStyle();
        Atom r4 = this.base;
        Box r7 = null;
        if ((r4 instanceof TypedAtom) == false) goto L12;
        Atom r5 = ((TypedAtom) r4).getBase();
        if ((r5 instanceof RowAtom) == false) goto L11;
        RowAtom r8 = (RowAtom) r5;
        if (r8.lookAtLastAtom == false) goto L11;
        if (this.base.type_limits == 2) goto L11;
        this.base = r8.getLastAtom();
    L13:
        boolean r52 = this.limitsSet;
        if (r52 == true) goto L16;
    L17:
        if (r52 == true) goto L19;
        if (r3 < 2) goto L19;
    L63:
        if (r8 == null) goto L67;
        r8.add(new ScriptsAtom(this.base, this.under, this.over));
        Box r1 = r8.createBox(r18);
        r8.getLastAtom();
        r8.add(this.base);
        this.base = r4;
        return r1;
    L67:
        return new ScriptsAtom(this.base, this.under, this.over).createBox(r18);
    L19:
        Atom r53 = this.base;
        int r9 = r53.type_limits;
        if (r9 == 1) goto L63;
        if (r9 != 0) goto L25;
        if (r3 >= 2) goto L63;
    L25:
        if ((r53 instanceof SymbolAtom) == true) goto L27;
    L29:
        Atom r54 = this.base;
        if (r54 != null) goto L32;
        Box r55 = new StrutBox(0.0f, 0.0f, 0.0f, 0.0f);
    L33:
        Box r6 = new HorizontalBox(r55);
        float r56 = 0.0f;
    L34:
        Atom r10 = this.over;
        if (r10 == null) goto L37;
        Box r102 = r10.createBox(r18.supStyle());
    L38:
        Atom r11 = this.under;
        if (r11 == null) goto L41;
        r7 = r11.createBox(r18.subStyle());
    L41:
        if (r102 != null) goto L43;
        float r112 = 0.0f;
    L44:
        float r113 = Math.max(r112, r6.getWidth());
        if (r7 != null) goto L47;
        float r12 = 0.0f;
    L48:
        float r114 = Math.max(r113, r12);
        Box r103 = changeWidth(r102, r114);
        Box r62 = changeWidth(r6, r114);
        Box r72 = changeWidth(r7, r114);
        VerticalBox r115 = new VerticalBox();
        float r122 = r2.getBigOpSpacing5(r3);
        if (this.over == null) goto L51;
        r115.add(new StrutBox(0.0f, r122, 0.0f, 0.0f));
        r103.setShift(r56 / 2.0f);
        r115.add(r103);
        float r13 = Math.max(r2.getBigOpSpacing1(r3), r2.getBigOpSpacing3(r3) - r103.getDepth());
        r115.add(new StrutBox(0.0f, r13, 0.0f, 0.0f));
        r115.getHeight();
        r115.getDepth();
    L52:
        r115.add(r62);
        if (this.under == null) goto L55;
        r115.add(new StrutBox(0.0f, Math.max(r2.getBigOpSpacing2(r3), r2.getBigOpSpacing4(r3) - r72.getHeight()), 0.0f, 0.0f));
        r72.setShift((-r56) / 2.0f);
        r115.add(r72);
        r115.add(new StrutBox(0.0f, r122, 0.0f, 0.0f));
    L55:
        float r22 = r62.getHeight();
        float r32 = r115.getHeight() + r115.getDepth();
        if (r103 == null) goto L58;
        r22 = r22 + (((r122 + r13) + r103.getHeight()) + r103.getDepth());
    L58:
        r115.setHeight(r22);
        r115.setDepth(r32 - r22);
        if (r8 == null) goto L62;
        HorizontalBox r23 = new HorizontalBox(r8.createBox(r18));
        r8.add(this.base);
        r23.add(r115);
        this.base = r4;
        return r23;
    L62:
        return r115;
    L51:
        r13 = 0.0f;
        goto L52
    L47:
        r12 = r7.getWidth();
        goto L48
    L43:
        r112 = r102.getWidth();
        goto L44
    L37:
        r102 = null;
        goto L38
    L32:
        r55 = r54.createBox(r18);
        goto L33
    L27:
        if (r53.type != 1) goto L29;
        Char r57 = r2.getChar(((SymbolAtom) r53).getName(), r3);
        r6 = this.base.createBox(r18);
        r56 = r57.getItalic();
        goto L34
    L16:
        if (this.limits == false) goto L63;
    L11:
        this.base = r5;
    L12:
        r8 = null;
        goto L13
    }
}
