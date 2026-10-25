package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes5.dex */
public final class zzcc extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzcc> CREATOR = null;
    public final int zza;

    static {
        CREATOR = new zzcd();
    }

    public zzcc(int r1) {
        this.zza = r1;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r2, int r3) {
        int r32 = SafeParcelWriter.beginObjectHeader(r2);
        SafeParcelWriter.writeInt(r2, 2, this.zza);
        SafeParcelWriter.finishObjectHeader(r2, r32);
    }
}
