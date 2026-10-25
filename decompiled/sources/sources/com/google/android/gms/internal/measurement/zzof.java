package com.google.android.gms.internal.measurement;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
abstract class zzof extends zznp implements zznw {
    private volatile int zza;
    private zzps zzb;

    public zzof(String r1, String r2, zzph r3) {
        super("com.google.android.gms.measurement", r2, r3);
        this.zza = -1;
    }

    @Override // com.google.android.gms.internal.measurement.zznp
    public final Object zzb(zzlk r2) {
        return zzaA(this, r2, _UrlKt.FRAGMENT_ENCODE_SET);
    }

    @Override // com.google.android.gms.internal.measurement.zznw
    public final int zzf() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.measurement.zznw
    public final zzps zzg() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.measurement.zznw
    public final void zzi(int r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.internal.measurement.zznw
    public final void zzj(zzps r1) {
        this.zzb = r1;
    }
}
