package com.google.android.recaptcha.internal;

import java.util.TimerTask;
import kotlinx.coroutines.BuildersKt;

/* loaded from: classes5.dex */
public final class zzfl extends TimerTask {
    final /* synthetic */ zzfp zza;
    final /* synthetic */ zzmg zzb;

    public zzfl(zzfp r1, zzmg r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        zzfp r0 = this.zza;
        BuildersKt.launch$default(zzfp.zzk(r0).zzd(), null, null, new zzfk(r0, this.zzb, null), 3, null);
    }
}
