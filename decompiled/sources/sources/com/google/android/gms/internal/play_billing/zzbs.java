package com.google.android.gms.internal.play_billing;

import java.util.List;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzbs extends zzbt {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzbt zzc;

    public zzbs(zzbt r1, int r2, int r3) {
        Objects.requireNonNull(r1);
        this.zzc = r1;
        this.zza = r2;
        this.zzb = r3;
    }

    @Override // java.util.List
    public final Object get(int r3) {
        zzbg.zza(r3, this.zzb, "index");
        return this.zzc.get(r3 + this.zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.play_billing.zzbt, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int r1, int r2) {
        return zzh(r1, r2);
    }

    @Override // com.google.android.gms.internal.play_billing.zzbq
    public final int zzb() {
        return (this.zzc.zzc() + this.zza) + this.zzb;
    }

    @Override // com.google.android.gms.internal.play_billing.zzbq
    public final int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    @Override // com.google.android.gms.internal.play_billing.zzbq
    public final boolean zzf() {
        return true;
    }

    @Override // com.google.android.gms.internal.play_billing.zzbq
    public final Object[] zzg() {
        return this.zzc.zzg();
    }

    @Override // com.google.android.gms.internal.play_billing.zzbt
    public final zzbt zzh(int r2, int r3) {
        zzbg.zzd(r2, r3, this.zzb);
        int r0 = this.zza;
        return this.zzc.zzh(r2 + r0, r3 + r0);
    }
}
