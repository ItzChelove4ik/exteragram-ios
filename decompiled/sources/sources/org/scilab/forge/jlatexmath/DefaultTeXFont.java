package org.scilab.forge.jlatexmath;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.Character;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.noties.jlatexmath.JLatexMathAndroid;
import ru.noties.jlatexmath.awt.Font;

/* loaded from: classes5.dex */
public class DefaultTeXFont implements TeXFont {
    protected static final int BOT = 3;
    protected static final int CAPITALS = 1;
    protected static final int DEPTH = 2;
    protected static final int HEIGHT = 1;
    protected static final int IT = 3;
    protected static final int MID = 1;
    protected static final int NONE = -1;
    protected static final int NUMBERS = 0;
    protected static final int REP = 2;
    protected static final int SMALL = 2;
    protected static final int TOP = 0;
    protected static final int UNICODE = 3;
    protected static final int WIDTH = 0;
    private static String[] defaultTextStyleMappings = null;
    private static FontInfo[] fontInfo = null;
    private static Map<String, Number> generalSettings = null;
    public static List<Character.UnicodeBlock> loadedAlphabets = null;
    private static boolean magnificationEnable = true;
    private static Map<String, Float> parameters;
    public static Map<Character.UnicodeBlock, AlphabetRegistration> registeredAlphabets;
    private static Map<String, CharFont> symbolMappings;
    private static Map<String, CharFont[]> textStyleMappings;
    protected float factor;
    public boolean isBold;
    public boolean isIt;
    public boolean isRoman;
    public boolean isSs;
    public boolean isTt;
    private final float size;

    static {
        fontInfo = new FontInfo[0];
        loadedAlphabets = new ArrayList();
        registeredAlphabets = new HashMap();
        DefaultTeXFontParser r0 = new DefaultTeXFontParser();
        loadedAlphabets.add(Character.UnicodeBlock.of('a'));
        fontInfo = r0.parseFontDescriptions(fontInfo);
        parameters = r0.parseParameters();
        textStyleMappings = r0.parseTextStyleMappings();
        defaultTextStyleMappings = r0.parseDefaultTextStyleMappings();
        symbolMappings = r0.parseSymbolMappings();
        Map<String, Number> r02 = r0.parseGeneralSettings();
        generalSettings = r02;
        r02.put("textfactor", 1);
        int r03 = generalSettings.get(DefaultTeXFontParser.MUFONTID_ATTR).intValue();
        if (r03 < 0) goto L10;
        FontInfo[] r2 = fontInfo;
        if (r03 >= r2.length) goto L10;
        if (r2[r03] == null) goto L10;
        return;
    L10:
        throw new XMLResourceParseException(DefaultTeXFontParser.RESOURCE_NAME, DefaultTeXFontParser.GEN_SET_EL, DefaultTeXFontParser.MUFONTID_ATTR, "contains an unknown font id!");
    }

    public DefaultTeXFont(float r2) {
        this.factor = 1.0f;
        this.isBold = false;
        this.isRoman = false;
        this.isSs = false;
        this.isTt = false;
        this.isIt = false;
        this.size = r2;
    }

    public DefaultTeXFont(float r1, float r2, boolean r3, boolean r4, boolean r5, boolean r6, boolean r7) {
        this.size = r1;
        this.factor = r2;
        this.isBold = r3;
        this.isRoman = r4;
        this.isSs = r5;
        this.isTt = r6;
        this.isIt = r7;
    }

    public DefaultTeXFont(float r9, boolean r10, boolean r11, boolean r12, boolean r13, boolean r14) {
        this(r9, 1.0f, r10, r11, r12, r13, r14);
    }

    public static void addAlphabet(Character.UnicodeBlock r1, InputStream r2, String r3, InputStream r4, String r5, InputStream r6, String r7) {
        if (loadedAlphabets.contains(r1) == true) goto L6;
        addTeXFontDescription(r2, r3);
        SymbolAtom.addSymbolAtom(r4, r5);
        TeXFormula.addSymbolMappings(r6, r7);
        loadedAlphabets.add(r1);
        return;
    }

    public static void addAlphabet(Character.UnicodeBlock r10, String r11) {
        String r5 = "fonts/" + r11 + "/language_" + r11 + ".xml";
        String r7 = "fonts/" + r11 + "/symbols_" + r11 + ".xml";
        String r9 = "fonts/" + r11 + "/mappings_" + r11 + ".xml";
        addAlphabet(r10, JLatexMathAndroid.getResourceAsStream(r5), r5, JLatexMathAndroid.getResourceAsStream(r7), r7, JLatexMathAndroid.getResourceAsStream(r9), r9);     // Catch: FontAlreadyLoadedException -> L5
        return;
    }

    public static void addAlphabet(Object r6, Character.UnicodeBlock[] r7, String r8) {
        boolean r1 = false;
        int r2 = 0;
    L4:
        if (r1 == true) goto L14;
        if (r2 >= r7.length) goto L14;
        if (loadedAlphabets.contains(r7[r2]) == true) goto L12;
        if (r1 == true) goto L12;
        r1 = false;
    L13:
        r2 = r2 + 1;
    L12:
        r1 = true;
    L14:
        if (r1 == true) goto L26;
        TeXParser.isLoading = true;
        addTeXFontDescription(r6, JLatexMathAndroid.getResourceAsStream(r8), r8);
        int r62 = 0;
    L17:
        if (r62 >= r7.length) goto L19;
        loadedAlphabets.add(r7[r62]);
        r62 = r62 + 1;
        goto L17
    L19:
        TeXParser.isLoading = false;
        return;
    }

    public static void addAlphabet(AlphabetRegistration r2) {
        if (r2 == null) goto L11;
        addAlphabet(r2.getPackage(), r2.getUnicodeBlock(), r2.getTeXFontFileName());     // Catch: AlphabetRegistrationException -> L5 FontAlreadyLoadedException -> L8
        return;
    L5:
        e = move-exception;
        System.err.println(e.toString());
        return;
    L12:
        return;
    }

    public static void addTeXFontDescription(InputStream r1, String r2) {
        DefaultTeXFontParser r0 = new DefaultTeXFontParser(r1, r2);
        fontInfo = r0.parseFontDescriptions(fontInfo);
        textStyleMappings.putAll(r0.parseTextStyleMappings());
        symbolMappings.putAll(r0.parseSymbolMappings());
    }

    public static void addTeXFontDescription(Object r1, InputStream r2, String r3) {
        DefaultTeXFontParser r0 = new DefaultTeXFontParser(r1, r2, r3);
        fontInfo = r0.parseFontDescriptions(fontInfo);
        r0.parseExtraPath();
        textStyleMappings.putAll(r0.parseTextStyleMappings());
        symbolMappings.putAll(r0.parseSymbolMappings());
    }

    public static void addTeXFontDescription(String r2) {
        addTeXFontDescription(new FileInputStream(r2), r2);
        return;
    L5:
        e = move-exception;
        throw new ResourceParseException(r2, e);
    }

    public static void enableMagnification(boolean r0) {
        magnificationEnable = r0;
    }

    private Char getChar(char r3, CharFont[] r4, int r5) {
        if (r3 < '0') goto L8;
        if (r3 > '9') goto L8;
        int r0 = r3 - '0';
        char r1 = 0;
    L18:
        CharFont r42 = r4[r1];
        if (r42 != null) goto L23;
        return getDefaultChar(r3, r5);
    L23:
        return getChar(new CharFont((char) (r42.f503c + r0), r42.fontId), r5);
    L8:
        if (r3 < 'a') goto L13;
        if (r3 > 'z') goto L13;
        r0 = r3 - 'a';
        r1 = 2;
    L13:
        if (r3 >= 'A') goto L15;
    L17:
        r1 = 3;
        r0 = r3;
        goto L18
    L15:
        if (r3 > 'Z') goto L17;
        r0 = r3 - 'A';
        r1 = 1;
        goto L18
    }

    private Metrics getMetrics(CharFont r8, float r9) {
        float[] r7 = fontInfo[r8.fontId].getMetrics(r8.f503c);
        return new Metrics(r7[0], r7[1], r7[2], r7[3], r9 * TeXFormula.PIXELS_PER_POINT, r9);
    }

    private static float getParameter(String r1) {
        Float r12 = parameters.get(r1);
        if (r12 != null) goto L7;
        return 0.0f;
    L7:
        return r12.floatValue();
    }

    public static float getSizeFactor(int r1) {
        if (r1 >= 2) goto L7;
        return 1.0f;
    L7:
        if (r1 >= 4) goto L11;
        return generalSettings.get("textfactor").floatValue();
    L11:
        if (r1 >= 6) goto L15;
        return generalSettings.get("scriptfactor").floatValue();
    L15:
        return generalSettings.get("scriptscriptfactor").floatValue();
    }

    public static void registerAlphabet(AlphabetRegistration r4) {
        Character.UnicodeBlock[] r0 = r4.getUnicodeBlock();
        int r1 = 0;
    L4:
        if (r1 >= r0.length) goto L6;
        registeredAlphabets.put(r0[r1], r4);
        r1 = r1 + 1;
        goto L4
    }

    public static void setMagnification(float r1) {
        if (magnificationEnable == false) goto L6;
        TeXIcon.magFactor = r1 / 1000.0f;
        return;
    }

    public static void setMathSizes(float r2, float r3, float r4, float r5) {
        if (magnificationEnable == false) goto L6;
        generalSettings.put("scriptfactor", Float.valueOf(Math.abs(r4 / r2)));
        generalSettings.put("scriptscriptfactor", Float.valueOf(Math.abs(r5 / r2)));
        generalSettings.put("textfactor", Float.valueOf(Math.abs(r3 / r2)));
        TeXIcon.defaultSize = Math.abs(r2);
        return;
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public TeXFont copy() {
        return new DefaultTeXFont(this.size, this.factor, this.isBold, this.isRoman, this.isSs, this.isTt, this.isIt);
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public TeXFont deriveFont(float r9) {
        return new DefaultTeXFont(r9, this.factor, this.isBold, this.isRoman, this.isSs, this.isTt, this.isIt);
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public float getAxisHeight(int r1) {
        return (getParameter("axisheight") * getSizeFactor(r1)) * TeXFormula.PIXELS_PER_POINT;
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public float getBigOpSpacing1(int r1) {
        return (getParameter("bigopspacing1") * getSizeFactor(r1)) * TeXFormula.PIXELS_PER_POINT;
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public float getBigOpSpacing2(int r1) {
        return (getParameter("bigopspacing2") * getSizeFactor(r1)) * TeXFormula.PIXELS_PER_POINT;
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public float getBigOpSpacing3(int r1) {
        return (getParameter("bigopspacing3") * getSizeFactor(r1)) * TeXFormula.PIXELS_PER_POINT;
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public float getBigOpSpacing4(int r1) {
        return (getParameter("bigopspacing4") * getSizeFactor(r1)) * TeXFormula.PIXELS_PER_POINT;
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public float getBigOpSpacing5(int r1) {
        return (getParameter("bigopspacing5") * getSizeFactor(r1)) * TeXFormula.PIXELS_PER_POINT;
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public boolean getBold() {
        return this.isBold;
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public Char getChar(char r2, String r3, int r4) {
        CharFont[] r0 = textStyleMappings.get(r3);
        if (r0 == null) goto L7;
        return getChar(r2, r0, r4);
    L7:
        throw new TextStyleMappingNotFoundException(r3);
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public Char getChar(String r2, int r3) {
        CharFont r0 = symbolMappings.get(r2);
        if (r0 == null) goto L7;
        return getChar(r0, r3);
    L7:
        throw new SymbolMappingNotFoundException(r2);
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public Char getChar(CharFont r6, int r7) {
        float r0 = getSizeFactor(r7);
        boolean r1 = this.isBold;
        if (r1 == false) goto L5;
        int r2 = r6.boldFontId;
    L6:
        FontInfo r3 = fontInfo[r2];
        if (r1 == false) goto L12;
        if (r6.fontId != r6.boldFontId) goto L12;
        r2 = r3.getBoldId();
        r3 = fontInfo[r2];
        r6 = new CharFont(r6.f503c, r2, r7);
    L12:
        if (this.isRoman == false) goto L15;
        r2 = r3.getRomanId();
        r3 = fontInfo[r2];
        r6 = new CharFont(r6.f503c, r2, r7);
    L15:
        if (this.isSs == false) goto L18;
        r2 = r3.getSsId();
        r3 = fontInfo[r2];
        r6 = new CharFont(r6.f503c, r2, r7);
    L18:
        if (this.isTt == false) goto L21;
        r2 = r3.getTtId();
        r3 = fontInfo[r2];
        r6 = new CharFont(r6.f503c, r2, r7);
    L21:
        if (this.isIt == false) goto L23;
        r2 = r3.getItId();
        r3 = fontInfo[r2];
        r6 = new CharFont(r6.f503c, r2, r7);
    L23:
        Font r72 = r3.getFont();
        return new Char(r6.f503c, r72, r2, getMetrics(r6, this.factor * r0));
    L5:
        r2 = r6.fontId;
        goto L6
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public Char getDefaultChar(char r3, int r4) {
        if (r3 < '0') goto L9;
        if (r3 > '9') goto L9;
        return getChar(r3, defaultTextStyleMappings[0], r4);
    L9:
        if (r3 < 'a') goto L15;
        if (r3 > 'z') goto L15;
        return getChar(r3, defaultTextStyleMappings[2], r4);
    L15:
        return getChar(r3, defaultTextStyleMappings[1], r4);
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public float getDefaultRuleThickness(int r1) {
        return (getParameter("defaultrulethickness") * getSizeFactor(r1)) * TeXFormula.PIXELS_PER_POINT;
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public float getDenom1(int r1) {
        return (getParameter("denom1") * getSizeFactor(r1)) * TeXFormula.PIXELS_PER_POINT;
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public float getDenom2(int r1) {
        return (getParameter("denom2") * getSizeFactor(r1)) * TeXFormula.PIXELS_PER_POINT;
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public float getEM(int r1) {
        return getSizeFactor(r1) * TeXFormula.PIXELS_PER_POINT;
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public Extension getExtension(Char r10, int r11) {
        Font r0 = r10.getFont();
        int r1 = r10.getFontCode();
        float r112 = getSizeFactor(r11);
        int[] r102 = fontInfo[r1].getExtension(r10.getChar());
        Char[] r2 = new Char[r102.length];
        int r4 = 0;
    L4:
        if (r4 >= r102.length) goto L11;
        int r5 = r102[r4];
        if (r5 != (-1)) goto L8;
        r2[r4] = null;
    L9:
        r4 = r4 + 1;
        goto L4
    L8:
        r2[r4] = new Char((char) r5, r0, r1, getMetrics(new CharFont((char) r5, r1), r112));
        goto L9
    L11:
        return new Extension(r2[0], r2[1], r2[2], r2[3]);
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public boolean getIt() {
        return this.isIt;
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public float getKern(CharFont r2, CharFont r3, int r4) {
        int r1 = r2.fontId;
        if (r1 == r3.fontId) goto L5;
        return 0.0f;
    L5:
        return fontInfo[r1].getKern(r2.f503c, r3.f503c, getSizeFactor(r4) * TeXFormula.PIXELS_PER_POINT);
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public CharFont getLigature(CharFont r2, CharFont r3) {
        int r1 = r2.fontId;
        if (r1 == r3.fontId) goto L5;
        return null;
    L5:
        return fontInfo[r1].getLigature(r2.f503c, r3.f503c);
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public int getMuFontId() {
        return generalSettings.get(DefaultTeXFontParser.MUFONTID_ATTR).intValue();
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public Char getNextLarger(Char r5, int r6) {
        CharFont r52 = fontInfo[r5.getFontCode()].getNextLarger(r5.getChar());
        FontInfo r0 = fontInfo[r52.fontId];
        return new Char(r52.f503c, r0.getFont(), r52.fontId, getMetrics(r52, getSizeFactor(r6)));
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public float getNum1(int r1) {
        return (getParameter("num1") * getSizeFactor(r1)) * TeXFormula.PIXELS_PER_POINT;
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public float getNum2(int r1) {
        return (getParameter("num2") * getSizeFactor(r1)) * TeXFormula.PIXELS_PER_POINT;
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public float getNum3(int r1) {
        return (getParameter("num3") * getSizeFactor(r1)) * TeXFormula.PIXELS_PER_POINT;
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public float getQuad(int r1, int r2) {
        return fontInfo[r2].getQuad(getSizeFactor(r1) * TeXFormula.PIXELS_PER_POINT);
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public boolean getRoman() {
        return this.isRoman;
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public float getScaleFactor() {
        return this.factor;
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public float getSize() {
        return this.size;
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public float getSkew(CharFont r4, int r5) {
        char r0 = fontInfo[r4.fontId].getSkewChar();
        if (r0 != 65535) goto L7;
        return 0.0f;
    L7:
        return getKern(r4, new CharFont(r0, r4.fontId), r5);
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public float getSpace(int r2) {
        int r1 = generalSettings.get(DefaultTeXFontParser.SPACEFONTID_ATTR).intValue();
        return fontInfo[r1].getSpace(getSizeFactor(r2) * TeXFormula.PIXELS_PER_POINT);
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public boolean getSs() {
        return this.isSs;
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public float getSub1(int r1) {
        return (getParameter("sub1") * getSizeFactor(r1)) * TeXFormula.PIXELS_PER_POINT;
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public float getSub2(int r1) {
        return (getParameter("sub2") * getSizeFactor(r1)) * TeXFormula.PIXELS_PER_POINT;
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public float getSubDrop(int r1) {
        return (getParameter("subdrop") * getSizeFactor(r1)) * TeXFormula.PIXELS_PER_POINT;
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public float getSup1(int r1) {
        return (getParameter("sup1") * getSizeFactor(r1)) * TeXFormula.PIXELS_PER_POINT;
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public float getSup2(int r1) {
        return (getParameter("sup2") * getSizeFactor(r1)) * TeXFormula.PIXELS_PER_POINT;
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public float getSup3(int r1) {
        return (getParameter("sup3") * getSizeFactor(r1)) * TeXFormula.PIXELS_PER_POINT;
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public float getSupDrop(int r1) {
        return (getParameter("supdrop") * getSizeFactor(r1)) * TeXFormula.PIXELS_PER_POINT;
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public boolean getTt() {
        return this.isTt;
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public float getXHeight(int r1, int r2) {
        return fontInfo[r2].getXHeight(getSizeFactor(r1) * TeXFormula.PIXELS_PER_POINT);
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public boolean hasNextLarger(Char r2) {
        if (fontInfo[r2.getFontCode()].getNextLarger(r2.getChar()) == null) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public boolean hasSpace(int r1) {
        return fontInfo[r1].hasSpace();
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public boolean isExtensionChar(Char r2) {
        if (fontInfo[r2.getFontCode()].getExtension(r2.getChar()) == null) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public TeXFont scaleFont(float r9) {
        return new DefaultTeXFont(this.size, r9, this.isBold, this.isRoman, this.isSs, this.isTt, this.isIt);
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public void setBold(boolean r1) {
        this.isBold = r1;
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public void setIt(boolean r1) {
        this.isIt = r1;
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public void setRoman(boolean r1) {
        this.isRoman = r1;
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public void setSs(boolean r1) {
        this.isSs = r1;
    }

    @Override // org.scilab.forge.jlatexmath.TeXFont
    public void setTt(boolean r1) {
        this.isTt = r1;
    }
}
