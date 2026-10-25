package org.scilab.forge.jlatexmath;

import java.io.InputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import okhttp3.internal.url._UrlKt;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import ru.noties.jlatexmath.JLatexMathAndroid;

/* loaded from: classes5.dex */
public class TeXFormulaSettingsParser {
    public static final String CHARTODEL_MAPPING_EL = "Map";
    public static final String RESOURCE_NAME = "TeXFormulaSettings.xml";
    private Element root;

    public TeXFormulaSettingsParser() {
        this(JLatexMathAndroid.getResourceAsStream(RESOURCE_NAME), RESOURCE_NAME);
    }

    public TeXFormulaSettingsParser(InputStream r3, String r4) {
        DocumentBuilderFactory r0 = DocumentBuilderFactory.newInstance();     // Catch: Exception -> L5
        r0.setIgnoringElementContentWhitespace(true);     // Catch: Exception -> L5
        r0.setIgnoringComments(true);     // Catch: Exception -> L5
        this.root = r0.newDocumentBuilder().parse(r3).getDocumentElement();     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        throw new XMLResourceParseException(r4, e);
    }

    private static void addFormulaToMap(NodeList r12, String[] r13, String[] r14) {
        int r1 = 0;
    L4:
        if (r1 >= r12.getLength()) goto L23;
        Element r2 = (Element) r12.item(r1);
        String r4 = r2.getAttribute("char");
        String r6 = r2.getAttribute("formula");
        String r7 = r2.getAttribute("text");
        if (r4.equals(_UrlKt.FRAGMENT_ENCODE_SET) == true) goto L22;
        if (r6.equals(_UrlKt.FRAGMENT_ENCODE_SET) == true) goto L20;
        if (r4.length() != 1) goto L18;
        r13[r4.charAt(0)] = r6;
        if (r14 == null) goto L16;
        if (r7.equals(_UrlKt.FRAGMENT_ENCODE_SET) == true) goto L16;
        r14[r4.charAt(0)] = r7;
    L16:
        r1 = r1 + 1;
        goto L4
    L18:
        throw new XMLResourceParseException(RESOURCE_NAME, r2.getTagName(), "char", "must have a value that contains exactly 1 character!");
    L20:
        throw new XMLResourceParseException(RESOURCE_NAME, r2.getTagName(), "formula", null);
    L22:
        throw new XMLResourceParseException(RESOURCE_NAME, r2.getTagName(), "char", null);
    }

    private static void addToMap(NodeList r12, String[] r13, String[] r14) {
        int r1 = 0;
    L4:
        if (r1 >= r12.getLength()) goto L23;
        Element r2 = (Element) r12.item(r1);
        String r4 = r2.getAttribute("char");
        String r6 = r2.getAttribute("symbol");
        String r7 = r2.getAttribute("text");
        if (r4.equals(_UrlKt.FRAGMENT_ENCODE_SET) == true) goto L22;
        if (r6.equals(_UrlKt.FRAGMENT_ENCODE_SET) == true) goto L20;
        if (r4.length() != 1) goto L18;
        r13[r4.charAt(0)] = r6;
        if (r14 == null) goto L16;
        if (r7.equals(_UrlKt.FRAGMENT_ENCODE_SET) == true) goto L16;
        r14[r4.charAt(0)] = r7;
    L16:
        r1 = r1 + 1;
        goto L4
    L18:
        throw new XMLResourceParseException(RESOURCE_NAME, r2.getTagName(), "char", "must have a value that contains exactly 1 character!");
    L20:
        throw new XMLResourceParseException(RESOURCE_NAME, r2.getTagName(), "symbol", null);
    L22:
        throw new XMLResourceParseException(RESOURCE_NAME, r2.getTagName(), "char", null);
    }

    public void parseSymbolMappings(String[] r2, String[] r3) {
        Element r1 = (Element) this.root.getElementsByTagName("CharacterToSymbolMappings").item(0);
        if (r1 == null) goto L6;
        addToMap(r1.getElementsByTagName(CHARTODEL_MAPPING_EL), r2, r3);
        return;
    }

    public void parseSymbolToFormulaMappings(String[] r2, String[] r3) {
        Element r1 = (Element) this.root.getElementsByTagName("CharacterToFormulaMappings").item(0);
        if (r1 == null) goto L6;
        addFormulaToMap(r1.getElementsByTagName(CHARTODEL_MAPPING_EL), r2, r3);
        return;
    }
}
