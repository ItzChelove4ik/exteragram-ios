package com.google.android.gms.internal.recaptchabase;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.recaptchabase.ExecuteRequest;
import com.google.android.gms.recaptchabase.InitRequest;

/* loaded from: classes5.dex */
public final class zzf extends zza implements IInterface {
    public zzf(IBinder r2) {
        super(r2, "com.google.android.gms.recaptchabase.internal.IRecaptchaBaseService");
    }

    public final void zzc(zze r2, ExecuteRequest r3) {
        Parcel r0 = zza();
        zzc.zzd(r0, r2);
        zzc.zzc(r0, r3);
        zzb(2, r0);
    }

    public final void zzd(zze r2, InitRequest r3) {
        Parcel r0 = zza();
        zzc.zzd(r0, r2);
        zzc.zzc(r0, r3);
        zzb(1, r0);
    }
}
