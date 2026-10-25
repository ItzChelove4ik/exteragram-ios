package androidx.car.app.hardware.info;

import androidx.car.app.hardware.common.CarValue;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class Speed {
    private final CarValue<Float> mDisplaySpeedMetersPerSecond;
    private final CarValue<Float> mRawSpeedMetersPerSecond;
    private final CarValue<Integer> mSpeedDisplayUnit;

    public static final class Builder {
        CarValue<Float> mDisplaySpeedMetersPerSecond;
        CarValue<Float> mRawSpeedMetersPerSecond;
        CarValue<Integer> mSpeedDisplayUnit;

        public Builder() {
            CarValue<Float> r0 = CarValue.UNKNOWN_FLOAT;
            this.mRawSpeedMetersPerSecond = r0;
            this.mDisplaySpeedMetersPerSecond = r0;
            this.mSpeedDisplayUnit = CarValue.UNKNOWN_INTEGER;
        }

        public Speed build() {
            return new Speed(this);
        }
    }

    private Speed() {
        CarValue<Float> r0 = CarValue.UNKNOWN_FLOAT;
        this.mRawSpeedMetersPerSecond = r0;
        this.mDisplaySpeedMetersPerSecond = r0;
        this.mSpeedDisplayUnit = CarValue.UNKNOWN_INTEGER;
    }

    public Speed(Builder r2) {
        CarValue<Float> r0 = r2.mRawSpeedMetersPerSecond;
        Objects.requireNonNull(r0);
        this.mRawSpeedMetersPerSecond = r0;
        CarValue<Float> r02 = r2.mDisplaySpeedMetersPerSecond;
        Objects.requireNonNull(r02);
        this.mDisplaySpeedMetersPerSecond = r02;
        CarValue<Integer> r22 = r2.mSpeedDisplayUnit;
        Objects.requireNonNull(r22);
        this.mSpeedDisplayUnit = r22;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof Speed) == true) goto L8;
        return false;
    L8:
        Speed r52 = (Speed) r5;
        if (Objects.equals(getRawSpeedMetersPerSecond(), r52.getRawSpeedMetersPerSecond()) == true) goto L11;
    L15:
        return false;
    L11:
        if (Objects.equals(getDisplaySpeedMetersPerSecond(), r52.getDisplaySpeedMetersPerSecond()) == false) goto L15;
        if (Objects.equals(this.mSpeedDisplayUnit, r52.mSpeedDisplayUnit) == false) goto L15;
        return true;
    }

    public CarValue<Float> getDisplaySpeedMetersPerSecond() {
        CarValue<Float> r0 = this.mDisplaySpeedMetersPerSecond;
        Objects.requireNonNull(r0);
        return r0;
    }

    public CarValue<Float> getRawSpeedMetersPerSecond() {
        CarValue<Float> r0 = this.mRawSpeedMetersPerSecond;
        Objects.requireNonNull(r0);
        return r0;
    }

    public CarValue<Integer> getSpeedDisplayUnit() {
        CarValue<Integer> r0 = this.mSpeedDisplayUnit;
        Objects.requireNonNull(r0);
        return r0;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{getRawSpeedMetersPerSecond(), getDisplaySpeedMetersPerSecond(), this.mSpeedDisplayUnit});
    }

    public String toString() {
        return "[ raw speed: " + getRawSpeedMetersPerSecond() + ", display speed: " + getDisplaySpeedMetersPerSecond() + ", speed display unit: " + this.mSpeedDisplayUnit + "]";
    }
}
