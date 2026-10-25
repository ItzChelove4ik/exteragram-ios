package com.google.android.gms.internal.vision;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;
import okio.Segment$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class zzjs extends zzhj<String> implements zzjv, RandomAccess {
    private static final zzjs zza = null;
    private static final zzjv zzb = null;
    private final List<Object> zzc;

    static {
        zzjs r0 = new zzjs();
        zza = r0;
        r0.zzb();
        zzb = r0;
    }

    public zzjs() {
        this(10);
    }

    public zzjs(int r2) {
        this(new ArrayList(r2));
    }

    private zzjs(ArrayList<Object> r1) {
        this.zzc = r1;
    }

    private static String zza(Object r1) {
        if ((r1 instanceof String) == false) goto L7;
        return (String) r1;
    L7:
        if ((r1 instanceof zzht) == false) goto L11;
        return ((zzht) r1).zzb();
    L11:
        return zzjf.zzb((byte[]) r1);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int r2, Object r3) {
        zzc();
        this.zzc.add(r2, (String) r3);
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.vision.zzhj, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean add(Object r1) {
        return super.add(r1);
    }

    @Override // com.google.android.gms.internal.vision.zzhj, java.util.AbstractList, java.util.List
    public final boolean addAll(int r2, Collection<? extends String> r3) {
        zzc();
        if ((r3 instanceof zzjv) == false) goto L5;
        r3 = ((zzjv) r3).zzd();
    L5:
        boolean r22 = this.zzc.addAll(r2, r3);
        ((AbstractList) this).modCount++;
        return r22;
    }

    @Override // com.google.android.gms.internal.vision.zzhj, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends String> r2) {
        return addAll(size(), r2);
    }

    @Override // com.google.android.gms.internal.vision.zzhj, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        zzc();
        this.zzc.clear();
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.vision.zzhj, java.util.AbstractList, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean equals(Object r1) {
        return super.equals(r1);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int r3) {
        Object r0 = this.zzc.get(r3);
        if ((r0 instanceof String) == false) goto L7;
        return (String) r0;
    L7:
        if ((r0 instanceof zzht) == false) goto L12;
        zzht r02 = (zzht) r0;
        String r1 = r02.zzb();
        if (r02.zzc() == false) goto L11;
        this.zzc.set(r3, r1);
    L11:
        return r1;
    L12:
        byte[] r03 = (byte[]) r0;
        String r12 = zzjf.zzb(r03);
        if (zzjf.zza(r03) == false) goto L15;
        this.zzc.set(r3, r12);
    L15:
        return r12;
    }

    @Override // com.google.android.gms.internal.vision.zzhj, java.util.AbstractList, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.android.gms.internal.vision.zzhj, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int r2) {
        zzc();
        Object r22 = this.zzc.remove(r2);
        ((AbstractList) this).modCount++;
        return zza(r22);
    }

    @Override // com.google.android.gms.internal.vision.zzhj, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean remove(Object r1) {
        return super.remove(r1);
    }

    @Override // com.google.android.gms.internal.vision.zzhj, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean removeAll(Collection r1) {
        return super.removeAll(r1);
    }

    @Override // com.google.android.gms.internal.vision.zzhj, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean retainAll(Collection r1) {
        return super.retainAll(r1);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int r1, Object r2) {
        zzc();
        List<Object> r0 = this.zzc;
        return zza(r0.set(r1, (String) r2));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc.size();
    }

    @Override // com.google.android.gms.internal.vision.zzjl
    public final /* synthetic */ zzjl zza(int r2) {
        if (r2 < size()) goto L6;
        ArrayList r0 = new ArrayList(r2);
        r0.addAll(this.zzc);
        return new zzjs(r0);
    L6:
        Segment$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    @Override // com.google.android.gms.internal.vision.zzjv
    public final void zza(zzht r2) {
        zzc();
        this.zzc.add(r2);
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.vision.zzhj, com.google.android.gms.internal.vision.zzjl
    public final /* bridge */ /* synthetic */ boolean zza() {
        return super.zza();
    }

    @Override // com.google.android.gms.internal.vision.zzjv
    public final Object zzb(int r1) {
        return this.zzc.get(r1);
    }

    @Override // com.google.android.gms.internal.vision.zzjv
    public final List<?> zzd() {
        return Collections.unmodifiableList(this.zzc);
    }

    @Override // com.google.android.gms.internal.vision.zzjv
    public final zzjv zze() {
        if (zza() == true) goto L5;
        return this;
    L5:
        return new zzlz(this);
    }
}
