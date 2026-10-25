package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
final class zzai extends AbstractSet {
    final /* synthetic */ zzal zza;

    public zzai(zzal r1) {
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
        zzal r1 = this.zza;
        Map r0 = r1.zzj();
        if (r0 == null) goto L7;
        return r0.keySet().iterator();
    L7:
        return new zzac(r1);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object r2) {
        Map r0 = this.zza.zzj();
        if (r0 == null) goto L7;
        return r0.keySet().remove(r2);
    L7:
        if (zzal.zzg(this.zza, r2) != zzal.zzi()) goto L10;
        return false;
    L10:
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zza.size();
    }
}
