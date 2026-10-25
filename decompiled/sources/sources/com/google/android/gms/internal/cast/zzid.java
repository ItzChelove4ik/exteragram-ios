package com.google.android.gms.internal.cast;

import java.util.AbstractMap;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzid extends zzhv {
    final /* synthetic */ zzie zza;

    public zzid(zzie r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int r3) {
        zzie r2 = this.zza;
        zzhd.zzb(r3, r2.zzn(), "index");
        int r32 = r3 + r3;
        Object r0 = r2.zzm()[r32];
        Objects.requireNonNull(r0);
        Object r22 = r2.zzm()[r32 + 1];
        Objects.requireNonNull(r22);
        return new AbstractMap.SimpleImmutableEntry(r0, r22);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.zzn();
    }

    @Override // com.google.android.gms.internal.cast.zzhr
    public final boolean zzf() {
        return true;
    }
}
