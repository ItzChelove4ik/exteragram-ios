package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

/* loaded from: classes4.dex */
class zzv extends zzt implements List {
    final /* synthetic */ zzw zzf;

    public zzv(zzw r1, Object r2, List r3, zzt r4) {
        this.zzf = r1;
        super(r1, r2, r3, r4);
    }

    @Override // java.util.List
    public final void add(int r3, Object r4) {
        zzb();
        boolean r0 = this.zzb.isEmpty();
        ((List) this.zzb).add(r3, r4);
        zzw r32 = this.zzf;
        zzw.zzj(r32, zzw.zzd(r32) + 1);
        if (r0 == false) goto L6;
        zza();
        return;
    }

    @Override // java.util.List
    public final boolean addAll(int r4, Collection r5) {
        if (r5.isEmpty() == false) goto L6;
        return false;
    L6:
        int r0 = size();
        boolean r42 = ((List) this.zzb).addAll(r4, r5);
        if (r42 == false) goto L12;
        int r52 = this.zzb.size();
        zzw r1 = this.zzf;
        int r2 = zzw.zzd(r1);
        zzw.zzj(r1, r2 + (r52 - r0));
        if (r0 != 0) goto L12;
        zza();
        return true;
    L12:
        return r42;
    }

    @Override // java.util.List
    public final Object get(int r1) {
        zzb();
        return ((List) this.zzb).get(r1);
    }

    @Override // java.util.List
    public final int indexOf(Object r1) {
        zzb();
        return ((List) this.zzb).indexOf(r1);
    }

    @Override // java.util.List
    public final int lastIndexOf(Object r1) {
        zzb();
        return ((List) this.zzb).lastIndexOf(r1);
    }

    @Override // java.util.List
    public final ListIterator listIterator() {
        zzb();
        return new zzu(this);
    }

    @Override // java.util.List
    public final ListIterator listIterator(int r2) {
        zzb();
        return new zzu(this, r2);
    }

    @Override // java.util.List
    public final Object remove(int r3) {
        zzb();
        Object r32 = ((List) this.zzb).remove(r3);
        zzw.zzj(this.zzf, zzw.zzd(r0) - 1);
        zzc();
        return r32;
    }

    @Override // java.util.List
    public final Object set(int r1, Object r2) {
        zzb();
        return ((List) this.zzb).set(r1, r2);
    }

    @Override // java.util.List
    public final List subList(int r2, int r3) {
        zzb();
        List r22 = ((List) this.zzb).subList(r2, r3);
        zzt r32 = this.zzc;
        if (r32 != null) goto L5;
        r32 = this;
    L5:
        Object r0 = this.zza;
        return this.zzf.zzf(r0, r22, r32);
    }
}
