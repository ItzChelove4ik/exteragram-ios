package com.google.android.gms.internal.cast;

import java.util.Objects;

/* loaded from: classes4.dex */
final class zzic extends zzhv {
    static final zzhv zza = null;
    final transient Object[] zzb;
    private final transient int zzc;

    static {
        zza = new zzic(new Object[0], 0);
    }

    public zzic(Object[] r1, int r2) {
        this.zzb = r1;
        this.zzc = r2;
    }

    @Override // java.util.List
    public final Object get(int r3) {
        zzhd.zzb(r3, this.zzc, "index");
        Object r2 = this.zzb[r3];
        Objects.requireNonNull(r2);
        return r2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.cast.zzhr
    public final Object[] zzb() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.cast.zzhr
    public final int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.cast.zzhr
    public final int zzd() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.cast.zzhr
    public final boolean zzf() {
        return false;
    }

    @Override // com.google.android.gms.internal.cast.zzhv, com.google.android.gms.internal.cast.zzhr
    public final int zzg(Object[] r2, int r3) {
        Object[] r32 = this.zzb;
        int r1 = this.zzc;
        System.arraycopy(r32, 0, r2, 0, r1);
        return r1;
    }
}
