package com.google.android.recaptcha.internal;

import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzpx extends zzqu {
    final Iterator zza;
    Object zzb;
    Iterator zzc;
    final /* synthetic */ zzpz zzd;

    public zzpx(zzpz r2) {
        Objects.requireNonNull(r2);
        this.zzd = r2;
        this.zza = r2.map.zza().zzj().zzj(0);
        this.zzb = null;
        this.zzc = zzqf.zza;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.zzc.hasNext() == false) goto L5;
        return true;
    L5:
        if (this.zza.hasNext() == true) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        if (this.zzc.hasNext() == true) goto L5;
        Map.Entry r0 = (Map.Entry) this.zza.next();
        this.zzb = r0.getKey();
        this.zzc = ((zzpq) r0.getValue()).zza();
    L5:
        Object r02 = this.zzb;
        Objects.requireNonNull(r02);
        return new AbstractMap.SimpleImmutableEntry(r02, this.zzc.next());
    }
}
