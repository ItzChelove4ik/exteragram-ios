package androidx.datastore.preferences.protobuf;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import kotlin.text.Typography;
import okhttp3.internal.url._UrlKt;
import org.scilab.forge.jlatexmath.TeXFormulaSettingsParser;

/* loaded from: classes3.dex */
abstract class MessageLiteToString {
    private static final char[] INDENT_BUFFER = null;

    static {
        char[] r0 = new char[80];
        INDENT_BUFFER = r0;
        Arrays.fill(r0, ' ');
    }

    private static void indent(int r3, StringBuilder r4) {
    L2:
        if (r3 <= 0) goto L8;
        char[] r0 = INDENT_BUFFER;
        if (r3 <= r0.length) goto L6;
        int r1 = r0.length;
    L7:
        r4.append(r0, 0, r1);
        r3 = r3 - r1;
        goto L2
    L6:
        r1 = r3;
        goto L7
    }

    private static boolean isDefaultValue(Object r7) {
        if ((r7 instanceof Boolean) == false) goto L7;
        return !((Boolean) r7).booleanValue();
    L7:
        if ((r7 instanceof Integer) == false) goto L13;
        if (((Integer) r7).intValue() != 0) goto L11;
        return true;
    L11:
        return false;
    L13:
        if ((r7 instanceof Float) == false) goto L19;
        if (Float.floatToRawIntBits(((Float) r7).floatValue()) != 0) goto L17;
        return true;
    L17:
        return false;
    L19:
        if ((r7 instanceof Double) == false) goto L25;
        if (Double.doubleToRawLongBits(((Double) r7).doubleValue()) != 0) goto L23;
        return true;
    L23:
        return false;
    L25:
        if ((r7 instanceof String) == false) goto L29;
        return r7.equals(_UrlKt.FRAGMENT_ENCODE_SET);
    L29:
        if ((r7 instanceof ByteString) == false) goto L33;
        return r7.equals(ByteString.EMPTY);
    L33:
        if ((r7 instanceof MessageLite) == false) goto L39;
        if (r7 != ((MessageLite) r7).getDefaultInstanceForType()) goto L37;
        return true;
    L37:
        return false;
    L39:
        if ((r7 instanceof Enum) == true) goto L41;
    L43:
        return false;
    L41:
        if (((Enum) r7).ordinal() != 0) goto L43;
        return true;
    }

    private static String pascalCaseToSnakeCase(String r4) {
        if (r4.isEmpty() == false) goto L5;
        return r4;
    L5:
        StringBuilder r0 = new StringBuilder();
        r0.append(Character.toLowerCase(r4.charAt(0)));
        int r1 = 1;
    L7:
        if (r1 >= r4.length()) goto L13;
        char r2 = r4.charAt(r1);
        if (Character.isUpperCase(r2) == false) goto L11;
        r0.append("_");
    L11:
        r0.append(Character.toLowerCase(r2));
        r1 = r1 + 1;
        goto L7
    L13:
        return r0.toString();
    }

    public static void printField(StringBuilder r4, int r5, String r6, Object r7) {
        if ((r7 instanceof List) == false) goto L9;
        Iterator r72 = ((List) r7).iterator();
    L6:
        if (r72.hasNext() == false) goto L14;
        printField(r4, r5, r6, r72.next());
        goto L6
    L14:
        return;
    L9:
        if ((r7 instanceof Map) == false) goto L15;
        Iterator r73 = ((Map) r7).entrySet().iterator();
    L12:
        if (r73.hasNext() == false) goto L35;
        printField(r4, r5, r6, (Map.Entry) r73.next());
        goto L12
    L35:
        return;
    L15:
        r4.append('\n');
        indent(r5, r4);
        r4.append(pascalCaseToSnakeCase(r6));
        if ((r7 instanceof String) == false) goto L20;
        r4.append(": \"");
        r4.append(TextFormatEscaper.escapeText((String) r7));
        r4.append(Typography.quote);
        return;
    L20:
        if ((r7 instanceof ByteString) == false) goto L24;
        r4.append(": \"");
        r4.append(TextFormatEscaper.escapeBytes((ByteString) r7));
        r4.append(Typography.quote);
        return;
    L24:
        if ((r7 instanceof GeneratedMessageLite) == false) goto L28;
        r4.append(" {");
        reflectivePrintWithIndent((GeneratedMessageLite) r7, r4, r5 + 2);
        r4.append("\n");
        indent(r5, r4);
        r4.append("}");
        return;
    L28:
        if ((r7 instanceof Map.Entry) == false) goto L31;
        r4.append(" {");
        Map.Entry r74 = (Map.Entry) r7;
        int r62 = r5 + 2;
        printField(r4, r62, PluginsConstants.Settings.KEY, r74.getKey());
        printField(r4, r62, "value", r74.getValue());
        r4.append("\n");
        indent(r5, r4);
        r4.append("}");
        return;
    L31:
        r4.append(": ");
        r4.append(r7);
    }

    private static void reflectivePrintWithIndent(MessageLite r17, StringBuilder r18, int r19) {
        HashSet r3 = new HashSet();
        HashMap r4 = new HashMap();
        TreeMap r5 = new TreeMap();
        Method[] r6 = r17.getClass().getDeclaredMethods();
        int r7 = r6.length;
        int r9 = 0;
    L3:
        int r13 = 3;
        if (r9 >= r7) goto L27;
        Method r14 = r6[r9];
        if (Modifier.isStatic(r14.getModifiers()) == true) goto L26;
        if (r14.getName().length() < 3) goto L26;
        if (r14.getName().startsWith("set") == false) goto L15;
        r3.add(r14.getName());
        goto L26
    L15:
        if (Modifier.isPublic(r14.getModifiers()) == false) goto L26;
        if (r14.getParameterTypes().length != 0) goto L26;
        if (r14.getName().startsWith("has") == false) goto L24;
        r4.put(r14.getName(), r14);
        goto L26
    L24:
        if (r14.getName().startsWith("get") == false) goto L26;
        r5.put(r14.getName(), r14);
    L26:
        r9 = r9 + 1;
        goto L3
    L27:
        Iterator r62 = r5.entrySet().iterator();
    L29:
        if (r62.hasNext() == false) goto L75;
        Map.Entry r72 = (Map.Entry) r62.next();
        String r92 = ((String) r72.getKey()).substring(r13);
        if (r92.endsWith("List") == true) goto L33;
    L42:
        int r16 = r13;
    L44:
        if (r92.endsWith(TeXFormulaSettingsParser.CHARTODEL_MAPPING_EL) == false) goto L57;
        if (r92.equals(TeXFormulaSettingsParser.CHARTODEL_MAPPING_EL) == true) goto L57;
        Method r132 = (Method) r72.getValue();
        if (r132 == null) goto L57;
        if (r132.getReturnType().equals(Map.class) == false) goto L57;
        if (r132.isAnnotationPresent(Deprecated.class) == true) goto L57;
        if (Modifier.isPublic(r132.getModifiers()) == false) goto L57;
        printField(r18, r19, r92.substring(0, r92.length() - 3), GeneratedMessageLite.invokeOrDie(r132, r17, new Object[0]));
    L41:
        r13 = r16;
    L57:
        if (r3.contains("set".concat(r92)) == false) goto L41;
        if (r92.endsWith("Bytes") == true) goto L62;
    L64:
        Method r73 = (Method) r72.getValue();
        Method r133 = (Method) r4.get("has".concat(r92));
        if (r73 == null) goto L41;
        Object r74 = GeneratedMessageLite.invokeOrDie(r73, r17, new Object[0]);
        if (r133 == null) goto L69;
        boolean r134 = ((Boolean) GeneratedMessageLite.invokeOrDie(r133, r17, new Object[0])).booleanValue();
    L73:
        if (r134 == false) goto L41;
        printField(r18, r19, r92, r74);
        goto L41
    L69:
        if (isDefaultValue(r74) == true) goto L71;
        r134 = true;
        goto L73
    L71:
        r134 = false;
        goto L73
    L62:
        if (r5.containsKey("get".concat(r92.substring(0, r92.length() - 5))) == false) goto L64;
    L33:
        if (r92.endsWith("OrBuilderList") == true) goto L42;
        if (r92.equals("List") == true) goto L42;
        Method r142 = (Method) r72.getValue();
        if (r142 == null) goto L42;
        r16 = r13;
        if (r142.getReturnType().equals(List.class) == false) goto L44;
        printField(r18, r19, r92.substring(0, r92.length() - 4), GeneratedMessageLite.invokeOrDie(r142, r17, new Object[0]));
        goto L41
    L75:
        UnknownFieldSetLite r0 = ((GeneratedMessageLite) r17).unknownFields;
        if (r0 == null) goto L96;
        r0.printWithIndent(r18, r19);
        return;
    }

    public static String toString(MessageLite r2, String r3) {
        StringBuilder r0 = new StringBuilder();
        r0.append("# ");
        r0.append(r3);
        reflectivePrintWithIndent(r2, r0, 0);
        return r0.toString();
    }
}
