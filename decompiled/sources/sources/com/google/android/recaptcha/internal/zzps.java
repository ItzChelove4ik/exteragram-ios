package com.google.android.recaptcha.internal;

import java.util.List;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzps extends zzpt {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzpt zzc;

    public zzps(zzpt r1, int r2, int r3) {
        Objects.requireNonNull(r1);
        this.zzc = r1;
        this.zza = r2;
        this.zzb = r3;
    }

    @Override // java.util.List
    public final Object get(int r3) {
        zzoq.zze(r3, this.zzb, "index");
        return this.zzc.get(r3 + this.zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzb;
    }

    @Override // com.google.android.recaptcha.internal.zzpt, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int r1, int r2) {
        return zzg(r1, r2);
    }

    @Override // com.google.android.recaptcha.internal.zzpq
    public final Object[] zzb() {
        return this.zzc.zzb();
    }

    @Override // com.google.android.recaptcha.internal.zzpq
    public final int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    @Override // com.google.android.recaptcha.internal.zzpq
    public final int zzd() {
        return (this.zzc.zzc() + this.zza) + this.zzb;
    }

    @Override // com.google.android.recaptcha.internal.zzpt
    public final zzpt zzg(int r2, int r3) {
        zzoq.zzg(r2, r3, this.zzb);
        int r0 = this.zza;
        return this.zzc.zzg(r2 + r0, r3 + r0);
    }
}
