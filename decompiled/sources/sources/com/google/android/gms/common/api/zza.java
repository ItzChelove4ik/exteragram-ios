package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes4.dex */
final class zza implements Parcelable.Creator {
    private static final zza zzb = null;
    private final Parcelable.Creator zza;

    static {
        zzb = new zza(new zzb());
    }

    private zza(Parcelable.Creator r1) {
        this.zza = r1;
    }

    public static zza zza() {
        return zzb;
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r3) {
        int r2 = r3.dataPosition();
        if (r3.readInt() == (-204102970)) goto L5;
        r3.setDataPosition(r2 - 4);
        return ApiMetadata.getEmptyInstance();
    L5:
        return zzb.zza(r3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object[] newArray(int r1) {
        return new ApiMetadata[r1];
    }
}
