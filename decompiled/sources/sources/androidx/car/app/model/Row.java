package androidx.car.app.model;

import android.annotation.SuppressLint;
import androidx.car.app.model.constraints.CarIconConstraints;
import androidx.car.app.model.constraints.CarTextConstraints;
import androidx.car.app.utils.CollectionUtils;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public final class Row implements Item {
    public static final int IMAGE_TYPE_ICON = 4;
    public static final int IMAGE_TYPE_LARGE = 2;
    public static final int IMAGE_TYPE_SMALL = 1;
    public static final int NO_DECORATION = -1;
    private static final String YOUR_BOAT = "🚣";
    private final List<Action> mActions;
    private final CarIcon mImage;
    private final boolean mIsBrowsable;
    private final boolean mIsEnabled;
    private final Metadata mMetadata;
    private final int mNumericDecoration;
    private final OnClickDelegate mOnClickDelegate;
    private final int mRowImageType;
    private final List<CarText> mTexts;
    private final CarText mTitle;
    private final Toggle mToggle;

    public static final class Builder {
        final List<Action> mActions;
        int mDecoration;
        CarIcon mImage;
        boolean mIsBrowsable;
        boolean mIsEnabled;
        Metadata mMetadata;
        OnClickDelegate mOnClickDelegate;
        int mRowImageType;
        final List<CarText> mTexts;
        CarText mTitle;
        Toggle mToggle;

        public Builder() {
            this.mIsEnabled = true;
            this.mTexts = new ArrayList();
            this.mActions = new ArrayList();
            this.mDecoration = -1;
            this.mMetadata = Metadata.EMPTY_METADATA;
            this.mRowImageType = 1;
        }

        public Builder addText(CharSequence r3) {
            Objects.requireNonNull(r3);
            CarText r0 = CarText.create(r3);
            CarTextConstraints.TEXT_WITH_COLORS_AND_ICON.validateOrThrow(r0);
            this.mTexts.add(CarText.create(r3));
            return this;
        }

        public Row build() {
            if (this.mTitle != null) goto L5;
            Segment$$ExternalSyntheticBUOutline1.m("A title must be set on the row");
            return null;
        L5:
            if (this.mIsBrowsable == false) goto L19;
            if (this.mToggle == null) goto L9;
            Segment$$ExternalSyntheticBUOutline1.m("A browsable row must not have a toggle set");
            return null;
        L9:
            if (this.mOnClickDelegate != null) goto L11;
            Segment$$ExternalSyntheticBUOutline1.m("A browsable row must have its onClickListener set");
            return null;
        L11:
            if (this.mActions.isEmpty() == true) goto L19;
            Segment$$ExternalSyntheticBUOutline1.m("A browsable row must not have a secondary action set");
            return null;
        L19:
            if (this.mToggle == null) goto L31;
            if (this.mOnClickDelegate == null) goto L23;
            Segment$$ExternalSyntheticBUOutline1.m("If a row contains a toggle, it must not have an onClickListener set");
            return null;
        L23:
            if (this.mDecoration == (-1)) goto L25;
            Segment$$ExternalSyntheticBUOutline1.m("If a row contains a toggle, it must not have a numeric decoration set");
            return null;
        L25:
            if (this.mActions.isEmpty() == true) goto L31;
            Segment$$ExternalSyntheticBUOutline1.m("If a row contains a toggle, it must not have a secondary action set");
            return null;
        L31:
            return new Row(this);
        }

        public Builder setBrowsable(boolean r1) {
            this.mIsBrowsable = r1;
            return this;
        }

        public Builder setImage(CarIcon r2) {
            Objects.requireNonNull(r2);
            return setImage(r2, 1);
        }

        public Builder setImage(CarIcon r2, int r3) {
            CarIconConstraints r0 = CarIconConstraints.UNCONSTRAINED;
            Objects.requireNonNull(r2);
            r0.validateOrThrow(r2);
            this.mImage = r2;
            this.mRowImageType = r3;
            return this;
        }

        @SuppressLint({"MissingGetterMatchingBuilder", "ExecutorRegistration"})
        public Builder setOnClickListener(OnClickListener r1) {
            this.mOnClickDelegate = OnClickDelegateImpl.create(r1);
            return this;
        }

        public Builder setTitle(CharSequence r2) {
            Objects.requireNonNull(r2);
            CarText r22 = CarText.create(r2);
            if (r22.isEmpty() == true) goto L6;
            CarTextConstraints.TEXT_AND_ICON.validateOrThrow(r22);
            this.mTitle = r22;
            return this;
        L6:
            f$$ExternalSyntheticBUOutline1.m("The title cannot be null or empty");
            return null;
        }
    }

    private Row() {
        this.mTitle = null;
        List r1 = Collections.EMPTY_LIST;
        this.mTexts = r1;
        this.mImage = null;
        this.mActions = r1;
        this.mNumericDecoration = -1;
        this.mToggle = null;
        this.mOnClickDelegate = null;
        this.mMetadata = Metadata.EMPTY_METADATA;
        this.mIsBrowsable = false;
        this.mRowImageType = 1;
        this.mIsEnabled = true;
    }

    public Row(Builder r2) {
        this.mTitle = r2.mTitle;
        this.mTexts = CollectionUtils.unmodifiableCopy(r2.mTexts);
        this.mImage = r2.mImage;
        this.mActions = CollectionUtils.unmodifiableCopy(r2.mActions);
        this.mNumericDecoration = r2.mDecoration;
        this.mToggle = r2.mToggle;
        this.mOnClickDelegate = r2.mOnClickDelegate;
        this.mMetadata = r2.mMetadata;
        this.mIsBrowsable = r2.mIsBrowsable;
        this.mRowImageType = r2.mRowImageType;
        this.mIsEnabled = r2.mIsEnabled;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof Row) == true) goto L8;
        return false;
    L8:
        Row r52 = (Row) r5;
        if (Objects.equals(this.mTitle, r52.mTitle) == true) goto L11;
    L35:
        return false;
    L11:
        if (Objects.equals(this.mTexts, r52.mTexts) == false) goto L35;
        if (Objects.equals(this.mImage, r52.mImage) == false) goto L35;
        if (Objects.equals(this.mToggle, r52.mToggle) == false) goto L35;
        if (this.mOnClickDelegate != null) goto L19;
        boolean r1 = true;
    L20:
        Boolean r12 = Boolean.valueOf(r1);
        if (r52.mOnClickDelegate != null) goto L23;
        boolean r3 = true;
    L25:
        if (r12.equals(Boolean.valueOf(r3)) == false) goto L35;
        if (Objects.equals(this.mMetadata, r52.mMetadata) == false) goto L35;
        if (this.mIsBrowsable != r52.mIsBrowsable) goto L35;
        if (this.mRowImageType != r52.mRowImageType) goto L35;
        if (this.mIsEnabled != r52.isEnabled()) goto L35;
        return true;
    L23:
        r3 = false;
        goto L25
    L19:
        r1 = false;
        goto L20
    }

    public List<Action> getActions() {
        return this.mActions;
    }

    public CarIcon getImage() {
        return this.mImage;
    }

    public Metadata getMetadata() {
        return this.mMetadata;
    }

    public int getNumericDecoration() {
        return this.mNumericDecoration;
    }

    public OnClickDelegate getOnClickDelegate() {
        return this.mOnClickDelegate;
    }

    public int getRowImageType() {
        return this.mRowImageType;
    }

    public List<CarText> getTexts() {
        return CollectionUtils.emptyIfNull(this.mTexts);
    }

    public CarText getTitle() {
        return this.mTitle;
    }

    public Toggle getToggle() {
        return this.mToggle;
    }

    public int hashCode() {
        CarText r0 = this.mTitle;
        List<CarText> r1 = this.mTexts;
        CarIcon r2 = this.mImage;
        Toggle r3 = this.mToggle;
        if (this.mOnClickDelegate != null) goto L5;
        boolean r4 = true;
    L7:
        return Objects.hash(new Object[]{r0, r1, r2, r3, Boolean.valueOf(r4), this.mMetadata, Boolean.valueOf(this.mIsBrowsable), Integer.valueOf(this.mRowImageType), Boolean.valueOf(this.mIsEnabled)});
    L5:
        r4 = false;
        goto L7
    }

    public boolean isBrowsable() {
        return this.mIsBrowsable;
    }

    public boolean isEnabled() {
        return this.mIsEnabled;
    }

    public Row row() {
        return this;
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder("[title: ");
        r0.append(CarText.toShortString(this.mTitle));
        r0.append(", text count: ");
        List<CarText> r1 = this.mTexts;
        if (r1 == null) goto L5;
        int r12 = r1.size();
    L6:
        r0.append(r12);
        r0.append(", image: ");
        r0.append(this.mImage);
        r0.append(", isBrowsable: ");
        r0.append(this.mIsBrowsable);
        r0.append(", isEnabled: ");
        r0.append(this.mIsEnabled);
        r0.append("]");
        return r0.toString();
    L5:
        r12 = 0;
        goto L6
    }

    public CharSequence yourBoat() {
        return YOUR_BOAT;
    }
}
