package com.google.android.recaptcha.internal;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import org.telegram.tgnet.TLObject;

/* loaded from: classes5.dex */
public abstract class zzqb<E> extends zzpq<E> implements Set<E> {
    private transient zzpt zza;

    public zzqb() {
    }

    public static int zzg(int r5) {
        int r52 = Math.max(r5, 2);
        if (r52 >= 751619276) goto L10;
        int r0 = Integer.highestOneBit(r52 - 1);
    L5:
        r0 = r0 + r0;
        if ((r0 * 0.7d) < r52) goto L5;
        return r0;
    L10:
        if (r52 >= 1073741824) goto L12;
        return TLObject.FLAG_30;
    L12:
        f$$ExternalSyntheticBUOutline1.m("collection too large");
        return 0;
    }

    public static zzqb zzh(Collection r2) {
        if ((r2 instanceof zzqb) == true) goto L5;
    L9:
        Object[] r22 = r2.toArray();
        return zzn(r22.length, r22);
    L5:
        if ((r2 instanceof SortedSet) == true) goto L9;
        zzqb r0 = (zzqb) r2;
        if (r0.zze() == true) goto L9;
        return r0;
    }

    public static /* synthetic */ zzqb zzl(int r0, Object[] r1) {
        return zzn(r0, r1);
    }

    public static /* synthetic */ boolean zzm(int r0, int r1) {
        return zzo(r0, r1);
    }

    private static zzqb zzn(int r13, Object... r14) {
        if (r13 == 0) goto L31;
        if (r13 == 1) goto L28;
        int r2 = zzg(r13);
        Object[] r6 = new Object[r2];
        int r7 = r2 - 1;
        int r3 = 0;
        int r5 = 0;
        int r8 = 0;
    L6:
        if (r3 >= r13) goto L15;
        Object r4 = r14[r3];
        zzqj.zza(r4, r3);
        int r9 = r4.hashCode();
        int r10 = zzpn.zza(r9);
    L8:
        int r11 = r10 & r7;
        Object r12 = r6[r11];
        if (r12 == null) goto L10;
        if (r12.equals(r4) == true) goto L14;
        r10 = r10 + 1;
    L14:
        r3 = r3 + 1;
        goto L6
    L10:
        r14[r8] = r4;
        r6[r11] = r4;
        r5 = r5 + r9;
        r8 = r8 + 1;
        goto L14
    L15:
        Arrays.fill(r14, r8, r13, null);
        if (r8 != 1) goto L20;
        Object r132 = r14[0];
        Objects.requireNonNull(r132);
        return new zzqt(r132);
    L20:
        if (zzg(r8) >= (r2 / 2)) goto L24;
        return zzn(r8, r14);
    L24:
        if (zzo(r8, r14.length) == false) goto L27;
        r14 = Arrays.copyOf(r14, r8);
    L27:
        return new zzqr(r14, r5, r6, r7, r8);
    L28:
        Object r133 = r14[0];
        Objects.requireNonNull(r133);
        return new zzqt(r133);
    L31:
        return zzqr.zza;
    }

    private static boolean zzo(int r1, int r2) {
        if (r1 >= ((r2 >> 1) + (r2 >> 2))) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzqb) == true) goto L8;
    L15:
        if (r5 != this) goto L18;
        return true;
    L18:
        if ((r5 instanceof Set) == false) goto L26;
        Set r52 = (Set) r5;
        if (size() != r52.size()) goto L26;
        if (containsAll(r52) == true) goto L25;
        return false;
    L25:
        return true;
    L26:
        return false;
    L8:
        if (zzi() == false) goto L15;
        if (((zzqb) r5).zzi() == false) goto L15;
        if (hashCode() == r5.hashCode()) goto L15;
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        return zzqs.zza(this);
    }

    @Override // com.google.android.recaptcha.internal.zzpq
    public abstract zzqu zza();

    public boolean zzi() {
        return false;
    }

    public final zzpt zzj() {
        zzpt r0 = this.zza;
        if (r0 != null) goto L6;
        zzpt r02 = zzk();
        this.zza = r02;
        return r02;
    L6:
        return r0;
    }

    public zzpt zzk() {
        Object[] r1 = toArray();
        int r0 = zzpt.$r8$clinit;
        return zzpt.zzi(r1, r1.length);
    }
}
