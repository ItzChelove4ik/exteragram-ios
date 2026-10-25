package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
final class zzq extends zzbe {
    final /* synthetic */ zzw zza;

    public zzq(zzw r1, Map r2) {
        this.zza = r1;
        super(r2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        zzaz.zza(iterator());
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean containsAll(Collection r1) {
        return this.zzb.keySet().containsAll(r1);
    }

    @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
    public final boolean equals(Object r1) {
        if (this != r1) goto L4;
        return true;
    L4:
        if (this.zzb.keySet().equals(r1) == true) goto L10;
        return false;
    L10:
        return true;
    }

    @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
    public final int hashCode() {
        return this.zzb.keySet().hashCode();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzbe, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new zzp(this, this.zzb.entrySet().iterator());
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object r2) {
        Collection r22 = (Collection) this.zzb.remove(r2);
        if (r22 == null) goto L8;
        int r0 = r22.size();
        r22.clear();
        zzw r1 = this.zza;
        zzw.zzj(r1, zzw.zzd(r1) - r0);
        if (r0 <= 0) goto L10;
        return true;
    L10:
        return false;
    L8:
        return false;
    }
}
