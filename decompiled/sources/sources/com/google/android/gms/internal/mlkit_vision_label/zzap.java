package com.google.android.gms.internal.mlkit_vision_label;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
final class zzap extends AbstractSet {
    final /* synthetic */ zzau zza;

    public zzap(zzau r1) {
        this.zza = r1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.zza.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object r4) {
        Map r0 = this.zza.zzl();
        if (r0 == null) goto L7;
        return r0.entrySet().contains(r4);
    L7:
        if ((r4 instanceof Map.Entry) == false) goto L14;
        Map.Entry r42 = (Map.Entry) r4;
        int r02 = zzau.zzc(this.zza, r42.getKey());
        if (r02 == (-1)) goto L14;
        if (zzo.zza(zzau.zzj(this.zza, r02), r42.getValue()) == false) goto L14;
        return true;
    L14:
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        zzau r1 = this.zza;
        Map r0 = r1.zzl();
        if (r0 == null) goto L7;
        return r0.entrySet().iterator();
    L7:
        return new zzan(r1);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object r11) {
        Map r0 = this.zza.zzl();
        if (r0 == null) goto L7;
        return r0.entrySet().remove(r11);
    L7:
        if ((r11 instanceof Map.Entry) == false) goto L16;
        Map.Entry r112 = (Map.Entry) r11;
        zzau r02 = this.zza;
        if (r02.zzq() == false) goto L11;
        return false;
    L11:
        int r5 = zzau.zzd(r02);
        int r113 = zzav.zzb(r112.getKey(), r112.getValue(), r5, zzau.zzk(this.zza), zzau.zzr(this.zza), zzau.zzs(this.zza), zzau.zzt(this.zza));
        if (r113 != (-1)) goto L14;
        return false;
    L14:
        this.zza.zzp(r113, r5);
        zzau.zzb(this.zza);
        this.zza.zzn();
        return true;
    L16:
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zza.size();
    }
}
