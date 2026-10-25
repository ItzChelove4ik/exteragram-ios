package com.google.android.gms.internal.location;

import android.os.Parcel;
import com.google.android.gms.common.api.ApiMetadata;
import com.google.android.gms.location.LocationSettingsResult;

/* loaded from: classes4.dex */
public abstract class zzz extends zzb implements zzaa {
    public zzz() {
        super("com.google.android.gms.location.internal.ISettingsCallbacks");
    }

    @Override // com.google.android.gms.internal.location.zzb
    public final boolean zza(int r1, Parcel r2, Parcel r3, int r4) {
        if (r1 != 1) goto L6;
        LocationSettingsResult r12 = (LocationSettingsResult) zzc.zza(r2, LocationSettingsResult.CREATOR);
        ApiMetadata r42 = (ApiMetadata) zzc.zza(r2, ApiMetadata.CREATOR);
        zzc.zzd(r2);
        zzb(r12, r42);
        return true;
    L6:
        return false;
    }
}
