package com.google.android.gms.internal.clearcut;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import kotlin.UByte;
import kotlin.text.Typography;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public abstract class zzga {
    public static <T extends zzfz> String zza(T r4) {
        if (r4 != null) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        StringBuffer r1 = new StringBuffer();
        zza(null, r4, new StringBuffer(), r1);     // Catch: InvocationTargetException -> L10 IllegalAccessException -> L12
    L9:
        return r1.toString();
    L12:
        e = move-exception;
        String r42 = String.valueOf(e.getMessage());
        if (r42.length() == 0) goto L25;
        return "Error printing proto: ".concat(r42);
    L25:
        return new String("Error printing proto: ");
    L10:
        e = move-exception;
        String r43 = String.valueOf(e.getMessage());
        if (r43.length() == 0) goto L19;
        return "Error printing proto: ".concat(r43);
    L19:
        return new String("Error printing proto: ");
    }

    private static void zza(String r11, Object r12, StringBuffer r13, StringBuffer r14) {
        if (r12 == null) goto L114;
        int r1 = 0;
        if ((r12 instanceof zzfz) == false) goto L53;
        int r0 = r13.length();
        if (r11 == null) goto L8;
        r14.append(r13);
        r14.append(zzl(r11));
        r14.append(" <\n");
        r13.append("  ");
    L8:
        Class<?> r2 = r12.getClass();
        Field[] r3 = r2.getFields();
        int r4 = r3.length;
        int r5 = 0;
    L9:
        if (r5 >= r4) goto L32;
        Field r6 = r3[r5];
        int r7 = r6.getModifiers();
        String r8 = r6.getName();
        if ("cachedSize".equals(r8) == true) goto L31;
        if ((r7 & 1) != 1) goto L31;
        if ((r7 & 8) == 8) goto L31;
        if (r8.startsWith("_") == true) goto L31;
        if (r8.endsWith("_") == true) goto L31;
        Class<?> r72 = r6.getType();
        Object r62 = r6.get(r12);
        if (r72.isArray() == true) goto L23;
    L30:
        zza(r8, r62, r13, r14);
        goto L31
    L23:
        if (r72.getComponentType() == Byte.TYPE) goto L30;
        if (r62 != null) goto L26;
        int r73 = 0;
    L27:
        int r9 = 0;
    L28:
        if (r9 >= r73) goto L31;
        zza(r8, Array.get(r62, r9), r13, r14);
        r9 = r9 + 1;
        goto L28
    L26:
        r73 = Array.getLength(r62);
    L31:
        r5 = r5 + 1;
        goto L9
    L32:
        Method[] r32 = r2.getMethods();
        int r42 = r32.length;
    L33:
        if (r1 >= r42) goto L51;
        String r52 = r32[r1].getName();
        if (r52.startsWith("set") == false) goto L50;
        String r53 = r52.substring(3);
        if (r53.length() == 0) goto L40;
        String r63 = "has".concat(r53);     // Catch: NoSuchMethodException -> L93
    L43:
        if (((Boolean) r2.getMethod(r63, null).invoke(r12, null)).booleanValue() == false) goto L50;
        if (r53.length() == 0) goto L47;
        String r64 = "get".concat(r53);     // Catch: NoSuchMethodException -> L93
    L49:
        zza(r53, r2.getMethod(r64, null).invoke(r12, null), r13, r14);
        goto L50
    L47:
        r64 = new String("get");     // Catch: NoSuchMethodException -> L93
        goto L49
    L40:
        r63 = new String("has");     // Catch: NoSuchMethodException -> L93
    L50:
        r1 = r1 + 1;
        goto L33
    L51:
        if (r11 == null) goto L115;
        r13.setLength(r0);
        r14.append(r13);
        r14.append(">\n");
        return;
    L115:
        return;
    L53:
        String r112 = zzl(r11);
        r14.append(r13);
        r14.append(r112);
        r14.append(": ");
        if ((r12 instanceof String) == false) goto L74;
        String r122 = (String) r12;
        if (r122.startsWith("http") == false) goto L58;
    L60:
        int r113 = r122.length();
        StringBuilder r22 = new StringBuilder(r113);
    L61:
        if (r1 >= r113) goto L72;
        char r33 = r122.charAt(r1);
        if (r33 >= ' ') goto L65;
    L70:
        r22.append(String.format("\\u%04x", new Object[]{Integer.valueOf(r33)}));
    L71:
        r1 = r1 + 1;
        goto L61
    L65:
        if (r33 > '~') goto L70;
        if (r33 == '\"') goto L70;
        if (r33 == '\'') goto L70;
        r22.append(r33);
        goto L71
    L72:
        String r114 = r22.toString();
        r14.append("\"");
        r14.append(r114);
        r14.append("\"");
    L91:
        r14.append("\n");
        return;
    L58:
        if (r122.length() <= 200) goto L60;
        r122 = r122.substring(0, 200).concat("[...]");
        goto L60
    L74:
        if ((r12 instanceof byte[]) == false) goto L90;
        byte[] r123 = (byte[]) r12;
        r14.append(Typography.quote);
    L77:
        if (r1 >= r123.length) goto L89;
        int r115 = r123[r1] & UByte.MAX_VALUE;
        if (r115 == 92) goto L87;
        if (r115 == 34) goto L87;
        if (r115 >= 32) goto L84;
    L86:
        r14.append(String.format("\\%03o", new Object[]{Integer.valueOf(r115)}));
    L88:
        r1 = r1 + 1;
        goto L77
    L84:
        if (r115 >= 127) goto L86;
    L85:
        r14.append((char) r115);
    L87:
        r14.append('\\');
        goto L85
    L89:
        r14.append(Typography.quote);
        goto L91
    L90:
        r14.append(r12);
        goto L91
    }

    private static String zzl(String r4) {
        StringBuffer r0 = new StringBuffer();
        int r1 = 0;
    L4:
        if (r1 >= r4.length()) goto L14;
        char r2 = r4.charAt(r1);
        if (r1 != 0) goto L10;
    L7:
        r2 = Character.toLowerCase(r2);
    L8:
        r0.append(r2);
        r1 = r1 + 1;
        goto L4
    L10:
        if (Character.isUpperCase(r2) == false) goto L8;
        r0.append('_');
        goto L7
    L14:
        return r0.toString();
    }
}
