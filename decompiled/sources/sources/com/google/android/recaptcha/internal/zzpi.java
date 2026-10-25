package com.google.android.recaptcha.internal;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzpi extends AbstractCollection {
    final /* synthetic */ zzpj zza;

    public /* synthetic */ zzpi(zzpj r1, byte[] r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        this.zza.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        zzpj r1 = this.zza;
        Map r0 = r1.zzb();
        if (r0 == null) goto L7;
        return r0.values().iterator();
    L7:
        return new zzpd(r1);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        return this.zza.size();
    }
}
