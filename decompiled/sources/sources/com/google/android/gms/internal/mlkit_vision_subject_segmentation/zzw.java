package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

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
abstract class zzw extends zzy implements Serializable {
    private transient Map zza;
    private transient int zzb;

    public zzw(Map r2) {
        if (r2.isEmpty() == false) goto L6;
        this.zza = r2;
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline0.m();
        throw null;
    }

    public static /* bridge */ /* synthetic */ int zzd(zzw r0) {
        return r0.zzb;
    }

    public static /* bridge */ /* synthetic */ Map zzg(zzw r0) {
        return r0.zza;
    }

    public static /* bridge */ /* synthetic */ void zzj(zzw r0, int r1) {
        r0.zzb = r1;
    }

    public static /* bridge */ /* synthetic */ void zzk(zzw r1, Object r2) {
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

    public final Collection zze(Object r2) {
        Collection r0 = (Collection) this.zza.get(r2);
        if (r0 != null) goto L6;
        r0 = zza();
    L6:
        return zzb(r2, r0);
    }

    public final List zzf(Object r2, List r3, zzt r4) {
        if ((r3 instanceof RandomAccess) == false) goto L7;
        return new zzr(this, r2, r3, r4);
    L7:
        return new zzv(this, r2, r3, r4);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzy
    public final Map zzh() {
        return new zzo(this, this.zza);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzy
    public final Set zzi() {
        return new zzq(this, this.zza);
    }

    public final void zzl() {
        Iterator r0 = this.zza.values().iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        ((Collection) r0.next()).clear();
        goto L4
    L6:
        this.zza.clear();
        this.zzb = 0;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzbi
    public final boolean zzm(Object r4, Object r5) {
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
