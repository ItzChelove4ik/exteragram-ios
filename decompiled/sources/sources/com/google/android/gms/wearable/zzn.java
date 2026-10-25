package com.google.android.gms.wearable;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.internal.wearable.zzi;

/* loaded from: classes5.dex */
final class zzn extends zzi {
    final /* synthetic */ WearableListenerService zza;
    private boolean zzb;
    private final zzm zzc;

    public zzn(WearableListenerService r2, Looper r3) {
        this.zza = r2;
        super(r3);
        this.zzc = new zzm(r2, null);
    }

    private final synchronized void zzc() {
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L4:
        if (this.zzb == false) goto L8;
        monitor-exit(this);
        return;
    L8:
        if (Log.isLoggable("WearableLS", 2) == false) goto L12;
        Log.v("WearableLS", "bindService: ".concat(String.valueOf(WearableListenerService.zza(this.zza))));     // Catch: Throwable -> L10
    L12:
        WearableListenerService r0 = this.zza;     // Catch: Throwable -> L10
        r0.bindService(WearableListenerService.zzb(r0), this.zzc, 1);     // Catch: Throwable -> L10
        this.zzb = true;     // Catch: Throwable -> L10
        monitor-exit(this);
    }

    private final synchronized void zzd(String r4) {
        monitor-enter(this);
    L12:
        th = move-exception;
        throw th;
    L5:
        if (this.zzb == true) goto L9;
        monitor-exit(this);
        return;
    L9:
        if (Log.isLoggable("WearableLS", 2) == false) goto L23;
        Log.v("WearableLS", "unbindService: " + r4 + ", " + String.valueOf(WearableListenerService.zza(this.zza)));     // Catch: Throwable -> L12
    L23:
        this.zza.unbindService(this.zzc);     // Catch: Throwable -> L12 RuntimeException -> L16
    L18:
        this.zzb = false;     // Catch: Throwable -> L12
        monitor-exit(this);
        return;
    L16:
        e = move-exception;
        Log.e("WearableLS", "Exception when unbinding from local service", e);     // Catch: Throwable -> L12
        goto L18
    }

    @Override // com.google.android.gms.internal.wearable.zzi
    public final void zza(Message r3) {
        zzc();
        super.zza(r3);     // Catch: Throwable -> L8
        if (hasMessages(0) == true) goto L16;
        zzd("dispatch");
        return;
    L16:
        return;
    L8:
        th = move-exception;
        if (hasMessages(0) == true) goto L13;
        zzd("dispatch");
    L13:
        throw th;
    }

    public final void zzb() {
        getLooper().quit();
        zzd("quit");
    }
}
