package com.google.android.gms.internal.cast;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.common.api.internal.IStatusCallback;

/* loaded from: classes4.dex */
public final class zzai extends zza implements IInterface {
    public zzai(IBinder r2) {
        super(r2, "com.google.android.gms.cast.framework.devicesuggestions.internal.IDeviceSuggestionsService");
    }

    public final void zze(IStatusCallback r2, zzah r3) {
        Parcel r0 = zza();
        zzc.zze(r0, r2);
        zzc.zze(r0, r3);
        zzc(1, r0);
    }

    public final void zzf(IStatusCallback r2, zzah r3) {
        Parcel r0 = zza();
        zzc.zze(r0, r2);
        zzc.zze(r0, r3);
        zzc(2, r0);
    }

    public final void zzg(IStatusCallback r2) {
        Parcel r0 = zza();
        zzc.zze(r0, r2);
        zzc(3, r0);
    }

    public final void zzh(IStatusCallback r2) {
        Parcel r0 = zza();
        zzc.zze(r0, r2);
        zzc(4, r0);
    }
}
