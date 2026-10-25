package com.google.android.gms.internal.clearcut;

import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes4.dex */
public final class zzo extends zza implements zzn {
    public zzo(IBinder r2) {
        super(r2, "com.google.android.gms.clearcut.internal.IClearcutLoggerService");
    }

    @Override // com.google.android.gms.internal.clearcut.zzn
    public final void zza(zzl r2, com.google.android.gms.clearcut.zze r3) {
        Parcel r0 = obtainAndWriteInterfaceToken();
        zzc.zza(r0, r2);
        zzc.zza(r0, r3);
        transactOneway(1, r0);
    }
}
