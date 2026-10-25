package com.google.android.gms.internal.play_billing;

/* loaded from: classes.dex */
final class zzik implements zzfl {
    static final zzfl zza = null;

    static {
        zza = new zzik();
    }

    private zzik() {
    }

    @Override // com.google.android.gms.internal.play_billing.zzfl
    public final boolean zza(int r2) {
        if (r2 == 0) goto L13;
        if (r2 != 1) goto L6;
        zzil r22 = zzil.zzb;
    L14:
        if (r22 == null) goto L16;
        return true;
    L16:
        return false;
    L6:
        if (r2 != 2) goto L8;
        r22 = zzil.zzc;
        goto L14
    L8:
        if (r2 == 3) goto L10;
        r22 = null;
        goto L14
    L10:
        r22 = zzil.zzd;
        goto L14
    L13:
        r22 = zzil.zza;
        goto L14
    }
}
