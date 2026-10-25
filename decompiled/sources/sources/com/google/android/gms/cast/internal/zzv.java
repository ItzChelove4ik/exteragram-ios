package com.google.android.gms.cast.internal;

import com.google.android.gms.cast.Cast;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzv implements Runnable {
    final /* synthetic */ zzx zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ String zzc;

    public zzv(zzw r1, zzx r2, String r3, String r4) {
        this.zza = r2;
        this.zzb = r3;
        this.zzc = r4;
        Objects.requireNonNull(r1);
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzx r0 = this.zza;
        Map r1 = r0.zzR();
        monitor-enter(r1);
        Cast.MessageReceivedCallback r02 = (Cast.MessageReceivedCallback) r0.zzR().get(this.zzb);     // Catch: Throwable -> L11
        monitor-exit(r1);     // Catch: Throwable -> L11
        if (r02 == null) goto L9;
        zzx r12 = this.zza;
        String r2 = this.zzb;
        String r3 = this.zzc;
        r02.onMessageReceived(r12.zzP(), r2, r3);
        return;
    L9:
        Object[] r32 = {this.zzb};
        zzx.zzN().d("Discarded message for unknown namespace '%s'", r32);
        return;
    L11:
        th = move-exception;
        throw th;
    }
}
