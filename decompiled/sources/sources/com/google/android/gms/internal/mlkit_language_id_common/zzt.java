package com.google.android.gms.internal.mlkit_language_id_common;

import java.util.List;

/* loaded from: classes4.dex */
final class zzt extends zzu {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzu zzc;

    public zzt(zzu r1, int r2, int r3) {
        this.zzc = r1;
        this.zza = r2;
        this.zzb = r3;
    }

    @Override // java.util.List
    public final Object get(int r3) {
        zzk.zza(r3, this.zzb, "index");
        return this.zzc.get(r3 + this.zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzu, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int r1, int r2) {
        return zzf(r1, r2);
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzq
    public final int zzb() {
        return (this.zzc.zzc() + this.zza) + this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzq
    public final int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzq
    public final Object[] zze() {
        return this.zzc.zze();
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzu
    public final zzu zzf(int r2, int r3) {
        zzk.zzc(r2, r3, this.zzb);
        zzu r0 = this.zzc;
        int r1 = this.zza;
        return r0.zzf(r2 + r1, r3 + r1);
    }
}
