package com.google.android.gms.internal.mlkit_genai_prompt;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.SortedSet;
import okio.Segment$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public abstract class zzmt extends zzms implements NavigableSet, zznq {
    final transient Comparator zza;
    transient zzmt zzb;

    public zzmt(Comparator r1) {
        this.zza = r1;
    }

    public static zznn zzl(Comparator r2) {
        if (zzmz.zza.equals(r2) == true) goto L5;
        int r1 = zzml.$r8$clinit;
        return new zznn(zzng.zza, r2);
    L5:
        return zznn.zzc;
    }

    @Deprecated
    public final void addFirst(Object r1) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void addLast(Object r1) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.SortedSet, com.google.android.gms.internal.mlkit_genai_prompt.zznq
    public final Comparator comparator() {
        return this.zza;
    }

    @Override // java.util.NavigableSet
    public final /* bridge */ /* synthetic */ NavigableSet descendingSet() {
        zzmt r0 = this.zzb;
        if (r0 != null) goto L6;
        zzmt r02 = zzq();
        this.zzb = r02;
        r02.zzb = this;
        return r02;
    L6:
        return r0;
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
        return zzn(r1, r2);
    }

    @Override // java.util.NavigableSet, java.util.SortedSet
    public final /* synthetic */ SortedSet headSet(Object r2) {
        r2.getClass();
        return zzn(r2, false);
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
        return zzm(r1, r2, r3, r4);
    }

    @Override // java.util.NavigableSet, java.util.SortedSet
    public final /* bridge */ /* synthetic */ SortedSet subSet(Object r3, Object r4) {
        return zzm(r3, true, r4, false);
    }

    @Override // java.util.NavigableSet
    public final /* synthetic */ NavigableSet tailSet(Object r1, boolean r2) {
        r1.getClass();
        return zzp(r1, r2);
    }

    @Override // java.util.NavigableSet, java.util.SortedSet
    public final /* synthetic */ SortedSet tailSet(Object r2) {
        r2.getClass();
        return zzp(r2, true);
    }

    public final zzmt zzm(Object r2, boolean r3, Object r4, boolean r5) {
        r2.getClass();
        r4.getClass();
        if (this.zza.compare(r2, r4) <= 0) goto L5;
        Segment$$ExternalSyntheticBUOutline0.m();
        return null;
    L5:
        return zzo(r2, r3, r4, r5);
    }

    public abstract zzmt zzn(Object r1, boolean r2);

    public abstract zzmt zzo(Object r1, boolean r2, Object r3, boolean r4);

    public abstract zzmt zzp(Object r1, boolean r2);

    public abstract zzmt zzq();
}
