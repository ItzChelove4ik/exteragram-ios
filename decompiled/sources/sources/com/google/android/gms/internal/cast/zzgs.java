package com.google.android.gms.internal.cast;

import android.view.Choreographer;

/* loaded from: classes4.dex */
final /* synthetic */ class zzgs implements Choreographer.FrameCallback {
    private final /* synthetic */ zzgt zza;

    public /* synthetic */ zzgs(zzgt r1) {
        this.zza = r1;
    }

    @Override // android.view.Choreographer.FrameCallback
    public final /* synthetic */ void doFrame(long r1) {
        this.zza.zza(r1);
    }
}
