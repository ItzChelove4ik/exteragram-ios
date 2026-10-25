package com.google.android.gms.internal.mlkit_vision_label;

import java.util.AbstractSet;
import java.util.Collection;

/* loaded from: classes4.dex */
abstract class zzca extends AbstractSet {
    public zzca() {
    }

    @Override // java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean removeAll(Collection r1) {
        return zzcb.zzb(this, r1);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean retainAll(Collection r1) {
        r1.getClass();
        return super.retainAll(r1);
    }
}
