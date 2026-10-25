package com.google.android.gms.internal.measurement;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
final class zzkv extends BroadcastReceiver {
    final /* synthetic */ AtomicBoolean zza;
    final /* synthetic */ Context zzb;
    final /* synthetic */ SettableFuture zzc;
    final /* synthetic */ AsyncCallable zzd;
    final /* synthetic */ Executor zze;

    public zzkv(AtomicBoolean r1, Context r2, SettableFuture r3, AsyncCallable r4, Executor r5) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context r2, Intent r3) {
        if (this.zza.compareAndSet(false, true) == false) goto L6;
        zzky.zzg(this.zzb, this);
        this.zzc.setFuture(Futures.submitAsync(this.zzd, this.zze));
        return;
    }
}
