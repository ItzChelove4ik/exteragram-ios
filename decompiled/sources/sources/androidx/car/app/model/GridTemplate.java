package androidx.car.app.model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class GridTemplate implements Template {
    public static final int ITEM_IMAGE_SHAPE_CIRCLE = 2;
    public static final int ITEM_IMAGE_SHAPE_UNSET = 1;
    public static final int ITEM_SIZE_LARGE = 4;
    public static final int ITEM_SIZE_MEDIUM = 2;
    public static final int ITEM_SIZE_SMALL = 1;
    private final ActionStrip mActionStrip;
    private final List<Action> mActions;
    private final Action mHeaderAction;
    private final boolean mIsLoading;
    private final int mItemImageShape;
    private final int mItemSize;
    private final ItemList mSingleList;
    private final CarText mTitle;

    public static final class Builder {
    }

    private GridTemplate() {
        this.mIsLoading = false;
        this.mTitle = null;
        this.mHeaderAction = null;
        this.mSingleList = null;
        this.mActionStrip = null;
        this.mActions = Collections.EMPTY_LIST;
        this.mItemSize = 1;
        this.mItemImageShape = 1;
    }

    public GridTemplate(Builder r1) {
        throw null;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof GridTemplate) == true) goto L8;
        return false;
    L8:
        GridTemplate r52 = (GridTemplate) r5;
        if (this.mIsLoading == r52.mIsLoading) goto L11;
    L25:
        return false;
    L11:
        if (Objects.equals(this.mTitle, r52.mTitle) == false) goto L25;
        if (Objects.equals(this.mHeaderAction, r52.mHeaderAction) == false) goto L25;
        if (Objects.equals(this.mSingleList, r52.mSingleList) == false) goto L25;
        if (Objects.equals(this.mActionStrip, r52.mActionStrip) == false) goto L25;
        if (Objects.equals(this.mActions, r52.mActions) == false) goto L25;
        if (this.mItemSize != r52.mItemSize) goto L25;
        if (this.mItemImageShape != r52.mItemImageShape) goto L25;
        return true;
    }

    public ActionStrip getActionStrip() {
        return this.mActionStrip;
    }

    public List<Action> getActions() {
        return this.mActions;
    }

    public Action getHeaderAction() {
        return this.mHeaderAction;
    }

    public int getItemImageShape() {
        return this.mItemImageShape;
    }

    public int getItemSize() {
        return this.mItemSize;
    }

    public ItemList getSingleList() {
        return this.mSingleList;
    }

    public CarText getTitle() {
        return this.mTitle;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Boolean.valueOf(this.mIsLoading), this.mTitle, this.mHeaderAction, this.mSingleList, this.mActionStrip, Integer.valueOf(this.mItemSize), Integer.valueOf(this.mItemImageShape)});
    }

    public boolean isLoading() {
        return this.mIsLoading;
    }

    public String toString() {
        return "GridTemplate";
    }
}
