package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.identity.intents.model.UserAddress;

/* loaded from: classes5.dex */
public final class CardInfo extends AbstractSafeParcelable {
    public static final Parcelable.Creator<CardInfo> CREATOR = null;
    String zza;
    String zzb;
    String zzc;
    int zzd;
    UserAddress zze;

    static {
        CREATOR = new zze();
    }

    public CardInfo(String r1, String r2, String r3, int r4, UserAddress r5) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeString(r5, 1, this.zza, false);
        SafeParcelWriter.writeString(r5, 2, this.zzb, false);
        SafeParcelWriter.writeString(r5, 3, this.zzc, false);
        SafeParcelWriter.writeInt(r5, 4, this.zzd);
        SafeParcelWriter.writeParcelable(r5, 5, this.zze, r6, false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }
}
