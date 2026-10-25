package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes5.dex */
public final class zzhc extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzhc> CREATOR = null;
    public final int zza;
    public final int zzb;

    static {
        CREATOR = new zzhd();
    }

    public zzhc(int r1, int r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r3, int r4) {
        int r42 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeInt(r3, 2, this.zza);
        SafeParcelWriter.writeInt(r3, 3, this.zzb);
        SafeParcelWriter.finishObjectHeader(r3, r42);
    }
}
