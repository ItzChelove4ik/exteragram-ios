package com.google.android.gms.internal.mlkit_common;

import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
final class zzan extends zzaj {
    private final transient zzai zza;
    private final transient Object[] zzb;
    private final transient int zzc;

    public zzan(zzai r1, Object[] r2, int r3, int r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r4;
    }

    public static /* bridge */ /* synthetic */ int zzh(zzan r0) {
        return r0.zzc;
    }

    public static /* bridge */ /* synthetic */ Object[] zzi(zzan r0) {
        return r0.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzab, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object r3) {
        if ((r3 instanceof Map.Entry) == false) goto L10;
        Map.Entry r32 = (Map.Entry) r3;
        Object r0 = r32.getKey();
        Object r33 = r32.getValue();
        if (r33 == null) goto L10;
        if (r33.equals(this.zza.get(r0)) == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return zzf().zzj(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzab
    public final int zza(Object[] r1, int r2) {
        return zzf().zza(r1, 0);
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzaj
    public final zzaf zzg() {
        return new zzam(this);
    }
}
