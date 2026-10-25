package com.google.android.gms.internal.cast;

import android.os.Parcel;
import com.google.android.gms.common.api.ApiMetadata;

/* loaded from: classes4.dex */
public abstract class zzfa extends zzb implements zzfb {
    public zzfa() {
        super("com.google.android.gms.cast.remote_display.ICastRemoteDisplaySessionCallbacks");
    }

    @Override // com.google.android.gms.internal.cast.zzb
    public final boolean zza(int r1, Parcel r2, Parcel r3, int r4) {
        if (r1 != 1) goto L6;
        int r12 = r2.readInt();
        ApiMetadata r42 = (ApiMetadata) zzc.zzb(r2, ApiMetadata.CREATOR);
        zzc.zzf(r2);
        zzb(r12, r42);
        return true;
    L6:
        return false;
    }
}
