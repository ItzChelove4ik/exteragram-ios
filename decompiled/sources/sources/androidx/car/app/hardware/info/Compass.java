package androidx.car.app.hardware.info;

import androidx.car.app.hardware.common.CarValue;
import java.util.List;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class Compass {
    private final CarValue<List<Float>> mOrientations;

    private Compass() {
        this.mOrientations = CarValue.UNKNOWN_FLOAT_LIST;
    }

    public Compass(CarValue<List<Float>> r1) {
        Objects.requireNonNull(r1);
        this.mOrientations = r1;
    }

    public boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof Compass) == true) goto L10;
        return false;
    L10:
        return Objects.equals(this.mOrientations, ((Compass) r2).mOrientations);
    }

    public CarValue<List<Float>> getOrientations() {
        return this.mOrientations;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mOrientations});
    }

    public String toString() {
        return "[ orientations: " + this.mOrientations + " ]";
    }
}
