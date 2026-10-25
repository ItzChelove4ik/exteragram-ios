package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class InvalidDelimiterException extends JMathTeXException {
    private static final long serialVersionUID = 212553180078002724L;

    public InvalidDelimiterException(char r3, String r4) {
        super("The character '" + r3 + "' is mapped to a symbol with the name '" + r4 + "', but that symbol is not defined as a delimiter (del='true') in 'TeXSymbols.xml'!");
    }

    public InvalidDelimiterException(String r3) {
        super("The symbol with the name '" + r3 + "' is not defined as a delimiter (del='true') in 'TeXSymbols.xml'!");
    }
}
