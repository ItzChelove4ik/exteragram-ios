package com.google.android.gms.internal.mlkit_vision_label;

import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
abstract class zzbn extends zzca {
    final Map zzb;

    public zzbn(Map r1) {
        r1.getClass();
        this.zzb = r1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object r1) {
        return this.zzb.containsKey(r1);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        return this.zzb.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public abstract Iterator iterator();

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zzb.size();
    }
}
