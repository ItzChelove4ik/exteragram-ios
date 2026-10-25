package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.ArrayUtils;
import java.util.ArrayList;

@KeepName
/* loaded from: classes5.dex */
public class CommonWalletObject extends AbstractSafeParcelable {
    public static final Parcelable.Creator<CommonWalletObject> CREATOR = null;
    String zza;
    String zzb;
    String zzc;
    String zzd;
    String zze;
    String zzf;
    String zzg;

    @Deprecated
    String zzh;
    int zzi;
    final ArrayList zzj;
    TimeInterval zzk;
    final ArrayList zzl;

    @Deprecated
    String zzm;

    @Deprecated
    String zzn;
    final ArrayList zzo;
    boolean zzp;
    final ArrayList zzq;
    final ArrayList zzr;
    final ArrayList zzs;

    static {
        CREATOR = new zzc();
    }

    public CommonWalletObject() {
        this.zzj = ArrayUtils.newArrayList();
        this.zzl = ArrayUtils.newArrayList();
        this.zzo = ArrayUtils.newArrayList();
        this.zzq = ArrayUtils.newArrayList();
        this.zzr = ArrayUtils.newArrayList();
        this.zzs = ArrayUtils.newArrayList();
    }

    public CommonWalletObject(String r1, String r2, String r3, String r4, String r5, String r6, String r7, String r8, int r9, ArrayList r10, TimeInterval r11, ArrayList r12, String r13, String r14, ArrayList r15, boolean r16, ArrayList r17, ArrayList r18, ArrayList r19) {
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
    }

    public static zzb zzb() {
        return new zzb(new CommonWalletObject(), null);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeString(r5, 2, this.zza, false);
        SafeParcelWriter.writeString(r5, 3, this.zzb, false);
        SafeParcelWriter.writeString(r5, 4, this.zzc, false);
        SafeParcelWriter.writeString(r5, 5, this.zzd, false);
        SafeParcelWriter.writeString(r5, 6, this.zze, false);
        SafeParcelWriter.writeString(r5, 7, this.zzf, false);
        SafeParcelWriter.writeString(r5, 8, this.zzg, false);
        SafeParcelWriter.writeString(r5, 9, this.zzh, false);
        SafeParcelWriter.writeInt(r5, 10, this.zzi);
        SafeParcelWriter.writeTypedList(r5, 11, this.zzj, false);
        SafeParcelWriter.writeParcelable(r5, 12, this.zzk, r6, false);
        SafeParcelWriter.writeTypedList(r5, 13, this.zzl, false);
        SafeParcelWriter.writeString(r5, 14, this.zzm, false);
        SafeParcelWriter.writeString(r5, 15, this.zzn, false);
        SafeParcelWriter.writeTypedList(r5, 16, this.zzo, false);
        SafeParcelWriter.writeBoolean(r5, 17, this.zzp);
        SafeParcelWriter.writeTypedList(r5, 18, this.zzq, false);
        SafeParcelWriter.writeTypedList(r5, 19, this.zzr, false);
        SafeParcelWriter.writeTypedList(r5, 20, this.zzs, false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }
}
