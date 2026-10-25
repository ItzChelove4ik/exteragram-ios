package org.scilab.forge.jlatexmath;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.BitSet;
import java.util.Map;

/* loaded from: classes5.dex */
public class SymbolAtom extends CharSymbol {
    public static Map<String, SymbolAtom> symbols;
    private static BitSet validSymbolTypes;
    private final boolean delimiter;
    private final String name;
    private char unicode;

    static {
        symbols = new TeXSymbolParser().readSymbols();
        BitSet r0 = new BitSet(16);
        validSymbolTypes = r0;
        r0.set(0);
        validSymbolTypes.set(1);
        validSymbolTypes.set(2);
        validSymbolTypes.set(3);
        validSymbolTypes.set(4);
        validSymbolTypes.set(5);
        validSymbolTypes.set(6);
        validSymbolTypes.set(10);
    }

    public SymbolAtom(String r1, int r2, boolean r3) {
        this.name = r1;
        this.type = r2;
        if (r2 != 1) goto L5;
        this.type_limits = 0;
    L5:
        this.delimiter = r3;
    }

    public SymbolAtom(SymbolAtom r2, int r3) {
        if (validSymbolTypes.get(r3) == false) goto L10;
        this.name = r2.name;
        this.type = r3;
        if (r3 != 1) goto L7;
        this.type_limits = 0;
    L7:
        this.delimiter = r2.delimiter;
        return;
    L10:
        throw new InvalidSymbolTypeException("The symbol type was not valid! Use one of the symbol type constants from the class 'TeXConstants'.");
    }

    public static void addSymbolAtom(InputStream r1, String r2) {
        TeXSymbolParser r0 = new TeXSymbolParser(r1, r2);
        symbols.putAll(r0.readSymbols());
    }

    public static void addSymbolAtom(String r2) {
        addSymbolAtom(new FileInputStream(r2), r2);
        return;
    L5:
        e = move-exception;
        throw new ResourceParseException(r2, e);
    }

    public static void addSymbolAtom(SymbolAtom r2) {
        symbols.put(r2.name, r2);
    }

    public static SymbolAtom get(String r1) {
        SymbolAtom r0 = symbols.get(r1);
        if (r0 == null) goto L7;
        return r0;
    L7:
        throw new SymbolNotFoundException(r1);
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r12) {
        TeXFont r0 = r12.getTeXFont();
        int r1 = r12.getStyle();
        Char r2 = r0.getChar(this.name, r1);
        Box r3 = new CharBox(r2);
        if (r12.getSmallCap() == false) goto L11;
        char r4 = this.unicode;
        if (r4 == 0) goto L11;
        if (Character.isLowerCase(r4) == false) goto L11;
        r3 = new ScaleBox(new CharBox(r0.getChar(TeXFormula.symbolTextMappings[Character.toUpperCase(this.unicode)], r1)), 0.8d, 0.8d);     // Catch: SymbolMappingNotFoundException -> L22
    L11:
        if (this.type == 1) goto L13;
        return r3;
    L13:
        if (r1 < 2) goto L15;
    L17:
        CharBox r11 = new CharBox(r2);
        r11.setShift(((-(r11.getHeight() + r11.getDepth())) / 2.0f) - r12.getTeXFont().getAxisHeight(r12.getStyle()));
        float r122 = r2.getItalic();
        HorizontalBox r02 = new HorizontalBox(r11);
        if (r122 <= 1.0E-7f) goto L20;
        r02.add(new StrutBox(r122, 0.0f, 0.0f, 0.0f));
    L20:
        return r02;
    L15:
        if (r0.hasNextLarger(r2) == false) goto L17;
        r2 = r0.getNextLarger(r2, r1);
        goto L17
    }

    @Override // org.scilab.forge.jlatexmath.CharSymbol
    public CharFont getCharFont(TeXFont r2) {
        return r2.getChar(this.name, 0).getCharFont();
    }

    public String getName() {
        return this.name;
    }

    public char getUnicode() {
        return this.unicode;
    }

    public boolean isDelimiter() {
        return this.delimiter;
    }

    public SymbolAtom setUnicode(char r1) {
        this.unicode = r1;
        return this;
    }
}
