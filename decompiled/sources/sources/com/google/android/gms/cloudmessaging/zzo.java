package com.google.android.gms.cloudmessaging;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.SparseArray;

/* loaded from: classes4.dex */
final /* synthetic */ class zzo implements Handler.Callback {
    private final /* synthetic */ zzp zza;

    public /* synthetic */ zzo(zzp r1) {
        this.zza = r1;
    }

    @Override // android.os.Handler.Callback
    public final /* synthetic */ boolean handleMessage(Message r5) {
        int r2 = r5.arg1;
        if (Log.isLoggable("MessengerIpcClient", 3) == false) goto L5;
        StringBuilder r3 = new StringBuilder(String.valueOf(r2).length() + 30);
        r3.append("Received response to request: ");
        r3.append(r2);
        Log.d("MessengerIpcClient", r3.toString());
    L5:
        zzp r4 = this.zza;
        monitor-enter(r4);
        SparseArray r0 = r4.zze;     // Catch: Throwable -> L12
        zzs r32 = (zzs) r0.get(r2);     // Catch: Throwable -> L12
        if (r32 != null) goto L14;
        StringBuilder r33 = new StringBuilder(String.valueOf(r2).length() + 39);     // Catch: Throwable -> L12
        r33.append("Received response for unknown request: ");     // Catch: Throwable -> L12
        r33.append(r2);     // Catch: Throwable -> L12
        Log.w("MessengerIpcClient", r33.toString());     // Catch: Throwable -> L12
        monitor-exit(r4);     // Catch: Throwable -> L12
        return true;
    L14:
        r0.remove(r2);     // Catch: Throwable -> L12
        r4.zze();     // Catch: Throwable -> L12
        monitor-exit(r4);     // Catch: Throwable -> L12
        Bundle r42 = r5.getData();
        if (r42.getBoolean("unsupported", false) == false) goto L19;
        r32.zzd(new zzt(4, "Not supported by GmsCore", null));
        return true;
    L19:
        r32.zzb(r42);
        return true;
    L12:
        th = move-exception;
        throw th;
    }
}
