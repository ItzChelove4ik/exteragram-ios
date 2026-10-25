package com.google.android.gms.cast.framework;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class zzm extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzm> CREATOR = null;
    private final int zza;

    static {
        CREATOR = new zzn();
    }

    public zzm(int r1) {
        this.zza = r1;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r2, int r3) {
        int r1 = this.zza;
        int r32 = SafeParcelWriter.beginObjectHeader(r2);
        SafeParcelWriter.writeInt(r2, 2, r1);
        SafeParcelWriter.finishObjectHeader(r2, r32);
    }
}
