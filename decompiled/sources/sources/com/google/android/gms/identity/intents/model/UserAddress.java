package com.google.android.gms.identity.intents.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class UserAddress extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<UserAddress> CREATOR = null;
    String zza;
    String zzb;
    String zzc;
    String zzd;
    String zze;
    String zzf;
    String zzg;
    String zzh;
    String zzi;
    String zzj;
    String zzk;
    String zzl;
    boolean zzm;
    String zzn;
    String zzo;

    static {
        CREATOR = new zzb();
    }

    public UserAddress(String r1, String r2, String r3, String r4, String r5, String r6, String r7, String r8, String r9, String r10, String r11, String r12, boolean r13, String r14, String r15) {
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
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeString(r4, 2, this.zza, false);
        SafeParcelWriter.writeString(r4, 3, this.zzb, false);
        SafeParcelWriter.writeString(r4, 4, this.zzc, false);
        SafeParcelWriter.writeString(r4, 5, this.zzd, false);
        SafeParcelWriter.writeString(r4, 6, this.zze, false);
        SafeParcelWriter.writeString(r4, 7, this.zzf, false);
        SafeParcelWriter.writeString(r4, 8, this.zzg, false);
        SafeParcelWriter.writeString(r4, 9, this.zzh, false);
        SafeParcelWriter.writeString(r4, 10, this.zzi, false);
        SafeParcelWriter.writeString(r4, 11, this.zzj, false);
        SafeParcelWriter.writeString(r4, 12, this.zzk, false);
        SafeParcelWriter.writeString(r4, 13, this.zzl, false);
        SafeParcelWriter.writeBoolean(r4, 14, this.zzm);
        SafeParcelWriter.writeString(r4, 15, this.zzn, false);
        SafeParcelWriter.writeString(r4, 16, this.zzo, false);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }
}
