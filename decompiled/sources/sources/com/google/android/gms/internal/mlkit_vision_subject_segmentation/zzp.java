package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
final class zzp implements Iterator {
    Map.Entry zza;
    final /* synthetic */ Iterator zzb;
    final /* synthetic */ zzq zzc;

    public zzp(zzq r1, Iterator r2) {
        this.zzc = r1;
        this.zzb = r2;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zzb.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        Map.Entry r0 = (Map.Entry) this.zzb.next();
        this.zza = r0;
        return r0.getKey();
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (this.zza == null) goto L5;
        boolean r0 = true;
    L6:
        zzi.zzd(r0, "no calls to next() since the last call to remove()");
        Collection r02 = (Collection) this.zza.getValue();
        this.zzb.remove();
        zzw r1 = this.zzc.zza;
        zzw.zzj(r1, zzw.zzd(r1) - r02.size());
        r02.clear();
        this.zza = null;
        return;
    L5:
        r0 = false;
        goto L6
    }
}
