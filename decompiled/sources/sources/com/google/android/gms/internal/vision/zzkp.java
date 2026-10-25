package com.google.android.gms.internal.vision;

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

/* loaded from: classes5.dex */
abstract class zzkp {
    public static String zza(zzkk r2, String r3) {
        StringBuilder r0 = new StringBuilder();
        r0.append("# ");
        r0.append(r3);
        zza(r2, r0, 0);
        return r0.toString();
    }

    private static final String zza(String r4) {
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

    private static void zza(zzkk r13, StringBuilder r14, int r15) {
        HashMap r0 = new HashMap();
        HashMap r1 = new HashMap();
        TreeSet r2 = new TreeSet();
        Method[] r3 = r13.getClass().getDeclaredMethods();
        int r4 = r3.length;
        int r6 = 0;
    L4:
        if (r6 >= r4) goto L11;
        Method r8 = r3[r6];
        r1.put(r8.getName(), r8);
        if (r8.getParameterTypes().length != 0) goto L10;
        r0.put(r8.getName(), r8);
        if (r8.getName().startsWith("get") == false) goto L10;
        r2.add(r8.getName());
    L10:
        r6 = r6 + 1;
        goto L4
    L11:
        Iterator r22 = r2.iterator();
    L13:
        if (r22.hasNext() == false) goto L123;
        String r32 = (String) r22.next();
        if (r32.startsWith("get") == false) goto L17;
        String r42 = r32.substring(3);
    L18:
        boolean r10 = true;
        if (r42.endsWith("List") == false) goto L34;
        if (r42.endsWith("OrBuilderList") == true) goto L34;
        if (r42.equals("List") == true) goto L34;
        String r82 = String.valueOf(r42.substring(0, 1).toLowerCase());
        String r9 = r42.substring(1, r42.length() - 4);
        if (r9.length() == 0) goto L27;
        String r83 = r82.concat(r9);
    L28:
        Method r92 = (Method) r0.get(r32);
        if (r92 == null) goto L34;
        if (r92.getReturnType().equals(List.class) == false) goto L34;
        zza(r14, r15, zza(r83), zzjb.zza(r92, r13, new Object[0]));
        goto L13
    L27:
        r83 = new String(r82);
    L34:
        if (r42.endsWith(TeXFormulaSettingsParser.CHARTODEL_MAPPING_EL) == false) goto L51;
        if (r42.equals(TeXFormulaSettingsParser.CHARTODEL_MAPPING_EL) == true) goto L51;
        String r84 = String.valueOf(r42.substring(0, 1).toLowerCase());
        String r62 = r42.substring(1, r42.length() - 3);
        if (r62.length() == 0) goto L40;
        String r63 = r84.concat(r62);
    L41:
        Method r33 = (Method) r0.get(r32);
        if (r33 == null) goto L51;
        if (r33.getReturnType().equals(Map.class) == false) goto L51;
        if (r33.isAnnotationPresent(Deprecated.class) == true) goto L51;
        if (Modifier.isPublic(r33.getModifiers()) == false) goto L51;
        zza(r14, r15, zza(r63), zzjb.zza(r33, r13, new Object[0]));
        goto L13
    L40:
        r63 = new String(r84);
    L51:
        if (r42.length() == 0) goto L53;
        String r34 = "set".concat(r42);
    L55:
        if (((Method) r1.get(r34)) == null) goto L13;
        if (r42.endsWith("Bytes") == false) goto L64;
        String r35 = r42.substring(0, r42.length() - 5);
        if (r35.length() == 0) goto L61;
        String r36 = "get".concat(r35);
    L63:
        if (r0.containsKey(r36) == true) goto L13;
    L61:
        r36 = new String("get");
    L64:
        String r37 = String.valueOf(r42.substring(0, 1).toLowerCase());
        String r64 = r42.substring(1);
        if (r64.length() == 0) goto L67;
        String r38 = r37.concat(r64);
    L69:
        if (r42.length() == 0) goto L71;
        String r65 = "get".concat(r42);
    L72:
        Method r66 = (Method) r0.get(r65);
        if (r42.length() == 0) goto L75;
        String r43 = "has".concat(r42);
    L76:
        Method r44 = (Method) r0.get(r43);
        if (r66 == null) goto L13;
        Object r67 = zzjb.zza(r66, r13, new Object[0]);
        if (r44 == null) goto L81;
        r10 = ((Boolean) zzjb.zza(r44, r13, new Object[0])).booleanValue();
    L121:
        if (r10 == false) goto L13;
        zza(r14, r15, zza(r38), r67);
        goto L13
    L81:
        if ((r67 instanceof Boolean) == false) goto L87;
        if (((Boolean) r67).booleanValue() == true) goto L85;
    L84:
        boolean r45 = true;
    L117:
        if (r45 == false) goto L121;
        r10 = false;
    L85:
        r45 = false;
        goto L117
    L87:
        if ((r67 instanceof Integer) == false) goto L92;
        if (((Integer) r67).intValue() != 0) goto L85;
    L92:
        if ((r67 instanceof Float) == false) goto L97;
        if (((Float) r67).floatValue() != 0.0f) goto L85;
    L97:
        if ((r67 instanceof Double) == false) goto L102;
        if (((Double) r67).doubleValue() != 0.0d) goto L85;
    L102:
        if ((r67 instanceof String) == false) goto L105;
        r45 = r67.equals(_UrlKt.FRAGMENT_ENCODE_SET);
        goto L117
    L105:
        if ((r67 instanceof zzht) == false) goto L108;
        r45 = r67.equals(zzht.zza);
        goto L117
    L108:
        if ((r67 instanceof zzkk) == false) goto L113;
        if (r67 != ((zzkk) r67).zzr()) goto L85;
    L113:
        if ((r67 instanceof Enum) == false) goto L85;
        if (((Enum) r67).ordinal() != 0) goto L85;
    L75:
        r43 = new String("has");
        goto L76
    L71:
        r65 = new String("get");
        goto L72
    L67:
        r38 = new String(r37);
        goto L69
    L53:
        r34 = new String("set");
        goto L55
    L17:
        r42 = r32;
        goto L18
    L123:
        zzlx r132 = ((zzjb) r13).zzb;
        if (r132 == null) goto L158;
        r132.zza(r14, r15);
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
        r6.append(zzlq.zza(zzht.zza((String) r9)));
        r6.append(Typography.quote);
        return;
    L24:
        if ((r9 instanceof zzht) == false) goto L28;
        r6.append(": \"");
        r6.append(zzlq.zza((zzht) r9));
        r6.append(Typography.quote);
        return;
    L28:
        if ((r9 instanceof zzjb) == false) goto L35;
        r6.append(" {");
        zza((zzjb) r9, r6, r7 + 2);
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
}
