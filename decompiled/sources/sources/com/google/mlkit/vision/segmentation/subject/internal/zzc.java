package com.google.mlkit.vision.segmentation.subject.internal;

import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zztw;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.vision.segmentation.subject.SubjectSegmenterOptions;

/* loaded from: classes.dex */
public final class zzc {
    private final zze zza;
    private final ExecutorSelector zzb;

    public zzc(zze r1, ExecutorSelector r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    public final zzd zza(SubjectSegmenterOptions r4) {
        return new zzd(r4, (zzj) this.zza.get(r4), this.zzb.getExecutorToUse(r4.zzb()), zztw.zzb("subject-segmentation"));
    }
}
