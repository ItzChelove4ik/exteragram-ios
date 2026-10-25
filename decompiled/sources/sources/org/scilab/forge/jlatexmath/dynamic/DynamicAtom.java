package org.scilab.forge.jlatexmath.dynamic;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.Box;
import org.scilab.forge.jlatexmath.EmptyAtom;
import org.scilab.forge.jlatexmath.StrutBox;
import org.scilab.forge.jlatexmath.TeXEnvironment;
import org.scilab.forge.jlatexmath.TeXFormula;

/* loaded from: classes5.dex */
public class DynamicAtom extends Atom {
    private static ExternalConverterFactory ecFactory;
    private ExternalConverter converter;
    private String externalCode;
    private TeXFormula formula;
    private boolean insert;
    private boolean refreshed;

    public DynamicAtom(String r2, String r3) {
        this.formula = new TeXFormula();
        this.externalCode = r2;
        ExternalConverterFactory r22 = ecFactory;
        if (r22 == null) goto L5;
        this.converter = r22.getExternalConverter();
    L5:
        if (r3 != null) goto L7;
        return;
    L7:
        if (r3.equals("i") == false) goto L11;
        this.insert = true;
        return;
    }

    public static boolean hasAnExternalConverterFactory() {
        if (ecFactory == null) goto L6;
        return true;
    L6:
        return false;
    }

    public static void setExternalConverterFactory(ExternalConverterFactory r0) {
        ecFactory = r0;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r4) {
        ExternalConverter r0 = this.converter;
        if (r0 == null) goto L13;
        if (this.refreshed == false) goto L7;
        this.refreshed = false;
    L8:
        Atom r3 = this.formula.root;
        if (r3 == null) goto L13;
        return r3.createBox(r4);
    L7:
        this.formula.setLaTeX(r0.getLaTeXString(this.externalCode));
    L13:
        return new StrutBox(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public Atom getAtom() {
        if (this.refreshed == true) goto L5;
        this.formula.setLaTeX(this.converter.getLaTeXString(this.externalCode));
        this.refreshed = true;
    L5:
        Atom r3 = this.formula.root;
        if (r3 == null) goto L8;
        return r3;
    L8:
        return new EmptyAtom();
    }

    public boolean getInsertMode() {
        return this.insert;
    }
}
