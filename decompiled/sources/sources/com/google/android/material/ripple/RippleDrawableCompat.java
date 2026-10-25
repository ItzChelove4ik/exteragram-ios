package com.google.android.material.ripple;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.dynamicanimation.animation.SpringForce;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import com.google.android.material.shape.StateListShapeAppearanceModel;

/* loaded from: classes5.dex */
public class RippleDrawableCompat extends Drawable implements Shapeable {
    private RippleDrawableCompatState drawableState;

    /* renamed from: com.google.android.material.ripple.RippleDrawableCompat$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class RippleDrawableCompatState extends Drawable.ConstantState {
        MaterialShapeDrawable delegate;
        boolean shouldDrawDelegate;

        public RippleDrawableCompatState(RippleDrawableCompatState r2) {
            this.delegate = (MaterialShapeDrawable) r2.delegate.getConstantState().newDrawable();
            this.shouldDrawDelegate = r2.shouldDrawDelegate;
        }

        public RippleDrawableCompatState(MaterialShapeDrawable r1) {
            this.delegate = r1;
            this.shouldDrawDelegate = false;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public /* bridge */ /* synthetic */ Drawable newDrawable() {
            return newDrawable();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public RippleDrawableCompat newDrawable() {
            return new RippleDrawableCompat(new RippleDrawableCompatState(this), null);
        }
    }

    private RippleDrawableCompat(RippleDrawableCompatState r1) {
        this.drawableState = r1;
    }

    public /* synthetic */ RippleDrawableCompat(RippleDrawableCompatState r1, AnonymousClass1 r2) {
        this(r1);
    }

    public RippleDrawableCompat(ShapeAppearanceModel r3) {
        this(new RippleDrawableCompatState(new MaterialShapeDrawable(r3)));
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r2) {
        RippleDrawableCompatState r1 = this.drawableState;
        if (r1.shouldDrawDelegate == false) goto L6;
        r1.delegate.draw(r2);
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.drawableState;
    }

    public SpringForce getCornerSpringForce() {
        return this.drawableState.delegate.getCornerSpringForce();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.drawableState.delegate.getOpacity();
    }

    @Override // com.google.android.material.shape.Shapeable
    public ShapeAppearanceModel getShapeAppearanceModel() {
        return this.drawableState.delegate.getShapeAppearanceModel();
    }

    public StateListShapeAppearanceModel getStateListShapeAppearanceModel() {
        return this.drawableState.delegate.getStateListShapeAppearanceModel();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Drawable mutate() {
        return mutate();
    }

    @Override // android.graphics.drawable.Drawable
    public RippleDrawableCompat mutate() {
        this.drawableState = new RippleDrawableCompatState(this.drawableState);
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect r1) {
        super.onBoundsChange(r1);
        this.drawableState.delegate.setBounds(r1);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] r4) {
        boolean r0 = super.onStateChange(r4);
        if (this.drawableState.delegate.setState(r4) == false) goto L5;
        r0 = true;
    L5:
        boolean r42 = RippleUtils.shouldDrawRippleCompat(r4);
        RippleDrawableCompatState r3 = this.drawableState;
        if (r3.shouldDrawDelegate == r42) goto L9;
        r3.shouldDrawDelegate = r42;
        return true;
    L9:
        return r0;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
        this.drawableState.delegate.setAlpha(r1);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
        this.drawableState.delegate.setColorFilter(r1);
    }

    public void setCornerSpringForce(SpringForce r1) {
        this.drawableState.delegate.setCornerSpringForce(r1);
    }

    @Override // com.google.android.material.shape.Shapeable
    public void setShapeAppearanceModel(ShapeAppearanceModel r1) {
        this.drawableState.delegate.setShapeAppearanceModel(r1);
    }

    public void setStateListShapeAppearanceModel(StateListShapeAppearanceModel r1) {
        this.drawableState.delegate.setShapeAppearance(r1);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTint(int r1) {
        this.drawableState.delegate.setTint(r1);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList r1) {
        this.drawableState.delegate.setTintList(r1);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode r1) {
        this.drawableState.delegate.setTintMode(r1);
    }
}
