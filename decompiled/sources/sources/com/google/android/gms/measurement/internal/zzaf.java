package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes5.dex */
public final class zzaf extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzaf> CREATOR = null;
    public final long zza;
    public final int zzb;
    public final long zzc;

    static {
        CREATOR = new zzag();
    }

    public zzaf(long r1, int r3, long r4) {
        this.zza = r1;
        this.zzb = r3;
        this.zzc = r4;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        long r0 = this.zza;
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeLong(r4, 1, r0);
        SafeParcelWriter.writeInt(r4, 2, this.zzb);
        SafeParcelWriter.writeLong(r4, 3, this.zzc);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }
}
