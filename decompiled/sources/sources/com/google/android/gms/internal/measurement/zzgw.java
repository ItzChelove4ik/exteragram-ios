package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
final class zzgw implements zzadz {
    static final zzadz zza = null;

    static {
        zza = new zzgw();
    }

    private zzgw() {
    }

    @Override // com.google.android.gms.internal.measurement.zzadz
    public final boolean zza(int r2) {
        if (r2 == 0) goto L15;
        if (r2 != 1) goto L6;
        return true;
    L6:
        if (r2 != 2) goto L8;
        return true;
    L8:
        if (r2 != 3) goto L10;
        return true;
    L10:
        if (r2 != 4) goto L12;
        return true;
    L12:
        if (r2 == 5) goto L20;
        return false;
    L20:
        return true;
    L15:
        return true;
    }
}
