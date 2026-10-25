package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

/* loaded from: classes4.dex */
final class zzbq extends zzax {
    static final zzax zza = null;
    final transient Object[] zzb;
    private final transient int zzc;

    static {
        zza = new zzbq(null, new Object[0], 0);
    }

    private zzbq(Object r1, Object[] r2, int r3) {
        this.zzb = r2;
        this.zzc = r3;
    }

    public static zzbq zzg(int r1, Object[] r2, zzaw r3) {
        Object r12 = r2[0];
        r12.getClass();
        Object r0 = r2[1];
        r0.getClass();
        zzab.zzb(r12, r0);
        return new zzbq(null, r2, 1);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzax, java.util.Map
    public final Object get(Object r4) {
        if (r4 != null) goto L5;
    L4:
        Object r3 = null;
    L10:
        if (r3 != null) goto L12;
        return null;
    L12:
        return r3;
    L5:
        int r1 = this.zzc;
        Object[] r32 = this.zzb;
        if (r1 != 1) goto L4;
        Object r12 = r32[0];
        r12.getClass();
        if (r12.equals(r4) == false) goto L4;
        r3 = r32[1];
        r3.getClass();
        goto L10
    }

    @Override // java.util.Map
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzax
    public final zzaq zza() {
        int r0 = this.zzc;
        return new zzbp(this.zzb, 1, r0);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzax
    public final zzay zzd() {
        int r0 = this.zzc;
        return new zzbn(this, this.zzb, 0, r0);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzax
    public final zzay zze() {
        int r0 = this.zzc;
        return new zzbo(this, new zzbp(this.zzb, 0, r0));
    }
}
