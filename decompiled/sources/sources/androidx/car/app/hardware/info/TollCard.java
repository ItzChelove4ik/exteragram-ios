package androidx.car.app.hardware.info;

import androidx.car.app.hardware.common.CarValue;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class TollCard {
    public static final int TOLLCARD_STATE_INVALID = 2;
    public static final int TOLLCARD_STATE_NOT_INSERTED = 3;
    public static final int TOLLCARD_STATE_UNKNOWN = 0;
    public static final int TOLLCARD_STATE_VALID = 1;
    private final CarValue<Integer> mCardState;

    public static final class Builder {
        CarValue<Integer> mCardState;

        public Builder() {
            this.mCardState = CarValue.UNKNOWN_INTEGER;
        }

        public TollCard build() {
            return new TollCard(this);
        }
    }

    private TollCard() {
        this.mCardState = CarValue.UNKNOWN_INTEGER;
    }

    public TollCard(Builder r1) {
        CarValue<Integer> r12 = r1.mCardState;
        Objects.requireNonNull(r12);
        this.mCardState = r12;
    }

    public boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof TollCard) == true) goto L10;
        return false;
    L10:
        return Objects.equals(this.mCardState, ((TollCard) r2).mCardState);
    }

    public CarValue<Integer> getCardState() {
        CarValue<Integer> r0 = this.mCardState;
        Objects.requireNonNull(r0);
        return r0;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mCardState});
    }

    public String toString() {
        return "[ tollcard state: " + this.mCardState + "]";
    }
}
