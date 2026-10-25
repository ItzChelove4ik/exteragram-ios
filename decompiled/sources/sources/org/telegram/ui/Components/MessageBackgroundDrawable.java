package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes7.dex */
public class MessageBackgroundDrawable extends Drawable {
    private boolean animationInProgress;
    private float currentAnimationProgress;
    private Paint customPaint;
    private float finalRadius;
    private boolean isSelected;
    private long lastAnimationTime;
    private long lastTouchTime;
    private Paint paint;
    private View parentView;
    private float touchOverrideX;
    private float touchOverrideY;
    private float touchX;
    private float touchY;

    public MessageBackgroundDrawable(View r3) {
        this.paint = new Paint(1);
        this.customPaint = null;
        this.touchX = -1.0f;
        this.touchY = -1.0f;
        this.touchOverrideX = -1.0f;
        this.touchOverrideY = -1.0f;
        this.parentView = r3;
    }

    private void calcRadius() {
        Rect r0 = getBounds();
        float r1 = r0.centerX();
        float r2 = r0.centerY();
        int r3 = r0.left;
        int r02 = r0.top;
        this.finalRadius = (float) Math.ceil(Math.sqrt(((r3 - r1) * (r3 - r1)) + ((r02 - r2) * (r02 - r2))));
    }

    private void invalidate() {
        View r0 = this.parentView;
        if (r0 == null) goto L8;
        r0.invalidate();
        if (this.parentView.getParent() == null) goto L9;
        ((ViewGroup) this.parentView.getParent()).invalidate();
        return;
    L9:
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r11) {
        float r0 = this.currentAnimationProgress;
        if (r0 != 1.0f) goto L10;
        Rect r02 = getBounds();
        Paint r2 = this.customPaint;
        if (r2 != null) goto L8;
        r2 = this.paint;
    L8:
        r11.drawRect(r02, r2);
    L32:
        if (this.animationInProgress == false) goto L47;
        long r4 = SystemClock.elapsedRealtime();
        long r6 = r4 - this.lastAnimationTime;
        if (r6 <= 20) goto L36;
        r6 = 17;
    L36:
        this.lastAnimationTime = r4;
        boolean r112 = this.isSelected;
        float r03 = this.currentAnimationProgress;
        if (r112 == false) goto L41;
        float r04 = r03 + (r6 / 240.0f);
        this.currentAnimationProgress = r04;
        if (r04 < 1.0f) goto L45;
        this.currentAnimationProgress = 1.0f;
    L44:
        this.touchX = -1.0f;
        this.touchY = -1.0f;
        this.touchOverrideX = -1.0f;
        this.touchOverrideY = -1.0f;
        this.animationInProgress = false;
    L45:
        invalidate();
        return;
    L41:
        float r05 = r03 - (r6 / 240.0f);
        this.currentAnimationProgress = r05;
        if (r05 > 0.0f) goto L45;
        this.currentAnimationProgress = 0.0f;
        goto L44
    L47:
        return;
    L10:
        if (r0 == 0.0f) goto L32;
        if (this.isSelected == false) goto L14;
        float r06 = CubicBezierInterpolator.EASE_OUT_QUINT.getInterpolation(r0);
    L15:
        Rect r22 = getBounds();
        float r42 = r22.centerX();
        float r23 = r22.centerY();
        float r5 = this.touchOverrideX;
        if (r5 < 0.0f) goto L20;
        float r62 = this.touchOverrideY;
        if (r62 < 0.0f) goto L20;
    L26:
        float r7 = 1.0f - r06;
        float r43 = r42 + ((r5 - r42) * r7);
        float r24 = r23 + (r7 * (r62 - r23));
        float r52 = this.finalRadius * r06;
        Paint r07 = this.customPaint;
        if (r07 != null) goto L30;
        r07 = this.paint;
    L30:
        r11.drawCircle(r43, r24, r52, r07);
    L20:
        r5 = this.touchX;
        if (r5 < 0.0f) goto L25;
        r62 = this.touchY;
        if (r62 >= 0.0f) goto L26;
    L25:
        r62 = r23;
        r5 = r42;
        goto L26
    L14:
        r06 = 1.0f - CubicBezierInterpolator.EASE_OUT_QUINT.getInterpolation(1.0f - r0);
        goto L15
    }

    public long getLastTouchTime() {
        return this.lastTouchTime;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    public float getTouchX() {
        return this.touchX;
    }

    public float getTouchY() {
        return this.touchY;
    }

    public boolean isAnimationInProgress() {
        return this.animationInProgress;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
        this.paint.setAlpha(r1);
    }

    @Override // android.graphics.drawable.Drawable
    public void setBounds(int r1, int r2, int r3, int r4) {
        super.setBounds(r1, r2, r3, r4);
        calcRadius();
    }

    @Override // android.graphics.drawable.Drawable
    public void setBounds(Rect r1) {
        super.setBounds(r1);
        calcRadius();
    }

    public void setColor(int r1) {
        this.paint.setColor(r1);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
        this.paint.setColorFilter(r1);
    }

    public void setCustomPaint(Paint r1) {
        this.customPaint = r1;
    }

    public void setSelected(boolean r4, boolean r5) {
        float r1 = 0.0f;
        if (this.isSelected == r4) goto L5;
        this.isSelected = r4;
        this.animationInProgress = r5;
        if (r5 == false) goto L14;
        this.lastAnimationTime = SystemClock.elapsedRealtime();
    L17:
        calcRadius();
        invalidate();
        return;
    L14:
        if (r4 == false) goto L16;
        r1 = 1.0f;
    L16:
        this.currentAnimationProgress = r1;
        goto L17
    L5:
        if (this.animationInProgress == r5) goto L19;
        if (r5 == true) goto L20;
        if (r4 == false) goto L9;
        r1 = 1.0f;
    L9:
        this.currentAnimationProgress = r1;
        this.animationInProgress = false;
        return;
    L20:
        return;
    }

    public void setTouchCoords(float r1, float r2) {
        this.touchX = r1;
        this.touchY = r2;
        this.lastTouchTime = SystemClock.elapsedRealtime();
    }

    public void setTouchCoordsOverride(float r1, float r2) {
        this.touchOverrideX = r1;
        this.touchOverrideY = r2;
    }
}
