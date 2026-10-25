package com.google.android.gms.internal.measurement;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public abstract class zzabr {
    public static Object zza(Object r0, String r1) {
        if (r0 == null) goto L4;
        return r0;
    L4:
        f$$ExternalSyntheticBUOutline2.m(r1.concat(" must not be null"));
        return null;
    }

    public static void zzb(boolean r0, String r1) {
        if (r0 == false) goto L4;
        return;
    L4:
        f$$ExternalSyntheticBUOutline1.m(r1);
    }

    public static void zzc(boolean r0, String r1) {
        if (r0 == false) goto L4;
        return;
    L4:
        Segment$$ExternalSyntheticBUOutline1.m(r1);
    }

    public static String zzd(String r4) {
        if (zze(r4.charAt(0)) == false) goto L20;
        int r0 = 1;
    L6:
        if (r0 >= r4.length()) goto L19;
        char r2 = r4.charAt(r0);
        if (zze(r2) == true) goto L18;
        if (r2 < '0') goto L14;
        if (r2 <= '9') goto L18;
    L14:
        if (r2 == '_') goto L18;
        f$$ExternalSyntheticBUOutline1.m("identifier must contain only ASCII letters, digits or underscore: ".concat(r4));
        return null;
    L18:
        r0 = r0 + 1;
        goto L6
    L19:
        return r4;
    L20:
        f$$ExternalSyntheticBUOutline1.m("identifier must start with an ASCII letter: ".concat(r4));
        return null;
    }

    private static boolean zze(char r3) {
        if (r3 < 'a') goto L9;
        if (r3 > 'z') goto L9;
        return true;
    L9:
        if (r3 >= 'A') goto L11;
    L13:
        return false;
    L11:
        if (r3 > 'Z') goto L13;
        return true;
    }
}
