package com.google.android.gms.internal.clearcut;

import java.util.ListIterator;

/* loaded from: classes4.dex */
final class zzfb implements ListIterator<String> {
    private ListIterator<String> zzpc;
    private final /* synthetic */ int zzpd;
    private final /* synthetic */ zzfa zzpe;

    public zzfb(zzfa r1, int r2) {
        this.zzpe = r1;
        this.zzpd = r2;
        this.zzpc = zzfa.zza(r1).listIterator(r2);
    }

    @Override // java.util.ListIterator
    public final /* synthetic */ void add(String r1) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        return this.zzpc.hasNext();
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.zzpc.hasPrevious();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final /* synthetic */ Object next() {
        return this.zzpc.next();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.zzpc.nextIndex();
    }

    @Override // java.util.ListIterator
    public final /* synthetic */ String previous() {
        return this.zzpc.previous();
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.zzpc.previousIndex();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator
    public final /* synthetic */ void set(String r1) {
        throw new UnsupportedOperationException();
    }
}
