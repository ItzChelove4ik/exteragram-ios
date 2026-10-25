package com.google.mlkit.common.sdkinternal;

/* loaded from: classes5.dex */
public final /* synthetic */ class zzt implements Runnable {
    public final /* synthetic */ TaskQueue zza;
    public final /* synthetic */ Runnable zzb;

    public /* synthetic */ zzt(TaskQueue r1, Runnable r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzx r0 = new zzx(this.zza, null);
        this.zzb.run();     // Catch: Throwable -> L6
        r0.close();
        return;
    L6:
        th = move-exception;
        r0.close();     // Catch: Throwable -> L9
    L11:
        throw th;
    L9:
        th = move-exception;
        th.addSuppressed(th);
        goto L11
    }
}
