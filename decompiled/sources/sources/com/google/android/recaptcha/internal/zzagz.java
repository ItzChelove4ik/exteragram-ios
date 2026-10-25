package com.google.android.recaptcha.internal;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
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

/* loaded from: classes5.dex */
abstract class zzagz {
    private static final char[] zza = null;

    static {
        char[] r0 = new char[80];
        zza = r0;
        Arrays.fill(r0, ' ');
    }

    public static String zza(zzagx r2, String r3) {
        StringBuilder r0 = new StringBuilder();
        r0.append("# ");
        r0.append(r3);
        zzc(r2, r0, 0);
        return r0.toString();
    }

    public static void zzb(StringBuilder r6, int r7, String r8, Object r9) {
        if ((r9 instanceof List) == false) goto L9;
        Iterator r92 = ((List) r9).iterator();
    L6:
        if (r92.hasNext() == false) goto L14;
        zzb(r6, r7, r8, r92.next());
        goto L6
    L14:
        return;
    L9:
        if ((r9 instanceof Map) == false) goto L15;
        Iterator r93 = ((Map) r9).entrySet().iterator();
    L12:
        if (r93.hasNext() == false) goto L81;
        zzb(r6, r7, r8, (Map.Entry) r93.next());
        goto L12
    L81:
        return;
    L15:
        r6.append('\n');
        zzd(r7, r6);
        int r1 = 0;
        if (r8.isEmpty() == true) goto L25;
        StringBuilder r0 = new StringBuilder();
        r0.append(Character.toLowerCase(r8.charAt(0)));
        int r3 = 1;
    L19:
        if (r3 >= r8.length()) goto L24;
        char r4 = r8.charAt(r3);
        if (Character.isUpperCase(r4) == false) goto L23;
        r0.append("_");
    L23:
        r0.append(Character.toLowerCase(r4));
        r3 = r3 + 1;
        goto L19
    L24:
        r8 = r0.toString();
    L25:
        r6.append(r8);
        if ((r9 instanceof String) == false) goto L56;
        r6.append(": \"");
        String r94 = (String) r9;
        boolean r72 = false;
        boolean r82 = false;
        boolean r02 = false;
    L29:
        if (r1 >= r94.length()) goto L46;
        char r42 = r94.charAt(r1);
        if (r42 < ' ') goto L45;
        if (r42 > '~') goto L45;
        if (r42 != '\"') goto L37;
        r02 = true;
    L44:
        r1 = r1 + 1;
        goto L29
    L37:
        if (r42 != '\'') goto L39;
        r82 = true;
        goto L44
    L39:
        if (r42 != '\\') goto L44;
        r72 = true;
    L45:
        String r73 = zzahv.zza(r94.getBytes(StandardCharsets.UTF_8));
    L53:
        r6.append(r73);
        r6.append(Typography.quote);
        return;
    L46:
        if (r72 == false) goto L48;
        r94 = r94.replace("\\", "\\\\");
    L48:
        if (r82 == false) goto L50;
        r73 = r94.replace("'", "\\'");
    L51:
        if (r02 == false) goto L53;
        r73 = r73.replace("\"", "\\\"");
        goto L53
    L50:
        r73 = r94;
        goto L51
    L56:
        if ((r9 instanceof zzaer) == false) goto L60;
        r6.append(": \"");
        r6.append(zzahv.zza(((zzaer) r9).zzy()));
        r6.append(Typography.quote);
        return;
    L60:
        if ((r9 instanceof zzafv) == false) goto L64;
        r6.append(" {");
        zzc((zzafv) r9, r6, r7 + 2);
        r6.append("\n");
        zzd(r7, r6);
        r6.append("}");
        return;
    L64:
        if ((r9 instanceof Map.Entry) == false) goto L67;
        int r83 = r7 + 2;
        r6.append(" {");
        Map.Entry r95 = (Map.Entry) r9;
        zzb(r6, r83, PluginsConstants.Settings.KEY, r95.getKey());
        zzb(r6, r83, "value", r95.getValue());
        r6.append("\n");
        zzd(r7, r6);
        r6.append("}");
        return;
    L67:
        r6.append(": ");
        r6.append(r9);
    }

    private static void zzc(zzagx r18, StringBuilder r19, int r20) {
        HashSet r3 = new HashSet();
        HashMap r4 = new HashMap();
        TreeMap r5 = new TreeMap();
        Method[] r6 = r18.getClass().getDeclaredMethods();
        int r7 = r6.length;
        int r9 = 0;
    L3:
        int r13 = 3;
        if (r9 >= r7) goto L24;
        Method r14 = r6[r9];
        if (Modifier.isStatic(r14.getModifiers()) == true) goto L23;
        if (r14.getName().length() < 3) goto L23;
        if (r14.getName().startsWith("set") == false) goto L14;
        r3.add(r14.getName());
        goto L23
    L14:
        if (Modifier.isPublic(r14.getModifiers()) == false) goto L23;
        if (r14.getParameterTypes().length != 0) goto L23;
        if (r14.getName().startsWith("has") == false) goto L21;
        r4.put(r14.getName(), r14);
        goto L23
    L21:
        if (r14.getName().startsWith("get") == false) goto L23;
        r5.put(r14.getName(), r14);
    L23:
        r9 = r9 + 1;
        goto L3
    L24:
        Iterator r62 = r5.entrySet().iterator();
    L26:
        if (r62.hasNext() == false) goto L104;
        Map.Entry r72 = (Map.Entry) r62.next();
        String r92 = ((String) r72.getKey()).substring(r13);
        if (r92.endsWith("List") == false) goto L40;
        if (r92.endsWith("OrBuilderList") == true) goto L40;
        if (r92.equals("List") == true) goto L40;
        Method r142 = (Method) r72.getValue();
        if (r142 == null) goto L40;
        if (r142.getReturnType().equals(List.class) == false) goto L40;
        zzb(r19, r20, r92.substring(0, r92.length() - 4), zzafv.zzN(r142, r18, new Object[0]));
    L38:
        r13 = 3;
    L40:
        if (r92.endsWith(TeXFormulaSettingsParser.CHARTODEL_MAPPING_EL) == false) goto L53;
        if (r92.equals(TeXFormulaSettingsParser.CHARTODEL_MAPPING_EL) == true) goto L53;
        Method r132 = (Method) r72.getValue();
        if (r132 == null) goto L53;
        if (r132.getReturnType().equals(Map.class) == false) goto L53;
        if (r132.isAnnotationPresent(Deprecated.class) == true) goto L53;
        if (Modifier.isPublic(r132.getModifiers()) == false) goto L53;
        zzb(r19, r20, r92.substring(0, r92.length() - 3), zzafv.zzN(r132, r18, new Object[0]));
    L53:
        if (r3.contains("set".concat(r92)) == false) goto L38;
        if (r92.endsWith("Bytes") == true) goto L57;
    L58:
        Method r73 = (Method) r72.getValue();
        Method r133 = (Method) r4.get("has".concat(r92));
        if (r73 == null) goto L38;
        Object r74 = zzafv.zzN(r73, r18, new Object[0]);
        if (r133 == null) goto L63;
        boolean r134 = ((Boolean) zzafv.zzN(r133, r18, new Object[0])).booleanValue();
    L102:
        if (r134 == false) goto L38;
        zzb(r19, r20, r92, r74);
        goto L38
    L63:
        if ((r74 instanceof Boolean) == false) goto L68;
        if (((Boolean) r74).booleanValue() == true) goto L100;
    L66:
        r134 = false;
    L100:
        r134 = true;
        goto L102
    L68:
        if ((r74 instanceof Integer) == false) goto L73;
        if (((Integer) r74).intValue() != 0) goto L100;
    L73:
        if ((r74 instanceof Float) == false) goto L78;
        if (Float.floatToRawIntBits(((Float) r74).floatValue()) != 0) goto L100;
    L78:
        if ((r74 instanceof Double) == false) goto L83;
        if (Double.doubleToRawLongBits(((Double) r74).doubleValue()) != 0) goto L100;
    L83:
        if ((r74 instanceof String) == false) goto L86;
        boolean r135 = r74.equals(_UrlKt.FRAGMENT_ENCODE_SET);
    L88:
        if (r135 == false) goto L100;
    L86:
        if ((r74 instanceof zzaer) == false) goto L91;
        r135 = r74.equals(zzaer.zza);
        goto L88
    L91:
        if ((r74 instanceof zzagx) == false) goto L96;
        if (r74 != ((zzagx) r74).zzac()) goto L100;
    L96:
        if ((r74 instanceof Enum) == false) goto L100;
        if (((Enum) r74).ordinal() != 0) goto L100;
    L57:
        if (r5.containsKey("get".concat(r92.substring(0, r92.length() - 5))) == true) goto L38;
    L104:
        zzaia r0 = ((zzafv) r18).zzc;
        if (r0 == null) goto L125;
        r0.zzj(r19, r20);
        return;
    }

    private static void zzd(int r3, StringBuilder r4) {
    L2:
        if (r3 <= 0) goto L8;
        int r0 = 80;
        if (r3 > 80) goto L7;
        r0 = r3;
    L7:
        r4.append(zza, 0, r0);
        r3 = r3 - r0;
        goto L2
    }
}
