package androidx.car.app.model;

import java.util.Objects;

/* loaded from: classes3.dex */
public final class GridItem implements Item {
    public static final int IMAGE_TYPE_ICON = 1;
    public static final int IMAGE_TYPE_LARGE = 2;
    private final Badge mBadge;
    private final CarIcon mImage;
    private final int mImageType;
    private final boolean mIsLoading;
    private final OnClickDelegate mOnClickDelegate;
    private final CarText mText;
    private final CarText mTitle;

    public static final class Builder {
    }

    private GridItem() {
        this.mIsLoading = false;
        this.mTitle = null;
        this.mText = null;
        this.mImage = null;
        this.mImageType = 2;
        this.mOnClickDelegate = null;
        this.mBadge = null;
    }

    public GridItem(Builder r1) {
        throw null;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof GridItem) == true) goto L8;
        return false;
    L8:
        GridItem r52 = (GridItem) r5;
        if (this.mIsLoading == r52.mIsLoading) goto L11;
    L31:
        return false;
    L11:
        if (Objects.equals(this.mTitle, r52.mTitle) == false) goto L31;
        if (Objects.equals(this.mText, r52.mText) == false) goto L31;
        if (Objects.equals(this.mImage, r52.mImage) == false) goto L31;
        if (this.mOnClickDelegate != null) goto L19;
        boolean r1 = true;
    L20:
        Boolean r12 = Boolean.valueOf(r1);
        if (r52.mOnClickDelegate != null) goto L23;
        boolean r3 = true;
    L25:
        if (r12.equals(Boolean.valueOf(r3)) == false) goto L31;
        if (Objects.equals(this.mBadge, r52.mBadge) == false) goto L31;
        if (this.mImageType != r52.mImageType) goto L31;
        return true;
    L23:
        r3 = false;
        goto L25
    L19:
        r1 = false;
        goto L20
    }

    public Badge getBadge() {
        return this.mBadge;
    }

    public CarIcon getImage() {
        return this.mImage;
    }

    public int getImageType() {
        return this.mImageType;
    }

    public OnClickDelegate getOnClickDelegate() {
        return this.mOnClickDelegate;
    }

    public CarText getText() {
        return this.mText;
    }

    public CarText getTitle() {
        return this.mTitle;
    }

    public int hashCode() {
        Boolean r1 = Boolean.valueOf(this.mIsLoading);
        CarText r2 = this.mTitle;
        CarIcon r3 = this.mImage;
        Integer r4 = Integer.valueOf(this.mImageType);
        if (this.mOnClickDelegate != null) goto L5;
        boolean r0 = true;
    L7:
        return Objects.hash(new Object[]{r1, r2, r3, r4, Boolean.valueOf(r0), this.mBadge});
    L5:
        r0 = false;
        goto L7
    }

    public boolean isLoading() {
        return this.mIsLoading;
    }

    public String toString() {
        return "[title: " + CarText.toShortString(this.mTitle) + ", text: " + CarText.toShortString(this.mText) + ", image: " + this.mImage + ", isLoading: " + this.mIsLoading + ", badge: " + this.mBadge + "]";
    }
}
