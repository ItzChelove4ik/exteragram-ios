package com.google.mlkit.vision.segmentation.subject.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zznz;
import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzoa;
import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzob;
import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzoc;
import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzqx;
import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zztl;
import com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzto;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.MobileVisionBase;
import com.google.mlkit.vision.segmentation.subject.SubjectSegmentationResult;
import com.google.mlkit.vision.segmentation.subject.SubjectSegmenter;
import com.google.mlkit.vision.segmentation.subject.SubjectSegmenterOptions;
import java.util.concurrent.Executor;

/* loaded from: classes5.dex */
public final class zzd extends MobileVisionBase implements SubjectSegmenter {
    public zzd(SubjectSegmenterOptions r1, zzj r2, Executor r3, zztl r4) {
        super(r2, r3);
        zzoc r0 = new zzoc();
        r0.zze(zznz.zzb);
        zzqx r22 = new zzqx();
        r22.zzc(r1.zza());
        r22.zzb(zzoa.zza);
        r0.zzg(r22.zzd());
        r4.zzd(zzto.zzg(r0, 1), zzob.zzew);
    }

    @Override // com.google.android.gms.common.api.OptionalModuleApi
    public final Feature[] getOptionalFeatures() {
        return new Feature[]{OptionalModuleUtils.FEATURE_SUBJECT_SEGMENTATION};
    }

    @Override // com.google.mlkit.vision.segmentation.subject.SubjectSegmenter
    public final Task<SubjectSegmentationResult> process(InputImage r1) {
        return super.processBase(r1);
    }
}
