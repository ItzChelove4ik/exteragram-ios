package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
final class zzn implements Iterator {
    final Iterator zza;
    Collection zzb;
    final /* synthetic */ zzo zzc;

    public zzn(zzo r1) {
        this.zzc = r1;
        this.zza = r1.zza.entrySet().iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        Map.Entry r0 = (Map.Entry) this.zza.next();
        this.zzb = (Collection) r0.getValue();
        Object r1 = r0.getKey();
        Collection r02 = (Collection) r0.getValue();
        return new zzar(r1, this.zzc.zzb.zzb(r1, r02));
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (this.zzb == null) goto L5;
        boolean r0 = true;
    L6:
        zzi.zzd(r0, "no calls to next() since the last call to remove()");
        this.zza.remove();
        zzw r02 = this.zzc.zzb;
        zzw.zzj(r02, zzw.zzd(r02) - this.zzb.size());
        this.zzb.clear();
        this.zzb = null;
        return;
    L5:
        r0 = false;
        goto L6
    }
}
