package org.telegram.ui.ActionBar;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.animation.DecelerateInterpolator;
import androidx.core.graphics.ColorUtils;
import org.mvel2.asm.Opcodes;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes3.dex */
public class BackDrawable extends Drawable {
    private boolean alwaysClose;
    private float animationTime;
    private int arrowRotation;
    private int color;
    private int currentAnimationTime;
    private float currentRotation;
    private float finalRotation;
    private DecelerateInterpolator interpolator;
    private long lastFrameTime;
    private Paint paint;
    private Paint prevPaint;
    private boolean reverseAngle;
    private boolean rotated;
    private int rotatedColor;

    public BackDrawable(boolean r4) {
        this.paint = new Paint(1);
        this.prevPaint = new Paint(1);
        this.interpolator = new DecelerateInterpolator();
        this.color = -1;
        this.rotatedColor = -9079435;
        this.animationTime = 300.0f;
        this.rotated = true;
        this.paint.setStrokeWidth(AndroidUtilities.dp(2.0f));
        this.paint.setStrokeCap(Paint.Cap.ROUND);
        this.prevPaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
        this.prevPaint.setColor(Opcodes.V_PREVIEW);
        this.alwaysClose = r4;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r13) {
        if (this.currentRotation != this.finalRotation) goto L5;
    L14:
        this.paint.setColor(ColorUtils.blendARGB(this.color, this.rotatedColor, this.currentRotation));
        r13.save();
        r13.translate(getIntrinsicWidth() / 2.0f, getIntrinsicHeight() / 2.0f);
        int r2 = this.arrowRotation;
        if (r2 == 0) goto L17;
        r13.rotate(r2);
    L17:
        float r22 = this.currentRotation;
        r13.translate(-AndroidUtilities.dp(0.66f), 0.0f);
        boolean r5 = this.alwaysClose;
        float r6 = this.currentRotation;
        if (r5 == true) goto L25;
        if (this.reverseAngle == false) goto L22;
        int r52 = -225;
    L23:
        r13.rotate(r6 * r52);
        float r8 = r22;
    L29:
        float r10 = 1.0f - r8;
        r13.drawLine(AndroidUtilities.dp(AndroidUtilities.lerp(-6.75f, -8.0f, r8)), 0.0f, AndroidUtilities.dp(8.0f) - ((this.paint.getStrokeWidth() / 2.0f) * r10), 0.0f, this.paint);
        float r11 = AndroidUtilities.dp(-0.25f);
        float r9 = AndroidUtilities.dp(AndroidUtilities.lerp(7.0f, 8.0f, r8)) - ((this.paint.getStrokeWidth() / 4.0f) * r10);
        float r1 = AndroidUtilities.dp(AndroidUtilities.lerp(-7.25f, 0.0f, r8));
        r13.drawLine(r1, -r11, 0.0f, -r9, this.paint);
        r13.drawLine(r1, r11, 0.0f, r9, this.paint);
        r13.restore();
        return;
    L22:
        r52 = 135;
        goto L23
    L25:
        if (this.reverseAngle == false) goto L27;
        int r23 = -180;
    L28:
        r13.rotate((r6 * r23) + 135.0f);
        r8 = 1.0f;
        goto L29
    L27:
        r23 = 180;
        goto L28
    L5:
        if (this.lastFrameTime == 0) goto L13;
        int r24 = this.currentAnimationTime + ((int) (System.currentTimeMillis() - this.lastFrameTime));
        this.currentAnimationTime = r24;
        float r4 = r24;
        float r53 = this.animationTime;
        if (r4 < r53) goto L9;
        this.currentRotation = this.finalRotation;
        goto L13
    L9:
        float r42 = this.currentRotation;
        float r62 = this.finalRotation;
        DecelerateInterpolator r63 = this.interpolator;
        if (r42 >= r62) goto L12;
        this.currentRotation = r63.getInterpolation(r24 / r53) * this.finalRotation;
        goto L13
    L12:
        this.currentRotation = 1.0f - r63.getInterpolation(r24 / r53);
    L13:
        this.lastFrameTime = System.currentTimeMillis();
        invalidateSelf();
        goto L14
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return AndroidUtilities.dp(24.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return AndroidUtilities.dp(24.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    public float getRotation() {
        return this.finalRotation;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
        this.paint.setAlpha(r1);
    }

    public void setAnimationTime(float r1) {
        this.animationTime = r1;
    }

    public void setArrowRotation(int r1) {
        this.arrowRotation = r1;
        invalidateSelf();
    }

    public void setColor(int r1) {
        this.color = r1;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
        this.paint.setColorFilter(r1);
    }

    public void setRotatedColor(int r1) {
        this.rotatedColor = r1;
        invalidateSelf();
    }

    public void setRotation(float r6, boolean r7) {
        this.lastFrameTime = 0;
        float r2 = this.currentRotation;
        if (r2 != 1.0f) goto L6;
        this.reverseAngle = true;
    L8:
        this.lastFrameTime = 0;
        if (r7 == false) goto L15;
        float r0 = this.animationTime;
        if (r2 >= r6) goto L13;
        this.currentAnimationTime = (int) (r2 * r0);
    L14:
        this.lastFrameTime = System.currentTimeMillis();
        this.finalRotation = r6;
    L16:
        invalidateSelf();
        return;
    L13:
        this.currentAnimationTime = (int) ((1.0f - r2) * r0);
        goto L14
    L15:
        this.currentRotation = r6;
        this.finalRotation = r6;
        goto L16
    L6:
        if (r2 != 0.0f) goto L8;
        this.reverseAngle = false;
        goto L8
    }
}
