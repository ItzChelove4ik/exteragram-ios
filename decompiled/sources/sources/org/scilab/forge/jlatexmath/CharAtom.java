package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class CharAtom extends CharSymbol {

    /* renamed from: c, reason: collision with root package name */
    private final char f502c;
    private boolean mathMode;
    private String textStyle;

    public CharAtom(char r2, String r3) {
        this(r2, r3, false);
    }

    public CharAtom(char r1, String r2, boolean r3) {
        this.f502c = r1;
        this.textStyle = r2;
        this.mathMode = r3;
    }

    private Char getChar(TeXFont r2, int r3, boolean r4) {
        char r0 = this.f502c;
        if (r4 == true) goto L5;
    L7:
        String r1 = this.textStyle;
        if (r1 != null) goto L12;
        return r2.getDefaultChar(r0, r3);
    L12:
        return r2.getChar(r0, r1, r3);
    L5:
        if (Character.isLowerCase(r0) == false) goto L7;
        r0 = Character.toUpperCase(this.f502c);
        goto L7
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r8) {
        if (this.textStyle != null) goto L7;
        String r0 = r8.getTextStyle();
        if (r0 == null) goto L7;
        this.textStyle = r0;
    L7:
        boolean r02 = r8.getSmallCap();
        CharBox r2 = new CharBox(getChar(r8.getTeXFont(), r8.getStyle(), r02));
        if (r02 == true) goto L10;
    L13:
        return r2;
    L10:
        if (Character.isLowerCase(this.f502c) == false) goto L13;
        return new ScaleBox(r2, 0.800000011920929d, 0.800000011920929d);
    }

    @Override // org.scilab.forge.jlatexmath.CharSymbol
    public CharFont getCharFont(TeXFont r2) {
        return getChar(r2, 0, false).getCharFont();
    }

    public char getCharacter() {
        return this.f502c;
    }

    public boolean isMathMode() {
        return this.mathMode;
    }

    public String toString() {
        return "CharAtom: '" + this.f502c + "'";
    }
}
