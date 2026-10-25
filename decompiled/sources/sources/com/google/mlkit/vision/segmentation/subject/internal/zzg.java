package com.google.mlkit.vision.segmentation.subject.internal;

import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzoa;
import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzta;
import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zztk;
import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzuh;
import com.google.mlkit.vision.common.InputImage;

/* loaded from: classes5.dex */
public final /* synthetic */ class zzg implements zztk {
    public final /* synthetic */ zzj zza;
    public final /* synthetic */ long zzb;
    public final /* synthetic */ zzoa zzc;
    public final /* synthetic */ boolean zzd;
    public final /* synthetic */ InputImage zze;
    public final /* synthetic */ zzuh zzf;

    public /* synthetic */ zzg(zzj r1, long r2, zzoa r4, boolean r5, InputImage r6, zzuh r7) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r4;
        this.zzd = r5;
        this.zze = r6;
        this.zzf = r7;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_subject_segmentation.zztk
    public final zzta zza() {
        return this.zza.zzc(this.zzb, this.zzc, this.zzd, this.zze, this.zzf);
    }
}
