package androidx.appcompat.graphics.drawable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$style;
import androidx.appcompat.R$styleable;
import androidx.core.graphics.drawable.DrawableCompat;

/* loaded from: classes3.dex */
public class DrawerArrowDrawable extends Drawable {
    private static final float ARROW_HEAD_ANGLE = 0.0f;
    private float mArrowHeadLength;
    private float mArrowShaftLength;
    private float mBarGap;
    private float mBarLength;
    private int mDirection;
    private float mMaxCutForBarSize;
    private final Paint mPaint;
    private final Path mPath;
    private float mProgress;
    private final int mSize;
    private boolean mSpin;
    private boolean mVerticalMirror;

    static {
        ARROW_HEAD_ANGLE = (float) Math.toRadians(45.0d);
    }

    public DrawerArrowDrawable(Context r7) {
        Paint r0 = new Paint();
        this.mPaint = r0;
        this.mPath = new Path();
        this.mVerticalMirror = false;
        this.mDirection = 2;
        r0.setStyle(Paint.Style.STROKE);
        r0.setStrokeJoin(Paint.Join.MITER);
        r0.setStrokeCap(Paint.Cap.BUTT);
        r0.setAntiAlias(true);
        TypedArray r72 = r7.getTheme().obtainStyledAttributes(null, R$styleable.DrawerArrowToggle, R$attr.drawerArrowStyle, R$style.Base_Widget_AppCompat_DrawerArrowToggle);
        setColor(r72.getColor(R$styleable.DrawerArrowToggle_color, 0));
        setBarThickness(r72.getDimension(R$styleable.DrawerArrowToggle_thickness, 0.0f));
        setSpinEnabled(r72.getBoolean(R$styleable.DrawerArrowToggle_spinBars, true));
        setGapSize(Math.round(r72.getDimension(R$styleable.DrawerArrowToggle_gapBetweenBars, 0.0f)));
        this.mSize = r72.getDimensionPixelSize(R$styleable.DrawerArrowToggle_drawableSize, 0);
        this.mBarLength = Math.round(r72.getDimension(R$styleable.DrawerArrowToggle_barLength, 0.0f));
        this.mArrowHeadLength = Math.round(r72.getDimension(R$styleable.DrawerArrowToggle_arrowHeadLength, 0.0f));
        this.mArrowShaftLength = r72.getDimension(R$styleable.DrawerArrowToggle_arrowShaftLength, 0.0f);
        r72.recycle();
    }

    private static float lerp(float r0, float r1, float r2) {
        return r0 + ((r1 - r0) * r2);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r19) {
        Rect r2 = getBounds();
        int r3 = this.mDirection;
        boolean r4 = false;
        if (r3 == 0) goto L13;
        if (r3 != 1) goto L6;
    L12:
        r4 = true;
        goto L13
    L6:
        if (r3 == 3) goto L11;
        if (DrawableCompat.getLayoutDirection(this) != 1) goto L13;
    L11:
        if (DrawableCompat.getLayoutDirection(this) == 0) goto L12;
    L13:
        float r32 = this.mArrowHeadLength;
        float r33 = lerp(this.mBarLength, (float) Math.sqrt((r32 * r32) * 2.0f), this.mProgress);
        float r7 = lerp(this.mBarLength, this.mArrowShaftLength, this.mProgress);
        float r8 = Math.round(lerp(0.0f, this.mMaxCutForBarSize, this.mProgress));
        float r9 = lerp(0.0f, ARROW_HEAD_ANGLE, this.mProgress);
        if (r4 == false) goto L16;
        float r11 = 0.0f;
    L18:
        if (r4 == false) goto L20;
        float r13 = 180.0f;
    L21:
        float r112 = lerp(r11, r13, this.mProgress);
        double r132 = r33;
        double r5 = r9;
        boolean r92 = r4;
        float r34 = Math.round(Math.cos(r5) * r132);
        float r42 = Math.round(r132 * Math.sin(r5));
        this.mPath.rewind();
        float r52 = lerp(this.mBarGap + this.mPaint.getStrokeWidth(), -this.mMaxCutForBarSize, this.mProgress);
        float r6 = (-r7) / 2.0f;
        this.mPath.moveTo(r6 + r8, 0.0f);
        this.mPath.rLineTo(r7 - (r8 * 2.0f), 0.0f);
        this.mPath.moveTo(r6, r52);
        this.mPath.rLineTo(r34, r42);
        this.mPath.moveTo(r6, -r52);
        this.mPath.rLineTo(r34, -r42);
        this.mPath.close();
        r19.save();
        float r35 = this.mPaint.getStrokeWidth();
        float r43 = r2.height() - (3.0f * r35);
        float r53 = this.mBarGap;
        r19.translate(r2.centerX(), ((((int) (r43 - (r53 * 2.0f))) / 4) * 2) + ((r35 * 1.5f) + r53));
        if (this.mSpin == true) goto L24;
        if (r92 == false) goto L30;
        r19.rotate(180.0f);
    L30:
        r19.drawPath(this.mPath, this.mPaint);
        r19.restore();
        return;
    L24:
        if ((this.mVerticalMirror ^ r92) == false) goto L26;
        int r54 = -1;
    L27:
        r19.rotate(r112 * r54);
        goto L30
    L26:
        r54 = 1;
        goto L27
    L20:
        r13 = 0.0f;
        goto L21
    L16:
        r11 = -180.0f;
        goto L18
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.mSize;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.mSize;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r2) {
        if (r2 == this.mPaint.getAlpha()) goto L6;
        this.mPaint.setAlpha(r2);
        invalidateSelf();
        return;
    }

    public void setBarThickness(float r5) {
        if (this.mPaint.getStrokeWidth() == r5) goto L6;
        this.mPaint.setStrokeWidth(r5);
        this.mMaxCutForBarSize = (float) ((r5 / 2.0f) * Math.cos(ARROW_HEAD_ANGLE));
        invalidateSelf();
        return;
    }

    public void setColor(int r2) {
        if (r2 == this.mPaint.getColor()) goto L6;
        this.mPaint.setColor(r2);
        invalidateSelf();
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r2) {
        this.mPaint.setColorFilter(r2);
        invalidateSelf();
    }

    public void setGapSize(float r2) {
        if (r2 == this.mBarGap) goto L6;
        this.mBarGap = r2;
        invalidateSelf();
        return;
    }

    public void setProgress(float r2) {
        if (this.mProgress == r2) goto L6;
        this.mProgress = r2;
        invalidateSelf();
        return;
    }

    public void setSpinEnabled(boolean r2) {
        if (this.mSpin == r2) goto L6;
        this.mSpin = r2;
        invalidateSelf();
        return;
    }
}
