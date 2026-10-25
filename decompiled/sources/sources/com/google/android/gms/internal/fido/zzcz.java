package com.google.android.gms.internal.fido;

import java.util.Iterator;

/* loaded from: classes4.dex */
final class zzcz extends zzcf {
    final transient Object zza;

    public zzcz(Object r1) {
        r1.getClass();
        this.zza = r1;
    }

    @Override // com.google.android.gms.internal.fido.zzby, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object r1) {
        return this.zza.equals(r1);
    }

    @Override // com.google.android.gms.internal.fido.zzcf, java.util.Collection, java.util.Set
    public final int hashCode() {
        return this.zza.hashCode();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return new zzcm(this.zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return 1;
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        return "[" + this.zza.toString() + "]";
    }

    @Override // com.google.android.gms.internal.fido.zzby
    public final int zza(Object[] r1, int r2) {
        r1[0] = this.zza;
        return 1;
    }

    @Override // com.google.android.gms.internal.fido.zzcf, com.google.android.gms.internal.fido.zzby
    public final zzdc zzd() {
        return new zzcm(this.zza);
    }

    @Override // com.google.android.gms.internal.fido.zzcf
    public final zzcc zzi() {
        return zzcc.zzj(this.zza);
    }
}
