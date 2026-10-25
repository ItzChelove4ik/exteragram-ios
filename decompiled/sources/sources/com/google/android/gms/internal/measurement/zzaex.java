package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
final class zzaex {
    public zzaex() {
    }

    public static final boolean zza(Object r0) {
        if (((zzaew) r0).zze() == true) goto L6;
        return true;
    L6:
        return false;
    }

    public static final Object zzb(Object r1, Object r2) {
        zzaew r12 = (zzaew) r1;
        zzaew r22 = (zzaew) r2;
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
