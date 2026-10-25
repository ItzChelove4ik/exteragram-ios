package com.google.android.gms.internal.measurement;

import com.android.dx.util.IntList$$ExternalSyntheticBUOutline0;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;
import java.util.RandomAccess;
import okio.Segment$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
final class zzafm<E> extends zzace<E> implements RandomAccess {
    private static final Object[] zza = null;
    private static final zzafm zzb = null;
    private Object[] zzc;
    private int zzd;

    static {
        Object[] r1 = new Object[0];
        zza = r1;
        zzb = new zzafm(r1, 0, false);
    }

    private zzafm(Object[] r1, int r2, boolean r3) {
        super(r3);
        this.zzc = r1;
        this.zzd = r2;
    }

    public static zzafm zzd() {
        return zzb;
    }

    private static int zzf(int r1) {
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
        return zzacg.zza(this.zzd, r4, (byte) 13, "Index:", ", Size:");
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int r5, Object r6) {
        zzcM();
        if (r5 < 0) goto L12;
        int r0 = this.zzd;
        if (r5 > r0) goto L12;
        int r1 = r5 + 1;
        Object[] r2 = this.zzc;
        int r3 = r2.length;
        if (r0 >= r3) goto L9;
        System.arraycopy(r2, r5, r2, r1, r0 - r5);
    L10:
        this.zzc[r5] = r6;
        this.zzd++;
        ((AbstractList) this).modCount++;
        return;
    L9:
        Object[] r02 = new Object[zzf(r3)];
        System.arraycopy(this.zzc, 0, r02, 0, r5);
        System.arraycopy(this.zzc, r5, r02, r1, this.zzd - r5);
        this.zzc = r02;
    L12:
        IntList$$ExternalSyntheticBUOutline0.m(zzi(r5));
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object r4) {
        zzcM();
        int r0 = this.zzd;
        int r1 = this.zzc.length;
        if (r0 != r1) goto L5;
        this.zzc = Arrays.copyOf(this.zzc, zzf(r1));
    L5:
        Object[] r02 = this.zzc;
        int r12 = this.zzd;
        this.zzd = r12 + 1;
        r02[r12] = r4;
        ((AbstractList) this).modCount++;
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzace, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object r7) {
        if (r7 != this) goto L6;
        return true;
    L6:
        if ((r7 instanceof List) == true) goto L9;
        return false;
    L9:
        if ((r7 instanceof RandomAccess) == false) goto L11;
        List r1 = (List) r7;
        int r3 = this.zzd;
        if (r3 == r1.size()) goto L16;
        return false;
    L16:
        if ((r7 instanceof zzafm) == false) goto L24;
        zzafm r72 = (zzafm) r7;
        int r12 = 0;
    L18:
        if (r12 >= r3) goto L23;
        if (this.zzc[r12].equals(r72.zzc[r12]) == false) goto L21;
        r12 = r12 + 1;
        goto L18
    L21:
        return false;
    L23:
        return true;
    L24:
        int r73 = 0;
    L25:
        if (r73 >= r3) goto L30;
        if (this.zzc[r73].equals(r1.get(r73)) == false) goto L28;
        r73 = r73 + 1;
        goto L25
    L28:
        return false;
    L30:
        return true;
    L11:
        return super.equals(r7);
    }

    @Override // java.util.AbstractList, java.util.List
    public final E get(int r1) {
        zzh(r1);
        return (E) this.zzc[r1];
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int r0 = this.zzd;
        int r1 = 0;
        int r2 = 1;
    L3:
        if (r1 >= r0) goto L5;
        r2 = (r2 * 31) + this.zzc[r1].hashCode();
        r1 = r1 + 1;
        goto L3
    L5:
        return r2;
    }

    @Override // com.google.android.gms.internal.measurement.zzace, java.util.AbstractList, java.util.List
    public final Object remove(int r5) {
        zzcM();
        zzh(r5);
        Object[] r0 = this.zzc;
        Object r1 = r0[r5];
        if (r5 >= (this.zzd - 1)) goto L5;
        System.arraycopy(r0, r5 + 1, r0, r5, (r2 - r5) - 1);
    L5:
        this.zzd--;
        ((AbstractList) this).modCount++;
        return r1;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int r3, Object r4) {
        zzcM();
        zzh(r3);
        Object[] r0 = this.zzc;
        Object r1 = r0[r3];
        r0[r3] = r4;
        ((AbstractList) this).modCount++;
        return r1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzd;
    }

    public final void zze(int r2) {
        int r0 = this.zzc.length;
        if (r2 > r0) goto L5;
        return;
    L5:
        if (r0 == 0) goto L10;
    L6:
        if (r0 >= r2) goto L8;
        r0 = zzf(r0);
        goto L6
    L8:
        this.zzc = Arrays.copyOf(this.zzc, r0);
        return;
    L10:
        this.zzc = new Object[Math.max(r2, 10)];
    }

    @Override // com.google.android.gms.internal.measurement.zzaef
    public final /* bridge */ /* synthetic */ zzaef zzg(int r3) {
        if (r3 < this.zzd) goto L9;
        if (r3 != 0) goto L6;
        Object[] r32 = zza;
    L8:
        return new zzafm(r32, this.zzd, true);
    L6:
        r32 = Arrays.copyOf(this.zzc, r3);
        goto L8
    L9:
        Segment$$ExternalSyntheticBUOutline0.m();
        return null;
    }
}
