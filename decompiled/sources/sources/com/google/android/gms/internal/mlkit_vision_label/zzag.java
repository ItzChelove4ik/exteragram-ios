package com.google.android.gms.internal.mlkit_vision_label;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;
import java.util.Set;
import okio.Buffer$$ExternalSyntheticBUOutline2;
import okio.Segment$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
abstract class zzag extends zzai implements Serializable {
    private transient Map zza;
    private transient int zzb;

    public zzag(Map r2) {
        if (r2.isEmpty() == false) goto L6;
        this.zza = r2;
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline0.m();
        throw null;
    }

    public static /* synthetic */ int zzd(zzag r2) {
        int r0 = r2.zzb;
        r2.zzb = r0 + 1;
        return r0;
    }

    public static /* synthetic */ int zze(zzag r2) {
        int r0 = r2.zzb;
        r2.zzb = r0 - 1;
        return r0;
    }

    public static /* synthetic */ int zzf(zzag r1, int r2) {
        int r0 = r1.zzb + r2;
        r1.zzb = r0;
        return r0;
    }

    public static /* synthetic */ int zzg(zzag r1, int r2) {
        int r0 = r1.zzb - r2;
        r1.zzb = r0;
        return r0;
    }

    public static /* synthetic */ Map zzj(zzag r0) {
        return r0.zza;
    }

    public static /* synthetic */ void zzm(zzag r1, Object r2) {
        Map r0 = r1.zza;
        r0.getClass();
        Object r22 = r0.remove(r2);     // Catch: Throwable -> L5
    L6:
        Collection r23 = (Collection) r22;
        if (r23 == null) goto L12;
        int r02 = r23.size();
        r23.clear();
        r1.zzb -= r02;
        return;
    L12:
        return;
    L5:
        r22 = null;
        goto L6
    }

    public abstract Collection zza();

    public abstract Collection zzb(Object r1, Collection r2);

    public final Collection zzh(Object r2) {
        Collection r0 = (Collection) this.zza.get(r2);
        if (r0 != null) goto L6;
        r0 = zza();
    L6:
        return zzb(r2, r0);
    }

    public final List zzi(Object r2, List r3, zzad r4) {
        if ((r3 instanceof RandomAccess) == false) goto L7;
        return new zzab(this, r2, r3, r4);
    L7:
        return new zzaf(this, r2, r3, r4);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_label.zzai
    public final Map zzk() {
        return new zzy(this, this.zza);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_label.zzai
    public final Set zzl() {
        return new zzaa(this, this.zza);
    }

    public final void zzn() {
        Iterator r0 = this.zza.values().iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        ((Collection) r0.next()).clear();
        goto L4
    L6:
        this.zza.clear();
        this.zzb = 0;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_label.zzbr
    public final boolean zzo(Object r4, Object r5) {
        Collection r0 = (Collection) this.zza.get(r4);
        if (r0 != null) goto L11;
        Collection r02 = zza();
        if (r02.add(r5) == false) goto L8;
        this.zzb++;
        this.zza.put(r4, r02);
        return true;
    L8:
        Buffer$$ExternalSyntheticBUOutline2.m("New Collection violated the Collection spec");
        return false;
    L11:
        if (r0.add(r5) == false) goto L14;
        this.zzb++;
        return true;
    L14:
        return false;
    }
}
