package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import java.util.Map;

/* loaded from: classes4.dex */
final class zzaj extends zzx {
    final /* synthetic */ zzal zza;
    private final Object zzb;
    private int zzc;

    public zzaj(zzal r1, int r2) {
        this.zza = r1;
        Object[] r12 = r1.zzb;
        r12.getClass();
        this.zzb = r12[r2];
        this.zzc = r2;
    }

    private final void zza() {
        int r0 = this.zzc;
        if (r0 != (-1)) goto L5;
    L10:
        this.zzc = zzal.zzd(this.zza, this.zzb);
        return;
    L5:
        if (r0 >= this.zza.size()) goto L10;
        Object r02 = this.zzb;
        zzal r1 = this.zza;
        int r2 = this.zzc;
        Object[] r12 = r1.zzb;
        r12.getClass();
        if (zze.zza(r02, r12[r2]) == false) goto L10;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzx, java.util.Map.Entry
    public final Object getKey() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzx, java.util.Map.Entry
    public final Object getValue() {
        Map r0 = this.zza.zzj();
        if (r0 != null) goto L5;
        zza();
        int r02 = this.zzc;
        if (r02 != (-1)) goto L10;
        return null;
    L10:
        Object[] r2 = this.zza.zzc;
        r2.getClass();
        return r2[r02];
    L5:
        return r0.get(this.zzb);
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object r4) {
        Map r0 = this.zza.zzj();
        if (r0 != null) goto L5;
        zza();
        int r02 = this.zzc;
        zzal r1 = this.zza;
        if (r02 != (-1)) goto L10;
        r1.put(this.zzb, r4);
        return null;
    L10:
        Object[] r3 = r1.zzc;
        r3.getClass();
        Object r12 = r3[r02];
        r3[r02] = r4;
        return r12;
    L5:
        return r0.put(this.zzb, r4);
    }
}
