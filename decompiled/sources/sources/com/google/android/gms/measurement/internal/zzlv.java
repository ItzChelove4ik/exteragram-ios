package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzlv implements Runnable {
    final /* synthetic */ Bundle zza;
    final /* synthetic */ zzlu zzb;
    final /* synthetic */ zzlu zzc;
    final /* synthetic */ long zzd;
    final /* synthetic */ zzmb zze;

    public zzlv(zzmb r1, Bundle r2, zzlu r3, zzlu r4, long r5) {
        this.zza = r2;
        this.zzb = r3;
        this.zzc = r4;
        this.zzd = r5;
        Objects.requireNonNull(r1);
        this.zze = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Bundle r3 = this.zza;
        r3.remove("screen_name");
        r3.remove("screen_class");
        zzmb r6 = this.zze;
        Bundle r10 = r6.zzu.zzk().zzH(null, "screen_view", r3, null, false);
        r6.zzm(this.zzb, this.zzc, this.zzd, true, r10);
    }
}
