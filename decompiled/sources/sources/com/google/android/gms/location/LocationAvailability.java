package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes5.dex */
public final class LocationAvailability extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<LocationAvailability> CREATOR = null;
    final int zza;

    static {
        new LocationAvailability(0, true);
        new LocationAvailability(1000, false);
        CREATOR = new zzaa();
    }

    @Deprecated
    public LocationAvailability(int r1, boolean r2) {
        this.zza = r1;
    }

    public boolean equals(Object r3) {
        if ((r3 instanceof LocationAvailability) == true) goto L5;
    L8:
        return false;
    L5:
        if (this.zza != ((LocationAvailability) r3).zza) goto L8;
        return true;
    }

    public int hashCode() {
        return this.zza;
    }

    public boolean isLocationAvailable() {
        if (this.zza >= 1000) goto L6;
        return true;
    L6:
        return false;
    }

    public String toString() {
        boolean r2 = isLocationAvailable();
        StringBuilder r1 = new StringBuilder(String.valueOf(r2).length() + 22);
        r1.append("LocationAvailability[");
        r1.append(r2);
        r1.append("]");
        return r1.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r3, int r4) {
        int r42 = this.zza;
        int r0 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeInt(r3, 4, r42);
        SafeParcelWriter.writeBoolean(r3, 6, isLocationAvailable());
        SafeParcelWriter.finishObjectHeader(r3, r0);
    }
}
