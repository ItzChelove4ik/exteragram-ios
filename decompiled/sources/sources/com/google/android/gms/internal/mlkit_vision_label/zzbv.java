package com.google.android.gms.internal.mlkit_vision_label;

import java.util.AbstractMap;

/* loaded from: classes4.dex */
final class zzbv extends zzbe {
    final /* synthetic */ zzbw zza;

    public zzbv(zzbw r1) {
        this.zza = r1;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int r3) {
        zzs.zza(r3, zzbw.zzh(this.zza), "index");
        zzbw r2 = this.zza;
        int r32 = r3 + r3;
        Object r0 = zzbw.zzi(r2)[r32];
        r0.getClass();
        Object r22 = zzbw.zzi(r2)[r32 + 1];
        r22.getClass();
        return new AbstractMap.SimpleImmutableEntry(r0, r22);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return zzbw.zzh(this.zza);
    }
}
