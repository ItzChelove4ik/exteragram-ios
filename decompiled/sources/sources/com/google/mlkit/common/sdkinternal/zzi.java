package com.google.mlkit.common.sdkinternal;

/* loaded from: classes5.dex */
public final /* synthetic */ class zzi implements Runnable {
    public final /* synthetic */ Runnable zza;

    public /* synthetic */ zzi(Runnable r1) {
        this.zza = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MlKitThreadPool.zzc(this.zza);
    }
}
