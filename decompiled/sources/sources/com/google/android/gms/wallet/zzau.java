package com.google.android.gms.wallet;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes5.dex */
public final class zzau extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzau> CREATOR = null;
    String zza;
    Bundle zzb;

    static {
        CREATOR = new zzav();
    }

    public zzau(String r1, Bundle r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeString(r4, 2, this.zza, false);
        SafeParcelWriter.writeBundle(r4, 3, this.zzb, false);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }
}
