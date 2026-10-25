package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.wearable.zzb;
import com.google.android.gms.internal.wearable.zzc;

/* loaded from: classes5.dex */
public abstract class zzew extends zzb implements zzex {
    public zzew() {
        super("com.google.android.gms.wearable.internal.IWearableCallbacks");
    }

    @Override // com.google.android.gms.internal.wearable.zzb
    public final boolean zza(int r1, Parcel r2, Parcel r3, int r4) {
        switch(r1) {
            case 2: goto L37;
            case 3: goto L36;
            case 4: goto L35;
            case 5: goto L34;
            case 6: goto L33;
            case 7: goto L32;
            case 8: goto L31;
            case 9: goto L30;
            case 10: goto L29;
            case 11: goto L28;
            case 12: goto L27;
            case 13: goto L26;
            case 14: goto L25;
            case 15: goto L24;
            case 16: goto L23;
            case 17: goto L22;
            case 18: goto L21;
            case 19: goto L20;
            case 20: goto L19;
            case 21: goto L3;
            case 22: goto L18;
            case 23: goto L17;
            case 24: goto L3;
            case 25: goto L3;
            case 26: goto L16;
            case 27: goto L15;
            case 28: goto L14;
            case 29: goto L13;
            case 30: goto L12;
            case 31: goto L3;
            case 32: goto L3;
            case 33: goto L3;
            case 34: goto L11;
            case 35: goto L10;
            case 36: goto L9;
            case 37: goto L8;
            case 38: goto L7;
            case 39: goto L6;
            case 40: goto L5;
            default: goto L3;
        };
    L3:
        return false;
    L5:
        zzq r12 = (zzq) zzc.zza(r2, zzq.CREATOR);
        zzc.zzb(r2);
        zzc(r12);
    L38:
        r3.writeNoException();
        return true;
    L6:
        zzer r13 = (zzer) zzc.zza(r2, zzer.CREATOR);
        zzc.zzb(r2);
        zzy(r13);
        goto L38
    L7:
        zzcf r14 = (zzcf) zzc.zza(r2, zzcf.CREATOR);
        zzc.zzb(r2);
        zzH(r14);
        goto L38
    L8:
        zzeb r15 = (zzeb) zzc.zza(r2, zzeb.CREATOR);
        zzc.zzb(r2);
        zzq(r15);
        goto L38
    L9:
        zzgs r16 = (zzgs) zzc.zza(r2, zzgs.CREATOR);
        zzc.zzb(r2);
        zzB(r16);
        goto L38
    L10:
        zzel r17 = (zzel) zzc.zza(r2, zzel.CREATOR);
        zzc.zzb(r2);
        zzv(r17);
        goto L38
    L11:
        zzha r18 = (zzha) zzc.zza(r2, zzha.CREATOR);
        zzc.zzb(r2);
        zzF(r18);
        goto L38
    L12:
        zzdy r19 = (zzdy) zzc.zza(r2, zzdy.CREATOR);
        zzc.zzb(r2);
        zzo(r19);
        goto L38
    L13:
        zzea r110 = (zzea) zzc.zza(r2, zzea.CREATOR);
        zzc.zzb(r2);
        zzp(r110);
        goto L38
    L14:
        zzdw r111 = (zzdw) zzc.zza(r2, zzdw.CREATOR);
        zzc.zzb(r2);
        zzn(r111);
        goto L38
    L15:
        zzgy r112 = (zzgy) zzc.zza(r2, zzgy.CREATOR);
        zzc.zzb(r2);
        zzD(r112);
        goto L38
    L16:
        zzf r113 = (zzf) zzc.zza(r2, zzf.CREATOR);
        zzc.zzb(r2);
        zzb(r113);
        goto L38
    L17:
        zzdn r114 = (zzdn) zzc.zza(r2, zzdn.CREATOR);
        zzc.zzb(r2);
        zzj(r114);
        goto L38
    L18:
        zzdp r115 = (zzdp) zzc.zza(r2, zzdp.CREATOR);
        zzc.zzb(r2);
        zzk(r115);
        goto L38
    L19:
        zzby r116 = (zzby) zzc.zza(r2, zzby.CREATOR);
        zzc.zzb(r2);
        zze(r116);
        goto L38
    L20:
        zzbw r117 = (zzbw) zzc.zza(r2, zzbw.CREATOR);
        zzc.zzb(r2);
        zzd(r117);
        goto L38
    L21:
        zzdt r118 = (zzdt) zzc.zza(r2, zzdt.CREATOR);
        zzc.zzb(r2);
        zzm(r118);
        goto L38
    L22:
        zzdr r119 = (zzdr) zzc.zza(r2, zzdr.CREATOR);
        zzc.zzb(r2);
        zzl(r119);
        goto L38
    L23:
        zzcc r120 = (zzcc) zzc.zza(r2, zzcc.CREATOR);
        zzc.zzb(r2);
        zzg(r120);
        goto L38
    L24:
        zzcc r121 = (zzcc) zzc.zza(r2, zzcc.CREATOR);
        zzc.zzb(r2);
        zzf(r121);
        goto L38
    L25:
        zzgo r122 = (zzgo) zzc.zza(r2, zzgo.CREATOR);
        zzc.zzb(r2);
        zzA(r122);
        goto L38
    L26:
        zzef r123 = (zzef) zzc.zza(r2, zzef.CREATOR);
        zzc.zzb(r2);
        zzs(r123);
        goto L38
    L27:
        zzhg r124 = (zzhg) zzc.zza(r2, zzhg.CREATOR);
        zzc.zzb(r2);
        zzz(r124);
        goto L38
    L28:
        Status r125 = (Status) zzc.zza(r2, Status.CREATOR);
        zzc.zzb(r2);
        zzG(r125);
        goto L38
    L29:
        zzeh r126 = (zzeh) zzc.zza(r2, zzeh.CREATOR);
        zzc.zzb(r2);
        zzt(r126);
        goto L38
    L30:
        zzep r127 = (zzep) zzc.zza(r2, zzep.CREATOR);
        zzc.zzb(r2);
        zzx(r127);
        goto L38
    L31:
        zzen r128 = (zzen) zzc.zza(r2, zzen.CREATOR);
        zzc.zzb(r2);
        zzw(r128);
        goto L38
    L32:
        zzhc r129 = (zzhc) zzc.zza(r2, zzhc.CREATOR);
        zzc.zzb(r2);
        zzE(r129);
        goto L38
    L33:
        zzdl r130 = (zzdl) zzc.zza(r2, zzdl.CREATOR);
        zzc.zzb(r2);
        zzi(r130);
        goto L38
    L34:
        DataHolder r131 = (DataHolder) zzc.zza(r2, DataHolder.CREATOR);
        zzc.zzb(r2);
        zzh(r131);
        goto L38
    L35:
        zzej r132 = (zzej) zzc.zza(r2, zzej.CREATOR);
        zzc.zzb(r2);
        zzu(r132);
        goto L38
    L36:
        zzgu r133 = (zzgu) zzc.zza(r2, zzgu.CREATOR);
        zzc.zzb(r2);
        zzC(r133);
        goto L38
    L37:
        zzed r134 = (zzed) zzc.zza(r2, zzed.CREATOR);
        zzc.zzb(r2);
        zzr(r134);
        goto L38
    }
}
