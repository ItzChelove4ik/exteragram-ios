package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import java.io.Serializable;

/* loaded from: classes4.dex */
final class zzar extends zzx implements Serializable {
    final Object zza;
    final Object zzb;

    public zzar(Object r1, Object r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzx, java.util.Map.Entry
    public final Object getKey() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzx, java.util.Map.Entry
    public final Object getValue() {
        return this.zzb;
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object r1) {
        throw new UnsupportedOperationException();
    }
}
