package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.wallet.wobs.CommonWalletObject;

/* loaded from: classes5.dex */
public final class OfferWalletObject extends AbstractSafeParcelable {
    public static final Parcelable.Creator<OfferWalletObject> CREATOR = null;
    String zza;
    String zzb;
    CommonWalletObject zzc;
    private final int zzd;

    static {
        CREATOR = new zzv();
    }

    public OfferWalletObject(int r1, String r2, String r3, CommonWalletObject r4) {
        this.zzd = r1;
        this.zzb = r3;
        if (r1 >= 3) goto L6;
        com.google.android.gms.wallet.wobs.zzb r12 = CommonWalletObject.zzb();
        r12.zzr(r2);
        this.zzc = r12.zzz();
        return;
    L6:
        this.zzc = r4;
    }

    public int getVersionCode() {
        return this.zzd;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeInt(r5, 1, getVersionCode());
        SafeParcelWriter.writeString(r5, 2, this.zza, false);
        SafeParcelWriter.writeString(r5, 3, this.zzb, false);
        SafeParcelWriter.writeParcelable(r5, 4, this.zzc, r6, false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }
}
