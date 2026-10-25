package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import java.util.AbstractMap;

/* loaded from: classes4.dex */
final class zzbm extends zzav {
    final /* synthetic */ zzbn zza;

    public zzbm(zzbn r1) {
        this.zza = r1;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int r3) {
        zzi.zza(r3, zzbn.zzh(this.zza), "index");
        zzbn r2 = this.zza;
        int r32 = r3 + r3;
        Object r0 = zzbn.zzi(r2)[r32];
        r0.getClass();
        Object r22 = zzbn.zzi(r2)[r32 + 1];
        r22.getClass();
        return new AbstractMap.SimpleImmutableEntry(r0, r22);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return zzbn.zzh(this.zza);
    }
}
