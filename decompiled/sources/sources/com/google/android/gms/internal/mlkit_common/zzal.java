package com.google.android.gms.internal.mlkit_common;

import java.util.Objects;

/* loaded from: classes.dex */
final class zzal extends zzaf {
    static final zzaf zza = null;
    final transient Object[] zzb;
    private final transient int zzc;

    static {
        zza = new zzal(new Object[0], 0);
    }

    public zzal(Object[] r1, int r2) {
        this.zzb = r1;
        this.zzc = r2;
    }

    @Override // java.util.List
    public final Object get(int r3) {
        zzt.zza(r3, this.zzc, "index");
        Object r2 = this.zzb[r3];
        Objects.requireNonNull(r2);
        return r2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzaf, com.google.android.gms.internal.mlkit_common.zzab
    public final int zza(Object[] r3, int r4) {
        System.arraycopy(this.zzb, 0, r3, 0, this.zzc);
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzab
    public final int zzb() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzab
    public final int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzab
    public final Object[] zze() {
        return this.zzb;
    }
}
