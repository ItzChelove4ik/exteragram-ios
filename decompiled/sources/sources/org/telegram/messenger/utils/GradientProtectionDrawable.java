package org.telegram.messenger.utils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import androidx.core.graphics.ColorUtils;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes.dex */
public class GradientProtectionDrawable extends Drawable {
    public static final Interpolator DEFAULT_INTERPOLATOR = null;
    private int mAlpha;
    private int mColor;
    private final int[] mColors;
    private final GradientDrawable mDrawable;
    private final Rect mInsets;
    private final Interpolator mInterpolator;
    private final Paint mPaint;
    private int mSide;

    static {
        DEFAULT_INTERPOLATOR = new PathInterpolator(0.42f, 0.0f, 0.58f, 1.0f);
    }

    public GradientProtectionDrawable(int r4) {
        this(r4, 0, DEFAULT_INTERPOLATOR, 8);
    }

    public GradientProtectionDrawable(int r3, int r4, Interpolator r5, int r6) {
        this.mPaint = new Paint(1);
        this.mInsets = new Rect();
        this.mAlpha = 255;
        this.mDrawable = new GradientDrawable();
        this.mInterpolator = r5;
        this.mColors = new int[r6];
        setSide(r3);
        setColor(r4);
    }

    public static void fillColors(Interpolator r5, int r6, int[] r7) {
        int r0 = r7.length - 1;
        int r1 = Color.alpha(r6);
        int r2 = r0;
    L3:
        if (r2 < 0) goto L5;
        r7[r2] = ColorUtils.setAlphaComponent(r6, (int) (r5.getInterpolation((r0 - r2) / r0) * r1));
        r2 = r2 - 1;
        goto L3
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r17) {
        Rect r1 = getBounds();
        if (r1.isEmpty() == true) goto L25;
        int r2 = this.mSide;
        if (r2 != 1) goto L10;
        if (this.mInsets.left <= 0) goto L10;
        r17.drawRect(r1.left, r1.top, Math.min(r1.right, r2 + r3), r1.bottom, this.mPaint);
    L10:
        if (r2 != 2) goto L15;
        if (this.mInsets.top <= 0) goto L15;
        r17.drawRect(r1.left, r1.top, r1.right, Math.min(r1.bottom, r2 + r3), this.mPaint);
    L15:
        if (r2 != 4) goto L20;
        if (this.mInsets.right <= 0) goto L20;
        r17.drawRect(Math.max(r1.left, r1.right - r3), r1.top, r1.right, r1.bottom, this.mPaint);
    L20:
        if (r2 != 8) goto L25;
        if (this.mInsets.bottom <= 0) goto L25;
        r17.drawRect(r1.left, Math.max(r1.top, r1.bottom - r2), r1.right, r1.bottom, this.mPaint);
    L25:
        if (this.mDrawable.getBounds().isEmpty() == true) goto L28;
        this.mDrawable.draw(r17);
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.mAlpha;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect r6) {
        super.onBoundsChange(r6);
        GradientDrawable r0 = this.mDrawable;
        int r1 = r6.left;
        Rect r5 = this.mInsets;
        r0.setBounds(r1 + r5.left, r6.top + r5.top, r6.right - r5.right, r6.bottom - r5.bottom);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r3) {
        this.mAlpha = r3;
        this.mDrawable.setAlpha(r3);
        this.mPaint.setColor(Theme.multAlpha(this.mColor, this.mAlpha / 255.0f));
    }

    public void setColor(int r3) {
        if (this.mColor != r3) goto L5;
        return;
    L5:
        this.mColor = r3;
        fillColors(this.mInterpolator, r3, this.mColors);
        this.mDrawable.setColors(this.mColors);
        this.mPaint.setColor(Theme.multAlpha(this.mColor, this.mAlpha / 255.0f));
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r2) {
        this.mDrawable.setColorFilter(r2);
        this.mPaint.setColorFilter(r2);
    }

    public void setInsets(int r3, int r4, int r5, int r6) {
        Rect r0 = this.mInsets;
        if (r0.left == r3) goto L5;
    L12:
        r0.set(r3, r4, r5, r6);
        onBoundsChange(getBounds());
        return;
    L5:
        if (r0.top != r4) goto L12;
        if (r0.right != r5) goto L12;
        if (r0.bottom != r6) goto L12;
    }

    public void setSide(int r2) {
        this.mSide = r2;
        if (r2 != 1) goto L5;
        this.mDrawable.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
        return;
    L5:
        if (r2 != 2) goto L7;
        this.mDrawable.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
        return;
    L7:
        if (r2 != 4) goto L9;
        this.mDrawable.setOrientation(GradientDrawable.Orientation.RIGHT_LEFT);
        return;
    L9:
        if (r2 == 8) goto L11;
        return;
    L11:
        this.mDrawable.setOrientation(GradientDrawable.Orientation.BOTTOM_TOP);
    }
}
