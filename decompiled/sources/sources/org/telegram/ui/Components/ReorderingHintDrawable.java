package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.animation.Interpolator;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes7.dex */
public class ReorderingHintDrawable extends Drawable {
    private final Interpolator interpolator;
    private final int intrinsicHeight;
    private final int intrinsicWidth;
    private final RectDrawable primaryRectDrawable;
    private float scaleX;
    private float scaleY;
    private final RectDrawable secondaryRectDrawable;
    private long startedTime;
    private final Rect tempRect;

    public static class RectDrawable extends Drawable {
        private final Paint paint;
        private final RectF tempRect;

        public RectDrawable() {
            this.tempRect = new RectF();
            this.paint = new Paint(1);
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r3) {
            this.tempRect.set(getBounds());
            float r0 = this.tempRect.height() * 0.2f;
            r3.drawRoundRect(this.tempRect, r0, r0, this.paint);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -3;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
            this.paint.setAlpha(r1);
        }

        public void setColor(int r1) {
            this.paint.setColor(r1);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
            this.paint.setColorFilter(r1);
        }
    }

    public ReorderingHintDrawable() {
        this.tempRect = new Rect();
        this.interpolator = Easings.easeInOutSine;
        this.intrinsicWidth = AndroidUtilities.dp(24.0f);
        this.intrinsicHeight = AndroidUtilities.dp(24.0f);
        this.startedTime = -1;
        RectDrawable r0 = new RectDrawable();
        this.primaryRectDrawable = r0;
        r0.setColor(-2130706433);
        RectDrawable r02 = new RectDrawable();
        this.secondaryRectDrawable = r02;
        r02.setColor(-2130706433);
    }

    private void drawStage1(Canvas r6, float r7) {
        Rect r0 = getBounds();
        float r72 = this.interpolator.getInterpolation(r7);
        this.tempRect.left = (int) (AndroidUtilities.dp(2.0f) * this.scaleX);
        this.tempRect.bottom = r0.bottom - ((int) (AndroidUtilities.dp(6.0f) * this.scaleY));
        Rect r1 = this.tempRect;
        r1.right = r0.right - r1.left;
        r1.top = r1.bottom - ((int) (AndroidUtilities.dp(4.0f) * this.scaleY));
        this.secondaryRectDrawable.setBounds(this.tempRect);
        this.secondaryRectDrawable.draw(r6);
        Rect r02 = this.tempRect;
        int r12 = AndroidUtilities.dp(12.0f);
        r02.right = r12;
        r02.left = r12;
        Rect r03 = this.tempRect;
        int r13 = AndroidUtilities.dp(8.0f);
        r03.bottom = r13;
        r03.top = r13;
        this.tempRect.inset(-AndroidUtilities.dp(AndroidUtilities.lerp(10, 11, r72)), -AndroidUtilities.dp(AndroidUtilities.lerp(2, 3, r72)));
        this.primaryRectDrawable.setBounds(this.tempRect);
        this.primaryRectDrawable.setAlpha(AndroidUtilities.lerp(128, 255, r72));
        this.primaryRectDrawable.draw(r6);
    }

    private void drawStage2(Canvas r7, float r8) {
        Rect r0 = getBounds();
        float r82 = this.interpolator.getInterpolation(r8);
        this.tempRect.left = (int) (AndroidUtilities.dp(2.0f) * this.scaleX);
        this.tempRect.bottom = r0.bottom - ((int) (AndroidUtilities.dp(6.0f) * this.scaleY));
        Rect r1 = this.tempRect;
        r1.right = r0.right - r1.left;
        r1.top = r1.bottom - ((int) (AndroidUtilities.dp(4.0f) * this.scaleY));
        this.tempRect.offset(0, AndroidUtilities.dp(AndroidUtilities.lerp(0, -8, r82)));
        this.secondaryRectDrawable.setBounds(this.tempRect);
        this.secondaryRectDrawable.draw(r7);
        this.tempRect.left = (int) (AndroidUtilities.dpf2(AndroidUtilities.lerp(1, 2, r82)) * this.scaleX);
        this.tempRect.top = (int) (AndroidUtilities.dpf2(AndroidUtilities.lerp(5, 6, r82)) * this.scaleY);
        Rect r12 = this.tempRect;
        r12.right = r0.right - r12.left;
        r12.bottom = r12.top + ((int) (AndroidUtilities.dpf2(AndroidUtilities.lerp(6, 4, r82)) * this.scaleY));
        this.tempRect.offset(0, AndroidUtilities.dp(AndroidUtilities.lerp(0, 8, r82)));
        this.primaryRectDrawable.setBounds(this.tempRect);
        this.primaryRectDrawable.setAlpha(255);
        this.primaryRectDrawable.draw(r7);
    }

    private void drawStage3(Canvas r9, float r10) {
        Rect r0 = getBounds();
        float r102 = this.interpolator.getInterpolation(r10);
        this.tempRect.left = (int) (AndroidUtilities.dp(2.0f) * this.scaleX);
        this.tempRect.bottom = r0.bottom - ((int) (AndroidUtilities.dp(6.0f) * this.scaleY));
        Rect r1 = this.tempRect;
        r1.right = r0.right - r1.left;
        r1.top = r1.bottom - ((int) (AndroidUtilities.dp(4.0f) * this.scaleY));
        this.tempRect.offset(0, AndroidUtilities.dp(-8.0f));
        this.secondaryRectDrawable.setBounds(this.tempRect);
        this.secondaryRectDrawable.draw(r9);
        this.tempRect.left = (int) (AndroidUtilities.dpf2(2.0f) * this.scaleX);
        this.tempRect.top = (int) (AndroidUtilities.dpf2(6.0f) * this.scaleY);
        Rect r12 = this.tempRect;
        r12.right = r0.right - r12.left;
        r12.bottom = r12.top + ((int) (AndroidUtilities.dpf2(4.0f) * this.scaleY));
        this.tempRect.offset(0, AndroidUtilities.dp(8.0f));
        this.primaryRectDrawable.setBounds(this.tempRect);
        this.primaryRectDrawable.setAlpha(AndroidUtilities.lerp(255, 128, r102));
        this.primaryRectDrawable.draw(r9);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r7) {
        if (this.startedTime <= 0) goto L26;
        int r0 = (int) (System.currentTimeMillis() - this.startedTime);
        int r2 = r0 - 300;
        if (r2 >= 0) goto L7;
        drawStage1(r7, 0.0f);
    L24:
        invalidateSelf();
        return;
    L7:
        if (r2 >= 150) goto L9;
        drawStage1(r7, r2 / 150.0f);
        goto L24
    L9:
        int r22 = r0 - 750;
        if (r22 >= 0) goto L12;
        drawStage1(r7, 1.0f);
        goto L24
    L12:
        if (r22 >= 200) goto L14;
        drawStage2(r7, r22 / 200.0f);
        goto L24
    L14:
        int r23 = r0 - 1250;
        if (r23 < 0) goto L21;
        if (r23 >= 150) goto L18;
        drawStage3(r7, r23 / 150.0f);
        goto L24
    L18:
        drawStage3(r7, 1.0f);
        if ((r0 - 1400) < 100) goto L24;
        this.startedTime = System.currentTimeMillis();
        goto L24
    L21:
        drawStage2(r7, 1.0f);
        goto L24
    L26:
        drawStage1(r7, 0.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.intrinsicHeight;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.intrinsicWidth;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect r3) {
        this.scaleX = r3.width() / this.intrinsicWidth;
        this.scaleY = r3.height() / this.intrinsicHeight;
    }

    public void resetAnimation() {
        this.startedTime = -1;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r2) {
        this.primaryRectDrawable.setColorFilter(r2);
        this.secondaryRectDrawable.setColorFilter(r2);
        invalidateSelf();
    }

    public void startAnimation() {
        this.startedTime = System.currentTimeMillis();
        invalidateSelf();
    }
}
