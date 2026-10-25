package androidx.car.app.model;

import android.location.Location;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class CarLocation {
    private final double mLat;
    private final double mLng;

    private CarLocation() {
        this(0.0d, 0.0d);
    }

    private CarLocation(double r1, double r3) {
        this.mLat = r1;
        this.mLng = r3;
    }

    public static CarLocation create(double r1, double r3) {
        return new CarLocation(r1, r3);
    }

    public static CarLocation create(Location r4) {
        Objects.requireNonNull(r4);
        return create(r4.getLatitude(), r4.getLongitude());
    }

    public boolean equals(Object r8) {
        if (this != r8) goto L6;
        return true;
    L6:
        if ((r8 instanceof CarLocation) == true) goto L8;
        return false;
    L8:
        CarLocation r82 = (CarLocation) r8;
        if (Double.doubleToLongBits(this.mLat) == Double.doubleToLongBits(r82.mLat)) goto L11;
    L13:
        return false;
    L11:
        if (Double.doubleToLongBits(this.mLng) != Double.doubleToLongBits(r82.mLng)) goto L13;
        return true;
    }

    public double getLatitude() {
        return this.mLat;
    }

    public double getLongitude() {
        return this.mLng;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Double.valueOf(this.mLat), Double.valueOf(this.mLng)});
    }

    public String toString() {
        return "[" + getLatitude() + ", " + getLongitude() + "]";
    }
}
