package androidx.cardview.widget;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/* loaded from: classes3.dex */
class RoundRectDrawable extends Drawable {
    private ColorStateList mBackground;
    private final RectF mBoundsF;
    private final Rect mBoundsI;
    private boolean mInsetForPadding;
    private boolean mInsetForRadius;
    private float mPadding;
    private final Paint mPaint;
    private float mRadius;
    private ColorStateList mTint;
    private PorterDuffColorFilter mTintFilter;
    private PorterDuff.Mode mTintMode;

    public RoundRectDrawable(ColorStateList r2, float r3) {
        this.mInsetForPadding = false;
        this.mInsetForRadius = true;
        this.mTintMode = PorterDuff.Mode.SRC_IN;
        this.mRadius = r3;
        this.mPaint = new Paint(5);
        setBackground(r2);
        this.mBoundsF = new RectF();
        this.mBoundsI = new Rect();
    }

    private PorterDuffColorFilter createTintFilter(ColorStateList r2, PorterDuff.Mode r3) {
        if (r2 == null) goto L7;
        if (r3 != null) goto L6;
        return null;
    L6:
        return new PorterDuffColorFilter(r2.getColorForState(getState(), 0), r3);
    L7:
        return null;
    }

    private void setBackground(ColorStateList r3) {
        if (r3 != null) goto L4;
        r3 = ColorStateList.valueOf(0);
    L4:
        this.mBackground = r3;
        this.mPaint.setColor(r3.getColorForState(getState(), this.mBackground.getDefaultColor()));
    }

    private void updateBounds(Rect r6) {
        if (r6 != null) goto L4;
        r6 = getBounds();
    L4:
        this.mBoundsF.set(r6.left, r6.top, r6.right, r6.bottom);
        this.mBoundsI.set(r6);
        if (this.mInsetForPadding == false) goto L8;
        float r62 = RoundRectDrawableWithShadow.calculateVerticalPadding(this.mPadding, this.mRadius, this.mInsetForRadius);
        this.mBoundsI.inset((int) Math.ceil(RoundRectDrawableWithShadow.calculateHorizontalPadding(this.mPadding, this.mRadius, this.mInsetForRadius)), (int) Math.ceil(r62));
        this.mBoundsF.set(this.mBoundsI);
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r4) {
        Paint r0 = this.mPaint;
        if (this.mTintFilter != null) goto L5;
    L7:
        boolean r1 = false;
    L8:
        RectF r2 = this.mBoundsF;
        float r3 = this.mRadius;
        r4.drawRoundRect(r2, r3, r3, r0);
        if (r1 == false) goto L12;
        r0.setColorFilter(null);
        return;
    L12:
        return;
    L5:
        if (r0.getColorFilter() != null) goto L7;
        r0.setColorFilter(this.mTintFilter);
        r1 = true;
        goto L8
    }

    public ColorStateList getColor() {
        return this.mBackground;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline r2) {
        r2.setRoundRect(this.mBoundsI, this.mRadius);
    }

    public float getPadding() {
        return this.mPadding;
    }

    public float getRadius() {
        return this.mRadius;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList r0 = this.mTint;
        if (r0 != null) goto L5;
    L6:
        ColorStateList r02 = this.mBackground;
        if (r02 == null) goto L11;
        if (r02.isStateful() == false) goto L11;
        return true;
    L11:
        if (super.isStateful() == true) goto L17;
        return false;
    L17:
        return true;
    L5:
        if (r0.isStateful() == false) goto L6;
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect r1) {
        super.onBoundsChange(r1);
        updateBounds(r1);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] r4) {
        ColorStateList r0 = this.mBackground;
        int r42 = r0.getColorForState(r4, r0.getDefaultColor());
        if (r42 == this.mPaint.getColor()) goto L5;
        boolean r02 = true;
    L6:
        if (r02 == false) goto L8;
        this.mPaint.setColor(r42);
    L8:
        ColorStateList r43 = this.mTint;
        if (r43 == null) goto L14;
        PorterDuff.Mode r2 = this.mTintMode;
        if (r2 == null) goto L14;
        this.mTintFilter = createTintFilter(r43, r2);
        return true;
    L14:
        return r02;
    L5:
        r02 = false;
        goto L6
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
        this.mPaint.setAlpha(r1);
    }

    public void setColor(ColorStateList r1) {
        setBackground(r1);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
        this.mPaint.setColorFilter(r1);
    }

    public void setPadding(float r2, boolean r3, boolean r4) {
        if (r2 == this.mPadding) goto L5;
    L9:
        this.mPadding = r2;
        this.mInsetForPadding = r3;
        this.mInsetForRadius = r4;
        updateBounds(null);
        invalidateSelf();
        return;
    L5:
        if (this.mInsetForPadding != r3) goto L9;
        if (this.mInsetForRadius != r4) goto L9;
    }

    public void setRadius(float r2) {
        if (r2 != this.mRadius) goto L5;
        return;
    L5:
        this.mRadius = r2;
        updateBounds(null);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList r2) {
        this.mTint = r2;
        this.mTintFilter = createTintFilter(r2, this.mTintMode);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode r2) {
        this.mTintMode = r2;
        this.mTintFilter = createTintFilter(this.mTint, r2);
        invalidateSelf();
    }
}
