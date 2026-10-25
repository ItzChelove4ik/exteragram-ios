package com.google.android.recaptcha.internal;

import java.util.Iterator;

/* loaded from: classes5.dex */
final class zzqo extends zzqb {
    private final transient zzpw zza;
    private final transient zzpt zzb;

    public zzqo(zzpw r1, zzpt r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.android.recaptcha.internal.zzpq, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object r1) {
        if (this.zza.get(r1) == null) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return this.zzb.zzj(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zza.size();
    }

    @Override // com.google.android.recaptcha.internal.zzqb, com.google.android.recaptcha.internal.zzpq
    public final zzqu zza() {
        return this.zzb.zzj(0);
    }

    @Override // com.google.android.recaptcha.internal.zzpq
    public final boolean zze() {
        return true;
    }

    @Override // com.google.android.recaptcha.internal.zzpq
    public final int zzf(Object[] r1, int r2) {
        return this.zzb.zzf(r1, 0);
    }
}
