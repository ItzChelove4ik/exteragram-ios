package org.scilab.forge.jlatexmath;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import okhttp3.internal.url._UrlKt;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.noties.jlatexmath.JLatexMathAndroid;
import ru.noties.jlatexmath.awt.Font;

/* loaded from: classes5.dex */
public class DefaultTeXFontParser {
    protected static ArrayList<String> Font_ID = null;
    public static final String GEN_SET_EL = "GeneralSettings";
    public static final String MUFONTID_ATTR = "mufontid";
    public static final String RESOURCE_NAME = "DefaultTeXFont.xml";
    public static final String SPACEFONTID_ATTR = "spacefontid";
    public static final String STYLE_MAPPING_EL = "TextStyleMapping";
    public static final String SYMBOL_MAPPING_EL = "SymbolMapping";
    private static Map<String, CharChildParser> charChildParsers = null;
    private static DocumentBuilderFactory factory = null;
    private static Map<String, Integer> rangeTypeMappings = null;
    private static boolean registerFontExceptionDisplayed = false;
    private static boolean shouldRegisterFonts = true;
    private Object base;
    private Map<String, CharFont[]> parsedTextStyles;
    private Element root;

    public interface CharChildParser {
        void parse(Element r1, char r2, FontInfo r3);
    }

    public static class ExtensionParser implements CharChildParser {
        public ExtensionParser() {
        }

        @Override // org.scilab.forge.jlatexmath.DefaultTeXFontParser.CharChildParser
        public void parse(Element r5, char r6, FontInfo r7) {
            int r4 = DefaultTeXFontParser.getIntAndCheck("rep", r5);
            r7.setExtension(r6, new int[]{DefaultTeXFontParser.getOptionalInt("top", r5, -1), DefaultTeXFontParser.getOptionalInt("mid", r5, -1), r4, DefaultTeXFontParser.getOptionalInt("bot", r5, -1)});
        }
    }

    public static class KernParser implements CharChildParser {
        public KernParser() {
        }

        @Override // org.scilab.forge.jlatexmath.DefaultTeXFontParser.CharChildParser
        public void parse(Element r2, char r3, FontInfo r4) {
            int r1 = DefaultTeXFontParser.getIntAndCheck("code", r2);
            char r12 = (char) r1;
            r4.addKern(r3, r12, DefaultTeXFontParser.getFloatAndCheck("val", r2));
        }
    }

    public static class LigParser implements CharChildParser {
        public LigParser() {
        }

        @Override // org.scilab.forge.jlatexmath.DefaultTeXFontParser.CharChildParser
        public void parse(Element r2, char r3, FontInfo r4) {
            int r1 = DefaultTeXFontParser.getIntAndCheck("code", r2);
            r4.addLigature(r3, (char) r1, (char) DefaultTeXFontParser.getIntAndCheck("ligCode", r2));
        }
    }

    public static class NextLargerParser implements CharChildParser {
        public NextLargerParser() {
        }

        @Override // org.scilab.forge.jlatexmath.DefaultTeXFontParser.CharChildParser
        public void parse(Element r2, char r3, FontInfo r4) {
            String r1 = DefaultTeXFontParser.m3847$$Nest$smgetAttrValueAndCheckIfNotNull("fontId", r2);
            r4.setNextLarger(r3, (char) DefaultTeXFontParser.getIntAndCheck("code", r2), DefaultTeXFontParser.Font_ID.indexOf(r1));
        }
    }

    /* renamed from: -$$Nest$smgetAttrValueAndCheckIfNotNull, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m3847$$Nest$smgetAttrValueAndCheckIfNotNull(String r0, Element r1) {
        return getAttrValueAndCheckIfNotNull(r0, r1);
    }

    static {
        factory = DocumentBuilderFactory.newInstance();
        Font_ID = new ArrayList();
        rangeTypeMappings = new HashMap();
        charChildParsers = new HashMap();
        setRangeTypeMappings();
        setCharChildParsers();
    }

    public DefaultTeXFontParser() {
        this(JLatexMathAndroid.getResourceAsStream(RESOURCE_NAME), RESOURCE_NAME);
    }

    public DefaultTeXFontParser(InputStream r3, String r4) {
        this.base = null;
        factory.setIgnoringElementContentWhitespace(true);
        factory.setIgnoringComments(true);
        this.root = factory.newDocumentBuilder().parse(r3).getDocumentElement();     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        throw new XMLResourceParseException(r4, e);
    }

    public DefaultTeXFontParser(Object r2, InputStream r3, String r4) {
        this.base = r2;
        factory.setIgnoringElementContentWhitespace(true);
        factory.setIgnoringComments(true);
        this.root = factory.newDocumentBuilder().parse(r3).getDocumentElement();     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        throw new XMLResourceParseException(r4, e);
    }

    public static Font createFont(String r2) {
        return Font.createFont(JLatexMathAndroid.loadTypeface(r2), TeXFormula.PIXELS_PER_POINT * TeXFormula.FONT_SCALE_FACTOR);
    }

    private static String getAttrValueAndCheckIfNotNull(String r3, Element r4) {
        String r0 = r4.getAttribute(r3);
        if (r0.equals(_UrlKt.FRAGMENT_ENCODE_SET) == true) goto L6;
        return r0;
    L6:
        throw new XMLResourceParseException(RESOURCE_NAME, r4.getTagName(), r3, null);
    }

    public static float getFloatAndCheck(String r3, Element r4) {
        return (float) Double.parseDouble(getAttrValueAndCheckIfNotNull(r3, r4));
    L7:
        throw new XMLResourceParseException(RESOURCE_NAME, r4.getTagName(), r3, "has an invalid real value!");
    }

    public static int getIntAndCheck(String r3, Element r4) {
        return Integer.parseInt(getAttrValueAndCheckIfNotNull(r3, r4));
    L6:
        throw new XMLResourceParseException(RESOURCE_NAME, r4.getTagName(), r3, "has an invalid integer value!");
    }

    public static float getOptionalFloat(String r2, Element r3, float r4) {
        String r0 = r3.getAttribute(r2);
        if (r0.equals(_UrlKt.FRAGMENT_ENCODE_SET) == false) goto L10;
        return r4;
    L10:
        return (float) Double.parseDouble(r0);
    L9:
        throw new XMLResourceParseException(RESOURCE_NAME, r3.getTagName(), r2, "has an invalid float value!");
    }

    public static int getOptionalInt(String r2, Element r3, int r4) {
        String r0 = r3.getAttribute(r2);
        if (r0.equals(_UrlKt.FRAGMENT_ENCODE_SET) == false) goto L9;
        return r4;
    L9:
        return Integer.parseInt(r0);
    L8:
        throw new XMLResourceParseException(RESOURCE_NAME, r3.getTagName(), r2, "has an invalid integer value!");
    }

    private Map<String, CharFont[]> parseStyleMappings() {
        HashMap r0 = new HashMap();
        Element r15 = (Element) this.root.getElementsByTagName("TextStyleMappings").item(0);
        if (r15 == null) goto L24;
        NodeList r152 = r15.getElementsByTagName(STYLE_MAPPING_EL);
        int r2 = 0;
    L7:
        if (r2 >= r152.getLength()) goto L24;
        Element r3 = (Element) r152.item(r2);
        String r4 = getAttrValueAndCheckIfNotNull("name", r3);
        String r6 = getAttrValueAndCheckIfNotNull("bold", r3);     // Catch: ResourceParseException -> L11
    L12:
        NodeList r32 = r3.getElementsByTagName("MapRange");
        CharFont[] r8 = new CharFont[4];
        int r9 = 0;
    L14:
        if (r9 >= r32.getLength()) goto L23;
        Element r10 = (Element) r32.item(r9);
        String r11 = getAttrValueAndCheckIfNotNull("fontId", r10);
        int r12 = getIntAndCheck("start", r10);
        String r102 = getAttrValueAndCheckIfNotNull("code", r10);
        Integer r14 = rangeTypeMappings.get(r102);
        if (r14 == null) goto L21;
        if (r6 != null) goto L19;
        r8[r14.intValue()] = new CharFont((char) r12, Font_ID.indexOf(r11));
    L20:
        r9 = r9 + 1;
        goto L14
    L19:
        r8[r14.intValue()] = new CharFont((char) r12, Font_ID.indexOf(r11), Font_ID.indexOf(r6));
        goto L20
    L21:
        TeXSymbolParser$$ExternalSyntheticBUOutline0.m("contains an unknown \"range name\" '", r102, RESOURCE_NAME, "MapRange", "code");
        return null;
    L23:
        r0.put(r4, r8);
        r2 = r2 + 1;
    L11:
        r6 = null;
    L24:
        return r0;
    }

    private static void processCharElement(Element r7, FontInfo r8) {
        char r0 = (char) getIntAndCheck("code", r7);
        int r6 = 0;
        r8.setMetrics(r0, new float[]{getOptionalFloat("width", r7, 0.0f), getOptionalFloat("height", r7, 0.0f), getOptionalFloat("depth", r7, 0.0f), getOptionalFloat("italic", r7, 0.0f)});
        NodeList r72 = r7.getChildNodes();
    L4:
        if (r6 >= r72.getLength()) goto L13;
        Node r2 = r72.item(r6);
        if (r2.getNodeType() == 3) goto L12;
        Element r22 = (Element) r2;
        CharChildParser r3 = charChildParsers.get(r22.getTagName());
        if (r3 == null) goto L11;
        r3.parse(r22, r0, r8);
        goto L12
    L11:
        throw new XMLResourceParseException("DefaultTeXFont.xml: a <Char>-element has an unknown child element '" + r22.getTagName() + "'!");
    L12:
        r6 = r6 + 1;
        goto L4
    }

    public static void registerFonts(boolean r0) {
        shouldRegisterFonts = r0;
    }

    private static void setCharChildParsers() {
        charChildParsers.put("Kern", new KernParser());
        charChildParsers.put("Lig", new LigParser());
        charChildParsers.put("NextLarger", new NextLargerParser());
        charChildParsers.put("Extension", new ExtensionParser());
    }

    private static void setRangeTypeMappings() {
        rangeTypeMappings.put("numbers", 0);
        rangeTypeMappings.put("capitals", 1);
        rangeTypeMappings.put("small", 2);
        rangeTypeMappings.put("unicode", 3);
    }

    public String[] parseDefaultTextStyleMappings() {
        String[] r0 = new String[4];
        int r2 = 0;
        Element r1 = (Element) this.root.getElementsByTagName("DefaultTextStyleMapping").item(0);
        if (r1 == null) goto L21;
        NodeList r12 = r1.getElementsByTagName("MapStyle");
    L7:
        if (r2 >= r12.getLength()) goto L21;
        Element r4 = (Element) r12.item(r2);
        String r6 = getAttrValueAndCheckIfNotNull("code", r4);
        Integer r7 = rangeTypeMappings.get(r6);
        if (r7 == null) goto L19;
        String r42 = getAttrValueAndCheckIfNotNull("textStyle", r4);
        if (this.parsedTextStyles.get(r42) == null) goto L17;
        CharFont[] r5 = this.parsedTextStyles.get(r42);
        int r72 = r7.intValue();
        if (r5[r72] == null) goto L16;
        r0[r72] = r42;
        r2 = r2 + 1;
        goto L7
    L16:
        throw new XMLResourceParseException("DefaultTeXFont.xml: the default text style mapping '" + r42 + "' for the range '" + r6 + "' contains no mapping for that range!");
    L17:
        TeXSymbolParser$$ExternalSyntheticBUOutline0.m("contains an unknown text style '", r42, RESOURCE_NAME, "MapStyle", "textStyle");
        return null;
    L19:
        TeXSymbolParser$$ExternalSyntheticBUOutline0.m("contains an unknown \"range name\" '", r6, RESOURCE_NAME, "MapStyle", "code");
        return null;
    L21:
        return r0;
    }

    public void parseExtraPath() {
        Element r0 = (Element) this.root.getElementsByTagName("TeXSymbols").item(0);
        if (r0 == null) goto L5;
        String r02 = getAttrValueAndCheckIfNotNull("include", r0);
        SymbolAtom.addSymbolAtom(JLatexMathAndroid.getResourceAsStream(r02), r02);
    L5:
        Element r4 = (Element) this.root.getElementsByTagName("FormulaSettings").item(0);
        if (r4 == null) goto L9;
        String r42 = getAttrValueAndCheckIfNotNull("include", r4);
        TeXFormula.addSymbolMappings(JLatexMathAndroid.getResourceAsStream(r42), r42);
        return;
    }

    public FontInfo[] parseFontDescriptions(FontInfo[] r5) {
        int r1 = 0;
        Element r0 = (Element) this.root.getElementsByTagName("FontDescriptions").item(0);
        if (r0 == null) goto L12;
        NodeList r02 = r0.getElementsByTagName("Metrics");
    L6:
        if (r1 >= r02.getLength()) goto L12;
        String r2 = getAttrValueAndCheckIfNotNull("include", (Element) r02.item(r1));
        if (this.base != null) goto L10;
        r5 = parseFontDescriptions(r5, JLatexMathAndroid.getResourceAsStream(r2), r2);
    L11:
        r1 = r1 + 1;
        goto L6
    L10:
        r5 = parseFontDescriptions(r5, JLatexMathAndroid.getResourceAsStream(r2), r2);
    L12:
        return r5;
    }

    public FontInfo[] parseFontDescriptions(FontInfo[] r21, InputStream r22, String r23) {
        if (r22 != null) goto L5;
        return r21;
    L5:
        ArrayList r4 = new ArrayList(Arrays.asList(r21));
        Element r2 = factory.newDocumentBuilder().parse(r22).getDocumentElement();     // Catch: Exception -> L38
        String r10 = getAttrValueAndCheckIfNotNull("name", r2);
        String r5 = getAttrValueAndCheckIfNotNull("id", r2);
        if (Font_ID.indexOf(r5) >= 0) goto L37;
        Font_ID.add(r5);
        float r13 = getFloatAndCheck("space", r2);
        float r12 = getFloatAndCheck("xHeight", r2);
        float r14 = getFloatAndCheck("quad", r2);
        int r6 = getOptionalInt("skewChar", r2, -1);
        int r11 = getOptionalInt("unicode", r2, 0);
        String r8 = null;
        String r15 = getAttrValueAndCheckIfNotNull("boldVersion", r2);     // Catch: ResourceParseException -> L12
    L42:
        String r16 = getAttrValueAndCheckIfNotNull("romanVersion", r2);     // Catch: ResourceParseException -> L15
    L50:
        String r17 = getAttrValueAndCheckIfNotNull("ssVersion", r2);     // Catch: ResourceParseException -> L18
    L46:
        String r18 = getAttrValueAndCheckIfNotNull("ttVersion", r2);     // Catch: ResourceParseException -> L21
    L52:
        r8 = getAttrValueAndCheckIfNotNull("itVersion", r2);     // Catch: ResourceParseException -> L41
    L23:
        String r3 = r23.substring(0, r23.lastIndexOf("/") + 1) + r10;
        FontInfo r62 = new FontInfo(Font_ID.indexOf(r5), this.base, r3, r10, r11, r12, r13, r14, r15, r16, r17, r18, r8);
        if (r6 == (-1)) goto L26;
        r62.setSkewChar((char) r6);
    L26:
        NodeList r24 = r2.getElementsByTagName("Char");
        int r9 = 0;
    L28:
        if (r9 >= r24.getLength()) goto L30;
        processCharElement((Element) r24.item(r9), r62);
        r9 = r9 + 1;
        goto L28
    L30:
        r4.add(r62);
        int r92 = 0;
    L32:
        if (r92 >= r4.size()) goto L34;
        FontInfo r25 = (FontInfo) r4.get(r92);
        r25.setBoldId(Font_ID.indexOf(r25.boldVersion));
        r25.setRomanId(Font_ID.indexOf(r25.romanVersion));
        r25.setSsId(Font_ID.indexOf(r25.ssVersion));
        r25.setTtId(Font_ID.indexOf(r25.ttVersion));
        r25.setItId(Font_ID.indexOf(r25.itVersion));
        r92 = r92 + 1;
        goto L32
    L34:
        this.parsedTextStyles = parseStyleMappings();
        return (FontInfo[]) r4.toArray(r21);
    L21:
        r18 = null;
    L18:
        r17 = null;
    L15:
        r16 = null;
    L12:
        r15 = null;
        goto L42
    L37:
        throw new FontAlreadyLoadedException("Font " + r5 + " is already loaded !");
    L38:
        e = move-exception;
        throw new XMLResourceParseException("Cannot find the file " + r23 + "!" + e.toString());
    }

    public Map<String, Number> parseGeneralSettings() {
        HashMap r0 = new HashMap();
        Element r4 = (Element) this.root.getElementsByTagName(GEN_SET_EL).item(0);
        if (r4 == null) goto L7;
        r0.put(MUFONTID_ATTR, Integer.valueOf(Font_ID.indexOf(getAttrValueAndCheckIfNotNull(MUFONTID_ATTR, r4))));
        r0.put(SPACEFONTID_ATTR, Integer.valueOf(Font_ID.indexOf(getAttrValueAndCheckIfNotNull(SPACEFONTID_ATTR, r4))));
        r0.put("scriptfactor", Float.valueOf(getFloatAndCheck("scriptfactor", r4)));
        r0.put("scriptscriptfactor", Float.valueOf(getFloatAndCheck("scriptscriptfactor", r4)));
        return r0;
    L7:
        throw new XMLResourceParseException(RESOURCE_NAME, GEN_SET_EL);
    }

    public Map<String, Float> parseParameters() {
        HashMap r0 = new HashMap();
        int r2 = 0;
        Element r6 = (Element) this.root.getElementsByTagName("Parameters").item(0);
        if (r6 == null) goto L10;
        NamedNodeMap r1 = r6.getAttributes();
    L6:
        if (r2 >= r1.getLength()) goto L8;
        String r3 = ((Attr) r1.item(r2)).getName();
        r0.put(r3, new Float(getFloatAndCheck(r3, r6)));
        r2 = r2 + 1;
        goto L6
    L8:
        return r0;
    L10:
        throw new XMLResourceParseException(RESOURCE_NAME, "Parameters");
    }

    public Map<String, CharFont> parseSymbolMappings() {
        HashMap r0 = new HashMap();
        Element r1 = (Element) this.root.getElementsByTagName("SymbolMappings").item(0);
        if (r1 == null) goto L28;
        NodeList r12 = r1.getElementsByTagName("Mapping");
        int r2 = 0;
    L6:
        if (r2 >= r12.getLength()) goto L26;
        String r4 = getAttrValueAndCheckIfNotNull("include", (Element) r12.item(r2));
        if (this.base != null) goto L11;
        Element r42 = factory.newDocumentBuilder().parse(JLatexMathAndroid.getResourceAsStream(r4)).getDocumentElement();     // Catch: Exception -> L24
    L12:
        NodeList r43 = r42.getElementsByTagName(SYMBOL_MAPPING_EL);
        int r5 = 0;
    L14:
        if (r5 >= r43.getLength()) goto L23;
        Element r6 = (Element) r43.item(r5);
        String r7 = getAttrValueAndCheckIfNotNull("name", r6);
        int r8 = getIntAndCheck("ch", r6);
        String r9 = getAttrValueAndCheckIfNotNull("fontId", r6);
        String r62 = getAttrValueAndCheckIfNotNull("boldId", r6);     // Catch: ResourceParseException -> L18
    L19:
        if (r62 != null) goto L21;
        r0.put(r7, new CharFont((char) r8, Font_ID.indexOf(r9)));
    L22:
        r5 = r5 + 1;
        goto L14
    L21:
        r0.put(r7, new CharFont((char) r8, Font_ID.indexOf(r9), Font_ID.indexOf(r62)));
    L18:
        r62 = null;
        goto L19
    L23:
        r2 = r2 + 1;
        goto L6
    L11:
        r42 = factory.newDocumentBuilder().parse(JLatexMathAndroid.getResourceAsStream(r4)).getDocumentElement();     // Catch: Exception -> L24
    L25:
        throw new XMLResourceParseException("Cannot find the file " + r4 + "!");
    L26:
        return r0;
    L28:
        throw new XMLResourceParseException(RESOURCE_NAME, "SymbolMappings");
    }

    public Map<String, CharFont[]> parseTextStyleMappings() {
        return this.parsedTextStyles;
    }
}
