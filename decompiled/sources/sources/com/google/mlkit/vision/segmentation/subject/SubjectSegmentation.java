package com.google.mlkit.vision.segmentation.subject;

import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.vision.segmentation.subject.internal.zzc;

/* loaded from: classes5.dex */
public abstract class SubjectSegmentation {
    public static SubjectSegmenter getClient(SubjectSegmenterOptions r2) {
        return ((zzc) MlKitContext.getInstance().get(zzc.class)).zza(r2);
    }
}
