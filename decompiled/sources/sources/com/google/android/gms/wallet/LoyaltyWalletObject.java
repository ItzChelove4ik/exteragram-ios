package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.wallet.wobs.LoyaltyPoints;
import com.google.android.gms.wallet.wobs.TimeInterval;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public final class LoyaltyWalletObject extends AbstractSafeParcelable {
    public static final Parcelable.Creator<LoyaltyWalletObject> CREATOR = null;
    String zza;
    String zzb;
    String zzc;
    String zzd;
    String zze;
    String zzf;
    String zzg;
    String zzh;

    @Deprecated
    String zzi;
    String zzj;
    int zzk;
    final ArrayList zzl;
    TimeInterval zzm;
    final ArrayList zzn;

    @Deprecated
    String zzo;

    @Deprecated
    String zzp;
    final ArrayList zzq;
    boolean zzr;
    final ArrayList zzs;
    final ArrayList zzt;
    final ArrayList zzu;
    LoyaltyPoints zzv;

    static {
        CREATOR = new zzs();
    }

    public LoyaltyWalletObject(String r1, String r2, String r3, String r4, String r5, String r6, String r7, String r8, String r9, String r10, int r11, ArrayList r12, TimeInterval r13, ArrayList r14, String r15, String r16, ArrayList r17, boolean r18, ArrayList r19, ArrayList r20, ArrayList r21, LoyaltyPoints r22) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
        this.zzf = r6;
        this.zzg = r7;
        this.zzh = r8;
        this.zzi = r9;
        this.zzj = r10;
        this.zzk = r11;
        this.zzl = r12;
        this.zzm = r13;
        this.zzn = r14;
        this.zzo = r15;
        this.zzp = r16;
        this.zzq = r17;
        this.zzr = r18;
        this.zzs = r19;
        this.zzt = r20;
        this.zzu = r21;
        this.zzv = r22;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeString(r5, 2, this.zza, false);
        SafeParcelWriter.writeString(r5, 3, this.zzb, false);
        SafeParcelWriter.writeString(r5, 4, this.zzc, false);
        SafeParcelWriter.writeString(r5, 5, this.zzd, false);
        SafeParcelWriter.writeString(r5, 6, this.zze, false);
        SafeParcelWriter.writeString(r5, 7, this.zzf, false);
        SafeParcelWriter.writeString(r5, 8, this.zzg, false);
        SafeParcelWriter.writeString(r5, 9, this.zzh, false);
        SafeParcelWriter.writeString(r5, 10, this.zzi, false);
        SafeParcelWriter.writeString(r5, 11, this.zzj, false);
        SafeParcelWriter.writeInt(r5, 12, this.zzk);
        SafeParcelWriter.writeTypedList(r5, 13, this.zzl, false);
        SafeParcelWriter.writeParcelable(r5, 14, this.zzm, r6, false);
        SafeParcelWriter.writeTypedList(r5, 15, this.zzn, false);
        SafeParcelWriter.writeString(r5, 16, this.zzo, false);
        SafeParcelWriter.writeString(r5, 17, this.zzp, false);
        SafeParcelWriter.writeTypedList(r5, 18, this.zzq, false);
        SafeParcelWriter.writeBoolean(r5, 19, this.zzr);
        SafeParcelWriter.writeTypedList(r5, 20, this.zzs, false);
        SafeParcelWriter.writeTypedList(r5, 21, this.zzt, false);
        SafeParcelWriter.writeTypedList(r5, 22, this.zzu, false);
        SafeParcelWriter.writeParcelable(r5, 23, this.zzv, r6, false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }
}
