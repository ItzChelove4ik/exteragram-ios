package com.google.android.gms.internal.mlkit_vision_label;

import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
final class zzbw extends zzbh {
    private final transient zzbg zza;
    private final transient Object[] zzb;
    private final transient int zzc;

    public zzbw(zzbg r1, Object[] r2, int r3, int r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r4;
    }

    public static /* synthetic */ int zzh(zzbw r0) {
        return r0.zzc;
    }

    public static /* synthetic */ Object[] zzi(zzbw r0) {
        return r0.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_label.zzaz, java.util.AbstractCollection, java.util.Collection
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
        return zzf().zzk(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_label.zzaz
    public final int zza(Object[] r1, int r2) {
        return zzf().zza(r1, 0);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_label.zzbh
    public final zzbe zzg() {
        return new zzbv(this);
    }
}
