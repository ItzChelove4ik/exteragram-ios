package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: classes4.dex */
final class zzm extends zzbd {
    final /* synthetic */ zzo zza;

    public zzm(zzo r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzbd, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object r1) {
        Set r0 = this.zza.zza.entrySet();
        r0.getClass();
        return r0.contains(r1);
    L5:
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new zzn(this.zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object r2) {
        if (contains(r2) == true) goto L6;
        return false;
    L6:
        Map.Entry r22 = (Map.Entry) r2;
        r22.getClass();
        zzo r1 = this.zza;
        Object r23 = r22.getKey();
        zzw.zzk(r1.zzb, r23);
        return true;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzbd
    public final Map zza() {
        return this.zza;
    }
}
