package com.google.android.gms.location;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.internal.location.zzb;
import com.google.android.gms.internal.location.zzc;

/* loaded from: classes5.dex */
public abstract class zzu extends zzb implements zzv {
    public zzu() {
        super("com.google.android.gms.location.ILocationCallback");
    }

    public static zzv zzb(IBinder r2) {
        IInterface r0 = r2.queryLocalInterface("com.google.android.gms.location.ILocationCallback");
        if ((r0 instanceof zzv) == false) goto L7;
        return (zzv) r0;
    L7:
        return new zzt(r2);
    }

    @Override // com.google.android.gms.internal.location.zzb
    public final boolean zza(int r1, Parcel r2, Parcel r3, int r4) {
        if (r1 != 1) goto L5;
        LocationResult r12 = (LocationResult) zzc.zza(r2, LocationResult.CREATOR);
        zzc.zzd(r2);
        zzd(r12);
    L13:
        return true;
    L5:
        if (r1 != 2) goto L7;
        LocationAvailability r13 = (LocationAvailability) zzc.zza(r2, LocationAvailability.CREATOR);
        zzc.zzd(r2);
        zze(r13);
        goto L13
    L7:
        if (r1 == 3) goto L10;
        return false;
    L10:
        zzf();
        goto L13
    }
}
