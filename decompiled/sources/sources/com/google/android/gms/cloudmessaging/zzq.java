package com.google.android.gms.cloudmessaging;

import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import java.util.Objects;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
final class zzq {
    private final Messenger zza;
    private final zzd zzb;

    public zzq(IBinder r4) {
        String r0 = r4.getInterfaceDescriptor();
        if (Objects.equals(r0, "android.os.IMessenger") == false) goto L7;
        this.zza = new Messenger(r4);
        this.zzb = null;
        return;
    L7:
        if (Objects.equals(r0, "com.google.android.gms.iid.IMessengerCompat") == false) goto L10;
        this.zzb = new zzd(r4);
        this.zza = null;
        return;
    L10:
        Log.w("MessengerIpcClient", "Invalid interface descriptor: ".concat(String.valueOf(r0)));
        throw new RemoteException();
    }

    public final void zza(Message r2) {
        Messenger r0 = this.zza;
        if (r0 == null) goto L6;
        r0.send(r2);
        return;
    L6:
        zzd r1 = this.zzb;
        if (r1 == null) goto L10;
        r1.zza.send(r2);
        return;
    L10:
        Segment$$ExternalSyntheticBUOutline1.m("Both messengers are null");
    }
}
