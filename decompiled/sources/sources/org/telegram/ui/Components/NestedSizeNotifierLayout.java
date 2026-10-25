package org.telegram.ui.Components;

import android.content.Context;
import android.view.View;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.BottomSheet;

/* loaded from: classes7.dex */
public abstract class NestedSizeNotifierLayout extends SizeNotifierFrameLayout implements NestedScrollingParent3, View.OnLayoutChangeListener {
    boolean attached;
    BottomSheet.ContainerView bottomSheetContainerView;
    ChildLayout childLayout;
    int maxTop;
    int maxTopPadding;
    private NestedScrollingParentHelper nestedScrollingParentHelper;
    View targetListView;

    public interface ChildLayout {
        void addOnLayoutChangeListener(View.OnLayoutChangeListener r1);

        RecyclerListView getListView();

        int getMeasuredHeight();

        int getTop();

        boolean isAttached();

        void removeOnLayoutChangeListener(View.OnLayoutChangeListener r1);
    }

    public NestedSizeNotifierLayout(Context r1) {
        super(r1);
        this.nestedScrollingParentHelper = new NestedScrollingParentHelper(this);
    }

    private boolean childAttached() {
        ChildLayout r0 = this.childLayout;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isAttached() == true) goto L7;
        return false;
    L7:
        if (this.childLayout.getListView() == null) goto L13;
        return true;
    L13:
        return false;
    }

    private void updateMaxTop() {
        View r0 = this.targetListView;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.childLayout != null) goto L7;
        return;
    L7:
        if (this.maxTopPadding == 0) goto L10;
        this.maxTop = r0.getPaddingTop() + this.maxTopPadding;
        return;
    L10:
        this.maxTop = (r0.getMeasuredHeight() - this.targetListView.getPaddingBottom()) - this.childLayout.getMeasuredHeight();
    }

    @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, org.telegram.ui.ActionBar.Theme.Colorable
    public /* bridge */ /* synthetic */ int[] getColorKeys() {
        return super.getColorKeys();
    }

    public boolean isPinnedToTop() {
        ChildLayout r0 = this.childLayout;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.getTop() != this.maxTop) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.attached = true;
        ChildLayout r0 = this.childLayout;
        if (r0 == null) goto L6;
        r0.addOnLayoutChangeListener(this);
        return;
    }

    @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.attached = false;
        ChildLayout r0 = this.childLayout;
        if (r0 == null) goto L6;
        r0.removeOnLayoutChangeListener(this);
        return;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public void onLayoutChange(View r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9) {
        updateMaxTop();
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r1, int r2) {
        super.onMeasure(r1, r2);
        updateMaxTop();
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedPreFling(View r1, float r2, float r3) {
        return super.onNestedPreFling(r1, r2, r3);
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedPreScroll(View r7, int r8, int r9, int[] r10, int r11) {
        if (r7 == this.targetListView) goto L5;
        return;
    L5:
        if (childAttached() == false) goto L34;
        int r112 = this.childLayout.getTop();
        RecyclerListView r0 = this.childLayout.getListView();
        if (r9 < 0) goto L9;
        BottomSheet.ContainerView r6 = this.bottomSheetContainerView;
        if (r6 == null) goto L39;
        r6.onNestedPreScroll(r7, r8, r9, r10);
        return;
    L39:
        return;
    L9:
        if (r112 > (this.maxTop + AndroidUtilities.dp(4.0f))) goto L24;
        int r62 = ((LinearLayoutManager) r0.getLayoutManager()).findFirstVisibleItemPosition();
        int r72 = -1;
        if (r62 == (-1)) goto L35;
        RecyclerView.ViewHolder r82 = r0.findViewHolderForAdapterPosition(r62);
        if (r82 == null) goto L15;
        r72 = r82.itemView.getTop();
    L15:
        int r83 = r0.getPaddingTop();
        if (r72 != r83) goto L18;
        if (r62 != 0) goto L18;
        return;
    L18:
        if (r62 == 0) goto L20;
        int r63 = r9;
    L21:
        r10[1] = r63;
        r0.scrollBy(0, r9);
        return;
    L20:
        r63 = Math.max(r9, r72 - r83);
        goto L21
    L35:
        return;
    L24:
        if (this.bottomSheetContainerView != null) goto L26;
        return;
    L26:
        if (this.targetListView.canScrollVertically(r9) == true) goto L38;
        this.bottomSheetContainerView.onNestedScroll(r7, 0, 0, r8, r9);
        return;
    L38:
        return;
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedScroll(View r1, int r2, int r3, int r4, int r5, int r6) {
    }

    @Override // androidx.core.view.NestedScrollingParent3
    public void onNestedScroll(View r1, int r2, int r3, int r4, int r5, int r6, int[] r7) {
        if (r1 == this.targetListView) goto L5;
        return;
    L5:
        if (childAttached() == false) goto L11;
        RecyclerListView r12 = this.childLayout.getListView();
        if (this.childLayout.getTop() > (this.maxTop + AndroidUtilities.dp(4.0f))) goto L12;
        r7[1] = r5;
        r12.scrollBy(0, r5);
        return;
    L12:
        return;
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedScrollAccepted(View r1, View r2, int r3, int r4) {
        this.nestedScrollingParentHelper.onNestedScrollAccepted(r1, r2, r3);
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public boolean onStartNestedScroll(View r1, View r2, int r3, int r4) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (r1.isAttachedToWindow() == true) goto L6;
        return false;
    L6:
        if (r3 != 2) goto L12;
        return true;
    L12:
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onStopNestedScroll(View r1) {
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onStopNestedScroll(View r1, int r2) {
        this.nestedScrollingParentHelper.onStopNestedScroll(r1);
        BottomSheet.ContainerView r0 = this.bottomSheetContainerView;
        if (r0 == null) goto L6;
        r0.onStopNestedScroll(r1);
        return;
    }

    public void setBottomSheetContainerView(BottomSheet.ContainerView r1) {
        this.bottomSheetContainerView = r1;
    }

    public void setChildLayout(ChildLayout r2) {
        setChildLayout(r2, 0);
    }

    public void setChildLayout(ChildLayout r1, int r2) {
        this.maxTopPadding = r2;
        if (this.childLayout == r1) goto L10;
        this.childLayout = r1;
        if (this.attached == false) goto L10;
        if (r1 == null) goto L10;
        if (r1.getListView() == null) goto L10;
        r1.getListView().addOnLayoutChangeListener(this);
    L10:
        updateMaxTop();
    }

    public void setTargetListView(View r1) {
        this.targetListView = r1;
        updateMaxTop();
    }
}
