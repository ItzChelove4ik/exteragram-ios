package com.google.android.gms.internal.wallet;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.PaymentDataRequest;

/* loaded from: classes5.dex */
public final class zzo extends zza implements IInterface {
    public zzo(IBinder r2) {
        super(r2, "com.google.android.gms.wallet.internal.IOwService");
    }

    public final void zzf(IsReadyToPayRequest r2, Bundle r3, zzq r4) {
        Parcel r0 = zza();
        zzc.zzc(r0, r2);
        zzc.zzc(r0, r3);
        zzc.zzd(r0, r4);
        zzc(14, r0);
    }

    public final void zzg(PaymentDataRequest r2, Bundle r3, zzq r4) {
        Parcel r0 = zza();
        zzc.zzc(r0, r2);
        zzc.zzc(r0, r3);
        zzc.zzd(r0, r4);
        zzc(19, r0);
    }
}
