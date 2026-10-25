package com.google.mlkit.vision.segmentation.subject.internal;

import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zztl;
import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zztn;
import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zztw;
import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.vision.segmentation.subject.SubjectSegmenterOptions;

/* loaded from: classes.dex */
public final class zze extends LazyInstanceMap {
    private final MlKitContext zza;

    public zze(MlKitContext r1) {
        this.zza = r1;
    }

    @Override // com.google.mlkit.common.sdkinternal.LazyInstanceMap
    public final /* bridge */ /* synthetic */ Object create(Object r4) {
        MlKitContext r0 = this.zza;
        zztl r2 = zztw.zzb("subject-segmentation");
        zztn r02 = zztn.zza(r0.getApplicationContext());
        MlKitContext r3 = this.zza;
        return new zzj(r3, (SubjectSegmenterOptions) r4, r2, r02);
    }
}
