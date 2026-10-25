package androidx.car.app.navigation.model;

import java.util.Objects;

/* loaded from: classes3.dex */
public final class LaneDirection {
    public static final int SHAPE_NORMAL_LEFT = 5;
    public static final int SHAPE_NORMAL_RIGHT = 6;
    public static final int SHAPE_SHARP_LEFT = 7;
    public static final int SHAPE_SHARP_RIGHT = 8;
    public static final int SHAPE_SLIGHT_LEFT = 3;
    public static final int SHAPE_SLIGHT_RIGHT = 4;
    public static final int SHAPE_STRAIGHT = 2;
    public static final int SHAPE_UNKNOWN = 1;
    public static final int SHAPE_U_TURN_LEFT = 9;
    public static final int SHAPE_U_TURN_RIGHT = 10;
    private final boolean mIsRecommended;
    private final int mShape;

    private LaneDirection() {
        this.mShape = 1;
        this.mIsRecommended = false;
    }

    private LaneDirection(int r1, boolean r2) {
        this.mShape = r1;
        this.mIsRecommended = r2;
    }

    public static LaneDirection create(int r1, boolean r2) {
        return new LaneDirection(r1, r2);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof LaneDirection) == true) goto L8;
        return false;
    L8:
        LaneDirection r52 = (LaneDirection) r5;
        if (this.mShape == r52.mShape) goto L11;
    L13:
        return false;
    L11:
        if (this.mIsRecommended != r52.mIsRecommended) goto L13;
        return true;
    }

    public int getShape() {
        return this.mShape;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(this.mShape), Boolean.valueOf(this.mIsRecommended)});
    }

    public boolean isRecommended() {
        return this.mIsRecommended;
    }

    public String toString() {
        return "[shape: " + this.mShape + ", isRecommended: " + this.mIsRecommended + "]";
    }
}
