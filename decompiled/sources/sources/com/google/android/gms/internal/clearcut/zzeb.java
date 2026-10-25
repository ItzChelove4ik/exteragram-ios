package com.google.android.gms.internal.clearcut;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import okio.Segment$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
final class zzeb<E> extends zzav<E> {
    private static final zzeb<Object> zznf = null;
    private final List<E> zzls;

    static {
        zzeb<Object> r0 = new zzeb();
        zznf = r0;
        r0.zzv();
    }

    public zzeb() {
        this(new ArrayList(10));
    }

    private zzeb(List<E> r1) {
        this.zzls = r1;
    }

    public static <E> zzeb<E> zzcn() {
        return (zzeb<E>) zznf;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int r2, E r3) {
        zzw();
        this.zzls.add(r2, r3);
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final E get(int r1) {
        return this.zzls.get(r1);
    }

    @Override // java.util.AbstractList, java.util.List
    public final E remove(int r2) {
        zzw();
        E r22 = this.zzls.remove(r2);
        ((AbstractList) this).modCount++;
        return r22;
    }

    @Override // java.util.AbstractList, java.util.List
    public final E set(int r2, E r3) {
        zzw();
        E r22 = this.zzls.set(r2, r3);
        ((AbstractList) this).modCount++;
        return r22;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzls.size();
    }

    @Override // com.google.android.gms.internal.clearcut.zzcn
    public final /* synthetic */ zzcn zzi(int r2) {
        if (r2 < size()) goto L6;
        ArrayList r0 = new ArrayList(r2);
        r0.addAll(this.zzls);
        return new zzeb(r0);
    L6:
        Segment$$ExternalSyntheticBUOutline0.m();
        return null;
    }
}
