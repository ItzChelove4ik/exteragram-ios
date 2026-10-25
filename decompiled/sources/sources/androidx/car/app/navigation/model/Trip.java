package androidx.car.app.navigation.model;

import androidx.car.app.model.CarText;
import androidx.car.app.utils.CollectionUtils;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class Trip {
    private final CarText mCurrentRoad;
    private final List<TravelEstimate> mDestinationTravelEstimates;
    private final List<Destination> mDestinations;
    private final boolean mIsLoading;
    private final List<TravelEstimate> mStepTravelEstimates;
    private final List<Step> mSteps;

    public static final class Builder {
    }

    private Trip() {
        List r0 = Collections.EMPTY_LIST;
        this.mDestinations = r0;
        this.mSteps = r0;
        this.mDestinationTravelEstimates = r0;
        this.mStepTravelEstimates = r0;
        this.mCurrentRoad = null;
        this.mIsLoading = false;
    }

    public Trip(Builder r1) {
        throw null;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof Trip) == true) goto L8;
        return false;
    L8:
        Trip r52 = (Trip) r5;
        if (Objects.equals(this.mDestinations, r52.mDestinations) == true) goto L11;
    L21:
        return false;
    L11:
        if (Objects.equals(this.mSteps, r52.mSteps) == false) goto L21;
        if (Objects.equals(this.mDestinationTravelEstimates, r52.mDestinationTravelEstimates) == false) goto L21;
        if (Objects.equals(this.mStepTravelEstimates, r52.mStepTravelEstimates) == false) goto L21;
        if (Objects.equals(this.mCurrentRoad, r52.mCurrentRoad) == false) goto L21;
        if (Boolean.valueOf(this.mIsLoading).equals(Boolean.valueOf(r52.mIsLoading)) == false) goto L21;
        return true;
    }

    public CarText getCurrentRoad() {
        return this.mCurrentRoad;
    }

    public List<TravelEstimate> getDestinationTravelEstimates() {
        return CollectionUtils.emptyIfNull(this.mDestinationTravelEstimates);
    }

    public List<Destination> getDestinations() {
        return CollectionUtils.emptyIfNull(this.mDestinations);
    }

    public List<TravelEstimate> getStepTravelEstimates() {
        return CollectionUtils.emptyIfNull(this.mStepTravelEstimates);
    }

    public List<Step> getSteps() {
        return CollectionUtils.emptyIfNull(this.mSteps);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mDestinations, this.mSteps, this.mDestinationTravelEstimates, this.mStepTravelEstimates, this.mCurrentRoad});
    }

    public boolean isLoading() {
        return this.mIsLoading;
    }

    public String toString() {
        return "[ destinations : " + this.mDestinations.toString() + ", steps: " + this.mSteps.toString() + ", dest estimates: " + this.mDestinationTravelEstimates.toString() + ", step estimates: " + this.mStepTravelEstimates.toString() + ", road: " + CarText.toShortString(this.mCurrentRoad) + ", isLoading: " + this.mIsLoading + "]";
    }
}
