package com.google.mlkit.vision.label.defaults.thin;

import android.content.Context;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.internal.mlkit_vision_label.zzoa;
import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions;

/* loaded from: classes.dex */
public final class zze extends LazyInstanceMap {
    private final MlKitContext zza;

    public zze(MlKitContext r1) {
        this.zza = r1;
    }

    @Override // com.google.mlkit.common.sdkinternal.LazyInstanceMap
    public final /* bridge */ /* synthetic */ Object create(Object r3) {
        ImageLabelerOptions r32 = (ImageLabelerOptions) r3;
        Context r2 = this.zza.getApplicationContext();
        if (GoogleApiAvailabilityLight.getInstance().getApkVersion(r2) < 204700000) goto L5;
        zzb r0 = new zza(r2, r32);
    L7:
        return new zzh(r32, r0, zzoa.zzb("play-services-mlkit-image-labeling"));
    L5:
        r0 = new zzc(r2, r32);
        goto L7
    }
}
