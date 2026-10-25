package com.google.android.gms.internal.play_billing;

import java.util.AbstractMap;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzcb extends zzbt {
    final /* synthetic */ zzcc zza;

    public zzcb(zzcc r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int r3) {
        zzcc r2 = this.zza;
        zzbg.zza(r3, zzcc.zzi(r2), "index");
        int r32 = r3 + r3;
        Object r0 = zzcc.zzj(r2)[r32];
        Objects.requireNonNull(r0);
        Object r22 = zzcc.zzj(r2)[r32 + 1];
        Objects.requireNonNull(r22);
        return new AbstractMap.SimpleImmutableEntry(r0, r22);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return zzcc.zzi(this.zza);
    }

    @Override // com.google.android.gms.internal.play_billing.zzbq
    public final boolean zzf() {
        return true;
    }
}
