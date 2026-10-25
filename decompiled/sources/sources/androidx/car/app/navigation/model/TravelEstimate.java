package androidx.car.app.navigation.model;

import androidx.car.app.model.CarColor;
import androidx.car.app.model.CarIcon;
import androidx.car.app.model.CarText;
import androidx.car.app.model.DateTimeWithZone;
import androidx.car.app.model.Distance;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class TravelEstimate {
    public static final long REMAINING_TIME_UNKNOWN = -1;
    private final DateTimeWithZone mArrivalTimeAtDestination;
    private final Distance mRemainingDistance;
    private final CarColor mRemainingDistanceColor;
    private final CarColor mRemainingTimeColor;
    private final long mRemainingTimeSeconds;
    private final CarIcon mTripIcon;
    private final CarText mTripText;

    public static final class Builder {
    }

    private TravelEstimate() {
        this.mRemainingDistance = null;
        this.mRemainingTimeSeconds = 0;
        this.mArrivalTimeAtDestination = null;
        CarColor r1 = CarColor.DEFAULT;
        this.mRemainingTimeColor = r1;
        this.mRemainingDistanceColor = r1;
        this.mTripText = null;
        this.mTripIcon = null;
    }

    public TravelEstimate(Builder r1) {
        throw null;
    }

    public boolean equals(Object r8) {
        if (this != r8) goto L6;
        return true;
    L6:
        if ((r8 instanceof TravelEstimate) == true) goto L8;
        return false;
    L8:
        TravelEstimate r82 = (TravelEstimate) r8;
        if (Objects.equals(this.mRemainingDistance, r82.mRemainingDistance) == true) goto L11;
    L23:
        return false;
    L11:
        if (this.mRemainingTimeSeconds != r82.mRemainingTimeSeconds) goto L23;
        if (Objects.equals(this.mArrivalTimeAtDestination, r82.mArrivalTimeAtDestination) == false) goto L23;
        if (Objects.equals(this.mRemainingTimeColor, r82.mRemainingTimeColor) == false) goto L23;
        if (Objects.equals(this.mRemainingDistanceColor, r82.mRemainingDistanceColor) == false) goto L23;
        if (Objects.equals(this.mTripText, r82.mTripText) == false) goto L23;
        if (Objects.equals(this.mTripIcon, r82.mTripIcon) == false) goto L23;
        return true;
    }

    public DateTimeWithZone getArrivalTimeAtDestination() {
        return this.mArrivalTimeAtDestination;
    }

    public Distance getRemainingDistance() {
        return this.mRemainingDistance;
    }

    public CarColor getRemainingDistanceColor() {
        return this.mRemainingDistanceColor;
    }

    public CarColor getRemainingTimeColor() {
        return this.mRemainingTimeColor;
    }

    public long getRemainingTimeSeconds() {
        long r0 = this.mRemainingTimeSeconds;
        if (r0 < 0) goto L5;
        return r0;
    L5:
        return -1;
    }

    public CarIcon getTripIcon() {
        return this.mTripIcon;
    }

    public CarText getTripText() {
        return this.mTripText;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mRemainingDistance, Long.valueOf(this.mRemainingTimeSeconds), this.mArrivalTimeAtDestination, this.mRemainingTimeColor, this.mRemainingDistanceColor, this.mTripText, this.mTripIcon});
    }

    public String toString() {
        return "[ remaining distance: " + this.mRemainingDistance + ", time (s): " + this.mRemainingTimeSeconds + ", ETA: " + this.mArrivalTimeAtDestination + "]";
    }
}
