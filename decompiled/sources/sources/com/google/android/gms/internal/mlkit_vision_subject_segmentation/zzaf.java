package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
final class zzaf extends AbstractSet {
    final /* synthetic */ zzal zza;

    public zzaf(zzal r1) {
        this.zza = r1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.zza.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object r4) {
        Map r0 = this.zza.zzj();
        if (r0 == null) goto L7;
        return r0.entrySet().contains(r4);
    L7:
        if ((r4 instanceof Map.Entry) == false) goto L14;
        Map.Entry r42 = (Map.Entry) r4;
        int r02 = zzal.zzd(this.zza, r42.getKey());
        if (r02 == (-1)) goto L14;
        Object[] r3 = this.zza.zzc;
        r3.getClass();
        if (zze.zza(r3[r02], r42.getValue()) == false) goto L14;
        return true;
    L14:
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        zzal r1 = this.zza;
        Map r0 = r1.zzj();
        if (r0 == null) goto L7;
        return r0.entrySet().iterator();
    L7:
        return new zzad(r1);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object r11) {
        Map r0 = this.zza.zzj();
        if (r0 == null) goto L7;
        return r0.entrySet().remove(r11);
    L7:
        if ((r11 instanceof Map.Entry) == false) goto L16;
        Map.Entry r112 = (Map.Entry) r11;
        zzal r02 = this.zza;
        if (r02.zzo() == false) goto L11;
        return false;
    L11:
        int r5 = zzal.zzc(r02);
        Object r3 = r112.getKey();
        Object r4 = r112.getValue();
        zzal r113 = this.zza;
        Object r6 = zzal.zzh(r113);
        int[] r7 = r113.zza;
        r7.getClass();
        zzal r114 = this.zza;
        Object[] r8 = r114.zzb;
        r8.getClass();
        Object[] r9 = r114.zzc;
        r9.getClass();
        int r115 = zzam.zzb(r3, r4, r5, r6, r7, r8, r9);
        if (r115 != (-1)) goto L14;
        return false;
    L14:
        this.zza.zzn(r115, r5);
        zzal.zzk(this.zza, zzal.zzb(r11) - 1);
        this.zza.zzl();
        return true;
    L16:
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zza.size();
    }
}
