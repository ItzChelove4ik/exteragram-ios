package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes5.dex */
public final class zzgo extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzgo> CREATOR = null;
    public final int zza;
    public final zzbq zzb;

    static {
        CREATOR = new zzgp();
    }

    public zzgo(int r1, zzbq r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        int r0 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeInt(r4, 2, this.zza);
        SafeParcelWriter.writeParcelable(r4, 3, this.zzb, r5, false);
        SafeParcelWriter.finishObjectHeader(r4, r0);
    }
}
