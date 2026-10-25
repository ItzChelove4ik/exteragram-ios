package com.google.android.gms.internal.fido;

import android.app.PendingIntent;
import android.os.Parcel;
import com.google.android.gms.common.api.Status;

/* loaded from: classes4.dex */
public abstract class zzq extends zzb implements zzr {
    public zzq() {
        super("com.google.android.gms.fido.fido2.internal.regular.IFido2AppCallbacks");
    }

    @Override // com.google.android.gms.internal.fido.zzb
    public final boolean zza(int r1, Parcel r2, Parcel r3, int r4) {
        if (r1 != 1) goto L6;
        Status r12 = (Status) zzc.zza(r2, Status.CREATOR);
        PendingIntent r42 = (PendingIntent) zzc.zza(r2, PendingIntent.CREATOR);
        zzc.zzc(r2);
        zzb(r12, r42);
        return true;
    L6:
        return false;
    }
}
