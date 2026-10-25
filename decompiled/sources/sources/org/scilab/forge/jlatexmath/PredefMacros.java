package org.scilab.forge.jlatexmath;

import com.google.android.gms.cast.MediaError;
import java.lang.Character;
import java.util.Map;
import java.util.StringTokenizer;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.dynamic.DynamicAtom;
import ru.noties.jlatexmath.awt.Color;

/* loaded from: classes5.dex */
public class PredefMacros {
    static {
        NewEnvironmentMacro.addNewEnvironment("array", "\\array@@env{#1}{", "}", 1);
        NewEnvironmentMacro.addNewEnvironment("tabular", "\\array@@env{#1}{", "}", 1);
        NewEnvironmentMacro.addNewEnvironment("matrix", "\\matrix@@env{", "}", 0);
        NewEnvironmentMacro.addNewEnvironment("smallmatrix", "\\smallmatrix@@env{", "}", 0);
        NewEnvironmentMacro.addNewEnvironment("pmatrix", "\\left(\\begin{matrix}", "\\end{matrix}\\right)", 0);
        NewEnvironmentMacro.addNewEnvironment("bmatrix", "\\left[\\begin{matrix}", "\\end{matrix}\\right]", 0);
        NewEnvironmentMacro.addNewEnvironment("Bmatrix", "\\left\\{\\begin{matrix}", "\\end{matrix}\\right\\}", 0);
        NewEnvironmentMacro.addNewEnvironment("vmatrix", "\\left|\\begin{matrix}", "\\end{matrix}\\right|", 0);
        NewEnvironmentMacro.addNewEnvironment("Vmatrix", "\\left\\|\\begin{matrix}", "\\end{matrix}\\right\\|", 0);
        NewEnvironmentMacro.addNewEnvironment("eqnarray", "\\begin{array}{rcl}", "\\end{array}", 0);
        NewEnvironmentMacro.addNewEnvironment("align", "\\align@@env{", "}", 0);
        NewEnvironmentMacro.addNewEnvironment("flalign", "\\flalign@@env{", "}", 0);
        NewEnvironmentMacro.addNewEnvironment("alignat", "\\alignat@@env{#1}{", "}", 1);
        NewEnvironmentMacro.addNewEnvironment("aligned", "\\aligned@@env{", "}", 0);
        NewEnvironmentMacro.addNewEnvironment("alignedat", "\\alignedat@@env{#1}{", "}", 1);
        NewEnvironmentMacro.addNewEnvironment("multline", "\\multline@@env{", "}", 0);
        NewEnvironmentMacro.addNewEnvironment("cases", "\\left\\{\\begin{array}{l@{\\!}l}", "\\end{array}\\right.", 0);
        NewEnvironmentMacro.addNewEnvironment("split", "\\begin{array}{rl}", "\\end{array}", 0);
        NewEnvironmentMacro.addNewEnvironment("gather", "\\gather@@env{", "}", 0);
        NewEnvironmentMacro.addNewEnvironment("gathered", "\\gathered@@env{", "}", 0);
        NewEnvironmentMacro.addNewEnvironment("math", "\\(", "\\)", 0);
        NewEnvironmentMacro.addNewEnvironment("displaymath", "\\[", "\\]", 0);
        NewCommandMacro.addNewCommand("operatorname", "\\mathop{\\mathrm{#1}}\\nolimits ", 1);
        NewCommandMacro.addNewCommand("DeclareMathOperator", "\\newcommand{#1}{\\mathop{\\mathrm{#2}}\\nolimits}", 2);
        NewCommandMacro.addNewCommand("substack", "{\\scriptstyle\\begin{array}{c}#1\\end{array}}", 1);
        NewCommandMacro.addNewCommand("dfrac", "\\genfrac{}{}{}{}{#1}{#2}", 2);
        NewCommandMacro.addNewCommand("tfrac", "\\genfrac{}{}{}{1}{#1}{#2}", 2);
        NewCommandMacro.addNewCommand("dbinom", "\\genfrac{(}{)}{0pt}{}{#1}{#2}", 2);
        NewCommandMacro.addNewCommand("tbinom", "\\genfrac{(}{)}{0pt}{1}{#1}{#2}", 2);
        NewCommandMacro.addNewCommand("pmod", "\\qquad\\mathbin{(\\mathrm{mod}\\ #1)}", 1);
        NewCommandMacro.addNewCommand("mod", "\\qquad\\mathbin{\\mathrm{mod}\\ #1}", 1);
        NewCommandMacro.addNewCommand("pod", "\\qquad\\mathbin{(#1)}", 1);
        NewCommandMacro.addNewCommand("dddot", "\\mathop{#1}\\limits^{...}", 1);
        NewCommandMacro.addNewCommand("ddddot", "\\mathop{#1}\\limits^{....}", 1);
        NewCommandMacro.addNewCommand("spdddot", "^{\\mathrm{...}}", 0);
        NewCommandMacro.addNewCommand("spbreve", "^{\\makeatletter\\sp@breve\\makeatother}", 0);
        NewCommandMacro.addNewCommand("sphat", "^{\\makeatletter\\sp@hat\\makeatother}", 0);
        NewCommandMacro.addNewCommand("spddot", "^{\\displaystyle..}", 0);
        NewCommandMacro.addNewCommand("spcheck", "^{\\vee}", 0);
        NewCommandMacro.addNewCommand("sptilde", "^{\\sim}", 0);
        NewCommandMacro.addNewCommand("spdot", "^{\\displaystyle.}", 0);
        NewCommandMacro.addNewCommand("d", "\\underaccent{\\dot}{#1}", 1);
        NewCommandMacro.addNewCommand("b", "\\underaccent{\\bar}{#1}", 1);
        NewCommandMacro.addNewCommand("Bra", "\\left\\langle{#1}\\right\\vert", 1);
        NewCommandMacro.addNewCommand("Ket", "\\left\\vert{#1}\\right\\rangle", 1);
        NewCommandMacro.addNewCommand("textsuperscript", "{}^{\\text{#1}}", 1);
        NewCommandMacro.addNewCommand("textsubscript", "{}_{\\text{#1}}", 1);
        NewCommandMacro.addNewCommand("textit", "\\mathit{\\text{#1}}", 1);
        NewCommandMacro.addNewCommand("textbf", "\\mathbf{\\text{#1}}", 1);
        NewCommandMacro.addNewCommand("textsf", "\\mathsf{\\text{#1}}", 1);
        NewCommandMacro.addNewCommand("texttt", "\\mathtt{\\text{#1}}", 1);
        NewCommandMacro.addNewCommand("textrm", "\\text{#1}", 1);
        NewCommandMacro.addNewCommand("degree", "^\\circ", 0);
        NewCommandMacro.addNewCommand("with", "\\mathbin{\\&}", 0);
        NewCommandMacro.addNewCommand("parr", "\\mathbin{\\rotatebox[origin=c]{180}{\\&}}", 0);
        NewCommandMacro.addNewCommand("copyright", "\\textcircled{\\raisebox{0.2ex}{c}}", 0);
        NewCommandMacro.addNewCommand("L", "\\mathrm{\\polishlcross L}", 0);
        NewCommandMacro.addNewCommand("l", "\\mathrm{\\polishlcross l}", 0);
        NewCommandMacro.addNewCommand("Join", "\\mathop{\\rlap{\\ltimes}\\rtimes}", 0);
    }

    public PredefMacros() {
    }

    public static final Atom Big_macro(TeXParser r2, String[] r3) {
        Atom r22 = new TeXFormula(r2, r3[1], false).root;
        if ((r22 instanceof SymbolAtom) == true) goto L6;
        return r22;
    L6:
        return new BigDelimiterAtom((SymbolAtom) r22, 2);
    }

    public static final Atom Bigg_macro(TeXParser r2, String[] r3) {
        Atom r22 = new TeXFormula(r2, r3[1], false).root;
        if ((r22 instanceof SymbolAtom) == true) goto L6;
        return r22;
    L6:
        return new BigDelimiterAtom((SymbolAtom) r22, 4);
    }

    public static final Atom Biggl_macro(TeXParser r2, String[] r3) {
        Atom r22 = new TeXFormula(r2, r3[1], false).root;
        if ((r22 instanceof SymbolAtom) == true) goto L5;
        return r22;
    L5:
        BigDelimiterAtom r32 = new BigDelimiterAtom((SymbolAtom) r22, 4);
        r32.type = 4;
        return r32;
    }

    public static final Atom Biggr_macro(TeXParser r2, String[] r3) {
        Atom r22 = new TeXFormula(r2, r3[1], false).root;
        if ((r22 instanceof SymbolAtom) == true) goto L5;
        return r22;
    L5:
        BigDelimiterAtom r32 = new BigDelimiterAtom((SymbolAtom) r22, 4);
        r32.type = 5;
        return r32;
    }

    public static final Atom Bigl_macro(TeXParser r2, String[] r3) {
        Atom r22 = new TeXFormula(r2, r3[1], false).root;
        if ((r22 instanceof SymbolAtom) == true) goto L5;
        return r22;
    L5:
        BigDelimiterAtom r32 = new BigDelimiterAtom((SymbolAtom) r22, 2);
        r32.type = 4;
        return r32;
    }

    public static final Atom Bigr_macro(TeXParser r2, String[] r3) {
        Atom r22 = new TeXFormula(r2, r3[1], false).root;
        if ((r22 instanceof SymbolAtom) == true) goto L5;
        return r22;
    L5:
        BigDelimiterAtom r32 = new BigDelimiterAtom((SymbolAtom) r22, 2);
        r32.type = 5;
        return r32;
    }

    public static final Atom Braket_macro(TeXParser r3, String[] r4) {
        return new TeXFormula(r3, "\\left\\langle " + r4[1].replaceAll("\\|", "\\\\middle\\\\vert ") + "\\right\\rangle").root;
    }

    public static final Atom DeclareMathSizes_macro(TeXParser r3, String[] r4) {
        DefaultTeXFont.setMathSizes(Float.parseFloat(r4[1]), Float.parseFloat(r4[2]), Float.parseFloat(r4[3]), Float.parseFloat(r4[4]));
        return null;
    }

    public static final Atom Dstrok_macro(TeXParser r4, String[] r5) {
        RowAtom r52 = new RowAtom(new SpaceAtom(1, -0.1f, 0.0f, 0.0f));
        r52.add(SymbolAtom.get("bar"));
        VRowAtom r0 = new VRowAtom(new LapedAtom(r52, 'r'));
        r0.setRaise(1, -0.55f);
        RowAtom r53 = new RowAtom(r0);
        r53.add(new RomanAtom(new CharAtom('D', r4.formula.textStyle)));
        return r53;
    }

    public static final Atom GeoGebra_macro(TeXParser r2, String[] r3) {
        TeXFormula r22 = new TeXFormula("\\mathbb{G}\\mathsf{e}");
        r22.add(new GeoGebraLogoAtom());
        r22.add("\\mathsf{Gebra}");
        return new ColorAtom(r22.root, null, new Color(102, 102, 102));
    }

    public static final Atom Hstrok_macro(TeXParser r4, String[] r5) {
        RowAtom r52 = new RowAtom(new SpaceAtom(1, 0.28f, 0.0f, 0.0f));
        r52.add(SymbolAtom.get("textendash"));
        VRowAtom r0 = new VRowAtom(new LapedAtom(r52, 'r'));
        r0.setRaise(1, 0.55f);
        RowAtom r53 = new RowAtom(r0);
        r53.add(new RomanAtom(new CharAtom('H', r4.formula.textStyle)));
        return r53;
    }

    public static final Atom IJ_macro(TeXParser r2, String[] r3) {
        boolean r0 = false;
        if (r3[0].charAt(0) != 'I') goto L6;
        r0 = true;
    L6:
        return new IJAtom(r0);
    }

    public static final Atom LCaron_macro(TeXParser r2, String[] r3) {
        boolean r0 = false;
        if (r3[0].charAt(0) != 'L') goto L6;
        r0 = true;
    L6:
        return new LCaronAtom(r0);
    }

    public static final Atom LaTeX_macro(TeXParser r0, String[] r1) {
        return new LaTeXAtom();
    }

    public static final Atom Set_macro(TeXParser r3, String[] r4) {
        return new TeXFormula(r3, "\\left\\{" + r4[1].replaceFirst("\\|", "\\\\middle\\\\vert ") + "\\right\\}").root;
    }

    public static final Atom TStroke_macro(TeXParser r2, String[] r3) {
        boolean r0 = false;
        if (r3[0].charAt(0) != 'T') goto L6;
        r0 = true;
    L6:
        return new TStrokeAtom(r0);
    }

    public static final Atom T_macro(TeXParser r3, String[] r4) {
        return new RotateAtom(new TeXFormula(r3, r4[1]).root, 180.0d, "origin=cc");
    }

    public static final Atom above_macro(TeXParser r5, String[] r6) {
        Atom r62 = r5.getFormulaAtom();
        float[] r0 = r5.getLength();
        Atom r52 = new TeXFormula(r5, r5.getOverArgument(), false).root;
        if (r0 != null) goto L5;
    L12:
        Box$$ExternalSyntheticBUOutline0.m("Invalid length in above macro");
        return null;
    L5:
        if (r0.length != 2) goto L12;
        if (r62 == null) goto L10;
        if (r52 == null) goto L10;
        return new FractionAtom(r62, r52, (int) r0[0], r0[1]);
    L10:
        Box$$ExternalSyntheticBUOutline0.m("Both numerator and denominator of a fraction can't be empty!");
        return null;
    }

    public static final Atom abovewithdelims_macro(TeXParser r8, String[] r9) {
        Atom r0 = r8.getFormulaAtom();
        float[] r1 = r8.getLength();
        Atom r2 = new TeXFormula(r8, r8.getOverArgument(), false).root;
        if (r1 != null) goto L5;
    L24:
        Box$$ExternalSyntheticBUOutline0.m("Invalid length in above macro");
        return null;
    L5:
        if (r1.length != 2) goto L24;
        if (r0 == null) goto L22;
        if (r2 == null) goto L22;
        Atom r3 = new TeXFormula(r8, r9[1], false).root;
        if ((r3 instanceof BigDelimiterAtom) == false) goto L11;
        r3 = ((BigDelimiterAtom) r3).delim;
    L11:
        Atom r82 = new TeXFormula(r8, r9[2], false).root;
        if ((r82 instanceof BigDelimiterAtom) == false) goto L15;
        r82 = ((BigDelimiterAtom) r82).delim;
    L15:
        if ((r3 instanceof SymbolAtom) == true) goto L17;
    L20:
        RowAtom r92 = new RowAtom();
        r92.add(r3);
        r92.add(new FractionAtom(r0, r2, true));
        r92.add(r82);
        return r92;
    L17:
        if ((r82 instanceof SymbolAtom) == false) goto L20;
        return new FencedAtom(new FractionAtom(r0, r2, (int) r1[0], r1[1]), (SymbolAtom) r3, (SymbolAtom) r82);
    L22:
        Box$$ExternalSyntheticBUOutline0.m("Both numerator and denominator of a fraction can't be empty!");
        return null;
    }

    public static final Atom accent_macro(TeXParser r5, String[] r6) {
        return new AccentedAtom(new TeXFormula(r5, r6[2], false).root, new TeXFormula(r5, r6[1], false).root);
    }

    public static final Atom accent_macros(TeXParser r4, String[] r5) {
        return new AccentedAtom(new TeXFormula(r4, r5[1], false).root, r5[0]);
    }

    public static final Atom accentbis_macros(TeXParser r5, String[] r6) {
        char r1 = r6[0].charAt(0);
        if (r1 != '\"') goto L5;
        String r12 = "ddot";
    L38:
        return new AccentedAtom(new TeXFormula(r5, r6[1], false).root, r12);
    L5:
        if (r1 != '\'') goto L7;
        r12 = "acute";
        goto L38
    L7:
        if (r1 != '.') goto L9;
        r12 = "dot";
        goto L38
    L9:
        if (r1 != '=') goto L11;
        r12 = "bar";
        goto L38
    L11:
        if (r1 != 'H') goto L13;
        r12 = "doubleacute";
        goto L38
    L13:
        if (r1 != 'U') goto L15;
        r12 = "cyrbreve";
        goto L38
    L15:
        if (r1 != '^') goto L17;
        r12 = "hat";
        goto L38
    L17:
        if (r1 != '`') goto L19;
        r12 = "grave";
        goto L38
    L19:
        if (r1 != 'r') goto L21;
        r12 = "mathring";
        goto L38
    L21:
        if (r1 == '~') goto L27;
        switch(r1) {
            case 116: goto L26;
            case 117: goto L25;
            case 118: goto L24;
            default: goto L23;
        };
    L23:
        r12 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L38
    L24:
        r12 = "check";
        goto L38
    L25:
        r12 = "breve";
        goto L38
    L26:
        r12 = "tie";
        goto L38
    L27:
        r12 = "tilde";
        goto L38
    }

    public static final Atom accentset_macro(TeXParser r5, String[] r6) {
        return new AccentedAtom(new TeXFormula(r5, r6[2], false).root, new TeXFormula(r5, r6[1], false).root);
    }

    public static final Atom alignATATenv_macro(TeXParser r4, String[] r5) {
        ArrayOfAtoms r0 = new ArrayOfAtoms();
        new TeXParser(r4.getIsPartial(), r5[1], r0, false).parse();
        r0.checkDimensions();
        return new MatrixAtom(r4.getIsPartial(), r0, 2);
    }

    public static final Atom alignatATATenv_macro(TeXParser r6, String[] r7) {
        ArrayOfAtoms r0 = new ArrayOfAtoms();
        new TeXParser(r6.getIsPartial(), r7[2], r0, false).parse();
        r0.checkDimensions();
        int r72 = Integer.parseInt(r7[1]);
        if (r0.col == (r72 * 2)) goto L5;
        Box$$ExternalSyntheticBUOutline0.m("Bad number of equations in alignat environment !");
        return null;
    L5:
        return new MatrixAtom(r6.getIsPartial(), r0, 3);
    }

    public static final Atom alignedATATenv_macro(TeXParser r4, String[] r5) {
        ArrayOfAtoms r0 = new ArrayOfAtoms();
        new TeXParser(r4.getIsPartial(), r5[1], r0, false).parse();
        r0.checkDimensions();
        return new MatrixAtom(r4.getIsPartial(), r0, 6);
    }

    public static final Atom alignedatATATenv_macro(TeXParser r6, String[] r7) {
        ArrayOfAtoms r0 = new ArrayOfAtoms();
        new TeXParser(r6.getIsPartial(), r7[2], r0, false).parse();
        r0.checkDimensions();
        int r72 = Integer.parseInt(r7[1]);
        if (r0.col == (r72 * 2)) goto L5;
        Box$$ExternalSyntheticBUOutline0.m("Bad number of equations in alignedat environment !");
        return null;
    L5:
        return new MatrixAtom(r6.getIsPartial(), r0, 7);
    }

    public static final Atom approxcolon_macro(TeXParser r10, String[] r11) {
        RowAtom r102 = new RowAtom(SymbolAtom.get("approx"));
        r102.add(new SpaceAtom(0, -0.095f, 0.0f, 0.0f));
        r102.add(new UnderOverAtom(SymbolAtom.get("normaldot"), SymbolAtom.get("normaldot"), 5, 5.2f, false, true));
        return new TypedAtom(3, 3, r102);
    }

    public static final Atom approxcoloncolon_macro(TeXParser r10, String[] r11) {
        RowAtom r102 = new RowAtom(SymbolAtom.get("approx"));
        r102.add(new SpaceAtom(0, -0.095f, 0.0f, 0.0f));
        UnderOverAtom r3 = new UnderOverAtom(SymbolAtom.get("normaldot"), SymbolAtom.get("normaldot"), 5, 5.2f, false, true);
        r102.add(r3);
        r102.add(r3);
        return new TypedAtom(3, 3, r102);
    }

    public static final Atom arrayATATenv_macro(TeXParser r5, String[] r6) {
        ArrayOfAtoms r0 = new ArrayOfAtoms();
        new TeXParser(r5.getIsPartial(), r6[2], r0, false).parse();
        r0.checkDimensions();
        return new MatrixAtom(r5.getIsPartial(), r0, r6[1], true);
    }

    public static final Atom atop_macro(TeXParser r3, String[] r4) {
        Atom r42 = r3.getFormulaAtom();
        Atom r32 = new TeXFormula(r3, r3.getOverArgument(), false).root;
        if (r42 == null) goto L7;
        if (r32 == null) goto L7;
        return new FractionAtom(r42, r32, false);
    L7:
        Box$$ExternalSyntheticBUOutline0.m("Both numerator and denominator of a fraction can't be empty!");
        return null;
    }

    public static final Atom atopwithdelims_macro(TeXParser r6, String[] r7) {
        Atom r0 = r6.getFormulaAtom();
        Atom r1 = new TeXFormula(r6, r6.getOverArgument(), false).root;
        if (r0 == null) goto L19;
        if (r1 == null) goto L19;
        Atom r2 = new TeXFormula(r6, r7[1], false).root;
        if ((r2 instanceof BigDelimiterAtom) == false) goto L8;
        r2 = ((BigDelimiterAtom) r2).delim;
    L8:
        Atom r62 = new TeXFormula(r6, r7[2], false).root;
        if ((r62 instanceof BigDelimiterAtom) == false) goto L12;
        r62 = ((BigDelimiterAtom) r62).delim;
    L12:
        if ((r2 instanceof SymbolAtom) == true) goto L14;
    L17:
        RowAtom r72 = new RowAtom();
        r72.add(r2);
        r72.add(new FractionAtom(r0, r1, false));
        r72.add(r62);
        return r72;
    L14:
        if ((r62 instanceof SymbolAtom) == false) goto L17;
        return new FencedAtom(new FractionAtom(r0, r1, false), (SymbolAtom) r2, (SymbolAtom) r62);
    L19:
        Box$$ExternalSyntheticBUOutline0.m("Both numerator and denominator of a fraction can't be empty!");
        return null;
    }

    public static final Atom backslashcr_macro(TeXParser r0, String[] r1) {
        return cr_macro(r0, r1);
    }

    public static final Atom bangle_macro(TeXParser r2, String[] r3) {
        return choose_brackets("langle", "rangle", r2, r3);
    }

    public static final Atom bf_macro(TeXParser r7, String[] r8) {
        return new BoldAtom(new RomanAtom(new TeXFormula(r7, r7.getOverArgument(), null, false, r7.isIgnoreWhiteSpace()).root));
    }

    public static final Atom bgcolor_macro(TeXParser r3, String[] r4) {
        return new ColorAtom(new TeXFormula(r3, r4[2]).root, ColorAtom.getColor(r4[1]), null);
    L4:
        e = move-exception;
        throw new ParseException(e.toString());
    }

    public static final Atom big_macro(TeXParser r3, String[] r4) {
        Atom r32 = new TeXFormula(r3, r4[1], false).root;
        if ((r32 instanceof SymbolAtom) == true) goto L6;
        return r32;
    L6:
        return new BigDelimiterAtom((SymbolAtom) r32, 1);
    }

    public static final Atom bigg_macro(TeXParser r2, String[] r3) {
        Atom r22 = new TeXFormula(r2, r3[1], false).root;
        if ((r22 instanceof SymbolAtom) == true) goto L6;
        return r22;
    L6:
        return new BigDelimiterAtom((SymbolAtom) r22, 3);
    }

    public static final Atom biggl_macro(TeXParser r2, String[] r3) {
        Atom r22 = new TeXFormula(r2, r3[1], false).root;
        if ((r22 instanceof SymbolAtom) == true) goto L5;
        return r22;
    L5:
        BigDelimiterAtom r32 = new BigDelimiterAtom((SymbolAtom) r22, 3);
        r32.type = 4;
        return r32;
    }

    public static final Atom biggr_macro(TeXParser r2, String[] r3) {
        Atom r22 = new TeXFormula(r2, r3[1], false).root;
        if ((r22 instanceof SymbolAtom) == true) goto L5;
        return r22;
    L5:
        BigDelimiterAtom r32 = new BigDelimiterAtom((SymbolAtom) r22, 3);
        r32.type = 5;
        return r32;
    }

    public static final Atom bigl_macro(TeXParser r3, String[] r4) {
        Atom r32 = new TeXFormula(r3, r4[1], false).root;
        if ((r32 instanceof SymbolAtom) == true) goto L5;
        return r32;
    L5:
        BigDelimiterAtom r42 = new BigDelimiterAtom((SymbolAtom) r32, 1);
        r42.type = 4;
        return r42;
    }

    public static final Atom bigr_macro(TeXParser r3, String[] r4) {
        Atom r32 = new TeXFormula(r3, r4[1], false).root;
        if ((r32 instanceof SymbolAtom) == true) goto L5;
        return r32;
    L5:
        BigDelimiterAtom r42 = new BigDelimiterAtom((SymbolAtom) r32, 1);
        r42.type = 5;
        return r42;
    }

    public static final Atom binom_macro(TeXParser r5, String[] r6) {
        TeXFormula r0 = new TeXFormula(r5, r6[1], false);
        TeXFormula r2 = new TeXFormula(r5, r6[2], false);
        Atom r52 = r0.root;
        if (r52 == null) goto L8;
        Atom r62 = r2.root;
        if (r62 == null) goto L8;
        return new FencedAtom(new FractionAtom(r52, r62, false), new SymbolAtom("lbrack", 4, true), new SymbolAtom("rbrack", 5, true));
    L8:
        Box$$ExternalSyntheticBUOutline0.m("Both binomial coefficients must be not empty !!");
        return null;
    }

    public static final Atom boldsymbol_macro(TeXParser r3, String[] r4) {
        return new BoldAtom(new TeXFormula(r3, r4[1], false).root);
    }

    public static final Atom brace_macro(TeXParser r2, String[] r3) {
        return choose_brackets("lbrace", "rbrace", r2, r3);
    }

    public static final Atom brack_macro(TeXParser r2, String[] r3) {
        return choose_brackets("lsqbrack", "rsqbrack", r2, r3);
    }

    public static final Atom cedilla_macro(TeXParser r3, String[] r4) {
        return new CedillaAtom(new TeXFormula(r3, r4[1]).root);
    }

    public static final Atom cfrac_macro(TeXParser r11, String[] r12) {
        if ("r".equals(r12[3]) == false) goto L6;
        int r9 = 1;
    L9:
        TeXFormula r0 = new TeXFormula(r11, r12[1], false);
        TeXFormula r1 = new TeXFormula(r11, r12[2], false);
        Atom r6 = r0.root;
        if (r6 == null) goto L15;
        Atom r7 = r1.root;
        if (r7 == null) goto L15;
        FractionAtom r5 = new FractionAtom(r6, r7, true, r9, 2);
        RowAtom r112 = new RowAtom();
        r112.add(new StyleAtom(0, r5));
        return r112;
    L15:
        Box$$ExternalSyntheticBUOutline0.m("Both numerator and denominator of a fraction can't be empty!");
        return null;
    L6:
        if ("l".equals(r12[3]) == false) goto L8;
        r9 = 0;
        goto L9
    L8:
        r9 = 2;
        goto L9
    }

    public static final Atom char_macro(TeXParser r3, String[] r4) {
        String r42 = r4[1];
        int r2 = 16;
        if (r42.startsWith("0x") == false) goto L5;
    L17:
        r42 = r42.substring(2);
    L19:
        return r3.convertCharacter((char) Integer.parseInt(r42, r2), true);
    L5:
        if (r42.startsWith("0X") == true) goto L17;
        if (r42.startsWith("x") == false) goto L10;
    L16:
        r42 = r42.substring(1);
        goto L19
    L10:
        if (r42.startsWith("X") == true) goto L16;
        if (r42.startsWith(MVEL.VERSION_SUB) == false) goto L15;
        r42 = r42.substring(1);
        r2 = 8;
        goto L19
    L15:
        r2 = 10;
        goto L19
    }

    public static final Atom choose_brackets(String r3, String r4, TeXParser r5, String[] r6) {
        Atom r62 = r5.getFormulaAtom();
        Atom r52 = new TeXFormula(r5, r5.getOverArgument(), false).root;
        if (r62 == null) goto L7;
        if (r52 == null) goto L7;
        return new FencedAtom(new FractionAtom(r62, r52, false), new SymbolAtom(r3, 4, true), new SymbolAtom(r4, 5, true));
    L7:
        Box$$ExternalSyntheticBUOutline0.m("Both numerator and denominator of choose can't be empty!");
        return null;
    }

    public static final Atom choose_macro(TeXParser r2, String[] r3) {
        return choose_brackets("lbrack", "rbrack", r2, r3);
    }

    public static final Atom clrlap_macro(TeXParser r3, String[] r4) {
        return new LapedAtom(new TeXFormula(r3, r4[1]).root, r4[0].charAt(0));
    }

    public static final Atom colonapprox_macro(TeXParser r7, String[] r8) {
        RowAtom r72 = new RowAtom(new UnderOverAtom(SymbolAtom.get("normaldot"), SymbolAtom.get("normaldot"), 5, 5.2f, false, true));
        r72.add(new SpaceAtom(0, -0.32f, 0.0f, 0.0f));
        r72.add(SymbolAtom.get("approx"));
        return new TypedAtom(3, 3, r72);
    }

    public static final Atom coloncolon_macro(TeXParser r7, String[] r8) {
        UnderOverAtom r0 = new UnderOverAtom(SymbolAtom.get("normaldot"), SymbolAtom.get("normaldot"), 5, 5.2f, false, true);
        RowAtom r72 = new RowAtom(r0);
        r72.add(r0);
        return new TypedAtom(3, 3, r72);
    }

    public static final Atom coloncolonapprox_macro(TeXParser r7, String[] r8) {
        UnderOverAtom r0 = new UnderOverAtom(SymbolAtom.get("normaldot"), SymbolAtom.get("normaldot"), 5, 5.2f, false, true);
        RowAtom r72 = new RowAtom(r0);
        r72.add(r0);
        r72.add(new SpaceAtom(0, -0.32f, 0.0f, 0.0f));
        r72.add(SymbolAtom.get("approx"));
        return new TypedAtom(3, 3, r72);
    }

    public static final Atom coloncolonequals_macro(TeXParser r7, String[] r8) {
        UnderOverAtom r0 = new UnderOverAtom(SymbolAtom.get("normaldot"), SymbolAtom.get("normaldot"), 5, 5.2f, false, true);
        RowAtom r72 = new RowAtom(r0);
        r72.add(r0);
        r72.add(new SpaceAtom(0, -0.32f, 0.0f, 0.0f));
        r72.add(SymbolAtom.get("equals"));
        return new TypedAtom(3, 3, r72);
    }

    public static final Atom coloncolonminus_macro(TeXParser r7, String[] r8) {
        UnderOverAtom r0 = new UnderOverAtom(SymbolAtom.get("normaldot"), SymbolAtom.get("normaldot"), 5, 5.2f, false, true);
        RowAtom r72 = new RowAtom(r0);
        r72.add(r0);
        r72.add(new SpaceAtom(0, -0.32f, 0.0f, 0.0f));
        r72.add(SymbolAtom.get("minus"));
        return new TypedAtom(3, 3, r72);
    }

    public static final Atom coloncolonsim_macro(TeXParser r7, String[] r8) {
        UnderOverAtom r0 = new UnderOverAtom(SymbolAtom.get("normaldot"), SymbolAtom.get("normaldot"), 5, 5.2f, false, true);
        RowAtom r72 = new RowAtom(r0);
        r72.add(r0);
        r72.add(new SpaceAtom(0, -0.32f, 0.0f, 0.0f));
        r72.add(SymbolAtom.get("sim"));
        return new TypedAtom(3, 3, r72);
    }

    public static final Atom colonequals_macro(TeXParser r7, String[] r8) {
        RowAtom r72 = new RowAtom(new UnderOverAtom(SymbolAtom.get("normaldot"), SymbolAtom.get("normaldot"), 5, 5.2f, false, true));
        r72.add(new SpaceAtom(0, -0.32f, 0.0f, 0.0f));
        r72.add(SymbolAtom.get("equals"));
        return new TypedAtom(3, 3, r72);
    }

    public static final Atom colonminus_macro(TeXParser r7, String[] r8) {
        RowAtom r72 = new RowAtom(new UnderOverAtom(SymbolAtom.get("normaldot"), SymbolAtom.get("normaldot"), 5, 5.2f, false, true));
        r72.add(new SpaceAtom(0, -0.32f, 0.0f, 0.0f));
        r72.add(SymbolAtom.get("minus"));
        return new TypedAtom(3, 3, r72);
    }

    public static final Atom colonsim_macro(TeXParser r7, String[] r8) {
        RowAtom r72 = new RowAtom(new UnderOverAtom(SymbolAtom.get("normaldot"), SymbolAtom.get("normaldot"), 5, 5.2f, false, true));
        r72.add(new SpaceAtom(0, -0.32f, 0.0f, 0.0f));
        r72.add(SymbolAtom.get("sim"));
        return new TypedAtom(3, 3, r72);
    }

    public static final Atom colorbox_macro(TeXParser r4, String[] r5) {
        Color r0 = ColorAtom.getColor(r5[1]);
        return new FBoxAtom(new TeXFormula(r4, r5[2]).root, r0, r0);
    }

    public static final Atom cong_macro(TeXParser r3, String[] r4) {
        VRowAtom r32 = new VRowAtom(SymbolAtom.get("equals"));
        r32.add(new SpaceAtom(5, 0.0f, 1.5f, 0.0f));
        r32.add(SymbolAtom.get("sim"));
        r32.setRaise(5, -1.0f);
        return new TypedAtom(3, 3, r32);
    }

    public static final Atom cr_macro(TeXParser r6, String[] r7) {
        if (r6.isArrayMode() == false) goto L5;
        r6.addRow();
        return null;
    L5:
        ArrayOfAtoms r3 = new ArrayOfAtoms();
        r3.add(r6.formula.root);
        r3.addRow();
        new TeXParser(r6.getIsPartial(), r6.getStringFromCurrentPos(), r3, false, r6.isIgnoreWhiteSpace()).parse();
        r3.checkDimensions();
        r6.finish();
        TeXFormula r62 = r6.formula;
        r62.root = r3.getAsVRow();
        return null;
    }

    public static final Atom ddots_macro(TeXParser r1, String[] r2) {
        return new TypedAtom(7, 7, new DdotsAtom());
    }

    public static final Atom definecolor_macro(TeXParser r9, String[] r10) {
        if ("gray".equals(r10[2]) == false) goto L6;
        float r92 = Float.parseFloat(r10[3]);
        Color r0 = new Color(r92, r92, r92);
    L20:
        ColorAtom.Colors.put(r10[1], r0);
        return null;
    L6:
        if ("rgb".equals(r10[2]) == false) goto L13;
        StringTokenizer r93 = new StringTokenizer(r10[3], ";,");
        if (r93.countTokens() != 3) goto L10;
        r0 = new Color(Float.parseFloat(r93.nextToken().trim()), Float.parseFloat(r93.nextToken().trim()), Float.parseFloat(r93.nextToken().trim()));
        goto L20
    L10:
        Box$$ExternalSyntheticBUOutline0.m("The color definition must have three components !");
        return null;
    L13:
        if ("cmyk".equals(r10[2]) == false) goto L24;
        StringTokenizer r02 = new StringTokenizer(r10[3], ",;");
        if (r02.countTokens() != 4) goto L22;
        float[] r4 = new float[4];
        int r7 = 0;
    L17:
        if (r7 >= 4) goto L19;
        r4[r7] = Float.parseFloat(r02.nextToken().trim());
        r7 = r7 + 1;
        goto L17
    L19:
        float r03 = 1.0f - r4[3];
        r0 = new Color((1.0f - r4[0]) * r03, (1.0f - r4[1]) * r03, r03 * (1.0f - r4[2]));
        goto L20
    L22:
        Box$$ExternalSyntheticBUOutline0.m("The color definition must have four components !");
        return null;
    L24:
        Box$$ExternalSyntheticBUOutline0.m("The color model is incorrect !");
        return null;
    }

    public static final Atom displaystyle_macro(TeXParser r2, String[] r3) {
        return new StyleAtom(0, new TeXFormula(r2, r2.getOverArgument(), false).root);
    }

    public static final Atom doteq_macro(TeXParser r7, String[] r8) {
        return new TypedAtom(3, 3, new UnderOverAtom(SymbolAtom.get("equals"), SymbolAtom.get("ldotp"), 5, 3.7f, false, true));
    }

    public static final Atom dotminus_macro(TeXParser r7, String[] r8) {
        return new TypedAtom(2, 2, new UnderOverAtom(SymbolAtom.get("minus"), SymbolAtom.get("normaldot"), 5, -3.3f, false, true));
    }

    public static final Atom doublebox_macro(TeXParser r3, String[] r4) {
        return new DoubleFramedAtom(new TeXFormula(r3, r4[1]).root);
    }

    public static final Atom dstrok_macro(TeXParser r4, String[] r5) {
        RowAtom r52 = new RowAtom(new SpaceAtom(1, 0.25f, 0.0f, 0.0f));
        r52.add(SymbolAtom.get("bar"));
        VRowAtom r0 = new VRowAtom(new LapedAtom(r52, 'r'));
        r0.setRaise(1, -0.1f);
        RowAtom r53 = new RowAtom(r0);
        r53.add(new RomanAtom(new CharAtom('d', r4.formula.textStyle)));
        return r53;
    }

    public static final Atom equalscolon_macro(TeXParser r10, String[] r11) {
        RowAtom r102 = new RowAtom(SymbolAtom.get("equals"));
        r102.add(new SpaceAtom(0, -0.095f, 0.0f, 0.0f));
        r102.add(new UnderOverAtom(SymbolAtom.get("normaldot"), SymbolAtom.get("normaldot"), 5, 5.2f, false, true));
        return new TypedAtom(3, 3, r102);
    }

    public static final Atom equalscoloncolon_macro(TeXParser r10, String[] r11) {
        RowAtom r102 = new RowAtom(SymbolAtom.get("equals"));
        r102.add(new SpaceAtom(0, -0.095f, 0.0f, 0.0f));
        UnderOverAtom r3 = new UnderOverAtom(SymbolAtom.get("normaldot"), SymbolAtom.get("normaldot"), 5, 5.2f, false, true);
        r102.add(r3);
        r102.add(r3);
        return new TypedAtom(3, 3, r102);
    }

    public static final Atom fbox_macro(TeXParser r3, String[] r4) {
        return new FBoxAtom(new TeXFormula(r3, r4[1], false).root);
    }

    public static final Atom fcolorbox_macro(TeXParser r3, String[] r4) {
        return new FBoxAtom(new TeXFormula(r3, r4[3]).root, ColorAtom.getColor(r4[2]), ColorAtom.getColor(r4[1]));
    }

    public static final Atom fcscore_macro(TeXParser r4, String[] r5) {
        int r42 = Integer.parseInt(r5[1]);
        if (r42 <= 4096) goto L6;
        r42 = 4096;
    L6:
        if (r42 <= 5) goto L13;
        int r0 = r42 / 5;
        int r43 = r42 % 5;
        RowAtom r1 = new RowAtom();
        int r2 = 0;
    L8:
        if (r2 >= r0) goto L10;
        r1.add(new FcscoreAtom(5));
        r2 = r2 + 1;
        goto L8
    L10:
        r1.add(new FcscoreAtom(r43));
        return r1;
    L13:
        return new FcscoreAtom(r42);
    }

    public static final Atom fgcolor_macro(TeXParser r3, String[] r4) {
        return new ColorAtom(new TeXFormula(r3, r4[2]).root, null, ColorAtom.getColor(r4[1]));
    L4:
        e = move-exception;
        throw new ParseException(e.toString());
    }

    public static final Atom flalignATATenv_macro(TeXParser r4, String[] r5) {
        ArrayOfAtoms r0 = new ArrayOfAtoms();
        new TeXParser(r4.getIsPartial(), r5[1], r0, false).parse();
        r0.checkDimensions();
        return new MatrixAtom(r4.getIsPartial(), r0, 4);
    }

    public static final Atom frac_macro(TeXParser r5, String[] r6) {
        TeXFormula r0 = new TeXFormula(r5, r6[1], false);
        TeXFormula r2 = new TeXFormula(r5, r6[2], false);
        Atom r52 = r0.root;
        if (r52 == null) goto L8;
        Atom r62 = r2.root;
        if (r62 == null) goto L8;
        return new FractionAtom(r52, r62, true);
    L8:
        Box$$ExternalSyntheticBUOutline0.m("Both numerator and denominator of a fraction can't be empty!");
        return null;
    }

    public static final Atom gatherATATenv_macro(TeXParser r5, String[] r6) {
        ArrayOfAtoms r0 = new ArrayOfAtoms();
        new TeXParser(r5.getIsPartial(), r6[1], r0, false).parse();
        r0.checkDimensions();
        int r62 = r0.col;
        if (r62 > 1) goto L8;
        if (r62 != 0) goto L7;
        return null;
    L7:
        return new MultlineAtom(r5.getIsPartial(), r0, 1);
    L8:
        Box$$ExternalSyntheticBUOutline0.m("Character '&' is only available in array mode !");
        return null;
    }

    public static final Atom gatheredATATenv_macro(TeXParser r5, String[] r6) {
        ArrayOfAtoms r0 = new ArrayOfAtoms();
        new TeXParser(r5.getIsPartial(), r6[1], r0, false).parse();
        r0.checkDimensions();
        int r62 = r0.col;
        if (r62 > 1) goto L8;
        if (r62 != 0) goto L7;
        return null;
    L7:
        return new MultlineAtom(r5.getIsPartial(), r0, 2);
    L8:
        Box$$ExternalSyntheticBUOutline0.m("Character '&' is only available in array mode !");
        return null;
    }

    public static final Atom genfrac_macro(TeXParser r14, String[] r15) {
        Atom r0 = new TeXFormula(r14, r15[1], false).root;
        if ((r0 instanceof SymbolAtom) == false) goto L5;
        SymbolAtom r02 = (SymbolAtom) r0;
    L6:
        Atom r2 = new TeXFormula(r14, r15[2], false).root;
        if ((r2 instanceof SymbolAtom) == false) goto L9;
        SymbolAtom r22 = (SymbolAtom) r2;
    L10:
        float[] r7 = SpaceAtom.getLength(r15[3]);
        String r6 = r15[3];
        if (r6 != null) goto L13;
    L18:
        r7 = new float[]{0.0f, 0.0f};
        boolean r11 = false;
    L20:
        if (r15[4].length() == 0) goto L22;
        int r62 = Integer.parseInt(r15[4]);
    L23:
        TeXFormula r8 = new TeXFormula(r14, r15[5], false);
        TeXFormula r9 = new TeXFormula(r14, r15[6], false);
        Atom r142 = r8.root;
        if (r142 == null) goto L29;
        Atom r10 = r9.root;
        if (r10 == null) goto L29;
        FractionAtom r82 = new FractionAtom(r142, r10, r11, (int) r7[0], r7[1]);
        RowAtom r143 = new RowAtom();
        r143.add(new StyleAtom(r62 * 2, new FencedAtom(r82, r02, r22)));
        return r143;
    L29:
        Box$$ExternalSyntheticBUOutline0.m("Both numerator and denominator of a fraction can't be empty!");
        return null;
    L22:
        r62 = 0;
        goto L23
    L13:
        if (r6.length() == 0) goto L18;
        if (r7.length == 1) goto L18;
        r11 = true;
        goto L20
    L9:
        r22 = null;
        goto L10
    L5:
        r02 = null;
        goto L6
    }

    public static final Atom geoprop_macro(TeXParser r10, String[] r11) {
        RowAtom r2 = new RowAtom(SymbolAtom.get("normaldot"));
        r2.add(new SpaceAtom(5, 4.0f, 0.0f, 0.0f));
        r2.add(SymbolAtom.get("normaldot"));
        return new TypedAtom(3, 3, new UnderOverAtom(SymbolAtom.get("minus"), r2, 5, -3.4f, false, r2, 5, -3.4f, false));
    }

    public static final Atom grkaccent_macro(TeXParser r5, String[] r6) {
        return new AccentedAtom(new TeXFormula(r5, r6[2], false).root, new TeXFormula(r5, r6[1], false).root, false);
    }

    public static final Atom hdotsfor_macro(TeXParser r2, String[] r3) {
        int r0 = 1;
        int r1 = Integer.parseInt(r3[1]);
        if (r1 < 1) goto L9;
        r0 = 4096;
        if (r1 > 4096) goto L9;
        r0 = r1;
    L9:
        String r32 = r3[2];
        if (r32 == null) goto L12;
        float r33 = Float.parseFloat(r32);
    L13:
        r2.addAtom(new HdotsforAtom(r0, r33));
        ((ArrayOfAtoms) r2.formula).addCol(r0);
        return null;
    L12:
        r33 = 1.0f;
        goto L13
    }

    public static final Atom hline_macro(TeXParser r0, String[] r1) {
        if (r0.isArrayMode() == true) goto L5;
        Box$$ExternalSyntheticBUOutline0.m("The macro \\hline is only available in array mode !");
        return null;
    L5:
        return new HlineAtom();
    }

    public static final Atom hphantom_macro(TeXParser r4, String[] r5) {
        return new PhantomAtom(new TeXFormula(r4, r5[1], false).root, true, false, false);
    }

    public static final Atom hstrok_macro(TeXParser r5, String[] r6) {
        RowAtom r62 = new RowAtom(new SpaceAtom(1, -0.1f, 0.0f, 0.0f));
        r62.add(SymbolAtom.get("bar"));
        VRowAtom r0 = new VRowAtom(new LapedAtom(r62, 'r'));
        r0.setRaise(1, -0.1f);
        RowAtom r63 = new RowAtom(r0);
        r63.add(new RomanAtom(new CharAtom('h', r5.formula.textStyle)));
        return r63;
    }

    public static final Atom hvspace_macro(TeXParser r5, String[] r6) {
        int r0 = 0;
    L4:
        if (r0 < r6[1].length()) goto L6;
    L26:
        float r2 = Float.parseFloat(r6[1].substring(0, r0));     // Catch: NumberFormatException -> L23
        if (r0 == r6[1].length()) goto L12;
        int r3 = SpaceAtom.getUnit(r6[1].substring(r0).toLowerCase());
    L14:
        if (r3 == (-1)) goto L22;
        if (r6[0].charAt(0) != 'h') goto L20;
        return new SpaceAtom(r3, r2, 0.0f, 0.0f);
    L20:
        return new SpaceAtom(r3, 0.0f, r2, 0.0f);
    L22:
        throw new ParseException("Unknown unit \"" + r6[1].substring(r0) + "\" !");
    L12:
        r3 = 3;
    L23:
        e = move-exception;
        throw new ParseException(e.toString());
    L6:
        if (Character.isLetter(r6[1].charAt(r0)) == true) goto L26;
        r0 = r0 + 1;
        goto L4
    }

    public static final Atom iddots_macro(TeXParser r1, String[] r2) {
        return new TypedAtom(7, 7, new IddotsAtom());
    }

    public static final Atom idotsint_macro(TeXParser r7, String[] r8) {
        Atom r72 = SymbolAtom.get("int").m3846clone();
        r72.type_limits = 1;
        RowAtom r0 = new RowAtom(r72);
        r0.add(new SpaceAtom(5, -1.0f, 0.0f, 0.0f));
        SymbolAtom r1 = SymbolAtom.get("cdotp");
        RowAtom r5 = new RowAtom(r1);
        r5.add(r1);
        r5.add(r1);
        r0.add(new TypedAtom(7, 7, r5));
        r0.add(new SpaceAtom(5, -1.0f, 0.0f, 0.0f));
        r0.add(r72);
        r0.lookAtLastAtom = true;
        return new TypedAtom(1, 1, r0);
    }

    public static final Atom iiiint_macro(TeXParser r5, String[] r6) {
        Atom r52 = SymbolAtom.get("int").m3846clone();
        r52.type_limits = 1;
        RowAtom r0 = new RowAtom(r52);
        r0.add(new SpaceAtom(5, -6.0f, 0.0f, 0.0f));
        r0.add(r52);
        r0.add(new SpaceAtom(5, -6.0f, 0.0f, 0.0f));
        r0.add(r52);
        r0.add(new SpaceAtom(5, -6.0f, 0.0f, 0.0f));
        r0.add(r52);
        r0.lookAtLastAtom = true;
        return new TypedAtom(1, 1, r0);
    }

    public static final Atom iiint_macro(TeXParser r5, String[] r6) {
        Atom r52 = SymbolAtom.get("int").m3846clone();
        r52.type_limits = 1;
        RowAtom r0 = new RowAtom(r52);
        r0.add(new SpaceAtom(5, -6.0f, 0.0f, 0.0f));
        r0.add(r52);
        r0.add(new SpaceAtom(5, -6.0f, 0.0f, 0.0f));
        r0.add(r52);
        r0.lookAtLastAtom = true;
        return new TypedAtom(1, 1, r0);
    }

    public static final Atom iint_macro(TeXParser r5, String[] r6) {
        Atom r52 = SymbolAtom.get("int").m3846clone();
        r52.type_limits = 1;
        RowAtom r0 = new RowAtom(r52);
        r0.add(new SpaceAtom(5, -6.0f, 0.0f, 0.0f));
        r0.add(r52);
        r0.lookAtLastAtom = true;
        return new TypedAtom(1, 1, r0);
    }

    public static final Atom includegraphics_macro(TeXParser r2, String[] r3) {
        return new GraphicsAtom(r3[1], r3[2]);
    }

    public static final Atom insertBreakMark_macro(TeXParser r0, String[] r1) {
        return new BreakMarkAtom();
    }

    public static final Atom int_macro(TeXParser r0, String[] r1) {
        Atom r02 = SymbolAtom.get("int").m3846clone();
        r02.type_limits = 1;
        return r02;
    }

    public static final Atom intertext_macro(TeXParser r9, String[] r10) {
        if (r9.isArrayMode() == false) goto L6;
        RomanAtom r102 = new RomanAtom(new TeXFormula(r9, r10[1].replaceAll("\\^\\{\\\\prime\\}", "'").replaceAll("\\^\\{\\\\prime\\\\prime\\}", "''"), "mathnormal", false, false).root);
        r102.type = 11;
        r9.addAtom(r102);
        r9.addRow();
        return null;
    L6:
        Box$$ExternalSyntheticBUOutline0.m("Bad environment for \\intertext command !");
        return null;
    }

    public static final Atom it_macro(TeXParser r6, String[] r7) {
        return new ItAtom(new TeXFormula(r6, r6.getOverArgument(), null, false, r6.isIgnoreWhiteSpace()).root);
    }

    public static final Atom jlatexmathcumsub_macro(TeXParser r4, String[] r5) {
        return new CumulativeScriptsAtom(r4.getLastAtom(), new TeXFormula(r4, r5[1]).root, null);
    }

    public static final Atom jlatexmathcumsup_macro(TeXParser r4, String[] r5) {
        return new CumulativeScriptsAtom(r4.getLastAtom(), null, new TeXFormula(r4, r5[1]).root);
    }

    public static final Atom jlmDynamic_macro(TeXParser r2, String[] r3) {
        if (DynamicAtom.hasAnExternalConverterFactory() == true) goto L5;
        Box$$ExternalSyntheticBUOutline0.m("No ExternalConverterFactory set !");
        return null;
    L5:
        return new DynamicAtom(r3[1], r3[2]);
    }

    public static final Atom jlmExternalFont_macro(TeXParser r0, String[] r1) {
        JavaFontRenderingBox.setFont(r1[1]);
        return null;
    }

    public static final Atom jlmText_macro(TeXParser r1, String[] r2) {
        return new JavaFontRenderingAtom(r2[1], 0);
    }

    public static final Atom jlmTextbf_macro(TeXParser r1, String[] r2) {
        return new JavaFontRenderingAtom(r2[1], 1);
    }

    public static final Atom jlmTextit_macro(TeXParser r1, String[] r2) {
        return new JavaFontRenderingAtom(r2[1], 2);
    }

    public static final Atom jlmTextitbf_macro(TeXParser r1, String[] r2) {
        return new JavaFontRenderingAtom(r2[1], 3);
    }

    public static final Atom jlmXML_macro(TeXParser r7, String[] r8) {
        Map<String, String> r0 = r7.formula.jlmXMLMap;
        String r82 = r8[1];
        StringBuffer r2 = new StringBuffer();
    L3:
        int r3 = r82.indexOf("$");
        if (r3 == (-1)) goto L19;
        if (r3 < (r82.length() - 1)) goto L7;
        r2.append(r82);
        r82 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L3
    L7:
        int r4 = r3;
    L8:
        r4 = r4 + 1;
        if (r4 >= r82.length()) goto L13;
        if (Character.isLetter(r82.charAt(r4)) == true) goto L8;
    L13:
        String r5 = r0.get(r82.substring(r3 + 1, r4));
        if (r5 == null) goto L16;
        r2.append(r82.substring(0, r3));
        r2.append(r5);
    L17:
        r82 = r82.substring(r4);
        goto L3
    L16:
        r2.append(r82.substring(0, r4));
        goto L17
    L19:
        r2.append(r82);
        return new TeXFormula(r7, r2.toString()).root;
    }

    public static final Atom joinrel_macro(TeXParser r3, String[] r4) {
        return new TypedAtom(3, 3, new SpaceAtom(5, -2.6f, 0.0f, 0.0f));
    }

    public static final Atom kern_macro(TeXParser r2, String[] r3) {
        float[] r32 = SpaceAtom.getLength(r3[1]);
        if (r32.length != 1) goto L5;
        Box$$ExternalSyntheticBUOutline0.m("Error in getting kern in \\kern command !");
        return null;
    L5:
        return new SpaceAtom((int) r32[0], r32[1], 0.0f, 0.0f);
    }

    public static final Atom left_macro(TeXParser r4, String[] r5) {
        String r0 = r4.getGroup("\\left", "\\right");
        Atom r52 = new TeXFormula(r4, r5[1], false).root;
        if ((r52 instanceof BigDelimiterAtom) == false) goto L5;
        r52 = ((BigDelimiterAtom) r52).delim;
    L5:
        Atom r1 = r4.getArgument();
        if ((r1 instanceof BigDelimiterAtom) == false) goto L9;
        r1 = ((BigDelimiterAtom) r1).delim;
    L9:
        if ((r52 instanceof SymbolAtom) == true) goto L11;
    L14:
        RowAtom r3 = new RowAtom();
        r3.add(r52);
        r3.add(new TeXFormula(r4, r0, false).root);
        r3.add(r1);
        return r3;
    L11:
        if ((r1 instanceof SymbolAtom) == false) goto L14;
        TeXFormula r32 = new TeXFormula(r4, r0, false);
        return new FencedAtom(r32.root, (SymbolAtom) r52, r32.middle, (SymbolAtom) r1);
    }

    public static final Atom leftbracket_macro(TeXParser r3, String[] r4) {
        return new MathAtom(new TeXFormula(r3, r3.getGroup("\\[", "\\]"), false).root, 0);
    }

    public static final Atom leftparenthesis_macro(TeXParser r3, String[] r4) {
        return new MathAtom(new TeXFormula(r3, r3.getGroup("\\(", "\\)"), false).root, 2);
    }

    public static final Atom limits_macro(TeXParser r0, String[] r1) {
        Atom r02 = r0.getLastAtom();
        r02.type_limits = 2;
        return r02.m3846clone();
    }

    public static final Atom lmoustache_macro(TeXParser r1, String[] r2) {
        BigDelimiterAtom r12 = new BigDelimiterAtom((SymbolAtom) SymbolAtom.get("lmoustache").m3846clone(), 1);
        r12.type = 4;
        return r12;
    }

    public static final Atom longdiv_macro(TeXParser r7, String[] r8) {
        long r0 = Long.valueOf(r8[1]).longValue();     // Catch: NumberFormatException -> L19
        long r72 = Long.valueOf(r8[2]).longValue();     // Catch: NumberFormatException -> L19
        if (r72 == 0) goto L18;
        if (r0 > 1000000000) goto L16;
        if (r0 < (-1000000000)) goto L16;
        if (r72 > 1000000000) goto L16;
        if (r72 < (-1000000000)) goto L16;
        return new LongdivAtom(r72, r0);
    L16:
        throw new ParseException("Operands are too large for longdiv");     // Catch: NumberFormatException -> L19
    L18:
        throw new ParseException("Divisor must not be 0");     // Catch: NumberFormatException -> L19
    L19:
        Box$$ExternalSyntheticBUOutline0.m("Divisor and dividend must be integer numbers");
        return null;
    }

    public static final Atom magnification_macro(TeXParser r0, String[] r1) {
        DefaultTeXFont.setMagnification(Float.parseFloat(r1[1]));
        return null;
    }

    public static final Atom makeatletter_macro(TeXParser r0, String[] r1) {
        r0.makeAtLetter();
        return null;
    }

    public static final Atom makeatother_macro(TeXParser r0, String[] r1) {
        r0.makeAtOther();
        return null;
    }

    public static final Atom mathbf_macro(TeXParser r4, String[] r5) {
        return new BoldAtom(new RomanAtom(new TeXFormula(r4, r5[1], false).root));
    }

    public static final Atom mathbin_macro(TeXParser r3, String[] r4) {
        return new TypedAtom(2, 2, new TeXFormula(r3, r4[1], false).root);
    }

    public static final Atom mathclose_macro(TeXParser r3, String[] r4) {
        return new TypedAtom(5, 5, new TeXFormula(r3, r4[1], false).root);
    }

    public static final Atom mathclrlap_macro(TeXParser r3, String[] r4) {
        return new LapedAtom(new TeXFormula(r3, r4[1]).root, r4[0].charAt(4));
    }

    public static final Atom mathinner_macro(TeXParser r3, String[] r4) {
        return new TypedAtom(7, 7, new TeXFormula(r3, r4[1], false).root);
    }

    public static final Atom mathit_macro(TeXParser r3, String[] r4) {
        return new ItAtom(new TeXFormula(r3, r4[1], false).root);
    }

    public static final Atom mathop_macro(TeXParser r4, String[] r5) {
        TypedAtom r0 = new TypedAtom(1, 1, new TeXFormula(r4, r5[1], false).root);
        r0.type_limits = 0;
        return r0;
    }

    public static final Atom mathopen_macro(TeXParser r3, String[] r4) {
        return new TypedAtom(4, 4, new TeXFormula(r3, r4[1], false).root);
    }

    public static final Atom mathord_macro(TeXParser r3, String[] r4) {
        return new TypedAtom(0, 0, new TeXFormula(r3, r4[1], false).root);
    }

    public static final Atom mathpunct_macro(TeXParser r3, String[] r4) {
        return new TypedAtom(6, 6, new TeXFormula(r3, r4[1], false).root);
    }

    public static final Atom mathrel_macro(TeXParser r3, String[] r4) {
        return new TypedAtom(3, 3, new TeXFormula(r3, r4[1], false).root);
    }

    public static final Atom mathrm_macro(TeXParser r3, String[] r4) {
        return new RomanAtom(new TeXFormula(r3, r4[1], false).root);
    }

    public static final Atom mathsf_macro(TeXParser r3, String[] r4) {
        return new SsAtom(new TeXFormula(r3, r4[1], false).root);
    }

    public static final Atom mathtt_macro(TeXParser r3, String[] r4) {
        return new TtAtom(new TeXFormula(r3, r4[1], false).root);
    }

    public static final Atom matrixATATenv_macro(TeXParser r5, String[] r6) {
        ArrayOfAtoms r0 = new ArrayOfAtoms();
        new TeXParser(r5.getIsPartial(), r6[1], r0, false).parse();
        r0.checkDimensions();
        return new MatrixAtom(r5.getIsPartial(), r0, 1);
    }

    public static final Atom mbox_macro(TeXParser r7, String[] r8) {
        return new StyleAtom(2, new RomanAtom(new TeXFormula(r7, r8[1], "mathnormal", false, false).root));
    }

    public static final Atom middle_macro(TeXParser r3, String[] r4) {
        return new MiddleAtom(new TeXFormula(r3, r4[1]).root);
    }

    public static final Atom minuscolon_macro(TeXParser r10, String[] r11) {
        RowAtom r102 = new RowAtom(SymbolAtom.get("minus"));
        r102.add(new SpaceAtom(0, -0.095f, 0.0f, 0.0f));
        r102.add(new UnderOverAtom(SymbolAtom.get("normaldot"), SymbolAtom.get("normaldot"), 5, 5.2f, false, true));
        return new TypedAtom(3, 3, r102);
    }

    public static final Atom minuscoloncolon_macro(TeXParser r10, String[] r11) {
        RowAtom r102 = new RowAtom(SymbolAtom.get("minus"));
        r102.add(new SpaceAtom(0, -0.095f, 0.0f, 0.0f));
        UnderOverAtom r3 = new UnderOverAtom(SymbolAtom.get("normaldot"), SymbolAtom.get("normaldot"), 5, 5.2f, false, true);
        r102.add(r3);
        r102.add(r3);
        return new TypedAtom(3, 3, r102);
    }

    public static final Atom multicolumn_macro(TeXParser r5, String[] r6) {
        int r0 = Integer.parseInt(r6[1]);
        if (r0 <= 4096) goto L5;
        r0 = 4096;
    L5:
        r5.addAtom(new MulticolumnAtom(r0, r6[2], new TeXFormula(r5, r6[3]).root));
        ((ArrayOfAtoms) r5.formula).addCol(r0);
        return null;
    }

    public static final Atom multlineATATenv_macro(TeXParser r5, String[] r6) {
        ArrayOfAtoms r0 = new ArrayOfAtoms();
        new TeXParser(r5.getIsPartial(), r6[1], r0, false).parse();
        r0.checkDimensions();
        int r62 = r0.col;
        if (r62 > 1) goto L8;
        if (r62 != 0) goto L7;
        return null;
    L7:
        return new MultlineAtom(r5.getIsPartial(), r0, 0);
    L8:
        Box$$ExternalSyntheticBUOutline0.m("Character '&' is only available in array mode !");
        return null;
    }

    public static final Atom muskip_macros(TeXParser r5, String[] r6) {
        int r52 = 0;
        int r1 = 1;
        if (r6[0].equals(",") == false) goto L6;
    L4:
        r52 = r1;
    L33:
        return new SpaceAtom(r52);
    L6:
        if (r6[0].equals(":") == false) goto L9;
    L7:
        r52 = 2;
        goto L33
    L9:
        if (r6[0].equals(";") == false) goto L12;
    L10:
        r52 = 3;
        goto L33
    L12:
        if (r6[0].equals("thinspace") == true) goto L4;
        if (r6[0].equals("medspace") == true) goto L7;
        if (r6[0].equals("thickspace") == true) goto L10;
        r1 = -1;
        if (r6[0].equals("!") == true) goto L4;
        if (r6[0].equals("negthinspace") == true) goto L4;
        if (r6[0].equals("negmedspace") == false) goto L30;
        r52 = -2;
        goto L33
    L30:
        if (r6[0].equals("negthickspace") == false) goto L33;
        r52 = -3;
        goto L33
    }

    public static final Atom nbsp_macro(TeXParser r0, String[] r1) {
        return new SpaceAtom();
    }

    public static final Atom newcommand_macro(TeXParser r5, String[] r6) {
        String r1 = r6[1];
        if (r5.isValidName(r1) == false) goto L15;
        String r52 = r6[3];
        if (r52 != null) goto L7;
        Integer r53 = new Integer(0);
    L9:
        if (r6[4] != null) goto L11;
        NewCommandMacro.addNewCommand(r1.substring(1), r6[2], r53.intValue());
        return null;
    L11:
        NewCommandMacro.addNewCommand(r1.substring(1), r6[2], r53.intValue(), r6[4]);
        return null;
    L7:
        r53 = Integer.valueOf(Integer.parseInt(r52));
        goto L9
    L15:
        throw new ParseException("Invalid name for the command :" + r1);
    }

    public static final Atom newenvironment_macro(TeXParser r3, String[] r4) {
        String r32 = r4[4];
        if (r32 != null) goto L5;
        int r33 = 0;
    L6:
        NewEnvironmentMacro.addNewEnvironment(r4[1], r4[2], r4[3], r33);
        return null;
    L5:
        r33 = Integer.parseInt(r32);
        goto L6
    }

    public static final Atom nolimits_macro(TeXParser r0, String[] r1) {
        Atom r02 = r0.getLastAtom();
        r02.type_limits = 1;
        return r02.m3846clone();
    }

    public static final Atom normal_macro(TeXParser r0, String[] r1) {
        Atom r02 = r0.getLastAtom();
        r02.type_limits = 0;
        return r02.m3846clone();
    }

    public static final Atom ogonek_macro(TeXParser r3, String[] r4) {
        return new OgonekAtom(new TeXFormula(r3, r4[1]).root);
    }

    public static final Atom oint_macro(TeXParser r0, String[] r1) {
        Atom r02 = SymbolAtom.get("oint").m3846clone();
        r02.type_limits = 1;
        return r02;
    }

    public static final Atom ovalbox_macro(TeXParser r3, String[] r4) {
        return new OvalAtom(new TeXFormula(r3, r4[1]).root);
    }

    public static final Atom over_macro(TeXParser r3, String[] r4) {
        Atom r42 = r3.getFormulaAtom();
        Atom r32 = new TeXFormula(r3, r3.getOverArgument(), false).root;
        if (r42 == null) goto L7;
        if (r32 == null) goto L7;
        return new FractionAtom(r42, r32, true);
    L7:
        Box$$ExternalSyntheticBUOutline0.m("Both numerator and denominator of a fraction can't be empty!");
        return null;
    }

    public static final Atom overbrace_macro(TeXParser r7, String[] r8) {
        return new OverUnderDelimiter(new TeXFormula(r7, r8[1], false).root, null, SymbolAtom.get("lbrace"), 1, 0.0f, true);
    }

    public static final Atom overbrack_macro(TeXParser r7, String[] r8) {
        return new OverUnderDelimiter(new TeXFormula(r7, r8[1], false).root, null, SymbolAtom.get("lsqbrack"), 1, 0.0f, true);
    }

    public static final Atom overleftarrow_macro(TeXParser r4, String[] r5) {
        return new UnderOverArrowAtom(new TeXFormula(r4, r5[1], false).root, true, true);
    }

    public static final Atom overleftrightarrow_macro(TeXParser r4, String[] r5) {
        return new UnderOverArrowAtom(new TeXFormula(r4, r5[1], false).root, true);
    }

    public static final Atom overline_macro(TeXParser r3, String[] r4) {
        return new OverlinedAtom(new TeXFormula(r3, r4[1], false).root);
    }

    public static final Atom overparen_macro(TeXParser r7, String[] r8) {
        return new OverUnderDelimiter(new TeXFormula(r7, r8[1], false).root, null, SymbolAtom.get("lbrack"), 1, 0.0f, true);
    }

    public static final Atom overrightarrow_macro(TeXParser r4, String[] r5) {
        return new UnderOverArrowAtom(new TeXFormula(r4, r5[1], false).root, false, true);
    }

    public static final Atom overset_macro(TeXParser r7, String[] r8) {
        return new TypedAtom(3, 3, new UnderOverAtom(new TeXFormula(r7, r8[2], false).root, new TeXFormula(r7, r8[1], false).root, 5, 2.5f, true, true));
    }

    public static final Atom overwithdelims_macro(TeXParser r7, String[] r8) {
        Atom r0 = r7.getFormulaAtom();
        Atom r1 = new TeXFormula(r7, r7.getOverArgument(), false).root;
        if (r0 == null) goto L19;
        if (r1 == null) goto L19;
        Atom r2 = new TeXFormula(r7, r8[1], false).root;
        if ((r2 instanceof BigDelimiterAtom) == false) goto L8;
        r2 = ((BigDelimiterAtom) r2).delim;
    L8:
        Atom r72 = new TeXFormula(r7, r8[2], false).root;
        if ((r72 instanceof BigDelimiterAtom) == false) goto L12;
        r72 = ((BigDelimiterAtom) r72).delim;
    L12:
        if ((r2 instanceof SymbolAtom) == true) goto L14;
    L17:
        RowAtom r82 = new RowAtom();
        r82.add(r2);
        r82.add(new FractionAtom(r0, r1, true));
        r82.add(r72);
        return r82;
    L14:
        if ((r72 instanceof SymbolAtom) == false) goto L17;
        return new FencedAtom(new FractionAtom(r0, r1, true), (SymbolAtom) r2, (SymbolAtom) r72);
    L19:
        Box$$ExternalSyntheticBUOutline0.m("Both numerator and denominator of a fraction can't be empty!");
        return null;
    }

    public static final Atom phantom_macro(TeXParser r4, String[] r5) {
        return new PhantomAtom(new TeXFormula(r4, r5[1], false).root, true, true, true);
    }

    public static final Atom prescript_macro(TeXParser r7, String[] r8) {
        Atom r0 = new TeXFormula(r7, r8[3]).root;
        r7.addAtom(new ScriptsAtom(new PhantomAtom(r0, false, true, true), new TeXFormula(r7, r8[2]).root, new TeXFormula(r7, r8[1]).root, false));
        r7.addAtom(new SpaceAtom(5, -0.3f, 0.0f, 0.0f));
        return new TypedAtom(0, 0, r0);
    }

    public static final Atom qquad_macro(TeXParser r2, String[] r3) {
        return new SpaceAtom(0, 2.0f, 0.0f, 0.0f);
    }

    public static final Atom quad_macro(TeXParser r2, String[] r3) {
        return new SpaceAtom(0, 1.0f, 0.0f, 0.0f);
    }

    public static final Atom questeq_macro(TeXParser r7, String[] r8) {
        return new TypedAtom(3, 3, new UnderOverAtom(SymbolAtom.get(TeXFormula.symbolMappings[61]), new ScaleAtom(SymbolAtom.get(TeXFormula.symbolMappings[63]), 0.75d), 5, 2.5f, true, true));
    }

    public static final Atom raisebox_macro(TeXParser r17, String[] r18) {
        float[] r1 = SpaceAtom.getLength(r18[1]);
        if (r1.length == 1) goto L16;
        float[] r2 = SpaceAtom.getLength(r18[3]);
        float[] r3 = SpaceAtom.getLength(r18[4]);
        if (r2.length != 1) goto L7;
    L8:
        r2 = new float[]{-1.0f, 0.0f};
    L10:
        if (r3.length != 1) goto L12;
    L13:
        r3 = new float[]{-1.0f, 0.0f};
    L15:
        return new RaiseAtom(new TeXFormula(r17, r18[2]).root, (int) r1[0], r1[1], (int) r2[0], r2[1], (int) r3[0], r3[1]);
    L12:
        if (r3[1] != 0.0f) goto L15;
    L7:
        if (r2[1] != 0.0f) goto L10;
    L16:
        Box$$ExternalSyntheticBUOutline0.m("Error in getting raise in \\raisebox command !");
        return null;
    }

    public static final Atom ratio_macro(TeXParser r7, String[] r8) {
        return new TypedAtom(3, 3, new UnderOverAtom(SymbolAtom.get("normaldot"), SymbolAtom.get("normaldot"), 5, 5.2f, false, true));
    }

    public static final Atom reflectbox_macro(TeXParser r3, String[] r4) {
        return new ReflectAtom(new TeXFormula(r3, r4[1]).root);
    }

    public static final Atom renewcommand_macro(TeXParser r3, String[] r4) {
        String r1 = r4[1];
        if (r3.isValidName(r1) == false) goto L11;
        String r32 = r4[3];
        if (r32 != null) goto L7;
        Integer r33 = new Integer(0);
    L8:
        NewCommandMacro.addReNewCommand(r1.substring(1), r4[2], r33.intValue());
        return null;
    L7:
        r33 = Integer.valueOf(Integer.parseInt(r32));
        goto L8
    L11:
        throw new ParseException("Invalid name for the command :" + r1);
    }

    public static final Atom renewenvironment_macro(TeXParser r3, String[] r4) {
        String r32 = r4[4];
        if (r32 != null) goto L5;
        int r33 = 0;
    L6:
        NewEnvironmentMacro.addReNewEnvironment(r4[1], r4[2], r4[3], r33);
        return null;
    L5:
        r33 = Integer.parseInt(r32);
        goto L6
    }

    public static final Atom resizebox_macro(TeXParser r7, String[] r8) {
        Atom r72 = new TeXFormula(r7, r8[3]).root;
        boolean r1 = true;
        String r2 = r8[1];
        String r4 = r8[2];
        if (r2.equals("!") == true) goto L9;
        if (r8[2].equals("!") == true) goto L9;
        r1 = false;
    L9:
        return new ResizeAtom(r72, r2, r4, r1);
    }

    public static final Atom rm_macro(TeXParser r6, String[] r7) {
        return new RomanAtom(new TeXFormula(r6, r6.getOverArgument(), null, false, r6.isIgnoreWhiteSpace()).root);
    }

    public static final Atom rmoustache_macro(TeXParser r1, String[] r2) {
        BigDelimiterAtom r12 = new BigDelimiterAtom((SymbolAtom) SymbolAtom.get("rmoustache").m3846clone(), 1);
        r12.type = 5;
        return r12;
    }

    public static final Atom romannumeral_macro(TeXParser r14, String[] r15) {
        int[] r0 = {1000, 900, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN, MediaError.DetailedErrorCode.MANIFEST_UNKNOWN, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] r1 = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int r2 = Integer.parseInt(r15[1].trim());
        if (r2 <= 4000000) goto L5;
        r2 = 4000000;
    L5:
        String r4 = _UrlKt.FRAGMENT_ENCODE_SET;
        int r5 = 0;
    L6:
        if (r5 >= 13) goto L12;
    L8:
        if (r2 < r0[r5]) goto L10;
        r4 = r4 + r1[r5];
        r2 = r2 - r0[r5];
        goto L8
    L10:
        r5 = r5 + 1;
        goto L6
    L12:
        if (r15[0].charAt(0) != 'r') goto L15;
        r4 = r4.toLowerCase();
    L15:
        return new TeXFormula(r4, false).root;
    }

    public static final Atom rotatebox_macro(TeXParser r4, String[] r5) {
        Atom r42 = new TeXFormula(r4, r5[2]).root;
        String r1 = r5[1];
        if (r1 != null) goto L5;
        double r12 = 0.0d;
    L7:
        return new RotateAtom(r42, r12, r5[3]);
    L5:
        r12 = Double.parseDouble(r1);
        goto L7
    }

    public static final Atom rule_macro(TeXParser r11, String[] r12) {
        float[] r0 = SpaceAtom.getLength(r12[1]);
        if (r0.length == 1) goto L14;
        float[] r1 = SpaceAtom.getLength(r12[2]);
        if (r1.length == 1) goto L12;
        float[] r122 = SpaceAtom.getLength(r12[3]);
        if (r122.length != 1) goto L9;
        Box$$ExternalSyntheticBUOutline0.m("Error in getting raise in \\rule command !");
        return null;
    L9:
        return new RuleAtom((int) r0[0], r0[1], (int) r1[0], r1[1], (int) r122[0], -r122[1]);
    L12:
        Box$$ExternalSyntheticBUOutline0.m("Error in getting height in \\rule command !");
        return null;
    L14:
        Box$$ExternalSyntheticBUOutline0.m("Error in getting width in \\rule command !");
        return null;
    }

    public static final Atom sc_macro(TeXParser r6, String[] r7) {
        return new SmallCapAtom(new TeXFormula(r6, r6.getOverArgument(), null, false, r6.isIgnoreWhiteSpace()).root);
    }

    public static final Atom scalebox_macro(TeXParser r6, String[] r7) {
        Atom r1 = new TeXFormula(r6, r7[2]).root;
        double r2 = Double.parseDouble(r7[1]);
        String r4 = r7[3];
        if (r4 != null) goto L6;
        double r62 = Double.parseDouble(r7[1]);
    L8:
        return new ScaleAtom(r1, r2, r62);
    L6:
        r62 = Double.parseDouble(r4);
        goto L8
    }

    public static final Atom scriptscriptstyle_macro(TeXParser r2, String[] r3) {
        return new StyleAtom(6, new TeXFormula(r2, r2.getOverArgument(), false).root);
    }

    public static final Atom scriptstyle_macro(TeXParser r2, String[] r3) {
        return new StyleAtom(4, new TeXFormula(r2, r2.getOverArgument(), false).root);
    }

    public static final Atom sf_macro(TeXParser r6, String[] r7) {
        return new SsAtom(new TeXFormula(r6, r6.getOverArgument(), null, false, r6.isIgnoreWhiteSpace()).root);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final Atom sfrac_macro(TeXParser r13, String[] r14) {
        TeXFormula r0 = new TeXFormula(r13, r14[1], false);
        TeXFormula r2 = new TeXFormula(r13, r14[2], false);
        if (r0.root != null) goto L5;
    L13:
        Box$$ExternalSyntheticBUOutline0.m("Both numerator and denominator of a fraction can't be empty!");
        return null;
    L5:
        if (r2.root == null) goto L13;
        SymbolAtom r142 = SymbolAtom.get("slash");
        if (r13.isMathMode() == true) goto L10;
        VRowAtom r143 = new VRowAtom(new ScaleAtom(SymbolAtom.get("textfractionsolidus"), 1.25d, 0.65d));
        r143.setRaise(1, 0.4f);
        float r132 = -0.24f;
        float r8 = 0.75f;
        double r9 = 0.6d;
        double r11 = 0.5d;
        float r6 = -0.24f;
        SymbolAtom r144 = r143;
    L9:
        float r4 = r8;
        VRowAtom r5 = new VRowAtom(new ScaleAtom(r0.root, r9, r11));
        r5.setRaise(1, r4);
        RowAtom r02 = new RowAtom(r5);
        r02.add(new SpaceAtom(0, r132, 0.0f, 0.0f));
        r02.add(r144);
        r02.add(new SpaceAtom(0, r6, 0.0f, 0.0f));
        r02.add(new ScaleAtom(r2.root, r9, r11));
        return r02;
    L10:
        r8 = 0.45f;
        r132 = -0.13f;
        r6 = -0.065f;
        r9 = 0.75d;
        r11 = 0.75d;
        r144 = r142;
        goto L9
    }

    public static final Atom shadowbox_macro(TeXParser r3, String[] r4) {
        return new ShadowAtom(new TeXFormula(r3, r4[1]).root);
    }

    public static final Atom shoveleft_macro(TeXParser r2, String[] r3) {
        Atom r22 = new TeXFormula(r2, r3[1]).root;
        r22.alignment = 0;
        return r22;
    }

    public static final Atom shoveright_macro(TeXParser r2, String[] r3) {
        Atom r22 = new TeXFormula(r2, r3[1]).root;
        r22.alignment = 1;
        return r22;
    }

    public static final Atom sideset_macro(TeXParser r7, String[] r8) {
        TeXFormula r0 = new TeXFormula();
        r0.add(new PhantomAtom(new TeXFormula(r7, r8[3]).root, false, true, true));
        r0.append(r7.getIsPartial(), r8[1]);
        r0.add(new SpaceAtom(5, -0.3f, 0.0f, 0.0f));
        r0.append(r7.getIsPartial(), r8[3] + "\\nolimits" + r8[2]);
        return new TypedAtom(0, 0, r0.root);
    }

    public static final Atom simcolon_macro(TeXParser r10, String[] r11) {
        RowAtom r102 = new RowAtom(SymbolAtom.get("sim"));
        r102.add(new SpaceAtom(0, -0.095f, 0.0f, 0.0f));
        r102.add(new UnderOverAtom(SymbolAtom.get("normaldot"), SymbolAtom.get("normaldot"), 5, 5.2f, false, true));
        return new TypedAtom(3, 3, r102);
    }

    public static final Atom simcoloncolon_macro(TeXParser r10, String[] r11) {
        RowAtom r102 = new RowAtom(SymbolAtom.get("sim"));
        r102.add(new SpaceAtom(0, -0.095f, 0.0f, 0.0f));
        UnderOverAtom r3 = new UnderOverAtom(SymbolAtom.get("normaldot"), SymbolAtom.get("normaldot"), 5, 5.2f, false, true);
        r102.add(r3);
        r102.add(r3);
        return new TypedAtom(3, 3, r102);
    }

    public static final Atom size_macros(TeXParser r7, String[] r8) {
        if ("tiny".equals(r8[0]) == false) goto L6;
        float r82 = 0.5f;
    L33:
        return new MonoScaleAtom(new TeXFormula(r7, r7.getOverArgument(), null, false, r7.isIgnoreWhiteSpace()).root, r82);
    L6:
        if ("scriptsize".equals(r8[0]) == false) goto L9;
        r82 = 0.7f;
        goto L33
    L9:
        if ("footnotesize".equals(r8[0]) == false) goto L12;
        r82 = 0.8f;
        goto L33
    L12:
        if ("small".equals(r8[0]) == false) goto L15;
        r82 = 0.9f;
        goto L33
    L15:
        if ("normalsize".equals(r8[0]) == false) goto L18;
    L16:
        r82 = 1.0f;
        goto L33
    L18:
        if ("large".equals(r8[0]) == false) goto L21;
        r82 = 1.2f;
        goto L33
    L21:
        if ("Large".equals(r8[0]) == false) goto L24;
        r82 = 1.4f;
        goto L33
    L24:
        if ("LARGE".equals(r8[0]) == false) goto L27;
        r82 = 1.8f;
        goto L33
    L27:
        if ("huge".equals(r8[0]) == false) goto L30;
        r82 = 2.0f;
        goto L33
    L30:
        if ("Huge".equals(r8[0]) == false) goto L16;
        r82 = 2.5f;
        goto L33
    }

    public static final Atom smallfrowneq_macro(TeXParser r7, String[] r8) {
        return new TypedAtom(3, 3, new UnderOverAtom(SymbolAtom.get("equals"), SymbolAtom.get("smallfrown"), 5, -2.0f, true, true));
    }

    public static final Atom smallmatrixATATenv_macro(TeXParser r4, String[] r5) {
        ArrayOfAtoms r0 = new ArrayOfAtoms();
        new TeXParser(r4.getIsPartial(), r5[1], r0, false).parse();
        r0.checkDimensions();
        return new MatrixAtom(r4.getIsPartial(), r0, 5);
    }

    public static final Atom smash_macro(TeXParser r4, String[] r5) {
        return new SmashedAtom(new TeXFormula(r4, r5[1], false).root, r5[2]);
    }

    public static final Atom spATbreve_macro(TeXParser r1, String[] r2) {
        VRowAtom r12 = new VRowAtom(new TeXFormula("\\displaystyle\\!\\breve{}").root);
        r12.setRaise(1, 0.6f);
        return new SmashedAtom(r12, null);
    }

    public static final Atom spAThat_macro(TeXParser r1, String[] r2) {
        VRowAtom r12 = new VRowAtom(new TeXFormula("\\displaystyle\\widehat{}").root);
        r12.setRaise(1, 0.6f);
        return new SmashedAtom(r12, null);
    }

    public static final Atom sqrt_macro(TeXParser r5, String[] r6) {
        if (r6[2] != null) goto L7;
        return new NthRoot(new TeXFormula(r5, r6[1], false).root, null);
    L7:
        return new NthRoot(new TeXFormula(r5, r6[1], false).root, new TeXFormula(r5, r6[2], false).root);
    }

    public static final Atom st_macro(TeXParser r3, String[] r4) {
        return new StrikeThroughAtom(new TeXFormula(r3, r4[1], false).root);
    }

    public static final Atom stackbin_macro(TeXParser r11, String[] r12) {
        return new TypedAtom(2, 2, new UnderOverAtom(new TeXFormula(r11, r12[2], false).root, new TeXFormula(r11, r12[3], false).root, 5, 0.5f, true, new TeXFormula(r11, r12[1], false).root, 5, 2.5f, true));
    }

    public static final Atom stackrel_macro(TeXParser r11, String[] r12) {
        return new TypedAtom(3, 3, new UnderOverAtom(new TeXFormula(r11, r12[2], false).root, new TeXFormula(r11, r12[3], false).root, 5, 0.5f, true, new TeXFormula(r11, r12[1], false).root, 5, 2.5f, true));
    }

    public static final Atom surd_macro(TeXParser r0, String[] r1) {
        return new VCenteredAtom(SymbolAtom.get("surdsign"));
    }

    public static final Atom tcaron_macro(TeXParser r0, String[] r1) {
        return new tcaronAtom();
    }

    public static final Atom text_macro(TeXParser r7, String[] r8) {
        return new RomanAtom(new TeXFormula(r7, r8[1], "mathnormal", false, false).root);
    }

    public static final Atom textcircled_macro(TeXParser r4, String[] r5) {
        return new TextCircledAtom(new RomanAtom(new TeXFormula(r4, r5[1]).root));
    }

    public static final Atom textcolor_macro(TeXParser r3, String[] r4) {
        return new ColorAtom(new TeXFormula(r3, r4[2]).root, null, ColorAtom.getColor(r4[1]));
    }

    public static final Atom textsc_macro(TeXParser r3, String[] r4) {
        return new SmallCapAtom(new TeXFormula(r3, r4[1], false).root);
    }

    public static final Atom textstyle_macro(TeXParser r2, String[] r3) {
        return new StyleAtom(2, new TeXFormula(r2, r2.getOverArgument(), false).root);
    }

    public static final Atom textstyle_macros(TeXParser r7, String[] r8) {
        String r1 = r8[0];
        if ("frak".equals(r1) == false) goto L6;
        r1 = "mathfrak";
    L15:
        Map<Character.UnicodeBlock, TeXFormula.FontInfos> r2 = TeXFormula.externalFontMap;
        Character.UnicodeBlock r4 = Character.UnicodeBlock.BASIC_LATIN;
        TeXFormula.FontInfos r22 = r2.get(r4);
        if (r22 == null) goto L18;
        TeXFormula.externalFontMap.put(r4, null);
    L18:
        Atom r72 = new TeXFormula(r7, r8[1], false).root;
        if (r22 == null) goto L22;
        TeXFormula.externalFontMap.put(r4, r22);
    L22:
        return new TextStyleAtom(r72, r1);
    L6:
        if ("Bbb".equals(r8[0]) == false) goto L9;
        r1 = "mathbb";
        goto L15
    L9:
        if ("bold".equals(r8[0]) == false) goto L13;
        return new BoldAtom(new TeXFormula(r7, r8[1], false).root);
    L13:
        if ("cal".equals(r8[0]) == false) goto L15;
        r1 = "mathcal";
        goto L15
    }

    public static final Atom tt_macro(TeXParser r6, String[] r7) {
        return new TtAtom(new TeXFormula(r6, r6.getOverArgument(), null, false, r6.isIgnoreWhiteSpace()).root);
    }

    public static final Atom underaccent_macro(TeXParser r7, String[] r8) {
        return new UnderOverAtom(new TeXFormula(r7, r8[2], false).root, new TeXFormula(r7, r8[1], false).root, 5, 0.3f, true, false);
    }

    public static final Atom underbrace_macro(TeXParser r7, String[] r8) {
        return new OverUnderDelimiter(new TeXFormula(r7, r8[1], false).root, null, SymbolAtom.get("rbrace"), 1, 0.0f, false);
    }

    public static final Atom underbrack_macro(TeXParser r7, String[] r8) {
        return new OverUnderDelimiter(new TeXFormula(r7, r8[1], false).root, null, SymbolAtom.get("rsqbrack"), 1, 0.0f, false);
    }

    public static final Atom underleftarrow_macro(TeXParser r4, String[] r5) {
        return new UnderOverArrowAtom(new TeXFormula(r4, r5[1], false).root, true, false);
    }

    public static final Atom underleftrightarrow_macro(TeXParser r3, String[] r4) {
        return new UnderOverArrowAtom(new TeXFormula(r3, r4[1], false).root, false);
    }

    public static final Atom underline_macro(TeXParser r3, String[] r4) {
        return new UnderlinedAtom(new TeXFormula(r3, r4[1], false).root);
    }

    public static final Atom underparen_macro(TeXParser r7, String[] r8) {
        return new OverUnderDelimiter(new TeXFormula(r7, r8[1], false).root, null, SymbolAtom.get("rbrack"), 1, 0.0f, false);
    }

    public static final Atom underrightarrow_macro(TeXParser r3, String[] r4) {
        return new UnderOverArrowAtom(new TeXFormula(r3, r4[1], false).root, false, false);
    }

    public static final Atom underscore_macro(TeXParser r0, String[] r1) {
        return new UnderscoreAtom();
    }

    public static final Atom underset_macro(TeXParser r7, String[] r8) {
        return new TypedAtom(3, 3, new UnderOverAtom(new TeXFormula(r7, r8[2], false).root, new TeXFormula(r7, r8[1], false).root, 5, 0.5f, true, false));
    }

    public static final Atom undertilde_macro(TeXParser r10, String[] r11) {
        Atom r4 = new TeXFormula(r10, r11[1], false).root;
        return new UnderOverAtom(r4, new AccentedAtom(new PhantomAtom(r4, true, false, false), "widetilde"), 5, 0.3f, true, false);
    }

    public static final Atom vdots_macro(TeXParser r0, String[] r1) {
        return new VdotsAtom();
    }

    public static final Atom vphantom_macro(TeXParser r4, String[] r5) {
        return new PhantomAtom(new TeXFormula(r4, r5[1], false).root, false, true, true);
    }

    public static final Atom xleftarrow_macro(TeXParser r5, String[] r6) {
        return new XArrowAtom(new TeXFormula(r5, r6[1], false).root, new TeXFormula(r5, r6[2]).root, true);
    }

    public static final Atom xrightarrow_macro(TeXParser r5, String[] r6) {
        return new XArrowAtom(new TeXFormula(r5, r6[1], false).root, new TeXFormula(r5, r6[2]).root, false);
    }
}
