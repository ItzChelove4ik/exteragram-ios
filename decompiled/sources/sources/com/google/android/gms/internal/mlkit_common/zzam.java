package com.google.android.gms.internal.mlkit_common;

import java.util.AbstractMap;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzam extends zzaf {
    final /* synthetic */ zzan zza;

    public zzam(zzan r1) {
        this.zza = r1;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int r3) {
        zzt.zza(r3, zzan.zzh(this.zza), "index");
        int r32 = r3 + r3;
        Object r0 = zzan.zzi(this.zza)[r32];
        Objects.requireNonNull(r0);
        Object r2 = zzan.zzi(this.zza)[r32 + 1];
        Objects.requireNonNull(r2);
        return new AbstractMap.SimpleImmutableEntry(r0, r2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return zzan.zzh(this.zza);
    }
}
