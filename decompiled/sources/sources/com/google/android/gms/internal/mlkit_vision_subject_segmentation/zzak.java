package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
final class zzak extends AbstractCollection {
    final /* synthetic */ zzal zza;

    public zzak(zzal r1) {
        this.zza = r1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        this.zza.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        zzal r1 = this.zza;
        Map r0 = r1.zzj();
        if (r0 == null) goto L7;
        return r0.values().iterator();
    L7:
        return new zzae(r1);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        return this.zza.size();
    }
}
