package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzzx implements Iterator {
    final /* synthetic */ zzzy zza;
    private final zzyl zzb;
    private int zzc;
    private int zzd;

    public /* synthetic */ zzzx(zzzy r1, zzyl r2, int r3, byte[] r4) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        this.zzb = r2;
        int r12 = r3 & 31;
        this.zzc = r12;
        this.zzd = r3 >>> (r12 + 5);
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.zzc < 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        Object r0 = this.zzb.zze(this.zza.zze(this.zzc));
        int r1 = this.zzd;
        if (r1 == 0) goto L6;
        int r12 = Integer.numberOfTrailingZeros(r1) + 1;
        this.zzd >>>= r12;
        this.zzc += r12;
        return r0;
    L6:
        this.zzc = -1;
        return r0;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
