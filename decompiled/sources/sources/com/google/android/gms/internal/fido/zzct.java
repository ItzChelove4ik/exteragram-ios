package com.google.android.gms.internal.fido;

import java.util.Objects;

/* loaded from: classes4.dex */
final class zzct extends zzcc {
    static final zzcc zza = null;
    final transient Object[] zzb;
    private final transient int zzc;

    static {
        zza = new zzct(new Object[0], 0);
    }

    public zzct(Object[] r1, int r2) {
        this.zzb = r1;
        this.zzc = r2;
    }

    @Override // java.util.List
    public final Object get(int r3) {
        zzbm.zza(r3, this.zzc, "index");
        Object r2 = this.zzb[r3];
        Objects.requireNonNull(r2);
        return r2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.fido.zzcc, com.google.android.gms.internal.fido.zzby
    public final int zza(Object[] r3, int r4) {
        System.arraycopy(this.zzb, 0, r3, 0, this.zzc);
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.fido.zzby
    public final int zzb() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.fido.zzby
    public final int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.fido.zzby
    public final Object[] zze() {
        return this.zzb;
    }
}
