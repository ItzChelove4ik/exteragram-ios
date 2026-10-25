package com.google.android.gms.cast.framework;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.ConnectionResult;

/* loaded from: classes4.dex */
public final class zzaj extends com.google.android.gms.internal.cast.zza implements zzal {
    public zzaj(IBinder r2) {
        super(r2, "com.google.android.gms.cast.framework.ICastSession");
    }

    @Override // com.google.android.gms.cast.framework.zzal
    public final void zze(Bundle r2) {
        Parcel r22 = zza();
        com.google.android.gms.internal.cast.zzc.zzc(r22, null);
        zzc(1, r22);
    }

    @Override // com.google.android.gms.cast.framework.zzal
    public final void zzf(int r2) {
        Parcel r0 = zza();
        r0.writeInt(r2);
        zzc(2, r0);
    }

    @Override // com.google.android.gms.cast.framework.zzal
    public final void zzg(ConnectionResult r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.cast.zzc.zzc(r0, r2);
        zzc(3, r0);
    }

    @Override // com.google.android.gms.cast.framework.zzal
    public final void zzh(ApplicationMetadata r2, String r3, String r4, boolean r5) {
        Parcel r0 = zza();
        com.google.android.gms.internal.cast.zzc.zzc(r0, r2);
        r0.writeString(r3);
        r0.writeString(r4);
        r0.writeInt(r5 ? 1 : 0);
        zzc(4, r0);
    }

    @Override // com.google.android.gms.cast.framework.zzal
    public final void zzi(int r2) {
        Parcel r0 = zza();
        r0.writeInt(r2);
        zzc(5, r0);
    }

    @Override // com.google.android.gms.cast.framework.zzal
    public final void zzj(boolean r2, int r3) {
        Parcel r32 = zza();
        int r0 = com.google.android.gms.internal.cast.zzc.$r8$clinit;
        r32.writeInt(r2 ? 1 : 0);
        r32.writeInt(0);
        zzc(6, r32);
    }
}
