package com.google.android.gms.internal.cast;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;

/* loaded from: classes4.dex */
final class zzzm implements zzzs {
    private final zzzi zza;
    private final zzaad zzb;
    private final boolean zzc;
    private final zzxs zzd;

    private zzzm(zzaad r1, zzxs r2, zzzi r3) {
        this.zzb = r1;
        this.zzc = false;
        this.zzd = r2;
        this.zza = r3;
    }

    public static zzzm zzi(zzaad r1, zzxs r2, zzzi r3) {
        return new zzzm(r1, r2, r3);
    }

    @Override // com.google.android.gms.internal.cast.zzzs
    public final Object zza() {
        zzzi r1 = this.zza;
        if ((r1 instanceof zzyd) == false) goto L7;
        return ((zzyd) r1).zzy();
    L7:
        return r1.zzO().zzw();
    }

    @Override // com.google.android.gms.internal.cast.zzzs
    public final boolean zzb(Object r2, Object r3) {
        if (((zzyd) r2).zzc.equals(((zzyd) r3).zzc) == true) goto L7;
        return false;
    L7:
        if (this.zzc == true) goto L10;
        return true;
    L10:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r2);
        throw null;
    }

    @Override // com.google.android.gms.internal.cast.zzzs
    public final int zzc(Object r2) {
        int r0 = ((zzyd) r2).zzc.hashCode();
        if (this.zzc == true) goto L5;
        return r0;
    L5:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r2);
        throw null;
    }

    @Override // com.google.android.gms.internal.cast.zzzs
    public final void zzd(Object r2, Object r3) {
        zzzu.zzE(this.zzb, r2, r3);
        if (this.zzc == false) goto L6;
        zzzu.zzD(this.zzd, r2, r3);
        return;
    }

    @Override // com.google.android.gms.internal.cast.zzzs
    public final int zze(Object r2) {
        int r0 = ((zzyd) r2).zzc.zze();
        if (this.zzc == true) goto L5;
        return r0;
    L5:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r2);
        throw null;
    }

    @Override // com.google.android.gms.internal.cast.zzzs
    public final void zzf(Object r1, zzaar r2) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1);
        throw null;
    }

    @Override // com.google.android.gms.internal.cast.zzzs
    public final void zzg(Object r2) {
        this.zzb.zza(r2);
        this.zzd.zza(r2);
    }

    @Override // com.google.android.gms.internal.cast.zzzs
    public final boolean zzh(Object r1) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1);
        throw null;
    }
}
