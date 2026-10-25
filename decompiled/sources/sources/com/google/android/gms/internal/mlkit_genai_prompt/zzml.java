package com.google.android.gms.internal.mlkit_genai_prompt;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.RandomAccess;
import java.util.stream.Collector;

/* loaded from: classes4.dex */
public abstract class zzml extends zzmg implements List, RandomAccess {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final zznt zza = null;

    static {
        zza = new zzmi(zzng.zza, 0);
    }

    public zzml() {
    }

    public static Collector zzj() {
        return zzlt.zza();
    }

    public static zzml zzk() {
        return zzng.zza;
    }

    public static zzml zzl(Object r1) {
        Object[] r12 = {r1};
        zzna.zza(r12, 1);
        return zzr(r12, 1);
    }

    public static zzml zzm(Object r0, Object r1) {
        Object[] r02 = {r0, r1};
        zzna.zza(r02, 2);
        return zzr(r02, 2);
    }

    public static zzml zzn(Object r0, Object r1, Object r2) {
        Object[] r02 = {"auxiliary_lora_file", "auxiliary_drafter_lora_file", "auxiliary_session_state_file"};
        zzna.zza(r02, 3);
        return zzr(r02, 3);
    }

    public static zzml zzo(Object r0, Object r1, Object r2, Object r3) {
        Object[] r02 = {r0, r1, r2, r3};
        zzna.zza(r02, 4);
        return zzr(r02, 4);
    }

    public static zzml zzp(Object r0, Object r1, Object r2, Object r3, Object r4) {
        Object[] r02 = {r0, r1, r2, r3, r4};
        zzna.zza(r02, 5);
        return zzr(r02, 5);
    }

    public static zzml zzq(Collection r1) {
        if ((r1 instanceof zzmg) == false) goto L8;
        zzml r12 = ((zzmg) r1).zze();
        if (r12.zzf() == false) goto L10;
        Object[] r13 = r12.toArray();
        return zzr(r13, r13.length);
    L10:
        return r12;
    L8:
        Object[] r14 = r1.toArray();
        int r0 = r14.length;
        zzna.zza(r14, r0);
        return zzr(r14, r0);
    }

    public static zzml zzr(Object[] r1, int r2) {
        if (r2 != 0) goto L6;
        return zzng.zza;
    L6:
        return new zzng(r1, r2);
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

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzmg, java.util.AbstractCollection, java.util.Collection
    public boolean contains(Object r1) {
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
        if (Objects.equals(get(r3), r72.get(r3)) == false) goto L17;
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
        if (Objects.equals(r6.next(), r73.next()) == true) goto L22;
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

    public int indexOf(Object r5) {
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
        return zzs(0);
    }

    public int lastIndexOf(Object r4) {
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
        return zzs(0);
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ ListIterator listIterator(int r1) {
        return zzs(r1);
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
        return zzi(r1, r2);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzmg
    @Deprecated
    public final zzml zze() {
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzmg
    public int zzg(Object[] r3, int r4) {
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

    public zzml zzh() {
        if (size() > 1) goto L6;
        return this;
    L6:
        return new zzmj(this);
    }

    public zzml zzi(int r2, int r3) {
        zzla.zzh(r2, r3, size());
        int r32 = r3 - r2;
        if (r32 != size()) goto L5;
        return this;
    L5:
        if (r32 != 0) goto L9;
        return zzng.zza;
    L9:
        return new zzmk(this, r2, r32);
    }

    public final zznt zzs(int r3) {
        zzla.zzg(r3, size(), "index");
        if (isEmpty() == false) goto L7;
        return zza;
    L7:
        return new zzmi(this, r3);
    }
}
