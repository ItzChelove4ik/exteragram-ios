package com.google.android.gms.cast.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.api.ApiMetadata;

/* loaded from: classes4.dex */
public abstract class zzaf extends com.google.android.gms.internal.cast.zzb implements zzag {
    public zzaf() {
        super("com.google.android.gms.cast.internal.IBundleCallback");
    }

    @Override // com.google.android.gms.internal.cast.zzb
    public final boolean zza(int r1, Parcel r2, Parcel r3, int r4) {
        if (r1 != 1) goto L6;
        Bundle r12 = (Bundle) com.google.android.gms.internal.cast.zzc.zzb(r2, Bundle.CREATOR);
        ApiMetadata r42 = (ApiMetadata) com.google.android.gms.internal.cast.zzc.zzb(r2, ApiMetadata.CREATOR);
        com.google.android.gms.internal.cast.zzc.zzf(r2);
        zzb(r12, r42);
        return true;
    L6:
        return false;
    }
}
