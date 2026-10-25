package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public final class zzfz extends com.google.android.gms.internal.measurement.zzbl implements zzgb {
    public zzfz(IBinder r2) {
        super(r2, "com.google.android.gms.measurement.internal.IMeasurementService");
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzA(zzr r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.measurement.zzbn.zzc(r0, r2);
        zzc(27, r0);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzB(zzr r2, zzoo r3, zzgh r4) {
        Parcel r0 = zza();
        com.google.android.gms.internal.measurement.zzbn.zzc(r0, r2);
        com.google.android.gms.internal.measurement.zzbn.zzc(r0, r3);
        com.google.android.gms.internal.measurement.zzbn.zzd(r0, r4);
        zzc(29, r0);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzC(zzr r2, zzaf r3) {
        Parcel r0 = zza();
        com.google.android.gms.internal.measurement.zzbn.zzc(r0, r2);
        com.google.android.gms.internal.measurement.zzbn.zzc(r0, r3);
        zzc(30, r0);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzD(zzr r2, Bundle r3, zzge r4) {
        Parcel r0 = zza();
        com.google.android.gms.internal.measurement.zzbn.zzc(r0, r2);
        com.google.android.gms.internal.measurement.zzbn.zzc(r0, r3);
        com.google.android.gms.internal.measurement.zzbn.zzd(r0, r4);
        zzc(31, r0);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zze(zzbh r2, zzr r3) {
        Parcel r0 = zza();
        com.google.android.gms.internal.measurement.zzbn.zzc(r0, r2);
        com.google.android.gms.internal.measurement.zzbn.zzc(r0, r3);
        zzc(1, r0);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzf(zzpl r2, zzr r3) {
        Parcel r0 = zza();
        com.google.android.gms.internal.measurement.zzbn.zzc(r0, r2);
        com.google.android.gms.internal.measurement.zzbn.zzc(r0, r3);
        zzc(2, r0);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzg(zzr r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.measurement.zzbn.zzc(r0, r2);
        zzc(4, r0);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzi(zzr r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.measurement.zzbn.zzc(r0, r2);
        zzc(6, r0);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final byte[] zzk(zzbh r2, String r3) {
        Parcel r0 = zza();
        com.google.android.gms.internal.measurement.zzbn.zzc(r0, r2);
        r0.writeString(r3);
        Parcel r1 = zzP(9, r0);
        byte[] r22 = r1.createByteArray();
        r1.recycle();
        return r22;
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzl(long r2, String r4, String r5, String r6) {
        Parcel r0 = zza();
        r0.writeLong(r2);
        r0.writeString(r4);
        r0.writeString(r5);
        r0.writeString(r6);
        zzc(10, r0);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final String zzm(zzr r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.measurement.zzbn.zzc(r0, r2);
        Parcel r1 = zzP(11, r0);
        String r22 = r1.readString();
        r1.recycle();
        return r22;
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzn(zzah r2, zzr r3) {
        Parcel r0 = zza();
        com.google.android.gms.internal.measurement.zzbn.zzc(r0, r2);
        com.google.android.gms.internal.measurement.zzbn.zzc(r0, r3);
        zzc(12, r0);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final List zzp(String r2, String r3, boolean r4, zzr r5) {
        Parcel r0 = zza();
        r0.writeString(r2);
        r0.writeString(r3);
        int r22 = com.google.android.gms.internal.measurement.zzbn.$r8$clinit;
        r0.writeInt(r4 ? 1 : 0);
        com.google.android.gms.internal.measurement.zzbn.zzc(r0, r5);
        Parcel r1 = zzP(14, r0);
        ArrayList r23 = r1.createTypedArrayList(zzpl.CREATOR);
        r1.recycle();
        return r23;
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final List zzq(String r2, String r3, String r4, boolean r5) {
        Parcel r22 = zza();
        r22.writeString(null);
        r22.writeString(r3);
        r22.writeString(r4);
        int r32 = com.google.android.gms.internal.measurement.zzbn.$r8$clinit;
        r22.writeInt(r5 ? 1 : 0);
        Parcel r1 = zzP(15, r22);
        ArrayList r23 = r1.createTypedArrayList(zzpl.CREATOR);
        r1.recycle();
        return r23;
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final List zzr(String r2, String r3, zzr r4) {
        Parcel r0 = zza();
        r0.writeString(r2);
        r0.writeString(r3);
        com.google.android.gms.internal.measurement.zzbn.zzc(r0, r4);
        Parcel r1 = zzP(16, r0);
        ArrayList r22 = r1.createTypedArrayList(zzah.CREATOR);
        r1.recycle();
        return r22;
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final List zzs(String r2, String r3, String r4) {
        Parcel r22 = zza();
        r22.writeString(null);
        r22.writeString(r3);
        r22.writeString(r4);
        Parcel r1 = zzP(17, r22);
        ArrayList r23 = r1.createTypedArrayList(zzah.CREATOR);
        r1.recycle();
        return r23;
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzt(zzr r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.measurement.zzbn.zzc(r0, r2);
        zzc(18, r0);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzu(Bundle r2, zzr r3) {
        Parcel r0 = zza();
        com.google.android.gms.internal.measurement.zzbn.zzc(r0, r2);
        com.google.android.gms.internal.measurement.zzbn.zzc(r0, r3);
        zzc(19, r0);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzv(zzr r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.measurement.zzbn.zzc(r0, r2);
        zzc(20, r0);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final zzao zzw(zzr r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.measurement.zzbn.zzc(r0, r2);
        Parcel r1 = zzP(21, r0);
        zzao r22 = (zzao) com.google.android.gms.internal.measurement.zzbn.zzb(r1, zzao.CREATOR);
        r1.recycle();
        return r22;
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzy(zzr r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.measurement.zzbn.zzc(r0, r2);
        zzc(25, r0);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzz(zzr r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.measurement.zzbn.zzc(r0, r2);
        zzc(26, r0);
    }
}
