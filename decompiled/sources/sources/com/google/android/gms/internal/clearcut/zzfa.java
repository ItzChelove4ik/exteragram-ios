package com.google.android.gms.internal.clearcut;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* loaded from: classes4.dex */
public final class zzfa extends AbstractList<String> implements zzcx, RandomAccess {
    private final zzcx zzpb;

    public zzfa(zzcx r1) {
        this.zzpb = r1;
    }

    public static /* synthetic */ zzcx zza(zzfa r0) {
        return r0.zzpb;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int r1) {
        return (String) this.zzpb.get(r1);
    }

    @Override // com.google.android.gms.internal.clearcut.zzcx
    public final Object getRaw(int r1) {
        return this.zzpb.getRaw(r1);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator<String> iterator() {
        return new zzfc(this);
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator<String> listIterator(int r2) {
        return new zzfb(this, r2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzpb.size();
    }

    @Override // com.google.android.gms.internal.clearcut.zzcx
    public final List<?> zzbt() {
        return this.zzpb.zzbt();
    }

    @Override // com.google.android.gms.internal.clearcut.zzcx
    public final zzcx zzbu() {
        return this;
    }
}
