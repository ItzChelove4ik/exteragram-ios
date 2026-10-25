package com.google.android.gms.internal.play_billing;

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

/* loaded from: classes.dex */
abstract class zzgn {
    private static final char[] zza = null;

    static {
        char[] r0 = new char[80];
        zza = r0;
        Arrays.fill(r0, ' ');
    }

    public static String zza(zzgl r2, String r3) {
        StringBuilder r0 = new StringBuilder();
        r0.append("# ");
        r0.append(r3);
        zzd(r2, r0, 0);
        return r0.toString();
    }

    public static void zzb(StringBuilder r4, int r5, String r6, Object r7) {
        if ((r7 instanceof List) == false) goto L9;
        Iterator r72 = ((List) r7).iterator();
    L6:
        if (r72.hasNext() == false) goto L14;
        zzb(r4, r5, r6, r72.next());
        goto L6
    L14:
        return;
    L9:
        if ((r7 instanceof Map) == false) goto L15;
        Iterator r73 = ((Map) r7).entrySet().iterator();
    L12:
        if (r73.hasNext() == false) goto L48;
        zzb(r4, r5, r6, (Map.Entry) r73.next());
        goto L12
    L48:
        return;
    L15:
        r4.append('\n');
        zzc(r5, r4);
        if (r6.isEmpty() == true) goto L25;
        StringBuilder r0 = new StringBuilder();
        r0.append(Character.toLowerCase(r6.charAt(0)));
        int r1 = 1;
    L19:
        if (r1 >= r6.length()) goto L24;
        char r2 = r6.charAt(r1);
        if (Character.isUpperCase(r2) == false) goto L23;
        r0.append("_");
    L23:
        r0.append(Character.toLowerCase(r2));
        r1 = r1 + 1;
        goto L19
    L24:
        r6 = r0.toString();
    L25:
        r4.append(r6);
        if ((r7 instanceof String) == false) goto L30;
        r4.append(": \"");
        zzei r52 = zzei.zzb;
        r4.append(zzhf.zza(new zzeg(((String) r7).getBytes(zzfo.zza))));
        r4.append(Typography.quote);
        return;
    L30:
        if ((r7 instanceof zzei) == false) goto L34;
        r4.append(": \"");
        r4.append(zzhf.zza((zzei) r7));
        r4.append(Typography.quote);
        return;
    L34:
        if ((r7 instanceof zzfi) == false) goto L38;
        r4.append(" {");
        zzfi r74 = (zzfi) r7;
        zzd(r74, r4, r5 + 2);
        r4.append("\n");
        zzc(r5, r4);
        r4.append("}");
        return;
    L38:
        if ((r7 instanceof Map.Entry) == false) goto L41;
        int r62 = r5 + 2;
        r4.append(" {");
        Map.Entry r75 = (Map.Entry) r7;
        zzb(r4, r62, PluginsConstants.Settings.KEY, r75.getKey());
        zzb(r4, r62, "value", r75.getValue());
        r4.append("\n");
        zzc(r5, r4);
        r4.append("}");
        return;
    L41:
        r4.append(": ");
        r4.append(r7);
    }

    private static void zzc(int r3, StringBuilder r4) {
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

    private static void zzd(zzgl r18, StringBuilder r19, int r20) {
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
        if (r62.hasNext() == false) goto L103;
        Map.Entry r72 = (Map.Entry) r62.next();
        String r92 = ((String) r72.getKey()).substring(r13);
        if (r92.endsWith("List") == false) goto L40;
        if (r92.endsWith("OrBuilderList") == true) goto L40;
        if (r92.equals("List") == true) goto L40;
        Method r142 = (Method) r72.getValue();
        if (r142 == null) goto L40;
        if (r142.getReturnType().equals(List.class) == false) goto L40;
        zzb(r19, r20, r92.substring(0, r92.length() - 4), zzfi.zzs(r142, r18, new Object[0]));
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
        zzb(r19, r20, r92.substring(0, r92.length() - 3), zzfi.zzs(r132, r18, new Object[0]));
    L53:
        if (r3.contains("set".concat(r92)) == false) goto L38;
        if (r92.endsWith("Bytes") == true) goto L57;
    L58:
        Method r73 = (Method) r72.getValue();
        Method r133 = (Method) r4.get("has".concat(r92));
        if (r73 == null) goto L38;
        Object r74 = zzfi.zzs(r73, r18, new Object[0]);
        if (r133 != null) goto L101;
        if ((r74 instanceof Boolean) == false) goto L68;
        if (((Boolean) r74).booleanValue() == false) goto L38;
    L102:
        zzb(r19, r20, r92, r74);
        goto L38
    L68:
        if ((r74 instanceof Integer) == false) goto L73;
        if (((Integer) r74).intValue() == 0) goto L38;
    L73:
        if ((r74 instanceof Float) == false) goto L78;
        if (Float.floatToRawIntBits(((Float) r74).floatValue()) == 0) goto L38;
    L78:
        if ((r74 instanceof Double) == false) goto L83;
        if (Double.doubleToRawLongBits(((Double) r74).doubleValue()) == 0) goto L38;
    L83:
        if ((r74 instanceof String) == false) goto L86;
        boolean r134 = r74.equals(_UrlKt.FRAGMENT_ENCODE_SET);
    L88:
        if (r134 == true) goto L38;
    L86:
        if ((r74 instanceof zzei) == false) goto L91;
        r134 = r74.equals(zzei.zzb);
        goto L88
    L91:
        if ((r74 instanceof zzgl) == false) goto L96;
        if (r74 == ((zzgl) r74).zzh()) goto L38;
    L96:
        if ((r74 instanceof Enum) == false) goto L102;
        if (((Enum) r74).ordinal() == 0) goto L38;
    L101:
        if (((Boolean) zzfi.zzs(r133, r18, new Object[0])).booleanValue() == false) goto L38;
    L57:
        if (r5.containsKey("get".concat(r92.substring(0, r92.length() - 5))) == true) goto L38;
    L103:
        zzhi r0 = ((zzfi) r18).zzc;
        if (r0 == null) goto L131;
        r0.zzi(r19, r20);
        return;
    }
}
