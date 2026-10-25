package com.google.android.gms.internal.cast;

import java.util.Iterator;

/* loaded from: classes4.dex */
final class zzik extends zzhz {
    final transient Object zza;

    public zzik(Object r1) {
        r1.getClass();
        this.zza = r1;
    }

    @Override // com.google.android.gms.internal.cast.zzhr, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object r1) {
        return this.zza.equals(r1);
    }

    @Override // com.google.android.gms.internal.cast.zzhz, java.util.Collection, java.util.Set
    public final int hashCode() {
        return this.zza.hashCode();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return new zzia(this.zza);
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

    @Override // com.google.android.gms.internal.cast.zzhz, com.google.android.gms.internal.cast.zzhr
    public final zzhv zze() {
        int r0 = zzhv.$r8$clinit;
        Object[] r1 = {this.zza};
        zzib.zza(r1, 1);
        return zzhv.zzk(r1, 1);
    }

    @Override // com.google.android.gms.internal.cast.zzhr
    public final int zzg(Object[] r1, int r2) {
        r1[0] = this.zza;
        return 1;
    }
}
