package org.scilab.forge.jlatexmath;

import java.lang.Character;
import java.util.HashSet;
import java.util.Set;
import okhttp3.internal.url._UrlKt;
import org.scilab.forge.jlatexmath.TeXFormula;
import ru.noties.jlatexmath.awt.Color;

/* loaded from: classes5.dex */
public class TeXParser {
    private static final char BACKPRIME = 8245;
    private static final char DEGRE = 176;
    private static final char DOLLAR = '$';
    private static final char DQUOTE = '\"';
    private static final char ESCAPE = '\\';
    private static final char L_BRACK = '[';
    private static final char L_GROUP = '{';
    private static final int MAX_FIRSTPASS_EXPANSIONS = 10000;
    private static final int MAX_FIRSTPASS_LENGTH = 65536;
    private static final long MAX_FIRSTPASS_WORK = 33554432;
    private static final int MAX_LASTPASS_EXPANSIONS = 10000;
    private static final int MAX_MACRO_ARGS = 256;
    private static final int MAX_PARSE_DEPTH = 64;
    private static final char PERCENT = '%';
    private static final char PRIME = '\'';
    private static final char R_BRACK = ']';
    private static final char R_GROUP = '}';
    private static final char SUBEIGHT = 8328;
    private static final char SUBEQUAL = 8332;
    private static final char SUBFIVE = 8325;
    private static final char SUBFOUR = 8324;
    private static final char SUBLPAR = 8333;
    private static final char SUBMINUS = 8331;
    private static final char SUBNINE = 8329;
    private static final char SUBONE = 8321;
    private static final char SUBPLUS = 8330;
    private static final char SUBRPAR = 8334;
    private static final char SUBSEVEN = 8327;
    private static final char SUBSIX = 8326;
    private static final char SUBTHREE = 8323;
    private static final char SUBTWO = 8322;
    private static final char SUBZERO = 8320;
    private static final char SUB_SCRIPT = '_';
    private static final char SUPEIGHT = 8312;
    private static final char SUPEQUAL = 8316;
    private static final char SUPER_SCRIPT = '^';
    private static final char SUPFIVE = 8309;
    private static final char SUPFOUR = 8308;
    private static final char SUPLPAR = 8317;
    private static final char SUPMINUS = 8315;
    private static final char SUPN = 8319;
    private static final char SUPNINE = 8313;
    private static final char SUPONE = 185;
    private static final char SUPPLUS = 8314;
    private static final char SUPRPAR = 8318;
    private static final char SUPSEVEN = 8311;
    private static final char SUPSIX = 8310;
    private static final char SUPTHREE = 179;
    private static final char SUPTWO = 178;
    private static final char SUPZERO = 8304;
    private static long firstpassExpansionWork = 0;
    protected static boolean isLoading = false;
    private static int lastpassExpansions;
    private static int parseDepth;
    private static final Set<String> unparsedContents = null;
    private boolean arrayMode;
    private int atIsLetter;
    private int col;
    TeXFormula formula;
    private int group;
    private boolean ignoreWhiteSpace;
    private boolean insertion;
    private boolean isPartial;
    private int len;
    private int line;
    private StringBuffer parseString;
    private int pos;
    private int spos;

    static {
        HashSet r0 = new HashSet(6);
        unparsedContents = r0;
        r0.add("jlmDynamic");
        r0.add("jlmText");
        r0.add("jlmTextit");
        r0.add("jlmTextbf");
        r0.add("jlmTextitbf");
        r0.add("jlmExternalFont");
    }

    public TeXParser(String r2, ArrayOfAtoms r3, boolean r4) {
        this(false, r2, r3, r4);
    }

    public TeXParser(String r2, TeXFormula r3) {
        this(r2, r3, true);
    }

    public TeXParser(String r2, TeXFormula r3, boolean r4) {
        this(false, r2, r3, r4);
    }

    public TeXParser(String r2, TeXFormula r3, boolean r4, boolean r5) {
        this(false, r2, r3, r4);
        this.ignoreWhiteSpace = r5;
    }

    public TeXParser(boolean r1, String r2, ArrayOfAtoms r3, boolean r4) {
        this(r1, r2, r3, r4);
        this.arrayMode = true;
    }

    public TeXParser(boolean r1, String r2, ArrayOfAtoms r3, boolean r4, boolean r5) {
        this(r1, r2, r3, r4, r5);
        this.arrayMode = true;
    }

    public TeXParser(boolean r2, String r3, TeXFormula r4) {
        this(r3, r4, false);
        this.isPartial = r2;
        firstpass();
    }

    public TeXParser(boolean r2, String r3, TeXFormula r4, boolean r5) {
        this.ignoreWhiteSpace = true;
        this.formula = r4;
        this.isPartial = r2;
        if (r3 == null) goto L8;
        this.parseString = new StringBuffer(r3);
        this.len = r3.length();
        this.pos = 0;
        if (r5 == false) goto L10;
        firstpass();
        return;
    L10:
        return;
    L8:
        this.parseString = null;
        this.pos = 0;
        this.len = 0;
    }

    public TeXParser(boolean r1, String r2, TeXFormula r3, boolean r4, boolean r5) {
        this(r1, r2, r3, r4);
        this.ignoreWhiteSpace = r5;
    }

    private static char convertToRomanNumber(char r2) {
        if (r2 != 1643) goto L7;
        return '.';
    L7:
        if (1632 > r2) goto L14;
        if (r2 > 1641) goto L14;
        int r22 = r2 - 1584;
    L12:
        return (char) r22;
    L14:
        if (1776 > r2) goto L19;
        if (r2 > 1785) goto L19;
        r22 = r2 - 1728;
    L19:
        if (2406 > r2) goto L24;
        if (r2 > 2415) goto L24;
        r22 = r2 - 2358;
    L24:
        if (2534 > r2) goto L29;
        if (r2 > 2543) goto L29;
        r22 = r2 - 2486;
    L29:
        if (2662 > r2) goto L34;
        if (r2 > 2671) goto L34;
        r22 = r2 - 2614;
    L34:
        if (2790 > r2) goto L39;
        if (r2 > 2799) goto L39;
        r22 = r2 - 2742;
    L39:
        if (2918 > r2) goto L44;
        if (r2 > 2927) goto L44;
        r22 = r2 - 2870;
    L44:
        if (3174 > r2) goto L49;
        if (r2 > 3183) goto L49;
        r22 = r2 - 3126;
    L49:
        if (3430 > r2) goto L54;
        if (r2 > 3439) goto L54;
        r22 = r2 - 3382;
    L54:
        if (3664 > r2) goto L59;
        if (r2 > 3673) goto L59;
        r22 = r2 - 3616;
    L59:
        if (3792 > r2) goto L64;
        if (r2 > 3801) goto L64;
        r22 = r2 - 3744;
    L64:
        if (3872 > r2) goto L69;
        if (r2 > 3881) goto L69;
        r22 = r2 - 3728;
    L69:
        if (4160 > r2) goto L74;
        if (r2 > 4169) goto L74;
        r22 = r2 - 4112;
    L74:
        if (6112 > r2) goto L79;
        if (r2 > 6121) goto L79;
        r22 = r2 - 6064;
    L79:
        if (6160 > r2) goto L84;
        if (r2 > 6169) goto L84;
        r22 = r2 - 6112;
    L84:
        if (6992 > r2) goto L89;
        if (r2 > 7001) goto L89;
        r22 = r2 - 6944;
    L89:
        if (7088 > r2) goto L94;
        if (r2 > 7097) goto L94;
        r22 = r2 - 7040;
    L94:
        if (7232 > r2) goto L99;
        if (r2 > 7241) goto L99;
        r22 = r2 - 7184;
    L99:
        if (7248 > r2) goto L104;
        if (r2 > 7257) goto L104;
        r22 = r2 - 7200;
    L104:
        if (43216 <= r2) goto L106;
    L108:
        return r2;
    L106:
        if (r2 > 43225) goto L108;
        r22 = r2 - 43168;
        goto L12
    }

    private void firstpass() {
        NewCommandMacro.reset();
        lastpassExpansions = 0;
        if (this.len == 0) goto L264;
        firstpassExpansionWork = 0;
        int r4 = 0;
    L6:
        if (this.pos >= this.len) goto L141;
        if (this.parseString.length() > 65536) goto L139;
        char r0 = this.parseString.charAt(this.pos);
        if (r0 != '%') goto L12;
        int r02 = this.pos;
        this.pos = r02 + 1;
    L129:
        int r5 = this.pos;
        if (r5 >= this.len) goto L135;
        StringBuffer r6 = this.parseString;
        this.pos = r5 + 1;
        char r52 = r6.charAt(r5);
        if (r52 == '\r') goto L135;
        if (r52 != '\n') goto L129;
    L135:
        int r53 = this.pos;
        if (r53 >= this.len) goto L138;
        this.pos = r53 - 1;
    L138:
        this.parseString.replace(r02, this.pos, _UrlKt.FRAGMENT_ENCODE_SET);
        this.len = this.parseString.length();
        this.pos = r02;
        goto L6
    L12:
        if (r0 != '\\') goto L14;
        int r54 = this.pos;
        String r7 = getCommand();
        if ("newcommand".equals(r7) == true) goto L147;
        if ("renewcommand".equals(r7) == true) goto L147;
        if (NewCommandMacro.isMacro(r7) == true) goto L64;
        if ("begin".equals(r7) == true) goto L83;
        if ("makeatletter".equals(r7) == true) goto L113;
        if ("makeatother".equals(r7) == true) goto L116;
        if (unparsedContents.contains(r7) == false) goto L6;
        getOptsArgs(1, 0);
        goto L6
    L116:
        this.atIsLetter--;
        goto L6
    L113:
        this.atIsLetter++;
        goto L6
    L83:
        String[] r03 = getOptsArgs(1, 0);
        MacroInfo r72 = MacroInfo.Commands.get(r03[1] + "@env");
        if (r72 == null) goto L86;
        r4 = r4 + 1;
        if (r4 > 10000) goto L109;
        long r11 = firstpassExpansionWork + this.len;
        firstpassExpansionWork = r11;
        if (r11 > MAX_FIRSTPASS_WORK) goto L107;
        String[] r8 = getOptsArgs(r72.nbArgs - 1, 0);     // Catch: ParseException -> L99
        String r9 = getGroup("\\begin{" + r03[1] + "}", "\\end{" + r03[1] + "}");     // Catch: ParseException -> L99
        String r04 = "{\\makeatletter \\" + r03[1] + "@env";     // Catch: ParseException -> L99
        int r10 = 1;
    L95:
        if (r10 > (r72.nbArgs - 1)) goto L101;
        r04 = r04 + "{" + r8[r10] + "}";     // Catch: ParseException -> L99
        r10 = r10 + 1;     // Catch: ParseException -> L99
        goto L95
    L101:
        this.parseString.replace(r54, this.pos, r04 + "{" + r9 + "}\\makeatother}");     // Catch: ParseException -> L99
        this.len = this.parseString.length();     // Catch: ParseException -> L99
        this.pos = r54;     // Catch: ParseException -> L99
    L99:
        e = move-exception;
        if (this.isPartial == true) goto L6;
        throw e;
    L107:
        Box$$ExternalSyntheticBUOutline0.m("Formula expands too much");
        return;
    L109:
        Box$$ExternalSyntheticBUOutline0.m("Macro expansion limit exceeded");
        return;
    L86:
        if (this.isPartial == true) goto L6;
        throw new ParseException("Unknown environment: " + r03[1] + " at position " + getLine() + ":" + getCol());
    L64:
        r4 = r4 + 1;
        if (r4 > 10000) goto L79;
        long r112 = firstpassExpansionWork + this.len;
        firstpassExpansionWork = r112;
        if (r112 > MAX_FIRSTPASS_WORK) goto L77;
        MacroInfo r05 = MacroInfo.Commands.get(r7);
        String[] r82 = getOptsArgs(r05.nbArgs, r05.hasOptions ? 1 : 0);
        r82[0] = r7;
        this.parseString.replace(r54, this.pos, (String) r05.invoke(this, r82));     // Catch: ParseException -> L71
    L75:
        this.len = this.parseString.length();
        this.pos = r54;
    L71:
        e = move-exception;
        if (this.isPartial == false) goto L76;
        r54 = r54 + (r7.length() + 1);
        goto L75
    L76:
        throw e;
    L77:
        Box$$ExternalSyntheticBUOutline0.m("Formula expands too much");
        return;
    L79:
        Box$$ExternalSyntheticBUOutline0.m("Macro expansion limit exceeded");
        return;
    L147:
        MacroInfo.Commands.get(r7).invoke(this, getOptsArgs(2, 2));     // Catch: ParseException -> L123
    L126:
        this.parseString.delete(r54, this.pos);
        this.len = this.parseString.length();
        this.pos = r54;
    L123:
        e = move-exception;
        if (this.isPartial == true) goto L126;
        throw e;
    L14:
        if (r0 != 176) goto L16;
        StringBuffer r06 = this.parseString;
        int r55 = this.pos;
        r06.replace(r55, r55 + 1, "^{\\circ}");
        this.len = this.parseString.length();
        this.pos++;
        goto L6
    L16:
        if (r0 != 185) goto L18;
        StringBuffer r07 = this.parseString;
        int r56 = this.pos;
        r07.replace(r56, r56 + 1, "\\jlatexmathcumsup{1}");
        this.len = this.parseString.length();
        this.pos++;
        goto L6
    L18:
        if (r0 != 8304) goto L20;
        StringBuffer r08 = this.parseString;
        int r57 = this.pos;
        r08.replace(r57, r57 + 1, "\\jlatexmathcumsup{0}");
        this.len = this.parseString.length();
        this.pos++;
        goto L6
    L20:
        if (r0 != 178) goto L22;
        StringBuffer r09 = this.parseString;
        int r58 = this.pos;
        r09.replace(r58, r58 + 1, "\\jlatexmathcumsup{2}");
        this.len = this.parseString.length();
        this.pos++;
        goto L6
    L22:
        if (r0 != 179) goto L23;
        StringBuffer r010 = this.parseString;
        int r59 = this.pos;
        r010.replace(r59, r59 + 1, "\\jlatexmathcumsup{3}");
        this.len = this.parseString.length();
        this.pos++;
        goto L6
    L23:
        switch(r0) {
            case 8308: goto L51;
            case 8309: goto L50;
            case 8310: goto L49;
            case 8311: goto L48;
            case 8312: goto L47;
            case 8313: goto L46;
            case 8314: goto L45;
            case 8315: goto L44;
            case 8316: goto L43;
            case 8317: goto L42;
            case 8318: goto L41;
            case 8319: goto L40;
            case 8320: goto L39;
            case 8321: goto L38;
            case 8322: goto L37;
            case 8323: goto L36;
            case 8324: goto L35;
            case 8325: goto L34;
            case 8326: goto L33;
            case 8327: goto L32;
            case 8328: goto L31;
            case 8329: goto L30;
            case 8330: goto L29;
            case 8331: goto L28;
            case 8332: goto L27;
            case 8333: goto L26;
            case 8334: goto L25;
            default: goto L24;
        };
    L25:
        StringBuffer r011 = this.parseString;
        int r510 = this.pos;
        r011.replace(r510, r510 + 1, "\\jlatexmathcumsub{)}");
        this.len = this.parseString.length();
        this.pos++;
        goto L6
    L26:
        StringBuffer r012 = this.parseString;
        int r511 = this.pos;
        r012.replace(r511, r511 + 1, "\\jlatexmathcumsub{(}");
        this.len = this.parseString.length();
        this.pos++;
        goto L6
    L27:
        StringBuffer r013 = this.parseString;
        int r512 = this.pos;
        r013.replace(r512, r512 + 1, "\\jlatexmathcumsub{=}");
        this.len = this.parseString.length();
        this.pos++;
        goto L6
    L28:
        StringBuffer r014 = this.parseString;
        int r513 = this.pos;
        r014.replace(r513, r513 + 1, "\\jlatexmathcumsub{-}");
        this.len = this.parseString.length();
        this.pos++;
        goto L6
    L29:
        StringBuffer r015 = this.parseString;
        int r514 = this.pos;
        r015.replace(r514, r514 + 1, "\\jlatexmathcumsub{+}");
        this.len = this.parseString.length();
        this.pos++;
        goto L6
    L30:
        StringBuffer r016 = this.parseString;
        int r515 = this.pos;
        r016.replace(r515, r515 + 1, "\\jlatexmathcumsub{9}");
        this.len = this.parseString.length();
        this.pos++;
        goto L6
    L31:
        StringBuffer r017 = this.parseString;
        int r516 = this.pos;
        r017.replace(r516, r516 + 1, "\\jlatexmathcumsub{8}");
        this.len = this.parseString.length();
        this.pos++;
        goto L6
    L32:
        StringBuffer r018 = this.parseString;
        int r517 = this.pos;
        r018.replace(r517, r517 + 1, "\\jlatexmathcumsub{7}");
        this.len = this.parseString.length();
        this.pos++;
        goto L6
    L33:
        StringBuffer r019 = this.parseString;
        int r518 = this.pos;
        r019.replace(r518, r518 + 1, "\\jlatexmathcumsub{6}");
        this.len = this.parseString.length();
        this.pos++;
        goto L6
    L34:
        StringBuffer r020 = this.parseString;
        int r519 = this.pos;
        r020.replace(r519, r519 + 1, "\\jlatexmathcumsub{5}");
        this.len = this.parseString.length();
        this.pos++;
        goto L6
    L35:
        StringBuffer r021 = this.parseString;
        int r520 = this.pos;
        r021.replace(r520, r520 + 1, "\\jlatexmathcumsub{4}");
        this.len = this.parseString.length();
        this.pos++;
        goto L6
    L36:
        StringBuffer r022 = this.parseString;
        int r521 = this.pos;
        r022.replace(r521, r521 + 1, "\\jlatexmathcumsub{3}");
        this.len = this.parseString.length();
        this.pos++;
        goto L6
    L37:
        StringBuffer r023 = this.parseString;
        int r522 = this.pos;
        r023.replace(r522, r522 + 1, "\\jlatexmathcumsub{2}");
        this.len = this.parseString.length();
        this.pos++;
        goto L6
    L38:
        StringBuffer r024 = this.parseString;
        int r523 = this.pos;
        r024.replace(r523, r523 + 1, "\\jlatexmathcumsub{1}");
        this.len = this.parseString.length();
        this.pos++;
        goto L6
    L39:
        StringBuffer r025 = this.parseString;
        int r524 = this.pos;
        r025.replace(r524, r524 + 1, "\\jlatexmathcumsub{0}");
        this.len = this.parseString.length();
        this.pos++;
        goto L6
    L40:
        StringBuffer r026 = this.parseString;
        int r525 = this.pos;
        r026.replace(r525, r525 + 1, "\\jlatexmathcumsup{n}");
        this.len = this.parseString.length();
        this.pos++;
        goto L6
    L41:
        StringBuffer r027 = this.parseString;
        int r526 = this.pos;
        r027.replace(r526, r526 + 1, "\\jlatexmathcumsup{)}");
        this.len = this.parseString.length();
        this.pos++;
        goto L6
    L42:
        StringBuffer r028 = this.parseString;
        int r527 = this.pos;
        r028.replace(r527, r527 + 1, "\\jlatexmathcumsup{(}");
        this.len = this.parseString.length();
        this.pos++;
        goto L6
    L43:
        StringBuffer r029 = this.parseString;
        int r528 = this.pos;
        r029.replace(r528, r528 + 1, "\\jlatexmathcumsup{=}");
        this.len = this.parseString.length();
        this.pos++;
        goto L6
    L44:
        StringBuffer r030 = this.parseString;
        int r529 = this.pos;
        r030.replace(r529, r529 + 1, "\\jlatexmathcumsup{-}");
        this.len = this.parseString.length();
        this.pos++;
        goto L6
    L45:
        StringBuffer r031 = this.parseString;
        int r530 = this.pos;
        r031.replace(r530, r530 + 1, "\\jlatexmathcumsup{+}");
        this.len = this.parseString.length();
        this.pos++;
        goto L6
    L46:
        StringBuffer r032 = this.parseString;
        int r531 = this.pos;
        r032.replace(r531, r531 + 1, "\\jlatexmathcumsup{9}");
        this.len = this.parseString.length();
        this.pos++;
        goto L6
    L47:
        StringBuffer r033 = this.parseString;
        int r532 = this.pos;
        r033.replace(r532, r532 + 1, "\\jlatexmathcumsup{8}");
        this.len = this.parseString.length();
        this.pos++;
        goto L6
    L48:
        StringBuffer r034 = this.parseString;
        int r533 = this.pos;
        r034.replace(r533, r533 + 1, "\\jlatexmathcumsup{7}");
        this.len = this.parseString.length();
        this.pos++;
        goto L6
    L49:
        StringBuffer r035 = this.parseString;
        int r534 = this.pos;
        r035.replace(r534, r534 + 1, "\\jlatexmathcumsup{6}");
        this.len = this.parseString.length();
        this.pos++;
        goto L6
    L50:
        StringBuffer r036 = this.parseString;
        int r535 = this.pos;
        r036.replace(r535, r535 + 1, "\\jlatexmathcumsup{5}");
        this.len = this.parseString.length();
        this.pos++;
        goto L6
    L51:
        StringBuffer r037 = this.parseString;
        int r536 = this.pos;
        r037.replace(r536, r536 + 1, "\\jlatexmathcumsup{4}");
        this.len = this.parseString.length();
        this.pos++;
        goto L6
    L24:
        this.pos++;
        goto L6
    L139:
        Box$$ExternalSyntheticBUOutline0.m("Formula too large after macro expansion");
        return;
    L141:
        this.pos = 0;
        this.len = this.parseString.length();
        return;
    }

    private String getCommand() {
        int r0 = this.pos + 1;
        this.pos = r0;
        char r1 = 0;
    L3:
        int r2 = this.pos;
        if (r2 >= this.len) goto L19;
        r1 = this.parseString.charAt(r2);
        if (r1 < 'a') goto L10;
        if (r1 > 'z') goto L10;
    L18:
        this.pos++;
    L10:
        if (r1 < 'A') goto L14;
        if (r1 <= 'Z') goto L18;
    L14:
        if (this.atIsLetter == 0) goto L19;
        if (r1 == '@') goto L18;
    L19:
        if (r1 != 0) goto L22;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L22:
        int r12 = this.pos;
        if (r12 != r0) goto L25;
        this.pos = r12 + 1;
    L25:
        String r02 = this.parseString.substring(r0, this.pos);
        if ("cr".equals(r02) == false) goto L32;
        int r13 = this.pos;
        if (r13 >= this.len) goto L32;
        if (this.parseString.charAt(r13) != ' ') goto L32;
        this.pos++;
    L32:
        return r02;
    }

    private String getCommandWithArgs(String r6) {
        if (r6.equals("left") == true) goto L5;
        MacroInfo r0 = MacroInfo.Commands.get(r6);
        if (r0 == null) goto L27;
        int r3 = 0;
        if (r0.hasOptions == false) goto L11;
        int r2 = r0.posOpts;
    L12:
        String[] r5 = getOptsArgs(r0.nbArgs, r2);
        StringBuffer r22 = new StringBuffer("\\");
        r22.append(r6);
        int r62 = 0;
    L14:
        if (r62 >= r0.posOpts) goto L20;
        String r1 = r5[(r0.nbArgs + r62) + 1];
        if (r1 == null) goto L18;
        r22.append("[");
        r22.append(r1);
        r22.append("]");
    L18:
        r62 = r62 + 1;
    L20:
        if (r3 >= r0.nbArgs) goto L25;
        r3 = r3 + 1;
        String r63 = r5[r3];
        if (r63 == null) goto L20;
        r22.append("{");
        r22.append(r63);
        r22.append("}");
        goto L20
    L25:
        return r22.toString();
    L11:
        r2 = 0;
        goto L12
    L27:
        return "\\".concat(r6);
    L5:
        return getGroup("\\left", "\\right");
    }

    private Atom getScripts(char r9) {
        this.pos++;
        Atom r0 = getArgument();
        int r2 = this.pos;
        if (r2 >= this.len) goto L5;
        char r22 = this.parseString.charAt(r2);
    L7:
        if (r9 != '^') goto L11;
        if (r22 != '^') goto L11;
    L9:
        Atom r92 = r0;
        r0 = null;
    L21:
        TeXFormula r8 = this.formula;
        Atom r23 = r8.root;
        if ((r23 instanceof RowAtom) == false) goto L24;
        r23 = ((RowAtom) r23).getLastAtom();
    L28:
        if (r23.getRightType() != 1) goto L32;
        return new BigOperatorAtom(r23, r0, r92);
    L32:
        if ((r23 instanceof OverUnderDelimiter) == false) goto L42;
        OverUnderDelimiter r82 = (OverUnderDelimiter) r23;
        if (r82.isOver() == false) goto L38;
        if (r92 == null) goto L42;
        r82.addScript(r92);
        return new ScriptsAtom(r23, r0, null);
    L38:
        if (r0 == null) goto L42;
        r82.addScript(r0);
        return new ScriptsAtom(r23, null, r92);
    L42:
        return new ScriptsAtom(r23, r0, r92);
    L24:
        if (r23 != null) goto L26;
        r23 = new PhantomAtom(new CharAtom('M', "mathnormal"), false, true, true);
        goto L28
    L26:
        r8.root = null;
    L11:
        if (r9 != '_') goto L14;
        if (r22 != '^') goto L14;
        this.pos++;
        r92 = getArgument();
    L14:
        if (r9 != '^') goto L17;
        if (r22 != '_') goto L17;
        this.pos++;
        r0 = getArgument();
        r92 = r0;
    L17:
        if (r9 != '^') goto L20;
        if (r22 != '_') goto L9;
    L20:
        r92 = null;
        goto L21
    L5:
        r22 = 0;
        goto L7
    }

    private void insert(int r2, int r3, String r4) {
        this.parseString.replace(r2, r3, r4);
        this.len = this.parseString.length();
        this.pos = r2;
        this.insertion = true;
    }

    private Atom processCommands(String r5) {
        MacroInfo r0 = MacroInfo.Commands.get(r5);
        if (r0.hasOptions == false) goto L5;
        int r1 = r0.posOpts;
    L6:
        String[] r12 = getOptsArgs(r0.nbArgs, r1);
        r12[0] = r5;
        if (NewCommandMacro.isMacro(r5) == false) goto L15;
        int r52 = lastpassExpansions + 1;
        lastpassExpansions = r52;
        if (r52 > 10000) goto L12;
        insert(this.spos, this.pos, (String) r0.invoke(this, r12));
        return null;
    L12:
        Box$$ExternalSyntheticBUOutline0.m("Macro expansion limit exceeded");
        return null;
    L15:
        return (Atom) r0.invoke(this, r12);
    L5:
        r1 = 0;
        goto L6
    }

    private Atom processEscape() {
        this.spos = this.pos;
        String r0 = getCommand();
        if (r0.length() != 0) goto L7;
        return new EmptyAtom();
    L7:
        if (MacroInfo.Commands.get(r0) != null) goto L9;
        return TeXFormula.get(r0).root;
    L12:
        return SymbolAtom.get(r0);
    L15:
        if (this.isPartial == true) goto L17;
        TeXParser$$ExternalSyntheticBUOutline0.m("Unknown symbol or command or predefined TeXFormula: '", r0, "'");
        return null;
    L17:
        return new ColorAtom(new RomanAtom(new TeXFormula("\\backslash ".concat(r0)).root), null, Color.RED);
    L9:
        return processCommands(r0);
    }

    private final void skipWhiteSpace() {
    L2:
        int r0 = this.pos;
        if (r0 >= this.len) goto L15;
        char r02 = this.parseString.charAt(r0);
        if (r02 != ' ') goto L7;
    L12:
        if (r02 != '\n') goto L14;
        this.line++;
        this.col = this.pos;
    L14:
        this.pos++;
        goto L2
    L7:
        if (r02 == '\t') goto L12;
        if (r02 == '\n') goto L12;
        if (r02 == '\r') goto L12;
        return;
    }

    public void addAtom(Atom r1) {
        this.formula.add(r1);
    }

    public void addRow() {
        if (this.arrayMode == false) goto L6;
        ((ArrayOfAtoms) this.formula).addRow();
        return;
    L6:
        Box$$ExternalSyntheticBUOutline0.m("You can add a row only in array mode !");
    }

    public Atom convertCharacter(char r10, boolean r11) {
        if (this.ignoreWhiteSpace == true) goto L5;
    L16:
        char r102 = convertToRomanNumber(r10);
        if (r102 < '0') goto L19;
        if (r102 > '9') goto L19;
    L24:
        TeXFormula.FontInfos r6 = TeXFormula.externalFontMap.get(Character.UnicodeBlock.BASIC_LATIN);
        if (r6 == null) goto L45;
        if (r11 == true) goto L28;
        int r103 = this.pos;
        this.pos = r103 + 1;
        int r112 = this.len - 1;
    L30:
        int r7 = this.pos;
        if (r7 >= this.len) goto L43;
        char r72 = this.parseString.charAt(r7);
        if (r72 < '0') goto L35;
        if (r72 > '9') goto L35;
    L40:
        this.pos++;
    L35:
        if (r72 < 'a') goto L37;
        if (r72 <= 'z') goto L40;
    L37:
        if (r72 < 'A') goto L41;
        if (r72 <= 'Z') goto L40;
    L41:
        r112 = this.pos - 1;
        this.pos = r112;
    L43:
        return new JavaFontRenderingAtom(this.parseString.substring(r103, r112 + 1), r6);
    L28:
        return new JavaFontRenderingAtom(Character.toString(r102), r6);
    L45:
        return new CharAtom(r102, this.formula.textStyle, this.ignoreWhiteSpace);
    L19:
        if (r102 < 'a') goto L21;
        if (r102 <= 'z') goto L24;
    L21:
        if (r102 < 'A') goto L46;
        if (r102 <= 'Z') goto L24;
    L46:
        Character.UnicodeBlock r0 = Character.UnicodeBlock.of(r102);
        if (isLoading == false) goto L49;
    L51:
        String r1 = TeXFormula.symbolMappings[r102];
        if (r1 != null) goto L84;
        String[] r2 = TeXFormula.symbolFormulaMappings;
        if (r2 != null) goto L56;
    L57:
        Character.UnicodeBlock r12 = Character.UnicodeBlock.BASIC_LATIN;
        boolean r22 = r12.equals(r0);
        if (r22 == true) goto L60;
    L61:
        if (r22 == true) goto L63;
    L62:
        TeXFormula.FontInfos r13 = TeXFormula.getExternalFont(r0);
    L64:
        if (r13 == null) goto L78;
        if (r11 == true) goto L67;
        int r104 = this.pos;
        this.pos = r104 + 1;
        int r113 = this.len - 1;
    L69:
        int r23 = this.pos;
        if (r23 >= this.len) goto L76;
        boolean r24 = Character.UnicodeBlock.of(this.parseString.charAt(r23)).equals(r0);
        int r3 = this.pos;
        if (r24 == false) goto L73;
        this.pos = r3 + 1;
        goto L69
    L73:
        r113 = r3 - 1;
        this.pos = r113;
    L76:
        return new JavaFontRenderingAtom(this.parseString.substring(r104, r113 + 1), r13);
    L67:
        return new JavaFontRenderingAtom(Character.toString(r102), r13);
    L78:
        if (this.isPartial == false) goto L82;
        return new ColorAtom(new RomanAtom(new TeXFormula("\\text{(Unknown char " + r102 + ")}").root), null, Color.RED);
    L82:
        throw new ParseException("Unknown character : '" + Character.toString(r102) + "' (or " + r102 + ")");
    L63:
        r13 = null;
        goto L64
    L60:
        if (TeXFormula.isRegisteredBlock(r12) == true) goto L62;
    L56:
        if (r2[r102] == null) goto L57;
    L84:
        if (this.ignoreWhiteSpace == true) goto L89;
        String r9 = TeXFormula.symbolTextMappings[r102];
        if (r9 == null) goto L89;
        return SymbolAtom.get(r9).setUnicode(r102);
    L89:
        String[] r92 = TeXFormula.symbolFormulaMappings;
        if (r92 != null) goto L92;
    L100:
        return SymbolAtom.get(r1);
    L97:
        e = move-exception;
        throw new ParseException("The character '" + Character.toString(r102) + "' was mapped to an unknown symbol with the name '" + r1 + "'!", e);
    L92:
        if (r92[r102] == null) goto L100;
        return new TeXFormula(TeXFormula.symbolFormulaMappings[r102]).root;
    L49:
        if (DefaultTeXFont.loadedAlphabets.contains(r0) == true) goto L51;
        DefaultTeXFont.addAlphabet(DefaultTeXFont.registeredAlphabets.get(r0));
        goto L51
    L5:
        if (r10 < 945) goto L11;
        if (r10 > 969) goto L11;
        return SymbolAtom.get(TeXFormula.symbolMappings[r10]);
    L11:
        if (r10 < 913) goto L16;
        if (r10 > 937) goto L16;
        return new TeXFormula(TeXFormula.symbolFormulaMappings[r10]).root;
    }

    public void finish() {
        this.pos = this.parseString.length();
    }

    public Atom getArgument() {
        skipWhiteSpace();
        int r0 = this.pos;
        if (r0 >= this.len) goto L22;
        char r02 = this.parseString.charAt(r0);
        if (r02 != '{') goto L13;
        TeXFormula r03 = new TeXFormula();
        TeXFormula r1 = this.formula;
        this.formula = r03;
        this.pos++;
        this.group++;
        parse();
        this.formula = r1;
        if (r1.root != null) goto L11;
        RowAtom r4 = new RowAtom();
        r4.add(r03.root);
        return r4;
    L11:
        return r03.root;
    L13:
        if (r02 != '\\') goto L19;
        Atom r04 = processEscape();
        if (this.insertion == false) goto L18;
        this.insertion = false;
        return getArgument();
    L18:
        return r04;
    L19:
        Atom r05 = convertCharacter(r02, true);
        this.pos++;
        return r05;
    L22:
        return new EmptyAtom();
    }

    public int getCol() {
        return (this.pos - this.col) - 1;
    }

    public String getDollarGroup(char r5) {
        int r0 = this.pos;
    L3:
        StringBuffer r1 = this.parseString;
        int r2 = this.pos;
        this.pos = r2 + 1;
        char r12 = r1.charAt(r2);
        if (r12 != '\\') goto L6;
        this.pos++;
    L6:
        int r22 = this.pos;
        if (r22 >= this.len) goto L9;
        if (r12 != r5) goto L3;
    L9:
        StringBuffer r4 = this.parseString;
        if (r12 != r5) goto L14;
        return r4.substring(r0, r22 - 1);
    L14:
        return r4.substring(r0, r22);
    }

    public Atom getFormulaAtom() {
        TeXFormula r2 = this.formula;
        Atom r0 = r2.root;
        r2.root = null;
        return r0;
    }

    public String getGroup(char r6, char r7) {
        int r0 = this.pos;
        if (r0 != this.len) goto L6;
        return null;
    L6:
        char r02 = this.parseString.charAt(r0);
        int r1 = this.pos;
        if (r1 >= this.len) goto L30;
        if (r02 != r6) goto L30;
        int r2 = 1;
    L10:
        int r3 = this.pos;
        if (r3 >= (this.len - 1)) goto L23;
        if (r2 == 0) goto L23;
        int r32 = r3 + 1;
        this.pos = r32;
        char r33 = this.parseString.charAt(r32);
        if (r33 == r6) goto L15;
        if (r33 == r7) goto L17;
        if (r33 != '\\') goto L10;
        int r34 = this.pos;
        if (r34 == (this.len - 1)) goto L10;
        this.pos = r34 + 1;
        goto L10
    L17:
        r2 = r2 - 1;
        goto L10
    L15:
        r2 = r2 + 1;
    L23:
        int r62 = r3 + 1;
        this.pos = r62;
        StringBuffer r5 = this.parseString;
        if (r2 == 0) goto L28;
        return r5.substring(r1 + 1, r62);
    L28:
        return r5.substring(r1 + 1, r3);
    L30:
        throw new ParseException("missing '" + r6 + "'!");
    }

    public String getGroup(String r18, String r19) {
        int r3 = r18.length();
        int r4 = r19.length();
        boolean r5 = isValidCharacterInCommand(r18.charAt(r3 - 1));
        boolean r6 = isValidCharacterInCommand(r19.charAt(r4 - 1));
        StringBuffer r7 = new StringBuffer();
        int r10 = 1;
        int r11 = 0;
        char r12 = 0;
        int r13 = 0;
        int r14 = 0;
    L3:
        int r15 = this.pos;
        if (r15 >= this.len) goto L51;
        if (r10 == 0) goto L51;
        char r8 = this.parseString.charAt(r15);
        if (r12 != '\\') goto L9;
    L20:
        r12 = r8;
    L22:
        if (r12 != r18.charAt(r13)) goto L24;
        r13 = r13 + 1;
    L26:
        if (r12 != r19.charAt(r14)) goto L30;
        if (r14 != 0) goto L29;
        r11 = this.pos;
    L29:
        r14 = r14 + 1;
    L31:
        int r82 = this.pos;
        if ((r82 + 1) >= this.len) goto L46;
        char r83 = this.parseString.charAt(r82 + 1);
        if (r13 != r3) goto L40;
        if (r5 == true) goto L37;
    L38:
        r10 = r10 + 1;
    L39:
        r13 = 0;
        goto L40
    L37:
        if (isValidCharacterInCommand(r83) == true) goto L39;
    L40:
        if (r14 != r4) goto L50;
        if (r6 == true) goto L43;
    L49:
        r10 = r10 - 1;
    L45:
        r14 = 0;
        goto L50
    L43:
        if (isValidCharacterInCommand(r83) == true) goto L45;
    L50:
        r7.append(r12);
        this.pos++;
        goto L3
    L46:
        if (r13 != r3) goto L48;
        r10 = r10 + 1;
        r13 = 0;
    L48:
        if (r14 != r4) goto L50;
    L30:
        r14 = 0;
        goto L31
    L24:
        r13 = 0;
        goto L26
    L9:
        if (r8 != ' ') goto L20;
    L10:
        int r84 = this.pos;
        if (r84 >= this.len) goto L15;
        StringBuffer r9 = this.parseString;
        this.pos = r84 + 1;
        if (r9.charAt(r84) != ' ') goto L15;
        r7.append(' ');
    L15:
        StringBuffer r85 = this.parseString;
        int r92 = this.pos - 1;
        this.pos = r92;
        r8 = r85.charAt(r92);
        if (isValidCharacterInCommand(r12) == false) goto L20;
        if (isValidCharacterInCommand(r8) == false) goto L20;
        r12 = r8;
        r13 = 0;
        r14 = 0;
    L51:
        if (r10 == 0) goto L59;
        if (this.isPartial == false) goto L57;
        return r7.toString();
    L57:
        throw new ParseException("The token " + r18 + " must be closed by " + r19);
    L59:
        return r7.substring(0, (r7.length() - this.pos) + r11);
    }

    public boolean getIsPartial() {
        return this.isPartial;
    }

    public Atom getLastAtom() {
        TeXFormula r2 = this.formula;
        Atom r0 = r2.root;
        if ((r0 instanceof RowAtom) == true) goto L5;
        r2.root = null;
        return r0;
    L5:
        return ((RowAtom) r0).getLastAtom();
    }

    public float[] getLength() {
        if (this.pos != this.len) goto L6;
        return null;
    L6:
        skipWhiteSpace();
        int r0 = this.pos;
        char r1 = 0;
    L7:
        int r2 = this.pos;
        if (r2 >= this.len) goto L12;
        if (r1 == ' ') goto L12;
        StringBuffer r12 = this.parseString;
        this.pos = r2 + 1;
        r1 = r12.charAt(r2);
    L12:
        skipWhiteSpace();
        return SpaceAtom.getLength(this.parseString.substring(r0, this.pos - 1));
    }

    public int getLine() {
        return this.line;
    }

    public String[] getOptsArgs(int r13, int r14) {
        if (r13 < 0) goto L5;
        if (r13 > 256) goto L5;
    L6:
        String[] r1 = new String[r13 + 11];
        if (r13 != 0) goto L9;
    L43:
        return r1;
    L9:
        if (r14 != 1) goto L16;
        int r6 = r13 + 1;
    L12:
        if (r6 >= (r13 + 11)) goto L16;
        skipWhiteSpace();     // Catch: ParseException -> L15
        r1[r6] = getGroup(L_BRACK, R_BRACK);     // Catch: ParseException -> L15
        r6 = r6 + 1;
    L15:
        r1[r6] = null;
    L16:
        skipWhiteSpace();
        r1[1] = getGroup(L_GROUP, R_GROUP);     // Catch: ParseException -> L19
    L23:
        int r9 = 2;
        if (r14 != 2) goto L31;
        int r142 = r13 + 1;
    L27:
        if (r142 >= (r13 + 11)) goto L31;
        skipWhiteSpace();     // Catch: ParseException -> L30
        r1[r142] = getGroup(L_BRACK, R_BRACK);     // Catch: ParseException -> L30
        r142 = r142 + 1;
    L30:
        r1[r142] = null;
    L31:
        if (r9 > r13) goto L41;
        skipWhiteSpace();
        r1[r9] = getGroup(L_GROUP, R_GROUP);     // Catch: ParseException -> L35
    L39:
        r9 = r9 + 1;
    L36:
        if (this.parseString.charAt(this.pos) == '\\') goto L38;
        r1[r9] = _UrlKt.FRAGMENT_ENCODE_SET + this.parseString.charAt(this.pos);
        this.pos = this.pos + 1;
        goto L39
    L38:
        r1[r9] = getCommandWithArgs(getCommand());
        goto L39
    L41:
        if (this.ignoreWhiteSpace == false) goto L43;
        skipWhiteSpace();
    L20:
        if (this.parseString.charAt(this.pos) == '\\') goto L22;
        r1[1] = _UrlKt.FRAGMENT_ENCODE_SET + this.parseString.charAt(this.pos);
        this.pos = this.pos + 1;
        goto L23
    L22:
        r1[1] = getCommandWithArgs(getCommand());
    L5:
        r13 = 256;
        goto L6
    }

    public String getOverArgument() {
        int r0 = this.pos;
        if (r0 != this.len) goto L6;
        return null;
    L6:
        char r1 = 0;
        char r4 = 0;
        int r3 = 1;
    L7:
        int r5 = this.pos;
        if (r5 >= this.len) goto L37;
        if (r3 == 0) goto L37;
        r4 = this.parseString.charAt(r5);
        if (r4 == '&') goto L33;
        if (r4 != '\\') goto L14;
        int r52 = this.pos + 1;
        this.pos = r52;
        if (r52 < this.len) goto L22;
    L25:
        int r53 = this.pos;
        if (r53 >= (this.len - 1)) goto L35;
        if (this.parseString.charAt(r53) != 'c') goto L35;
        if (this.parseString.charAt(this.pos + 1) != 'r') goto L35;
        if (r3 != 1) goto L35;
        r3 = r3 - 1;
        this.pos--;
    L35:
        this.pos++;
        goto L7
    L22:
        if (this.parseString.charAt(r52) != '\\') goto L25;
        if (r3 != 1) goto L25;
        r3 = r3 - 1;
        this.pos--;
        goto L35
    L14:
        if (r4 == '{') goto L18;
        if (r4 != '}') goto L35;
    L17:
        r3 = r3 - 1;
        goto L35
    L18:
        r3 = r3 + 1;
        goto L35
    L33:
        if (r3 != 1) goto L35;
    L37:
        if (r3 >= 2) goto L49;
        StringBuffer r6 = this.parseString;
        if (r3 != 0) goto L41;
        String r02 = r6.substring(r0, r5 - 1);
        r1 = r4;
    L42:
        if (r1 == '&') goto L47;
        if (r1 == '\\') goto L47;
        if (r1 == '}') goto L47;
        return r02;
    L47:
        this.pos--;
        return r02;
    L41:
        r02 = r6.substring(r0, r5);
        goto L42
    L49:
        Box$$ExternalSyntheticBUOutline0.m("Illegal end,  missing '}' !");
        return null;
    }

    public int getPos() {
        return this.pos;
    }

    public String getStringFromCurrentPos() {
        return this.parseString.substring(this.pos);
    }

    public boolean isArrayMode() {
        return this.arrayMode;
    }

    public boolean isAtLetter() {
        if (this.atIsLetter == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isIgnoreWhiteSpace() {
        return this.ignoreWhiteSpace;
    }

    public boolean isMathMode() {
        return this.ignoreWhiteSpace;
    }

    public final boolean isValidCharacterInCommand(char r2) {
        if (Character.isLetter(r2) == false) goto L5;
        return true;
    L5:
        if (this.atIsLetter != 0) goto L7;
        return false;
    L7:
        if (r2 == '@') goto L14;
        return false;
    L14:
        return true;
    }

    public final boolean isValidName(String r5) {
        char r0 = 0;
        if (r5 != null) goto L5;
    L21:
        return false;
    L5:
        if (_UrlKt.FRAGMENT_ENCODE_SET.equals(r5) == true) goto L21;
        if (r5.charAt(0) != '\\') goto L21;
        int r1 = r5.length();
        int r2 = 1;
    L10:
        if (r2 >= r1) goto L20;
        r0 = r5.charAt(r2);
        if (Character.isLetter(r0) == true) goto L18;
        if (this.atIsLetter == 0) goto L20;
        if (r0 != '@') goto L20;
    L18:
        r2 = r2 + 1;
    L20:
        return Character.isLetter(r0);
    }

    public void makeAtLetter() {
        this.atIsLetter++;
    }

    public void makeAtOther() {
        this.atIsLetter--;
    }

    public void parse() {
        int r0 = parseDepth;
        int r1 = r0 + 1;
        parseDepth = r1;
        if (r1 <= 64) goto L125;
        parseDepth = r0;
        throw new DepthLimitExceededException();
    L125:
    L40:
        th = move-exception;
        parseDepth--;
        throw th;
    L6:
        if (this.len == 0) goto L115;
    L7:
        int r12 = this.pos;     // Catch: Throwable -> L40
        if (r12 >= this.len) goto L115;
        char r13 = this.parseString.charAt(r12);     // Catch: Throwable -> L40
        if (r13 == '\t') goto L114;
        if (r13 == '\n') goto L113;
        if (r13 == '\r') goto L114;
        if (r13 != ' ') goto L19;
        this.pos++;
        if (this.ignoreWhiteSpace == true) goto L7;
        this.formula.add(new SpaceAtom());     // Catch: Throwable -> L40
        this.formula.add(new BreakMarkAtom());     // Catch: Throwable -> L40
    L105:
        int r14 = this.pos;     // Catch: Throwable -> L40
        if (r14 >= this.len) goto L7;
        char r15 = this.parseString.charAt(r14);     // Catch: Throwable -> L40
        if (r15 != ' ') goto L7;
        if (r15 != '\t') goto L7;
        if (r15 != '\r') goto L7;
        this.pos++;
        goto L105
    L19:
        if (r13 != '\"') goto L21;
        boolean r16 = this.ignoreWhiteSpace;     // Catch: Throwable -> L40
        TeXFormula r2 = this.formula;
        if (r16 == false) goto L100;
        r2.add(new CumulativeScriptsAtom(getLastAtom(), null, SymbolAtom.get("prime")));     // Catch: Throwable -> L40
        this.formula.add(new CumulativeScriptsAtom(getLastAtom(), null, SymbolAtom.get("prime")));     // Catch: Throwable -> L40
    L101:
        this.pos++;
        goto L7
    L100:
        r2.add(convertCharacter(PRIME, true));     // Catch: Throwable -> L40
        this.formula.add(convertCharacter(PRIME, true));     // Catch: Throwable -> L40
        goto L101
    L21:
        if (r13 != '$') goto L23;
        int r17 = this.pos + 1;     // Catch: Throwable -> L40
        this.pos = r17;     // Catch: Throwable -> L40
        if (this.ignoreWhiteSpace == true) goto L7;
        if (this.parseString.charAt(r17) != '$') goto L90;
        this.pos++;
        boolean r3 = true;
        int r18 = 0;
    L91:
        this.formula.add(new MathAtom(new TeXFormula(this, getDollarGroup('$'), false).root, r18));     // Catch: Throwable -> L40
        if (r3 == false) goto L7;
        if (this.parseString.charAt(this.pos) != '$') goto L7;
        this.pos++;
        goto L7
    L90:
        r18 = 2;
        r3 = false;
        goto L91
    L23:
        if (r13 != '\\') goto L25;
        Atom r19 = processEscape();     // Catch: Throwable -> L40
        this.formula.add(r19);     // Catch: Throwable -> L40
        if (this.arrayMode == false) goto L83;
        if ((r19 instanceof HlineAtom) == false) goto L83;
        ((ArrayOfAtoms) this.formula).addRow();     // Catch: Throwable -> L40
    L83:
        if (this.insertion == false) goto L7;
        this.insertion = false;     // Catch: Throwable -> L40
        goto L7
    L25:
        if (r13 != '{') goto L27;
        Atom r110 = getArgument();     // Catch: Throwable -> L40
        if (r110 == null) goto L76;
        r110.type = 0;     // Catch: Throwable -> L40
    L76:
        this.formula.add(r110);     // Catch: Throwable -> L40
        goto L7
    L27:
        if (r13 != 8245) goto L29;
        boolean r111 = this.ignoreWhiteSpace;     // Catch: Throwable -> L40
        TeXFormula r32 = this.formula;
        if (r111 == false) goto L71;
        r32.add(new CumulativeScriptsAtom(getLastAtom(), null, SymbolAtom.get("backprime")));     // Catch: Throwable -> L40
    L72:
        this.pos++;
        goto L7
    L71:
        r32.add(convertCharacter(BACKPRIME, true));     // Catch: Throwable -> L40
        goto L72
    L29:
        if (r13 != '&') goto L30;
        if (this.arrayMode == false) goto L66;
        ((ArrayOfAtoms) this.formula).addCol();     // Catch: Throwable -> L40
        this.pos++;
        goto L7
    L66:
        throw new ParseException("Character '&' is only available in array mode !");     // Catch: Throwable -> L40
    L30:
        if (r13 != '\'') goto L32;
        boolean r112 = this.ignoreWhiteSpace;     // Catch: Throwable -> L40
        TeXFormula r22 = this.formula;
        if (r112 == false) goto L60;
        r22.add(new CumulativeScriptsAtom(getLastAtom(), null, SymbolAtom.get("prime")));     // Catch: Throwable -> L40
    L61:
        this.pos++;
        goto L7
    L60:
        r22.add(convertCharacter(PRIME, true));     // Catch: Throwable -> L40
        goto L61
    L32:
        if (r13 != '^') goto L34;
        this.formula.add(getScripts(r13));     // Catch: Throwable -> L40
        goto L7
    L34:
        if (r13 != '_') goto L36;
        boolean r23 = this.ignoreWhiteSpace;     // Catch: Throwable -> L40
        TeXFormula r33 = this.formula;
        if (r23 == true) goto L53;
        r33.add(new UnderscoreAtom());     // Catch: Throwable -> L40
        this.pos++;
        goto L7
    L53:
        r33.add(getScripts(r13));     // Catch: Throwable -> L40
        goto L7
    L36:
        if (r13 == '}') goto L43;
        TeXFormula r24 = this.formula;
        if (r13 != '~') goto L39;
        r24.add(new SpaceAtom());     // Catch: Throwable -> L40
        this.pos++;
        goto L7
    L39:
        r24.add(convertCharacter(r13, false));     // Catch: Throwable -> L40
        this.pos++;
        goto L7
    L43:
        int r113 = this.group - 1;     // Catch: Throwable -> L40
        this.group = r113;     // Catch: Throwable -> L40
        this.pos++;
        if (r113 == (-1)) goto L49;
    L46:
        parseDepth--;
        return;
    L49:
        throw new ParseException("Found a closing '}' without an opening '{'!");     // Catch: Throwable -> L40
    L113:
        this.line++;
        this.col = this.pos;     // Catch: Throwable -> L40
    L114:
        this.pos++;
    L115:
        TeXFormula r114 = this.formula;     // Catch: Throwable -> L40
        if (r114.root != null) goto L46;
        if (this.arrayMode == true) goto L46;
        r114.add(new EmptyAtom());     // Catch: Throwable -> L40
        goto L46
    }

    public void reset(String r2) {
        StringBuffer r0 = new StringBuffer(r2);
        this.parseString = r0;
        this.len = r0.length();
        this.formula.root = null;
        this.pos = 0;
        this.spos = 0;
        this.line = 0;
        this.col = 0;
        this.group = 0;
        this.insertion = false;
        this.atIsLetter = 0;
        this.arrayMode = false;
        this.ignoreWhiteSpace = true;
        firstpass();
    }

    public int rewind(int r2) {
        int r0 = this.pos - r2;
        this.pos = r0;
        return r0;
    }

    public void setArrayMode(boolean r1) {
        this.arrayMode = r1;
    }
}
