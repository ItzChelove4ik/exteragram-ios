package androidx.core.graphics.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/* loaded from: classes3.dex */
public abstract class RoundedBitmapDrawable extends Drawable {
    private boolean mApplyGravity;
    final Bitmap mBitmap;
    private int mBitmapHeight;
    private final BitmapShader mBitmapShader;
    private int mBitmapWidth;
    private float mCornerRadius;
    final Rect mDstRect;
    private final RectF mDstRectF;
    private int mGravity;
    private boolean mIsCircular;
    private final Paint mPaint;
    private final Matrix mShaderMatrix;
    private int mTargetDensity;

    public RoundedBitmapDrawable(Resources r3, Bitmap r4) {
        this.mTargetDensity = 160;
        this.mGravity = 119;
        this.mPaint = new Paint(3);
        this.mShaderMatrix = new Matrix();
        this.mDstRect = new Rect();
        this.mDstRectF = new RectF();
        this.mApplyGravity = true;
        if (r3 == null) goto L5;
        this.mTargetDensity = r3.getDisplayMetrics().densityDpi;
    L5:
        this.mBitmap = r4;
        if (r4 == null) goto L9;
        computeBitmapSize();
        Shader.TileMode r0 = Shader.TileMode.CLAMP;
        this.mBitmapShader = new BitmapShader(r4, r0, r0);
        return;
    L9:
        this.mBitmapHeight = -1;
        this.mBitmapWidth = -1;
        this.mBitmapShader = null;
    }

    private void computeBitmapSize() {
        this.mBitmapWidth = this.mBitmap.getScaledWidth(this.mTargetDensity);
        this.mBitmapHeight = this.mBitmap.getScaledHeight(this.mTargetDensity);
    }

    private static boolean isGreaterThanZero(float r1) {
        if (r1 <= 0.05f) goto L6;
        return true;
    L6:
        return false;
    }

    private void updateCircularCornerRadius() {
        this.mCornerRadius = Math.min(this.mBitmapHeight, this.mBitmapWidth) / 2;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r4) {
        Bitmap r0 = this.mBitmap;
        if (r0 != null) goto L5;
        return;
    L5:
        updateDstRect();
        if (this.mPaint.getShader() != null) goto L9;
        r4.drawBitmap(r0, null, this.mDstRect, this.mPaint);
        return;
    L9:
        RectF r02 = this.mDstRectF;
        float r1 = this.mCornerRadius;
        r4.drawRoundRect(r02, r1, r1, this.mPaint);
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.mPaint.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        return this.mPaint.getColorFilter();
    }

    public float getCornerRadius() {
        return this.mCornerRadius;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.mBitmapHeight;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.mBitmapWidth;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        if (this.mGravity == 119) goto L5;
    L18:
        return -3;
    L5:
        if (this.mIsCircular == true) goto L18;
        Bitmap r0 = this.mBitmap;
        if (r0 == null) goto L18;
        if (r0.hasAlpha() == true) goto L18;
        if (this.mPaint.getAlpha() < 255) goto L18;
        if (isGreaterThanZero(this.mCornerRadius) == true) goto L18;
        return -1;
    }

    public abstract void gravityCompatApply(int r1, int r2, int r3, Rect r4, Rect r5);

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect r1) {
        super.onBoundsChange(r1);
        if (this.mIsCircular == false) goto L5;
        updateCircularCornerRadius();
    L5:
        this.mApplyGravity = true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r2) {
        if (r2 == this.mPaint.getAlpha()) goto L6;
        this.mPaint.setAlpha(r2);
        invalidateSelf();
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r2) {
        this.mPaint.setColorFilter(r2);
        invalidateSelf();
    }

    public void setCornerRadius(float r3) {
        if (this.mCornerRadius != r3) goto L5;
        return;
    L5:
        this.mIsCircular = false;
        boolean r0 = isGreaterThanZero(r3);
        Paint r1 = this.mPaint;
        if (r0 == false) goto L8;
        r1.setShader(this.mBitmapShader);
    L9:
        this.mCornerRadius = r3;
        invalidateSelf();
        return;
    L8:
        r1.setShader(null);
        goto L9
    }

    @Override // android.graphics.drawable.Drawable
    public void setDither(boolean r2) {
        this.mPaint.setDither(r2);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setFilterBitmap(boolean r2) {
        this.mPaint.setFilterBitmap(r2);
        invalidateSelf();
    }

    public void updateDstRect() {
        if (this.mApplyGravity == true) goto L5;
        return;
    L5:
        if (this.mIsCircular == false) goto L7;
        int r5 = Math.min(this.mBitmapWidth, this.mBitmapHeight);
        RoundedBitmapDrawable r3 = this;
        r3.gravityCompatApply(this.mGravity, r5, r5, getBounds(), this.mDstRect);
        int r15 = Math.min(r3.mDstRect.width(), r3.mDstRect.height());
        r3.mDstRect.inset(Math.max(0, (r3.mDstRect.width() - r15) / 2), Math.max(0, (r3.mDstRect.height() - r15) / 2));
        r3.mCornerRadius = r15 * 0.5f;
    L8:
        r3.mDstRectF.set(r3.mDstRect);
        if (r3.mBitmapShader == null) goto L11;
        Matrix r152 = r3.mShaderMatrix;
        RectF r0 = r3.mDstRectF;
        r152.setTranslate(r0.left, r0.top);
        r3.mShaderMatrix.preScale(r3.mDstRectF.width() / r3.mBitmap.getWidth(), r3.mDstRectF.height() / r3.mBitmap.getHeight());
        r3.mBitmapShader.setLocalMatrix(r3.mShaderMatrix);
        r3.mPaint.setShader(r3.mBitmapShader);
    L11:
        r3.mApplyGravity = false;
        return;
    L7:
        r3 = this;
        r3.gravityCompatApply(r3.mGravity, r3.mBitmapWidth, r3.mBitmapHeight, r3.getBounds(), r3.mDstRect);
        goto L8
    }
}
