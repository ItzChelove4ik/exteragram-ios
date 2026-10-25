package com.google.android.gms.internal.cloudmessaging;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.cloudmessaging.RegisterRequest;
import com.google.android.gms.common.api.ApiMetadata;

/* loaded from: classes.dex */
public final class zze extends zza implements IInterface {
    public zze(IBinder r2) {
        super(r2, "com.google.android.gms.cloudmessaging.internal.ICloudMessagingService");
    }

    public final void zzc(zzg r2, RegisterRequest r3, ApiMetadata r4) {
        Parcel r0 = zza();
        zzc.zzc(r0, r2);
        zzc.zzb(r0, r3);
        zzc.zzb(r0, r4);
        zzb(1, r0);
    }
}
