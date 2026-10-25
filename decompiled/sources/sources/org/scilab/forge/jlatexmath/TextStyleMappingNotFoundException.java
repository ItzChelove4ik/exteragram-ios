package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class TextStyleMappingNotFoundException extends JMathTeXException {
    private static final long serialVersionUID = 4887043712790844966L;

    public TextStyleMappingNotFoundException(String r3) {
        super("No mapping found for the text style '" + r3 + "'! Insert a <TextStyleMapping>-element in 'DefaultTeXFont.xml'.");
    }
}
