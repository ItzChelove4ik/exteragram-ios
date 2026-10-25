package androidx.car.app.model;

import androidx.car.app.model.constraints.CarIconConstraints;
import com.google.gson.JsonArray$$ExternalSyntheticBUOutline0;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class CarIconSpan extends CarSpan {
    public static final int ALIGN_BASELINE = 1;
    public static final int ALIGN_BOTTOM = 0;
    public static final int ALIGN_CENTER = 2;
    private final int mAlignment;
    private final CarIcon mIcon;

    private CarIconSpan() {
        this.mIcon = null;
        this.mAlignment = 1;
    }

    private CarIconSpan(CarIcon r1, int r2) {
        this.mIcon = r1;
        this.mAlignment = r2;
    }

    private static String alignmentToString(int r1) {
        if (r1 != 0) goto L4;
        return "bottom";
    L4:
        if (r1 != 1) goto L6;
        return "baseline";
    L6:
        if (r1 == 2) goto L9;
        return "unknown";
    L9:
        return "center";
    }

    public static CarIconSpan create(CarIcon r1) {
        return create(r1, 1);
    }

    public static CarIconSpan create(CarIcon r1, int r2) {
        CarIconConstraints.DEFAULT.validateOrThrow(r1);
        if (r2 == 1) goto L10;
        if (r2 == 0) goto L10;
        if (r2 == 2) goto L10;
        JsonArray$$ExternalSyntheticBUOutline0.m("Invalid alignment value: ", r2);
        return null;
    L10:
        Objects.requireNonNull(r1);
        return new CarIconSpan(r1, r2);
    }

    public boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof CarIconSpan) == true) goto L10;
        return false;
    L10:
        return Objects.equals(this.mIcon, ((CarIconSpan) r2).mIcon);
    }

    public int getAlignment() {
        return this.mAlignment;
    }

    public CarIcon getIcon() {
        CarIcon r0 = this.mIcon;
        Objects.requireNonNull(r0);
        return r0;
    }

    public int hashCode() {
        return Objects.hashCode(this.mIcon);
    }

    public String toString() {
        return "[icon: " + this.mIcon + ", alignment: " + alignmentToString(this.mAlignment) + "]";
    }
}
