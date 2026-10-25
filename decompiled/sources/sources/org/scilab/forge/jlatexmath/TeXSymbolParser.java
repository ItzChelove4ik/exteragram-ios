package org.scilab.forge.jlatexmath;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import okhttp3.internal.url._UrlKt;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import ru.noties.jlatexmath.JLatexMathAndroid;

/* loaded from: classes5.dex */
public class TeXSymbolParser {
    public static final String DELIMITER_ATTR = "del";
    public static final String RESOURCE_NAME = "TeXSymbols.xml";
    public static final String TYPE_ATTR = "type";
    private static Map<String, Integer> typeMappings;
    private Element root;

    static {
        typeMappings = new HashMap();
    }

    public TeXSymbolParser() {
        this(JLatexMathAndroid.getResourceAsStream(RESOURCE_NAME), RESOURCE_NAME);
    }

    public TeXSymbolParser(InputStream r3, String r4) {
        DocumentBuilderFactory r0 = DocumentBuilderFactory.newInstance();     // Catch: Exception -> L5
        r0.setIgnoringElementContentWhitespace(true);     // Catch: Exception -> L5
        r0.setIgnoringComments(true);     // Catch: Exception -> L5
        this.root = r0.newDocumentBuilder().parse(r3).getDocumentElement();     // Catch: Exception -> L5
        setTypeMappings();     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        throw new XMLResourceParseException(r4, e);
    }

    private static String getAttrValueAndCheckIfNotNull(String r3, Element r4) {
        String r0 = r4.getAttribute(r3);
        if (r0.equals(_UrlKt.FRAGMENT_ENCODE_SET) == true) goto L6;
        return r0;
    L6:
        throw new XMLResourceParseException(RESOURCE_NAME, r4.getTagName(), r3, null);
    }

    private void setTypeMappings() {
        typeMappings.put("ord", 0);
        typeMappings.put("op", 1);
        typeMappings.put("bin", 2);
        typeMappings.put("rel", 3);
        typeMappings.put("open", 4);
        typeMappings.put("close", 5);
        typeMappings.put("punct", 6);
        typeMappings.put("acc", 10);
    }

    public Map<String, SymbolAtom> readSymbols() {
        HashMap r0 = new HashMap();
        NodeList r9 = this.root.getElementsByTagName("Symbol");
        int r3 = 0;
    L4:
        if (r3 >= r9.getLength()) goto L16;
        Element r4 = (Element) r9.item(r3);
        String r5 = getAttrValueAndCheckIfNotNull("name", r4);
        String r7 = getAttrValueAndCheckIfNotNull("type", r4);
        String r42 = r4.getAttribute(DELIMITER_ATTR);
        if (r42 != null) goto L8;
    L10:
        boolean r43 = false;
    L11:
        Integer r8 = typeMappings.get(r7);
        if (r8 == null) goto L14;
        r0.put(r5, new SymbolAtom(r5, r8.intValue(), r43));
        r3 = r3 + 1;
        goto L4
    L14:
        TeXSymbolParser$$ExternalSyntheticBUOutline0.m("has an unknown value '", r7, RESOURCE_NAME, "Symbol", "type");
        return null;
    L8:
        if (r42.equals("true") == false) goto L10;
        r43 = true;
        goto L11
    L16:
        return r0;
    }
}
