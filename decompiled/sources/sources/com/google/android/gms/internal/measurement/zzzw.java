package com.google.android.gms.internal.measurement;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzzw extends AbstractSet {
    final /* synthetic */ zzzy zza;

    public zzzw(zzzy r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new zzzv(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zza.zzg();
    }
}
