package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.widget.Button;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class ProgressButton extends Button {
    private int angle;
    private boolean drawProgress;
    private long lastUpdateTime;
    private float progressAlpha;
    private final Paint progressPaint;
    private final RectF progressRect;

    public ProgressButton(Context r3) {
        super(r3);
        setAllCaps(false);
        setTextSize(1, 14.0f);
        setTypeface(AndroidUtilities.bold());
        setOutlineProvider(null);
        ViewHelper.setPadding(this, 8.0f, 0.0f, 8.0f, 0.0f);
        int r32 = AndroidUtilities.dp(60.0f);
        setMinWidth(r32);
        setMinimumWidth(r32);
        this.progressRect = new RectF();
        Paint r33 = new Paint(1);
        this.progressPaint = r33;
        r33.setStrokeCap(Paint.Cap.ROUND);
        r33.setStyle(Paint.Style.STROKE);
        r33.setStrokeWidth(AndroidUtilities.dp(2.0f));
    }

    @Override // android.widget.TextView, android.view.View
    public void onDraw(Canvas r10) {
        super.onDraw(r10);
        if (this.drawProgress == false) goto L5;
    L8:
        this.progressRect.set(getMeasuredWidth() - AndroidUtilities.dp(11.0f), AndroidUtilities.dp(3.0f), r0 + AndroidUtilities.dp(8.0f), AndroidUtilities.dp(11.0f));
        this.progressPaint.setAlpha(Math.min(255, (int) (this.progressAlpha * 255.0f)));
        r10.drawArc(this.progressRect, this.angle, 220.0f, false, this.progressPaint);
        long r2 = System.currentTimeMillis();
        if (Math.abs(this.lastUpdateTime - System.currentTimeMillis()) >= 1000) goto L22;
        long r4 = r2 - this.lastUpdateTime;
        int r102 = (int) (this.angle + ((360 * r4) / 2000.0f));
        this.angle = r102 - ((r102 / 360) * 360);
        boolean r103 = this.drawProgress;
        float r0 = this.progressAlpha;
        if (r103 == false) goto L18;
        if (r0 >= 1.0f) goto L22;
        float r02 = r0 + (r4 / 200.0f);
        this.progressAlpha = r02;
        if (r02 <= 1.0f) goto L22;
        this.progressAlpha = 1.0f;
        goto L22
    L18:
        if (r0 <= 0.0f) goto L22;
        float r03 = r0 - (r4 / 200.0f);
        this.progressAlpha = r03;
        if (r03 >= 0.0f) goto L22;
        this.progressAlpha = 0.0f;
    L22:
        this.lastUpdateTime = r2;
        postInvalidateOnAnimation();
        return;
    L5:
        if (this.progressAlpha != 0.0f) goto L8;
    }

    public void setBackgroundRoundRect(int r2, int r3) {
        setBackgroundRoundRect(r2, r3, 14.0f);
    }

    public void setBackgroundRoundRect(int r2, int r3, float r4) {
        setBackground(Theme.AdaptiveRipple.filledRect(r2, new float[]{r4}));
    }

    public void setDrawProgress(boolean r2, boolean r3) {
        if (this.drawProgress == r2) goto L12;
        this.drawProgress = r2;
        if (r3 == true) goto L10;
        if (r2 == false) goto L8;
        float r22 = 1.0f;
    L9:
        this.progressAlpha = r22;
        goto L10
    L8:
        r22 = 0.0f;
    L10:
        this.lastUpdateTime = System.currentTimeMillis();
        invalidate();
        return;
    }

    public void setProgressColor(int r1) {
        this.progressPaint.setColor(r1);
    }
}
