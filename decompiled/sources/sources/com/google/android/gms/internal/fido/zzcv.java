package com.google.android.gms.internal.fido;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
final class zzcv extends zzck {
    static final zzcv zzc = null;
    final transient zzcc zzd;

    static {
        int r1 = zzcc.$r8$clinit;
        zzc = new zzcv(zzct.zza, zzcq.zza);
    }

    public zzcv(zzcc r1, Comparator r2) {
        super(r2);
        this.zzd = r1;
    }

    @Override // java.util.NavigableSet
    public final Object ceiling(Object r3) {
        zzcc r0 = this.zzd;
        int r32 = zzv(r3, true);
        if (r32 != r0.size()) goto L7;
        return null;
    L7:
        return this.zzd.get(r32);
    }

    @Override // com.google.android.gms.internal.fido.zzby, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object r3) {
        if (r3 != null) goto L10;
    L8:
        return false;
    L10:
        if (Collections.binarySearch(this.zzd, r3, this.zza) < 0) goto L8;
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean containsAll(Collection r7) {
        if ((r7 instanceof zzcp) == false) goto L6;
        r7 = ((zzcp) r7).zza();
    L6:
        if (zzdb.zza(this.zza, r7) == false) goto L28;
        if (r7.size() <= 1) goto L28;
        zzdd r0 = this.zzd.zzk(0);
        Iterator r72 = r7.iterator();
        if (r0.hasNext() == true) goto L13;
        return false;
    L13:
        Object r3 = r72.next();
        E r4 = r0.next();
    L30:
        int r5 = this.zza.compare(r4, r3);     // Catch: Throwable -> L29
        if (r5 < 0) goto L17;
        if (r5 != 0) goto L26;
        if (r72.hasNext() == false) goto L23;
        r3 = r72.next();     // Catch: Throwable -> L29
        goto L30
    L23:
        return true;
    L17:
        if (r0.hasNext() == false) goto L18;
        r4 = r0.next();     // Catch: Throwable -> L29
        goto L30
    L18:
        return false;
    L26:
        return false;
    L28:
        return super.containsAll(r7);
    }

    @Override // java.util.NavigableSet
    public final /* bridge */ /* synthetic */ Iterator descendingIterator() {
        return zzt();
    }

    @Override // com.google.android.gms.internal.fido.zzcf, java.util.Collection, java.util.Set
    public final boolean equals(Object r7) {
        if (r7 != this) goto L6;
        return true;
    L6:
        if ((r7 instanceof Set) == true) goto L8;
        return false;
    L8:
        Set r72 = (Set) r7;
        if (this.zzd.size() == r72.size()) goto L12;
        return false;
    L12:
        if (isEmpty() == false) goto L15;
        return true;
    L15:
        if (zzdb.zza(this.zza, r72) == false) goto L28;
        Iterator r73 = r72.iterator();
        zzdd r1 = this.zzd.zzk(0);     // Catch: Throwable -> L26
    L18:
        if (r1.hasNext() == false) goto L25;
        E r3 = r1.next();     // Catch: Throwable -> L26
        Object r4 = r73.next();     // Catch: Throwable -> L26
        if (r4 == null) goto L24;
        if (this.zza.compare(r3, r4) == 0) goto L18;
    L24:
        return false;
    L25:
        return true;
    L26:
        return false;
    L28:
        return containsAll(r72);
    }

    @Override // com.google.android.gms.internal.fido.zzck, java.util.SortedSet
    public final Object first() {
        if (isEmpty() == false) goto L5;
        Utils$$ExternalSyntheticBUOutline0.m();
        return null;
    L5:
        return this.zzd.get(0);
    }

    @Override // java.util.NavigableSet
    public final Object floor(Object r2) {
        int r22 = zzu(r2, true) - 1;
        if (r22 != (-1)) goto L7;
        return null;
    L7:
        return this.zzd.get(r22);
    }

    @Override // java.util.NavigableSet
    public final Object higher(Object r3) {
        zzcc r0 = this.zzd;
        int r32 = zzv(r3, false);
        if (r32 != r0.size()) goto L7;
        return null;
    L7:
        return this.zzd.get(r32);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet
    public final /* synthetic */ Iterator iterator() {
        return this.zzd.zzk(0);
    }

    @Override // com.google.android.gms.internal.fido.zzck, java.util.SortedSet
    public final Object last() {
        if (isEmpty() == false) goto L5;
        Utils$$ExternalSyntheticBUOutline0.m();
        return null;
    L5:
        return this.zzd.get(r1.size() - 1);
    }

    @Override // java.util.NavigableSet
    public final Object lower(Object r2) {
        int r22 = zzu(r2, false) - 1;
        if (r22 != (-1)) goto L7;
        return null;
    L7:
        return this.zzd.get(r22);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zzd.size();
    }

    @Override // com.google.android.gms.internal.fido.zzby
    public final int zza(Object[] r1, int r2) {
        return this.zzd.zza(r1, 0);
    }

    @Override // com.google.android.gms.internal.fido.zzby
    public final int zzb() {
        return this.zzd.zzb();
    }

    @Override // com.google.android.gms.internal.fido.zzby
    public final int zzc() {
        return this.zzd.zzc();
    }

    @Override // com.google.android.gms.internal.fido.zzcf, com.google.android.gms.internal.fido.zzby
    public final zzdc zzd() {
        return this.zzd.zzk(0);
    }

    @Override // com.google.android.gms.internal.fido.zzby
    public final Object[] zze() {
        return this.zzd.zze();
    }

    @Override // com.google.android.gms.internal.fido.zzck
    public final zzck zzf() {
        Comparator r0 = Collections.reverseOrder(this.zza);
        if (isEmpty() == false) goto L7;
        return zzck.zzs(r0);
    L7:
        return new zzcv(this.zzd.zzf(), r0);
    }

    @Override // com.google.android.gms.internal.fido.zzcf
    public final zzcc zzi() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.fido.zzck
    public final zzck zzo(Object r2, boolean r3) {
        return zzw(0, zzu(r2, r3));
    }

    @Override // com.google.android.gms.internal.fido.zzck
    public final zzck zzq(Object r1, boolean r2, Object r3, boolean r4) {
        return zzr(r1, r2).zzo(r3, r4);
    }

    @Override // com.google.android.gms.internal.fido.zzck
    public final zzck zzr(Object r2, boolean r3) {
        zzcc r0 = this.zzd;
        return zzw(zzv(r2, r3), r0.size());
    }

    public final zzdc zzt() {
        return this.zzd.zzf().zzk(0);
    }

    public final int zzu(Object r2, boolean r3) {
        r2.getClass();
        int r1 = Collections.binarySearch(this.zzd, r2, this.zza);
        if (r1 < 0) goto L8;
        if (r3 == true) goto L6;
        return r1;
    L6:
        return r1 + 1;
    L8:
        return ~r1;
    }

    public final int zzv(Object r2, boolean r3) {
        r2.getClass();
        int r1 = Collections.binarySearch(this.zzd, r2, this.zza);
        if (r1 < 0) goto L9;
        if (r3 == false) goto L7;
        return r1;
    L7:
        return r1 + 1;
    L9:
        return ~r1;
    }

    public final zzcv zzw(int r3, int r4) {
        if (r3 == 0) goto L4;
    L7:
        if (r3 >= r4) goto L11;
        zzcc r0 = this.zzd;
        Comparator r2 = this.zza;
        return new zzcv(r0.zzg(r3, r4), r2);
    L11:
        return zzck.zzs(this.zza);
    L4:
        if (r4 == this.zzd.size()) goto L6;
        r3 = 0;
        goto L7
    L6:
        return this;
    }
}
