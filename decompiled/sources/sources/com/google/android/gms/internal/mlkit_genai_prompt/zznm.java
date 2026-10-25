package com.google.android.gms.internal.mlkit_genai_prompt;

import java.util.Iterator;

/* loaded from: classes4.dex */
final class zznm extends zzms {
    static final zznm zza = null;
    private static final Object[] zzd = null;
    final transient Object[] zzb;
    final transient Object[] zzc;
    private final transient int zze;
    private final transient int zzf;
    private final transient int zzg;

    static {
        Object[] r2 = new Object[0];
        zzd = r2;
        zza = new zznm(r2, 0, r2, 0, 0);
    }

    public zznm(Object[] r1, int r2, Object[] r3, int r4, int r5) {
        this.zzb = r1;
        this.zze = r2;
        this.zzc = r3;
        this.zzf = r4;
        this.zzg = r5;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzmg, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object r5) {
        if (r5 == null) goto L15;
        Object[] r1 = this.zzc;
        if (r1.length == 0) goto L15;
        int r2 = zzmd.zza(r5.hashCode());
    L7:
        int r22 = r2 & this.zzf;
        Object r3 = r1[r22];
        if (r3 == null) goto L9;
        if (r3.equals(r5) == true) goto L12;
        r2 = r22 + 1;
        goto L7
    L12:
        return true;
    L9:
        return false;
    L15:
        return false;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzms, java.util.Collection, java.util.Set
    public final int hashCode() {
        return this.zze;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return zze().zzs(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzms
    public final zzns zza() {
        return zze().zzs(0);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzmg
    public final Object[] zzb() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzmg
    public final int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzmg
    public final int zzd() {
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzmg
    public final int zzg(Object[] r2, int r3) {
        Object[] r32 = this.zzb;
        int r1 = this.zzg;
        System.arraycopy(r32, 0, r2, 0, r1);
        return r1;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzms
    public final boolean zzi() {
        return true;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzms
    public final zzml zzj() {
        return zzml.zzr(this.zzb, this.zzg);
    }
}
