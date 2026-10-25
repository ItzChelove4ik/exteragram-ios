package com.google.android.gms.internal.fido;

/* loaded from: classes4.dex */
public abstract class zzba {
    public static boolean zza(char r1) {
        if (r1 >= 'a') goto L5;
        return false;
    L5:
        if (r1 > 'z') goto L10;
        return true;
    L10:
        return false;
    }
}
