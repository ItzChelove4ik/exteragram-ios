package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes5.dex */
public final class zzoh extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzoh> CREATOR = null;
    public final String zza;
    public final long zzb;
    public final int zzc;

    static {
        CREATOR = new zzoi();
    }

    public zzoh(String r1, long r2, int r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r4;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        String r52 = this.zza;
        int r0 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeString(r4, 1, r52, false);
        SafeParcelWriter.writeLong(r4, 2, this.zzb);
        SafeParcelWriter.writeInt(r4, 3, this.zzc);
        SafeParcelWriter.finishObjectHeader(r4, r0);
    }
}
