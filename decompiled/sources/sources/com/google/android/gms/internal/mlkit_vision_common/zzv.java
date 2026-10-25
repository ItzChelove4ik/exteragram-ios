package com.google.android.gms.internal.mlkit_vision_common;

import java.util.AbstractMap;

/* loaded from: classes4.dex */
final class zzv extends zzp {
    final /* synthetic */ zzw zza;

    public zzv(zzw r1) {
        this.zza = r1;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int r3) {
        zzf.zza(r3, zzw.zzh(this.zza), "index");
        zzw r2 = this.zza;
        int r32 = r3 + r3;
        Object r0 = zzw.zzi(r2)[r32];
        r0.getClass();
        Object r22 = zzw.zzi(r2)[r32 + 1];
        r22.getClass();
        return new AbstractMap.SimpleImmutableEntry(r0, r22);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return zzw.zzh(this.zza);
    }
}
