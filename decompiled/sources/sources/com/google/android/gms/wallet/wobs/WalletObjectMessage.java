package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes5.dex */
public final class WalletObjectMessage extends AbstractSafeParcelable {
    public static final Parcelable.Creator<WalletObjectMessage> CREATOR = null;
    String zza;
    String zzb;
    TimeInterval zzc;

    @Deprecated
    UriData zzd;

    @Deprecated
    UriData zze;

    static {
        CREATOR = new zzo();
    }

    public WalletObjectMessage(String r1, String r2, TimeInterval r3, UriData r4, UriData r5) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeString(r5, 2, this.zza, false);
        SafeParcelWriter.writeString(r5, 3, this.zzb, false);
        SafeParcelWriter.writeParcelable(r5, 4, this.zzc, r6, false);
        SafeParcelWriter.writeParcelable(r5, 5, this.zzd, r6, false);
        SafeParcelWriter.writeParcelable(r5, 6, this.zze, r6, false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }
}
