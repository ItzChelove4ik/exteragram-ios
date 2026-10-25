package com.google.android.material.floatingactionbutton;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeAppearancePathProvider;

/* loaded from: classes5.dex */
class BorderDrawable extends Drawable {
    private static final float DRAW_STROKE_WIDTH_MULTIPLE = 1.3333f;
    private ColorStateList borderTint;
    float borderWidth;
    private int bottomInnerStrokeColor;
    private int bottomOuterStrokeColor;
    private final RectF boundsRectF;
    private int currentBorderTintColor;
    private boolean invalidateShader;
    private final Paint paint;
    private final ShapeAppearancePathProvider pathProvider;
    private final Rect rect;
    private final RectF rectF;
    private ShapeAppearanceModel shapeAppearanceModel;
    private final Path shapePath;
    private final BorderState state;
    private int topInnerStrokeColor;
    private int topOuterStrokeColor;

    /* renamed from: com.google.android.material.floatingactionbutton.BorderDrawable$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public class BorderState extends Drawable.ConstantState {
        final /* synthetic */ BorderDrawable this$0;

        private BorderState(BorderDrawable r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ BorderState(BorderDrawable r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return this.this$0;
        }
    }

    public BorderDrawable(ShapeAppearanceModel r3) {
        this.pathProvider = ShapeAppearancePathProvider.getInstance();
        this.shapePath = new Path();
        this.rect = new Rect();
        this.rectF = new RectF();
        this.boundsRectF = new RectF();
        this.state = new BorderState(this, null);
        this.invalidateShader = true;
        this.shapeAppearanceModel = r3;
        Paint r32 = new Paint(1);
        this.paint = r32;
        r32.setStyle(Paint.Style.STROKE);
    }

    private Shader createGradientShader() {
        copyBounds(this.rect);
        float r2 = this.borderWidth / r1.height();
        return new LinearGradient(0.0f, r1.top, 0.0f, r1.bottom, new int[]{ColorUtils.compositeColors(this.topOuterStrokeColor, this.currentBorderTintColor), ColorUtils.compositeColors(this.topInnerStrokeColor, this.currentBorderTintColor), ColorUtils.compositeColors(ColorUtils.setAlphaComponent(this.topInnerStrokeColor, 0), this.currentBorderTintColor), ColorUtils.compositeColors(ColorUtils.setAlphaComponent(this.bottomInnerStrokeColor, 0), this.currentBorderTintColor), ColorUtils.compositeColors(this.bottomInnerStrokeColor, this.currentBorderTintColor), ColorUtils.compositeColors(this.bottomOuterStrokeColor, this.currentBorderTintColor)}, new float[]{0.0f, r2, 0.5f, 0.5f, 1.0f - r2, 1.0f}, Shader.TileMode.CLAMP);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r5) {
        if (this.invalidateShader == false) goto L5;
        this.paint.setShader(createGradientShader());
        this.invalidateShader = false;
    L5:
        float r0 = this.paint.getStrokeWidth() / 2.0f;
        copyBounds(this.rect);
        this.rectF.set(this.rect);
        float r1 = Math.min(this.shapeAppearanceModel.getTopLeftCornerSize().getCornerSize(getBoundsAsRectF()), this.rectF.width() / 2.0f);
        if (this.shapeAppearanceModel.isRoundRect(getBoundsAsRectF()) == false) goto L9;
        this.rectF.inset(r0, r0);
        r5.drawRoundRect(this.rectF, r1, r1, this.paint);
        return;
    }

    public RectF getBoundsAsRectF() {
        this.boundsRectF.set(getBounds());
        return this.boundsRectF;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.state;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        if (this.borderWidth <= 0.0f) goto L6;
        return -3;
    L6:
        return -2;
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline r6) {
        if (this.shapeAppearanceModel.isRoundRect(getBoundsAsRectF()) == false) goto L6;
        float r0 = this.shapeAppearanceModel.getTopLeftCornerSize().getCornerSize(getBoundsAsRectF());
        r6.setRoundRect(getBounds(), r0);
        return;
    L6:
        copyBounds(this.rect);
        this.rectF.set(this.rect);
        this.pathProvider.calculatePath(this.shapeAppearanceModel, 1.0f, this.rectF, this.shapePath);
        DrawableUtils.setOutlineToPath(r6, this.shapePath);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect r3) {
        if (this.shapeAppearanceModel.isRoundRect(getBoundsAsRectF()) == false) goto L7;
        int r2 = Math.round(this.borderWidth);
        r3.set(r2, r2, r2, r2);
        return true;
    L7:
        return true;
    }

    public ShapeAppearanceModel getShapeAppearanceModel() {
        return this.shapeAppearanceModel;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList r0 = this.borderTint;
        if (r0 == null) goto L7;
        if (r0.isStateful() == false) goto L7;
        return true;
    L7:
        if (super.isStateful() == true) goto L12;
        return false;
    L12:
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect r1) {
        this.invalidateShader = true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] r3) {
        ColorStateList r0 = this.borderTint;
        if (r0 == null) goto L8;
        int r32 = r0.getColorForState(r3, this.currentBorderTintColor);
        if (r32 == this.currentBorderTintColor) goto L8;
        this.invalidateShader = true;
        this.currentBorderTintColor = r32;
    L8:
        if (this.invalidateShader == false) goto L11;
        invalidateSelf();
    L11:
        return this.invalidateShader;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r2) {
        this.paint.setAlpha(r2);
        invalidateSelf();
    }

    public void setBorderTint(ColorStateList r3) {
        if (r3 == null) goto L4;
        this.currentBorderTintColor = r3.getColorForState(getState(), this.currentBorderTintColor);
    L4:
        this.borderTint = r3;
        this.invalidateShader = true;
        invalidateSelf();
    }

    public void setBorderWidth(float r3) {
        if (this.borderWidth == r3) goto L6;
        this.borderWidth = r3;
        this.paint.setStrokeWidth(r3 * DRAW_STROKE_WIDTH_MULTIPLE);
        this.invalidateShader = true;
        invalidateSelf();
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r2) {
        this.paint.setColorFilter(r2);
        invalidateSelf();
    }

    public void setGradientColors(int r1, int r2, int r3, int r4) {
        this.topOuterStrokeColor = r1;
        this.topInnerStrokeColor = r2;
        this.bottomOuterStrokeColor = r3;
        this.bottomInnerStrokeColor = r4;
    }

    public void setShapeAppearanceModel(ShapeAppearanceModel r1) {
        this.shapeAppearanceModel = r1;
        invalidateSelf();
    }
}
