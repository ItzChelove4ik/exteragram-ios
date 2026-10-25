package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* loaded from: classes.dex */
public abstract class zzav extends zzaq implements List, RandomAccess {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final zzbv zza = null;

    static {
        zza = new zzat(zzbl.zza, 0);
    }

    public zzav() {
    }

    public static zzav zzg(Object[] r1, int r2) {
        if (r2 != 0) goto L6;
        return zzbl.zza;
    L6:
        return new zzbl(r1, r2);
    }

    public static zzav zzh(Collection r1) {
        Object[] r12 = r1.toArray();
        int r0 = r12.length;
        zzbk.zza(r12, r0);
        return zzg(r12, r0);
    }

    public static zzav zzi(Object r0, Object r1) {
        Object[] r02 = {r0, r1};
        zzbk.zza(r02, 2);
        return zzg(r02, 2);
    }

    @Override // java.util.List
    @Deprecated
    public final void add(int r1, Object r2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @Deprecated
    public final boolean addAll(int r1, Collection r2) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzaq, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object r1) {
        if (indexOf(r1) < 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.Collection, java.util.List
    public final boolean equals(Object r7) {
        if (r7 != this) goto L6;
        return true;
    L6:
        if ((r7 instanceof List) == true) goto L8;
        return false;
    L8:
        List r72 = (List) r7;
        int r1 = size();
        if (r1 == r72.size()) goto L12;
        return false;
    L12:
        if ((r72 instanceof RandomAccess) == false) goto L20;
        int r3 = 0;
    L14:
        if (r3 >= r1) goto L19;
        if (zze.zza(get(r3), r72.get(r3)) == false) goto L17;
        r3 = r3 + 1;
        goto L14
    L17:
        return false;
    L19:
        return true;
    L20:
        Iterator r6 = iterator();
        Iterator r73 = r72.iterator();
    L22:
        if (r6.hasNext() == false) goto L30;
        if (r73.hasNext() == false) goto L25;
        if (zze.zza(r6.next(), r73.next()) == true) goto L22;
        return false;
    L25:
        return false;
    L30:
        if (r73.hasNext() == true) goto L32;
        return true;
    L32:
        return false;
    }

    @Override // java.util.Collection, java.util.List
    public final int hashCode() {
        int r0 = size();
        int r1 = 0;
        int r2 = 1;
    L3:
        if (r1 >= r0) goto L5;
        r2 = (r2 * 31) + get(r1).hashCode();
        r1 = r1 + 1;
        goto L3
    L5:
        return r2;
    }

    @Override // java.util.List
    public final int indexOf(Object r5) {
        if (r5 != null) goto L5;
        return -1;
    L5:
        int r1 = size();
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L11;
        if (r5.equals(get(r2)) == true) goto L9;
        r2 = r2 + 1;
        goto L6
    L9:
        return r2;
    L11:
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final /* synthetic */ Iterator iterator() {
        return zzj(0);
    }

    @Override // java.util.List
    public final int lastIndexOf(Object r4) {
        if (r4 != null) goto L5;
        return -1;
    L5:
        int r1 = size() - 1;
    L6:
        if (r1 < 0) goto L11;
        if (r4.equals(get(r1)) == true) goto L9;
        r1 = r1 - 1;
        goto L6
    L9:
        return r1;
    L11:
        return -1;
    }

    @Override // java.util.List
    public final /* synthetic */ ListIterator listIterator() {
        return zzj(0);
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ ListIterator listIterator(int r1) {
        return zzj(r1);
    }

    @Override // java.util.List
    @Deprecated
    public final Object remove(int r1) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @Deprecated
    public final Object set(int r1, Object r2) {
        throw new UnsupportedOperationException();
    }

    public /* bridge */ /* synthetic */ List subList(int r1, int r2) {
        return zzf(r1, r2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzaq
    public int zza(Object[] r3, int r4) {
        int r42 = size();
        int r0 = 0;
    L3:
        if (r0 >= r42) goto L5;
        r3[r0] = get(r0);
        r0 = r0 + 1;
        goto L3
    L5:
        return r42;
    }

    public zzav zzf(int r2, int r3) {
        zzi.zzc(r2, r3, size());
        int r32 = r3 - r2;
        if (r32 != size()) goto L5;
        return this;
    L5:
        if (r32 != 0) goto L9;
        return zzbl.zza;
    L9:
        return new zzau(this, r2, r32);
    }

    public final zzbv zzj(int r3) {
        zzi.zzb(r3, size(), "index");
        if (isEmpty() == false) goto L7;
        return zza;
    L7:
        return new zzat(this, r3);
    }
}
