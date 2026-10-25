package com.google.mlkit.vision.common.internal;

import com.google.mlkit.vision.common.InputImage;
import java.util.concurrent.Callable;

/* loaded from: classes5.dex */
public final /* synthetic */ class zza implements Callable {
    public final /* synthetic */ MobileVisionBase zza;
    public final /* synthetic */ InputImage zzb;

    public /* synthetic */ zza(MobileVisionBase r1, InputImage r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        return this.zza.zza(this.zzb);
    }
}
