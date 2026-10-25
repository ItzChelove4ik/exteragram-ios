package com.google.android.gms.cloudmessaging;

/* loaded from: classes.dex */
final /* synthetic */ class zzm implements Runnable {
    private final /* synthetic */ zzp zza;

    public /* synthetic */ zzm(zzp r1) {
        this.zza = r1;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        this.zza.zzc(2, "Service disconnected");
    }
}
