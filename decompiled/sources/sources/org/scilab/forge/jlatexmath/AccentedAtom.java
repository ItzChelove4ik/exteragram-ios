package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class AccentedAtom extends Atom {
    private boolean acc;
    private final SymbolAtom accent;
    protected Atom base;
    private boolean changeSize;
    protected Atom underbase;

    public AccentedAtom(Atom r3, String r4) {
        this.acc = false;
        this.changeSize = true;
        this.base = null;
        this.underbase = null;
        SymbolAtom r0 = SymbolAtom.get(r4);
        this.accent = r0;
        if (r0.type != 10) goto L11;
        this.base = r3;
        if ((r3 instanceof AccentedAtom) == false) goto L8;
        this.underbase = ((AccentedAtom) r3).underbase;
        return;
    L8:
        this.underbase = r3;
        return;
    L11:
        throw new InvalidSymbolTypeException("The symbol with the name '" + r4 + "' is not defined as an accent (type='acc') in 'TeXSymbols.xml'!");
    }

    public AccentedAtom(Atom r3, Atom r4) {
        this.acc = false;
        this.changeSize = true;
        this.underbase = null;
        this.base = r3;
        if ((r3 instanceof AccentedAtom) == false) goto L5;
        this.underbase = ((AccentedAtom) r3).underbase;
    L7:
        if ((r4 instanceof SymbolAtom) == false) goto L11;
        this.accent = (SymbolAtom) r4;
        this.acc = true;
        return;
    L11:
        throw new InvalidSymbolTypeException("Invalid accent");
    L5:
        this.underbase = r3;
        goto L7
    }

    public AccentedAtom(Atom r1, Atom r2, boolean r3) {
        this(r1, r2);
        this.changeSize = r3;
    }

    public AccentedAtom(Atom r3, TeXFormula r4) {
        this.acc = false;
        this.changeSize = true;
        this.base = null;
        this.underbase = null;
        if (r4 == null) goto L15;
        Atom r42 = r4.root;
        if ((r42 instanceof SymbolAtom) == false) goto L13;
        SymbolAtom r43 = (SymbolAtom) r42;
        this.accent = r43;
        if (r43.type != 10) goto L11;
        this.base = r3;
        return;
    L11:
        throw new InvalidSymbolTypeException("The accent TeXFormula represents a single symbol with the name '" + r43.getName() + "', but this symbol is not defined as an accent (type='acc') in 'TeXSymbols.xml'!");
    L13:
        throw new InvalidTeXFormulaException("The accent TeXFormula does not represent a single symbol!");
    L15:
        throw new InvalidTeXFormulaException("The accent TeXFormula can't be null!");
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r11) {
        TeXFont r0 = r11.getTeXFont();
        int r1 = r11.getStyle();
        Atom r2 = this.base;
        if (r2 != null) goto L5;
        Box r22 = new StrutBox(0.0f, 0.0f, 0.0f, 0.0f);
    L6:
        float r4 = r22.getWidth();
        Atom r5 = this.underbase;
        if ((r5 instanceof CharSymbol) == false) goto L9;
        float r52 = r0.getSkew(((CharSymbol) r5).getCharFont(r0), r1);
    L10:
        Char r6 = r0.getChar(this.accent.getName(), r1);
    L12:
        if (r0.hasNextLarger(r6) == false) goto L16;
        Char r7 = r0.getNextLarger(r6, r1);
        if (r7.getWidth() > r4) goto L16;
        r6 = r7;
    L16:
        float r72 = -SpaceAtom.getFactor(5, r11);
        if (this.acc == true) goto L20;
        r72 = Math.min(r22.getHeight(), r0.getXHeight(r1, r6.getFontCode()));
    L20:
        VerticalBox r02 = new VerticalBox();
        float r12 = r6.getItalic();
        Box r8 = new CharBox(r6);
        if (this.acc == false) goto L27;
        SymbolAtom r62 = this.accent;
        if (this.changeSize == false) goto L25;
        r11 = r11.subStyle();
    L25:
        r8 = r62.createBox(r11);
    L27:
        if (Math.abs(r12) <= 1.0E-7f) goto L29;
        HorizontalBox r112 = new HorizontalBox(new StrutBox(-r12, 0.0f, 0.0f, 0.0f));
        r112.add(r8);
        r8 = r112;
    L29:
        float r113 = (r4 - r8.getWidth()) / 2.0f;
        if (r113 <= 0.0f) goto L32;
        float r13 = r113;
    L33:
        r8.setShift(r52 + r13);
        if (r113 >= 0.0f) goto L36;
        r22 = new HorizontalBox(r22, r8.getWidth(), 2);
    L36:
        r02.add(r8);
        if (this.changeSize == false) goto L39;
        float r10 = -r72;
    L40:
        r02.add(new StrutBox(0.0f, r10, 0.0f, 0.0f));
        r02.add(r22);
        float r102 = r02.getHeight() + r02.getDepth();
        float r23 = r22.getDepth();
        r02.setDepth(r23);
        r02.setHeight(r102 - r23);
        if (r113 >= 0.0f) goto L44;
        HorizontalBox r103 = new HorizontalBox(new StrutBox(r113, 0.0f, 0.0f, 0.0f));
        r103.add(r02);
        r103.setWidth(r4);
        return r103;
    L44:
        return r02;
    L39:
        r10 = -r22.getHeight();
        goto L40
    L32:
        r13 = 0.0f;
        goto L33
    L9:
        r52 = 0.0f;
        goto L10
    L5:
        r22 = r2.createBox(r11.crampStyle());
        goto L6
    }
}
