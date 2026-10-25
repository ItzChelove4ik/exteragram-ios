package com.google.android.gms.internal.play_billing;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
final class zzdb implements Runnable {
    zzde zza;

    public zzdb(zzde r1) {
        this.zza = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzde r1 = this.zza;
        if (r1 == null) goto L33;
        zzcz r2 = zzde.zzr(r1);
        if (r2 == null) goto L29;
        this.zza = null;
        if (r2.isDone() == false) goto L32;
        r1.zzj(r2);
        return;
    L32:
        ScheduledFuture r4 = zzde.zzt(r1);     // Catch: Throwable -> L23
        zzde.zzu(r1, null);     // Catch: Throwable -> L23
        String r5 = "Timed out";
        if (r4 != null) goto L30;
    L19:
        r1.zzi(new zzdc(r5 + ": " + r2.toString(), null));     // Catch: Throwable -> L23
        r2.cancel(true);
        return;
    L17:
        th = move-exception;
        r1.zzi(new zzdc(r5, null));     // Catch: Throwable -> L23
        throw th;     // Catch: Throwable -> L23
    L30:
        long r6 = Math.abs(r4.getDelay(TimeUnit.MILLISECONDS));     // Catch: Throwable -> L17
        if (r6 <= 10) goto L19;
        r5 = "Timed out (timeout delayed by " + r6 + " ms after scheduled time)";     // Catch: Throwable -> L17
    L23:
        th = move-exception;
        r2.cancel(true);
        throw th;
    L29:
        return;
    }
}
