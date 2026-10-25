package com.google.android.gms.internal.cast;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes4.dex */
public final class zzbf extends zza implements zzbg {
    public zzbf(IBinder r2) {
        super(r2, "com.google.android.gms.cast.framework.internal.IMediaRouterCallback");
    }

    @Override // com.google.android.gms.internal.cast.zzbg
    public final int zze() {
        Parcel r2 = zzb(7, zza());
        int r0 = r2.readInt();
        r2.recycle();
        return r0;
    }

    @Override // com.google.android.gms.internal.cast.zzbg
    public final void zzf(String r2, Bundle r3) {
        Parcel r0 = zza();
        r0.writeString(r2);
        zzc.zzc(r0, r3);
        zzc(1, r0);
    }

    @Override // com.google.android.gms.internal.cast.zzbg
    public final void zzg(String r2, Bundle r3) {
        Parcel r0 = zza();
        r0.writeString(r2);
        zzc.zzc(r0, r3);
        zzc(2, r0);
    }

    @Override // com.google.android.gms.internal.cast.zzbg
    public final void zzh(String r2, Bundle r3) {
        Parcel r0 = zza();
        r0.writeString(r2);
        zzc.zzc(r0, r3);
        zzc(3, r0);
    }

    @Override // com.google.android.gms.internal.cast.zzbg
    public final void zzi(String r2, Bundle r3) {
        Parcel r0 = zza();
        r0.writeString(r2);
        zzc.zzc(r0, r3);
        zzc(4, r0);
    }

    @Override // com.google.android.gms.internal.cast.zzbg
    public final void zzj(String r2, Bundle r3, int r4) {
        Parcel r0 = zza();
        r0.writeString(r2);
        zzc.zzc(r0, r3);
        r0.writeInt(r4);
        zzc(6, r0);
    }

    @Override // com.google.android.gms.internal.cast.zzbg
    public final void zzk(String r2, String r3, Bundle r4) {
        Parcel r0 = zza();
        r0.writeString(r2);
        r0.writeString(r3);
        zzc.zzc(r0, r4);
        zzc(8, r0);
    }

    @Override // com.google.android.gms.internal.cast.zzbg
    public final void zzl(String r2, String r3, Bundle r4) {
        Parcel r0 = zza();
        r0.writeString(r2);
        r0.writeString(r3);
        zzc.zzc(r0, r4);
        zzc(9, r0);
    }

    @Override // com.google.android.gms.internal.cast.zzbg
    public final void zzm(String r2, String r3, Bundle r4, int r5) {
        Parcel r0 = zza();
        r0.writeString(r2);
        r0.writeString(r3);
        zzc.zzc(r0, r4);
        r0.writeInt(r5);
        zzc(10, r0);
    }
}
