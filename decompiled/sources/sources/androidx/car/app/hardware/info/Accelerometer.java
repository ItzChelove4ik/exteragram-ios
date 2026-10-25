package androidx.car.app.hardware.info;

import androidx.car.app.hardware.common.CarValue;
import java.util.List;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class Accelerometer {
    private final CarValue<List<Float>> mForces;

    private Accelerometer() {
        this.mForces = CarValue.UNKNOWN_FLOAT_LIST;
    }

    public Accelerometer(CarValue<List<Float>> r1) {
        Objects.requireNonNull(r1);
        this.mForces = r1;
    }

    public boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof Accelerometer) == true) goto L10;
        return false;
    L10:
        return Objects.equals(this.mForces, ((Accelerometer) r2).mForces);
    }

    public CarValue<List<Float>> getForces() {
        return this.mForces;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mForces});
    }

    public String toString() {
        return "[ forces: " + this.mForces + " ]";
    }
}
