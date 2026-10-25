package com.google.android.material.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.R;

/* loaded from: classes5.dex */
public class FlowLayout extends ViewGroup {
    private int itemSpacing;
    private int lineSpacing;
    private int rowCount;
    private boolean singleLine;

    public FlowLayout(Context r2) {
        this(r2, null);
    }

    public FlowLayout(Context r2, AttributeSet r3) {
        this(r2, r3, 0);
    }

    public FlowLayout(Context r1, AttributeSet r2, int r3) {
        super(r1, r2, r3);
        this.singleLine = false;
        loadFromAttributes(r1, r2);
    }

    public FlowLayout(Context r1, AttributeSet r2, int r3, int r4) {
        super(r1, r2, r3, r4);
        this.singleLine = false;
        loadFromAttributes(r1, r2);
    }

    private static int getMeasuredDimension(int r1, int r2, int r3) {
        if (r2 == Integer.MIN_VALUE) goto L9;
        if (r2 == 1073741824) goto L7;
        return r3;
    L7:
        return r1;
    L9:
        return Math.min(r3, r1);
    }

    private void loadFromAttributes(Context r3, AttributeSet r4) {
        TypedArray r32 = r3.getTheme().obtainStyledAttributes(r4, R.styleable.FlowLayout, 0, 0);
        this.lineSpacing = r32.getDimensionPixelSize(R.styleable.FlowLayout_lineSpacing, 0);
        this.itemSpacing = r32.getDimensionPixelSize(R.styleable.FlowLayout_horizontalItemSpacing, 0);
        r32.recycle();
    }

    public int getItemSpacing() {
        return this.itemSpacing;
    }

    public int getLineSpacing() {
        return this.lineSpacing;
    }

    public int getRowCount() {
        return this.rowCount;
    }

    public int getRowIndex(View r1) {
        Object r0 = r1.getTag(R.id.row_index_key);
        if ((r0 instanceof Integer) == true) goto L7;
        return -1;
    L7:
        return ((Integer) r0).intValue();
    }

    public boolean isSingleLine() {
        return this.singleLine;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean r17, int r18, int r19, int r20, int r21) {
        if (getChildCount() != 0) goto L6;
        this.rowCount = 0;
        return;
    L6:
        boolean r1 = true;
        this.rowCount = 1;
        if (getLayoutDirection() != 1) goto L9;
        boolean r3 = true;
    L10:
        if (r3 == false) goto L12;
        int r4 = getPaddingRight();
    L13:
        if (r3 == false) goto L15;
        int r5 = getPaddingLeft();
    L16:
        int r6 = getPaddingTop();
        int r8 = 0;
        int r9 = r4;
        int r7 = r6;
    L18:
        if (r8 >= getChildCount()) goto L36;
        View r10 = getChildAt(r8);
        if (r10.getVisibility() != 8) goto L22;
        r10.setTag(R.id.row_index_key, -1);
        boolean r172 = r1;
    L35:
        r8 = r8 + 1;
        r1 = r172;
        goto L18
    L22:
        ViewGroup.LayoutParams r11 = r10.getLayoutParams();
        if ((r11 instanceof ViewGroup.MarginLayoutParams) == false) goto L25;
        ViewGroup.MarginLayoutParams r112 = (ViewGroup.MarginLayoutParams) r11;
        int r12 = r112.getMarginStart();
        int r113 = r112.getMarginEnd();
    L26:
        int r13 = (r9 + r12) + r10.getMeasuredWidth();
        int r14 = r20 - r18;
        int r15 = r14 - r5;
        r172 = r1;
        if (this.singleLine == true) goto L30;
        if (r13 <= r15) goto L30;
        r13 = (r4 + r12) + r10.getMeasuredWidth();
        r7 = r6 + this.lineSpacing;
        this.rowCount++;
        r9 = r4;
    L30:
        r10.setTag(R.id.row_index_key, Integer.valueOf(this.rowCount - 1));
        int r16 = r10.getMeasuredHeight() + r7;
        if (r3 == false) goto L33;
        r10.layout(r14 - r13, r7, (r14 - r9) - r12, r16);
    L34:
        r9 = r9 + (((r12 + r113) + r10.getMeasuredWidth()) + this.itemSpacing);
        r6 = r16;
        goto L35
    L33:
        r10.layout(r9 + r12, r7, r13, r16);
        goto L34
    L25:
        r113 = 0;
        r12 = 0;
        goto L26
    L36:
        return;
    L15:
        r5 = getPaddingRight();
        goto L16
    L12:
        r4 = getPaddingLeft();
        goto L13
    L9:
        r3 = false;
        goto L10
    }

    @Override // android.view.View
    public void onMeasure(int r20, int r21) {
        int r1 = View.MeasureSpec.getSize(r20);
        int r2 = View.MeasureSpec.getMode(r20);
        int r3 = View.MeasureSpec.getSize(r21);
        int r4 = View.MeasureSpec.getMode(r21);
        if (r2 != Integer.MIN_VALUE) goto L5;
    L8:
        int r5 = r1;
    L9:
        int r6 = getPaddingLeft();
        int r7 = getPaddingTop();
        int r52 = r5 - getPaddingRight();
        int r9 = r7;
        int r10 = 0;
        int r11 = 0;
    L11:
        if (r10 >= getChildCount()) goto L33;
        View r12 = getChildAt(r10);
        if (r12.getVisibility() == 8) goto L32;
        measureChild(r12, r20, r21);
        ViewGroup.LayoutParams r15 = r12.getLayoutParams();
        if ((r15 instanceof ViewGroup.MarginLayoutParams) == false) goto L18;
        ViewGroup.MarginLayoutParams r152 = (ViewGroup.MarginLayoutParams) r15;
        int r8 = r152.leftMargin;
        int r153 = r152.rightMargin;
    L19:
        int r18 = r6;
        if (((r6 + r8) + r12.getMeasuredWidth()) > r52) goto L22;
    L24:
        int r62 = r18;
    L25:
        int r72 = (r62 + r8) + r12.getMeasuredWidth();
        int r16 = r9 + r12.getMeasuredHeight();
        if (r72 <= r11) goto L28;
        r11 = r72;
    L28:
        r6 = r62 + (((r8 + r153) + r12.getMeasuredWidth()) + this.itemSpacing);
        if (r10 != (getChildCount() - 1)) goto L31;
        r11 = r11 + r153;
    L31:
        r7 = r16;
        goto L32
    L22:
        if (isSingleLine() == true) goto L24;
        r62 = getPaddingLeft();
        r9 = this.lineSpacing + r7;
        goto L25
    L18:
        r8 = 0;
        r153 = 0;
    L32:
        r10 = r10 + 1;
        goto L11
    L33:
        setMeasuredDimension(getMeasuredDimension(r1, r2, r11 + getPaddingRight()), getMeasuredDimension(r3, r4, r7 + getPaddingBottom()));
        return;
    L5:
        if (r2 == 1073741824) goto L8;
        r5 = Integer.MAX_VALUE;
        goto L9
    }

    public void setItemSpacing(int r1) {
        this.itemSpacing = r1;
    }

    public void setLineSpacing(int r1) {
        this.lineSpacing = r1;
    }

    public void setSingleLine(boolean r1) {
        this.singleLine = r1;
    }
}
