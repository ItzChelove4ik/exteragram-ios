package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class ContextProgressView extends View {
    private RectF cicleRect;
    private int innerColor;
    private int innerKey;
    private Paint innerPaint;
    private long lastUpdateTime;
    private int outerColor;
    private int outerKey;
    private Paint outerPaint;
    private int radOffset;

    public ContextProgressView(Context r5, int r6) {
        super(r5);
        this.innerPaint = new Paint(1);
        this.outerPaint = new Paint(1);
        this.cicleRect = new RectF();
        this.radOffset = 0;
        Paint r52 = this.innerPaint;
        Paint.Style r1 = Paint.Style.STROKE;
        r52.setStyle(r1);
        this.innerPaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
        this.outerPaint.setStyle(r1);
        this.outerPaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
        this.outerPaint.setStrokeCap(Paint.Cap.ROUND);
        if (r6 != 0) goto L5;
        this.innerKey = Theme.key_contextProgressInner1;
        this.outerKey = Theme.key_contextProgressOuter1;
    L13:
        updateColors();
        return;
    L5:
        if (r6 != 1) goto L8;
        this.innerKey = Theme.key_contextProgressInner2;
        this.outerKey = Theme.key_contextProgressOuter2;
        goto L13
    L8:
        if (r6 != 2) goto L11;
        this.innerKey = Theme.key_contextProgressInner3;
        this.outerKey = Theme.key_contextProgressOuter3;
        goto L13
    L11:
        if (r6 != 3) goto L13;
        this.innerKey = Theme.key_contextProgressInner4;
        this.outerKey = Theme.key_contextProgressOuter4;
        goto L13
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.lastUpdateTime = System.currentTimeMillis();
        invalidate();
    }

    @Override // android.view.View
    public void onDraw(Canvas r11) {
        if (getVisibility() == 0) goto L5;
        return;
    L5:
        long r0 = System.currentTimeMillis();
        long r2 = r0 - this.lastUpdateTime;
        this.lastUpdateTime = r0;
        this.radOffset = (int) (this.radOffset + ((r2 * 360) / 1000.0f));
        this.cicleRect.set((getMeasuredWidth() / 2) - AndroidUtilities.dp(9.0f), (getMeasuredHeight() / 2) - AndroidUtilities.dp(9.0f), r0 + AndroidUtilities.dp(18.0f), r2 + AndroidUtilities.dp(18.0f));
        r11.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, AndroidUtilities.dp(9.0f), this.innerPaint);
        r11.drawArc(this.cicleRect, this.radOffset - 90, 90.0f, false, this.outerPaint);
        invalidate();
    }

    public void setColors(int r2, int r3) {
        this.innerKey = -1;
        this.outerKey = -1;
        this.innerColor = r2;
        this.outerColor = r3;
        updateColors();
    }

    @Override // android.view.View
    public void setVisibility(int r3) {
        super.setVisibility(r3);
        this.lastUpdateTime = System.currentTimeMillis();
        invalidate();
    }

    public void updateColors() {
        int r0 = this.innerKey;
        Paint r1 = this.innerPaint;
        if (r0 < 0) goto L5;
        r1.setColor(Theme.getColor(r0));
    L6:
        int r02 = this.outerKey;
        Paint r12 = this.outerPaint;
        if (r02 < 0) goto L9;
        r12.setColor(Theme.getColor(r02));
    L10:
        invalidate();
        return;
    L9:
        r12.setColor(this.outerColor);
        goto L10
    L5:
        r1.setColor(this.innerColor);
        goto L6
    }
}
