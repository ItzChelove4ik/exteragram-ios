package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes5.dex */
public final class LoyaltyPoints extends AbstractSafeParcelable {
    public static final Parcelable.Creator<LoyaltyPoints> CREATOR = null;
    String zza;
    LoyaltyPointsBalance zzb;

    @Deprecated
    TimeInterval zzc;

    static {
        CREATOR = new zzj();
    }

    public LoyaltyPoints(String r1, LoyaltyPointsBalance r2, TimeInterval r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeString(r5, 2, this.zza, false);
        SafeParcelWriter.writeParcelable(r5, 3, this.zzb, r6, false);
        SafeParcelWriter.writeParcelable(r5, 5, this.zzc, r6, false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }
}
