package androidx.car.app.model;

import java.util.Objects;

/* loaded from: classes3.dex */
public final class SearchTemplate implements Template {
    private final ActionStrip mActionStrip;
    private final Action mHeaderAction;
    private final String mInitialSearchText;
    private final boolean mIsLoading;
    private final ItemList mItemList;
    private final SearchCallbackDelegate mSearchCallbackDelegate;
    private final String mSearchHint;
    private final boolean mShowKeyboardByDefault;

    public static final class Builder {
    }

    public interface SearchCallback {
    }

    private SearchTemplate() {
        this.mInitialSearchText = null;
        this.mSearchHint = null;
        this.mIsLoading = false;
        this.mItemList = null;
        this.mHeaderAction = null;
        this.mActionStrip = null;
        this.mSearchCallbackDelegate = null;
        this.mShowKeyboardByDefault = true;
    }

    public SearchTemplate(Builder r1) {
        throw null;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof SearchTemplate) == true) goto L8;
        return false;
    L8:
        SearchTemplate r52 = (SearchTemplate) r5;
        if (this.mIsLoading == r52.mIsLoading) goto L11;
    L23:
        return false;
    L11:
        if (Objects.equals(this.mInitialSearchText, r52.mInitialSearchText) == false) goto L23;
        if (Objects.equals(this.mSearchHint, r52.mSearchHint) == false) goto L23;
        if (Objects.equals(this.mItemList, r52.mItemList) == false) goto L23;
        if (Objects.equals(this.mHeaderAction, r52.mHeaderAction) == false) goto L23;
        if (Objects.equals(this.mActionStrip, r52.mActionStrip) == false) goto L23;
        if (this.mShowKeyboardByDefault != r52.mShowKeyboardByDefault) goto L23;
        return true;
    }

    public ActionStrip getActionStrip() {
        return this.mActionStrip;
    }

    public Action getHeaderAction() {
        return this.mHeaderAction;
    }

    public String getInitialSearchText() {
        return this.mInitialSearchText;
    }

    public ItemList getItemList() {
        return this.mItemList;
    }

    public SearchCallbackDelegate getSearchCallbackDelegate() {
        SearchCallbackDelegate r0 = this.mSearchCallbackDelegate;
        Objects.requireNonNull(r0);
        return r0;
    }

    public String getSearchHint() {
        return this.mSearchHint;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mInitialSearchText, Boolean.valueOf(this.mIsLoading), this.mSearchHint, this.mItemList, Boolean.valueOf(this.mShowKeyboardByDefault), this.mHeaderAction, this.mActionStrip});
    }

    public boolean isLoading() {
        return this.mIsLoading;
    }

    public boolean isShowKeyboardByDefault() {
        return this.mShowKeyboardByDefault;
    }

    public String toString() {
        return "SearchTemplate";
    }
}
