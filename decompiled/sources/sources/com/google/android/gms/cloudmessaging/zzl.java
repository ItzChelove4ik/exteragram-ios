package com.google.android.gms.cloudmessaging;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
final /* synthetic */ class zzl implements Runnable {
    private final /* synthetic */ zzp zza;

    public /* synthetic */ zzl(zzp r1) {
        this.zza = r1;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
    L2:
        zzp r0 = this.zza;
        monitor-enter(r0);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (r0.zza != 2) goto L6;
        Queue r1 = r0.zzd;     // Catch: Throwable -> L8
        if (r1.isEmpty() == true) goto L12;
        zzs r12 = (zzs) r1.poll();     // Catch: Throwable -> L8
        SparseArray r3 = r0.zze;     // Catch: Throwable -> L8
        int r4 = r12.zza;     // Catch: Throwable -> L8
        r3.put(r4, r12);     // Catch: Throwable -> L8
        r0.zzf.zze().schedule(new zzn(r0, r12), 30, TimeUnit.SECONDS);     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        if (Log.isLoggable("MessengerIpcClient", 3) == false) goto L20;
        Log.d("MessengerIpcClient", "Sending ".concat(String.valueOf(r12)));
    L20:
        zzv r32 = r0.zzf;
        Messenger r5 = r0.zzb;
        int r6 = r12.zzc;
        Message r7 = Message.obtain();
        r7.what = r6;
        r7.arg1 = r4;
        r7.replyTo = r5;
        Bundle r42 = new Bundle();
        r42.putBoolean("oneWay", r12.zza());
        r42.putString("pkg", r32.zzd().getPackageName());
        r42.putBundle("data", r12.zzd);
        r7.setData(r42);
        r0.zzc.zza(r7);     // Catch: RemoteException -> L23
    L23:
        e = move-exception;
        r0.zzc(2, e.getMessage());
        goto L2
    L12:
        r0.zze();     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L6:
        monitor-exit(r0);     // Catch: Throwable -> L8
    }
}
