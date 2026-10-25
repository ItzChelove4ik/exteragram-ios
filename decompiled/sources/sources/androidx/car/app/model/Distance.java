package androidx.car.app.model;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.Locale;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class Distance {
    public static final int UNIT_FEET = 6;
    public static final int UNIT_KILOMETERS = 2;
    public static final int UNIT_KILOMETERS_P1 = 3;
    public static final int UNIT_METERS = 1;
    public static final int UNIT_MILES = 4;
    public static final int UNIT_MILES_P1 = 5;
    public static final int UNIT_YARDS = 7;
    private final double mDisplayDistance;
    private final int mDisplayUnit;

    private Distance() {
        this.mDisplayDistance = 0.0d;
        this.mDisplayUnit = 1;
    }

    private Distance(double r1, int r3) {
        this.mDisplayDistance = r1;
        this.mDisplayUnit = r3;
    }

    public static Distance create(double r2, int r4) {
        if (r2 >= 0.0d) goto L5;
        f$$ExternalSyntheticBUOutline1.m("displayDistance must be a positive value");
        return null;
    L5:
        return new Distance(r2, r4);
    }

    private static String unitToString(int r0) {
        switch(r0) {
            case 1: goto L17;
            case 2: goto L15;
            case 3: goto L13;
            case 4: goto L11;
            case 5: goto L9;
            case 6: goto L7;
            case 7: goto L5;
            default: goto L3;
        };
    L3:
        return "?";
    L5:
        return "yd";
    L7:
        return "ft";
    L9:
        return "mi_p1";
    L11:
        return "mi";
    L13:
        return "km_p1";
    L15:
        return "km";
    L17:
        return "m";
    }

    public boolean equals(Object r6) {
        if (this != r6) goto L6;
        return true;
    L6:
        if ((r6 instanceof Distance) == true) goto L8;
        return false;
    L8:
        Distance r62 = (Distance) r6;
        if (this.mDisplayUnit == r62.mDisplayUnit) goto L11;
    L13:
        return false;
    L11:
        if (this.mDisplayDistance != r62.mDisplayDistance) goto L13;
        return true;
    }

    public double getDisplayDistance() {
        return this.mDisplayDistance;
    }

    public int getDisplayUnit() {
        return this.mDisplayUnit;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Double.valueOf(this.mDisplayDistance), Integer.valueOf(this.mDisplayUnit)});
    }

    public String toString() {
        return String.format(Locale.US, "%.04f%s", new Object[]{Double.valueOf(this.mDisplayDistance), unitToString(this.mDisplayUnit)});
    }
}
