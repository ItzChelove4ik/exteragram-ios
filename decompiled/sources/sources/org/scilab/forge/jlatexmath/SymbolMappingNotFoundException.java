package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class SymbolMappingNotFoundException extends JMathTeXException {
    private static final long serialVersionUID = 2659192520874275262L;

    public SymbolMappingNotFoundException(String r3) {
        super("No mapping found for the symbol '" + r3 + "'! Insert a <SymbolMapping>-element in 'DefaultTeXFont.xml'.");
    }
}
