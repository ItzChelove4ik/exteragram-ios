package com.google.android.gms.internal.cast;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes4.dex */
public final class zzgh extends zza implements IInterface {
    public zzgh(IBinder r2) {
        super(r2, "com.google.android.gms.usagereporting.internal.IUsageReportingService");
    }

    public final void zze(zzgf r2) {
        Parcel r0 = zza();
        zzc.zze(r0, r2);
        zzc(2, r0);
    }
}
