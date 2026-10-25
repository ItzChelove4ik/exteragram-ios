package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes5.dex */
public final class LocationSettingsResult extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<LocationSettingsResult> CREATOR = null;
    private final Status zza;
    private final LocationSettingsStates zzb;

    static {
        CREATOR = new zzah();
    }

    public LocationSettingsResult(Status r1, LocationSettingsStates r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    public LocationSettingsStates getLocationSettingsStates() {
        return this.zzb;
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.zza;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeParcelable(r5, 1, getStatus(), r6, false);
        SafeParcelWriter.writeParcelable(r5, 2, getLocationSettingsStates(), r6, false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }
}
