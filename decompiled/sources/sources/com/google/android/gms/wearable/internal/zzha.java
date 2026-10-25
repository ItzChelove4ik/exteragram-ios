package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes5.dex */
public final class zzha extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzha> CREATOR = null;
    public final int zza;
    public final int zzb;
    public final byte[] zzc;

    static {
        CREATOR = new zzhb();
    }

    public zzha(int r1, int r2, byte[] r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r3, int r4) {
        int r42 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeInt(r3, 1, this.zza);
        SafeParcelWriter.writeInt(r3, 2, this.zzb);
        SafeParcelWriter.writeByteArray(r3, 3, this.zzc, false);
        SafeParcelWriter.finishObjectHeader(r3, r42);
    }
}
