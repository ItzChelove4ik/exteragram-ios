package com.google.android.gms.internal.mlkit_genai_prompt;

import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public abstract class zzlb {
    public static String zza(String r0) {
        if (r0 != null) goto L5;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        return r0;
    }

    public static String zzb(String r1) {
        if (zzkz.zza(r1) == false) goto L6;
        return null;
    L6:
        return r1;
    }

    public static boolean zzc(String r0) {
        return zzkz.zza(r0);
    }

    public static String zzd(String r7, Object... r8) {
        StringBuilder r2 = new StringBuilder(r7.length() + (r8.length * 16));
        int r0 = 0;
        int r1 = 0;
    L3:
        int r3 = r8.length;
        if (r0 >= r3) goto L9;
        int r4 = r7.indexOf("%s", r1);
        if (r4 == (-1)) goto L9;
        r2.append(r7, r1, r4);
        r2.append(zze(r8[r0]));
        r1 = r4 + 2;
        r0 = r0 + 1;
    L9:
        r2.append(r7, r1, r7.length());
        if (r0 >= r3) goto L17;
        String r72 = " [";
    L13:
        if (r0 >= r8.length) goto L15;
        r2.append(r72);
        r2.append(zze(r8[r0]));
        r0 = r0 + 1;
        r72 = ", ";
        goto L13
    L15:
        r2.append(']');
    L17:
        return r2.toString();
    }

    private static String zze(Object r6) {
        if (r6 != null) goto L10;
        return "null";
    L10:
        return r6.toString();
    L7:
        e = move-exception;
        String r0 = r6.getClass().getName();
        String r62 = Integer.toHexString(System.identityHashCode(r6));
        int r1 = r0.length();
        StringBuilder r3 = new StringBuilder((r1 + 1) + String.valueOf(r62).length());
        r3.append(r0);
        r3.append("@");
        r3.append(r62);
        String r63 = r3.toString();
        Logger.getLogger("com.google.common.base.Strings").logp(Level.WARNING, "com.google.common.base.Strings", "lenientToString", "Exception during lenientFormat for ".concat(r63), e);
        String r02 = e.getClass().getName();
        StringBuilder r32 = new StringBuilder(((r63.length() + 8) + r02.length()) + 1);
        r32.append("<");
        r32.append(r63);
        r32.append(" threw ");
        r32.append(r02);
        r32.append(">");
        return r32.toString();
    }
}
