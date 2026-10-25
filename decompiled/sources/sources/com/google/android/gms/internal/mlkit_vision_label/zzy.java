package com.google.android.gms.internal.mlkit_vision_label;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* loaded from: classes4.dex */
final class zzy extends zzbp {
    final transient Map zza;
    final /* synthetic */ zzag zzb;

    public zzy(zzag r1, Map r2) {
        this.zzb = r1;
        this.zza = r2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        Map r0 = this.zza;
        zzag r1 = this.zzb;
        if (r0 != zzag.zzj(r1)) goto L6;
        r1.zzn();
        return;
    L6:
        zzbi.zza(new zzx(this));
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object r1) {
        return zzbq.zzb(this.zza, r1);
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
        Collection r0 = (Collection) zzbq.zza(this.zza, r2);
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
        return this.zzb.zzq();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final /* bridge */ /* synthetic */ Object remove(Object r3) {
        Collection r32 = (Collection) this.zza.remove(r3);
        if (r32 != null) goto L6;
        return null;
    L6:
        Collection r0 = this.zzb.zza();
        r0.addAll(r32);
        zzag.zzg(this.zzb, r32.size());
        r32.clear();
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

    @Override // com.google.android.gms.internal.mlkit_vision_label.zzbp
    public final Set zza() {
        return new zzw(this);
    }
}
