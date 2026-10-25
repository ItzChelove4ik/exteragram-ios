package com.google.android.gms.internal.measurement;

import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzabb extends AbstractSet {
    final int zza;
    final /* synthetic */ zzabc zzb;

    public zzabb(zzabc r1, int r2) {
        Objects.requireNonNull(r1);
        this.zzb = r1;
        this.zza = r2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object r5) {
        int r0 = this.zza;
        int r1 = zzb();
        int r2 = zzc();
        if (r0 != (-1)) goto L5;
        Comparator r02 = zzabc.zza();
    L7:
        if (Arrays.binarySearch(this.zzb.zzb(), r1, r2, r5, r02) < 0) goto L10;
        return true;
    L10:
        return false;
    L5:
        r02 = zzabe.zze();
        goto L7
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new zzaba(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return zzc() - zzb();
    }

    public final Object zza(int r2) {
        int r0 = zzb() + r2;
        return this.zzb.zzb()[r0];
    }

    public final int zzb() {
        int r0 = this.zza;
        if (r0 != (-1)) goto L7;
        return 0;
    L7:
        return this.zzb.zzc()[r0];
    }

    public final int zzc() {
        int r0 = this.zza;
        return this.zzb.zzc()[r0 + 1];
    }
}
