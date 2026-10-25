package com.google.android.material.appbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;
import com.google.android.material.R;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ToolbarUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import org.telegram.tgnet.TLObject;

/* loaded from: classes5.dex */
public class MaterialToolbar extends Toolbar {
    private static final int DEF_STYLE_RES = 0;
    private static final ImageView.ScaleType[] LOGO_SCALE_TYPE_ARRAY = null;
    private Boolean logoAdjustViewBounds;
    private ImageView.ScaleType logoScaleType;
    private Integer navigationIconTint;
    private boolean subtitleCentered;
    private boolean titleCentered;

    static {
        DEF_STYLE_RES = R.style.Widget_MaterialComponents_Toolbar;
        LOGO_SCALE_TYPE_ARRAY = new ImageView.ScaleType[]{ImageView.ScaleType.MATRIX, ImageView.ScaleType.FIT_XY, ImageView.ScaleType.FIT_START, ImageView.ScaleType.FIT_CENTER, ImageView.ScaleType.FIT_END, ImageView.ScaleType.CENTER, ImageView.ScaleType.CENTER_CROP, ImageView.ScaleType.CENTER_INSIDE};
    }

    public MaterialToolbar(Context r2) {
        this(r2, null);
    }

    public MaterialToolbar(Context r2, AttributeSet r3) {
        this(r2, r3, R$attr.toolbarStyle);
    }

    public MaterialToolbar(Context r7, AttributeSet r8, int r9) {
        int r4 = DEF_STYLE_RES;
        super(MaterialThemeOverlay.wrap(r7, r8, r9, r4), r8, r9);
        Context r0 = getContext();
        TypedArray r82 = ThemeEnforcement.obtainStyledAttributes(r0, r8, R.styleable.MaterialToolbar, r9, r4, new int[0]);
        if (r82.hasValue(R.styleable.MaterialToolbar_navigationIconTint) == false) goto L5;
        setNavigationIconTint(r82.getColor(R.styleable.MaterialToolbar_navigationIconTint, -1));
    L5:
        this.titleCentered = r82.getBoolean(R.styleable.MaterialToolbar_titleCentered, false);
        this.subtitleCentered = r82.getBoolean(R.styleable.MaterialToolbar_subtitleCentered, false);
        int r92 = r82.getInt(R.styleable.MaterialToolbar_logoScaleType, -1);
        if (r92 < 0) goto L11;
        ImageView.ScaleType[] r2 = LOGO_SCALE_TYPE_ARRAY;
        if (r92 >= r2.length) goto L11;
        this.logoScaleType = r2[r92];
    L11:
        if (r82.hasValue(R.styleable.MaterialToolbar_logoAdjustViewBounds) == false) goto L13;
        this.logoAdjustViewBounds = Boolean.valueOf(r82.getBoolean(R.styleable.MaterialToolbar_logoAdjustViewBounds, false));
    L13:
        r82.recycle();
        initBackground(r0, ShapeAppearanceModel.builder(r0, r8, r9, r4).build());
    }

    private Pair<Integer, Integer> calculateTitleBoundLimits(TextView r8, TextView r9) {
        int r0 = getMeasuredWidth();
        int r1 = r0 / 2;
        int r2 = getPaddingLeft();
        int r02 = r0 - getPaddingRight();
        int r3 = 0;
    L4:
        if (r3 >= getChildCount()) goto L21;
        View r4 = getChildAt(r3);
        if (r4.getVisibility() == 8) goto L19;
        if (r4 == r8) goto L19;
        if (r4 == r9) goto L19;
        if (r4.getRight() >= r1) goto L15;
        if (r4.getRight() <= r2) goto L15;
        r2 = r4.getRight();
    L15:
        if (r4.getLeft() <= r1) goto L19;
        if (r4.getLeft() >= r02) goto L19;
        r02 = r4.getLeft();
    L19:
        r3 = r3 + 1;
        goto L4
    L21:
        return new Pair(Integer.valueOf(r2), Integer.valueOf(r02));
    }

    private void initBackground(Context r3, ShapeAppearanceModel r4) {
        Drawable r0 = getBackground();
        if (r0 != null) goto L5;
        ColorStateList r02 = ColorStateList.valueOf(0);
    L6:
        if (r02 == null) goto L9;
        MaterialShapeDrawable r1 = new MaterialShapeDrawable(r4);
        r1.setFillColor(r02);
        r1.initializeElevationOverlay(r3);
        r1.setElevation(getElevation());
        setBackground(r1);
        return;
    L9:
        return;
    L5:
        r02 = DrawableUtils.getColorStateListOrNull(r0);
        goto L6
    }

    private void layoutTitleCenteredHorizontally(View r4, Pair<Integer, Integer> r5) {
        int r3 = getMeasuredWidth();
        int r0 = r4.getMeasuredWidth();
        int r32 = (r3 / 2) - (r0 / 2);
        int r02 = r0 + r32;
        int r52 = Math.max(Math.max(((Integer) r5.first).intValue() - r32, 0), Math.max(r02 - ((Integer) r5.second).intValue(), 0));
        if (r52 <= 0) goto L5;
        r32 = r32 + r52;
        r02 = r02 - r52;
        r4.measure(View.MeasureSpec.makeMeasureSpec(r02 - r32, TLObject.FLAG_30), r4.getMeasuredHeightAndState());
    L5:
        r4.layout(r32, r4.getTop(), r02, r4.getBottom());
    }

    private void maybeCenterTitleViews() {
        if (this.titleCentered == false) goto L5;
    L7:
        TextView r0 = ToolbarUtils.getTitleTextView(this);
        TextView r1 = ToolbarUtils.getSubtitleTextView(this);
        if (r0 != null) goto L11;
        if (r1 != null) goto L11;
        return;
    L11:
        Pair<Integer, Integer> r2 = calculateTitleBoundLimits(r0, r1);
        if (this.titleCentered == false) goto L16;
        if (r0 == null) goto L16;
        layoutTitleCenteredHorizontally(r0, r2);
    L16:
        if (this.subtitleCentered == false) goto L20;
        if (r1 == null) goto L21;
        layoutTitleCenteredHorizontally(r1, r2);
        return;
    L21:
        return;
    L20:
        return;
    L5:
        if (this.subtitleCentered == true) goto L7;
    }

    private Drawable maybeTintNavigationIcon(Drawable r2) {
        if (r2 != null) goto L4;
        return r2;
    L4:
        if (this.navigationIconTint == null) goto L8;
        Drawable r22 = DrawableCompat.wrap(r2.mutate());
        r22.setTint(this.navigationIconTint.intValue());
        return r22;
    L8:
        return r2;
    }

    private void updateLogoImageView() {
        ImageView r0 = ToolbarUtils.getLogoImageView(this);
        if (r0 == null) goto L11;
        Boolean r1 = this.logoAdjustViewBounds;
        if (r1 == null) goto L7;
        r0.setAdjustViewBounds(r1.booleanValue());
    L7:
        ImageView.ScaleType r2 = this.logoScaleType;
        if (r2 == null) goto L12;
        r0.setScaleType(r2);
        return;
    L12:
        return;
    }

    public void clearNavigationIconTint() {
        this.navigationIconTint = null;
        Drawable r1 = getNavigationIcon();
        if (r1 == null) goto L6;
        DrawableCompat.wrap(r1.mutate()).setTintList(null);
        setNavigationIcon(r1);
        return;
    }

    public ImageView.ScaleType getLogoScaleType() {
        return this.logoScaleType;
    }

    public Integer getNavigationIconTint() {
        return this.navigationIconTint;
    }

    public boolean isLogoAdjustViewBounds() {
        Boolean r0 = this.logoAdjustViewBounds;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.booleanValue() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isSubtitleCentered() {
        return this.subtitleCentered;
    }

    public boolean isTitleCentered() {
        return this.titleCentered;
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this);
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        maybeCenterTitleViews();
        updateLogoImageView();
    }

    @Override // android.view.View
    public void setElevation(float r1) {
        super.setElevation(r1);
        MaterialShapeUtils.setElevation(this, r1);
    }

    public void setLogoAdjustViewBounds(boolean r2) {
        Boolean r0 = this.logoAdjustViewBounds;
        if (r0 != null) goto L5;
    L8:
        this.logoAdjustViewBounds = Boolean.valueOf(r2);
        requestLayout();
        return;
    L5:
        if (r0.booleanValue() != r2) goto L8;
    }

    public void setLogoScaleType(ImageView.ScaleType r2) {
        if (this.logoScaleType == r2) goto L6;
        this.logoScaleType = r2;
        requestLayout();
        return;
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setNavigationIcon(Drawable r1) {
        super.setNavigationIcon(maybeTintNavigationIcon(r1));
    }

    public void setNavigationIconTint(int r1) {
        this.navigationIconTint = Integer.valueOf(r1);
        Drawable r12 = getNavigationIcon();
        if (r12 == null) goto L6;
        setNavigationIcon(r12);
        return;
    }

    public void setSubtitleCentered(boolean r2) {
        if (this.subtitleCentered == r2) goto L6;
        this.subtitleCentered = r2;
        requestLayout();
        return;
    }

    public void setTitleCentered(boolean r2) {
        if (this.titleCentered == r2) goto L6;
        this.titleCentered = r2;
        requestLayout();
        return;
    }
}
