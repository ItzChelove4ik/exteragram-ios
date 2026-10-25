package org.scilab.forge.jlatexmath;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.Character;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.scilab.forge.jlatexmath.cyrillic.CyrillicRegistration;
import org.scilab.forge.jlatexmath.greek.GreekRegistration;
import ru.noties.jlatexmath.awt.Color;
import ru.noties.jlatexmath.awt.GraphicsEnvironment;
import ru.noties.jlatexmath.awt.Toolkit;

/* loaded from: classes5.dex */
public class TeXFormula {
    public static final int BOLD = 2;
    public static float FONT_SCALE_FACTOR = 100.0f;
    public static final int ITALIC = 4;
    public static float PIXELS_PER_POINT = 1.0f;
    protected static final float PREC = 1.0E-7f;
    public static final int ROMAN = 8;
    public static final int SANSSERIF = 1;
    public static final int SERIF = 0;
    public static final int TYPEWRITER = 16;
    public static final String VERSION = "1.0.3";
    public static Map<Character.UnicodeBlock, FontInfos> externalFontMap;
    public static Map<String, TeXFormula> predefinedTeXFormulas;
    public static Map<String, String> predefinedTeXFormulasAsString;
    public static String[] symbolFormulaMappings;
    public static String[] symbolMappings;
    public static String[] symbolTextMappings;
    public boolean isColored;
    protected Map<String, String> jlmXMLMap;
    public List<MiddleAtom> middle;
    private TeXParser parser;
    public Atom root;
    public String textStyle;

    public static class FontInfos {
        String sansserif;
        String serif;

        public FontInfos(String r1, String r2) {
            this.sansserif = r1;
            this.serif = r2;
        }
    }

    public class TeXIconBuilder {
        private Integer align;
        private Color fgcolor;
        private Float interLineSpacing;
        private Integer interLineUnit;
        private boolean isMaxWidth;
        private Float size;
        private Integer style;
        private Float textWidth;
        final /* synthetic */ TeXFormula this$0;
        private boolean trueValues;
        private Integer type;
        private Integer widthUnit;

        public TeXIconBuilder(TeXFormula r1) {
            this.this$0 = r1;
            this.trueValues = false;
            this.isMaxWidth = false;
        }

        public TeXIcon build() {
            Box.resetBoxBudget();
            if (this.style == null) goto L41;
            Float r0 = this.size;
            if (r0 != null) goto L7;
            Segment$$ExternalSyntheticBUOutline1.m("A size is required. Use setStyle()");
            return null;
        L7:
            if (this.type != null) goto L9;
            DefaultTeXFont r02 = new DefaultTeXFont(this.size.floatValue());
        L10:
            Integer r1 = this.widthUnit;
            Integer r2 = this.style;
            if (r1 == null) goto L13;
            TeXEnvironment r12 = new TeXEnvironment(r2.intValue(), r02, this.widthUnit.intValue(), this.textWidth.floatValue());
        L14:
            Integer r03 = this.interLineUnit;
            if (r03 == null) goto L17;
            r12.setInterline(r03.intValue(), this.interLineSpacing.floatValue());
        L17:
            Box r04 = TeXFormula.m3848$$Nest$mcreateBox(this.this$0, r12);
            if (this.widthUnit != null) goto L20;
            TeXIcon r05 = new TeXIcon(r04, this.size.floatValue(), this.trueValues);
        L33:
            Color r5 = this.fgcolor;
            if (r5 == null) goto L36;
            r05.setForeground(r5);
        L36:
            r05.isColored = r12.isColored;
            return r05;
        L20:
            if (this.interLineUnit == null) goto L27;
            float r22 = this.interLineSpacing.floatValue() * SpaceAtom.getFactor(this.interLineUnit.intValue(), r12);
            Box r06 = BreakFormula.split(r04, r12.getTextwidth(), r22);
            if (this.isMaxWidth == false) goto L24;
            float r3 = r06.getWidth();
        L25:
            HorizontalBox r23 = new HorizontalBox(r06, r3, this.align.intValue());
        L31:
            r05 = new TeXIcon(r23, this.size.floatValue(), this.trueValues);
            goto L33
        L24:
            r3 = r12.getTextwidth();
            goto L25
        L27:
            if (this.isMaxWidth == false) goto L29;
            float r32 = r04.getWidth();
        L30:
            r23 = new HorizontalBox(r04, r32, this.align.intValue());
            goto L31
        L29:
            r32 = r12.getTextwidth();
            goto L30
        L13:
            r12 = new TeXEnvironment(r2.intValue(), r02);
            goto L14
        L9:
            r02 = TeXFormula.m3849$$Nest$mcreateFont(this.this$0, r0.floatValue(), this.type.intValue());
            goto L10
        L41:
            Segment$$ExternalSyntheticBUOutline1.m("A style is required. Use setStyle()");
            return null;
        }

        public TeXIconBuilder setFGColor(Color r1) {
            this.fgcolor = r1;
            return this;
        }

        public TeXIconBuilder setInterLineSpacing(int r2, float r3) {
            if (this.widthUnit == null) goto L6;
            this.interLineUnit = Integer.valueOf(r2);
            this.interLineSpacing = Float.valueOf(r3);
            return this;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("Cannot set inter line spacing without having specified a width!");
            return null;
        }

        public TeXIconBuilder setIsMaxWidth(boolean r2) {
            if (this.widthUnit == null) goto L8;
            if (r2 == false) goto L6;
            this.align = 0;
        L6:
            this.isMaxWidth = r2;
            return this;
        L8:
            Segment$$ExternalSyntheticBUOutline1.m("Cannot set 'isMaxWidth' without having specified a width!");
            return null;
        }

        public TeXIconBuilder setSize(float r1) {
            this.size = Float.valueOf(r1);
            return this;
        }

        public TeXIconBuilder setStyle(int r1) {
            this.style = Integer.valueOf(r1);
            return this;
        }

        public TeXIconBuilder setTrueValues(boolean r1) {
            this.trueValues = r1;
            return this;
        }

        public TeXIconBuilder setType(int r1) {
            this.type = Integer.valueOf(r1);
            return this;
        }

        public TeXIconBuilder setWidth(int r1, float r2, int r3) {
            this.widthUnit = Integer.valueOf(r1);
            this.textWidth = Float.valueOf(r2);
            this.align = Integer.valueOf(r3);
            this.trueValues = true;
            return this;
        }
    }

    /* renamed from: -$$Nest$mcreateBox, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Box m3848$$Nest$mcreateBox(TeXFormula r0, TeXEnvironment r1) {
        return r0.createBox(r1);
    }

    /* renamed from: -$$Nest$mcreateFont, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DefaultTeXFont m3849$$Nest$mcreateFont(TeXFormula r0, float r1, int r2) {
        return r0.createFont(r1, r2);
    }

    static {
        predefinedTeXFormulas = new HashMap(150);
        predefinedTeXFormulasAsString = new HashMap(150);
        symbolMappings = new String[65536];
        symbolTextMappings = new String[65536];
        symbolFormulaMappings = new String[65536];
        externalFontMap = new HashMap();
        TeXFormulaSettingsParser r0 = new TeXFormulaSettingsParser();
        r0.parseSymbolMappings(symbolMappings, symbolTextMappings);
        new PredefinedCommands();
        new PredefinedTeXFormulas();
        new PredefMacros();
        r0.parseSymbolToFormulaMappings(symbolFormulaMappings, symbolTextMappings);
        DefaultTeXFont.registerAlphabet((AlphabetRegistration) CyrillicRegistration.class.newInstance());     // Catch: Exception -> L5
        DefaultTeXFont.registerAlphabet((AlphabetRegistration) GreekRegistration.class.newInstance());     // Catch: Exception -> L5
        return;
    }

    public TeXFormula() {
        this.middle = new LinkedList();
        this.root = null;
        this.textStyle = null;
        this.isColored = false;
        this.parser = new TeXParser(_UrlKt.FRAGMENT_ENCODE_SET, this, false);
    }

    public TeXFormula(String r2) {
        this(r2, null);
    }

    public TeXFormula(String r2, String r3) {
        this.middle = new LinkedList();
        this.root = null;
        this.isColored = false;
        this.textStyle = r3;
        TeXParser r32 = new TeXParser(r2, this);
        this.parser = r32;
        r32.parse();
    }

    public TeXFormula(String r2, String r3, boolean r4, boolean r5) {
        this.middle = new LinkedList();
        this.root = null;
        this.isColored = false;
        this.textStyle = r3;
        TeXParser r32 = new TeXParser(r2, this, r4, r5);
        this.parser = r32;
        r32.parse();
    }

    public TeXFormula(String r2, Map<String, String> r3) {
        this.middle = new LinkedList();
        this.root = null;
        this.textStyle = null;
        this.isColored = false;
        this.jlmXMLMap = r3;
        TeXParser r32 = new TeXParser(r2, this);
        this.parser = r32;
        r32.parse();
    }

    public TeXFormula(String r3, boolean r4) {
        this.middle = new LinkedList();
        this.root = null;
        this.isColored = false;
        this.textStyle = null;
        TeXParser r0 = new TeXParser(r3, this, r4);
        this.parser = r0;
        r0.parse();
    }

    public TeXFormula(TeXFormula r2) {
        this.middle = new LinkedList();
        this.root = null;
        this.textStyle = null;
        this.isColored = false;
        if (r2 == null) goto L6;
        addImpl(r2);
        return;
    }

    public TeXFormula(TeXParser r4) {
        this.middle = new LinkedList();
        this.root = null;
        this.textStyle = null;
        this.isColored = false;
        this.jlmXMLMap = r4.formula.jlmXMLMap;
        this.parser = new TeXParser(r4.getIsPartial(), _UrlKt.FRAGMENT_ENCODE_SET, this, false);
    }

    public TeXFormula(TeXParser r2, String r3) {
        this(r2, r3, null);
    }

    public TeXFormula(TeXParser r2, String r3, String r4) {
        this.middle = new LinkedList();
        this.root = null;
        this.isColored = false;
        this.textStyle = r4;
        this.jlmXMLMap = r2.formula.jlmXMLMap;
        boolean r22 = r2.getIsPartial();
        TeXParser r42 = new TeXParser(r22, r3, this);
        this.parser = r42;
        if (r22 == true) goto L12;
        r42.parse();
        return;
    L12:
        r42.parse();     // Catch: Exception -> L6
        return;
    L7:
        if (this.root != null) goto L14;
        this.root = new EmptyAtom();
        return;
    }

    public TeXFormula(TeXParser r7, String r8, String r9, boolean r10, boolean r11) {
        this.middle = new LinkedList();
        this.root = null;
        this.isColored = false;
        this.textStyle = r9;
        this.jlmXMLMap = r7.formula.jlmXMLMap;
        boolean r1 = r7.getIsPartial();
        TeXParser r0 = new TeXParser(r1, r8, this, r10, r11);
        this.parser = r0;
        if (r1 == true) goto L12;
        r0.parse();
        return;
    L12:
        r0.parse();     // Catch: Exception -> L6
        return;
    L7:
        if (this.root != null) goto L14;
        this.root = new EmptyAtom();
        return;
    }

    public TeXFormula(TeXParser r3, String r4, boolean r5) {
        this.middle = new LinkedList();
        this.root = null;
        this.isColored = false;
        this.textStyle = null;
        this.jlmXMLMap = r3.formula.jlmXMLMap;
        boolean r32 = r3.getIsPartial();
        TeXParser r0 = new TeXParser(r32, r4, this, r5);
        this.parser = r0;
        if (r32 == true) goto L9;
        r0.parse();
        return;
    L9:
        r0.parse();     // Catch: Exception -> L8
        return;
    }

    private void addImpl(TeXFormula r3) {
        Atom r0 = r3.root;
        if (r0 != null) goto L5;
        return;
    L5:
        if ((r0 instanceof RowAtom) == false) goto L8;
        add(new RowAtom(r3.root));
        return;
    L8:
        add(r0);
    }

    public static void addPredefinedCommands(InputStream r2) {
        new PredefinedTeXFormulaParser(r2, "Command").parse(MacroInfo.Commands);
    }

    public static void addPredefinedTeXFormula(InputStream r2) {
        new PredefinedTeXFormulaParser(r2, "TeXFormula").parse(predefinedTeXFormulas);
    }

    public static void addSymbolMappings(InputStream r1, String r2) {
        TeXFormulaSettingsParser r0 = new TeXFormulaSettingsParser(r1, r2);
        r0.parseSymbolMappings(symbolMappings, symbolTextMappings);
        r0.parseSymbolToFormulaMappings(symbolFormulaMappings, symbolTextMappings);
    }

    public static void addSymbolMappings(String r2) {
        addSymbolMappings(new FileInputStream(r2), r2);
        return;
    L5:
        e = move-exception;
        throw new ResourceParseException(r2, e);
    }

    private Box createBox(TeXEnvironment r1) {
        Atom r0 = this.root;
        if (r0 != null) goto L7;
        return new StrutBox(0.0f, 0.0f, 0.0f, 0.0f);
    L7:
        return r0.createBox(r1);
    }

    private DefaultTeXFont createFont(float r2, int r3) {
        DefaultTeXFont r1 = new DefaultTeXFont(r2);
        if (r3 != 0) goto L6;
        r1.setSs(false);
    L6:
        if ((r3 & 8) == 0) goto L9;
        r1.setRoman(true);
    L9:
        if ((r3 & 16) == 0) goto L12;
        r1.setTt(true);
    L12:
        if ((r3 & 1) == 0) goto L15;
        r1.setSs(true);
    L15:
        if ((r3 & 4) == 0) goto L18;
        r1.setIt(true);
    L18:
        if ((r3 & 2) == 0) goto L20;
        r1.setBold(true);
    L20:
        return r1;
    }

    public static TeXFormula get(String r2) {
        TeXFormula r0 = predefinedTeXFormulas.get(r2);
        if (r0 != null) goto L13;
        String r02 = predefinedTeXFormulasAsString.get(r2);
        if (r02 == null) goto L11;
        TeXFormula r1 = new TeXFormula(r02);
        if ((r1.root instanceof RowAtom) == true) goto L9;
        predefinedTeXFormulas.put(r2, r1);
    L9:
        return r1;
    L11:
        throw new FormulaNotFoundException(r2);
    L13:
        return new TeXFormula(r0);
    }

    public static TeXFormula getAsText(String r9, int r10) {
        TeXFormula r0 = new TeXFormula();
        if (r9 != null) goto L5;
    L12:
        r0.add(new EmptyAtom());
        return r0;
    L5:
        if (_UrlKt.FRAGMENT_ENCODE_SET.equals(r9) == true) goto L12;
        String[] r92 = r9.split("\n|\\\\\\\\|\\\\cr");
        ArrayOfAtoms r1 = new ArrayOfAtoms();
        int r2 = r92.length;
        int r4 = 0;
    L8:
        if (r4 >= r2) goto L10;
        r1.add(new RomanAtom(new TeXFormula(r92[r4], "mathnormal", true, false).root));
        r1.addRow();
        r4 = r4 + 1;
        goto L8
    L10:
        r1.checkDimensions();
        r0.add(new MatrixAtom(false, r1, 0, r10));
        return r0;
    }

    public static FontInfos getExternalFont(Character.UnicodeBlock r3) {
        FontInfos r0 = externalFontMap.get(r3);
        if (r0 != null) goto L6;
        FontInfos r02 = new FontInfos("SansSerif", "Serif");
        externalFontMap.put(r3, r02);
        return r02;
    L6:
        return r0;
    }

    public static TeXFormula getPartialTeXFormula(String r3) {
        TeXFormula r0 = new TeXFormula();
        if (r3 != null) goto L13;
        r0.add(new EmptyAtom());
        return r0;
    L13:
        new TeXParser(true, r3, r0).parse();     // Catch: Exception -> L9
        return r0;
    L10:
        if (r0.root != null) goto L12;
        r0.root = new EmptyAtom();
    L12:
        return r0;
    }

    public static boolean isRegisteredBlock(Character.UnicodeBlock r1) {
        if (externalFontMap.get(r1) == null) goto L6;
        return true;
    L6:
        return false;
    }

    public static void registerExternalFont(Character.UnicodeBlock r0, String r1) {
        registerExternalFont(r0, r1, r1);
    }

    public static void registerExternalFont(Character.UnicodeBlock r2, String r3, String r4) {
        if (r3 != null) goto L6;
        if (r4 != null) goto L6;
        externalFontMap.remove(r2);
        return;
    L6:
        externalFontMap.put(r2, new FontInfos(r3, r4));
        if (r2.equals(Character.UnicodeBlock.BASIC_LATIN) == false) goto L10;
        predefinedTeXFormulas.clear();
        return;
    }

    public static void registerFonts(boolean r0) {
        DefaultTeXFontParser.registerFonts(r0);
    }

    public static void setDPITarget(float r1) {
        PIXELS_PER_POINT = r1 / 72.0f;
    }

    public static void setDefaultDPI() {
        if (GraphicsEnvironment.isHeadless() == true) goto L6;
        setDPITarget(Toolkit.getDefaultToolkit().getScreenResolution());
        return;
    }

    public TeXFormula add(String r2) {
        if (r2 != null) goto L4;
    L6:
        return this;
    L4:
        if (r2.length() == 0) goto L6;
        this.textStyle = null;
        add(new TeXFormula(r2));
        goto L6
    }

    public TeXFormula add(Atom r3) {
        if (r3 != null) goto L4;
    L20:
        return this;
    L4:
        if ((r3 instanceof MiddleAtom) == false) goto L6;
        this.middle.add((MiddleAtom) r3);
    L6:
        Atom r0 = this.root;
        if (r0 != null) goto L11;
        this.root = r3;
        return this;
    L11:
        if ((r0 instanceof RowAtom) == true) goto L13;
        this.root = new RowAtom(this.root);
    L13:
        ((RowAtom) this.root).add(r3);
        if ((r3 instanceof TypedAtom) == false) goto L20;
        int r32 = ((TypedAtom) r3).getRightType();
        if (r32 != 2) goto L18;
    L19:
        ((RowAtom) this.root).add(new BreakMarkAtom());
        goto L20
    L18:
        if (r32 != 3) goto L20;
        goto L19
    }

    public TeXFormula add(TeXFormula r1) {
        addImpl(r1);
        return this;
    }

    public TeXFormula addStrut(int r2) {
        return add(new SpaceAtom(r2));
    }

    public TeXFormula addStrut(int r2, float r3, float r4, float r5) {
        return add(new SpaceAtom(r2, r3, r4, r5));
    }

    public TeXFormula addStrut(int r8, float r9, int r10, float r11, int r12, float r13) {
        return add(new SpaceAtom(r8, r9, r10, r11, r12, r13));
    }

    public TeXFormula append(String r2) {
        return append(false, r2);
    }

    public TeXFormula append(boolean r2, String r3) {
        if (r3 != null) goto L4;
    L6:
        return this;
    L4:
        if (r3.length() == 0) goto L6;
        new TeXParser(r2, r3, this).parse();
        goto L6
    }

    public TeXFormula centerOnAxis() {
        this.root = new VCenteredAtom(this.root);
        return this;
    }

    public TeXIcon createTeXIcon(int r2, float r3) {
        return new TeXIconBuilder(this).setStyle(r2).setSize(r3).build();
    }

    public TeXIcon createTeXIcon(int r2, float r3, int r4) {
        return new TeXIconBuilder(this).setStyle(r2).setSize(r3).setType(r4).build();
    }

    public TeXIcon createTeXIcon(int r8, float r9, int r10, float r11, int r12) {
        return createTeXIcon(r8, r9, 0, r10, r11, r12);
    }

    public TeXIcon createTeXIcon(int r10, float r11, int r12, float r13, int r14, int r15, float r16) {
        return createTeXIcon(r10, r11, 0, r12, r13, r14, r15, r16);
    }

    public TeXIcon createTeXIcon(int r2, float r3, int r4, int r5, float r6, int r7) {
        return new TeXIconBuilder(this).setStyle(r2).setSize(r3).setType(r4).setWidth(r5, r6, r7).build();
    }

    public TeXIcon createTeXIcon(int r2, float r3, int r4, int r5, float r6, int r7, int r8, float r9) {
        return new TeXIconBuilder(this).setStyle(r2).setSize(r3).setType(r4).setWidth(r5, r6, r7).setInterLineSpacing(r8, r9).build();
    }

    public TeXIcon createTeXIcon(int r2, float r3, int r4, Color r5) {
        return new TeXIconBuilder(this).setStyle(r2).setSize(r3).setType(r4).setFGColor(r5).build();
    }

    public TeXIcon createTeXIcon(int r2, float r3, boolean r4) {
        return new TeXIconBuilder(this).setStyle(r2).setSize(r3).setTrueValues(r4).build();
    }

    public boolean getLookAtLastAtom() {
        Atom r1 = this.root;
        if ((r1 instanceof RowAtom) == true) goto L5;
        return false;
    L5:
        return ((RowAtom) r1).lookAtLastAtom;
    }

    public TeXFormula setBackground(Color r4) {
        if (r4 != null) goto L4;
    L8:
        return this;
    L4:
        if ((this.root instanceof ColorAtom) == false) goto L7;
        this.root = new ColorAtom(r4, null, (ColorAtom) this.root);
        return this;
    L7:
        this.root = new ColorAtom(this.root, r4, null);
        goto L8
    }

    public TeXFormula setColor(Color r4) {
        if (r4 != null) goto L4;
    L8:
        return this;
    L4:
        if ((this.root instanceof ColorAtom) == false) goto L7;
        this.root = new ColorAtom(null, r4, (ColorAtom) this.root);
        return this;
    L7:
        this.root = new ColorAtom(this.root, null, r4);
        goto L8
    }

    public void setDEBUG(boolean r1) {
        Box.DEBUG = r1;
    }

    public TeXFormula setFixedTypes(int r3, int r4) {
        this.root = new TypedAtom(r3, r4, this.root);
        return this;
    }

    public void setLaTeX(String r2) {
        this.parser.reset(r2);
        if (r2 != null) goto L5;
        return;
    L5:
        if (r2.length() == 0) goto L9;
        this.parser.parse();
        return;
    }

    public void setLookAtLastAtom(boolean r2) {
        Atom r1 = this.root;
        if ((r1 instanceof RowAtom) == false) goto L6;
        ((RowAtom) r1).lookAtLastAtom = r2;
        return;
    }
}
