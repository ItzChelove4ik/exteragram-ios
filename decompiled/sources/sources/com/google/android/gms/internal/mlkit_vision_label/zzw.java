package com.google.android.gms.internal.mlkit_vision_label;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: classes4.dex */
final class zzw extends zzbm {
    final /* synthetic */ zzy zza;

    public zzw(zzy r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_label.zzbm, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object r1) {
        Set r0 = this.zza.zza.entrySet();
        r0.getClass();
        return r0.contains(r1);
    L5:
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new zzx(this.zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object r2) {
        if (contains(r2) == true) goto L6;
        return false;
    L6:
        Map.Entry r22 = (Map.Entry) r2;
        r22.getClass();
        zzag.zzm(this.zza.zzb, r22.getKey());
        return true;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_label.zzbm
    public final Map zza() {
        return this.zza;
    }
}
