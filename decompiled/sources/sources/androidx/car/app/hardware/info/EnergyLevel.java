package androidx.car.app.hardware.info;

import androidx.car.app.hardware.common.CarValue;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class EnergyLevel {
    private final CarValue<Float> mBatteryPercent;
    private final CarValue<Integer> mDistanceDisplayUnit;
    private final CarValue<Boolean> mEnergyIsLow;
    private final CarValue<Float> mFuelPercent;
    private final CarValue<Integer> mFuelVolumeDisplayUnit;
    private final CarValue<Float> mRangeRemainingMeters;

    public static final class Builder {
        CarValue<Float> mBatteryPercent;
        CarValue<Integer> mDistanceDisplayUnit;
        CarValue<Boolean> mEnergyIsLow;
        CarValue<Float> mFuelPercent;
        CarValue<Integer> mFuelVolumeDisplayUnit;
        CarValue<Float> mRangeRemainingMeters;

        public Builder() {
            CarValue<Float> r0 = CarValue.UNKNOWN_FLOAT;
            this.mBatteryPercent = r0;
            this.mFuelPercent = r0;
            this.mEnergyIsLow = CarValue.UNKNOWN_BOOLEAN;
            this.mRangeRemainingMeters = r0;
            CarValue<Integer> r02 = CarValue.UNKNOWN_INTEGER;
            this.mDistanceDisplayUnit = r02;
            this.mFuelVolumeDisplayUnit = r02;
        }

        public EnergyLevel build() {
            return new EnergyLevel(this);
        }
    }

    private EnergyLevel() {
        CarValue<Float> r0 = CarValue.UNKNOWN_FLOAT;
        this.mBatteryPercent = r0;
        this.mFuelPercent = r0;
        this.mEnergyIsLow = CarValue.UNKNOWN_BOOLEAN;
        this.mRangeRemainingMeters = r0;
        CarValue<Integer> r02 = CarValue.UNKNOWN_INTEGER;
        this.mDistanceDisplayUnit = r02;
        this.mFuelVolumeDisplayUnit = r02;
    }

    public EnergyLevel(Builder r2) {
        CarValue<Float> r0 = r2.mBatteryPercent;
        Objects.requireNonNull(r0);
        this.mBatteryPercent = r0;
        CarValue<Float> r02 = r2.mFuelPercent;
        Objects.requireNonNull(r02);
        this.mFuelPercent = r02;
        CarValue<Boolean> r03 = r2.mEnergyIsLow;
        Objects.requireNonNull(r03);
        this.mEnergyIsLow = r03;
        CarValue<Float> r04 = r2.mRangeRemainingMeters;
        Objects.requireNonNull(r04);
        this.mRangeRemainingMeters = r04;
        CarValue<Integer> r05 = r2.mDistanceDisplayUnit;
        Objects.requireNonNull(r05);
        this.mDistanceDisplayUnit = r05;
        CarValue<Integer> r22 = r2.mFuelVolumeDisplayUnit;
        Objects.requireNonNull(r22);
        this.mFuelVolumeDisplayUnit = r22;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof EnergyLevel) == true) goto L8;
        return false;
    L8:
        EnergyLevel r52 = (EnergyLevel) r5;
        if (Objects.equals(this.mBatteryPercent, r52.mBatteryPercent) == true) goto L11;
    L21:
        return false;
    L11:
        if (Objects.equals(this.mFuelPercent, r52.mFuelPercent) == false) goto L21;
        if (Objects.equals(this.mEnergyIsLow, r52.mEnergyIsLow) == false) goto L21;
        if (Objects.equals(getRangeRemainingMeters(), r52.getRangeRemainingMeters()) == false) goto L21;
        if (Objects.equals(this.mDistanceDisplayUnit, r52.mDistanceDisplayUnit) == false) goto L21;
        if (Objects.equals(this.mFuelVolumeDisplayUnit, r52.mFuelVolumeDisplayUnit) == false) goto L21;
        return true;
    }

    public CarValue<Float> getBatteryPercent() {
        CarValue<Float> r0 = this.mBatteryPercent;
        Objects.requireNonNull(r0);
        return r0;
    }

    public CarValue<Integer> getDistanceDisplayUnit() {
        CarValue<Integer> r0 = this.mDistanceDisplayUnit;
        Objects.requireNonNull(r0);
        return r0;
    }

    public CarValue<Boolean> getEnergyIsLow() {
        CarValue<Boolean> r0 = this.mEnergyIsLow;
        Objects.requireNonNull(r0);
        return r0;
    }

    public CarValue<Float> getFuelPercent() {
        CarValue<Float> r0 = this.mFuelPercent;
        Objects.requireNonNull(r0);
        return r0;
    }

    public CarValue<Integer> getFuelVolumeDisplayUnit() {
        CarValue<Integer> r0 = this.mFuelVolumeDisplayUnit;
        Objects.requireNonNull(r0);
        return r0;
    }

    public CarValue<Float> getRangeRemainingMeters() {
        CarValue<Float> r0 = this.mRangeRemainingMeters;
        Objects.requireNonNull(r0);
        return r0;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mBatteryPercent, this.mFuelPercent, this.mEnergyIsLow, getRangeRemainingMeters(), this.mDistanceDisplayUnit, this.mFuelVolumeDisplayUnit});
    }

    public String toString() {
        return "[ battery percent: " + this.mBatteryPercent + ", fuel percent: " + this.mFuelPercent + ", energyIsLow: " + this.mEnergyIsLow + ", range remaining: " + getRangeRemainingMeters() + ", distance display unit: " + this.mDistanceDisplayUnit + ", fuel volume display unit: " + this.mFuelVolumeDisplayUnit + "]";
    }
}
