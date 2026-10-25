package com.google.android.recaptcha.internal;

import java.util.Iterator;

/* loaded from: classes5.dex */
final class zzqt extends zzqb {
    final transient Object zza;

    public zzqt(Object r1) {
        r1.getClass();
        this.zza = r1;
    }

    @Override // com.google.android.recaptcha.internal.zzpq, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object r1) {
        return this.zza.equals(r1);
    }

    @Override // com.google.android.recaptcha.internal.zzqb, java.util.Collection, java.util.Set
    public final int hashCode() {
        return this.zza.hashCode();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return new zzqg(this.zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return 1;
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        String r2 = this.zza.toString();
        StringBuilder r1 = new StringBuilder(String.valueOf(r2).length() + 2);
        r1.append("[");
        r1.append(r2);
        r1.append("]");
        return r1.toString();
    }

    @Override // com.google.android.recaptcha.internal.zzqb, com.google.android.recaptcha.internal.zzpq
    public final zzqu zza() {
        return new zzqg(this.zza);
    }

    @Override // com.google.android.recaptcha.internal.zzpq
    public final boolean zze() {
        return false;
    }

    @Override // com.google.android.recaptcha.internal.zzpq
    public final int zzf(Object[] r1, int r2) {
        r1[0] = this.zza;
        return 1;
    }
}
