package com.google.android.gms.internal.vision;

import com.android.dx.util.IntList$$ExternalSyntheticBUOutline0;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.RandomAccess;
import okio.Segment$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
final class zzlb<E> extends zzhj<E> implements RandomAccess {
    private static final zzlb<Object> zza = null;
    private E[] zzb;
    private int zzc;

    static {
        zzlb<Object> r0 = new zzlb(new Object[0], 0);
        zza = r0;
        r0.zzb();
    }

    private zzlb(E[] r1, int r2) {
        this.zzb = r1;
        this.zzc = r2;
    }

    private final void zzb(int r2) {
        if (r2 >= 0) goto L4;
    L6:
        IntList$$ExternalSyntheticBUOutline0.m(zzc(r2));
        return;
    L4:
        if (r2 >= this.zzc) goto L6;
    }

    private final String zzc(int r3) {
        int r2 = this.zzc;
        StringBuilder r0 = new StringBuilder(35);
        r0.append("Index:");
        r0.append(r3);
        r0.append(", Size:");
        r0.append(r2);
        return r0.toString();
    }

    public static <E> zzlb<E> zzd() {
        return (zzlb<E>) zza;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int r5, E r6) {
        zzc();
        if (r5 < 0) goto L12;
        int r0 = this.zzc;
        if (r5 > r0) goto L12;
        E[] r1 = this.zzb;
        if (r0 >= r1.length) goto L9;
        System.arraycopy(r1, r5, r1, r5 + 1, r0 - r5);
    L10:
        this.zzb[r5] = r6;
        this.zzc++;
        ((AbstractList) this).modCount++;
        return;
    L9:
        E[] r02 = (E[]) new Object[((r0 * 3) / 2) + 1];
        System.arraycopy(r1, 0, r02, 0, r5);
        System.arraycopy(this.zzb, r5, r02, r5 + 1, this.zzc - r5);
        this.zzb = r02;
    L12:
        IntList$$ExternalSyntheticBUOutline0.m(zzc(r5));
    }

    @Override // com.google.android.gms.internal.vision.zzhj, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(E r5) {
        zzc();
        int r0 = this.zzc;
        E[] r1 = this.zzb;
        if (r0 != r1.length) goto L5;
        this.zzb = (E[]) Arrays.copyOf(r1, ((r0 * 3) / 2) + 1);
    L5:
        E[] r02 = this.zzb;
        int r12 = this.zzc;
        this.zzc = r12 + 1;
        r02[r12] = r5;
        ((AbstractList) this).modCount++;
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final E get(int r1) {
        zzb(r1);
        return this.zzb[r1];
    }

    @Override // com.google.android.gms.internal.vision.zzhj, java.util.AbstractList, java.util.List
    public final E remove(int r5) {
        zzc();
        zzb(r5);
        E[] r0 = this.zzb;
        E r1 = r0[r5];
        if (r5 >= (this.zzc - 1)) goto L5;
        System.arraycopy(r0, r5 + 1, r0, r5, (r2 - r5) - 1);
    L5:
        this.zzc--;
        ((AbstractList) this).modCount++;
        return r1;
    }

    @Override // java.util.AbstractList, java.util.List
    public final E set(int r3, E r4) {
        zzc();
        zzb(r3);
        E[] r0 = this.zzb;
        E r1 = r0[r3];
        r0[r3] = r4;
        ((AbstractList) this).modCount++;
        return r1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.vision.zzjl
    public final /* synthetic */ zzjl zza(int r2) {
        if (r2 >= this.zzc) goto L5;
        Segment$$ExternalSyntheticBUOutline0.m();
        return null;
    L5:
        return new zzlb(Arrays.copyOf(this.zzb, r2), this.zzc);
    }
}
