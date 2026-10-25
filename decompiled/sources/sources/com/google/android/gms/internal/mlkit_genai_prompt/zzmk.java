package com.google.android.gms.internal.mlkit_genai_prompt;

import java.util.List;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzmk extends zzml {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzml zzc;

    public zzmk(zzml r1, int r2, int r3) {
        Objects.requireNonNull(r1);
        this.zzc = r1;
        this.zza = r2;
        this.zzb = r3;
    }

    @Override // java.util.List
    public final Object get(int r3) {
        zzla.zzf(r3, this.zzb, "index");
        return this.zzc.get(r3 + this.zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzml, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int r1, int r2) {
        return zzi(r1, r2);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzmg
    public final Object[] zzb() {
        return this.zzc.zzb();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzmg
    public final int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzmg
    public final int zzd() {
        return (this.zzc.zzc() + this.zza) + this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzmg
    public final boolean zzf() {
        return true;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzml
    public final zzml zzi(int r2, int r3) {
        zzla.zzh(r2, r3, this.zzb);
        int r0 = this.zza;
        return this.zzc.zzi(r2 + r0, r3 + r0);
    }
}
