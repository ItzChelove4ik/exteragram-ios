package com.google.android.gms.internal.cast;

import java.util.Objects;

/* loaded from: classes4.dex */
final class zzig extends zzhv {
    private final transient Object[] zza;
    private final transient int zzb;
    private final transient int zzc;

    public zzig(Object[] r1, int r2, int r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // java.util.List
    public final Object get(int r3) {
        zzhd.zzb(r3, this.zzc, "index");
        Object r2 = this.zza[(r3 + r3) + this.zzb];
        Objects.requireNonNull(r2);
        return r2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.cast.zzhr
    public final boolean zzf() {
        return true;
    }
}
