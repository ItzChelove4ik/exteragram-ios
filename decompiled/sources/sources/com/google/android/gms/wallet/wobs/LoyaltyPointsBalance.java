package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes5.dex */
public final class LoyaltyPointsBalance extends AbstractSafeParcelable {
    public static final Parcelable.Creator<LoyaltyPointsBalance> CREATOR = null;
    int zza;
    String zzb;
    double zzc;
    String zzd;
    long zze;
    int zzf;

    static {
        CREATOR = new zzi();
    }

    public LoyaltyPointsBalance(int r1, String r2, double r3, String r5, long r6, int r8) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r5;
        this.zze = r6;
        this.zzf = r8;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r6, int r7) {
        int r72 = SafeParcelWriter.beginObjectHeader(r6);
        SafeParcelWriter.writeInt(r6, 2, this.zza);
        SafeParcelWriter.writeString(r6, 3, this.zzb, false);
        SafeParcelWriter.writeDouble(r6, 4, this.zzc);
        SafeParcelWriter.writeString(r6, 5, this.zzd, false);
        SafeParcelWriter.writeLong(r6, 6, this.zze);
        SafeParcelWriter.writeInt(r6, 7, this.zzf);
        SafeParcelWriter.finishObjectHeader(r6, r72);
    }
}
