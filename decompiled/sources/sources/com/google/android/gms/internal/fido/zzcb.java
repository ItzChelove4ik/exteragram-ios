package com.google.android.gms.internal.fido;

import java.util.List;

/* loaded from: classes4.dex */
final class zzcb extends zzcc {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzcc zzc;

    public zzcb(zzcc r1, int r2, int r3) {
        this.zzc = r1;
        this.zza = r2;
        this.zzb = r3;
    }

    @Override // java.util.List
    public final Object get(int r3) {
        zzbm.zza(r3, this.zzb, "index");
        return this.zzc.get(r3 + this.zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.fido.zzcc, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int r1, int r2) {
        return zzg(r1, r2);
    }

    @Override // com.google.android.gms.internal.fido.zzby
    public final int zzb() {
        return (this.zzc.zzc() + this.zza) + this.zzb;
    }

    @Override // com.google.android.gms.internal.fido.zzby
    public final int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    @Override // com.google.android.gms.internal.fido.zzby
    public final Object[] zze() {
        return this.zzc.zze();
    }

    @Override // com.google.android.gms.internal.fido.zzcc
    public final zzcc zzg(int r2, int r3) {
        zzbm.zze(r2, r3, this.zzb);
        int r0 = this.zza;
        return this.zzc.zzg(r2 + r0, r3 + r0);
    }
}
