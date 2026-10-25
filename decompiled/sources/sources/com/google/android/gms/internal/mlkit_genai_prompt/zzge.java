package com.google.android.gms.internal.mlkit_genai_prompt;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes4.dex */
final /* synthetic */ class zzge implements zzoj {
    private final /* synthetic */ zzgl zza;
    private final /* synthetic */ zzdq zzb;
    private final /* synthetic */ zzdy zzc;

    public /* synthetic */ zzge(zzgl r1, zzdq r2, zzdy r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzoj
    public final /* synthetic */ ListenableFuture zza(Object r4) {
        zzdh r42 = (zzdh) r4;
        ListenableFuture r3 = CallbackToFutureAdapter.getFuture(new zzgg(this.zza, r42, this.zzb, this.zzc));
        return r42.zzb().zza(r3, zzgh.zza);
    }
}
