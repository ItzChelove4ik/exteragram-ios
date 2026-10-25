package com.google.android.gms.internal.mlkit_vision_label;

import androidx.collection.ArraySet$$ExternalSyntheticBUOutline0;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
class zzac implements Iterator {
    final Iterator zza;
    final Collection zzb;
    final /* synthetic */ zzad zzc;

    public zzac(zzad r2) {
        this.zzc = r2;
        Collection r22 = r2.zzb;
        this.zzb = r22;
        if ((r22 instanceof List) == false) goto L5;
        Iterator r23 = ((List) r22).listIterator();
    L6:
        this.zza = r23;
        return;
    L5:
        r23 = r22.iterator();
        goto L6
    }

    public zzac(zzad r1, Iterator r2) {
        this.zzc = r1;
        this.zzb = r1.zzb;
        this.zza = r2;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        zza();
        return this.zza.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        zza();
        return this.zza.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        this.zza.remove();
        zzag.zze(this.zzc.zze);
        this.zzc.zzc();
    }

    public final void zza() {
        this.zzc.zzb();
        if (this.zzc.zzb != this.zzb) goto L5;
        return;
    L5:
        ArraySet$$ExternalSyntheticBUOutline0.m();
    }
}
