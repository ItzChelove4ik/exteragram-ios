package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes5.dex */
public final class CameraPosition extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<CameraPosition> CREATOR = null;
    public final float bearing;
    public final LatLng target;
    public final float tilt;
    public final float zoom;

    static {
        CREATOR = new zza();
    }

    public CameraPosition(LatLng r5, float r6, float r7, float r8) {
        Preconditions.checkNotNull(r5, "camera target must not be null.");
        boolean r2 = false;
        if (r7 >= 0.0f) goto L5;
    L7:
        Preconditions.checkArgument(r2, "Tilt needs to be between 0 and 90 inclusive: %s", new Object[]{Float.valueOf(r7)});
        this.target = r5;
        this.zoom = r6;
        this.tilt = r7 + 0.0f;
        if (r8 > 0.0d) goto L10;
        r8 = (r8 % 360.0f) + 360.0f;
    L10:
        this.bearing = r8 % 360.0f;
        return;
    L5:
        if (r7 > 90.0f) goto L7;
        r2 = true;
        goto L7
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof CameraPosition) == true) goto L8;
        return false;
    L8:
        CameraPosition r52 = (CameraPosition) r5;
        if (this.target.equals(r52.target) == true) goto L11;
    L17:
        return false;
    L11:
        if (Float.floatToIntBits(this.zoom) != Float.floatToIntBits(r52.zoom)) goto L17;
        if (Float.floatToIntBits(this.tilt) != Float.floatToIntBits(r52.tilt)) goto L17;
        if (Float.floatToIntBits(this.bearing) != Float.floatToIntBits(r52.bearing)) goto L17;
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.target, Float.valueOf(this.zoom), Float.valueOf(this.tilt), Float.valueOf(this.bearing)});
    }

    public String toString() {
        return Objects.toStringHelper(this).add("target", this.target).add("zoom", Float.valueOf(this.zoom)).add("tilt", Float.valueOf(this.tilt)).add("bearing", Float.valueOf(this.bearing)).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r5, int r6) {
        LatLng r0 = this.target;
        int r1 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeParcelable(r5, 2, r0, r6, false);
        SafeParcelWriter.writeFloat(r5, 3, this.zoom);
        SafeParcelWriter.writeFloat(r5, 4, this.tilt);
        SafeParcelWriter.writeFloat(r5, 5, this.bearing);
        SafeParcelWriter.finishObjectHeader(r5, r1);
    }
}
