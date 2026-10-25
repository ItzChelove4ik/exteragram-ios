package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes5.dex */
public final class LatLngBounds extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<LatLngBounds> CREATOR = null;
    public final LatLng northeast;
    public final LatLng southwest;

    public static final class Builder {
        private double zza;
        private double zzb;
        private double zzc;
        private double zzd;

        public Builder() {
            this.zza = Double.POSITIVE_INFINITY;
            this.zzb = Double.NEGATIVE_INFINITY;
            this.zzc = Double.NaN;
            this.zzd = Double.NaN;
        }

        public LatLngBounds build() {
            Preconditions.checkState(!Double.isNaN(this.zzc), "no included points");
            return new LatLngBounds(new LatLng(this.zza, this.zzc), new LatLng(this.zzb, this.zzd));
        }

        public Builder include(LatLng r9) {
            Preconditions.checkNotNull(r9, "point must not be null");
            this.zza = Math.min(this.zza, r9.latitude);
            this.zzb = Math.max(this.zzb, r9.latitude);
            double r0 = r9.longitude;
            if (Double.isNaN(this.zzc) == false) goto L6;
            this.zzc = r0;
            this.zzd = r0;
            return this;
        L6:
            double r2 = this.zzc;
            double r4 = this.zzd;
            if (r2 > r4) goto L14;
            if (r2 <= r0) goto L11;
        L18:
            Parcelable.Creator<LatLngBounds> r92 = LatLngBounds.CREATOR;
            if ((((r2 - r0) + 360.0d) % 360.0d) >= (((r0 - r4) + 360.0d) % 360.0d)) goto L22;
            this.zzc = r0;
            return this;
        L22:
            this.zzd = r0;
        L23:
            return this;
        L11:
            if (r0 <= r4) goto L23;
        L14:
            if (r2 <= r0) goto L23;
            if (r0 > r4) goto L18;
            goto L18
        }
    }

    static {
        CREATOR = new zzm();
    }

    public LatLngBounds(LatLng r8, LatLng r9) {
        Preconditions.checkNotNull(r8, "southwest must not be null.");
        Preconditions.checkNotNull(r9, "northeast must not be null.");
        double r0 = r9.latitude;
        double r2 = r8.latitude;
        Object[] r4 = {Double.valueOf(r2), Double.valueOf(r9.latitude)};
        if (r0 < r2) goto L5;
        boolean r02 = true;
    L6:
        Preconditions.checkArgument(r02, "southern latitude exceeds northern latitude (%s > %s)", r4);
        this.southwest = r8;
        this.northeast = r9;
        return;
    L5:
        r02 = false;
        goto L6
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof LatLngBounds) == true) goto L8;
        return false;
    L8:
        LatLngBounds r52 = (LatLngBounds) r5;
        if (this.southwest.equals(r52.southwest) == true) goto L11;
    L13:
        return false;
    L11:
        if (this.northeast.equals(r52.northeast) == false) goto L13;
        return true;
    }

    public LatLng getCenter() {
        LatLng r0 = this.northeast;
        LatLng r11 = this.southwest;
        double r1 = r11.latitude + r0.latitude;
        double r3 = r0.longitude;
        double r5 = r11.longitude;
        if (r5 <= r3) goto L7;
        r3 = r3 + 360.0d;
    L7:
        return new LatLng(r1 / 2.0d, (r3 + r5) / 2.0d);
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.southwest, this.northeast});
    }

    public String toString() {
        return Objects.toStringHelper(this).add("southwest", this.southwest).add("northeast", this.northeast).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r5, int r6) {
        LatLng r0 = this.southwest;
        int r1 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeParcelable(r5, 2, r0, r6, false);
        SafeParcelWriter.writeParcelable(r5, 3, this.northeast, r6, false);
        SafeParcelWriter.finishObjectHeader(r5, r1);
    }
}
