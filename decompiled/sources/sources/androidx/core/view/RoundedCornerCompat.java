package androidx.core.view;

import android.graphics.Point;
import android.view.RoundedCorner;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
public final class RoundedCornerCompat {
    private final Point mCenter;
    private final int mPosition;
    private final int mRadius;

    public RoundedCornerCompat(int r1, int r2, int r3, int r4) {
        this.mPosition = r1;
        this.mRadius = r2;
        this.mCenter = new Point(r3, r4);
    }

    private RoundedCornerCompat(int r2, int r3, Point r4) {
        this(r2, r3, r4.x, r4.y);
    }

    private String getPositionString(int r1) {
        if (r1 != 0) goto L4;
        return "TopLeft";
    L4:
        if (r1 != 1) goto L6;
        return "TopRight";
    L6:
        if (r1 != 2) goto L8;
        return "BottomRight";
    L8:
        if (r1 == 3) goto L11;
        return "Invalid";
    L11:
        return "BottomLeft";
    }

    private static int toCompatPosition(int r1) {
        if (r1 == 0) goto L13;
        int r0 = 1;
        if (r1 == 1) goto L12;
        r0 = 2;
        if (r1 == 2) goto L12;
        if (r1 != 3) goto L10;
        return 3;
    L10:
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Invalid position: ", r1);
        return 0;
    L12:
        return r0;
    L13:
        return 0;
    }

    public static RoundedCornerCompat toRoundedCornerCompat(RoundedCorner r3) {
        if (r3 != null) goto L4;
        return null;
    L4:
        return new RoundedCornerCompat(toCompatPosition(r3.getPosition()), r3.getRadius(), r3.getCenter());
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof RoundedCornerCompat) == false) goto L14;
        RoundedCornerCompat r52 = (RoundedCornerCompat) r5;
        if (this.mPosition != r52.mPosition) goto L14;
        if (this.mRadius != r52.mRadius) goto L14;
        if (this.mCenter.equals(r52.mCenter) == false) goto L14;
        return true;
    L14:
        return false;
    }

    public int getRadius() {
        return this.mRadius;
    }

    public int hashCode() {
        return (((this.mPosition * 31) + this.mRadius) * 31) + this.mCenter.hashCode();
    }

    public String toString() {
        return "RoundedCornerCompat{position=" + getPositionString(this.mPosition) + ", radius=" + this.mRadius + ", center=" + this.mCenter + '}';
    }
}
