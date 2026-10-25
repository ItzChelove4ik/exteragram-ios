package com.google.android.gms.internal.clearcut;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import kotlin.text.Typography;
import okhttp3.internal.url._UrlKt;
import org.scilab.forge.jlatexmath.TeXFormulaSettingsParser;

/* loaded from: classes4.dex */
abstract class zzdr {
    public static String zza(zzdo r2, String r3) {
        StringBuilder r0 = new StringBuilder();
        r0.append("# ");
        r0.append(r3);
        zza(r2, r0, 0);
        return r0.toString();
    }

    private static void zza(zzdo r18, StringBuilder r19, int r20) {
        HashMap r3 = new HashMap();
        HashMap r4 = new HashMap();
        TreeSet r5 = new TreeSet();
        Method[] r6 = r18.getClass().getDeclaredMethods();
        int r7 = r6.length;
        int r9 = 0;
    L4:
        if (r9 >= r7) goto L11;
        Method r11 = r6[r9];
        r4.put(r11.getName(), r11);
        if (r11.getParameterTypes().length != 0) goto L10;
        r3.put(r11.getName(), r11);
        if (r11.getName().startsWith("get") == false) goto L10;
        r5.add(r11.getName());
    L10:
        r9 = r9 + 1;
        goto L4
    L11:
        Iterator r52 = r5.iterator();
    L13:
        if (r52.hasNext() == false) goto L119;
        String r62 = (String) r52.next();
        Object r72 = _UrlKt.FRAGMENT_ENCODE_SET;
        String r92 = r62.replaceFirst("get", _UrlKt.FRAGMENT_ENCODE_SET);
        boolean r13 = true;
        if (r92.endsWith("List") == false) goto L30;
        if (r92.endsWith("OrBuilderList") == true) goto L30;
        if (r92.equals("List") == true) goto L30;
        String r112 = String.valueOf(r92.substring(0, 1).toLowerCase());
        String r12 = r92.substring(1, r92.length() - 4);
        if (r12.length() == 0) goto L23;
        String r113 = r112.concat(r12);
    L24:
        Method r122 = (Method) r3.get(r62);
        if (r122 == null) goto L30;
        if (r122.getReturnType().equals(List.class) == false) goto L30;
        zza(r19, r20, zzj(r113), zzcg.zza(r122, r18, new Object[0]));
        goto L13
    L23:
        r113 = new String(r112);
    L30:
        if (r92.endsWith(TeXFormulaSettingsParser.CHARTODEL_MAPPING_EL) == false) goto L47;
        if (r92.equals(TeXFormulaSettingsParser.CHARTODEL_MAPPING_EL) == true) goto L47;
        String r114 = String.valueOf(r92.substring(0, 1).toLowerCase());
        String r123 = r92.substring(1, r92.length() - 3);
        if (r123.length() == 0) goto L36;
        String r115 = r114.concat(r123);
    L37:
        Method r63 = (Method) r3.get(r62);
        if (r63 == null) goto L47;
        if (r63.getReturnType().equals(Map.class) == false) goto L47;
        if (r63.isAnnotationPresent(Deprecated.class) == true) goto L47;
        if (Modifier.isPublic(r63.getModifiers()) == false) goto L47;
        zza(r19, r20, zzj(r115), zzcg.zza(r63, r18, new Object[0]));
        goto L13
    L36:
        r115 = new String(r114);
    L47:
        if (r92.length() == 0) goto L49;
        String r64 = "set".concat(r92);
    L51:
        if (((Method) r4.get(r64)) == null) goto L13;
        if (r92.endsWith("Bytes") == false) goto L60;
        String r65 = r92.substring(0, r92.length() - 5);
        if (r65.length() == 0) goto L57;
        String r66 = "get".concat(r65);
    L59:
        if (r3.containsKey(r66) == true) goto L13;
    L57:
        r66 = new String("get");
    L60:
        String r67 = String.valueOf(r92.substring(0, 1).toLowerCase());
        String r116 = r92.substring(1);
        if (r116.length() == 0) goto L63;
        String r68 = r67.concat(r116);
    L65:
        if (r92.length() == 0) goto L67;
        String r117 = "get".concat(r92);
    L68:
        Method r118 = (Method) r3.get(r117);
        if (r92.length() == 0) goto L71;
        String r93 = "has".concat(r92);
    L72:
        Method r94 = (Method) r3.get(r93);
        if (r118 == null) goto L13;
        Object r119 = zzcg.zza(r118, r18, new Object[0]);
        if (r94 == null) goto L77;
        r13 = ((Boolean) zzcg.zza(r94, r18, new Object[0])).booleanValue();
    L117:
        if (r13 == false) goto L13;
        zza(r19, r20, zzj(r68), r119);
        goto L13
    L77:
        if ((r119 instanceof Boolean) == false) goto L83;
        if (((Boolean) r119).booleanValue() == true) goto L81;
    L80:
        boolean r73 = true;
    L113:
        if (r73 == false) goto L117;
        r13 = false;
    L81:
        r73 = false;
        goto L113
    L83:
        if ((r119 instanceof Integer) == false) goto L88;
        if (((Integer) r119).intValue() != 0) goto L81;
    L88:
        if ((r119 instanceof Float) == false) goto L93;
        if (((Float) r119).floatValue() != 0.0f) goto L81;
    L93:
        if ((r119 instanceof Double) == false) goto L98;
        if (((Double) r119).doubleValue() != 0.0d) goto L81;
    L98:
        if ((r119 instanceof String) == false) goto L101;
    L99:
        r73 = r119.equals(r72);
        goto L113
    L101:
        if ((r119 instanceof zzbb) == false) goto L104;
        r72 = zzbb.zzfi;
        goto L99
    L104:
        if ((r119 instanceof zzdo) == false) goto L109;
        if (r119 != ((zzdo) r119).zzbe()) goto L81;
    L109:
        if ((r119 instanceof Enum) == false) goto L81;
        if (((Enum) r119).ordinal() != 0) goto L81;
    L71:
        r93 = new String("has");
        goto L72
    L67:
        r117 = new String("get");
        goto L68
    L63:
        r68 = new String(r67);
        goto L65
    L49:
        r64 = new String("set");
        goto L51
    L119:
        zzey r0 = ((zzcg) r18).zzjp;
        if (r0 == null) goto L154;
        r0.zza(r19, r20);
        return;
    }

    public static final void zza(StringBuilder r6, int r7, String r8, Object r9) {
        if ((r9 instanceof List) == false) goto L9;
        Iterator r92 = ((List) r9).iterator();
    L6:
        if (r92.hasNext() == false) goto L14;
        zza(r6, r7, r8, r92.next());
        goto L6
    L14:
        return;
    L9:
        if ((r9 instanceof Map) == false) goto L15;
        Iterator r93 = ((Map) r9).entrySet().iterator();
    L12:
        if (r93.hasNext() == false) goto L48;
        zza(r6, r7, r8, (Map.Entry) r93.next());
        goto L12
    L48:
        return;
    L15:
        r6.append('\n');
        int r0 = 0;
        int r1 = 0;
    L17:
        if (r1 >= r7) goto L19;
        r6.append(' ');
        r1 = r1 + 1;
        goto L17
    L19:
        r6.append(r8);
        if ((r9 instanceof String) == false) goto L24;
        r6.append(": \"");
        r6.append(zzet.zzc(zzbb.zzf((String) r9)));
        r6.append(Typography.quote);
        return;
    L24:
        if ((r9 instanceof zzbb) == false) goto L28;
        r6.append(": \"");
        r6.append(zzet.zzc((zzbb) r9));
        r6.append(Typography.quote);
        return;
    L28:
        if ((r9 instanceof zzcg) == false) goto L35;
        r6.append(" {");
        zza((zzcg) r9, r6, r7 + 2);
        r6.append("\n");
    L30:
        if (r0 >= r7) goto L32;
        r6.append(' ');
        r0 = r0 + 1;
        goto L30
    L32:
        r6.append("}");
        return;
    L35:
        if ((r9 instanceof Map.Entry) == false) goto L41;
        r6.append(" {");
        Map.Entry r94 = (Map.Entry) r9;
        int r82 = r7 + 2;
        zza(r6, r82, PluginsConstants.Settings.KEY, r94.getKey());
        zza(r6, r82, "value", r94.getValue());
        r6.append("\n");
    L37:
        if (r0 >= r7) goto L39;
        r6.append(' ');
        r0 = r0 + 1;
        goto L37
    L39:
        r6.append("}");
        return;
    L41:
        r6.append(": ");
        r6.append(r9.toString());
    }

    private static final String zzj(String r4) {
        StringBuilder r0 = new StringBuilder();
        int r1 = 0;
    L4:
        if (r1 >= r4.length()) goto L10;
        char r2 = r4.charAt(r1);
        if (Character.isUpperCase(r2) == false) goto L8;
        r0.append("_");
    L8:
        r0.append(Character.toLowerCase(r2));
        r1 = r1 + 1;
        goto L4
    L10:
        return r0.toString();
    }
}
