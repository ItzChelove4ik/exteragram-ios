package androidx.car.app.hardware.info;

import androidx.car.app.hardware.common.CarValue;
import java.util.List;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class Gyroscope {
    private final CarValue<List<Float>> mRotations;

    private Gyroscope() {
        this.mRotations = CarValue.UNKNOWN_FLOAT_LIST;
    }

    public Gyroscope(CarValue<List<Float>> r1) {
        Objects.requireNonNull(r1);
        this.mRotations = r1;
    }

    public boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof Gyroscope) == true) goto L10;
        return false;
    L10:
        return Objects.equals(this.mRotations, ((Gyroscope) r2).mRotations);
    }

    public CarValue<List<Float>> getRotations() {
        return this.mRotations;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mRotations});
    }

    public String toString() {
        return "[ rotations: " + this.mRotations + " ]";
    }
}
