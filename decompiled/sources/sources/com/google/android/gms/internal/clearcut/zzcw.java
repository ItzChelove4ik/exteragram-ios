package com.google.android.gms.internal.clearcut;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;
import okio.Segment$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class zzcw extends zzav<String> implements zzcx, RandomAccess {
    private static final zzcw zzlq = null;
    private static final zzcx zzlr = null;
    private final List<Object> zzls;

    static {
        zzcw r0 = new zzcw();
        zzlq = r0;
        r0.zzv();
        zzlr = r0;
    }

    public zzcw() {
        this(10);
    }

    public zzcw(int r2) {
        this(new ArrayList(r2));
    }

    private zzcw(ArrayList<Object> r1) {
        this.zzls = r1;
    }

    private static String zze(Object r1) {
        if ((r1 instanceof String) == false) goto L7;
        return (String) r1;
    L7:
        if ((r1 instanceof zzbb) == false) goto L11;
        return ((zzbb) r1).zzz();
    L11:
        return zzci.zzf((byte[]) r1);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int r2, Object r3) {
        zzw();
        this.zzls.add(r2, (String) r3);
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractList, java.util.List
    public final boolean addAll(int r2, Collection<? extends String> r3) {
        zzw();
        if ((r3 instanceof zzcx) == false) goto L5;
        r3 = ((zzcx) r3).zzbt();
    L5:
        boolean r22 = this.zzls.addAll(r2, r3);
        ((AbstractList) this).modCount++;
        return r22;
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends String> r2) {
        return addAll(size(), r2);
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        zzw();
        this.zzls.clear();
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractList, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean equals(Object r1) {
        return super.equals(r1);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int r3) {
        Object r0 = this.zzls.get(r3);
        if ((r0 instanceof String) == false) goto L7;
        return (String) r0;
    L7:
        if ((r0 instanceof zzbb) == false) goto L12;
        zzbb r02 = (zzbb) r0;
        String r1 = r02.zzz();
        if (r02.zzaa() == false) goto L11;
    L10:
        this.zzls.set(r3, r1);
    L11:
        return r1;
    L12:
        byte[] r03 = (byte[]) r0;
        r1 = zzci.zzf(r03);
        if (zzci.zze(r03) == true) goto L10;
        return r1;
    }

    @Override // com.google.android.gms.internal.clearcut.zzcx
    public final Object getRaw(int r1) {
        return this.zzls.get(r1);
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractList, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int r2) {
        zzw();
        Object r22 = this.zzls.remove(r2);
        ((AbstractList) this).modCount++;
        return zze(r22);
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean remove(Object r1) {
        return super.remove(r1);
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean removeAll(Collection r1) {
        return super.removeAll(r1);
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean retainAll(Collection r1) {
        return super.retainAll(r1);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int r1, Object r2) {
        zzw();
        List<Object> r0 = this.zzls;
        return zze(r0.set(r1, (String) r2));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzls.size();
    }

    @Override // com.google.android.gms.internal.clearcut.zzcx
    public final List<?> zzbt() {
        return Collections.unmodifiableList(this.zzls);
    }

    @Override // com.google.android.gms.internal.clearcut.zzcx
    public final zzcx zzbu() {
        if (zzu() == true) goto L5;
        return this;
    L5:
        return new zzfa(this);
    }

    @Override // com.google.android.gms.internal.clearcut.zzcn
    public final /* synthetic */ zzcn zzi(int r2) {
        if (r2 < size()) goto L6;
        ArrayList r0 = new ArrayList(r2);
        r0.addAll(this.zzls);
        return new zzcw(r0);
    L6:
        Segment$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, com.google.android.gms.internal.clearcut.zzcn
    public final /* bridge */ /* synthetic */ boolean zzu() {
        return super.zzu();
    }
}
