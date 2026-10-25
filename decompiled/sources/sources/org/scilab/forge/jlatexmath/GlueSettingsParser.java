package org.scilab.forge.jlatexmath;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import okhttp3.internal.url._UrlKt;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import ru.noties.jlatexmath.JLatexMathAndroid;

/* loaded from: classes5.dex */
public class GlueSettingsParser {
    private static final String RESOURCE_NAME = "GlueSettings.xml";
    private final Map<String, Integer> glueTypeMappings;
    private Glue[] glueTypes;
    private Element root;
    private final Map<String, Integer> styleMappings;
    private final Map<String, Integer> typeMappings;

    public GlueSettingsParser() {
        this.typeMappings = new HashMap();
        this.glueTypeMappings = new HashMap();
        this.styleMappings = new HashMap();
        setTypeMappings();     // Catch: Exception -> L5
        setStyleMappings();     // Catch: Exception -> L5
        DocumentBuilderFactory r1 = DocumentBuilderFactory.newInstance();     // Catch: Exception -> L5
        r1.setIgnoringElementContentWhitespace(true);     // Catch: Exception -> L5
        r1.setIgnoringComments(true);     // Catch: Exception -> L5
        this.root = r1.newDocumentBuilder().parse(JLatexMathAndroid.getResourceAsStream(RESOURCE_NAME)).getDocumentElement();     // Catch: Exception -> L5
        parseGlueTypes();     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        throw new XMLResourceParseException(RESOURCE_NAME, e);
    }

    private static void checkMapping(Object r1, String r2, String r3, String r4) {
        if (r1 == null) goto L4;
        return;
    L4:
        TeXSymbolParser$$ExternalSyntheticBUOutline0.m("has an unknown value '", r4, RESOURCE_NAME, r2, r3);
    }

    private Glue createGlue(Element r7, String r8) {
        String[] r6 = {"space", "stretch", "shrink"};
        float[] r1 = new float[3];
        int r3 = 0;
    L3:
        if (r3 >= 3) goto L14;
        String r4 = null;
        r4 = r7.getAttribute(r6[r3]);     // Catch: NumberFormatException -> L11
        if (r4.equals(_UrlKt.FRAGMENT_ENCODE_SET) == true) goto L9;
        double r42 = Double.parseDouble(r4);     // Catch: NumberFormatException -> L11
    L10:
        r1[r3] = (float) r42;
        r3 = r3 + 1;
        goto L3
    L9:
        r42 = 0.0d;
    L12:
        throw new XMLResourceParseException(RESOURCE_NAME, "GlueType", r6[r3], "has an invalid real value '" + r4 + "'!");
    L14:
        return new Glue(r1[0], r1[1], r1[2], r8);
    }

    private static String getAttrValueAndCheckIfNotNull(String r3, Element r4) {
        String r0 = r4.getAttribute(r3);
        if (r0.equals(_UrlKt.FRAGMENT_ENCODE_SET) == true) goto L6;
        return r0;
    L6:
        throw new XMLResourceParseException(RESOURCE_NAME, r4.getTagName(), r3, null);
    }

    private void parseGlueTypes() {
        ArrayList r0 = new ArrayList();
        int r2 = 0;
        Element r1 = (Element) this.root.getElementsByTagName("GlueTypes").item(0);
        int r4 = -1;
        if (r1 == null) goto L11;
        NodeList r12 = r1.getElementsByTagName("GlueType");
        int r5 = 0;
        int r6 = 0;
    L6:
        if (r5 >= r12.getLength()) goto L12;
        Element r7 = (Element) r12.item(r5);
        String r8 = getAttrValueAndCheckIfNotNull("name", r7);
        Glue r72 = createGlue(r7, r8);
        if (r8.equalsIgnoreCase(PluginsConstants.Settings.DEFAULT) == false) goto L10;
        r4 = r6;
    L10:
        r0.add(r72);
        r6 = r6 + 1;
        r5 = r5 + 1;
    L12:
        if (r4 >= 0) goto L14;
        r0.add(new Glue(0.0f, 0.0f, 0.0f, PluginsConstants.Settings.DEFAULT));
        r4 = r6;
    L14:
        Glue[] r02 = (Glue[]) r0.toArray(new Glue[r0.size()]);
        this.glueTypes = r02;
        if (r4 <= 0) goto L17;
        Glue r13 = r02[r4];
        r02[r4] = r02[0];
        r02[0] = r13;
    L17:
        Glue[] r03 = this.glueTypes;
        if (r2 >= r03.length) goto L20;
        this.glueTypeMappings.put(r03[r2].getName(), Integer.valueOf(r2));
        r2 = r2 + 1;
        goto L17
    L20:
        return;
    L11:
        r6 = 0;
        goto L12
    }

    private void setStyleMappings() {
        this.styleMappings.put("display", 0);
        this.styleMappings.put("text", 1);
        this.styleMappings.put("script", 2);
        this.styleMappings.put("script_script", 3);
    }

    private void setTypeMappings() {
        this.typeMappings.put("ord", 0);
        this.typeMappings.put("op", 1);
        this.typeMappings.put("bin", 2);
        this.typeMappings.put("rel", 3);
        this.typeMappings.put("open", 4);
        this.typeMappings.put("close", 5);
        this.typeMappings.put("punct", 6);
        this.typeMappings.put("inner", 7);
    }

    public int[][][] createGlueTable() {
        int r1 = this.typeMappings.size();
        int r2 = 0;
        int[][][] r12 = (int[][][]) Array.newInstance(Integer.TYPE, new int[]{r1, r1, this.styleMappings.size()});
        Element r3 = (Element) this.root.getElementsByTagName("GlueTable").item(0);
        if (r3 == null) goto L13;
        NodeList r32 = r3.getElementsByTagName("Glue");
        int r5 = 0;
    L6:
        if (r5 >= r32.getLength()) goto L13;
        Element r6 = (Element) r32.item(r5);
        String r8 = getAttrValueAndCheckIfNotNull("lefttype", r6);
        String r10 = getAttrValueAndCheckIfNotNull("righttype", r6);
        String r122 = getAttrValueAndCheckIfNotNull("gluetype", r6);
        NodeList r62 = r6.getElementsByTagName("Style");
        int r14 = r2;
    L9:
        if (r14 >= r62.getLength()) goto L11;
        String r15 = getAttrValueAndCheckIfNotNull("name", (Element) r62.item(r14));
        int[][][] r16 = r12;
        Integer r13 = this.typeMappings.get(r8);
        NodeList r17 = r32;
        Integer r33 = this.typeMappings.get(r10);
        int r18 = r5;
        Integer r52 = this.styleMappings.get(r15);
        NodeList r19 = r62;
        Integer r63 = this.glueTypeMappings.get(r122);
        checkMapping(r13, "Glue", "lefttype", r8);
        checkMapping(r33, "Glue", "righttype", r10);
        checkMapping(r63, "Glue", "gluetype", r122);
        checkMapping(r52, "Style", "name", r15);
        r16[r13.intValue()][r33.intValue()][r52.intValue()] = r63.intValue();
        r14 = r14 + 1;
        r12 = r16;
        r32 = r17;
        r5 = r18;
        r62 = r19;
        goto L9
    L11:
        r5 = r5 + 1;
        r2 = 0;
    L13:
        return r12;
    }

    public Glue[] getGlueTypes() {
        return this.glueTypes;
    }
}
