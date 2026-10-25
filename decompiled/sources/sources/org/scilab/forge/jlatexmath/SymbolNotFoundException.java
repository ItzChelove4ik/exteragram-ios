package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class SymbolNotFoundException extends JMathTeXException {
    private static final long serialVersionUID = -3005021333407670912L;

    public SymbolNotFoundException(String r3) {
        super("There's no symbol with the name '" + r3 + "' defined in 'TeXSymbols.xml'!");
    }
}
