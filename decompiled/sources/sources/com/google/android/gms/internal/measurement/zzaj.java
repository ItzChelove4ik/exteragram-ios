package com.google.android.gms.internal.measurement;

import java.util.Iterator;

/* loaded from: classes4.dex */
final class zzaj implements Iterator {
    final /* synthetic */ Iterator zza;

    public zzaj(Iterator r1) {
        this.zza = r1;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        return new zzas((String) this.zza.next());
    }
}
