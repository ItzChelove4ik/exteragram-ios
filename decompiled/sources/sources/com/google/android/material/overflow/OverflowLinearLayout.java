package com.google.android.material.overflow;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.TintTypedArray;
import androidx.appcompat.widget.TooltipCompat;
import com.google.android.material.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonGroup;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/* loaded from: classes5.dex */
public class OverflowLinearLayout extends LinearLayout {
    private static final int DEF_STYLE_RES = 0;
    private final MaterialButton overflowButton;
    private boolean overflowButtonAdded;
    private final Set<View> overflowViews;

    public static class LayoutParams extends LinearLayout.LayoutParams {
        public Drawable overflowIcon;
        public CharSequence overflowText;

        public LayoutParams(int r1, int r2) {
            super(r1, r2);
            this.overflowIcon = null;
            this.overflowText = null;
        }

        public LayoutParams(int r1, int r2, float r3) {
            super(r1, r2, r3);
            this.overflowIcon = null;
            this.overflowText = null;
        }

        public LayoutParams(int r1, int r2, float r3, Drawable r4, CharSequence r5) {
            super(r1, r2, r3);
            this.overflowIcon = r4;
            this.overflowText = r5;
        }

        public LayoutParams(Context r2, AttributeSet r3) {
            super(r2, r3);
            this.overflowIcon = null;
            this.overflowText = null;
            TypedArray r22 = r2.obtainStyledAttributes(r3, R.styleable.OverflowLinearLayout_Layout);
            this.overflowIcon = r22.getDrawable(R.styleable.OverflowLinearLayout_Layout_layout_overflowIcon);
            this.overflowText = r22.getText(R.styleable.OverflowLinearLayout_Layout_layout_overflowText);
            r22.recycle();
        }

        public LayoutParams(ViewGroup.LayoutParams r1) {
            super(r1);
            this.overflowIcon = null;
            this.overflowText = null;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams r1) {
            super(r1);
            this.overflowIcon = null;
            this.overflowText = null;
        }

        public LayoutParams(LinearLayout.LayoutParams r1) {
            super(r1);
            this.overflowIcon = null;
            this.overflowText = null;
        }

        public LayoutParams(LayoutParams r2) {
            super(r2);
            this.overflowIcon = null;
            this.overflowText = null;
            this.overflowText = r2.overflowText;
            this.overflowIcon = r2.overflowIcon;
        }
    }

    public static /* synthetic */ boolean $r8$lambda$1s1OaeEBNkKDR2VYkF28_VV0Jtk(View r0, MenuItem r1, MenuItem r2) {
        r0.performClick();
        if (r1.isCheckable() == false) goto L5;
        r1.setChecked(!r1.isChecked());
    L5:
        return true;
    }

    /* renamed from: $r8$lambda$mTBnn7CqC-5eYM1sT6pXVfnP_R8, reason: not valid java name */
    public static /* synthetic */ void m1887$r8$lambda$mTBnn7CqC5eYM1sT6pXVfnP_R8(OverflowLinearLayout r0, PopupMenu r1, int r2, View r3) {
        r0.handleOverflowButtonClick(r1, r2);
    }

    static {
        DEF_STYLE_RES = R.style.Widget_Material3_OverflowLinearLayout;
    }

    public OverflowLinearLayout(Context r2) {
        this(r2, null);
    }

    public OverflowLinearLayout(Context r2, AttributeSet r3) {
        this(r2, r3, R.attr.overflowLinearLayoutStyle);
    }

    public OverflowLinearLayout(Context r8, AttributeSet r9, int r10) {
        int r4 = DEF_STYLE_RES;
        super(MaterialThemeOverlay.wrap(r8, r9, r10, r4), r9, r10);
        this.overflowButtonAdded = false;
        this.overflowViews = new LinkedHashSet();
        Context r0 = getContext();
        TintTypedArray r92 = ThemeEnforcement.obtainTintedStyledAttributes(r0, r9, R.styleable.OverflowLinearLayout, r10, r4, new int[0]);
        Drawable r102 = r92.getDrawable(R.styleable.OverflowLinearLayout_overflowButtonIcon);
        r92.recycle();
        MaterialButton r3 = (MaterialButton) LayoutInflater.from(r0).inflate(R.layout.m3_overflow_linear_layout_overflow_button, this, false);
        this.overflowButton = r3;
        TooltipCompat.setTooltipText(r3, getResources().getString(R.string.m3_overflow_linear_layout_button_tooltip_text));
        setOverflowButtonIcon(r102);
        if (r3.getContentDescription() != null) goto L5;
        r3.setContentDescription(r0.getString(R.string.m3_overflow_linear_layout_button_content_description));
    L5:
        int r6 = MaterialAttributes.resolveOrThrow(this, R.attr.overflowLinearLayoutPopupMenuStyle);
        r3.setOnClickListener(new OverflowLinearLayout$$ExternalSyntheticLambda1(this, new PopupMenu(getContext(), r3, 17, 0, r6), r0.getResources().getDimensionPixelOffset(R.dimen.m3_overflow_item_icon_horizontal_padding)));
    }

    private int getChildSize(boolean r1, View r2, int r3, int r4) {
        measureChild(r2, r3, r4);
        LayoutParams r0 = (LayoutParams) r2.getLayoutParams();
        if (r1 == false) goto L6;
        int r32 = r2.getMeasuredWidth() + ((LinearLayout.LayoutParams) r0).leftMargin;
        int r42 = ((LinearLayout.LayoutParams) r0).rightMargin;
    L5:
        int r33 = r32 + r42;
        if (r33 != 0) goto L13;
        if (r1 == false) goto L12;
        int r12 = r2.getMinimumWidth() + ((LinearLayout.LayoutParams) r0).leftMargin;
        int r02 = ((LinearLayout.LayoutParams) r0).rightMargin;
    L11:
        return r12 + r02;
    L12:
        r12 = r2.getMinimumHeight() + ((LinearLayout.LayoutParams) r0).topMargin;
        r02 = ((LinearLayout.LayoutParams) r0).bottomMargin;
        goto L11
    L13:
        return r33;
    L6:
        r32 = r2.getMeasuredHeight() + ((LinearLayout.LayoutParams) r0).topMargin;
        r42 = ((LinearLayout.LayoutParams) r0).bottomMargin;
        goto L5
    }

    private int getOverflowButtonSize(boolean r1, View r2, int r3, int r4) {
        measureChild(r2, r3, r4);
        LinearLayout.LayoutParams r0 = (LinearLayout.LayoutParams) r2.getLayoutParams();
        if (r1 == false) goto L7;
        int r12 = r2.getMeasuredWidth() + r0.leftMargin;
        int r02 = r0.rightMargin;
    L6:
        return r12 + r02;
    L7:
        r12 = r2.getMeasuredHeight() + r0.topMargin;
        r02 = r0.bottomMargin;
        goto L6
    }

    private void handleOverflowButtonClick(PopupMenu r10, int r11) {
        r10.getMenu().clear();
        r10.setForceShowIcon(true);
        Iterator<View> r9 = this.overflowViews.iterator();
    L4:
        if (r9.hasNext() == false) goto L13;
        View r0 = r9.next();
        LayoutParams r1 = (LayoutParams) r0.getLayoutParams();
        CharSequence r2 = MaterialButtonGroup.OverflowUtils.getMenuItemText(r0, r1.overflowText);
        MenuItem r22 = r10.getMenu().add(r2);
        Drawable r4 = r1.overflowIcon;
        if (r4 == null) goto L8;
        int r5 = r11;
        r22.setIcon(new InsetDrawable(r4, r5, 0, r11, 0));
    L10:
        if ((r0 instanceof MaterialButton) == false) goto L12;
        MaterialButton r112 = (MaterialButton) r0;
        r22.setCheckable(r112.isCheckable());
        r22.setChecked(r112.isChecked());
    L12:
        r22.setEnabled(r0.isEnabled());
        r22.setOnMenuItemClickListener(new OverflowLinearLayout$$ExternalSyntheticLambda0(r0, r22));
        r11 = r5;
        goto L4
    L8:
        r5 = r11;
        goto L10
    L13:
        r10.show();
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams r1) {
        return r1 instanceof LayoutParams;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return generateDefaultLayoutParams();
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public /* bridge */ /* synthetic */ LinearLayout.LayoutParams generateDefaultLayoutParams() {
        return generateDefaultLayoutParams();
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        if (getOrientation() != 0) goto L7;
        return new LayoutParams(-2, -2);
    L7:
        return new LayoutParams(-1, -2);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet r1) {
        return generateLayoutParams(r1);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams r1) {
        return generateLayoutParams(r1);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public /* bridge */ /* synthetic */ LinearLayout.LayoutParams generateLayoutParams(AttributeSet r1) {
        return generateLayoutParams(r1);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public /* bridge */ /* synthetic */ LinearLayout.LayoutParams generateLayoutParams(ViewGroup.LayoutParams r1) {
        return generateLayoutParams(r1);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet r2) {
        return new LayoutParams(getContext(), r2);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams r1) {
        if ((r1 instanceof LayoutParams) == false) goto L7;
        return new LayoutParams(r1);
    L7:
        if ((r1 instanceof LinearLayout.LayoutParams) == false) goto L11;
        return new LayoutParams((LinearLayout.LayoutParams) r1);
    L11:
        if ((r1 instanceof ViewGroup.MarginLayoutParams) == false) goto L15;
        return new LayoutParams((ViewGroup.MarginLayoutParams) r1);
    L15:
        return new LayoutParams(r1);
    }

    public Drawable getOverflowButtonIcon() {
        return this.overflowButton.getIcon();
    }

    public Set<View> getOverflowedViews() {
        return this.overflowViews;
    }

    public boolean isOverflowed() {
        return !this.overflowViews.isEmpty();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int r12, int r13) {
        if (getOrientation() != 0) goto L5;
        boolean r0 = true;
    L7:
        if (this.overflowButtonAdded == false) goto L9;
        int r3 = getChildCount() - 1;
    L10:
        if (r0 == false) goto L12;
        int r4 = View.MeasureSpec.getSize(r12);
    L13:
        int r5 = getOverflowButtonSize(r0, this.overflowButton, r12, r13);
        this.overflowButton.setVisibility(8);
        this.overflowViews.clear();
        int r6 = 0;
        int r8 = 0;
    L14:
        if (r6 >= r3) goto L32;
        View r9 = getChildAt(r6);
        r9.setVisibility(0);
        r8 = r8 + getChildSize(r0, r9, r12, r13);
        if ((r8 + r5) <= r4) goto L18;
        this.overflowViews.add(r9);
    L18:
        if (r8 > r4) goto L19;
        r6 = r6 + 1;
        goto L14
    L19:
        int r62 = r6 + 1;
    L20:
        Set<View> r02 = this.overflowViews;
        if (r62 >= r3) goto L23;
        r02.add(getChildAt(r62));
        r62 = r62 + 1;
        goto L20
    L23:
        Iterator<View> r03 = r02.iterator();
    L25:
        if (r03.hasNext() == false) goto L28;
        r03.next().setVisibility(8);
        goto L25
    L28:
        if (this.overflowButtonAdded == true) goto L30;
        addView(this.overflowButton);
        this.overflowButtonAdded = true;
    L30:
        this.overflowButton.setVisibility(0);
    L33:
        super.onMeasure(r12, r13);
        return;
    L32:
        this.overflowButton.setVisibility(8);
        this.overflowViews.clear();
        goto L33
    L12:
        r4 = View.MeasureSpec.getSize(r13);
        goto L13
    L9:
        r3 = getChildCount();
        goto L10
    L5:
        r0 = false;
        goto L7
    }

    public void setOverflowButtonIcon(Drawable r1) {
        this.overflowButton.setIcon(r1);
    }

    public void setOverflowButtonIconResource(int r1) {
        this.overflowButton.setIconResource(r1);
    }
}
