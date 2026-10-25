package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class ScriptsAtom extends Atom {
    private static final int MAX_WRAP_DEPTH = 64;
    private static final SpaceAtom SCRIPT_SPACE = null;
    private static int boxWrapDepth;
    private int align;
    private final Atom base;
    private final Atom subscript;
    private final Atom superscript;
    private final int wrapDepth;

    static {
        SCRIPT_SPACE = new SpaceAtom(3, 0.5f, 0.0f, 0.0f);
    }

    public ScriptsAtom(Atom r2, Atom r3, Atom r4) {
        this.align = 0;
        this.base = r2;
        this.subscript = r3;
        this.superscript = r4;
        int r42 = 1;
        if ((r2 instanceof ScriptsAtom) == false) goto L5;
        r42 = 1 + ((ScriptsAtom) r2).wrapDepth;
    L5:
        this.wrapDepth = r42;
        if (r42 > 64) goto L9;
        return;
    L9:
        throw new DepthLimitExceededException();
    }

    public ScriptsAtom(Atom r1, Atom r2, Atom r3, boolean r4) {
        this(r1, r2, r3);
        if (r4 == true) goto L6;
        this.align = 1;
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v5, types: [org.scilab.forge.jlatexmath.Box] */
    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r23) {
        int r2 = boxWrapDepth;
        int r3 = r2 + 1;
        boxWrapDepth = r3;
        if (r3 <= 64) goto L101;
        boxWrapDepth = r2;
        throw new DepthLimitExceededException();
    L101:
        Atom r32 = this.base;     // Catch: Throwable -> L8
        if (r32 != null) goto L10;
        Box r33 = new StrutBox(0.0f, 0.0f, 0.0f, 0.0f);     // Catch: Throwable -> L8
    L11:
        StrutBox r5 = new StrutBox(0.0f, 0.0f, 0.0f, 0.0f);     // Catch: Throwable -> L8
        if (this.subscript == null) goto L14;
    L17:
        TeXFont r6 = r23.getTeXFont();     // Catch: Throwable -> L8
        int r7 = r23.getStyle();     // Catch: Throwable -> L8
        Atom r9 = this.base;     // Catch: Throwable -> L8
        int r8 = r9.type_limits;     // Catch: Throwable -> L8
        if (r8 == 2) goto L95;
        if (r8 != 0) goto L22;
        if (r7 == 0) goto L95;
    L22:
        HorizontalBox r82 = new HorizontalBox(r33);     // Catch: Throwable -> L8
        int r92 = r33.getLastFontId();     // Catch: Throwable -> L8
        if (r92 != (-1)) goto L25;
        r92 = r6.getMuFontId();     // Catch: Throwable -> L8
    L25:
        TeXEnvironment r11 = r23.subStyle();     // Catch: Throwable -> L8
        TeXEnvironment r12 = r23.supStyle();     // Catch: Throwable -> L8
        Atom r13 = this.base;     // Catch: Throwable -> L8
        if ((r13 instanceof AccentedAtom) == false) goto L30;
        Box r34 = ((AccentedAtom) r13).base.createBox(r23.crampStyle());     // Catch: Throwable -> L8
        float r10 = r34.getHeight() - r6.getSupDrop(r12.getStyle());     // Catch: Throwable -> L8
        float r35 = r34.getDepth();     // Catch: Throwable -> L8
        float r132 = r6.getSubDrop(r11.getStyle());     // Catch: Throwable -> L8
    L28:
        float r36 = r35 + r132;     // Catch: Throwable -> L8
        float r133 = r10;
        HorizontalBox r102 = r82;
        StrutBox r83 = r5;
        float r52 = 0.0f;
    L60:
        Atom r14 = this.superscript;     // Catch: Throwable -> L8
        if (r14 != null) goto L66;
        Box r0 = this.subscript.createBox(r11);     // Catch: Throwable -> L8
        r0.setShift(Math.max(Math.max(r36, r6.getSub1(r7)), r0.getHeight() - ((Math.abs(r6.getXHeight(r7, r92)) * 4.0f) / 5.0f)));     // Catch: Throwable -> L8
        r102.add(r0);     // Catch: Throwable -> L8
        r102.add(r83);     // Catch: Throwable -> L8
        int r02 = boxWrapDepth - 1;
    L64:
        boxWrapDepth = r02;
        return r102;
    L66:
        Box r122 = r14.createBox(r12);     // Catch: Throwable -> L8
        float r142 = r122.getWidth();     // Catch: Throwable -> L8
        Atom r15 = this.subscript;     // Catch: Throwable -> L8
        if (r15 == null) goto L71;
        float r18 = 0.0f;
        if (this.align != 1) goto L72;
        r142 = Math.max(r142, r15.createBox(r11).getWidth());     // Catch: Throwable -> L8
    L72:
        HorizontalBox r4 = new HorizontalBox(r122, r142, this.align);     // Catch: Throwable -> L8
        SpaceAtom r152 = SCRIPT_SPACE;     // Catch: Throwable -> L8
        r4.add(r152.createBox(r23));     // Catch: Throwable -> L77
        if (r7 != 0) goto L80;
        float r22 = r6.getSup1(r7);     // Catch: Throwable -> L77
    L83:
        float r24 = Math.max(Math.max(r133, r22), r122.getDepth() + (Math.abs(r6.getXHeight(r7, r92)) / 4.0f));     // Catch: Throwable -> L77
        Atom r134 = this.subscript;     // Catch: Throwable -> L77
        if (r134 != null) goto L86;
        r4.setShift(-r24);     // Catch: Throwable -> L77
        r102.add(r4);     // Catch: Throwable -> L77
    L92:
        r102.add(r83);     // Catch: Throwable -> L77
        r02 = boxWrapDepth - 1;
        goto L64
    L86:
        Box r112 = r134.createBox(r11);     // Catch: Throwable -> L77
        HorizontalBox r135 = new HorizontalBox(r112, r142, this.align);     // Catch: Throwable -> L77
        r135.add(r152.createBox(r23));     // Catch: Throwable -> L77
        float r03 = Math.max(r36, r6.getSub2(r7));     // Catch: Throwable -> L77
        float r1 = r6.getDefaultRuleThickness(r7);     // Catch: Throwable -> L77
        float r37 = ((r24 - r122.getDepth()) + r03) - r112.getHeight();     // Catch: Throwable -> L77
        float r16 = r1 * 4.0f;     // Catch: Throwable -> L77
        if (r37 >= r16) goto L91;
        r24 = r24 + (r16 - r37);     // Catch: Throwable -> L77
        float r17 = ((Math.abs(r6.getXHeight(r7, r92)) * 4.0f) / 5.0f) - (r24 - r122.getDepth());     // Catch: Throwable -> L77
        if (r17 <= r18) goto L91;
        r24 = r24 + r17;     // Catch: Throwable -> L77
        r03 = r03 - r17;     // Catch: Throwable -> L77
    L91:
        VerticalBox r19 = new VerticalBox();     // Catch: Throwable -> L77
        r4.setShift(r52);     // Catch: Throwable -> L77
        r19.add(r4);     // Catch: Throwable -> L77
        float r53 = r18;
        r19.add(new StrutBox(r53, ((r24 - r122.getDepth()) + r03) - r112.getHeight(), r53, r53));     // Catch: Throwable -> L77
        r19.add(r135);     // Catch: Throwable -> L77
        r19.setHeight(r24 + r122.getHeight());     // Catch: Throwable -> L77
        r19.setDepth(r03 + r112.getDepth());     // Catch: Throwable -> L77
        r102.add(r19);     // Catch: Throwable -> L77
        goto L92
    L80:
        if (r23.crampStyle().getStyle() != r7) goto L82;
        r22 = r6.getSup3(r7);     // Catch: Throwable -> L77
        goto L83
    L82:
        r22 = r6.getSup2(r7);     // Catch: Throwable -> L77
        goto L83
    L71:
        r18 = 0.0f;
        goto L72
    L30:
        if ((r13 instanceof SymbolAtom) == false) goto L45;
        if (r13.type != 1) goto L45;
        Char r38 = r6.getChar(((SymbolAtom) r13).getName(), r7);     // Catch: Throwable -> L8
        if (r7 < 2) goto L36;
    L38:
        CharBox r54 = new CharBox(r38);     // Catch: Throwable -> L8
        r54.setShift(((-(r54.getHeight() + r54.getDepth())) / 2.0f) - r23.getTeXFont().getAxisHeight(r23.getStyle()));     // Catch: Throwable -> L8
        HorizontalBox r84 = new HorizontalBox(r54);     // Catch: Throwable -> L8
        float r39 = r38.getItalic();     // Catch: Throwable -> L8
        ?? r55 = new SpaceAtom(2).createBox(r23);     // Catch: Throwable -> L8
        if (r39 > 1.0E-7f) goto L41;
    L43:
        float r103 = r84.getHeight() - r6.getSupDrop(r12.getStyle());     // Catch: Throwable -> L8
        r52 = r39;
        r36 = r84.getDepth() + r6.getSubDrop(r11.getStyle());     // Catch: Throwable -> L8
        r133 = r103;
        r102 = r84;
        r83 = r55;
        goto L60
    L41:
        if (this.subscript != null) goto L43;
        r84.add(new StrutBox(r39, 0.0f, 0.0f, 0.0f));     // Catch: Throwable -> L8
        goto L43
    L36:
        if (r6.hasNextLarger(r38) == false) goto L38;
        r38 = r6.getNextLarger(r38, r7);     // Catch: Throwable -> L8
    L45:
        if ((r13 instanceof CharSymbol) == false) goto L59;
        CharFont r310 = ((CharSymbol) r13).getCharFont(r6);     // Catch: Throwable -> L8
        if (((CharSymbol) this.base).isMarkedAsTextSymbol() == true) goto L49;
    L52:
        float r311 = r6.getChar(r310, r7).getItalic();     // Catch: Throwable -> L8
    L54:
        if (r311 > 1.0E-7f) goto L56;
    L58:
        r133 = 0.0f;
        r102 = r82;
        r83 = r5;
        r52 = r311;
        r36 = 0.0f;
        goto L60
    L56:
        if (this.subscript != null) goto L58;
        r82.add(new StrutBox(r311, 0.0f, 0.0f, 0.0f));     // Catch: Throwable -> L8
        r311 = 0.0f;
        goto L58
    L49:
        if (r6.hasSpace(r310.fontId) == false) goto L52;
        r311 = 0.0f;
        goto L54
    L59:
        r10 = r33.getHeight() - r6.getSupDrop(r12.getStyle());     // Catch: Throwable -> L8
        r35 = r33.getDepth();     // Catch: Throwable -> L8
        r132 = r6.getSubDrop(r11.getStyle());     // Catch: Throwable -> L8
    L95:
        boxWrapDepth--;
        return new UnderOverAtom(new UnderOverAtom(r9, this.subscript, 3, 0.3f, true, false), this.superscript, 3, 3.0f, true, true).createBox(r23);
    L77:
        th = th;
    L97:
        boxWrapDepth--;
        throw th;
    L14:
        if (this.superscript != null) goto L17;
        boxWrapDepth--;
        return r33;
    L10:
        r33 = r32.createBox(r23);     // Catch: Throwable -> L8
    L8:
        th = th;
        goto L97
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
