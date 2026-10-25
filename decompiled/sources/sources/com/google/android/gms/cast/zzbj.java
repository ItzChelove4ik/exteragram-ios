package com.google.android.gms.cast;

import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.internal.Logger;
import java.util.Map;

/* loaded from: classes4.dex */
final /* synthetic */ class zzbj implements Runnable {
    private final /* synthetic */ zzbl zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ String zzc;

    public /* synthetic */ zzbj(zzbl r1, String r2, String r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        zzbl r0 = this.zza;
        Map r1 = r0.zza.zze;
        String r2 = this.zzb;
        monitor-enter(r1);
        Cast.MessageReceivedCallback r3 = (Cast.MessageReceivedCallback) r1.get(r2);     // Catch: Throwable -> L11
        monitor-exit(r1);     // Catch: Throwable -> L11
        if (r3 == null) goto L9;
        String r4 = this.zzc;
        r3.onMessageReceived(r0.zza.zzR(), r2, r4);
        return;
    L9:
        Logger r02 = zzbm.zzM();
        r02.d("Discarded message for unknown namespace '%s'", new Object[]{r2});
        return;
    L11:
        th = move-exception;
        throw th;
    }
}
