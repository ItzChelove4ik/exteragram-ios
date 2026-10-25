package com.google.android.gms.internal.cast;

import android.app.PendingIntent;
import android.os.Parcel;
import com.google.android.gms.common.api.Status;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public abstract class zzge extends zzb implements zzgf {
    public zzge() {
        super("com.google.android.gms.usagereporting.internal.IUsageReportingCallbacks");
    }

    @Override // com.google.android.gms.internal.cast.zzb
    public final boolean zza(int r1, Parcel r2, Parcel r3, int r4) {
        switch(r1) {
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
        Status r12 = (Status) zzc.zzb(r2, Status.CREATOR);
        zzfn r32 = (zzfn) zzc.zzb(r2, zzfn.CREATOR);
        zzc.zzf(r2);
        zzo(r12, r32);
        return true;
    L6:
        Status r13 = (Status) zzc.zzb(r2, Status.CREATOR);
        zzfw r33 = (zzfw) zzc.zzb(r2, zzfw.CREATOR);
        zzc.zzf(r2);
        zzn(r13, r33);
        return true;
    L7:
        Status r14 = (Status) zzc.zzb(r2, Status.CREATOR);
        zzc.zzf(r2);
        zzm(r14);
        return true;
    L8:
        Status r15 = (Status) zzc.zzb(r2, Status.CREATOR);
        zzfp r34 = (zzfp) zzc.zzb(r2, zzfp.CREATOR);
        zzc.zzf(r2);
        zzl(r15, r34);
        return true;
    L9:
        Status r16 = (Status) zzc.zzb(r2, Status.CREATOR);
        zzc.zzf(r2);
        zzk(r16);
        return true;
    L10:
        PendingIntent r17 = (PendingIntent) zzc.zzb(r2, PendingIntent.CREATOR);
        zzc.zzf(r2);
        zzj(r17);
        return true;
    L11:
        Status r18 = (Status) zzc.zzb(r2, Status.CREATOR);
        zzfn r35 = (zzfn) zzc.zzb(r2, zzfn.CREATOR);
        zzc.zzf(r2);
        zzi(r18, r35);
        return true;
    L12:
        Status r19 = (Status) zzc.zzb(r2, Status.CREATOR);
        boolean r36 = zzc.zza(r2);
        zzfn r42 = (zzfn) zzc.zzb(r2, zzfn.CREATOR);
        zzc.zzf(r2);
        zzh(r19, r36, r42);
        return true;
    L13:
        Status r110 = (Status) zzc.zzb(r2, Status.CREATOR);
        zzc.zzf(r2);
        zzg(r110);
        return true;
    L14:
        Status r111 = (Status) zzc.zzb(r2, Status.CREATOR);
        ArrayList<String> r37 = r2.createStringArrayList();
        zzc.zzf(r2);
        zzf(r111, r37);
        return true;
    L15:
        Status r112 = (Status) zzc.zzb(r2, Status.CREATOR);
        zzc.zzf(r2);
        zze(r112);
        return true;
    L16:
        Status r113 = (Status) zzc.zzb(r2, Status.CREATOR);
        zzc.zzf(r2);
        zzd(r113);
        return true;
    L17:
        Status r114 = (Status) zzc.zzb(r2, Status.CREATOR);
        zzc.zzf(r2);
        zzc(r114);
        return true;
    L18:
        Status r115 = (Status) zzc.zzb(r2, Status.CREATOR);
        zzgc r38 = (zzgc) zzc.zzb(r2, zzgc.CREATOR);
        zzc.zzf(r2);
        zzb(r115, r38);
        return true;
    }
}
