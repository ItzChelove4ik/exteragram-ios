package com.google.android.material.listitem;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.TintTypedArray;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.ref.WeakReference;
import org.telegram.tgnet.TLObject;

/* loaded from: classes5.dex */
public class ListItemRevealLayout extends ViewGroup implements RevealableListItem {
    private static final int UNSET = -1;
    private int intrinsicHeight;
    private int intrinsicWidth;
    private int minChildWidth;
    private int[] originalChildHeights;
    private int[] originalChildWidths;
    private int originalHeightMeasureSpec;
    private int originalWidthMeasureSpec;
    private int primaryActionSwipeMode;
    private int revealedWidth;
    private WeakReference<View> siblingSwipeableView;

    public ListItemRevealLayout(Context r2) {
        this(r2, null);
    }

    public ListItemRevealLayout(Context r2, AttributeSet r3) {
        this(r2, r3, R.attr.listItemRevealLayoutStyle);
    }

    public ListItemRevealLayout(Context r2, AttributeSet r3, int r4) {
        this(r2, r3, r4, R.style.Widget_Material3_ListItemRevealLayout);
    }

    public ListItemRevealLayout(Context r8, AttributeSet r9, int r10, int r11) {
        super(MaterialThemeOverlay.wrap(r8, r9, r10, r11), r9, r10);
        this.intrinsicWidth = -1;
        this.intrinsicHeight = -1;
        this.revealedWidth = 0;
        this.originalWidthMeasureSpec = -1;
        this.originalHeightMeasureSpec = -1;
        Context r1 = getContext();
        setClipToPadding(false);
        TintTypedArray r82 = ThemeEnforcement.obtainTintedStyledAttributes(r1, r9, R.styleable.ListItemRevealLayout, r10, r11, new int[0]);
        this.minChildWidth = r82.getDimensionPixelSize(R.styleable.ListItemRevealLayout_minChildWidth, getResources().getDimensionPixelSize(R.dimen.m3_list_reveal_min_child_width));
        this.primaryActionSwipeMode = r82.getInt(R.styleable.ListItemRevealLayout_primaryActionSwipeMode, 0);
        r82.recycle();
    }

    private int calculateFullRevealableWidth() {
        WeakReference<View> r0 = this.siblingSwipeableView;
        if (r0 == null) goto L9;
        if (r0.get() == null) goto L9;
        return this.siblingSwipeableView.get().getMeasuredWidth();
    L9:
        if ((getParent() instanceof View) == false) goto L13;
        return ((View) getParent()).getMeasuredWidth();
    L13:
        return this.intrinsicWidth;
    }

    private Integer findFirstVisibleChildIndex() {
        int r0 = getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L9;
        if (getChildAt(r1).getVisibility() != 8) goto L7;
        r1 = r1 + 1;
        goto L3
    L7:
        return Integer.valueOf(r1);
    L9:
        return null;
    }

    private Integer findLastVisibleChildIndex() {
        int r0 = getChildCount() - 1;
    L3:
        if (r0 < 0) goto L9;
        if (getChildAt(r0).getVisibility() != 8) goto L7;
        r0 = r0 - 1;
        goto L3
    L7:
        return Integer.valueOf(r0);
    L9:
        return null;
    }

    private View findSiblingSwipeableView() {
        if ((getParent() instanceof ViewGroup) == true) goto L5;
        return null;
    L5:
        ViewGroup r5 = (ViewGroup) getParent();
        int r0 = r5.getChildCount();
        int r2 = 0;
    L6:
        if (r2 >= r0) goto L11;
        View r3 = r5.getChildAt(r2);
        if ((r3 instanceof SwipeableListItem) == true) goto L9;
        r2 = r2 + 1;
        goto L6
    L9:
        return r3;
    L11:
        return null;
    }

    private void measureByGrowingPrimarySwipeAction(int r12) {
        boolean r2 = true;
        if (getLayoutDirection() == 1) goto L7;
        r2 = false;
    L7:
        if (ListItemUtils.isRightAligned(this) != r2) goto L9;
        Integer r0 = findFirstVisibleChildIndex();
    L10:
        if (r0 == null) goto L31;
        int r22 = getPaddingStart() + getPaddingEnd();
        int r3 = this.revealedWidth;
        int r4 = this.intrinsicWidth;
        float r32 = Math.max(0.0f, Math.min(1.0f, (r3 - r4) / (r12 - r4)));
        int r42 = getChildCount();
        int r5 = 0;
    L13:
        if (r5 >= r42) goto L26;
        View r8 = getChildAt(r5);
        if (r8.getVisibility() == 8) goto L25;
        if (r5 == r0.intValue()) goto L25;
        r8.measure(View.MeasureSpec.makeMeasureSpec(AnimationUtils.lerp(Math.max(this.originalChildWidths[r5], this.minChildWidth), this.minChildWidth, r32), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(this.originalChildHeights[r5], TLObject.FLAG_30));
        ViewGroup.MarginLayoutParams r7 = (ViewGroup.MarginLayoutParams) r8.getLayoutParams();
        r22 = r22 + ((r7.leftMargin + r7.rightMargin) + this.minChildWidth);
        if ((r8 instanceof MaterialButton) == false) goto L25;
        MaterialButton r82 = (MaterialButton) r8;
        if (r82.getIcon() == null) goto L25;
        r82.getIcon().setAlpha(AnimationUtils.lerp(255, 0, r32));
    L25:
        r5 = r5 + 1;
        goto L13
    L26:
        View r43 = getChildAt(r0.intValue());
        ViewGroup.MarginLayoutParams r52 = (ViewGroup.MarginLayoutParams) r43.getLayoutParams();
        int r23 = ((r12 - r22) - r52.rightMargin) - r52.leftMargin;
        r43.measure(View.MeasureSpec.makeMeasureSpec(AnimationUtils.lerp(this.originalChildWidths[r0.intValue()], r23, r32) + Math.max(this.revealedWidth - r12, 0), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(this.originalChildHeights[r0.intValue()], TLObject.FLAG_30));
        if ((r43 instanceof MaterialButton) == false) goto L31;
        MaterialButton r44 = (MaterialButton) r43;
        if (r44.getIcon() == null) goto L31;
        r44.getIcon().setAlpha(255);
    L31:
        setMeasuredDimension(this.revealedWidth, this.intrinsicHeight);
        return;
    L9:
        r0 = findLastVisibleChildIndex();
        goto L10
    }

    private void measureByPreservingSwipeActionRatios(int r12) {
        int r0 = this.intrinsicWidth;
        int r02 = (int) AnimationUtils.lerp(0.0f, 255.0f, r0 / 4.0f, r0 / 2.0f, this.revealedWidth);
        float r1 = this.revealedWidth / this.intrinsicWidth;
        int r2 = (int) (getPaddingLeft() * r1);
        int r3 = (int) (getPaddingRight() * r1);
        int r4 = 0;
        int r5 = 0;
    L3:
        if (r4 >= r12) goto L13;
        View r6 = getChildAt(r4);
        if (r6.getVisibility() == 8) goto L12;
        int r7 = Math.max(this.minChildWidth, (int) (this.originalChildWidths[r4] * r1));
        r6.measure(View.MeasureSpec.makeMeasureSpec(r7, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(this.originalChildHeights[r4], TLObject.FLAG_30));
        ViewGroup.MarginLayoutParams r8 = (ViewGroup.MarginLayoutParams) r6.getLayoutParams();
        r5 = r5 + ((r7 + ((int) (r8.leftMargin * r1))) + ((int) (r8.rightMargin * r1)));
        if ((r6 instanceof MaterialButton) == false) goto L12;
        MaterialButton r62 = (MaterialButton) r6;
        if (r62.getIcon() == null) goto L12;
        r62.getIcon().setAlpha(r02);
    L12:
        r4 = r4 + 1;
        goto L3
    L13:
        setMeasuredDimension(Math.max(this.revealedWidth, (r5 + r2) + r3), this.intrinsicHeight);
    }

    private void saveOriginalChildSizes(int r5) {
        this.originalChildWidths = new int[r5];
        this.originalChildHeights = new int[r5];
        int r0 = 0;
    L3:
        if (r0 >= r5) goto L10;
        View r1 = getChildAt(r0);
        if (r1.getVisibility() == 8) goto L9;
        this.originalChildWidths[r0] = r1.getMeasuredWidth();
        this.originalChildHeights[r0] = r1.getMeasuredHeight();
        if (((ViewGroup.MarginLayoutParams) r1.getLayoutParams()).height != (-1)) goto L9;
        this.originalChildHeights[r0] = this.intrinsicHeight;
    L9:
        r0 = r0 + 1;
        goto L3
    }

    private boolean shouldRemeasureIntrinsicSizes(int r3, int r4, int r5) {
        if (r5 != (-1)) goto L6;
        return true;
    L6:
        if (r3 != r4) goto L8;
        return false;
    L8:
        int r32 = View.MeasureSpec.getMode(r4);
        if (r32 != 0) goto L11;
    L15:
        return false;
    L11:
        if (r32 == 1073741824) goto L13;
    L14:
        return true;
    L13:
        if (View.MeasureSpec.getSize(r4) == r5) goto L15;
        goto L14
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-2, -2);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet r2) {
        return new ViewGroup.MarginLayoutParams(getContext(), r2);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams r1) {
        return new ViewGroup.MarginLayoutParams(r1);
    }

    @Override // com.google.android.material.listitem.RevealableListItem
    public int getIntrinsicWidth() {
        int r1 = this.intrinsicWidth;
        if (r1 == (-1)) goto L5;
        return r1;
    L5:
        return 0;
    }

    public int getMinChildWidth() {
        return this.minChildWidth;
    }

    @Override // com.google.android.material.listitem.RevealableListItem
    public int getPrimaryActionSwipeMode() {
        return this.primaryActionSwipeMode;
    }

    public void measureIntrinsicSize(int r13, int r14) {
        int r0 = getChildCount();
        int r2 = 0;
        int r6 = 0;
        int r9 = 0;
        int r10 = 0;
    L3:
        if (r2 >= r0) goto L9;
        View r4 = this.getChildAt(r2);
        if (r4.getVisibility() != 8) goto L7;
        ListItemRevealLayout r3 = this;
        int r5 = r13;
        int r7 = r14;
    L8:
        r2 = r2 + 1;
        ListItemRevealLayout this = r3;
        r13 = r5;
        r14 = r7;
        goto L3
    L7:
        ViewGroup.MarginLayoutParams r11 = (ViewGroup.MarginLayoutParams) r4.getLayoutParams();
        r3 = this;
        r5 = r13;
        r7 = r14;
        r3.measureChildWithMargins(r4, r5, r6, r7, 0);
        r6 = r6 + ((r4.getMeasuredWidth() + r11.leftMargin) + r11.rightMargin);
        r9 = Math.max(r9, (r4.getMeasuredHeight() + r11.topMargin) + r11.bottomMargin);
        r10 = View.combineMeasuredStates(r10, r4.getMeasuredState());
        goto L8
    L9:
        ListItemRevealLayout r32 = this;
        int r52 = r13;
        int r72 = r14;
        int r12 = Math.max(r6 + (r32.getPaddingLeft() + r32.getPaddingRight()), r32.getSuggestedMinimumWidth());
        int r132 = View.resolveSizeAndState(Math.max(r9 + (r32.getPaddingTop() + r32.getPaddingBottom()), r32.getSuggestedMinimumHeight()), r72, 0) & 16777215;
        r32.intrinsicWidth = View.resolveSizeAndState(r12, r52, 0);
        r32.intrinsicHeight = View.resolveSizeAndState(r132, r72, r10 << 16);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean r11, int r12, int r13, int r14, int r15) {
        int r112 = this.revealedWidth;
        int r122 = this.intrinsicWidth;
        if (r112 < r122) goto L5;
        float r113 = 1.0f;
    L6:
        int r123 = (int) (getPaddingLeft() * r113);
        int r132 = getPaddingTop();
        int r142 = getChildCount();
        int r0 = 0;
        int r1 = 1;
        if (getLayoutDirection() != 1) goto L9;
        int r152 = r142 - 1;
        r1 = -1;
    L10:
        if (r0 >= r142) goto L16;
        View r2 = getChildAt((r1 * r0) + r152);
        if (r2.getVisibility() == 8) goto L15;
        ViewGroup.MarginLayoutParams r3 = (ViewGroup.MarginLayoutParams) r2.getLayoutParams();
        int r4 = r2.getMeasuredWidth();
        int r5 = r2.getMeasuredHeight();
        int r6 = r3.topMargin + r132;
        int r7 = (int) (r3.leftMargin * r113);
        int r8 = r123 + r7;
        r2.layout(r8, r6, r8 + r4, r5 + r6);
        r123 = r123 + ((r7 + r4) + ((int) (r3.rightMargin * r113)));
    L15:
        r0 = r0 + 1;
        goto L10
    L16:
        return;
    L9:
        r152 = 0;
        goto L10
    L5:
        r113 = r112 / r122;
        goto L6
    }

    @Override // android.view.View
    public void onMeasure(int r4, int r5) {
        int r0 = getChildCount();
        if (shouldRemeasureIntrinsicSizes(this.originalHeightMeasureSpec, r5, this.intrinsicHeight) == false) goto L5;
    L6:
        this.originalHeightMeasureSpec = r5;
        this.originalWidthMeasureSpec = r4;
        measureIntrinsicSize(r4, r5);
        saveOriginalChildSizes(r0);
    L7:
        WeakReference<View> r42 = this.siblingSwipeableView;
        if (r42 != null) goto L10;
    L11:
        this.siblingSwipeableView = new WeakReference(findSiblingSwipeableView());
    L13:
        if (this.siblingSwipeableView.get() == null) goto L15;
        int r43 = ((SwipeableListItem) this.siblingSwipeableView.get()).getSwipeMaxOvershoot();
    L16:
        int r1 = calculateFullRevealableWidth();
        if (this.revealedWidth != 0) goto L19;
        int r2 = 4;
    L20:
        setVisibility(r2);
        int r22 = this.revealedWidth;
        if (r22 != 0) goto L24;
        setMeasuredDimension(0, this.intrinsicHeight);
        return;
    L24:
        if (r0 != 0) goto L28;
        setMeasuredDimension(r22, this.intrinsicHeight);
        return;
    L28:
        if (this.primaryActionSwipeMode == 0) goto L34;
        int r52 = this.intrinsicWidth;
        if (r22 <= (r43 + r52)) goto L34;
        if (r1 <= r52) goto L34;
        measureByGrowingPrimarySwipeAction(r1);
        return;
    L34:
        measureByPreservingSwipeActionRatios(r0);
        return;
    L19:
        r2 = 0;
        goto L20
    L15:
        r43 = 0;
        goto L16
    L10:
        if (r42.get() != null) goto L13;
    L5:
        if (shouldRemeasureIntrinsicSizes(this.originalWidthMeasureSpec, r4, this.intrinsicWidth) == false) goto L7;
        goto L6
    }

    public void resetIntrinsicWidth() {
        this.intrinsicWidth = -1;
        requestLayout();
    }

    public void setMinChildWidth(int r2) {
        if (this.minChildWidth != r2) goto L5;
        return;
    L5:
        this.minChildWidth = r2;
        requestLayout();
    }

    @Override // com.google.android.material.listitem.RevealableListItem
    public void setPrimaryActionSwipeMode(int r1) {
        this.primaryActionSwipeMode = r1;
    }

    @Override // com.google.android.material.listitem.RevealableListItem
    public void setRevealedWidth(int r2) {
        int r22 = Math.max(0, r2);
        if (this.revealedWidth != r22) goto L5;
        return;
    L5:
        this.revealedWidth = r22;
        requestLayout();
    }
}
