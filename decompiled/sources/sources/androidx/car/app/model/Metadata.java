package androidx.car.app.model;

import java.util.Objects;

/* loaded from: classes3.dex */
public final class Metadata {
    public static final Metadata EMPTY_METADATA = null;
    private final Place mPlace;

    public static final class Builder {
        Place mPlace;

        public Builder() {
        }

        public Metadata build() {
            return new Metadata(this);
        }
    }

    static {
        EMPTY_METADATA = new Builder().build();
    }

    private Metadata() {
        this.mPlace = null;
    }

    public Metadata(Builder r1) {
        this.mPlace = r1.mPlace;
    }

    public boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof Metadata) == true) goto L10;
        return false;
    L10:
        return Objects.equals(this.mPlace, ((Metadata) r2).mPlace);
    }

    public Place getPlace() {
        return this.mPlace;
    }

    public int hashCode() {
        return Objects.hashCode(this.mPlace);
    }
}
