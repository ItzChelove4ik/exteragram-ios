package com.google.android.gms.cast.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.common.api.ApiMetadata;
import com.google.android.gms.common.api.internal.IStatusCallback;
import java.util.List;

/* loaded from: classes4.dex */
public final class zzak extends com.google.android.gms.internal.cast.zza implements IInterface {
    public zzak(IBinder r2) {
        super(r2, "com.google.android.gms.cast.internal.ICastService");
    }

    public final void zze(IStatusCallback r1, String[] r2, String r3, List r4, ApiMetadata r5) {
        Parcel r42 = zza();
        com.google.android.gms.internal.cast.zzc.zze(r42, r1);
        r42.writeStringArray(r2);
        r42.writeString(r3);
        r42.writeTypedList(null);
        com.google.android.gms.internal.cast.zzc.zzc(r42, r5);
        zzd(2, r42);
    }

    public final void zzf(zzag r2, String[] r3, ApiMetadata r4) {
        Parcel r0 = zza();
        com.google.android.gms.internal.cast.zzc.zze(r0, r2);
        r0.writeStringArray(r3);
        com.google.android.gms.internal.cast.zzc.zzc(r0, r4);
        zzd(5, r0);
    }

    public final void zzg(zzag r2, String[] r3, ApiMetadata r4) {
        Parcel r0 = zza();
        com.google.android.gms.internal.cast.zzc.zze(r0, r2);
        r0.writeStringArray(r3);
        com.google.android.gms.internal.cast.zzc.zzc(r0, r4);
        zzd(6, r0);
    }

    public final void zzh(zzag r2, String[] r3, ApiMetadata r4) {
        Parcel r0 = zza();
        com.google.android.gms.internal.cast.zzc.zze(r0, r2);
        r0.writeStringArray(r3);
        com.google.android.gms.internal.cast.zzc.zzc(r0, r4);
        zzd(7, r0);
    }
}
