package com.google.android.material.divider;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.R;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import kotlin.text.CharsKt__CharKt$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public class MaterialDividerItemDecoration extends RecyclerView.ItemDecoration {
    private static final int DEF_STYLE_RES = 0;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    private int color;
    private Drawable dividerDrawable;
    private int insetEnd;
    private int insetStart;
    private boolean lastItemDecorated;
    private int orientation;
    private final Rect tempRect;
    private int thickness;

    static {
        DEF_STYLE_RES = R.style.Widget_MaterialComponents_MaterialDivider;
    }

    public MaterialDividerItemDecoration(Context r2, int r3) {
        this(r2, null, r3);
    }

    public MaterialDividerItemDecoration(Context r2, AttributeSet r3, int r4) {
        this(r2, r3, R.attr.materialDividerStyle, r4);
    }

    public MaterialDividerItemDecoration(Context r8, AttributeSet r9, int r10, int r11) {
        this.tempRect = new Rect();
        TypedArray r82 = ThemeEnforcement.obtainStyledAttributes(r8, r9, R.styleable.MaterialDivider, r10, DEF_STYLE_RES, new int[0]);
        this.color = MaterialResources.getColorStateList(r8, r82, R.styleable.MaterialDivider_dividerColor).getDefaultColor();
        this.thickness = r82.getDimensionPixelSize(R.styleable.MaterialDivider_dividerThickness, r8.getResources().getDimensionPixelSize(R.dimen.material_divider_thickness));
        this.insetStart = r82.getDimensionPixelOffset(R.styleable.MaterialDivider_dividerInsetStart, 0);
        this.insetEnd = r82.getDimensionPixelOffset(R.styleable.MaterialDivider_dividerInsetEnd, 0);
        this.lastItemDecorated = r82.getBoolean(R.styleable.MaterialDivider_lastItemDecorated, true);
        r82.recycle();
        this.dividerDrawable = new ShapeDrawable();
        setDividerColor(this.color);
        setOrientation(r11);
    }

    private void drawForHorizontalOrientation(Canvas r10, RecyclerView r11) {
        r10.save();
        int r1 = 0;
        if (r11.getClipToPadding() == false) goto L5;
        int r0 = r11.getPaddingTop();
        int r2 = r11.getHeight() - r11.getPaddingBottom();
        r10.clipRect(r11.getPaddingLeft(), r0, r11.getWidth() - r11.getPaddingRight(), r2);
    L6:
        int r02 = r0 + this.insetStart;
        int r22 = r2 - this.insetEnd;
        boolean r3 = ViewUtils.isLayoutRtl(r11);
        int r4 = r11.getChildCount();
    L7:
        if (r1 >= r4) goto L16;
        View r5 = r11.getChildAt(r1);
        if (shouldDrawDivider(r11, r5) == false) goto L15;
        r11.getLayoutManager().getDecoratedBoundsWithMargins(r5, this.tempRect);
        int r6 = Math.round(r5.getTranslationX());
        Rect r7 = this.tempRect;
        if (r3 == false) goto L13;
        int r72 = r7.left + r6;
        int r62 = this.thickness + r72;
    L14:
        this.dividerDrawable.setBounds(r72, r02, r62, r22);
        this.dividerDrawable.setAlpha(Math.round(r5.getAlpha() * 255.0f));
        this.dividerDrawable.draw(r10);
        goto L15
    L13:
        r62 = r6 + r7.right;
        r72 = r62 - this.thickness;
    L15:
        r1 = r1 + 1;
        goto L7
    L16:
        r10.restore();
        return;
    L5:
        r2 = r11.getHeight();
        r0 = 0;
        goto L6
    }

    private void drawForVerticalOrientation(Canvas r9, RecyclerView r10) {
        r9.save();
        int r1 = 0;
        if (r10.getClipToPadding() == false) goto L5;
        int r0 = r10.getPaddingLeft();
        int r2 = r10.getWidth() - r10.getPaddingRight();
        r9.clipRect(r0, r10.getPaddingTop(), r2, r10.getHeight() - r10.getPaddingBottom());
    L6:
        boolean r3 = ViewUtils.isLayoutRtl(r10);
        if (r3 == false) goto L9;
        int r4 = this.insetEnd;
    L10:
        int r02 = r0 + r4;
        if (r3 == false) goto L13;
        int r32 = this.insetStart;
    L14:
        int r22 = r2 - r32;
        int r33 = r10.getChildCount();
    L15:
        if (r1 >= r33) goto L20;
        View r42 = r10.getChildAt(r1);
        if (shouldDrawDivider(r10, r42) == false) goto L19;
        r10.getLayoutManager().getDecoratedBoundsWithMargins(r42, this.tempRect);
        int r5 = this.tempRect.bottom + Math.round(r42.getTranslationY());
        this.dividerDrawable.setBounds(r02, r5 - this.thickness, r22, r5);
        this.dividerDrawable.setAlpha(Math.round(r42.getAlpha() * 255.0f));
        this.dividerDrawable.draw(r9);
    L19:
        r1 = r1 + 1;
        goto L15
    L20:
        r9.restore();
        return;
    L13:
        r32 = this.insetEnd;
        goto L14
    L9:
        r4 = this.insetStart;
        goto L10
    L5:
        r2 = r10.getWidth();
        r0 = 0;
        goto L6
    }

    private boolean shouldDrawDivider(RecyclerView r5, View r6) {
        int r62 = r5.getChildAdapterPosition(r6);
        RecyclerView.Adapter r52 = r5.getAdapter();
        if (r52 != null) goto L5;
    L7:
        boolean r2 = false;
    L9:
        if (r62 == (-1)) goto L16;
        if (r2 == false) goto L14;
        if (this.lastItemDecorated == false) goto L16;
    L14:
        if (shouldDrawDivider(r62, r52) == false) goto L16;
        return true;
    L16:
        return false;
    L5:
        if (r62 != (r52.getItemCount() - 1)) goto L7;
        r2 = true;
        goto L9
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

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect r1, View r2, RecyclerView r3, RecyclerView.State r4) {
        r1.set(0, 0, 0, 0);
        if (shouldDrawDivider(r3, r2) == true) goto L5;
        return;
    L5:
        if (this.orientation != 1) goto L8;
        r1.bottom = this.thickness;
        return;
    L8:
        boolean r22 = ViewUtils.isLayoutRtl(r3);
        int r0 = this.thickness;
        if (r22 == false) goto L12;
        r1.left = r0;
        return;
    L12:
        r1.right = r0;
    }

    public int getOrientation() {
        return this.orientation;
    }

    public boolean isLastItemDecorated() {
        return this.lastItemDecorated;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas r2, RecyclerView r3, RecyclerView.State r4) {
        if (r3.getLayoutManager() != null) goto L6;
        return;
    L6:
        if (this.orientation != 1) goto L9;
        drawForVerticalOrientation(r2, r3);
        return;
    L9:
        drawForHorizontalOrientation(r2, r3);
    }

    public void setDividerColor(int r2) {
        this.color = r2;
        Drawable r0 = DrawableCompat.wrap(this.dividerDrawable);
        this.dividerDrawable = r0;
        r0.setTint(r2);
    }

    public void setDividerColorResource(Context r1, int r2) {
        setDividerColor(ContextCompat.getColor(r1, r2));
    }

    public void setDividerInsetEnd(int r1) {
        this.insetEnd = r1;
    }

    public void setDividerInsetEndResource(Context r1, int r2) {
        setDividerInsetEnd(r1.getResources().getDimensionPixelOffset(r2));
    }

    public void setDividerInsetStart(int r1) {
        this.insetStart = r1;
    }

    public void setDividerInsetStartResource(Context r1, int r2) {
        setDividerInsetStart(r1.getResources().getDimensionPixelOffset(r2));
    }

    public void setDividerThickness(int r1) {
        this.thickness = r1;
    }

    public void setDividerThicknessResource(Context r1, int r2) {
        setDividerThickness(r1.getResources().getDimensionPixelSize(r2));
    }

    public void setLastItemDecorated(boolean r1) {
        this.lastItemDecorated = r1;
    }

    public void setOrientation(int r2) {
        if (r2 != 0) goto L4;
    L8:
        this.orientation = r2;
        return;
    L4:
        if (r2 == 1) goto L8;
        CharsKt__CharKt$$ExternalSyntheticBUOutline0.m("Invalid orientation: ", r2, ". It should be either HORIZONTAL or VERTICAL");
    }

    public boolean shouldDrawDivider(int r1, RecyclerView.Adapter<?> r2) {
        return true;
    }
}
