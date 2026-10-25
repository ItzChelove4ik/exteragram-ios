package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzad implements Iterator {
    final /* synthetic */ zzae zza;
    private int zzb;

    public zzad(zzae r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        this.zzb = 0;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        zzae r0 = this.zza;
        if (this.zzb >= r0.zzh()) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        zzae r0 = this.zza;
        int r1 = this.zzb;
        int r2 = r0.zzh();
        int r3 = this.zzb;
        if (r1 >= r2) goto L6;
        this.zzb = r3 + 1;
        return r0.zzl(r3);
    L6:
        StringBuilder r12 = new StringBuilder(String.valueOf(r3).length() + 21);
        r12.append("Out of bounds index: ");
        r12.append(r3);
        throw new NoSuchElementException(r12.toString());
    }
}
