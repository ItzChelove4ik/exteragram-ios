package com.google.android.gms.wallet.button;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes5.dex */
public final class zzc extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzc> CREATOR = null;
    String zza;
    int zzb;
    boolean zzc;

    static {
        CREATOR = new zzd();
    }

    public zzc(String r1, int r2, boolean r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeString(r4, 1, this.zza, false);
        SafeParcelWriter.writeInt(r4, 2, this.zzb);
        SafeParcelWriter.writeBoolean(r4, 3, this.zzc);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }
}
