package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;

/* loaded from: classes4.dex */
public final class zzf extends zza {
    public final IBinder zze;
    final /* synthetic */ BaseGmsClient zzf;

    public zzf(BaseGmsClient r1, int r2, IBinder r3, Bundle r4) {
        java.util.Objects.requireNonNull(r1);
        this.zzf = r1;
        super(r1, r2, r4);
        this.zze = r3;
    }

    @Override // com.google.android.gms.common.internal.zza
    public final boolean zza() {
        IBinder r2 = this.zze;     // Catch: RemoteException -> L20
        Preconditions.checkNotNull(r2);     // Catch: RemoteException -> L20
        String r22 = r2.getInterfaceDescriptor();     // Catch: RemoteException -> L20
        BaseGmsClient r3 = this.zzf;
        if (r3.getServiceDescriptor().equals(r22) == true) goto L8;
        String r6 = r3.getServiceDescriptor();
        int r32 = String.valueOf(r6).length();
        StringBuilder r5 = new StringBuilder((r32 + 34) + String.valueOf(r22).length());
        r5.append("service descriptor mismatch: ");
        r5.append(r6);
        r5.append(" vs. ");
        r5.append(r22);
        Log.w("GmsClient", r5.toString());
        return false;
    L8:
        IInterface r62 = r3.createServiceInterface(this.zze);
        if (r62 != null) goto L11;
    L19:
        return false;
    L11:
        if (r3.zze(2, 4, r62) == false) goto L13;
    L14:
        r3.zzn(null);
        BaseGmsClient.BaseConnectionCallbacks r63 = r3.zzk();
        Bundle r0 = r3.getConnectionHint();
        if (r63 == null) goto L24;
        r3.zzk().onConnected(r0);
        return true;
    L24:
        return true;
    L13:
        if (r3.zze(3, 4, r62) == false) goto L19;
    L20:
        Log.w("GmsClient", "service probably died");
        return false;
    }

    @Override // com.google.android.gms.common.internal.zza
    public final void zzb(ConnectionResult r2) {
        BaseGmsClient r1 = this.zzf;
        if (r1.zzl() == null) goto L5;
        r1.zzl().onConnectionFailed(r2);
    L5:
        r1.onConnectionFailed(r2);
    }
}
