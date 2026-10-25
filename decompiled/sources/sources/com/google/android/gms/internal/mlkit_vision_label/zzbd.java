package com.google.android.gms.internal.mlkit_vision_label;

import java.util.List;

/* loaded from: classes4.dex */
final class zzbd extends zzbe {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzbe zzc;

    public zzbd(zzbe r1, int r2, int r3) {
        this.zzc = r1;
        this.zza = r2;
        this.zzb = r3;
    }

    @Override // java.util.List
    public final Object get(int r3) {
        zzs.zza(r3, this.zzb, "index");
        return this.zzc.get(r3 + this.zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_label.zzbe, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int r1, int r2) {
        return zzf(r1, r2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_label.zzaz
    public final int zzb() {
        return (this.zzc.zzc() + this.zza) + this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_label.zzaz
    public final int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_label.zzaz
    public final Object[] zze() {
        return this.zzc.zze();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_label.zzbe
    public final zzbe zzf(int r2, int r3) {
        zzs.zzc(r2, r3, this.zzb);
        zzbe r0 = this.zzc;
        int r1 = this.zza;
        return r0.zzf(r2 + r1, r3 + r1);
    }
}
