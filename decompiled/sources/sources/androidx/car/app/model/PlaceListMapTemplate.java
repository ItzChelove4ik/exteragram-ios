package androidx.car.app.model;

import java.util.Objects;

/* loaded from: classes3.dex */
public final class PlaceListMapTemplate implements Template {
    private final ActionStrip mActionStrip;
    private final Place mAnchor;
    private final Action mHeaderAction;
    private final boolean mIsLoading;
    private final ItemList mItemList;
    private final OnContentRefreshDelegate mOnContentRefreshDelegate;
    private final boolean mShowCurrentLocation;
    private final CarText mTitle;

    public static final class Builder {
    }

    private PlaceListMapTemplate() {
        this.mShowCurrentLocation = false;
        this.mIsLoading = false;
        this.mTitle = null;
        this.mItemList = null;
        this.mHeaderAction = null;
        this.mActionStrip = null;
        this.mAnchor = null;
        this.mOnContentRefreshDelegate = null;
    }

    public PlaceListMapTemplate(Builder r1) {
        throw null;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof PlaceListMapTemplate) == true) goto L8;
        return false;
    L8:
        PlaceListMapTemplate r52 = (PlaceListMapTemplate) r5;
        if (this.mShowCurrentLocation == r52.mShowCurrentLocation) goto L11;
    L33:
        return false;
    L11:
        if (this.mIsLoading != r52.mIsLoading) goto L33;
        if (Objects.equals(this.mTitle, r52.mTitle) == false) goto L33;
        if (Objects.equals(this.mItemList, r52.mItemList) == false) goto L33;
        if (Objects.equals(this.mHeaderAction, r52.mHeaderAction) == false) goto L33;
        if (Objects.equals(this.mActionStrip, r52.mActionStrip) == false) goto L33;
        if (Objects.equals(this.mAnchor, r52.mAnchor) == false) goto L33;
        if (this.mOnContentRefreshDelegate != null) goto L25;
        boolean r4 = true;
    L26:
        Boolean r42 = Boolean.valueOf(r4);
        if (r52.mOnContentRefreshDelegate != null) goto L29;
        boolean r53 = true;
    L31:
        if (r42.equals(Boolean.valueOf(r53)) == false) goto L33;
        return true;
    L29:
        r53 = false;
        goto L31
    L25:
        r4 = false;
        goto L26
    }

    public ActionStrip getActionStrip() {
        return this.mActionStrip;
    }

    public Place getAnchor() {
        return this.mAnchor;
    }

    public Action getHeaderAction() {
        return this.mHeaderAction;
    }

    public ItemList getItemList() {
        return this.mItemList;
    }

    public OnContentRefreshDelegate getOnContentRefreshDelegate() {
        return this.mOnContentRefreshDelegate;
    }

    public CarText getTitle() {
        return this.mTitle;
    }

    public int hashCode() {
        Boolean r1 = Boolean.valueOf(this.mShowCurrentLocation);
        Boolean r2 = Boolean.valueOf(this.mIsLoading);
        CarText r3 = this.mTitle;
        ItemList r4 = this.mItemList;
        Action r5 = this.mHeaderAction;
        ActionStrip r6 = this.mActionStrip;
        Place r7 = this.mAnchor;
        if (this.mOnContentRefreshDelegate != null) goto L5;
        boolean r9 = true;
    L7:
        return Objects.hash(new Object[]{r1, r2, r3, r4, r5, r6, r7, Boolean.valueOf(r9)});
    L5:
        r9 = false;
        goto L7
    }

    public boolean isCurrentLocationEnabled() {
        return this.mShowCurrentLocation;
    }

    public boolean isLoading() {
        return this.mIsLoading;
    }

    public String toString() {
        return "PlaceListMapTemplate";
    }
}
