package com.google.android.gms.internal.location;

import android.os.Parcel;
import com.google.android.gms.common.api.ApiMetadata;

/* loaded from: classes4.dex */
public abstract class zzp extends zzb implements zzq {
    public zzp() {
        super("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
    }

    @Override // com.google.android.gms.internal.location.zzb
    public final boolean zza(int r1, Parcel r2, Parcel r3, int r4) {
        if (r1 != 1) goto L5;
        zzk r12 = (zzk) zzc.zza(r2, zzk.CREATOR);
        ApiMetadata r42 = (ApiMetadata) zzc.zza(r2, ApiMetadata.CREATOR);
        zzc.zzd(r2);
        zzd(r12, r42);
    L10:
        return true;
    L5:
        if (r1 == 2) goto L8;
        return false;
    L8:
        ApiMetadata r13 = (ApiMetadata) zzc.zza(r2, ApiMetadata.CREATOR);
        zzc.zzd(r2);
        zze(r13);
        goto L10
    }
}
