package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes5.dex */
public final class LatLng extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<LatLng> CREATOR = null;
    public final double latitude;
    public final double longitude;

    static {
        CREATOR = new zzn();
    }

    public LatLng(double r5, double r7) {
        if (r7 >= (-180.0d)) goto L5;
    L7:
        this.longitude = ((((r7 - 180.0d) % 360.0d) + 360.0d) % 360.0d) - 180.0d;
    L8:
        this.latitude = Math.max(-90.0d, Math.min(90.0d, r5));
        return;
    L5:
        if (r7 >= 180.0d) goto L7;
        this.longitude = r7;
        goto L8
    }

    public boolean equals(Object r8) {
        if (this != r8) goto L6;
        return true;
    L6:
        if ((r8 instanceof LatLng) == true) goto L8;
        return false;
    L8:
        LatLng r82 = (LatLng) r8;
        if (Double.doubleToLongBits(this.latitude) == Double.doubleToLongBits(r82.latitude)) goto L11;
    L13:
        return false;
    L11:
        if (Double.doubleToLongBits(this.longitude) != Double.doubleToLongBits(r82.longitude)) goto L13;
        return true;
    }

    public int hashCode() {
        long r0 = Double.doubleToLongBits(this.latitude);
        long r02 = r0 ^ (r0 >>> 32);
        long r3 = Double.doubleToLongBits(this.longitude);
        return ((((int) r02) + 31) * 31) + ((int) (r3 ^ (r3 >>> 32)));
    }

    public String toString() {
        return "lat/lng: (" + this.latitude + "," + this.longitude + ")";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r4, int r5) {
        double r0 = this.latitude;
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeDouble(r4, 2, r0);
        SafeParcelWriter.writeDouble(r4, 3, this.longitude);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }
}
