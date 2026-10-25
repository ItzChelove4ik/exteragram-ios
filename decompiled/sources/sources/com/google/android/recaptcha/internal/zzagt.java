package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
final class zzagt {
    public zzagt() {
    }

    public static final boolean zza(Object r0) {
        if (((zzags) r0).zze() == true) goto L6;
        return true;
    L6:
        return false;
    }

    public static final Object zzb(Object r1, Object r2) {
        zzags r12 = (zzags) r1;
        zzags r22 = (zzags) r2;
        if (r22.isEmpty() == false) goto L5;
    L8:
        return r12;
    L5:
        if (r12.zze() == true) goto L7;
        r12 = r12.zzc();
    L7:
        r12.zzb(r22);
        goto L8
    }
}
