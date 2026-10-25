package com.google.android.gms.cast.internal;

import android.os.Parcel;
import com.google.android.gms.cast.ApplicationMetadata;

/* loaded from: classes4.dex */
public abstract class zzai extends com.google.android.gms.internal.cast.zzb implements zzaj {
    public zzai() {
        super("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
    }

    @Override // com.google.android.gms.internal.cast.zzb
    public final boolean zza(int r2, Parcel r3, Parcel r4, int r5) {
        switch(r2) {
            case 1: goto L19;
            case 2: goto L18;
            case 3: goto L17;
            case 4: goto L16;
            case 5: goto L15;
            case 6: goto L14;
            case 7: goto L13;
            case 8: goto L12;
            case 9: goto L11;
            case 10: goto L10;
            case 11: goto L9;
            case 12: goto L8;
            case 13: goto L7;
            case 14: goto L6;
            case 15: goto L5;
            default: goto L3;
        };
    L3:
        return false;
    L5:
        int r22 = r3.readInt();
        com.google.android.gms.internal.cast.zzc.zzf(r3);
        zzd(r22);
        return true;
    L6:
        int r23 = r3.readInt();
        com.google.android.gms.internal.cast.zzc.zzf(r3);
        zzc(r23);
        return true;
    L7:
        zzac r24 = (zzac) com.google.android.gms.internal.cast.zzc.zzb(r3, zzac.CREATOR);
        com.google.android.gms.internal.cast.zzc.zzf(r3);
        zzk(r24);
        return true;
    L8:
        zza r25 = (zza) com.google.android.gms.internal.cast.zzc.zzb(r3, zza.CREATOR);
        com.google.android.gms.internal.cast.zzc.zzf(r3);
        zzl(r25);
        return true;
    L9:
        String r26 = r3.readString();
        long r42 = r3.readLong();
        com.google.android.gms.internal.cast.zzc.zzf(r3);
        zzp(r26, r42);
        return true;
    L10:
        String r27 = r3.readString();
        long r43 = r3.readLong();
        int r0 = r3.readInt();
        com.google.android.gms.internal.cast.zzc.zzf(r3);
        zzo(r27, r43, r0);
        return true;
    L11:
        int r28 = r3.readInt();
        com.google.android.gms.internal.cast.zzc.zzf(r3);
        zzi(r28);
        return true;
    L12:
        int r29 = r3.readInt();
        com.google.android.gms.internal.cast.zzc.zzf(r3);
        zzg(r29);
        return true;
    L13:
        int r210 = r3.readInt();
        com.google.android.gms.internal.cast.zzc.zzf(r3);
        zzh(r210);
        return true;
    L14:
        String r211 = r3.readString();
        byte[] r44 = r3.createByteArray();
        com.google.android.gms.internal.cast.zzc.zzf(r3);
        zzn(r211, r44);
        return true;
    L15:
        String r212 = r3.readString();
        String r45 = r3.readString();
        com.google.android.gms.internal.cast.zzc.zzf(r3);
        zzm(r212, r45);
        return true;
    L16:
        String r213 = r3.readString();
        double r46 = r3.readDouble();
        boolean r02 = com.google.android.gms.internal.cast.zzc.zza(r3);
        com.google.android.gms.internal.cast.zzc.zzf(r3);
        zzj(r213, r46, r02);
        return true;
    L17:
        int r214 = r3.readInt();
        com.google.android.gms.internal.cast.zzc.zzf(r3);
        zzf(r214);
        return true;
    L18:
        ApplicationMetadata r215 = (ApplicationMetadata) com.google.android.gms.internal.cast.zzc.zzb(r3, ApplicationMetadata.CREATOR);
        String r47 = r3.readString();
        String r52 = r3.readString();
        boolean r03 = com.google.android.gms.internal.cast.zzc.zza(r3);
        com.google.android.gms.internal.cast.zzc.zzf(r3);
        zze(r215, r47, r52, r03);
        return true;
    L19:
        int r216 = r3.readInt();
        com.google.android.gms.internal.cast.zzc.zzf(r3);
        zzb(r216);
        return true;
    }
}
