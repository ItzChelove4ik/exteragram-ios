package com.google.android.recaptcha.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
final class zzqp extends zzpt {
    private final transient Object[] zza;
    private final transient int zzb;
    private final transient int zzc;

    public zzqp(Object[] r1, int r2, int r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // java.util.List
    public final Object get(int r3) {
        zzoq.zze(r3, this.zzc, "index");
        Object r2 = this.zza[(r3 + r3) + this.zzb];
        Objects.requireNonNull(r2);
        return r2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }
}
