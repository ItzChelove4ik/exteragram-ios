package com.google.android.gms.internal.cast;

import com.android.dx.util.IntList$$ExternalSyntheticBUOutline0;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;
import okio.Segment$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
final class zzyx extends zzxa implements RandomAccess, zzyk {
    private static final long[] zza = null;
    private static final zzyx zzb = null;
    private long[] zzc;
    private int zzd;

    static {
        long[] r1 = new long[0];
        zza = r1;
        zzb = new zzyx(r1, 0, false);
    }

    private zzyx(long[] r1, int r2, boolean r3) {
        super(r3);
        this.zzc = r1;
        this.zzd = r2;
    }

    public static zzyx zzd() {
        return zzb;
    }

    private static int zzg(int r1) {
        return Math.max(((r1 * 3) / 2) + 1, 10);
    }

    private final void zzh(int r2) {
        if (r2 >= 0) goto L4;
    L6:
        IntList$$ExternalSyntheticBUOutline0.m(zzi(r2));
        return;
    L4:
        if (r2 >= this.zzd) goto L6;
    }

    private final String zzi(int r4) {
        return zzxd.zza(this.zzd, r4, (byte) 13, "Index:", ", Size:");
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int r6, Object r7) {
        long r0 = ((Long) r7).longValue();
        zzR();
        if (r6 < 0) goto L12;
        int r72 = this.zzd;
        if (r6 > r72) goto L12;
        int r2 = r6 + 1;
        long[] r3 = this.zzc;
        int r4 = r3.length;
        if (r72 >= r4) goto L9;
        System.arraycopy(r3, r6, r3, r2, r72 - r6);
    L10:
        this.zzc[r6] = r0;
        this.zzd++;
        ((AbstractList) this).modCount++;
        return;
    L9:
        long[] r73 = new long[zzg(r4)];
        System.arraycopy(this.zzc, 0, r73, 0, r6);
        System.arraycopy(this.zzc, r6, r73, r2, this.zzd - r6);
        this.zzc = r73;
    L12:
        IntList$$ExternalSyntheticBUOutline0.m(zzi(r6));
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean add(Object r6) {
        long r0 = ((Long) r6).longValue();
        zzR();
        int r62 = this.zzd;
        int r2 = this.zzc.length;
        if (r62 != r2) goto L5;
        long[] r63 = new long[zzg(r2)];
        System.arraycopy(this.zzc, 0, r63, 0, this.zzd);
        this.zzc = r63;
    L5:
        long[] r64 = this.zzc;
        int r22 = this.zzd;
        this.zzd = r22 + 1;
        r64[r22] = r0;
        return true;
    }

    @Override // com.google.android.gms.internal.cast.zzxa, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection r6) {
        zzR();
        byte[] r0 = zzym.zzb;
        r6.getClass();
        if ((r6 instanceof zzyx) == false) goto L5;
        zzyx r62 = (zzyx) r6;
        int r02 = r62.zzd;
        if (r02 != 0) goto L9;
        return false;
    L9:
        int r2 = this.zzd;
        if ((Integer.MAX_VALUE - r2) < r02) goto L17;
        int r22 = r2 + r02;
        long[] r03 = this.zzc;
        if (r22 <= r03.length) goto L14;
        this.zzc = Arrays.copyOf(r03, r22);
    L14:
        System.arraycopy(r62.zzc, 0, this.zzc, this.zzd, r62.zzd);
        this.zzd = r22;
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

    @Override // com.google.android.gms.internal.cast.zzxa, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object r9) {
        if (this != r9) goto L6;
        return true;
    L6:
        if ((r9 instanceof zzyx) == false) goto L8;
        zzyx r92 = (zzyx) r9;
        if (this.zzd == r92.zzd) goto L12;
        return false;
    L12:
        long[] r93 = r92.zzc;
        int r1 = 0;
    L14:
        if (r1 >= this.zzd) goto L19;
        if (this.zzc[r1] != r93[r1]) goto L17;
        r1 = r1 + 1;
        goto L14
    L17:
        return false;
    L19:
        return true;
    L8:
        return super.equals(r9);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int r3) {
        zzh(r3);
        return Long.valueOf(this.zzc[r3]);
    }

    @Override // com.google.android.gms.internal.cast.zzxa, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int r0 = 0;
        int r1 = 1;
    L4:
        if (r0 >= this.zzd) goto L6;
        long r3 = this.zzc[r0];
        byte[] r2 = zzym.zzb;
        r1 = (r1 * 31) + ((int) (r3 ^ (r3 >>> 32)));
        r0 = r0 + 1;
        goto L4
    L6:
        return r1;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object r8) {
        if ((r8 instanceof Long) == true) goto L5;
        return -1;
    L5:
        long r2 = ((Long) r8).longValue();
        int r82 = this.zzd;
        int r0 = 0;
    L6:
        if (r0 >= r82) goto L11;
        if (this.zzc[r0] == r2) goto L9;
        r0 = r0 + 1;
        goto L6
    L9:
        return r0;
    L11:
        return -1;
    }

    @Override // com.google.android.gms.internal.cast.zzxa, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int r6) {
        zzR();
        zzh(r6);
        long[] r0 = this.zzc;
        long r1 = r0[r6];
        if (r6 >= (this.zzd - 1)) goto L5;
        System.arraycopy(r0, r6 + 1, r0, r6, (r3 - r6) - 1);
    L5:
        this.zzd--;
        ((AbstractList) this).modCount++;
        return Long.valueOf(r1);
    }

    @Override // java.util.AbstractList
    public final void removeRange(int r3, int r4) {
        zzR();
        if (r4 < r3) goto L6;
        long[] r0 = this.zzc;
        System.arraycopy(r0, r4, r0, r3, this.zzd - r4);
        this.zzd -= r4 - r3;
        ((AbstractList) this).modCount++;
        return;
    L6:
        IntList$$ExternalSyntheticBUOutline0.m("toIndex < fromIndex");
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int r5, Object r6) {
        long r0 = ((Long) r6).longValue();
        zzR();
        zzh(r5);
        long[] r4 = this.zzc;
        long r2 = r4[r5];
        r4[r5] = r0;
        return Long.valueOf(r2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzd;
    }

    public final zzyk zzc(int r3) {
        if (r3 < this.zzd) goto L9;
        if (r3 != 0) goto L6;
        long[] r32 = zza;
    L8:
        return new zzyx(r32, this.zzd, true);
    L6:
        r32 = Arrays.copyOf(this.zzc, r3);
        goto L8
    L9:
        Segment$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    public final long zze(int r3) {
        zzh(r3);
        return this.zzc[r3];
    }

    @Override // com.google.android.gms.internal.cast.zzyl
    public final /* bridge */ /* synthetic */ zzyl zzf(int r1) {
        return zzc(r1);
    }
}
