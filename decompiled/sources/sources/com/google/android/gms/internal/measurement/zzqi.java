package com.google.android.gms.internal.measurement;

import android.os.Process;
import android.util.Log;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public final class zzqi implements zzqm {
    private static boolean zza;
    private final Supplier zzb;
    private final int zzc;
    private final Supplier zzd;

    public zzqi(Supplier r2, int r3) {
        zzqh r32 = zzqh.zza;
        this.zzb = r2;
        this.zzc = Math.max(5, 10);
        this.zzd = r32;
    }

    @Override // com.google.android.gms.internal.measurement.zzqm
    public final void zza() {
        monitor-enter(zzqi.class);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (zza == true) goto L9;
        zzqg r4 = new zzqg(this);     // Catch: Throwable -> L7
        long r6 = this.zzc;     // Catch: Throwable -> L7
        TimeUnit r8 = TimeUnit.MINUTES;     // Catch: Throwable -> L7
        ListeningScheduledExecutorService r5 = (ListeningScheduledExecutorService) this.zzb.get();     // Catch: Throwable -> L7
        zzpx.zza(r5.schedule(new zzqf(this, r4, r5, r6, r8), r6, r8));     // Catch: Throwable -> L7
        zza = true;     // Catch: Throwable -> L7
    L9:
        monitor-exit(zzqi.class);     // Catch: Throwable -> L7
    }

    public final /* synthetic */ void zzb() {
        if (((Boolean) this.zzd.get()).booleanValue() == false) goto L6;
        Log.i("PhenotypeProcessReaper", "Killing process to refresh experiment configuration");
        Process.killProcess(Process.myPid());
        System.exit(0);
        return;
    }
}
