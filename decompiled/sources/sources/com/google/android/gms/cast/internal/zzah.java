package com.google.android.gms.cast.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.zzbn;
import com.google.android.gms.common.api.ApiMetadata;

/* loaded from: classes4.dex */
public final class zzah extends com.google.android.gms.internal.cast.zza implements IInterface {
    public zzah(IBinder r2) {
        super(r2, "com.google.android.gms.cast.internal.ICastDeviceController");
    }

    public final void zze(ApiMetadata r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.cast.zzc.zzc(r0, r2);
        zzd(1, r0);
    }

    public final void zzf(ApiMetadata r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.cast.zzc.zzc(r0, r2);
        zzd(4, r0);
    }

    public final void zzg(String r2, ApiMetadata r3) {
        Parcel r0 = zza();
        r0.writeString(r2);
        com.google.android.gms.internal.cast.zzc.zzc(r0, r3);
        zzd(5, r0);
    }

    public final void zzh(ApiMetadata r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.cast.zzc.zzc(r0, r2);
        zzd(6, r0);
    }

    public final void zzi(double r2, double r4, boolean r6, ApiMetadata r7) {
        Parcel r0 = zza();
        r0.writeDouble(r2);
        r0.writeDouble(r4);
        int r22 = com.google.android.gms.internal.cast.zzc.$r8$clinit;
        r0.writeInt(r6 ? 1 : 0);
        com.google.android.gms.internal.cast.zzc.zzc(r0, r7);
        zzd(7, r0);
    }

    public final void zzj(boolean r3, double r4, boolean r6, ApiMetadata r7) {
        Parcel r0 = zza();
        int r1 = com.google.android.gms.internal.cast.zzc.$r8$clinit;
        r0.writeInt(r3 ? 1 : 0);
        r0.writeDouble(r4);
        r0.writeInt(r6 ? 1 : 0);
        com.google.android.gms.internal.cast.zzc.zzc(r0, r7);
        zzd(8, r0);
    }

    public final void zzk(String r2, String r3, long r4, ApiMetadata r6) {
        Parcel r0 = zza();
        r0.writeString(r2);
        r0.writeString(r3);
        r0.writeLong(r4);
        com.google.android.gms.internal.cast.zzc.zzc(r0, r6);
        zzd(9, r0);
    }

    public final void zzl(String r2, ApiMetadata r3) {
        Parcel r0 = zza();
        r0.writeString(r2);
        com.google.android.gms.internal.cast.zzc.zzc(r0, r3);
        zzd(11, r0);
    }

    public final void zzm(String r2, ApiMetadata r3) {
        Parcel r0 = zza();
        r0.writeString(r2);
        com.google.android.gms.internal.cast.zzc.zzc(r0, r3);
        zzd(12, r0);
    }

    public final void zzn(String r2, LaunchOptions r3, ApiMetadata r4) {
        Parcel r0 = zza();
        r0.writeString(r2);
        com.google.android.gms.internal.cast.zzc.zzc(r0, r3);
        com.google.android.gms.internal.cast.zzc.zzc(r0, r4);
        zzd(13, r0);
    }

    public final void zzo(String r2, String r3, zzbn r4, ApiMetadata r5) {
        Parcel r0 = zza();
        r0.writeString(r2);
        r0.writeString(r3);
        com.google.android.gms.internal.cast.zzc.zzc(r0, r4);
        com.google.android.gms.internal.cast.zzc.zzc(r0, r5);
        zzd(14, r0);
    }

    public final void zzp(ApiMetadata r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.cast.zzc.zzc(r0, r2);
        zzd(17, r0);
    }

    public final void zzq(zzaj r2, ApiMetadata r3) {
        Parcel r0 = zza();
        com.google.android.gms.internal.cast.zzc.zze(r0, r2);
        com.google.android.gms.internal.cast.zzc.zzc(r0, r3);
        zzd(18, r0);
    }

    public final void zzr(ApiMetadata r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.cast.zzc.zzc(r0, r2);
        zzd(19, r0);
    }
}
