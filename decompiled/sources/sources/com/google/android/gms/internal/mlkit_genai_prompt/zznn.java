package com.google.android.gms.internal.mlkit_genai_prompt;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
final class zznn extends zzmt {
    static final zznn zzc = null;
    final transient zzml zzd;

    static {
        int r1 = zzml.$r8$clinit;
        zzc = new zznn(zzng.zza, zzmz.zza);
    }

    public zznn(zzml r1, Comparator r2) {
        super(r2);
        this.zzd = r1;
    }

    @Override // java.util.NavigableSet
    public final Object ceiling(Object r3) {
        zzml r0 = this.zzd;
        int r2 = zzt(r3, true);
        if (r2 != r0.size()) goto L7;
        return null;
    L7:
        return r0.get(r2);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzmg, java.util.AbstractCollection, java.util.Collection
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
        if ((r7 instanceof zzmy) == false) goto L5;
        r7 = ((zzmy) r7).zza();
    L5:
        Comparator r0 = this.zza;
        if (zznr.zza(r0, r7) == false) goto L27;
        if (r7.size() <= 1) goto L27;
        zznt r6 = this.zzd.zzs(0);
        Iterator r72 = r7.iterator();
        if (r6.hasNext() == true) goto L12;
        return false;
    L12:
        Object r3 = r72.next();
        E r4 = r6.next();
    L29:
        int r5 = r0.compare(r4, r3);     // Catch: Throwable -> L28
        if (r5 < 0) goto L16;
        if (r5 != 0) goto L25;
        if (r72.hasNext() == false) goto L22;
        r3 = r72.next();     // Catch: Throwable -> L28
        goto L29
    L22:
        return true;
    L16:
        if (r6.hasNext() == false) goto L17;
        r4 = r6.next();     // Catch: Throwable -> L28
        goto L29
    L17:
        return false;
    L25:
        return false;
    L27:
        return super.containsAll(r7);
    }

    @Override // java.util.NavigableSet
    public final /* bridge */ /* synthetic */ Iterator descendingIterator() {
        return zzr();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzms, java.util.Collection, java.util.Set
    public final boolean equals(Object r7) {
        if (r7 != this) goto L6;
        return true;
    L6:
        if ((r7 instanceof Set) == true) goto L8;
        return false;
    L8:
        Set r72 = (Set) r7;
        zzml r1 = this.zzd;
        if (r1.size() == r72.size()) goto L12;
        return false;
    L12:
        if (isEmpty() == false) goto L15;
        return true;
    L15:
        if (zznr.zza(this.zza, r72) == false) goto L28;
        Iterator r73 = r72.iterator();
        zznt r12 = r1.zzs(0);     // Catch: Throwable -> L26
    L18:
        if (r12.hasNext() == false) goto L25;
        E r3 = r12.next();     // Catch: Throwable -> L26
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

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzmt, java.util.SortedSet
    public final Object first() {
        if (isEmpty() == false) goto L5;
        Utils$$ExternalSyntheticBUOutline0.m();
        return null;
    L5:
        return this.zzd.get(0);
    }

    @Override // java.util.NavigableSet
    public final Object floor(Object r2) {
        int r22 = zzs(r2, true) - 1;
        if (r22 != (-1)) goto L7;
        return null;
    L7:
        return this.zzd.get(r22);
    }

    @Override // java.util.NavigableSet
    public final Object higher(Object r3) {
        zzml r0 = this.zzd;
        int r2 = zzt(r3, false);
        if (r2 != r0.size()) goto L7;
        return null;
    L7:
        return r0.get(r2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet
    public final /* synthetic */ Iterator iterator() {
        return this.zzd.zzs(0);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzmt, java.util.SortedSet
    public final Object last() {
        if (isEmpty() == false) goto L5;
        Utils$$ExternalSyntheticBUOutline0.m();
        return null;
    L5:
        return this.zzd.get(r1.size() - 1);
    }

    @Override // java.util.NavigableSet
    public final Object lower(Object r2) {
        int r22 = zzs(r2, false) - 1;
        if (r22 != (-1)) goto L7;
        return null;
    L7:
        return this.zzd.get(r22);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zzd.size();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzms
    public final zzns zza() {
        return this.zzd.zzs(0);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzmg
    public final Object[] zzb() {
        return this.zzd.zzb();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzmg
    public final int zzc() {
        return this.zzd.zzc();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzmg
    public final int zzd() {
        return this.zzd.zzd();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzms, com.google.android.gms.internal.mlkit_genai_prompt.zzmg
    public final zzml zze() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzmg
    public final int zzg(Object[] r1, int r2) {
        return this.zzd.zzg(r1, 0);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzmt
    public final zzmt zzn(Object r2, boolean r3) {
        return zzu(0, zzs(r2, r3));
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzmt
    public final zzmt zzo(Object r1, boolean r2, Object r3, boolean r4) {
        return zzp(r1, r2).zzn(r3, r4);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzmt
    public final zzmt zzp(Object r2, boolean r3) {
        zzml r0 = this.zzd;
        return zzu(zzt(r2, r3), r0.size());
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzmt
    public final zzmt zzq() {
        Comparator r0 = Collections.reverseOrder(this.zza);
        if (isEmpty() == false) goto L7;
        return zzmt.zzl(r0);
    L7:
        return new zznn(this.zzd.zzh(), r0);
    }

    public final zzns zzr() {
        return this.zzd.zzh().zzs(0);
    }

    public final int zzs(Object r2, boolean r3) {
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

    public final int zzt(Object r2, boolean r3) {
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

    public final zznn zzu(int r3, int r4) {
        if (r3 == 0) goto L4;
    L7:
        if (r3 >= r4) goto L11;
        zzml r0 = this.zzd;
        Comparator r2 = this.zza;
        return new zznn(r0.zzi(r3, r4), r2);
    L11:
        return zzmt.zzl(this.zza);
    L4:
        if (r4 == this.zzd.size()) goto L6;
        r3 = 0;
        goto L7
    L6:
        return this;
    }
}
