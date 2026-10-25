package androidx.car.app.navigation.model;

import androidx.car.app.model.CarIcon;
import androidx.car.app.model.Distance;
import androidx.car.app.navigation.model.NavigationTemplate;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class RoutingInfo implements NavigationTemplate.NavigationInfo {
    private final Distance mCurrentDistance;
    private final Step mCurrentStep;
    private final boolean mIsLoading;
    private final CarIcon mJunctionImage;
    private final Step mNextStep;

    public static final class Builder {
    }

    private RoutingInfo() {
        this.mCurrentStep = null;
        this.mCurrentDistance = null;
        this.mNextStep = null;
        this.mJunctionImage = null;
        this.mIsLoading = false;
    }

    public RoutingInfo(Builder r1) {
        throw null;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof RoutingInfo) == true) goto L8;
        return false;
    L8:
        RoutingInfo r52 = (RoutingInfo) r5;
        if (this.mIsLoading == r52.mIsLoading) goto L11;
    L19:
        return false;
    L11:
        if (Objects.equals(this.mCurrentStep, r52.mCurrentStep) == false) goto L19;
        if (Objects.equals(this.mCurrentDistance, r52.mCurrentDistance) == false) goto L19;
        if (Objects.equals(this.mNextStep, r52.mNextStep) == false) goto L19;
        if (Objects.equals(this.mJunctionImage, r52.mJunctionImage) == false) goto L19;
        return true;
    }

    public Distance getCurrentDistance() {
        return this.mCurrentDistance;
    }

    public Step getCurrentStep() {
        return this.mCurrentStep;
    }

    public CarIcon getJunctionImage() {
        return this.mJunctionImage;
    }

    public Step getNextStep() {
        return this.mNextStep;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mCurrentStep, this.mCurrentDistance, this.mNextStep, this.mJunctionImage, Boolean.valueOf(this.mIsLoading)});
    }

    public boolean isLoading() {
        return this.mIsLoading;
    }

    public String toString() {
        return "RoutingInfo";
    }
}
