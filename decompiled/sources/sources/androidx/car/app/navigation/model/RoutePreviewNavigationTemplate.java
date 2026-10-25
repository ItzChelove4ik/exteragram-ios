package androidx.car.app.navigation.model;

import androidx.car.app.model.Action;
import androidx.car.app.model.ActionStrip;
import androidx.car.app.model.CarText;
import androidx.car.app.model.Header;
import androidx.car.app.model.ItemList;
import androidx.car.app.model.Template;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class RoutePreviewNavigationTemplate implements Template {
    private final ActionStrip mActionStrip;
    private final Header mHeader;

    @Deprecated
    private final Action mHeaderAction;
    private final boolean mIsLoading;
    private final ItemList mItemList;
    private final ActionStrip mMapActionStrip;
    private final Action mNavigateAction;
    private final PanModeDelegate mPanModeDelegate;

    @Deprecated
    private final CarText mTitle;

    public static final class Builder {
    }

    private RoutePreviewNavigationTemplate() {
        this.mTitle = null;
        this.mIsLoading = false;
        this.mNavigateAction = null;
        this.mItemList = null;
        this.mHeader = null;
        this.mHeaderAction = null;
        this.mActionStrip = null;
        this.mMapActionStrip = null;
        this.mPanModeDelegate = null;
    }

    public RoutePreviewNavigationTemplate(Builder r1) {
        throw null;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof RoutePreviewNavigationTemplate) == true) goto L8;
        return false;
    L8:
        RoutePreviewNavigationTemplate r52 = (RoutePreviewNavigationTemplate) r5;
        if (this.mIsLoading == r52.mIsLoading) goto L11;
    L35:
        return false;
    L11:
        if (Objects.equals(this.mTitle, r52.mTitle) == false) goto L35;
        if (Objects.equals(this.mNavigateAction, r52.mNavigateAction) == false) goto L35;
        if (Objects.equals(this.mItemList, r52.mItemList) == false) goto L35;
        if (Objects.equals(this.mHeaderAction, r52.mHeaderAction) == false) goto L35;
        if (Objects.equals(this.mActionStrip, r52.mActionStrip) == false) goto L35;
        if (Objects.equals(this.mMapActionStrip, r52.mMapActionStrip) == false) goto L35;
        if (this.mPanModeDelegate != null) goto L25;
        boolean r1 = true;
    L26:
        Boolean r12 = Boolean.valueOf(r1);
        if (r52.mPanModeDelegate != null) goto L29;
        boolean r3 = true;
    L31:
        if (r12.equals(Boolean.valueOf(r3)) == false) goto L35;
        if (Objects.equals(this.mHeader, r52.mHeader) == false) goto L35;
        return true;
    L29:
        r3 = false;
        goto L31
    L25:
        r1 = false;
        goto L26
    }

    public ActionStrip getActionStrip() {
        return this.mActionStrip;
    }

    public Header getHeader() {
        return this.mHeader;
    }

    @Deprecated
    public Action getHeaderAction() {
        return this.mHeaderAction;
    }

    public ItemList getItemList() {
        return this.mItemList;
    }

    public ActionStrip getMapActionStrip() {
        return this.mMapActionStrip;
    }

    public Action getNavigateAction() {
        return this.mNavigateAction;
    }

    public PanModeDelegate getPanModeDelegate() {
        return this.mPanModeDelegate;
    }

    @Deprecated
    public CarText getTitle() {
        return this.mTitle;
    }

    public int hashCode() {
        CarText r0 = this.mTitle;
        Boolean r1 = Boolean.valueOf(this.mIsLoading);
        Action r2 = this.mNavigateAction;
        ItemList r3 = this.mItemList;
        Action r4 = this.mHeaderAction;
        ActionStrip r5 = this.mActionStrip;
        ActionStrip r6 = this.mMapActionStrip;
        if (this.mPanModeDelegate != null) goto L5;
        boolean r7 = true;
    L7:
        return Objects.hash(new Object[]{r0, r1, r2, r3, r4, r5, r6, Boolean.valueOf(r7), this.mHeader});
    L5:
        r7 = false;
        goto L7
    }

    public boolean isLoading() {
        return this.mIsLoading;
    }

    public String toString() {
        return "RoutePreviewNavigationTemplate";
    }
}
