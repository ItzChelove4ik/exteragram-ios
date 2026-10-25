package com.google.android.gms.internal.mlkit_vision_label;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
final class zzx implements Iterator {
    final Iterator zza;
    Collection zzb;
    final /* synthetic */ zzy zzc;

    public zzx(zzy r1) {
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
        zzy r2 = this.zzc;
        Object r1 = r0.getKey();
        return new zzba(r1, r2.zzb.zzb(r1, (Collection) r0.getValue()));
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (this.zzb == null) goto L5;
        boolean r0 = true;
    L6:
        zzs.zzd(r0, "no calls to next() since the last call to remove()");
        this.zza.remove();
        zzag.zzg(this.zzc.zzb, this.zzb.size());
        this.zzb.clear();
        this.zzb = null;
        return;
    L5:
        r0 = false;
        goto L6
    }
}
