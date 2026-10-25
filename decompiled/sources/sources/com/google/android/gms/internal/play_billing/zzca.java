package com.google.android.gms.internal.play_billing;

import java.util.Objects;

/* loaded from: classes5.dex */
final class zzca extends zzbt {
    static final zzbt zza = null;
    final transient Object[] zzb;
    private final transient int zzc;

    static {
        zza = new zzca(new Object[0], 0);
    }

    public zzca(Object[] r1, int r2) {
        this.zzb = r1;
        this.zzc = r2;
    }

    @Override // java.util.List
    public final Object get(int r3) {
        zzbg.zza(r3, this.zzc, "index");
        Object r2 = this.zzb[r3];
        Objects.requireNonNull(r2);
        return r2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.play_billing.zzbt, com.google.android.gms.internal.play_billing.zzbq
    public final int zza(Object[] r2, int r3) {
        Object[] r32 = this.zzb;
        int r1 = this.zzc;
        System.arraycopy(r32, 0, r2, 0, r1);
        return r1;
    }

    @Override // com.google.android.gms.internal.play_billing.zzbq
    public final int zzb() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.play_billing.zzbq
    public final int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.play_billing.zzbq
    public final boolean zzf() {
        return false;
    }

    @Override // com.google.android.gms.internal.play_billing.zzbq
    public final Object[] zzg() {
        return this.zzb;
    }
}
