package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
final class zzbf extends AbstractCollection {
    final Map zza;

    public zzbf(Map r1) {
        this.zza = r1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        this.zza.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object r1) {
        return this.zza.containsValue(r1);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean isEmpty() {
        return this.zza.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new zzbc(this.zza.entrySet().iterator());
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean remove(Object r4) {
        return super.remove(r4);
    L4:
        Iterator r0 = this.zza.entrySet().iterator();
    L6:
        if (r0.hasNext() == false) goto L11;
        Map.Entry r1 = (Map.Entry) r0.next();
        if (zze.zza(r4, r1.getValue()) == false) goto L6;
        this.zza.remove(r1.getKey());
        return true;
    L11:
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean removeAll(Collection r5) {
        if (r5 == null) goto L6;
        return super.removeAll(r5);
    L6:
        throw null;     // Catch: UnsupportedOperationException -> L7
    L7:
        HashSet r0 = new HashSet();
        Iterator r1 = this.zza.entrySet().iterator();
    L9:
        if (r1.hasNext() == false) goto L14;
        Map.Entry r2 = (Map.Entry) r1.next();
        if (r5.contains(r2.getValue()) == false) goto L9;
        r0.add(r2.getKey());
        goto L9
    L14:
        return this.zza.keySet().removeAll(r0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean retainAll(Collection r5) {
        if (r5 == null) goto L6;
        return super.retainAll(r5);
    L6:
        throw null;     // Catch: UnsupportedOperationException -> L7
    L7:
        HashSet r0 = new HashSet();
        Iterator r1 = this.zza.entrySet().iterator();
    L9:
        if (r1.hasNext() == false) goto L14;
        Map.Entry r2 = (Map.Entry) r1.next();
        if (r5.contains(r2.getValue()) == false) goto L9;
        r0.add(r2.getKey());
        goto L9
    L14:
        return this.zza.keySet().retainAll(r0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        return this.zza.size();
    }
}
