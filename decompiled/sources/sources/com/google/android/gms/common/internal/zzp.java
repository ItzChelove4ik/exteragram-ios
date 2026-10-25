package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.util.HashMap;

/* loaded from: classes4.dex */
final class zzp implements Handler.Callback {
    final /* synthetic */ zzq zza;

    public /* synthetic */ zzp(zzq r1, byte[] r2) {
        java.util.Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message r8) {
        int r0 = r8.what;
        if (r0 == 0) goto L25;
        if (r0 == 1) goto L7;
        return false;
    L7:
        zzq r7 = this.zza;
        HashMap r02 = r7.zzf();
        monitor-enter(r02);
        zzn r82 = (zzn) r8.obj;     // Catch: Throwable -> L16
        zzo r72 = (zzo) r7.zzf().get(r82);     // Catch: Throwable -> L16
        if (r72 != null) goto L12;
    L21:
        monitor-exit(r02);     // Catch: Throwable -> L16
        return true;
    L12:
        if (r72.zze() != 3) goto L21;
        String r4 = String.valueOf(r82);     // Catch: Throwable -> L16
        StringBuilder r6 = new StringBuilder(r4.length() + 47);     // Catch: Throwable -> L16
        r6.append("Timeout waiting for ServiceConnection callback ");     // Catch: Throwable -> L16
        r6.append(r4);     // Catch: Throwable -> L16
        Log.e("GmsClientSupervisor", r6.toString(), new Exception());     // Catch: Throwable -> L16
        ComponentName r1 = r72.zzi();     // Catch: Throwable -> L16
        if (r1 != null) goto L18;
        r1 = r82.zzc();     // Catch: Throwable -> L16
    L18:
        if (r1 != null) goto L20;
        String r83 = r82.zzb();     // Catch: Throwable -> L16
        Preconditions.checkNotNull(r83);     // Catch: Throwable -> L16
        r1 = new ComponentName(r83, "unknown");     // Catch: Throwable -> L16
    L20:
        r72.onServiceDisconnected(r1);     // Catch: Throwable -> L16
    L16:
        th = move-exception;
        throw th;
    L25:
        zzq r73 = this.zza;
        HashMap r03 = r73.zzf();
        monitor-enter(r03);
        zzn r84 = (zzn) r8.obj;     // Catch: Throwable -> L34
        zzo r12 = (zzo) r73.zzf().get(r84);     // Catch: Throwable -> L34
        if (r12 != null) goto L30;
    L37:
        monitor-exit(r03);     // Catch: Throwable -> L34
        return true;
    L30:
        if (r12.zzg() == false) goto L37;
        if (r12.zzd() == false) goto L36;
        r12.zza("GmsClientSupervisor");     // Catch: Throwable -> L34
    L36:
        r73.zzf().remove(r84);     // Catch: Throwable -> L34
    L34:
        th = move-exception;
        throw th;
    }
}
