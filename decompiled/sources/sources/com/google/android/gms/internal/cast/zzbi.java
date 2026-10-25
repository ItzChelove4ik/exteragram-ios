package com.google.android.gms.internal.cast;

import android.app.Activity;
import android.view.ViewGroup;

/* loaded from: classes4.dex */
final /* synthetic */ class zzbi implements Runnable {
    private final /* synthetic */ zzbj zza;
    private final /* synthetic */ Activity zzb;

    public /* synthetic */ zzbi(zzbj r1, Activity r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        zzbk r0 = this.zza.zzc;
        if (r0.zzc() == false) goto L9;
        ((ViewGroup) this.zzb.getWindow().getDecorView()).removeView(r0);
        if (r0.zzb() == null) goto L7;
        r0.zzb().onOverlayDismissed();
    L7:
        r0.zza();
        return;
    }
}
