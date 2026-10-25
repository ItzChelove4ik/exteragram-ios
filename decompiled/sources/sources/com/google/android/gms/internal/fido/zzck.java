package com.google.android.gms.internal.fido;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.SortedSet;

/* loaded from: classes4.dex */
public abstract class zzck extends zzcf implements NavigableSet, zzda {
    final transient Comparator zza;
    transient zzck zzb;

    public zzck(Comparator r1) {
        this.zza = r1;
    }

    public static zzcv zzs(Comparator r2) {
        if (zzcq.zza.equals(r2) == true) goto L5;
        int r1 = zzcc.$r8$clinit;
        return new zzcv(zzct.zza, r2);
    L5:
        return zzcv.zzc;
    }

    @Deprecated
    public final void addFirst(Object r1) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void addLast(Object r1) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.SortedSet, com.google.android.gms.internal.fido.zzda
    public final Comparator comparator() {
        return this.zza;
    }

    @Override // java.util.NavigableSet
    public final /* bridge */ /* synthetic */ NavigableSet descendingSet() {
        return zzn();
    }

    @Override // java.util.SortedSet
    public abstract Object first();

    public final Object getFirst() {
        return first();
    }

    public final Object getLast() {
        return last();
    }

    @Override // java.util.NavigableSet
    public final /* synthetic */ NavigableSet headSet(Object r1, boolean r2) {
        r1.getClass();
        return zzo(r1, r2);
    }

    @Override // java.util.NavigableSet, java.util.SortedSet
    public final /* synthetic */ SortedSet headSet(Object r2) {
        r2.getClass();
        return zzo(r2, false);
    }

    @Override // java.util.SortedSet
    public abstract Object last();

    @Override // java.util.NavigableSet
    @Deprecated
    public final Object pollFirst() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.NavigableSet
    @Deprecated
    public final Object pollLast() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final Object removeFirst() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final Object removeLast() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.NavigableSet
    public final /* bridge */ /* synthetic */ NavigableSet subSet(Object r1, boolean r2, Object r3, boolean r4) {
        return zzp(r1, r2, r3, r4);
    }

    @Override // java.util.NavigableSet, java.util.SortedSet
    public final /* bridge */ /* synthetic */ SortedSet subSet(Object r3, Object r4) {
        return zzp(r3, true, r4, false);
    }

    @Override // java.util.NavigableSet
    public final /* synthetic */ NavigableSet tailSet(Object r1, boolean r2) {
        r1.getClass();
        return zzr(r1, r2);
    }

    @Override // java.util.NavigableSet, java.util.SortedSet
    public final /* synthetic */ SortedSet tailSet(Object r2) {
        r2.getClass();
        return zzr(r2, true);
    }

    public abstract zzck zzf();

    public final zzck zzn() {
        zzck r0 = this.zzb;
        if (r0 != null) goto L6;
        zzck r02 = zzf();
        this.zzb = r02;
        r02.zzb = this;
        return r02;
    L6:
        return r0;
    }

    public abstract zzck zzo(Object r1, boolean r2);

    public final zzck zzp(Object r2, boolean r3, Object r4, boolean r5) {
        r2.getClass();
        r4.getClass();
        if (this.zza.compare(r2, r4) > 0) goto L5;
        boolean r0 = true;
    L6:
        zzbm.zzc(r0);
        return zzq(r2, r3, r4, r5);
    L5:
        r0 = false;
        goto L6
    }

    public abstract zzck zzq(Object r1, boolean r2, Object r3, boolean r4);

    public abstract zzck zzr(Object r1, boolean r2);
}
