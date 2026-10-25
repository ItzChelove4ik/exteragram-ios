package com.google.android.gms.internal.vision;

import java.util.Iterator;

/* loaded from: classes5.dex */
final class zzmb implements Iterator<String> {
    private Iterator<String> zza;
    private final /* synthetic */ zzlz zzb;

    public zzmb(zzlz r1) {
        this.zzb = r1;
        this.zza = zzlz.zza(r1).iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    @Override // java.util.Iterator
    public final /* synthetic */ String next() {
        return this.zza.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
