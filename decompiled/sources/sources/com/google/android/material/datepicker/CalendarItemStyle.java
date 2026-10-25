package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.widget.TextView;
import androidx.core.util.Preconditions;
import com.google.android.material.R;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;

/* loaded from: classes5.dex */
final class CalendarItemStyle {
    private final ColorStateList backgroundColor;
    private final Rect insets;
    private final ShapeAppearanceModel itemShape;
    private final ColorStateList strokeColor;
    private final int strokeWidth;
    private final ColorStateList textColor;

    private CalendarItemStyle(ColorStateList r2, ColorStateList r3, ColorStateList r4, int r5, ShapeAppearanceModel r6, Rect r7) {
        Preconditions.checkArgumentNonnegative(r7.left);
        Preconditions.checkArgumentNonnegative(r7.top);
        Preconditions.checkArgumentNonnegative(r7.right);
        Preconditions.checkArgumentNonnegative(r7.bottom);
        this.insets = r7;
        this.textColor = r3;
        this.backgroundColor = r2;
        this.strokeColor = r4;
        this.strokeWidth = r5;
        this.itemShape = r6;
    }

    public static CalendarItemStyle create(Context r12, int r13) {
        if (r13 == 0) goto L5;
        boolean r1 = true;
    L6:
        Preconditions.checkArgument(r1, "Cannot create a CalendarItemStyle with a styleResId of 0");
        TypedArray r132 = r12.obtainStyledAttributes(r13, R.styleable.MaterialCalendarItem);
        Rect r11 = new Rect(r132.getDimensionPixelOffset(R.styleable.MaterialCalendarItem_android_insetLeft, 0), r132.getDimensionPixelOffset(R.styleable.MaterialCalendarItem_android_insetTop, 0), r132.getDimensionPixelOffset(R.styleable.MaterialCalendarItem_android_insetRight, 0), r132.getDimensionPixelOffset(R.styleable.MaterialCalendarItem_android_insetBottom, 0));
        ColorStateList r6 = MaterialResources.getColorStateList(r12, r132, R.styleable.MaterialCalendarItem_itemFillColor);
        ColorStateList r7 = MaterialResources.getColorStateList(r12, r132, R.styleable.MaterialCalendarItem_itemTextColor);
        ColorStateList r8 = MaterialResources.getColorStateList(r12, r132, R.styleable.MaterialCalendarItem_itemStrokeColor);
        int r9 = r132.getDimensionPixelSize(R.styleable.MaterialCalendarItem_itemStrokeWidth, 0);
        ShapeAppearanceModel r10 = ShapeAppearanceModel.builder(r12, r132.getResourceId(R.styleable.MaterialCalendarItem_itemShapeAppearance, 0), r132.getResourceId(R.styleable.MaterialCalendarItem_itemShapeAppearanceOverlay, 0)).build();
        r132.recycle();
        return new CalendarItemStyle(r6, r7, r8, r9, r10, r11);
    L5:
        r1 = false;
        goto L6
    }

    public int getBottomInset() {
        return this.insets.bottom;
    }

    public ShapeAppearanceModel getItemShapeAppearanceModel() {
        return this.itemShape;
    }

    public int getLeftInset() {
        return this.insets.left;
    }

    public int getRightInset() {
        return this.insets.right;
    }

    public int getTopInset() {
        return this.insets.top;
    }

    public void styleItem(TextView r2) {
        styleItem(r2, null, null);
    }

    public void styleItem(TextView r9, ColorStateList r10, ColorStateList r11) {
        MaterialShapeDrawable r0 = new MaterialShapeDrawable();
        MaterialShapeDrawable r1 = new MaterialShapeDrawable();
        r0.setShapeAppearanceModel(this.itemShape);
        r1.setShapeAppearanceModel(this.itemShape);
        if (r10 != null) goto L6;
        r10 = this.backgroundColor;
    L6:
        r0.setFillColor(r10);
        r0.setStroke(this.strokeWidth, this.strokeColor);
        if (r11 != null) goto L10;
        r11 = this.textColor;
    L10:
        r9.setTextColor(r11);
        RippleDrawable r3 = new RippleDrawable(this.textColor.withAlpha(30), r0, r1);
        Rect r8 = this.insets;
        r9.setBackground(new InsetDrawable(r3, r8.left, r8.top, r8.right, r8.bottom));
    }
}
