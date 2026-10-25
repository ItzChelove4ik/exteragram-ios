package com.google.android.gms.internal.location;

import android.location.Location;
import android.os.Parcel;
import com.google.android.gms.common.api.Status;

/* loaded from: classes4.dex */
public abstract class zzx extends zzb implements zzy {
    public zzx() {
        super("com.google.android.gms.location.internal.ILocationStatusCallback");
    }

    @Override // com.google.android.gms.internal.location.zzb
    public final boolean zza(int r1, Parcel r2, Parcel r3, int r4) {
        if (r1 != 1) goto L6;
        Status r12 = (Status) zzc.zza(r2, Status.CREATOR);
        Location r42 = (Location) zzc.zza(r2, Location.CREATOR);
        zzc.zzd(r2);
        zzb(r12, r42);
        return true;
    L6:
        return false;
    }
}
