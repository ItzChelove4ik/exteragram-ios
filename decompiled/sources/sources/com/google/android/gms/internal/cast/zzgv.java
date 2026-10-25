package com.google.android.gms.internal.cast;

import android.annotation.TargetApi;
import android.view.Choreographer;

@TargetApi(16)
/* loaded from: classes4.dex */
final class zzgv extends zzgw {
    private final Choreographer zza;

    public zzgv() {
        this.zza = Choreographer.getInstance();
    }

    @Override // com.google.android.gms.internal.cast.zzgw
    public final void zza(zzgt r1) {
        this.zza.postFrameCallback(r1.zzb());
    }
}
