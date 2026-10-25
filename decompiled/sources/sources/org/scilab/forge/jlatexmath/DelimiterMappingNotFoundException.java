package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class DelimiterMappingNotFoundException extends JMathTeXException {
    private static final long serialVersionUID = 273456491396361682L;

    public DelimiterMappingNotFoundException(char r3) {
        super("No mapping found for the character '" + r3 + "'! Insert a <Map>-element in 'TeXFormulaSettings.xml'.");
    }
}
