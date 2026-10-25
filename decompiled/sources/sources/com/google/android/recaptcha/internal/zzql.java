package com.google.android.recaptcha.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
final class zzql extends zzpt {
    static final zzpt zza = null;
    final transient Object[] zzb;
    private final transient int zzc;

    static {
        zza = new zzql(new Object[0], 0);
    }

    public zzql(Object[] r1, int r2) {
        this.zzb = r1;
        this.zzc = r2;
    }

    @Override // java.util.List
    public final Object get(int r3) {
        zzoq.zze(r3, this.zzc, "index");
        Object r2 = this.zzb[r3];
        Objects.requireNonNull(r2);
        return r2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.recaptcha.internal.zzpq
    public final Object[] zzb() {
        return this.zzb;
    }

    @Override // com.google.android.recaptcha.internal.zzpq
    public final int zzc() {
        return 0;
    }

    @Override // com.google.android.recaptcha.internal.zzpq
    public final int zzd() {
        return this.zzc;
    }

    @Override // com.google.android.recaptcha.internal.zzpt, com.google.android.recaptcha.internal.zzpq
    public final int zzf(Object[] r2, int r3) {
        Object[] r32 = this.zzb;
        int r1 = this.zzc;
        System.arraycopy(r32, 0, r2, 0, r1);
        return r1;
    }
}
