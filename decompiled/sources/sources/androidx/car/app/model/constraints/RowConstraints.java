package androidx.car.app.model.constraints;

import androidx.car.app.model.CarIcon;
import androidx.car.app.model.Row;
import c.f$$ExternalSyntheticBUOutline1;
import com.sun.jna.IntegerType$$ExternalSyntheticBUOutline0;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class RowConstraints {
    public static final RowConstraints ROW_CONSTRAINTS_CONSERVATIVE = null;
    public static final RowConstraints ROW_CONSTRAINTS_FULL_LIST = null;
    public static final RowConstraints ROW_CONSTRAINTS_PANE = null;
    public static final RowConstraints ROW_CONSTRAINTS_SIMPLE = null;
    public static final RowConstraints UNCONSTRAINED = null;
    private final CarIconConstraints mCarIconConstraints;
    private final boolean mIsImageAllowed;
    private final boolean mIsOnClickListenerAllowed;
    private final boolean mIsToggleAllowed;
    private final int mMaxActionsExclusive;
    private final int mMaxTextLinesPerRow;

    public static final class Builder {
        CarIconConstraints mCarIconConstraints;
        boolean mIsImageAllowed;
        boolean mIsOnClickListenerAllowed;
        boolean mIsToggleAllowed;
        int mMaxActionsExclusive;
        int mMaxTextLines;

        public Builder() {
            this.mIsOnClickListenerAllowed = true;
            this.mIsToggleAllowed = true;
            this.mMaxTextLines = Integer.MAX_VALUE;
            this.mMaxActionsExclusive = Integer.MAX_VALUE;
            this.mIsImageAllowed = true;
            this.mCarIconConstraints = CarIconConstraints.UNCONSTRAINED;
        }

        public Builder(RowConstraints r3) {
            this.mIsOnClickListenerAllowed = true;
            this.mIsToggleAllowed = true;
            this.mMaxTextLines = Integer.MAX_VALUE;
            this.mMaxActionsExclusive = Integer.MAX_VALUE;
            this.mIsImageAllowed = true;
            this.mCarIconConstraints = CarIconConstraints.UNCONSTRAINED;
            Objects.requireNonNull(r3);
            this.mIsOnClickListenerAllowed = r3.isOnClickListenerAllowed();
            this.mMaxTextLines = r3.getMaxTextLinesPerRow();
            this.mMaxActionsExclusive = r3.getMaxActionsExclusive();
            this.mIsToggleAllowed = r3.isToggleAllowed();
            this.mIsImageAllowed = r3.isImageAllowed();
            this.mCarIconConstraints = r3.getCarIconConstraints();
        }

        public RowConstraints build() {
            return new RowConstraints(this);
        }

        public Builder setImageAllowed(boolean r1) {
            this.mIsImageAllowed = r1;
            return this;
        }

        public Builder setMaxActionsExclusive(int r1) {
            this.mMaxActionsExclusive = r1;
            return this;
        }

        public Builder setMaxTextLinesPerRow(int r1) {
            this.mMaxTextLines = r1;
            return this;
        }

        public Builder setOnClickListenerAllowed(boolean r1) {
            this.mIsOnClickListenerAllowed = r1;
            return this;
        }

        public Builder setToggleAllowed(boolean r1) {
            this.mIsToggleAllowed = r1;
            return this;
        }
    }

    static {
        UNCONSTRAINED = new Builder().build();
        ROW_CONSTRAINTS_CONSERVATIVE = new Builder().setMaxActionsExclusive(0).setImageAllowed(false).setMaxTextLinesPerRow(1).setOnClickListenerAllowed(true).setToggleAllowed(false).build();
        ROW_CONSTRAINTS_PANE = new Builder().setMaxActionsExclusive(2).setImageAllowed(true).setMaxTextLinesPerRow(2).setToggleAllowed(true).setOnClickListenerAllowed(false).build();
        RowConstraints r0 = new Builder().setMaxActionsExclusive(0).setImageAllowed(true).setMaxTextLinesPerRow(2).setToggleAllowed(true).setOnClickListenerAllowed(true).build();
        ROW_CONSTRAINTS_SIMPLE = r0;
        ROW_CONSTRAINTS_FULL_LIST = new Builder(r0).setToggleAllowed(true).build();
    }

    public RowConstraints(Builder r2) {
        this.mIsOnClickListenerAllowed = r2.mIsOnClickListenerAllowed;
        this.mMaxTextLinesPerRow = r2.mMaxTextLines;
        this.mMaxActionsExclusive = r2.mMaxActionsExclusive;
        this.mIsToggleAllowed = r2.mIsToggleAllowed;
        this.mIsImageAllowed = r2.mIsImageAllowed;
        this.mCarIconConstraints = r2.mCarIconConstraints;
    }

    public CarIconConstraints getCarIconConstraints() {
        return this.mCarIconConstraints;
    }

    public int getMaxActionsExclusive() {
        return this.mMaxActionsExclusive;
    }

    public int getMaxTextLinesPerRow() {
        return this.mMaxTextLinesPerRow;
    }

    public boolean isImageAllowed() {
        return this.mIsImageAllowed;
    }

    public boolean isOnClickListenerAllowed() {
        return this.mIsOnClickListenerAllowed;
    }

    public boolean isToggleAllowed() {
        return this.mIsToggleAllowed;
    }

    public void validateOrThrow(Row r3) {
        if (this.mIsOnClickListenerAllowed == true) goto L10;
        if (r3.getOnClickDelegate() == null) goto L10;
        f$$ExternalSyntheticBUOutline1.m("A click listener is not allowed on the row");
        return;
    L10:
        if (this.mIsToggleAllowed == false) goto L12;
    L16:
        CarIcon r0 = r3.getImage();
        if (r0 == null) goto L24;
        if (this.mIsImageAllowed == false) goto L21;
        this.mCarIconConstraints.validateOrThrow(r0);
        goto L24
    L21:
        f$$ExternalSyntheticBUOutline1.m("An image is not allowed on the row");
        return;
    L24:
        if (r3.getTexts().size() > this.mMaxTextLinesPerRow) goto L26;
        return;
    L26:
        IntegerType$$ExternalSyntheticBUOutline0.m("The number of lines of texts for the row exceeded the supported max of ", this.mMaxTextLinesPerRow);
        return;
    L12:
        if (r3.getToggle() == null) goto L16;
        f$$ExternalSyntheticBUOutline1.m("A toggle is not allowed on the row");
    }
}
