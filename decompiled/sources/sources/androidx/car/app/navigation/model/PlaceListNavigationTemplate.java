package androidx.car.app.navigation.model;

import androidx.car.app.model.Action;
import androidx.car.app.model.ActionStrip;
import androidx.car.app.model.CarText;
import androidx.car.app.model.Header;
import androidx.car.app.model.ItemList;
import androidx.car.app.model.OnContentRefreshDelegate;
import androidx.car.app.model.Template;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class PlaceListNavigationTemplate implements Template {
    private final ActionStrip mActionStrip;
    private final Header mHeader;

    @Deprecated
    private final Action mHeaderAction;
    private final boolean mIsLoading;
    private final ItemList mItemList;
    private final ActionStrip mMapActionStrip;
    private final OnContentRefreshDelegate mOnContentRefreshDelegate;
    private final PanModeDelegate mPanModeDelegate;

    @Deprecated
    private final CarText mTitle;

    public static final class Builder {
    }

    private PlaceListNavigationTemplate() {
        this.mTitle = null;
        this.mIsLoading = false;
        this.mItemList = null;
        this.mHeader = null;
        this.mHeaderAction = null;
        this.mActionStrip = null;
        this.mMapActionStrip = null;
        this.mPanModeDelegate = null;
        this.mOnContentRefreshDelegate = null;
    }

    public PlaceListNavigationTemplate(Builder r1) {
        throw null;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof PlaceListNavigationTemplate) == true) goto L8;
        return false;
    L8:
        PlaceListNavigationTemplate r52 = (PlaceListNavigationTemplate) r5;
        if (this.mIsLoading == r52.mIsLoading) goto L11;
    L43:
        return false;
    L11:
        if (Objects.equals(this.mTitle, r52.mTitle) == false) goto L43;
        if (Objects.equals(this.mItemList, r52.mItemList) == false) goto L43;
        if (Objects.equals(this.mHeaderAction, r52.mHeaderAction) == false) goto L43;
        if (Objects.equals(this.mActionStrip, r52.mActionStrip) == false) goto L43;
        if (Objects.equals(this.mMapActionStrip, r52.mMapActionStrip) == false) goto L43;
        if (this.mPanModeDelegate != null) goto L23;
        boolean r1 = true;
    L24:
        Boolean r12 = Boolean.valueOf(r1);
        if (r52.mPanModeDelegate != null) goto L27;
        boolean r3 = true;
    L29:
        if (r12.equals(Boolean.valueOf(r3)) == false) goto L43;
        if (this.mOnContentRefreshDelegate != null) goto L33;
        boolean r13 = true;
    L34:
        Boolean r14 = Boolean.valueOf(r13);
        if (r52.mOnContentRefreshDelegate != null) goto L37;
        boolean r32 = true;
    L39:
        if (r14.equals(Boolean.valueOf(r32)) == false) goto L43;
        if (Objects.equals(this.mHeader, r52.mHeader) == false) goto L43;
        return true;
    L37:
        r32 = false;
        goto L39
    L33:
        r13 = false;
        goto L34
    L27:
        r3 = false;
        goto L29
    L23:
        r1 = false;
        goto L24
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

    public OnContentRefreshDelegate getOnContentRefreshDelegate() {
        return this.mOnContentRefreshDelegate;
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
        ItemList r2 = this.mItemList;
        Action r3 = this.mHeaderAction;
        ActionStrip r4 = this.mActionStrip;
        ActionStrip r5 = this.mMapActionStrip;
        boolean r7 = false;
        if (this.mPanModeDelegate != null) goto L5;
        boolean r6 = true;
    L6:
        Boolean r62 = Boolean.valueOf(r6);
        if (this.mOnContentRefreshDelegate != null) goto L10;
        r7 = true;
    L10:
        return Objects.hash(new Object[]{r0, r1, r2, r3, r4, r5, r62, Boolean.valueOf(r7), this.mHeader});
    L5:
        r6 = false;
        goto L6
    }

    public boolean isLoading() {
        return this.mIsLoading;
    }

    public String toString() {
        return "PlaceListNavigationTemplate";
    }
}
