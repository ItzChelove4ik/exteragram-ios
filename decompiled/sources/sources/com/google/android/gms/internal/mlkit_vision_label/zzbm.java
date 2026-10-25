package com.google.android.gms.internal.mlkit_vision_label;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
abstract class zzbm extends zzca {
    public zzbm() {
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        zza().clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public abstract boolean contains(Object r1);

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        return zza().isEmpty();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_label.zzca, java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean removeAll(Collection r2) {
        if (r2 == null) goto L6;
        return zzcb.zzb(this, r2);
    L6:
        throw null;     // Catch: UnsupportedOperationException -> L7
    L8:
        return zzcb.zzc(this, r2.iterator());
    }

    @Override // com.google.android.gms.internal.mlkit_vision_label.zzca, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean retainAll(Collection r7) {
        if (r7 == null) goto L6;
        return super.retainAll(r7);
    L6:
        throw null;     // Catch: UnsupportedOperationException -> L7
    L7:
        int r0 = r7.size();
        if (r0 >= 3) goto L11;
        zzal.zza(r0, "expectedSize");
        int r02 = r0 + 1;
    L14:
        HashSet r1 = new HashSet(r02);
        Iterator r72 = r7.iterator();
    L16:
        if (r72.hasNext() == false) goto L23;
        Object r03 = r72.next();
        if (contains(r03) == false) goto L16;
        if ((r03 instanceof Map.Entry) == false) goto L16;
        r1.add(((Map.Entry) r03).getKey());
        goto L16
    L23:
        return ((zzy) zza()).zzb.zzq().retainAll(r1);
    L11:
        if (r0 >= 1073741824) goto L13;
        r02 = (int) Math.ceil(r0 / 0.75d);
        goto L14
    L13:
        r02 = Integer.MAX_VALUE;
        goto L14
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return zza().size();
    }

    public abstract Map zza();
}
