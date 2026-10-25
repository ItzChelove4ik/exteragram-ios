package com.google.android.recaptcha.internal;

import java.util.Iterator;

/* loaded from: classes5.dex */
final class zzqr extends zzqb {
    static final zzqr zza = null;
    private static final Object[] zzd = null;
    final transient Object[] zzb;
    final transient Object[] zzc;
    private final transient int zze;
    private final transient int zzf;
    private final transient int zzg;

    static {
        Object[] r2 = new Object[0];
        zzd = r2;
        zza = new zzqr(r2, 0, r2, 0, 0);
    }

    public zzqr(Object[] r1, int r2, Object[] r3, int r4, int r5) {
        this.zzb = r1;
        this.zze = r2;
        this.zzc = r3;
        this.zzf = r4;
        this.zzg = r5;
    }

    @Override // com.google.android.recaptcha.internal.zzpq, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object r5) {
        if (r5 == null) goto L16;
        Object[] r1 = this.zzc;
        if (r1.length == 0) goto L16;
        int r2 = zzpn.zzb(r5);
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

    @Override // com.google.android.recaptcha.internal.zzqb, java.util.Collection, java.util.Set
    public final int hashCode() {
        return this.zze;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return zzj().zzj(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zzg;
    }

    @Override // com.google.android.recaptcha.internal.zzqb, com.google.android.recaptcha.internal.zzpq
    public final zzqu zza() {
        return zzj().zzj(0);
    }

    @Override // com.google.android.recaptcha.internal.zzpq
    public final Object[] zzb() {
        return this.zzb;
    }

    @Override // com.google.android.recaptcha.internal.zzpq
    public final int zzc() {
        return 0;
    }

    @Override // com.google.android.recaptcha.internal.zzpq
    public final int zzd() {
        return this.zzg;
    }

    @Override // com.google.android.recaptcha.internal.zzpq
    public final boolean zze() {
        return false;
    }

    @Override // com.google.android.recaptcha.internal.zzpq
    public final int zzf(Object[] r2, int r3) {
        Object[] r32 = this.zzb;
        int r1 = this.zzg;
        System.arraycopy(r32, 0, r2, 0, r1);
        return r1;
    }

    @Override // com.google.android.recaptcha.internal.zzqb
    public final boolean zzi() {
        return true;
    }

    @Override // com.google.android.recaptcha.internal.zzqb
    public final zzpt zzk() {
        return zzpt.zzi(this.zzb, this.zzg);
    }
}
