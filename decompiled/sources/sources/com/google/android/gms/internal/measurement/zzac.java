package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Objects;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
final class zzac implements Iterator {
    final /* synthetic */ Iterator zza;
    final /* synthetic */ Iterator zzb;

    public zzac(zzae r1, Iterator r2, Iterator r3) {
        this.zza = r2;
        this.zzb = r3;
        Objects.requireNonNull(r1);
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.zza.hasNext() == false) goto L7;
        return true;
    L7:
        return this.zzb.hasNext();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        Iterator r0 = this.zza;
        if (r0.hasNext() == true) goto L5;
        Iterator r2 = this.zzb;
        if (r2.hasNext() == true) goto L9;
        Utils$$ExternalSyntheticBUOutline0.m();
        return null;
    L9:
        return new zzas((String) r2.next());
    L5:
        return new zzas(((Integer) r0.next()).toString());
    }
}
