package com.google.android.gms.internal.location;

import android.location.Location;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.api.internal.IStatusCallback;
import com.google.android.gms.location.LastLocationRequest;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationSettingsRequest;

/* loaded from: classes4.dex */
public final class zzt extends zza implements zzu {
    public zzt(IBinder r2) {
        super(r2, "com.google.android.gms.location.internal.IGoogleLocationManagerService");
    }

    @Override // com.google.android.gms.internal.location.zzu
    public final void zzD(LocationSettingsRequest r1, zzaa r2, String r3) {
        Parcel r32 = zza();
        zzc.zzb(r32, r1);
        zzc.zzc(r32, r2);
        r32.writeString(null);
        zzc(63, r32);
    }

    @Override // com.google.android.gms.internal.location.zzu
    public final void zzq(LastLocationRequest r2, zzed r3) {
        Parcel r0 = zza();
        zzc.zzb(r0, r2);
        zzc.zzb(r0, r3);
        zzc(90, r0);
    }

    @Override // com.google.android.gms.internal.location.zzu
    public final void zzr(LastLocationRequest r2, zzy r3) {
        Parcel r0 = zza();
        zzc.zzb(r0, r2);
        zzc.zzc(r0, r3);
        zzc(82, r0);
    }

    @Override // com.google.android.gms.internal.location.zzu
    public final Location zzs() {
        Parcel r2 = zzb(7, zza());
        Location r0 = (Location) zzc.zza(r2, Location.CREATOR);
        r2.recycle();
        return r0;
    }

    @Override // com.google.android.gms.internal.location.zzu
    public final void zzv(zzeh r2) {
        Parcel r0 = zza();
        zzc.zzb(r0, r2);
        zzc(59, r0);
    }

    @Override // com.google.android.gms.internal.location.zzu
    public final void zzw(zzed r2, LocationRequest r3, IStatusCallback r4) {
        Parcel r0 = zza();
        zzc.zzb(r0, r2);
        zzc.zzb(r0, r3);
        zzc.zzc(r0, r4);
        zzc(88, r0);
    }

    @Override // com.google.android.gms.internal.location.zzu
    public final void zzx(zzed r2, IStatusCallback r3) {
        Parcel r0 = zza();
        zzc.zzb(r0, r2);
        zzc.zzc(r0, r3);
        zzc(89, r0);
    }
}
