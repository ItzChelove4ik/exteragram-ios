package com.google.android.gms.internal.mlkit_language_id_common;

import java.util.AbstractMap;

/* loaded from: classes4.dex */
final class zzz extends zzu {
    final /* synthetic */ zzaa zza;

    public zzz(zzaa r1) {
        this.zza = r1;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int r3) {
        zzk.zza(r3, zzaa.zzh(this.zza), "index");
        zzaa r2 = this.zza;
        int r32 = r3 + r3;
        Object r0 = zzaa.zzi(r2)[r32];
        r0.getClass();
        Object r22 = zzaa.zzi(r2)[r32 + 1];
        r22.getClass();
        return new AbstractMap.SimpleImmutableEntry(r0, r22);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return zzaa.zzh(this.zza);
    }
}
