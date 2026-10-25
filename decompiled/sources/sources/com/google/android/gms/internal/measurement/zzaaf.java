package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
public final class zzaaf {
    private final zzabn zza;
    private final String zzb;

    public zzaaf(zzabn r2, String r3) {
        zzabr.zza(r2, "parser");
        this.zza = r2;
        this.zzb = r3;
    }

    public final boolean equals(Object r4) {
        if ((r4 instanceof zzaaf) == false) goto L10;
        zzaaf r42 = (zzaaf) r4;
        if (this.zza.equals(r42.zza) == false) goto L10;
        if (this.zzb.equals(r42.zzb) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public final int hashCode() {
        String r0 = this.zzb;
        return this.zza.hashCode() ^ r0.hashCode();
    }

    public final zzabn zza() {
        return this.zza;
    }

    public final String zzb() {
        return this.zzb;
    }
}
