package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import java.util.List;

/* loaded from: classes5.dex */
public abstract class zzga extends com.google.android.gms.internal.measurement.zzbm implements zzgb {
    public zzga() {
        super("com.google.android.gms.measurement.internal.IMeasurementService");
    }

    @Override // com.google.android.gms.internal.measurement.zzbm
    public final boolean zza(int r9, Parcel r10, Parcel r11, int r12) {
        zzgh r122 = null;
        zzge r123 = null;
        switch(r9) {
            case 1: goto L47;
            case 2: goto L46;
            case 3: goto L4;
            case 4: goto L45;
            case 5: goto L44;
            case 6: goto L43;
            case 7: goto L42;
            case 8: goto L4;
            case 9: goto L41;
            case 10: goto L40;
            case 11: goto L39;
            case 12: goto L38;
            case 13: goto L37;
            case 14: goto L36;
            case 15: goto L35;
            case 16: goto L34;
            case 17: goto L33;
            case 18: goto L32;
            case 19: goto L31;
            case 20: goto L30;
            case 21: goto L26;
            case 22: goto L4;
            case 23: goto L4;
            case 24: goto L25;
            case 25: goto L24;
            case 26: goto L23;
            case 27: goto L22;
            case 28: goto L4;
            case 29: goto L14;
            case 30: goto L13;
            case 31: goto L5;
            default: goto L4;
        };
    L4:
        return false;
    L5:
        zzr r92 = (zzr) com.google.android.gms.internal.measurement.zzbn.zzb(r10, zzr.CREATOR);
        Bundle r0 = (Bundle) com.google.android.gms.internal.measurement.zzbn.zzb(r10, Bundle.CREATOR);
        IBinder r2 = r10.readStrongBinder();
        if (r2 == null) goto L12;
        IInterface r124 = r2.queryLocalInterface("com.google.android.gms.measurement.internal.ITriggerUrisCallback");
        if ((r124 instanceof zzge) == false) goto L11;
        r123 = (zzge) r124;
        goto L12
    L11:
        r123 = new zzgc(r2);
    L12:
        com.google.android.gms.internal.measurement.zzbn.zzf(r10);
        zzD(r92, r0, r123);
        r11.writeNoException();
    L48:
        return true;
    L13:
        zzr r93 = (zzr) com.google.android.gms.internal.measurement.zzbn.zzb(r10, zzr.CREATOR);
        zzaf r125 = (zzaf) com.google.android.gms.internal.measurement.zzbn.zzb(r10, zzaf.CREATOR);
        com.google.android.gms.internal.measurement.zzbn.zzf(r10);
        zzC(r93, r125);
        r11.writeNoException();
        goto L48
    L14:
        zzr r94 = (zzr) com.google.android.gms.internal.measurement.zzbn.zzb(r10, zzr.CREATOR);
        zzoo r02 = (zzoo) com.google.android.gms.internal.measurement.zzbn.zzb(r10, zzoo.CREATOR);
        IBinder r22 = r10.readStrongBinder();
        if (r22 == null) goto L21;
        IInterface r126 = r22.queryLocalInterface("com.google.android.gms.measurement.internal.IUploadBatchesCallback");
        if ((r126 instanceof zzgh) == false) goto L20;
        r122 = (zzgh) r126;
        goto L21
    L20:
        r122 = new zzgf(r22);
    L21:
        com.google.android.gms.internal.measurement.zzbn.zzf(r10);
        zzB(r94, r02, r122);
        r11.writeNoException();
        goto L48
    L22:
        zzr r95 = (zzr) com.google.android.gms.internal.measurement.zzbn.zzb(r10, zzr.CREATOR);
        com.google.android.gms.internal.measurement.zzbn.zzf(r10);
        zzA(r95);
        r11.writeNoException();
        goto L48
    L23:
        zzr r96 = (zzr) com.google.android.gms.internal.measurement.zzbn.zzb(r10, zzr.CREATOR);
        com.google.android.gms.internal.measurement.zzbn.zzf(r10);
        zzz(r96);
        r11.writeNoException();
        goto L48
    L24:
        zzr r97 = (zzr) com.google.android.gms.internal.measurement.zzbn.zzb(r10, zzr.CREATOR);
        com.google.android.gms.internal.measurement.zzbn.zzf(r10);
        zzy(r97);
        r11.writeNoException();
        goto L48
    L25:
        zzr r98 = (zzr) com.google.android.gms.internal.measurement.zzbn.zzb(r10, zzr.CREATOR);
        Bundle r127 = (Bundle) com.google.android.gms.internal.measurement.zzbn.zzb(r10, Bundle.CREATOR);
        com.google.android.gms.internal.measurement.zzbn.zzf(r10);
        List r8 = zzx(r98, r127);
        r11.writeNoException();
        r11.writeTypedList(r8);
        goto L48
    L26:
        zzr r99 = (zzr) com.google.android.gms.internal.measurement.zzbn.zzb(r10, zzr.CREATOR);
        com.google.android.gms.internal.measurement.zzbn.zzf(r10);
        zzao r82 = zzw(r99);
        r11.writeNoException();
        if (r82 != null) goto L29;
        r11.writeInt(0);
        goto L48
    L29:
        r11.writeInt(1);
        r82.writeToParcel(r11, 1);
        goto L48
    L30:
        zzr r910 = (zzr) com.google.android.gms.internal.measurement.zzbn.zzb(r10, zzr.CREATOR);
        com.google.android.gms.internal.measurement.zzbn.zzf(r10);
        zzv(r910);
        r11.writeNoException();
        goto L48
    L31:
        Bundle r911 = (Bundle) com.google.android.gms.internal.measurement.zzbn.zzb(r10, Bundle.CREATOR);
        zzr r128 = (zzr) com.google.android.gms.internal.measurement.zzbn.zzb(r10, zzr.CREATOR);
        com.google.android.gms.internal.measurement.zzbn.zzf(r10);
        zzu(r911, r128);
        r11.writeNoException();
        goto L48
    L32:
        zzr r912 = (zzr) com.google.android.gms.internal.measurement.zzbn.zzb(r10, zzr.CREATOR);
        com.google.android.gms.internal.measurement.zzbn.zzf(r10);
        zzt(r912);
        r11.writeNoException();
        goto L48
    L33:
        String r913 = r10.readString();
        String r129 = r10.readString();
        String r03 = r10.readString();
        com.google.android.gms.internal.measurement.zzbn.zzf(r10);
        List r83 = zzs(r913, r129, r03);
        r11.writeNoException();
        r11.writeTypedList(r83);
        goto L48
    L34:
        String r914 = r10.readString();
        String r1210 = r10.readString();
        zzr r04 = (zzr) com.google.android.gms.internal.measurement.zzbn.zzb(r10, zzr.CREATOR);
        com.google.android.gms.internal.measurement.zzbn.zzf(r10);
        List r84 = zzr(r914, r1210, r04);
        r11.writeNoException();
        r11.writeTypedList(r84);
        goto L48
    L35:
        String r915 = r10.readString();
        String r1211 = r10.readString();
        String r05 = r10.readString();
        boolean r23 = com.google.android.gms.internal.measurement.zzbn.zza(r10);
        com.google.android.gms.internal.measurement.zzbn.zzf(r10);
        List r85 = zzq(r915, r1211, r05, r23);
        r11.writeNoException();
        r11.writeTypedList(r85);
        goto L48
    L36:
        String r916 = r10.readString();
        String r1212 = r10.readString();
        boolean r06 = com.google.android.gms.internal.measurement.zzbn.zza(r10);
        zzr r24 = (zzr) com.google.android.gms.internal.measurement.zzbn.zzb(r10, zzr.CREATOR);
        com.google.android.gms.internal.measurement.zzbn.zzf(r10);
        List r86 = zzp(r916, r1212, r06, r24);
        r11.writeNoException();
        r11.writeTypedList(r86);
        goto L48
    L37:
        zzah r917 = (zzah) com.google.android.gms.internal.measurement.zzbn.zzb(r10, zzah.CREATOR);
        com.google.android.gms.internal.measurement.zzbn.zzf(r10);
        zzo(r917);
        r11.writeNoException();
        goto L48
    L38:
        zzah r918 = (zzah) com.google.android.gms.internal.measurement.zzbn.zzb(r10, zzah.CREATOR);
        zzr r1213 = (zzr) com.google.android.gms.internal.measurement.zzbn.zzb(r10, zzr.CREATOR);
        com.google.android.gms.internal.measurement.zzbn.zzf(r10);
        zzn(r918, r1213);
        r11.writeNoException();
        goto L48
    L39:
        zzr r919 = (zzr) com.google.android.gms.internal.measurement.zzbn.zzb(r10, zzr.CREATOR);
        com.google.android.gms.internal.measurement.zzbn.zzf(r10);
        String r87 = zzm(r919);
        r11.writeNoException();
        r11.writeString(r87);
        goto L48
    L40:
        long r3 = r10.readLong();
        String r5 = r10.readString();
        String r6 = r10.readString();
        String r7 = r10.readString();
        com.google.android.gms.internal.measurement.zzbn.zzf(r10);
        zzl(r3, r5, r6, r7);
        r11.writeNoException();
        goto L48
    L41:
        zzbh r88 = (zzbh) com.google.android.gms.internal.measurement.zzbn.zzb(r10, zzbh.CREATOR);
        String r920 = r10.readString();
        com.google.android.gms.internal.measurement.zzbn.zzf(r10);
        byte[] r89 = zzk(r88, r920);
        r11.writeNoException();
        r11.writeByteArray(r89);
        goto L48
    L42:
        zzr r810 = (zzr) com.google.android.gms.internal.measurement.zzbn.zzb(r10, zzr.CREATOR);
        boolean r921 = com.google.android.gms.internal.measurement.zzbn.zza(r10);
        com.google.android.gms.internal.measurement.zzbn.zzf(r10);
        List r811 = zzj(r810, r921);
        r11.writeNoException();
        r11.writeTypedList(r811);
        goto L48
    L43:
        zzr r812 = (zzr) com.google.android.gms.internal.measurement.zzbn.zzb(r10, zzr.CREATOR);
        com.google.android.gms.internal.measurement.zzbn.zzf(r10);
        zzi(r812);
        r11.writeNoException();
        goto L48
    L44:
        zzbh r813 = (zzbh) com.google.android.gms.internal.measurement.zzbn.zzb(r10, zzbh.CREATOR);
        String r922 = r10.readString();
        String r1214 = r10.readString();
        com.google.android.gms.internal.measurement.zzbn.zzf(r10);
        zzh(r813, r922, r1214);
        r11.writeNoException();
        goto L48
    L45:
        zzr r814 = (zzr) com.google.android.gms.internal.measurement.zzbn.zzb(r10, zzr.CREATOR);
        com.google.android.gms.internal.measurement.zzbn.zzf(r10);
        zzg(r814);
        r11.writeNoException();
        goto L48
    L46:
        zzpl r815 = (zzpl) com.google.android.gms.internal.measurement.zzbn.zzb(r10, zzpl.CREATOR);
        zzr r923 = (zzr) com.google.android.gms.internal.measurement.zzbn.zzb(r10, zzr.CREATOR);
        com.google.android.gms.internal.measurement.zzbn.zzf(r10);
        zzf(r815, r923);
        r11.writeNoException();
        goto L48
    L47:
        zzbh r816 = (zzbh) com.google.android.gms.internal.measurement.zzbn.zzb(r10, zzbh.CREATOR);
        zzr r924 = (zzr) com.google.android.gms.internal.measurement.zzbn.zzb(r10, zzr.CREATOR);
        com.google.android.gms.internal.measurement.zzbn.zzf(r10);
        zze(r816, r924);
        r11.writeNoException();
        goto L48
    }
}
