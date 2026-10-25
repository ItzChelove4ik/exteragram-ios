package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes7.dex */
public class InfiniteProgress {
    private RectF cicleRect;
    private float currentCircleLength;
    private float currentProgressTime;
    private long lastUpdateTime;
    private int progressColor;
    private Paint progressPaint;
    private float radOffset;
    private int radius;
    private boolean risingCircleLength;

    public InfiniteProgress(int r2) {
        this.cicleRect = new RectF();
        this.radius = r2;
        Paint r22 = new Paint(1);
        this.progressPaint = r22;
        r22.setStyle(Paint.Style.STROKE);
        this.progressPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    private void updateAnimation() {
        long r0 = System.currentTimeMillis();
        long r2 = r0 - this.lastUpdateTime;
        if (r2 <= 17) goto L5;
        r2 = 17;
    L5:
        this.lastUpdateTime = r0;
        this.radOffset = (this.radOffset + ((360 * r2) / 2000.0f)) - (((int) (r0 / 360.0f)) * 360);
        float r02 = this.currentProgressTime + r2;
        this.currentProgressTime = r02;
        if (r02 < 500.0f) goto L9;
        this.currentProgressTime = 500.0f;
    L9:
        if (this.risingCircleLength == false) goto L11;
        this.currentCircleLength = (AndroidUtilities.accelerateInterpolator.getInterpolation(this.currentProgressTime / 500.0f) * 266.0f) + 4.0f;
    L13:
        if (this.currentProgressTime != 500.0f) goto L19;
        boolean r03 = this.risingCircleLength;
        if (r03 == false) goto L17;
        this.radOffset += 270.0f;
        this.currentCircleLength = -266.0f;
    L17:
        this.risingCircleLength = !r03;
        this.currentProgressTime = 0.0f;
        return;
    L19:
        return;
    L11:
        this.currentCircleLength = 4.0f - ((1.0f - AndroidUtilities.decelerateInterpolator.getInterpolation(this.currentProgressTime / 500.0f)) * 270.0f);
        goto L13
    }

    public void draw(Canvas r7, float r8, float r9, float r10) {
        RectF r0 = this.cicleRect;
        int r1 = this.radius;
        r0.set(r8 - (r1 * r10), r9 - (r1 * r10), r8 + (r1 * r10), r9 + (r1 * r10));
        this.progressPaint.setStrokeWidth(AndroidUtilities.dp(2.0f) * r10);
        r7.drawArc(this.cicleRect, this.radOffset, this.currentCircleLength, false, this.progressPaint);
        updateAnimation();
    }

    public void setAlpha(float r2) {
        this.progressPaint.setAlpha((int) (r2 * Color.alpha(this.progressColor)));
    }

    public void setColor(int r1) {
        this.progressColor = r1;
        this.progressPaint.setColor(r1);
    }
}
