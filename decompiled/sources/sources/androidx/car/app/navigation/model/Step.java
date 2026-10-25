package androidx.car.app.navigation.model;

import androidx.car.app.model.CarIcon;
import androidx.car.app.model.CarText;
import androidx.car.app.model.constraints.CarIconConstraints;
import androidx.car.app.utils.CollectionUtils;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class Step {
    private final CarText mCue;
    private final List<Lane> mLanes;
    private final CarIcon mLanesImage;
    private final Maneuver mManeuver;
    private final CarText mRoad;

    private Step() {
        this.mManeuver = null;
        this.mLanes = Collections.EMPTY_LIST;
        this.mLanesImage = null;
        this.mCue = null;
        this.mRoad = null;
    }

    public Step(Maneuver r1, List<Lane> r2, CarIcon r3, CarText r4, CarText r5) {
        this.mManeuver = r1;
        this.mLanes = CollectionUtils.unmodifiableCopy(r2);
        CarIconConstraints.DEFAULT.validateOrThrow(r3);
        this.mLanesImage = r3;
        this.mCue = r4;
        this.mRoad = r5;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof Step) == true) goto L8;
        return false;
    L8:
        Step r52 = (Step) r5;
        if (Objects.equals(this.mManeuver, r52.mManeuver) == true) goto L11;
    L19:
        return false;
    L11:
        if (Objects.equals(this.mLanes, r52.mLanes) == false) goto L19;
        if (Objects.equals(this.mLanesImage, r52.mLanesImage) == false) goto L19;
        if (Objects.equals(this.mCue, r52.mCue) == false) goto L19;
        if (Objects.equals(this.mRoad, r52.mRoad) == false) goto L19;
        return true;
    }

    public CarText getCue() {
        return this.mCue;
    }

    public List<Lane> getLanes() {
        return CollectionUtils.emptyIfNull(this.mLanes);
    }

    public CarIcon getLanesImage() {
        return this.mLanesImage;
    }

    public Maneuver getManeuver() {
        return this.mManeuver;
    }

    public CarText getRoad() {
        return this.mRoad;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mManeuver, this.mLanes, this.mLanesImage, this.mCue, this.mRoad});
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder("[maneuver: ");
        r0.append(this.mManeuver);
        r0.append(", lane count: ");
        List<Lane> r1 = this.mLanes;
        if (r1 == null) goto L5;
        int r12 = r1.size();
    L6:
        r0.append(r12);
        r0.append(", lanes image: ");
        r0.append(this.mLanesImage);
        r0.append(", cue: ");
        r0.append(CarText.toShortString(this.mCue));
        r0.append(", road: ");
        r0.append(CarText.toShortString(this.mRoad));
        r0.append("]");
        return r0.toString();
    L5:
        r12 = 0;
        goto L6
    }
}
