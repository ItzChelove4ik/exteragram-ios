package com.google.android.gms.cast.framework.media;

import java.util.Objects;
import java.util.TimerTask;

/* loaded from: classes4.dex */
final class zzbf extends TimerTask {
    final /* synthetic */ zzbg zza;

    public zzbf(zzbg r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        zzbg r0 = this.zza;
        RemoteMediaClient r1 = r0.zza;
        r1.zzo(r0.zzh());
        long r2 = r0.zzi();
        r1.zzs().postDelayed(this, r2);
    }
}
