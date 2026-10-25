package com.google.android.gms.internal.measurement;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzafu extends AbstractSet {
    final /* synthetic */ zzafv zza;

    public /* synthetic */ zzafu(zzafv r1, byte[] r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final /* bridge */ /* synthetic */ boolean add(Object r2) {
        Map.Entry r22 = (Map.Entry) r2;
        if (contains(r22) == true) goto L6;
        this.zza.zzf((Comparable) r22.getKey(), r22.getValue());
        return true;
    L6:
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.zza.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object r3) {
        Map.Entry r32 = (Map.Entry) r3;
        Object r0 = r32.getKey();
        Object r2 = this.zza.get(r0);
        Object r33 = r32.getValue();
        if (r2 != r33) goto L5;
        return true;
    L5:
        if (r2 != null) goto L7;
    L9:
        return false;
    L7:
        if (r2.equals(r33) == false) goto L9;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new zzaft(this.zza, null);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object r2) {
        Map.Entry r22 = (Map.Entry) r2;
        if (contains(r22) == false) goto L6;
        this.zza.remove(r22.getKey());
        return true;
    L6:
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zza.size();
    }
}
