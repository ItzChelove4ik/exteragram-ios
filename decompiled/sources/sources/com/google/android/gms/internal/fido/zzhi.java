package com.google.android.gms.internal.fido;

import java.util.Arrays;

/* loaded from: classes4.dex */
public final class zzhi extends zzhp {
    private final zzgx zza;

    public zzhi(zzgx r1) {
        this.zza = r1;
    }

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Object r4) {
        zzhp r42 = (zzhp) r4;
        int r0 = r42.zza();
        if (zzhp.zzd((byte) 64) == r0) goto L6;
        int r3 = r42.zza();
        return zzhp.zzd((byte) 64) - r3;
    L6:
        zzhi r43 = (zzhi) r42;
        zzgx r32 = this.zza;
        int r02 = r32.zzd();
        zzgx r1 = r43.zza;
        if (r02 != r1.zzd()) goto L9;
        byte[] r33 = r32.zzm();
        byte[] r44 = r43.zza.zzm();
        return zzgm.zza().compare(r33, r44);
    L9:
        return r32.zzd() - r1.zzd();
    }

    public final boolean equals(Object r4) {
        if (this != r4) goto L6;
        return true;
    L6:
        if (r4 != null) goto L9;
        return false;
    L9:
        if (zzhi.class == r4.getClass()) goto L12;
        return false;
    L12:
        return this.zza.equals(((zzhi) r4).zza);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(zzhp.zzd((byte) 64)), this.zza});
    }

    public final String toString() {
        zzgf r0 = zzgf.zzf().zzd();
        byte[] r3 = this.zza.zzm();
        return "h'" + r0.zzg(r3, 0, r3.length) + "'";
    }

    @Override // com.google.android.gms.internal.fido.zzhp
    public final int zza() {
        return zzhp.zzd((byte) 64);
    }

    public final zzgx zzc() {
        return this.zza;
    }
}
