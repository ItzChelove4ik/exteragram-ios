package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.animation.DecelerateInterpolator;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes7.dex */
public abstract class CloseProgressDrawable2 extends Drawable {
    private float angle;
    private boolean animating;
    private int currentColor;
    private int globalColorAlpha;
    private DecelerateInterpolator interpolator;
    private long lastFrameTime;
    private Paint paint;
    private RectF rect;
    private int side;

    public CloseProgressDrawable2() {
        this(2.0f);
    }

    public CloseProgressDrawable2(float r3) {
        this.paint = new Paint(1);
        this.interpolator = new DecelerateInterpolator();
        this.rect = new RectF();
        this.globalColorAlpha = 255;
        this.paint.setColor(-1);
        this.paint.setStrokeWidth(AndroidUtilities.dp(r3));
        this.paint.setStrokeCap(Paint.Cap.ROUND);
        this.paint.setStyle(Paint.Style.STROKE);
        this.side = AndroidUtilities.dp(8.0f);
    }

    private void setColor(int r2) {
        if (this.currentColor == r2) goto L6;
        this.globalColorAlpha = Color.alpha(r2);
        int r22 = ColorUtils.setAlphaComponent(r2, 255);
        this.paint.setColor(r22);
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r17) {
        long r8 = System.currentTimeMillis();
        setColor(getCurrentColor());
        long r1 = this.lastFrameTime;
        float r11 = 0.0f;
        if (r1 == 0) goto L16;
        long r12 = r8 - r1;
        boolean r3 = this.animating;
        if (r3 == false) goto L7;
    L8:
        float r4 = this.angle + ((r12 * 360) / 500.0f);
        this.angle = r4;
        if (r3 == false) goto L11;
    L13:
        this.angle = r4 - (((int) (r4 / 720.0f)) * 720);
    L14:
        invalidateSelf();
        goto L16
    L11:
        if (r4 < 720.0f) goto L13;
        this.angle = 0.0f;
        goto L14
    L7:
        if (this.angle != 0.0f) goto L8;
    L16:
        if (this.globalColorAlpha != 255) goto L18;
    L21:
        Canvas r13 = r17;
        r13.save();
    L24:
        r13.translate(getIntrinsicWidth() / 2, getIntrinsicHeight() / 2);
        r13.rotate(-45.0f);
        float r2 = this.angle;
        if (r2 < 0.0f) goto L31;
        if (r2 >= 90.0f) goto L31;
        float r22 = 1.0f - (r2 / 90.0f);
        float r15 = 0.0f;
        float r132 = 1.0f;
    L29:
        float r14 = r132;
    L69:
        if (r22 == 0.0f) goto L72;
        r13.drawLine(0.0f, 0.0f, 0.0f, this.side * r22, this.paint);
    L72:
        if (r132 == 0.0f) goto L75;
        r17.drawLine((-this.side) * r132, 0.0f, 0.0f, 0.0f, this.paint);
    L75:
        if (r14 == 0.0f) goto L78;
        r17.drawLine(0.0f, (-this.side) * r14, 0.0f, 0.0f, this.paint);
    L78:
        if (r15 == 1.0f) goto L80;
        int r16 = this.side;
        r17.drawLine(r16 * r15, 0.0f, r16, 0.0f, this.paint);
    L80:
        r17.restore();
        int r18 = getBounds().centerX();
        int r23 = getBounds().centerY();
        RectF r32 = this.rect;
        int r42 = this.side;
        r32.set(r18 - r42, r23 - r42, r18 + r42, r23 + r42);
        RectF r24 = this.rect;
        float r19 = this.angle;
        if (r19 < 360.0f) goto L84;
        r11 = r19 - 360.0f;
    L84:
        float r33 = r11 - 45.0f;
        if (r19 < 360.0f) goto L88;
        r19 = 720.0f - r19;
    L88:
        r17.drawArc(r24, r33, r19, false, this.paint);
        this.lastFrameTime = r8;
        return;
    L31:
        if (r2 < 90.0f) goto L36;
        if (r2 >= 180.0f) goto L36;
        r132 = 1.0f - ((r2 - 90.0f) / 90.0f);
        r22 = 0.0f;
        r15 = 0.0f;
        r14 = 1.0f;
    L36:
        if (r2 < 180.0f) goto L41;
        if (r2 >= 270.0f) goto L41;
        r14 = 1.0f - ((r2 - 180.0f) / 90.0f);
        r22 = 0.0f;
        r132 = 0.0f;
        r15 = 0.0f;
    L41:
        if (r2 < 270.0f) goto L48;
        if (r2 >= 360.0f) goto L48;
        float r25 = (r2 - 270.0f) / 90.0f;
    L45:
        r15 = r25;
        r22 = 0.0f;
    L46:
        r132 = r22;
    L48:
        if (r2 < 360.0f) goto L53;
        if (r2 >= 450.0f) goto L53;
        r25 = 1.0f - ((r2 - 360.0f) / 90.0f);
    L53:
        if (r2 < 450.0f) goto L58;
        if (r2 >= 540.0f) goto L58;
        r22 = (r2 - 450.0f) / 90.0f;
        r132 = 0.0f;
        r14 = 0.0f;
        r15 = 0.0f;
    L58:
        if (r2 < 540.0f) goto L63;
        if (r2 >= 630.0f) goto L63;
        r132 = (r2 - 540.0f) / 90.0f;
        r14 = 0.0f;
        r15 = 0.0f;
        r22 = 1.0f;
    L63:
        if (r2 >= 630.0f) goto L65;
    L67:
        r15 = 0.0f;
        r22 = 1.0f;
        goto L46
    L65:
        if (r2 >= 720.0f) goto L67;
        r14 = (r2 - 630.0f) / 90.0f;
        r15 = 0.0f;
        r22 = 1.0f;
        r132 = 1.0f;
        goto L69
    L18:
        if (getBounds() == null) goto L21;
        if (getBounds().isEmpty() == true) goto L21;
        r13 = r17;
        r13.saveLayerAlpha(getBounds().left, getBounds().top, getBounds().right, getBounds().bottom, this.globalColorAlpha, 31);
        goto L24
    }

    public abstract int getCurrentColor();

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

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }

    public void setSide(int r1) {
        this.side = r1;
    }

    public void startAnimation() {
        this.animating = true;
        this.lastFrameTime = System.currentTimeMillis();
        invalidateSelf();
    }

    public void stopAnimation() {
        this.animating = false;
    }
}
