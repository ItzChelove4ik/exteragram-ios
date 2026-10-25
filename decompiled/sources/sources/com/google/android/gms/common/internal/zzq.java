package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.stats.ConnectionTracker;
import java.util.HashMap;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
final class zzq extends GmsClientSupervisor {
    private final HashMap zzb;
    private final Context zzc;
    private volatile Handler zzd;
    private final zzp zze;
    private final ConnectionTracker zzf;
    private final long zzg;
    private final long zzh;
    private volatile Executor zzi;

    public zzq(Context r3, Looper r4, Executor r5) {
        this.zzb = new HashMap();
        zzp r0 = new zzp(this, null);
        this.zze = r0;
        this.zzc = r3.getApplicationContext();
        this.zzd = new com.google.android.gms.internal.common.zzg(r4, r0);
        this.zzf = ConnectionTracker.getInstance();
        this.zzg = 5000;
        this.zzh = 300000;
        this.zzi = r5;
    }

    @Override // com.google.android.gms.common.internal.GmsClientSupervisor
    public final ConnectionResult zza(zzn r5, ServiceConnection r6, String r7, Executor r8) {
        Preconditions.checkNotNull(r6, "ServiceConnection must not be null");
        HashMap r0 = this.zzb;
        monitor-enter(r0);
        zzo r2 = (zzo) r0.get(r5);     // Catch: Throwable -> L7
        if (r8 != null) goto L9;
        r8 = this.zzi;     // Catch: Throwable -> L7
    L9:
        if (r2 != null) goto L11;
        r2 = new zzo(this, r5);     // Catch: Throwable -> L7
        r2.zzb(r6, r6, r7);     // Catch: Throwable -> L7
        ConnectionResult r4 = r2.zzj(r7, r8);     // Catch: Throwable -> L7
        r0.put(r5, r2);     // Catch: Throwable -> L7
    L21:
        if (r2.zzd() == false) goto L25;
        ConnectionResult r42 = ConnectionResult.RESULT_SUCCESS;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r42;
    L25:
        if (r4 != null) goto L27;
        r4 = new ConnectionResult(-1);     // Catch: Throwable -> L7
    L27:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r4;
    L11:
        this.zzd.removeMessages(0, r5);     // Catch: Throwable -> L7
        if (r2.zzf(r6) == true) goto L29;
        r2.zzb(r6, r6, r7);     // Catch: Throwable -> L7
        int r43 = r2.zze();     // Catch: Throwable -> L7
        if (r43 != 1) goto L16;
        r6.onServiceConnected(r2.zzi(), r2.zzh());     // Catch: Throwable -> L7
    L17:
        r4 = null;
        goto L21
    L16:
        if (r43 != 2) goto L17;
        r4 = r2.zzj(r7, r8);     // Catch: Throwable -> L7
        goto L21
    L29:
        String r52 = r5.toString();     // Catch: Throwable -> L7
        StringBuilder r72 = new StringBuilder(r52.length() + 81);     // Catch: Throwable -> L7
        r72.append("Trying to bind a GmsServiceConnection that was already connected before.  config=");     // Catch: Throwable -> L7
        r72.append(r52);     // Catch: Throwable -> L7
        throw new IllegalStateException(r72.toString());     // Catch: Throwable -> L7
    L7:
        th = move-exception;
        throw th;
    }

    @Override // com.google.android.gms.common.internal.GmsClientSupervisor
    public final void zzc(zzn r5, ServiceConnection r6, String r7) {
        Preconditions.checkNotNull(r6, "ServiceConnection must not be null");
        HashMap r0 = this.zzb;
        monitor-enter(r0);
        zzo r3 = (zzo) r0.get(r5);     // Catch: Throwable -> L11
        if (r3 != null) goto L7;
        String r52 = r5.toString();     // Catch: Throwable -> L11
        StringBuilder r72 = new StringBuilder(r52.length() + 50);     // Catch: Throwable -> L11
        r72.append("Nonexistent connection status for service config: ");     // Catch: Throwable -> L11
        r72.append(r52);     // Catch: Throwable -> L11
        throw new IllegalStateException(r72.toString());     // Catch: Throwable -> L11
    L7:
        if (r3.zzf(r6) == false) goto L15;
        r3.zzc(r6, r7);     // Catch: Throwable -> L11
        if (r3.zzg() == false) goto L13;
        Message r53 = this.zzd.obtainMessage(0, r5);     // Catch: Throwable -> L11
        this.zzd.sendMessageDelayed(r53, this.zzg);     // Catch: Throwable -> L11
    L13:
        monitor-exit(r0);     // Catch: Throwable -> L11
        return;
    L15:
        String r54 = r5.toString();     // Catch: Throwable -> L11
        StringBuilder r73 = new StringBuilder(r54.length() + 76);     // Catch: Throwable -> L11
        r73.append("Trying to unbind a GmsServiceConnection  that was not bound before.  config=");     // Catch: Throwable -> L11
        r73.append(r54);     // Catch: Throwable -> L11
        throw new IllegalStateException(r73.toString());     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        throw th;
    }

    public final /* synthetic */ HashMap zzf() {
        return this.zzb;
    }

    public final /* synthetic */ Context zzg() {
        return this.zzc;
    }

    public final /* synthetic */ Handler zzh() {
        return this.zzd;
    }

    public final /* synthetic */ ConnectionTracker zzi() {
        return this.zzf;
    }

    public final /* synthetic */ long zzj() {
        return this.zzh;
    }
}
