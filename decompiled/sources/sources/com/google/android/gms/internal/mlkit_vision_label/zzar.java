package com.google.android.gms.internal.mlkit_vision_label;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
final class zzar extends AbstractSet {
    final /* synthetic */ zzau zza;

    public zzar(zzau r1) {
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
        zzau r1 = this.zza;
        Map r0 = r1.zzl();
        if (r0 == null) goto L7;
        return r0.keySet().iterator();
    L7:
        return new zzam(r1);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object r2) {
        Map r0 = this.zza.zzl();
        if (r0 == null) goto L7;
        return r0.keySet().remove(r2);
    L7:
        if (zzau.zzh(this.zza, r2) != zzau.zzi()) goto L10;
        return false;
    L10:
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zza.size();
    }
}
