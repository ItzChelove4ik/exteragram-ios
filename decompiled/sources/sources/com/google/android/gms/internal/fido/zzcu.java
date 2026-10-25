package com.google.android.gms.internal.fido;

import java.util.Iterator;

/* loaded from: classes4.dex */
final class zzcu extends zzcf {
    static final zzcu zza = null;
    private static final Object[] zzd = null;
    final transient Object[] zzb;
    final transient Object[] zzc;
    private final transient int zze;
    private final transient int zzf;
    private final transient int zzg;

    static {
        Object[] r2 = new Object[0];
        zzd = r2;
        zza = new zzcu(r2, 0, r2, 0, 0);
    }

    public zzcu(Object[] r1, int r2, Object[] r3, int r4, int r5) {
        this.zzb = r1;
        this.zze = r2;
        this.zzc = r3;
        this.zzf = r4;
        this.zzg = r5;
    }

    @Override // com.google.android.gms.internal.fido.zzby, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object r5) {
        if (r5 == null) goto L16;
        Object[] r1 = this.zzc;
        if (r1.length == 0) goto L16;
        int r2 = zzbx.zza(r5.hashCode());
    L8:
        int r22 = r2 & this.zzf;
        Object r3 = r1[r22];
        if (r3 == null) goto L10;
        if (r3.equals(r5) == true) goto L13;
        r2 = r22 + 1;
        goto L8
    L13:
        return true;
    L10:
        return false;
    L16:
        return false;
    }

    @Override // com.google.android.gms.internal.fido.zzcf, java.util.Collection, java.util.Set
    public final int hashCode() {
        return this.zze;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return zzi().zzk(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.fido.zzby
    public final int zza(Object[] r3, int r4) {
        System.arraycopy(this.zzb, 0, r3, 0, this.zzg);
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.fido.zzby
    public final int zzb() {
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.fido.zzby
    public final int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.fido.zzcf, com.google.android.gms.internal.fido.zzby
    public final zzdc zzd() {
        return zzi().zzk(0);
    }

    @Override // com.google.android.gms.internal.fido.zzby
    public final Object[] zze() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.fido.zzcf
    public final boolean zzg() {
        return true;
    }

    @Override // com.google.android.gms.internal.fido.zzcf
    public final zzcc zzj() {
        return zzcc.zzh(this.zzb, this.zzg);
    }
}
