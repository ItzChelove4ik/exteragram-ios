package com.google.android.material.button;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import androidx.dynamicanimation.animation.SpringForce;
import com.google.android.material.R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.focus.FocusRingDrawable;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearance;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import com.google.android.material.shape.StateListShapeAppearanceModel;

/* loaded from: classes5.dex */
class MaterialButtonHelper {
    private boolean backgroundOverwritten;
    private ColorStateList backgroundTint;
    private PorterDuff.Mode backgroundTintMode;
    private boolean checkable;
    private int cornerRadius;
    private boolean cornerRadiusSet;
    private SpringForce cornerSpringForce;
    private int elevation;
    private int insetBottom;
    private int insetLeft;
    private int insetRight;
    private int insetTop;
    private Drawable maskDrawable;
    private final MaterialButton materialButton;
    private MaterialShapeDrawable.OnCornerSizeChangeListener onCornerSizeChangeListener;
    private ColorStateList rippleColor;
    private RippleDrawable rippleDrawable;
    private ShapeAppearance shapeAppearance;
    private boolean shouldDrawSurfaceColorStroke;
    private ColorStateList strokeColor;
    private int strokeWidth;
    private boolean toggleCheckedStateOnClick;

    public MaterialButtonHelper(MaterialButton r2, ShapeAppearance r3) {
        this.shouldDrawSurfaceColorStroke = false;
        this.backgroundOverwritten = false;
        this.cornerRadiusSet = false;
        this.toggleCheckedStateOnClick = true;
        this.materialButton = r2;
        this.shapeAppearance = r3;
    }

    private Drawable createBackground() {
        MaterialShapeDrawable r0 = new MaterialShapeDrawable(this.shapeAppearance);
        SpringForce r1 = this.cornerSpringForce;
        if (r1 == null) goto L5;
        r0.setCornerSpringForce(r1);
    L5:
        MaterialShapeDrawable.OnCornerSizeChangeListener r12 = this.onCornerSizeChangeListener;
        if (r12 == null) goto L8;
        r0.setOnCornerSizeChangeListener(r12);
    L8:
        Context r13 = this.materialButton.getContext();
        r0.initializeElevationOverlay(r13);
        r0.setTintList(this.backgroundTint);
        PorterDuff.Mode r2 = this.backgroundTintMode;
        if (r2 == null) goto L11;
        r0.setTintMode(r2);
    L11:
        r0.setStroke(this.strokeWidth, this.strokeColor);
        MaterialShapeDrawable r22 = new MaterialShapeDrawable(this.shapeAppearance);
        SpringForce r3 = this.cornerSpringForce;
        if (r3 == null) goto L14;
        r22.setCornerSpringForce(r3);
    L14:
        r22.setTint(0);
        float r4 = this.strokeWidth;
        if (this.shouldDrawSurfaceColorStroke == false) goto L17;
        int r5 = MaterialColors.getColor(this.materialButton, R.attr.colorSurface);
    L18:
        r22.setStroke(r4, r5);
        MaterialShapeDrawable r42 = new MaterialShapeDrawable(this.shapeAppearance);
        this.maskDrawable = r42;
        SpringForce r52 = this.cornerSpringForce;
        if (r52 == null) goto L21;
        r42.setCornerSpringForce(r52);
    L21:
        this.maskDrawable.setTint(-1);
        RippleDrawable r43 = new RippleDrawable(RippleUtils.sanitizeRippleDrawableColor(this.rippleColor), wrapDrawableWithInset(new LayerDrawable(new Drawable[]{r22, r0})), this.maskDrawable);
        this.rippleDrawable = r43;
        FocusRingDrawable.layer(r13, r43);
        return this.rippleDrawable;
    L17:
        r5 = 0;
        goto L18
    }

    private MaterialShapeDrawable getMaterialShapeDrawable(boolean r2) {
        RippleDrawable r0 = this.rippleDrawable;
        if (r0 != null) goto L5;
        return null;
    L5:
        if (r0.getNumberOfLayers() > 0) goto L7;
        return null;
    L7:
        return (MaterialShapeDrawable) ((LayerDrawable) ((InsetDrawable) this.rippleDrawable.getDrawable(0)).getDrawable()).getDrawable(!r2 ? 1 : 0);
    }

    private MaterialShapeDrawable getSurfaceColorStrokeDrawable() {
        return getMaterialShapeDrawable(true);
    }

    private void setInsets(int r10, int r11, int r12, int r13) {
        int r0 = this.materialButton.getPaddingStart();
        int r1 = this.materialButton.getPaddingTop();
        int r2 = this.materialButton.getPaddingEnd();
        int r3 = this.materialButton.getPaddingBottom();
        int r4 = this.insetLeft;
        int r5 = this.insetTop;
        int r6 = this.insetRight;
        int r7 = this.insetBottom;
        this.insetLeft = r10;
        this.insetTop = r11;
        this.insetRight = r12;
        this.insetBottom = r13;
        if (this.backgroundOverwritten == true) goto L5;
        updateBackground();
    L5:
        this.materialButton.setPaddingRelative((r0 + r10) - r4, (r1 + r11) - r5, (r2 + r12) - r6, (r3 + r13) - r7);
    }

    private void updateBackground() {
        this.materialButton.setInternalBackground(createBackground());
        MaterialShapeDrawable r0 = getMaterialShapeDrawable();
        if (r0 == null) goto L5;
        r0.setElevation(this.elevation);
        r0.setState(this.materialButton.getDrawableState());
    L5:
        FocusRingDrawable r2 = FocusRingDrawable.find(this.materialButton.getBackground());
        if (r2 == null) goto L9;
        r2.setFocusRingMaterialShapeDrawable(r0);
        return;
    }

    private void updateButtonShape() {
        MaterialShapeDrawable r0 = getMaterialShapeDrawable();
        if (r0 == null) goto L7;
        r0.setShapeAppearance(this.shapeAppearance);
        SpringForce r1 = this.cornerSpringForce;
        if (r1 == null) goto L7;
        r0.setCornerSpringForce(r1);
    L7:
        MaterialShapeDrawable r02 = getSurfaceColorStrokeDrawable();
        if (r02 == null) goto L12;
        r02.setShapeAppearance(this.shapeAppearance);
        SpringForce r12 = this.cornerSpringForce;
        if (r12 == null) goto L12;
        r02.setCornerSpringForce(r12);
    L12:
        Shapeable r03 = getMaskDrawable();
        if (r03 != null) goto L15;
        return;
    L15:
        if ((r03 instanceof MaterialShapeDrawable) == false) goto L20;
        MaterialShapeDrawable r04 = (MaterialShapeDrawable) r03;
        r04.setShapeAppearance(this.shapeAppearance);
        SpringForce r2 = this.cornerSpringForce;
        if (r2 == null) goto L23;
        r04.setCornerSpringForce(r2);
        return;
    L23:
        return;
    L20:
        r03.setShapeAppearanceModel(this.shapeAppearance.getDefaultShape());
    }

    private void updateStroke() {
        MaterialShapeDrawable r0 = getMaterialShapeDrawable();
        MaterialShapeDrawable r1 = getSurfaceColorStrokeDrawable();
        if (r0 == null) goto L12;
        r0.setStroke(this.strokeWidth, this.strokeColor);
        if (r1 == null) goto L13;
        float r02 = this.strokeWidth;
        if (this.shouldDrawSurfaceColorStroke == false) goto L9;
        int r4 = MaterialColors.getColor(this.materialButton, R.attr.colorSurface);
    L10:
        r1.setStroke(r02, r4);
        return;
    L9:
        r4 = 0;
        goto L10
    L13:
        return;
    }

    private InsetDrawable wrapDrawableWithInset(Drawable r7) {
        return new InsetDrawable(r7, this.insetLeft, this.insetTop, this.insetRight, this.insetBottom);
    }

    public int getCornerRadius() {
        return this.cornerRadius;
    }

    public SpringForce getCornerSpringForce() {
        return this.cornerSpringForce;
    }

    public int getInsetBottom() {
        return this.insetBottom;
    }

    public int getInsetLeft() {
        return this.insetLeft;
    }

    public int getInsetRight() {
        return this.insetRight;
    }

    public int getInsetTop() {
        return this.insetTop;
    }

    public Shapeable getMaskDrawable() {
        RippleDrawable r1 = this.rippleDrawable;
        if (r1 == null) goto L8;
        Object r12 = r1.findDrawableByLayerId(android.R.id.mask);
        if ((r12 instanceof Shapeable) == true) goto L7;
        return null;
    L7:
        return (Shapeable) r12;
    L8:
        return null;
    }

    public MaterialShapeDrawable getMaterialShapeDrawable() {
        return getMaterialShapeDrawable(false);
    }

    public ColorStateList getRippleColor() {
        return this.rippleColor;
    }

    public ShapeAppearance getShapeAppearance() {
        return this.shapeAppearance;
    }

    public ShapeAppearanceModel getShapeAppearanceModel() {
        return this.shapeAppearance.getDefaultShape();
    }

    public ColorStateList getStrokeColor() {
        return this.strokeColor;
    }

    public int getStrokeWidth() {
        return this.strokeWidth;
    }

    public ColorStateList getSupportBackgroundTintList() {
        return this.backgroundTint;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return this.backgroundTintMode;
    }

    public boolean isBackgroundOverwritten() {
        return this.backgroundOverwritten;
    }

    public boolean isCheckable() {
        return this.checkable;
    }

    public boolean isToggleCheckedStateOnClick() {
        return this.toggleCheckedStateOnClick;
    }

    public void loadFromAttributes(TypedArray r6) {
        this.insetLeft = r6.getDimensionPixelOffset(R.styleable.MaterialButton_android_insetLeft, 0);
        this.insetRight = r6.getDimensionPixelOffset(R.styleable.MaterialButton_android_insetRight, 0);
        this.insetTop = r6.getDimensionPixelOffset(R.styleable.MaterialButton_android_insetTop, 0);
        this.insetBottom = r6.getDimensionPixelOffset(R.styleable.MaterialButton_android_insetBottom, 0);
        if (r6.hasValue(R.styleable.MaterialButton_cornerRadius) == false) goto L5;
        int r0 = r6.getDimensionPixelSize(R.styleable.MaterialButton_cornerRadius, -1);
        this.cornerRadius = r0;
        setShapeAppearance(this.shapeAppearance.withCornerSize(r0));
        this.cornerRadiusSet = true;
    L5:
        this.strokeWidth = r6.getDimensionPixelSize(R.styleable.MaterialButton_strokeWidth, 0);
        this.backgroundTintMode = ViewUtils.parseTintMode(r6.getInt(R.styleable.MaterialButton_backgroundTintMode, -1), PorterDuff.Mode.SRC_IN);
        this.backgroundTint = MaterialResources.getColorStateList(this.materialButton.getContext(), r6, R.styleable.MaterialButton_backgroundTint);
        this.strokeColor = MaterialResources.getColorStateList(this.materialButton.getContext(), r6, R.styleable.MaterialButton_strokeColor);
        this.rippleColor = MaterialResources.getColorStateList(this.materialButton.getContext(), r6, R.styleable.MaterialButton_rippleColor);
        this.checkable = r6.getBoolean(R.styleable.MaterialButton_android_checkable, false);
        this.elevation = r6.getDimensionPixelSize(R.styleable.MaterialButton_elevation, 0);
        this.toggleCheckedStateOnClick = r6.getBoolean(R.styleable.MaterialButton_toggleCheckedStateOnClick, true);
        int r02 = this.materialButton.getPaddingStart();
        int r1 = this.materialButton.getPaddingTop();
        int r2 = this.materialButton.getPaddingEnd();
        int r3 = this.materialButton.getPaddingBottom();
        if (r6.hasValue(R.styleable.MaterialButton_android_background) == false) goto L8;
        setBackgroundOverwritten();
    L9:
        this.materialButton.setPaddingRelative(r02 + this.insetLeft, r1 + this.insetTop, r2 + this.insetRight, r3 + this.insetBottom);
        return;
    L8:
        updateBackground();
        goto L9
    }

    public void setBackgroundColor(int r2) {
        if (getMaterialShapeDrawable() == null) goto L6;
        getMaterialShapeDrawable().setTint(r2);
        return;
    }

    public void setBackgroundOverwritten() {
        this.backgroundOverwritten = true;
        this.materialButton.setSupportBackgroundTintList(this.backgroundTint);
        this.materialButton.setSupportBackgroundTintMode(this.backgroundTintMode);
    }

    public void setCheckable(boolean r1) {
        this.checkable = r1;
    }

    public void setCornerRadius(int r2) {
        if (this.cornerRadiusSet == true) goto L5;
    L8:
        this.cornerRadius = r2;
        this.cornerRadiusSet = true;
        setShapeAppearance(this.shapeAppearance.withCornerSize(r2));
        return;
    L5:
        if (this.cornerRadius != r2) goto L8;
    }

    public void setCornerSizeChangeListener(MaterialShapeDrawable.OnCornerSizeChangeListener r1) {
        this.onCornerSizeChangeListener = r1;
        MaterialShapeDrawable r0 = getMaterialShapeDrawable();
        if (r0 == null) goto L6;
        r0.setOnCornerSizeChangeListener(r1);
        return;
    }

    public void setCornerSpringForce(SpringForce r1) {
        this.cornerSpringForce = r1;
        if ((this.shapeAppearance instanceof StateListShapeAppearanceModel) == false) goto L6;
        updateButtonShape();
        return;
    }

    public void setInsetBottom(int r4) {
        setInsets(this.insetLeft, this.insetTop, this.insetRight, r4);
    }

    public void setInsetLeft(int r4) {
        setInsets(r4, this.insetTop, this.insetRight, this.insetBottom);
    }

    public void setInsetRight(int r4) {
        setInsets(this.insetLeft, this.insetTop, r4, this.insetBottom);
    }

    public void setInsetTop(int r4) {
        setInsets(this.insetLeft, r4, this.insetRight, this.insetBottom);
    }

    public void setRippleColor(ColorStateList r2) {
        if (this.rippleColor == r2) goto L8;
        this.rippleColor = r2;
        if ((this.materialButton.getBackground() instanceof RippleDrawable) == false) goto L9;
        ((RippleDrawable) this.materialButton.getBackground()).setColor(RippleUtils.sanitizeRippleDrawableColor(r2));
        return;
    L9:
        return;
    }

    public void setShapeAppearance(ShapeAppearance r1) {
        this.shapeAppearance = r1;
        updateButtonShape();
    }

    public void setShouldDrawSurfaceColorStroke(boolean r1) {
        this.shouldDrawSurfaceColorStroke = r1;
        updateStroke();
    }

    public void setStrokeColor(ColorStateList r2) {
        if (this.strokeColor == r2) goto L6;
        this.strokeColor = r2;
        updateStroke();
        return;
    }

    public void setStrokeWidth(int r2) {
        if (this.strokeWidth == r2) goto L6;
        this.strokeWidth = r2;
        updateStroke();
        return;
    }

    public void setSupportBackgroundTintList(ColorStateList r2) {
        if (this.backgroundTint == r2) goto L8;
        this.backgroundTint = r2;
        if (getMaterialShapeDrawable() == null) goto L9;
        getMaterialShapeDrawable().setTintList(this.backgroundTint);
        return;
    L9:
        return;
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode r2) {
        if (this.backgroundTintMode == r2) goto L10;
        this.backgroundTintMode = r2;
        if (getMaterialShapeDrawable() != null) goto L7;
        return;
    L7:
        if (this.backgroundTintMode == null) goto L12;
        getMaterialShapeDrawable().setTintMode(this.backgroundTintMode);
        return;
    L12:
        return;
    }

    public void setToggleCheckedStateOnClick(boolean r1) {
        this.toggleCheckedStateOnClick = r1;
    }

    public void updateMaskBounds(int r5, int r6) {
        Drawable r0 = this.maskDrawable;
        if (r0 == null) goto L6;
        r0.setBounds(this.insetLeft, this.insetTop, r6 - this.insetRight, r5 - this.insetBottom);
        return;
    }
}
