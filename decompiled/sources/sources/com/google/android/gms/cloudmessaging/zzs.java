package com.google.android.gms.cloudmessaging;

import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes.dex */
abstract class zzs {
    final int zza;
    final TaskCompletionSource zzb;
    final int zzc;
    final Bundle zzd;

    public zzs(int r2, int r3, Bundle r4) {
        this.zzb = new TaskCompletionSource();
        this.zza = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    public final String toString() {
        int r0 = this.zzc;
        int r1 = String.valueOf(r0).length();
        int r2 = this.zza;
        int r3 = String.valueOf(r2).length();
        boolean r5 = zza();
        int r12 = ((r1 + 19) + r3) + 8;
        StringBuilder r32 = new StringBuilder((r12 + String.valueOf(r5).length()) + 1);
        r32.append("Request { what=");
        r32.append(r0);
        r32.append(" id=");
        r32.append(r2);
        r32.append(" oneWay=");
        r32.append(r5);
        r32.append("}");
        return r32.toString();
    }

    public abstract boolean zza();

    public abstract void zzb(Bundle r1);

    public final void zzc(Object r7) {
        if (Log.isLoggable("MessengerIpcClient", 3) == false) goto L5;
        String r0 = toString();
        String r2 = String.valueOf(r7);
        StringBuilder r5 = new StringBuilder((r0.length() + 16) + r2.length());
        r5.append("Finishing ");
        r5.append(r0);
        r5.append(" with ");
        r5.append(r2);
        Log.d("MessengerIpcClient", r5.toString());
    L5:
        this.zzb.setResult(r7);
    }

    public final void zzd(zzt r7) {
        if (Log.isLoggable("MessengerIpcClient", 3) == false) goto L5;
        String r0 = toString();
        String r2 = r7.toString();
        StringBuilder r5 = new StringBuilder((r0.length() + 14) + r2.length());
        r5.append("Failing ");
        r5.append(r0);
        r5.append(" with ");
        r5.append(r2);
        Log.d("MessengerIpcClient", r5.toString());
    L5:
        this.zzb.setException(r7);
    }
}
