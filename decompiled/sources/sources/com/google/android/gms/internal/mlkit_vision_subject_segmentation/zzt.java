package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import androidx.collection.ArraySet$$ExternalSyntheticBUOutline0;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

/* loaded from: classes4.dex */
abstract class zzt extends AbstractCollection {
    final Object zza;
    Collection zzb;
    final zzt zzc;
    final Collection zzd;
    final /* synthetic */ zzw zze;

    public zzt(zzw r1, Object r2, Collection r3, zzt r4) {
        this.zze = r1;
        this.zza = r2;
        this.zzb = r3;
        this.zzc = r4;
        if (r4 != null) goto L5;
        Collection r12 = null;
    L6:
        this.zzd = r12;
        return;
    L5:
        r12 = r4.zzb;
        goto L6
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean add(Object r5) {
        zzb();
        boolean r0 = this.zzb.isEmpty();
        boolean r52 = this.zzb.add(r5);
        if (r52 == false) goto L8;
        zzw r1 = this.zze;
        zzw.zzj(r1, zzw.zzd(r1) + 1);
        if (r0 == false) goto L8;
        zza();
        return true;
    L8:
        return r52;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean addAll(Collection r5) {
        if (r5.isEmpty() == false) goto L6;
        return false;
    L6:
        int r0 = size();
        boolean r52 = this.zzb.addAll(r5);
        if (r52 == false) goto L12;
        int r1 = this.zzb.size();
        zzw r2 = this.zze;
        int r3 = zzw.zzd(r2);
        zzw.zzj(r2, r3 + (r1 - r0));
        if (r0 != 0) goto L12;
        zza();
        return true;
    L12:
        return r52;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        int r0 = size();
        if (r0 != 0) goto L5;
        return;
    L5:
        this.zzb.clear();
        zzw r1 = this.zze;
        zzw.zzj(r1, zzw.zzd(r1) - r0);
        zzc();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object r1) {
        zzb();
        return this.zzb.contains(r1);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean containsAll(Collection r1) {
        zzb();
        return this.zzb.containsAll(r1);
    }

    @Override // java.util.Collection
    public final boolean equals(Object r1) {
        if (r1 != this) goto L5;
        return true;
    L5:
        zzb();
        return this.zzb.equals(r1);
    }

    @Override // java.util.Collection
    public final int hashCode() {
        zzb();
        return this.zzb.hashCode();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        zzb();
        return new zzs(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean remove(Object r3) {
        zzb();
        boolean r32 = this.zzb.remove(r3);
        if (r32 == false) goto L5;
        zzw.zzj(this.zze, zzw.zzd(r0) - 1);
        zzc();
    L5:
        return r32;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean removeAll(Collection r4) {
        if (r4.isEmpty() == false) goto L6;
        return false;
    L6:
        int r0 = size();
        boolean r42 = this.zzb.removeAll(r4);
        if (r42 == false) goto L9;
        int r1 = this.zzb.size();
        zzw r2 = this.zze;
        int r12 = r1 - r0;
        zzw.zzj(r2, zzw.zzd(r2) + r12);
        zzc();
    L9:
        return r42;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean retainAll(Collection r4) {
        r4.getClass();
        int r0 = size();
        boolean r42 = this.zzb.retainAll(r4);
        if (r42 == false) goto L5;
        int r1 = this.zzb.size();
        zzw r2 = this.zze;
        int r12 = r1 - r0;
        zzw.zzj(r2, zzw.zzd(r2) + r12);
        zzc();
    L5:
        return r42;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        zzb();
        return this.zzb.size();
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        zzb();
        return this.zzb.toString();
    }

    public final void zza() {
        zzt r0 = this.zzc;
        if (r0 == null) goto L6;
        r0.zza();
        return;
    L6:
        zzw r02 = this.zze;
        Object r1 = this.zza;
        zzw.zzg(r02).put(r1, this.zzb);
    }

    public final void zzb() {
        zzt r0 = this.zzc;
        if (r0 == null) goto L10;
        r0.zzb();
        zzt r02 = this.zzc;
        Collection r2 = this.zzd;
        if (r02.zzb == r2) goto L17;
        ArraySet$$ExternalSyntheticBUOutline0.m();
        return;
    L17:
        return;
    L10:
        if (this.zzb.isEmpty() == false) goto L15;
        zzw r03 = this.zze;
        Object r1 = this.zza;
        Collection r04 = (Collection) zzw.zzg(r03).get(r1);
        if (r04 == null) goto L16;
        this.zzb = r04;
        return;
    L16:
        return;
    }

    public final void zzc() {
        zzt r0 = this.zzc;
        if (r0 == null) goto L7;
        r0.zzc();
        return;
    L7:
        if (this.zzb.isEmpty() == false) goto L10;
        zzw r02 = this.zze;
        Object r1 = this.zza;
        zzw.zzg(r02).remove(r1);
        return;
    }
}
