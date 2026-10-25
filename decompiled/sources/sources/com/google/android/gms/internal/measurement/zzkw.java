package com.google.android.gms.internal.measurement;

import android.content.BroadcastReceiver;
import android.content.Context;
import com.google.common.util.concurrent.SettableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
final /* synthetic */ class zzkw implements Runnable {
    private final /* synthetic */ SettableFuture zza;
    private final /* synthetic */ AtomicBoolean zzb;
    private final /* synthetic */ Context zzc;
    private final /* synthetic */ BroadcastReceiver zzd;

    public /* synthetic */ zzkw(SettableFuture r1, AtomicBoolean r2, Context r3, BroadcastReceiver r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        zzky.zzf(this.zza, this.zzb, this.zzc, this.zzd);
    }
}
