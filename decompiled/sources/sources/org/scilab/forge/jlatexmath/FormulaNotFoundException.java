package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class FormulaNotFoundException extends JMathTeXException {
    private static final long serialVersionUID = 7660105446051204466L;

    public FormulaNotFoundException(String r3) {
        super("There's no predefined TeXFormula with the name '" + r3 + "' defined in 'PredefinedTeXFormulas.xml'!");
    }
}
