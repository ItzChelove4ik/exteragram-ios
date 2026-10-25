package com.google.android.recaptcha.internal;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzpg extends AbstractSet {
    final /* synthetic */ zzpj zza;

    public /* synthetic */ zzpg(zzpj r1, byte[] r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.zza.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object r1) {
        return this.zza.containsKey(r1);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        zzpj r1 = this.zza;
        Map r0 = r1.zzb();
        if (r0 == null) goto L7;
        return r0.keySet().iterator();
    L7:
        return new zzpb(r1);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object r2) {
        zzpj r1 = this.zza;
        Map r0 = r1.zzb();
        if (r0 == null) goto L7;
        return r0.keySet().remove(r2);
    L7:
        if (r1.zzi(r2) != zzpj.zzq()) goto L10;
        return false;
    L10:
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zza.size();
    }
}
