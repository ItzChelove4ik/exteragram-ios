package com.google.android.gms.internal.cast;

/* loaded from: classes4.dex */
public abstract class zzhb {
    public static String zza(String r4) {
        int r0 = r4.length();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L15;
        if (zzb(r4.charAt(r1)) == true) goto L6;
        r1 = r1 + 1;
        goto L3
    L6:
        char[] r42 = r4.toCharArray();
    L7:
        if (r1 >= r0) goto L13;
        char r2 = r42[r1];
        if (zzb(r2) == false) goto L11;
        r42[r1] = (char) (r2 ^ ' ');
    L11:
        r1 = r1 + 1;
        goto L7
    L13:
        return String.valueOf(r42);
    L15:
        return r4;
    }

    public static boolean zzb(char r1) {
        if (r1 >= 'a') goto L5;
        return false;
    L5:
        if (r1 > 'z') goto L10;
        return true;
    L10:
        return false;
    }
}
