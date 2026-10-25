package org.scilab.forge.jlatexmath;

import java.io.InputStream;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import okhttp3.internal.url._UrlKt;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import ru.noties.jlatexmath.JLatexMathAndroid;

/* loaded from: classes5.dex */
public class PredefinedTeXFormulaParser {
    public static final String RESOURCE_NAME = "PredefinedTeXFormulas.xml";
    private Element root;
    private String type;

    public PredefinedTeXFormulaParser(InputStream r2, String r3) {
        this.type = r3;     // Catch: Exception -> L5
        DocumentBuilderFactory r32 = DocumentBuilderFactory.newInstance();     // Catch: Exception -> L5
        r32.setIgnoringElementContentWhitespace(true);     // Catch: Exception -> L5
        r32.setIgnoringComments(true);     // Catch: Exception -> L5
        this.root = r32.newDocumentBuilder().parse(r2).getDocumentElement();     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        throw new XMLResourceParseException(_UrlKt.FRAGMENT_ENCODE_SET, e);
    }

    public PredefinedTeXFormulaParser(String r1, String r2) {
        this(JLatexMathAndroid.getResourceAsStream(r1), r2);
    }

    private static String getAttrValueAndCheckIfNotNull(String r3, Element r4) {
        String r0 = r4.getAttribute(r3);
        if (r0.equals(_UrlKt.FRAGMENT_ENCODE_SET) == true) goto L6;
        return r0;
    L6:
        throw new XMLResourceParseException(RESOURCE_NAME, r4.getTagName(), r3, null);
    }

    public void parse(Map r9) {
        if ("true".equals(getAttrValueAndCheckIfNotNull("enabled", this.root)) == false) goto L14;
        NodeList r0 = this.root.getElementsByTagName(this.type);
        int r3 = 0;
    L6:
        if (r3 >= r0.getLength()) goto L19;
        Element r4 = (Element) r0.item(r3);
        if ("true".equals(getAttrValueAndCheckIfNotNull("enabled", r4)) == false) goto L13;
        String r5 = getAttrValueAndCheckIfNotNull("name", r4);
        if ("TeXFormula".equals(this.type) == false) goto L12;
        r9.put(r5, (TeXFormula) new TeXFormulaParser(r5, r4, this.type).parse());
        goto L13
    L12:
        r9.put(r5, (MacroInfo) new TeXFormulaParser(r5, r4, this.type).parse());
    L13:
        r3 = r3 + 1;
        goto L6
    L19:
        return;
    }
}
