package com.google.mlkit.vision.label.defaults.thin;

import com.google.android.gms.common.Feature;
import com.google.android.gms.internal.mlkit_vision_label.zzkd;
import com.google.android.gms.internal.mlkit_vision_label.zzkf;
import com.google.android.gms.internal.mlkit_vision_label.zzkg;
import com.google.android.gms.internal.mlkit_vision_label.zznp;
import com.google.android.gms.internal.mlkit_vision_label.zzns;
import com.google.android.gms.internal.mlkit_vision_label.zzoa;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.common.sdkinternal.MLTask;
import com.google.mlkit.vision.common.internal.MultiFlavorDetectorCreator;
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions;
import com.google.mlkit.vision.label.internal.ImageLabelerImpl;

/* loaded from: classes.dex */
final class zzd implements MultiFlavorDetectorCreator.DetectorCreator {
    private final zze zza;
    private final ExecutorSelector zzb;
    private final zznp zzc;

    public zzd(zze r2, ExecutorSelector r3) {
        zznp r0 = zzoa.zzb("play-services-mlkit-image-labeling");
        this.zza = r2;
        this.zzb = r3;
        this.zzc = r0;
    }

    @Override // com.google.mlkit.vision.common.internal.MultiFlavorDetectorCreator.DetectorCreator
    public final /* bridge */ /* synthetic */ MultiFlavorDetectorCreator.MultiFlavorDetector create(MultiFlavorDetectorCreator.DetectorOptions r5) {
        ImageLabelerOptions r52 = (ImageLabelerOptions) r5;
        zznp r0 = this.zzc;
        zzkg r1 = new zzkg();
        r1.zze(zzkd.zzb);
        r0.zzd(zzns.zzg(r1, 1), zzkf.zzo);
        return ImageLabelerImpl.newInstance((MLTask) this.zza.get(r52), this.zzb.getExecutorToUse(r52.getExecutor()), new Feature("vision.ica", 1));
    }
}
