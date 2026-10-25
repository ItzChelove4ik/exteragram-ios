package com.google.android.material.expandable;

import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* loaded from: classes5.dex */
public final class ExpandableWidgetHelper {
    private boolean expanded;
    private int expandedComponentIdHint;
    private final View widget;

    /* JADX WARN: Multi-variable type inference failed */
    public ExpandableWidgetHelper(ExpandableWidget r2) {
        this.expanded = false;
        this.expandedComponentIdHint = 0;
        this.widget = (View) r2;
    }

    private void dispatchExpandedStateChanged() {
        ViewParent r0 = this.widget.getParent();
        if ((r0 instanceof CoordinatorLayout) == false) goto L6;
        ((CoordinatorLayout) r0).dispatchDependentViewsChanged(this.widget);
        return;
    }

    public int getExpandedComponentIdHint() {
        return this.expandedComponentIdHint;
    }

    public boolean isExpanded() {
        return this.expanded;
    }

    public void onRestoreInstanceState(Bundle r3) {
        this.expanded = r3.getBoolean("expanded", false);
        this.expandedComponentIdHint = r3.getInt("expandedComponentIdHint", 0);
        if (this.expanded == false) goto L6;
        dispatchExpandedStateChanged();
        return;
    }

    public Bundle onSaveInstanceState() {
        Bundle r0 = new Bundle();
        r0.putBoolean("expanded", this.expanded);
        r0.putInt("expandedComponentIdHint", this.expandedComponentIdHint);
        return r0;
    }

    public boolean setExpanded(boolean r2) {
        if (this.expanded == r2) goto L6;
        this.expanded = r2;
        dispatchExpandedStateChanged();
        return true;
    L6:
        return false;
    }

    public void setExpandedComponentIdHint(int r1) {
        this.expandedComponentIdHint = r1;
    }
}
