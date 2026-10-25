package com.google.android.gms.internal.cast;

import android.annotation.TargetApi;
import android.view.Choreographer;

/* loaded from: classes4.dex */
public abstract class zzgt {
    private Choreographer.FrameCallback zzb;

    public zzgt() {
    }

    public abstract void zza(long r1);

    @TargetApi(16)
    public final Choreographer.FrameCallback zzb() {
        if (this.zzb != null) goto L6;
        this.zzb = new zzgs(this);
    L6:
        return this.zzb;
    }
}
