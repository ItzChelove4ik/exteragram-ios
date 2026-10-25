package com.google.android.material.divider;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.google.android.material.R;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;

/* loaded from: classes5.dex */
public class MaterialDivider extends View {
    private static final int DEF_STYLE_RES = 0;
    private int color;
    private final MaterialShapeDrawable dividerDrawable;
    private int insetEnd;
    private int insetStart;
    private int thickness;

    static {
        DEF_STYLE_RES = R.style.Widget_MaterialComponents_MaterialDivider;
    }

    public MaterialDivider(Context r2) {
        this(r2, null);
    }

    public MaterialDivider(Context r2, AttributeSet r3) {
        this(r2, r3, R.attr.materialDividerStyle);
    }

    public MaterialDivider(Context r7, AttributeSet r8, int r9) {
        int r4 = DEF_STYLE_RES;
        super(MaterialThemeOverlay.wrap(r7, r8, r9, r4), r8, r9);
        Context r0 = getContext();
        this.dividerDrawable = new MaterialShapeDrawable();
        TypedArray r82 = ThemeEnforcement.obtainStyledAttributes(r0, r8, R.styleable.MaterialDivider, r9, r4, new int[0]);
        this.thickness = r82.getDimensionPixelSize(R.styleable.MaterialDivider_dividerThickness, getResources().getDimensionPixelSize(R.dimen.material_divider_thickness));
        this.insetStart = r82.getDimensionPixelOffset(R.styleable.MaterialDivider_dividerInsetStart, 0);
        this.insetEnd = r82.getDimensionPixelOffset(R.styleable.MaterialDivider_dividerInsetEnd, 0);
        setDividerColor(MaterialResources.getColorStateList(r0, r82, R.styleable.MaterialDivider_dividerColor).getDefaultColor());
        r82.recycle();
    }

    public int getDividerColor() {
        return this.color;
    }

    public int getDividerInsetEnd() {
        return this.insetEnd;
    }

    public int getDividerInsetStart() {
        return this.insetStart;
    }

    public int getDividerThickness() {
        return this.thickness;
    }

    @Override // android.view.View
    public void onDraw(Canvas r7) {
        super.onDraw(r7);
        boolean r2 = true;
        if (getLayoutDirection() == 1) goto L6;
        r2 = false;
    L6:
        if (r2 == false) goto L8;
        int r0 = this.insetEnd;
    L9:
        if (r2 == false) goto L12;
        int r22 = getWidth();
        int r3 = this.insetStart;
    L13:
        this.dividerDrawable.setBounds(r0, 0, r22 - r3, getBottom() - getTop());
        this.dividerDrawable.draw(r7);
        return;
    L12:
        r22 = getWidth();
        r3 = this.insetEnd;
        goto L13
    L8:
        r0 = this.insetStart;
        goto L9
    }

    @Override // android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(r2, r3);
        int r22 = View.MeasureSpec.getMode(r3);
        int r32 = getMeasuredHeight();
        if (r22 == Integer.MIN_VALUE) goto L7;
        if (r22 == 0) goto L7;
        return;
    L7:
        int r23 = this.thickness;
        if (r23 <= 0) goto L11;
        if (r32 == r23) goto L11;
        r32 = r23;
    L11:
        setMeasuredDimension(getMeasuredWidth(), r32);
    }

    public void setDividerColor(int r2) {
        if (this.color == r2) goto L6;
        this.color = r2;
        this.dividerDrawable.setFillColor(ColorStateList.valueOf(r2));
        invalidate();
        return;
    }

    public void setDividerColorResource(int r2) {
        setDividerColor(ContextCompat.getColor(getContext(), r2));
    }

    public void setDividerInsetEnd(int r1) {
        this.insetEnd = r1;
    }

    public void setDividerInsetEndResource(int r2) {
        setDividerInsetEnd(getContext().getResources().getDimensionPixelOffset(r2));
    }

    public void setDividerInsetStart(int r1) {
        this.insetStart = r1;
    }

    public void setDividerInsetStartResource(int r2) {
        setDividerInsetStart(getContext().getResources().getDimensionPixelOffset(r2));
    }

    public void setDividerThickness(int r2) {
        if (this.thickness == r2) goto L6;
        this.thickness = r2;
        requestLayout();
        return;
    }

    public void setDividerThicknessResource(int r2) {
        setDividerThickness(getContext().getResources().getDimensionPixelSize(r2));
    }
}
