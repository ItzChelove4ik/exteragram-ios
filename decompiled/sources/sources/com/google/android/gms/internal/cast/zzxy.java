package com.google.android.gms.internal.cast;

import com.android.dx.util.IntList$$ExternalSyntheticBUOutline0;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;
import okio.Segment$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
final class zzxy extends zzxa implements RandomAccess, zzyi {
    private static final float[] zza = null;
    private static final zzxy zzb = null;
    private float[] zzc;
    private int zzd;

    static {
        float[] r1 = new float[0];
        zza = r1;
        zzb = new zzxy(r1, 0, false);
    }

    private zzxy(float[] r1, int r2, boolean r3) {
        super(r3);
        this.zzc = r1;
        this.zzd = r2;
    }

    public static zzxy zzd() {
        return zzb;
    }

    private static int zzh(int r1) {
        return Math.max(((r1 * 3) / 2) + 1, 10);
    }

    private final void zzi(int r2) {
        if (r2 >= 0) goto L4;
    L6:
        IntList$$ExternalSyntheticBUOutline0.m(zzj(r2));
        return;
    L4:
        if (r2 >= this.zzd) goto L6;
    }

    private final String zzj(int r4) {
        return zzxd.zza(this.zzd, r4, (byte) 13, "Index:", ", Size:");
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int r5, Object r6) {
        float r62 = ((Float) r6).floatValue();
        zzR();
        if (r5 < 0) goto L12;
        int r0 = this.zzd;
        if (r5 > r0) goto L12;
        int r1 = r5 + 1;
        float[] r2 = this.zzc;
        int r3 = r2.length;
        if (r0 >= r3) goto L9;
        System.arraycopy(r2, r5, r2, r1, r0 - r5);
    L10:
        this.zzc[r5] = r62;
        this.zzd++;
        ((AbstractList) this).modCount++;
        return;
    L9:
        float[] r02 = new float[zzh(r3)];
        System.arraycopy(this.zzc, 0, r02, 0, r5);
        System.arraycopy(this.zzc, r5, r02, r1, this.zzd - r5);
        this.zzc = r02;
    L12:
        IntList$$ExternalSyntheticBUOutline0.m(zzj(r5));
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean add(Object r5) {
        float r52 = ((Float) r5).floatValue();
        zzR();
        int r0 = this.zzd;
        int r1 = this.zzc.length;
        if (r0 != r1) goto L5;
        float[] r02 = new float[zzh(r1)];
        System.arraycopy(this.zzc, 0, r02, 0, this.zzd);
        this.zzc = r02;
    L5:
        float[] r03 = this.zzc;
        int r12 = this.zzd;
        this.zzd = r12 + 1;
        r03[r12] = r52;
        return true;
    }

    @Override // com.google.android.gms.internal.cast.zzxa, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection r6) {
        zzR();
        byte[] r0 = zzym.zzb;
        r6.getClass();
        if ((r6 instanceof zzxy) == false) goto L5;
        zzxy r62 = (zzxy) r6;
        int r02 = r62.zzd;
        if (r02 != 0) goto L9;
        return false;
    L9:
        int r2 = this.zzd;
        if ((Integer.MAX_VALUE - r2) < r02) goto L17;
        int r22 = r2 + r02;
        float[] r03 = this.zzc;
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
    public final boolean equals(Object r6) {
        if (this != r6) goto L6;
        return true;
    L6:
        if ((r6 instanceof zzxy) == false) goto L8;
        zzxy r62 = (zzxy) r6;
        if (this.zzd == r62.zzd) goto L12;
        return false;
    L12:
        float[] r63 = r62.zzc;
        int r1 = 0;
    L14:
        if (r1 >= this.zzd) goto L19;
        if (Float.floatToIntBits(this.zzc[r1]) != Float.floatToIntBits(r63[r1])) goto L17;
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
        zzi(r1);
        return Float.valueOf(this.zzc[r1]);
    }

    @Override // com.google.android.gms.internal.cast.zzxa, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int r0 = 0;
        int r1 = 1;
    L4:
        if (r0 >= this.zzd) goto L6;
        r1 = (r1 * 31) + Float.floatToIntBits(this.zzc[r0]);
        r0 = r0 + 1;
        goto L4
    L6:
        return r1;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object r5) {
        if ((r5 instanceof Float) == true) goto L5;
        return -1;
    L5:
        float r52 = ((Float) r5).floatValue();
        int r0 = this.zzd;
        int r2 = 0;
    L6:
        if (r2 >= r0) goto L11;
        if (this.zzc[r2] == r52) goto L9;
        r2 = r2 + 1;
        goto L6
    L9:
        return r2;
    L11:
        return -1;
    }

    @Override // com.google.android.gms.internal.cast.zzxa, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int r5) {
        zzR();
        zzi(r5);
        float[] r0 = this.zzc;
        float r1 = r0[r5];
        if (r5 >= (this.zzd - 1)) goto L5;
        System.arraycopy(r0, r5 + 1, r0, r5, (r2 - r5) - 1);
    L5:
        this.zzd--;
        ((AbstractList) this).modCount++;
        return Float.valueOf(r1);
    }

    @Override // java.util.AbstractList
    public final void removeRange(int r3, int r4) {
        zzR();
        if (r4 < r3) goto L6;
        float[] r0 = this.zzc;
        System.arraycopy(r0, r4, r0, r3, this.zzd - r4);
        this.zzd -= r4 - r3;
        ((AbstractList) this).modCount++;
        return;
    L6:
        IntList$$ExternalSyntheticBUOutline0.m("toIndex < fromIndex");
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int r2, Object r3) {
        float r32 = ((Float) r3).floatValue();
        zzR();
        zzi(r2);
        float[] r1 = this.zzc;
        float r0 = r1[r2];
        r1[r2] = r32;
        return Float.valueOf(r0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzd;
    }

    public final zzyi zze(int r3) {
        if (r3 < this.zzd) goto L9;
        if (r3 != 0) goto L6;
        float[] r32 = zza;
    L8:
        return new zzxy(r32, this.zzd, true);
    L6:
        r32 = Arrays.copyOf(this.zzc, r3);
        goto L8
    L9:
        Segment$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    @Override // com.google.android.gms.internal.cast.zzyl
    public final /* bridge */ /* synthetic */ zzyl zzf(int r1) {
        return zze(r1);
    }

    public final float zzg(int r1) {
        zzi(r1);
        return this.zzc[r1];
    }
}
