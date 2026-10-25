package androidx.car.app.hardware.info;

import androidx.car.app.hardware.common.CarValue;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class Model {
    private final CarValue<String> mManufacturer;
    private final CarValue<String> mName;
    private final CarValue<Integer> mYear;

    public static final class Builder {
        CarValue<String> mManufacturer;
        CarValue<String> mName;
        CarValue<Integer> mYear;

        public Builder() {
            CarValue<String> r0 = CarValue.UNKNOWN_STRING;
            this.mName = r0;
            this.mYear = CarValue.UNKNOWN_INTEGER;
            this.mManufacturer = r0;
        }

        public Model build() {
            return new Model(this);
        }
    }

    private Model() {
        CarValue<String> r0 = CarValue.UNKNOWN_STRING;
        this.mName = r0;
        this.mManufacturer = r0;
        this.mYear = CarValue.UNKNOWN_INTEGER;
    }

    public Model(Builder r2) {
        CarValue<String> r0 = r2.mName;
        Objects.requireNonNull(r0);
        this.mName = r0;
        CarValue<String> r02 = r2.mManufacturer;
        Objects.requireNonNull(r02);
        this.mManufacturer = r02;
        CarValue<Integer> r22 = r2.mYear;
        Objects.requireNonNull(r22);
        this.mYear = r22;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof Model) == true) goto L8;
        return false;
    L8:
        Model r52 = (Model) r5;
        if (Objects.equals(this.mName, r52.mName) == true) goto L11;
    L15:
        return false;
    L11:
        if (Objects.equals(this.mYear, r52.mYear) == false) goto L15;
        if (Objects.equals(this.mManufacturer, r52.mManufacturer) == false) goto L15;
        return true;
    }

    public CarValue<String> getManufacturer() {
        CarValue<String> r0 = this.mManufacturer;
        Objects.requireNonNull(r0);
        return r0;
    }

    public CarValue<String> getName() {
        CarValue<String> r0 = this.mName;
        Objects.requireNonNull(r0);
        return r0;
    }

    public CarValue<Integer> getYear() {
        CarValue<Integer> r0 = this.mYear;
        Objects.requireNonNull(r0);
        return r0;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mName, this.mYear, this.mManufacturer});
    }

    public String toString() {
        return "[ name: " + this.mName + ", year: " + this.mYear + ", manufacturer: " + this.mManufacturer + "]";
    }
}
