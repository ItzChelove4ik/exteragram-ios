package com.google.android.gms.cloudmessaging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* loaded from: classes.dex */
final /* synthetic */ class zzg implements Runnable {
    private final /* synthetic */ CloudMessagingReceiver zza;
    private final /* synthetic */ Intent zzb;
    private final /* synthetic */ Context zzc;
    private final /* synthetic */ boolean zzd;
    private final /* synthetic */ BroadcastReceiver.PendingResult zze;

    public /* synthetic */ zzg(CloudMessagingReceiver r1, Intent r2, Context r3, boolean r4, BroadcastReceiver.PendingResult r5) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        this.zza.zza(this.zzb, this.zzc, this.zzd, this.zze);
    }
}
