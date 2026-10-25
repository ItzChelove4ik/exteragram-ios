package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* loaded from: classes4.dex */
final class zzo extends zzbg {
    final transient Map zza;
    final /* synthetic */ zzw zzb;

    public zzo(zzw r1, Map r2) {
        this.zzb = r1;
        this.zza = r2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        zzw r0 = this.zzb;
        if (this.zza != zzw.zzg(r0)) goto L6;
        r0.zzl();
        return;
    L6:
        zzaz.zza(new zzn(this));
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object r1) {
        return zzbh.zzb(this.zza, r1);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(Object r1) {
        if (this != r1) goto L4;
        return true;
    L4:
        if (this.zza.equals(r1) == true) goto L10;
        return false;
    L10:
        return true;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final /* bridge */ /* synthetic */ Object get(Object r2) {
        Collection r0 = (Collection) zzbh.zza(this.zza, r2);
        if (r0 != null) goto L7;
        return null;
    L7:
        return this.zzb.zzb(r2, r0);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        return this.zza.hashCode();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        return this.zzb.zzo();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final /* bridge */ /* synthetic */ Object remove(Object r4) {
        Collection r42 = (Collection) this.zza.remove(r4);
        if (r42 != null) goto L6;
        return null;
    L6:
        Collection r0 = this.zzb.zza();
        r0.addAll(r42);
        zzw r3 = this.zzb;
        zzw.zzj(r3, zzw.zzd(r3) - r42.size());
        r42.clear();
        return r0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.zza.size();
    }

    @Override // java.util.AbstractMap
    public final String toString() {
        return this.zza.toString();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzbg
    public final Set zza() {
        return new zzm(this);
    }
}
