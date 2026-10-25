package com.google.android.gms.internal.play_billing;

import java.util.Iterator;
import java.util.Map;

/* loaded from: classes5.dex */
final class zzcc extends zzbx {
    private final transient zzbw zza;
    private final transient Object[] zzb;
    private final transient int zzc;

    public zzcc(zzbw r1, Object[] r2, int r3, int r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r4;
    }

    public static /* bridge */ /* synthetic */ int zzi(zzcc r0) {
        return r0.zzc;
    }

    public static /* bridge */ /* synthetic */ Object[] zzj(zzcc r0) {
        return r0.zzb;
    }

    @Override // com.google.android.gms.internal.play_billing.zzbq, java.util.AbstractCollection, java.util.Collection
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
        return zzd().zzn(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.play_billing.zzbq
    public final int zza(Object[] r1, int r2) {
        return zzd().zza(r1, 0);
    }

    @Override // com.google.android.gms.internal.play_billing.zzbx
    public final zzbt zzh() {
        return new zzcb(this);
    }
}
