package com.google.android.gms.internal.measurement;

import android.os.Parcel;
import com.google.android.gms.common.api.Status;

/* loaded from: classes4.dex */
public abstract class zzkr extends zzbm implements zzks {
    public zzkr() {
        super("com.google.android.gms.phenotype.internal.IPhenotypeCallbacks");
    }

    @Override // com.google.android.gms.internal.measurement.zzbm
    public final boolean zza(int r1, Parcel r2, Parcel r3, int r4) {
        switch(r1) {
            case 1: goto L20;
            case 2: goto L19;
            case 3: goto L18;
            case 4: goto L17;
            case 5: goto L16;
            case 6: goto L15;
            case 7: goto L14;
            case 8: goto L13;
            case 9: goto L12;
            case 10: goto L11;
            case 11: goto L10;
            case 12: goto L9;
            case 13: goto L8;
            case 14: goto L7;
            case 15: goto L6;
            case 16: goto L5;
            default: goto L3;
        };
    L3:
        return false;
    L5:
        Status r12 = (Status) zzbn.zzb(r2, Status.CREATOR);
        long r32 = r2.readLong();
        zzbn.zzf(r2);
        zzq(r12, r32);
        return true;
    L6:
        Status r13 = (Status) zzbn.zzb(r2, Status.CREATOR);
        zzbn.zzf(r2);
        zzp(r13);
        return true;
    L7:
        Status r14 = (Status) zzbn.zzb(r2, Status.CREATOR);
        zzbn.zzf(r2);
        zzd(r14);
        return true;
    L8:
        Status r15 = (Status) zzbn.zzb(r2, Status.CREATOR);
        zzjs r33 = (zzjs) zzbn.zzb(r2, zzjs.CREATOR);
        zzbn.zzf(r2);
        zzo(r15, r33);
        return true;
    L9:
        Status r16 = (Status) zzbn.zzb(r2, Status.CREATOR);
        zzbn.zzf(r2);
        zzn(r16);
        return true;
    L10:
        Status r17 = (Status) zzbn.zzb(r2, Status.CREATOR);
        long r34 = r2.readLong();
        zzbn.zzf(r2);
        zzm(r17, r34);
        return true;
    L11:
        Status r18 = (Status) zzbn.zzb(r2, Status.CREATOR);
        zzjh r35 = (zzjh) zzbn.zzb(r2, zzjh.CREATOR);
        zzbn.zzf(r2);
        zzl(r18, r35);
        return true;
    L12:
        Status r19 = (Status) zzbn.zzb(r2, Status.CREATOR);
        zzjo r36 = (zzjo) zzbn.zzb(r2, zzjo.CREATOR);
        zzbn.zzf(r2);
        zzk(r19, r36);
        return true;
    L13:
        Status r110 = (Status) zzbn.zzb(r2, Status.CREATOR);
        zzbn.zzf(r2);
        zzj(r110);
        return true;
    L14:
        Status r111 = (Status) zzbn.zzb(r2, Status.CREATOR);
        zzjj r37 = (zzjj) zzbn.zzb(r2, zzjj.CREATOR);
        zzbn.zzf(r2);
        zzi(r111, r37);
        return true;
    L15:
        Status r112 = (Status) zzbn.zzb(r2, Status.CREATOR);
        zzjl r38 = (zzjl) zzbn.zzb(r2, zzjl.CREATOR);
        zzbn.zzf(r2);
        zzh(r112, r38);
        return true;
    L16:
        Status r113 = (Status) zzbn.zzb(r2, Status.CREATOR);
        zzbn.zzf(r2);
        zzg(r113);
        return true;
    L17:
        Status r114 = (Status) zzbn.zzb(r2, Status.CREATOR);
        zzjh r39 = (zzjh) zzbn.zzb(r2, zzjh.CREATOR);
        zzbn.zzf(r2);
        zzf(r114, r39);
        return true;
    L18:
        Status r115 = (Status) zzbn.zzb(r2, Status.CREATOR);
        zzbn.zzf(r2);
        zze(r115);
        return true;
    L19:
        Status r116 = (Status) zzbn.zzb(r2, Status.CREATOR);
        zzbn.zzf(r2);
        zzc(r116);
        return true;
    L20:
        Status r117 = (Status) zzbn.zzb(r2, Status.CREATOR);
        zzbn.zzf(r2);
        zzb(r117);
        return true;
    }
}
