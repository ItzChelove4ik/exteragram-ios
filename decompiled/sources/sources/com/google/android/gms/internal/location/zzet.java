package com.google.android.gms.internal.location;

import java.util.List;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzet extends zzeu {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzeu zzc;

    public zzet(zzeu r1, int r2, int r3) {
        Objects.requireNonNull(r1);
        this.zzc = r1;
        this.zza = r2;
        this.zzb = r3;
    }

    @Override // java.util.List
    public final Object get(int r3) {
        zzeo.zzb(r3, this.zzb, "index");
        return this.zzc.get(r3 + this.zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.location.zzeu, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int r1, int r2) {
        return zzh(r1, r2);
    }

    @Override // com.google.android.gms.internal.location.zzer
    public final Object[] zzb() {
        return this.zzc.zzb();
    }

    @Override // com.google.android.gms.internal.location.zzer
    public final int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    @Override // com.google.android.gms.internal.location.zzer
    public final int zzd() {
        return (this.zzc.zzc() + this.zza) + this.zzb;
    }

    @Override // com.google.android.gms.internal.location.zzer
    public final boolean zzf() {
        return true;
    }

    @Override // com.google.android.gms.internal.location.zzeu
    public final zzeu zzh(int r2, int r3) {
        zzeo.zzd(r2, r3, this.zzb);
        int r0 = this.zza;
        return this.zzc.zzh(r2 + r0, r3 + r0);
    }
}
