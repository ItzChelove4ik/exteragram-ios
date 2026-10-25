package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes5.dex */
public final class TimeInterval extends AbstractSafeParcelable {
    public static final Parcelable.Creator<TimeInterval> CREATOR = null;
    long zza;
    long zzb;

    static {
        CREATOR = new zzl();
    }

    public TimeInterval(long r1, long r3) {
        this.zza = r1;
        this.zzb = r3;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeLong(r4, 2, this.zza);
        SafeParcelWriter.writeLong(r4, 3, this.zzb);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }
}
