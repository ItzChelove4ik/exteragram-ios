package androidx.car.app.model;

import java.util.Objects;

/* loaded from: classes3.dex */
public final class Place {
    private final CarLocation mLocation;
    private final PlaceMarker mMarker;

    public static final class Builder {
    }

    private Place() {
        this.mLocation = null;
        this.mMarker = null;
    }

    public Place(Builder r1) {
        throw null;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof Place) == true) goto L8;
        return false;
    L8:
        Place r52 = (Place) r5;
        if (Objects.equals(this.mLocation, r52.mLocation) == true) goto L11;
    L13:
        return false;
    L11:
        if (Objects.equals(this.mMarker, r52.mMarker) == false) goto L13;
        return true;
    }

    public CarLocation getLocation() {
        CarLocation r0 = this.mLocation;
        Objects.requireNonNull(r0);
        return r0;
    }

    public PlaceMarker getMarker() {
        return this.mMarker;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mLocation, this.mMarker});
    }

    public String toString() {
        return "[ location: " + this.mLocation + ", marker: " + this.mMarker + "]";
    }
}
