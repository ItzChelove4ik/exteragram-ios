package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import java.util.Iterator;

/* loaded from: classes4.dex */
final class zzbo extends zzay {
    private final transient zzax zza;
    private final transient zzav zzb;

    public zzbo(zzax r1, zzav r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzaq, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object r1) {
        if (this.zza.get(r1) == null) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return this.zzb.zzj(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zza.size();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzaq
    public final int zza(Object[] r1, int r2) {
        return this.zzb.zza(r1, 0);
    }
}
