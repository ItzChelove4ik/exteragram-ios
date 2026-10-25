package com.google.android.gms.internal.cast;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.common.api.ApiMetadata;

/* loaded from: classes4.dex */
public final class zzez extends zza implements IInterface {
    public zzez(IBinder r2) {
        super(r2, "com.google.android.gms.cast.remote_display.ICastRemoteDisplayService");
    }

    public final void zze(ApiMetadata r2) {
        Parcel r0 = zza();
        zzc.zzc(r0, r2);
        zzd(3, r0);
    }

    public final void zzf(zzey r2, zzfb r3, String r4, String r5, Bundle r6, ApiMetadata r7) {
        Parcel r0 = zza();
        zzc.zze(r0, r2);
        zzc.zze(r0, r3);
        r0.writeString(r4);
        r0.writeString(r5);
        zzc.zzc(r0, r6);
        zzc.zzc(r0, r7);
        zzd(7, r0);
    }

    public final void zzg(zzey r2, PendingIntent r3, String r4, String r5, Bundle r6, ApiMetadata r7) {
        Parcel r0 = zza();
        zzc.zze(r0, r2);
        zzc.zzc(r0, r3);
        r0.writeString(r4);
        r0.writeString(r5);
        zzc.zzc(r0, r6);
        zzc.zzc(r0, r7);
        zzd(8, r0);
    }

    public final void zzh(zzey r2, int r3, ApiMetadata r4) {
        Parcel r0 = zza();
        zzc.zze(r0, r2);
        r0.writeInt(r3);
        zzc.zzc(r0, r4);
        zzd(5, r0);
    }

    public final void zzi(zzey r2, ApiMetadata r3) {
        Parcel r0 = zza();
        zzc.zze(r0, r2);
        zzc.zzc(r0, r3);
        zzd(6, r0);
    }
}
