package androidx.car.app.hardware.info;

import android.location.Location;
import androidx.car.app.hardware.common.CarValue;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class CarHardwareLocation {
    public static final CarValue<Location> UNIMPLEMENTED_LOCATION = null;
    public static final CarValue<Location> UNKNOWN_LOCATION = null;
    private final CarValue<Location> mLocation;

    static {
        UNIMPLEMENTED_LOCATION = new CarValue(null, 0, 2);
        UNKNOWN_LOCATION = new CarValue(null, 0, 0);
    }

    private CarHardwareLocation() {
        this.mLocation = UNKNOWN_LOCATION;
    }

    public CarHardwareLocation(CarValue<Location> r1) {
        Objects.requireNonNull(r1);
        this.mLocation = r1;
    }

    public boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof CarHardwareLocation) == true) goto L10;
        return false;
    L10:
        return Objects.equals(this.mLocation, ((CarHardwareLocation) r2).mLocation);
    }

    public CarValue<Location> getLocation() {
        return this.mLocation;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mLocation});
    }

    public String toString() {
        return "[ location: " + this.mLocation + " ]";
    }
}
