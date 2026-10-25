package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes5.dex */
public final class zzdy extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzdy> CREATOR = null;
    public final int zza;
    public final boolean zzb;
    public final boolean zzc;

    static {
        CREATOR = new zzdx();
    }

    public zzdy(int r1, boolean r2, boolean r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r3, int r4) {
        int r42 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeInt(r3, 2, this.zza);
        SafeParcelWriter.writeBoolean(r3, 3, this.zzb);
        SafeParcelWriter.writeBoolean(r3, 4, this.zzc);
        SafeParcelWriter.finishObjectHeader(r3, r42);
    }
}
