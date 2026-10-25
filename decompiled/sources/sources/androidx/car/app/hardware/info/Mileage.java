package androidx.car.app.hardware.info;

import androidx.car.app.hardware.common.CarValue;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class Mileage {
    private final CarValue<Integer> mDistanceDisplayUnit;
    private final CarValue<Float> mOdometerMeters;

    public static final class Builder {
        CarValue<Integer> mDistanceDisplayUnit;
        CarValue<Float> mOdometerMeters;

        public Builder() {
            this.mOdometerMeters = CarValue.UNKNOWN_FLOAT;
            this.mDistanceDisplayUnit = CarValue.UNKNOWN_INTEGER;
        }

        public Mileage build() {
            return new Mileage(this);
        }
    }

    private Mileage() {
        this.mOdometerMeters = CarValue.UNKNOWN_FLOAT;
        this.mDistanceDisplayUnit = CarValue.UNKNOWN_INTEGER;
    }

    public Mileage(Builder r2) {
        CarValue<Float> r0 = r2.mOdometerMeters;
        Objects.requireNonNull(r0);
        this.mOdometerMeters = r0;
        CarValue<Integer> r22 = r2.mDistanceDisplayUnit;
        Objects.requireNonNull(r22);
        this.mDistanceDisplayUnit = r22;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof Mileage) == true) goto L8;
        return false;
    L8:
        Mileage r52 = (Mileage) r5;
        if (Objects.equals(getOdometerMeters(), r52.getOdometerMeters()) == true) goto L11;
    L13:
        return false;
    L11:
        if (Objects.equals(this.mDistanceDisplayUnit, r52.mDistanceDisplayUnit) == false) goto L13;
        return true;
    }

    public CarValue<Integer> getDistanceDisplayUnit() {
        CarValue<Integer> r0 = this.mDistanceDisplayUnit;
        Objects.requireNonNull(r0);
        return r0;
    }

    public CarValue<Float> getOdometerMeters() {
        CarValue<Float> r0 = this.mOdometerMeters;
        Objects.requireNonNull(r0);
        return r0;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{getOdometerMeters(), this.mDistanceDisplayUnit});
    }

    public String toString() {
        return "[ odometer: " + getOdometerMeters() + ", distance display unit: " + this.mDistanceDisplayUnit + "]";
    }
}
