package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes5.dex */
public final class LocationSettingsStates extends AbstractSafeParcelable {
    public static final Parcelable.Creator<LocationSettingsStates> CREATOR = null;
    private final boolean zza;
    private final boolean zzb;
    private final boolean zzc;
    private final boolean zzd;
    private final boolean zze;
    private final boolean zzf;

    static {
        CREATOR = new zzai();
    }

    public LocationSettingsStates(boolean r1, boolean r2, boolean r3, boolean r4, boolean r5, boolean r6) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
        this.zzf = r6;
    }

    public boolean isBlePresent() {
        return this.zzf;
    }

    public boolean isBleUsable() {
        return this.zzc;
    }

    public boolean isGpsPresent() {
        return this.zzd;
    }

    public boolean isGpsUsable() {
        return this.zza;
    }

    public boolean isNetworkLocationPresent() {
        return this.zze;
    }

    public boolean isNetworkLocationUsable() {
        return this.zzb;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r3, int r4) {
        int r42 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeBoolean(r3, 1, isGpsUsable());
        SafeParcelWriter.writeBoolean(r3, 2, isNetworkLocationUsable());
        SafeParcelWriter.writeBoolean(r3, 3, isBleUsable());
        SafeParcelWriter.writeBoolean(r3, 4, isGpsPresent());
        SafeParcelWriter.writeBoolean(r3, 5, isNetworkLocationPresent());
        SafeParcelWriter.writeBoolean(r3, 6, isBlePresent());
        SafeParcelWriter.finishObjectHeader(r3, r42);
    }
}
