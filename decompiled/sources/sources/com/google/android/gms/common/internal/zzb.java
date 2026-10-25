package com.google.android.gms.common.internal;

import android.app.PendingIntent;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;

/* loaded from: classes4.dex */
final class zzb extends com.google.android.gms.internal.common.zzg {
    final /* synthetic */ BaseGmsClient zza;

    public zzb(BaseGmsClient r1, Looper r2) {
        java.util.Objects.requireNonNull(r1);
        this.zza = r1;
        super(r2);
    }

    private static final void zza(Message r0) {
        zzc r02 = (zzc) r0.obj;
        if (r02 == null) goto L6;
        r02.zze();
        return;
    }

    private static final boolean zzb(Message r2) {
        int r22 = r2.what;
        if (r22 == 2) goto L10;
        if (r22 == 1) goto L10;
        if (r22 == 7) goto L10;
        return false;
    L10:
        return true;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message r8) {
        BaseGmsClient r7 = this.zza;
        if (r7.zzd.get() != r8.arg1) goto L5;
        int r0 = r8.what;
        if (r0 == 1) goto L18;
        if (r0 == 7) goto L18;
        if (r0 != 4) goto L16;
        if (r7.enableLocalFallback() == false) goto L18;
    L16:
        if (r8.what == 5) goto L18;
    L19:
        int r02 = r8.what;
        PendingIntent r6 = null;
        if (r02 != 4) goto L34;
        r7.zzn(new ConnectionResult(r8.arg2));
        if (r7.zzg() == false) goto L29;
        if (r7.zzo() == true) goto L29;
        r7.zzd(3, null);
        return;
    L29:
        if (r7.zzm() == null) goto L31;
        ConnectionResult r82 = r7.zzm();
    L32:
        r7.zzc.onReportServiceBinding(r82);
        r7.onConnectionFailed(r82);
        return;
    L31:
        r82 = new ConnectionResult(8);
        goto L32
    L34:
        if (r02 == 5) goto L36;
        if (r02 != 3) goto L48;
        Object r03 = r8.obj;
        if ((r03 instanceof PendingIntent) == false) goto L45;
        r6 = (PendingIntent) r03;
    L45:
        ConnectionResult r04 = new ConnectionResult(r8.arg2, r6);
        r7.zzc.onReportServiceBinding(r04);
        r7.onConnectionFailed(r04);
        return;
    L48:
        if (r02 != 6) goto L55;
        r7.zzd(5, null);
        if (r7.zzk() == null) goto L52;
        int r05 = r8.arg2;
        r7.zzk().onConnectionSuspended(r05);
    L52:
        r7.onConnectionSuspended(r8.arg2);
        r7.zze(5, 1, null);
        return;
    L55:
        if (r02 != 2) goto L62;
        if (r7.isConnected() == true) goto L62;
        zza(r8);
        return;
    L62:
        if (zzb(r8) == false) goto L65;
        ((zzc) r8.obj).zzd();
        return;
    L65:
        int r72 = r8.what;
        StringBuilder r06 = new StringBuilder(String.valueOf(r72).length() + 34);
        r06.append("Don't know how to handle message: ");
        r06.append(r72);
        Log.wtf("GmsClient", r06.toString(), new Exception());
        return;
    L36:
        if (r7.zzm() == null) goto L38;
        ConnectionResult r83 = r7.zzm();
    L39:
        r7.zzc.onReportServiceBinding(r83);
        r7.onConnectionFailed(r83);
        return;
    L38:
        r83 = new ConnectionResult(8);
    L18:
        if (r7.isConnecting() == true) goto L19;
        zza(r8);
        return;
    L5:
        if (zzb(r8) == false) goto L69;
        zza(r8);
        return;
    }
}
