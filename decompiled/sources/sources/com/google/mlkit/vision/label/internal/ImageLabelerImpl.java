package com.google.mlkit.vision.label.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.common.sdkinternal.MLTask;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.MobileVisionBase;
import com.google.mlkit.vision.label.ImageLabel;
import com.google.mlkit.vision.label.ImageLabeler;
import java.util.List;
import java.util.concurrent.Executor;

/* loaded from: classes5.dex */
public class ImageLabelerImpl extends MobileVisionBase<List<ImageLabel>> implements ImageLabeler {
    private final Feature zzb;

    private ImageLabelerImpl(MLTask r1, Executor r2, Feature r3) {
        super(r1, r2);
        this.zzb = r3;
    }

    public static ImageLabelerImpl newInstance(MLTask<List<ImageLabel>, InputImage> r1, Executor r2, Feature r3) {
        return new ImageLabelerImpl(r1, r2, r3);
    }

    @Override // com.google.android.gms.common.api.OptionalModuleApi
    public final Feature[] getOptionalFeatures() {
        Feature r2 = this.zzb;
        if (r2 == null) goto L7;
        return new Feature[]{r2};
    L7:
        return OptionalModuleUtils.EMPTY_FEATURES;
    }

    @Override // com.google.mlkit.vision.label.ImageLabeler
    public final Task<List<ImageLabel>> process(InputImage r1) {
        return processBase(r1);
    }
}
