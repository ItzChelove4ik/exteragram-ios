package androidx.car.app.model;

import androidx.car.app.model.constraints.CarColorConstraints;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class ForegroundCarColorSpan extends CarSpan {
    private final CarColor mCarColor;

    private ForegroundCarColorSpan() {
        this.mCarColor = CarColor.DEFAULT;
    }

    private ForegroundCarColorSpan(CarColor r1) {
        this.mCarColor = r1;
    }

    public static ForegroundCarColorSpan create(CarColor r1) {
        CarColorConstraints.UNCONSTRAINED.validateOrThrow(r1);
        Objects.requireNonNull(r1);
        return new ForegroundCarColorSpan(r1);
    }

    public boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof ForegroundCarColorSpan) == true) goto L10;
        return false;
    L10:
        return Objects.equals(this.mCarColor, ((ForegroundCarColorSpan) r2).mCarColor);
    }

    public CarColor getColor() {
        return this.mCarColor;
    }

    public int hashCode() {
        return Objects.hashCode(this.mCarColor);
    }

    public String toString() {
        return "[color: " + this.mCarColor + "]";
    }
}
