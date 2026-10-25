package androidx.car.app.model;

import androidx.car.app.model.constraints.CarIconConstraints;
import androidx.car.app.model.constraints.CarTextConstraints;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.Objects;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public final class Tab {
    private static final String EMPTY_TAB_CONTENT_ID = "EMPTY_TAB_CONTENT_ID";
    private final String mContentId;
    private final CarIcon mIcon;
    private final CarText mTitle;

    public static final class Builder {
        String mContentId;
        CarIcon mIcon;
        CarText mTitle;

        public Builder() {
        }

        public Tab build() {
            if (this.mTitle != null) goto L5;
            Segment$$ExternalSyntheticBUOutline1.m("A title must be set for the tab");
            return null;
        L5:
            if (this.mIcon != null) goto L7;
            Segment$$ExternalSyntheticBUOutline1.m("A icon must be set for the tab");
            return null;
        L7:
            if (this.mContentId != null) goto L9;
            Segment$$ExternalSyntheticBUOutline1.m("A content ID must be set for the tab");
            return null;
        L9:
            return new Tab(this);
        }

        public Builder setContentId(String r2) {
            Objects.requireNonNull(r2);
            if (r2.isEmpty() == true) goto L6;
            this.mContentId = r2;
            return this;
        L6:
            f$$ExternalSyntheticBUOutline1.m("The content ID cannot be null or empty");
            return null;
        }

        public Builder setIcon(CarIcon r2) {
            CarIconConstraints r0 = CarIconConstraints.DEFAULT;
            Objects.requireNonNull(r2);
            r0.validateOrThrow(r2);
            this.mIcon = r2;
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

    private Tab() {
        this.mTitle = null;
        this.mContentId = EMPTY_TAB_CONTENT_ID;
        this.mIcon = null;
    }

    public Tab(Builder r2) {
        this.mTitle = r2.mTitle;
        this.mIcon = r2.mIcon;
        String r22 = r2.mContentId;
        if (r22 == null) goto L6;
        this.mContentId = r22;
        return;
    L6:
        this.mContentId = EMPTY_TAB_CONTENT_ID;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof Tab) == true) goto L8;
        return false;
    L8:
        Tab r52 = (Tab) r5;
        if (Objects.equals(this.mTitle, r52.mTitle) == true) goto L11;
    L15:
        return false;
    L11:
        if (Objects.equals(this.mContentId, r52.mContentId) == false) goto L15;
        if (Objects.equals(this.mIcon, r52.mIcon) == false) goto L15;
        return true;
    }

    public String getContentId() {
        String r0 = this.mContentId;
        Objects.requireNonNull(r0);
        return r0;
    }

    public CarIcon getIcon() {
        CarIcon r0 = this.mIcon;
        Objects.requireNonNull(r0);
        return r0;
    }

    public CarText getTitle() {
        CarText r0 = this.mTitle;
        Objects.requireNonNull(r0);
        return r0;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mTitle, this.mContentId, this.mIcon});
    }

    public String toString() {
        return "[title: " + CarText.toShortString(this.mTitle) + ", contentId: " + this.mContentId + ", icon: " + this.mIcon + "]";
    }
}
