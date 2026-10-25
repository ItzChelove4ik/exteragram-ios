package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

@Deprecated
/* loaded from: classes5.dex */
public final class zza extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zza> CREATOR = null;
    String zza;
    String zzb;
    String zzc;
    String zzd;
    String zze;
    String zzf;
    String zzg;
    String zzh;
    String zzi;
    boolean zzj;
    String zzk;

    static {
        CREATOR = new zzb();
    }

    public zza(String r1, String r2, String r3, String r4, String r5, String r6, String r7, String r8, String r9, boolean r10, String r11) {
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
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
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
        SafeParcelWriter.writeBoolean(r4, 11, this.zzj);
        SafeParcelWriter.writeString(r4, 12, this.zzk, false);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }
}
