package androidx.car.app.model;

import java.util.Objects;

/* loaded from: classes3.dex */
public final class DistanceSpan extends CarSpan {
    private final Distance mDistance;

    private DistanceSpan() {
        this.mDistance = null;
    }

    private DistanceSpan(Distance r1) {
        this.mDistance = r1;
    }

    public static DistanceSpan create(Distance r1) {
        Objects.requireNonNull(r1);
        return new DistanceSpan(r1);
    }

    public boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof DistanceSpan) == true) goto L10;
        return false;
    L10:
        return Objects.equals(this.mDistance, ((DistanceSpan) r2).mDistance);
    }

    public Distance getDistance() {
        Distance r0 = this.mDistance;
        Objects.requireNonNull(r0);
        return r0;
    }

    public int hashCode() {
        return Objects.hashCode(this.mDistance);
    }

    public String toString() {
        return "[distance: " + this.mDistance + "]";
    }
}
