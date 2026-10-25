package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes4.dex */
public final class zzcw extends zzbl implements zzcy {
    public zzcw(IBinder r2) {
        super(r2, "com.google.android.gms.measurement.api.internal.IEventHandlerProxy");
    }

    @Override // com.google.android.gms.internal.measurement.zzcy
    public final void zze(String r2, String r3, Bundle r4, long r5) {
        Parcel r0 = zza();
        r0.writeString(r2);
        r0.writeString(r3);
        zzbn.zzc(r0, r4);
        r0.writeLong(r5);
        zzc(1, r0);
    }

    @Override // com.google.android.gms.internal.measurement.zzcy
    public final int zzf() {
        Parcel r2 = zzP(2, zza());
        int r0 = r2.readInt();
        r2.recycle();
        return r0;
    }
}
