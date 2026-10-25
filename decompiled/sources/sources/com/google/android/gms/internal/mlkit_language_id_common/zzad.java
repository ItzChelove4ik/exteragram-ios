package com.google.android.gms.internal.mlkit_language_id_common;

/* loaded from: classes4.dex */
final class zzad extends zzw {
    static final zzw zza = null;
    final transient Object[] zzb;
    private final transient int zzc;

    static {
        zza = new zzad(null, new Object[0], 0);
    }

    private zzad(Object r1, Object[] r2, int r3) {
        this.zzb = r2;
        this.zzc = r3;
    }

    public static zzad zzg(int r1, Object[] r2, zzv r3) {
        Object r12 = r2[0];
        r12.getClass();
        Object r0 = r2[1];
        r0.getClass();
        zzn.zza(r12, r0);
        return new zzad(null, r2, 1);
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzw, java.util.Map
    public final Object get(Object r4) {
        Object[] r0 = this.zzb;
        int r3 = this.zzc;
        if (r4 != null) goto L6;
    L4:
        Object r32 = null;
    L10:
        if (r32 != null) goto L12;
        return null;
    L12:
        return r32;
    L6:
        if (r3 != 1) goto L4;
        Object r33 = r0[0];
        r33.getClass();
        if (r33.equals(r4) == false) goto L4;
        r32 = r0[1];
        r32.getClass();
        goto L10
    }

    @Override // java.util.Map
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzw
    public final zzq zza() {
        return new zzac(this.zzb, 1, this.zzc);
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzw
    public final zzx zzd() {
        return new zzaa(this, this.zzb, 0, this.zzc);
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzw
    public final zzx zze() {
        return new zzab(this, new zzac(this.zzb, 0, this.zzc));
    }
}
