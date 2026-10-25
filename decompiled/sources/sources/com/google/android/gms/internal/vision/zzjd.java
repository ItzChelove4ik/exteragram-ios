package com.google.android.gms.internal.vision;

import com.android.dx.util.IntList$$ExternalSyntheticBUOutline0;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;
import okio.Segment$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
final class zzjd extends zzhj<Integer> implements zzjl<Integer>, zzkw, RandomAccess {
    private static final zzjd zza = null;
    private int[] zzb;
    private int zzc;

    static {
        zzjd r0 = new zzjd(new int[0], 0);
        zza = r0;
        r0.zzb();
    }

    private zzjd(int[] r1, int r2) {
        this.zzb = r1;
        this.zzc = r2;
    }

    public static zzjd zzd() {
        return zza;
    }

    private final void zzd(int r2) {
        if (r2 >= 0) goto L4;
    L6:
        IntList$$ExternalSyntheticBUOutline0.m(zze(r2));
        return;
    L4:
        if (r2 >= this.zzc) goto L6;
    }

    private final String zze(int r3) {
        int r2 = this.zzc;
        StringBuilder r0 = new StringBuilder(35);
        r0.append("Index:");
        r0.append(r3);
        r0.append(", Size:");
        r0.append(r2);
        return r0.toString();
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int r5, Object r6) {
        int r62 = ((Integer) r6).intValue();
        zzc();
        if (r5 < 0) goto L12;
        int r0 = this.zzc;
        if (r5 > r0) goto L12;
        int[] r1 = this.zzb;
        if (r0 >= r1.length) goto L9;
        System.arraycopy(r1, r5, r1, r5 + 1, r0 - r5);
    L10:
        this.zzb[r5] = r62;
        this.zzc++;
        ((AbstractList) this).modCount++;
        return;
    L9:
        int[] r02 = new int[((r0 * 3) / 2) + 1];
        System.arraycopy(r1, 0, r02, 0, r5);
        System.arraycopy(this.zzb, r5, r02, r5 + 1, this.zzc - r5);
        this.zzb = r02;
    L12:
        IntList$$ExternalSyntheticBUOutline0.m(zze(r5));
    }

    @Override // com.google.android.gms.internal.vision.zzhj, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* synthetic */ boolean add(Object r1) {
        zzc(((Integer) r1).intValue());
        return true;
    }

    @Override // com.google.android.gms.internal.vision.zzhj, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Integer> r6) {
        zzc();
        zzjf.zza(r6);
        if ((r6 instanceof zzjd) == false) goto L5;
        zzjd r62 = (zzjd) r6;
        int r0 = r62.zzc;
        if (r0 != 0) goto L9;
        return false;
    L9:
        int r2 = this.zzc;
        if ((Integer.MAX_VALUE - r2) < r0) goto L17;
        int r22 = r2 + r0;
        int[] r02 = this.zzb;
        if (r22 <= r02.length) goto L14;
        this.zzb = Arrays.copyOf(r02, r22);
    L14:
        System.arraycopy(r62.zzb, 0, this.zzb, this.zzc, r62.zzc);
        this.zzc = r22;
        ((AbstractList) this).modCount++;
        return true;
    L17:
        throw new OutOfMemoryError();
    L5:
        return super.addAll(r6);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object r1) {
        if (indexOf(r1) == (-1)) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.google.android.gms.internal.vision.zzhj, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object r6) {
        if (this != r6) goto L6;
        return true;
    L6:
        if ((r6 instanceof zzjd) == false) goto L8;
        zzjd r62 = (zzjd) r6;
        if (this.zzc == r62.zzc) goto L12;
        return false;
    L12:
        int[] r63 = r62.zzb;
        int r1 = 0;
    L14:
        if (r1 >= this.zzc) goto L19;
        if (this.zzb[r1] != r63[r1]) goto L17;
        r1 = r1 + 1;
        goto L14
    L17:
        return false;
    L19:
        return true;
    L8:
        return super.equals(r6);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int r1) {
        return Integer.valueOf(zzb(r1));
    }

    @Override // com.google.android.gms.internal.vision.zzhj, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int r0 = 1;
        int r1 = 0;
    L4:
        if (r1 >= this.zzc) goto L6;
        r0 = (r0 * 31) + this.zzb[r1];
        r1 = r1 + 1;
        goto L4
    L6:
        return r0;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object r5) {
        if ((r5 instanceof Integer) == true) goto L5;
        return -1;
    L5:
        int r52 = ((Integer) r5).intValue();
        int r0 = size();
        int r2 = 0;
    L6:
        if (r2 >= r0) goto L11;
        if (this.zzb[r2] == r52) goto L9;
        r2 = r2 + 1;
        goto L6
    L9:
        return r2;
    L11:
        return -1;
    }

    @Override // com.google.android.gms.internal.vision.zzhj, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int r5) {
        zzc();
        zzd(r5);
        int[] r0 = this.zzb;
        int r1 = r0[r5];
        if (r5 >= (this.zzc - 1)) goto L5;
        System.arraycopy(r0, r5 + 1, r0, r5, (r2 - r5) - 1);
    L5:
        this.zzc--;
        ((AbstractList) this).modCount++;
        return Integer.valueOf(r1);
    }

    @Override // java.util.AbstractList
    public final void removeRange(int r3, int r4) {
        zzc();
        if (r4 < r3) goto L6;
        int[] r0 = this.zzb;
        System.arraycopy(r0, r4, r0, r3, this.zzc - r4);
        this.zzc -= r4 - r3;
        ((AbstractList) this).modCount++;
        return;
    L6:
        IntList$$ExternalSyntheticBUOutline0.m("toIndex < fromIndex");
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int r2, Object r3) {
        int r32 = ((Integer) r3).intValue();
        zzc();
        zzd(r2);
        int[] r1 = this.zzb;
        int r0 = r1[r2];
        r1[r2] = r32;
        return Integer.valueOf(r0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.vision.zzjl
    public final /* synthetic */ zzjl<Integer> zza(int r3) {
        if (r3 >= this.zzc) goto L5;
        Segment$$ExternalSyntheticBUOutline0.m();
        return null;
    L5:
        return new zzjd(Arrays.copyOf(this.zzb, r3), this.zzc);
    }

    public final int zzb(int r1) {
        zzd(r1);
        return this.zzb[r1];
    }

    public final void zzc(int r5) {
        zzc();
        int r0 = this.zzc;
        int[] r1 = this.zzb;
        if (r0 != r1.length) goto L5;
        int[] r2 = new int[((r0 * 3) / 2) + 1];
        System.arraycopy(r1, 0, r2, 0, r0);
        this.zzb = r2;
    L5:
        int[] r02 = this.zzb;
        int r12 = this.zzc;
        this.zzc = r12 + 1;
        r02[r12] = r5;
    }
}
