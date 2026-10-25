package androidx.car.app.model;

import android.annotation.SuppressLint;
import androidx.car.app.model.constraints.ActionsConstraints;
import androidx.car.app.model.constraints.TabsConstraints;
import androidx.car.app.utils.CollectionUtils;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public class TabTemplate implements Template {
    private final String mActiveTabContentId;
    private final Action mHeaderAction;
    private final boolean mIsLoading;
    private final TabCallbackDelegate mTabCallbackDelegate;
    private final TabContents mTabContents;
    private final List<Tab> mTabs;

    public static final class Builder {
        String mActiveTabContentId;
        Action mHeaderAction;
        boolean mIsLoading;
        final TabCallbackDelegate mTabCallbackDelegate;
        TabContents mTabContents;
        final List<Tab> mTabs;

        @SuppressLint({"ExecutorRegistration"})
        public Builder(TabCallback r1) {
            Objects.requireNonNull(r1);
            this.mTabCallbackDelegate = TabCallbackDelegateImpl.create(r1);
            this.mTabs = new ArrayList();
        }

        public Builder addTab(Tab r2) {
            Objects.requireNonNull(r2);
            this.mTabs.add(r2);
            return this;
        }

        public TabTemplate build() {
            if (this.mTabContents != null) goto L5;
        L7:
            boolean r0 = false;
        L8:
            boolean r1 = this.mIsLoading;
            if (r1 == false) goto L15;
            if (r0 == false) goto L15;
            Segment$$ExternalSyntheticBUOutline1.m("Template is in a loading state but tabs are added");
            return null;
        L15:
            if (r1 == true) goto L19;
            if (r0 == true) goto L19;
            Segment$$ExternalSyntheticBUOutline1.m("Template is not in a loading state but does not contain tabs or tab contents");
            return null;
        L19:
            if (r0 == true) goto L21;
        L24:
            if (r0 == false) goto L29;
            String r02 = this.mActiveTabContentId;
            if (r02 == null) goto L29;
            TabsConstraints.DEFAULT.validateOrThrow(this.mTabs, r02);
        L29:
            if (this.mIsLoading == true) goto L35;
            if (this.mHeaderAction != null) goto L35;
            f$$ExternalSyntheticBUOutline1.m("Template requires a Header Action of TYPE_APP_ICON when not in Loading state");
            return null;
        L35:
            return new TabTemplate(this);
        L21:
            if (this.mActiveTabContentId != null) goto L24;
            Segment$$ExternalSyntheticBUOutline1.m("Template requires setting content ID for the active tab when not in Loading state");
            return null;
        L5:
            if (this.mTabs.isEmpty() == true) goto L7;
            r0 = true;
            goto L8
        }

        public Builder setActiveTabContentId(String r2) {
            Objects.requireNonNull(r2);
            if (r2.isEmpty() == true) goto L6;
            this.mActiveTabContentId = r2;
            return this;
        L6:
            f$$ExternalSyntheticBUOutline1.m("The content ID cannot be null or empty");
            return null;
        }

        public Builder setHeaderAction(Action r3) {
            ActionsConstraints r0 = ActionsConstraints.ACTIONS_CONSTRAINTS_TABS;
            Objects.requireNonNull(r3);
            r0.validateOrThrow(Collections.singletonList(r3));
            this.mHeaderAction = r3;
            return this;
        }

        public Builder setTabContents(TabContents r1) {
            Objects.requireNonNull(r1);
            this.mTabContents = r1;
            return this;
        }
    }

    public interface TabCallback {
        void onTabSelected(String r1);
    }

    private TabTemplate() {
        this.mIsLoading = false;
        this.mHeaderAction = null;
        this.mTabs = Collections.EMPTY_LIST;
        this.mTabContents = null;
        this.mTabCallbackDelegate = null;
        this.mActiveTabContentId = null;
    }

    public TabTemplate(Builder r2) {
        this.mIsLoading = r2.mIsLoading;
        this.mHeaderAction = r2.mHeaderAction;
        this.mTabs = CollectionUtils.unmodifiableCopy(r2.mTabs);
        this.mTabContents = r2.mTabContents;
        this.mTabCallbackDelegate = r2.mTabCallbackDelegate;
        this.mActiveTabContentId = r2.mActiveTabContentId;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof TabTemplate) == true) goto L8;
        return false;
    L8:
        TabTemplate r52 = (TabTemplate) r5;
        if (this.mIsLoading == r52.mIsLoading) goto L11;
    L19:
        return false;
    L11:
        if (Objects.equals(this.mHeaderAction, r52.mHeaderAction) == false) goto L19;
        if (Objects.equals(this.mTabs, r52.mTabs) == false) goto L19;
        if (Objects.equals(this.mTabContents, r52.mTabContents) == false) goto L19;
        if (Objects.equals(this.mActiveTabContentId, r52.getActiveTabContentId()) == false) goto L19;
        return true;
    }

    public String getActiveTabContentId() {
        String r0 = this.mActiveTabContentId;
        Objects.requireNonNull(r0);
        return r0;
    }

    public Action getHeaderAction() {
        Action r0 = this.mHeaderAction;
        Objects.requireNonNull(r0);
        return r0;
    }

    public TabCallbackDelegate getTabCallbackDelegate() {
        TabCallbackDelegate r0 = this.mTabCallbackDelegate;
        Objects.requireNonNull(r0);
        return r0;
    }

    public TabContents getTabContents() {
        TabContents r0 = this.mTabContents;
        Objects.requireNonNull(r0);
        return r0;
    }

    public List<Tab> getTabs() {
        return CollectionUtils.emptyIfNull(this.mTabs);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Boolean.valueOf(this.mIsLoading), this.mHeaderAction, this.mTabs, this.mTabContents, this.mActiveTabContentId});
    }

    public boolean isLoading() {
        return this.mIsLoading;
    }

    public String toString() {
        return "TabTemplate";
    }
}
