package com.google.android.material.carousel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import androidx.core.math.MathUtils;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.focus.FocusRingDrawable;
import com.google.android.material.shape.AbsoluteCornerSize;
import com.google.android.material.shape.ClampedCornerSize;
import com.google.android.material.shape.CornerSize;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import com.google.android.material.shape.ShapeableDelegate;

/* loaded from: classes5.dex */
public class MaskableFrameLayout extends FrameLayout implements Maskable, Shapeable {
    private static final int NOT_SET = -1;
    private View.OnHoverListener hoverListener;
    private boolean isHovered;
    private final RectF maskRect;
    private float maskXPercentage;
    private OnMaskChangedListener onMaskChangedListener;
    private Boolean savedForceCompatClippingEnabled;
    private final Rect screenBoundsRect;
    private ShapeAppearanceModel shapeAppearanceModel;
    private final ShapeableDelegate shapeableDelegate;

    public static /* synthetic */ CornerSize $r8$lambda$Agw_urQ8Ww6aiBeJU9RbO6qyqA8(CornerSize r1) {
        if ((r1 instanceof AbsoluteCornerSize) == true) goto L5;
        return r1;
    L5:
        return ClampedCornerSize.createFromCornerSize((AbsoluteCornerSize) r1);
    }

    /* renamed from: $r8$lambda$kUSPnzaO2bap3ZjIEG78-cwlgmg, reason: not valid java name */
    public static /* synthetic */ void m1826$r8$lambda$kUSPnzaO2bap3ZjIEG78cwlgmg(MaskableFrameLayout r0, Canvas r1) {
        super.dispatchDraw(r1);
    }

    public MaskableFrameLayout(Context r2) {
        this(r2, null);
    }

    public MaskableFrameLayout(Context r2, AttributeSet r3) {
        this(r2, r3, 0);
    }

    public MaskableFrameLayout(Context r2, AttributeSet r3, int r4) {
        super(r2, r3, r4);
        this.maskXPercentage = -1.0f;
        this.maskRect = new RectF();
        this.screenBoundsRect = new Rect();
        this.shapeableDelegate = ShapeableDelegate.create(this);
        this.savedForceCompatClippingEnabled = null;
        this.isHovered = false;
        setShapeAppearanceModel(ShapeAppearanceModel.builder(r2, r3, r4, 0, 0).build());
    }

    private void maybeUpdateFocusRingDrawableShapeAppearance(Drawable r1, ShapeAppearanceModel r2) {
        FocusRingDrawable r0 = FocusRingDrawable.findAndMutate(r1);
        if (r0 == null) goto L6;
        r0.setFocusRingShapeAppearance(r2);
        return;
    }

    private void onMaskChanged() {
        this.shapeableDelegate.onMaskChanged(this, this.maskRect);
        OnMaskChangedListener r0 = this.onMaskChangedListener;
        if (r0 == null) goto L6;
        r0.onMaskChanged(this.maskRect);
        return;
    }

    private void updateMaskRectForMaskXPercentage() {
        if (this.maskXPercentage == (-1.0f)) goto L6;
        float r0 = AnimationUtils.lerp(0.0f, getWidth() / 2.0f, 0.0f, 1.0f, this.maskXPercentage);
        setMaskRectF(new RectF(r0, 0.0f, getWidth() - r0, getHeight()));
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r3) {
        this.shapeableDelegate.maybeClip(r3, new MaskableFrameLayout$$ExternalSyntheticLambda1(this));
    }

    @Override // android.view.View
    public void getFocusedRect(Rect r4) {
        RectF r3 = this.maskRect;
        r4.set((int) r3.left, (int) r3.top, (int) r3.right, (int) r3.bottom);
    }

    @Override // com.google.android.material.carousel.Maskable
    public RectF getMaskRectF() {
        return this.maskRect;
    }

    @Override // com.google.android.material.carousel.Maskable
    @Deprecated
    public float getMaskXPercentage() {
        return this.maskXPercentage;
    }

    @Override // com.google.android.material.shape.Shapeable
    public ShapeAppearanceModel getShapeAppearanceModel() {
        return this.shapeAppearanceModel;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Boolean r0 = this.savedForceCompatClippingEnabled;
        if (r0 == null) goto L6;
        this.shapeableDelegate.setForceCompatClippingEnabled(this, r0.booleanValue());
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        this.savedForceCompatClippingEnabled = Boolean.valueOf(this.shapeableDelegate.isForceCompatClippingEnabled());
        this.shapeableDelegate.setForceCompatClippingEnabled(this, true);
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent r8) {
        int r0 = r8.getAction();
        if (this.maskRect.isEmpty() == true) goto L18;
        if (r0 == 9) goto L8;
        if (r0 == 10) goto L8;
        if (r0 != 7) goto L18;
    L8:
        float r4 = r8.getX();
        float r5 = r8.getY();
        if (this.maskRect.contains(r4, r5) == true) goto L18;
        if (this.isHovered == true) goto L13;
    L15:
        this.isHovered = false;
        return false;
    L13:
        if (this.hoverListener == null) goto L15;
        r8.setAction(10);
        this.hoverListener.onHover(this, r8);
    L18:
        if (this.hoverListener == null) goto L28;
        if (this.isHovered == true) goto L23;
        if (r0 != 7) goto L23;
        r8.setAction(9);
        this.isHovered = true;
    L23:
        if (r0 == 7) goto L25;
        if (r0 == 9) goto L25;
    L26:
        this.hoverListener.onHover(this, r8);
    L25:
        this.isHovered = true;
    L28:
        return super.onHoverEvent(r8);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r5) {
        super.onInitializeAccessibilityNodeInfo(r5);
        r5.getBoundsInScreen(this.screenBoundsRect);
        if (getX() <= 0.0f) goto L6;
        this.screenBoundsRect.left = (int) (r0.left + this.maskRect.left);
    L6:
        if (getY() <= 0.0f) goto L8;
        this.screenBoundsRect.top = (int) (r0.top + this.maskRect.top);
    L8:
        Rect r0 = this.screenBoundsRect;
        r0.right = r0.left + Math.round(this.maskRect.width());
        Rect r02 = this.screenBoundsRect;
        r02.bottom = r02.top + Math.round(this.maskRect.height());
        r5.setBoundsInScreen(this.screenBoundsRect);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r4) {
        if (this.maskRect.isEmpty() == true) goto L9;
        float r0 = r4.getX();
        float r1 = r4.getY();
        if (this.maskRect.contains(r0, r1) == true) goto L9;
        return true;
    L9:
        return super.onInterceptTouchEvent(r4);
    }

    @Override // android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        super.onSizeChanged(r1, r2, r3, r4);
        if (this.maskXPercentage == (-1.0f)) goto L6;
        updateMaskRectForMaskXPercentage();
        return;
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent r4) {
        if (this.maskRect.isEmpty() == true) goto L11;
        if (r4.getAction() != 0) goto L11;
        float r0 = r4.getX();
        float r1 = r4.getY();
        if (this.maskRect.contains(r0, r1) == true) goto L11;
        return false;
    L11:
        return super.onTouchEvent(r4);
    }

    public void setForceCompatClipping(boolean r2) {
        this.shapeableDelegate.setForceCompatClippingEnabled(this, r2);
    }

    @Override // com.google.android.material.carousel.Maskable
    public void setMaskRectF(RectF r2) {
        this.maskRect.set(r2);
        onMaskChanged();
    }

    @Override // com.google.android.material.carousel.Maskable
    @Deprecated
    public void setMaskXPercentage(float r3) {
        float r32 = MathUtils.clamp(r3, 0.0f, 1.0f);
        if (this.maskXPercentage == r32) goto L6;
        this.maskXPercentage = r32;
        updateMaskRectForMaskXPercentage();
        return;
    }

    @Override // android.view.View
    public void setOnHoverListener(View.OnHoverListener r1) {
        this.hoverListener = r1;
    }

    @Override // com.google.android.material.carousel.Maskable
    public void setOnMaskChangedListener(OnMaskChangedListener r1) {
        this.onMaskChangedListener = r1;
    }

    @Override // com.google.android.material.shape.Shapeable
    public void setShapeAppearanceModel(ShapeAppearanceModel r2) {
        ShapeAppearanceModel r22 = r2.withTransformedCornerSizes(new MaskableFrameLayout$$ExternalSyntheticLambda0());
        this.shapeAppearanceModel = r22;
        this.shapeableDelegate.onShapeAppearanceChanged(this, r22);
        maybeUpdateFocusRingDrawableShapeAppearance(getBackground(), this.shapeAppearanceModel);
        maybeUpdateFocusRingDrawableShapeAppearance(getForeground(), this.shapeAppearanceModel);
    }
}
